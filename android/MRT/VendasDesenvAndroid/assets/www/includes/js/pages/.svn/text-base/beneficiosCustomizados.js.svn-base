var alteracaoBeneficioPermitida = false;
var alteracaoBeneficioPrazoPermitida = false;
var alteracaoBeneficioDescontoPermitida = false;
const SOMENTE_DESCONTO = 1;
const SOMENTE_PRAZO = 2;
const DESCONTO_E_PRAZO = 3;
const DESCONTO_OU_PRAZO = 4;
const ORIGEM_PED_FECHAMENTO = 2;
const ORIGEM_PED_CABECALHO = 1;
var isFaixaLiberada = false;
var prazoOriginal;
var descontoOriginal;
var beneficioAtualObj;
var dadosComerciaisRepresentante;

const TIPO_BENEFICIO_PRAZO = 'P';
const TIPO_BENEFICIO_DESCONTO = 'D';
const TIPO_BENEFICIO_PRAZO_DESCONTO = 'PD';

//variavel abaixo deve conter a origem do beneficio customizado
//quando ORIGEM_PED_FECHAMENTO, o beneficio sera aplicado 
var origem = ORIGEM_PED_FECHAMENTO;

$(document).ready(		
	function(){
		BRQNavegacao.adicionarPilhaNavegacao('beneficiosCustomizadosDetalhe');
		carregaBeneficioCustomizadoDetalhe();
		configuraEventoAplicaBeneficio();
	}
);

function aplicarBeneficio(){
	var codigoBeneficio = sessionStorage.getObject('codigoBeneficioCustomizado');
	var numeroPedido = sessionStorage.getObject('numeroPedido');
	var pedidoBase = getPedidoBase();
	var prazoBeneficio = totalizaPrazoBeneficio();
	var descontoBeneficio = totalizaDescontoBeneficio();
	var codigoFilialExpedicao = pedidoBase.filialExpedicao.codigoFilial;
	var codigoFilialFaturamento = pedidoBase.filialFaturamento.codigoFilial;
	BRQMob.exec("sucessoAplicaBeneficio", "falha", "VendasDispatcher", "aplicarBeneficioCustomizado", [origem, prazoBeneficio, descontoBeneficio, codigoFilialExpedicao, codigoFilialFaturamento, pedidoBase.cliente.codigoCliente, pedidoBase.cliente.codigoTerritorio, false]);
}

function totalizaPrazoBeneficio(){
	var prazo = 0;
	var beneficiosCustomizados = getPedidoBase().beneficiosCustomizados;
	if(beneficiosCustomizados){
		for(i = 0; i < beneficiosCustomizados.length; i++){
			prazo = eval(prazo) + eval(beneficiosCustomizados[i].prazoBeneficio);
		}
	}
	return prazo;
}

function totalizaDescontoBeneficio(){
	var descontoBeneficio = 0;
	var beneficiosCustomizados = getPedidoBase().beneficiosCustomizados;
	if (beneficiosCustomizados) {
		for (i = 0; i < beneficiosCustomizados.length; i++) {
			descontoBeneficio = eval(descontoBeneficio) + eval(beneficiosCustomizados[i].descontoBeneficio.replace(',', '.'));
		}
	}
	return descontoBeneficio;
}
		
function eventoAplicaBeneficio(){
	var codigoBeneficioCustomizado = sessionStorage.getObject('codigoBeneficioCustomizado');
	var prazoBeneficio = 0;
	var descontoBeneficio = "0,0";
	var tipoBeneficioPrazo = $('#tipoBeneficioPrazo').attr('checked');
	var tipoBeneficioDesconto = $('#tipoBeneficioDesconto').attr('checked');
	if($("#aplicaoBeneficio").hasClass("on")) {
		if(tipoBeneficioPrazo){
			prazoBeneficio = $('#prazoBeneficio').val();	
		}
		if(tipoBeneficioDesconto){
			descontoBeneficio = $('#descontoBeneficio').val();	
		}
		beneficioCustomizado = new Object();
		beneficioCustomizado.codigoBeneficio = codigoBeneficioCustomizado;
		beneficioCustomizado.prazoBeneficio = prazoBeneficio;
		beneficioCustomizado.descontoBeneficio = descontoBeneficio;
		beneficioCustomizado.tipoBeneficio = getTipoBeneficio();
		addBeneficioCustomizado(beneficioCustomizado);
	} else {
		removerBeneficioCustomizado(codigoBeneficioCustomizado);
	}
}

function getTipoBeneficio(){
	tipoBeneficioPrazo = $('#tipoBeneficioPrazo').attr('checked');
	tipoBeneficioDesconto = $('#tipoBeneficioDesconto').attr('checked');
	if(tipoBeneficioPrazo && tipoBeneficioDesconto){
		return TIPO_BENEFICIO_PRAZO_DESCONTO;
	}else if(tipoBeneficioPrazo){
		return TIPO_BENEFICIO_PRAZO;
	}else{
		return TIPO_BENEFICIO_DESCONTO;
	}
}

function configuraEventoAplicaBeneficio(){
	$("#aplicaoBeneficio").mousedown(function() {
		eventoAplicaBeneficio();
	});
}

function carregaBeneficioCustomizadoDetalhe(){
	var codigoBeneficio = sessionStorage.getObject('codigoBeneficioCustomizado');
	var numeroPedido = sessionStorage.getObject('numeroPedido');
	
	if(existeBeneficioCustomizado(codigoBeneficio)){
		switchON($("#aplicaoBeneficio"));
	}
	
	BRQMob.log("entrando na funcao carregar condicoes de pagamento");
	var pedidoBase = getPedidoBase();
	BRQMob.exec("sucesso", "falha", "VendasDispatcher", "beneficiosCustomizadosDetalhe", [numeroPedido, codigoBeneficio, pedidoBase.cliente.codigoCliente, pedidoBase.cliente.codigoTerritorio, pedidoBase.cliente.scoreCliente]);		
}

function sucesso(retorno){
	montaValoresCabecalho(retorno.beneficioAtual);
	montaListaBeneficiosCustomizados(retorno.beneficiosCustomizados);
	controlaSelecaoBeneficioPrazoDesconto(retorno.beneficioAtual);
	mantemValoresSelecionados();
}

function mantemValoresSelecionados(){
	var codigoBeneficio = sessionStorage.getObject('codigoBeneficioCustomizado');
	if(existeBeneficioCustomizado(codigoBeneficio)){
		beneficioCustomizado = getBeneficioCustomizado(codigoBeneficio);
		tipoBeneficio = beneficioCustomizado.tipoBeneficio;
		if(tipoBeneficio == TIPO_BENEFICIO_PRAZO){
			$('#tipoBeneficioPrazo').attr('checked', true);
			$('#tipoBeneficioDesconto').attr('checked', false);
			$('#prazoBeneficio').val(beneficioCustomizado.prazoBeneficio);
		}else if(tipoBeneficio == TIPO_BENEFICIO_DESCONTO){
			$('#tipoBeneficioPrazo').attr('checked', false);
			$('#tipoBeneficioDesconto').attr('checked', true);
			$('#descontoBeneficio').val(beneficioCustomizado.descontoBeneficio);	
		}else{
			$('#prazoBeneficio').val(beneficioCustomizado.prazoBeneficio);
			$('#descontoBeneficio').val(beneficioCustomizado.descontoBeneficio);	
		}
	}
}

function montaValoresCabecalho(beneficioAtual){
	$("#descricaoBeneficioCustomizado").text(beneficioAtual.descricaoBeneficio);
	$("#codigoCliente").text("codigo cliente da tela de pedido");
	$("#razaoSocialCliente").text("razao social do cliente da tela de pedido");
	$("#descricaoBeneficio").text(beneficioAtual.descricaoBeneficio);
	$("#valorLiquidoPedido").text(beneficioAtual.valorReferenciaTotalPedido);
	$("#quantidadeItensPedido").text(beneficioAtual.quantidadeItensPedido);
	$("#quantidadePontosPedido").text(beneficioAtual.quantidadeTotalPontosPedido);
	$("#quantidadePontosPorPedido").text(beneficioAtual.razaoCompraPedidoCalculado);
	
	//mantem a opcao selecionada
	if(beneficioAtual.isAplicaBeneficio){
		switchON($("#aplicaoBeneficio"));
	}
}

function controlaSelecaoBeneficioPrazoDesconto(beneficioAtual){
	beneficioAtualObj = beneficioAtual;
	//TODO::so pode alterar o prazo e desconto quando for fechamento do pedido
	//esta tela pode ser acessada pelo caminhao(nao pode ter o beneficio alterado)
	//ou no fechamento do pedido(permitida a alteracao)
	//remover o false pela condicao acima
	if(false || !isFaixaLiberada){
		$("#aplicaoBeneficio").unbind('mousedown');
		alteracaoBeneficioPermitida = false;
		$('#tipoBeneficioDesconto').attr('disabled', true);
		$('#tipoBeneficioPrazo').attr('disabled', true);
		$('#prazoBeneficio').attr('readonly', true);
		$('#descontoBeneficio').attr('readonly', true);
		$('#tipoBeneficioDesconto').hide();
		$('#tipoBeneficioPrazo').hide();
		return;
	}
	alteracaoBeneficioPermitida = true;
	dadosComerciaisRepresentante = getDadosComerciaisRepresentante()
	if ((beneficioAtual.isPrazoFixo != 1) && (dadosComerciaisRepresentante.pecentualTaxaCDI > 0) && (dadosComerciaisRepresentante.pecentualTaxaDPZ > 0)){
		//controle desconto
		if (beneficioAtual.tipoBeneficio != SOMENTE_DESCONTO){
			alteracaoBeneficioPrazoPermitida = true;
		}else{
			alteracaoBeneficioPrazoPermitida = false;
			$('#tipoBeneficioPrazo').attr('disabled', true);
		}
		//controle prazo
		if (beneficioAtual.tipoBeneficio != SOMENTE_PRAZO){
			alteracaoBeneficioDescontoPermitida = true;
		}else{
			alteracaoBeneficioDescontoPermitida = false;
			$('#tipoBeneficioDesconto').attr('disabled', true);
		}
		
	}else if(dadosComerciaisRepresentante.pecentualTaxaCDI <= 0 || dadosComerciaisRepresentante.pecentualTaxaDPZ <= 0){
		alert('Os Valores das Taxas de Conversão impedem que os benefícios possam ser alterados!');
	}
	setaColunaDefault(beneficioAtual);
}

function setaColunaDefault(beneficioAtual){
	if(beneficioAtual.tipoBeneficio == DESCONTO_OU_PRAZO){
		$('#tipoBeneficioPrazo').attr('checked', true);
		$('#tipoBeneficioDesconto').attr('checked', false);
		$('#prazoBeneficio').attr('readonly', false);
		$('#descontoBeneficio').attr('readonly', true);
	}else if(beneficioAtual.tipoBeneficio == SOMENTE_DESCONTO){
		$('#tipoBeneficioPrazo').attr('checked', false);
		$('#tipoBeneficioDesconto').attr('checked', true);
		$('#prazoBeneficio').attr('readonly', true);
		$('#descontoBeneficio').attr('readonly', false);
	}else if(beneficioAtual.tipoBeneficio == SOMENTE_PRAZO){
		$('#tipoBeneficioPrazo').attr('checked', true);
		$('#tipoBeneficioDesconto').attr('checked', false);
		$('#prazoBeneficio').attr('readonly', false);
		$('#descontoBeneficio').attr('readonly', true);
	}else if(beneficioAtual.tipoBeneficio == DESCONTO_E_PRAZO){
		$('#tipoBeneficioPrazo').attr('checked', true);
		$('#tipoBeneficioDesconto').attr('checked', true);				
	}
}

function atualizaPrazoDesconto(tipo, valor){
	if ($("#aplicaoBeneficio").hasClass("on")) {
		codigoBeneficio = sessionStorage.getObject('codigoBeneficioCustomizado');
		beneficioCustomizado = getBeneficioCustomizado(codigoBeneficio);
		if(tipo == 'D'){
			beneficioCustomizado.descontoBeneficio = valor;
		}else{
			beneficioCustomizado.prazoBeneficio = valor;
		}
		atualizaBeneficioCustomizado(beneficioCustomizado);
	}
}

function montaListaBeneficiosCustomizados(beneficiosCustomizados){
	var tbody_beneficios_customizados = $("#body_beneficios_customizados");
	$.each(beneficiosCustomizados, function(i, obj) {
		if(obj.isFaixaLiberada){
			isFaixaLiberada = true;
		}
		linha = $('<tr/>', {"class" : obj.isFaixaLiberada ? 'highlight' : ( i % 2 == 0 ? "off" : " ")});
		$('<td/>', {"style" : "text-align:center;", text : obj.numeroSequencialFaixaBFX}).appendTo(linha);
		$('<td/>', {"style" : "text-align:right;", text : obj.valorMinimoCompra}).appendTo(linha);
		$('<td/>', {"style" : "text-align:center;", text : obj.indicaFaixaObrigatoria == 1 ? 'E' : 'OU'}).appendTo(linha);
		$('<td/>', {"style" : "text-align:center;", text : obj.quantidadeMinimaCompra}).appendTo(linha);
		$('<td/>', {"style" : "text-align:center;", text : obj.quantidadeMinimaPontos}).appendTo(linha);
		$('<td/>', {"style" : "text-align:center;", text : obj.percentualMinimoPontos}).appendTo(linha);
		
		prazoOriginal = obj.prazo;
		descontoOriginal = obj.descontoBeneficio;

		var td_prazo;
		var td_desconto;
		if(obj.isFaixaLiberada){
			td_prazo = $('<td/>', {"style" : "text-align:right;"});
			$('<input/>', {type: "text",  id : "prazoBeneficio", "class" : "type", "style" : "text-align:right; width:75px", value: obj.prazo, "onchange" : "javascript:validaInformacoesBeneficio('prazo'); atualizaPrazoDesconto('P', this.value)"}).appendTo(td_prazo);
			
			td_desconto = $('<td/>', {"style" : "text-align:right;"});
			$('<input/>', {type: "text", id : "descontoBeneficio","class" : "type", "style" : "text-align:right; width:75px", value: obj.descontoBeneficio, "onchange" : "javascript:validaInformacoesBeneficio('desconto'); atualizaPrazoDesconto('D',this.value)"}).appendTo(td_desconto);
		}else{
			td_prazo = $('<td/>', {"style" : "text-align:right;"});
			$('<input/>', {type: "text", "readonly" : "readonly", "class" : "type", "style" : "text-align:right; width:75px", value: obj.prazo, "onchange" : "javascript:validaInformacoesBeneficio('prazo')"}).appendTo(td_prazo);
			
			td_desconto = $('<td/>', {"style" : "text-align:right;"});
			$('<input/>', {type: "text", "class" : "type", "readonly" : "readonly", "style" : "text-align:right; width:75px", value: obj.descontoBeneficio, "onchange" : "javascript:validaInformacoesBeneficio('desconto')"}).appendTo(td_desconto);
		}
		
		td_prazo.appendTo(linha);
		td_desconto.appendTo(linha);
		
		$('<td/>', {"style" : "text-align:right;", text : obj.percentualComissao}).appendTo(linha);
		$(linha).appendTo(tbody_beneficios_customizados);
	});
	
	//Montagem de Campos texto
	$(".type").each(
		function() {
			createTypeInput(this);
		}
	);
}

function validaInformacoesBeneficio(colunaAlterada){
	var prazoBeneficio = $('#prazoBeneficio').val();
	var descontoBeneficio = $('#descontoBeneficio').val();
	descontoBeneficio = descontoBeneficio.replace('.',',');
	$('#descontoBeneficio').val(descontoBeneficio);
	
	var dDscAtu = descontoBeneficio;
	var dPerDscBfc = descontoOriginal;
	var dDesc;
	var iPrazo;
	var iQdeDiaPrz = prazoOriginal;
	var iPrzAtu = prazoBeneficio;
	if(beneficioAtualObj.tipoBeneficio == SOMENTE_DESCONTO){
		if (descontoBeneficio > descontoOriginal){
			$('#descontoBeneficio').val(descontoOriginal);
		}
	}else if(beneficioAtualObj.tipoBeneficio == SOMENTE_PRAZO){
		if (prazoBeneficio > prazoOriginal){
			$('#prazoBeneficio').val(prazoOriginal);
		}
	}else if(beneficioAtualObj.tipoBeneficio == DESCONTO_E_PRAZO){
		
		if (false){
			if((beneficioAtualObj.isDescontoFixo) || (dadosComerciaisRepresentante.pecentualTaxaCDI <= 0) || (dadosComerciaisRepresentante.pecentualTaxaDPZ <= 0)){
				$('#prazoBeneficio').val(prazoOriginal);
			}else{
				dDesc = convertePrazoDesconto(dadosComerciaisRepresentante.pecentualTaxaCDI, iQdeDiaPrz);
				if (parseDecimalStringToFloat(dDscAtu) > parseDecimalStringToFloat(dPerDscBfc)){
					iPrazo = converteDescontoPrazo(dadosComerciaisRepresentante.pecentualTaxaCDI, (parseDecimalStringToFloat(dPerDscBfc) + eval(dDesc)) - parseDecimalStringToFloat(dDscAtu));
				}else{
					iPrazo = converteDescontoPrazo(dadosComerciaisRepresentante.pecentualTaxaDPZ, parseDecimalStringToFloat(dPerDscBfc) - parseDecimalStringToFloat(dDscAtu));
					iPrazo = iQdeDiaPrz + iPrazo;
				}
				
				if (iPrzAtu > iPrazo){
					iPrzAtu = iPrazo;
					$('#prazoBeneficio').val(iPrzAtu);
				}
			}
			if((beneficioAtualObj.isPrazoFixo) || (dadosComerciaisRepresentante.pecentualTaxaCDI <= 0) || (dadosComerciaisRepresentante.pecentualTaxaDPZ <= 0)){
				dDscAtu = dPerDscBfc;
				dDscAtu = formatarDecimal(dDscAtu, 2)
				$('#descontoBeneficio').val(dDscAtu);
			}else{
				iPrazo = converteDescontoPrazo(dadosComerciaisRepresentante.pecentualTaxaDPZ, parseDecimalStringToFloat(dPerDscBfc));
				if (iPrzAtu > iQdeDiaPrz){
					dDesc = convertePrazoDesconto(dadosComerciaisRepresentante.pecentualTaxaDPZ, (iQdeDiaPrz + iPrazo) - iPrzAtu);
				}else{
					dDesc = convertePrazoDesconto(dadosComerciaisRepresentante.pecentualTaxaCDI, iQdeDiaPrz - iPrzAtu);
					dDesc = parseDecimalStringToFloat(dPerDscBfc) + eval(dDesc);
				}
				if (parseDecimalStringToFloat(dDscAtu) > eval(dDesc)){
					dDscAtu = eval(dDesc);
					dDscAtu = formatarDecimal(dDscAtu, 2)
					$('#descontoBeneficio').val(dDscAtu);
				}
			}
		}else{
			if ((beneficioAtualObj.isPrazoFixo) || (dadosComerciaisRepresentante.pecentualTaxaCDI <= 0) || (dadosComerciaisRepresentante.pecentualTaxaDPZ <= 0)){
				dDscAtu = dPerDscBfc;
				dDscAtu = formatarDecimal(dDscAtu, 2)
				$('#descontoBeneficio').val(dDscAtu);
			}else{
				iPrazo = converteDescontoPrazo(dadosComerciaisRepresentante.pecentualTaxaDPZ, parseDecimalStringToFloat(dPerDscBfc));
				if (iPrzAtu > iQdeDiaPrz){
					dDesc = convertePrazoDesconto(dadosComerciaisRepresentante.pecentualTaxaDPZ, (iQdeDiaPrz + iPrazo) - iPrzAtu);
				}else{
					dDesc = convertePrazoDesconto(dadosComerciaisRepresentante.pecentualTaxaCDI, iQdeDiaPrz - iPrzAtu);
					dDesc = parseDecimalStringToFloat(dPerDscBfc) + eval(dDesc);
				}
				if (parseDecimalStringToFloat(dDscAtu) > eval(dDesc)){
					dDscAtu = eval(dDesc);
					dDscAtu = formatarDecimal(dDscAtu, 2)
					$('#descontoBeneficio').val(dDscAtu);
				}
			}
			if ((beneficioAtualObj.isDescontoFixo) || (dadosComerciaisRepresentante.pecentualTaxaCDI <= 0) || (dadosComerciaisRepresentante.pecentualTaxaDPZ <= 0)){
				iPrzAtu = iQdeDiaPrz;
				$('#prazoBeneficio').val(iPrzAtu);
			}else{
				dDesc = convertePrazoDesconto(dadosComerciaisRepresentante.pecentualTaxaCDI, iQdeDiaPrz);
				if (parseDecimalStringToFloat(dDscAtu) > parseDecimalStringToFloat(dPerDscBfc)){
					iPrazo = converteDescontoPrazo(dadosComerciaisRepresentante.pecentualTaxaCDI, (dPerDscBfc + eval(dDesc)) - parseDecimalStringToFloat(dDscAtu));
				}else{
					iPrazo = converteDescontoPrazo(dadosComerciaisRepresentante.pecentualTaxaDPZ, parseDecimalStringToFloat(dPerDscBfc) - parseDecimalStringToFloat(dDscAtu));
					iPrazo = iQdeDiaPrz + iPrazo;
				}
				if (iPrzAtu > iPrazo){
					iPrzAtu = iPrazo;
					$('#prazoBeneficio').val(iPrzAtu);
				}
			}
		}
	}else if(beneficioAtualObj.tipoBeneficio == DESCONTO_OU_PRAZO){
		// Se um for informado o outro é zerado
		// Benefício padrão concedido será o prazo
		if ((iQdeDiaPrz != iPrzAtu) && (iPrzAtu != 0)){
			if (iPrzAtu > iQdeDiaPrz){
				iPrzAtu = iQdeDiaPrz;
				$('#prazoBeneficio').val(iPrzAtu);
			}
		}
		
		
		// Prazo é o padrão
		if ((parseDecimalStringToFloat(dPerDscBfc) != parseDecimalStringToFloat(dDscAtu)) && (parseDecimalStringToFloat(dDscAtu) != 0)){
			if (parseDecimalStringToFloat(dDscAtu) > parseDecimalStringToFloat(dPerDscBfc)){
				dDscAtu = dPerDscBfc;
				dDscAtu = formatarDecimal(dDscAtu, 2)
				$('#descontoBeneficio').val(dDscAtu);
			}
		}
	}
	if (((iQdeDiaPrz != prazoBeneficio) || (dPerDscBfc != descontoBeneficio)) && !$("#aplicaoBeneficio").hasClass("on")){
		switchON($("#aplicaoBeneficio"));
		eventoAplicaBeneficio();
	}
}

function convertePrazoDesconto(pecentualTaxaCDI, prazoOriginal){
	if (pecentualTaxaCDI > 0){
		dDscDia = precisaoDecimal(pecentualTaxaCDI / 30);
		return precisaoDecimal(prazoOriginal * dDscDia);
	}
}

function converteDescontoPrazo(pecentualTaxaCDI, descontoAtualizado){
	if (pecentualTaxaCDI > 0){
		dDscDia = precisaoDecimal(pecentualTaxaCDI / 30);
		return parseInt(precisaoDecimal(descontoAtualizado / dDscDia));
	}
}


function falha(retorno){
	alert('Falha');
	BRQMob.log("Falhou entrando na funcao carregar beneficios customizados detalhe");
}			

function controlaRadio(tipoBeneficio){	
	if(alteracaoBeneficioPermitida && beneficioAtualObj.tipoBeneficio == DESCONTO_OU_PRAZO){
		if(tipoBeneficio == 'P' && alteracaoBeneficioPrazoPermitida){
			$('#tipoBeneficioDesconto').attr('checked', false);
			$('#prazoBeneficio').attr('readonly', false);
			$('#descontoBeneficio').attr('readonly', true);
			atualizaTipoBeneficio(TIPO_BENEFICIO_PRAZO);
		}else if(tipoBeneficio == 'D' && alteracaoBeneficioDescontoPermitida){
			$('#tipoBeneficioPrazo').attr('checked', false);
			$('#descontoBeneficio').attr('readonly', false);
			$('#prazoBeneficio').attr('readonly', true);
			atualizaTipoBeneficio(TIPO_BENEFICIO_DESCONTO);
		}
	}
}

function atualizaTipoBeneficio(valor){
	if ($("#aplicaoBeneficio").hasClass("on")) {
		codigoBeneficio = sessionStorage.getObject('codigoBeneficioCustomizado');
		beneficioCustomizado = getBeneficioCustomizado(codigoBeneficio);
		beneficioCustomizado.tipoBeneficio = valor;
		atualizaBeneficioCustomizado(beneficioCustomizado);
	}
}

function sucessoAplicaBeneficio(retorno){
	if(retorno.confirmacaoMensagensAplicaBeneficio){
		criaModalConfirmacaoValorMinimo(retorno.confirmacaoMensagensAplicaBeneficio);
		controleMensagemModal();
	}else{
		BRQNavegacao.voltar();
	}
}

function confirmarReajustePreco(){
	var codigoBeneficio = sessionStorage.getObject('codigoBeneficioCustomizado');
	var numeroPedido = sessionStorage.getObject('numeroPedido');
	var pedidoBase = getPedidoBase();
	var prazoBeneficio = totalizaPrazoBeneficio();
	var descontoBeneficio = totalizaDescontoBeneficio();
	var codigoFilialExpedicao = pedidoBase.filialExpedicao.codigoFilial;
	var codigoFilialFaturamento = pedidoBase.filialFaturamento.codigoFilial;
	BRQMob.exec("sucessoAplicaBeneficio", "falha", "VendasDispatcher", "aplicarBeneficioCustomizado", [origem, prazoBeneficio, descontoBeneficio, codigoFilialExpedicao, codigoFilialFaturamento, pedidoBase.cliente.codigoCliente, pedidoBase.cliente.codigoTerritorio, true]);
}

function controleMensagemModal(){
	if($(".modalMensagem").css("display") !== "none"){
		$("#divModal").hide("fast");
		$("#divTotal").hide("fast");
	}else{
		$("#divTotal").show("fast");
		$("#divModal").show("fast");
	}	
}

function criaModalConfirmacaoValorMinimo(confirmacaoMensagensAplicaBeneficio) { 
	var tbody_confirmacao_valor_minimo = $("#body_confirmacao_valor_minimo");
	tbody_confirmacao_valor_minimo.empty();
	var linha;
	
	$.each(confirmacaoMensagensAplicaBeneficio, function(i,obj){
		linha = $('<tr/>', {"class" : ( i % 2 == 0 ? "" : "off ")});	
		$('<td/>', {style: "text-align:center;", text: obj.precoBC }).appendTo(linha);
		$('<td/>', {style: "text-align:center;", text: obj.percentualVariacaoPreco }).appendTo(linha);
		$('<td/>', {style: "text-align:center;", text: obj.codigoMercadoria }).appendTo(linha);
		$('<td/>', {style: "text-align:center;", text: obj.descricaoMercadoria }).appendTo(linha);
		$('<td/>', {style: "text-align:center;", text: obj.filialExpedicao }).appendTo(linha);
		$('<td/>', {style: "text-align:center;", text: obj.codigoCondicaoPagamento }).appendTo(linha);
		$(linha).appendTo(tbody_confirmacao_valor_minimo);
	});
}