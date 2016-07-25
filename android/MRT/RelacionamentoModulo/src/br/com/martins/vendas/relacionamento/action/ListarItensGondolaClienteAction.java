package br.com.martins.vendas.relacionamento.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.GondolaService;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarItensGondolaClienteAction {

	private static final String TAG = ListarItensGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);
		try {
			List<Gondola> listaGondulaCliente = GondolaService.listarItensGondolaCliente(args.getInt(0));
			JSONObject json = JsonUtil.toJson("Gondolas", listaGondulaCliente);
			result = new ActionResult(Status.OK, json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}