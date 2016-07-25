$(document).ready(function(){

	BRQNavegacao.adicionarPilhaNavegacao('gerarPDF_pedido');
	
	var _DISPATCHER 		 = 'VendasDispatcher';
	var _UNEXPECTED_ERROR 	 = 'Ocorreu um erro inesperado!';
	var _MSG_SEND_MAIL_OK	 = 'Operação realizado com sucesso!';
	var _MSG_NO_MAIL_ADDRESS = 'Necessário informar ao menos um endereço de email!';
	
	
	var arrayMail = new Array();
	$('#span-add').click(function() {
		
//		var email = $('#input-mail').val();
//		if (!isEmailValido(email)) {
//			apresentaMensagemErro('Email Inválido: '+ email, true);
//			return;
//		}
		$('.panel.paddedGrey').remove();
		
		arrayMail.push(email);
		$('#input-mail').val('');
	});
	
	$('#span-send').click(function() {
		
		var valorFrete = $('#switch_valor_frete').hasClass("on") 					? 0 : 1;
		var totalImpostos = $('#switch_totalLiquido_impostos_frete').hasClass("on") ? 0 : 1;
		var filialExp = $('#switch_filial_Expedicao').hasClass("on") 				? 0 : 1;
		var filialFaturamento = $('#switch_filial_faturamento').hasClass("on") 		? 0 : 1;
		var percentualIcms = $('#switch_percentual_icms').hasClass("on") 			? 0 : 1;

		//recuperando detalhes do pedido 
		var pedidoEmail = getPedidoEmail();
		//recuperando Email do representante
		var representanteEmail = getRepresentante();

		BRQMob.exec("SucessoEnviarEmail", "falhaEnviarEmail", _DISPATCHER, "enviarEmailPedido", [pedidoEmail.nomeCliente,pedidoEmail.codigoCliente,representanteEmail.email, valorFrete, totalImpostos, filialExp, filialFaturamento, percentualIcms,pedidoEmail.numeroPedido,pedidoEmail.preparado,pedidoEmail.dataPedido,pedidoEmail.horaPedido,pedidoEmail.dataResultado,pedidoEmail.quantidadeItens,pedidoEmail.valorTotalPedido]);
	});
	
	falhaEnviarEmail = function (object) {apresentaMensagemErro(_UNEXPECTED_ERROR, true);}
	SucessoEnviarEmail = function (object) {arrayMail = new Array(); apresentaMensagemSucesso(_MSG_SEND_MAIL_OK, true);}
	
});

function voltar(){		
	if(getPesquisaByCliente()=='true'){
		BRQNavegacao.voltar();	
	}else{
		sessionStorage.removeItem("cliente");
		BRQNavegacao.voltar();	
	}
	
}
