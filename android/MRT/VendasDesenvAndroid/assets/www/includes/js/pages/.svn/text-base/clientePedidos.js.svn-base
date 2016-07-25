$(document).ready(		
	function(){
		BRQNavegacao.adicionarPilhaNavegacao('clientePedidos');
		sessionStorage.setObject(CONST_PESQUISA_BY_CLIENTE, 'true');
		carregaPedidos();
		
		$("#clientePedido").keydown(function(event){
			var keyCode = (event.keyCode ? event.keyCode : event.which);
			if (keyCode == 13){
				montarListaPedido();
			}				
	});	
	
	$("#clientePedido-clearBt").mousedown(function(){
		carregaPedidos();
	});					
});	

function PedidoEmail(nomeCliente,codigoCliente,numeroPedido,preparado,dataPedido,horaPedido,dataResultado,quantidadeItens,valorTotalPedido ) {
	this.nomeCliente = nomeCliente;
	this.codigoCliente = codigoCliente;
	this.numeroPedido = numeroPedido;
	this.preparado = preparado;
	this.dataPedido = dataPedido;
	this.horaPedido = horaPedido;
	this.dataResultado = dataResultado;
	this.quantidadeItens = quantidadeItens;
	this.valorTotalPedido = valorTotalPedido;
	
}

function montarPedidoEmail(nomeCliente,codigoCliente,numeroPedido,preparado,dataPedido,horaPedido,dataResultado,quantidadeItens,valorTotalPedido){
	var pedidoEmail = new PedidoEmail(nomeCliente,codigoCliente,numeroPedido,preparado,dataPedido,horaPedido,dataResultado,quantidadeItens,valorTotalPedido);
	//guarda o pedido no objeto de pedidos
	sessionStorage.setObject('pedidoEmail', pedidoEmail);
	window.location.href = "../vendas/gerarPDF_pedido.html";
	// window.location.href = getUrlPagina("gerarPDF_pedido");
}



function carregaPedidos(){
	var objStorageClientes = getCliente();
	var codigoCliente = (objStorageClientes != null ? objStorageClientes.codigoCliente : null);
	BRQMob.log("entrando na funcao carregar pedidos");
  	BRQMob.exec("sucesso", "falha", "VendasDispatcher", "pedidos", [ codigoCliente ]);			
}

function sucesso(retorno){
	listaPedidos = retorno.pedidos;
	loadListaPedidos(retorno.pedidos);
}

function loadListaPedidos(pedidos){
	$("#pedidos").empty();
	
	var outer_list = $('<div/>', {"class": "tableList" });
		
	table=$('<table/>', {"style":"width:2200px;"});
	thead =  $('<thead/>', {});
	tr= $('<tr/>', {});
	$($('<th/>', {text: 'Código/Nome do Cliente',"style":"width:100px;"})).appendTo(tr);
	$($('<th/>', {text:'N˚ Pedido',"style":"width:100px;"})).appendTo(tr);
	$($('<th/>', {text:'Prep.',"style":"width:50px;"})).appendTo(tr);
	$($('<th/>', {text:'Data Ped.',"style":"width:100px;"})).appendTo(tr);
	$($('<th/>', {text:'Hora Ped.',"style":"width:100px;"})).appendTo(tr);
	$($('<th/>', {text:'Data Result.',"style":"width:100px;"})).appendTo(tr);
	$($('<th/>', {text:'N˚ itens',"style":"width:100px;"})).appendTo(tr);
	$($('<th/>', {text:'Total líq.',"style":"width:50px;"})).appendTo(tr);
	$($('<th/>', {text:'Cortes',"style":"width:50px;"})).appendTo(tr);
	$(tr).appendTo(thead);
	$(thead).appendTo(table);
	tbody =  $('<tbody/>', {});				
	
	$.each(pedidos, function(i,obj){
		tr= $('<tr/>', {"onclick":"tableTR(this);"});
		td_codigo= $('<td/>', {});
		div_codigo=$('<div/>', {"class":"left"});
		$($('<label/>', {"class":"left"})).appendTo(div_codigo);
		$($('<span/>', {text:obj.codigoCliente})).appendTo(div_codigo);
		$($('<br/>', {"class":"clear"})).appendTo(div_codigo);
		$($('<label/>', {"class":"grid"})).appendTo(div_codigo);
		$($('<span/>', {text:obj.nomeCliente})).appendTo(div_codigo);
		div_btn=$('<div/>', {"class":"right", "style":"margin-top: 2px;"});
		href=$('<a/>', {href:"javascript:void(0);","class":"button right", onclick : "gravaNumeroPedidoSelecionado("+obj.numeroPedido+");ativaOpcoesPedido('"+obj.nomeCliente+"','"+obj.codigoCliente+"','"+obj.numeroPedido+"','"+obj.preparado +"','"+ obj.dataPedido +"','"+ obj.horaPedido +"','"+ obj.dataResultado +"','"+ obj.quantidadeItens +"','"+ obj.valorTotalPedido +"');selecionaLinha(" + obj.codigoCliente + ",'" + obj.nomeCliente + "'," + obj.numeroPedido + "," + obj.codigoTerritorio + ",'" + obj.codigoSegmento + "'" + ");"});
		$($('<span/>', {"class":"icon opcoes"})).appendTo(href);
		$(href).appendTo(div_btn);
		$(div_codigo).appendTo(td_codigo);
		$(div_btn).appendTo(td_codigo);
		$(td_codigo).appendTo(tr);
		
		td_numPedido= $('<td/>', {});
		$($('<label/>', {text:obj.numeroPedido})).appendTo(td_numPedido);
		$(td_numPedido).appendTo(tr);
		
		td_prep= $('<td/>', {});
		prep=$($('<label/>', {"class":"left"})).appendTo(td_prep);
		$($('<span/>', {text:obj.preparado})).appendTo(prep);
		$(td_prep).appendTo(tr);
		
		td_data_pedido= $('<td/>', {});
		$($('<label/>', {text:obj.dataPedido})).appendTo(td_data_pedido);
		$(td_data_pedido).appendTo(tr);
		
		td_hora_pedido= $('<td/>', {});
		$($('<label/>', {text:obj.horaPedido})).appendTo(td_hora_pedido);
		$(td_hora_pedido).appendTo(tr);
		
		td_data_result= $('<td/>', {});
		$($('<label/>', {text:obj.dataResultado})).appendTo(td_data_result);
		$(td_data_result).appendTo(tr);
		
		td_qtd_itens= $('<td/>', {});
		$($('<label/>', {text:obj.quantidadeItens})).appendTo(td_qtd_itens);
		$(td_qtd_itens).appendTo(tr);
		
		td_tot_liq= $('<td/>', {});
		liq=$($('<label/>', {"class":"right"})).appendTo(td_tot_liq);
		$($('<span/>', {text:obj.valorTotalPedido})).appendTo(liq);
		$(td_tot_liq).appendTo(tr);
		
		td_cortes= $('<td/>', {});
		$($('<label/>', {text:obj.mercadoriaCortada})).appendTo(td_cortes);
		$(td_cortes).appendTo(tr);
		
		$(tr).appendTo(tbody);	
		$(tbody).appendTo(table);	
		$(table).appendTo(outer_list);							
	});
	$("#pedidos").append(outer_list);
}

function selecionaLinha(codigoCliente, nomeCliente, numPedido,codigoTerritorio,codigoSegmento){
	objStorageClientes = new Object();
	objStoragePedido = new Object();			

	objStorageClientes.codigoCliente = codigoCliente;
	objStorageClientes.nomeCliente = nomeCliente;
	objStorageClientes.codigoTerritorio = codigoTerritorio;
	objStorageClientes.codigoSegmento = codigoSegmento;
	objStoragePedido.numeroPedido = numPedido;			
	
	sessionStorage.setObject(CONST_PEDIDO, objStoragePedido);
	sessionStorage.setObject(CONST_CLIENTE, objStorageClientes);		
}	
	
function falha(retorno){
	BRQMob.log("falha dados iniciais: " + retorno);	 
}

function proximaPagina(){
	if('clienteRelacionamento' == getViewNameOrigem()){
		window.location.href = getUrlPagina('informacoesGerais');
	}else{
		window.location.href = getUrlPagina('listaCliente');
	}
}		

function montarListaPedido(){
 if(listaPedidos!=null){
	var pedidos = new Array();
		var clientePedido = $('#clientePedido').val().trim();	
		if(clientePedido!=""){
  			if(parseInt(clientePedido) ) {
		   		pedidos = retornaListaPorCodPedido(listaPedidos, clientePedido);     			   		
			}else{					
  				pedidos = retornaListaPorNomeCliente(listaPedidos, clientePedido);
  			}
  			loadListaPedidos(pedidos);
		}			
	}
}

function retornaListaPorNomeCliente(listaPedidos, clientePedido){
	var listaPorNomeCliente = new Array();
	for(var index=0;index<listaPedidos.length;index++){
		if(listaPedidos[index].nomeCliente.toLowerCase().indexOf(clientePedido.toLowerCase()) != -1){
			listaPorNomeCliente.push(listaPedidos[index]);
			
		}
	}
	return listaPorNomeCliente;
}

function retornaListaPorCodPedido(listaPedidos, clientePedido){
	var listaPorCodPedido = new Array();	
	for(var index=0;index<listaPedidos.length;index++){
		if(listaPedidos[index].numeroPedido==clientePedido){				
			listaPorCodPedido.push(listaPedidos[index]);
		}
	}	
		return listaPorCodPedido;
}

function gravaNumeroPedidoSelecionado(numeroPedidoSelecionado){
			sessionStorage.setObject('numeroPedidoSelecionado', numeroPedidoSelecionado);
}

function excluiPedido(){
	var numeroPedidoSelecionado = sessionStorage.getObject('numeroPedidoSelecionado');
	if (confirm("Deseja realmente excluir o pedido de no. " + numeroPedidoSelecionado + " ?")){
		BRQMob.exec("retornoExcluiPedido", "retornoExcluiPedido", "VendasDispatcher", "excluiPedido", [numeroPedidoSelecionado]);
	}		
}
		
function retornoExcluiPedido(retorno){
	if(retorno.mensagem != null){
		alert(retorno.mensagem);
		carregaPedidos();
		evento3(this);
	}
}

function voltar(){
	sessionStorage.setObject(CONST_PESQUISA_BY_CLIENTE, 'false');
	if('listaCliente' == getViewNameOrigem()){
		sessionStorage.removeItem("listaCliente");	
		sessionStorage.removeItem(CONST_CLIENTE);	
		BRQNavegacao.voltar();
	}else{
		BRQNavegacao.voltar();
	}
}
