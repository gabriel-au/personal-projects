package br.com.martins.vendas.vendas.action;


import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.ProdutoService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;

public class MarcarCurtirProdutoAction {

	public ActionResult execute(JSONArray array) throws JSONException {
		Integer produtoId = array.getInt(0);
		Integer codTerCli = array.getInt(1);
		Integer itefilFat = array.getInt(2);
		Integer codGrpAfd = array.getInt(3);
		boolean curtir    = (0 == array.getInt(4));
		Integer codigoFilialExpedicao = array.getInt(5);
		
		try {
			ProdutoService.atualizarPontoEncontroEletronico(produtoId, codTerCli, itefilFat, codGrpAfd, curtir, false,codigoFilialExpedicao);
		} catch (Exception e) {
			return new ActionResult(Status.ERROR, e.getLocalizedMessage());
		}
		
		return new ActionResult(Status.OK);
	}

}
