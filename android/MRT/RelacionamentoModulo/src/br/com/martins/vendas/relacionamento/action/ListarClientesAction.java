package br.com.martins.vendas.relacionamento.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.relacionamento.dto.PedidoCliente;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.vo.Cliente;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarClientesAction {

	private static final String TAG = ListarClientesAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			//	long tempo1 = System.currentTimeMillis();
			
			JSONObject json = new JSONObject();
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
					pedidoCliente.canal=cliente.canal;
					pedidoCliente.mensagem=cliente.mensagem;
					pedidoCliente.bloqueado=cliente.bloqueado;
					pedidoCliente.motivoBloqueio=cliente.descMotivoSituacao;
					pedidoCliente.telefone=cliente.telefone;
					pedidoCliente.codigoTerritorio=cliente.codigoTerritorio;
					pedidoCliente.codigoSegmento = cliente.codigoSegmento;
					pedidoCliente.diferencaDias = cliente.diferencaDias;
					pedidoCliente.tipoContratoVendor= cliente.tipoContratoVendor;
					pedidoCliente.statusAtendimento = cliente.statusAtendimento;
					pedidoCliente.tipoAnaliseCredito = cliente.tipoAnaliseCredito;
					
					if(cliente.dataUltimoPedido != null && cliente.maximoDiasSemAtendimento != null && cliente.maximoDiasSemAtendimento > 0
							&& cliente.mediaDiasSemAtendimento != null && cliente.mediaDiasSemAtendimento > 0) {
						pedidoCliente.positivar = DateUtil.adicionarDias(DateUtil.obterDataAtual(), cliente.maximoDiasSemAtendimento);
					}

					listaPedidoCliente.add(pedidoCliente);
				}
			}

			json = JsonUtil.toJson("Clientes", listaPedidoCliente);
			result = new ActionResult(Status.OK, json);
			
		//	long tempo4=System.currentTimeMillis();		
		//	Log.i(TAG, "********** TEMPO TOTAL JAVA  "  + (tempo4 - tempo1));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}