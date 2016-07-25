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

public class AlterarFilialPromocaoAction {
	
	private static final String	TAG	= AlterarFilialPromocaoAction.class.getName();

	public ActionResult execute(JSONArray args) {
		try {
			Item item = null;
			JSONObject jsonControl = args.getJSONObject(0);
			String origem = jsonControl.getString("origem");
			JSONObject jsonData = args.getJSONObject(1);
			JSONObject jsonCliente = jsonData.getJSONObject("cliente");
			JSONObject jsonRepresentante = jsonData.getJSONObject("representante");
			boolean ignoraBloqueioItemSimiliar = true;
			
			item = FilialService.verificaItemPromocaoPorFilial(
					ignoraBloqueioItemSimiliar,
					jsonCliente.getInt("codigoCliente"), 
					jsonData.getInt("codigoFilialExpedicao"), 
					jsonData.getInt("codigoFilialFaturamento"),
					jsonCliente.getInt("numeroRelacionamentoCliente"),
					jsonCliente.getInt("codigoGrupoCliente"),
					jsonCliente.getString("flagAlvaraPsicotropico"), 
					jsonRepresentante.getInt("codigoRepresentante"), 
					jsonCliente.getInt("codigoCidade"),
					jsonCliente.getInt("codigoAtividade"), 
					jsonCliente.getInt("codigoSupervisor"),
					jsonCliente.getString("uf"), 
					jsonData.getInt("codigoCondicaoPagamento"),
					false,
					null,
					jsonData.getString("codigoMercadoria"),
					null,
					new ItemFiltro(),
					jsonData.getInt("codigoPromocao"),
					origem
					
			);	
			
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
