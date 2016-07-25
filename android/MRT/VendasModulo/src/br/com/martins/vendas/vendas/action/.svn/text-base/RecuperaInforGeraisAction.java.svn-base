package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.AlteraPedidoService;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.FilialService;
import br.com.martins.vendas.services.ModeloDistribuicaoService;
import br.com.martins.vendas.services.RelacaoGiroService;
import br.com.martins.vendas.services.RepresentanteService;
import br.com.martins.vendas.services.TitulosService;
import br.com.martins.vendas.services.pedido.PedidoService;
import br.com.martins.vendas.vendas.dto.InformacoesGerais;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;import br.com.martins.vendas.vo.Filial;
import br.com.martins.vendas.vo.FilialCliente;
import br.com.martins.vendas.vo.ModeloDistribuicao;
import br.com.martins.vendas.vo.ParametroMinimoFilial;
import br.com.martins.vendas.vo.Pedido;
import br.com.martins.vendas.vo.PontoMinimoPedidoSegmento;
import br.com.martins.vendas.vo.Representante;
import br.com.martins.vendas.vo.Titulo;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class RecuperaInforGeraisAction {

	private static final String TAG = RecuperaInforGeraisAction.class.getName();

	public ActionResult execute(JSONArray array) {
		ActionResult result = new ActionResult(Status.ERROR);
		
		try {
			InformacoesGerais gerais = new InformacoesGerais();
			Integer clientId = array.getInt(0);	
			Integer territoryId = array.getInt(1);
			gerais.numeroPedido = !array.isNull(3) ? array.getInt(3) : 0;
			boolean isPrimeiraPesquisa = array.getBoolean(4);			
			String origem =!array.isNull(5) ? array.getString(5): "";
			
			Cliente cliente = ClienteService.obterDetalheCliente(clientId);
			Integer canal = cliente.canal;
			String estadoUF = cliente.uf;
			
			FilialCliente filialCliente = new FilialCliente();
			ModeloDistribuicao modeloDistribuicao = new ModeloDistribuicao();
			
			if(PedidoService.isPedidoExiste(gerais.numeroPedido)){
				try {
					gerais.isPedidoExiste = true;
					
					CondicaoPagamento condicaoPagamento = new CondicaoPagamento();
					Pedido pedido= AlteraPedidoService.carregaDadosInfoGerais(gerais.numeroPedido);//Passar número do pedido
					Filial filialExpedicao = new Filial();
					Filial filialFaturamento = new Filial();
					
					filialExpedicao.codigoFilial=pedido.filialExpedicao;
					filialFaturamento.codigoFilial=pedido.filialFaturamento;
					filialExpedicao.nomeFilial=FilialService.obtemNomeFilialFaturamento(pedido.filialExpedicao);
					filialFaturamento.nomeFilial=FilialService.obtemNomeFilialFaturamento(pedido.filialFaturamento);
					
					filialCliente.filialExpedicao=filialExpedicao;
					filialCliente.filialFaturamento=filialFaturamento;
					gerais.codigoModeloDistribuicao=pedido.codigoModeloDistribuicao;
					gerais.tipoVenda = TipoVendaPedido.toString(pedido.tipoVendaPedido.getValue());
					condicaoPagamento.codigoCondicaoPagamento=pedido.cliente.condicaoPagamento.codigoCondicaoPagamento;
					condicaoPagamento.descricaoCondicao=AlteraPedidoService.montaDescricaoCondicaoPagamento(pedido);
					cliente.condicaoPagamento=condicaoPagamento;					
					modeloDistribuicao.codRegraTabela=pedido.codigoModeloDistribuicao;
					
					//Carrega os itens do pedido para a tabela temporária, quando entra na tela pela primeira vez
					if(isPrimeiraPesquisa){
					gerais.numeroPedido = carregaItensPedido(gerais.numeroPedido,clientId,origem);	
					}				
				} catch (Exception e) {
					e.getMessage();
				}
				
			} else {
				gerais.isPedidoExiste = false;
				
				filialCliente = FilialService.obterFiliaisPorCliente(clientId, territoryId).get(0);
				
				//recupera Codigo do modelo de distribuicao padrao
				modeloDistribuicao = ModeloDistribuicaoService.listarModeloDistricao(cliente.codigoCliente, filialCliente.filialExpedicao.codigoFilial, filialCliente.filialFaturamento.codigoFilial, false).get(0);
	
			}		
			 
			cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(filialCliente.filialExpedicao.codigoFilial, filialCliente.filialFaturamento.codigoFilial, cliente.codigoCidadePreco, cliente.codigoTerritorio);
			cliente.codigoSupervisor = RelacaoGiroService.recuperarPorFilial(cliente.codigoTerritorio, filialCliente.filialExpedicao.codigoFilial, filialCliente.filialFaturamento.codigoFilial).codSupervisor;
			
			Integer filialId = filialCliente.filialExpedicao.codigoFilial;
			
			ParametroMinimoFilial parametroMinimoFilial = FilialService.obterParametroMinimoFilialPorFilial(filialId, estadoUF, canal);

			Integer codigoSegmento = array.getInt(2);
			PontoMinimoPedidoSegmento pontoMinimoPedidoSegmento = ClienteService.recuperaPontoMinimoPedidoSegmento(estadoUF, canal, codigoSegmento);

			BigDecimal saldoAbertoCliente = TitulosService.obterSaldoAbertoCliente(clientId);
			
			Representante represenante = null;
			try {
				represenante = RepresentanteService.findRepresentante(null);
			} catch (SQLException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return new ActionResult(Status.ERROR, e.getMessage());
			}

			BigDecimal fatorKMargem = RepresentanteService.calularFatorKMargem(represenante.fatorKMargem);
			BigDecimal valorMinimoPontoPedido = RepresentanteService.obterValorMinimoPontoPedido(fatorKMargem, pontoMinimoPedidoSegmento.valorLancamentoPedido);

			//Obter o total de titulos em aberto do cliente
			List<Titulo> titulos = TitulosService.findTitulosAberto(null, null, clientId, territoryId);
			
			gerais.nomeCliente = cliente.nomeCliente;
			/*gerais.filialCliente = filialCliente;*/
			gerais.valorExpedicao = parametroMinimoFilial.valorMinimoExpedicao;
			gerais.valorBoletoBancario = parametroMinimoFilial.valorMinimoBoletoBancario;
			gerais.valorMinimoPontoPedido = valorMinimoPontoPedido;
			gerais.valorMaximoBrindePoupeCerto = represenante.valorMaxBrinde;
			gerais.valorLimiteCreditoNomeado = cliente.valorLimiteCredito;
			gerais.valorSaldoAbertoCliente = saldoAbertoCliente;
			gerais.totalTitulosAbertos = titulos.size();
			gerais.codigoModeloDistribuicao = modeloDistribuicao.codRegraTabela;		

			
			// Altera o status do novo pedido na tabela PCASOS
			Pedido pedido = new Pedido();
			pedido.numeroPedido = gerais.numeroPedido.toString();
			pedido.cliente = cliente;
			pedido.filialExpedicao = filialCliente.filialExpedicao.codigoFilial;
			pedido.filialFaturamento = filialCliente.filialFaturamento.codigoFilial;
//			PedidoService.atualizaStatusPcasos(PedidoStatus.CRIADO);
			PedidoService.atualizaStatusPedidoPcasos(pedido, PedidoStatus.CRIADO);
			
			
			// PRAPARACAO DOS DADOS DE RETORNO
			JSONObject jsonInfoGerais = new JSONObject();
			jsonInfoGerais.put("infoGerais",        JsonUtil.toJson(gerais));
			jsonInfoGerais.put("cliente",        	JsonUtil.toJson(cliente));
			jsonInfoGerais.put("filialFaturamento", JsonUtil.toJson(filialCliente.filialFaturamento));
			jsonInfoGerais.put("filialExpedicao",   JsonUtil.toJson(filialCliente.filialExpedicao));
			
			result = new ActionResult(Status.OK, jsonInfoGerais);

		} catch (IntegrationException integrationException) {
			Log.e(TAG, integrationException.getMessage());
			result = new ActionResult(Status.ERROR, integrationException.getMessage());
		} catch (JSONException jsonException) {
			Log.e(TAG, jsonException.getMessage());
			result = new ActionResult(Status.ERROR, jsonException.getMessage());
		}
		
		return result;
	}
	
	private Integer carregaItensPedido(Integer codigoPedido,Integer clientId,String origem) throws Exception{
		Integer novoPedido;
		
		if (!"".equals(origem) && origem.equals("cortes")) {
			// recarrega o(s) item(ns) cortado(s) para regeração do pedido.
			novoPedido = PedidoService.criaNovoPedido();
			AlteraPedidoService.carregaDadosItensRegeraByCortes(codigoPedido, clientId);
		} else if (PedidoService.isPedidoPreparadoReprovado(codigoPedido)) {
			// recarrega o(s) item(ns) da(s) filial(is) reprovada(s) para
			// regeração do pedido.
			novoPedido = PedidoService.criaNovoPedido();
			AlteraPedidoService.carregaDadosItensRegeraByFilial(codigoPedido, clientId);
		} else {
			AlteraPedidoService.carregaDadosItensAlteraPedido(codigoPedido, clientId);
			novoPedido = codigoPedido;
		}
		
		return novoPedido;
	}

}