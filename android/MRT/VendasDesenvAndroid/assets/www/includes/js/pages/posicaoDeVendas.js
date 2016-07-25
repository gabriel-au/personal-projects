$(document).ready(function() {
	BRQNavegacao.adicionarPilhaNavegacao('posicaoVendas');
	
	var periodo = sessionStorage.getObject('periodo');
		if(periodo==null){
			carregar();
		}else{
			var periodo = sessionStorage.getObject('periodo');
			$("#periodoSelecionado").text("De " + periodo.dataInicio + " a " + periodo.dataFim);
			definirValores(periodo);
		}
	
	setTimeout("carregarTabelaDefault()",50);

	$("#ordenarPorCliente").click(function(){
		var periodo = $("#periodoSelecionado").html();
		BRQMob.log("entrando na funcao obterPosicaoVendasPorCliente()");
		BRQMob.exec("sucessoPosicaoVendasPorCliente", "falhaPosicaoVendasPorCliente", "VendasDispatcher", "obterPosicaoVendasPorCliente", [recuperaDataInicio(periodo),recuperaDataFim(periodo)]);
	});
	
	$("#ordenarPorPrazoPagamento").click(function(){
		var periodo = $("#periodoSelecionado").html();
		BRQMob.log("entrando na funcao obterPosicaoVendasPorPrazoDePagamento()");
		BRQMob.exec("sucessoPosicaoVendasPorPrazoPagamento", "falhaPosicaoVendasPorPrazoPagamento", "VendasDispatcher", "obterPosicaoVendasPorPrazoDePagamento", [recuperaDataInicio(periodo),recuperaDataFim(periodo)]);
	});

});

function sucesso(retorno){
	var periodo = { dataInicio : retorno.posicao.dataInicio, dataFim  : retorno.posicao.dataFim };
	sessionStorage.setObject('periodo' , periodo );	
	definirValores(retorno.posicao);
}

function sucessoPosicaoVendasPorCliente(retorno){
	montarTabelaVendasPorCliente(retorno.listaPosicaoVendasPorCliente);
}

function falhaPosicaoVendasPorCliente(retorno){
	alert(retorno.mensagem);
}

function sucessoPosicaoVendasPorPrazoPagamento(retorno){
	montarTabelaVendasPorPrazoPagamento(retorno.listaPosicaoVendasPorPrazoDePagamento);
}

function falhaPosicaoVendasPorPrazoPagamento(retorno){
	alert(retorno.mensagem);
}

function carregarTabelaDefault(){
	var listaPosicaoVendas  = $("#listaPosicaoVendas").val();
	if(listaPosicaoVendas == 0 || listaPosicaoVendas == ""){
		var periodo = $("#periodoSelecionado").html();
		BRQMob.exec("sucessoPosicaoVendasPorPrazoPagamento", "falhaPosicaoVendasPorPrazoPagamento", "VendasDispatcher", "obterPosicaoVendasPorPrazoDePagamento", [recuperaDataInicio(periodo),recuperaDataFim(periodo)]);
	}
}


function montarTabelaVendasPorPrazoPagamento(obj){
	limpaBodyTabela();
	var _div   			= $("#listaPosicaoVendas");
	var _tabela			= $('<table/>', {style: 'width:100%;'});
	var _thead 			= $('<thead/>',{});
	var _tr	  			= $('<tr/>',{});
	var _tbody			= $('<tbody/>',{});
	var _tfoot 			= $('<tfoot/>',{});
	var ultimaPosicao	= 0;
	var _trFoot 		= $('<tr/>',{ "onclick":"tableTR(this);"}).appendTo(_tfoot);
	
	$('<th/>', {text:"Cód. Condição Pagamento"}).appendTo(_tr);
	$('<th/>', {text:"Descrição"}).appendTo(_tr);
	$('<th/>', {text:"Vlr Líquido"}).appendTo(_tr);
	$('<th/>', {text:"Vlr Efetivo"}).appendTo(_tr);
	
	$(_tr).appendTo(_thead);
	$(_thead).appendTo(_tabela);
	$(_tbody).appendTo(_tabela);
	
		for(var index=0 ,length = obj.length-1; index <= length; index+= 1 ){
			ultimaPosicao = length;
			var _trBody = $('<tr/>',{ "class" : ( index % 2 == 0 ? "" : "off "), "onclick":"tableTR(this);"});
			var _tdBody =	$('<td/>', {});
			$('<td/>',{style:'text-align: left', text: obj[index].pedido.condicaoPagamento }).appendTo(_trBody);
			$('<td/>',{style:'text-align: left', text: obj[index].condicaoPagamento.descricaoCondicao }).appendTo(_trBody);
			$('<td/>',{style:'text-align: left', text: obj[index].pedido.valorLiquidoMercadoria}).appendTo(_trBody);
			$('<td/>',{style:'text-align: left', text: obj[index].pedido.valorSemEncargos}).appendTo(_trBody);
			$(_trBody).appendTo(_tbody);
		}
	
	$(_tfoot).appendTo(_tabela);
	
	$('<td/>', {"class":"grid", text: ""}).appendTo(_trFoot);
	$('<td/>', {"class":"grid",style:'text-align: right', text: "Total :    "}).appendTo(_trFoot);
	$('<td/>',{"class":"grid", text: obj[ultimaPosicao].pedido.valorTotalSemEncargos}).appendTo(_trFoot);
	$('<td/>',{"class":"grid", text: obj[ultimaPosicao].pedido.valorTotalPedido}).appendTo(_trFoot);
	
	$(_tabela).appendTo(_div);
	
}
function montarTabelaVendasPorCliente(obj){
		limpaBodyTabela();
		var _div   			= $("#listaPosicaoVendas");
		var _tabela			= $('<table/>', {style: 'width:100%;'});
		var _thead 			= $('<thead/>',{});
		var _tr	  			= $('<tr/>',{});
		var _tbody			= $('<tbody/>',{});
		var _tfoot 			= $('<tfoot/>',{});
		var ultimaPosicao 	= 0;
		var _trFoot			= $('<tr/>',{ "onclick":"tableTR(this);"}).appendTo(_tfoot);
		
		$('<th/>', {text:"Código/Nome do Cliente"}).appendTo(_tr);
		$('<th/>', {text:"Vlr Líquido"}).appendTo(_tr);
		$('<th/>', {text:"Vlr Efetivo"}).appendTo(_tr);
		
		$(_tr).appendTo(_thead);
		$(_thead).appendTo(_tabela);
		$(_tbody).appendTo(_tabela);
		
			for(var index=0 ,length = obj.length-1; index <= length; index+= 1 ){
				ultimaPosicao = length;
				var _trBody = $('<tr/>',{ "class" : ( index % 2 == 0 ? "" : "off"), "onclick":"tableTR(this);"});
				var _tdBody =	$('<td/>', {});
				var _divBody = $('<div/>',{"class":"left"});
				
				$('<a/>',{"class":"left",href :"javascript:void(0);", "onclick":"legenda(this);"}).appendTo(_divBody);
				$('<label/>',{"class":"left grid", text:obj[index].cliente.codigoCliente }).appendTo(_divBody);
				$('<br/>',{'class':'clear'}).appendTo(_divBody);
				$('<label/>',{"class":"grid", text: obj[index].cliente.nomeCliente}).appendTo(_divBody);
				
				$(_divBody).appendTo(_tdBody);
				$(_tdBody).appendTo(_trBody);
				
				$('<td/>',{style:'text-align: left', text: obj[index].pedido.valorLiquidoMercadoria}).appendTo(_trBody);
				$('<td/>',{style:'text-align: left', text: obj[index].pedido.valorSemEncargos}).appendTo(_trBody);
				
				$(_trBody).appendTo(_tbody);
			
			}	
			$(_tfoot).appendTo(_tabela);
			
				$('<td/>', {"class":"grid",style:'text-align: right', text: "Total :    "}).appendTo(_trFoot);
			$('<td/>',{"class":"grid", text: obj[ultimaPosicao].pedido.valorTotalSemEncargos}).appendTo(_trFoot);
			$('<td/>',{"class":"grid", text: obj[ultimaPosicao].pedido.valorTotalPedido}).appendTo(_trFoot);
		
	$(_tabela).appendTo(_div);
	
	//$("#teste").text($(_div).html());
}


function limpaBodyTabela(){
	$("#listaPosicaoVendas").empty();
}

function recuperaDataInicio(periodo){
	return periodo.substring(3,13);

}

function recuperaDataFim(periodo){
	return periodo.substring(16,26);
}

function carregar(){
		BRQMob.log("entrando na funcao carregar ");
	  	BRQMob.exec("sucesso", "falha", "VendasDispatcher", "posicaoVendas",[]);
}
	
	
function definirValores(periodo){
	var representante = getDadosComerciaisRepresentante();

	$("#periodoSelecionado").text("De " + periodo.dataInicio + " a " + periodo.dataFim);
	$("#previsaoSemanal").text(representante.previsaoSemanal);
	
	$("#aproveitamentoAtual").text(representante.aproveitamentoAtual);
	$("#tempAproveitamentoAtual").width(representante.aproveitamentoAtual + "%");
	
	$("#aproveitamentoIdeal").text(representante.aproveitamentoIdeal);
	$("#tempAproveitamentoIdeal").width(representante.aproveitamentoIdeal + "%");
}
	
		
function proxima(){
	window.location="../martins/posicaoPeriodo.html";
}	