package br.com.martins.vendas.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.ComissaoDAO;
import br.com.martins.vendas.dao.MercadoriaDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpGrpDeDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMeSmrDAO;
import br.com.martins.vendas.enums.DadoTemporario;
import br.com.martins.vendas.enums.TipoCobranca;
import br.com.martins.vendas.enums.TipoNegociacaoMercadoria;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.CalculoUtil;
import br.com.martins.vendas.services.calculodepreco.ComissaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.ECM;
import br.com.martins.vendas.vo.ItemPedidoTemporario;
import br.com.martins.vendas.vo.Kit;
import br.com.martins.vendas.vo.LivroPreco;
import br.com.martins.vendas.vo.LivroPrecoGrupamentoCliente;
import br.com.martins.vendas.vo.Mensagem;
import br.com.martins.vendas.vo.MensagemValorMinimoItem;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.RelacaoGiro;
import br.com.martins.vendas.vo.ValorMinimoItem;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class ItemPedidoService {
	
	private static final int NUM_CSA_DEC_DSC = 2;

	/**
	 * Classe cslIte, metodo BOOL clsIte::VerificaValorMinimoItemBeneficio, linha 4272
	 * @param percentualSimulacaoBeneficio 
	 * @param dDscBfcSml
	 * @param dblPerDscIte
	 * @param bFechamento
	 * @param dblVlrLiqNvo
	 * @param bReajusta
	 * @param mercadoria 
	 * @param relacaoGiro 
	 * @param flagPrecoEspecial 
	 * @param tipoNegocicaoMercadoria
	 * @param valorBrutoMercadoria
	 * @param valorBrutoCaixa
	 * @param valorBrutoFracionado
	 * @param flagUtilizaFracionado
	 * @param flagUtilizaMinimo
	 * @return
	 * @throws Exception 
	 */
	public static ValorMinimoItem verificaValorMinimoItemBeneficio(
			BigDecimal percentualDescontoBeneficio, 
			boolean bFechamento,
			boolean bReajusta, 
			ItemPedidoTemporario itemPedidoTemporario, 
			Cliente cliente) throws Exception {
				
		RelacaoGiro relacaoGiro = RelacaoGiroService.recuperarPorFilial(cliente.codigoTerritorio, itemPedidoTemporario.codigoFilialExpedicao, itemPedidoTemporario.codigoFilialFaturamento);
		LivroPreco livroPreco = LivroPrecoService.recupera(itemPedidoTemporario.codigoFilialExpedicao, itemPedidoTemporario.codigoFilialFaturamento, cliente.numeroRelacionamentoCliente, itemPedidoTemporario.codigoMercadoria);
		LivroPrecoGrupamentoCliente livroPrecoGrupamentoCliente = LivroPrecoGrupamentoClienteService.obtemLivroPrecoGrupamento(itemPedidoTemporario.codigoFilialExpedicao, itemPedidoTemporario.codigoFilialFaturamento, cliente.uf, cliente.codigoGrupoCliente, itemPedidoTemporario.codigoMercadoria);
		LivroPrecoService.obtemFlagsPrecoEspecial(cliente, livroPreco, livroPrecoGrupamentoCliente, RelacaoGiroService.isEstadoPrice(relacaoGiro));
		Mercadoria mercadoria = MercadoriaService.buscaMercadoria(itemPedidoTemporario.codigoMercadoria);
		
		if ((itemPedidoTemporario.tipoNegocicaoMercadoria != TipoNegociacaoMercadoria.VENDA.idTipoNegociacaoMercadoria) 
				|| (itemPedidoTemporario.valorBrutoMercadoria.doubleValue() <= 0.00) 
				|| (itemPedidoTemporario.valorBrutoCaixa.doubleValue() <= 0.00) 
				|| (itemPedidoTemporario.valorBrutoFracionado.doubleValue() <= 0.00) 
				|| (!livroPreco.flagUtilizaMinimoPrecoEspecial)){
			return null;
		}
		
		ValorMinimoItem valorMinimoItem = new ValorMinimoItem();
		
		int iQdeMer;
		double dblVlrMnmIte, dblVlrBnfSemEnc, dblPerAjt;
		
		Double dblPerDscAnt = itemPedidoTemporario.percentualDescontoFlexivel.doubleValue() - itemPedidoTemporario.percentualAcrescimoConcedido.doubleValue();
		Double dblPerDscIte = 0.00, dblVlrBrtTmp = itemPedidoTemporario.valorBrutoTemporario.doubleValue(), dblVlrLiqNvo;
		
		BigDecimal valorMercadoria = CalculoService.calculaAcrescimoVirtual(itemPedidoTemporario.valorBrutoMercadoria, itemPedidoTemporario.percentualAcrescimoVirtual);
		CalculoUtil.roundDown(valorMercadoria, 2);

		dblVlrMnmIte = CalculoService.obtemValorMinimoMercadoria(itemPedidoTemporario.codigoMercadoria, cliente.uf, itemPedidoTemporario.codigoFilialExpedicao, itemPedidoTemporario.codigoFilialFaturamento).doubleValue();
		
		CondicaoPagamento condicaoPagamento = CondicaoPagamentoService.buscaCondicaoPagamento(mercadoria, itemPedidoTemporario.codigoCondicaoPagamento);
		
		if (dblVlrMnmIte > 0.00){
			
			double dFator = 1.00;

			if (condicaoPagamento.tipoCobrancaCondicao != TipoCobranca.CASH.tipoCobranca){
				// O valor mínimo do item não considera os encargos
				// financeiros, para qualquer condição de pagamento
				
				boolean isFatorVendor = CondicaoPagamentoService.isUtilizadoFatorVendor(condicaoPagamento,
						mercadoria,
						cliente,
						relacaoGiro.codEstadoOrigem,
						relacaoGiro.codEstadoDestino);
				
				if (isFatorVendor){
					// Obtém o Fator de Encargos Vendor
					dFator = condicaoPagamento.fatorEncargoVendor.doubleValue();
				}else{
					// Obtém o Fator da Condição de Pagamento
					dFator = condicaoPagamento.fatorCondicaoPagamento.doubleValue();	

					//Adicionar o Fator Gap Vendor ao Fator da Condição - retirado 16/09/2010
					//dFator += P_CLSREP->ObtemFatorGapVendor();
				}
			}

			iQdeMer = itemPedidoTemporario.quantidadeMercadoria;
			if (iQdeMer <= 0)
			{
				iQdeMer = 1;
			}

			dblVlrMnmIte = dblVlrMnmIte * iQdeMer;

			dblVlrBnfSemEnc = (CalculoUtil.roundDown((itemPedidoTemporario.valorLiquidoMercadoria.doubleValue() / dFator), NUM_CSA_DEC_DSC).doubleValue() * iQdeMer) - (CalculoUtil.roundDown(((percentualDescontoBeneficio.doubleValue() / 100) * (CalculoUtil.roundDown((itemPedidoTemporario.valorLiquidoMercadoria.doubleValue() / dFator), NUM_CSA_DEC_DSC).doubleValue() * iQdeMer)), NUM_CSA_DEC_DSC)).doubleValue();
			
			if (dblVlrBnfSemEnc <= 0.00)
			{
				dblVlrBnfSemEnc = 0.01;
			}

			if (dblVlrBnfSemEnc < dblVlrMnmIte){

				dblPerDscIte = dblPerDscAnt;
				dblPerAjt = CalculoUtil.roundDown((dblVlrMnmIte / dblVlrBnfSemEnc), 4).doubleValue();
				dblPerAjt = ((1 - dblPerAjt) * 100) * 2;

				while (dblVlrBnfSemEnc != dblVlrMnmIte){
					dblPerAjt = CalculoUtil.roundFloor((dblPerAjt / 2), NUM_CSA_DEC_DSC).doubleValue();

					if (dblVlrBnfSemEnc < dblVlrMnmIte){
						
						valorMinimoItem.isAbaixoValorMinimo = true;
						
						if (dblPerAjt <= 0.00)
						{
							if (dblPerAjt == 0.00)
							{
								dblPerAjt = -0.01;
							}

							dblPerDscIte += dblPerAjt;
						}
						else
						{
							dblPerDscIte -= dblPerAjt;
						}
					}
					else
					{
						if (dblPerAjt < 0.00)
						{
							dblPerDscIte -= dblPerAjt;
						}
						else
						{
							if (dblPerAjt == 0.00)
							{
								dblPerAjt = 0.01;
							}

							dblPerDscIte += dblPerAjt;
						}
					}

					dblVlrBnfSemEnc = (CalculoUtil.roundDown((CalculoUtil.roundUP((dblVlrBrtTmp * (1 - (dblPerDscIte / 100))), NUM_CSA_DEC_DSC).doubleValue() / dFator), NUM_CSA_DEC_DSC).doubleValue() * iQdeMer) - (CalculoUtil.roundDown(((percentualDescontoBeneficio.doubleValue() / 100) * (CalculoUtil.roundDown((CalculoUtil.roundUP((dblVlrBrtTmp * (1 - (dblPerDscIte / 100))), NUM_CSA_DEC_DSC).doubleValue() / dFator), NUM_CSA_DEC_DSC).doubleValue() * iQdeMer)), NUM_CSA_DEC_DSC)).doubleValue();
					if (dblVlrBnfSemEnc <= 0.00)
					{
						dblVlrBnfSemEnc = 0.01;
					}

					if ((dblVlrBnfSemEnc >= dblVlrMnmIte) && ((dblPerAjt == 0.01) || (dblPerAjt == -0.01)))
					{
						break;
					}
				}
				
				MensagemValorMinimoItem mensagemValorMinimoItem = new MensagemValorMinimoItem();
				if (! bFechamento){
					if (dblPerDscIte < -99.99){
						mensagemValorMinimoItem.mensagem = "Devido ao preço mínimo de venda do item, não será possível conceder este benefício!";
						mensagemValorMinimoItem.tipoMensagem = MensagemValorMinimoItem.MENSAGEM_NAO_POSSIVEL_CONCESSAO_BENEFICIO;
					}else{
						mensagemValorMinimoItem.mensagem = String.format("Com a concessão deste benefício, a variação de preço será reajustada de %.2f%% para %.2f%% neste item. Confirma ?", ((-1) * dblPerDscAnt), ((-1) * dblPerDscIte));
						mensagemValorMinimoItem.tipoMensagem = MensagemValorMinimoItem.MENSAGEM_CONFIRMACAO_VARIACAO_PRECO;
					}
				}else{
					if (dblPerDscIte < -99.99){
						mensagemValorMinimoItem.mensagem = "Devido ao preço mínimo de venda, não será possível conceder este benefício. Pedido sujeito à cortes!";
						mensagemValorMinimoItem.tipoMensagem = MensagemValorMinimoItem.MENSAGEM_NAO_POSSIVEL_CONCESSAO_BENEFICIO_SUJEITO_CORTES;
					}else{
						if (dblPerDscIte < 0.00){										
							if (itemPedidoTemporario.percentualDescontoFlexivel.doubleValue() > 0.00)
							{
								alteraFlexibilizacao(0.00, itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
							}
							alteraAcrescimoPreco((-1) * dblPerDscIte, itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
						}else{
							if (itemPedidoTemporario.percentualAcrescimoConcedido.doubleValue() > 0.00)
							{
								alteraAcrescimoPreco(0.00, itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
							}
							alteraFlexibilizacao(dblPerDscIte, itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
						}
					}
					
					//TODO: VERIFICAR A CHAMADA ABAIXO COM CESAR
					/*if (m_dblVlrStbMer > 0.00)
					{
						//ImpostoService.calculaImpostos(precoBruto, condicaoPagamento, cliente, mercadoria, siglaEstadoOrigem, siglaEstadoDestino, quantidadeMercadoriaPedido, quantidadeMercadoriaSimulada, valorBrutoTemp, numeroRelacaoCidade, codigoFilialFaturamento, codigoFilialExpedicao, valorDescontoBeneficio, codigoAtividade, item, tipoNegociacao)
						CalculaImpostos((clsStbMer *) P_OBJRGRNGC->m_pclsStbMer, (clsStbDtz *) P_OBJRGRNGC->m_pclsStbDtz, dblDscBfcIte);
					}*/
					
					dblVlrLiqNvo = ((itemPedidoTemporario.valorLiquidoMercadoria.doubleValue() * iQdeMer) - (CalculoUtil.roundDown(((percentualDescontoBeneficio.doubleValue() / 100) * (itemPedidoTemporario.valorLiquidoMercadoria.doubleValue() * iQdeMer)), NUM_CSA_DEC_DSC)).doubleValue());
					dblVlrLiqNvo = dblVlrLiqNvo + (itemPedidoTemporario.valorFreteMercadoria.doubleValue() * iQdeMer) + itemPedidoTemporario.valorSTBTotal.doubleValue() + itemPedidoTemporario.valorIPITotal.doubleValue();

					// Valor Unitário do Item
					valorMinimoItem.valorLiquidoNovo = CalculoUtil.roundDbl(((dblVlrLiqNvo / iQdeMer) / mercadoria.fatorConversaoUnitario), 4);

					//no primeiro momento, o valor do reajusta e falso
					//para que se descubra o valor original e monte a grid com as informacoes originais
					//para confirmacao de ajuste de valor de acordo com o preco minimo
					if (! bReajusta)
					{
						if (dblPerDscAnt < 0.00)
						{										
							if (itemPedidoTemporario.percentualDescontoFlexivel.doubleValue() > 0.00)
							{
								alteraFlexibilizacao(0.00, itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
							}

							alteraAcrescimoPreco((-1) * itemPedidoTemporario.percentualDescontoFlexivel.doubleValue(), itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
						}
						else
						{
							if (itemPedidoTemporario.percentualAcrescimoConcedido.doubleValue() > 0.00)
							{
								alteraAcrescimoPreco(0.00, itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
							}

							alteraFlexibilizacao(itemPedidoTemporario.percentualDescontoFlexivel.doubleValue(), itemPedidoTemporario, cliente, relacaoGiro, valorMinimoItem);
						}

						/*calculo do imposto ja foi feito
						 * if (itemPedidoTemporario.valorSTBMercadoria.doubleValue() > 0.00)
						{
							CalculaImpostos((clsStbMer *) P_OBJRGRNGC->m_pclsStbMer, (clsStbDtz *) P_OBJRGRNGC->m_pclsStbDtz);
						}*/
					}
					
				}
				
				valorMinimoItem.mensagemValorMinimoItem = mensagemValorMinimoItem;
				
				//guarda os valores para caso necessite continuar apos confirmacao do usuario
				valorMinimoItem.percentualDescontoFlexivel = itemPedidoTemporario.percentualDescontoFlexivel;
				valorMinimoItem.percentualAcrescimoConcedido = itemPedidoTemporario.percentualAcrescimoConcedido;
				valorMinimoItem.percentualDescontoItem = CalculoUtil.roundHalfEven(dblPerDscIte,2);
			}
		}
		return valorMinimoItem;
	}
	
	public static void alteraAcrescimoPreco(double percentualAcrescimo, ItemPedidoTemporario itemPedidoTemporario, Cliente cliente, RelacaoGiro relacaoGiro, ValorMinimoItem valorMinimoItem) throws Exception {
		
		BigDecimal percentualAcrescimoConcedidoTmp = itemPedidoTemporario.percentualAcrescimoConcedido;
		
		if (percentualAcrescimo > 99.99)
		{
			percentualAcrescimo = 99.99;
		}
		itemPedidoTemporario.percentualAcrescimoConcedido = CalculoUtil.roundUP(BigDecimal.valueOf(percentualAcrescimo), 2);
		obtemDadosDoLivro(itemPedidoTemporario, relacaoGiro, cliente.numeroRelacionamentoCliente);
		
		Item item = new Item();
		item.populaObjeto(itemPedidoTemporario);
		
		CalculoService.calculaPreco(item, cliente);
		
		itemPedidoTemporario.valorLiquidoMercadoria = item.valorLiquidoMercadoria;
		valorMinimoItem.item = item;
		
		itemPedidoTemporario.percentualAcrescimoConcedido = percentualAcrescimoConcedidoTmp;
		
		//TODO: VERIFICAR COM CESAR ABAIXO
		/*iVlrRet = CalculaPrecos();
		if (RN_OK == iVlrRet)
		{
			if (RN_OK == OnAlteraFlexibilizacao())
			{
				Atribui();
				iVlrRet = Grava();
			}
		}*/
	}
	
	/**
	 * Método int clsIte::AlteraFlexibilizacao(double dblPerFlx)
	 * Classe clsIte
	 * @param dblPerFlx
	 * @param itemPedidoTemporario
	 * @param valorMinimoItem 
	 * @param atualizaPercentual 
	 * @param numeroRelacionamentoCliente
	 * @throws Exception
	 */
	public static void alteraFlexibilizacao(double dblPerFlx, ItemPedidoTemporario itemPedidoTemporario, Cliente cliente, RelacaoGiro relacaoGiro, ValorMinimoItem valorMinimoItem) throws Exception {
		
		BigDecimal percentualDescontoFlexivelTmp = itemPedidoTemporario.percentualDescontoFlexivel;
		
		itemPedidoTemporario.percentualDescontoFlexivel = BigDecimal.valueOf(dblPerFlx);
		obtemDadosDoLivro(itemPedidoTemporario, relacaoGiro, cliente.numeroRelacionamentoCliente);
		
		Item item = new Item();
		item.populaObjeto(itemPedidoTemporario);
		
		CalculoService.calculaPreco(item, cliente);
		
		itemPedidoTemporario.valorLiquidoMercadoria = item.valorLiquidoMercadoria;
		valorMinimoItem.item = item;
		
		itemPedidoTemporario.percentualDescontoFlexivel = percentualDescontoFlexivelTmp;
		
		//TODO: VERIFICAR COM CESAR ABAIXO
		/*iVlrRet = CalculaPrecos();
		if (RN_OK == iVlrRet)
		{
			if (RN_OK == OnAlteraFlexibilizacao())
			{
				Atribui();
				iVlrRet = Grava();
			}
		}*/
	}
	
	/**
	 * Metodo void clsIte::ObtemDadosDoLivro()
	 * da classe clsIte.cpp
	 * @param itemPedidoTemporario
	 * @param relacaoGiro
	 * @param numeroRelacionamentoCliente
	 * @throws Exception
	 */
	private static void obtemDadosDoLivro(ItemPedidoTemporario itemPedidoTemporario, RelacaoGiro relacaoGiro, Integer numeroRelacionamentoCliente) throws Exception {
		LivroPreco livroPreco = LivroPrecoService.recupera(itemPedidoTemporario.codigoFilialExpedicao, itemPedidoTemporario.codigoFilialFaturamento, numeroRelacionamentoCliente, itemPedidoTemporario.codigoMercadoria);
		
		itemPedidoTemporario.valorBrutoMercadoria = livroPreco.valorUnitarioBruto;
		itemPedidoTemporario.valorBrutoCaixa = livroPreco.valorUnitarioBruto;
		itemPedidoTemporario.valorBrutoFracionado = livroPreco.valorUnitarioBruto;
		
		itemPedidoTemporario.valorLiquidoMercadoria = livroPreco.valorUnitarioBruto;
		itemPedidoTemporario.valorLiquidoCaixa = livroPreco.valorBrutoCaixa;
		
		itemPedidoTemporario.valorFreteMercadoria = BigDecimal.ZERO;
		itemPedidoTemporario.valorSTBMercadoria = BigDecimal.ZERO;
		itemPedidoTemporario.valorSTBTotal = BigDecimal.ZERO;
		itemPedidoTemporario.valorIPIMercadoria = BigDecimal.ZERO;
		
		if (itemPedidoTemporario.tipoNegocicaoMercadoria.compareTo(TipoNegociacaoMercadoria.VENDA.idTipoNegociacaoMercadoria) == 0)
		{
			if (RelacaoGiroService.isEstadoPrice(relacaoGiro))
			{
				//Estado Price nao possuem letra de flexivel
				itemPedidoTemporario.codigoPrecoFlexivel = "";
			}
			else
			{
				// Letra do Desconto Flexível
				//m_strCodFlxPco = struDdoLivPco.m_strCodFlxPco;
				itemPedidoTemporario.codigoPrecoFlexivel = livroPreco.codFlexivelPreco;
			}
		}
		else
		{
			itemPedidoTemporario.codigoPrecoFlexivel = "";
		}
		
		/* verificar caso necessario
		 * m_bTemDesc = FALSE;
		m_strCodSmbSit = struDdoLivPco.m_strCodSmbSit;

		if (m_pclsCli == NULL)
		{
			m_pclsCli = m_pclsPed->ObtemCliente();
		}

		m_pclsCli->ObtemSegmentoCliente(strCodSgmCli);
		m_pclsCli->ObtemDadosCidadeTerritorio(strCodTetVnd, strNomCid, strCodEstUni);

		if (TabSmbSbt.AbreTabela())
		{
			if (TabSmbSbt.Busca(strCodEstUni + strCodSgmCli + m_strCodSmbSit))
			{
				m_strCodSmbSit = TabSmbSbt.ObtemSimboloSubstituto();
			}

			TabSmbSbt.FechaTabela();
		}*/
	}
	
	public static List<Item> obtemItensPedido(String numeroPedido) throws Exception {
		Database db = DatabaseFactory.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select ITE.CODMER,ITE.STBTOTBNF,ITE.VLRSTBMER,ITE.CMSCNSMER,ITE.VLRSTBTOT,ITE.VLRSTBCPL,MER.DESMER,ITE.CODCNDPGT,ITE.QDEMERPED,");
		sql.append("ITE.PERACOTTC,ITE.PERMAXSMP,ITE.PERACOCLI,ITE.CODFLXPCO,ITE.CODPMC,ITE.VLRSEMENC,ITE.STBCPLTOT,ITE.IPITOTBNF,ITE.PERDSCFLX,");
		sql.append("ITE.VLRLIQMER,ITE.CODSMBSIT,ITE.TIPDTBMER,ITE.CODFILEPD,ITE.CODFILFAT,ITE.CODRGRDTB,ITE.INDRTCBFV,ITE.INDRTCBFB,ITE.VLRPTOMER,");
		sql.append("ITE.VLRFRTMER,ITE.PERECOBFC,ITE.PERDSCBFC,ITE.QDEPRZBFC,ITE.PERCMSADI,ITE.TIPESGMER,ITE.NUMNOTFSC,ITE.VLRBRTMER,");
		sql.append("ITE.CMSMAXMER,ITE.VLRDSCESP,ITE.TIPNGCMER,ITE.VLRBNFMER,ITE.INDMERKIT,ITE.VLRPTOBFC,ITE.CODCORMRGB,ITE.VLRIPITOT,ITE.PERACRCNS ");
		sql.append(" from PCAITE ITE ");
		sql.append(" left join CADMER MER ON ITE.CODMER = MER.CODMER");
		sql.append(" where ITE.NUMPED = " + numeroPedido);
		
		List<Map<String, String>> result = db.executeQuery(sql.toString());
		return criaListaItemPedidos(result);
	}

	public static List<Item> obtemItensPedidoSemana(Date inicioSemana, Date fimSemana) throws Exception {
		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		query.append(" select distinct ITE.CODMER, MER.DESMER ").append(" from PCAPED PED ").append(" join PCAITE ITE ON PED.NUMPED = ITE.NUMPED")
				.append(" join CADMER MER ON ITE.CODMER = MER.CODMER").append(" where PED.DATPED between ").append(DateUtil.formataData(inicioSemana, DateUtil.DEFAULT_DATE_DATABASE))
				.append(" and ").append(DateUtil.formataData(fimSemana, DateUtil.DEFAULT_DATE_DATABASE));
		List<Map<String, String>> result = db.executeQuery(query.toString());
		return criaListaItemPedidos(result);
	}

	private static List<Item> criaListaItemPedidos(List<Map<String, String>> itensPedido) throws Exception {
		Item item;
		List<Item> listaItensPedido = new ArrayList<Item>();
		
		for (Map<String, String> pedidoConsulta : itensPedido) {
			item = new Item();
			item.mercadoria.codigo = Util.getInteger(pedidoConsulta.get("CODMER"));
			item.mercadoria.descricao = pedidoConsulta.get("DESMER");
			item.condicaoPagamento.codigoCondicaoPagamento = Util.getInteger(pedidoConsulta.get("CODCNDPGT"));
			item.mercadoria.qtdMercadoriaPedida = Util.getInteger(pedidoConsulta.get("QDEMERPED"));
			item.notaFiscal = Util.getInteger(pedidoConsulta.get("NUMNOTFSC"));
			item.preco.valorBrutoMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRBRTMER"));
			item.percentualDescontoFlex = Util.getBigDecimal(pedidoConsulta.get("PERDSCFLX"));
			item.percentualDescontoAcaoTatica = Util.getBigDecimal(pedidoConsulta.get("PERACOTTC"));
			item.percentualDescontoSimplificado = Util.getBigDecimal(pedidoConsulta.get("PERMAXSMP"));
			item.comissao.percentualAcaoCliente = Util.getBigDecimal(pedidoConsulta.get("PERACOCLI"));
			item.preco.codigoFlexivelPreco = pedidoConsulta.get("CODFLXPCO");
			item.promocao.codigo = Util.getInteger(pedidoConsulta.get("CODPMC"));
			item.valorSemEncargos = Util.getBigDecimal(pedidoConsulta.get("VLRSEMENC"));
			item.preco.valorLiquidoMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRLIQMER"));
			item.preco.codigoSimboloSituacao = pedidoConsulta.get("CODSMBSIT");
			
			// TODO VERIFICAR SE item.tipoDistribuicaoMercadoria EH A MESMA COISA QUE item.codigoRegraDistribuicao,
			item.tipoDistribuicaoMercadoria = Util.getInteger(pedidoConsulta.get("TIPDTBMER"));
			item.codigoRegraDistribuicao = Util.getInteger(pedidoConsulta.get("CODRGRDTB"));
			
			item.codigoFilialExpedicao = Util.getInteger(pedidoConsulta.get("CODFILEPD"));
			item.codigoFilialFaturamento = Util.getInteger(pedidoConsulta.get("CODFILFAT"));
			item.mercadoria.valorFrete = Util.getBigDecimal(pedidoConsulta.get("VLRFRTMER"));
			item.percentualEconomicoBeneficio = Util.getBigDecimal(pedidoConsulta.get("PERECOBFC"));
			item.percentualDescontoBeneficio = Util.getBigDecimal(pedidoConsulta.get("PERDSCBFC"));
			item.condicaoPagamento.quantidadeDiaPrazo = Util.getInteger(pedidoConsulta.get("QDEPRZBFC"));
			item.preco.percentualComissaoAdicional = Util.getBigDecimal(pedidoConsulta.get("PERCMSADI"));
			item.mercadoria.tipoEsgotamento = pedidoConsulta.get("TIPESGMER");
			item.comissao.comissaoMaximaMercadoria = Util.getBigDecimal(pedidoConsulta.get("CMSMAXMER"));
			item.valorDescontoEspecial = Util.getBigDecimal(pedidoConsulta.get("VLRDSCESP"));
			item.tipoNegociacaoMercadoria = Util.getInteger(pedidoConsulta.get("TIPNGCMER"));
			item.mercadoria.valorBeneficio = Util.getBigDecimal(pedidoConsulta.get("VLRBNFMER"));
			item.mercadoria.indicaMercadoriaKit = Util.getInteger(pedidoConsulta.get("INDMERKIT"));
			item.mercadoria.valorPontosBonificacao = Util.getBigDecimal(pedidoConsulta.get("VLRPTOBFC"));
			item.codigoCorMargemB = Util.getInteger(pedidoConsulta.get("CODCORMRGB"));
			item.stb.valorSTBTotalBonificacao = Util.getBigDecimal(pedidoConsulta.get("STBTOTBNF"));
			item.stb.valorSTBUnitario = Util.getBigDecimal(pedidoConsulta.get("VLRSTBMER"));
			item.stb.valorSTBComplementar = Util.getBigDecimal(pedidoConsulta.get("VLRSTBCPL"));
			item.comissao.comissaoMercadoria = Util.getBigDecimal(pedidoConsulta.get("CMSCNSMER"));
			item.stb.valorSTBTotal = Util.getBigDecimal(pedidoConsulta.get("VLRSTBTOT"));
			item.stb.valorSTBComplementar = Util.getBigDecimal(pedidoConsulta.get("STBCPLTOT"));
			item.valorIPITotalBonificacao = Util.getBigDecimal(pedidoConsulta.get("IPITOTBNF"));
			item.indicaRestricaoBeneficioCustomizado = Util.getInteger(pedidoConsulta.get("INDRTCBFV"));
			item.indicaRestricaoBeneficioCustomizadoBrinde = Util.getInteger(pedidoConsulta.get("INDRTCBFB"));
			item.valorPontuacaoMercadoria = Util.getInteger(pedidoConsulta.get("VLRPTOMER"));
			item.valorIPITotal = Util.getBigDecimal(pedidoConsulta.get("VLRIPITOT"));
			item.percentualAcrescimoConcedido = Util.getBigDecimal(pedidoConsulta.get("PERACRCNS"));
			listaItensPedido.add(item);
		}
		return listaItensPedido;

	}

	public static List<Kit> buscaMercadoriasKit(Integer codigoKit) throws Exception {
		Database db = DatabaseFactory.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select mer.codmer,mer.desmer ");
		sql.append("from cadmer mer ");
		sql.append("inner join pcakit kit ");
		sql.append("on mer.codmer=kit.codmer ");
		sql.append("where kit.codmerkit= " + codigoKit);

		List<Map<String, String>> result = db.executeQuery(sql.toString());

		return criaListaKit(result);
	}

	private static List<Kit> criaListaKit(List<Map<String, String>> lista) throws Exception {

		Kit kit;
		List<Kit> listaKit = new ArrayList<Kit>();
		for (Map<String, String> k : lista) {
			kit = new Kit();
			kit.codigo = Util.getInteger(k.get("CODMER"));
			kit.descricao = k.get("DESMER");
			listaKit.add(kit);
		}
		return listaKit;
	}

	/**
	 * 
	 * @param item
	 * @param cliente
	 * @param quantidadeAnt
	 * @param quantidade
	 * @return
	 */
	public static Mensagem verificarQuantidade(Item item, Cliente cliente, Integer quantidadeAnt, Integer quantidade) {

		ECM ecm = MercadoriaDAO.recuperaQuantidadeMinima(
				item.codigoFilialExpedicao,
				item.codigoFilialFaturamento,
				item.mercadoria.codigo,
				cliente.codigoCliente,
				cliente.codigoTerritorio,
				item.mercadoria.qtdMercadoriaPedida,
				Constants.TRUE.equals(item.mercadoria.indicadorRestricaoKit)
		);

		Mensagem mensagem = verificarQuantidadeMinima(ecm, quantidadeAnt, quantidade);
		
// 		TRECHO COMENTADO CONFORME INSTRUÇÕES DO CESAR
//		if (mensagem == null) {
//			mensagem = verificarCaixaFechadaPotencial(item, cliente, quantidade);
//			if (mensagem == null) {
//				mensagem = new Mensagem();
//				mensagem.tipo  = Mensagem.TIPO.JS_NONE.value;
//				mensagem.valor = Util.getStringValue(quantidade);
//			}
//		}
		
		if (mensagem == null) {
			mensagem = new Mensagem();
			mensagem.tipo  = Mensagem.TIPO.JS_NONE.value;
			mensagem.valor = Util.getStringValue(quantidade);
		}
		
		return mensagem;
	}

	/**
	 * 
	 * @param ecm
	 * @param quantidadeAnt
	 * @param quantidade
	 * @return
	 */
	public static Mensagem verificarQuantidadeMinima(ECM ecm, Integer quantidadeAnt, Integer quantidade) {

		Mensagem mensagem = null;
		if (quantidade > 0) {
			
			if (Constants.FLG_ASTERISTICO.equals(ecm.flagMultiplicadorQuantidade)) {
				
				Integer resto = (ecm.quantidadeMininaVenda != 0) ? quantidade % ecm.quantidadeMininaVenda : 0;
				if (resto > 0) {
					
					if (quantidadeAnt > quantidade) {
						
						mensagem = new Mensagem();
						mensagem.tipo  = Mensagem.TIPO.JS_CONFIRM.value;
						mensagem.texto = String.format("A quantidade informada não é válida !!! Este produto deve ser negociado com uma quantidade múltipla de %2d unidades de venda. Deseja reduzir a quantidade para o valor múltiplo anterior ?", ecm.quantidadeMininaVenda);

						quantidade = quantidade - resto;
						if (quantidade < 0) {
							quantidade = 0;
						}
						mensagem.valor = Util.getStringValue(quantidade);
						mensagem.valorAbortado = Util.getStringValue(quantidadeAnt);
						
					} else {
						
						mensagem = new Mensagem();
						mensagem.tipo  = Mensagem.TIPO.JS_CONFIRM.value;
						mensagem.texto = String.format("A quantidade informada não é válida !!! Este produto deve ser negociado com uma quantidade múltipla de %2d unidades de venda. Deseja completar a quantidade para o próximo valor múltiplo ?", ecm.quantidadeMininaVenda);

						mensagem.valor = Util.getStringValue(quantidade + (ecm.quantidadeMininaVenda - resto));
						mensagem.valorAbortado = "0";
					}
				}
				
			} else {
				
				if (quantidade < ecm.quantidadeMininaVenda) {
					
					if (quantidadeAnt > quantidade) {
						
						mensagem = new Mensagem();
						mensagem.tipo  = Mensagem.TIPO.JS_ALERT.value;
						mensagem.texto = String.format("A quantidade informada não é válida !!! Este produto deve ser negociado com uma quantidade mínima de %2d unidades de venda. Estou reduzindo a quantidade da mercadoria para 0 (ZERO) !", ecm.quantidadeMininaVenda);
						mensagem.valor = "0";
						
					} else {
						
						mensagem = new Mensagem();
						mensagem.tipo  = Mensagem.TIPO.JS_CONFIRM.value;
						mensagem.texto = String.format("A quantidade informada não é válida !!! Este produto deve ser negociado com uma quantidade mínima de %2d unidades de venda. Deseja completar a quantidade do item para o valor mínimo ?", ecm.quantidadeMininaVenda);
						mensagem.valor = Util.getStringValue(ecm.quantidadeMininaVenda);
						mensagem.valorAbortado = Util.getStringValue(quantidadeAnt);
					}
				}
			}
		}
		
		return mensagem;
	}

	/**
	 * 
	 * @param ecm
	 * @param quantidadeAnt
	 * @param quantidade
	 * @return
	 */
	public static Mensagem verificarCaixaFechadaPotencial(Item item, Cliente cliente, Integer quantidade) {
		
		Mensagem mensagem = null;
		Integer quantidadeFornecedor = item.mercadoria.quantidadeCaixaFornecedor;
		
		if (quantidade > 0)
		{
			int resto;
			resto = (quantidadeFornecedor != 0) ? quantidade % quantidadeFornecedor : 0;

			if (resto > 0)
			{
				RelacaoGiro relacaoGiro = RelacaoGiroService.recuperarPorFilial(cliente.codigoTerritorio, item.codigoFilialExpedicao, item.codigoFilialFaturamento);
				
				br.com.martins.vendas.services.desconto.PercentualFlexibilizacao percentualFlexibilizacao = 
						ComissaoService.obtemPercentuaisFlexibilizacao(
								item.mercadoria, 
								relacaoGiro.codEstadoDestino,
								ComissaoDAO.obtemParametrosComissao().percentualRateioBandaInferior
				);
				
				BigDecimal dblFator = new BigDecimal(resto / quantidadeFornecedor * 100);
				if (dblFator.compareTo(percentualFlexibilizacao.percentualProximoCaixa) >= 0)
				{
					BigDecimal dblVlrUntImp = item.valorUnitarioComImposto;
					BigDecimal dblVlrUntCxaImp = item.valorUnitarioCaixaComImposto; 


					// Se a quantidade que falta para a próxima caixa atingir o limite de proximidade
					int quantidadeNova = quantidade + (quantidadeFornecedor - resto);


					if (dblVlrUntCxaImp.compareTo(dblVlrUntImp) == -1)
					{
						mensagem = new Mensagem();
						mensagem.tipo  = Mensagem.TIPO.JS_CONFIRM.value;
						mensagem.texto = String.format("%s\n\nA quantidade foi completada com mais %d unidades de venda,\nresultando em (mais) uma caixa fechada.\n\n'OK' confirma a quantidade %d, reduzindo o Preço Unit. para R$ %.4f.\n'Cancelar' mantém a quantidade %d.", item.mercadoria.descricao, quantidadeNova - quantidade, quantidadeNova, dblVlrUntCxaImp, quantidade);
						mensagem.valor         = Util.getStringValue(quantidadeNova);
						mensagem.valorAbortado = Util.getStringValue(quantidade);
					}
					else
					{
						mensagem = new Mensagem();
						mensagem.tipo  = Mensagem.TIPO.JS_CONFIRM.value;
						mensagem.texto = String.format("%s\n\nA quantidade foi completada com mais %d unidades de venda,\nresultando em (mais) uma caixa fechada.\n\nClique 'OK' para confirmar a quantidade %d.\nClique 'Cancelar' para manter a quantidade %d.", item.mercadoria.descricao, quantidadeNova - quantidade, quantidadeNova, quantidade);
						mensagem.valor         = Util.getStringValue(quantidadeNova);
						mensagem.valorAbortado = Util.getStringValue(quantidade);
					}
				}
			}
		}
		
		return mensagem;
	}
	
	/**
	 * 
	 * @param codigoMercadoria
	 * @return
	 */
	public static List<Item> obtemTodosItensTemporarios() {
		return TabelaTmpGrpDeDAO.obtemTodosItens();
	}
	
	/**
	 * Recupera item da tabela temporaria grupo de pesquisa
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static Item obtemItemTemporario(Integer codigoMercadoria) {
		return obtemItemTemporario(codigoMercadoria, DadoTemporario.TMPGRPDE);
	}
	
	/**
	 * Recuepra item de tabela temporaria de acordo com o enum idicador da tabela
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @param temp enum indicador de tabela temporaria
	 * 
	 * @return item
	 */
	public static Item obtemItemTemporario(Integer codigoMercadoria, DadoTemporario temp) {
		if (DadoTemporario.TMPMESMR.equals(temp)) {
			return TabelaTmpMeSmrDAO.obtemItem(codigoMercadoria);
		}
		return TabelaTmpGrpDeDAO.obtemItem(codigoMercadoria);
	}
	
	/**
	 * Recupera item da tabela temporaria grupo de pesquisa
	 * @param incluirNoPedido 
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static boolean atualizaItemTemporario(Item item, boolean incluirNoPedido) {
		
		boolean result = false;
		Item itemSimilar = TabelaTmpMeSmrDAO.obtemItem(item.mercadoria.codigo);
		if (itemSimilar != null) {
			result = TabelaTmpMeSmrDAO.alteraItem(item);
		} else {
			result = TabelaTmpGrpDeDAO.alteraItem(item);	
		}
		
		if (incluirNoPedido) {
			
			Item itemTemp = TabelaTmpItePeDAO.obtemItem(
					item.mercadoria.codigo,
					item.codigoFilialExpedicao,
					item.codigoFilialFaturamento,
					item.notaFiscal,
					item.tipoNegociacaoMercadoria);
			
			if (itemTemp == null) {
				
				result = TabelaTmpItePeDAO.insereItem(item);
				
			} else {
				
				result = TabelaTmpItePeDAO.alteraItem(item);
			}
			
		}
		return result;
	}
}