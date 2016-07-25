package br.com.martins.vendas.martins.action;

import org.json.JSONObject;

import br.com.martins.vendas.services.RepresentanteService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class VerificarGmRca {

	private static final String TAG = VerificarGmRca.class.getName();
	
	public ActionResult execute() {
		ActionResult result = null;
		try {
			JSONObject json = new JSONObject();
			json = JsonUtil.toJson("Representante", RepresentanteService.findRepresentante(null));
			result = new ActionResult(Status.OK, json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}
		return result;
	}

}