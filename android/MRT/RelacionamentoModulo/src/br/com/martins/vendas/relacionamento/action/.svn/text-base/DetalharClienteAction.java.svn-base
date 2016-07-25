package br.com.martins.vendas.relacionamento.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.relacionamento.dto.DetalheCliente;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.JustificativaNaoAtendimentoService;
import br.com.martins.vendas.services.pedido.PedidoService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Pedido;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.JsonUtil;

public class DetalharClienteAction {

	private static final String TAG = DetalharClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);
		try {
			DetalheCliente detalheCliente = new DetalheCliente();
			Cliente cliente = ClienteService.obterDetalheCliente(args.getInt(0));

			if (cliente != null) {
				Pedido pedido = PedidoService.obtemUltimoPedido(cliente.codigoCliente);
				if (pedido != null && pedido.dataPedido != null && cliente.maximoDiasSemAtendimento != null) {
					cliente.positivar = DateUtil.adicionarDias(pedido.dataPedido, cliente.maximoDiasSemAtendimento);
				}
				
			}
			
			detalheCliente.cliente = cliente;
			detalheCliente.justificativaNaoAtendimento = JustificativaNaoAtendimentoService.obterUltimaJustificativaCliente(args.getInt(0));

			JSONObject json = JsonUtil.toJson("Detalhe", detalheCliente);
			result = new ActionResult(Status.OK, json);

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}