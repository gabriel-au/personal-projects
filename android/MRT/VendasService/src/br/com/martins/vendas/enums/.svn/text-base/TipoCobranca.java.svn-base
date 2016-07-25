package br.com.martins.vendas.enums;

public enum TipoCobranca {
	BANCARIA(2), ANTECIPADO(6), CASH(6), CHEQUE(1), DUPLICATA(3), ORDEMPAGAMENTO(4);
    public Integer tipoCobranca;
 
    private TipoCobranca(Integer tipoCobranca) {
    	this.tipoCobranca = tipoCobranca;
    }
    
//    private boolean equals(Integer tipoCobrancaAux) {
//    	return tipoCobranca.compareTo(tipoCobrancaAux) == 0;
//    }
    
    /**
     * Retorna descricao do tipo de financiamento
     * Tabela PCACND Campo TIPCOBCND
     * COB BANCARIA = 2
     * ANTECIPADO = 6
     * CHEQUE = else
     * @param tipoCobrancaAux
     * @return
     */
    public static String toString(Integer tipoCobrancaAux) {
    	return BANCARIA.tipoCobranca.equals(tipoCobrancaAux) ? "COB. BANCARIA" :  
    		ANTECIPADO.tipoCobranca.equals(tipoCobrancaAux) ? "ANTECIPADO" : 
    		"CHEQUE";
    }
    
    public Integer getValue(){
    	return tipoCobranca;
    }
}
