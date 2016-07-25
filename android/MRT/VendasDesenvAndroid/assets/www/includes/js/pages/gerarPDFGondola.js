$(document).ready(function(){
	var _DISPATCHER 		 = 'VendasDispatcher';
	var _UNEXPECTED_ERROR 	 = 'Ocorreu um erro inesperado!';
	var _MSG_SEND_MAIL_OK	 = 'Operação realizado com sucesso!';
	var _MSG_NO_MAIL_ADDRESS = 'Necessário informar ao menos um endereço de email!';
	
	
	var arrayMail = new Array();
	$('#span-add').click(function() {
		
		var email = $('#input-mail').val();
		if (!isEmailValido(email)) {
			apresentaMensagemErro('Email Inválido: '+ email, true);
			return;
		}
		$('.panel.paddedGrey').remove();
		
		arrayMail.push(email);
		$('#input-mail').val('');
	});
	
	
	$('#span-send').click(function() {
		//
		if ($('#input-mail').val().replace(/^\s+|\s+$/g, '').length > 0) {
			$('#span-add').click();
		}
		//
		if (arrayMail.length == 0) {
			apresentaMensagemErro(_MSG_NO_MAIL_ADDRESS, true);
			return;
		}
		//
		
		var clienteId = '1912158';
		
		var estAnt = $('#switch_estoque_anterior').hasClass("on") 			? 0 : 1;
		var estAtu = $('#switch_estoque_atual').hasClass("on") 				? 0 : 1;
		var valFre = $('#switch_valor_frete_martins').hasClass("on") 		? 0 : 1;
		var proLiq = $('#switch_precoliq_impostos_frete').hasClass("on") 	? 0 : 1;
		var filExp = $('#switch_filial_expedicao').hasClass("on") 			? 0 : 1;
		var filFat = $('#switch_filial_faturamento').hasClass("on") 		? 0 : 1;
		var peICMS = $('#switch_percentual_icms').hasClass("on") 			? 0 : 1;
		
		BRQMob.exec("SucessoEnviarEmail", "falhaEnviarEmail", _DISPATCHER, "enviarEmail", [clienteId, arrayMail, estAnt, estAtu, valFre, proLiq, filExp, filFat, peICMS]);
	});
	
	falhaEnviarEmail = function (object) {apresentaMensagemErro(_UNEXPECTED_ERROR, true);}
	SucessoEnviarEmail = function (object) {arrayMail = new Array(); apresentaMensagemSucesso(_MSG_SEND_MAIL_OK, true);}
	
});