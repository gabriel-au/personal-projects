package br.com.martins.vendas.martins.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.RelacaoGiroService;
import br.com.martins.vendas.vo.RelacaoGiro;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarTerritorioRcaAction {

	private static final String TAG = ListarTerritorioRcaAction.class.getName();

	public ActionResult execute(JSONArray args) {

		ActionResult result = new ActionResult(Status.ERROR);
		try {
			JSONObject json = new JSONObject();
			List<RelacaoGiro> listaRelacaoGiro = RelacaoGiroService.listaRelacaoGiroRepresentante(args.getInt(0));
			json = JsonUtil.toJson("RelacaoGiros", listaRelacaoGiro);
			result = new ActionResult(Status.OK, json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}
	
}