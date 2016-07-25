package br.com.martins.vendas.services.desconto;

import java.math.BigDecimal;


public class PercentualFlexibilizacao {
	
	public PercentualFlexibilizacao(){
		percentualMaximoFlex = BigDecimal.ZERO;
		percentualRateioFlex = BigDecimal.ZERO;
		percentualProximoCaixa = BigDecimal.ZERO;
		percentualRateioBandaInferior = BigDecimal.ZERO;
		
	}
	public BigDecimal percentualMaximoFlex;
	public BigDecimal percentualRateioFlex;
	public BigDecimal percentualProximoCaixa;
	public BigDecimal percentualRateioBandaInferior;
}