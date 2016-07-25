/*
	Estrutura referente as opcoes selecionadas pelo usuario na aplicacao de pedidos
	pedidoBase
		- cliente
			- codigoCliente
			- nomeCliente
			- canal
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
			- tipoPedido (NORMAL, SIMPLIFICADO, MISTO)
		- tipoPedido (C = Cotacao, P = Pedido)

	Ex.: Para ter acessso ao codigo da filial de expedicao:
		- getPedidoBase().filial.filialExpedicao.codigoFilial
	Ex.: Para ter acessso ao codigo do cliente:
		- getPedidoBase().cliente.codigoCliente
*/
function getPedidoBase(){
	var pedidoBase = sessionStorage.getObject('pedidoBase');
	return pedidoBase == '' || pedidoBase == null ? new Object() : pedidoBase;
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
	var pedidoBase = new Object();
	setPedidoBase(pedidoBase);
}

function setPedidoBase(pedidoBase){
	sessionStorage.setObject('pedidoBase', pedidoBase);
}

function setClientePedidoBase(cliente){
	var pedidoBase = getPedidoBase();
	pedidoBase.cliente = cliente;
	setPedidoBase(pedidoBase);
}

function setFilialPedidoBase(filial){
	var pedidoBase = getPedidoBase();
	pedidoBase.filial = filial;
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

function setTipoPedidoPedidoBase(tipoPedido){
	var pedidoBase = getPedidoBase();
	pedidoBase.tipoPedido = tipoPedido;
	setPedidoBase(pedidoBase);
}
