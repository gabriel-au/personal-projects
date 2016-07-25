package br.com.martins.vendas.martins;

import org.json.JSONArray;

import br.com.martins.vendas.martins.action.InicialAction;
import br.com.martins.vendas.martins.action.ListarRcaAction;
import br.com.martins.vendas.martins.action.ListarTerritorioRcaAction;
import br.com.martins.vendas.martins.action.VerificarGmRca;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.core.DispatcherBase;
import com.brq.mobile.framework.log.Log;

public class Dispatcher extends DispatcherBase {

	private static final String TAG = Dispatcher.class.getName();

	public ActionResult execute(String action, JSONArray array, String arg2) {
		try {

			if (action != null) {
				if ("inicial".equalsIgnoreCase(action)) {
					return new InicialAction().execute(array);
				} else if ("verificarGmRca".equalsIgnoreCase(action)) {
					return new VerificarGmRca().execute();
				} else if ("listarRca".equalsIgnoreCase(action)) {
					return new ListarRcaAction().execute(array);
				} else if ("listarTerritorios".equalsIgnoreCase(action)) {
					return new ListarTerritorioRcaAction().execute(array);
				}
			}
			return new ActionResult(Status.INVALID_ACTION);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return new ActionResult(Status.JSON_EXCEPTION, e.getMessage());
		}
	}
}