/* Variável de instância utilizada para armazenamento do alfabeto */
var alphabet = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");

/* Variável de instância utilizada para armazenamento da lista de clientes após cada consulta (Normais, Suspensos ou Cortados)*/
var jsonClientes = null;

/*
* Executado ao iniciar a tela
*/
$(document).ready(function() {
	
	BRQNavegacao.adicionarPilhaNavegacao('clientes');
	sessionStorage.removeItem(CONST_CLIENTE);
	//controleBotoes();
	
	//Recuperando dados do Representando para barra de aproveitamento
	//setarValoresAproveitamento();
	
	$("#divRazaoFantasia").children().children().each(function() {
		$(this).click(function() {
			montarListaClientes();
		});
	});
	
	$("#tops").click(
		function() {
			montarListaClientes();
		}
	);

	$("#favoritos").click(
		function() {
			montarListaClientes();
		}
	);
	
	$("#divTipoConsulta").children().children().each(function() {
		$(this).click( function() {
			montarListaClientes();
		});
	});
	
	//Função para adicionar ação ao botão da letra do alfabeto
	$("#clientFilter").children().children().click(
		function() {
			montarListaClientes();
		}
	);
	
	var objStorageClientes = getCliente();
	
	
	//Identifica se existe objeto no local storage
	if(objStorageClientes == null){
		
		// (Primeira vez na tela)
		//Selecionar padrão inicial da tela: css nos botões Código do cliente e Normais
  		$("#divRazaoFantasia").children().children().eq(0).addClass("selected");
		//Selecionar o tipo de consulta (Normais - 0, Suspensos - 1 ou cortados - 2)
		$("#divTipoConsulta").children().children().eq(0).addClass("selected");
		
		listarClientes();
		
	}else{
		//Recarregar os dados
		recarregarTela(objStorageClientes);
	}

});

/**
* Método que preenche os dados do aproveitamento do cliente
*/
/*function setarValoresAproveitamento(){
	var dadosRepresentante = getDadosComerciaisRepresentante();
	var html =  "<span>Ideal: " + dadosRepresentante.aproveitamentoIdeal + "%</span><br />";
		html += "<span>Atual: " + dadosRepresentante.aproveitamentoAtual + "%</span>";
	$('#divValores').html(html);
	
	var divValores = $('#divAproveitamento');
	html = "<div style='width:"+dadosRepresentante.aproveitamentoAtual+"%;' class='green'></div>";
	$('#divAproveitamento').html(html);
}*/

/**
* Método para listar clientes
*/
function listarClientes(){
	
	var codigoTerritorio = null;
	if(isGerenteMercado()){
		codigoTerritorio = getRepresentanteEquipe().codigoTerritorio;
	}
	
	BRQMob.log("entrando na funcao listarClientes: action - listarClientes");
	BRQMob.exec("retornoListaClientes", "falha", "RelacionamentoDispatcher", 'listarClientes', [ codigoTerritorio ]);			
}

/**
* Método para montar a lista de clientes
* @param clientes - lista de clientes retornados da action
*/
function retornoListaClientes(clientes){
	if(clientes != null){
		//Armazenando a lista de clientes
		jsonClientes = clientes.Clientes;
		goTop();
		montarListaClientes();
	 }
 }

/**
* Método que monta a lista de clientes de acordo com todas as opções selecionadas em tela:
* Tipo de consulta - Razão Social ou Nome Fantasia
* Favoritos - identifica se a opção de favoritos está selecionada
* Letra - identifica se existe letra do alfabeto selecionada
*/
function montarListaClientes(){
	
	if(jsonClientes != null){
		
		var listaClientes = new Array();
		
		//Verifica o tipo de ordenação da lista
		var	tipoRazaoFantasia = retornarRazaoFantasia();
		
		//Verifica o tipo consulta selecionado
		var	tipoConsulta = retornarTipoConsulta();
		
		//Verificar se tem favoritos selecionado
		var favoritos = retornarFavorito();
		
		//Verificar se tem top selecionado
		var tops = retornarTop();
		
		//Verificando o tipo de consulta
		listaClientes = retornarListaPorTipoConsulta(tipoConsulta);
		
		//Verifica se existe letra selecionada
		var letra = retornarLetra();
		
		if(letra != ""){
			listaClientes = retornarListaPorLetra(listaClientes, letra, tipoRazaoFantasia);
		}

		var objLista = inicializarLista();
		
		if(listaClientes.length > 0){
			
			//Ordenar a lista de clientes
			listaClientes = ordenarPorRazaoFantasia(listaClientes, tipoRazaoFantasia);
			
			//Separar a lista em favoritos
			if(favoritos == "S" && tops == "S"){
				listaClientes = ordenarPorFavoritoTop(listaClientes);
			}else if(favoritos == "S"){
				listaClientes = ordenarPorFavorito(listaClientes);
			}else if(tops == "S"){
				listaClientes = ordenarPorTop(listaClientes);
			}
			
			for(var index=0;index<listaClientes.length;index++){
				montarLinha(listaClientes[index], objLista, index);
			}
		}

	}
	scrollTop();
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

/**
* Método que monta a linha com os dados de cliente
* @param cliente - objeto cliente
* @param objLista - objeto onde a linha será adicionada
*/
function montarLinha(pedidoCliente, objLista, index){
	
	var html = '';
	if(index%2 != 0){
		html += '<li class="off" onclick="javascript:detalharCliente(' + pedidoCliente.codigoCliente + ', \'' + pedidoCliente.nomeCliente 
				+ '\', ' + pedidoCliente.canal + ', \'' + pedidoCliente.situacao + '\'' + ', \'' + pedidoCliente.mensagem + '\'' + ', \'' + pedidoCliente.motivoBloqueio 
				+ '\'' + ', ' + pedidoCliente.bloqueado + ',' + pedidoCliente.diferencaDias + ',' + pedidoCliente.codigoTerritorio + ', \'' + pedidoCliente.tipoContratoVendor  
				+ '\'' + ',' + pedidoCliente.tipoCobrancaCondicaoPagamento + ', \''+ pedidoCliente.tipoAnaliseCredito + '\'' + ', ' + pedidoCliente.codigoSegmento + ');">';
	}else{
		html += '<li onclick="javascript:detalharCliente(' + pedidoCliente.codigoCliente + ', \'' + pedidoCliente.nomeCliente 
			+ '\', ' + pedidoCliente.canal + ', \'' + pedidoCliente.situacao + '\'' + ', \'' + pedidoCliente.mensagem + '\'' + ', \'' + pedidoCliente.motivoBloqueio 
			+ '\'' + ', ' + pedidoCliente.bloqueado + ',' + pedidoCliente.diferencaDias + ',' + pedidoCliente.codigoTerritorio + ', \'' + pedidoCliente.tipoContratoVendor  
			+ '\'' + ',' + pedidoCliente.tipoCobrancaCondicaoPagamento + ', \''+ pedidoCliente.tipoAnaliseCredito + '\'' + ', ' + pedidoCliente.codigoSegmento + ');">';
	}
	
	if(pedidoCliente.indicativoFavorito == "1"){
		html +=	"<span class='icon favorito'></span>";
	}else if(pedidoCliente.indicativoTop == "1"){
		html +=	"<span class='icon top'></span>";
	}else{
		html +=	"<span class='icon'></span>";
	}
		html +=	"<div class='left'>";
			html +=	"<label class='large'>" + pedidoCliente.codigoCliente + "</label><span class='large'> " + pedidoCliente.nomeCliente + "</span><br />";
			html +=	"<label>Nome fantasia: </label><span>" + pedidoCliente.nomeFantasia + "</span>";
		html +=	"</div>";
		html +=	"<div class='right positivar'>";
			html +=	"<label>Positivar</label><br/>";
			html +=	"<span style='position:relative; float: right'>" + pedidoCliente.positivar + "</span>";
		html +=	"</div>";
	if(pedidoCliente.statusAtendimento == 0){
		html +=	"<span class='icon bulletRed right' style='margin-right: -44px'></span>";
	} else if(pedidoCliente.statusAtendimento == 1) {
		html +=	"<span class='icon bulletGreen right' style='margin-right: -44px'></span>";
	}else{
		html +=	"<span class='icon bulletYellow right' style='margin-right: -44px'></span>";
	}
	html +=	"</li>";
		
	objLista.append(html)
	
}

/*
* Método que idetifica o tipo de seleção (index do botão na página - 0 para Razão Social e 1 para Nome Fantasia)
* @return tipoRazaoFantasia - retorna com R (Razão Social) ou N (Nome Fantasia)
*/
function retornarRazaoFantasia(){
	var index = $("#divRazaoFantasia").children().children(".selected").index();
	var tipoConsulta = "";
	switch (index){
	case 0: tipoConsulta = "C"; //Código cliente
		break;
	case 1: tipoConsulta = "R"; // Razão Social
		break;
	case 2: tipoConsulta = "N"; // Nome Fantasia
		break;
	}
	return tipoConsulta;
}

/*
* Método que idetifica o tipo de consulta selecionado (index do botão - 0 Normais, 1 - Suspensos ou 2 - Cortados)
* @return tipoConsulta - retorna N (Normais), S (Suspensos) ou C (Cortados)
*/
function retornarTipoConsulta(){
	var index =  $("#divTipoConsulta").children().children(".selected").index();
	var tipoConsulta = "";
	switch (index){
	case 0: tipoConsulta = "N"; // Normais
		break;
	case 1: tipoConsulta = "S"; // Suspensos
		break;
	case 2: tipoConsulta = "C"; // Cortados
		break;
	}
	return tipoConsulta;
}

/*
* Método que idetifica se favorito está selecionado ou não
* @return favorito - retorna S (selecionado) ou N (não está selecionado)
*/
function retornarFavorito(){
	return ($("#favoritos").hasClass("highlight") ? "S" : "N");
}

/*
* Método que idetifica se favorito está selecionado ou não
* @return favorito - retorna S (selecionado) ou N (não está selecionado)
*/
function retornarTop(){
	return ($("#tops").hasClass("highlight") ? "S" : "N");
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

/*
* Método que ordena a lista de clientes de acordo com o tipo de consulta selecinado (Razão Social ou Nome Fantasia)
* @param clientes - lista de clientes para ordenação
* @param tipoRazaoFantasia - tipo Razão ou Fantasia selecionado
* @return clientes - lista de clientes ordenada conforme seleção	
*/
function ordenarPorRazaoFantasia(clientes, tipoRazaoFantasia){
	if(tipoRazaoFantasia == 'C'){ // Código
		function SortClientesCodigo (cliente, clienteAux) {
			return ((cliente.codigoCliente < clienteAux.codigoCliente) ? -1 : ((cliente.codigoCliente > clienteAux.codigoCliente) ? 1 : 0));
		}
		return clientes.sort(SortClientesCodigo);
	}else{ // razão social ou nome fantasia
		function SortClientes (cliente, clienteAux) {
			if(tipoRazaoFantasia == 'R'){
				return ((cliente.nomeCliente < clienteAux.nomeCliente) ? -1 : ((cliente.nomeCliente > clienteAux.nomeCliente) ? 1 : 0));
			}else{
				return ((cliente.nomeFantasia < clienteAux.nomeFantasia) ? -1 : ((cliente.nomeFantasia > clienteAux.nomeFantasia) ? 1 : 0));
			}
		}
		return clientes.sort(SortClientes);
	}
}

/*
* Método que ordena a lista de clientes exibindo primeiro os favoritos
* @param clientes - lista de clientes para ordenação
* @return clientes - lista de clientes ordenada conforme seleção	
*/
function ordenarPorFavorito(clientes){
	var listaFavoritos = new Array();
	var listaNaoFavoritos = new Array(); 
	for(var index=0;index<clientes.length;index++){
		if(clientes[index].indicativoFavorito != null && clientes[index].indicativoFavorito == "1"){
			listaFavoritos.push(clientes[index]);
		}else{
			listaNaoFavoritos.push(clientes[index]);
		}
	}
	for(var index=0;index<listaNaoFavoritos.length;index++){
		listaFavoritos.push(listaNaoFavoritos[index]);
	}
	return listaFavoritos;
}

/*
* Método que ordena a lista de clientes exibindo primeiro os favoritos
* @param clientes - lista de clientes para ordenação
* @return clientes - lista de clientes ordenada conforme seleção	
*/
function ordenarPorTop(clientes){
	var listaTops = new Array();
	var listaNaoTops = new Array(); 
	for(var index=0;index<clientes.length;index++){
		if(clientes[index].indicativoTop != null && clientes[index].indicativoTop == "1"){
			listaTops.push(clientes[index]);
		}else{
			listaNaoTops.push(clientes[index]);
		}
	}
	for(var index=0;index<listaNaoTops.length;index++){
		listaTops.push(listaNaoTops[index]);
	}
	return listaTops;
}

/*
* Método que ordena a lista de clientes exibindo primeiro os favoritos, segundo os clientes tops
* @param clientes - lista de clientes para ordenação
* @return clientes - lista de clientes ordenada conforme seleção	
*/
function ordenarPorFavoritoTop(clientes){
	var listaFavoritos = new Array();
	var listaNaoFavoritos = new Array();
	var listaTops = new Array();
	for(var index=0;index<clientes.length;index++){
		if(clientes[index].indicativoFavorito != null && clientes[index].indicativoFavorito == "1"){
			listaFavoritos.push(clientes[index]);
		}else if(clientes[index].indicativoTop != null && clientes[index].indicativoTop == "1"){
			listaTops.push(clientes[index]);
		}else{
			listaNaoFavoritos.push(clientes[index]);
		}
	}
	for(var index=0;index<listaTops.length;index++){
		listaFavoritos.push(listaTops[index]);
	}
	for(var index=0;index<listaNaoFavoritos.length;index++){
		listaFavoritos.push(listaNaoFavoritos[index]);
	}
	return listaFavoritos;
}

/*
* Método que retorna a lista de clientes de acordo com a letra e tipo de consulta selecionado
* @param letra - letra do alfabeto selecionada
* @param tipoRazaoFantasia - tipo de consulta (Razão Social ou Nome Fantasia)
* @return listaPorLetra - nova lista de acordo com os parâmetros selecionados
*/
function retornarListaPorLetra(listaClientes, letra, tipoRazaoFantasia){
	var listaPorLetra = new Array();
	for(var index=0;index<listaClientes.length;index++){
		if(tipoRazaoFantasia == 'R' || tipoRazaoFantasia == 'C'){
			if(listaClientes[index].nomeCliente[0].toUpperCase() == letra){
				listaPorLetra.push(listaClientes[index]);
			}
		}else{
			if(listaClientes[index].nomeFantasia[0].toUpperCase() == letra){
				listaPorLetra.push(listaClientes[index]);
			}
		}
	}
	return listaPorLetra;
}

/**
* Método que retorna a lista de clientes de acordo com o tipo de consulta selecionado
* @param tipoConsulta - tipo de consulta selecionada (N - Normais, S - Suspensos ou C - Cortados)
* @return lista - lista apenas com o tipo de consula selecionado
*/
function retornarListaPorTipoConsulta(tipoConsulta){
	var listaPorTipoConsulta = new Array();
	for(var index=0;index<jsonClientes.length;index++){
		if(jsonClientes[index].situacao == tipoConsulta){
			listaPorTipoConsulta.push(jsonClientes[index]);
		}
	}
	return listaPorTipoConsulta;
}

/**
* Método para detalhar informações do cliente
* @param codigoCliente - código do cliente selecionado
*/
function detalharCliente(codigoCliente, nomeCliente, canal, situacaoCliente, mensagem, motivoBloqueio, bloqueado, diferencaDias, territorio, tipoContratoVendor, 
		tipoCobrancaCondicaoPagamento, tipoAnaliseCredito, codigoSegmento){
	BRQMob.log("entrando na funcao detalharCliente: codigoCliente = " + codigoCliente + ", nomeCliente = " + nomeCliente);
	
	var msg = verificarMensagem(mensagem,motivoBloqueio,diferencaDias);
	if(bloqueado){
		alert(msg);								
	}else{

		if(msg != ""){
			alert(msg);
		}
		
		if(situacaoCliente != "N" && tipoAnaliseCredito == "B"){
			if(confirm('Este cliente está com o crédito bloqueado no sistema. Os pedidos enviados estarão sujeitos à reprovação automática.\n\nMotivo do bloqueio : ' + motivoBloqueio + '\n\nDeseja continuar o pedido?\n')){
				addSessionStorageClientes(codigoCliente, nomeCliente, canal, territorio, tipoContratoVendor, tipoCobrancaCondicaoPagamento, codigoSegmento);
				sessionStorage.removeObject(CONST_MODELO_DISTRIBUICAO);
				sessionStorage.removeObject(CONST_CONDICAO_PAGAMENTO);
				proximaPagina();
			}
		}else{
			addSessionStorageClientes(codigoCliente, nomeCliente, canal, territorio, tipoContratoVendor, tipoCobrancaCondicaoPagamento, codigoSegmento);
			sessionStorage.removeObject(CONST_MODELO_DISTRIBUICAO);
			sessionStorage.removeObject(CONST_CONDICAO_PAGAMENTO);
			proximaPagina();
		}
	
	}
	
}

function verificarMensagem(mensagem, motivoBloqueio, diferencaDias){
	var msg = "";
	if(mensagem == '0'){
		msg = 'O prazo para a revisão cadastral deste cliente foi esgotado !!! Para fazer o desbloqueio, basta revisar o cadastro do cliente !!! '+ motivoBloqueio;
	}else if(mensagem == '1'){
		msg = 'Hoje é o último dia para a revisão cadastral deste cliente!!! A partir de amanhã, os novos pedidos para ele serão bloqueados!!! '+ motivoBloqueio;
	}else if(mensagem == '2'){
		msg = 'O prazo para a revisão cadastral deste cliente é de ' + diferencaDias + ' dia(s)!!! Após este prazo, os novos pedidos para ele serão bloqueados!!! '+ motivoBloqueio;
	}
	return msg;
}

/**
* Método para armazenamento do local storage o último estado da tela
* @param codigoCliente - código do cliente selecionado
*/
function addSessionStorageClientes(codigoCliente, nomeCliente, canal,territorio, tipoContratoVendor, tipoCobrancaCondicaoPagamento, codigoSegmento) {
	BRQMob.log("entrando na funcao addSessionStorageClientes");
	 
	//Removendo item anterior
	sessionStorage.removeItem("cliente");
	
	//Armazenar o estado da tela
	var objStorageClientes = new Object();
	
	//Código do cliente selecionado para detalhamento
	objStorageClientes.codigoCliente = codigoCliente;
	
	//Nome do cliente selecionado para detalhamento
	objStorageClientes.nomeCliente = nomeCliente;
	
	//Canal do cliente selecionado para detalhamento
	objStorageClientes.canal = canal;
	
	objStorageClientes.codigoTerritorio = territorio;
	
	//Verifica se exibe Código cliente - 0, razão social - 1 ou nome fantasia - 2
	objStorageClientes.indexRazaoFantasia = $("#divRazaoFantasia").children().children(".selected").index();
	
	//Verifica seleção de Favoritos
	objStorageClientes.indicativoFavorito = retornarFavorito();
	
	//Verifica seleção de Tops
	objStorageClientes.indicativoTop = retornarTop();
	
	objStorageClientes.tipoContratoVendor = tipoContratoVendor;
	
	objStorageClientes.tipoCobrancaCondicaoPagamento = tipoCobrancaCondicaoPagamento;
	
	//Verifica seleção de Tops
	objStorageClientes.codigoSegmento = codigoSegmento;

	//Armazena o objeto json retornado na consulta
	objStorageClientes.clientes = jsonClientes;
	
	//Verifica a seleção de letra selecionada
	objStorageClientes.indexOrdenacao = $("#clientFilter").children().children(".selected").index();
	
	//Index do botão tipo de consulta (0 - Normais, 1 - Suspensos ou 2 - Cortados)
	objStorageClientes.indexTipoConsulta = $("#divTipoConsulta").children().children(".selected").index();
	
	sessionStorage.setObject(CONST_CLIENTE, objStorageClientes);
}

/**
* Método para recarregar o conteúdo da página após detalhamento de dados do cliente
* @param objStorageClientes - objeto armazenado no local storage contendo dados do último estado da tela
*/
function recarregarTela(objStorageClientes){
	BRQMob.log("entrando na funcao recarregarTela");

	jsonClientes = objStorageClientes.clientes;
	
	//Selecionar Botão Código o cliente, Razão social ou Nome fantasia
 	$("#divRazaoFantasia").children().children().eq(objStorageClientes.indexRazaoFantasia).addClass("selected");

	//Verifica seleção de Favoritos
 	if(objStorageClientes.indicativoFavorito == "S"){
 		$("#favoritos").addClass("highlight");
 	}
	//Verifica seleção de Tops
 	if(objStorageClientes.indicativoTop == "S"){
 		$("#tops").addClass("highlight");
 	}

	//Selecionar letra de ordenação
	if(objStorageClientes.indexOrdenacao > -1){
		$("#clientFilter").children().children().eq(objStorageClientes.indexOrdenacao).addClass("selected");
	}
	
	//Selecionar o tipo de consulta (Normais - 0, Suspensos - 1 ou cortados - 2)
	$("#divTipoConsulta").children().children().eq(objStorageClientes.indexTipoConsulta).addClass("selected");
	if(objStorageClientes.indexTipoConsulta!='0'){
		$("#divTipoConsulta").children().children().eq(0).removeClass("selected");
	}
	
 	//Montar a lista de clientes
	montarListaClientes();
 	
	//Limpar sessionStorage
	sessionStorage.removeItem("cliente");
	
}

/**
* Método para chamar a tela de detalhe do cliente
*/
function proximaPagina() {
	window.location.href = getUrlPagina('clienteDetalhe');
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