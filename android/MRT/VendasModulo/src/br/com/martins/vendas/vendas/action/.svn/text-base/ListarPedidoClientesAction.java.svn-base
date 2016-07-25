package br.com.martins.vendas.vendas.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.pedido.PedidoService;
import br.com.martins.vendas.vendas.dto.PedidoCliente;
import br.com.martins.vendas.vo.Cliente;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarPedidoClientesAction {

	private static final String TAG = ListarPedidoClientesAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			List<PedidoCliente> listaPedidoCliente = new ArrayList<PedidoCliente>();
			PedidoCliente pedidoCliente = null;

			Integer codigoTerritorio = !args.isNull(0) ? args.getInt(0) : null;
			List<Cliente> listaClientes = ClienteService.listarClientes(codigoTerritorio);

			if (!listaClientes.isEmpty()) {
				for (Cliente cliente : listaClientes) {
					pedidoCliente = new PedidoCliente();
					pedidoCliente.codigoCliente = cliente.codigoCliente;
					pedidoCliente.indicativoFavorito = cliente.indicativoFavorito;
					pedidoCliente.indicativoTop = cliente.indicativoTop;
					pedidoCliente.nomeCliente = cliente.nomeCliente;
					pedidoCliente.nomeFantasia = cliente.nomeFantasia;
					pedidoCliente.situacao = cliente.situacao;
					pedidoCliente.canal = cliente.canal;
					pedidoCliente.mensagem = cliente.mensagem;
					pedidoCliente.bloqueado = cliente.bloqueado;
					pedidoCliente.motivoBloqueio = cliente.descMotivoSituacao;
					pedidoCliente.telefone = cliente.telefone;
					pedidoCliente.codigoTerritorio = cliente.codigoTerritorio;
					pedidoCliente.tipoContratoVendor = cliente.tipoContratoVendor;
					pedidoCliente.tipoCobrancaCondicaoPagamento = cliente.tipoCobrancaCondicaoPagamento;
					pedidoCliente.codigoSegmento = cliente.codigoSegmento;
					pedidoCliente.statusAtendimento = cliente.statusAtendimento;
					pedidoCliente.diferencaDias = cliente.diferencaDias;
					pedidoCliente.tipoAnaliseCredito = cliente.tipoAnaliseCredito;
					pedidoCliente.nomeCidade = cliente.nomeCidade;
					pedidoCliente.uf = cliente.uf;
					
					if (cliente.dataUltimoPedido != null
							&& cliente.maximoDiasSemAtendimento != null
							&& cliente.maximoDiasSemAtendimento > 0
							&& cliente.mediaDiasSemAtendimento != null 
							&& cliente.mediaDiasSemAtendimento > 0) {
						pedidoCliente.positivar = DateUtil.adicionarDias(DateUtil.obterDataAtual(), cliente.maximoDiasSemAtendimento);
					}
					
					listaPedidoCliente.add(pedidoCliente);
				}
			}

			JSONObject json = new JSONObject();
			json = JsonUtil.toJson("Clientes", listaPedidoCliente);
			json = verificaStatus(json);
			result = new ActionResult(Status.OK, json);
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

	private JSONObject verificaStatus(JSONObject json) throws JSONException {
		// Verifica se existe uma linha na tabela, caso negativo, realiza a correcao.
		PedidoService.verificaTabelasStatusPedido();
		
		// Verifica o status do pedido na tabela PCASOS
		PedidoStatus pedidoStatus = PedidoService.obtemStatusPcasos();
		
		// Obtem o codigo do cliente
		String[] dados = PedidoService.obtemDadosPcasos();
		Integer codigoCliente = dados[0] != null ? Integer.valueOf(dados[0]) : 0;
		Integer codigoClienteTerritorio = dados[5] != null ? Integer.valueOf(dados[5]) : 0;
		Cliente cliente = ClienteService.obterDetalheCliente(codigoCliente, codigoClienteTerritorio);
		
		if (null == pedidoStatus) { // || codigoClientePcasos != array.getInt(0)) {
			json.put("numeroStatus", PedidoStatus.EM_CRIACAO.getValue());
		} else {
			json.put("numeroStatus", pedidoStatus.getValue());
		}
		
		if (null != cliente) {
			json.put("cliente", cliente);
			json.put("descricaoCliente", cliente.codigoCliente + " - " + cliente.nomeCliente);
		}
		
		return json;
	}

}