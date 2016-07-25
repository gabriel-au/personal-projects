package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.MercadoriaService;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;

public class ValidarCondicaoPagamentoAction {

	public ActionResult execute(JSONArray array) throws Exception {
		
		int index = 0;
		
		int rowIndex 		 		= array.getInt(index++);
		int codigoMercadoria 		= array.getInt(index++);
		int condicaoPagamentoAntiga = array.getInt(index++);
		int condicaoPagamentoNova 	=  array.getInt(index++);
				
		boolean result = CondicaoPagamentoService.verificaSeCondicaoDePagamentoValida(
				condicaoPagamentoNova,
				MercadoriaService.buscaMercadoria(codigoMercadoria)
			);
		
		JSONObject jsonCondPag = new JSONObject();
		
		jsonCondPag.put("rowIndex", rowIndex);
		jsonCondPag.put("codigoMercadoria", codigoMercadoria);
		jsonCondPag.put("condicaoPagamento", result ? condicaoPagamentoNova : condicaoPagamentoAntiga);
		
		return new ActionResult(Status.OK, jsonCondPag);
	}

}
