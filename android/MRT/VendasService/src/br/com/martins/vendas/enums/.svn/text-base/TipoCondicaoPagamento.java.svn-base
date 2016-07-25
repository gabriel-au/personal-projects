package br.com.martins.vendas.enums;

public enum TipoCondicaoPagamento {
	BRINDES_PROMOCOES(55), // Condição de Pagamento para Brindes de Promoções
	BRINDES_DESCONTO_FLEXIVEL(2000), // Condição de Pagamento para Brindes sobre a Economia de Desconto Flexível
	BRINDES_BENEFICIO_CUSTOMIZADO(2001), // Condição de Pagamento para Brindes sobre a Economia de Benefício Customizado
	TODAS_ATIVIDADES(9999),
	TODOS_CANAIS(9999),
	TODOS_GRUPAMENTOS(9999),
	TODOS_ESTADOS(99),
	SEM_CODIGO_CONDICAO_PAGAMENTO(0);

	public Integer codigoCondicaoPagamento;

	private TipoCondicaoPagamento(Integer codigoCondicaoPagamento) {
		this.codigoCondicaoPagamento = codigoCondicaoPagamento;
	}

	public boolean equals(Integer codigoCondicaoPagamento) {
		return this.codigoCondicaoPagamento.compareTo(codigoCondicaoPagamento) == 0;
	}

	public Integer getValue() {
		return this.codigoCondicaoPagamento;
	}

}
