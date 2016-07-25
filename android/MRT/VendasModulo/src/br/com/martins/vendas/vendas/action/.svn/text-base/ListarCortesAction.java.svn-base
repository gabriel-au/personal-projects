package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;

import br.com.martins.vendas.dao.PedidoCortesDAO;
import br.com.martins.vendas.vo.Cortes;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarCortesAction {

	public ActionResult execute(JSONArray array) throws Exception {
		Integer numPedido = array.getInt(0);
		List<Cortes>listaCortes = PedidoCortesDAO.calculaValorLiquido(PedidoCortesDAO.carregaListaCortes(numPedido));
		
		return new ActionResult(Status.OK, JsonUtil.toJson("listaCortes", listaCortes));
	}

}
