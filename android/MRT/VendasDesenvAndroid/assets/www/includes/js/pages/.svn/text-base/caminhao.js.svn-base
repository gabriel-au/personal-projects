var TBODY_ITENS = 'tbody_itens_disponiveis';
//Table Column Hidden
var TDH = {
    'flgCalculado'    : 0,
    'codMercadoria'   : 1,
    'perAcrescimo'    : 2,
    'perDesconto'     : 3,
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
    representante             = getRepresentante();
} catch(ex) {
    BRQMob.log(ex.description);
}

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

$(document).ready(function(){
	BRQNavegacao.adicionarPilhaNavegacao('caminhao'); //CONST_CAMINHAO);
	
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
	
	 carregaFiliais();

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
	        //codigo provisorio para calcular o preco quando o slider for liberado
	        //clearTimeout(sliderTimeOut);
	        //sliderTimeOut = setTimeout(function(){calculaPreco(ultimaLinhaSelecionda, true)}, 200);
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
    
    document.getElementById("panelPedido").style.display = 'block';
	document.getElementById("panelComissao").style.display = 'none';
    
    $('#href_comissao').click(function() {
   			document.getElementById("panelPedido").style.display = 'none';
			document.getElementById("panelComissao").style.display = 'block';
			document.getElementById("caracteristicas_li_detalhes").className = "last";
			document.getElementById("caracteristicas_li_caracteristicas").className = "frist selected";
		});
	
		$('#href_pedido').click( function() {
			document.getElementById("panelPedido").style.display = 'block';
			document.getElementById("panelComissao").style.display = 'none';
			document.getElementById("detalhes_li_detalhes").className = "frist selected";
			document.getElementById("detalhes_li_caracteristicas").className = "last";
		});   
});

function pagination() {
    try {
        if (pageControl.isPossible()) {
            pageControl.increment();
           // dadosUltimaPesquisa.dadosPadrao.pesquisaAnterior = false;
           // dadosUltimaPesquisa.dadosPadrao.numPagina        = pageControl.getPage();
           	  carregarCaminhao();
        }
    } catch(err) {
        alert('[pagination] - '.concat(err.message));
    }
}

function carregaFiliais(){ 
    try {
        filiaisDisponiveis = getFiliaisDisponiveis();
        if (filiaisDisponiveis == null || filiaisDisponiveis == undefined) {
            
            var codigoCliente = getCliente().codigoCliente;
            var terriorioId   = getCliente().codigoTerritorio;
            
            BRQMob.exec("sucessoCarregaFiliais", "falhaCarregaFiliais", VENDAS_DISPATCHER, "listaFiliais", [codigoCliente, terriorioId]);
            
        } else {            
          	posicionaFiliais(filiaisDisponiveis);
			carregarCaminhao();                   
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
     carregarCaminhao();  	 
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

function carregarCaminhao(){
  	BRQMob.log("entrando na funcao carregarCaminhao()");
  	BRQMob.exec("sucessoCarregarCaminhao", "falhaCarregarCaminhao", "VendasDispatcher", "carregarCaminhao", []);
	
}

function sucessoCarregarCaminhao(retorno){
	montarListaCaminhao(retorno);	
}

function falhaCarregarCaminhao(){
	BRQMob.log("Erro sucessoCarregarCaminhao"+object);
}
function limpaTabela(){
	$("#tbody_itens_disponiveis").empty();	
}
function montarListaCaminhao(retorno) {

	try {
	 primeiraPesquisa = false;
	
	 limpaTabela();
	
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
     var ul_item_data   = document.getElementById('ul_item_data');
	    
     var itens = retorno.itensCaminhao;		
    	
	 if ((itens == undefined || itens.length == 0) && pageControl.getPage() == 1) {
            apresentaMensagemErro('Nenhum item encontrado!', true);            
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
		
	// Mantendo dados na página para uso posterior
    var li_mercadoria =  document.createElement('li');
    li_mercadoria.textContent = mercadoria.codigo;
    ul_item_data.appendChild(li_mercadoria);
	
			
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
                 * Chamar a rotina de calculo antes de apresentar a linha de informação sobre o item  
                 */
                if (selected == null || selected.indexOf('selected') == -1) {
                    try {
                        var TR = document.getElementById(TBODY_ITENS).children[index];
                        calculaPreco(TR);
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
	
	var div 		 = li.children[1].children[0];
	
	var labelCodProd = div.children[0];
	var labelNomProd = div.children[3].children[0];
	
	var labelNF = div.children[2];
	
    if (index % 2 != 0) { li.className = 'off'; }
    labelCodProd.textContent = 'Cód:'.concat(mercadoria.codigo);
    labelNomProd.textContent = mercadoria.descricao;	
    labelNF.textContent = item.notaFiscal;
	if(mercadoria.indicaMercadoriaKit == 1) {
	   div.children[1].children[0].setAttribute('class','icon kit');
	   div.children[1].children[0].onclick = function (){detalharKit(this)};
    }
    if (promocao.codigo != undefined) {
     	div.children[1].children[0].setAttribute('class','icon promo');
        div.children[1].children[0].onclick = function (){detalhePromocao(this)};       
    }
    
    
		// ////////////////////////////////////////////
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
                    
        // ////////////////////////////////////////////
        // [FIM] Montar TD de Dados Escondidos       //
        // ////////////////////////////////////////////            
        if (index % 2 != 0) { tr.className = 'off'; }
                    
        if (!isNaN(preco.tipoMarcacaoRepresentante)) {
             
            tr.children[TCI.codMarcacao].children[0].textContent = preco.tipoMarcacaoRepresentante;
             
        } else {
            
            tr.children[TCI.codMarcacao].removeChild(tr.children[1].children[0]);    
        }
            
		
		//Quantidade de mercadoria pedida		
		var a_quantidade = tr.children[TCI.quantidade].children[0];
        a_quantidade.textContent = item.quantidadeMercadoria;
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
            tr.children[TCI.descMercadoria].children[0].children[1].children[0].onclick = function(){detalharKit(this)};
        }
        if (mercadoria.temRestricaoBeneficioCustomizado == FLAG_MARTINS_TRUE) {
            tr.children[TCI.descMercadoria].children[0].children[1].setAttribute('href','javascript:void(0)');
            tr.children[TCI.descMercadoria].children[0].children[1].children[0].setAttribute('class','icon cadeado');
        }
        
        if (promocao.codigo != undefined) {
        	tr.children[TCI.descMercadoria].children[0].children[1].children[0].setAttribute('class','icon promo');
            tr.children[TCI.descMercadoria].children[0].children[1].children[0].onclick = function(){detalhePromocao(this)};	     	              
        }
        			
		tr.children[TCI.filFaturamento].children[0].textContent = posicaoVetorFiliaisDisponiveis;
    	tr.children[TCI.filFaturamento].children[1].textContent = item.codigoFilialFaturamento;
		
		tr.children[TCI.tipEsgotamento].textContent = mercadoria.tipoEsgotamento == undefined ? '' : mercadoria.tipoEsgotamento;
		
		tr.children[TCI.caixa].textContent = mercadoria.quantidadeCaixaFornecedor;
		
		tr.children[TCI.condPagmento].children[0].children[1].textContent = item.condicaoPagamento.codigoCondicaoPagamento;
		
		tr.children[TCI.simbolo].textContent = preco.codigoSimboloSituacao;
		
		// Coluna NF
        comboBox = tr.children[TCI.notaFiscal].children[0].children[0];
        comboBox.value = item.notaFiscal;
		
		//Coluna Negociação
        comboBox = tr.children[TCI.tipNegociacao].children[0].children[0];
        if (!isNaN(mercadoria.temRestricaoBeneficioCustomizado) && itens[index].indicaRestricaoBeneficioCustomizado == 1) {
            comboBox.removeChild(comboBox.children[1]);
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
    var page = parseInt(quantLinhas / retorno.tamanhoPagina) + (quantLinhas % retorno.tamanhoPagina == 0 ? 0 : 1);
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
	}catch(err) {
       alert('[montarListaCaminhao] - '.concat(err.message));
    } finally {
       pageControl.free();
    }
  }

var detalharKit = function(iconeKit){
	 try {
        var currentTR           = iconeKit.parentNode.parentNode.parentNode.parentNode;
        var codigoMercadoria    = currentTR.children[TCI.hidden].children[TDH.codMercadoria].textContent;
        
        mercadoria = new Object();
        mercadoria.codigo = codigoMercadoria;
        sessionStorage.setObject(CONST_MERCADORIA,mercadoria);  
        nav(getPage('kit'));
    } catch (err) {
        alert('[detalharKit()]: '.concat(err.message));
    }
}
var detalhePromocao = function(iconePromocao){
	// var codigoPromocao = 	iconePromocao.children[TCI.hidden].children[TDH.codigoPromocao].textContent;     	
	// sessionStorage.setObject(CONST_ITEM_DISPONIVEL_PROMOCAO_CODIGO_BUSCA,codigoPromocao);
	 nav(getPage('promocoes'));
}
function alterarValorUnitarioCaixa(HTMLTableRowElement) {   
try {   
        if (ultimaLinhaSelecionda == null || ultimaLinhaSelecionda == undefined) {
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

function renderizaDadosPrecoItem(currentTR, item) {
    try {
        var mercadoria = item.mercadoria;
        
        if (mercadoria.temRestricaoBeneficioCustomizado == FLAG_MARTINS_TRUE) {
            preparaIcone('append', currentTR, 'icon cadeado', '#');
        } else {
            preparaIcone('remove', currentTR, 'icon cadeado', '#');
        }
        
        preencheInfo(currentTR.children[TCI.hidden].children[TDH.perDesconto], item.percentualDescontoConcedido, 0);
        preencheInfo(currentTR.children[TCI.hidden].children[TDH.perAcrescimo], item.percentualAcrescimoConcedido, 0);
            
        sliderValorItem.value = document.getElementById('ul-unitario-caixa').children[0].className.indexOf('selected') != -1 ? item.valorUnitarioComImposto : item.valorCaixaComImposto ;
    
        preencheInfo(currentTR.children[TCI.precoLiqImpFrt], item.valorLiquidoComImposto, '');
        preencheInfo(currentTR.children[TCI.valcaixaImpFrt], item.valorCaixaComImposto,   '');
        preencheInfo(currentTR.children[TCI.valUntImpFrt]  , item.valorUnitarioComImposto,'');
        preencheInfo(currentTR.children[TCI.valFrt]        , item.frete, '');
        
        document.getElementById('label-header-comissao').children[0].innerHTML  = item.comissao.comissaoMercadoria;       
        
        document.getElementById('label-header-pontos').children[0].innerHTML    = item.indicaItemImune == 1 ? 'Imune' : item.valorPontoMercadoria;
        
        document.getElementById('label-header-precoLiq').children[0].innerHTML  = item.valorLiquidoMercadoria;
        document.getElementById('label-header-cad').children[0].innerHTML       = item.codigoFilialExpedicao;
        
        document.getElementById('label-header-incentivo').children[0].innerHTML = item.preco.tipoIncentivoMercadoria;
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

function calculaPreco(HTMLTableRowElement, executarSlider, incluirNoPedido) {
   try {       
    	if(HTMLTableRowElement == null) return;

        var currentTR               = HTMLTableRowElement;
       
        var cliente                 = getCliente();
        
        var representante           = getRepresentante();
        
        var modeloDistribuicao      = getModeloDistribuicao();
        
        var ul_item_data            = currentTR.parentNode.parentNode.parentNode.children[0];
        var codigoMercadoria        = ul_item_data.children[currentTR.rowIndex - 1].textContent;
        
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
                      
        incluirNoPedido = incluirNoPedido || quantidadeMercadoria > 0 ? true : false;
        
        var control = {
            'rowIndex'             : currentTR.rowIndex,
            'origem'               : 'CAMINHAO',
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

function sucessoCalculaPreco(result) {
    
    try {    
   		 var start = new Date().getMilliseconds();	 	
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

function falhaCalculaPreco(result) {
    BRQMob.log(result);
}

function clickItem(HTMLTableRowElement) {
	tableTR(HTMLTableRowElement);
	
    try {
        ultimaLinhaSelecionda    = HTMLTableRowElement;
            
        var flagCalculado        = ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.flgCalculado];
        
        var sliderValue = 0;
        var percentualDesconto   = ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perDesconto].textContent;
        var percentualAcrescimo  = ultimaLinhaSelecionda.children[TCI.hidden].children[TDH.perAcrescimo].textContent;
        if (!isNaN(percentualDesconto) && percentualDesconto < 0) { sliderValue = percentualDesconto; }
        if (!isNaN(percentualAcrescimo) && percentualAcrescimo > 0) { sliderValue = percentualAcrescimo; }

       
        sliderVaricao.value = sliderValue;
        slider.value(parseFloat(sliderValue));
        slider.disabled(false);
        
        if (flagCalculado.textContent == 'false') {
            calculaPreco(HTMLTableRowElement, false);
        } else {
            var control = {
                'rowIndex': ultimaLinhaSelecionda.rowIndex,
                'origem': 'CAMINHAO'         
            };
            var data = {
                'codigoMercadoria': ultimaLinhaSelecionda.children[TCI.hidden]
            			.children[TDH.codMercadoria].textContent
            };
            BRQMob.exec("sucessoObtemItem", "falhaObtemItem", VENDAS_DISPATCHER, "obtemItem", [control, data]);
        }
    } catch (err) {
        BRQMob.log('[clickItem]: ' + err.message);
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

function alteraQuantidade() {
      try {        
        var tr                    = ultimoBtnQtq.parentNode.parentNode;
        
        var cliente               = getCliente();
        
        //  Filial Service
        var posicaoVetorFilial    = getPosicaoVetorFilial(tr, false);
        var filialFaturamento     = filiaisDisponiveis[posicaoVetorFilial].filialFaturamento;    
        var filialExpedicao       = filiaisDisponiveis[posicaoVetorFilial].filialExpedicao;
        //
        var ul_item_data          = tr.parentNode.parentNode.parentNode.children[0];
        var codigoMercadoria      = ul_item_data.children[tr.rowIndex - 1].textContent;
        
        var condicaoPagamento     = tr.children[TCI.condPagmento].children[0].children[1].textContent;
        
        var control = {
            'rowIndex' : tr.rowIndex,
            'origem'   : 'CAMINHAO'
        };
        
        var data = {
            'codigoMercadoria'        : codigoMercadoria,
            'codigoCliente'           : cliente.codigoCliente,
            'codigoFilialExpedicao'   : filialExpedicao.codigoFilial,
            'codigoFilialFaturamento' : filialFaturamento.codigoFilial,
            'codigoCondicaoPagamento' : condicaoPagamento,
            'quantidadeAnterior'      : quantidadeAnterior,
            'quantidadeAtual'         : document.getElementById('qtd').value
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
        calculaPreco(ultimoBtnQtq.parentNode.parentNode, true);
    } catch (err) {
        alert('[sucessoAlteraQuantidade(),calculaPreco()] - '.concat(err.message));
    }   
}

function falhaAlteraQuantidade(result) {
    try {
         apresentaMensagemErro(result.msg, true);   
    } catch (err) {
        alert('[falhaAlteraQuantidade()] - '.concat(err.message));
    }
}

function alteraFilial(tr, stopPropagation) {
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
                alert(btnFilial.textContent);
                alert(filiaisDisponiveis[posicaoVetorFilial].filialFaturamento.codigoFilial);
                btnFilial.textContent = getPosicaoVetorFilial(tr, true);
            }
            //
            var cliente                 = getCliente();
            var representante           = getRepresentante();
            
            // Filial Service
            var filiais           = filiaisDisponiveis[posicaoVetorFilial];
            var filialFaturamento = filiais.filialFaturamento;    
            var filialExpedicao   = filiais.filialExpedicao;
            
            var ul_item_data      = tr.parentNode.parentNode.parentNode.children[0];
            var codigoMercadoria  = ul_item_data.children[tr.rowIndex - 1].textContent;
     
            btnFilial.textContent = '...';
            
            var condicaoPagamento = tr.children[TCI.condPagmento].children[0].textContent;
            
            var control = {
                'rowIndex' : tr.rowIndex,
                'origem'   : 'CAMINHAO'
            };
            var data = {
            	'cliente'                 : cliente,
                'codigoMercadoria'        : codigoMercadoria,
                'codigoFilialExpedicao'   : filialExpedicao.codigoFilial,
                'codigoFilialFaturamento' : filialFaturamento.codigoFilial,
                'codigoCondicaoPagamento' : condicaoPagamento,
                'codigoRepresentante'     : representante.codigoRepresentante
            };
     
            BRQMob.exec("sucessoAlteraFilial", "falhaAlteraFilial", VENDAS_DISPATCHER, "alterarFilialItem", [control, data]);
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

function falhaAlteraFilial(result) {
    BRQMob.log(result);
    try {
        var filiais = filiaisDisponiveis[getPosicaoVetorFilial(tr)];
       
        var tr = document.getElementById(TBODY_ITENS).children[result.rowIndex - 1]; 
        
        tr.children[4].children[1].textContent = filiais.filialFaturamento.nomeFilial;        
    } catch (err) {
        alert('[falhaAlteraFilial] - '.concat(err.message));
    }
}

function descricaoProduto(div){
	 try {

        var index = div.parentNode.parentNode.getAttribute("id");
        index = index.substring(index.lastIndexOf('_')+1);
        
        var currentTR                   = document.getElementById(TBODY_ITENS).children[index];
        
        var cliente                     = getCliente();
        var modeloDistribuicao          = getModeloDistribuicao();
        
        var ul_item_data                = currentTR.parentNode.parentNode.parentNode.children[0];
        var codigoMercadoria            = ul_item_data.children[currentTR.rowIndex - 1].textContent;

        /*
         * Recuperar Filial 
         */
        var posicaoVetorFilial          = getPosicaoVetorFilial(currentTR, false);
        var filialExpedicao             = filiaisDisponiveis[posicaoVetorFilial].filialExpedicao.codigoFilial;
        var filialFaturamento           = filiaisDisponiveis[posicaoVetorFilial].filialFaturamento.codigoFilial;
                
        var codigoCliente               = cliente.codigoCliente;
        var codigoTerritorioCliente     = cliente.codigoTerritorio;
        var codigoModeloDistribuicao    = modeloDistribuicao.codigo;
        
        var comboTipoNegociacao         = currentTR.children[TCI.tipNegociacao].children[0].children[0];
        var dscTipoNegociacao           = comboTipoNegociacao.textContent; 
        var codTipoNegociacao           = comboTipoNegociacao.value;

        var dadosProduto = {
            codigoMercadoria            :   codigoMercadoria,
            filialExpedicao             :   filialExpedicao,
            filialFaturamento           :   filialFaturamento,
            codigoCliente               :   codigoCliente,
            codigoTerritorioCliente     :   codigoTerritorioCliente,
            codigoModeloDistribuicao    :   codigoModeloDistribuicao,
            tipoNegociacao              :   codTipoNegociacao
        };
      
        sessionStorage.setObject(CONST_DADOS_PRODUTO_SELECIONADO,dadosProduto);
    
        nav(getPage('produtoDesc'));
        
    } catch (err) {
        alert('[detalheItem(div)] - '.concat(err.message));
    }
}

function excluir(tr){
	var ul_item_data = tr.parentNode.children[0];
	var codigoMercadoria  = ul_item_data.children[0].textContent;
	notaFiscal=ul_item_data.children[2].textContent;
	codigo=codigoMercadoria.split(':');		
	
	if(confirm('Deseja realmente excluir este item da lista?')){		
		BRQMob.log("entrando na funcao excluir mercadoria da lista");
		BRQMob.exec("sucesso", "falha", "VendasDispatcher", "excluirMercadoria",[codigo[1],notaFiscal]);		
	}
}

function sucesso(retorno){
	alert(retorno.mensagem);
	carregaFiliais();
}
function alteraCondicaoPagamento(currentTR) {
    try {
        event.stopPropagation();
        
        var ul_item_data          = currentTR.parentNode.parentNode.parentNode.children[0];
        var codigoMercadoria      = ul_item_data.children[currentTR.rowIndex - 1].textContent;
        var condicaoPagamento     = currentTR.children[TCI.condPagmento].children[0].children[1].textContent;
              
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




