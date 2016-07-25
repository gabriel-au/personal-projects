$(document).ready(function() {

	BRQNavegacao.adicionarPilhaNavegacao('clienteRelacionamento');
	
	//Recuperando dados do Representando para barra de aproveitamento
	setarValoresAproveitamento();
	
	$('#li_titulos').click(function() { 
		nav('../vendas/titulosEmAberto.html');
	});
	
	$('#li_pedidos').click(function() { 
		nav('../relacionamento/clientePedidos.html');
	});
	
	$('#li_cotacao').click(function() { 
		//nav('clienteCotacao.html');
//		exibeMensagem();
	});
	execRelacionamentoCliente(getCliente().codigoCliente);

});

function execRelacionamentoCliente(codigoCliente) {
	BRQMob.log("Lendo dados execListarSituacaoCliente");
	BRQMob.exec("retornoRelacionamentoCliente", "falha", "RelacionamentoDispatcher", "relacionamentoCliente", [ codigoCliente ]);
}

function retornoRelacionamentoCliente(retorno){
	BRQMob.log("Lendo dados retornoRelacionamentoCliente");
	if(retorno != null){
		//Atualizando botão de favoritos
		if(retorno.Detalhe.cliente.indicativoFavorito == "1"){
			$("#favoritos").addClass("highlight");
		}
		$('#limiteCredito').html(retorno.Detalhe.cliente.valorLimiteCredito);
		$('#label_limite_credito').html("Limite de Crédito " + retorno.Detalhe.cliente.tipoLimiteCredito);
		if(retorno.Detalhe.cliente.valorSaldoAberto == ''){
			$('#saldoAberto').html('R$ 0,00');
		}else{
			$('#saldoAberto').html(retorno.Detalhe.cliente.valorSaldoAberto);
		}
		$('#span_total_titulos').html(retorno.Detalhe.totalTitulosAbertos);
		$('#span_total_pedidos').html(retorno.Detalhe.totalPedidos);
    }
}

/**
* Método que preenche os dados do aproveitamento do cliente
*/
function setarValoresAproveitamento(){
	try{
		var dadosRepresentante = getDadosComerciaisRepresentante();
		var html =  "<span>Ideal: " + dadosRepresentante.aproveitamentoIdeal + "%</span><br />";
			html += "<span>Atual: " + dadosRepresentante.aproveitamentoAtual + "%</span>";
		$('#divValores').html(html);
		var divValores = $('#divAproveitamento');
		html = "<div style='width:"+dadosRepresentante.aproveitamentoAtual+"%;' class='green'></div>";
		$('#divAproveitamento').html(html);
	}catch(err) {
		alert('[setarValoresAproveitamento()] - '.concat(err.message));
	}
}

/**
* Método para tratamento de falha no retorno das consultas
* @param result - mensagem de falha
*/
function falha(result){
	BRQMob.log("falha: " + result);
	$("#mensagem").html(result);
	$("#divMensagem").css('display', 'inline');
}