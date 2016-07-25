package br.com.martins.vendas.enums;

public enum TipoNegociacao {
    VENDA(0), PROMOCAO(1), FLEX_POUPE_CERTO(2), BENEFICIO(3);
    
    public Integer tipoNegociacao;
 
    private TipoNegociacao(Integer tipoCobranca) {
    	this.tipoNegociacao = tipoCobranca;
    }
    @SuppressWarnings("unused")
	private boolean equals(Integer tipoCobrancaAux) {
    	return tipoNegociacao.compareTo(tipoCobrancaAux) == 0;
    }
    
	public Integer getValue() {	
		return tipoNegociacao;
	}
    
}
