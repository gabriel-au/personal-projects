/*
	Estrutura referente as opcoes selecionadas pelo usuario na aplicacao de pedidos
	pedidoBase
		- cliente
			- codigoCliente
			- nomeCliente
			- canal
			- codigoTerritorio
		- filial
			- br.com.martins.vendas.vo.FilialCliente (informacoes retornadas de FilialService.obterFiliaisPorCliente)
		- condicaoPagamento
			- codigo
			- descricao
		- modeloDistribuicao
			- codigo
			- descricao
		- info
			- mensagemNota
			- tipoPedido
			- bloqueiaBotaoTipoPedido
		- beneficiosCustomizados
			- codigoBeneficio
			- prazoBeneficio
			- descontoBeneficio
			- tipoBeneficio(P=prazo/D=desconto/PD=prazo e desconto)
			
	Ex.: Para ter acessso ao codigo da filial de expedicao:
		- getPedidoBase().filial.filialExpedicao.codigoFilial
	Ex.: Para ter acessso ao codigo do cliente:
		- getPedidoBase().cliente.codigoCliente
*/
function getPedidoBase(){
	var pedidoBase = sessionStorage.getObject('pedidoBase');
	return pedidoBase == '' || pedidoBase == null ? new Object() : pedidoBase;
}

function getPedidoBaseInfo(){
	var pedidoBase = getPedidoBase();
	return pedidoBase.info;
}

function isPedidoBaseEmpty(){
	var pedidoBase = getPedidoBase();
	for(var i in pedidoBase){ 
		if(pedidoBase.hasOwnProperty(i)){
			return false;
		}
	}
	return true;
}

function resetPedidoBase(){
	sessionStorage.removeObject('pedidoBase');
}

function setPedidoBase(pedidoBase){
	sessionStorage.setObject('pedidoBase', pedidoBase);
}

function setClientePedidoBase(cliente){
	var pedidoBase = getPedidoBase();
	pedidoBase.cliente = cliente;
	setPedidoBase(pedidoBase);
}

/*function setClientePedidoBase(cliente){
	var pedidoBase = getPedidoBase();
	pedidoBase.cliente = cliente;
	setPedidoBase(pedidoBase);
}*/

function setFilialFaturamentoPedidoBase(filial){
	var pedidoBase = getPedidoBase();
	pedidoBase.filialFaturamento = filial;
	setPedidoBase(pedidoBase);
}

function setFilialExpedicaoPedidoBase(filial){
	var pedidoBase = getPedidoBase();
	pedidoBase.filialExpedicao = filial;
	setPedidoBase(pedidoBase);
}

function setTipoPedidoPedidoBase(tipoPedido){
	var pedidoBase = getPedidoBase();
	pedidoBase.tipoPedido = tipoPedido;
	setPedidoBase(pedidoBase);
}

function setCondicaoPagamentoPedidoBase(condicaoPagamento){
	var pedidoBase = getPedidoBase();
	pedidoBase.condicaoPagamento = condicaoPagamento;
	setPedidoBase(pedidoBase);
}

function setModeloDistribuicaoPedidoBase(modeloDistribuicao){
	var pedidoBase = getPedidoBase();
	pedidoBase.modeloDistribuicao = modeloDistribuicao;
	setPedidoBase(pedidoBase);
}

function setInfoPedidoBase(info){
	var pedidoBase = getPedidoBase();
	pedidoBase.info = info;
	setPedidoBase(pedidoBase);
}

function atualizaBeneficioCustomizado(beneficioCustomizado){
	var pedidoBase = getPedidoBase();
	var beneficiosCustomizados = pedidoBase.beneficiosCustomizados;
	for(i = 0; i < beneficiosCustomizados.length; i++){
		if(beneficiosCustomizados[i].codigoBeneficio == beneficioCustomizado.codigoBeneficio){
			pedidoBase.beneficiosCustomizados[i] = beneficioCustomizado;
		}
	}
	setPedidoBase(pedidoBase);
}

function addBeneficioCustomizado(beneficioCustomizado){
	var pedidoBase = getPedidoBase();
	var beneficiosCustomizados = pedidoBase.beneficiosCustomizados;
	if(!beneficiosCustomizados){
		beneficiosCustomizados = new Array();
	}
	beneficiosCustomizados.push(beneficioCustomizado);
	pedidoBase.beneficiosCustomizados = beneficiosCustomizados;
	setPedidoBase(pedidoBase);
}

function removerBeneficioCustomizado(codigoBeneficioCustomizado){
	var pedidoBase = getPedidoBase();
	var beneficiosCustomizados = pedidoBase.beneficiosCustomizados;
	for(i = 0; i < beneficiosCustomizados.length; i++){
		if(beneficiosCustomizados[i].codigoBeneficio == codigoBeneficioCustomizado){
			beneficiosCustomizados.splice(i,1);
		}
	}
	pedidoBase.beneficiosCustomizados = beneficiosCustomizados;
	setPedidoBase(pedidoBase);
}

function getBeneficioCustomizado(codigoBeneficioCustomizado){
	var pedidoBase = getPedidoBase();
	var beneficiosCustomizados = pedidoBase.beneficiosCustomizados;
	if(!beneficiosCustomizados){
		beneficiosCustomizados = new Array();
	}
	for(i = 0; i < beneficiosCustomizados.length; i++){
		if(beneficiosCustomizados[i].codigoBeneficio == codigoBeneficioCustomizado){
			return beneficiosCustomizados[i];
		}
	}
}

function existeBeneficioCustomizado(codigoBeneficioCustomizado){
	var pedidoBase = getPedidoBase();
	var beneficiosCustomizados = pedidoBase.beneficiosCustomizados;
	if(!beneficiosCustomizados){
		beneficiosCustomizados = new Array();
	}
	for(i = 0; i < beneficiosCustomizados.length; i++){
		if(beneficiosCustomizados[i].codigoBeneficio == codigoBeneficioCustomizado){
			return true;
		}
	}

	return false;
}

function gravaPedido() {
	try {
		var opcao = $("#switch").hasClass("on") ? true : false;
		var dados = {};
		
		sistema = getSistema();
		representante = getRepresentante();
		pedidoBase = getPedidoBase();
		cliente = getCliente();
		condicaoPagamento = getCondicaoPagamento();
		numeroPedido = sessionStorage.getObject('numeroPedido');
		filialExpedicao = sessionStorage.getObject(CONST_FILIAL_EXPEDICAO);
		filialFaturamento = sessionStorage.getObject(CONST_FILIAL_FATURAMENTO);
		
		if (opcao) {
			dados = {
				'codigoRepresentante' : representante.codigoRepresentante,
				'tipoVendaPedido' : pedidoBase.info.tipoPedido,
				'tipoPedido' : pedidoBase.tipoPedido,
				'percentualBonificacaoPedido' : representante.percentualBonificacaoPedido,
				'filialExpedicao' : filialExpedicao.codigoFilial,
				'filialFaturamento' : filialFaturamento.codigoFilial,
				'versao_sistema' : sistema.versao,
				'codigoCliente' : cliente.codigoCliente,
				'codigoCondicaoPagamento' : condicaoPagamento.codigo,
				'numeroPedido' : numeroPedido,
				'seraPreparado' : true
			};
		} else {
			dados = {
				'codigoRepresentante' : representante.codigoRepresentante,
				'tipoVendaPedido' : pedidoBase.info.tipoPedido,
				'tipoPedido' : pedidoBase.tipoPedido,
				'percentualBonificacaoPedido' : representante.percentualBonificacaoPedido,
				'filialExpedicao' : filialExpedicao.codigoFilial,
				'filialFaturamento' : filialFaturamento.codigoFilial,
				'versao_sistema' : sistema.versao,
				'codigoCliente' : cliente.codigoCliente,
				'codigoCondicaoPagamento' : condicaoPagamento.codigo,
				'numeroPedido' : numeroPedido,
				'seraPreparado' : false
			};
		}
		
		BRQMob.exec("gravaPedidoSucesso", "gravaPedidoFalha", "VendasDispatcher", "gravaPedido", [dados]);
		
	} catch (err) {
		alert('[gravaPedido()]:'.concat(err.message));
	}
}

function gravaPedidoSucesso(retorno){
	limparSessao();
	window.location.href = '../vendas/listaClientes.html';
}

function gravaPedidoFalha(){
	alert("Erro na gravação do arquivo de exportação do pedido (ARQUIVO.NOT) !!!");
}

function fechaPedido() {
	try {
		sistema = getSistema();
		representante = getRepresentante();
		pedidoBase = getPedidoBase();
		cliente = getCliente();
		condicaoPagamento = getCondicaoPagamento();
		numeroPedido = sessionStorage.getObject('numeroPedido');
		filialExpedicao = sessionStorage.getObject(CONST_FILIAL_EXPEDICAO);
		filialFaturamento = sessionStorage.getObject(CONST_FILIAL_FATURAMENTO);

		var dados = {
			'representante' : representante,
			'codigoRepresentante' : representante.codigoRepresentante,
			'tipoVendaPedido' : pedidoBase.info.tipoPedido,
			'percentualBonificacaoPedido' : representante.percentualBonificacaoPedido,
			'filialExpedicao' : filialExpedicao.codigoFilial,
			'filialFaturamento' : filialFaturamento.codigoFilial,
			'versao_sistema' : sistema.versao,
			'codigoCliente' : cliente.codigoCliente,
			//'condicaoPagamento' : condicaoPagamento,
			'codigoCondicaoPagamento' : condicaoPagamento.codigo,
			'numeroPedido' : numeroPedido
			};
		
	  	BRQMob.exec("sucessoFechaPedido", "falhaFechaPedido", "VendasDispatcher", "fechaPedido", [dados]);
	} catch (err) {
		alert('[fechaPedido()]:'.concat(err.message));
	}
}

function falhaFechaPedido(retorno) {
	sessionStorage.setObject("mensagensPedido", retorno.detalhePedido.mensagens);
	sessionStorage.setObject("detalhePedido", retorno.detalhePedido);
	nav('fecharPedidoMensagens.html');
}

function sucessoFechaPedido(retorno) {
	sessionStorage.setObject("pedido", retorno.pedido);
	nav('fecharPedido.html');
}

//Abandonar Pedido
function abandonar() {
	try {
		if (confirm("Deseja abandonar o pedido?")) {
			BRQMob.exec(null, null, "VendasDispatcher", "abandonaPedido", []);
			limparSessao();
			window.location.href = '../vendas/listaClientes.html';
	  	} else {
			$('#itensMenu').hide('fast');
		}
	} catch (err) {
		alert('[abandonar()]:'.concat(err.message));
	}
}

//function retornoAbandonaPedido() {
//	
//}
