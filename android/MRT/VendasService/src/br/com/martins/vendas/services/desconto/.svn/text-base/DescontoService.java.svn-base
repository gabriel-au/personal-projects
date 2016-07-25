package br.com.martins.vendas.services.desconto;

import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.CEM;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundDown;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundUP;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundFloor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.martins.vendas.dao.DescontoDAO;
import br.com.martins.vendas.enums.TipoCobranca;
import br.com.martins.vendas.enums.TipoNegociacao;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.CalculoUtil;
import br.com.martins.vendas.services.calculodepreco.ComissaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Preco;

public class DescontoService {
	
	private static BigDecimal valorDesconto = BigDecimal.ZERO;
	private static BigDecimal percentualDescontoAcao = BigDecimal.ZERO;
	private static BigDecimal percentualDescontoIsencao = BigDecimal.ZERO;
	private static BigDecimal valorDescontoIsencao = BigDecimal.ZERO;
	private static BigDecimal valorDescontoEspecial = BigDecimal.ZERO;
	private static BigDecimal valorMaximoIsencao = BigDecimal.ZERO;
	private static BigDecimal percentualMaximoBarraDesconto = BigDecimal.ZERO;
	private static BigDecimal percentualMaximoAcrescimo = BigDecimal.ZERO;
	private static BigDecimal percentualMaximoDesconto = BigDecimal.ZERO;
	
	private static BigDecimal percentualMaximoFlex;
	private static BigDecimal percentualDescontoMinimo;

	/**
	 * Aplica Acrescimo.
	 *
	 * @param item the item
	 * @param acrescimo the acrescimo
	 * @param mercadoria the mercadoria
	 * @return the item
	 */
	public static Item aplicaAcrecismoPreco(final Item item, final BigDecimal acrescimo){
					
		BigDecimal valorLiquidoMercadoria = BigDecimal.ZERO;
		BigDecimal valorLiquidoCaixa = BigDecimal.ZERO;
		
		if(item.preco != null){

			valorLiquidoMercadoria = calculaAcrescimoPreco(item.preco.valorBrutoMercadoria, acrescimo);
			valorLiquidoCaixa = calculaAcrescimoPreco(item.preco.valorBrutoCaixa, acrescimo);
			
			valorLiquidoMercadoria = roundUP(valorLiquidoMercadoria, 2);
			valorLiquidoCaixa = roundUP(valorLiquidoCaixa, 2);
						
			item.preco.valorBrutoMercadoria = valorLiquidoMercadoria;
			item.preco.valorBrutoCaixa = valorLiquidoCaixa;
			
			item.valorLiquidoMercadoria = valorLiquidoMercadoria;
			item.valorLiquidoCaixa = valorLiquidoCaixa;
		}
		
		
		return item;
	}
	
	/**
	 * Aplica desconto
	 * @param preco
	 * @param acrescimo
	 * @param mercadoria
	 * @return
	 */
	public static Item aplicaDescontoPreco(
			final Preco preco, 
			final Item item,
			final BigDecimal percentualDescontoConcedido, 
			final Integer quantidadeMercadoriaSimilar) {
		
		int quantidadeMercadoria = quantidadeMercadoriaSimilar == 0 ? 1 : quantidadeMercadoriaSimilar;
		
		if (preco != null && percentualDescontoConcedido != null 
				&& percentualDescontoConcedido.compareTo(BigDecimal.ZERO) != 0) {
			
			double desconto = calculaDescontoPreco(item.valorUnitarioComImposto, 
					percentualDescontoConcedido, quantidadeMercadoria).doubleValue();
			item.valorUnitarioComImposto = CalculoUtil.roundDown(desconto / quantidadeMercadoria, 4);
			
			desconto = calculaDescontoPreco(item.valorUnitarioCaixaComImposto, 
					percentualDescontoConcedido, 
					quantidadeMercadoria).doubleValue();
			item.valorUnitarioCaixaComImposto = CalculoUtil.roundDown(desconto / quantidadeMercadoria, 4);
			
			item.valorLiquidoComImposto = CalculoUtil.roundDown(
					calculaDescontoPreco(item.valorLiquidoComImposto, 
							percentualDescontoConcedido,
							quantidadeMercadoria), 2);		
		}
		
		return item;
	}
	

	/**
	 * 
	 * @param preco
	 * @param acrescimo
	 */
	private static BigDecimal calculaAcrescimoPreco(BigDecimal preco, 
			BigDecimal acrescimo) {
		
		if(acrescimo.doubleValue() > 0){
			preco = preco.multiply(BigDecimal.ONE.add(acrescimo.divide(CEM)));
		}
		
		return preco;
	}
	
	/**
	 * 
	 * @param preco
	 * @param acrescimo
	 */
	private static BigDecimal calculaDescontoPreco(BigDecimal preco, 
			BigDecimal desconto, 
			Integer quantidadeMercadoriaSimilar) {
		if(desconto.doubleValue() > 0){
			preco = roundUP(preco
					.multiply(BigDecimal.valueOf(quantidadeMercadoriaSimilar))
					.subtract(CalculoUtil.roundDown(desconto.divide(BigDecimal.valueOf(100))
							.multiply(preco
							.multiply(BigDecimal.valueOf(quantidadeMercadoriaSimilar))),2)), 2);
		}
		return preco;
	}
	
	
	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param siglaEstadoDestino
	 * @param codigoAtividade
	 * @param cliente
	 * @param mercadoria
	 * @param tipoNegociacao  
	 * @param codigoTerritorioVenda 
	 * @param condicaoPagamento 
	 * @return 
	 */
	public static Desconto obtemDescontos(Item item, 
			Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			String siglaEstadoOrigem,
			String siglaEstadoDestino, 
			Integer codigoAtividade,
			Cliente cliente, 
			Mercadoria mercadoria, 
			Integer tipoVendaPedido, 
			Integer tipoNegociacao, 
			Integer codigoTerritorioVenda, 
			CondicaoPagamento condicaoPagamento,
			Integer quantidadeMercadoriaPedido,
			BigDecimal percentualAcrescimoVirtual) {
		
		long inicio = new Date().getTime();
		Preco preco = item.preco;
		
		Desconto desconto = new Desconto();
		
		valorMaximoIsencao = BigDecimal.ZERO;
		percentualMaximoFlex = BigDecimal.ZERO;
		
		BigDecimal percentualAcaoCliente = BigDecimal.ZERO;
		BigDecimal percentualAcaoTatica = BigDecimal.ZERO;
		
		BigDecimal maximoIsencaoAux;

		List<DescontoAcaoTatica> descontosAcaoTatica = DescontoDAO.obtemDescontoPorAcaoTatica(
				codigoFilialExpedicao, siglaEstadoDestino, codigoAtividade,
				cliente, mercadoria);
		
		for(DescontoAcaoTatica descontoAcaoTatica: descontosAcaoTatica){
			maximoIsencaoAux = descontoAcaoTatica.valorMaximoIsencao;
			
			acumulaDescontos(descontoAcaoTatica);
			if(descontoAcaoTatica.tipoAcaoTatica.equals("4") ||
					valorMaximoIsencao.equals(BigDecimal.ZERO)){
				valorMaximoIsencao = maximoIsencaoAux;
			}
			
			if((descontoAcaoTatica.indicaAcaoFundada == 1) 
					&& (descontoAcaoTatica.codigoCliente.equals(cliente.codigoCliente))){
				percentualAcaoCliente = percentualAcaoCliente
						.add((BigDecimal.ONE.subtract(descontoAcaoTatica.fatorDesconto)).divide(CEM));
			}
			
			if((descontoAcaoTatica.tipoAcaoTatica.equals("4")) && (valorMaximoIsencao.doubleValue() == 0)){
				valorMaximoIsencao = maximoIsencaoAux;
			}
		}
		
		if(preco.flagUtilizaAcaoTatica == 1){
			percentualAcaoTatica = obtemPercentualAcaoTatica(preco.valorBrutoMercadoria, 
					percentualDescontoAcao, 
					valorDesconto);
		}else {
			percentualAcaoTatica = BigDecimal.ZERO;
		}
		
		BigDecimal percentualDescontoIsencao = obtemPercentualIsencao(preco.valorBrutoMercadoria, valorDescontoIsencao);
		BigDecimal percentualMaximoSimplificado = BigDecimal.ZERO;
		
		
		if (preco.flagUtilizaBeneficios == BigDecimal.ZERO.intValue()) {
			mercadoria.temRestricaoBeneficioCustomizado = 1;
		}
		
		if((tipoVendaPedido.equals(TipoVendaPedido.SIMPLIFICADA.getValue()))  
				&& ( (tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())) && (mercadoria.temRestricaoBeneficioCustomizado != 1) )
				|| ( (tipoNegociacao.equals(TipoNegociacao.VENDA.getValue())) && (mercadoria.temRestricaoBeneficioCustomizadoNoBrinde != 1) )){
			
			DescontoSimplificado descontoSimplificado = DescontoDAO.obtemDescontoSimplificado(cliente, 
					codigoTerritorioVenda, codigoFilialExpedicao, condicaoPagamento);
			
			if(descontoSimplificado.percentualDescontoSimulado != null){
				percentualMaximoSimplificado = descontoSimplificado.percentualDescontoSimulado;				
			}else {
				percentualMaximoSimplificado = BigDecimal.ZERO;
			}					
		}
			
		if(preco.flagUtilizaFlex == 1){
			percentualMaximoFlex = obtemPercentualFlexibilizacao(
					mercadoria,
					siglaEstadoDestino, 
					BigDecimal.ZERO, //percentualRateioBanda
					percentualAcaoTatica, 
					percentualMaximoSimplificado);
		}
		
		DescontoBanda descontoBanda = null;
		if(preco.flagUtilizaFlex == 1){
			descontoBanda = DescontoDAO.obtemDescontoPorBanda(mercadoria, siglaEstadoDestino);
		}
		
		if(descontoBanda !=  null){
			percentualMaximoBarraDesconto = descontoBanda.percentualLimiteInferior;
			desconto.percentualMaximoAcrescimo = descontoBanda.percentualLimiteSuperior;//TODO ver depois
		} else {
			percentualMaximoBarraDesconto = BigDecimal.ZERO;
		}
		percentualMaximoBarraDesconto = percentualMaximoBarraDesconto.add(percentualAcaoTatica);
		percentualMaximoBarraDesconto = percentualMaximoBarraDesconto.add(percentualMaximoSimplificado);
		percentualMaximoBarraDesconto = roundDown(percentualMaximoBarraDesconto, 2);
		
		BigDecimal fatorCondicaoPagamento = CondicaoPagamentoService.obtemFatorCondicaoPagamento(condicaoPagamento, 
				mercadoria, 
				cliente, 
				siglaEstadoOrigem, 
				siglaEstadoDestino);
		
		boolean isValorMaximoIsencao = verificaValorMaximoDeIsencao(fatorCondicaoPagamento, 
				quantidadeMercadoriaPedido, 
				preco);
		
		BigDecimal percentualMaxDesconto = BigDecimal.ZERO;
		
		boolean isValorMinimo = verificaValorMinimo(false, 
				item, 
				tipoNegociacao,
				mercadoria,
				condicaoPagamento, 
				cliente, 
				siglaEstadoOrigem, 
				siglaEstadoDestino, 
				quantidadeMercadoriaPedido, 
				percentualAcrescimoVirtual, 
				codigoFilialFaturamento,
				percentualMaxDesconto,
				codigoFilialExpedicao);
		
		if(percentualMaximoDesconto.doubleValue() <= percentualMaximoBarraDesconto.doubleValue()){
			percentualMaximoBarraDesconto = percentualMaximoDesconto;
		}
		
		if(isValorMinimo){
			if (percentualMaximoDesconto.doubleValue() != 999.99) {
				if (percentualMaximoDesconto.doubleValue() > 0) {
					if (percentualMaximoFlex.doubleValue() > percentualMaximoDesconto.doubleValue()){
						percentualMaximoFlex = percentualMaximoDesconto;
						if (isValorMaximoIsencao) {	
							if ((percentualMaximoDesconto.doubleValue() >= percentualDescontoMinimo.doubleValue()) 
									&& validaFlexibilizacaoAutomatica(percentualDescontoMinimo, 
											BigDecimal.ZERO, //percentualDescontoIsencao - No legado este valor eh passado como zero;
											cliente.percentualDescontoFlexivel)) //percentualFlexCliente;
							{ 
								// Devo adicionar o desconto de isencao na acao tatica para ser utilizado no flexivel inteligente, 
								//mas nao posso utiliza-la para flexibilizar pois fere o preco minimo
								percentualAcaoTatica = percentualAcaoTatica.add(percentualDescontoIsencao);
							} else {
								isValorMaximoIsencao = false;
							}
						}
					}else {
						if (isValorMaximoIsencao){
							if (percentualMaxDesconto.doubleValue()  
									> (percentualMaximoFlex.add(percentualDescontoIsencao)).doubleValue())
							{
								// O desconto maximo permitido pelo preco minimo eh maior que o maximo de desconto, 
								// logo poderei adicionar todo desconto de isencao
								if (validaFlexibilizacaoAutomatica(percentualDescontoMinimo, 
										percentualDescontoIsencao, 
										cliente.percentualDescontoFlexivel)){
									percentualMaximoFlex = percentualMaximoFlex.add(percentualDescontoIsencao);
									percentualAcaoTatica = percentualAcaoTatica.add(percentualDescontoIsencao);
								}else {
									isValorMaximoIsencao = false;
								}
							}else {
								// O desconto maximo permitido pelo preco minimo eh menor que a soma de descontos, 
								// logo adicionarei somente a diferenca de descontos
								BigDecimal diferencaDesconto = (percentualMaxDesconto.subtract(percentualMaximoDesconto));
								if ((diferencaDesconto.add(percentualMaximoDesconto)).doubleValue() 
										>= percentualDescontoMinimo.doubleValue()) 
								{
									// Poderei utilizar parte do desconto da MP do BEM
									if (validaFlexibilizacaoAutomatica(percentualDescontoIsencao, 
											percentualDescontoIsencao, 
											cliente.percentualDescontoFlexivel))
									{
										percentualMaximoFlex = percentualMaximoFlex.add(diferencaDesconto);
										percentualAcaoTatica = percentualAcaoTatica.add(percentualDescontoIsencao);
									} else{
										isValorMaximoIsencao = false;
									}
								}else {
									isValorMaximoIsencao = false;
								}
							}
						}
					}
				}else {
					percentualMaximoFlex = BigDecimal.ZERO;

					// Produto possui minimo mas sera preciso aplicar um SuperFlex
					if (isValorMaximoIsencao && (percentualMaximoAcrescimo.doubleValue() > ((-1) * percentualMaxDesconto.doubleValue())) 
							&& validaFlexibilizacaoAutomatica(percentualDescontoIsencao, 
									BigDecimal.ZERO, //percentualDescontoIsencao - no legado este valor e passado como zero nesse momento;
									cliente.percentualDescontoFlexivel)) 
					{
						percentualAcaoTatica = percentualAcaoTatica.add(percentualDescontoIsencao);
					}else {
						isValorMaximoIsencao = false;
					}
				}
			}
		}else {
			// Item nao possui preco minimo
			if (isValorMaximoIsencao && validaFlexibilizacaoAutomatica(percentualDescontoIsencao, 
					percentualDescontoIsencao, 
					cliente.percentualDescontoFlexivel)) 
			{
				percentualMaximoFlex = percentualMaximoFlex.add(percentualDescontoIsencao);
				percentualAcaoTatica = percentualAcaoTatica.add(percentualDescontoIsencao);
			}else {
				isValorMaximoIsencao = false;
	    	}
		}
		
		if (isValorMaximoIsencao) {	
			if ((percentualMaximoFlex.doubleValue() >= percentualDescontoMinimo.doubleValue()) 
				&&	(percentualMaximoFlex.doubleValue() < percentualDescontoMinimo.doubleValue()))
			{
				// Obriga aplicar desconto para que o produto entre na MP do BEM
				percentualMaximoFlex = percentualDescontoMinimo;
			}
		}else {
			percentualDescontoMinimo = BigDecimal.ZERO;
		}
		
//		boolean temDesconto = (percentualAcaoTatica.doubleValue() > 0);

		if (cliente.percentualDescontoFlexivel.doubleValue() > 0) {
			// Cliente tem flexibiliza��o autom�tica
			desconto.percentualDescontoFlexivel = (percentualMaximoFlex.multiply(cliente.percentualDescontoFlexivel).divide(CEM));
		}

		// Caso a flexibiliza��o seja maior que a permitida, flexibiliza no m�ximo
		if (desconto.percentualDescontoFlexivel.doubleValue() > percentualMaximoFlex.doubleValue()){
			desconto.percentualDescontoFlexivel = percentualMaximoFlex;
		}

		BigDecimal percentualMaximoDescontoMinimoCaixa = BigDecimal.ZERO;
		
		boolean isValorMinimoCaixa = verificaValorMinimoCaixa(false, 
				item, 
				tipoNegociacao,
				mercadoria, 
				condicaoPagamento, 
				cliente, 
				siglaEstadoOrigem, 
				siglaEstadoDestino,
				percentualAcrescimoVirtual, 
				codigoFilialFaturamento,
				codigoFilialExpedicao,
				percentualMaximoDesconto);
		/*
		Estou verificando qual o maximo de desconto posso aplicar
		se for comercializado a quantidade de caixa do produto.
		*/
		if (isValorMinimoCaixa) {
			if (percentualMaxDesconto.doubleValue() > percentualMaximoFlex.doubleValue()){
				percentualMaximoDescontoMinimoCaixa = percentualMaximoFlex;
			}else {
				percentualMaximoDescontoMinimoCaixa = percentualMaximoDesconto;
			}
			
			if (percentualMaximoDescontoMinimoCaixa.doubleValue() >= 0) {
				desconto.percentualDescontoCaixa = percentualMaximoDescontoMinimoCaixa;
			}else {
				desconto.percentualDescontoCaixa = BigDecimal.ZERO;
			}
		}else {
			desconto.percentualDescontoCaixa = desconto.percentualDescontoFlexivel;
		}	
				
		desconto.percentualDescontoAcaoTatica = percentualAcaoTatica;
		desconto.percentualDescontoSimplificado = percentualMaximoSimplificado;
		desconto.percentualDescontoIsencao = percentualDescontoIsencao;
		desconto.percentualMaximoDesconto = percentualMaximoBarraDesconto;
		desconto.percentualMinimoDesconto = percentualDescontoMinimo;
		desconto.percentualDescontoFlexivel = percentualMaximoFlex;
		
		System.out.println("Tempo Gasto - Obtem Descontos: " 
				+ TimeUnit.MILLISECONDS.toMillis(new Date().getTime() - inicio) + " milisegundos");
		
		return desconto;	
	}
	
	/**
	 * Acumula descontos.
	 *
	 * @param desconto the desconto
	 */
	private static void acumulaDescontos(DescontoAcaoTatica desconto){
		BigDecimal valorDescontoIsencao = BigDecimal.ZERO;
		
		if(desconto.tipoAcaoTatica.equals("4")){
			percentualDescontoIsencao = percentualDescontoIsencao
					.add((BigDecimal.ONE.subtract(desconto.fatorDesconto)).multiply(CEM));
		}else {
			percentualDescontoAcao = percentualDescontoAcao
					.add((BigDecimal.ONE.subtract(desconto.fatorDesconto)).multiply(CEM));
		}
		
		if(desconto.valorDescontoUnitario.doubleValue() > 0){
			if(desconto.tipoAcaoTatica.equals("0") || desconto.tipoAcaoTatica.equals("")){
				valorDesconto = valorDesconto.add(desconto.valorDescontoUnitario);
			}else {
				if(desconto.tipoAcaoTatica.equals("1")){
					valorDescontoEspecial = valorDescontoEspecial.add(desconto.valorDescontoUnitario);
				}else if (desconto.tipoAcaoTatica.equals("4")){
					valorDescontoIsencao = valorDescontoIsencao.add(desconto.valorDescontoUnitario);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param precoLiquido
	 * @param percentualDescontoAcao
	 * @param valorDesconto
	 * @return
	 */
	private static BigDecimal obtemPercentualAcaoTatica(BigDecimal precoLiquido, 
			BigDecimal percentualDescontoAcao, 
			BigDecimal valorDesconto){
		BigDecimal percentual;
		
		if (percentualDescontoAcao.doubleValue() > 0){
			percentual = percentualDescontoAcao;
		} else {
			percentual = BigDecimal.ZERO;
		}
		
		if (valorDesconto.doubleValue() > 0){
			percentual = percentual.add(roundDown((valorDesconto.divide(precoLiquido)).multiply(CEM), 2));
		}
		
		return percentual;
	}
	
	/**
	 * 
	 * @param precoLiquido
	 * @param valorDescontoIsencao
	 * @return
	 */
	private static BigDecimal obtemPercentualIsencao(BigDecimal precoLiquido, BigDecimal valorDescontoIsencao){
		BigDecimal percentual = BigDecimal.ZERO;
		
		if(percentualDescontoIsencao.doubleValue() > 0){
			percentual = percentual.add(roundDown((valorDescontoIsencao.divide(precoLiquido)).divide(CEM),2));
		}
		
		return percentual;
	}
	
	/**
	 * Verifica valor minimo.
	 *
	 * @param isValido the is valido
	 * @param preco the preco
	 * @param tipoNegociacao the tipo negociacao
	 * @param percentualMaximoDesconto the percentual maximo desconto
	 * @param mercadoria the mercadoria
	 * @param siglaEstado the sigla estado
	 * @param condicaoPagamento the condicao pagamento
	 * @param cliente the cliente
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param quantidadeMercadoriaPedido the quantidade mercadoria pedido
	 * @param percentualAcrescimoVirtual the percentual acrescimo virtual
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @param codigoFilialExpedicao 
	 */
	public static boolean verificaValorMinimo(final boolean isValido, 
			final Item item,
			final Integer tipoNegociacao, 
			final Mercadoria mercadoria,
			final CondicaoPagamento condicaoPagamento, 
			final Cliente cliente, 
			final String siglaEstadoOrigem, 
			final String siglaEstadoDestino, 
			final Integer quantidadeMercadoriaPedido, 
			BigDecimal percentualAcrescimoVirtual,
			final Integer codigoFilialFaturamento,
			BigDecimal percentualMaxDesconto, Integer codigoFilialExpedicao){
		
		Preco preco = item.preco;
		
		BigDecimal percentualAjuste;
		
		if(!tipoNegociacao.equals(TipoNegociacao.VENDA.getValue()) || preco.flagUtilizaMinimo == 0 ||
				preco.valorBrutoMercadoria.doubleValue() <= 0 ||
				preco.valorBrutoCaixa.doubleValue() <= 0 ||
				preco.valorBrutoFracionado.doubleValue() <= 0 && !(isValido)){
			
			return false;
		}
		
		percentualMaxDesconto = BigDecimal.valueOf(999.99);
		
		BigDecimal valorMinimo = CalculoService.obtemValorMinimoMercadoria(mercadoria.codigo, 
				cliente.uf, 
				codigoFilialExpedicao, 
				codigoFilialFaturamento);
		
		BigDecimal fator = BigDecimal.ONE;
		
		if(valorMinimo != null){
			if(valorMinimo.doubleValue() > 0){
				if(isValido){
					return true;
				}
			}	
			
			fator = BigDecimal.ONE;
			if( !condicaoPagamento.tipoCobrancaCondicao.equals(TipoCobranca.CASH.getValue()) ){
				// O valor minimo do item nao considera os encargos financeiros, para qualquer condicao de pagamento.
				boolean isFatorVendor = CondicaoPagamentoService.isUtilizadoFatorVendor(condicaoPagamento,
						mercadoria,
						cliente,
						siglaEstadoOrigem,
						siglaEstadoDestino);
				if(isFatorVendor){
					fator = condicaoPagamento.fatorEncargoVendor;
				}else {
					fator = condicaoPagamento.fatorCondicaoPagamento;
				}
			}
			
			BigDecimal quantidadeMercadoria = (quantidadeMercadoriaPedido <= 0) ? 
					BigDecimal.ONE : new BigDecimal(quantidadeMercadoriaPedido);
			
			percentualMaxDesconto = BigDecimal.ZERO;
			
			mercadoria.valorMinimoMercadoria = valorMinimo;
			valorMinimo = valorMinimo.multiply(quantidadeMercadoria);
			
			BigDecimal valorSemEncargos=BigDecimal.ZERO;
			
			if(fator.doubleValue() > 0){
				valorSemEncargos = roundDown(preco.valorBrutoTMP.doubleValue() 
						/ fator.doubleValue(), 2).multiply(quantidadeMercadoria);
			}
			
			if(valorSemEncargos.doubleValue() <= 0){
				valorSemEncargos = BigDecimal.valueOf(0.01);
			}
			
			percentualAjuste = roundDown(valorMinimo.doubleValue() / valorSemEncargos.doubleValue(), 4);
			percentualAjuste = BigDecimal.valueOf(((1 - percentualAjuste.doubleValue()) * 100 ) * 2);
			
			while (valorSemEncargos.doubleValue() != valorMinimo.doubleValue()){
//				percentualAjuste = roundDown( (percentualAjuste.doubleValue() / 2), 2);
				percentualAjuste = roundFloor((percentualAjuste.doubleValue() / 2), 2);
				
				if(valorSemEncargos.doubleValue() < valorMinimo.doubleValue()){
					if(percentualAjuste.doubleValue() <= 0){
						if(percentualAjuste.doubleValue() == 0){
							percentualAjuste = BigDecimal.valueOf(-0.01);
						}
						percentualMaxDesconto = percentualMaxDesconto.add(percentualAjuste);
					}else {
						percentualMaxDesconto = percentualMaxDesconto.subtract(percentualAjuste);
					}
				}else {
					if(percentualAjuste.doubleValue() < 0){
						percentualMaxDesconto = percentualMaxDesconto.subtract(percentualAjuste);
					}else {
						if(percentualAjuste.doubleValue() == 0){
							percentualAjuste = BigDecimal.valueOf(-0.01);
						}
						percentualMaxDesconto = percentualMaxDesconto.add(percentualAjuste);						
					}
				}
				/**
				 * dblVlrSemEnc = ::Trunca(
				 * (ROUNDDBL((m_dblVlrBrtTmp * (1 - (dblPerMaxDsc / 100))), NUM_CSA_DEC_DSC)
				 *  / dFator), NUM_CSA_DEC_DSC) 
				 *  * iQdeMer;
				 */
				
				valorSemEncargos = roundUP(preco.valorBrutoTMP
						.multiply(BigDecimal.ONE.subtract((percentualMaxDesconto.divide(CEM)))), 2);
				
				if(fator.doubleValue()>0){
					valorSemEncargos = roundDown(valorSemEncargos.doubleValue() / fator.doubleValue() ,2)
							.multiply(quantidadeMercadoria);
				}
				
					
				if(valorSemEncargos.doubleValue() <= 0){
					valorSemEncargos = BigDecimal.valueOf(0.01);
				}
				
				if(( valorSemEncargos.doubleValue() >= valorMinimo.doubleValue()) 
						&& (percentualAjuste.doubleValue() == 0.01 || percentualAjuste.doubleValue() == -0.01)){
					break;
				}		
			}
			if(percentualMaxDesconto.doubleValue() < 0){
				//Comentario tirado do fonte Martins Legado
				// Obsoleto depois que passamos a trocar o preco minimo se o valor da caixa estiver abaixo do mesmo
				percentualAcrescimoVirtual = percentualMaxDesconto.negate();
				CalculoService.substituiPrecoMinimo(item, fator, mercadoria, percentualAcrescimoVirtual);
				percentualMaxDesconto = BigDecimal.ZERO;
			}else {
				percentualAcrescimoVirtual = BigDecimal.ZERO;
			}
			percentualMaximoDesconto = percentualMaxDesconto;
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica valor minimo caixa.
	 *
	 * @param isValido the is valido
	 * @param preco the preco
	 * @param tipoNegociacao the tipo negociacao
	 * @param mercadoria the mercadoria
	 * @param siglaEstado the sigla estado
	 * @param condicaoPagamento the condicao pagamento
	 * @param cliente the cliente
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param quantidadeMercadoriaPedido the quantidade mercadoria pedido
	 * @param percentualAcrescimoVirtual the percentual acrescimo virtual
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @param percentualMaxDesconto the percentual max desconto
	 * @return true, if successful
	 */
	private static boolean verificaValorMinimoCaixa(final boolean isValido, 
			final Item item, 
			final Integer tipoNegociacao, 
			final Mercadoria mercadoria, 
			final CondicaoPagamento condicaoPagamento, 
			final Cliente cliente, 
			final String siglaEstadoOrigem, 
			final String siglaEstadoDestino, 
			BigDecimal percentualAcrescimoVirtual,
			final Integer codigoFilialFaturamento,
			final Integer codigoFilialExpedicao,
			BigDecimal percentualMaxDesconto){
		
		Preco preco = item.preco;
		
		BigDecimal percentualAjuste;
		
		if(!tipoNegociacao.equals(TipoNegociacao.VENDA.getValue()) || preco.flagUtilizaMinimo == 0 ||
				preco.valorBrutoMercadoria.doubleValue() <= 0 ||
				preco.valorBrutoCaixa.doubleValue() <= 0 ||
				preco.valorBrutoFracionado.doubleValue() <= 0 && !(isValido)){
			
			return false;
		}
		
		percentualMaxDesconto = new BigDecimal(999.99);
		
		BigDecimal valorMinimo = CalculoService.obtemValorMinimoMercadoria(mercadoria.codigo, 
				siglaEstadoDestino, 
				codigoFilialExpedicao, 
				codigoFilialFaturamento);
		
		BigDecimal fator = BigDecimal.ONE;
		
		if(valorMinimo != null){
			if(valorMinimo.doubleValue() > 0){
				if(isValido){
					return true;
				}
			}	
			
			if( !condicaoPagamento.tipoCobrancaCondicao.equals(TipoCobranca.CASH.getValue()) ){
				// O valor minimo do item nao considera os encargos financeiros, para qualquer condicao de pagamento.
				boolean isFatorVendor = CondicaoPagamentoService.isUtilizadoFatorVendor(condicaoPagamento,
						mercadoria,
						cliente,
						siglaEstadoOrigem,
						siglaEstadoDestino);
				if(isFatorVendor){
					fator = condicaoPagamento.fatorEncargoVendor;
				}else {
					fator = condicaoPagamento.fatorCondicaoPagamento;
				}
			}
						
			BigDecimal quantidadeMercadoria = (mercadoria.quantidadeCaixaFornecedor <= 0) ? BigDecimal.ONE : new BigDecimal(mercadoria.quantidadeCaixaFornecedor);
			
			percentualMaxDesconto = BigDecimal.ZERO;
			
			mercadoria.valorMinimoMercadoria = valorMinimo;
			valorMinimo = valorMinimo.multiply(quantidadeMercadoria);
			
			BigDecimal valorSemEncargos = BigDecimal.ZERO;
			
			if(fator.doubleValue() > 0){
				valorSemEncargos = roundDown(preco.valorBrutoCaixa.doubleValue() 
						/ fator.doubleValue(), 2).multiply(quantidadeMercadoria);
			}
			
		
			
			if(valorSemEncargos.doubleValue() <= 0){
				valorSemEncargos = BigDecimal.valueOf(0.01);
			}
			
			percentualAjuste = roundDown(valorMinimo.doubleValue() / valorSemEncargos.doubleValue(), 4);
			percentualAjuste = BigDecimal.valueOf(((1 - percentualAjuste.doubleValue()) * 100 ) * 2);
			
			while (valorSemEncargos.doubleValue() != valorMinimo.doubleValue()){
				percentualAjuste = roundFloor((percentualAjuste.doubleValue() / 2), 2);
				
				if(valorSemEncargos.doubleValue() < valorMinimo.doubleValue()){
					if(percentualAjuste.doubleValue() <= 0){
						if(percentualAjuste.doubleValue() == 0){
							percentualAjuste = BigDecimal.valueOf(-0.01);
						}
						percentualMaxDesconto = percentualMaxDesconto.add(percentualAjuste);
					}else {
						percentualMaxDesconto = percentualMaxDesconto.subtract(percentualAjuste);
					}
				}else {
					if(percentualAjuste.doubleValue() < 0){
						percentualMaxDesconto = percentualMaxDesconto.subtract(percentualAjuste);
					}else {
						if(percentualAjuste.doubleValue() == 0){
							percentualAjuste = BigDecimal.valueOf(-0.01);
						}
						percentualMaxDesconto = percentualMaxDesconto.add(percentualAjuste);						
					}
				}
				
				if(fator.doubleValue() > 0){
					valorSemEncargos = roundDown( roundUP(preco.valorBrutoCaixa.multiply(
							BigDecimal.ONE.subtract((percentualMaxDesconto.divide(CEM)))), 2).doubleValue()
							/ fator.doubleValue() ,2)
							.multiply(quantidadeMercadoria);
				}
							
							
				if(valorSemEncargos.doubleValue() <= 0){
					valorSemEncargos = BigDecimal.valueOf(0.01);
				}
				
				if(( valorSemEncargos.doubleValue() >= valorMinimo.doubleValue() ) 
						&& (percentualAjuste.doubleValue() == -0.01 || percentualAjuste.doubleValue() == 0.01)){
					break;
				}		
			}
			if(percentualMaxDesconto.doubleValue() < 0){
				//Comentario tirado do fonte Martins Legado
				// Obsoleto depois que passamos a trocar o preco minimo se o valor da caixa estiver abaixo do mesmo
				percentualAcrescimoVirtual = percentualAcrescimoVirtual.multiply(percentualMaxDesconto).negate();
				CalculoService.substituiPrecoMinimo(item, fator, mercadoria, percentualAcrescimoVirtual);
				percentualMaxDesconto = BigDecimal.ZERO;
			}else {
				percentualAcrescimoVirtual = BigDecimal.ZERO;
			}
			
			percentualMaximoDesconto = percentualMaxDesconto;
			
			return true;
		}
		return false;
	}
	
	
	/**
	 * Verifica valor maximo de isencao.
	 *
	 * @param fatorCondicaoPagamento the fator condicao pagamento
	 * @param quantidadeMercadoria the quantidade mercadoria
	 * @param preco the preco
	 * @return true, if successful
	 */
	private static boolean verificaValorMaximoDeIsencao(BigDecimal fatorCondicaoPagamento, 
			Integer quantidadeMercadoria,
			Preco preco){
		
		boolean isValorMaximoIsencao = false;
		
		BigDecimal valorSemEncargos;
		percentualDescontoMinimo = BigDecimal.ZERO;
		percentualMaximoAcrescimo = BigDecimal.ZERO;
		
		if(valorMaximoIsencao.doubleValue() > 0){		
			valorSemEncargos = roundDown(preco.valorBrutoMercadoria.divide(fatorCondicaoPagamento), 2);

			if(valorSemEncargos.doubleValue() <= valorMaximoIsencao.doubleValue()) {
				// Nao precisamos aplicar descontos para o produto entrar na MP do BEM
				percentualDescontoMinimo = BigDecimal.ZERO;

				percentualDescontoMinimo = obtemPercentualDescontoMinimo(valorSemEncargos, 
						fatorCondicaoPagamento, 
						quantidadeMercadoria, 
						preco);
				if (percentualMaximoAcrescimo.doubleValue() < 0) {
					percentualMaximoAcrescimo = percentualMaximoAcrescimo.multiply(BigDecimal.ONE.negate()); 
				}
				isValorMaximoIsencao = true;

			} else {			
				// Temos que aplicar descontos para o produto entrar na MP do BEM
				percentualDescontoMinimo = obtemPercentualDescontoMinimo(valorSemEncargos, 
						fatorCondicaoPagamento, 
						quantidadeMercadoria, 
						preco);
				if (percentualDescontoMinimo.doubleValue() > (percentualMaximoFlex.add(percentualDescontoIsencao)).doubleValue()) {
					// Todos os descontos aplicados inclusive o da MP do BEM n�o faz o item entrar na regra da MP.
					// Logo este produto n�o poder� ser vendido na MP do BEM
					percentualDescontoMinimo = BigDecimal.ZERO;
					isValorMaximoIsencao = false;
				} else {
					// Posso ter descontos que me fa�am chegar na MP do BEM
					isValorMaximoIsencao = true;
				}
			}
		}
		
		return isValorMaximoIsencao;
	}
	
	/**
	 * Obtem percentual desconto minimo.
	 *
	 * @param valorSemEncargos the valor sem encargos
	 * @param fatorCondicaoPagamento the fator condicao pagamento
	 * @param quantidadeMercadoria the quantidade mercadoria
	 * @param preco the preco
	 * @return the big decimal
	 */
	private static BigDecimal obtemPercentualDescontoMinimo(BigDecimal valorSemEncargos, 
			BigDecimal fatorCondicaoPagamento,
			Integer quantidadeMercadoria,
			Preco preco){
		
		if(quantidadeMercadoria == 0){
			quantidadeMercadoria = 1;
		}

		BigDecimal percentualDescontoMinimo = BigDecimal.ZERO;
		BigDecimal valorMaxIsencao =  valorMaximoIsencao;
		
		valorMaxIsencao = valorMaxIsencao.multiply(BigDecimal.valueOf(quantidadeMercadoria));
		valorSemEncargos = valorSemEncargos.multiply(BigDecimal.valueOf(quantidadeMercadoria));;

		BigDecimal percentualAjuste = roundDown((valorMaxIsencao.doubleValue() / valorSemEncargos.doubleValue()), 4);
		percentualAjuste = BigDecimal.valueOf(((1 - percentualAjuste.doubleValue()) * 100) * 2);

		while (! valorSemEncargos.equals(valorMaxIsencao)) {
			percentualAjuste = roundDown((percentualAjuste.divide(BigDecimal.valueOf(2))), 2);

			if (valorSemEncargos.doubleValue() < valorMaxIsencao.doubleValue()) {
				if (percentualAjuste.doubleValue() <= 0) {
					if (percentualAjuste.equals(BigDecimal.ZERO)) {
						percentualAjuste = BigDecimal.valueOf(-0.01);
					}

					percentualDescontoMinimo = percentualDescontoMinimo.add(percentualAjuste);
				} else {
					percentualDescontoMinimo = percentualDescontoMinimo.subtract(percentualAjuste);
				}
			} else {
				if (percentualAjuste.doubleValue() <  0){
					percentualDescontoMinimo = percentualDescontoMinimo.subtract(percentualAjuste);
				} else {
					if (percentualAjuste.doubleValue() <= 0) {
						if (percentualAjuste.equals(BigDecimal.ZERO)) {
							percentualAjuste = BigDecimal.valueOf(0.01);
						}

						percentualDescontoMinimo = percentualDescontoMinimo.add(percentualAjuste);
					}
				}
			}

			valorSemEncargos = roundDown((roundUP((preco.valorBrutoMercadoria
					.multiply(BigDecimal.ONE.subtract(percentualDescontoMinimo.divide(CEM)) )), 2)
					.divide(fatorCondicaoPagamento)), 2).multiply(BigDecimal.valueOf(2));
			if (valorSemEncargos.doubleValue() <= 0) {
				valorSemEncargos = BigDecimal.valueOf(0.01);
			}

			if ((valorSemEncargos.doubleValue() <= valorMaxIsencao.doubleValue()) 
					&& ( (percentualAjuste.doubleValue() == 0.01) || (percentualAjuste.doubleValue() == -0.01) )){
				break;
			}
		}
		
		return percentualDescontoMinimo;
	}
	
	
	/**
	 * Obtem percentual flexibilizacao.
	 *
	 * @param mercadoria the mercadoria
	 * @param siglaEstado the sigla estado
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param percentualRateioBanda the percentual rateio banda
	 * @param percentualAcaoTatica the percentual acao tatica
	 * @param percentualMaximoSimplificado the percentual maximo simplificado
	 * @return the big decimal
	 */
	public static BigDecimal obtemPercentualFlexibilizacao(Mercadoria mercadoria, 
			String siglaEstadoDestino, 
			BigDecimal percentualRateioBanda, 
			BigDecimal percentualAcaoTatica,
			BigDecimal percentualMaximoSimplificado){
		
		BigDecimal percentualDescontoFlex = BigDecimal.ZERO;
		PercentualFlexibilizacao flexibilizacao = ComissaoService.obtemPercentuaisFlexibilizacao(mercadoria, 
				siglaEstadoDestino, 
				percentualRateioBanda);
		
		percentualDescontoFlex = flexibilizacao.percentualMaximoFlex;
		percentualDescontoFlex = percentualDescontoFlex.add(percentualAcaoTatica);
		percentualDescontoFlex = percentualDescontoFlex.add(percentualMaximoSimplificado);
		
		return roundDown(percentualDescontoFlex, 2);		
	}
	
	
	/**
	 * Valida flexibilizacao automatica.
	 *
	 * @param percentualDescontoMinimo the percentual desconto minimo
	 * @param percentualDescontoIsencao the percentual desconto isencao
	 * @return true, if successful
	 */
	private static boolean validaFlexibilizacaoAutomatica(BigDecimal percentualDescontoMinimo, 
			BigDecimal percentualDescontoIsencao, BigDecimal percentualFlexCliente){
		if (percentualFlexCliente.doubleValue() > 0.00)
		{
			// Cliente tem flexibiliza��o autom�tica
			BigDecimal percentualDescontoFlex = ((percentualMaximoFlex.add(percentualDescontoIsencao))
					.multiply(percentualFlexCliente)
					.divide(CEM));
			return (percentualDescontoFlex.doubleValue() >= percentualDescontoMinimo.doubleValue());
		}
		return true;
	}
	
	/**
	 * Obtem o percentual de acao tatica de cliente utilizada.
	 *
	 * @param percentualDescontoConcedido the percentual desconto concedido
	 * @param percentualMaximoAcaoTatica the percentual maximo acao tatica
	 * @param percentualMaximoSimplificado the percentual maximo simplificado
	 * @param percentualAcaoCliente the percentual acao cliente
	 * @return the big decimal
	 */
	public static BigDecimal calculaAcaoTaticaUtilizadaDoCliente(BigDecimal percentualDescontoConcedido, 
			BigDecimal percentualMaximoAcaoTatica, 
			BigDecimal percentualMaximoSimplificado, 
			BigDecimal percentualAcaoCliente){
		
		BigDecimal percentualUsadoAcaoCliente = BigDecimal.ZERO;
		BigDecimal percentualUsadoAcaoTatica = BigDecimal.ZERO;
		
		if(percentualDescontoConcedido.doubleValue() >= percentualMaximoSimplificado.doubleValue()){
			if((percentualDescontoConcedido.subtract(percentualMaximoSimplificado)).doubleValue() > percentualMaximoAcaoTatica.doubleValue()){
				//Toda acao foi utilizada
				percentualUsadoAcaoTatica = percentualMaximoAcaoTatica;
			}else {
				//Utiliza apenas parte da acao tatica;
				percentualUsadoAcaoTatica = percentualDescontoConcedido.subtract(percentualMaximoSimplificado);
			}
			//Obtendo quanto da acao nao � de cliente
			BigDecimal percentualAcaoNaoCliente = percentualMaximoAcaoTatica.subtract(percentualAcaoCliente);
			
			if(percentualAcaoNaoCliente.doubleValue() > percentualUsadoAcaoTatica.doubleValue()){
				percentualUsadoAcaoCliente = BigDecimal.ZERO;
			} else {
				percentualUsadoAcaoCliente = percentualUsadoAcaoTatica.subtract(percentualAcaoNaoCliente);
			}
		}
		
		
		return percentualUsadoAcaoCliente;
	}
		
}