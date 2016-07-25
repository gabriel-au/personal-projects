package br.com.martins.vendas.vendas.action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.ModeloDistribuicaoService;
import br.com.martins.vendas.vo.ModeloDistribuicao;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarModeloDistribuicaoAction {

	private static final String TAG = ListarModeloDistribuicaoAction.class.getName();

	public ActionResult execute(JSONArray args) throws Exception {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			JSONObject json = new JSONObject();

			List<ModeloDistribuicao> listaModeloDistribuicao = new ArrayList<ModeloDistribuicao>();

			listaModeloDistribuicao = ModeloDistribuicaoService.listarModeloDistricao(args.getInt(0), args.getInt(1), args.getInt(2), args.getBoolean(3));

			json = JsonUtil.toJson("listaModeloDistribuicao", listaModeloDistribuicao);
			result = new ActionResult(Status.OK, json);

		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			result = new ActionResult(Status.ERROR, e.getMessage());
		}
		
		return result;
	}

}