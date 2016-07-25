$(document).ready(function() {
	BRQNavegacao.adicionarPilhaNavegacao('fecharPedidoMensagem');
	
	var mensagens = sessionStorage.getObject('mensagensPedido');
	loadListaPedidos(mensagens);
});

function loadListaPedidos(mensagens) {
	$("#mensagens").empty();

	var msg_ul = $('<ul/>', {"class" : "list selectable"});

	$.each(mensagens, function(i, obj) {
		var msg_li = $('<li/>', {onclick : "listSelect(this);"}).appendTo(msg_ul);
		var msg_span = $('<span/>', {"class" : "icon aviso"}).appendTo(msg_li);
		var msg_div = $('<div/>', {"class" : "left"}).appendTo(msg_li);
		var msg_label = $('<label/>').css({"margin-left" : "10px"});
		$(msg_label).text(obj.codigo + " - " + obj.descricao).appendTo(msg_div);
	});

	$("#mensagens").append(msg_ul);
	
	var detalhePedido = sessionStorage.getObject('detalhePedido');
	$("#valorCpgto").html(getCondicaoPagamento().valorTotalLiquido);
	$("#valorMinimo").html(getCondicaoPagamento().valorLimiteMinimo);
	$("#codigo").html(getCondicaoPagamento().codigoCondicaoPagamento);
	$("#descricaoCondicao").html(getCondicaoPagamento().descricaoCondicao);
	$("#filialExpedicao").html(getCondicaoPagamento().codigoFilialExpedicao);
	
//	BRQMob.log("*******************************************************");
//	BRQMob.log(JSON.stringify(getCondicaoPagamento()));
//	BRQMob.log(JSON.stringify(getPedidoBase()));
	
}