package br.com.martins.vendas.estrutura.action;

import org.json.JSONObject;

import br.com.martins.vendas.services.MenuService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.log.Log;

public class MenuAction {

	private static final String NOME_ARQUIVO_MENU = "menu.xml";
	private static final String DIR_ARQUIVO_MENU = ConfigAccess.getConfig().diretoryBase() + "/menu/";
	private static final String TAG = MenuAction.class.getName();

	public ActionResult execute() throws Exception {
		JSONObject jsonMenu = null;
		try {
			jsonMenu = MenuService.carregaMenu(DIR_ARQUIVO_MENU + NOME_ARQUIVO_MENU);
		} catch (Exception e) {
			Log.e(TAG, "Menu.xml nao encontrado");
		}
		return new ActionResult(Status.OK, jsonMenu);
	}

}
