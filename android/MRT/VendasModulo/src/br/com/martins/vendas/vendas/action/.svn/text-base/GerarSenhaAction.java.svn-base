package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.SenhaService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;

public class GerarSenhaAction {

	private static final String TAG = GerarSenhaAction.class.getName();

	public ActionResult execute(JSONArray ja) throws Exception {
		String preSenha;
		int codRep;
		JSONObject json;

		try {
			preSenha = ja.getString(0);
			codRep = ja.getInt(1);
			json = SenhaService.gerarSenha(codRep, preSenha);
			return new ActionResult(json.has("senha") ? Status.OK : Status.ERROR, json);
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			throw e;
		}
	}

}
