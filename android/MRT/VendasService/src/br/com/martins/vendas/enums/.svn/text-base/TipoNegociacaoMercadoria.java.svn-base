package br.com.martins.vendas.enums;

public enum TipoNegociacaoMercadoria {
	VENDA(0, "Venda"), PROMOCAO(1, "Promoção"), POUPECERTO(2, "Poupe Certo"), BENEFICIO(3, "Benefício");

	public Integer	idTipoNegociacaoMercadoria;
	
	public String	descTipoNegociacaoMercadoria;

	private TipoNegociacaoMercadoria(Integer idTipoNegociacaoMercadoria) {
		this.idTipoNegociacaoMercadoria = idTipoNegociacaoMercadoria;
	}
	
	private TipoNegociacaoMercadoria(Integer idTipoNegociacaoMercadoria, String descTipoNegociacaoMercadoria) {
		this.idTipoNegociacaoMercadoria = idTipoNegociacaoMercadoria;
		this.descTipoNegociacaoMercadoria = descTipoNegociacaoMercadoria;
	}

	public boolean equals(Integer idTipoNegociacaoMercadoriaAux) {
		return idTipoNegociacaoMercadoria.compareTo(idTipoNegociacaoMercadoriaAux) == 0;
	}
}
