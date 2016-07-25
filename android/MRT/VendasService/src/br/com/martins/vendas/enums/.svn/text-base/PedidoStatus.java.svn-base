package br.com.martins.vendas.enums;

public enum PedidoStatus {

	EM_CRIACAO(0), CRIADO(1), GRAVADO(2), PREPARADO(3), SOMENTE_LEITURA(4);

	public Integer status;

	private PedidoStatus(Integer status) {
		this.status = status;
	}

	public boolean equals(Integer statusAux) {
		return status.compareTo(statusAux) == 0;
	}

	public Integer getValue() {
		return status;
	}

}
