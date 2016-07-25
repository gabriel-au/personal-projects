// NAVEGAÇÃO
function nav(anchor) {
	window.location.href = anchor;
}

function getGlobalOffset(element) {
	var x = 0, y = 0;
	while(element) {
		x += element.offsetLeft;
		y += element.offsetTop;
		element = element.offsetParent;
	}
	return {
		top: y,
		left: x
	};
}

function calculaAltura(wrapper, footer, modificador) {
	var wrapperTop = getGlobalOffset(wrapper).top;
	wrapper.style.height = ($(window).height() - wrapperTop - (footer ? footer.offsetHeight : 0) - (modificador || 0)) + 'px';
}

// menu
function menu() {
	var target = $('#content');
	if (target.css("left") == "0px") {
		$("a.menu").removeClass("off");
		target.animate({left:'300px'}, 100);
		myScroll = new iScroll('menu', {
			desktopCompatibility : true, bounce: false
		});
	} else {
		$("a.menu").addClass("off");
		target.animate({left:'0'}, 100);
		myScroll.destroy();
		myScroll = null;
	}
}

// BOTÕES
function tableTR(e) {
	$(e).parent().children().removeClass("selected");
	$(e).addClass("selected");
}

// Abrir descrição do Produto
function openDescription(e) {
	if($(e).parent().hasClass("selected")) {
		$(e).parent().removeClass("selected");
	} else {
		$(e).parent().addClass("selected");
	}
}
	
// Selecionar linha
function selectLine(e) {
	if($(e).parent().parent().parent().hasClass("selected")) {
		$(e).parent().parent().parent().removeClass("selected");
	} else {
		$(e).parent().parent().parent().addClass("selected");
	}
}
	
// Selecionar apenas uma linha
function selectOneLine(e) {
	if($(e).parent().parent().parent().hasClass("selected")) {
		$(e).parent().parent().parent().removeClass("selected");
	}else{
		var linhaSelecionada = false;
		$('#'+$(e).parent().parent().parent().parent().parent().attr("id")+' tbody tr').each(function(i){
			if($(this).hasClass("selected")) {
				$(this).removeClass("selected");
			}
		})
		selectLine(e);
	}
}

// Limpar Campo
function clearField(e) {
	$(e).next().val("");
}

// Botão Selecionado
function selectButton(e) {
	if ($(e).hasClass("highlight")) {
		$(e).removeClass("highlight");
	} else {
		$(e).addClass("highlight");
	}
}

// SelectOnce
function select(e) {
	$(e).parent().parent().children().removeClass("selected");
	$(e).parent().addClass("selected")
}

// selectOnce lista
function listSelect(e) {
	$(e).parent().children().removeClass("selected");
	$(e).addClass("selected")
}

// Selecionar Filtro
function selectFilter(e) {
	if ($(e).hasClass("selected")) {
		$(e).removeClass("selected")
	} else {
		$(e).parent().children().removeClass("selected");
		$(e).addClass("selected")
	}
}
	
//Switch
function switchON(f) {
 var target = $(f);
	if(target.hasClass("on")) {
		target.removeClass("on");
		target.children().animate({left:'-30px'}, 100);
	} else {
		target.children().animate({left:'-0px'}, 100);
		target.addClass("on");
	}
}

//Abrir Opções
function openOptions(e){
	if($(e).next().css("display") != "block") {
		$(e).next().show("fast");
	} else {
		$(e).next().hide("fast");
	}
}

//Description
function createDescription(e) {
	$(e).width($(window).width() - 27);
}
	
function tableMov(e, mov){
	var table = $(e).parent().parent().children("table");
	var tamanhoFilho = table.width();
	var tamanhoPai = $(window).width();
	var pos = parseFloat(table.css("margin-left").replace("px",""));
	var prev = 150;
	var next = -150;
	
	if (mov == "prev" && pos + prev < 0) {
		//alert(tamanhoPai + "+" + pos + "=" + tamanhoFilho);
		pos = parseFloat(pos + prev) + "px";
		table.css("margin-left", pos);
		$(e).next().show();
		return;
	}
	
	if (pos + prev >= 0 && mov == "prev") {
		table.css("margin-left", "0px");
		$(e).hide();
		return;
	}
	if (tamanhoFilho <= tamanhoPai - pos + 150) {
		pos = parseFloat(tamanhoPai - tamanhoFilho) + "px";
		table.css("margin-left", pos);
		$(e).hide();
		return;
	}
	
	if (mov == "next" && tamanhoFilho > tamanhoPai - pos + 150) {
		pos = parseFloat(pos + next) + "px";
		table.css("margin-left", pos);
		$(e).prev().show();
	}
}
	
// MONTAGEM DE COMPONENTES
//ABAS
function createTabs(e) {
	if ($(e).hasClass("topMenu")) {
		$(e).width($(window).width() - 40);
	}
	var totalWidth = Math.floor($(e).width()/$(e).children().size());
	$(e).children().first().addClass("first");
	$(e).children().last().addClass("last");
	$(e).children().each(
		function() {
			if ($(this).hasClass("first") || $(this).hasClass("last")) {
				$(this).width(totalWidth - 21)
			} else {
				$(this).width(totalWidth - 22)
			}
		}
	)
}

// Switch
function createSwitch(e) {
	$(e).append("<div><a href='javascript:void(0);'></a></div>");
	$(e).mousedown(
		function() {
			switchON(this);
		}
	)
}

// Campos de texto
function createTypeInput(e) {
	var id = $(e).attr('id');
	var idElement = id? id+'-clearBt':'';
	var idElementDiv = id? id+'-clearBt-div':'';
	if($(e).attr('readonly')){
		$(e).after("<div class='typeInput'></div>")
	}else{
		$(e).after("<div class='typeInput'><a style='display:none' href='javascript:void(0);' id="+idElement+"></a></div>")
	}
	$(e).appendTo($(e).next());
	if ($(e).hasClass("search")) {
		$(e).parent().width($(e).width() + 60);
	} else {
		$(e).parent().width($(e).width() + 30);
	}
	$(e).focus(
		function() {
			$(this).prev().show();
		}
	)
	$(e).focusout(function() {
		var Nchar = $(e).val();
		if (Nchar.length == 0) {
			$(e).prev().hide();
		}
	});
	
	$(e).prev().mousedown(
		function() {
			clearField(this);
			$(this).hide();
		}
	)
}

// Select
function createSelect(e) {
	if (!$(e).hasClass("normal")) {
		$(e).after(
		"<a class='button left select'><span class='icon aplicadoHighlight'></span><label></label></a>");
		$(e).appendTo($(e).next());
		$(e).parent().width(parseFloat($(e).width()));
		$(e).hide();
		$(e).prev().html($(e).children("option[selected]").html());			
		$(e).parent().mousedown(
			function() {
				openSelect(this, $(e).attr('id'));
			}
		)
	}
}
	
function openSelect(e, idObjeto) {	
	$(e).parent().append("<div id='select' class='options' style='width:"+ parseFloat($(e).width() + 20) +"px;'><ul></ul></div>");
	$(e).children("select").children().each(
		function() {
			if($(this).html() == $(e).children("label").html()) {
				$('div#select').children().append("<li class='selected'><span class='icon aplicado'></span><a href='javascript:void(0);' onclick='selectChoice(this,this.parentNode.parentNode.parentNode.parentNode);'>"+ $(this).html() +"</a></li>");
			} else {
				$('div#select').children().append("<li><span class='icon aplicado'></span><a href='javascript:void(0);' onclick='selectChoice(this,this.parentNode.parentNode.parentNode.parentNode);'>"+ $(this).html() +"</a></li>");
			}
		}
	)
}
	
function selectDefault(divWithSelect, defaultValue) {
	$(divWithSelect).parent().children("label").html(defaultValue);
}
	
function selectChoice(e, divWithSelect) {
	$(e).parent().parent().parent().parent().children("a").children("label").html($(e).html());
	$(e).parent().parent().parent().remove();
	
	//alterando valor do componente select		
	var index  = $(e).parent().index();
	var select = $(divWithSelect).find('select');
	$(select)[0].selectedIndex = index;
	$(select).change();
}
	
// Grupo de botões
function createGroupButtons(e) {
	$(e).children().children().first().addClass("first");
	$(e).children().children().last().addClass("last");
	$(e).children().children().children().mousedown(
		function() {
			select(this);
		}
	)
}
			
//Montagem filtro por alfabeto
function createFilterAlphabet(e) {
	$(e).append("<ul></ul>")
	var alphabet = new Array("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
	var i;
	for (i=0; i<26; i++) {
		if(i == 13) {
			$(e).children().append("<br class='clear'/>");
		}
		$(e).children().append("<li>" + alphabet[i] + "</li>");
		$(e).children().children().last().mousedown(
			function() {
				selectFilter(this);
			}
		)
	}
}
	
// Montagem de filtro de atributos
function createListAtribute(e) {
	var tamanho = 0; 
	$(e).children().each(
		function() {
			tamanho = tamanho + parseFloat($(this).width()) + 30;
		}
	);
	$(e).width(tamanho);
	if ($(e).width() > $(e).parent().width()) {
		$(e).parent().parent().append("<a href='javascript:void(0);' class='button left prev'></a><a href='javascript:void(0);' class='button right next'></span></a>")
		$(e).parent().parent().children("a.prev").mousedown(
			function() {
				prevAttr(this);
			}
		)
		$(e).parent().parent().children("a.next").mousedown(
			function() {
				nextAttr(this);
			}
		)
	}
}
	
function nextAttr(e) {
	var tamChild = $(e).parent().children("div").width();
	var tamTotal = 0;
	$(e).parent().children("div").children().children().each(
		function() {
			tamTotal = tamTotal + parseFloat($(this).width()) + 30;
		}
	)
	var posChild = tamTotal + parseFloat($(e).parent().children("div").children().css("left").replace("px",""));

	if (posChild > tamChild) {		
		var left = parseFloat($(e).parent().children("div").children().css("left").replace("px",""));
		var movLeft = left - 150;
		$(e).parent().children("div").children().animate({left: movLeft + 'px'}, 100);
	}
	posChild = tamTotal + parseFloat($(e).parent().children("div").children().css("left").replace("px",""));
	if (posChild <= tamChild) {
		$(e).hide();
	}
	$(e).parent().children("a.prev").show();
}
	
function prevAttr(e) {
	var posChild = parseFloat($(e).parent().children("div").children().css("left").replace("px",""));

	if (posChild < 0) {		
		var left = parseFloat($(e).parent().children("div").children().css("left").replace("px",""));
		var movLeft = left + 150;
		$(e).parent().children("div").children().animate({left: movLeft + 'px'}, 100);
	}
	posChild = parseFloat($(e).parent().children("div").children().css("left").replace("px",""));
	if (posChild >= 0) {
		$(e).hide();
		$(e).parent().children("div").children().css("left", "0px");
	}
	$(e).parent().children("a.next").show();
}

function sucessoMenu(retorno) {
	sessionStorage.setObject('menu', retorno);
	init();
}

function falhaMenu(retorno) {
	BRQMob.log(retorno);
}

function buscarMenu() {
	try {
		if(!sessionStorage.getObject("menu")) {
			BRQMob.exec("sucessoMenu", "falhaMenu", "EstruturaDispatcher", "montaMenu", []);
		} else {
			init();
		}
	} catch (err) {
		BRQMob.log('[error actions.js function buscarMenu()]: ' + err.message);
	}
}

Storage.prototype.setObject = function(key, value) {
	this.setItem(key, JSON.stringify(value));
}

Storage.prototype.getObject = function(key) {
	return JSON.parse(this.getItem(key));
}

Storage.prototype.removeObject = function(key) {
	this.removeItem(key);
}

function menuLoad() {
	try {
		var menuStorage = sessionStorage.getObject('menu');
		montarNavegacao(menuStorage.navigationConfigList);
		montarMenu(menuStorage.menuList);
	} catch (err) {
		BRQMob.log('[error actions.js function menuLoad()]: ' + err.message);
	}
}

function montarNavegacao(navigationConfigList) {
	var navegacao = [];	
	if(!sessionStorage.getObject('navigationConfigList')){		
		$.each(navigationConfigList, function(i, obj) {
			navegacao.push({"viewName":obj.name,"page":obj.page});
		});
		sessionStorage.setObject(CONST_NAVEGACAO, navegacao);
	}
}

function getPage(viewName) {
	navegacaoList = sessionStorage.getObject(CONST_NAVEGACAO);
	var page = '';
	$.each(navegacaoList, function(i, obj) {
		if(obj.viewName == viewName){
			page = obj.page;
			return;
		}
	});
	if(page == ''){
		alert('View Name '+viewName+' nao encontrada');
	}
	return page;
}

function montarMenu(menuList) {
	var outer_list = $('<ul/>', {
		id : "menu",
		"class" : "menu"
	});
	$.each(menuList, function(i, obj) {
		inner_list = $('<a/>', {});
		if(obj.styleClass != null) {
			li_text = $('<h2/>', {
				id : obj.name,
				text : obj.title,
				"class" : obj.styleClass
			});
		} else {
			li_text = $('<h2/>', {
				id : obj.name,
				text : obj.title
			});

		}
		$(li_text).appendTo(inner_list);
		$(inner_list).appendTo(outer_list);
		$.each(obj.itens, function(i, objItem) {
			inner_list = $('<li/>', {});
			if(objItem.styleClass != null) {
				a_href_text = $('<a/>', {
					id : objItem.name,
					text : objItem.title,
					href : getPage(objItem.viewName),
					"class" : objItem.styleClass,
					onclick : "javascript:resetPilhaNavegacao()"
				});
			} else {
				a_href_text = $('<a/>', {
					id : objItem.name,
					text : objItem.title,
					href : getPage(objItem.viewName),
					onclick : "javascript:resetPilhaNavegacao()"
				});
			}
			$(a_href_text).appendTo(inner_list);
			$(inner_list).appendTo(outer_list);
		});
	});
	$("#menu").append(outer_list);
}

var myScroll;
var a = 0;

var supportsOrientationChange = "onorientationchange" in window,
    orientationEvent = supportsOrientationChange ? "orientationchange" : "resize";

/* Evento causa a perda dos dados preenchidos na tela
window.addEventListener(orientationEvent, function() {
   	setTimeout ( function() {location.reload();}, 300)
}, false);*/

function init() {
	try {
		// Definição das alturas dos componentes na tela
		var alturaApp = $(window).height();
		$("#container").height(alturaApp);
		$("#content").height(alturaApp);
		$("#mainContent").height(alturaApp - Math.abs($("#header").height() - $("#footer").height()));
		
		$(".listAtributesParent").each(
			function() {
				$(this).width($(window).width() - 60)
			}
		);
		
		$(".qtdBox").each(
			function() {
				$(this).css("marginTop", Math.floor(-($(this).height()/2)) + "px");
				$(this).hide();
			}
		)
		// Redimensionamento da Tela
		$(window).resize(
			function() {
				var alturaApp = $(window).height();
				$("#container").height(alturaApp);
				$("#content").height(alturaApp);
				var tamMainContent = alturaApp - Math.abs($("#header").height() + $("#footer").height());
				$("#mainContent").height();
				$(".listAtributesParent").each(
					function() {
						$(this).width($(window).width() - 60)
					}
				);
				
				$(".qtdBox").each(
					function() {
						$(this).css("marginTop", Math.floor(-($(this).height()/2)) + "px");
					}
				)
				// Redimensionamento das abas
				$(".tabs").each(
					function() {
						createTabs(this);
					}
				);
				
				$(".filterAlphabet").each(
					function() {
						$(this).css({top:tamMainContent - 50});
					}
				);
			}
		);
		//Montagem do Menu
		if ($("#container").hasClass("wMenu")) {
			$("#content").before("<div id='menu'></div>");
			//$("#menu").load("../includes/html/menu.html");
			menuLoad();
			$("#header").append("<a id='menuButton' class='button left btIcon menu' href='javascript:void(0);'></a>");
			$("a.menu").mousedown(function() {
				menu();
			});
		}
		//Montagem de Campos texto
		$(".type").each(
			function() {
				createTypeInput(this);
			}
		);
		//Montagem de botões agrupados
		$(".groupButtons").each(
			function() {
				createGroupButtons(this);
			}
		);
		// Botões Selecionáveis
		$("a.selectable").each(
			function() {
				$(this).mousedown(
					function() {
						selectButton(this);
					}
				)
			}
		);
		//Listas selecionaveis
		$("ul.selectable").each(
			function() {
				$(this).children().each(
					function() {
						$(this).click(
							function() {
								listSelect(this);
							}
						)
					}
				)
			}
		);
		// Switch
		$("div.switch").each(
			function() {
				createSwitch(this);
			}
		);
		// Filtro por alfabeto
		$(".filterAlphabet").each(
			function() {
				createFilterAlphabet(this);
			}
		);
		// Selects
		$("select").each(
			function() {
			   // Não aplicação função createSelect [Deprecated] 
			   // createSelect(this);
			}
		);
		// Atributos
		$(".listAtributes").each(
			function() {
				createListAtribute(this);
			}
		);
		//Abas
		$(".tabs").each(
			function() {
				createTabs(this);
			}
		);
		//Descrição
		$(".description").each(
			function() {
				createDescription(this);
			}
		);
	
	} catch (err) {
		BRQMob.log('[error actions.js function init()]: ' + err.message);
	}
}

// CARREGAMENTO DEFAULT DA PÁGINA
$(document).ready(
	function(){
		buscarMenu();
	}
);
	
// Adicionar Quantidade
function openQtd(e) {
	var id = $(e).attr("id");
	$("#qtd").attr("name", id);
	$("#qtd").val($(e).html());
	$("#qtd").keyup(function(e) {
		try {
			var key = e.keyCode ? e.keyCode : e.which;
			if(key == 13) { $("#btn_qtd_ok").click(); }
			if(key == 27) { $("#btn_qtd_cancel").click(); }
		} catch(err) {
			/*IGNORE*/
		}
	});
	$("#qtd").keypress(function(e) {
		$(this).val($(this).val().replace(/[^\d]/g,''));
	});
	$(".qtdBox").show('fast', function(){$("#qtd").focus()});
}

function qtdAddNumber(objeto, limiteCampo) {
	if(limiteCampo != undefined){
		if(limiteCampo > $('#qtd').val().length){
			qtdAdd(objeto);
		}
	}else{
		qtdAdd(objeto);
	}
}

function qtdAdd(objeto){
	var numero = $(objeto).html();
	var qtdTotal;
	if ($("#qtd").val() != 0) {
		qtdTotal = $("#qtd").val() + numero;
		$("#qtd").val(qtdTotal);
	} else {
		qtdTotal = numero;
		$("#qtd").val(qtdTotal);
	}
}

function removeQtd() {
	var qtdTotal = $('#qtd').val().slice(0,-1); 
	$("#qtd").val(qtdTotal);
	if ($('#qtd').val().length == 0) {
		$('#qtd').val(0);
	}
}

function addQtd(){
	$(".qtdBox").hide('fast');
	var id = $("#qtd").attr("name");
	
	$("#"+ id).html($("#qtd").val());
	var qtd = parseFloat(0);
	var total = 0;
	
	var valores = $("#attr1Value").children();
	var qtdTotal = $(".qtdButton").each(function() {
		valorqtd = parseFloat($(this).html());
		qtd = qtd + valorqtd;
		total = total + parseFloat($(this).parent().parent().children(".listAtributesParent").children().children().children(".valorUnit").html().replace("R$","").replace(".","").replace(",00",""))*valorqtd;
	});
	$("#qtdTotal").html("R$" + qtd);
	$("#total").html("R$" + total + ",00");
}

//DETALHES
function showDet(e, target) {
	if($(target).css("display") != "none") {
		$(target).hide("fast");
		$(e).html("+ Detalhes");
	} else {
		$(target).show("fast");
		$(e).html("- Detalhes");
	}
}

// Funções para utilizar em janelas que podem ser fechadas clicando em qualquer lugar da tela
//MENU ITENS DISPONÍVEIS
function evento2(e){
	if (e.which==2,3) {
		$('#itensMenu').hide('fast');
	}
}

function ativa(){ 
	$('#itensMenu').show("fast");
	document.getElementById("mainContent").onmousedown = evento2;
}

// POPUP BUSCA AVANÇADA
function evento(e){
	if (e.which==2,3) {
		$('#buscaAvancada').hide('fast');
	}
}

function ativaBusca(){ 
	$('#buscaAvancada').show("fast");
	document.getElementById("mainContent").onmousedown = evento;
}

// MENU PEDIDO
function evento3(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

// MENU PEDIDO OPÇÕES
function evento3(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

function ativaOpcoesPedido(nomeCliente,codigoCliente,numeroPedido,preparado,dataPedido,horaPedido,dataResultado,quantidadeItens,valorTotalPedido) { 
	$("body").append("<div class='menuOpcoes'>" +
			"<h2>Pedido nº "+numeroPedido+"</h2>" +
			"<a href='javascript:void(0);' onclick=\"alteraPedido('"+ numeroPedido + "')\" class='button'>Alterar Pedido</a>" +
			"<a  href='../vendas/cortes.html' class='button'>Listar Cortes</a>" +
			"<a href='../vendas/relacaoRetornos.html' class='button'>Listar Retornos</a>" +
			"<a href='javascript:void(0);' onclick=\"regeraPedido('"+ numeroPedido + "')\" class='button'>Regerar Pedido</a>" +
			"<a onclick=\"montarPedidoEmail('"+ nomeCliente + "','" + codigoCliente+ "','"+ numeroPedido + "','" + preparado + "','" + dataPedido + "','" + horaPedido + "','" + dataResultado + "','" + quantidadeItens + "','" + valorTotalPedido +  "')\" class='button'>Gerar TXT</a>" +
			"<a href='javascript:void(0)' onclick='excluiPedido();' class='button'>Excluir Pedido</a></div>");
	$(".menuOpcoes").show("fast");
	document.getElementById("mainContent").onmousedown = evento3;
}

// MENU PEDIDO OPÇÕES VENDAS
function evento3(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

function ativaOpcoesPedRel() { 
	$("body").append("<div class='menuOpcoes'><h2>Pedido nº234</h2><a href='../vendas/infosGerais.html' class='button'>Alterar Pedido</a><a href='cortes.html' class='button'>Listar Cortes</a><a href='relacaoRetornos.html' class='button'>Listar de Retornos</a><a href='infosGerais.html' class='button'>Regerar Pedido</a><a href='gerarPDF_pedido.html' class='button'>Gerar PDF</a><a href='javascript:void(0)' class='button'>Excluir Pedido</a></div>");
	$(".menuOpcoes").show("fast");
	document.getElementById("mainContent").onmousedown = evento3;
}

// MENU PEDIDO DO CLIENTE RELACIONAMENTO
function evento3(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

function ativaOpcoesListPed() { 
	$("body").append("<div class='menuOpcoes'><h2>Cód.: 1704674</h2><a href='../relacionamento/clientePedidos.html' class='button'>Pedidos do cliente</a><a href='infosGerais.html' class='button'>Novo Pedido</a></div>");
	
	if($(".menuOpcoes").css("display") !== "none"){
		$(".menuOpcoes").hide("fast");
	}else{
		
		$(".menuOpcoes").show("fast");
	}	
	document.getElementById("mainContent").onmousedown = evento3;
}

// MENU PEDIDO DO CLIENTE VENDAS
function evento3(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

function ativaOpcoesListPedVenda() { 
	$("body").append("<div class='menuOpcoes'><h2>Cód.: 1704674</h2><a href='../relacionamento/clientePedidos.html' class='button'>Pedidos do cliente</a><a href='infosGerais.html' class='button'>Novo Pedido</a></div>");
	
	if($(".menuOpcoes").css("display") !== "none"){
		$(".menuOpcoes").hide("fast");
	}else{
		
		$(".menuOpcoes").show("fast");
	}	
	document.getElementById("mainContent").onmousedown = evento3;
}

// MENU COTAÇÕES VENDAS
function evento4(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

function ativaOpcoesCot() {
	$("body").append("<div class='menuOpcoes'><h2>Cotação nº 234</h2><a href='../vendas/infosGeraisCot.html' class='button'>Alterar Pedido</a><a href='gerarPDFGondola.html' class='button'>Gerar PDF</a><a href='javascript:void(0)' class='button'>Excluir Pedido</a></div>");
	$(".menuOpcoes").show("fast");
	document.getElementById("mainContent").onmousedown = evento4;
}

// MENU COTAÇÕES RELACIONAMENTO
function evento4(e){
	if (e.which==2,3) {
		$('.menuOpcoes').hide('fast', function() {$(this).remove()});
	}
}

function ativaOpcoesCotRel() {
	$("body").append("<div class='menuOpcoes'><h2>Cotação nº 234</h2><a href='../vendas/infosGerais.html' class='button'>Alterar Pedido</a><a href='gerarPDFGondola.html' class='button'>Gerar PDF</a><a href='javascript:void(0)' class='button'>Excluir Pedido</a></div>");
	$(".menuOpcoes").show("fast");
	document.getElementById("mainContent").onmousedown = evento4;
}

// MENU Restante
function evento5(e){
	if (e.which==2,3) {
		$('#buscaAvancada').hide('fast', function() {$(this).remove()});
	}
}

// MENU Descrição das Marcações
function evento6(e){
	if (e.which==2,3) {
		$('#boxDetalhe').hide('fast');
	}
}

function detalhesPEE() {
	// $("body").append("<div id='boxDetalhe' class='detalhesPEE'><h2>Descrição das Marcações</h2><p><label>Ação Tática:</label><span>XX %</span></p>Validação Ação Tática:<span>XX</span></p><br /><span>Itens Cadastrados na Lista XX</span></div>");
	$("#boxDetalhe").show("fast");
	document.getElementById("mainContent").onmousedown = evento6;
}

function limparSessao() {
	//recuperar os valores que não poderao ser apagados
	var rep = getRepresentante();
	var dadosComerciaisRepresentante = getDadosComerciaisRepresentante();
	var sistema = getSistema();
	var representanteEquipe = getRepresentanteEquipe();
	//apaga todos os dados da sessao
	sessionStorage.clear();
	//atribui a nova sessao os valores que serao necessarios
	sessionStorage.setObject(CONST_REPRESENTANTE, rep);
	sessionStorage.setObject(CONST_SISTEMA, sistema);
	sessionStorage.setObject(CONST_DADOS_COMERCIAIS_REPRESENTANTE, dadosComerciaisRepresentante);
	sessionStorage.setObject(CONST_REPRESENTANTE_EQUIPE, representanteEquipe);
}

function getHTMLData(url) { 
	request = new XMLHttpRequest(); 
	request.open("GET", url, false); 
	request.onreadystatechange = function() {
		if ( request.readyState == 4 ) {
			alert(request.responseText);
			return request.responseText;
		}
	}; 
	request.send(null); 
}

//SLIDER
(function(window) {
	//"use strict";// MUST NOT use this for production
	var isTouchSupported = 'ontouchstart' in window;
	var redrawRequested;

	// window.requestAnimFrame (http://paulirish.com/2011/requestanimationframe-for-smart-animating/)
	var requestAnimFrame = window.requestAnimFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
	function(callback) {
		window.setTimeout(callback, 1000 / 60);
	};

	function requestRedraw(callback) {
		if(redrawRequested)
			return;
		redrawRequested = 1;
		requestAnimFrame(function(time) {
			callback(time);
			redrawRequested = 0;
		});
	}

	function fakeFunction() { /*used in place of expected functions for fast pace on Android and IE*/
	}

	function globalOffsetLeft(element) {
		var x = 0;
		while(element) {
			x += element.offsetLeft || 0;
			element = element.offsetParent;
		}
		return x;
	}

	function limit(value, min, max) {
		// Tip: NaN < 0 === false and NaN > 0 === false
		return value > max ? max : min < value ? value : min;
		// this order avoids NaN
	}

	function getComputedStyle(element, property) {// IE compatible
		return window.getComputedStyle ? window.getComputedStyle(element)[property] : element.currentStyle[property] || null;
	}

	function buildCompatibleEvent(e) {
		e = e || window.event;

		if(!e.preventDefault) {
			e.preventDefault = function() {
				this.returnValue = false;
				// IE
			};
		}

		if(!e.stopPropagation) {
			e.stopPropagation = function() {
				this.cancelBubble = true;
				// IE
			};
		}

		if((e.pageX >> 0) === e.pageX /* is pageX numeric?*/) {
			e.changedTouches = [{
				pageX : e.pageX
			}];
		} else {
			var html = document.documentElement;
			var body = document.body;
			e.changedTouches = body ? [{
				pageX : e.clientX + (html.scrollLeft || body.scrollLeft) - (html.clientLeft >> 0) - (body.clientLeft >> 0)
			}] : [{
				pageX : e.clientX + html.scrollLeft - (html.clientLeft >> 0)
			}];
		}
		return e;
	}

	var addEventListener, removeEventListener, dispatchEvent;
	// strict mode compatible function definitions
	if(window.addEventListener) {
		addEventListener = function(type, listener) {
			if(this/*null on strict*/ && typeof type === 'string' && typeof listener === 'function') {
				this.addEventListener(type, listener, false);
			}
		};
		removeEventListener = function(type, listener) {
			if(this/*null on strict*/ && typeof type === 'string' && typeof listener === 'function') {
				this.removeEventListener(type, listener, false);
			}
		};
		dispatchEvent = function(type) {
			if(this/*null on strict*/ && typeof type === 'string' && typeof listener === 'function') {
				var e = document.createEvent('HTMLEvents');
				e.initEvent(type, true, true);
				this.dispatchEvent(e);
			}
		};
	} else {
		if(this.attachEvent)// IE compatibility
		{
			addEventListener = function(type, listener) {
				if( typeof type === 'string' && typeof listener === 'function') {
					var h = type + listener;
					this['on' + h] = listener;
					var that = this;
					this.attachEvent('on' + type, (this[h] = function() {
						that['on' + h] && that['on' + h](event);
					}));
				}
			};
			removeEventListener = function(type, listener) {
				if( typeof type === 'string' && typeof listener === 'function' && this[(type += listener)]) {

					this.detachEvent('on' + type, this[type]);
					this[type] = null;
					this['on' + type] = null;
				}
			};
			dispatchEvent = function(type) {
				if( typeof type === 'string') {
					this.fireEvent('on' + type, document.createEventObject());
				}
			};
		}
	}

	function Slider(slider, initialValue, max, min, modifier, snapping, valueCallback) {
		// global to local optimization
		var Number = window.Number;
		var Math = window.Math;
		var parseFloat = window.parseFloat;
		var document = window.document;

		// prepare properties
		if(!slider || slider.nodeType !== 1)
			return;
		modifier = Number(modifier) || 1;
		min = Number(min) || 0;
		max = Number(max) || 100;
		initialValue = limit(initialValue, min, max);
		valueCallback = typeof valueCallback === 'function' ? valueCallback : fakeFunction;

		// create the innerArea
		var innerArea = slider.appendChild(document.createElement('div'));
		// create & append
		innerArea.className = 'innerArea';
		var innerAreaWidth = innerArea.offsetWidth;

		// create the filler in innerArea
		var filler = innerArea.appendChild(document.createElement('div'));
		// create & append
		filler.className = 'filler';
		var fillerStyle = filler.style;

		// create and get knob's properties
		var knot = innerArea.appendChild(document.createElement('div'));
		// create & append
		knot.className = 'knot';
		var knotStyle = knot.style;
		//	var knotW = parseFloat(getComputedStyle(knot, 'width')) || 0;
		var knotW = knot.offsetWidth || 0;
		// tested on IE. OK.
		var knotHalfWidth = knotW / 2;

		// relative properties for calcs
		var sliderGlobalLeft;
		// defined on click start
		var dragAreaWidth = limit(innerAreaWidth - knotW, knotW, Number.MAX_VALUE);
		var relativeValue = max - min;

		var snaps = Math.ceil(relativeValue / modifier) + ( snapping ? 0 : 1);
		// +1 = last snap contact area

		var snapWidth = (dragAreaWidth / snaps);

		valueCallback(min);
		var knotPosition = 0;
		knotStyle.cssText = 'left: ' + knotPosition + 'px';
		fillerStyle.cssText = 'width: ' + (knotPosition + knotHalfWidth) + 'px';

		function updateKnot(pointerX) {
			var knotPosition = limit(pointerX - sliderGlobalLeft - knotHalfWidth, 0, dragAreaWidth);

			var snapsToLeft = knotPosition / snapWidth >> 0;
			valueCallback(limit(snapsToLeft * modifier + min, min, max));
			knotPosition = snapping ? limit(snapsToLeft * snapWidth, 0, dragAreaWidth) : knotPosition;

			requestRedraw(function(time) {
				knotStyle.cssText = 'left: ' + knotPosition + 'px';
				fillerStyle.cssText = 'width: ' + (knotPosition + knotHalfWidth) + 'px';
			});
		}

		function moveListener(e) {
			e.preventDefault();
			e.stopPropagation();

			updateKnot(( e = e.changedTouches[0]).pageX);
		}

		function startMoveListener(e) {
			e.preventDefault();
			e.stopPropagation();
			sliderGlobalLeft = globalOffsetLeft(slider);

			if(isTouchSupported) {
				addEventListener.call(slider, 'touchmove', moveListener, false);
				addEventListener.call(knot, 'touchmove', moveListener, false);
			} else {
				addEventListener.call(slider, 'mousemove', compatibleMoveListener, false);
				addEventListener.call(knot, 'mousemove', compatibleMoveListener, false);
				addEventListener.call(document, 'mousemove', compatibleMoveListener, false);
			}
			updateKnot(( e = e.changedTouches[0]).pageX);
		}

		function endMoveListener(e) {
			e.preventDefault();
			e.stopPropagation();

			if(isTouchSupported) {
				removeEventListener.call(slider, 'touchmove', moveListener, false);
				removeEventListener.call(knot, 'touchmove', moveListener, false);
			} else {
				removeEventListener.call(slider, 'mousemove', compatibleMoveListener, false);
				removeEventListener.call(knot, 'mousemove', compatibleMoveListener, false);
				removeEventListener.call(document, 'mousemove', compatibleMoveListener, false);
			}
		}

		function compatibleStartListener(e) {
			startMoveListener(buildCompatibleEvent(e));
		}

		function compatibleMoveListener(e) {
			moveListener(buildCompatibleEvent(e));
		}

		function compatibleEndListener(e) {
			endMoveListener(buildCompatibleEvent(e));
		}

		if(isTouchSupported) {
			addEventListener.call(slider, 'touchstart', startMoveListener, false);
			addEventListener.call(knot, 'touchstart', startMoveListener, false);
			addEventListener.call(slider, 'touchend', endMoveListener, false);
			addEventListener.call(slider, 'touchleave', endMoveListener, false);
			addEventListener.call(slider, 'touchcancel', endMoveListener, false);
		} else {
			addEventListener.call(slider, 'mousedown', compatibleStartListener, false);
			addEventListener.call(knot, 'mousedown', compatibleStartListener, false);
			addEventListener.call(slider, 'mouseup', compatibleEndListener, false);
			addEventListener.call(document, 'mouseup', compatibleEndListener, false);
		}
	}

	window.Slider = Slider;

})(this);

function setPilhaNavegacao(pilhaNavegacaoPar){
	sessionStorage.setObject('pilhaNavegacao', pilhaNavegacaoPar);
}

function resetPilhaNavegacao(){
	var pilhaNavegacao = [];
	setPilhaNavegacao(pilhaNavegacao);
}

function alteraPedido(numeroPedido){
	var dados = {'numeroPedido'   :	numeroPedido};   
	sessionStorage.setObject('numeroPedido', numeroPedido);   
	BRQMob.exec("sucessoLimpaTabelas", "falhaLimpaTabelas", "VendasDispatcher", 'alteraPedido', [dados]);   
}

function regeraPedido(numeroPedido){
 	var dados = {'numeroPedido'   :	numeroPedido};   
 	sessionStorage.setObject('numeroPedido', numeroPedido);   
   	BRQMob.exec("sucessoLimpaTabelas", "falhaLimpaTabelas", "VendasDispatcher", 'regeraPedido', [dados]);   
}

function sucessoLimpaTabelas(){
	nav('../vendas/infosGerais.html');
}

function falhaLimpaTabelas(retorno){
	alert(retorno.mensagem);
}
