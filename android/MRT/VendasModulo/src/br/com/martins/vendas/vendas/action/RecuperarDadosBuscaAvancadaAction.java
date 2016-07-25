package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.services.PalavraChaveService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class RecuperarDadosBuscaAvancadaAction {

	private static final String TAG = RecuperarDadosBuscaAvancadaAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		ActionResult result = new ActionResult(Status.ERROR);

		try {

			JSONObject json = new JSONObject();
			json.put("grupos"       , JsonUtil.toJsonArray(MercadoriaService.obtemGrupoMercadoria()));
			json.put("categorias"   , JsonUtil.toJsonArray(MercadoriaService.obtemCategoriaMercadoria()));
			json.put("subcategorias", JsonUtil.toJsonArray(MercadoriaService.obtemSubCategoriaMercadoria()));
			json.put("fornecedores" , JsonUtil.toJsonArray(PalavraChaveService.obtemFornecedores()));
			
			result = new ActionResult(Status.OK, json);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());

			result = new ActionResult(Status.ERROR, jsonObject);
		}
		
		return result;
	}
	
}