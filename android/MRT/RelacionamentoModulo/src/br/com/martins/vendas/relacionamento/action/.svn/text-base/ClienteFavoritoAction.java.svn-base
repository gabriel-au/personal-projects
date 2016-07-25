package br.com.martins.vendas.relacionamento.action;

import org.json.JSONArray;

import br.com.martins.vendas.services.ClienteService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;

public class ClienteFavoritoAction {

	private static final String TAG = ClienteFavoritoAction.class.getName();

	public ActionResult execute(JSONArray args) throws Exception {
		try {
			boolean sucesso = ClienteService.atualizarFavorito(args.getInt(0), args.getString(1));

			if (sucesso) {
				return new ActionResult(Status.OK);
			} else {
				return new ActionResult(Status.ERROR, "Não foi possível atualizar favorito.");
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return new ActionResult(Status.ERROR, e.getMessage());
		}

	}

}