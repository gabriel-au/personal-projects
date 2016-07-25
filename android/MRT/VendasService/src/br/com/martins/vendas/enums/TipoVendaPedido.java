package br.com.martins.vendas.enums;

public enum TipoVendaPedido {

	NORMAL(0), SIMPLIFICADA(1), MISTA(2);

	public Integer valor;

	private TipoVendaPedido(Integer valor) {
		this.valor = valor;
	}

	public Integer getValue() {
		return this.valor;
	}

	private boolean equals(Integer valor) {
		return this.valor.compareTo(valor) == 0;
	}

	public static String toString(Integer valor) {
		return MISTA.equals(valor) ? "MISTA" : NORMAL.equals(valor) ? "NORMAL" : SIMPLIFICADA.equals(valor) ? "SIMPLIFICADA" : "";
	}

	public static Integer toInteger(String valor) {
		if ("MISTA".equalsIgnoreCase(valor)) {
			return MISTA.valor;
		} else if ("NORMAL".equalsIgnoreCase(valor)) {
			return NORMAL.valor;
		} else if ("SIMPLIFICADA".equalsIgnoreCase(valor)) {
			return SIMPLIFICADA.valor;
		}
		return null;
	}

}
