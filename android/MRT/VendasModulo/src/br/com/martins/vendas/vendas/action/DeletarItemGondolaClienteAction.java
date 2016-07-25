package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;

import br.com.martins.vendas.services.GondolaService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;

public class DeletarItemGondolaClienteAction {

	private static final String TAG = DeletarItemGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) throws Exception {
		try {
			Integer codigoCliente = args.getInt(0);
			boolean retorno = GondolaService.deletarItemGondola(codigoCliente, args.getInt(1));

			if (retorno) {
				return new ActionResult(Status.OK);
			} else {
				return new ActionResult(Status.ERROR, "Nao foi possivel deletar item da gôndola.");
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			return new ActionResult(Status.ERROR, e.getMessage());
		}
	}

}