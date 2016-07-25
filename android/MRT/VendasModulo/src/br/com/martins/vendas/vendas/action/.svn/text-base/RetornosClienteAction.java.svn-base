package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;

import br.com.martins.vendas.services.RetornosClienteService;
import br.com.martins.vendas.vo.RetornosCliente;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class RetornosClienteAction {

	public ActionResult execute(JSONArray array) throws Exception {
		Integer numeroPedido = array.getInt(0);
		List<RetornosCliente> listaRetornos = RetornosClienteService.obterRetornosCliente(numeroPedido);
		return new ActionResult(Status.OK, JsonUtil.toJson("listaRetornos", listaRetornos));
	}

}
