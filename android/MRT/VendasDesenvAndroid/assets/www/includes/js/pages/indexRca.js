$(document).ready(
	function(){
		BRQNavegacao.adicionarPilhaNavegacao('inicialRca');
		setInterval("setDataHoraAtual()", 60000);
		sessionStorage.setObject(CONST_PAGINA_ORIGEM, 'indexRca.html');
		carregarDadosIniciais();
		
		//setTimeout("exit();",3000);
		
	}
);
 
function carregarDadosIniciais(){
	
	var codigoRepresentante = null, codigoTerritorio = null;
	
	var representanteEquipe = getRepresentanteEquipe();
	if (representanteEquipe != null){
		codigoRepresentante = representanteEquipe.codigoRepresentante;
		codigoTerritorio = representanteEquipe.codigoTerritorio;
	}

	BRQMob.log("entrando na funcao carregar dados iniciais");
	BRQMob.exec("sucesso", "falha", "MartinsDispatcher", "inicial", [ codigoRepresentante, codigoTerritorio ]);			
}

function sucesso(retorno){
	try {
		setDadosSistema(retorno.inicial.sistema);
		setDadosRepresentante(retorno.inicial.representante);
		setDadosComerciaisRepresentante(retorno.inicial.dadosComerciaisRepresentante);
		setDadosAvisos(retorno.inicial.avisosCliente,  retorno.inicial.avisosPedido);
	} catch (err) {
		alert('[sucesso()] - '.concat(err.message));
	}
}

function falha(retorno){
	BRQMob.log("falha dados iniciais: " + retorno);	 
}

function setDadosSistema(sistema){
	try {
		sessionStorage.setObject(CONST_SISTEMA, sistema);
		setDataHoraAtual();
		$("#versao").text(sistema.versao); 
		$("#ultimaSincronizacao").text(sistema.ultimaSincronizacao);
	} catch (err) {
		alert('[setDadosSistema()] - '.concat(err.message));
	}
}

function setDataHoraAtual(){
	try {
		var hoje = new Date();
		var data = hoje.format("dd/mm/yyyy");
		var hora = hoje.format("HH:MM");
		$("#dataAtual").text(data); 
		$("#horaAtual").text(hora);
	} catch (err) {
		alert('[setDataHoraAtual()] - '.concat(err.message));
	}
}

function setDadosRepresentante(representante){
	try {
		sessionStorage.setObject(CONST_REPRESENTANTE, representante);
		$("#codigoRepresentante").text(representante.codigoRepresentante); 
		$("#nomeRepresentante").text(representante.nomeRepresentante);
	} catch (err) {
		alert('[setDadosRepresentante()] - '.concat(err.message));
	}
}

function setDadosComerciaisRepresentante(comerciaisRepresentante){
	try {
		sessionStorage.setObject(CONST_DADOS_COMERCIAIS_REPRESENTANTE, comerciaisRepresentante);
		$("#previsaoSemanal").text(comerciaisRepresentante.previsaoSemanal);
	} catch (err) {
		alert('[setDadosComerciaisRepresentante()] - '.concat(err.message));
	}
}

function setDadosAvisos(avisosCliente, avisosPedido) {
	try {
		//cliente
		sessionStorage.setObject(CONST_AVISOS_CLIENTE, avisosCliente);
		$("#quantidadeAvisosClientes").text(avisosCliente.length);
		//pedido
		//sessionStorage.setObject(CONST_AVISOS_PEDIDO, avisosPedido);
		//$("#quantidadeAvisosPedidos").text(avisosPedido.length);
	} catch (err) {
		alert('[setDadosAvisos()] - '.concat(err.message));
	}
}

//	function exit(){
//			BRQMob.exec(null, null, "App", "exitApp", []);	
//	}	
		

function closeApp() {
	BRQMob.exec(null, null, "App", "recreateApp", ['br.com.martins.vendas.action.RECARREGAR', 'br.com.martins.vendas.action.ATUALIZAR']);
}