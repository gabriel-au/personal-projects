package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;

import br.com.martins.vendas.services.MailService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class EnviarRelatorioEmailGondolaAction {

	public ActionResult execute(JSONArray array) throws Exception {
		Integer codigoCliente 			= array.getInt(0);
		List<String> email 				= JsonUtil.jsonArraytoStringList(array.getJSONArray(1));
		boolean estoqueAnterior 		= (0 == array.getInt(2));
		boolean estoqueAtual			= (0 == array.getInt(3));
		boolean valorFreteMartins		= (0 == array.getInt(4));
		boolean precoLiqImpostoFrete	= (0 == array.getInt(5));
		boolean filialExpedicao			= (0 == array.getInt(6));
		boolean filialFaturamento		= (0 == array.getInt(7));
		boolean percentualICMS			= (0 == array.getInt(8));
		
		boolean sucesso = MailService.enviarEmailRelatorioGondola(codigoCliente, email.toArray(new String[]{}),  estoqueAnterior, estoqueAtual, valorFreteMartins, precoLiqImpostoFrete, filialExpedicao, filialFaturamento, percentualICMS);
			
		return new ActionResult(sucesso ? Status.OK : Status.ERROR);
	}

}
