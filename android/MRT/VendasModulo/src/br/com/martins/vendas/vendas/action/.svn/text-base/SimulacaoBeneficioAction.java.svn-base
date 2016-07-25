package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.SimulacaoBeneficioService;
import br.com.martins.vendas.vo.SimulacaoBeneficio;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class SimulacaoBeneficioAction {

	private static final String TAG = ItensVendidosSemanaAction.class.getName();
	
	public ActionResult execute(JSONArray array) throws Exception {
		SimulacaoBeneficio simulacaoBeneficio;
		try {
			simulacaoBeneficio = SimulacaoBeneficioService.criaObjetoSimulacaoBeneficio(array);
			if(!simulacaoBeneficio.continuacaoSimulacaoPreco){
				SimulacaoBeneficioService.calculaSimulacao(simulacaoBeneficio);
			}else{
				SimulacaoBeneficioService.continuaSimulacao(simulacaoBeneficio);
			}
			JSONObject jsonSimulacaoBeneficio = new JSONObject();
			jsonSimulacaoBeneficio.put("simulacaoBeneficio", JsonUtil.toJson(simulacaoBeneficio));
			return new ActionResult(Status.OK, jsonSimulacaoBeneficio);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			Log.e("CalculoPreco","Erro ao calcular preco", e);
			
			throw e;
		}
	}

}
