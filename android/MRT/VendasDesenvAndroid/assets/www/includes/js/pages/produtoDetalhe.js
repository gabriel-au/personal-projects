try {

$(document).ready(function() {
	
		(function($) {
		  $.fn.even = function() {
		   var jQ = $();
		   jQ.context = this.context;
		   jQ.selector = this.selector;
		   for (var i = j = 0, l = this.length; i < l; i += 2, j++)
		   jQ[j] = this[i];
		   return jQ;
		  };
		 })(jQuery);
	
		BRQNavegacao.adicionarPilhaNavegacao('produtoDesc');
	
		var _DISPATCHER = 'VendasDispatcher';
		var _UNEXPECTED_ERROR = 'Ocorreu um erro inesperado!';
		var _FICHA_TECNICA_ERROR = 'Não foi encontrado a ficha técnica do produto';
	
		var dadosProduto = getDadosProdutoSelecionado();
		var codigoEstadoDestino = getCliente().uf;
	
		var codMer = dadosProduto.codigoMercadoria; // Código do Produto
		var codfilExp = dadosProduto.filialExpedicao; // Código Filial Expedição do Item (Mercadoria)
		var codfilFat = dadosProduto.filialFaturamento; // Código Filial Faturamento do Item (Mercadoria)
		var codCli = dadosProduto.codigoCliente; // Código do Cliente
		var codTerCli = dadosProduto.codigoTerritorioCliente; // Código Território do Cliente
		var codRgrDtb = dadosProduto.codigoModeloDistribuicao; // Código Modelo de Distribuição
		var codAfdRep = getRepresentante().codigoAfinidade; // Código Afinidade do Representante
		/* ########### */
		$("#codigoModeloDistribuicao").text(codRgrDtb);
	
		BRQMob.exec("sucessoRecuperarProduto","falhaRecuperarProduto", _DISPATCHER,"recuperarDescricaoProduto", [ codMer,codfilExp, codfilFat, codCli,codTerCli, codRgrDtb, codigoEstadoDestino ]);
	
		$('#switch-curtir').mousedown(function() {
			var marcar = $(this).hasClass("on") ? 0	: 1;
			BRQMob.exec("sucessofalhaMarcarCurtir", "falhaMarcarCurtir", _DISPATCHER, "marcarCurtir", [ codMer, codTerCli, codfilFat, codAfdRep, marcar, codfilExp ]);
		});
	
		$('#href_caracteristica').click(function() {
			document.getElementById("div_detalhes").style.display = "none";
			document.getElementById("div_caracteristicas").style.visibility = "visible";
			document.getElementById("caracteristicas_li_detalhes").className = "last";
			document.getElementById("caracteristicas_li_caracteristicas").className = "frist selected";
		});
	
		$('#href_detalhes').click( function() {
			document.getElementById("div_detalhes").style.display = "block";
			document.getElementById("div_caracteristicas").style.visibility = "hidden";

			document.getElementById("detalhes_li_detalhes").className = "frist selected";
			document.getElementById("detalhes_li_caracteristicas").className = "last";
		});

		$('#groupButtonsLista').find('a').each(function(index) {
			$(this).click(function() {
				var valor = $(this).text();
				if (isNaN(valor)) {
					valor = 0;
				}
				BRQMob.exec("sucessoEscolherLista", "falhaEscolherLista", _DISPATCHER, "escolherLista", [ codMer, codTerCli, codfilFat, codAfdRep, valor, codfilExp ]);
			});
		});
	
		falhaRecuperarProduto = function(object) {
			apresentaMensagemErro(object.mensagem, true);
		}
		sucessoRecuperarProduto = function(object) {
	
			var detalheMercadoria = object.detalheMercadoria;
			
	
			preencherDetalhes(detalheMercadoria);
			montarImagens(detalheMercadoria);
			preecherFichaTecnica(detalheMercadoria.fichaTecnica);
			// cria o popup da imagem
			$("a[rel='galeria']").colorbox();
			$("#click").click(function() {
				$('#click').css({
					"background-color" : "#f00",
					"color" : "#fff",
					"cursor" : "inherit"
				}).text("galeria");
				return false;
			});
		}
	
		function preecherFichaTecnica(fichaTecnica){
			if(fichaTecnica != undefined){
				$.get(fichaTecnica, function(conteudo) {
					var a = $(conteudo).children().each(function(i,conteudo) {
						// remove todo o css
						$(this).find("*").removeAttr('style colspan class size');
						
						var table = $("<table/>",{'cellspacing':0 , 'cellpadding' : 0 , 'border':0});
						var html = document.createDocumentFragment();
						html = 	conteudo;
						html.children[0].children[0].setAttribute('class','BgTitulo');
						html.children[0].children[0].setAttribute('colspan','2');
						
						for( var index = 0, fim = conteudo.children.length; index < fim ; index  += 1 ) {
							if( index % 2 != 0 ) {
								var temp = html.children[index];
								$(temp).find('td').addClass("on");
							}
						}
						$('#div-scroller-area2').append(table.append($(this).html()));
					});
				}, 'html');
			}else {
				//apresentaMensagemErro(_FICHA_TECNICA_ERROR, true);
			}
		}
		
		function preencherDetalhes(detalheMercadoria) {
	
			$('#span-cod').html(detalheMercadoria.codigo);
			$('#span-mer').html(detalheMercadoria.descricao);
			$('#span-for').html(detalheMercadoria.fornecedor);
	
			$('#span-cat').html(detalheMercadoria.categoria);
			$('#span-sub').html(detalheMercadoria.subCategoria);
	
			$('#span-cxa').html(detalheMercadoria.quantidadeCaixaFechada);
	
			$('#span-dtb').html(detalheMercadoria.descModeloDistribuicao);
			$('#span-vnc').html(detalheMercadoria.dataVencimento);
	
			$('#span-und').html(detalheMercadoria.unidadeVenda);
			$('#span-inc').html(detalheMercadoria.esgotamentoIncentivo);
			$('#span-min').html(detalheMercadoria.qtdMinima);
			$('#span-qta').html(detalheMercadoria.quota);
	
			$('#span-brr').html(detalheMercadoria.codigoBarras);
	
			// Pré Marcar Curtir ?
			if (Boolean(detalheMercadoria.pee)) {
				$('#switch-curtir').addClass("on");
			}
			// Pré Marcar Número da Lista
			if (!isNaN(detalheMercadoria.tipMarcacaoRepresentande)) {
				var selector = 'a:eq(' + detalheMercadoria.tipMarcacaoRepresentande + ')';
				$('#groupButtonsLista').find('li').removeClass('selected');
				$('#groupButtonsLista').find(selector).parent().addClass('selected');
			}
	
		}
	
		function montarImagens(detalheMercadoria) {
			var ul_items = $("#items");
			var li_itens;
			for ( var index = 0, fim = detalheMercadoria.diretorioImagemResolucaoMinima.length; index < fim; index += 1) {
				li_itens = $('<li/>', {});
				var p = $('<p/>', {
					"class" : "image"
				});
				var href = $(
						'<a/>',
						{
							href : detalheMercadoria.diretorioImagemResolucaoMaxima[index],
							rel : "galeria"
						}).appendTo(p);
				var img = $(
						'<img/>',
						{
							src : detalheMercadoria.diretorioImagemResolucaoMinima[index],
							alt : "Imagem " + index
						}).appendTo(href);
				$(p).appendTo(li_itens);
				$(li_itens).appendTo(ul_items);
			}
			definirQuantidadeImagensCarrosel();
		}
	
		function definirQuantidadeImagensCarrosel() {
			// define a quantidade de imagens a montar
			$('ul#items').easyPaginate({
				step : 3
			});
	
		}
	
		falhaMarcarCurtir = function(object) {
			apresentaMensagemErro(_UNEXPECTED_ERROR, true);
		}
		sucessofalhaMarcarCurtir = function(object) {
		}
	
		falhaEscolherLista = function(object) {
			apresentaMensagemErro(_UNEXPECTED_ERROR, true);
		}
		sucessofalhaEscolherLista = function(object) {
		}
	});

} catch (e) {
	alert(1);
}