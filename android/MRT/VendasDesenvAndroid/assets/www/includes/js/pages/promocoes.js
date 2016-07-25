var TBODY_ITENS = 'tbody-itens';
var CODIGO_CONDICAO_PAGAMENTO_PREMIO_GRATIS = 55;
//Table Column Hidden
var TDH = {
    'flgCalculado'    : 0,
    'codMercadoria'   : 1,
    'perAcrescimo'    : 2,
    'perDesconto'     : 3,
    'codigoPromocao'  : 4,
}

//Table Column Index
var TCI = {
    'hidden'          : 0,
    'info'            : 1,
    'codMarcacao'     : 2,
    'quantidade'      : 3,
    'descMercadoria'  : 4,
    'filFaturamento'  : 5,
    'tipEsgotamento'  : 6,
    'caixa'           : 7,
    'precoLiqImpFrt'  : 8,
    'valcaixaImpFrt'  : 9,
    'valUntImpFrt'    :10,
    'condPagmento'    :11,
    'valFrt'          :12,
    'simbolo'         :13,
    'notaFiscal'      :14,
    'tipNegociacao'   :15 
}

/*
 * objeto de controle da paginação mediante ao movimento do scroll 
 */
var pageControl = null;
function PageControl() {    
    var DEFAULT_PRIMEIRA_PAGINA = 1;
    var numPagina               = DEFAULT_PRIMEIRA_PAGINA;
    var finding                 = false;
    
    this.hold                   = function() { finding = true;  }
    this.free                   = function() { finding = false; }
    this.isPossible             = function() { return !finding; }
    
    this.getPage                = function() { return numPagina;}
    this.increment              = function() { if (!finding) numPagina++; }
    this.reset                  = function() { numPagina = DEFAULT_PRIMEIRA_PAGINA; }
    
    this.ajust                  = function(page) { if(numPagina < page) numPagina = page;}
}

/*
 * Primeira pesquisa deve utilizar a filtro comportamento de compras 
 */
var primeiraPesquisa               = true;

var VENDAS_DISPATCHER              = null;
var RELACIONAMENTO_DISPATCHER      = null;

var filiaisDisponiveis             = null;
var flagExecucaoAlteraFilial       = false;
var posicaoVetorFiliaisDisponiveis = 0;

var representante                  = null;

var quantidadeAnterior             = null;
/*
 * Variável deve corresponder ao última o botão quantidade que foi clicado.
 */
var ultimoBtnQtq                   = null;

/*
 * Indica se popup esta fechado ou aberto 
 */
var flgPopupDetalhePEE             = false;

/*
 * Váriável que irá receber os dados da última busca para que a mesma seja utilizada durante a paginação 
 */
var dadosUltimaPesquisa           = null;

//Variavel do componente Slider.
var slider                = null;
var sliderVaricao         = null;
var sliderValorItem       = null;
var sliderTimeOut         = null;
var ultimaLinhaSelecionda = null;

try {
    RELACIONAMENTO_DISPATCHER = "RelacionamentoDispatcher";
    VENDAS_DISPATCHER         = "VendasDispatcher";
    filiaisDisponiveis        = null;
    /*
     *  Na funcionalidade itens similiares as filiais disponíveis já se encontram no session stored
     */
    representante             = getRepresentante();
} catch(ex) {
    BRQMob.log(ex.description);
}

//Functions necessarias para o calculo de Preco
function getPosicaoVetorFilial(tr, moving) {
    try {        
        var cellFilialFaturamento = tr.children[TCI.filFaturamento];
        var spanFilialFaturamento = cellFilialFaturamento.children[0];
        
        var posicaoVetorFilial    = spanFilialFaturamento.textContent;
        if (moving) {
            posicaoVetorFilial++;
            if (filiaisDisponiveis.length == posicaoVetorFilial) {
                posicaoVetorFilial = 0;     
            }
        }
        return posicaoVetorFilial;
    } catch(err) {
        alert('[getPosicaoVetorFilial] - '.concat(err.message));
    }
}

function preencheInfo(element, value, defaultValue) {
    
    if (value == undefined || value == null) {
        value = defaultValue;
    }
    element.textContent = value;
}

var execControl = null;
function ExecControl() {
	var execListarSelectGrupoMercadoria = false;
	var execListarSelectUnidadeNegocio = false;
	var execListarSelectPromocoes = false;

	this.execGrupoMercadoria = function() {	execListarSelectGrupoMercadoria = true;	}
	this.execUnidadeNegocio = function() { execListarSelectUnidadeNegocio = true; }
	this.execSelectPromocoes = function() {	execListarSelectPromocoes = true;}

	this.isPossibleListaItemPromocao = function() {
		if(execListarSelectGrupoMercadoria) {
			return true;
		} else if(execListarSelectUnidadeNegocio) {
			return true;
		} else if(execListarSelectPromocoes) {
			return true;
		} else {
			return false;
		}
	};
}



$(document).ready(function() {
	
	BRQNavegacao.adicionarPilhaNavegacao('promocoes');
    /*
     * Verificar informações necessárias para o preenchimento da Tela
     */
    if (getCondicaoPagamento() == undefined){
        alert('Necessário selecionar Condição de Pagamento!');
        BRQNavegacao.voltar();
        return;
    }
    if (getModeloDistribuicao() == undefined){
        alert('Necessário selecionar Modelo de Distribuição!');
        BRQNavegacao.voltar();
        return;            
    }
	pageControl = new PageControl();
	execControl = new ExecControl();
	
	inicializarValores();
	
	sliderVaricao = document.getElementById('sliderVariacaoDesconto');
    sliderValorItem = document.getElementById('sliderValorItem');
    slider = new UISlider(
            $('#sliderSuperFlex').get(0), // component
            0,     // initial value
            0,     // max value
            0,     // min value
            0.01,  // modifier
            false, // snapping
            function(value) { sliderVaricao.value = value; }, // value callBack
            false, //is vertical
            false); //disabled
    
    slider.onEnd = function(value) {                                                                                 
    	        if (value == 0) {return;}
    	        if (value < 0) {
    	            ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perDesconto].textContent  = value;
    	            ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perAcrescimo].textContent = 0;
    	        } else {
    	            ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perDesconto].textContent  = 0;
    	            ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perAcrescimo].textContent = value;                     
    	        }
    
    	        calculaPreco(ultimaLinhaSelecionda, true);
            };
        
    $('#switch-caixa').mousedown(function() {
        var selecionado = $(this).hasClass("on") ? true : false;
        slider.disabled(selecionado);
        if (selecionado){
        	alterarValorUnitarioCaixa();
        	sliderVaricao.setAttribute('disabled', 'disabled');
            sliderValorItem.setAttribute('disabled', 'disabled');
        } else {
        	alterarValorUnitarioCaixa();
            sliderVaricao.removeAttribute('disabled');
            sliderValorItem.removeAttribute('disabled');
        }
    });   
	
	//BU
	$('#unidadeNegocio').change(function() {
		setDataFimPromocao();
        sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
		sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR);
		listarSelectPromocoes();
	});
	
	$('#detalhePromocao').change(function() {
		 setDataFimPromocao();
		 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
		 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR);
		 
		 var isBuscaPromocao = $("#switch-promocao").hasClass("selected") ? true : false;
		 
		 if(isBuscaPromocao){
		 	listarItensDisponiveisDaPromocao();	
		 }else{
		 	carregarPremiosDaPromocao();
		 }
		
	});
	
	
	$('#grupoMercadoria').change(function() {
		 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
     	 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR);
     	 listarSelectPromocoes();
	});
	
	$('#switch-promocao').click(function() {
		var codigoPromocaoSelecionado = getCodigoPromocao();
		var codigoPromocaoSessao = sessionStorage.getObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
		if(codigoPromocaoSelecionado != codigoPromocaoSessao){
		 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
     	 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR);
     	}
     	 listarSelectGrupoMercadoria();
	});
	
	$('#switch-premio').click(function() {
		var codigoPromocaoSelecionado = getCodigoPromocao();
		var codigoPromocaoSessao = sessionStorage.getObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
		if(codigoPromocaoSelecionado != codigoPromocaoSessao){
		 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
     	 sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR);
     	} 
     	 carregarPremiosDaPromocao();
	});
	
});

//Resize event
//$(window).resize(function() {
//	scrollDivResize();
//});

//Ajusta o tamanho da area de scroll
//function scrollDivResize(){
//	$('#wrapperScroll').height($(window).height()-$('#wrapperScroll').offset().top-$('#footer').height());
//}

function pagination() {
    try {
        if (pageControl.isPossible()) {
            pageControl.increment();
            dadosUltimaPesquisa.dadosPadrao.pesquisaAnterior = false;
            dadosUltimaPesquisa.dadosPadrao.numPagina        = pageControl.getPage();
            chamarPesquisaItens(dadosUltimaPesquisa);
        }
    } catch(err) {
        alert('[pagination] - '.concat(err.message));
    }
}

function inicializarValores() {
	listarSelectUnidadeNegocio();
	carregaFiliais();
}


function listarSelectPromocoes() {
	BRQMob.log("Entrando no método listarSelectPromocoes");
	execControl.execSelectPromocoes();
	var codigoEstadoDestino = getCliente().uf;
	var codigoCliente = getCliente().codigoCliente;
	var codigoFilialExpedicao = getFilialExpedicao().codigoFilial;
	var codigoFilialFaturamento = getFilialFaturamento().codigoFilial;
	var unidadeEstrategica = getUnidadeNegocio();
	
	BRQMob.exec("sucessoListarSelectPromocoes", "falhaListarSelectPromocoes", "VendasDispatcher", "listarPromocoes", [codigoCliente, codigoEstadoDestino, codigoFilialExpedicao, codigoFilialFaturamento, unidadeEstrategica]);
}


function listarItensDisponiveisDaPromocao() {
	BRQMob.log("Entrando no método listarItensDisponiveisDaPromocao");
	try {
    	//variavel que ira receber os parametros da pesquisa
		var dadosBusca;

    	var pesquisaAnterior              = sessionStorage.getObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR) == true ? true : false;
    	//sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR);
    	
        var cliente                       = getCliente();           //  codigoCliente
        var filialExpedicao               = getFilialExpedicao();   //  codigoFilialExpedicao
        var filialFaturamento             = getFilialFaturamento(); //  codigoFilialFaturamento
        var condicaoPagamento             = getCondicaoPagamento(); //  codigoCondicaoPagamento padrão
        var representante           	  = getRepresentante();
        var codigoPromocao				  = getCodigoPromocao(); 	//	funcao para recuperar o valor marcado no campo select  $('#detalhePromocao ')
        var codigoGrupoMercadoria 		  = getCodigoGrupoMercadoria();	//	funcao para recuperar o valor marcado no campo select  $('#grupoMercadoria ') 
        var data = {
            'dadosPadrao' : {
                'pesquisaAnterior'        : pesquisaAnterior,
                'primeiraPesquisa'        : primeiraPesquisa,
                'codigoFilialExpedicao'   : filialExpedicao.codigoFilial,
                'codigoFilialFaturamento' : filialFaturamento.codigoFilial,
                'representante'           : representante,
                'condicaoPagamento'       : condicaoPagamento,
                'cliente'                 : cliente,
                'numPagina'               : pageControl.getPage(),
                'codigoPromocao'		  : codigoPromocao                               
            },
            'dadosFiltro'  :  {
            	'codigoGrupoMercadoria'   : codigoGrupoMercadoria
            }
        }
    	
    	
    	if(codigoPromocao != ""){
	    	BRQMob.exec("sucessoListarItensDisponiveisDaPromocao", "falhaListarItensDisponiveisDaPromocao", "VendasDispatcher", "listarItensDisponiveisDaPromocao", [data]);
	    }	
    	
	} catch(err) {
        alert('[listarItensDisponiveisDaPromocao] - '.concat(err.message));
    }
}


function carregarPremiosDaPromocao() {
	BRQMob.log("Entrando no método carregarPremiosDaPromocao");
	//variavel que ira receber os parametros da pesquisa
	var dadosBusca;
	limpaSelectGrupoMercadoria();
	limpaTabelaItensDaPromocao();
	try {

    	/*
    	 * sessionStorage.getObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR) pode retornar true, false ou undefined
    	 */
    	var pesquisaAnterior              = sessionStorage.getObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR) == true ? true : false;
    	
        var cliente                       = getCliente();           //  codigoCliente
        var filialExpedicao               = getFilialExpedicao();   //  codigoFilialExpedicao
        var filialFaturamento             = getFilialFaturamento(); //  codigoFilialFaturamento
        var condicaoPagamento             = getCondicaoPagamento(); //  codigoCondicaoPagamento padrão
        var codigoPromocao				  = getCodigoPromocao(); 	//	funcao para recuperar o valor marcado no campo select  $('#detalhePromocao ') 
        
        var data = {
            'dadosPadrao' : {
                'pesquisaAnterior'        : pesquisaAnterior,
                'primeiraPesquisa'        : primeiraPesquisa,
                'codigoFilialExpedicao'   : filialExpedicao.codigoFilial,
                'codigoFilialFaturamento' : filialFaturamento.codigoFilial,
                'representante'           : representante,
                'condicaoPagamento'       : condicaoPagamento,
                'cliente'                 : cliente,
                'numPagina'               : pageControl.getPage(),
                'codigoPromocao'		  : codigoPromocao
            },
            'dadosFiltro'  :  dadosBusca == undefined || dadosBusca == null ? null : dadosBusca
        }
        dadosUltimaPesquisa = data;
        	
    	if(codigoPromocao != ""){
    		BRQMob.exec("sucessoCarregarPremiosDaPromocao", "falhaCarregarPremiosDaPromocao", VENDAS_DISPATCHER, "listarPremiosDaPromocao", [data]);
    	}else{
    		alert("Não existe promoção selecionada. ");
    	}
	} catch(err) {
        alert('[carregaItensDisponiveis] - '.concat(err.message));
    }
}


function apresentaPopupDetalhePEE(HTMLTableRowElement) {
    try {
    	BRQMob.log("Entrando no método apresentaPopupDetalhePEE");
        event.stopPropagation();
        if (!flgPopupDetalhePEE) {
            var currentTR               = HTMLTableRowElement;
                    
            var cliente                 = getCliente();
            
            var codigoMercadoria        = currentTR.children[TCI.hidden].children[TDH.codMercadoria].textContent;
            var isOrigemPromocao = $("#switch-promocao").hasClass("selected") ? "ITEMPROMOCAO" : "ITEMPROMOCAOPREMIO";
            
            var control = {
                'rowIndex'          : currentTR.rowIndex,
                'origem'            : isOrigemPromocao
            }
            var data = {
                'codigoCliente'     : cliente.codigoCliente,
                'codigoMercadoria'  : codigoMercadoria
            }
            BRQMob.exec("sucessoRecuperaDetalhePEE", "falhaRecuperaDetalhePEE", VENDAS_DISPATCHER, "recuperarDetalhePEE", [control, data]);
        }
    } catch (err) {
        alert('[apresentaPopupDetalhePEE()] - '.concat(err.message));
    }
}

function sucessoRecuperaDetalhePEE(result) {   
    try {
        var control = result.control;
        var currentTR = document.getElementById(TBODY_ITENS).children[control.rowIndex - 1];
        var cell   = currentTR.children[TCI.codMarcacao];
        var link   = cell.children[0];
        if (result.detalhePEE.length == 0) {
            link.textContent = ' - ';
           return; 
        }
        
        var divPopup   = document.getElementById('boxDetalhe');
        
        var count = divPopup.children.length - 1;
        while (count >= 5) {
            divPopup.removeChild(divPopup.children[count]);
            count--;
        }
        
        var detalhePEE = result.detalhePEE;
        link.textContent = detalhePEE[0].identidadeInformacaoPEE;
        
        divPopup.children[1].childNodes[1].textContent = '';
        divPopup.children[2].childNodes[1].textContent = '';
        
        for ( var i = 0, length = detalhePEE.length; i < length ; i++) {
            var reg  = detalhePEE[i];
            if (reg.identidadeInformacaoPEE == 'T') {
                divPopup.children[1].childNodes[1].textContent = reg.descontoAcaoTatica + ' %';
                divPopup.children[2].childNodes[1].textContent = reg.dataVigoracao;
            }
                        
            divPopup.appendChild(document.createElement('br'));
            var _span = document.createElement('span');
            _span.textContent = reg.identidadeInformacaoPEE + ' - ' + reg.descricaoInformacaoPEE;
            divPopup.appendChild(_span);
                
        }
        detalhesPEE();
    } catch (err) {
        alert('[sucessoRecuperaDetalhePEE()] - '.concat(err.message));
    }
}

function falhaRecuperaDetalhePEE(result) {
    apresentaMensagemErro(result.msg, true);
}

function listarSelectGrupoMercadoria() {
	BRQMob.log("Entrando no método listarSelectGrupoMercadoria");
	execControl.execGrupoMercadoria();
	BRQMob.exec("sucessoListarSelectGrupoMercadoria", "falhaListarSelectGrupoMercadoria", "VendasDispatcher", "listarGrupoMercadoria", []);
}


function listarSelectUnidadeNegocio() {
	BRQMob.log("Entrando no método listarSelectUnidadeNegocio");
	execControl.execUnidadeNegocio();
	BRQMob.exec("sucessoListarSelectUnidadeNegocio", "falhaListarSelectUnidadeNegocio", "VendasDispatcher", "listarUnidadeNegocio", []);
}


function sucessoListarSelectPromocoes (retorno) {
	montarDetalhesPromocao(retorno.promocoes);
}


function sucessoListarSelectUnidadeNegocio (retorno) {
	montarSelectUnidadeNegocio(retorno.listaUnidadeNegocio);
}


function sucessoListarSelectGrupoMercadoria (retorno) {
	montarSelectGrupoMercadoria(retorno.listaGrupoMercadoria);
}


function sucessoListarItensDisponiveisDaPromocao (retorno) {
	try{	
		sessionStorage.setObject(CONST_ITEM_DISPONIVEL_PROMOCAO_RECUPERA_PESQUISA_ANTERIOR, true);
		if(retorno.listaItensPromocao == undefined || retorno.listaItensPromocao.length <=0) {
			limpaTabelaItensDaPromocao();
			alert("Nenhuma Mercadoria foi encontrada !!!");	
			
		}else{
			preparaTabela(retorno.listaItensPromocao);
		}
	} catch(err) {
        alert('[sucessoListarItensDisponiveisDaPromocao] - '.concat(err.message));
    }
}


function sucessoCarregarPremiosDaPromocao (retorno) {
	
	sessionStorage.setObject(CONST_ITEM_DISPONIVEL_PROMOCAO_PREMIO_RECUPERA_PESQUISA_ANTERIOR, true);
	
	if(retorno.listaPremiosPromocao == undefined || retorno.listaPremiosPromocao.length <=0) {
		limpaTabelaItensDaPromocao();
		alert("Nenhum prêmio foi encontrado !!!");	
		
	}else{
		preparaTabela(retorno.listaPremiosPromocao);
	}
}


function preparaTabela(listaItensPromocao){
	limpaTabelaItensDaPromocao();
  	carregarListaItensDisponiveisDaPromocao(listaItensPromocao);
}

function carregarListaItensDisponiveisDaPromocao(listaItensPromocao) { 
	var start = new Date().getMilliseconds();  
      try {
		removeMensagemSucessoErro();
   
		
        primeiraPesquisa = false;
        
		var numeroMaximoNotaFiscal = representante.numeroMaximoNotaFiscal;
    
        var template_table         = document.getElementById("table_model_item_desc");
		// Montar SELECT de Nota Fiscal                 
       	var selectNF = template_table.children[0].children[0].children[TCI.notaFiscal].children[0].children[0];
        for (var i=1; i <= numeroMaximoNotaFiscal; i++) {
            var element = document.createElement('option');
            element.setAttribute('value', i);
            element.textContent =  i;
            selectNF.appendChild(element);
        }
    
      
        var tbody_itens    = document.getElementById(TBODY_ITENS);
     
    	
    	var itens = listaItensPromocao;
    	
    	if ((itens == undefined || itens.length == 0) && pageControl.getPage() == 1) {
            apresentaMensagemErro('Nenhum item encontrado!',true);
            return;
        }
	

	for (var index = 0, length = itens.length; index < length; index++) {
    		
    		var comboBox     = null;
    				
    		var item         = itens[index];
    		var mercadoria   = item.mercadoria;
    		var preco        = item.preco;
    		var promocao	 = item.promocao;
    		
			// Montar Dados da Tabela

            var tr = template_table.children[0].children[0].cloneNode(true);

      	



    
            // ////////////////////////////////////////////
            // [INICIO] Montar Dados do Icone de Informação
            // ////////////////////////////////////////////
            var ul = tr.children[TCI.info].children[0];
            var li = ul.children[0];
            li.setAttribute('id', 'li_item_'.concat(index));
    
            var a_info = li.children[0];
            var aInfoFunctionOnClick = a_info.onclick;
            a_info.onclick = function() {                    
 				var li       = this.parentNode;


                var selected = li.getAttribute('class');
               
				 /*
                 * Chamar a rotina de calculo antes de apresentar a linha de informalção sobre o item  
                 */
                if (selected == null || selected.indexOf('selected') == -1) {
                    try {
                        var TR = document.getElementById(TBODY_ITENS).children[index];
                        calculaPreco(TR, true);
                    } catch (err) {
                        alert('[aInfoFunctionOnClick] - '.concat(err.message));
                    }
                }                
                aInfoFunctionOnClick.call(this, event);
            };
    
            var a_btn_gongola = li.children[1].children[1].children[0];
            a_btn_gongola.setAttribute('id', 'btn_gongola_'.concat(index));
    
            var div_info      = li.children[1];
            div_info.setAttribute('id', 'div_info_'.concat(index));
            
            var div           = li.children[1].children[0];
            
            var labelCodProd  = div.children[0];
            var labelNomProd  = div.children[3].children[0];
            
            if (index % 2 != 0) { li.className = 'off'; }
            labelCodProd.textContent = 'Cód:'.concat(mercadoria.codigo);
            labelNomProd.textContent = mercadoria.descricao;
            if (mercadoria.indicaMercadoriaKit == 1) {
            	div.children[1].children[0].setAttribute('class','icon kit');
                div.children[1].children[0].onclick = function (){detalharKit(this)};
            }
            if (promocao.codigo != undefined) {
             	div.children[1].children[0].setAttribute('class','icon promo');
                div.children[1].children[0].onclick = function (){detalhePromocao(this)};
               
            }
          
            ////////////////////////////////////////////////
            // [FIM] Montar Dados do Icone de Informação //
            // ////////////////////////////////////////////
 			// ////////////////////////////////////////////
            // [INICIO] Montar TD de Dados Escondidos    //
            // ////////////////////////////////////////////
            var valorLiquido = new Number(item.valorLiquidoMercadoria.replace('.','').replace(',','.'));
            tr.children[TCI.hidden].children[TDH.flgCalculado].textContent  = valorLiquido > 0;
            tr.children[TCI.hidden].children[TDH.codMercadoria].textContent = mercadoria.codigo;
            tr.children[TCI.hidden].children[TDH.perDesconto].textContent   = item.percentualDescontoConcedido;
            tr.children[TCI.hidden].children[TDH.perAcrescimo].textContent  = item.percentualAcrescimoConcedido;
            tr.children[TCI.hidden].children[TDH.codigoPromocao].textContent  = promocao.codigo ;
            
            // ////////////////////////////////////////////
            // [FIM] Montar TD de Dados Escondidos       //
            // ////////////////////////////////////////////            
            if (index % 2 != 0) { tr.className = 'off'; }
                        
            if (!isNaN(preco.tipoMarcacaoRepresentante)) {
                 
                tr.children[TCI.codMarcacao].children[0].textContent = preco.tipoMarcacaoRepresentante;
                 
            } else {
                
                tr.children[TCI.codMarcacao].removeChild(tr.children[1].children[0]);    
            }
            
                var a_quantidade = tr.children[TCI.quantidade].children[0];
                a_quantidade.textContent = isNaN(item.quantidadeMercadoria) ? 0 : item.quantidadeMercadoria;
                
				a_quantidade.setAttribute('id','qtd'.concat(index));

                var functionClick = a_quantidade.onclick;
                a_quantidade.onclick = function() {                    
                    
                    event.stopPropagation();
                    
                    tableTR(this.parentNode.parentNode);
                    
                    quantidadeAnterior = document.getElementById('qtd').value;
                    
                    ultimoBtnQtq = this;
                                        
                    functionClick.call(this, event);
                };
                
            tr.children[TCI.descMercadoria].children[0].children[0].textContent = 'Cód:'.concat(mercadoria.codigo);
            tr.children[TCI.descMercadoria].children[0].children[3].textContent = mercadoria.descricao;

			if (mercadoria.indicaMercadoriaKit == FLAG_MARTINS_TRUE) {              
                tr.children[TCI.descMercadoria].children[0].children[1].children[0].setAttribute('class','icon kit');               
	            tr.children[TCI.descMercadoria].children[0].children[1].children[0].onclick = function (){detalharKit(this)};               
            }
            
            if (mercadoria.temRestricaoBeneficioCustomizado == FLAG_MARTINS_TRUE) {
                tr.children[TCI.descMercadoria].children[0].children[1].setAttribute('href','javascript:void(0)');
                tr.children[TCI.descMercadoria].children[0].children[1].children[0].setAttribute('class','icon cadeado');
            }
            
            if (promocao.codigo != undefined) {
                tr.children[TCI.descMercadoria].children[0].children[1].children[0].setAttribute('class','icon promo');
                tr.children[TCI.descMercadoria].children[0].children[1].children[0].onclick = function (){detalhePromocao(this)}; 
            }
    
            if (item.codigoFilialFaturamento == undefined) { 
                tr.children[TCI.filFaturamento].children[0].textContent = 0;
                tr.children[TCI.filFaturamento].children[1].textContent = getFilialFaturamento().codigoFilial;
            } else {
                tr.children[TCI.filFaturamento].children[0].textContent = posicaoVetorFiliaisDisponiveis;
                tr.children[TCI.filFaturamento].children[1].textContent = item.codigoFilialFaturamento;
            }
            
            tr.children[TCI.tipEsgotamento].textContent = mercadoria.tipoEsgotamento == undefined ? '' : mercadoria.tipoEsgotamento;
            
            tr.children[TCI.caixa].textContent = mercadoria.quantidadeCaixaFornecedor;
            
            tr.children[TCI.condPagmento].children[0].textContent = item.condicaoPagamento.codigoCondicaoPagamento;

            tr.children[TCI.simbolo].textContent = preco.codigoSimboloSituacao != undefined ? preco.codigoSimboloSituacao : '';
            
            // Coluna NF
            comboBox = tr.children[TCI.notaFiscal].children[0].children[0];
            comboBox.value = item.notaFiscal;            
            
            //Coluna Negociação
            comboBox = tr.children[TCI.tipNegociacao].children[0].children[0];
            if (!isNaN(mercadoria.temRestricaoBeneficioCustomizado) && itens[index].indicaRestricaoBeneficioCustomizado == 1) {
                comboBox.removeChild(comboBox.children[1]);
            }else if ($("#switch-promocao").hasClass("selected") ? false : true) {
            	comboBox.removeChild(comboBox.children[1]);
            	comboBox.removeChild(comboBox.children[0]);

	            var element = document.createElement('option');
	            element.setAttribute('value', item.tipoNegociacaoMercadoria);
	            element.textContent =  item.mercadoria.descricaoNegocioMercadoria;
	            comboBox.appendChild(element);	
    
            	
            }
            comboBox.value = item.tipoNegociacaoMercadoria;
           
            ////////////////////////////////////////////////////
            //// CAMPOS CALCULADOS
            preencheInfo(tr.children[TCI.precoLiqImpFrt],  item.valorLiquidoComImposto,  '');
            preencheInfo(tr.children[TCI.valcaixaImpFrt],  item.valorCaixaComImposto,    '');
            preencheInfo(tr.children[TCI.valUntImpFrt]  ,  item.valorUnitarioComImposto, '');
            preencheInfo(tr.children[TCI.valFrt]        ,  item.frete, '');           
            ////////////////////////////////////////////////////
            
            //Appendando linha no tbody
            tbody_itens.appendChild(tr);                        
        }
        
        var quantLinhas = tbody_itens.children.length;
        
        // Atualizacao da quantidade             
        document.getElementById("span-qtd-total").textContent = quantLinhas;

        // Mantendo Controle de Paginação
        var page = parseInt(quantLinhas / listaItensPromocao.tamanhoPagina) + (quantLinhas % listaItensPromocao.tamanhoPagina == 0 ? 0 : 1);
        pageControl.ajust(page);
        
        /*
         * Alterar condicao pagamento e calcular preco 
         */
        var tmpCondicaoPagamento = sessionStorage.getObject(CONST_ITEM_CONDICAO_PAGAMENTO);
        if (tmpCondicaoPagamento != null && tmpCondicaoPagamento != undefined) {
            sessionStorage.removeObject(CONST_ITEM_CONDICAO_PAGAMENTO);
            
            var currentTR = tbody_itens.children[tmpCondicaoPagamento.rowIndex - 1];
            currentTR.children[TCI.condPagmento].children[0].textContent = tmpCondicaoPagamento.condicaoPagamento;
            currentTR.children[TCI.hidden].children[TDH.flgCalculado].textContent = false;
            clickItem(currentTR);
        }
    } catch(err) {
        alert('[sucessocarregaItens] - '.concat(err.message));
    } finally {
        pageControl.free();
         BRQMob.log('Tempo Sucesso Carrega Itens: ' + (new Date().getMilliseconds() - start));
    }
}

function limpaTabelaItensDaPromocao(){
	try{
		 var tbody      = document.getElementById(TBODY_ITENS);
        var count      = tbody.children.length - 1;
        while (count >= 0) {
            tbody.removeChild(tbody.children[count]);
            count--;
        }
        
        document.getElementById('label-header-comissao').children[0].innerHTML  = '';       
        document.getElementById('label-header-pontos').children[0].innerHTML    = '';    
        document.getElementById('label-header-precoLiq').children[0].innerHTML  = '';
        document.getElementById('label-header-cad').children[0].innerHTML       = '';
        document.getElementById('label-header-incentivo').children[0].innerHTML = '';
        document.getElementById('label-header-icm').children[0].innerHTML       = '';
        
        document.getElementById('span-qtd-total').textContent                   = '';
        document.getElementById('span-qtd-pedidos').textContent                 = '';
        document.getElementById('span-comissao-total').textContent              = '';
        document.getElementById('span-valor-total').textContent                 = '';
        
        pageControl.reset();
                
    }catch(err) {
        alert('[limpaTabelaItensDaPromocao] - '.concat(err.message));
    }
}




function clickItem(HTMLTableRowElement) {
	BRQMob.log("Entrando no método clickItem");	
	tableTR(HTMLTableRowElement);
    try {
        ultimaLinhaSelecionda    = HTMLTableRowElement;        
        var flagCalculado        = ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.flgCalculado];
        
        var sliderValue = 0;
        var percentualDesconto   = ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perDesconto].textContent;
        var percentualAcrescimo  = ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perAcrescimo].textContent;
        
        if (!isNaN(percentualDesconto) && percentualDesconto < 0) { sliderValue = percentualDesconto; }
        if (!isNaN(percentualAcrescimo) && percentualAcrescimo > 0) { sliderValue = percentualAcrescimo; }

		var isOrigemPromocao = $("#switch-promocao").hasClass("selected") ? "ITEMPROMOCAO" : "ITEMPROMOCAOPREMIO";

        sliderVaricao.value = sliderValue;
        slider.value(parseFloat(sliderValue));
        slider.disabled(false);
        
        if (flagCalculado.textContent == 'false') {
            calculaPreco(HTMLTableRowElement, false);
        } else {
            var control = {
                'rowIndex': ultimaLinhaSelecionda.rowIndex,
                'origem': isOrigemPromocao        
            };
            var data = {
                'codigoMercadoria': ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.codMercadoria].textContent
            };
            BRQMob.exec("sucessoObtemItem", "falhaObtemItem", VENDAS_DISPATCHER, "obtemItem", [control, data]);
        }
    } catch (err) {
        BRQMob.log('[clickItem]: ' + err.message);
    }
}

function falhaObtemItem(result) {
    try {
        BRQMob.log(result);
    } catch(err) {
        alert('[falhaObtemItem] - '.concat(err.message));
    } 
}

function sucessoObtemItem(result) {
    try {
        var control    = result.control;
        var item       = result.item;       
        var currentTR  = document.getElementById(TBODY_ITENS).children[control.rowIndex - 1];
        
        renderizaDadosPrecoItem(currentTR, item);
    } catch(err) {
        alert('[sucessoObtemItem] - '.concat(err.message));
    } 
}
function montarDetalhesPromocao(listaPromocao) {
	try {  
		limpaSelectDadosPromocao();
		
		var _div = document.getElementById('detalhePromocao');
		var _select = $('<select/>',{'style': 'width: 500px;'});
		
		//var listarItensPorCodigoPromocao = sessionStorage.getObject(CONST_ITEM_DISPONIVEL_PROMOCAO_CODIGO_BUSCA);
		//sessionStorage.removeObject(CONST_ITEM_DISPONIVEL_PROMOCAO_CODIGO_BUSCA);
		if(listaPromocao == undefined) {
			$('<option/>',{text : ''}).appendTo(_select);
			$(_select).appendTo(_div);
			alert("Nenhuma Promoção foi encontrada.");
			return;
		}else {
			for(var index = 0, length = listaPromocao.length; index < length; index +=1) {
				
			//	if(listaPromocao[index].codigo == listarItensPorCodigoPromocao){
			//		$('<option/>',{value : listaPromocao[index].dataTerminoPromocao, text : listaPromocao[index].codigo +' - ' + listaPromocao[index].descricaoPromocao, 'selected':'selected'  }).appendTo(_select);
			//	}else{
					$('<option/>',{value : listaPromocao[index].dataTerminoPromocao, text : listaPromocao[index].codigo +' - ' + listaPromocao[index].descricaoPromocao  }).appendTo(_select);	
			//	}
			}
		} 
		$(_select).appendTo(_div);
		setDataFimPromocao();
		if(execControl.isPossibleListaItemPromocao()){
			listarItensDisponiveisDaPromocao();	
		}
		
		
	}catch(err) {
		alert('[montarDetalhesPromocao] - '.concat(err.message));
	}

}


function montarSelectGrupoMercadoria(listaGrupoMercadoria){
	limpaSelectGrupoMercadoria();
	
	var _div = document.getElementById('grupoMercadoria');
	var _select = $('<select/>',{'style': 'width: 250px;'});
	 
	 	$('<option/>',{style:'text-align: left',value : 0, text : "Grupos De Mercadorias"}).appendTo(_select);
		for(var index = 0, length = listaGrupoMercadoria.length; index < length; index +=1){
			//$('<option/>',{style:'text-align: left',value : listaGrupoMercadoria[index].codigoGrupoMercadoria, text : listaGrupoMercadoria[index].codigoGrupoMercadoria +' - ' + listaGrupoMercadoria[index].descricaoGrupoMercadoria  }).appendTo(_select);
			$('<option/>',{style:'text-align: left',value : listaGrupoMercadoria[index].codigoGrupoMercadoria, text : listaGrupoMercadoria[index].descricaoGrupoMercadoria  }).appendTo(_select);		
		}
	
	$(_select).appendTo(_div);
	//createSelect(_select);
	listarSelectPromocoes();
}


function montarSelectUnidadeNegocio(listaUnidadeNegocio){
	limpaSelectUnidadeNegocio();
	
	var _div = document.getElementById('unidadeNegocio');
	var _select = $('<select/>',{'style': 'width: 96px;'});
	 
	 	$('<option/>',{style:'text-align: left',value : 0, text : "Todas"}).appendTo(_select);
		for(var index = 0, length = listaUnidadeNegocio.length; index < length; index +=1){
			$('<option/>',{style:'text-align: left',value : listaUnidadeNegocio[index].codigoUnidadeEstrategica, text : listaUnidadeNegocio[index].descricaoAbreviaturaEstrategica  }).appendTo(_select);		
		}
	$(_select).appendTo(_div);
	//createSelect(_select);
	
	listarSelectGrupoMercadoria();
}


function carregaFiliais(){ 
    try {
    	BRQMob.log("Entrando no método carregaFiliais");
        filiaisDisponiveis = getFiliaisDisponiveis();
        if (filiaisDisponiveis == null || filiaisDisponiveis == undefined) {
            
            var codigoCliente = getCliente().codigoCliente;
            var terriorioId   = getCliente().codigoTerritorio;
            
            BRQMob.exec("sucessoCarregaFiliais", "falhaCarregaFiliais", VENDAS_DISPATCHER, "listaFiliais", [codigoCliente, terriorioId]);
        } else {
            
            posicionaFiliais(filiaisDisponiveis);
          
        }
    } catch(err) {
        alert('[carregaFiliais] - '.concat(err.message));
    }
}


function sucessoCarregaFiliais(result) {
    try {
        sessionStorage.setObject(CONST_LISTA_FILIAIS, result.filiais);
        filiaisDisponiveis = getFiliaisDisponiveis();
        
        posicionaFiliais(filiaisDisponiveis);       
    } catch(err) {
        alert('[sucessoCarregaFiliais] - '.concat(err.message));
    }   
    	listarItensDisponiveisDaPromocao();
}


function posicionaFiliais(filiaisDisponiveis) {
    try {
        var filialExpedicao    = getFilialExpedicao().codigoFilial;
        var filialFaturamento  = getFilialFaturamento().codigoFilial;
        
        //Posicioção index do Vetor de Filiais Disponíveis
        for(var count = 0, size = filiaisDisponiveis.length; count < size; count++) {
            var exp = filiaisDisponiveis[count].filialExpedicao.codigoFilial;
            var fat = filiaisDisponiveis[count].filialFaturamento.codigoFilial;
            if (exp == filialExpedicao && fat == filialFaturamento) {
                posicaoVetorFiliaisDisponiveis = count;
                break;
            }
        }    
    } catch(err) {
        alert('[posicionaFiliais] - '.concat(err.message));
    }   
}

function alterarValorUnitarioCaixa(HTMLTableRowElement) {
try {  
		
        if (ultimaLinhaSelecionda == null || ultimaLinhaSelecionda == undefined ) {
            ultimaLinhaSelecionda = HTMLTableRowElement;
        }
        var group      = document.getElementById('ul-unitario-caixa');
        var quantidadeCaixa = ultimaLinhaSelecionda.children[TCI.caixa].textContent;
        var isCaixaSelecionada = $('#switch-caixa').hasClass("on") ? true : false; 

        if (group.children[0].className.indexOf('selected') != -1) {
            sliderValorItem.value = ultimaLinhaSelecionda.children[TCI.valUntImpFrt].innerHTML;
        }

        if (group.children[1].className.indexOf('selected') != -1) {
            sliderValorItem.value = ultimaLinhaSelecionda.children[TCI.precoLiqImpFrt].innerHTML;
        }

        //campos Caixa e Unitário selecionados
        if (isCaixaSelecionada && quantidadeCaixa!=1 && group.children[0].className.indexOf('selected') != -1) {
        
            sliderValorItem.value = ultimaLinhaSelecionda.children[TCI.valcaixaImpFrt].innerHTML;
        
        //campos Caixa e Martins selecionados com quantidade caixa igual a "1"
        } else if(isCaixaSelecionada && quantidadeCaixa==1 && group.children[1].className.indexOf('selected') != -1) {
        
            sliderValorItem.value = ultimaLinhaSelecionda.children[TCI.precoLiqImpFrt].innerHTML;
            
        //campos Caixa e Martins selecionados
        } else if(isCaixaSelecionada && quantidadeCaixa!=1 && group.children[1].className.indexOf('selected') != -1) {
            
            valor =  ultimaLinhaSelecionda.children[TCI.valcaixaImpFrt].innerHTML;          
            tamanho = valor.length;
            sliderValorItem.value = valor.substring(0,tamanho-2);
        }
    } catch(err) {
        alert('[alterarValorUnitarioCaixa] - '.concat(err.message));
    }
}


function calculaPreco(HTMLTableRowElement,executarSlider, incluirNoPedido) {
    try {
    	BRQMob.log("Entrando no método calculaPreco");
  		if(HTMLTableRowElement == null) return;
		else if(HTMLTableRowElement.children[TCI.condPagmento].children[0].textContent ==  CODIGO_CONDICAO_PAGAMENTO_PREMIO_GRATIS) return;
        var currentTR               = HTMLTableRowElement;
        
        var cliente                 = getCliente();
        
        var representante           = getRepresentante();
        
        var modeloDistribuicao      = getModeloDistribuicao();
        
 
        var codigoMercadoria        = currentTR.children[TCI.hidden].children[TDH.codMercadoria].textContent;
        
        var quantidadeMercadoria    = currentTR.children[TCI.quantidade].children[0].textContent;
       	if (isNaN(quantidadeMercadoria)) { quantidadeMercadoria = 0; }
    
        var filiais                 = filiaisDisponiveis[getPosicaoVetorFilial(currentTR)];
        var siglaEstadoOrigem       = filiais.siglaEstadoOrigem;
        var siglaEstadoDestino      = filiais.siglaEstadoDestino;
        var filialFaturamento       = filiais.filialFaturamento.codigoFilial;
        var filialExpedicao         = filiais.filialExpedicao.codigoFilial;
       
       	var condicaoPagamento       = currentTR.children[TCI.condPagmento].children[0].textContent;
       
        var notaFiscal              = currentTR.children[TCI.notaFiscal].children[0].children[0].value;
        
        var comboTipoNegociacao     = currentTR.children[TCI.tipNegociacao].children[0].children[0];
        var dscTipoNegociacao       = comboTipoNegociacao.textContent; 
        var codTipoNegociacao       = comboTipoNegociacao.value;

        var percentualDesconto      = HTMLTableRowElement.children[TCI.hidden].children[TDH.perDesconto].textContent;
        var percentualAcrescimo     = HTMLTableRowElement.children[TCI.hidden].children[TDH.perAcrescimo].textContent;
        
       var isOrigemPromocao = $("#switch-promocao").hasClass("selected") ? "ITEMPROMOCAO" : "ITEMPROMOCAOPREMIO";
        
	    incluirNoPedido = incluirNoPedido || quantidadeMercadoria > 0 ? true : false;
			
		var control = {
            'rowIndex'             : currentTR.rowIndex,
            'origem'               : isOrigemPromocao,
 			'executarSlider'       : executarSlider,
            'incluirNoPedido'      : incluirNoPedido                
        };

        var data = {
            'codigoMercadoria'           : codigoMercadoria,
            'codigoCondicaoPagamento'    : condicaoPagamento,
            'codigoFilialExpedicao'      : filialExpedicao,
            'codigoFilialFaturamento'    : filialFaturamento,
			'siglaEstadoOrigem'          : siglaEstadoOrigem,
            'siglaEstadoDestino'         : siglaEstadoDestino,
            'codigoCliente'              : cliente.codigoCliente,
            'representante'              : representante,
            'codigoModeloDistribuicao'   : modeloDistribuicao.codigo,
            'tipoPedido'                 : getPedidoBase().info.tipoPedido,
            'quantidadeMercadoria'       : quantidadeMercadoria,
            'codigoTipoNegociacao'       : codTipoNegociacao,
            'notaFiscal'                 : notaFiscal,
            'descontoBeneficio'          : '0',
			'percentualAcrescimo'        : percentualAcrescimo,
            'percentualDesconto'         : percentualDesconto
        };

        BRQMob.exec("sucessoCalculaPreco", "falhaCalculaPreco", VENDAS_DISPATCHER, "calculaPreco", [control, data]);
			
    } catch(err) {
        alert('[calculaPreco] - '.concat(err.message));
    }
}	


function renderizaDadosPrecoItem(currentTR, item) {
    try {
        var preco      = item.preco;
        var mercadoria = item.mercadoria;
        
        if (mercadoria.temRestricaoBeneficioCustomizado == FLAG_MARTINS_TRUE) {
            preparaIcone('append', currentTR, 'icon cadeado', '#');
        } else {
            preparaIcone('remove', currentTR, 'icon cadeado', '#');
        }
        
        preencheInfo(currentTR.children[TCI.hidden].children[TDH.perDesconto],  item.percentualDescontoConcedido,  0);
        preencheInfo(currentTR.children[TCI.hidden].children[TDH.perAcrescimo], item.percentualAcrescimoConcedido, 0);
            
        sliderValorItem.value = document.getElementById('ul-unitario-caixa').children[0].className.indexOf('selected') != -1 ? item.valorUnitarioComImposto : item.valorCaixaComImposto;
    
        preencheInfo(currentTR.children[TCI.precoLiqImpFrt], item.valorLiquidoComImposto, '');
        preencheInfo(currentTR.children[TCI.valcaixaImpFrt], item.valorCaixaComImposto,   '');
        preencheInfo(currentTR.children[TCI.valUntImpFrt]  , item.valorUnitarioComImposto,'');
        preencheInfo(currentTR.children[TCI.valFrt]        , item.frete, '');

        preencheInfo(currentTR.children[TCI.simbolo]       , preco.codigoSimboloSituacao, '');
        
        var comissao = item.comissao.comissaoMercadoria != undefined ? item.comissao.comissaoMercadoria : '';
        document.getElementById('label-header-comissao').children[0].innerHTML  = comissao;       
        
        var quantidadeMercadoria    = currentTR.children[TCI.quantidade].children[0].textContent;
        if (isNaN(quantidadeMercadoria)) { quantidadeMercadoria = 0; }
        
        var ponto = item.indicaItemImune == 1 ? 'Imune' : item.valorPontoMercadoria;
        ponto = item.indicaItemImune != 1 && quantidadeMercadoria > 0 ?  ponto * quantidadeMercadoria : ponto;  
        
        document.getElementById('label-header-pontos').children[0].innerHTML    = ponto;
        
        document.getElementById('label-header-precoLiq').children[0].innerHTML  = item.valorLiquidoMercadoria;
        
        document.getElementById('label-header-cad').children[0].innerHTML       = item.codigoFilialExpedicao;
        
        var tipoIncentivoMercadoria = item.preco.tipoIncentivoMercadoria != undefined ? item.preco.tipoIncentivoMercadoria : '';
        document.getElementById('label-header-incentivo').children[0].innerHTML = tipoIncentivoMercadoria;
        
        document.getElementById('label-header-icm').children[0].innerHTML       = item.percentualICM;
        
    } catch (err) {
        BRQMob.log('[renderizaDadosPrecoItem]:' + err.message);
    }
}

function preparaIcone(appendOrRemove, currentTR, classIcon, href) {
    try {
        var div   = currentTR.children[TCI.descMercadoria].children[0];

        for (var index = 0, length = div.children.length; index < length; index++) {
            var element = div.children[index];

            if (element.tagName == 'A') {
                if (element.children[0].getAttribute('class') == classIcon) {
                    if (appendOrRemove == 'remove') {
                        div.removeChild(element);
                    }                    
                    return;
                }
            }
        }

        if (appendOrRemove == 'append') {

            var a    = document.createElement('a');
            var span = document.createElement('span');
            
            a.setAttribute('class', 'left');
            a.setAttribute('href', href);
                
            span.setAttribute('class', classIcon);
            a.appendChild(span);
        
            div.insertBefore(a, div.children[2]);
        }
          
    } catch (err) {
        alert('[preparaIcone()] - '.concat(err.message));
    }       
}


function sucessoCalculaPreco(result) {
    var start = new Date().getMilliseconds();
    try {     	
        BRQMob.log(JSON.stringify(result));
        
        var control    = result.control;
        var item       = result.item;
        var currentTR  = document.getElementById(TBODY_ITENS).children[control.rowIndex - 1];
        
        renderizaDadosPrecoItem(currentTR, item);
        
        alterarValorUnitarioCaixa(currentTR);
               
        // SLIDER
        if (!control.executarSlider) {
            slider.min(item.desconto.percentualMaximoDesconto * (-1));
            slider.max(item.desconto.percentualMaximoAcrescimo);
            slider.disabled(false);
            slider.update();
            sliderVaricao.removeAttribute('disabled', 'disabled');
        }
        
        currentTR.children[TCI.hidden].children[TDH.flgCalculado].textContent = true;
        
    } catch(err) {
        alert('[sucessoCalculaPreco] - '.concat(err.message));
    } finally {
        BRQMob.log('Tempo Sucesso Calculo Preço: ' + (new Date().getMilliseconds() - start));
    }
    
}


function alteraFilial(tr, stopPropagation) {
	BRQMob.log("Entrando no método alteraFilial");
 	tableTR(tr); // Marca linha selecionada
    
    if (!flagExecucaoAlteraFilial) {           
        flagExecucaoAlteraFilial = true;
    
        try {
            var btnFilial = tr.children[TCI.filFaturamento].children[1];
            btnFilial.onclick = null;
            
            if (event != undefined && (stopPropagation == undefined || stopPropagation)) {
                // Evitar que seja executado o click da linha
                event.stopPropagation();
            }
            //
            var posicaoVetorFilial = getPosicaoVetorFilial(tr, true);
            while (parseInt(btnFilial.textContent) == filiaisDisponiveis[posicaoVetorFilial].filialFaturamento.codigoFilial) {
                btnFilial.textContent = getPosicaoVetorFilial(tr, true);
            }
            //
            var cliente                 = getCliente();
            var representante           = getRepresentante();
            
            // Filial Service
            var filiais           = filiaisDisponiveis[posicaoVetorFilial];
            var filialFaturamento = filiais.filialFaturamento;    
            var filialExpedicao   = filiais.filialExpedicao;
            
            var codigoMercadoria  = tr.children[TCI.hidden].children[TDH.codMercadoria].textContent;
     
            btnFilial.textContent = '...';
            
            var condicaoPagamento = tr.children[TCI.condPagmento].children[0].textContent;
			var codigoGrupoMercadoria 		  = getCodigoGrupoMercadoria();	//	funcao para recuperar o valor marcado no campo select  $('#grupoMercadoria ')   
			var codigoPromocao				  = getCodigoPromocao();
			
            var isOrigemPromocao = $("#switch-promocao").hasClass("selected") ? "ITEMPROMOCAO" : "ITEMPROMOCAOPREMIO";
            
            var control = {
                'rowIndex' : tr.rowIndex,
                'origem'   : isOrigemPromocao
            };
            var data = {
                'codigoMercadoria'        : codigoMercadoria,
                'cliente'     		      : cliente,
                'codigoFilialExpedicao'   : filialExpedicao.codigoFilial,
                'codigoFilialFaturamento' : filialFaturamento.codigoFilial,
                'codigoCondicaoPagamento' : condicaoPagamento,
				'representante'           : representante,
				'numPagina'               : pageControl.getPage(),
				'codigoPromocao'		  : codigoPromocao,
				'codigoGrupoMercadoria'   : codigoGrupoMercadoria				
            };
     
            BRQMob.exec("sucessoAlteraFilial", "falhaAlteraFilial", VENDAS_DISPATCHER, "alterarFilialItemPromocao", [control, data]);
        } catch(err) {
            alert('[alteraFilial] - '.concat(err.message));
        }
    }
}


function sucessoAlteraFilial(result) {
    BRQMob.log(JSON.stringify(result));
    var currentTR;
    try {
        var control = result.control;
        
        currentTR = document.getElementById(TBODY_ITENS).children[control.rowIndex - 1];
       
        if (result.item == undefined) {
                
            /*
             * Seta o código da próxima filial para realizar a pesquisa!
             */
            currentTR.children[TCI.filFaturamento].children[0].textContent = getPosicaoVetorFilial(currentTR, true);

            flagExecucaoAlteraFilial = false;
            alteraFilial(currentTR, true);
            
        } else {
                        
            var posicao = getPosicaoVetorFilial(currentTR, true);
            
            var filiais = filiaisDisponiveis[posicao];
            currentTR.children[TCI.filFaturamento].children[0].textContent = posicao;
            currentTR.children[TCI.filFaturamento].children[1].textContent = filiais.filialFaturamento.codigoFilial;
           
            if (result.item.flagPrecificavel) {        
                
                calculaPreco(currentTR, true);
                
            } else {
                
                preencheInfo(currentTR.children[TCI.precoLiqImpFrt], 0, '');
                preencheInfo(currentTR.children[TCI.valcaixaImpFrt], 0, '');
                preencheInfo(currentTR.children[TCI.valUntImpFrt],   0, '');
                preencheInfo(currentTR.children[TCI.valFrt],         0, '');
            }
        }
    } catch (err) {
        alert('[sucessoAlteraFilial] - '.concat(err.message));
    } finally {
        flagExecucaoAlteraFilial = false;
        
        var btnFilial = currentTR.children[TCI.filFaturamento].children[1];
        btnFilial.onclick = function(){alteraFilial(currentTR);}
    }
}


function alteraCondicaoPagamento(currentTR) {
    try {
        event.stopPropagation();
  		var condicaoPagamento     = currentTR.children[TCI.condPagmento].children[0].textContent;
        if(condicaoPagamento ==  CODIGO_CONDICAO_PAGAMENTO_PREMIO_GRATIS){
			alert("A condição de pagamento do brinde não pode ser alterada !!!");
			return;
		}        
        var codigoMercadoria      = currentTR.children[TCI.hidden].children[TDH.codMercadoria].textContent;
        var tmpCondicaoPagamento = {
             'rowIndex'          : currentTR.rowIndex,
             'codigoMercadoria'  : codigoMercadoria,
             'condicaoPagamento' : condicaoPagamento
        };
        sessionStorage.setObject(CONST_ITEM_CONDICAO_PAGAMENTO, tmpCondicaoPagamento);
        
        nav('condPagamento.html');
    } catch(ex) {
        alert('[alteraCondicaoPagamento] - '.concat(err.message));
    }
}


function alteraQuantidade() {
     try {        
     	BRQMob.log("Entrando no método alteraQuantidade");
        var tr                    = ultimoBtnQtq.parentNode.parentNode;
        
        var cliente               = getCliente();
        
        //  Filial Service
        var posicaoVetorFilial    = getPosicaoVetorFilial(tr, false);
        var filialFaturamento     = filiaisDisponiveis[posicaoVetorFilial].filialFaturamento;    
        var filialExpedicao       = filiaisDisponiveis[posicaoVetorFilial].filialExpedicao;
        //
        var codigoMercadoria      = tr.children[TCI.hidden].children[TDH.codMercadoria].textContent;        
        var condicaoPagamento     = tr.children[TCI.condPagmento].children[0].textContent;
        
        var isOrigemPromocao = $("#switch-promocao").hasClass("selected") ? "ITEMPROMOCAO" : "ITEMPROMOCAOPREMIO";
        
        var control = {
            'rowIndex' : tr.rowIndex,
            'origem'   : isOrigemPromocao
        };
        
        var data = {
            'codigoMercadoria'        : codigoMercadoria,
            'codigoCliente'           : cliente.codigoCliente,
            'codigoFilialExpedicao'   : filialExpedicao.codigoFilial,
            'codigoFilialFaturamento' : filialFaturamento.codigoFilial,
            'codigoCondicaoPagamento' : condicaoPagamento,
            'quantidadeAnterior'      : quantidadeAnterior,
            'quantidadeAtual'         : document.getElementById('qtd').value == '' ? 0 : document.getElementById('qtd').value //document.getElementById('qtd').value
        };
        
        BRQMob.exec("sucessoAlteraQuantidade", "falhaAlteraQuantidade", VENDAS_DISPATCHER, "alteraQuantidadeItem", [control, data]);
        
    } catch(err) {
        alert('[alteraQuantidade] - '.concat(err.message));
    }
}

function sucessoAlteraQuantidade(result) {
    try {
        var control = result.control;
        
        var tr     = document.getElementById(TBODY_ITENS).children[control.rowIndex - 1];
        var btnQtd = tr.children[TCI.quantidade].children[0];
        
        var mensagem = result.mensagem;
        var inputQtq = document.getElementById('qtd');
             
        if (mensagem.tipo == 1 && alert(mensagem.texto)) {
             
             inputQtq.value = mensagem.valor;
             alteraQuantidade();
             return;
                          
        } else if (mensagem.tipo == 2) {
            
            if (confirm(mensagem.texto)) {
                
                inputQtq.value = mensagem.valor;

                alteraQuantidade();
                
                return;
                                
            } else {
                
                inputQtq.value = mensagem.valorAbortado;
            }
                        
        } else {
            
            inputQtq.value = mensagem.valor;
                
        }
    } catch (err) {
        alert('[sucessoAlteraQuantidade()] - '.concat(err.message));
    }
    try {
        addQtd();
    } catch (err) {
        BRQMob.log('[addQtd()] - '.concat(err.message));
    }
    try {
        calculaPreco(ultimoBtnQtq.parentNode.parentNode, true, true);
    } catch (err) {
        alert('[sucessoAlteraQuantidade(),calculaPreco()] - '.concat(err.message));
    }   
}


function restante() {
	BRQMob.log("Entrando no método restante");
	var codigoPromocao = getCodigoPromocao();
	if(codigoPromocao != ""){
		BRQMob.exec("sucessoRestante", "falhaRestante", VENDAS_DISPATCHER, "obterValorRestantePromocao",[codigoPromocao]);
	}
}


function sucessoRestante(result){
	$("body").append("<div id='buscaAvancada' class='restante'><h2>Restante</h2><p>" +result.mensagem.texto +"" + result.mensagem.valor+ "</p></div>");
	$("#buscaAvancada").show("fast");
	document.getElementById("mainContent").onmousedown = evento5;
}


function limpaSelectDadosPromocao(){
	$("#detalhePromocao").empty();
	$("#dataFimPromocao").empty();
}


function limpaSelectUnidadeNegocio(){
	$("#unidadeNegocio").empty();
}


function limpaSelectGrupoMercadoria(){
	$("#grupoMercadoria").empty();
}


function setDataFimPromocao() {
		var dataFim = $('#detalhePromocao option:selected').val();
  		$('#dataFimPromocao').text(dataFim);
}


function getCodigoPromocao() {
		return $('#detalhePromocao option:selected').text().substring(0,4);
}


function getCodigoGrupoMercadoria() {
	var codigoGrupoMercadoria = $('#grupoMercadoria option:selected').val(); 
	return  codigoGrupoMercadoria != undefined ? codigoGrupoMercadoria : 0;
}


function getUnidadeNegocio() {
		return  $('#unidadeNegocio option:selected').val();
}


function falhaListarSelectGrupoMercadoria (erro) {
	alert("[falhaListarSelectGrupoMercadoria] " + erro.message);
}


function falhaListarSelectUnidadeNegocio (erro) {
	alert("[falhaListarSelectUnidadeNegocio]" + erro.message);
}


function falhaListarSelectPromocoes (erro) {
	alert("[falhaListarSelectPromocoes]" + erro.message);
}


function falhaListarItensDisponiveisDaPromocao (erro) {
	alert("[falhaListarItensDisponiveisDaPromocao]" + erro.message);
}


function falhaCarregarPremiosDaPromocao (erro) {
	alert("[falhaCarregarPremiosDaPromocao]" + erro.message);
}


function falhaAlteraFilial(result) {
    BRQMob.log('[falhaAlteraFilial]: ' + JSON.stringify(result));
    var currentTR;
    try {
        var filiais = filiaisDisponiveis[getPosicaoVetorFilial(currentTR)];
       
        currentTR = document.getElementById(TBODY_ITENS).children[result.rowIndex - 1]; 
        
        currentTR.children[4].children[1].textContent = filiais.filialFaturamento.codigoFilial;        
    } catch (err) {
        alert('[falhaAlteraFilial] - '.concat(err.message));
    } finally {
        flagExecucaoAlteraFilial = false;
        
        var btnFilial = currentTR.children[TCI.filFaturamento].children[1];
        btnFilial.onclick = function(){alteraFilial(currentTR);}
    }
}


function falhaAlteraQuantidade(result) {
    try {
         apresentaMensagemErro(result.msg, true);   
    } catch (err) {
        alert('[falhaAlteraQuantidade()] - '.concat(err.message));
    }
}


function falhaCarregaFiliais(result) {
    BRQMob.log("Falha ao carregar filiais: " + result);
}


function falhaCalculaPreco(result) {
    try {
        BRQMob.log(JSON.stringify(result));
        alert(result.msg);
    } catch(err) {
        alert('[falhaCalculaPreco] - '.concat(err.message));
    } 
}

