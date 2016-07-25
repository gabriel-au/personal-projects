package br.com.martins.vendas.vendas;

import java.util.Date;

import org.json.JSONArray;

import br.com.martins.vendas.vendas.action.AgendaRoteirizacaoAction;
import br.com.martins.vendas.vendas.action.AlterarCategoriaMercadoriaAction;
import br.com.martins.vendas.vendas.action.AlterarFilialItemAction;
import br.com.martins.vendas.vendas.action.AlterarFilialPromocaoAction;
import br.com.martins.vendas.vendas.action.AlterarGruposMercadoriaAction;
import br.com.martins.vendas.vendas.action.AlterarQuantidadeAction;
import br.com.martins.vendas.vendas.action.AlterarSubCategoriaMercadoriaAction;
import br.com.martins.vendas.vendas.action.AplicarBeneficioCustomizadoAction;
import br.com.martins.vendas.vendas.action.BeneficiosCustomizadosAction;
import br.com.martins.vendas.vendas.action.BeneficiosCustomizadosDetalheAction;
import br.com.martins.vendas.vendas.action.BuscarDescricaoProdutoAction;
import br.com.martins.vendas.vendas.action.CadastrarItemGondolaClienteAction;
import br.com.martins.vendas.vendas.action.CadastroChaveAcessoNFeAction;
import br.com.martins.vendas.vendas.action.CalculaPrecoAction;
import br.com.martins.vendas.vendas.action.CidadesTerritoriosAction;
import br.com.martins.vendas.vendas.action.ComissaoAction;
import br.com.martins.vendas.vendas.action.CondicaoPagamentoAction;
import br.com.martins.vendas.vendas.action.DetalheItemGondolaClienteAction;
import br.com.martins.vendas.vendas.action.DetalheProdutoAction;
import br.com.martins.vendas.vendas.action.EnviarRelatorioEmailGondolaAction;
import br.com.martins.vendas.vendas.action.EnviarRelatorioEmailPedidoAction;
import br.com.martins.vendas.vendas.action.EscolherListaProdutoAction;
import br.com.martins.vendas.vendas.action.FilialAction;
import br.com.martins.vendas.vendas.action.GerarSenhaAction;
import br.com.martins.vendas.vendas.action.ItensDisponiveisAction;
import br.com.martins.vendas.vendas.action.ItensSimilaresAction;
import br.com.martins.vendas.vendas.action.ItensVendidosSemanaAction;
import br.com.martins.vendas.vendas.action.KitAction;
import br.com.martins.vendas.vendas.action.ListarCortesAction;
import br.com.martins.vendas.vendas.action.ListarGondolaClienteAction;
import br.com.martins.vendas.vendas.action.ListarModeloDistribuicaoAction;
import br.com.martins.vendas.vendas.action.ListarPedidoClientesAction;
import br.com.martins.vendas.vendas.action.MarcarCurtirProdutoAction;
import br.com.martins.vendas.vendas.action.PedidosAction;
import br.com.martins.vendas.vendas.action.PosicaoVendasAction;
import br.com.martins.vendas.vendas.action.PromocaoAction;
import br.com.martins.vendas.vendas.action.RecuperaDetalhePEEAction;
import br.com.martins.vendas.vendas.action.RecuperaInforGeraisAction;
import br.com.martins.vendas.vendas.action.RecuperarDadosBuscaAvancadaAction;
import br.com.martins.vendas.vendas.action.RepresentanteAction;
import br.com.martins.vendas.vendas.action.RetornosClienteAction;
import br.com.martins.vendas.vendas.action.SimulacaoBeneficioAction;
import br.com.martins.vendas.vendas.action.TitulosAbertoAction;
import br.com.martins.vendas.vendas.action.ValidarCondicaoPagamentoAction;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.core.DispatcherBase;
import com.brq.mobile.framework.log.Log;

public class Dispatcher extends DispatcherBase {

	private static final String TAG = Dispatcher.class.getName();

	public ActionResult execute(String action, JSONArray array, String arg2) {
		try {
			if (action == null) {
				return new ActionResult(Status.INVALID_ACTION);
			}

			if ("gerarSenha".equalsIgnoreCase(action)) {
				return new GerarSenhaAction().execute(array);
			} else if ("verificaStatusPcasos".equalsIgnoreCase(action)) {
				return new PedidosAction().verificaStatusPcasos(array);
			} else if ("cadastroChaveAcessoNFe".equalsIgnoreCase(action)) {
				return new CadastroChaveAcessoNFeAction().execute(array);
			} else if ("listarModeloDistribuicao".equalsIgnoreCase(action)) {
				return new ListarModeloDistribuicaoAction().execute(array);
			} else if ("pedidos".equalsIgnoreCase(action)) {
				return new PedidosAction().execute(array);
			} else if ("excluiPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().excluiPedido(array);
			} else if ("agendaRoteirizacao".equalsIgnoreCase(action)) {
				return new AgendaRoteirizacaoAction().execute(array);
			} else if ("titulosAberto".equalsIgnoreCase(action)) {
				return new TitulosAbertoAction().execute(array);
			} else if ("itensVendidosSemana".equalsIgnoreCase(action)) {
				return new ItensVendidosSemanaAction().execute(array);
			} else if ("informacoesGerais".equalsIgnoreCase(action)) {
				return new RecuperaInforGeraisAction().execute(array);
			} else if ("condicaoPagamento".equalsIgnoreCase(action)) {
				return new CondicaoPagamentoAction().execute(array);
			} else if ("enviarEmail".equalsIgnoreCase(action)) {
				return new EnviarRelatorioEmailGondolaAction().execute(array);
			} else if ("enviarEmailPedido".equalsIgnoreCase(action)) {
				return new EnviarRelatorioEmailPedidoAction().execute(array);
			} else if ("detalhesProduto".equalsIgnoreCase(action)) {
				return new DetalheProdutoAction().execute(array);
			} else if (action.equalsIgnoreCase("simulacaoBeneficio")) {
				return new SimulacaoBeneficioAction().execute(array);
			} else if ("descricaoProduto".equalsIgnoreCase(action)) {
				return new BuscarDescricaoProdutoAction().execute(array);
			} else if ("listaTerritorios".equalsIgnoreCase(action)) {
				return new CidadesTerritoriosAction().execute();
			} else if ("listarPedidos".equalsIgnoreCase(action)) {
				return new ListarPedidoClientesAction().execute(array);
			} else if ("recuperarDescricaoProduto".equalsIgnoreCase(action)) {
				return new BuscarDescricaoProdutoAction().execute(array);
			} else if ("marcarCurtir".equalsIgnoreCase(action)) {
				return new MarcarCurtirProdutoAction().execute(array);
			} else if ("escolherLista".equalsIgnoreCase(action)) {
				return new EscolherListaProdutoAction().execute(array);
			} else if ("listarGondolaCliente".equalsIgnoreCase(action)) {
				return new ListarGondolaClienteAction().execute(array);
			} else if ("detalhesPedidoCad".equalsIgnoreCase(action)) {
				return new PedidosAction().detalhaPedidoPorCAD(array);
			} else if ("calcularDetalhes".equalsIgnoreCase(action)) {
				return new PedidosAction().calculaDetalhes(array);
			} else if ("listaFiliais".equalsIgnoreCase(action)) {
				return new FilialAction().execute(array);
			} else if ("beneficiosCustomizados".equalsIgnoreCase(action)) {
				return new BeneficiosCustomizadosAction().execute(array);
			} else if ("beneficiosCustomizadosDetalhe".equalsIgnoreCase(action)) {
				return new BeneficiosCustomizadosDetalheAction().execute(array);
			} else if ("aplicarBeneficioCustomizado".equalsIgnoreCase(action)) {
				return new AplicarBeneficioCustomizadoAction().execute(array);
			} else if ("retornosCliente".equalsIgnoreCase(action)) {
				return new RetornosClienteAction().execute(array);
			} else if ("detalheItemGondola".equalsIgnoreCase(action)) {
				return new DetalheItemGondolaClienteAction().execute(array);
			} else if ("cadastrarItemGondola".equalsIgnoreCase(action)) {
				return new CadastrarItemGondolaClienteAction().execute(array);
			} else if ("listaItensKit".equalsIgnoreCase(action)) {
				return new KitAction().obtemItensDoKit(array);
			} else if ("carregarCaminhao".equalsIgnoreCase(action)) {
				return new PedidosAction().carregaDetalhePedidoCaminhao();
			} else if ("posicaoVendas".equalsIgnoreCase(action)) {
				return new PosicaoVendasAction().execute();
			} else if ("calculaPreco".equalsIgnoreCase(action)) {
				Date tempo = new Date();
				long tempoInicio = tempo.getTime();
				System.out.println("Dispatcher -  calculo de preco - Inicio: " + tempoInicio);
				return new CalculaPrecoAction().execute(array);
			} else if ("listarCortes".equalsIgnoreCase(action)) {
				return new ListarCortesAction().execute(array);
			} else if ("cadastroAtualizacao".equalsIgnoreCase(action)) {
				return new RepresentanteAction().execute(array);
			} else if ("itensSimilares".equalsIgnoreCase(action)) {
				return new ItensSimilaresAction().execute(array);
			} else if ("itensDisponiveis".equalsIgnoreCase(action)) {
				return new ItensDisponiveisAction().execute(array);
			} else if ("obtemItem".equalsIgnoreCase(action)) {
				return new ItensDisponiveisAction().obtemItem(array);
			} else if ("calculaPreco".equalsIgnoreCase(action)) {
				return new CalculaPrecoAction().execute(array);
			} else if ("alterarFilialItem".equalsIgnoreCase(action)) {
				return new AlterarFilialItemAction().execute(array);
			} else if ("alterarFilialItemPromocao".equalsIgnoreCase(action)) {
				return new AlterarFilialPromocaoAction().execute(array);
			} else if ("alteraQuantidadeItem".equalsIgnoreCase(action)) {
				return new AlterarQuantidadeAction().execute(array);
			} else if ("validarCondicaoPagamento".equalsIgnoreCase(action)) {
				return new ValidarCondicaoPagamentoAction().execute(array);
			} else if ("recuperarDetalhePEE".equalsIgnoreCase(action)) {
				return new RecuperaDetalhePEEAction().execute(array);
			} else if ("recuperarDadosBuscaAvancada".equalsIgnoreCase(action)) {
				return new RecuperarDadosBuscaAvancadaAction().execute(array);
			} else if ("alterarGrupos".equalsIgnoreCase(action)) {
				return new AlterarGruposMercadoriaAction().execute(array);
			} else if ("alterarCategorias".equalsIgnoreCase(action)) {
				return new AlterarCategoriaMercadoriaAction().execute(array);
			} else if ("alterarSubCategorias".equalsIgnoreCase(action)) {
				return new AlterarSubCategoriaMercadoriaAction().execute(array);
			} else if ("comissaoSimbolo".equalsIgnoreCase(action)) {
				return new ComissaoAction().execute(array);
			} else if ("obterPosicaoVendasPorPrazoDePagamento".equalsIgnoreCase(action)) {
				return new PosicaoVendasAction().obterPosicaoVendasPorPrazoDePagamento(array);
			} else if ("obterPosicaoVendasPorCliente".equalsIgnoreCase(action)) {
				return new PosicaoVendasAction().obterPosicaoVendasPorCliente(array);
			} else if ("fechaPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().fechaPedido(array);
			} else if ("listarPromocoes".equalsIgnoreCase(action)) {
				return new PromocaoAction().listarPromocoes(array);
			} else if ("listarPremiosDaPromocao".equalsIgnoreCase(action)) {
				return new PromocaoAction().listarPremiosDisponiveisDaPromocao(array);
			} else if ("excluirMercadoria".equalsIgnoreCase(action)) {
				return new PedidosAction().excluiMercadoriaLista(array);
			} else if ("calcularDetalhesNotaFiscal".equalsIgnoreCase(action)) {
				return new PedidosAction().calculaValoresNF(array);
			} else if ("listarGrupoMercadoria".equalsIgnoreCase(action)) {
				return new PromocaoAction().listarGrupoMercadoria();
			} else if ("listarUnidadeNegocio".equalsIgnoreCase(action)) {
				return new PromocaoAction().listarUnidadeNegocio();
			} else if ("listarItensDisponiveisDaPromocao".equalsIgnoreCase(action)) {
				return new PromocaoAction().listarItensDisponiveisDaPromocao(array);
			} else if ("obterValorRestantePromocao".equalsIgnoreCase(action)) {
				return new PromocaoAction().obterValorRestantePromocao(array);
			} else if ("gravaPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().gravaPedido(array);
			} else if ("criaNovoPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().criaNovoPedido(array);
			} else if ("alteraPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().limpaTabelasTemporaria(array);
			} else if ("regeraPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().regeraPedidoByFilial(array);
				/*
				 * } else if
				 * ("deletarItemGondolaCliente".equalsIgnoreCase(action)) {
				 * return DeletarItemGondolaClienteAction().execute(array); }
				 * else if
				 * ("detalheAlterarItemGondola".equalsIgnoreCase(action)) {
				 * return
				 * DetalheAlterarItemGondolaClienteAction().execute(array); }
				 * else if ("alterarItemGondola".equalsIgnoreCase(action)) {
				 * return AlterarItemGondolaClienteAction().execute(array);
				 */
			} else if ("aplicaVariacao".equalsIgnoreCase(action)) {
				return new CalculaPrecoAction().aplicaDesconto(array);
			} else if ("abandonaPedido".equalsIgnoreCase(action)) {
				return new PedidosAction().abandonaPedido(array);
			} else if ("regeraPedidoByCortes".equalsIgnoreCase(action)) {
				return new PedidosAction().regeraPedidoByCortes(array);
			} else {
				return new ActionResult(Status.INVALID_ACTION);
			}

		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			return new ActionResult(Status.JSON_EXCEPTION, e.getMessage());
		}
	}

}