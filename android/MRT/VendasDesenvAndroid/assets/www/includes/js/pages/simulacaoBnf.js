/*variaveis para teste*/
const MENSAGEM_NAO_POSSIVEL_CONCESSAO_BENEFICIO = 1;
const MENSAGEM_CONFIRMACAO_VARIACAO_PRECO = 2;
const MENSAGEM_NAO_POSSIVEL_CONCESSAO_BENEFICIO_SUJEITO_CORTES = 3;

var idProduto;
var codCliente;
var codigoFilialExpedicao;
var codigoFilialFaturamento;
var tipoVendaPedido;
var codigoTerritorioVenda;
var tipoNegociacao;	
var codigoModeloDistribuicao;
var percentualSimulacaoBeneficio;

$(document).ready(function(){	
		BRQNavegacao.adicionarPilhaNavegacao('produtoDesc');
		
		$('#percentualSimulacaoBeneficio').bind('keypress', function(event) {
		 	return bloqueiAlfa(event);
		});
		
		$('#percentualSimulacaoBeneficio').bind('keyup', function(event) {
		 	bloqueiCtrlCV(event, this);
		});
		
		/*informacoes abaixo deverao vir da tela anterior*/
		
		idProduto                = getDadosProdutoSelecionado().codigoMercadoria;
		codCliente               = getDadosProdutoSelecionado().codigoCliente;
		codigoFilialExpedicao    = getDadosProdutoSelecionado().filialExpedicao;
		codigoFilialFaturamento  = getDadosProdutoSelecionado().filialFaturamento;
		tipoVendaPedido          = getPedidoBase().info.tipoPedido;
		codigoTerritorioVenda    = getDadosProdutoSelecionado().codigoTerritorioCliente;
		tipoNegociacao           = getDadosProdutoSelecionado().tipoNegocicao;
		codigoModeloDistribuicao = getDadosProdutoSelecionado().codigoModeloDistribuicao;
		/*###########*/
		$("#codigoModeloDistribuicao").text(codigoModeloDistribuicao);
		carregarDetalhesProduto(idProduto, codCliente, codigoFilialExpedicao, codigoFilialFaturamento, tipoVendaPedido, codigoTerritorioVenda, tipoNegociacao);
});

function carregarDetalhesProduto(idProduto, codCliente, codigoFilialExpedicao, codigoFilialFaturamento, tipoVendaPedido, codigoTerritorioVenda, tipoNegociacao){
	/*metodo CUGCtrlPsqGrpMer::OnLClicked(int col, long row, int updn, RECT * rect, POINT * point, int processed) da classe UGCtrlPsqGrpMer*/
	console.log("entrando na funcao carregar produto");
  	BRQMob.exec("sucesso", "falha", "VendasDispatcher", "detalhesProduto", [idProduto, codCliente, codigoFilialExpedicao, codigoFilialFaturamento, tipoVendaPedido, codigoTerritorioVenda, tipoNegociacao]);	
  	console.log("saindo na funcao carregar produto");		
}

function sucesso(retorno){
	loadProduto(retorno.simulacaoBeneficio);
}

function loadProduto(simulacaoBeneficio){
	precoLiquidoUnitarioSimulado = simulacaoBeneficio.precoLiquidoUnitarioSimulado; //null
	precoLiquidoSimulado = simulacaoBeneficio.precoLiquidoSimulado;
	$("#precoLiquidoSimulado").text(precoLiquidoSimulado);
	$("#precoLiquidoUnitarioSimulado").text(precoLiquidoUnitarioSimulado);			
}

function falha(retorno){
	alert('falha');	
	console.log("falha carregar produto" + retorno);	 
}

function calcularSimulacao(){
	/*metodo CDlgPopupItem::OnCalculaVlrLiqSimulado() classe DlgPopupItem.cpp*/
	percentualSimulacaoBeneficio = $("#percentualSimulacaoBeneficio").val();
	console.log("entrando na funcao carregar produto");
  	BRQMob.exec("sucessoSimulacao", "falha", "VendasDispatcher", "simulacaoBeneficio", [idProduto, codCliente, codigoFilialExpedicao, codigoFilialFaturamento, tipoVendaPedido, codigoTerritorioVenda, tipoNegociacao, percentualSimulacaoBeneficio, false]);	
  	console.log("saindo na funcao carregar produto");
}

function sucessoSimulacao(retorno){
	simulacaoBeneficio = retorno.simulacaoBeneficio;
	$("#percentualSimulacaoBeneficio").val(simulacaoBeneficio.percentualSimulacaoBeneficioFormatado);
	percentualSimulacaoBeneficio = $("#percentualSimulacaoBeneficio").val();
	if(simulacaoBeneficio.mensagemSimulacaoBeneficio){
		if(simulacaoBeneficio.tipoMensagemSimulacaoBeneficio == MENSAGEM_CONFIRMACAO_VARIACAO_PRECO){
			var confirma = confirm(simulacaoBeneficio.mensagemSimulacaoBeneficio);
			if (confirma){
				BRQMob.exec("sucessoSimulacao", "falha", "VendasDispatcher", "simulacaoBeneficio", [idProduto, codCliente, codigoFilialExpedicao, codigoFilialFaturamento, tipoVendaPedido, codigoTerritorioVenda, tipoNegociacao, percentualSimulacaoBeneficio, true, simulacaoBeneficio.percentualDescontoFlexivel, simulacaoBeneficio.percentualDescontoItem, simulacaoBeneficio.percentualAcrescimoConcedido]);
			}else{
				$("#percentualSimulacaoBeneficio").val('');
			}
		}else{
			alert(simulacaoBeneficio.mensagemSimulacaoBeneficio);
		}
	}else{
		loadProduto(retorno.simulacaoBeneficio);
	}
}