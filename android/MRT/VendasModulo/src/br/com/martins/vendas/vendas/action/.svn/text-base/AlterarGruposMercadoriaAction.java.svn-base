package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.MercadoriaService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;
import com.brq.mobile.framework.util.Util;

public class AlterarGruposMercadoriaAction {

	private static final String TAG = AlterarGruposMercadoriaAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		ActionResult result = new ActionResult(Status.ERROR);
		try {
			
			JSONObject json = array.getJSONObject(0);
			Integer codigoGrupo = Util.getInteger(json.getString("codigoGrupoMercadoria"));
			if (codigoGrupo != null) {
				json.put("categorias", JsonUtil.toJsonArray(MercadoriaService.obtemCategoriaMercadoria(codigoGrupo)));
			} else {
				json.put("categorias", JsonUtil.toJsonArray(MercadoriaService.obtemCategoriaMercadoria()));
			}
			result = new ActionResult(Status.OK, json);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());

			return new ActionResult(Status.ERROR, jsonObject);
		}
		return result;
	}
	
}