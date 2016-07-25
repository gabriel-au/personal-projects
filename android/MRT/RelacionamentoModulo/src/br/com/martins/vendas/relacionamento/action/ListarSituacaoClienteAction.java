package br.com.martins.vendas.relacionamento.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.vo.SituacaoCliente;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarSituacaoClienteAction {

	private static final String TAG = ListarSituacaoClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);
		try {
			Integer codigoTerritorio = !args.isNull(0) ? args.getInt(0) : null;
			
			List<SituacaoCliente> listaSituacaoClientes = ClienteService.listarSituacaoClientes(codigoTerritorio);
			JSONObject json = JsonUtil.toJson("Clientes", listaSituacaoClientes);
			result = new ActionResult(Status.OK, json);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}