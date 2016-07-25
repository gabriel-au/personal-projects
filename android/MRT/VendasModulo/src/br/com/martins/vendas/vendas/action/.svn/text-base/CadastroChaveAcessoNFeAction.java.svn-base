package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;

import br.com.martins.vendas.services.CadastroChaveAcessoNFeService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;

public class CadastroChaveAcessoNFeAction {

	private static final String TAG = CadastroChaveAcessoNFeAction.class.getName();

	public ActionResult execute(JSONArray args) throws Exception {
		String chaveAcessoNFe;
		
		try {
			chaveAcessoNFe = args.getString(0).replaceAll("-", "");
			String codigoRepresentante = args.getString(1);
			String tipoEntidade =  args.getString(2);
			
			StringBuilder mensagem = new StringBuilder();
			boolean sucesso = CadastroChaveAcessoNFeService.cadastrarChaveAcessoNFe(chaveAcessoNFe, mensagem, codigoRepresentante, tipoEntidade);
			return new ActionResult(sucesso ? Status.OK : Status.ERROR, mensagem.toString());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

}