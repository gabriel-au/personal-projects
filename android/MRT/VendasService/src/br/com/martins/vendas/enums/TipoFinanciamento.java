package br.com.martins.vendas.enums;

public enum TipoFinanciamento {
	PROPRIO(1), VENDOR(2), TRIBANCO(3);
    public Integer tipoFinanciamento;
 
    private TipoFinanciamento(Integer tipoFinanciamento) {
    	this.tipoFinanciamento = tipoFinanciamento;
    }
    private boolean equals(Integer tipoFinanciamentoAux) {
    	return tipoFinanciamento.compareTo(tipoFinanciamentoAux) == 0;
    }
    /**
     * Retorna descricao do tipo de financiamento
     * Tabela PCACND Campo = TIPFNMCND
     * VENDOR = 2
     * PROPRIO = 1
     * @param tipoFinanciamentoAux
     * @return
     */
    public static String toString(Integer tipoFinanciamentoAux) {
    	return VENDOR.equals(tipoFinanciamentoAux) ? "VENDOR" :  "PROPRIO";
    }
    
    public Integer getValue(){
    	return tipoFinanciamento;
    }
}
