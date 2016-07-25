package br.com.martins.vendas.services.calculodepreco;

import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.CEM;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundDown;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundUP;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.com.martins.vendas.dao.CalculoPrecoDAO;
import br.com.martins.vendas.dao.ComissaoDAO;
import br.com.martins.vendas.dao.PromocaoDAO;
import br.com.martins.vendas.enums.TipoCobranca;
import br.com.martins.vendas.enums.TipoMargem;
import br.com.martins.vendas.enums.TipoNegociacao;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.services.RegraDistribuicaoService;
import br.com.martins.vendas.services.RelacaoGiroService;
import br.com.martins.vendas.services.RepresentanteService;
import br.com.martins.vendas.services.desconto.Desconto;
import br.com.martins.vendas.services.desconto.DescontoService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PercentualICMS;
import br.com.martins.vendas.vo.PercentualIPI;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RegraDistribuicao;
import br.com.martins.vendas.vo.RelacaoGiro;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;

/**
 * The Class CalculaPreco.
 */
public class CalculoService {
	
	private static final String	TAG	= CalculoService.class.getName();	
	private static final String FLAG_PRECO_ESPECIAL = "*";

	/**
	 * Campo FLGCLICSM na tabela PCACLI com valor '*'
	 */
	public static final String FLAG_CLIENTE_CONSUMIDOR = FLAG_PRECO_ESPECIAL;	
	
	/**
	 * Para variáveis que não podem ser inicializadas com BigDecimal.ZERO
	 */
	private static final BigDecimal	MENOS_UM = new BigDecimal(-1);
	private static BigDecimal percentualICMS = BigDecimal.ZERO;
		
	/**
	 * Calcula os precos brutos, liquidos e liquido a faturar dos itens;.
	 *
	 * @param item the item
	 * @param condicaoPagamento the condicao pagamento
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @param tipoNegociacao - vem da tela
	 * @param quantidadeMercadoria - vem da tela
	 * @param flagPrecoEspecial the flag preco especial
	 * @param numeroRelacaoCidade the numero relacao cidade
	 * @param cliente the cliente
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param quantidadeMercadoriasSimuladas the quantidade mercadorias simuladas
	 * @param valorDescontoBeneficio the valor desconto beneficio
	 * @param codigoAtividade the codigo atividade
	 * @param regraDistribuicao the regra distribuicao
	 * @param percentualAcrescimo the percentual acrescimo
	 * @param tipoVendaPedido the tipo venda pedido
	 * @param percentualDescontoConcedido the percentual desconto concedido
	 * @param codigoTerritorioVenda the codigo territorio venda
	 * @param representante the representante
	 * @return the item
	 * @throws IntegrationException the integration exception
	 */
	public static Item calculaPreco(final Item item, final Cliente cliente) throws IntegrationException {
		
		final CondicaoPagamento condicaoPagamento;
		final Representante representante;
		
		final Integer codigoFilialExpedicao, 
		codigoFilialFaturamento, 		
		tipoNegociacao, 
		quantidadeMercadoria, 
		numeroRelacaoCidade, 
		codigoAtividade, 
		codigoTerritorioVenda, 
		tipoVendaPedido;
		
		final String flagPrecoEspecial, 
		siglaEstadoDestino, 
		siglaEstadoOrigem;
		
		final BigDecimal valorDescontoBeneficio, 
		percentualAcrescimo, 
		percentualDescontoConcedido;
		
		Integer quantidadeMercadoriasSimuladas;
		RegraDistribuicao regraDistribuicao;
		RelacaoGiro relacaoGiro;
				
		try {			
			item.validaItemPedidoCalculoPreco();
			
			if(item.tipoNegociacaoMercadoria.equals(TipoNegociacao.FLEX_POUPE_CERTO.getValue())){
				item.condicaoPagamento.codigoCondicaoPagamento = 2000;
			}
			
			condicaoPagamento = CondicaoPagamentoService
					.buscaCondicaoPagamento(item.condicaoPagamento.codigoCondicaoPagamento);
			representante = RepresentanteService.findRepresentante();
			codigoFilialExpedicao = item.codigoFilialExpedicao;
			codigoFilialFaturamento = item.codigoFilialFaturamento;
			tipoNegociacao = item.tipoNegociacaoMercadoria;
			quantidadeMercadoria = item.quantidadeMercadoria;
			
			//LINHA 87 DA CLASSE PedidosMain m_ddoTelaPopup.m_iQdeMerSml = pclsIte->ObtemQuantidade();
			
			quantidadeMercadoriasSimuladas = quantidadeMercadoria;
			flagPrecoEspecial = cliente.flagPrecoEspecial;
			numeroRelacaoCidade = cliente.numeroRelacionamentoCliente;
			codigoAtividade = cliente.codigoAtividade; 
			siglaEstadoDestino = cliente.uf; 
			codigoTerritorioVenda = cliente.codigoTerritorio;
			regraDistribuicao = RegraDistribuicaoService.obtemRegraDistribuicao(cliente.codigoCliente, 
					codigoFilialExpedicao, 
					codigoFilialFaturamento);
			relacaoGiro = RelacaoGiroService.recuperarPorFilial(codigoTerritorioVenda, 
					codigoFilialExpedicao, 
					codigoFilialFaturamento);
			siglaEstadoOrigem = relacaoGiro.codEstadoOrigem;
			valorDescontoBeneficio = item.valorDescontoBeneficio;
			percentualAcrescimo = item.percentualAcrescimoConcedido;
			//necessário negar o valor para que o mesmo seja positivo, pois se for negativo será um acrescimo.
			percentualDescontoConcedido = item.percentualDescontoConcedido.negate();
			tipoVendaPedido = item.tipoVendaPedido;
			
			if (quantidadeMercadoriasSimuladas <= 0){
				quantidadeMercadoriasSimuladas = Integer.valueOf(1);
			}
			
			Mercadoria mercadoria = item.mercadoria;
			
			/*
			 * Buscar informacoes complementares complementares
			 * - fatorConversaoUnitario 
			 */
			Mercadoria mercadoriaTMP = MercadoriaService.buscaMercadoria(mercadoria.codigo);
			mercadoria.fatorConversaoUnitario = mercadoriaTMP.fatorConversaoUnitario;
			mercadoria.tipo = mercadoriaTMP.tipo;
			mercadoria.codigoChaveCategoria = mercadoriaTMP.codigoChaveCategoria;
			
			item.codigoFilialFaturamento = codigoFilialFaturamento;
			
			BigDecimal valorBrutoBeneficio = BigDecimal.ZERO;
			BigDecimal valorBeneficioMercadoria;
			BigDecimal percentualAcrescimoVirtual = BigDecimal.ZERO;
			
			Preco preco = CalculoPrecoDAO
					.obtemPrecoBrutoMercadoriaNoLivroDePreco(mercadoria,
							codigoFilialExpedicao, 
							codigoFilialFaturamento,
							numeroRelacaoCidade);
			
			if (preco == null) {
				Item itemSemPreco = new Item();
				itemSemPreco.mercadoria = item.mercadoria;
				itemSemPreco.codigoFilialExpedicao = item.codigoFilialExpedicao;
				itemSemPreco.codigoFilialFaturamento = item.codigoFilialFaturamento;
				itemSemPreco.condicaoPagamento = item.condicaoPagamento;

				itemSemPreco.quantidadeMercadoria = item.quantidadeMercadoria;
				itemSemPreco.notaFiscal = item.notaFiscal;
				item.indicaRestricaoBeneficioCustomizado = mercadoria.temRestricaoBeneficioCustomizado;
				item.indicaRestricaoBeneficioCustomizadoBrinde = mercadoria.temRestricaoBeneficioCustomizadoNoBrinde;
				item.indicaRestricaoItemBrinde = mercadoria.temRestricaoBrinde;
				return itemSemPreco;
			}
			
			String simboloSubstituto =  ComissaoDAO.obtemCodigoSimboloSubstituto(cliente, preco.codigoSimboloSituacao);
			if(simboloSubstituto != null){
				preco.codigoSimboloSituacao = simboloSubstituto;
			}		
			
			// No fonte do martins as variaveis sao inicializados assim, com o valorBrutoUnitario(VLRUNTBRT)
			preco.valorBrutoMercadoria = preco.valorBrutoUnitario;
			preco.valorBrutoCaixa = preco.valorBrutoUnitario;
			preco.valorBrutoFracionado = preco.valorBrutoUnitario;

			preco.flagUtilizaAcaoTatica = BigDecimal.ONE.intValue();
			preco.flagUtilizaBeneficios = MENOS_UM.intValue();
			preco.flagUtilizaFlex = BigDecimal.ONE.intValue();
			preco.flagUtilizaFracionado = BigDecimal.ONE.intValue();
			preco.flagUtilizaMinimo = BigDecimal.ONE.intValue();
			
			boolean flagIndicaPrecoEspecial = false;
	
			if(flagPrecoEspecial.equals(FLAG_PRECO_ESPECIAL)){
				Preco precoEspecial = CalculoPrecoDAO.buscaPrecoEspecial(mercadoria.codigo,
						codigoFilialExpedicao, codigoFilialFaturamento,
						cliente.codigoGrupoCliente, siglaEstadoDestino);
	
				if(precoEspecial != null){
					flagIndicaPrecoEspecial = true;
					preco.flagUtilizaAcaoTatica = precoEspecial.flagUtilizaAcaoTatica;
					preco.flagUtilizaBeneficios = precoEspecial.flagUtilizaBeneficios;
					preco.flagUtilizaFlex = precoEspecial.flagUtilizaFlex;
					
					preco.flagUtilizaFracionado = precoEspecial.flagUtilizaFracionado;
					preco.flagUtilizaMinimo = precoEspecial.flagUtilizaMinimo;
					preco.valorBrutoUnitario = precoEspecial.valorBrutoUnitario;

					preco.valorBrutoMercadoria = precoEspecial.valorBrutoUnitario;
					preco.valorBrutoCaixa = precoEspecial.valorBrutoUnitario;
					preco.valorBrutoFracionado = precoEspecial.valorBrutoUnitario;
				}
			}
			
			if(flagIndicaPrecoEspecial){
				mercadoria.flagUtilizaMinimoPrecoEspecial = 1;
			}else {
				mercadoria.flagUtilizaMinimoPrecoEspecial = 0;
			}
			
			item.preco = preco;
			
//			item.valorLiquidoMercadoria = item.preco.valorBrutoMercadoria;
//			item.valorLiquidoCaixa = item.preco.valorBrutoCaixa;
			
			mercadoria.valorMinimoMercadoria = obtemValorMinimoMercadoria(mercadoria.codigo, 
					siglaEstadoDestino, 
					codigoFilialExpedicao, 
					codigoFilialFaturamento);
			
			boolean isCondicaoValida = CondicaoPagamentoService
					.verificaSeCondicaoDePagamentoValida(condicaoPagamento.codigoCondicaoPagamento, mercadoria);
			
			// busca na tabela de condicao de pagamento com o codigo de condicao de pegamento e o tipo da mercadoria.
			if(isCondicaoValida){
				if (!tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())) {
					if (tipoNegociacao.equals(TipoNegociacao.PROMOCAO.getValue())) {
						Promocao promocao = PromocaoDAO.buscaInformacoesPromocao(mercadoria.codigo);
						if(promocao != null){
							valorBrutoBeneficio = PromocaoDAO.obtemPremioDaPromocao(promocao.codigo, mercadoria.codigo);
						}
					} else {
						valorBrutoBeneficio = roundUP(preco.valorBrutoMercadoria
								.multiply(representante.percentualValorBonificacao.divide(CEM)), 2);
					}
				}		
			
				aplicaVendaConsumidor(preco, 
						cliente,
						mercadoria, 
						codigoAtividade, 
						siglaEstadoOrigem, 
						siglaEstadoDestino, 
						codigoFilialExpedicao, 
						codigoFilialFaturamento, 
						numeroRelacaoCidade, 
						flagIndicaPrecoEspecial);
				
				item.percentualICM = percentualICMS;
				
				BigDecimal fatorMargemAdicionalCanal = obtemFatorMargemCanal(cliente.canal, mercadoria);
				
				if(fatorMargemAdicionalCanal.doubleValue() > 0){
					if(!flagIndicaPrecoEspecial){
						preco.valorBrutoCaixa = BigDecimal.valueOf(preco.valorBrutoCaixa.doubleValue() 
								* fatorMargemAdicionalCanal.doubleValue());
						preco.valorBrutoCaixa = roundUP(preco.valorBrutoCaixa ,2);
						preco.valorBrutoFracionado = roundUP(preco.valorBrutoFracionado.multiply(fatorMargemAdicionalCanal), 2);
					}
				}
				
				obtemQuantidadesMinimasDeVendaDaMercadoria(mercadoria, codigoFilialExpedicao);
				
				boolean custoCaixa = false;
				if(mercadoria.codigoGrupoFracionado != null && mercadoria.codigoGrupoFracionado == 0){
					if(mercadoria.quantidadeCaixaFornecedor <= 1){
						custoCaixa = true;
					}
				}
				
				BigDecimal custoFracionado;
				if(preco.flagUtilizaFracionado == 1){
					custoFracionado = obtemCustoFracionado(mercadoria, codigoFilialExpedicao, preco.valorBrutoFracionado);
				}else {
					custoFracionado = BigDecimal.ZERO;
				}
				
				if(custoCaixa){
					preco.valorBrutoCaixa = preco.valorBrutoCaixa.add(custoFracionado);
				}
				
				preco.valorBrutoTMP = preco.valorBrutoMercadoria;
				
				if(condicaoPagamento.tipoCobrancaCondicao.equals(TipoCobranca.CASH.getValue())){
					preco.valorBrutoCaixa = preco.valorBrutoCaixa.multiply(condicaoPagamento.fatorCondicaoPagamento);
					
					if((mercadoria.valorMinimoMercadoria.doubleValue() > preco.valorBrutoCaixa.doubleValue()) 
							&& preco.flagUtilizaMinimo == 1){
						preco.valorBrutoCaixa = mercadoria.valorMinimoMercadoria;
						preco.valorBrutoFracionado = mercadoria.valorMinimoMercadoria;
						preco.valorBrutoFracionado = preco.valorBrutoFracionado.add(custoFracionado);
					} else {
						if(custoCaixa){
							preco.valorBrutoFracionado = preco.valorBrutoCaixa;
						} else {
							preco.valorBrutoFracionado = preco.valorBrutoFracionado.add(custoFracionado);
							preco.valorBrutoFracionado = preco.valorBrutoFracionado
									.multiply(condicaoPagamento.fatorCondicaoPagamento);
						}
					}
				}else {
					if((mercadoria.valorMinimoMercadoria.doubleValue() > preco.valorBrutoCaixa.doubleValue()) 
							&& preco.flagUtilizaMinimo == 1){
						preco.valorBrutoCaixa = mercadoria.valorMinimoMercadoria;
						preco.valorBrutoFracionado = mercadoria.valorMinimoMercadoria;
					}
					
					preco.valorBrutoFracionado = preco.valorBrutoFracionado.add(custoFracionado);
					preco.valorBrutoCaixa = preco.valorBrutoCaixa.multiply(condicaoPagamento.fatorCondicaoPagamento);
					preco.valorBrutoFracionado = preco.valorBrutoFracionado.multiply(condicaoPagamento.fatorCondicaoPagamento);
				}
				
				preco.valorBrutoCaixa = roundUP(preco.valorBrutoCaixa,2); 
				preco.valorBrutoFracionado = roundUP(preco.valorBrutoFracionado,2);
				
				int resto = (quantidadeMercadoria != 0) ? quantidadeMercadoria % mercadoria.quantidadeCaixaFornecedor : -1;
				
				if(resto == 0){
					preco.valorBrutoMercadoria = preco.valorBrutoCaixa;
				}else {
					preco.valorBrutoMercadoria = preco.valorBrutoFracionado;
				}
				
				if(! tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())){
					if(tipoNegociacao.equals(TipoNegociacao.PROMOCAO.getValue())){
						valorBeneficioMercadoria = BigDecimal.ZERO;
					}else {
						valorBeneficioMercadoria = preco.valorBrutoMercadoria.subtract(valorBrutoBeneficio);
					}
					 preco.valorBrutoMercadoria = valorBrutoBeneficio;
					 preco.valorBrutoCaixa = valorBrutoBeneficio;
					 preco.valorBrutoFracionado = valorBrutoBeneficio;
				}
				
				//Calcula o Preco bruto temporario
				preco.valorBrutoTMP = preco.valorBrutoMercadoria;
				
				if(mercadoria.fatorConversaoUnitario <= 0){
					mercadoria.fatorConversaoUnitario = 1;
				}
				preco.valorBrutoUnitario = roundUP(
						BigDecimal.valueOf(preco.valorBrutoMercadoria.doubleValue() 
						/ mercadoria.fatorConversaoUnitario), 4);
				
				//TODO colocar brinde flexivel aqui.
				
				calculaValorDoFrete(mercadoria, 
						codigoFilialExpedicao, 
						codigoFilialFaturamento, 
						regraDistribuicao, 
						cliente.codigoCliente, 
						condicaoPagamento, 
						preco, 
						item);
				
				if ((mercadoria.valorMinimoMercadoria.doubleValue() > 0) 
						&& (percentualAcrescimoVirtual.doubleValue() > 0)) {
					//Tenho que verificar o preco minimo novamente pois o AcrVtl altera com a caixa
					//Obsoleto depois que passamos a trocar o preço minimo 
				    //se o valor da caixa estiver abaixo do mesmo
					BigDecimal percentualMaximoDesconto = null;
					DescontoService.verificaValorMinimo(false, 
							item, 
							tipoNegociacao, 
							mercadoriaTMP,
							condicaoPagamento, 
							cliente, 
							siglaEstadoOrigem, 
							siglaEstadoDestino, 
							quantidadeMercadoria, 
							percentualAcrescimoVirtual, 
							codigoFilialFaturamento, 
							percentualMaximoDesconto,
							codigoFilialExpedicao);
				}
				
				if(tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())){
					if((percentualAcrescimo.doubleValue() > 0) || percentualAcrescimoVirtual.doubleValue() > 0){
						Item itemComAcrescimo = DescontoService.aplicaAcrecismoPreco(item, 
								percentualAcrescimo);
						
						item.valorLiquidoMercadoria = itemComAcrescimo.valorLiquidoMercadoria;
						item.valorLiquidoCaixa = itemComAcrescimo.valorLiquidoCaixa;
						item.preco = itemComAcrescimo.preco;
					}else {
						Item itemComPrecoLiquido = calculaPrecoLiquido(item, 
								codigoFilialExpedicao, 
								codigoFilialFaturamento,
								siglaEstadoOrigem,
								siglaEstadoDestino, 
								codigoAtividade, 
								cliente, 
								tipoVendaPedido, 
								tipoNegociacao, 
								codigoTerritorioVenda, 
								condicaoPagamento, 
								percentualDescontoConcedido, 
								mercadoria,
								quantidadeMercadoria,
								percentualAcrescimoVirtual);
						
						item.valorLiquidoMercadoria = itemComPrecoLiquido.valorLiquidoMercadoria;
						item.valorLiquidoCaixa = itemComPrecoLiquido.valorLiquidoCaixa;
						//item.preco = itemComPrecoLiquido.preco;
						item.desconto = itemComPrecoLiquido.desconto;
					}
				} else {
//					m_strPerVlrMnm = TEXT(" ");
					item.desconto.percentualDescontoAcaoTatica = BigDecimal.ZERO;
					item.desconto.percentualDescontoSimplificado = BigDecimal.ZERO;
					item.desconto.valorDescontoEspecial = BigDecimal.ZERO;
					item.desconto.percentualMinimoDesconto = BigDecimal.ZERO;
					item.desconto.valorDescontoIsencao = BigDecimal.ZERO;
					item.percentualAcrescimoConcedido = BigDecimal.ZERO;
					percentualAcrescimoVirtual = BigDecimal.ZERO;
					
					item.valorLiquidoMercadoria = preco.valorBrutoMercadoria;
					item.valorLiquidoCaixa = preco.valorBrutoCaixa;
				}
				
				item.comissao = ComissaoService.calculaComissaoMercadoria(mercadoria, 
						item,
						tipoNegociacao, 
						condicaoPagamento, 
						cliente, 
						siglaEstadoOrigem, 
						quantidadeMercadoria, 
						codigoFilialExpedicao, 
						representante,
						true, // isUltimoItem //TODO quando estiver totalizando pedido e for o ultimo item, passar como true.
						true, // isBruta
						true, // BOOL bComissaoDscIte, 
						true, // BOOL bComissaoDscPed, 
						true);// BOOL bComissaoBnfFlx,

				item.margem = calculaPontuacao(item, 
						codigoFilialExpedicao, 
						false, //indicaAtualizacaoMargemBeneficio 
						tipoNegociacao, 
						condicaoPagamento, 
						cliente, 
						siglaEstadoOrigem, 
						siglaEstadoDestino, 
						item.valorDescontoBeneficio, //percentualDescontoBeneficio
						representante); //valorBeneficioMercadoria
				
				ImpostoService.calculaImpostos(preco, 
						condicaoPagamento, 
						cliente, 
						mercadoria,
						siglaEstadoOrigem, 
						siglaEstadoDestino, 
						quantidadeMercadoria,
						quantidadeMercadoriasSimuladas,
						preco.valorBrutoTMP, 
						numeroRelacaoCidade,
						codigoFilialFaturamento, 
						codigoFilialExpedicao,
						valorDescontoBeneficio, 
						codigoAtividade,
						item, 
						tipoNegociacao);
					
				preco.valorBrutoCaixa = roundUP(
						preco.valorBrutoCaixa.doubleValue()
								/ mercadoria.fatorConversaoUnitario, 4);
								
				item.quantidadeMercadoria = quantidadeMercadoriasSimuladas;
				item.valorPontuacaoMercadoria = item.margem.valorPontuacaoMercadoria.intValue();
				item.valorPontoBeneficioMercadoria = item.margem.valorPontuacaoMercadoriaBeneficio.intValue();
				item.indicaItemImune = item.margem.indicaItemImune == false ? 0 : 1; // TODO AVALIAR MUDANCA DO ATRIBUTOS NO OBJETO ITEM
				
				System.out.println("Pontuacao: "+ item.valorPontuacaoMercadoria);
				System.out.println("Pontuacao Beneficio: "+ item.valorPontoBeneficioMercadoria);
				System.out.println("Percentual ICM : "+ item.percentualICM);
				
			} else {
				throw new Exception("Condição de pagamento não é válida para o item!");
			}
			
		return item;
		
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			throw new IntegrationException(e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * Calcula o preco lâ€™quido de mercadoria e aplica os descontos ou acrÅ½scimos
	 * @param preco 
	 * @param codigoFilialExpedicao 
	 * @param siglaEstadoDestino 
	 * @param codigoAtividade 
	 * @param cliente 
	 * @param tipoVendaPedido 
	 * @param tipoNegociacao 
	 * @param codigoTerritorioVenda 
	 * @param condicaoPagamento 
	 * @param percentualDescontoConcedido 
	 * @param mercadoria 
	 */
	public static Item calculaPrecoLiquido(Item item,
			Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			String siglaEstadoOrigem,
			String siglaEstadoDestino,
			Integer codigoAtividade, 
			Cliente cliente, 
			Integer tipoVendaPedido,
			Integer tipoNegociacao, 
			Integer codigoTerritorioVenda,
			CondicaoPagamento condicaoPagamento, 
			BigDecimal percentualDescontoConcedido, 
			Mercadoria mercadoria,
			Integer quantidadeMercadoriaPedido,
			BigDecimal percentualAcrescimoVirtual) {
		
		Item itemCalculado = new Item();
		
		Desconto desconto = DescontoService.obtemDescontos(item,
				codigoFilialExpedicao,
				codigoFilialFaturamento,
				siglaEstadoOrigem,
				siglaEstadoDestino, 
				codigoAtividade,
				cliente, 
				mercadoria, 
				tipoVendaPedido, 
				tipoNegociacao,
				codigoTerritorioVenda, 
				condicaoPagamento,
				quantidadeMercadoriaPedido,
				percentualAcrescimoVirtual);
		
		itemCalculado.valorLiquidoMercadoria = aplicaDescontoMercadoria(item.preco, 
				desconto.percentualMaximoDesconto, 
				percentualDescontoConcedido);
		itemCalculado.valorLiquidoCaixa = aplicaDescontoCaixa(item.preco, 
				desconto, 
				percentualDescontoConcedido);
		
		itemCalculado.desconto = desconto;
		
		return itemCalculado;

	}
	
	/**
	 * Aplica descontos a mercadoria.
	 * @param preco
	 * @param percentualMaximoDesconto
	 * @param percentualDescontoConcedido (Å½ escolhido na barra de desconto! da tela)
	 */
	public static BigDecimal aplicaDescontoMercadoria(Preco preco, 
			BigDecimal percentualMaximoDesconto, 
			BigDecimal percentualDescontoConcedido){
		
		BigDecimal precoLiquido = preco.valorBrutoMercadoria;
		//BigDecimal precoLiquidoCaixa = preco.valorBrutoCaixa;
		
		if(percentualDescontoConcedido.doubleValue() > 0){
			double fator = 1 - (percentualDescontoConcedido.doubleValue() / 100) ;
			precoLiquido = roundUP(precoLiquido.multiply(BigDecimal.valueOf(fator)),2);
			//precoLiquidoCaixa = roundUP(precoLiquidoCaixa.multiply(BigDecimal.valueOf(fator)),2);
		}
		
		preco.valorBrutoMercadoria = precoLiquido;
		
		return precoLiquido;	
		
	}
	
	/**
	 * Aplica descontos a mercadoria.
	 * @param preco
	 * @param percentualMaximoDesconto
	 * @param percentualDescontoConcedido (Å½ escolhido na barra de desconto! da tela)
	 */
	public static BigDecimal aplicaDescontoCaixa(Preco preco, 
			Desconto desconto, 
			BigDecimal percentualDescontoConcedido){
		
		BigDecimal precoLiquidoCaixa = preco.valorBrutoCaixa;
		BigDecimal percentualDescontoCaixa = desconto.percentualDescontoCaixa;
		
		/*Aplicar somente o maximo de desconto da caixa - isto é feito para mostra o valor da caixa,
		compativel com o valor minimo. */
		if (percentualDescontoCaixa.doubleValue() >= 0)	{		
			if (percentualDescontoConcedido.doubleValue() <= percentualDescontoCaixa.doubleValue()){
				if(percentualDescontoConcedido.doubleValue() > 0){
					double fator = 1 - (percentualDescontoConcedido.doubleValue() / 100) ;
					precoLiquidoCaixa = roundUP(precoLiquidoCaixa.multiply(BigDecimal.valueOf(fator)),2);
				}
			} else{
				if(desconto.percentualDescontoFlexivel.doubleValue() > 0){
					if (percentualDescontoCaixa.doubleValue() > 0) {
						double fator = 1 - (percentualDescontoCaixa.doubleValue() / 100) ;
						precoLiquidoCaixa = roundUP(precoLiquidoCaixa.multiply(BigDecimal.valueOf(fator)),2);
					}
				}
			}
		}
		
		preco.valorBrutoCaixa = precoLiquidoCaixa;
		
		return precoLiquidoCaixa;
	
	}
	
	/**
	 * Aplica os câ€¡lculos de consumidor.
	 * @param cliente
	 * @param mercadoria
	 * @param codigoAtividade
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @param codigoFilialExpedicao 
	 * @param codigoFilialFaturamento 
	 * @param numeroRelacaoCliente 
	 * @param codigoClassificacaoCliente
	 * @return 
	 */
	public static void aplicaVendaConsumidor(Preco preco, 
			Cliente cliente, 
			Mercadoria mercadoria, 
			Integer codigoAtividade, 
			String siglaEstadoOrigem, 
			String siglaEstadoDestino, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer numeroRelacaoCliente, 
			boolean flagIndicaPrecoEspecial){
		
		long tempoInicio = new Date().getTime();
						
		Integer tipoUtilizacaoICMS = 0;
		BigDecimal icmsOrigem = BigDecimal.ZERO;
		BigDecimal icmsMercadoria = BigDecimal.ZERO;
		BigDecimal icmsConsumidorAcordo = BigDecimal.ZERO;
		
		boolean isMercadoriaConsumo = verificaMercadoriaConsumo(siglaEstadoOrigem,
				siglaEstadoDestino,
				mercadoria,
				codigoAtividade);
		boolean isClienteConsumidor = verificaClienteConsumidor(cliente,
				mercadoria,
				codigoAtividade);
		
		BigDecimal percentualICM = ImpostoService.obtemPercentualICM(mercadoria, 
				codigoFilialExpedicao, 
				codigoFilialFaturamento, 
				cliente.numeroRelacionamentoCliente, 
				siglaEstadoDestino, 
				siglaEstadoOrigem, 
				isMercadoriaConsumo, 
				isClienteConsumidor, 
				tipoUtilizacaoICMS,
				cliente);
		
		if (isClienteConsumidor || isMercadoriaConsumo) {
			if (isClienteConsumidor) {
				isMercadoriaConsumo = false;
			}
			//OBTEM PERCENTUAL DE ICM
			PercentualICMS percentual = CalculoPrecoDAO.obtemPercentualICMDaMercadoria(mercadoria, 
					siglaEstadoOrigem, 
					siglaEstadoDestino);

			if (percentual != null) {
				if (isMercadoriaConsumo) {
					icmsOrigem = percentual.percentualICMSConsumo;
					tipoUtilizacaoICMS = percentual.tipoUtilizacaoICMS;
					icmsConsumidorAcordo = percentual.percentualICMConsumidorAcordo;
				}

				icmsMercadoria = percentual.percentualICMSMercadoria;
			}
			else {
				icmsOrigem = BigDecimal.ONE.negate();
				icmsMercadoria = BigDecimal.ONE.negate();
			}

			if (!isMercadoriaConsumo) {
				if(!siglaEstadoOrigem.equals("PB")){
					percentual = CalculoPrecoDAO.obtemPercentualICMDaMercadoria(mercadoria, 
							siglaEstadoOrigem, 
							siglaEstadoOrigem);
				}else {
					// Tare Importacao PB
					percentual = CalculoPrecoDAO.obtemPercentualICMDaMercadoria(mercadoria, 
							siglaEstadoOrigem, 
							siglaEstadoDestino);
				}
				if (percentual!= null) {
					icmsOrigem = percentual.percentualICMSConsumo;
					tipoUtilizacaoICMS = percentual.tipoUtilizacaoICMS;
					icmsConsumidorAcordo = percentual.percentualICMConsumidorAcordo;

					if ((tipoUtilizacaoICMS == 0) ||
						((tipoUtilizacaoICMS == 1) && ( siglaEstadoOrigem.equals(siglaEstadoDestino) )) ||
						((tipoUtilizacaoICMS == 2) && ( !siglaEstadoOrigem.equals(siglaEstadoDestino) )))
					{
						// projeto TARE importacao MG e PB
						// e reducao da base calculo de ICMS para clientes nao contribuintes.
						// icmsOrigem = percentual.percentualICMSConsumo;
						icmsOrigem = icmsConsumidorAcordo;
					
					}else {
						icmsOrigem = percentual.percentualICMSMercadoria;
					}
				}else {
					icmsOrigem = BigDecimal.ONE.negate();
				}
			}
			//FIM OBTEM PERCENTUAL DE ICM
			PercentualICMS percentualPadraoOrigem = CalculoPrecoDAO
					.obtemPercentualPadraoICMSConsumo(codigoFilialExpedicao,
							codigoFilialFaturamento, 
							numeroRelacaoCliente);

			if (icmsMercadoria.doubleValue() == -1) {
				icmsMercadoria = percentualPadraoOrigem.percentualICMSMercadoria;
			}

			if (icmsOrigem.doubleValue() == -1) {
				if (isMercadoriaConsumo) {
					icmsOrigem = percentualPadraoOrigem.percentualPadraoConsumo;
				} else {
					tipoUtilizacaoICMS = percentualPadraoOrigem.tipoUtilizacaoICMS;

					if ((tipoUtilizacaoICMS == 0) ||
						((tipoUtilizacaoICMS == 1) && ( siglaEstadoOrigem.equals(siglaEstadoDestino) )) ||
						((tipoUtilizacaoICMS == 2) && ( !siglaEstadoOrigem.equals(siglaEstadoDestino) )))
					{
						icmsOrigem = percentualPadraoOrigem.percentualPadraoConsumoOriginal;
					}
					else
					{
						icmsOrigem = percentualPadraoOrigem.percentualOriginal;
					}
				}
			}	
			
			if(!flagIndicaPrecoEspecial){
				FatorAjuste fatorAjuste = CalculoPrecoDAO.obtemFatorAjuste(siglaEstadoOrigem,
						siglaEstadoDestino,
						preco.codigoTributacaoICM);
				icmsOrigem = roundUP(icmsOrigem.multiply(fatorAjuste.fatorOrigem), 2);
				icmsMercadoria = roundUP(icmsMercadoria.multiply(fatorAjuste.fatorOrigem), 2);
			}
			
			if( (icmsMercadoria.doubleValue() != -1) && (icmsOrigem.doubleValue() != -1) ){
				BigDecimal ipiMercadoria = BigDecimal.ZERO;
				
				PercentualIPI ipi = CalculoPrecoDAO.obtemIPIMercadoria(mercadoria);
				if(ipi != null){
					ipiMercadoria = ipi.percentualIPI;
				}
				
				BigDecimal fatorAux = roundUP( icmsOrigem.divide(CEM)
						.multiply(BigDecimal.ONE.add((ipiMercadoria.divide(CEM))) ), 4);
				BigDecimal fator = roundUP( 
						BigDecimal.ONE.subtract(icmsMercadoria.divide(CEM)).doubleValue()
						/ (1 - fatorAux.doubleValue()), 4);
				
				preco.valorBrutoMercadoria = roundUP(preco.valorBrutoMercadoria.multiply(fator), 2);
				preco.valorBrutoCaixa = roundUP(preco.valorBrutoCaixa.multiply(fator), 2);
				preco.valorBrutoFracionado = roundUP(preco.valorBrutoFracionado.multiply(fator), 2);				
			}else {
				//TODO ERRO !
			}
		}else {		
			BigDecimal percentualReduzido = obtemPercentualPrecoReduzidoDaMercadoria(mercadoria,
					siglaEstadoOrigem,
					siglaEstadoDestino,
					cliente.codigoClassificacao);
			
			if (percentualReduzido.intValue() != -1){
				icmsOrigem = percentualICM;
			
				if(icmsOrigem.doubleValue() == -1){
					icmsOrigem = CalculoPrecoDAO.obtemPercentualICMSOrigem(codigoFilialExpedicao,
						codigoFilialFaturamento,
						numeroRelacaoCliente);
				}
				if(icmsMercadoria.doubleValue() == -1){
					icmsMercadoria = CalculoPrecoDAO.obtemPercentualPadraoICMSConsumo(codigoFilialExpedicao,
						codigoFilialFaturamento,
						numeroRelacaoCliente).percentualICMSMercadoria;
				}
			
				FatorAjuste fatorAjuste = CalculoPrecoDAO.obtemFatorAjuste(siglaEstadoOrigem,
						siglaEstadoDestino,
						mercadoria.codigoTributacaoMercadoria);
			
				icmsMercadoria = roundUP( icmsMercadoria.multiply(fatorAjuste.fatorDestino), 2 );
				percentualReduzido = roundUP( percentualReduzido.multiply(fatorAjuste.fatorDestino), 2 );
			
				if(icmsMercadoria.doubleValue() != -1 && percentualReduzido.doubleValue() != -1){
					BigDecimal fator = roundUP( (BigDecimal.ONE
							.subtract(icmsMercadoria.divide(CEM)))
							.divide(BigDecimal.ONE.subtract(percentualReduzido.divide(CEM))) ,4);
							preco.valorBrutoMercadoria = roundUP(preco.valorBrutoMercadoria
									.multiply(fator), 2);
							preco.valorBrutoCaixa = roundUP(preco.valorBrutoCaixa
									.multiply(fator), 2);
							preco.valorBrutoFracionado = roundUP(preco.valorBrutoFracionado
									.multiply(fator), 2);
				}
			}
		}
		
		System.out.println("CalculoPreco - aplicaVendaConsumidor - tempo Gasto: " 
				+ TimeUnit.MILLISECONDS.toMillis(new Date().getTime() - tempoInicio));
		percentualICMS = percentualICM;
	}
	
	
	
	/**
	 * Verifica se mercadoria Ã© de consumo.
	 *
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param mercadoria the mercadoria
	 * @param codigoAtividade the codigo atividade
	 * @return true, if successful
	 */
	private static boolean verificaMercadoriaConsumo(String siglaEstadoOrigem,
			String siglaEstadoDestino,
			Mercadoria mercadoria,
			Integer codigoAtividade){	
		return CalculoPrecoDAO.verificaSeMercadoriaConsumo(siglaEstadoDestino,
				siglaEstadoDestino,
				codigoAtividade,
				codigoAtividade);
	}
	
	/**
	 * 
	 * @param cliente
	 * @param mercadoria
	 * @param codigoAtividade
	 * @return
	 */
	private static boolean verificaClienteConsumidor(Cliente cliente,
			Mercadoria mercadoria, Integer codigoAtividade) {
		boolean isConsumidor = false;
		if(cliente.flagClienteConsumidor.equals(FLAG_CLIENTE_CONSUMIDOR)){
			isConsumidor = true;
		}else {
			if(mercadoriaTemIPI(mercadoria)){
				if(mercadoriaTemICMS(mercadoria, codigoAtividade)){
					isConsumidor = true;
				}			
			}		
		}		
		return isConsumidor;
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @return
	 */
	private static boolean mercadoriaTemIPI(Mercadoria mercadoria) {		
		return CalculoPrecoDAO.mercadoriaTemIPI(mercadoria);
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @param codigoAtividade
	 * @return
	 */
	private static boolean mercadoriaTemICMS(Mercadoria mercadoria, Integer codigoAtividade) {
		String codigoGrupoNCM = mercadoria.codigoGrupoNCM.substring(0,8);	
		return CalculoPrecoDAO.mercadoriaTemICMS(codigoGrupoNCM, codigoAtividade);		
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @param codigoClassificacaoCliente
	 * @return
	 */
	public static BigDecimal obtemPercentualPrecoReduzidoDaMercadoria(
			Mercadoria mercadoria, 
			String siglaEstadoOrigem,
			String siglaEstadoDestino, 
			String codigoClassificacaoCliente) {
		
		Double percentual = CalculoPrecoDAO.obtemPercentualPrecoReduzidoDaMercadoria(mercadoria,
								siglaEstadoOrigem, 
								siglaEstadoDestino,
								codigoClassificacaoCliente);
		return BigDecimal.valueOf(percentual);
	}


	/**
	 * 
	 * @param codigoCanalCliente
	 * @param mercadoria
	 */
	public static BigDecimal obtemFatorMargemCanal(Integer codigoCanalCliente, Mercadoria mercadoria) {
		BigDecimal fatorMargem = CalculoPrecoDAO.obtemMargemCanalCliente(mercadoria, codigoCanalCliente);
		
		return fatorMargem;
	}
	
	/**
	 * Obtem valor minimo da mercadoria.
	 * @param mercadoria
	 * @param siglaEstado
	 * @return
	 */
	public static BigDecimal obtemValorMinimoMercadoria(Integer codigoMercadoria, 
			String siglaEstado, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento){
		
		 BigDecimal precoMinimo = CalculoPrecoDAO.obtemPrecoMinimoDaMercadoria(codigoMercadoria, 
				 siglaEstado, 
				 codigoFilialExpedicao, 
				 codigoFilialFaturamento);
		 
		 return precoMinimo;
	}
	
	/**
	 * Obtem quantidade minima de venda.
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 */
	public static void obtemQuantidadesMinimasDeVendaDaMercadoria(Mercadoria mercadoria, Integer codigoFilialExpedicao){
		Map<String, String> dadosQuantidades = CalculoPrecoDAO.obtemQuantidadesMinimasDeVenda(mercadoria, codigoFilialExpedicao);
		for(String key : dadosQuantidades.keySet()){
			if(key.equalsIgnoreCase("QDEMNMVND")){
				mercadoria.quantidadeMinimaVenda = dadosQuantidades.get(key) == null ? 0 : Util.getInteger(dadosQuantidades.get(key));
			}else if (key.equalsIgnoreCase("FLGMPLQDE")){
				mercadoria.flagMultiplicadorQuantidade = dadosQuantidades.get(key);
			}else if (key.equalsIgnoreCase("CODGRPFRC")){
				mercadoria.codigoGrupoFracionado = dadosQuantidades.get(key) == null ? 0 : Util.getInteger(dadosQuantidades.get(key));
			}else if (key.equalsIgnoreCase("QDEMNMKIT")){
				mercadoria.quantidadeMinimaKit = dadosQuantidades.get(key) == null ? 0 : Util.getInteger(dadosQuantidades.get(key));
			}
		}
		
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @return
	 */
	public static BigDecimal obtemCustoFracionado(Mercadoria mercadoria,
			Integer codigoFilialExpedicao, 
			BigDecimal precoBrutoMercadoria) {
		BigDecimal custoFracionadoAplicado = BigDecimal.ZERO;

		Fracionamento custoFracionado = CalculoPrecoDAO.obtemCustoFracionado(
				codigoFilialExpedicao, mercadoria.codigo,
				precoBrutoMercadoria);

		if (custoFracionado.valorCustoFracionado.doubleValue() > 0) {
			custoFracionadoAplicado = custoFracionado.valorCustoFracionado;
		} else {
			custoFracionadoAplicado = precoBrutoMercadoria
					.multiply(custoFracionado.percentualCustoFracionado
							.divide(BigDecimal.valueOf(100)));
		}

		return custoFracionadoAplicado;
	}
	
	
	/**
	 * Metodo verificaValorMinimo chamado na clsIte linha 2171 e 2469
	 * @param mercadoria 
	 * @param siglaEstado 
	 * @param cliente 
	 * @param siglaEstadoOrigem 
	 * @param siglaEstadoDestino 
	*/
	
	
	/**
	 * 
	 * @param preco
	 * @param fator
	 * @param mercadoria
	 * @param percentualAcrescimoVirtual
	 */
	public static void substituiPrecoMinimo(Item item, 
			BigDecimal fator, 
			Mercadoria mercadoria, 
			BigDecimal percentualAcrescimoVirtual){
		BigDecimal valorSemEncargos;
		
		Preco preco = item.preco;
		
		if(fator.doubleValue() > 0){
			valorSemEncargos = roundDown(preco.valorBrutoTMP.doubleValue() / fator.doubleValue() , 2);
		} else {
			valorSemEncargos = preco.valorBrutoTMP;
		}
		
		if(mercadoria.valorMinimoMercadoria.doubleValue() > valorSemEncargos.doubleValue()){
			BigDecimal valorMercadoria = calculaAcrescimoVirtual(preco.valorBrutoMercadoria, percentualAcrescimoVirtual);
			valorMercadoria = roundUP(valorMercadoria, 2);
			
			BigDecimal valorCaixa =  calculaAcrescimoVirtual(preco.valorBrutoCaixa, percentualAcrescimoVirtual);
			valorCaixa = roundUP(valorCaixa, 2);
			
			preco.valorBrutoMercadoria = valorMercadoria;
			preco.valorBrutoCaixa = valorCaixa;
			
			item.valorLiquidoMercadoria = preco.valorBrutoMercadoria;
			item.valorLiquidoCaixa = preco.valorBrutoCaixa;
		}
	}
	
	/**
	 * Calcula o acrescimo 
	 * @param valor
	 * @param percentualAcrescimo
	 * @return
	 */
	public static BigDecimal calculaAcrescimoVirtual(BigDecimal valor, BigDecimal percentualAcrescimo){
		
		valor = valor.multiply(BigDecimal.ONE.add(percentualAcrescimo.divide(CEM)));
	
		return valor;
		
	}
	
	/**
	 * Calcula margem.
	 *
	 * @param item the item
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param indicaAtualizacaoMargemBeneficio the indica atualizacao margem beneficio
	 * @param tipoNegociacao the tipo negociacao
	 * @param condicaoPagamento the condicao pagamento
	 * @param cliente the cliente
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param percentualDescontoBeneficio the percentual desconto beneficio
	 * @param representante the representante
	 * @return the margem
	 */
	public static Margem calculaPontuacao(Item item,
			Integer codigoFilialExpedicao,
			boolean indicaAtualizacaoMargemBeneficio, 
			int tipoNegociacao,
			CondicaoPagamento condicaoPagamento, 
			Cliente cliente,
			String siglaEstadoOrigem, 
			String siglaEstadoDestino,
			BigDecimal percentualDescontoBeneficio, 
			Representante representante) {
		
		BigDecimal valorBeneficioMercadoria = item.valorBonificacao;
		
		BigDecimal percentualAcaoCliente = item.desconto.percentualDescontoAcao;
		BigDecimal percentualDescontoConcedido = item.percentualDescontoConcedido;
		BigDecimal percentualMaximoSimplificado = item.desconto.percentualDescontoSimplificado;
		BigDecimal percentualMaximoAcaoTatica = item.desconto.percentualDescontoAcaoTatica;
		
		Margem margem = new Margem();
		
		//dblVlrUntEftMer
		BigDecimal valorUnitarioEfetivoMercadoria = BigDecimal.ZERO;
				
		BigDecimal valorPontuacaoMercadoria = BigDecimal.ZERO;
		BigDecimal valorPontuacaoMercadoriaBeneficio = BigDecimal.ZERO;
		BigDecimal receitaLiquida = BigDecimal.ZERO;
		
		int codigoCorMargem = 0;
		int codigoCorMargemBeneficio = 0;
		boolean indicaItemImune = false;
				
		boolean isImune = CalculoPrecoDAO.verificaItemImuneDePontuacao(codigoFilialExpedicao, 
				item.mercadoria.codigo);
		boolean indicaVendaDireta = obtemIndicadorDaFilial(codigoFilialExpedicao);
		
		if(indicaVendaDireta || isImune 
				|| ( tipoNegociacao == TipoNegociacao.PROMOCAO.getValue() 
				&& (item.preco.valorBrutoMercadoria.doubleValue() <= 0 
				|| item.preco.valorBrutoCaixa.doubleValue() <= 0 
				|| item.preco.valorBrutoFracionado.doubleValue() <= 0)) ){
			
		    margem.codigoCorMargem = 0;
			margem.codigoCorMargemBeneficio = 0;
			margem.valorPontuacaoMercadoria = BigDecimal.ZERO;
			margem.valorPontuacaoMercadoriaBeneficio = BigDecimal.ZERO;
			margem.indicaItemImune = true;			
			return margem;
		}else {
			indicaItemImune = false;
		}
		
		BigDecimal fator = CondicaoPagamentoService.obtemFatorCondicaoPagamento(condicaoPagamento,
				item.mercadoria,
				cliente,
				siglaEstadoOrigem,
				siglaEstadoDestino);
		
		BigDecimal valorLiquidoMercadoria = item.valorLiquidoMercadoria;
		BigDecimal valorUnitarioEfetivoAux = roundDown(valorLiquidoMercadoria.doubleValue() / fator.doubleValue(), 2);
		BigDecimal valorUnitarioBeneficioAux = roundDown((percentualDescontoBeneficio.divide(CEM))
				.multiply(valorUnitarioEfetivoAux) , 2);
		
		valorUnitarioEfetivoMercadoria = valorUnitarioEfetivoAux.subtract(valorUnitarioBeneficioAux);
			
		if(valorUnitarioEfetivoMercadoria.doubleValue() <= 0){
			valorUnitarioEfetivoMercadoria = BigDecimal.valueOf(0.01);
		}
		
		BigDecimal valorMargemMercadoria = BigDecimal.ZERO;
		BigDecimal percentualPontuacaoItem = BigDecimal.ZERO;
		
		BigDecimal fatorK = representante.fatorKMargem;
		BigDecimal fatorAjusteMargem = BigDecimal.ONE;
		
		// Calculo da Margem da Mercadoria.
		if(tipoNegociacao == TipoNegociacao.VENDA.getValue() 
				|| tipoNegociacao == TipoNegociacao.PROMOCAO.getValue()){
			//Venda
			BigDecimal valorFundadoCliente = BigDecimal.ZERO;
			
			//Obtendo o 'Funding' da acao tatica do cliente
			if(percentualAcaoCliente.doubleValue() > 0){
				BigDecimal percentualUtilizadoAcaoCliente = DescontoService
						.calculaAcaoTaticaUtilizadaDoCliente(percentualDescontoConcedido, 
								percentualMaximoAcaoTatica, 
								percentualMaximoSimplificado, 
								percentualAcaoCliente);
				valorFundadoCliente = roundDown((item.preco.valorBrutoMercadoria
						.multiply(percentualUtilizadoAcaoCliente))
						.divide(CEM), 2);
			}
			
			BigDecimal encargoTributario = roundDown((valorUnitarioEfetivoMercadoria
					.multiply(item.preco.percentualTributacao))
					.divide(CEM) , 2);
			BigDecimal custoComissao = roundDown((valorUnitarioEfetivoMercadoria
					.multiply(item.preco.percentualComissaoMercadoria))
					.divide(CEM), 2);
			
			valorMargemMercadoria = valorUnitarioEfetivoMercadoria
					.subtract(encargoTributario)
					.subtract(item.preco.custoLogistica)
					.subtract(custoComissao)
					.add(valorFundadoCliente);
			
			valorMargemMercadoria = roundDown(valorMargemMercadoria.multiply(fatorAjusteMargem), 2);
			
			//Comentario legado: "Multiplicar por FatorK truncar em 1 e multiplicar por 10"
			valorPontuacaoMercadoria = roundDown(valorMargemMercadoria.multiply(fatorK), 1)
					.multiply(BigDecimal.TEN);
			valorPontuacaoMercadoriaBeneficio = valorPontuacaoMercadoria;
			
			receitaLiquida = roundDown(valorUnitarioEfetivoMercadoria.subtract(encargoTributario), 2);
		} else {
			if(tipoNegociacao == TipoNegociacao.FLEX_POUPE_CERTO.getValue() 
					|| tipoNegociacao == TipoNegociacao.BENEFICIO.getValue()){
				 //BrindeFlexivel ou de economia de beneficio customizado.
				BigDecimal encargoTributario = roundDown(
						(valorBeneficioMercadoria.multiply(item.preco.percentualTributacao))
						.divide(CEM), 2);
				receitaLiquida = roundDown(valorBeneficioMercadoria.subtract(encargoTributario), 2);
				
				valorMargemMercadoria = item.preco.custoLogistica.negate(); //Custo do produto + custo logistico
				
				//Comentario legado: "Multiplicar por FatorK truncar em 1 e multiplicar por 10"
				valorPontuacaoMercadoria = roundDown(valorMargemMercadoria.multiply(fatorK), 1)
						.multiply(BigDecimal.TEN);
				valorPontuacaoMercadoriaBeneficio = valorPontuacaoMercadoria;
			}
		}
		
		//LINHA 5312 CLSITE - Legado
		if(receitaLiquida.doubleValue() > 0){
			percentualPontuacaoItem = roundDown((valorMargemMercadoria.divide(CEM)).doubleValue() 
					/receitaLiquida.doubleValue() ,2);
		}
		
		//Calculo da cor da margem da mercadoria.
		FaixaMargem faixaMargem = CalculoPrecoDAO.obtemFaixaParaColorirPontuacaoItem(siglaEstadoDestino, 
				item.mercadoria.codigoChaveCategoria);
				
		if(faixaMargem != null){
			codigoCorMargem = obtemCodigoFaixa(faixaMargem, percentualPontuacaoItem, valorPontuacaoMercadoria, false);
		}else {
			faixaMargem = new FaixaMargem();
			codigoCorMargem = obtemCodigoFaixa(faixaMargem, percentualPontuacaoItem, valorPontuacaoMercadoria, true);
		}
		
		codigoCorMargemBeneficio = codigoCorMargem;
		
		
		margem.codigoCorMargem = codigoCorMargem;
		margem.valorPontuacaoMercadoria = valorPontuacaoMercadoria;
		margem.indicaItemImune = indicaItemImune;
		margem.valorPontuacaoMercadoriaBeneficio = BigDecimal.ZERO;
		
		//if(indicaAtualizacaoMargemBeneficio){
			item.margem = margem;
			margem.codigoCorMargemBeneficio = codigoCorMargemBeneficio;
			margem.valorPontuacaoMercadoriaBeneficio = valorPontuacaoMercadoriaBeneficio;
			//TODO fazer update destes valores na tabela temporaria de itePe
			//m_f4CodCorMrgBfc.assignLong(m_iCodCorMrg);
			//m_f4VlrPtoMerBfc.assignDouble(m_dblVlrPtoMerBfc);
			//TabelaTmpItePeDAO.alteraItem(item);
		//}
		
		if(!valorPontuacaoMercadoria.equals(valorPontuacaoMercadoriaBeneficio)){
			// wtf? 
			//Legado: return (TRUE);
		}
		
		return margem;
		
	}
	
	/**
	 * Obtem indicador da filial.
	 *
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @return true, if successful
	 */
	private static boolean obtemIndicadorDaFilial(Integer codigoFilialExpedicao) {
		Integer indicativoVendaDireta = 1;
		try {
			Integer indicativo = CalculoPrecoDAO.obtemIndicativoVendaDireta(codigoFilialExpedicao);
			if(indicativo != null){
				indicativoVendaDireta = indicativo; 
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}
		if(indicativoVendaDireta == 1){
			return true;
		}else {
			return false;
		}

	}

	/**
	 * Obtem codigo faixa.
	 *
	 * @param faixa the faixa
	 * @param percentualMargemItem the percentual margem item
	 * @param valorPontuacaoMercadoria the valor pontuacao mercadoria
	 * @param indicaValorPadrao the indica valor padrao
	 * @return the int
	 */
	private static int obtemCodigoFaixa(FaixaMargem faixa, 
			BigDecimal percentualMargemItem, 
			BigDecimal valorPontuacaoMercadoria, 
			boolean indicaValorPadrao){
		
		if(indicaValorPadrao){
			faixa.percentualLimiteInferior = BigDecimal.ZERO;
			faixa.percentualLimiteSuperior = BigDecimal.ZERO;
		}
		
		if(faixa.percentualLimiteInferior.equals(faixa.percentualLimiteSuperior) 
				&& faixa.percentualLimiteInferior.doubleValue() == 0){
			if(valorPontuacaoMercadoria.doubleValue() == 0){
				return TipoMargem.NEUTRA.getValue();
			}
		}else if(percentualMargemItem.doubleValue() <= faixa.percentualLimiteInferior.doubleValue()){
			
			return TipoMargem.PREJUIZO.getValue();
		}else if (percentualMargemItem.doubleValue() > faixa.percentualLimiteInferior.doubleValue() 
				&& percentualMargemItem.doubleValue() <= faixa.percentualLimiteSuperior.doubleValue() ){
			
			return TipoMargem.BAIXA_RENTABILIDADE.getValue();
		}else if (percentualMargemItem.doubleValue() > faixa.percentualLimiteSuperior.doubleValue()){
			
			return TipoMargem.ALTA_RENTABILIDADE.getValue();
		}
		
		return TipoMargem.NEUTRA.getValue();		
	}
	
	
	/**
	 * Calcula o valor do Frete e o valor do frete da caixa para a mercadoria.
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoRegraDistribuicao
	 * @param codigoCliente 
	 * @param precoBruto 
	 */
	public static void calculaValorDoFrete(final Mercadoria mercadoria, 
			final Integer codigoFilialExpedicao,
			final Integer codigoFilialFaturamento, 
			final RegraDistribuicao regra, 
			final Integer codigoCliente, 
			final CondicaoPagamento condicaoPagamento, 
			final Preco preco,
			final Item item){
		long tempoInicio = new Date().getTime();
		
		BigDecimal valorFrete = BigDecimal.ZERO;
		BigDecimal valorFreteCaixa = BigDecimal.ZERO;
		
		RegraDistribuicao regraDistribuicao = CalculoPrecoDAO
				.obtemRegraDeDistribuicao(mercadoria, codigoFilialExpedicao,
						codigoFilialFaturamento, regra.codigoRegra, codigoCliente);
		
		if(regraDistribuicao != null){
			if(regraDistribuicao.percentualFrete == null){
				regraDistribuicao.percentualFrete = regraDistribuicao.percentualPadraoFrete;
			}
			if (regraDistribuicao.indicadorContaDestino == 1) {
				if (regraDistribuicao.indicadorTabelaManual == 1) {
					valorFrete =BigDecimal.ZERO;
					valorFreteCaixa = BigDecimal.ZERO;
				} else {
					if (regraDistribuicao.percentualFrete.doubleValue() == 0 ) {
						valorFrete = regraDistribuicao.valorUnitarioFrete;
						valorFreteCaixa = regraDistribuicao.valorUnitarioFrete;
					} else {
						BigDecimal fator = condicaoPagamento.fatorCondicaoPagamento;					
						valorFrete = roundUP(BigDecimal.valueOf(preco.valorBrutoMercadoria.doubleValue() 
																/ fator.doubleValue())
								.multiply(regraDistribuicao.percentualFrete.divide(CEM)), 2);
						valorFreteCaixa = roundUP(BigDecimal.valueOf(preco.valorBrutoCaixa.doubleValue() 
																	/ fator.doubleValue())
								.multiply(regraDistribuicao.percentualFrete.divide(CEM)), 2);
					}
				}
			}else {
				valorFrete = BigDecimal.ZERO;
				valorFreteCaixa = BigDecimal.ZERO;
			}
		}
		
		item.frete = valorFrete;
		item.freteCaixa = valorFreteCaixa;
		
		System.out.println("CalculaPreco - calculaFrete - tempo gasto: " 
				+ TimeUnit.MILLISECONDS.toMillis(new Date().getTime() - tempoInicio));
		
	}

	
	/**
	 * Verifica se hâ€¡ valor Minimo estadual ou Nacional de uma mercadoria.
	 * @param mercadoria
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @return
	 */
	public static boolean validaValorMinimoEstadualENacional(Mercadoria mercadoria, 
			String siglaEstadoOrigem, 
			String siglaEstadoDestino ){
		boolean validacao = true;
		
		BigDecimal valorMinimoMercadoria = CalculoPrecoDAO.obtemValorMinimoEstadual(mercadoria, 
				siglaEstadoOrigem, 
				siglaEstadoDestino);
		
		if(valorMinimoMercadoria == null){
			Double valorMinimoNacional = CalculoPrecoDAO.obtemValorMinimoNacional(mercadoria).doubleValue();
			if(valorMinimoNacional <= 0){
				validacao = false;
			}
		}
		
		return validacao;
	}
	
	/**
	 * Verifica item imune de pontuacao.
	 *
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoMercadoria the codigo mercadoria
	 * @return true, if successful
	 */
	public static boolean verificaItemImuneDePontuacao(Integer codigoFilialExpedicao, Integer codigoMercadoria) {
		return CalculoPrecoDAO.verificaItemImuneDePontuacao(codigoFilialExpedicao, codigoMercadoria);
	}
	
}
