package br.com.martins.vendas.enums;

/**
 * CLILIBPED TEXT("L")	 // Cliente liberadao para pedido
	CLIBLQPED TEXT("B")	 // Cliente bloqueado para pedido
	CLIANSPED TEXT("A")	 // Cliente em análise
	CLINORSIT TEXT("N")	 // Cliente com situação normal
	CLISUSSIT TEXT("S")	 // Cliente suspenso
	CLICTSSIT TEXT("C")	 // Cliente cortado
	CLIRCPSIT TEXT("R")	 // Cliente recuperado
	CLICNTGAR TEXT("G")	 // Cliente conta garantida
	CLIVIP TEXT("V")  // Cliente Vip
	CLITRI TEXT("T")  // Cliente Tribanco
	CLIANT TEXT("A")  // Cliente Antecipa
	CLINOM TEXT("N")  // Cliente Nomeado
	CLIHIST TEXT("C")  // Cliente Histórico
	CLIMAX TEXT("R")  // Cliente Máximo
	CLIEXT TEXT("E")  // Cliente Nomeado Externo
 * @author BRQ Mobile Team
 *
 */
public enum TipoCliente {
	LIBERADOPEDIDO("L"), BLOQUEADOPEDIDO("B"), ANALISE("A"), SITUACAONORMAL("N"), SUSPENSO("S")
    , CORTADO("C"), RECUPERADO("R"), CONTAGARANTIDA("G"),
    VIP("V"), TRIBANCO("T"), ANTECIPA("A"), NOMEADO("N"), HISTORICO("C")
    , MAXIMO("R"), NOMEADOEXTERNO("E");
    
    public String idTipoCliente;
 
    private TipoCliente(String idTipoCliente) {
    	this.idTipoCliente = idTipoCliente;
    }
}
