var FLAG_MARTINS_FALSE								 = 0;
var FLAG_MARTINS_TRUE								 = 1;

var CONST_REPRESENTANTE_EQUIPE					     = 'representanteEquipe';
var CONST_DADOS_PRODUTO_SELECIONADO					 = 'dadosProdutoSelecionado';
var CONST_REPRESENTANTE								 = 'representante';

var CONST_FILIAL_EXPEDICAO_DEFAULT 					 = 'filialExpedicaoDefault';
var CONST_FILIAL_FATURAMENTO_DEFAULT 				 = 'filialFaturamentoDefault';
var CONST_PEDIDO 									 = 'pedido';
var CONST_AVISOS_CLIENTE 							 = 'avisosCliente';
var CONST_AVISOS_PEDIDO								 = 'avisosPedido';
var CONST_SISTEMA 									 = 'sistema';
var CONST_CAMINHAO 									 = 'caminhao';
var CONST_DADOS_COMERCIAIS_REPRESENTANTE 			 = 'dadosComerciaisRepresentante';
var CONST_PAGINA_ORIGEM 						 	 = 'paginaOrigem';
var CONST_CONDICAO_PAGAMENTO 						 = 'condicaoPagamento';
var CONST_MODELO_DISTRIBUICAO 						 = 'modeloDistribuicao';
var CONST_CLIENTE 									 = 'cliente';
var CONST_MERCADORIA								 = 'mercadoria';
var CONST_PEDIDO_EMAIL								 = 'pedidoEmail';
var CONST_FILIAL_FATURAMENTO						 = 'filialFaturamento';
var CONST_FILIAL_EXPEDICAO							 = 'filialExpedicao';
var CONST_NAVEGACAO									 = 'navegacao';

var CONST_LISTA_FILIAIS								 = 'listaFiliaisDisponiveis';

var	CONST_ITEM_SIMILAR							     = 'item-similar';
var CONST_ITEM_GONDOLA 								 = 'item-gondola';
var CONST_ITEM_CONDICAO_PAGAMENTO 				     = 'item-condicao-pagamento';
var CONST_ITEM_DISPONIVEL_RECUPERA_PESQUISA_ANTERIOR = 'item-recupera-pesq-ant';
var CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR = 'item-promocao-recupera-pesq-ant';
var CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR = 'item-promocao-premio-recupera-pesq-ant';
var CONST_ITEM_DISPONIVEL_PROMOCAO_CODIGO_BUSCA = 'item-promocao-codigo-busca';
var CONST_PRIMEIRA_PESQUISA						='primeiraPesquisa';
var CONST_PESQUISA_BY_CLIENTE						='pesquisaByCliente';

function getPesquisaByCliente(){
	return sessionStorage.getObject(CONST_PESQUISA_BY_CLIENTE);
}
function getPrimeiraPesquisa(){
	return sessionStorage.getObject(CONST_PRIMEIRA_PESQUISA);
}

function isGerenteMercado(){
	return getRepresentante().isGerenteMercado;
}

function getRepresentanteEquipe(){
	return sessionStorage.getObject(CONST_REPRESENTANTE_EQUIPE);
}

function getDadosProdutoSelecionado(){
	return sessionStorage.getObject(CONST_DADOS_PRODUTO_SELECIONADO);
}

function getRepresentante(){
	return sessionStorage.getObject(CONST_REPRESENTANTE);
}
/*
 * PARECE Ñ ESTAR SENDO UTILIZADO
 */
function getPeriodoInicial(){
	return sessionStorage.getObject(CONST_PERIODO_INICIAL);
}
/*
 * PARECE Ñ ESTAR SENDO UTILIZADO
 */
function getPeriodoFinal(){
	return sessionStorage.getObject(CONST_PERIODO_FINAL);
}

function getFilialExpedicaoDefault(){
	return sessionStorage.getObject(CONST_FILIAL_EXPEDICAO_DEFAULT);
}

function getFilialFaturamentoDefault(){
	return sessionStorage.getObject(CONST_FILIAL_FATURAMENTO_DEFAULT);
}

function getPedido(){
	return sessionStorage.getObject(CONST_PEDIDO);
}

function getAvisosCliente(){
	return sessionStorage.getObject(CONST_AVISOS_CLIENTE);
}

function getAvisosPedido(){
	return sessionStorage.getObject(CONST_AVISOS_PEDIDO);
}

function getSistema(){
	return sessionStorage.getObject(CONST_SISTEMA);
}

function getCaminhao(){
	return sessionStorage.getObject(CONST_CAMINHAO);
}

function getDadosComerciaisRepresentante(){
	return sessionStorage.getObject(CONST_DADOS_COMERCIAIS_REPRESENTANTE);
}

function getPaginaOrigem(){
	return sessionStorage.getObject(CONST_PAGINA_ORIGEM);
}

function getCondicaoPagamento(){
	return sessionStorage.getObject(CONST_CONDICAO_PAGAMENTO);
}

function getModeloDistribuicao(){
	return sessionStorage.getObject(CONST_MODELO_DISTRIBUICAO);
}

function getCliente(){
	return sessionStorage.getObject(CONST_CLIENTE);
}


function getMercadoria(){
	return sessionStorage.getObject(CONST_MERCADORIA);
}
function getPedidoEmail(){
	return sessionStorage.getObject(CONST_PEDIDO_EMAIL);
}

function getFilialExpedicao(){
	return sessionStorage.getObject(CONST_FILIAL_EXPEDICAO);
}

function getFilialFaturamento(){
	return sessionStorage.getObject(CONST_FILIAL_FATURAMENTO);
}

function getFiliaisDisponiveis(){
	return sessionStorage.getObject(CONST_LISTA_FILIAIS);
}


function getRepresentanteEquipe(){
	return sessionStorage.getObject(CONST_REPRESENTANTE_EQUIPE);
}


function getNavegacao(){
	return sessionStorage.getObject(CONST_NAVEGACAO);
}


function criaColuna(objetoTR, textoColuna, conteudoColuna){
	if(!parseInt(textoColuna)){
		var coluna = $('<td/>', {style: "text-align:left;", text: (conteudoColuna ? "" : textoColuna)});
	}else{
		var coluna = $('<td/>', {style: "text-align:center;", text: (conteudoColuna ? "" : textoColuna)});
	}
	if(conteudoColuna){
		coluna = $('<td/>', {style: "text-align:center;"});
		$(conteudoColuna).appendTo(coluna);
	}
	$(coluna).appendTo(objetoTR);
}

function getConteudoColuna(objetoTR, indexColuna){
	return objetoTR.children(':eq('+indexColuna+')').text();
}

function voltarPaginaOrigem(){
	window.location.href = getPaginaOrigem();
}

/*
 * validação de email
 * @param email, email que será validado
 * @return true em caso positivo e false caso contrário
 */
function isEmailValido(email) {
	var regex = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;    
    return email.match(regex) == null ? false : true;    
}

/*
 * apresenta mensagem no topo da tela
 * 
 * @param 	tipo 	sucess, error 
 * @param	msg		mensagem que será apresenta
 * @param 	reset	indica se as mensagens anteriores devem ser ou não removidas [true, false]	
 */
function apresentaMensagem(tipo, msg, reset) {
	
	if (reset) {
		removeMensagemSucessoErro();
	}
	
	var   div   = $('<div/>'  ,   {'class': 'panel paddedGrey', 'style':'height:36px;position:absolute;z-index:99;overflow:hidden;opacity:0.9', 'onclick':'removeMensagemSucessoErro();'});
	var    ul   = $('<ul/>'   ,   {});
	var    li   = $('<li/>'   ,   {'class': tipo});
	var  span   = $('<span/>' ,   {'class':'icon'});
	var label   = $('<label/>',   {'text' : msg});
		
	var olddiv  = $('#mainContent').find('div.panel.paddedGrey');
	if ($(olddiv).html() != null) {
		//
		var _ul = $(olddiv).find('ul');
		$(_ul).append(li);
		$(li).append(span);
		$(li).append(label);
		//
	} else {
		//
		$(div).append(ul);
		$(ul).append(li);
		$(li).append(span);
		$(li).append(label);
		//
		$('#mainContent').prepend(div);
	}
}

/*
 * apresenta mensagem de sucesso no topo da tela
 * 
 * @param	msg		mensagem que será apresenta
 * @param 	reset	indica se as mensagens anteriores devem ser ou não removidas [true, false]	
 */
function apresentaMensagemSucesso(msg, reset) {
	apresentaMensagem('sucess', msg, reset);
}

/*
 * apresenta mensagem de erro no topo da tela
 * 
 * @param	msg		mensagem que será apresenta
 * @param 	reset	indica se as mensagens anteriores devem ser ou não removidas [true, false]	
 */
function apresentaMensagemErro(msg, reset) {
	apresentaMensagem('error', msg, reset);

}

/*
 * Remove mensagem de sucesso ou erro
 */
function removeMensagemSucessoErro(){
	$('#mainContent').find('div.panel.paddedGrey').remove();
}

/*
 * Adiciona Option Node em Combobox
 */
function getOptionNode(value, text){
	try {
	    var option = document.createElement('option');
	    option.setAttribute('value', value);
	    option.textContent = text;
	    return option;
	} catch (err) {
		alert('[appendOptionNode(node)]'.concat(err.message));
	}
}
/*
 * Remove todos os elementos de filhos de um nó html
 */
function removeAll(node){
	try {
		var element = typeof(node) == 'string' ? document.getElementById(node) : node;
	    var count   = element.children.length - 1;
	    while (count >= 0) {
	    	element.removeChild(element.children[count]);
	        count--;
	    }
	} catch (err) {
		alert('[removeAll(node)]'.concat(err.message));
	}
}

function bloqueiAlfa(event){
	var keyCode = event.which;
	if (keyCode >= 48 && keyCode <= 57){
		return true;
	}else{
		return false;
	}
} 

function bloqueiCtrlCV(event, objeto){
	event.preventDefault();
	var expre = /[^\d]/g;
	// REMOVE OS CARACTERES DA EXPRESSAO ACIMA
	if ($(objeto).val().match(expre)){
		$(objeto).val($(objeto).val().replace(expre,''));
	}
}

/**
* Método que direciona para o topo da tela após cada consulta
*/
function goTop(){
	window.scrollTo(0,0);

}

function formatarDecimal(valor, precisao){
	var decimal = tratamentoAtributoNulo(valor);
	if(decimal != ""){
		decimal = parseFloat(decimal).toFixed(precisao);
	}
	return decimal.replace('.',',');
}

function tratamentoAtributoNulo(valor){
	if(valor != undefined){
		return valor;
	}else{
		return "";
	}
}

function parseDecimalStringToFloat(valor){
	return parseFloat(valor.replace(',','.'));

}

/**
* Método que faz o arredondamento para 2 casas decimais
*/
function precisaoDecimal(valor){
	return parseFloat(Math.round(valor * 100) / 100).toFixed(2);
}