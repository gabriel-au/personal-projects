
var itensGondola = null;

$(document).ready(function() {
	
	//Scroll
	scrollAreaResize('wrapperScroll');	
	scrollRegistry("contentScroll", true, false);

	BRQNavegacao.adicionarPilhaNavegacao('clienteGondola');
	
	$("#nomeMercadoria").keydown(function(event){
		var keyCode = (event.keyCode ? event.keyCode : event.which);
		if (keyCode == 13){
			buscarItemGondola();
		}
		
	});

	$("#nomeMercadoria-clearBt").mousedown(function(){
		montarListaGondola();
	});
	
	var cliente = getCliente();
	execListarItensGondolaCliente(cliente.codigoCliente);
	
});

//Resize event
$(window).resize(function() {
	scrollAreaResize('wrapperScroll');
});

function execListarItensGondolaCliente(codigoCliente){
	BRQMob.log("entrando na funcao execListarItensGondolaCliente");
	BRQMob.exec("retornoListarItemGondolaCliente", "falha", "RelacionamentoDispatcher", "listarItensGondolaCliente", [ codigoCliente ]);			
}

function retornoListarItemGondolaCliente(retorno){
	BRQMob.log("Lendo dados retornoListarItemGondolaCliente");
	if(retorno != null){
		itensGondola = retorno.Gondolas;
		montarListaGondola();
	}
 }

function montarListaGondola(){
	BRQMob.log("Lendo dados montarListaGondola");
	limparListas();
	if(itensGondola != null && itensGondola.length > 0){
		montarListas(itensGondola);
	}
}

function limparListas(){
	$('#tbody_itens_gondola').empty();
	$('#ul_item_descricao').empty();
}

function montarListas(itensGondola){
	BRQMob.log("Lendo dados montarListas");

	goTop();
	
	var tblBody = $("#tbody_itens_gondola");
	var ulItem = $('#ul_item_descricao');
	
	for(var index=0;index<itensGondola.length;index++){
//				montarLinhaItemDescricao(ulItem, itensGondola[index], index);
		montarLinhaItemTabela(tblBody, itensGondola[index], index);
	}
	
	//Descrição
	$(".description").each(
		function() {
			createDescription(this);
		}
	);

}

/*		function montarLinhaItemDescricao(ulItem, gondola, index){
			
			if(index % 2==0){
				tag_li = $('<li/>', {"class":"off"});
			}else{
				tag_li = $('<li/>', {});
			}
			
			tag_a = $('<a/>', {href:"javascript:void(0);","class":"moreInfo", onclick : "openDescription(this);"});
			$('<span/>', {"class":"icon info"}).appendTo(tag_a);
			tag_a.appendTo(tag_li);
			
			tag_div_descricao = $('<div/>', {"class":"description"}); //, "style":"width: 100%;"});
		
			tag_div_esquerda = $('<div/>', {"class":"left"});
			$('<label/>', {"class":"left", text: "Cód: " + gondola.codigoMercadoria }).appendTo(tag_div_esquerda);
			$('<br/>', {"class":"clear"}).appendTo(tag_div_esquerda);
			tag_a_nomeMercadoria = $('<a/>', {href:"javascript:void(0);","class":"selectable left"});
			$('<label/>', {"class":"grid", text: gondola.descricaoMercadoria + " " + gondola.descricaoComplementarMercadoria }).appendTo(tag_a_nomeMercadoria);
			tag_a_nomeMercadoria.appendTo(tag_div_esquerda);
			tag_div_esquerda.appendTo(tag_div_descricao);
			
			tag_div_direita = $('<div/>', {"class":"right"});
			tag_a_produtoDescricao = $('<a/>', {href:"../vendas/produtoDesc.html","class":"button left" });
			$('<span/>', {"class":"icon detalhes"}).appendTo(tag_a_produtoDescricao);
			tag_a_produtoDescricao.appendTo(tag_div_direita);
			
		---	tag_a_editarGondola = $('<a/>', {href:"javascript:void(0);","class":"button left", 
											 onclick: "alterarItemGondola(" + gondola.codigoCliente + ", " + gondola.posicaoMercadoria + ", '" 
													 						+ gondola.descricaoMercadoria + " " + gondola.descricaoComplementarMercadoria + "');" });
			
			$('<span/>', {"class":"icon editar"}).appendTo(tag_a_editarGondola);
			tag_a_editarGondola.appendTo(tag_div_direita);
			
			
			tag_a_excluirGondola = $('<a/>', {href:"javascript:void(0);","class":"button left", onclick: "deletarItemGondola(" + gondola.codigoCliente + ","+ gondola.posicaoMercadoria + ");" });
			$('<span/>', {"class":"icon excluir"}).appendTo(tag_a_excluirGondola);
			tag_a_excluirGondola.appendTo(tag_div_direita);
			-----
			
			tag_div_direita.appendTo(tag_div_descricao);

			tag_div_descricao.appendTo(tag_li);
			
			$(tag_li).appendTo(ulItem);
			
		}*/

function montarLinhaItemTabela(tblBody, gondola, index){
	
	if(index % 2==0){
		tag_tr = $('<tr/>', {onclick:"tableTR(this);", "class":"off" });
	}else{
		tag_tr = $('<tr/>', {onclick:"tableTR(this);" });
	}
	
	//$('<td/>', {style:"width: 16px;", text: " "}).appendTo(tag_tr);
	$('<td/>', { text: gondola.posicaoMercadoria }).appendTo(tag_tr);
	
	tag_td_informacao = $('<td/>', {});
		tag_div_informacao = $('<div/>', {"class":"left" });
		$('<label/>', {"class":"left", text: "Cód: " + gondola.codigoMercadoria }).appendTo(tag_div_informacao);
		$('<br/>', {"class":"clear"}).appendTo(tag_div_informacao);
		$('<label/>', {"class":"grid", text: gondola.descricaoMercadoria + " " + gondola.descricaoComplementarMercadoria }).appendTo(tag_div_informacao);
		$(tag_div_informacao).appendTo(tag_td_informacao);
	$(tag_td_informacao).appendTo(tag_tr);
	
	$('<td/>', {style:"text-align: right;", text: gondola.precoCliente}).appendTo(tag_tr);
	$('<td/>', {text: gondola.quantidadeMercadoriaAnterior}).appendTo(tag_tr);
	$('<td/>', {text: gondola.quantidadeMercadoriaAtual}).appendTo(tag_tr);
	$('<td/>', {text: gondola.dataUltimaVenda}).appendTo(tag_tr);

	$(tag_tr).appendTo(tblBody);

}

/*		function alterarItemGondola(codigoCliente, posicaoMercadoria, descricaoMercadoriaComplementar){
			BRQMob.log("entrando na funcao alterarItemGondola");
		
			//Removendo item anterior
			sessionStorage.removeItem("itemGondola");
			
			//Armazenar o estado da tela
			var itemGondola = new Object();
			itemGondola.codigoCliente = codigoCliente;
			itemGondola.posicaoMercadoria =  posicaoMercadoria;
		    itemGondola.descricaoMercadoriaComplementar = descricaoMercadoriaComplementar;
			sessionStorage.setObject('itemGondola', itemGondola);
			
			window.location.href = "gondolaAlterar.html";
			
		}
				
		function removeFormatacao(valor, isBanco){
			if(isBanco){ //Remover os pontos
				valor = valor.replace(/[\.]/g, "");
			}
			return valor.replace(",",".");
		}
		
		function deletarItemGondola(codigoCliente, posicaoMercadoria){
			if(confirm('Deseja realmente retirar do caminhão ?')){
				BRQMob.log("entrando na funcao deletarItemGondola");
				BRQMob.exec("retornoDeletarItemGondola", "falha", "RelacionamentoDispatcher", "deletarItemGondolaCliente", [ codigoCliente, posicaoMercadoria ]);			
			}else{
				return false;
			}
		}
		
		function retornoDeletarItemGondola(retorno){
			limparMensagemErro();
			var cliente = getCliente();
			execListarItensGondolaCliente(cliente.codigoCliente);
		}
*/		
function falha(result){
	BRQMob.log("falha: " + result);
	$("#mensagem").html(result);
	$("#divMensagem").css('display', 'inline');
}

function limparMensagemErro(){
	BRQMob.log("limparMensagemErro: ");
	$("#divMensagem").css('display', 'none');
	$("#mensagem").html("");
}

function buscarItemGondola(){
	var nomeMercadoria = $("#nomeMercadoria").val().trim();
	var listaPorMercadoria = new Array();
	for(var index=0;index<itensGondola.length;index++){
		var descricaoMercadoria = itensGondola[index].descricaoMercadoria + " " + itensGondola[index].descricaoComplementarMercadoria;
				if(descricaoMercadoria.toLowerCase().indexOf(nomeMercadoria.toLowerCase()) != -1){
					listaPorMercadoria.push(itensGondola[index]);
	    		}
			}
    
    		limparListas();
    		
    		montarListas(listaPorMercadoria);
 
		}

