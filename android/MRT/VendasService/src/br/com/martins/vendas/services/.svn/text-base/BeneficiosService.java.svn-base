package br.com.martins.vendas.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.BeneficiosDAO;
import br.com.martins.vendas.enums.Faixa;
import br.com.martins.vendas.enums.TipoCondicaoPagamento;
import br.com.martins.vendas.enums.TipoNegociacaoMercadoria;
import br.com.martins.vendas.services.calculodepreco.CalculoUtil;
import br.com.martins.vendas.vo.Beneficio;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.ConfirmacaoMensagemAplicaBeneficio;
import br.com.martins.vendas.vo.FiliaisBeneficioCustomizado;
import br.com.martins.vendas.vo.ItemPedidoTemporario;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Representante;
import br.com.martins.vendas.vo.ValorMinimoItem;

import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;

public class BeneficiosService {

	private static final Integer CODIGO_GRUPO_CLIENTE_PADRAO = 0;
	private static final Integer INFINITO_PONTOS = 9999999;
	
	private static BigDecimal valorReferenciaTotal = BigDecimal.ZERO;
	private static Integer quantidadeTotalPontos = 0, quantidadeItens = 0;
	
//	private static final String TAG = BeneficiosService.class.getName();
	
//	private static Integer PEDCABECALHO = 1;
	private static Integer PEDFECHAMENTO = 2;
	
	/**
	 * Reajusta preco beneficio cliente.
	 *
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param descontoBeneficio the desconto beneficio
	 * @param prazoBeneficio the prazo beneficio
	 * @param cliente the cliente
	 * @param codigoTerritorioVenda the codigo territorio venda
	 * @throws Exception the exception
	 */
	public static void reajustaPrecoBeneficioCliente(Integer codigoFilialExpedicao, 
			BigDecimal descontoBeneficio, 
			Integer prazoBeneficio, 
			Cliente cliente, 
			Integer codigoTerritorioVenda) throws Exception {
		
		TransactionManager transactionManager = null;
		try{
			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();
			List<ItemPedidoTemporario> itensPedidos = ItemPedidoTemporarioService
					.buscaItensPedidosTemporario(codigoFilialExpedicao);
			
			ValorMinimoItem valorMinimoItem;
			for(ItemPedidoTemporario itemPedidoTemporario : itensPedidos){
				valorMinimoItem = ItemPedidoService.verificaValorMinimoItemBeneficio(descontoBeneficio,
						true, 
						true, 
						itemPedidoTemporario, 
						cliente);
				if(valorMinimoItem.isAbaixoValorMinimo){
					itemPedidoTemporario.valorLiquidoMercadoria = valorMinimoItem.item.valorLiquidoMercadoria;
					itemPedidoTemporario.percentualAcrescimoConcedido = valorMinimoItem.percentualDescontoItem.abs();
					itemPedidoTemporario.valorBrutoMercadoria = valorMinimoItem.item.preco.valorBrutoMercadoria;
					itemPedidoTemporario.valorBrutoCaixa = valorMinimoItem.item.preco.valorBrutoCaixa;
					itemPedidoTemporario.comissaoMaximaMercadoria = valorMinimoItem.item.comissao.comissaoMaximaMercadoria;
					itemPedidoTemporario.comissaoConcessaoMercadoria = valorMinimoItem.item.comissao.comissaoMercadoria;
					itemPedidoTemporario.valorCaixaComImposto  = valorMinimoItem.item.valorCaixaComImposto;
					itemPedidoTemporario.valorPontoMercadoria = valorMinimoItem.item.valorPontuacaoMercadoria;
					itemPedidoTemporario.valorUnitarioComImposto = valorMinimoItem.item.valorUnitarioComImposto;
					itemPedidoTemporario.valorUnitarioCaixaComImposto = valorMinimoItem.item.valorUnitarioCaixaComImposto;
					itemPedidoTemporario.valorLiquidoComImposto = valorMinimoItem.item.valorLiquidoComImposto;
					itemPedidoTemporario.valorLiquidoTotal = valorMinimoItem.item.valorLiquidoTotal;
					itemPedidoTemporario.valorLiquidoCaixa = valorMinimoItem.item.valorLiquidoCaixa;
					itemPedidoTemporario.percentualDescontoFlexivel = BigDecimal.ZERO;
				}
				itemPedidoTemporario.percentualDescontoBeneficio = descontoBeneficio;
				itemPedidoTemporario.prazoBeneficio = prazoBeneficio;
				itemPedidoTemporario.valorPontosBeneficio = valorMinimoItem.item != null ? valorMinimoItem.item.valorPontoBeneficioMercadoria : null;
				aplicaBeneficioItemPedido(itemPedidoTemporario, transactionManager);
			}
			transactionManager.commitTransaction();
		}catch (Exception e) {
			if(transactionManager != null){
				transactionManager.rollbackTransaction();	
			}
			
		}
	}

	/**
	 * Aplica beneficio cliente.
	 *
	 * @param origem the origem
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param descontoBeneficio the desconto beneficio
	 * @param prazoBeneficio the prazo beneficio
	 * @param cliente the cliente
	 * @param codigoTerritorioVenda the codigo territorio venda
	 * @return the list
	 * @throws Exception the exception
	 */
	public static List<ConfirmacaoMensagemAplicaBeneficio> aplicaBeneficioCliente(Integer origem, Integer codigoFilialExpedicao, BigDecimal descontoBeneficio, Integer prazoBeneficio, Cliente cliente, Integer codigoTerritorioVenda) throws Exception{
		TransactionManager transactionManager = null;
		boolean commitTransacao = true;
		try{
			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();
			List<ConfirmacaoMensagemAplicaBeneficio> listaConfirmacaoMensagemAplicaBeneficio = new ArrayList<ConfirmacaoMensagemAplicaBeneficio>();
			ConfirmacaoMensagemAplicaBeneficio confirmacaoMensagemAplicaBeneficio;
			List<ItemPedidoTemporario> itensPedidos;
			ValorMinimoItem valorMinimoItem;
			if (origem == PEDFECHAMENTO){
				itensPedidos = ItemPedidoTemporarioService.buscaItensPedidosTemporario(codigoFilialExpedicao);
				for(ItemPedidoTemporario itemPedidoTemporario : itensPedidos){
					itemPedidoTemporario.percentualDescontoBeneficio = descontoBeneficio;
					valorMinimoItem = ItemPedidoService.verificaValorMinimoItemBeneficio(descontoBeneficio, true, false, itemPedidoTemporario, cliente);
					if(!valorMinimoItem.isAbaixoValorMinimo){
						itemPedidoTemporario.percentualDescontoBeneficio = descontoBeneficio;
						itemPedidoTemporario.prazoBeneficio = prazoBeneficio;
						aplicaBeneficioItemPedido(itemPedidoTemporario, transactionManager);
					}else{
						confirmacaoMensagemAplicaBeneficio = new ConfirmacaoMensagemAplicaBeneficio();
						confirmacaoMensagemAplicaBeneficio.precoBC = valorMinimoItem.valorLiquidoNovo;
						confirmacaoMensagemAplicaBeneficio.percentualVariacaoPreco = "De -" + itemPedidoTemporario.percentualDescontoFlexivel.setScale(2) + " p/ " + valorMinimoItem.percentualDescontoItem.setScale(2).multiply(BigDecimal.valueOf(-1));
						confirmacaoMensagemAplicaBeneficio.codigoMercadoria = itemPedidoTemporario.codigoMercadoria;
						confirmacaoMensagemAplicaBeneficio.descricaoMercadoria = itemPedidoTemporario.descricaoMercadoria;
						confirmacaoMensagemAplicaBeneficio.filialExpedicao = itemPedidoTemporario.codigoFilialExpedicao;
						confirmacaoMensagemAplicaBeneficio.codigoCondicaoPagamento = itemPedidoTemporario.codigoCondicaoPagamento;
						listaConfirmacaoMensagemAplicaBeneficio.add(confirmacaoMensagemAplicaBeneficio);
						commitTransacao = false;
					}
				}
				//o commit so sera feito caso nao exista nenhum impedimento conforme o preco minimo
				if(commitTransacao){
					transactionManager.commitTransaction();	
				}else{
					transactionManager.rollbackTransaction();
				}
				
			}
			return listaConfirmacaoMensagemAplicaBeneficio;
		}catch (Exception e) {
			if(transactionManager != null){
				transactionManager.rollbackTransaction();
			}
			throw e;
		}
	}
	
	/**
	 * Aplica beneficio item pedido.
	 *
	 * @param itemPedidoTemporario the item pedido temporario
	 * @param transactionManager the transaction manager
	 * @throws Exception the exception
	 */
	private static void aplicaBeneficioItemPedido(ItemPedidoTemporario itemPedidoTemporario, TransactionManager transactionManager) throws Exception {
		ItemPedidoTemporarioService.atualiza(itemPedidoTemporario, transactionManager);
	}

	/**
	 * Método responsavel por montar o detalhes dos beneficios por cliente
	 * @param numeroPedido 
	 * @param beneficiosDisponiveis2 
	 * @param rep 
	 * @param codigoCliente
	 * @param codigoBeneficio
	 * @param scoreCliente
	 * @param codigoTerritorioVenda
	 * @throws Exception 
	 */
	public static List<Beneficio> montaDetalheBeneficiosCliente(Beneficio beneficioAutal, Integer numeroPedido, Integer codigoCliente, Integer scoreCliente, Integer codigoTerritorioVenda) throws Exception {
		Integer codigoBeneficio = beneficioAutal.codigoBeneficio;
		//busca os beneficios e os seus valores
		List<Beneficio> beneficiosCustomizados = BeneficiosDAO.buscaDetalheBeneficiosDisponiveis(codigoCliente, codigoBeneficio, scoreCliente, codigoTerritorioVenda);
		//calcula os itens pedidos
		boolean isCalculaApenasPontos = (beneficioAutal.valorMaximoCompra.doubleValue() > 0.00);
		totalizaPedido(beneficioAutal, isCalculaApenasPontos);
		//verifica o enquadramento aos beneficios
		return enquadraBeneficios(beneficioAutal, codigoCliente, numeroPedido, beneficiosCustomizados);
	}

	/**
	 * Metodo utilizado para enquadramento dos beneficios
	 * quivalente metodo clsCli::ObtemBeneficiosFaixas da classe clsCli
	 * @param beneficioAutal 
	 * @param numeroPedido 
	 * @param numeroPedido 
	 * @param beneficiosDisponiveis
	 * @throws Exception 
	 */
	private static List<Beneficio> enquadraBeneficios(Beneficio beneficioAtual, Integer codigoCliente, Integer numeroPedido, List<Beneficio> beneficiosCustomizados) throws Exception {
		Faixa faixa;
		Beneficio beneficioTemporaria;
		List<Beneficio> beneficiosCustomizadosAux = new ArrayList<Beneficio>();
		for(Beneficio beneficioDisponivel : beneficiosCustomizados){
			if (beneficioDisponivel.numeroSequencialFaixa > 0){
				beneficioTemporaria = BeneficiosDAO.buscaBeneficioCustomizadoTemporaria(numeroPedido, codigoCliente, beneficioDisponivel.codigoBeneficio, beneficioDisponivel.numeroSequencialFaixa);
				if(beneficioTemporaria != null){
					beneficioDisponivel.prazo = beneficioTemporaria.quantidadeDiaPrazoTemp;
				}else{
					beneficioDisponivel.isAplicaBeneficio = false;
				}
				beneficioDisponivel.isFaixaLiberada = true;
			}else{
				faixa = encaixaFaixa(beneficioDisponivel);
				if (faixa != Faixa.NAOPERTENCEFAIXA){
					//adiciona sempre na primeira posicao, para respeitar a ordem do legado
					beneficiosCustomizadosAux.add(0, beneficioDisponivel);
					if (faixa == Faixa.ENCAIXAFAIXA){
						
						beneficioTemporaria = BeneficiosDAO.buscaBeneficioCustomizadoTemporaria(numeroPedido, codigoCliente, beneficioDisponivel.codigoBeneficio, beneficioDisponivel.numeroSequencialFaixa);
						if(beneficioTemporaria != null){
							//utiliza as informacoes da TMPPCABCD, caso existam
							beneficioDisponivel.prazo = beneficioTemporaria.quantidadeDiaPrazoTemp;
							beneficioDisponivel.descontoBeneficio = beneficioTemporaria.percentualDescontoTemp;
						}else{
							beneficioDisponivel.isAplicaBeneficio = false;
						}
						beneficioDisponivel.isFaixaLiberada = true;
						break;
					}
					else{
						beneficioDisponivel.isFaixaLiberada = false;
						beneficioDisponivel.isAplicaBeneficio = false;
					}
					
					/*if (faixa == Faixa.ENCAIXAFAIXA){
						return beneficiosCustomizadosAux;
					}*/
				}
			}
		}
		
		beneficioAtual.valorReferenciaTotalPedido = valorReferenciaTotal;
		beneficioAtual.quantidadeTotalPontosPedido = quantidadeTotalPontos;
		beneficioAtual.quantidadeItensPedido = quantidadeItens;
		beneficioAtual.razaoCompraPedidoCalculado = valorReferenciaTotal.doubleValue() != .0 ? BigDecimal.valueOf((quantidadeTotalPontos / valorReferenciaTotal.doubleValue()) * 10) : null;
		
		return beneficiosCustomizadosAux;	
	}

	/**
	 *  Metodo para verficação se o pedido se enquadra em algum beneficio customizado
	 *  Equivalente EncaixaFaixa da classe clsCli
	 * @param beneficioDisponivel
	 * @return
	 */
	private static Faixa encaixaFaixa(Beneficio beneficioDisponivel){
		Integer iQdeMnmPto, iQdeMaxPto, iQdeMnmPed, iQdeMaxCmp, iQdeMnmCmp, iQdeBseCmp, iQdeMaxPed, iIndObrFxa;
		Double dVlrMaxCmp = 0., 
				dVlrMnmCmp = 0., 
				dVlrCalc = 0., 
				dPerMnmPto = 0., 
				dPerMaxPto = 0., 
				dRazaoPtoPed = 0., 
				dPerMnmCmp = 0., 
				dVlrBseCmp = 0.,
				dVlrMnmPed = 0., 
				dPerMaxCmp = 0., 
				dVlrMaxPed = 0., 
				dPerMnmQde = 0., 
				dPerMaxQde = 0., 
				iVlrFin;
		String strVlrMnmCmp = "", 
//				strVlrMaxCmp= "", 
				strQdeMnmCmp= "", 
				strQdeMaxCmp= "";
		
		boolean bVlrNulo = false,
				bQdeNulo = false, 
				bVlrMaxInf = false, 
				bQdeMaxInf = false, 
				bQdePtoMaxInf = false, 
				bRazaoPtoMaxInf = false, 
				bPtoNulo = false, 
				bPerPtoNulo = false;

		dVlrMaxCmp = parseDouble(beneficioDisponivel.valorMaximoCompra);
		dVlrMnmCmp = parseDouble(beneficioDisponivel.valorMinimoCompra);	
		
		iQdeMnmPto = parseInt(beneficioDisponivel.quantidadeMinimaPontos);
		iQdeMaxPto = parseInt(beneficioDisponivel.quantidadeMaximaPontos);

		dPerMnmPto = parseDouble(beneficioDisponivel.percentualMinimoPontos);
		dPerMaxPto = parseDouble(beneficioDisponivel.percentualMaximoPontos);

		iQdeMaxCmp = parseInt(beneficioDisponivel.quantidadeMaximaCompra);
		iQdeMnmCmp = parseInt(beneficioDisponivel.quantidadeMinimaCompra);
		
		dPerMnmCmp = parseDouble(beneficioDisponivel.percentualMinimoCompra);
		
		dVlrBseCmp = parseDouble(beneficioDisponivel.valorBaseCompra);
		
		dVlrMnmPed = parseDouble(beneficioDisponivel.valorMinimoPedido);
		
		dPerMaxCmp = parseDouble(beneficioDisponivel.percentualMaximoCompra);
		
		dVlrMaxPed = parseDouble(beneficioDisponivel.valorMaximoPedido);
		
		dPerMnmQde = parseDouble(beneficioDisponivel.percentualMinimoQuantidade);
		
		iQdeMnmPed = parseInt(beneficioDisponivel.quantidadeMinimaPedido);
		
		dPerMaxQde = parseDouble(beneficioDisponivel.percentualMaximoQuantidade);
		
		iQdeBseCmp = parseInt(beneficioDisponivel.quantidadeBaseCompra);
		
		iQdeMaxPed = parseInt(beneficioDisponivel.quantidadeMaximaPedido);

		iIndObrFxa = parseInt(beneficioDisponivel.indicaFaixaObrigatoria);
		
		if (valorReferenciaTotal.doubleValue() > 0)
		{
			dRazaoPtoPed = (quantidadeTotalPontos / valorReferenciaTotal.doubleValue()) * 10;
		}

		if (dVlrMaxCmp <= 0){
			// Mínimo pedido
			if ((dPerMnmCmp == 0.00) && ((((dPerMnmCmp * dVlrBseCmp) / 100) + dVlrBseCmp) == 0.0) &&
				(dVlrMnmPed == 0.00) && (dVlrMnmCmp == 0.00)){
				beneficioDisponivel.valorMinimoCompra = null;
				strVlrMnmCmp = "";
			}else{
				if ((dPerMnmCmp == 0.00) && (dPerMaxCmp == 0.00)){
					dVlrCalc = 0.00;
				}else{
					dVlrCalc = ((dPerMnmCmp * dVlrBseCmp) / 100) + dVlrBseCmp;
				}

				beneficioDisponivel.valorMinimoCompra = CalculoUtil.roundHalfEven(BigDecimal.valueOf(((dVlrCalc > dVlrMnmPed) ? dVlrCalc : dVlrMnmPed)),2);
			}

			// Máximo pedido
			if ((dPerMaxCmp == 0.00) && (dVlrMaxPed == 0.00) && (dVlrMaxCmp == 0.00))
			{
				if (!"".equals(strVlrMnmCmp)){
					bVlrMaxInf = true;
				}else{
					bVlrNulo = true;
				}
//				strVlrMaxCmp = "";
				beneficioDisponivel.valorMaximoCompra = null;
			}
			else{
				if ((dPerMnmCmp == 0.00) && (dPerMaxCmp == 0.00)){
					dVlrCalc = 0.00;
				}else{
					dVlrCalc = ((dPerMaxCmp * dVlrBseCmp) / 100) + dVlrBseCmp;
				}
				beneficioDisponivel.valorMaximoCompra = BigDecimal.valueOf(((dVlrCalc > dVlrMaxPed) ? dVlrCalc : dVlrMaxPed));
			}
		
			if ((dPerMnmQde == 0.00) && (iQdeMnmPed == 0) && (iQdeMnmCmp == 0))
			{
				strQdeMnmCmp = "";
				beneficioDisponivel.quantidadeMinimaCompra = null;
				
			}
			else
			{
				// Maximo itens
				if ((dPerMnmQde == 0.00) && (dPerMaxQde == 0.00))
				{
					dVlrCalc = 0.00;
				}
				else
				{
					dVlrCalc = ((dPerMnmQde * iQdeBseCmp) / 100) + iQdeBseCmp;
				}


				iVlrFin = ((dVlrCalc > iQdeMnmPed) ? dVlrCalc : iQdeMnmPed);
				strQdeMnmCmp = String.valueOf(iVlrFin);
				beneficioDisponivel.quantidadeMinimaCompra = iVlrFin.intValue();
			}

			// Maximo de Itens
			if ((dPerMaxQde == 0.00) && (iQdeMaxPed == 0) && (iQdeMaxCmp == 0))
			{		
				if (!"".equals(strQdeMnmCmp)){
					bQdeMaxInf = true;
				}
				else
				{
					bQdeNulo = true;
				}

				strQdeMaxCmp = "";
				beneficioDisponivel.quantidadeMaximaCompra = null;
			}
			else
			{
				if ((dPerMnmQde == 0.00) && (dPerMaxQde == 0.00))
				{
					dVlrCalc = 0.00;
				}
				else
				{
					dVlrCalc = ((dPerMaxQde * iQdeBseCmp) / 100) + iQdeBseCmp;
				}

				iVlrFin = ((dVlrCalc > iQdeMaxPed) ? dVlrCalc : iQdeMaxPed);
				strQdeMaxCmp = String.valueOf(iVlrFin);
				beneficioDisponivel.quantidadeMaximaCompra = iVlrFin.intValue();
			}
		}
		else
		{
			if ((dPerMaxQde == 0.00) && (iQdeMaxPed == 0) && (iQdeMaxCmp == 0))
			{
				if ((dPerMnmQde == 0.00) && (iQdeMnmPed == 0) && (iQdeMnmCmp == 0))
				{
					bQdeNulo = true;
					strQdeMnmCmp = "";
					beneficioDisponivel.quantidadeMinimaCompra = null;
				}
				else
				{
					bQdeMaxInf = true;
				}

				strQdeMaxCmp = "";
				beneficioDisponivel.quantidadeMaximaCompra = null;
			}

			valorReferenciaTotal = BigDecimal.valueOf(dVlrBseCmp);
			quantidadeItens = iQdeBseCmp;
		}

		//Validando intervalo de pontos 
		if ((iQdeMnmPto == 0.00) && (iQdeMaxPto == 0.00) )
		{
			beneficioDisponivel.quantidadeMinimaPontos = null;
			bPtoNulo = true;		
		}
			
		if (iQdeMaxPto == INFINITO_PONTOS)
		{
			bQdePtoMaxInf = true;
		}

		

		if ((dPerMnmPto == 0.00 && dPerMaxPto == 0.00) || !bPtoNulo)
		{
			bPerPtoNulo = true;
			beneficioDisponivel.percentualMinimoPontos = null;
		}

		if (dPerMaxPto == 999.99)
		{
			bRazaoPtoMaxInf = true;
		}

		
		dVlrMnmCmp = beneficioDisponivel.valorMinimoCompra == null ? BigDecimal.ZERO.doubleValue() : beneficioDisponivel.valorMinimoCompra.doubleValue();
		dVlrMaxCmp = beneficioDisponivel.valorMaximoCompra == null ? BigDecimal.ZERO.doubleValue() : beneficioDisponivel.valorMaximoCompra.doubleValue();
		
		iQdeMnmCmp = parseInt(strQdeMnmCmp);
		iQdeMaxCmp = parseInt(strQdeMaxCmp);
		
		if (iIndObrFxa == 1){
			if ((((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) && ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))) || bVlrNulo ) && 
				((((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf)) && (iQdeMnmCmp <= quantidadeItens)) || bQdeNulo ) &&
				(((iQdeMnmPto <= quantidadeTotalPontos) && ((iQdeMaxPto >= quantidadeTotalPontos) || bQdePtoMaxInf)) || bPtoNulo) &&
				(((dPerMnmPto <= dRazaoPtoPed) && (dPerMaxPto >= dRazaoPtoPed || bRazaoPtoMaxInf)) || bPerPtoNulo))			
			{
				return Faixa.ENCAIXAFAIXA;
			}
		}
		else {
			if (iIndObrFxa == 0) {
				if (bPtoNulo && bPerPtoNulo) {
					//Beneficio possui desafio por quantidade
					if (bVlrNulo) {				
						if (((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf)) && (iQdeMnmCmp <= quantidadeItens)) {
							return Faixa.ENCAIXAFAIXA;
						}
					}
					else {
						//Beneficio possui desafio por valor
						if (bQdeNulo) {
							if ((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) 
									&& ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))){
								return Faixa.ENCAIXAFAIXA;
							}
						}
						else
						{
							//Beneficio possui desafio por valor e quantidade
							if (((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) 
									&& ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))) 
									|| (((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf)) 
											&& (iQdeMnmCmp <= quantidadeItens)))
							{
								return Faixa.ENCAIXAFAIXA;
							}
						}
					}
				}
				else
				{				
					if (quantidadeItens > 0)
					{
						if (bVlrNulo && bQdeNulo)
						{
							if (bPerPtoNulo)
							{
								//Beneficio tem desafio apenas por pontos				
								if (quantidadeTotalPontos >= iQdeMnmPto 
										&& (quantidadeTotalPontos <= iQdeMaxPto || bQdePtoMaxInf))
								{
									return Faixa.ENCAIXAFAIXA;
								}
							}
							else
							{
								//Beneficio tem desafio apenas por razao entre ponto e valor do pedido
								if (dRazaoPtoPed >= dPerMnmPto && (dRazaoPtoPed <= dPerMaxPto || bRazaoPtoMaxInf))
								{
									return Faixa.ENCAIXAFAIXA;
								}
							}
						}
						else {
							if (bVlrNulo) {				
								if (bPerPtoNulo) {
									if ((((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf)) 
											&& (iQdeMnmCmp <= quantidadeItens)) 
											|| (quantidadeTotalPontos >= iQdeMnmPto 
											&& (quantidadeTotalPontos <= iQdeMaxPto || bQdePtoMaxInf))){
										return Faixa.ENCAIXAFAIXA;
									}
								}
								else {
									if ((((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf)) 
											&& (iQdeMnmCmp <= quantidadeItens)) 
											|| (dRazaoPtoPed >= dPerMnmPto 
											&& (dRazaoPtoPed <= dPerMaxPto || bRazaoPtoMaxInf)))
									{
										return Faixa.ENCAIXAFAIXA;
									}
								}
							}
							else {
								if (bQdeNulo) {
									if (bPerPtoNulo) {
										if (((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) 
												&& ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))) 
												|| (quantidadeTotalPontos >= iQdeMnmPto 
												&& (quantidadeTotalPontos <= iQdeMaxPto || bQdePtoMaxInf)))
										{
											return Faixa.ENCAIXAFAIXA;
										}
									}
									else {
										if (((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) 
												&& ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))) 
												|| (dRazaoPtoPed >= dPerMnmPto 
												&& (dRazaoPtoPed <= dPerMaxPto || bRazaoPtoMaxInf)))
										{
											return Faixa.ENCAIXAFAIXA;
										}
									}
								}
								else {
									if (bPerPtoNulo) {
										if ((((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) 
												&& ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))) 
												|| (((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf))
													&& (iQdeMnmCmp <= quantidadeItens))) 
														|| (quantidadeTotalPontos >= iQdeMnmPto 
														&& (quantidadeTotalPontos <= iQdeMaxPto || bQdePtoMaxInf)))
										{
											return Faixa.ENCAIXAFAIXA;
										}
									}
									else {
										if ((((dVlrMnmCmp <= valorReferenciaTotal.doubleValue()) 
												&& ((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) || (bVlrMaxInf))) 
												|| (((iQdeMaxCmp >= quantidadeItens) || (bQdeMaxInf))
														&& (iQdeMnmCmp <= quantidadeItens))) || (dRazaoPtoPed >= dPerMnmPto 
														&& (dRazaoPtoPed <= dPerMaxPto || bRazaoPtoMaxInf)))
										{
											return Faixa.ENCAIXAFAIXA;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (((dVlrMaxCmp >= valorReferenciaTotal.doubleValue()) 
				|| (bVlrMaxInf)) || ((iQdeMaxCmp >= quantidadeItens) 
						|| (bQdeMaxInf)) || !bPerPtoNulo || !bPtoNulo ) {
			return Faixa.PERTENCEFAIXA;
		}
		else {
			return Faixa.NAOPERTENCEFAIXA;
		}
	}

	private static Integer parseInt(Object valor) {
		if(valor == null || "".equals(valor)){
			return null;
		}
		if(valor instanceof BigDecimal){
			return ((BigDecimal)valor).intValue();
		}
		return new Double(valor.toString()).intValue();
	}

	private static Double parseDouble(Object valor) {
		if(valor == null || "".equals(valor)){
			return null;
		}
		if(valor instanceof BigDecimal){
			return ((BigDecimal)valor).doubleValue();
		}
		return valor == null || "".equals(valor) ? 0 : Double.valueOf(valor.toString());
	}

	/**
	 * Totaliza o pedido para calculo dos beneficios customizados
	 * @param beneficiosDisponiveis 
	 * @param beneficioAutal 
	 * @param representante 
	 * @param codigoBeneficio
	 * @param isCalculaApenasPontos 
	 * @throws Exception
	 */
	private static void totalizaPedido(Beneficio beneficioAtual,  boolean isCalculaApenasPontos) throws Exception {
		Integer codigoBeneficio = beneficioAtual.codigoBeneficio;
		//busca as filiais disponiveis do beneficio
		List<FiliaisBeneficioCustomizado> filiaisBeneficioCustomizadoList = FiliaisBeneficioCustomizadoService.buscaFiliaisBeneficioCustomizado(codigoBeneficio);
		List<ItemPedidoTemporario> itensPedidoTemporario;
		boolean pertence = false;
		CondicaoPagamento condicaoPagamento;
		Representante representante = RepresentanteService.findRepresentante(null);
		Map<Integer, Integer> mapaProdutos = new HashMap<Integer, Integer>();
		valorReferenciaTotal = BigDecimal.ZERO;
		quantidadeTotalPontos = 0;
		
		for(FiliaisBeneficioCustomizado filiaisBeneficioCustomizado : filiaisBeneficioCustomizadoList){
			//para cada filial, busca os itens do pedido
			itensPedidoTemporario = ItemPedidoTemporarioService.buscaItensPedidosTemporario(filiaisBeneficioCustomizado.codigoFilialExpedicao);
			for(ItemPedidoTemporario itemPedidoTemporario : itensPedidoTemporario){
				
				condicaoPagamento = obtemCondicaoPagamento(itemPedidoTemporario, representante);
				
				if ((!CondicaoPagamentoService.isBrindeIntegral(itemPedidoTemporario.tipoNegocicaoMercadoria, itemPedidoTemporario.codigoCondicaoPagamento, getPercentuaisBonificacaoValorBeneficio(), getValorBonificacaoMercadoria(representante))) &&
					(((itemPedidoTemporario.tipoNegocicaoMercadoria == TipoNegociacaoMercadoria.VENDA.idTipoNegociacaoMercadoria) && (itemPedidoTemporario.indicaRestricaoBeneficioCustomizado != 1)) ||
					((itemPedidoTemporario.tipoNegocicaoMercadoria != TipoNegociacaoMercadoria.VENDA.idTipoNegociacaoMercadoria) && (itemPedidoTemporario.indicaRestricaoBeneficioCustomizadoBrinde != 1)))){
					
					//TODO: VERIFICAR COM CESAR, POIS NO LEGADO, SEMPRE RETORNA FALSE
					pertence = isBeneficioPertenceListaBeneficiosCliente(null, itemPedidoTemporario.codigoCondicaoPagamento);
					
					if (((beneficioAtual.tipoCondicaoPagamento == condicaoPagamento.tipoFinanciamento) || (beneficioAtual.tipoCondicaoPagamento == 0)) ||
						((beneficioAtual.tipoCondicaoPagamento == 99) && pertence )){
						
						if(verificaGrupoBonificacaoMix(itemPedidoTemporario.codigoMercadoria, beneficioAtual.codigoGrupoCliente)){
							
							if(itemPedidoTemporario.quantidadeMercadoria > 0){
								mapaProdutos.put(itemPedidoTemporario.codigoMercadoria, itemPedidoTemporario.codigoMercadoria);
							}
							if(!isCalculaApenasPontos){
								valorReferenciaTotal = valorReferenciaTotal.add(BigDecimal.valueOf((itemPedidoTemporario.valorLiquidoMercadoria.doubleValue()
										* itemPedidoTemporario.quantidadeMercadoria.doubleValue()) 
										+ (itemPedidoTemporario.valorSTBTotal.doubleValue()
												+ itemPedidoTemporario.valorIPITotal.doubleValue()) 
												+ (itemPedidoTemporario.valorFreteMercadoria.doubleValue() 
														* itemPedidoTemporario.quantidadeMercadoria.intValue())));
							}
							quantidadeTotalPontos += itemPedidoTemporario.valorPontoMercadoria * itemPedidoTemporario.quantidadeMercadoria;
						}
					}
				}else{
					if ((itemPedidoTemporario.tipoNegocicaoMercadoria == TipoNegociacaoMercadoria.POUPECERTO.idTipoNegociacaoMercadoria) || 
							(itemPedidoTemporario.tipoNegocicaoMercadoria == TipoNegociacaoMercadoria.BENEFICIO.idTipoNegociacaoMercadoria)){					
						quantidadeTotalPontos += itemPedidoTemporario.valorPontoMercadoria * itemPedidoTemporario.quantidadeMercadoria;
					}	
				}
			}
		}
		
		quantidadeItens = mapaProdutos.size();
	}
	
	private static boolean verificaGrupoBonificacaoMix(Integer codigoMercadoria, Integer codigoGrupoCliente) {
		if (CODIGO_GRUPO_CLIENTE_PADRAO == codigoGrupoCliente){
			return true;
		}
		return ProdutoService.produtoEstaNoMix(codigoGrupoCliente, codigoMercadoria);
	}

	/**
	 * Verifica se a condicao de pagamento pertence aos disponiveis para o cliente
	 * @param beneficiosDisponiveis
	 * @param codigoCondicaoPagamento
	 * @return
	 */
	//TODO: VERIFICAR COM CESAR, POIS NO LEGADO, SEMPRE RETORNA FALSE
	private static boolean isBeneficioPertenceListaBeneficiosCliente(
			List<Beneficio> beneficiosDisponiveis,
			Integer codigoCondicaoPagamento) {
		return false;
	}

	public static BigDecimal getPercentuaisBonificacaoValorBeneficio(){
		/////////////////////////////////////////////////
		////Se algum dia voltar venda de brinde a 10%, basta popular o campo abaixo
		double dblPerVlrBnf = 0;//(double) m_f4PerVlrBnf;
		/////////////////////////////////////////////////
		return BigDecimal.valueOf(dblPerVlrBnf);
	}
	
	public static BigDecimal getValorBonificacaoMercadoria(Representante representante){
		return BigDecimal.valueOf(representante.percentualBonificacaoPedido);
	}
	
	/**
	 * Obtem condicao pagamento.
	 *
	 * @param itemPedidoTemporario the item pedido temporario
	 * @param representante the representante
	 * @return the condicao pagamento
	 * @throws SQLException the sQL exception
	 */
	public static CondicaoPagamento obtemCondicaoPagamento(ItemPedidoTemporario itemPedidoTemporario, 
			Representante representante) throws SQLException{

		CondicaoPagamento condicaoPagamento = new CondicaoPagamento(), condicaoPagamentoAux;
		Mercadoria mercadoria;
		Integer codigoMercadoriaPrincipal;
		Integer codigoCondicaoPagamento = itemPedidoTemporario.codigoCondicaoPagamento;
		//CondicaoPagamentoService.buscaCondicaoPagamento(itemPedidoTemporario.codigoCondicaoPagamento);
		if (codigoCondicaoPagamento != TipoCondicaoPagamento.SEM_CODIGO_CONDICAO_PAGAMENTO.codigoCondicaoPagamento){
			condicaoPagamento.codigoCondicaoPagamento = codigoCondicaoPagamento;
			condicaoPagamento.tipoFinanciamento = 0;
			if (CondicaoPagamentoService.isBrindeIntegral(itemPedidoTemporario.tipoNegocicaoMercadoria, codigoCondicaoPagamento, getPercentuaisBonificacaoValorBeneficio(), getValorBonificacaoMercadoria(representante))){
				return condicaoPagamento;
			}
			codigoMercadoriaPrincipal = itemPedidoTemporario.codigoMercadoriaPrincipal != 0 ? itemPedidoTemporario.codigoMercadoriaPrincipal : itemPedidoTemporario.codigoMercadoria;
			mercadoria = MercadoriaService.buscaMercadoria(codigoMercadoriaPrincipal);
			condicaoPagamentoAux = CondicaoPagamentoService.buscaCondicaoPagamentoPorTipoMercadoria(codigoCondicaoPagamento, 
					mercadoria.tipo);
			// Verifica se a mercadoria tem condição de pagamento
			if(condicaoPagamentoAux != null){
				condicaoPagamento.codigoCondicaoPagamento = codigoCondicaoPagamento;
				condicaoPagamento.tipoFinanciamento = condicaoPagamentoAux.tipoFinanciamento;
			}
		}
		return condicaoPagamento;
	}
	
	public static List<Beneficio> buscaBeneficiosDisponiveis(boolean indicaDescontoSimplificado, 
			Integer codigoCliente, 
			Integer scoreCliente, 
			Integer codigoTerritorioVenda, 
			Integer tipoVenda) throws Exception {
		return BeneficiosDAO.buscaBeneficiosDisponiveis(indicaDescontoSimplificado, 
				codigoCliente, 
				scoreCliente, 
				codigoTerritorioVenda, 
				tipoVenda);
	}	
	
	public static Beneficio buscaBeneficio(Integer codigoBeneficio) throws Exception {
		return BeneficiosDAO.buscaBeneficio(codigoBeneficio);
	}
}