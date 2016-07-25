var BRQNavegacao = {
		
}
BRQNavegacao.adicionarPilhaNavegacao = function(itemNavegacao){
	var pilhaNavegacao;
	if(!getPilhaNavegacao()){
		pilhaNavegacao = [itemNavegacao];
		setPilhaNavegacao(pilhaNavegacao);
	}else{
		pilhaNavegacao =  getPilhaNavegacao();
		if(!existsUltimaPosicao(pilhaNavegacao, itemNavegacao)){
			pilhaNavegacao.push(itemNavegacao);
			setPilhaNavegacao(pilhaNavegacao);
		}
	}
}


function isNavegacaoEmpty(){
	return !getPilhaNavegacao() ? true : false;
}

function exists(pilhaNavegacao, itemNavegacao){
	for(i = 0; i < pilhaNavegacao.length; i++){
		if(pilhaNavegacao[i] == itemNavegacao){
			return true;
		}
	}
	return false;
}

function existsUltimaPosicao(pilhaNavegacao, itemNavegacao){
		if(pilhaNavegacao[getNumElementos()-1] == itemNavegacao){
			return true;
		}
	return false;
}

function removerPilhaNavegacao(){
	var pilhaNavegacao =  getPilhaNavegacao();
	pilhaNavegacao.pop();
	setPilhaNavegacao(pilhaNavegacao);
}

function getNumElementos(){
	return getPilhaNavegacao().length;
}

function getPilhaNavegacao(){
	if(!sessionStorage.pilhaNavegacao){
		return false;
	}
	pilha = sessionStorage.pilhaNavegacao;
	pilha = JSON.parse(pilha);	
	return pilha;
}

function getViewNameOrigem(){
	if(getPilhaNavegacao().length < 2){
		return '';
	}
	return getElement(getPilhaNavegacao().length - 2);
}

function getUrlPagina(viewName){
	return getPage(viewName);
}

function getUrlPaginaOrigem(){
	return getUrlPagina(getViewNameOrigem());
}

BRQNavegacao.voltar = function(){
	paginaRetorno = getPage(getViewNameOrigem());
	removerPilhaNavegacao();	
	window.location.href = paginaRetorno;
}

function getElement(i){		
	var pilhaNavegacao = getPilhaNavegacao();
	return pilhaNavegacao[i];
}