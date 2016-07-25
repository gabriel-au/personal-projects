/* Variável de instância utilizada para armazenamento do código da justificativa selecionada */
var codigoJustificativaSelecionado = null;

/*
* Executado ao iniciar a tela
*/
$(document).ready(function() {
	
	$("#justificar").click(
		function() {
			cadastrarJustificativa();
		}
	);
	
	var objStorageClientes = getCliente();
	
	execJustificativaCliente(objStorageClientes.codigoCliente);

});

/**
* Método para recuperar os dados para carregamento da tela
* @param codigoCliente - código do cliente
*/
function execJustificativaCliente(codigoCliente) {
	BRQMob.log("Lendo dados execJustificativaCliente");
	BRQMob.exec("retornoJustificativaCliente", "falha", "RelacionamentoDispatcher", "justificativaAtendimento", [ codigoCliente ]);
}

/**
* Método para montar a lista de justificativas e carregar dados da última justificativa
* @param retorno - lista de clientes retornados da action
*/
function retornoJustificativaCliente(retorno){
	BRQMob.log("Lendo dados retornoJustificativaCliente");
	
	if(retorno.Justificativas != null){
		
		var objLista = inicializarLista();
		
		//Montando a lista de justificativas
		if(retorno.Justificativas.length > 0){
			for(var index=0;index<retorno.Justificativas.length;index++){
				montarLinha(retorno.Justificativas[index], objLista);
			}
			
			//Listas selecionaveis
			$("ul.selectable").each(function() {
				$(this).children().each(function() {
					$(this).click(function() {
						listSelect(this);
					})
				})
			});
			
			//Montagem de Campos texto
			$(".type").each(function() {
				createTypeInput(this);
			});
			
		}else{
			//Exibe a mensagem que não existe justificativas cadastradas
			objLista.html("Não há cadastro de Justificativas!");
		}

	}
}

/**
* Método que monta a linha com as justificativas cadastradas
* @param justifivativa - objeto justificativa
* @param objLista - objeto onde a linha será adicionada
*/
function montarLinha(justifivativa, objLista){
	var html = "<li  onclick='setarJustificativa("+ justifivativa.codigoJustificativa + ")'>";
		html +=    "<span class='icon aplicado'></span>";
    	html +=    "<div class='left'><label>"+ justifivativa.descricaoJustificativa +"</label></div>";
    	if(justifivativa.codigoJustificativa == 9999){
    	   html += "<div class='right'>";
    	   html += 		"<input type='text' class='type' size='50' maxlength='100' placeholder='Descrição' id='descricaoJustificativa' />";
    	   html += "</div>";
    	}
    	html += "</li>";

    objLista.append(html);
}

/*
* Método que cria e inicializa o objeto para montagem da lista de clientes
* @return objLista - objeto: <ul></ul>
*/
function inicializarLista(){
	var objLista = $("#ulJustificativas");
	objLista.html("");
	return objLista;
}

/**
* Método que guarda o valor da seleção da lista e limpa a descrição caso não seja OUTROS
*@param codigoJustificativa - código da justificativa selecionada na lista
*/
function setarJustificativa(codigoJustificativa){
	codigoJustificativaSelecionado = codigoJustificativa;
	if(codigoJustificativa != 9999){
		$('#descricaoJustificativa').val('');
	}
}

/**
* Método para cadastrar ou atualizar a justificativa de não atendimento ao cliente
*/
function cadastrarJustificativa(){
	
	if(codigoJustificativaSelecionado != null){
		var objStorageClientes = getCliente();
		var descricaoJustificativa = "";
		if(codigoJustificativaSelecionado == 9999){
			descricaoJustificativa = $('#descricaoJustificativa').val().trim();
			if(descricaoJustificativa == ""){
				alert("Favor preencher justificativa!");
				$('#descricaoJustificativa').val(descricaoJustificativa);
				return false;
			}
		}
		
		execCadastrarJustificativaCliente(objStorageClientes.codigoCliente, codigoJustificativaSelecionado, descricaoJustificativa);
	}else{
		//Mensagem que não item selecionadotem seleção (Cópia do sistema atual)
		alert("Não tem item selecionado.");
	}
}

/**
* Método para cadastrar ou atualizar a justificativa do cliente
* @param codigoCliente - código do cliente
*/
function execCadastrarJustificativaCliente(codigoCliente, codigoJustificativa, descricaoJustificativa) {
	BRQMob.log("Lendo dados execCadastrarJustificativaCliente");
	BRQMob.exec("retornoCadastrarJustificativaCliente", "falha", "RelacionamentoDispatcher", "cadastrarJustificativaAtendimento", [ codigoCliente, codigoJustificativaSelecionado, descricaoJustificativa ]);
}

/**
* Retorno de sucesso do cadastro de justificativa
* Direciona para a tela de informações do cliente
*/
function retornoCadastrarJustificativaCliente(retorno){
	BRQMob.log("retornoCadastrarJustificativaCliente ");
	retornarDetalheCliente(true);
}

/**
* Método para tratamento de falha no retorno das consultas
* @param result - mensagem de falha
*/
function falha(result){
	BRQMob.log("falha: " + result);
	retornarDetalheCliente(false);
}

/**
* Método que adiciona o retorno de sucesso ou falha no sessionstorage e retorna para a tela de Detalhe do Cliente
*/
function retornarDetalheCliente(valor){
	var objStorageJustificativa = new Object();
	objStorageJustificativa.isCadastroSucesso = valor;
	sessionStorage.setObject('objStorageJustificativa', objStorageJustificativa);
	
	window.location.href = "clienteDetalhe.html";
}