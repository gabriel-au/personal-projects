function loginValidation() {
	$('#buttonLogin').toggleClass('butGo');
}

//ABRIR/FECHAR APLICAÇÃO

function openAplication() {

	
	if (sistemaSelecionado == 1) {
		window.location.href = "conteudo.html?1";
	}
		
	if (sistemaSelecionado == 2) {
		window.location.href = "conteudo.html?2";
	}	

	 
}

function closeAplication() {
	
	window.location.href = "menu.html";
}

function login() {
	
	window.location.href = "menu.html";

}

function logout() { 
	$('.welcome').fadeOut('slow', function() {
		$(this).addClass('invisible');
		$(this).removeAttr('style');
	});
	$('.campaignBaseNave').fadeOut('fast', function() {
		$(this).addClass('invisible');
		$(this).removeAttr('style');
	});
	$('.loginArea').fadeIn('slow');
	$('.initial').css('background-position','0px 140px');
}


//MOVIMENTO MENU
var sistemaSelecionado = 0;

var nomeAplicacoes = new Array(4);
nomeAplicacoes[0] = new Array(2);
nomeAplicacoes[0][0] = "Plano de Bonus";
nomeAplicacoes[0][1] = 0;

nomeAplicacoes[1] = new Array(2);
nomeAplicacoes[1][0] = "Central de Credito Seguro";
nomeAplicacoes[1][1] = 1;

nomeAplicacoes[2] = new Array(2);
nomeAplicacoes[2][0] = "Acompanhamento de Producao";
nomeAplicacoes[2][1] = 0;

nomeAplicacoes[3] = new Array(2);
nomeAplicacoes[3][0] = "DWADP";
nomeAplicacoes[3][1] = 2;

var carrosselApp = 0;

var nomeAssuntos = new Array(2);
nomeAssuntos[0] = "Producao por Ramo";
nomeAssuntos[1] = "Ranking por Ramo";
var carrosselAssunto = 0;

//PRÓXIMA APLICAÇÃO
function nextApplication() {
	var tag = $('.CampaignNav div');
	var multiply = 1;
	
	carrosselApp++;
	if (carrosselApp > 3) {
		carrosselApp = 0;
	}
	$('.campaignBaseNave h1').text(nomeAplicacoes[carrosselApp][0]);
	var appz = new Number(carrosselApp) + 1;
	
	$('.CampaignNav div').each(function() {
		var valor_x = new Number($(this).css('left').replace('px', ''));
		var valor_y = new Number($(this).css('top').replace('px', ''));
		var alpha = new Number($(this).children('a').css('opacity'));
		var final_x = valor_x - 15;
		var final_y = valor_y + 15;
		var final_alpha = alpha - 0.25;

		$(this).animate({left:final_x + 'px',top:final_y + 'px'},100);

		$(this).children('a').css('opacity', final_alpha);
		
		$(this).css('z-index', tag.length - multiply + 1);
		
		if (multiply == 1) {
			$(this).fadeOut('normal', function() {
				$(this).removeAttr('onclick');
				$(this).remove();
				$('.CampaignNav').append('<div id="newApplication" class="aplicacao' + appz + '" onclick="nextApplication();" style="left:100px; top:225px; z-index:0; display:none;"><a href="javascript:void(0);" style="opacity:0.75;" title="' + nomeAplicacoes[carrosselApp][0] + '"></a></div>');
				// SETA O SISTEMA SELECIONADO PARA ABRIR A APLICACAO
				sistemaSelecionado = nomeAplicacoes[carrosselApp][1];
				$("#labelSist").text(nomeAplicacoes[carrosselApp][0]);

				$('#newApplication').fadeIn('normal', function() {
					$(this).removeAttr('id');
				});
			});
		}
		multiply++;
	});
}
//APLICAÇÃO ANTERIOR
function prevApplication() {
	

	var tag = $('.CampaignNav div');
	var multiply = 1;
	carrosselApp--;
	if (carrosselApp < 0) {
		carrosselApp = 3;
	}
	$('.campaignBaseNave h1').text(nomeAplicacoes[carrosselApp][0]);
	var appz2 = new Number(carrosselApp);
	$('.CampaignNav div').each(function() {
		var valor_x = new Number($(this).css('left').replace('px', ''));
		var valor_y = new Number($(this).css('top').replace('px', ''));
		var alpha = new Number($(this).children('a').css('opacity'));
		var final_x = valor_x + 15;
		var final_y = valor_y - 15;
		var final_alpha = alpha + 0.25;

		$(this).animate({left:final_x + 'px',top:final_y + 'px'},100);

		$(this).children('a').css('opacity', final_alpha);
		
		$(this).css('z-index', tag.length - multiply + 1);
		
		if (multiply == tag.length) {
			$(this).fadeOut('normal', function() {
				$(this).removeAttr('onclick');
				$(this).remove();
				$('.CampaignNav div:first').before('<div id="newApplication" class="aplicacao' + appz2 + '" onclick="nextApplication();" style="left:55px; top:270px; z-index:' + new Number(tag.length + 1) + '; display:none;"><a href="javascript:void(0);" style="opacity:0;" title="' + nomeAplicacoes[carrosselApp][0] + '"></a></div>');

				// SETA O SISTEMA SELECIONADO PARA ABRIR A APLICACAO
				sistemaSelecionado = nomeAplicacoes[carrosselApp][1];
				$("#labelSist").text(nomeAplicacoes[carrosselApp][0]);

				$('#newApplication').fadeIn('normal', function() {
					$(this).removeAttr('id');
				});
			});
		}
		multiply++;
	});
}

// NAVEGAÇÃO DE VISÃO
function openSubject(objeto) {
	$(objeto).children('a').toggleClass('closeSubject');
	if ($('.subjectNavContent').css('display') == 'none') {
		$('.subjectNavContent').show('fast');
	} else {
		$('.subjectNavContent').hide('slow');
	}
}

function nextVision() {
	var tag = $('.subjectVision');
	$('.subjectVision').each(function() {
		var valor_x = new Number($(this).css('left').replace('%', ''));
		var final_x = valor_x + 15;
		$(this).animate({left:final_x + '%'},100);
		if ($(this).hasClass('nextleft')) {
			$(this).removeClass('nextleft');
			$(this).children('img').animate({width:288 + 'px'},100);
		} else if ($(this).attr('class') == 'subjectVision') {
			$(this).addClass('nextright');
			$(this).children('img').animate({width:250 + 'px'},100);
		}
		$('.navControl h4').text('Assunto 2');
		$('.navControl .but_prevPage').show('slow');
		$('.navControl .but_nextPage').hide('slow');
		carrosselAssunto = 1;
		$('.navControl h4').text(nomeAssuntos[carrosselAssunto]);
	});
}

function prevVision() {
	var tag = $('.subjectVision');
	$('.subjectVision').each(function() {
		var valor_x = new Number($(this).css('left').replace('%', ''));
		var final_x = valor_x - 15;
		$(this).animate({left:final_x + '%'},100);
		if ($(this).hasClass('nextright')) {
			$(this).removeClass('nextright');
			$(this).children('img').animate({width:288 + 'px'},100);
		} else if ($(this).attr('class') == 'subjectVision') {
			$(this).addClass('nextleft');
			$(this).children('img').animate({width:250 + 'px'},100);
		}
		$('.navControl h4').text('Assunto 1');
		$('.navControl .but_prevPage').hide('slow');
		$('.navControl .but_nextPage').show('slow');
		carrosselAssunto = 0;
		$('.navControl h4').text(nomeAssuntos[carrosselAssunto]);
	});
}


function closeFloater(objeto) {
	$(objeto).offsetParent().hide('fast', function(){
		$(this).remove();
	});
}


//SCROLL
$(function() {
	$( ".scroll" ).draggable({ axis: 'x' },{ snap: true });
});

//MAXIMIZAR
function maximize(objeto) {
	if ($(objeto).parent().parent().parent().parent().hasClass('chartMaximized')) {
		$(objeto).parent().parent().parent().parent().removeClass('chartMaximized');
		$(objeto).toggleClass('but_maximize', 'but_minimize')
	} else {
		$(objeto).parent().parent().parent().parent().addClass('chartMaximized');
		$(objeto).addClass('but_minimize', 'but_maximize')
	}
}

//PAGINAÇÃO
function nextPage() {
	var valor_x = new Number($('.mainContent').css('left').replace('px', ''));
	$('.mainContent').css('left',valor_x - 1020 + 'px');
	if (valor_x == -990) {
		$('.navigation .but_nextPage').hide();
	}
	if (valor_x >= 0) {
		$('.navigation .but_prevPage').show('fast');
	}
	var highlight = $('.navigation ul li a.highlight');
	highlight.parent().next().children('a').addClass('highlight');
	highlight.removeClass('highlight');
}

function prevPage() {
	var valor_x = new Number($('.mainContent').css('left').replace('px', ''));
	$('.mainContent').css('left',valor_x + 1020 + 'px');
	if (valor_x == -990) {
		$('.navigation .but_prevPage').hide();
	}
	if (valor_x >= -2020) {
		$('.navigation .but_nextPage').show('fast');
	}
	var highlight = $('.navigation ul li a.highlight');
	highlight.parent().prev().children('a').addClass('highlight');
	highlight.removeClass('highlight');
}

function pageNav(page, objeto) {
	$('.mainContent').css('left',page + 'px');
	$('.navigation ul li a').removeClass('highlight');
	$(objeto).addClass('highlight')
	if (page == 30) {
		$('.navigation .but_prevPage').hide();
	} else {
		$('.navigation .but_prevPage').show('fast');
	}
	if (page == -2020) {
		$('.navigation .but_nextPage').hide();
	} else {
		$('.navigation .but_nextPage').show('fast');
	}
}
