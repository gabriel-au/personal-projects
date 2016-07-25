package br.com.martins.vendas.relacionamento.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.relacionamento.dto.DetalheCliente;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.TitulosService;
import br.com.martins.vendas.services.pedido.PedidoService;
import br.com.martins.vendas.vo.Pedido;
import br.com.martins.vendas.vo.Titulo;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class RelacionamentoClienteAction {

	private static final String TAG = RelacionamentoClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);
		try {
			DetalheCliente detalheCliente = new DetalheCliente();
			
			//Obter o total de titulos em aberto do cliente
			List<Titulo> titulos = TitulosService.findTitulosAberto(null, null, args.getInt(0), null);
			detalheCliente.totalTitulosAbertos = titulos.size();
			
			//Obter o total de pedidos do cliente
			List<Pedido> pedidos = PedidoService.obtemTodosPedidos(args.getInt(0));
			detalheCliente.totalPedidos = pedidos.size();
			
			detalheCliente.cliente = ClienteService.obterRelacionamentoCliente(args.getInt(0));
			
			JSONObject json = JsonUtil.toJson("Detalhe", detalheCliente);
			result = new ActionResult(Status.OK, json);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}