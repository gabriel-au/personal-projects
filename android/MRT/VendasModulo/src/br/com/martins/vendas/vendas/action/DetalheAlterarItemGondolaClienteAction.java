package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.GondolaService;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class DetalheAlterarItemGondolaClienteAction {

	private static final String TAG = DetalheAlterarItemGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			Gondola gondola = GondolaService.obterItemGondolaCliente(args.getInt(0), args.getInt(1));
			JSONObject json = JsonUtil.toJson("Gondola", gondola);
			result = new ActionResult(Status.OK, json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}