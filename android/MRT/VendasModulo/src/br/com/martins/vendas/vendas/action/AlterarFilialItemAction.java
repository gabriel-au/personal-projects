
package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.services.FilialService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.ItemFiltro;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class AlterarFilialItemAction {

	private static final String	TAG	= AlterarFilialItemAction.class.getName();

	public ActionResult execute(JSONArray args) {
		
		try {
			
			JSONObject jsonControl = args.getJSONObject(0);		
			JSONObject jsonData    = args.getJSONObject(1);
			
			JSONObject jsonCliente	        = jsonData.getJSONObject("cliente");
			int codigoCliente			 	= jsonCliente.getInt("codigoCliente");
			int codRelacionamentoCliente 	= jsonCliente.getInt("numeroRelacionamentoCliente");
			int codigoGrupamentoCliente  	= jsonCliente.getInt("codigoGrupoCliente");
			String flagAlvaraPsicotropico	= jsonCliente.getString("flagAlvaraPsicotropico");
			int codigoCidade 				= jsonCliente.getInt("codigoCidade");
			int codigoAtividade 			= jsonCliente.getInt("codigoAtividade");
			int codigoSupervisor 			= jsonCliente.getInt("codigoSupervisor");
			String codigoEstadoDestino 		= jsonCliente.getString("uf");
			
			ItemFiltro itemFiltro               = new ItemFiltro(jsonData.getInt("codigoMercadoria"));
			
			itemFiltro.isListaMercadoria        = "ITEMDISPONIVEL".equals(jsonControl.getString("origem"));
			itemFiltro.isListaMercadoriaSimilar = "ITEMSIMILAR".equals(jsonControl.getString("origem"));
			
			Item item = FilialService.verificaItemPorFilial(
					codigoCliente,
					jsonData.getInt("codigoFilialExpedicao"),
					jsonData.getInt("codigoFilialFaturamento"), 
					codRelacionamentoCliente, 
					codigoGrupamentoCliente,
					flagAlvaraPsicotropico,
					jsonData.getInt("codigoRepresentante"), 
					codigoCidade,
					codigoAtividade,
					codigoSupervisor, 
					codigoEstadoDestino,
					jsonData.getInt("codigoCondicaoPagamento"),
					itemFiltro);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("control", jsonControl);
			jsonObject.put("data"   , jsonData);
			
			if (item != null) {
				
				jsonObject.put("item", JsonUtil.toJson(item));
				
			}
			
 			return new ActionResult(Status.OK, jsonObject);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage());
			
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("rowIndex", args.getInt(0));
				jsonObject.put("localizedMessage", e.getLocalizedMessage());
			} catch (JSONException ex) {
				return new ActionResult(Status.ERROR, e.getLocalizedMessage());
			}
			return new ActionResult(Status.ERROR, jsonObject);
		}
	}

}