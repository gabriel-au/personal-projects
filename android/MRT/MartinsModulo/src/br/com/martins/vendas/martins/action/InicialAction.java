package br.com.martins.vendas.martins.action;

import org.json.JSONArray;

import br.com.martins.vendas.services.InicialService;
import br.com.martins.vendas.vo.Inicial;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class InicialAction {

	public ActionResult execute(JSONArray args) throws Exception {

		Integer codigoRepresentante = !args.isNull(0) ? args.getInt(0) : null;
		Integer codigoTerritorio = !args.isNull(1) ? args.getInt(1) : null;
		Inicial inicial = InicialService.carregaDadosIniciais(codigoRepresentante, codigoTerritorio);
		return new ActionResult(Status.OK, JsonUtil.toJson("inicial", inicial));
		
	}
}