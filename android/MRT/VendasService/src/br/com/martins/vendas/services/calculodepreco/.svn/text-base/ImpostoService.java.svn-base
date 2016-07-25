package br.com.martins.vendas.services.calculodepreco;

import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.CEM;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundDown;
import static br.com.martins.vendas.services.calculodepreco.CalculoUtil.roundUP;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.martins.vendas.dao.CalculoPrecoDAO;
import br.com.martins.vendas.dao.ItemDisponivelDAO;
import br.com.martins.vendas.enums.TipoFinanciamento;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.DiretrizSTB;
import br.com.martins.vendas.vo.Kit;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PercentualICMS;
import br.com.martins.vendas.vo.PercentualIPI;
import br.com.martins.vendas.vo.Preco;

import com.brq.mobile.framework.log.Log;

public class ImpostoService {
	
	private static final String FLAG_PRECO_ESPECIAL = "*";

	/**
	 * 
	 * @param condicaoPagamento
	 * @param cliente 
	 * @param mercadoria 
	 * @param siglaEstadoOrigem 
	 * @param siglaEstadoDestino 
	 * @param valorBrutoTemp 
	 * @param numeroRelacaoCidade 
	 * @param codigoFilialFaturamento 
	 * @param codigoFilialExpedicao 
	 * @param valorDescontoBeneficio 
	 * @param codigoAtividade 
	 */
	public static void calculaImpostos (Preco precoBruto, 
			CondicaoPagamento condicaoPagamento, 
			Cliente cliente, 
			Mercadoria mercadoria, 
			String siglaEstadoOrigem, 
			String siglaEstadoDestino,
			Integer quantidadeMercadoriaPedido,
			Integer quantidadeMercadoriaSimulada,
			BigDecimal valorBrutoTemp, 
			Integer numeroRelacaoCidade, 
			Integer codigoFilialFaturamento, 
			Integer codigoFilialExpedicao, 
			BigDecimal valorDescontoBeneficio, 
			Integer codigoAtividade,
			Item item, 
			Integer tipoNegociacao){
		
		long tempoInicio = new Date().getTime();
		
		boolean isAplicacaoFatorVendor = false;
		BigDecimal fator = BigDecimal.ONE;

		if ( condicaoPagamento.tipoFinanciamento.equals(TipoFinanciamento.VENDOR.getValue()) || 
				condicaoPagamento.tipoFinanciamento.equals(TipoFinanciamento.TRIBANCO.getValue()) ) {
			
			if( condicaoPagamento.indicaCondicaoTribanco == 0 &&
					!(ClienteService.isClienteTribanco(cliente)) ){
				isAplicacaoFatorVendor = CalculoService.validaValorMinimoEstadualENacional(mercadoria, 
						siglaEstadoOrigem, 
						siglaEstadoDestino);
			}
			
			if(isAplicacaoFatorVendor){
				fator = condicaoPagamento.fatorEncargoVendor;
			}else{
				fator = condicaoPagamento.fatorCondicaoPagamento;
			}
			
		}		
		if(quantidadeMercadoriaPedido <= 0){
			quantidadeMercadoriaPedido = 1;
		}
		
		PercentualIPI percentualIPI = CalculoPrecoDAO.obtemIPIMercadoria(mercadoria);
		
		IPI ipiMercadoria = new IPI();
		IPI ipiCaixa = new IPI();
		IPI ipiFlexivel = new IPI();
		
		BigDecimal valorEfetivo = BigDecimal.ZERO;
		
		Integer quantidadeMercadoriaAux = quantidadeMercadoriaPedido;
		
		
		if(percentualIPI.valorPalletIPI.doubleValue() > 0){
			ipiMercadoria.valorTotalIPI = fator.multiply(new BigDecimal(percentualIPI.valorPalletIPI.doubleValue() * quantidadeMercadoriaPedido));
			ipiMercadoria.valorTotalIPI = roundUP(ipiMercadoria.valorTotalIPI, 2);
			ipiMercadoria.valorTotalIPI = (ipiMercadoria.valorTotalIPI.doubleValue() < 0) ? BigDecimal.ZERO : ipiMercadoria.valorTotalIPI;

			ipiMercadoria.valorUnitarioIPI = roundUP(ipiMercadoria.valorTotalIPI.doubleValue() / quantidadeMercadoriaPedido, 2);
			
			if(quantidadeMercadoriaSimulada > 0){
				quantidadeMercadoriaAux = quantidadeMercadoriaSimulada;
			}
			
			ipiCaixa.valorTotalIPI = fator.multiply(new BigDecimal(percentualIPI.valorPalletIPI.doubleValue() * quantidadeMercadoriaAux));
			ipiCaixa.valorTotalIPI = roundUP(ipiCaixa.valorTotalIPI, 2);
			ipiCaixa.valorTotalIPI = (ipiCaixa.valorTotalIPI.doubleValue() < 0) ? BigDecimal.ZERO : ipiCaixa.valorTotalIPI;
			
			ipiCaixa.valorUnitarioIPI = roundUP(ipiCaixa.valorTotalIPI.doubleValue() / quantidadeMercadoriaAux, 2);
			
			ipiFlexivel.valorTotalIPI = ipiMercadoria.valorTotalIPI;
			ipiFlexivel.valorUnitarioIPI = ipiMercadoria.valorUnitarioIPI;
			
		} else {
			if(quantidadeMercadoriaSimulada > 0){
				quantidadeMercadoriaAux = quantidadeMercadoriaSimulada;
			}
			//CALCULA O IPI MERCADORIA			
			ipiMercadoria = calculaIPIMercadoria(fator, 
					quantidadeMercadoriaAux, valorEfetivo, item.valorLiquidoMercadoria, 
					ipiMercadoria.valorTotalIPI, percentualIPI.percentualIPI);
			
			// CALCULA O IPI DA CAIXA
			ipiCaixa =  calculaIPICaixa(fator, 
					quantidadeMercadoriaAux, valorEfetivo, item.valorLiquidoCaixa, 
					ipiCaixa.valorTotalIPI, percentualIPI.percentualIPI);
			
			// CALCULA O IPI (sem desconto Flexivel)
			ipiFlexivel = calculaIPIFlexivel(fator,
					quantidadeMercadoriaPedido, valorEfetivo, valorBrutoTemp,
				ipiFlexivel.valorTotalIPI, percentualIPI.percentualIPI);
		}
		
		Imposto impostoMercadoria = new Imposto();
		Imposto impostoCaixa = new Imposto();
		Imposto impostoFlexivel = new Imposto();

		if(mercadoria.indicaMercadoriaKit == 1){
			Item itemKit = calculaImpostoKIT(mercadoria, 
					fator, 
					item.valorLiquidoMercadoria,
					item.valorLiquidoCaixa, 
					valorBrutoTemp, 
					numeroRelacaoCidade,
					codigoFilialFaturamento, 
					codigoFilialExpedicao,
					ipiMercadoria, 
					ipiCaixa, 
					ipiFlexivel,
					quantidadeMercadoriaPedido, 
					cliente, siglaEstadoDestino,
					siglaEstadoOrigem, 
					codigoAtividade, 
					valorDescontoBeneficio,
					percentualIPI, 
					condicaoPagamento,
					tipoNegociacao);

			item.valorCaixaComImposto = itemKit.valorCaixaComImposto;
			item.valorLiquidoComImposto = itemKit.valorLiquidoComImposto;
			item.valorUnitarioCaixaComImposto = itemKit.valorUnitarioCaixaComImposto;
			item.valorUnitarioComImposto = item.valorLiquidoComImposto
					.divide(new BigDecimal(mercadoria.fatorConversaoUnitario));


		} else {
			Integer grupoSTB = CalculoPrecoDAO.obtemGrupoSTBMercadoria(mercadoria.codigoGrupoNCM, siglaEstadoOrigem, siglaEstadoDestino);
			mercadoria.grupoSTB = grupoSTB;
			if(grupoSTB != null) {

				impostoMercadoria = calculaSTB(mercadoria, 
						percentualIPI, 
						fator,
						quantidadeMercadoriaPedido, 
						ipiMercadoria.valorTotalIPI,
						valorDescontoBeneficio,
						item.valorLiquidoMercadoria,
						codigoAtividade,
						siglaEstadoOrigem,
						siglaEstadoDestino, 
						cliente,
						codigoFilialExpedicao, 
						codigoFilialFaturamento,
						numeroRelacaoCidade);
				impostoCaixa = calculaSTB(mercadoria,
						percentualIPI,
						fator,
						quantidadeMercadoriaAux,
						ipiCaixa.valorTotalIPI,
						valorDescontoBeneficio,
						item.valorLiquidoCaixa,
						codigoAtividade,
						siglaEstadoOrigem,
						siglaEstadoDestino,
						cliente,
						codigoFilialExpedicao,
						codigoFilialFaturamento,
						numeroRelacaoCidade);
				impostoFlexivel = calculaSTB(mercadoria, 
						percentualIPI,
						fator,
						quantidadeMercadoriaPedido,
						ipiFlexivel.valorTotalIPI,
						valorDescontoBeneficio,
						valorBrutoTemp,
						codigoAtividade,
						siglaEstadoOrigem,
						siglaEstadoDestino,
						cliente,
						codigoFilialExpedicao,
						codigoFilialFaturamento,
						numeroRelacaoCidade);

				precoBruto.impostoMercadoria = impostoMercadoria;
				precoBruto.impostoCaixa = impostoCaixa;
				precoBruto.impostoFlexivel = impostoFlexivel;
			}

			item.valorLiquidoComImposto = roundUP(item.valorLiquidoMercadoria
					.add(impostoMercadoria.valorSTBUnitario)
					.add(ipiMercadoria.valorUnitarioIPI)
					.add(item.frete),2);

			item.valorUnitarioCaixaComImposto = item.valorLiquidoCaixa
					.add(impostoCaixa.valorSTBUnitario)
					.add(ipiCaixa.valorUnitarioIPI)
					.add(item.freteCaixa);
			item.valorUnitarioCaixaComImposto = roundUP(item.valorUnitarioCaixaComImposto.doubleValue()  / mercadoria.fatorConversaoUnitario, 4);

			item.valorCaixaComImposto = precoBruto.valorBrutoCaixa
					.add(impostoCaixa.valorSTBUnitario)
					.add(ipiCaixa.valorUnitarioIPI)
					.add(item.freteCaixa);
					//.divide(BigDecimal.valueOf(mercadoria.fatorConversaoUnitario), BigDecimal.ROUND_CEILING), 4);
			item.valorCaixaComImposto = roundUP(item.valorCaixaComImposto.doubleValue()  / mercadoria.fatorConversaoUnitario, 4);
					
			item.valorUnitarioComImposto = roundUP(item.valorLiquidoComImposto.doubleValue() / 
					mercadoria.fatorConversaoUnitario,4);
		}

		System.out.println("Preco Liquido Mercadoria: " + item.valorLiquidoMercadoria);
		System.out.println("Valor Liquido Com imposto: " + item.valorLiquidoComImposto);
		System.out.println("Valor Caixa com Imposto: " + item.valorCaixaComImposto);
		System.out.println("Valor Unitario Caixa com Imposto: " + item.valorUnitarioCaixaComImposto);
		System.out.println("Valor unitario com Imposto: " +  item.valorUnitarioComImposto);	
		
		long tempoFim = new Date().getTime() - tempoInicio;
		System.out.println("ImpostoService - calculaImposto - tempo gasto: " + TimeUnit.MILLISECONDS.toMillis(tempoFim) + " milisegundos - Segundos: " + TimeUnit.MILLISECONDS.toSeconds(tempoFim));
		
	}
	
	
	/**
	 * Calcula stb.
	 *
	 * @param mercadoria the mercadoria
	 * @param ipi the ipi
	 * @param fator the fator
	 * @param quantidadeMercadoriaPedido the quantidade mercadoria pedido
	 * @param valorTotalIPI the valor total ipi
	 * @param percentualDescontoBeneficio the percentual desconto beneficio
	 * @param valorBase the valor base
	 * @param codigoAtividade the codigo atividade
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param cliente the cliente
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @param numeroRelacaoCidade the numero relacao cidade
	 * @return the imposto
	 */
	private static Imposto calculaSTB(Mercadoria mercadoria,
			PercentualIPI ipi,
			BigDecimal fator, 
			Integer quantidadeMercadoriaPedido, 
			BigDecimal valorTotalIPI, 
			BigDecimal percentualDescontoBeneficio,
			BigDecimal valorBase,
			Integer codigoAtividade, 
			String siglaEstadoOrigem, 
			String siglaEstadoDestino, 
			Cliente cliente, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer numeroRelacaoCidade){
		
		long tempoInicio = new Date().getTime();
		
		Imposto imposto = new Imposto();
		
		BigDecimal valorEfetivo = BigDecimal.ZERO;
		BigDecimal valorIPIEfetivo = BigDecimal.ZERO;
		BigDecimal fatorValorMinimo = BigDecimal.ZERO;
		BigDecimal valorEfetivoBeneficio = BigDecimal.ZERO;
		
		valorEfetivo = roundDown(valorBase.doubleValue() / fator.doubleValue(),2);
		if(valorEfetivo.doubleValue() <= 0){
			valorEfetivo = BigDecimal.valueOf(0.01);
		}
		
		valorEfetivo = valorEfetivo.multiply(new BigDecimal(quantidadeMercadoriaPedido));
		BigDecimal percentualValorEfetivo = valorEfetivo.multiply(percentualDescontoBeneficio.divide(CEM));
		valorEfetivoBeneficio = roundUP(valorEfetivo.subtract(percentualValorEfetivo),2);
		valorIPIEfetivo = roundUP(valorTotalIPI.doubleValue() / fator.doubleValue(),2);
		valorEfetivo = valorEfetivo.add(valorIPIEfetivo);
		
		if(ipi != null){
			if(CalculoPrecoDAO.mercadoriaTemICMS(mercadoria.codigoGrupoNCM, codigoAtividade)){
				valorEfetivoBeneficio = valorEfetivoBeneficio.add(valorIPIEfetivo);
			}
		}
		
		DiretrizSTB diretriz = obtemDiretrizSTBComGrupoClienteDeMaiorPrioridade(mercadoria.grupoSTB,
				siglaEstadoOrigem,
				siglaEstadoDestino,
				cliente);
		BigDecimal valorTotalSTB = BigDecimal.ZERO;
		
		if (diretriz != null) {
			diretriz.percentualCreditoSTB = obtemPercentualDeCredito(
					diretriz.percentualCreditoSTB,
					mercadoria,
					siglaEstadoOrigem,
					siglaEstadoDestino,
					codigoFilialExpedicao,
					codigoFilialFaturamento,
					numeroRelacaoCidade,
					cliente);
		
		
			if( (diretriz != null) && 
					(diretriz.tipoDiretrizSTB != 1) && (isDataVigorarValida(diretriz.dataInicioVigorar)) ){
				if(diretriz.flagValorMinimo.equals(FLAG_PRECO_ESPECIAL) || 
						diretriz.flagValorMinimo.equalsIgnoreCase("O") || 
						diretriz.flagValorMinimo.equalsIgnoreCase("V")){
					BigDecimal valorMinimo = CalculoPrecoDAO.obtemValorMinimoEstadual(mercadoria, 
							siglaEstadoOrigem, 
							siglaEstadoDestino);
					
					if(valorMinimo.doubleValue() > 0){
						valorMinimo = valorMinimo.multiply(new BigDecimal(quantidadeMercadoriaPedido));
						
						//aqui buscava o percentualCreditoSTB
						//indicaSemMargemLucro = (diretriz.percentualMargemDeLucro.doubleValue() > 0) ? false : true;
						
						if(diretriz.fatorReducaoPreco.doubleValue() <= 0){
							diretriz.fatorReducaoPreco = BigDecimal.ONE;
						}
						BigDecimal valor = valorEfetivo.multiply(diretriz.fatorReducaoPreco)
								.multiply(BigDecimal.ONE.add(diretriz.percentualMargemDeLucro.divide(CEM)));
						valor = roundUP(valor,2);
						
						if(valor.doubleValue() <= valorMinimo.doubleValue() || 
								diretriz.flagValorMinimo.equalsIgnoreCase("O") || 
								diretriz.flagValorMinimo.equalsIgnoreCase("V")){
							
							if(diretriz.flagValorMinimo.equals("O")){
								valorTotalSTB = roundUP(valorMinimo.multiply(diretriz.fatorReducaoPreco), 2);			
								valorTotalSTB = valorTotalSTB.multiply(BigDecimal.ONE.add(diretriz.percentualMargemDeLucro.divide(CEM)));
								valorTotalSTB = valorTotalSTB.multiply(diretriz.percentualDebitoSTB.divide(CEM));
								valorTotalSTB = valorTotalSTB.subtract(roundUP(valorMinimo
										.multiply(diretriz.fatorReducaoPreco),2)
										.multiply(diretriz.percentualCreditoSTB.divide(CEM)));					
								valorTotalSTB = roundUP(valorTotalSTB, 2);
								
								fatorValorMinimo = roundUP(valorMinimo.multiply(diretriz.fatorReducaoPreco),2);
								fatorValorMinimo = fatorValorMinimo.multiply(BigDecimal.ONE.add(diretriz.percentualMargemDeLucro.divide(CEM)));
								fatorValorMinimo = fatorValorMinimo.multiply(diretriz.percentualDebitoSTB.divide(CEM).subtract(BigDecimal.valueOf(0.05)));
								fatorValorMinimo = fatorValorMinimo.divide(roundUP(valorMinimo.divide(diretriz.fatorReducaoPreco) ,2));
								fatorValorMinimo = roundUP(fatorValorMinimo.multiply(diretriz.percentualCreditoSTB.divide(CEM)),2);
								
								//BigDecimal percentualValorMinimo = BigDecimal.ZERO;
								
								if (fatorValorMinimo.doubleValue() < 1.00) {							
								//	percentualValorMinimo = roundUP(fatorValorMinimo.subtract(BigDecimal.ONE).multiply(CEM),2);								
								}
								else
								{
								//	percentualValorMinimo = roundUP(fatorValorMinimo.subtract(BigDecimal.ONE).multiply(CEM),2);
									// m_strPerVlrMnm.Format(TEXT("+%.2f"), (dblFtrVlrMnm - 1) * 100);
								}
								
								if(valorTotalSTB.doubleValue() < 0){
									valorTotalSTB = BigDecimal.ZERO;
								}
								
							}else {
								valorTotalSTB = valorMinimo.multiply(diretriz.percentualDebitoSTB.divide(CEM));
								valorTotalSTB = valorTotalSTB.subtract(valorEfetivoBeneficio.multiply(diretriz.fatorReducaoPreco).
										multiply(diretriz.percentualCreditoSTB.divide(CEM)));
								valorTotalSTB = roundUP(valorTotalSTB,2);
							}
						}else {
							if(diretriz.fatorReducaoPreco.doubleValue() <= 0){
								diretriz.fatorReducaoPreco = BigDecimal.ONE;
							}
							
							valorTotalSTB = roundUP(valorEfetivo.multiply(diretriz.fatorReducaoPreco),2);
							valorTotalSTB = valorTotalSTB.multiply(BigDecimal.valueOf(( 1 + (diretriz.percentualMargemDeLucro.divide(CEM)).doubleValue())));					
							valorTotalSTB = valorTotalSTB.multiply(diretriz.percentualDebitoSTB.divide(CEM));
							valorTotalSTB = BigDecimal.valueOf(valorTotalSTB.doubleValue() - (roundUP(valorEfetivoBeneficio.doubleValue() * diretriz.fatorReducaoPreco.doubleValue(), 2).doubleValue() * diretriz.percentualCreditoSTB.doubleValue() / 100 ));					
							
							valorTotalSTB = roundUP(valorTotalSTB, 2);
								
						}
					}else {
						valorTotalSTB = calculaSTBComValorMinimoNacional(
								mercadoria, 
								quantidadeMercadoriaPedido,
								siglaEstadoDestino, 
								cliente, 
								codigoFilialExpedicao,
								codigoFilialFaturamento, 
								numeroRelacaoCidade,
								valorEfetivo, valorEfetivoBeneficio, diretriz, siglaEstadoOrigem);
									
					}
				} else {
					
					//Aqui buscava o percentualCreditoSTB
					//indicaSemMargemLucro = (diretriz.percentualMargemDeLucro.doubleValue() > 0) ? false : true;
					
					if(diretriz.fatorReducaoPreco.doubleValue() <= 0){
						diretriz.fatorReducaoPreco = BigDecimal.ONE;
					}
					
					valorTotalSTB = roundUP(valorEfetivo.doubleValue() * diretriz.fatorReducaoPreco.doubleValue(), 2);
					valorTotalSTB = BigDecimal.valueOf(valorTotalSTB.doubleValue() * (BigDecimal.ONE.doubleValue() + diretriz.percentualMargemDeLucro.doubleValue() / 100));
					valorTotalSTB = valorTotalSTB.multiply(diretriz.percentualDebitoSTB.divide(CEM));
					valorTotalSTB = BigDecimal.valueOf(valorTotalSTB.doubleValue() - (roundUP(valorEfetivoBeneficio.doubleValue() * diretriz.fatorReducaoPreco.doubleValue(), 2).doubleValue() * diretriz.percentualCreditoSTB.doubleValue() / 100));
					valorTotalSTB = roundUP(valorTotalSTB,2);
					
				}
			
				valorTotalSTB = valorTotalSTB.multiply(fator);
				valorTotalSTB = roundUP(valorTotalSTB,2);
			}
		
		}
		
		if(valorTotalSTB.doubleValue() < 0){
			valorTotalSTB = BigDecimal.ZERO;
		}
		
		imposto.valorSTBUnitario = roundUP(valorTotalSTB.doubleValue() / quantidadeMercadoriaPedido, 2);
		
		imposto.valorSTBTotal = valorTotalSTB;
		
		System.out.println("STB TOTAL: " + valorTotalSTB);
		System.out.println("STB Unitario: " + imposto.valorSTBUnitario);
		
		System.out.println("ImpostoService - calculaSTB - tempo gasto: " + TimeUnit.MILLISECONDS.toMillis(new Date().getTime() - tempoInicio));
		
		return imposto;
		
	}
	
	/**
	 * Calcula o valor Total do STB com o Valor Minimo Nacional
	 * @param mercadoria
	 * @param quantidadeMercadoriaPedido
	 * @param siglaEstadoDestino
	 * @param cliente
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param numeroRelacaoCidade
	 * @param valorEfetivo
	 * @param valorEfetivoBeneficio
	 * @param diretriz
	 * @param siglaEstadoOrigem 
	 * @return
	 */
	private static BigDecimal calculaSTBComValorMinimoNacional(
			Mercadoria mercadoria, 
			Integer quantidadeMercadoriaPedido,
			String siglaEstadoDestino, 
			Cliente cliente,
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer numeroRelacaoCidade, 
			BigDecimal valorEfetivo,
			BigDecimal valorEfetivoBeneficio, 
			DiretrizSTB diretriz, 
			String siglaEstadoOrigem) {
		
		BigDecimal valorTotalSTB;
		BigDecimal valorMinimo;
		valorMinimo = CalculoPrecoDAO.obtemValorMinimoNacional(mercadoria);
		valorMinimo = valorMinimo.multiply(new BigDecimal(quantidadeMercadoriaPedido));
		
		diretriz.percentualCreditoSTB = obtemPercentualDeCredito(
				diretriz.percentualCreditoSTB, 
				mercadoria,
				siglaEstadoOrigem, 
				siglaEstadoDestino,
				codigoFilialExpedicao, 
				codigoFilialFaturamento,
				numeroRelacaoCidade, 
				cliente);
	//	boolean indicaSemMargemLucro = (diretriz.percentualMargemDeLucro.doubleValue() > 0) ? false : true;
		
		if(diretriz.fatorReducaoPreco.doubleValue() <= 0){
			diretriz.fatorReducaoPreco = BigDecimal.ONE;
		}
		BigDecimal valor = valorEfetivo.multiply(diretriz.fatorReducaoPreco)
				.multiply(BigDecimal.ONE.add(diretriz.percentualMargemDeLucro.divide(CEM)));
		valor = roundUP(valor,2);
		
		if( (valorMinimo.doubleValue()> 0) && valor.doubleValue() <= valorMinimo.doubleValue() ){
			
			valorTotalSTB = valorMinimo.multiply(diretriz.percentualDebitoSTB.divide(CEM));
			valorTotalSTB = valorTotalSTB.subtract(roundUP(valorEfetivoBeneficio.multiply(diretriz.fatorReducaoPreco),2).multiply(diretriz.percentualCreditoSTB.divide(CEM)));
					
			valorTotalSTB = roundUP(valorTotalSTB, 2);
			
		}else {
			
			valorTotalSTB = roundUP(valorEfetivo.multiply(diretriz.fatorReducaoPreco), 2);
			valorTotalSTB = valorTotalSTB.multiply(BigDecimal.ONE.add(diretriz.percentualMargemDeLucro.divide(CEM)));
			valorTotalSTB = valorTotalSTB.multiply(diretriz.percentualDebitoSTB.divide(CEM));
			valorTotalSTB = valorTotalSTB.subtract(roundUP(valorEfetivoBeneficio.multiply(diretriz.fatorReducaoPreco),2).multiply(diretriz.percentualCreditoSTB.divide(CEM)));
			
			valorTotalSTB = roundUP(valorTotalSTB, 2);	
		}
		return valorTotalSTB;
	}
	
	/**
	 * Calcula Impostos de mercadorias KIT
	 * @param mercadoria
	 * @param valorLiquidoMercadoriaKit
	 * @param valorLiquidoCaixaKit
	 * @param valorBrutoTempKit
	 * @param numeroRelacaoCidade
	 * @param codigoFilialFaturamento
	 * @param codigoFilialExpedicao
	 * @param valorTotalIPIMercadoria
	 * @param valorTotalIPICaixa
	 * @param valorTotalIPIFlexivel
	 * @param quantidadeMercadoriaPedido 
	 * @param cliente 
	 * @param siglaEstadoDestino 
	 * @param siglaEstadoOrigem 
	 * @param codigoAtividade 
	 * @param percentualDescontoBeneficio 
	 * @return 
	 */
	private static Item calculaImpostoKIT(Mercadoria mercadoria,
			BigDecimal fator,
			BigDecimal valorLiquidoMercadoriaKit, 
			BigDecimal valorLiquidoCaixaKit,
			BigDecimal valorBrutoTempKit, 
			Integer numeroRelacaoCidade,
			Integer codigoFilialFaturamento, 
			Integer codigoFilialExpedicao,
			IPI ipiMercadoria, 
			IPI ipiCaixa, 
			IPI ipiFlexivel, 
			Integer quantidadeMercadoriaPedido, 
			Cliente cliente, 
			String siglaEstadoDestino, 
			String siglaEstadoOrigem, 
			Integer codigoAtividade, 
			BigDecimal percentualDescontoBeneficio, 
			PercentualIPI percentualIPI, 
			CondicaoPagamento condicaoPagamento,
			Integer tipoNegociacao) {
		
		Item item = new Item();
		
		//Integer codigoMercadoriaAtual = mercadoria.codigo;
		BigDecimal valorTotalIPIAtual = ipiMercadoria.valorTotalIPI;
		BigDecimal valorTotalIPICaixaAtual = ipiCaixa.valorTotalIPI;
		BigDecimal valorTotalIPIFlexivelAtual = ipiFlexivel.valorTotalIPI;
		
		BigDecimal valorLiquidoMercadoria = valorLiquidoMercadoriaKit;
		BigDecimal valorLiquidoCaixa = valorLiquidoCaixaKit;
		BigDecimal valorBrutoTMP = valorBrutoTempKit;
		
		Imposto stbMercadoria = new Imposto();
		Imposto stbCaixa = new Imposto();
		Imposto stbFlexivel = new Imposto();
		
		
		List<Kit> listaDeKits = CalculoPrecoDAO.buscaMercadoriaKit(mercadoria,
				codigoFilialExpedicao, codigoFilialFaturamento,
				numeroRelacaoCidade);
		if (listaDeKits != null) {
			for (Kit kit : listaDeKits) {
				if (kit.codigoFilialExpedicao == codigoFilialExpedicao
						&& kit.codigoFilialFaturamento == codigoFilialFaturamento
						&& kit.numeroRelacaoCidade == numeroRelacaoCidade) {

					Integer codigoMercadoria = kit.codigoMercadoria;
					valorLiquidoMercadoria = roundUP(valorLiquidoMercadoriaKit.multiply(kit.percentualPrecoMercadoria).divide(CEM),2);
					valorLiquidoCaixa = roundUP(valorLiquidoCaixaKit.multiply(kit.percentualPrecoMercadoria).divide(CEM), 2);
					valorBrutoTempKit = roundUP(valorBrutoTempKit.multiply(kit.percentualPrecoMercadoria).divide(CEM),2);

					valorTotalIPIAtual = roundUP(ipiMercadoria.valorTotalIPI
							.multiply(kit.percentualPrecoMercadoria)
							.divide(CEM), 2);
					valorTotalIPICaixaAtual = roundUP(ipiCaixa.valorTotalIPI
							.multiply(kit.percentualPrecoMercadoria)
							.divide(CEM), 2);
					valorTotalIPIFlexivelAtual = roundUP(ipiFlexivel.valorTotalIPI
							.multiply(kit.percentualPrecoMercadoria)
							.divide(CEM), 2);

					//if(obtemDescricaoMercadoria())
					
					Mercadoria mercadoriaKit = ItemDisponivelDAO.buscaMercadoriaPorCodigo(codigoMercadoria);
					
					if (mercadoriaKit.codigo != null) {
						kit.codigoGrupoNCM = mercadoriaKit.codigoGrupoNCM;
					}
					
					Integer grupoSTB = CalculoPrecoDAO.obtemGrupoSTBMercadoria(mercadoriaKit.codigoGrupoNCM, siglaEstadoOrigem, siglaEstadoDestino);
					mercadoriaKit.grupoSTB = grupoSTB;
					if(grupoSTB != null){				
						Imposto impostoMercadoria = calculaSTB(mercadoriaKit, percentualIPI, fator, quantidadeMercadoriaPedido, valorTotalIPIAtual, percentualDescontoBeneficio, valorLiquidoMercadoria, codigoAtividade, siglaEstadoOrigem, siglaEstadoDestino, cliente, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);					
						Imposto impostoCaixa = calculaSTB(mercadoriaKit, percentualIPI, fator, quantidadeMercadoriaPedido, valorTotalIPICaixaAtual, percentualDescontoBeneficio, valorLiquidoCaixa, codigoAtividade, siglaEstadoOrigem, siglaEstadoDestino, cliente, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);
						Imposto impostoFlexivel = calculaSTB(mercadoriaKit, percentualIPI, fator, quantidadeMercadoriaPedido, valorTotalIPIFlexivelAtual, percentualDescontoBeneficio, valorBrutoTMP, codigoAtividade, siglaEstadoOrigem, siglaEstadoDestino, cliente, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);
						
						stbMercadoria.valorSTBTotal = stbMercadoria.valorSTBTotal.add(impostoMercadoria.valorSTBTotal);
						stbMercadoria.valorSTBUnitario = stbMercadoria.valorSTBUnitario.add(impostoMercadoria.valorSTBUnitario);
						stbMercadoria.percentualValorMinimo = impostoMercadoria.percentualValorMinimo;
						
						stbCaixa.valorSTBTotal = stbCaixa.valorSTBTotal.add(impostoCaixa.valorSTBTotal);
						stbCaixa.valorSTBUnitario = stbCaixa.valorSTBUnitario.add(impostoCaixa.valorSTBUnitario);
						
						stbFlexivel.valorSTBUnitario = stbFlexivel.valorSTBUnitario.add(impostoFlexivel.valorSTBUnitario);
					}
				}
			}

		} else {
			Imposto impostoMercadoria = calculaSTB(mercadoria, percentualIPI, fator, quantidadeMercadoriaPedido, valorTotalIPIAtual, percentualDescontoBeneficio, valorLiquidoMercadoria, codigoAtividade, siglaEstadoOrigem, siglaEstadoDestino, cliente, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);					
			Imposto impostoCaixa = calculaSTB(mercadoria, percentualIPI, fator, quantidadeMercadoriaPedido, valorTotalIPICaixaAtual, percentualDescontoBeneficio, valorLiquidoCaixaKit, codigoAtividade, siglaEstadoOrigem, siglaEstadoDestino, cliente, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);
			Imposto impostoFlexivel = calculaSTB(mercadoria, percentualIPI, fator, quantidadeMercadoriaPedido, valorTotalIPIFlexivelAtual, percentualDescontoBeneficio, valorBrutoTempKit, codigoAtividade, siglaEstadoOrigem, siglaEstadoDestino, cliente, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);
			
			stbMercadoria.valorSTBTotal = stbMercadoria.valorSTBTotal.add(impostoMercadoria.valorSTBTotal);
			stbMercadoria.valorSTBUnitario = stbMercadoria.valorSTBUnitario.add(impostoMercadoria.valorSTBUnitario);
			stbMercadoria.percentualValorMinimo = impostoMercadoria.percentualValorMinimo;
			
			stbCaixa.valorSTBTotal = stbCaixa.valorSTBTotal.add(impostoCaixa.valorSTBTotal);
			stbCaixa.valorSTBUnitario = stbCaixa.valorSTBUnitario.add(impostoCaixa.valorSTBUnitario);
			
			stbFlexivel.valorSTBUnitario = stbFlexivel.valorSTBUnitario.add(impostoFlexivel.valorSTBUnitario);
		}
		
		boolean isBrindeIntegral = CondicaoPagamentoService.isBrindeIntegral(tipoNegociacao, condicaoPagamento.codigoCondicaoPagamento, percentualDescontoBeneficio, BigDecimal.ZERO);
		
		if( isBrindeIntegral && !(CondicaoPagamentoService.isBrindeIntegralPromocao(condicaoPagamento.codigoCondicaoPagamento))) {
//			valorBeneficioMercadoria = valorBeneficioMercadoria.add(roundUP( valorSTBTotalBeneficio.divide(quantidadeMercadoriaPedido)  ,2));
//			valorBeneficioMercadoria = valorBeneficioMercadoria.add(roundUP( valorSTBTotalBeneficio.divide(quantidadeMercadoriaPedido)  ,2));
		}
		
		item.valorLiquidoComImposto = valorLiquidoMercadoriaKit.add(stbMercadoria.valorSTBTotal);
		item.valorCaixaComImposto   = valorLiquidoCaixaKit.add(stbCaixa.valorSTBTotal); 
		
		item.valorUnitarioCaixaComImposto = roundUP(item.valorLiquidoCaixa
				.add(stbCaixa.valorSTBUnitario)
				.add(ipiCaixa.valorUnitarioIPI)
				.add(item.freteCaixa)
				.divide(BigDecimal.valueOf(mercadoria.fatorConversaoUnitario)), 4);
				
		return item;
	}
	
	/**
	 * Calcula IPI da mercadoria
	 * @param fator
	 * @param quantidadeMercadoriaPedido
	 * @param valorEfetivo
	 * @param valorLiquidoMercadoria
	 * @param valorTotalIPIMercadoria
	 * @param percentualIPI
	 * @return
	 */
	private static IPI calculaIPIMercadoria(BigDecimal fator, 
			Integer quantidadeMercadoriaPedido, 
			BigDecimal valorEfetivo, 
			BigDecimal valorLiquidoMercadoria, 
			BigDecimal valorTotalIPIMercadoria,
			BigDecimal percentualIPI){
		
		IPI ipi = new IPI();
		
		BigDecimal valorIPIMercadoria = BigDecimal.ZERO;
		
		valorEfetivo = roundDown(valorLiquidoMercadoria.doubleValue() / fator.doubleValue(), 2);
		valorEfetivo = (valorEfetivo.doubleValue() <= 0) ? BigDecimal.valueOf(0.01) : valorEfetivo;
		
		valorEfetivo = valorEfetivo.multiply(BigDecimal.valueOf(quantidadeMercadoriaPedido));
		
		valorTotalIPIMercadoria = roundUP(valorEfetivo.multiply(percentualIPI.divide(BigDecimal.valueOf(100))),2);
		valorTotalIPIMercadoria = roundUP(valorTotalIPIMercadoria.multiply(fator),2);
		valorIPIMercadoria		=  roundUP(valorTotalIPIMercadoria.doubleValue() / quantidadeMercadoriaPedido,2);	
		
		ipi.valorUnitarioIPI = valorIPIMercadoria;
		ipi.valorTotalIPI = valorTotalIPIMercadoria;
		
		return ipi;
	}
	
	/**
	 * Calcula valor da Caixa
	 * @param fator
	 * @param quantidadeMercadoriaAux
	 * @param valorEfetivo
	 * @param valorLiquidoCaixa
	 * @param valorTotalIPICaixa
	 * @param percentualIPI
	 * @return
	 */
	private static IPI calculaIPICaixa(BigDecimal fator, 
			Integer quantidadeMercadoriaAux, 
			BigDecimal valorEfetivo, 
			BigDecimal valorLiquidoCaixa, 
			BigDecimal valorTotalIPICaixa,
			BigDecimal percentualIPI){
		
		IPI ipi = new IPI();
		
		BigDecimal valorIPICaixa = BigDecimal.ZERO;
		
		valorEfetivo = roundUP(valorLiquidoCaixa.doubleValue() / fator.doubleValue(),2);
		valorEfetivo = (valorEfetivo.doubleValue() <= 0) ? BigDecimal.valueOf(0.01) : valorEfetivo;
		
		valorEfetivo = valorEfetivo.multiply(BigDecimal.valueOf(quantidadeMercadoriaAux));
		
		valorTotalIPICaixa = roundUP(valorEfetivo.multiply(percentualIPI.divide(CEM)),2); 
		valorTotalIPICaixa = roundUP(valorTotalIPICaixa.multiply(fator),2);
		valorIPICaixa 	   = roundUP(valorTotalIPICaixa.doubleValue() /quantidadeMercadoriaAux,2);
		
		ipi.valorTotalIPI = valorTotalIPICaixa;
		ipi.valorUnitarioIPI = valorIPICaixa;
		
		return ipi;
	}
	
	/**
	 * Calcula valor do IPI Flexivel 
	 * @param fator
	 * @param quantidadeMercadoriaPedido
	 * @param valorEfetivo
	 * @param valorBrutoTemp
	 * @param valorTotalIPIFlexivel
	 * @param percentualIPI
	 * @return
	 */
	public static IPI calculaIPIFlexivel(BigDecimal fator, 
			Integer quantidadeMercadoriaPedido, 
			BigDecimal valorEfetivo, 
			BigDecimal valorBrutoTemp, 
			BigDecimal valorTotalIPIFlexivel,
			BigDecimal percentualIPI){
		IPI ipi = new IPI();
		
		BigDecimal valorIPIFlexivel = BigDecimal.ZERO;
		
		valorEfetivo = roundUP(valorBrutoTemp.doubleValue() / fator.doubleValue(),2);
		valorEfetivo = (valorEfetivo.doubleValue() <= 0) ? BigDecimal.valueOf(0.01) : valorEfetivo;
		
		valorEfetivo = valorEfetivo.multiply(BigDecimal.valueOf(quantidadeMercadoriaPedido));
		
		valorTotalIPIFlexivel = roundUP(valorEfetivo.multiply(percentualIPI.divide(CEM)), 2);
		valorTotalIPIFlexivel = roundUP(valorTotalIPIFlexivel.multiply(fator), 2);		
		valorIPIFlexivel =  roundUP(valorTotalIPIFlexivel.doubleValue() / quantidadeMercadoriaPedido, 2);		
		
		ipi.valorTotalIPI = valorTotalIPIFlexivel;
		ipi.valorUnitarioIPI = valorIPIFlexivel;
		
		return ipi;
	}
	
	/**
	 * Busca qual a diretriz STB que tem a prioridade do grupo (CODPRRGRP) para o grupo STB do cliente (GRPCLISTB)
	 * @param grupoSTB
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @param cliente 
	 * @return
	 */
	private static DiretrizSTB obtemDiretrizSTBComGrupoClienteDeMaiorPrioridade(
			Integer grupoSTB, String siglaEstadoOrigem,
			String siglaEstadoDestino, Cliente cliente) {
		List<DiretrizSTB> diretrizesSTB = CalculoPrecoDAO.obtemDiretrizesDeSTB(
				grupoSTB, siglaEstadoOrigem, siglaEstadoDestino, cliente);

		if (diretrizesSTB.size() > 0) {
			return diretrizesSTB.get(0);
		}

		return null;
	}
	
	/**
	 * Obtem percentual de creditoSTB da mercadoria baseado no percentual de credito STB.
	 * @param mercadoria
	 * @param percentualCreditoSTB
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param numeroRelacaoCliente
	 * @param cliente 
	 */
	private static BigDecimal obtemPercentualDeCredito(BigDecimal percentualCreditoSTB, 
			Mercadoria mercadoria,
			String siglaEstadoOrigem, 
			String siglaEstadoDestino,
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer numeroRelacaoCliente, 
			Cliente cliente) {
		
		BigDecimal percentualCredito = BigDecimal.ZERO;
		
		PercentualICMS percentual = CalculoPrecoDAO
				.obtemPercentualICMDaMercadoria(mercadoria, siglaEstadoOrigem,
						siglaEstadoDestino);
		if(percentualCreditoSTB.doubleValue() == 0){			
			if (percentual == null) {
				percentualCredito =  CalculoPrecoDAO.obtemICMPadraoDoEstado(codigoFilialExpedicao,
						codigoFilialFaturamento, numeroRelacaoCliente);
			}else {
				percentualCredito = percentual.percentualICMSMercadoria;
			}
			BigDecimal percentualReduzido = CalculoPrecoDAO.obtemPercentualICMReduzido(cliente, mercadoria, siglaEstadoOrigem, siglaEstadoDestino);
			if(percentualReduzido != null){
				if(percentualReduzido.doubleValue() != 0)
					percentualCredito = percentualReduzido;
			}
			
		}else {
			percentualCredito = percentualCreditoSTB;
		}

		return percentualCredito;
	}
	
	/**
	 * Obtem percentual icm.
	 *
	 * @param icmsOrigem the icms origem
	 * @param mercadoria the mercadoria
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param percentualReduzido the percentual reduzido
	 * @param isMercadoriaConsumo the is mercadoria consumo
	 * @param tipoUtilizacaoICMS the tipo utilizacao icms
	 * @param cliente 
	 * @return o valor do percentual de ICMS
	 */
	public static BigDecimal obtemPercentualICM(final Mercadoria mercadoria,
			final int codigoFilialExpedicao,
			final int codigoFilialFaturamento,
			final int numeroRelacaoCliente,
			final String siglaEstadoDestino, 
			final String siglaEstadoOrigem,
			boolean isMercadoriaConsumo,
			boolean isClienteConsumidor,
			int tipoUtilizacaoICMS, 
			Cliente cliente){
		
		BigDecimal percentualICM = BigDecimal.ZERO;
		
		long tempoInicio = new Date().getTime();
		
		if (isClienteConsumidor || isMercadoriaConsumo) {
			if (isClienteConsumidor){
				isMercadoriaConsumo = false;
			}

			if (isMercadoriaConsumo) {
				percentualICM = obtemPercentualICMSMercadoria(mercadoria, 
						siglaEstadoDestino, 
						siglaEstadoOrigem, 
						isMercadoriaConsumo, 
						isClienteConsumidor);
			} else {
				percentualICM = obtemPercentualICMSMercadoria(mercadoria, 
						siglaEstadoOrigem, 
						siglaEstadoOrigem, 
						isMercadoriaConsumo, 
						isClienteConsumidor);
			}

			if (percentualICM.doubleValue() == -1) {
				PercentualICMS icms = CalculoPrecoDAO.obtemPercentualPadraoICMSConsumo(codigoFilialExpedicao,
						codigoFilialFaturamento,
						numeroRelacaoCliente);
				if (isMercadoriaConsumo) {
					percentualICM = icms.percentualPadraoConsumo;
				} else {
					tipoUtilizacaoICMS = icms.tipoUtilizacaoICMS;
					if ((tipoUtilizacaoICMS == 0) ||
						((tipoUtilizacaoICMS == 1) && (siglaEstadoOrigem.equals(siglaEstadoDestino) )) ||
						((tipoUtilizacaoICMS == 2) && (!siglaEstadoOrigem.equals(siglaEstadoDestino) )))
					{
						percentualICM = icms.percentualPadraoConsumoOriginal;
					} else {
						percentualICM = icms.percentualOriginal;
					}
				}
			}
		} else {
			percentualICM = obtemPercentualICMSMercadoria(mercadoria, 
					siglaEstadoDestino, 
					siglaEstadoOrigem, 
					isMercadoriaConsumo, 
					isClienteConsumidor);
			if (percentualICM.doubleValue() == -1) {
				percentualICM = CalculoPrecoDAO.obtemPercentualPadraoICMSConsumo(codigoFilialExpedicao, 
						codigoFilialFaturamento, 
						numeroRelacaoCliente).percentualICMSMercadoria;
			}

			BigDecimal percentualReduzido = CalculoPrecoDAO.obtemPercentualICMReduzido(cliente, 
					mercadoria, 
					siglaEstadoOrigem, 
					siglaEstadoDestino);
			if (percentualReduzido != null){
				percentualICM = percentualReduzido;
			}
		}
		
		System.out.println("CalculoPreco - obtemPercentualICM - tempo Gasto: " 
				+ TimeUnit.MILLISECONDS.toMillis(new Date().getTime() - tempoInicio));	
		return percentualICM;
	}
	
	
	/**
	 * Obtem icm.
	 *
	 * @param mercadoria the mercadoria
	 * @param siglaEstadoDestino the sigla estado destino
	 * @param siglaEstadoOrigem the sigla estado origem
	 * @param isMercadoriaConsumo the is mercadoria consumo
	 * @param isClienteConsumidor the is cliente consumidor
	 * @return the big decimal
	 */
	private static BigDecimal obtemPercentualICMSMercadoria(Mercadoria mercadoria,
			String siglaEstadoDestino, 
			String siglaEstadoOrigem,
			boolean isMercadoriaConsumo,
			boolean isClienteConsumidor){
		
		BigDecimal percentualICM = BigDecimal.ZERO;
		int tipoUtilizacaoICM;
		
		PercentualICMS icms = CalculoPrecoDAO.obtemPercentualICMDaMercadoria(mercadoria,
				siglaEstadoOrigem,
				siglaEstadoDestino);
		
		if (icms != null){
			if (isMercadoriaConsumo) {
				percentualICM = icms.percentualICMSConsumo;
				tipoUtilizacaoICM = icms.tipoUtilizacaoICMS;
			} else {
				if (isClienteConsumidor){
					percentualICM = icms.percentualICMSConsumo;
					tipoUtilizacaoICM = icms.tipoUtilizacaoICMS;

					if ( (tipoUtilizacaoICM == 0) 
						|| ( (tipoUtilizacaoICM == 1) && (siglaEstadoOrigem.equals(siglaEstadoDestino)) 
								|| ((tipoUtilizacaoICM == 2) && (!(siglaEstadoOrigem.equals(siglaEstadoDestino)))) )
					   ) {
						percentualICM = icms.percentualICMSConsumo;
					} else{
						percentualICM = icms.percentualICMSMercadoria;
					}
				} else {
					percentualICM = icms.percentualICMSMercadoria;
				}
			}
		} else {
			percentualICM = BigDecimal.ONE.negate();
		}
	
		return percentualICM;
	}
	
	/**
	 * Verifica se a data de vigora�‹o Ž menor ou igual a data corrente.
	 * @param data
	 * @return
	 */
	private static boolean isDataVigorarValida(String data){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String dataHoje = format.format(cal.getTime());
		
		long dataVerificar = 0;
		long hoje = 0;
		try {
			hoje = format.parse(dataHoje).getTime();
			dataVerificar = format.parse(data).getTime();
		} catch (ParseException e) {
			Log.e("CalculaPreco", e.getMessage());
		}
		
		if(dataVerificar <= hoje){
			return true;
		}
		
		return false;		
	}
		
}