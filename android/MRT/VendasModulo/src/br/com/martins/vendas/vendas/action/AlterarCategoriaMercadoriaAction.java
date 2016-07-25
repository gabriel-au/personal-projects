package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.vo.CategoriaMercadoria;
import br.com.martins.vendas.vo.GrupoMercadoria;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;
import com.brq.mobile.framework.util.Util;

public class AlterarCategoriaMercadoriaAction {

	private static final String TAG = AlterarCategoriaMercadoriaAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		ActionResult result = new ActionResult(Status.ERROR);

		try {

			JSONObject json = array.getJSONObject(0);
			
			Integer codigoGrupo        = Util.getInteger(json.getString("codigoGrupoMercadoria"));
			Integer codigoCategoria    = Util.getInteger(json.getString("codigoCategoriaMercadoria"));
			String  descricaoCategoria = json.getString("descricaoCategoriaMercadoria");
			
			if (codigoGrupo != null && codigoCategoria != null ) {
				json.put("subcategorias", JsonUtil.toJsonArray(MercadoriaService.obtemSubCategoriaMercadoria(codigoGrupo, codigoCategoria)));				
				return new ActionResult(Status.OK, json);
			}
							
			CategoriaMercadoria categoria = MercadoriaService.obtemCategoriaMercadoria(codigoCategoria, descricaoCategoria);
			GrupoMercadoria grupo = MercadoriaService.obtemGrupoMercadoria(categoria.codigoGrupoMercadoria);
			json.put("grupo", JsonUtil.toJson(grupo));
			json.put("subcategorias", JsonUtil.toJsonArray(MercadoriaService.obtemSubCategoriaMercadoria(codigoGrupo, codigoCategoria)));
				
			result = new ActionResult(Status.OK, json);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());

			return new ActionResult(Status.ERROR, jsonObject);
		}
		
		return result;
	}
	
}