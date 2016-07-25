package br.com.martins.vendas.services.calculodepreco;

import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.CEM;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundDown;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.ComissaoDAO;
import br.com.martins.vendas.dao.DescontoDAO;
import br.com.martins.vendas.dao.RelacaoGiroDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.enums.TipoNegociacao;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.desconto.DescontoBanda;
import br.com.martins.vendas.services.desconto.PercentualFlexibilizacao;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Comissao;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Representante;

public class ComissaoService {

	/**
 * Calcula comissao do item.
 *
 * @param mercadoria the mercadoria
 * @param item the item
 * @param tipoNegociacao the tipo negociacao
 * @param condicaoPagamento the condicao pagamento
 * @param cliente the cliente
 * @param siglaEstadoOrigem the sigla estado origem
 * @param quantidadeMercadoriaPedido the quantidade mercadoria pedido
 * @param codigoFilialExpedicao the codigo filial expedicao
 * @param representante the representante
 * @param isUltimoItem the is ultimo item
 * @param isComissaoBruta the is comissao bruta
 * @param isComissaoDescontoItem the is comissao desconto item
 * @param isComissaoDescontoPedido the is comissao desconto pedido
 * @param isComissaoBeneficioFlex the is comissao beneficio flex
 * @return the comissao
 */
	public static Comissao calculaComissaoMercadoria(Mercadoria mercadoria,
			Item item,
			Integer tipoNegociacao,
			CondicaoPagamento condicaoPagamento, 
			Cliente cliente,
			String siglaEstadoOrigem, 
			Integer quantidadeMercadoriaPedido, 
			Integer codigoFilialExpedicao,
			Representante representante, 
			boolean isUltimoItem, 
			boolean isComissaoBruta,
			boolean isComissaoDescontoItem,
			boolean isComissaoDescontoPedido,
			boolean isComissaoBeneficioFlex) {
		
		BigDecimal percentualAcaoTatica = item.desconto.percentualDescontoAcaoTatica;
		BigDecimal percentualMaximoSimplificado = item.desconto.percentualDescontoSimplificado;
		BigDecimal percentualDescontoFlex = item.desconto.percentualDescontoFlexivel;
		
		BigDecimal percentualAcrecimoConcedido = item.percentualAcrescimoConcedido;
//		BigDecimal percentualDescontoConcedido = item.percentualDescontoConcedido;
		BigDecimal valorBrutoTMP = item.preco.valorBrutoTMP;
		
//		String siglaEstadoDestino = cliente.codigoEstadoDestino;
		
		boolean isComissaoPessoaJuridica = (representante.naturezaRepresentante.equals("J"));
	
		Comissao comissaoPedido = new Comissao();
		Comissao parametrosComissao = ComissaoDAO.obtemParametrosComissao();
		
		BigDecimal percentualMaximoFlex = BigDecimal.ZERO;
		BigDecimal percentualDescontoAcaoTatica = BigDecimal.ZERO;
		BigDecimal percentualDescontoSimplificado = BigDecimal.ZERO;
		
		BigDecimal percentualDescontoBeneficio;
		BigDecimal percentualComissaoAdicional;
		BigDecimal valorSemEncargoTMP;
		BigDecimal valorEconomiaFlex;
		BigDecimal percentualRateioBeneficio;
		BigDecimal valorBrutoBeneficioMercadoria;
		BigDecimal valorBrutoAcrescimoBeneficioMercadoria;
		BigDecimal valorRateioTotal;
		BigDecimal faturamentoRateioBonificacao;
		
		BigDecimal valorComissaoAcao;
		
	//TODO e passado como parametro no legado para o metodo de calculaComissao - clsIte linha 4844;
		BigDecimal valorRateioBonificacao;
		BigDecimal valorTotalRateioBonificacao = BigDecimal.ZERO;
		BigDecimal valorVendaMercadoria;
		
	//fim
		
	//Pode preencher objeto com estes caras
		BigDecimal fatorAcrescimoComissao = parametrosComissao.fatorAcrescimoComissao;
		BigDecimal valorComissaoSimbolo;
		BigDecimal percentualComissaoSimbolo;
		BigDecimal percentualRateioAcao = parametrosComissao.rateioComissaoAcao;
				
		PercentualFlexibilizacao percentualFlexibilizacao = null;
		
		BigDecimal percentualRateioFlex = BigDecimal.ZERO;
		
		if(tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())){
			percentualFlexibilizacao = obtemPercentuaisFlexibilizacao(mercadoria,
					cliente.uf, //cliente.codigoEstadoDestino, 
					parametrosComissao.percentualRateioBandaInferior);
			percentualMaximoFlex = percentualFlexibilizacao.percentualMaximoFlex;		
			percentualRateioFlex = percentualFlexibilizacao.percentualRateioFlex;
			
			if(item.preco.flagUtilizaFlex == 0){
				percentualMaximoFlex = BigDecimal.ZERO;
			}
		}	
		
		//Calcula todos os percentuais de desconto concedidos no item
		percentualDescontoFlex = percentualDescontoFlex
				.subtract(percentualAcaoTatica
						.add(percentualMaximoSimplificado));
		
		if(isComissaoBruta || isComissaoDescontoItem || percentualDescontoFlex.doubleValue() < 0){
			percentualDescontoFlex = BigDecimal.ZERO;
		}
		
		percentualDescontoAcaoTatica = percentualDescontoFlex
				.subtract(percentualDescontoFlex
						.add(percentualMaximoSimplificado));
		if(isComissaoBruta || isComissaoDescontoItem || percentualDescontoAcaoTatica.doubleValue() < 0){
			percentualDescontoAcaoTatica = BigDecimal.ZERO;
		}else {
			if (percentualDescontoAcaoTatica.doubleValue() >= percentualAcaoTatica.doubleValue()) {
				percentualDescontoAcaoTatica = percentualAcaoTatica;
			}
		}
		
		percentualDescontoSimplificado = percentualDescontoFlex
				.subtract(percentualAcaoTatica
						.add(percentualDescontoFlex));
		if(isComissaoBruta || isComissaoDescontoItem || percentualDescontoSimplificado.doubleValue() < 0){
			percentualDescontoSimplificado = BigDecimal.ZERO;
		}
		
		// Calcula os valores para os c�lculos das comiss�es da mercadoria
		BigDecimal fator = CondicaoPagamentoService.obtemFatorCondicaoPagamento(condicaoPagamento,
				mercadoria,
				cliente,
				siglaEstadoOrigem,
				cliente.codigoEstadoDestino);
		
		quantidadeMercadoriaPedido = (quantidadeMercadoriaPedido <= 0) ? 1 : quantidadeMercadoriaPedido;
		
		percentualComissaoAdicional = BigDecimal.ZERO;
		percentualDescontoBeneficio = BigDecimal.ZERO;
		
		if(((tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())) &&
			(mercadoria.temRestricaoBeneficioCustomizado != 1)) ||
			(!(tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())) &&
			(mercadoria.temRestricaoBeneficioCustomizadoNoBrinde != 1))
		){
			
			obtemMapaBeneficiosItemAtual();
			//TODO obtemMapaBfcIteAtual clsIte 4942
			//if(mapaBeneficioItem != null)
				//percentualDescontoBeneficio = mapaBeneficio.percentualDescontoBeneficio;
				//percentualComissaoAdicional = mapabeneficio.percentualComissaoAdicional;
			//}
		}
		BigDecimal valorTotalBonificacao = BigDecimal.ZERO;
		obtemMapaBeneficiosAplicados(codigoFilialExpedicao);
		//TODO 
		//if(mapaBeneficioAplicado != null){
		//	valorTotalBeneficio = mapa.valorBeneficioItem;
		//}
		
		BigDecimal valorTotalEconomiaFlex = BigDecimal.ZERO;
		obtemMapaDoFechamentoFilial(codigoFilialExpedicao);
//		if(mapaFechamento != null){
//			if(!(isComissaoDescontoItem) && (isComissaoBruta)){
//				valorTotalEconomiaFlex = mapaFechamento.valorTotalEconomia;
//			}else {
//				valorTotalEconomiaFlex = mapa.Fechamento.valorTotalEconomiaMaximo;
//			}
//		}
		
		
		valorSemEncargoTMP = CalculoUtil.roundDown(valorBrutoTMP.doubleValue() / fator.doubleValue(), 2);
		if(valorSemEncargoTMP.doubleValue() <= 0){
			valorSemEncargoTMP = BigDecimal.valueOf(0.01);
		}
		
		valorEconomiaFlex = CalculoUtil.roundDown((percentualMaximoFlex.subtract(percentualDescontoFlex))
				.multiply(valorSemEncargoTMP)
				.multiply(new BigDecimal(quantidadeMercadoriaPedido)) , 2);
		if(valorTotalEconomiaFlex.doubleValue() <= 0){
			percentualRateioBeneficio = BigDecimal.ZERO;
		} else {
			percentualRateioBeneficio = CalculoUtil.roundDown(((valorEconomiaFlex.divide(CEM))
					.divide(valorTotalEconomiaFlex.divide(CEM)))
					.multiply(CEM), 2);
		}
		
		BigDecimal valorRateioFlex = BigDecimal.ZERO;
		
		if(isUltimoItem) {
			valorRateioBonificacao = valorTotalBonificacao.subtract(valorTotalRateioBonificacao);
		} else {
			valorRateioBonificacao = CalculoUtil.roundDown((percentualRateioBeneficio
					.multiply(valorTotalBonificacao))
					.divide(CEM), 2);
		}
		
		if(valorRateioBonificacao.doubleValue() > (valorEconomiaFlex.divide(CEM)).doubleValue()){
			valorRateioBonificacao = valorEconomiaFlex.divide(CEM);
		}
		
		BigDecimal valorLiquidoMercadoria = item.valorLiquidoMercadoria;
		
		if(isComissaoBruta){
			valorLiquidoMercadoria = item.preco.valorBrutoMercadoria;
			percentualDescontoBeneficio = BigDecimal.ZERO;
			valorRateioBonificacao = BigDecimal.ZERO;
			valorRateioFlex = BigDecimal.ZERO;
		}
		
		if(isComissaoDescontoItem){
			valorLiquidoMercadoria = item.preco.valorBrutoMercadoria;
		}
		
		if(isComissaoDescontoPedido){
			percentualDescontoBeneficio = BigDecimal.ZERO;
		}
		
		if(isComissaoBeneficioFlex ){
			valorRateioFlex = BigDecimal.ZERO;
			valorRateioBonificacao = BigDecimal.ZERO;
		}
		
		if(!(isComissaoPessoaJuridica ) && !(isComissaoBruta)){
			fatorAcrescimoComissao = BigDecimal.ONE;
		}
		BigDecimal quantidadePedido = new BigDecimal(quantidadeMercadoriaPedido);
		
		BigDecimal fatorValorVenda = roundDown(BigDecimal.valueOf(valorLiquidoMercadoria.doubleValue() / fator.doubleValue())
				.multiply(quantidadePedido), 2);
		
		BigDecimal fatorpercentualBeneficio = roundDown((percentualDescontoBeneficio.divide(CEM))
				.multiply(roundDown(valorLiquidoMercadoria.doubleValue() / fator.doubleValue(), 2))
				.multiply(quantidadePedido), 2);
		
		valorVendaMercadoria = fatorValorVenda.subtract(fatorpercentualBeneficio);
		
		if(valorVendaMercadoria.doubleValue() <= 0){
			valorVendaMercadoria = BigDecimal.valueOf(0.01);
		}
		// linha clsIte: 5055
			
		valorBrutoBeneficioMercadoria = valorVendaMercadoria
				.divide(BigDecimal.ONE
						.add(percentualAcrecimoConcedido.divide(CEM)), BigDecimal.ROUND_DOWN);
		valorBrutoBeneficioMercadoria = valorBrutoBeneficioMercadoria
				.divide(BigDecimal.valueOf(1 + percentualAcaoTatica.doubleValue() 
						/ 100 - percentualDescontoFlex.doubleValue() 
						/ 100 - percentualMaximoSimplificado.doubleValue() 
						/ 100), BigDecimal.ROUND_DOWN);
		
		valorBrutoBeneficioMercadoria = roundDown(valorBrutoBeneficioMercadoria, 2);
		
		valorBrutoAcrescimoBeneficioMercadoria = valorVendaMercadoria
				.divide(BigDecimal.valueOf(1 - percentualAcaoTatica.doubleValue() 
						/ 100 - percentualDescontoFlex.doubleValue() 
						/ 100 - percentualMaximoSimplificado.doubleValue() 
						/ 100), BigDecimal.ROUND_DOWN);
		valorBrutoAcrescimoBeneficioMercadoria = roundDown(valorBrutoAcrescimoBeneficioMercadoria, 2);
		
		percentualComissaoSimbolo = ComissaoDAO.buscaPercentualComissaoSimbolo(codigoFilialExpedicao, 
				item.preco.codigoSimboloSituacao);
		
		// Calcula o valor da comiss�o do s�mbolo da mercadoria	
		BigDecimal percentualRateioComissao = ComissaoDAO.obtemPercentualRateioComisssaoSimbolo(cliente.canal);
		if(percentualComissaoSimbolo.doubleValue() == 0){
			valorComissaoSimbolo = BigDecimal.ZERO;
		}else {
			valorComissaoSimbolo = roundDown(valorVendaMercadoria
					.multiply(percentualComissaoSimbolo.divide(CEM))
					.multiply(parametrosComissao.percentualBaseSimbolo .divide(CEM)), 2);
			valorComissaoSimbolo = roundDown(valorComissaoSimbolo.multiply(fatorAcrescimoComissao), 2);
			valorComissaoSimbolo = roundDown(valorComissaoSimbolo.multiply(parametrosComissao.percentualApropriacaoTLV.divide(CEM)), 2);
			valorComissaoSimbolo = roundDown(valorComissaoSimbolo.multiply(percentualRateioComissao.divide(CEM)), 2);
			valorComissaoSimbolo = roundDown(valorComissaoSimbolo, 2);	
		}
				
		//Calcula o valor da comissao da economia do desconto flexivel da mercadoria;
		valorRateioTotal = valorRateioFlex.add(valorRateioBonificacao);
		BigDecimal valorBrutoPorQuantidade =  item.preco.valorBrutoMercadoria.multiply(quantidadePedido);
		if(valorBrutoPorQuantidade.doubleValue() != 0)
			faturamentoRateioBonificacao = roundDown(valorRateioTotal.doubleValue() / valorBrutoPorQuantidade.doubleValue(), 6);
		else {
			faturamentoRateioBonificacao = BigDecimal.ONE.negate();
		}
		
		BigDecimal percentualAcrescimoAux = percentualAcrecimoConcedido.subtract(percentualDescontoBeneficio);
		if(percentualAcrescimoAux.doubleValue() < 0){
			percentualAcrescimoAux = BigDecimal.ZERO;
		}
		
		BigDecimal faturamentoAcrescimoMercadoria = roundDown((1 - (1 / (1 + percentualAcrescimoAux.doubleValue() /100))), 6);
		BigDecimal faturamentoMaximoEconomia;
		if(valorBrutoPorQuantidade.doubleValue()!= 0){		
			faturamentoMaximoEconomia = roundDown(BigDecimal.ONE
					.subtract(((item.preco.valorBrutoMercadoria.multiply(quantidadePedido))
							.divide(BigDecimal.ONE.add(percentualAcrescimoAux.divide(CEM)), RoundingMode.DOWN)
							.multiply(BigDecimal.ONE.subtract(percentualMaximoFlex.divide(CEM)))
							.divide(valorBrutoPorQuantidade, RoundingMode.DOWN))), 6);
		}else {
			faturamentoMaximoEconomia = BigDecimal.ONE.negate();
		}
		
		BigDecimal valorComissaoFlex;
		
		if(faturamentoRateioBonificacao.doubleValue() > faturamentoAcrescimoMercadoria.doubleValue()){
			valorComissaoFlex = roundDown(valorBrutoBeneficioMercadoria
					.multiply(faturamentoMaximoEconomia.subtract(faturamentoRateioBonificacao)
							.subtract(percentualDescontoFlex)
							.divide(CEM)),2);
			valorComissaoFlex = roundDown(valorComissaoFlex.multiply(fatorAcrescimoComissao), 2);
			valorComissaoFlex = roundDown(valorComissaoFlex.multiply(parametrosComissao.percentualApropriacaoTLV.divide(CEM)),2);
		} else {
			valorComissaoFlex = roundDown(valorBrutoBeneficioMercadoria
						.multiply(percentualMaximoFlex.divide(CEM)
							.subtract(percentualDescontoFlex.divide(CEM)))
						.multiply(percentualRateioFlex.divide(CEM)), 2);
			
			valorComissaoFlex = roundDown(valorComissaoFlex.multiply(fatorAcrescimoComissao), 2);
			valorComissaoFlex = roundDown(valorComissaoFlex.multiply(parametrosComissao.percentualApropriacaoTLV.divide(CEM)), 2);
		}
		
		//Calcula o valor da comissao da economia do desconto de acoes taticas da mercadoria
		if(percentualDescontoAcaoTatica.doubleValue() >= percentualAcaoTatica.doubleValue()){
			valorComissaoAcao = BigDecimal.ZERO;
		} else {
			valorComissaoAcao = roundDown(valorBrutoBeneficioMercadoria
					.multiply(percentualAcaoTatica.divide(CEM)
							.subtract(percentualAcaoTatica.divide(CEM)))
					.multiply(percentualRateioAcao.divide(CEM)), 2);
			valorComissaoAcao = roundDown(valorComissaoAcao.multiply(fatorAcrescimoComissao), 2);
			valorComissaoAcao = roundDown(valorComissaoAcao.multiply(parametrosComissao.percentualApropriacaoTLV.divide(CEM)), 2);
		}
		
		//Calcula o valor da comissao da economia do desconto de venda simplificada da mercadoria
		BigDecimal valorComissaoSimplificado;
		if(percentualMaximoSimplificado.doubleValue() >= percentualMaximoSimplificado.doubleValue()){
			valorComissaoSimplificado = BigDecimal.ZERO;
		} else {
			valorComissaoSimplificado = roundDown(valorBrutoBeneficioMercadoria.multiply(percentualMaximoSimplificado.divide(CEM).subtract(percentualMaximoSimplificado.divide(CEM))).multiply(parametrosComissao.rateioComissaoSimplificado.divide(CEM)), 2);
			valorComissaoSimplificado = roundDown(valorComissaoSimplificado.multiply(fatorAcrescimoComissao), 2);
			valorComissaoSimplificado = roundDown(valorComissaoSimplificado.multiply(parametrosComissao.percentualApropriacaoTLV.divide(CEM)), 2);	
		}
		
		//calcula o valor da comissao da aplicacao do acrescimo da mercadoria
		BigDecimal valorComissaoAcrescimo;
		if(faturamentoRateioBonificacao.doubleValue() >= faturamentoAcrescimoMercadoria.doubleValue()){
			valorComissaoAcrescimo = BigDecimal.ZERO;
		} else {
			valorComissaoAcrescimo = roundDown((faturamentoAcrescimoMercadoria.subtract(faturamentoRateioBonificacao)).multiply(valorBrutoAcrescimoBeneficioMercadoria).multiply(parametrosComissao.rateioComissaoAcrescimo.divide(CEM)), 2);
			valorComissaoAcrescimo = roundDown(valorComissaoAcrescimo.multiply(fatorAcrescimoComissao), 2);
			valorComissaoAcrescimo = roundDown(valorComissaoAcrescimo.multiply(parametrosComissao.percentualApropriacaoTLV.divide(CEM)), 2);
		}
		
		//calcula o valor da comissao adicional proveniente dos beneficios customizados
		BigDecimal valorComissaoBeneficio = roundDown(valorVendaMercadoria
				.multiply(percentualComissaoAdicional.divide(CEM)), 2);
		
		//calcula a comissao total da mercadoria
		comissaoPedido = parametrosComissao;
		comissaoPedido.valorComissaoSimbolo = valorComissaoSimbolo;
		
		comissaoPedido.comissaoMercadoria = valorComissaoSimbolo
				.add(valorComissaoFlex)
				.add(valorComissaoAcao)
				.add(valorComissaoSimplificado)
				.add(valorComissaoAcrescimo)
				.add(valorComissaoBeneficio);
		
		return comissaoPedido;
	
	}
	
	private static BigDecimal obtemMapaBeneficiosItemAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 */
	public static BigDecimal obtemMapaBeneficiosAplicados(Integer codigoFilialExpedicao){
		//TODO mapa beneficios Aplicados
		return null;
	}
	
	/**
	 * Obtem mapa do fechamento filial.
	 *
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 */
	public static BigDecimal obtemMapaDoFechamentoFilial(Integer codigoFilialExpedicao){
		//TODO mapa fechamento filial
		return null;
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @param siglaEstado
	 * @param siglaEstadoDestino
	 * @param percentualRateioBanda
	 * @return
	 */
	public static PercentualFlexibilizacao obtemPercentuaisFlexibilizacao(
			Mercadoria mercadoria,
			String siglaEstadoDestino, 
			BigDecimal percentualRateioBanda){
		
		BigDecimal limiteInferior = BigDecimal.ZERO;
		BigDecimal limiteSuperior = BigDecimal.ZERO;
		
		DescontoBanda banda = DescontoDAO.obtemDescontoPorBanda(mercadoria, siglaEstadoDestino);
		PercentualFlexibilizacao percentualFlex = new PercentualFlexibilizacao();	
		percentualFlex.percentualRateioFlex = percentualRateioBanda;
		
		if(banda != null){
			limiteInferior = banda.percentualLimiteInferior;
			limiteSuperior = banda.percentualLimiteSuperior;		
			percentualFlex.percentualMaximoFlex = limiteInferior;
			percentualFlex.percentualProximoCaixa = CEM;
		} 
		
		return percentualFlex;
	}
	
	
	/**
	 * Busca simbolo mercadoria.
	 *
	 * @param codigoFilialExpedicao the codigo filial exp
	 * @param siglaEstado the sigla estado
	 * @param cliente the cliente
	 * @param representante the representante
	 * @param percentualDescontoConcedido the percentual desconto concedido
	 * @param isUltimoItem the is ultimo item
	 * @param isComissaoBruta the is comissao bruta
	 * @return the list
	 */
	public static List<Comissao> buscaSimboloMercadoria(Integer codigoFilialExpedicao,
			String siglaEstado,	
			Cliente cliente, 
			Representante representante, 
			BigDecimal percentualDescontoConcedido,
			boolean isUltimoItem, 
			boolean isComissaoBruta){
		
			List<Item> itens = TabelaTmpItePeDAO.obtemTodosItens();
			List<Comissao>listaComissao = new ArrayList<Comissao>();

			Comissao comissao = new Comissao();
			for(Item item : itens){
				String[] estadoFilial = RelacaoGiroDAO.obtemEstadoFilial(cliente.codigoTerritorio, codigoFilialExpedicao, item.codigoFilialFaturamento);
				
				comissao = calculaComissaoMercadoria(item.mercadoria, 
						item,
						item.tipoNegociacaoMercadoria, 
						item.condicaoPagamento,
						cliente,
						estadoFilial[0],
						item.quantidadeMercadoria,
						codigoFilialExpedicao, 
						representante,
						isUltimoItem, 
						isComissaoBruta,
						true, // BOOL bComissaoDscIte, 
						true, // BOOL bComissaoDscPed, 
						true);// BOOL bComissaoBnfFlx,
				
				listaComissao.add(comissao);
			}


		return calculaValoresPorSimbolo(listaComissao);
	}	
	
	/**
	 * Calcula valores by simbolo.
	 *
	 * @param listaComissao the lista comissao
	 * @return the list
	 */
	public static List<Comissao>calculaValoresPorSimbolo(List<Comissao>listaComissao){
		List<String>listaSimbolo= new ArrayList<String>();					
		Map<String,BigDecimal> mapaComissao = new HashMap<String,BigDecimal>(); 
		Map<String,BigDecimal> mapaValorVenda = new HashMap<String,BigDecimal>(); 
		Map<String,BigDecimal> mapaComissaoSimbolo = new HashMap<String,BigDecimal>(); 
		Map<String,BigDecimal> mapaComissaoTotal = new HashMap<String,BigDecimal>(); 
		
		for(Comissao c : listaComissao){
			BigDecimal valorVenda = mapaValorVenda.get(c.codigoSimboloSituacao);
			BigDecimal comissaoSimbolo = mapaComissaoSimbolo.get(c.codigoSimboloSituacao);
			BigDecimal comissaoTotal = mapaComissaoTotal.get(c.codigoSimboloSituacao);
			

			if(!listaSimbolo.contains(c.codigoSimboloSituacao)){					
				listaSimbolo.add(c.codigoSimboloSituacao);
			}
			if(!mapaComissao.containsKey(c.codigoSimboloSituacao)){
				mapaComissao.put(c.codigoSimboloSituacao, c.percentualComissaoSimbolo);	
			}
			
			if(mapaValorVenda.containsKey(c.codigoSimboloSituacao)){
				valorVenda=valorVenda.add(c.valorVendaMercadoria);
				mapaValorVenda.put(c.codigoSimboloSituacao, valorVenda);					
			}else{
				mapaValorVenda.put(c.codigoSimboloSituacao, c.valorVendaMercadoria);
			}
			
			if(mapaComissaoSimbolo.containsKey(c.codigoSimboloSituacao)){
				comissaoSimbolo=comissaoSimbolo.add(c.valorComissaoSimbolo);
				mapaComissaoSimbolo.put(c.codigoSimboloSituacao, comissaoSimbolo);					
			}else{
				mapaComissaoSimbolo.put(c.codigoSimboloSituacao, c.valorComissaoSimbolo);
			}
			
			if(mapaComissaoTotal.containsKey(c.codigoSimboloSituacao)){
				comissaoTotal=comissaoTotal.add(c.valorComissaoTotal);
				mapaComissaoTotal.put(c.codigoSimboloSituacao, comissaoTotal);					
			}else{
				mapaComissaoTotal.put(c.codigoSimboloSituacao, c.valorComissaoTotal);
			}
		}
		
		List<Comissao>lista=new ArrayList<Comissao>();
		Comissao comissao = new Comissao();
		
		for(String  simbolo : listaSimbolo){
			comissao.codigoSimboloSituacao=simbolo;
			comissao.percentualComissaoSimbolo=mapaComissao.get(simbolo);
			comissao.valorVendaMercadoria=mapaValorVenda.get(simbolo);
			comissao.valorComissaoSimbolo=mapaComissaoSimbolo.get(simbolo);			
			comissao.valorComissaoTotal=mapaComissaoTotal.get(simbolo);
			comissao.valorComissaoVariavel=comissao.valorComissaoTotal.subtract(comissao.valorComissaoSimbolo);
			
			lista.add(comissao);
		}
		return lista;
	}
	
	public static void voidObtemMapaBeneficioAtual(){
		
	}
	
	
}
