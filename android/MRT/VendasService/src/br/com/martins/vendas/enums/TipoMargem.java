package br.com.martins.vendas.enums;

public enum TipoMargem {
	
	NEUTRA(0), PREJUIZO(1), BAIXA_RENTABILIDADE(2), ALTA_RENTABILIDADE(3) ;
	
	
	private int tipoMargem;

	private TipoMargem (int tipoMargem){
		this.tipoMargem = tipoMargem;
	}

	public int getValue(){
		return tipoMargem;
	}
	
}
