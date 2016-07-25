package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.services.TitulosService;
import br.com.martins.vendas.vo.Titulo;
import br.com.martins.vendas.vo.TituloTribanco;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class TitulosAbertoAction {

	private static final String TAG = TitulosAbertoAction.class.getName();

	public ActionResult execute(JSONArray array) {
		ActionResult result = new ActionResult(Status.ERROR);
		
		try {
			Integer idSituacao = !array.isNull(0) ? array.getInt(0) : null;
			Integer codigoCliente = !array.isNull(1) ? array.getInt(1) : null;
			String nomeCliente = (String) (!array.isNull(2) ? array.get(2) : null);
			Integer codigoTerritorio = !array.isNull(3) ? array.getInt(3) : null;
			
			List<Titulo> titulos = TitulosService.findTitulosAberto(idSituacao,nomeCliente, codigoCliente, codigoTerritorio);
			List<TituloTribanco> titulosTribanco = TitulosService.findTitulosTribanco(idSituacao,nomeCliente, codigoCliente, codigoTerritorio);
			
			JSONObject jsonTitulos = new JSONObject();
			jsonTitulos.put("titulosAbertos", JsonUtil.toJsonArray(titulos));
			jsonTitulos.put("titulosTribanco", JsonUtil.toJsonArray(titulosTribanco));
			
			result = new ActionResult(Status.OK, jsonTitulos);
			
		} catch (JSONException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			result = new ActionResult(Status.ERROR, e.getMessage());
		}
		
		return result;
	}

}
