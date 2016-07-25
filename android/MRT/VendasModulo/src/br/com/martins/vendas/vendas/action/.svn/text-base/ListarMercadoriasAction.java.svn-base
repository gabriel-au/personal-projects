package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;

import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.vo.Kit;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarMercadoriasAction {

	public ActionResult execute(JSONArray array) throws Exception {		
		Integer codigoKit =array.getInt(0);
		List<Kit> listaMercadoria = ItemPedidoService.buscaMercadoriasKit(codigoKit);
		return new ActionResult(Status.OK, JsonUtil.toJson("listaMercadoria", listaMercadoria));
	}

}
