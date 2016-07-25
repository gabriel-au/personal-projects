/* Variável de instância utilizada para armazenamento do alfabeto */
var alphabet = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");

/* Variável de instância utilizada para armazenamento da lista de clientes após cada consulta (Normais, Suspensos ou Cortados)*/
var listaSituacaoClientes = null;

/*
* Executado ao iniciar a tela
*/
$(document).ready(function() {
	
	//Função para adicionar ação ao botão da letra do alfabeto
	$("#clientFilter").children().children().click(
		function() {
			montarListaSituacaoCliente();
		}
	);
	
	$("#divTipoConsulta").children().children().each(function() {
		$(this).click( function() {
			montarListaSituacaoCliente();
		});
	});
	
	$("#nomeCliente").keydown(function(event){
		var keyCode = (event.keyCode ? event.keyCode : event.which);
		if (keyCode == 13){
			montarListaSituacaoCliente();
		}
		
	});

	$("#nomeCliente-clearBt").mousedown(function(){
		montarListaSituacaoCliente();
	});
	
	execListarSituacaoCliente();		

});

/**
* Método que recupera a lista de situação dos clientes
*/
function execListarSituacaoCliente() {
	
	var codigoTerritorio = null;
	if(isGerenteMercado()){
		codigoTerritorio = getRepresentanteEquipe().codigoTerritorio;
	}
	
	BRQMob.log("Lendo dados execListarSituacaoCliente");
	BRQMob.exec("retornoListaSituacaoCliente", "falha", "RelacionamentoDispatcher", "listarSituacaoCliente", [ codigoTerritorio ]);
}

/**
* Método que carrega a lista de situação dos clientes
* @param cliente - objeto json de retorno
*/
function retornoListaSituacaoCliente(retorno){
	BRQMob.log("Lendo dados retornoListaSituacaoCliente");
	if(retorno != null){
		listaSituacaoClientes = retorno.Clientes;
		montarLista(retorno.Clientes);
	}
	//Selecionar o tipo de consulta (Todos - 0, Normais - 1, Suspensos - 2 ou cortados - 3)
	$("#divTipoConsulta").children().children().eq(0).addClass("selected");
}

/**
* Método que monta a lista de situação de clientes
* @param listaSituacaoClientes - lista de situação dos clientes para exibir na tela
*/
function montarLista(listaClientes){			
	$("#tbody_situacao_clientes").empty();
	var tbody_situacao_clientes = $("#tbody_situacao_clientes");
	
	$.each(listaClientes, function(i,obj){
		tr = $('<tr/>', {"onclick":"tableTR(this);"});
		td_codigo= $('<td/>', {});
		$($('<label/>', {"class":"left grid"})).appendTo(td_codigo);
		$($('<span/>', {text:obj.codigoCliente})).appendTo(td_codigo);
		$(td_codigo).appendTo(tr);
		
		td_nome= $('<td/>', {});				
		label_nome=$($('<label/>', {"class":"left grid"})).appendTo(td_nome);
		$($('<span/>', {text:obj.nomeCliente})).appendTo(label_nome);
		$(td_nome).appendTo(tr);
		
		td_filial_exp= $('<td/>', {});					
		$($('<label/>', {"class":"left grid"})).appendTo(td_filial_exp);
		$($('<span/>', {text:obj.codigoFilialExpedicao})).appendTo(td_filial_exp);
		$(td_filial_exp).appendTo(tr);
		
		td_data_sit= $('<td/>', {});	
		$($('<label/>', {"class":"left grid"})).appendTo(td_data_sit);
		$($('<span/>', {text:obj.dataSituacao})).appendTo(td_data_sit);
		$(td_data_sit).appendTo(tr);
		
		td_situacao_cliente= $('<td/>', {});	
		label_situacao_cli=$($('<label/>', {"class":"left grid"})).appendTo(td_situacao_cliente);
		$($('<span/>', {text:obj.situacaoCliente})).appendTo(label_situacao_cli);
		$(td_situacao_cliente).appendTo(tr);
		
		td_situacao_pedido= $('<td/>', {});				
		label_situacao_ped=$($('<label/>', {"class":"left grid"})).appendTo(td_situacao_pedido);
		$($('<span/>', {text:obj.situacaoPedido})).appendTo(label_situacao_ped);
		$(td_situacao_pedido).appendTo(tr);
		
		td_nome_fant= $('<td/>', {});				
		label_nome_fant=$($('<label/>', {"class":"left grid" })).appendTo(td_nome_fant);
		$($('<span/>', {text:obj.nomeFantasia})).appendTo(label_nome_fant);
		$(td_nome_fant).appendTo(tr);
		
		td_desc_sit= $('<td/>', {});				
		label_desc=$($('<label/>', {"class":"left grid" })).appendTo(td_desc_sit);
		$($('<span/>', {text:obj.descricaoSituacao})).appendTo(label_desc);
		$(td_desc_sit).appendTo(tr);
		
		td_obs= $('<td/>', {});				
		label_obs=$($('<label/>', {"class":"left grid" })).appendTo(td_obs);
		$($('<span/>', {text:obj.descricaoJustificativa})).appendTo(label_obs);
		$(td_obs).appendTo(tr);
		
		$(tr).appendTo(tbody_situacao_clientes);
	});
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

/*
* Método que cria e inicializa o objeto para montagem da lista de clientes
* @return objLista - objeto: <ul></ul>
*/
function inicializarLista(){
	var objLista = $("#ulClientes");
	objLista.html("");
	return objLista;
}

/*
* Método que monta a lista de situação dos clientes
*/
function montarListaSituacaoCliente(){
	goTop();
	var objLista = inicializarLista();
	if(listaSituacaoClientes != null){
		var listaClientes = new Array();
		
		var tipoConsulta = retornarTipoConsulta();
		listaClientes = retornarListaPorTipoConsulta(tipoConsulta);

		var nomeCliente = $('#nomeCliente').val().trim();
		if(nomeCliente != ''){
			listaClientes = retornaListaPorNomeCliente(listaClientes, nomeCliente);
		}
		var letra = retornarLetra();
		if(letra != ""){
			listaClientes = retornarListaPorLetra(listaClientes, letra);
		}
		montarLista(listaClientes);
	}
}

/**
* Método que retorna o estilo para colorir as linhas pares
*/
function retornarEstiloLinha(index){
	var estilo = "";
	if(index%2 != 0){
		estilo = "off";
	}
	return estilo;
}

/*
* Método que idetifica o tipo de consulta selecionado (index do botão - 0 Todos, 1 - Normais, 2 - Suspensos ou 3 - Cortados)
* @return tipoConsulta - retorna com T (Todos), N (Normais), S (Suspensos) ou C (Cortados)
*/
function retornarTipoConsulta(){
	var index =  $("#divTipoConsulta").children().children(".selected").index();
	var tipoConsulta = "";
	switch (index){
	case 0: tipoConsulta = "T";
		break;
	case 1: tipoConsulta = "N";
		break;
	case 2: tipoConsulta = "S";
		break;
	case 3: tipoConsulta = "C";
		break;
	}
	return tipoConsulta;
}

/*
* Método que idetifica se tem alguma letra do alfabeto selecionada
* @return letra - retorna a letra do alfabeto ou vazio caso não tenha nenhuma letra selecionada
*/
function retornarLetra(){
	var letra = "";
	if($("#clientFilter").children().children(".selected").index() > -1){
		letra = alphabet[$("#clientFilter").children().children(".selected").index()];
	}
	return letra;
}

/**
* Método que filtra a lista e retona apenas clientes de acordo com o filtro nome do cliente digitado 
* @param - lista de clientes a ser filtrada
*/
function retornaListaPorNomeCliente(listaClientes, nomeCliente){
	var listaPorNomeCliente = new Array();
	for(var index=0,fim=listaClientes.length;index<fim;index+=1){
		if(listaClientes[index].nomeCliente.toLowerCase().indexOf(nomeCliente.toLowerCase()) != -1){
			listaPorNomeCliente.push(listaClientes[index]);
		}
	}
	return listaPorNomeCliente;
}

/**
* Método que retorna a lista de clientes de acordo com o tipo de consulta selecionado
* @param tipoConsulta - tipo de consulta selecionada (T - Todos, N - Normais, S - Suspensos ou C - Cortados)
* @return lista - lista apenas com o tipo de consula selecionado
*/
function retornarListaPorTipoConsulta(tipoConsulta){
	var listaPorTipoConsulta = new Array();
	if(tipoConsulta == "T"){
		listaPorTipoConsulta = listaSituacaoClientes;
	} else {
		for(var index=0,fim=listaSituacaoClientes.length;index<fim;index+=1){
			if(listaSituacaoClientes[index].tipoSituacaoCliente.toUpperCase() == tipoConsulta){
				listaPorTipoConsulta.push(listaSituacaoClientes[index]);
			}
		}
	}
	return listaPorTipoConsulta;
}

/*
* Método que retorna a lista de clientes de acordo com a letra e tipo de consulta selecionado
* @param listaClientes - letra do alfabeto selecionada
* @param letra - letra do alfabeto selecionada
* @return listaPorLetra - nova lista de acordo com os parâmetros selecionados
*/
function retornarListaPorLetra(listaClientes, letra){
	var listaPorLetra = new Array();
	for(var index=0,fim=listaClientes.length;index<fim;index+=1){
		if(listaClientes[index].nomeCliente[0].toUpperCase() == letra){
			listaPorLetra.push(listaClientes[index]);
		}
	}
	return listaPorLetra;
}