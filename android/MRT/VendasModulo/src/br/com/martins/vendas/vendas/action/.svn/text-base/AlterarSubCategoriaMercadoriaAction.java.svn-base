package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.vo.SubCategoriaMercadoria;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;
import com.brq.mobile.framework.util.Util;

public class AlterarSubCategoriaMercadoriaAction {

	private static final String TAG = AlterarSubCategoriaMercadoriaAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		try {
			JSONObject json = array.getJSONObject(0);
			Integer codigoGrupo             = Util.getInteger(json.getString("codigoGrupoMercadoria"));
			Integer codigoCategoria         = Util.getInteger(json.getString("codigoCategoriaMercadoria"));
			Integer codigoSubCategoria      = Util.getInteger(json.getString("codigoSubCategoriaMercadoria"));
			String  descricaoSubCategoria   = json.getString("descricaoSubCategoriaMercadoria");
			
			if (codigoGrupo != null && codigoCategoria != null && codigoSubCategoria != null) {
				return new ActionResult(Status.OK, json);
			}
				
			if (codigoSubCategoria != null) {
					
				SubCategoriaMercadoria subCategoria = MercadoriaService.obtemSubCategoriaMercadoria(codigoSubCategoria, descricaoSubCategoria);
				json.put("grupo", JsonUtil.toJson(MercadoriaService.obtemGrupoMercadoria(subCategoria.codigoGrupoMercadoria)));
				json.put("categoria", JsonUtil.toJson(MercadoriaService.obtemCategoriaMercadoria(subCategoria.codigoGrupoMercadoria, subCategoria.codigoCategoriaMercadoria)));
			}		
						
			return new ActionResult(Status.OK, json);
			
		} catch (Exception e) {
			
			Log.e(TAG, e.getLocalizedMessage());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());

			return new ActionResult(Status.ERROR, jsonObject);
		}
	}
	
}