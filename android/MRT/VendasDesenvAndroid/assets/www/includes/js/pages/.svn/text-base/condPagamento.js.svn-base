
var VENDAS_DISPATCHER = "VendasDispatcher";
var isFinanciamentoVendor = false, isCobrancaBancaria = false, isVendaCash = false, numeroParcelas = 0, prazo = 0, periodicidade = 0, jsonCondicaoPagamento = null;

$(document).ready(function() {
	try {
		BRQNavegacao.adicionarPilhaNavegacao('condPagamento');

		setValoresIniciais();
		BRQMob.log('setValoresIniciais()');

		criaSelectNumeroParcelas();
		BRQMob.log('criaSelectNumeroParcelas()');

		controlaOpcoesCliente();
		BRQMob.log('controlaOpcoesCliente()');

		configuraEventoObjetos();
		BRQMob.log('configuraEventoObjetos()');

		carregaCondicoesPagamento();
		BRQMob.log('carregaCondicoesPagamento()');

		if (getPlataform() == "desktop") {

			$('#prazo').keypress(function(event) {
				var keyCode = event.which;
				if (keyCode >= 48 && keyCode <= 57 || keyCode == 13) {
					return true;
				} else {
					return false;
				}
			});

			$('#prazo').keyup(function(event) {
				event.preventDefault();
				var expre = /[^\d]/g;
				// REMOVE OS CARACTERES DA EXPRESSAO ACIMA
				if ($(this).val().match(expre)) {
					$(this).val($(this).val().replace(expre, ''));
				}
			});

			$('#periodicidade').keypress(function(event) {
				var keyCode = event.which;
				if (keyCode >= 48 && keyCode <= 57 || keyCode == 13) {
					return true;
				} else {
					return false;
				}
			});

			$('#periodicidade').keyup(function(event) {
				event.preventDefault();
				var expre = /[^\d]/g;
				// REMOVE OS CARACTERES DA EXPRESSAO ACIMA
				if ($(this).val().match(expre)) {
					$(this).val($(this).val().replace(expre, ''));
				}
			});

		} else {
			var prazo = document.getElementById('prazo');
			// prazo.setAttribute('type','number');

			var periodicidade = document.getElementById('periodicidade');
			// periodicidade.setAttribute('type','number');
		}
	} catch (err) {
		alert('[$(document).ready()]: '.concat(err.message));
	}
});

function setValoresIniciais() {
	try {
		jsonCondicaoPagamento = getCondicaoPagamento();
	} catch (err) {
		alert('[setValoresIniciais]: '.concat(err.message));
	}
}

function criaSelectNumeroParcelas() {
	try {
		var selectNumeroParcelas = $('<select/>', {
			id : 'numeroParcelas'
		});
		selectNumeroParcelas.append('<option value="0">TODAS</option>');
		for (i = 1; i <= 18; i++) {
			selectNumeroParcelas.append('<option value="' + i + '">' + i
					+ '</option>');
		}
		$("#containerNumeroParcelas").append(selectNumeroParcelas);
	} catch (err) {
		alert('[criaSelectNumeroParcelas]: '.concat(err.message));
	}
}

function carregaCondicoesPagamento() {
	filtroCondicoesPagamento(numeroParcelas, prazo, periodicidade, isFinanciamentoVendor, isCobrancaBancaria, isVendaCash);
}

function configuraEventoObjetos() {
	try {
		$("#prazo-clearBt").mousedown(function() {
			prazo = 0;
			jsonCondicaoPagamento = null;
			carregaCondicoesPagamento();
		})
		$("#periodicidade-clearBt").mousedown(function() {
			periodicidade = 0;
			jsonCondicaoPagamento = null;
			carregaCondicoesPagamento();
		})
		$("#numeroParcelas").change(function() {
			numeroParcelas = $("#numeroParcelas").val();
			jsonCondicaoPagamento = null;
			carregaCondicoesPagamento();
		})
		$("#prazo").change(function() {
			if ($("#prazo").val() == '') {
				prazo = 0;
			} else {
				prazo = $("#prazo").val();
			}
			jsonCondicaoPagamento = null;
			carregaCondicoesPagamento();
		})
		$("#periodicidade").change(function() {
			if ($("#periodicidade").val() == '') {
				periodicidade = 0;
			} else {
				periodicidade = $("#periodicidade").val();
			}
			jsonCondicaoPagamento = null;
			carregaCondicoesPagamento();
		})
		eventosFinanciamento();
		eventosCobranca();
		eventosCash();
	} catch (err) {
		alert('[configuraEventoObjetos]: '.concat(err.message));
	}
}

function eventosCash() {
	try {
		$("#vendaCash").mousedown(function() {
			if ($("#vendaCash").hasClass("on")) {
				isVendaCash = true;
				$("#numeroParcelas").val(1);
				$("#prazo").val('');
				$("#periodicidade").val('');
				numeroParcelas = 1;
				prazo = 0;
				periodicidade = 0;
				$('#numeroParcelas').attr("disabled", true);
				$('#prazo').attr("disabled", true);
				$('#periodicidade').attr("disabled", true);
				switchOFF($("#financiamentoVendor"));
				$("#financiamentoVendor").unbind('mousedown');
				isFinanciamentoVendor = false;
			} else {
				isVendaCash = false;
				numeroParcelas = 0;
				//switchOFF($("#financiamentoVendor"));
				$("#financiamentoVendor").unbind('mousedown');
				isFinanciamentoVendor = false;
				$("#numeroParcelas").val(0);
				$('#numeroParcelas').attr("disabled", false);
				$('#prazo').attr("disabled", false);
				$('#periodicidade').attr("disabled", false);
				if (!isPermitidoSomenteFinanciamentoProprio()) {
					switchON($("#financiamentoVendor"));
					isFinanciamentoVendor = true;
					$("#financiamentoVendor").mousedown(function() {
						switchON(this);
					})
				}
				eventosFinanciamento();
				eventosCobranca();
			}
			jsonCondicaoPagamento = null;
			carregaCondicoesPagamento();
		})
	} catch (err) {
		alert('[eventosCash]: '.concat(err.message));
	}
}

function eventosFinanciamento() {
	try {
		if (!isPermitidoSomenteFinanciamentoProprio()) {
			$("#financiamentoVendor").mousedown(function() {
				if ($("#financiamentoVendor").hasClass("on")) {
					isFinanciamentoVendor = true;
				} else {
					isFinanciamentoVendor = false;
				}
				jsonCondicaoPagamento = null;
				carregaCondicoesPagamento();
			})
		}
	} catch (err) {
		alert('[eventosFinanciamento]: '.concat(err.message));
	}
}

function eventosCobranca() {
	try {
		if (!isPermitidoSomenteTipoCobrancaBancaria()) {
			$("#cobrancaBancaria").mousedown(function() {
				if ($("#cobrancaBancaria").hasClass("on")) {
					isCobrancaBancaria = true;
				} else {
					isCobrancaBancaria = false;
				}
				jsonCondicaoPagamento = null;
				carregaCondicoesPagamento();
			})
		}
	} catch (err) {
		alert('[eventosCobranca]: '.concat(err.message));
	}
}

function switchOFF(f) {
	var target = $(f);
	target.removeClass("on");
	target.children().animate({left : '-30px'}, 100);
}

function filtroCondicoesPagamento(numeroParcelas, prazo, periodicidade, isFinanciamentoVendor, isCobrancaBancaria, isVendaCash) {
	try {
		var cliente = getCliente();
		var codigoCliente = cliente.codigoCliente == null ? sessionStorage.getObject('codigoCliente') : cliente.codigoCliente;
		var codFilExp = getFilialExpedicao() != null && getFilialExpedicao() != undefined ? getFilialExpedicao().codigoFilial : -1;
		// BRQMob.log("entrando na funcao carregar condicoes de pagamento");
		BRQMob.exec("sucesso", "falha", VENDAS_DISPATCHER, "condicaoPagamento", [ numeroParcelas, prazo, periodicidade, isFinanciamentoVendor, isCobrancaBancaria, isVendaCash, codigoCliente, codFilExp ]);
	} catch (err) {
		alert('[filtroCondicoesPagamento]: '.concat(err.message));
	}
}

function sucesso(retorno) {
	try {
		ordenarLista(retorno.consultaCondicaoPagamento.itensCondicaoPagamento);
	} catch (err) {
		alert('[sucesso]: '.concat(err.message));
	}
}

function controlaOpcoesCliente() {
	try {
		if (isPermitidoSomenteTipoCobrancaBancaria()) {
			switchON($("#cobrancaBancaria"));
			$("#cobrancaBancaria").unbind('mousedown');
		}

		if (isPermitidoSomenteFinanciamentoProprio()) {
			$("#financiamentoVendor").unbind('mousedown');
			isFinanciamentoVendor = false;
		} else {
			switchON($("#financiamentoVendor"));
			isFinanciamentoVendor = true;
		}

		if (isFinanVendor() && !isFinanciamentoVendor) {
			switchON($("#financiamentoVendor"));
			isFinanciamentoVendor = true;
		}

	} catch (err) {
		alert('[controlaOpcoesCliente]: '.concat(err.message));
	}
}

function isFinanVendor(tipoContratoVendor) {
	try {
		return getCliente().tipoContratoVendor == 2;
	} catch (err) {
		alert('[isFinanVendor]: '.concat(err.message));
	}
}

function isPermitidoSomenteTipoCobrancaBancaria() {
	try {
		return getCliente().tipoCobrancaCondicaoPagamento == 2;
	} catch (err) {
		alert('[isPermitidoSomenteTipoCobrancaBancaria]: '.concat(err.message));
	}
}

function isPermitidoSomenteFinanciamentoProprio() {
	try {
		return !getCliente().tipoContratoVendor;
	} catch (err) {
		alert('[isPermitidoSomenteTipoCobrancaBancaria]: '.concat(err.message));
	}
}

function falha(retorno) {
	try {
		BRQMob.log("Falha ao carregar condições de pagamento: ".concat(JSON.stringify(retorno)));
	} catch (err) {
		alert('[falha]: '.concat(err.message));
	}
}

function ordenarLista(lista) {
	try {
		var index;

		if (lista != undefined) {
			for (index = 0; index < lista.length; index++) {
				for ( var j = index + 1; j < lista.length; j++) {
					if (lista[index].numeroParcelasCondicao > lista[j].numeroParcelasCondicao) {
						x = lista[index];
						lista[index] = lista[j];
						lista[j] = x;
					}
				}
			}
		}

		loadListaCondPagamento(lista);
	} catch (err) {
		alert('[ordenarLista]: '.concat(err.message));
	}
}

function loadListaCondPagamento(condicaoPagamento) {
	try {
		limpaBodyTabela();
		var tbody_condicoes_pagamento = $("#body_condicoes_pagamento");
		var linha;

		if (condicaoPagamento != undefined) {
			$.each(condicaoPagamento, function(i, obj) {
				if (i == 0) {
					linha = $('<tr/>', {"class" : (i % 2 == 0 ? "" : "off ") + ("selected"), id : i, 
						onclick : 'tableTR(this);setOpcaoSelecionada(' + obj.codigoCondicaoPagamento + ', \''
						+ obj.descricaoCondicao + '\'' + ' );'});
					setOpcaoSelecionada(obj.codigoCondicaoPagamento, obj.descricaoCondicao);
				} else {
					linha = $('<tr/>', {"class" : (i % 2 == 0 ? "" : "off ") + 
						(jsonCondicaoPagamento && jsonCondicaoPagamento.codigo == obj.codigoCondicaoPagamento ? "selected" : ""),
						id : i, onclick : 'tableTR(this);setOpcaoSelecionada(' + obj.codigoCondicaoPagamento 
						+ ', \'' + obj.descricaoCondicao + '\'' + ');'});
				}

				div_link = $('<div/>', {});
				href_link = $('<a/>', {href : "javascript:void(0);"});
				$('<label/>', {"class" : 'title large', text : obj.codigoCondicaoPagamento}).appendTo(href_link);

				$(href_link).appendTo(div_link);

				$('<td/>', {style : "text-align:center;", text : obj.codigoCondicaoPagamento}).appendTo(linha);
				$('<td/>', {style : "text-align:left;", text : obj.descricaoCondicao}).appendTo(linha);
				$('<td/>', {style : "text-align:center;", text : obj.numeroParcelasCondicao}).appendTo(linha);
				$('<td/>', {style : "text-align:center;", text : obj.quantidadeDiaPrazo}).appendTo(linha);
				$('<td/>', {style : "text-align:center;", text : obj.periodicidade}).appendTo(linha);
				$('<td/>', {style : "text-align:right;", text : obj.valorLimiteMinimo}).appendTo(linha);
				$('<td/>', {style : "text-align:left;", text : obj.descricaoObservacaoCondicao}).appendTo(linha);
				$(linha).appendTo(tbody_condicoes_pagamento);

			});
		}
	} catch (err) {
		alert('[loadListaCondPagamento]: '.concat(err.message));
	}
}

function limpaBodyTabela() {
	try {
		$("#body_condicoes_pagamento").empty();
	} catch (err) {
		alert('[limpaBodyTabela]: '.concat(err.message));
	}
}

function setOpcaoSelecionada(codigoCondicao, descricao) {
	try {
		jsonCondicaoPagamento = new Object();
		jsonCondicaoPagamento.codigo = codigoCondicao;
		jsonCondicaoPagamento.descricao = descricao;
	} catch (err) {
		alert('[setOpcaoSelecionada]: '.concat(err.message));
	}
}

function controleCondicaoPagamento() {
	try {
		if (!jsonCondicaoPagamento) {
			alert('A escolha da condição de pagamento é obrigatória.');
		} else {
			var tmpCondicaoPagamento = sessionStorage.getObject(CONST_ITEM_CONDICAO_PAGAMENTO);

			if (tmpCondicaoPagamento != null && tmpCondicaoPagamento != undefined) {
				BRQMob.exec("sucessoValidarCondicaoPagamento", "falhaValidarCondicaoPagamento", VENDAS_DISPATCHER,
						"validarCondicaoPagamento", [
								tmpCondicaoPagamento.rowIndex,
								tmpCondicaoPagamento.codigoMercadoria,
								tmpCondicaoPagamento.condicaoPagamento,
								jsonCondicaoPagamento.codigo ]);

			} else {
				sessionStorage.setObject('condicaoPagamento', jsonCondicaoPagamento);
				nav(getPage('informacoesGerais'));
			}
		}
	} catch (err) {
		alert('[controleCondicaoPagamento]: '.concat(err.message));
	}
}

function sucessoValidarCondicaoPagamento(result) {
	try {
		var tmpCondicaoPagamento = new Object();
		tmpCondicaoPagamento.rowIndex = result.rowIndex;
		tmpCondicaoPagamento.codigoMercadoria = result.codigoMercadoria;
		tmpCondicaoPagamento.condicaoPagamento = result.condicaoPagamento;
		sessionStorage.setObject(CONST_ITEM_CONDICAO_PAGAMENTO, tmpCondicaoPagamento);
		BRQNavegacao.voltar();
	} catch (err) {
		alert('[sucessoValidarCondicaoPagamento]: '.concat(err.message));
	}
}

function falhaValidarCondicaoPagamento(result) {
	try {
		alert('Não foi possível validar condição de pagamento');
		sessionStorage.setObject(CONST_ITEM_CONDICAO_PAGAMENTO, tmpCondicaoPagamento);
		BRQNavegacao.voltar();
	} catch (err) {
		alert('[falhaValidarCondicaoPagamento]: '.concat(err.message));
	}
}

