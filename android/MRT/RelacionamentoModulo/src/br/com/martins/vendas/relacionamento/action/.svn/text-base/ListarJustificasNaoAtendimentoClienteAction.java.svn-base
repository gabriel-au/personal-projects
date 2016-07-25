package br.com.martins.vendas.relacionamento.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.JustificativaNaoAtendimentoService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarJustificasNaoAtendimentoClienteAction {

	private static final String TAG = ListarJustificasNaoAtendimentoClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);
		try {

			JSONObject json = JsonUtil.toJson("Justificativas", JustificativaNaoAtendimentoService.listarJustificativas());
			result = new ActionResult(Status.OK, json);

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}