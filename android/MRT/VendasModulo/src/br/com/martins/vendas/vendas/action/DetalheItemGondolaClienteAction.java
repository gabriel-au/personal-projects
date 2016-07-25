package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.GondolaService;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class DetalheItemGondolaClienteAction {

	private static final String TAG = DetalheItemGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		try {
			
			final int codigoCliente    = args.getInt(0);
			final int codigoMercadoria = args.getInt(1);
			
			Item item       = ItemPedidoService.obtemItemTemporario(codigoMercadoria);
			Gondola gondola = GondolaService.adicionarItemGondola(codigoCliente, item);
			
			if (gondola != null) {
				JSONObject json = JsonUtil.toJson("Gondola", gondola);
				return new ActionResult(Status.OK, json); 
			}
			
			return new ActionResult(Status.OK);
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return new ActionResult(Status.ERROR, e.getMessage());
		}
	}

}