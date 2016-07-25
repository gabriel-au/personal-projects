package br.com.martins.vendas.estrutura;

import org.json.JSONArray;

import br.com.martins.vendas.estrutura.action.MenuAction;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.core.DispatcherBase;

public class Dispatcher extends DispatcherBase {

	public ActionResult execute(String action, JSONArray array, String arg2) {
		if (action == null) {
			return new ActionResult(Status.INVALID_ACTION);
		}

		if ("montaMenu".equalsIgnoreCase(action)) {
			try {
				return new MenuAction().execute();
			} catch (Exception e) {
				return new ActionResult(Status.JSON_EXCEPTION, e.getMessage());
			}
		}

		return new ActionResult(Status.INVALID_ACTION);
	}

}