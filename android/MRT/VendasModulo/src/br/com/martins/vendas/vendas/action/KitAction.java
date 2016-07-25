package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.ItensDisponiveisService;
import br.com.martins.vendas.services.calculodepreco.Item;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

/**
 * The Class KitAction.
 */
public class KitAction {

	private static final String TAG = KitAction.class.getName();
	
	/**
	 * Execute.
	 *
	 * @param array the array
	 * @return the action result
	 * @throws Exception the exception
	 */
	public ActionResult obtemItensDoKit(JSONArray array) throws Exception {
		JSONObject json = new JSONObject();
		
		try{
			JSONObject parameters = array.getJSONObject(0);
			
			int codigoMercadoriaKit = parameters.getInt("codigoMercadoriaKit");
			int codigoFilialExpedicao = parameters.getInt("codigoFilialExpedicao");
			int codigoFilialFaturamento = parameters.getInt("codigoFilialFaturamento");
			
			List<Item> itens = ItensDisponiveisService.obtemItensDoKit(codigoMercadoriaKit, 
					codigoFilialExpedicao, 
					codigoFilialFaturamento);
			
			json.put("itens", JsonUtil.toJsonArray(itens));
			
			return new ActionResult(Status.OK, json);
			} catch (Exception e){
				return new ActionResult(Status.ERROR, TAG + " - " + e.getLocalizedMessage());
			}
	}
}
