$(document).ready(function(){

	BRQNavegacao.adicionarPilhaNavegacao('informacoesGerais');
	
	recuperaValoresInfo();
	
	var cliente = getCliente();
		
	/*
	 * Foi identificado na funcionalidade nativa que são passados os seguintes valores para a tela de informações gerais
	 */
	var clienteId			= cliente.codigoCliente;
	var terriorioId			= cliente.codigoTerritorio;
	var segmentoId			= cliente.codigoSegmento;
	var primeiraPesquisa	= true;
	var origem				= getViewNameOrigem();
	
	if(getPrimeiraPesquisa() != null) {
		primeiraPesquisa = getPrimeiraPesquisa();			
	}
	
	var numeroPedido = sessionStorage.getObject('numeroPedido');
	
	$('#a_bt_ok').click(function() {
		if(isPedidoValido()){
			montaPedidoBase();
			//nav(getUrlPagina('infoPedido'));
			BRQNavegacao.voltar();
		} 
	});
	
	$('#li_cond_pagto').click(function() {
		nav(getPage('condPagamento'));
	});
	
	$('#span_numero_pedido').text("Pedido Nº " +  numeroPedido); 
	
	$('#li_modelo_dist').click(function() {
		nav(getPage('modeloDistribuicao')); 
	});
	
	$('#span_codigo_territorio').text(terriorioId);		

	// Valor Padrao
	setTipoPedidoPedidoBase("P");
	
	$('#btn_cotacao').click(function() {
		setTipoPedidoPedidoBase("C");
	});
	
	$('#btn_pedido').click(function() {
		setTipoPedidoPedidoBase("P");
	});
	
	BRQMob.exec("sucesso", "falha", "VendasDispatcher", "informacoesGerais", [clienteId, terriorioId, segmentoId, numeroPedido, primeiraPesquisa,origem]);		
	sessionStorage.setObject(CONST_PRIMEIRA_PESQUISA, false);
});

function setaFilialDefault(retorno) {
	var filiaisClienteExpDefault = new Object();
	var filiaisClienteFatDefault  = new Object();
	
	filiaisClienteExpDefault.codigoFilial	= retorno.filialExpedicao.codigoFilial;
	filiaisClienteExpDefault.nomeFilial		= retorno.filialExpedicao.nomeFilial;
	filiaisClienteFatDefault.codigoFilial	= retorno.filialFaturamento.codigoFilial;
	filiaisClienteFatDefault.nomeFilial		= retorno.filialFaturamento.nomeFilial;	
		
	sessionStorage.setObject(CONST_FILIAL_EXPEDICAO_DEFAULT, filiaisClienteExpDefault);
	sessionStorage.setObject(CONST_FILIAL_FATURAMENTO_DEFAULT, filiaisClienteFatDefault);
	
}

//Bloquear alteração do tipo de pedido a partir do momento que é troca de Aba
function bloquearBotaoTipoPedido() {

	bloquiarAlteracaoTipoVenda();
	
	//Recuperar o objeto info no storage para armazenar que não pode alterar mais opção de tipo de pedido
	bloqueiaBotaoTipoPedido = true;
	
	//Recupera  pedido no storage e bloqueia
	mantemValoresInfo();
	
}

function isPedidoValido(){
	if(!getCondicaoPagamento()){
		alert('A escolha da condição de pagamento principal é obrigatória.');
		return false;
	}
	if(!getModeloDistribuicao()){
		alert('A escolha do modelo de distribuição é obrigatória.');
		return false;
	}
	return true;
}

/*
function recuperaValoresInfo(){
	if(!isPedidoBaseEmpty()){
		var info = getPedidoBase().info;
		$("#mensagemNota").val(info.mensagemNota);
		if(info.tipoPedido == 'normal'){
			$("#li_normal").addClass("selected");
			$("#li_simplificado").removeClass("selected");
		}else{
			$("#li_simplificado").addClass("selected");
			$("#li_normal").removeClass("selected");
		}
	}
}
*/

//Recarregar tela
function recuperaValoresInfo(){
	if(!isPedidoBaseEmpty()){
		var info = getPedidoBase().info;
		$("#mensagemNota").val(info.mensagemNota);
		//infoTipoPedido = info.tipoPedido;
	}
}

function tratamentoTipoPedido(isPedidoExiste, tipoVendaPedido){
	//Adicionar a regra para quando for alteração de Pedido quando o RCA já abandonou o pedido.
	//Não poderá alterar o tipo de pedido independente do tipo de venda do RCA.
	if(!isPedidoExiste){
		
		//Verifica se tem objeto pedido no storage (alteração de pedido através da navegação das abas, onde o pedido ainda não foi abandonado.)
		var infoTipoPedido = '';
		var bloquiarBotaoTipoPedido = false;
		
		var pedidoBaseInfo = getPedidoBaseInfo();
		
		if(pedidoBaseInfo != undefined){
			infoTipoPedido = pedidoBaseInfo.tipoPedido;
			bloquiarBotaoTipoPedido = pedidoBaseInfo.bloqueiaBotaoTipoPedido;
			
			if(bloquiarBotaoTipoPedido){
				bloqueiaBotaoTipoPedido = true;
			}

		}
		
		var representante = getRepresentante();
		if (representante.tipoVenda == '' || representante.tipoVenda == "NORMAL") {
			setTipoVenda('normal');
			bloquiarAlteracaoTipoVenda();
				
		 } else if (representante.tipoVenda == "MISTA") {
			 
			if (infoTipoPedido == '' || infoTipoPedido == 'normal') {
				setTipoVenda('normal');

			} else {
				setTipoVenda('simplificado');

			}
			 
			if (!bloquiarBotaoTipoPedido) {
				$('#btn_normal').mousedown(function() {
					setTipoVenda('normal');
				});
				
				$('#btn_simplificado').mousedown(function() {
					setTipoVenda('simplificado');
				});
				
			} else {
				bloquiarAlteracaoTipoVenda();

			}
			
		} else {
			setTipoVenda('simplificado');
			bloquiarAlteracaoTipoVenda();

		}

	} else {
		//Alteração do pedido o campo é bloqueado para alteração
		if (tipoVendaPedido == 'NORMAL') {
			setTipoVenda('normal');

		} else {
			setTipoVenda('simplificado');

		}
		
		bloquiarAlteracaoTipoVenda();
		
	}
	
}

function bloquiarAlteracaoTipoVenda(){
	$("#btn_normal").unbind('mousedown');
	$("#btn_normal").addClass('btnBloqueado');
	$("#btn_simplificado").unbind('mousedown');
	$("#btn_simplificado").addClass('btnBloqueado');
}

function setTipoVenda(tipoVenda){
	if(tipoVenda == 'normal'){
		$("#li_simplificado").removeClass("selected");
		$("#li_normal").addClass("selected");
		setTipoPedido('normal');
	}else {
		$("#li_normal").removeClass("selected");
		$("#li_simplificado").addClass("selected");
		setTipoPedido('simplificado');
	}
}

function mantemValoresInfo(){
	setInfoPedidoBase(montaInfoPedidoBase());
}

function montaPedidoBase(){
	resetPedidoBase();
	setClientePedidoBase(getCliente());
	setCondicaoPagamentoPedidoBase(getCondicaoPagamento());
	setModeloDistribuicaoPedidoBase(getModeloDistribuicao());
	setInfoPedidoBase(montaInfoPedidoBase());
	setFilialFaturamentoPedidoBase(getFilialFaturamento());
	setFilialExpedicaoPedidoBase(getFilialExpedicao());
}

var bloqueiaBotaoTipoPedido = false;
function montaInfoPedidoBase(){
	var infoPedidoBase = new Object();
	infoPedidoBase.mensagemNota = $("#mensagemNota").val();
	infoPedidoBase.tipoPedido = tipoPedido;
	infoPedidoBase.bloqueiaBotaoTipoPedido = bloqueiaBotaoTipoPedido;
	return infoPedidoBase;
}

var tipoPedido = '';
function setTipoPedido(tipoPedidoSelecionado){
	tipoPedido = tipoPedidoSelecionado;
	//verificarLink();
	mantemValoresInfo();
}
		
falha = function (result) {
	BRQMob.log("falha: " + result);
	$("#mensagem").html(result);
	$("#divMensagem").css('display', 'inline');
}

function verificaCondicaoPagamento(object){

	if(object.cliente.condicaoPagamento!=null && getCondicaoPagamento()==undefined){
		jsonCondicaoPagamento = new Object();				
		jsonCondicaoPagamento.codigo = object.cliente.condicaoPagamento.codigoCondicaoPagamento;
		jsonCondicaoPagamento.descricao = object.cliente.condicaoPagamento.descricaoCondicao;
		sessionStorage.setObject(CONST_CONDICAO_PAGAMENTO, jsonCondicaoPagamento);
	}
}

function verificaModeloDistribuicao(object){
	if(object.infoGerais.codigoModeloDistribuicao!=undefined){
		jsonModeloDistribuicao = new Object();
		jsonModeloDistribuicao.codigo = object.infoGerais.codigoModeloDistribuicao;		
		sessionStorage.setObject(CONST_MODELO_DISTRIBUICAO, jsonModeloDistribuicao);		
	}
}

sucesso = function (object) {
	
	sessionStorage.setObject('numeroPedido', object.infoGerais.numeroPedido);
	
	setaFilialDefault(object);
	verificaCondicaoPagamento(object);
	verificaModeloDistribuicao(object);
	var condicaoPagamento = getCondicaoPagamento();
	
	var exp  = getFilialExpedicao();
	var fat  = getFilialFaturamento();
	var infoGerais = object.infoGerais;	
	
	var modeloDistribuicao=getModeloDistribuicao();

	if(exp == null && fat == null){
		var exp = getFilialExpedicaoDefault();
		var fat = getFilialFaturamentoDefault();
	}

	//guarda a filial no objeto de pedidos
	sessionStorage.setObject(CONST_FILIAL_EXPEDICAO, exp);
	sessionStorage.setObject(CONST_FILIAL_FATURAMENTO, fat);
	sessionStorage.setObject(CONST_CLIENTE, object.cliente);
	
	var nomCli = infoGerais.nomeCliente;
	
	//Tratamento tipo pedido
	tratamentoTipoPedido(infoGerais.isPedidoExiste, infoGerais.tipoVenda);
	
	var vbb    = infoGerais.valorBoletoBancario;
	var vex    = infoGerais.valorExpedicao;
	
	var vmpp   = infoGerais.valorMinimoPontoPedido;
	var vmbpc  = infoGerais.valorMaximoBrindePoupeCerto;
	
	var vlcnm  = infoGerais.valorLimiteCreditoNomeado;
	var vlsac  = infoGerais.valorSaldoAbertoCliente;
	
	if(infoGerais.totalTitulosAbertos > 0){
		$('#span_total_titulos').html(infoGerais.totalTitulosAbertos);
	}else{
		$('#titulosPendentes').empty().remove();
	}
	
	var txt    = exp.codigoFilial +' - '+ exp.nomeFilial + ' / ' + fat.codigoFilial +' - '+ fat.nomeFilial;
	
	$('#span_cad_filial').html(txt);
	$('#label_filial_exp').append("Para filial de Expedição " + exp.codigoFilial + " : ");

	$('#span_val_min_exp').append(vex);
	$('#span_val_bol_ban').append(vbb);
	
	$('#span_val_min_ponto_ped').append(vmpp);
	$('#span_val_max_brinde').append(vmbpc);
	
	$('#span_val_lim_cred_nom').append(vlcnm);
	
	$('#span_val_sld_abt_cli').append(vlsac);
	
	$('#span_nome_cliente').text(nomCli);
	
	if(condicaoPagamento != null){
		$('#span_cond_pagto').html(condicaoPagamento.descricao);
	}
	
	if(modeloDistribuicao != null){
		$('#span_modelo_dist').html(modeloDistribuicao.codigo);
	}	
}

function fecharPedido() {
	if(isPedidoValido()){
		window.location.href = getPage('fecharPedido');
		//window.location.href = getPage('infoPedido');
	}
}