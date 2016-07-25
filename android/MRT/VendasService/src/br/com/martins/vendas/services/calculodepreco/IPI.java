package br.com.martins.vendas.services.calculodepreco;

import java.math.BigDecimal;

public class IPI {
	
	public IPI (){
		valorTotalIPI = BigDecimal.ZERO;
		valorUnitarioIPI = BigDecimal.ZERO;
	}

	public BigDecimal valorUnitarioIPI;
	public BigDecimal valorTotalIPI;
}
