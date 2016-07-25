package br.com.martins.vendas.enums;

public enum GrupamentoCliente {
	CLIENTESEMGRUPAMENTO("0");
    public String codigoGrupamentoCliente;
 
    private GrupamentoCliente(String codigoGrupamentoCliente) {
    	this.codigoGrupamentoCliente = codigoGrupamentoCliente;
    }
}
