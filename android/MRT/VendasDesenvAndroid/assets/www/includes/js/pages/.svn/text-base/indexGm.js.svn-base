$(document).ready(
	function(){

		BRQNavegacao.adicionarPilhaNavegacao('inicialGm');
		
		$('#botao_ok').click(function() { 
			validaCamposSelecionados();
		});
		
		controleBotoes();
		
		execListarRCA();
		
	}
);

function validaCamposSelecionados(){
	
	var codigoRca = document.getElementById('rca').value;
	var codigoTerritorio = document.getElementById('territorio').value;
	
	if(codigoRca != "" && codigoTerritorio != ""){

		limparSessionStorage();
		
		var representanteEquipe = new Object();
		representanteEquipe.codigoRepresentante = codigoRca;
		representanteEquipe.codigoTerritorio = codigoTerritorio;
		sessionStorage.setObject(CONST_REPRESENTANTE_EQUIPE, representanteEquipe);
		
		window.location.href = "indexRCA.html";
	
	}else{
		alert("Informe o RCA e Território.")
	}
	
}

function limparSessionStorage(){
	sessionStorage.clear();
}

function execListarRCA(){
	BRQMob.log("entrando na funcao listar rca");
  	BRQMob.exec("sucessoRepresentante", "falha", "MartinsDispatcher", "listarRca", []);			
}

function sucessoRepresentante(retorno){
	BRQMob.log("Lendo dados sucessoRepresentante");
	if(retorno != null){
		montaComboboxRca(retorno.Representantes);
	}
}

function montaComboboxRca(representantes){
	if(representantes != undefined){
		var comboboxRca = document.getElementById("rca");
		for (var index=0; index < representantes.length; index++) {
			criaOptions(comboboxRca, representantes[index].codigoRepresentante, representantes[index].codigoRepresentante.concat(" - ").concat(representantes[index].nomeRepresentante));
	    }
	}else{
		alert("Não há Equipe para este Gerente de Mercado !!!");
		habilitaCombobox('rca', false);
	}
}

function criaOptions(combobox, valor, texto){
	var option = document.createElement('option');
	option.setAttribute('value', valor);
	option.appendChild(document.createTextNode(texto));
	combobox.appendChild(option);
}

function listaTerritorios(codigoRepresentante){
	removeMensagemSucessoErro();
	if(codigoRepresentante != ""){
		BRQMob.log("entrando na funcao listar territorios");
	  	BRQMob.exec("sucessoTerritorio", "falha", "MartinsDispatcher", "listarTerritorios", [ codigoRepresentante ]);	
	}else{
		removeOptions('territorio');
		habilitaCombobox('territorio', false);
	}
}

function habilitaCombobox(id, isHabilitar){
	var combobox = document.getElementById(id);
	if(isHabilitar){
		combobox.disabled = false;
	}else{
		combobox.disabled = true;
	}
}

function sucessoTerritorio(retorno){
	BRQMob.log("Lendo dados sucessoTerritorio");
	if(retorno != null){
		montaComboboxTerritorios(retorno.RelacaoGiros);
	}
}

function montaComboboxTerritorios(relacaoGiros){
	removeOptions('territorio');
	if(relacaoGiros != undefined){
		var combobox = document.getElementById("territorio");
		for (var index=0; index < relacaoGiros.length; index++) {
			criaOptions(combobox, relacaoGiros[index].codTerritorio, relacaoGiros[index].codTerritorio);
	    }
		habilitaCombobox('territorio', true);
	}else{
		alert("Não há Territórios disponíveis para este RCA !!!");
		habilitaCombobox('territorio', false);
	}
}

function removeOptions(id) {
	var combobox = document.getElementById(id);
	for(var i = combobox.length-1; i > 0; i--){
		combobox.remove(i);
	}
}

function falha(result){
	BRQMob.log("falha: " + result);
	apresentaMensagemErro(result, true);
}

function controleBotoes(){
	var representanteEquipe = getRepresentanteEquipe();
	if(representanteEquipe == null ){
		$('#menuButton').empty().remove();
	}
}