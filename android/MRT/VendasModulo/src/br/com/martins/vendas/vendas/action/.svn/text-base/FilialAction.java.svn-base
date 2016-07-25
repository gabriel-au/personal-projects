package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.FilialService;
import br.com.martins.vendas.services.RelacaoGiroService;
import br.com.martins.vendas.vo.FilialCliente;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class FilialAction {

	public ActionResult execute(JSONArray array) throws JSONException {
		Integer clientId   =  array.getInt(0);
		Integer territoryId = array.getInt(1);
		
		List<FilialCliente> filiais = FilialService.obterFiliaisPorCliente(clientId, territoryId);
		for (FilialCliente filialCliente: filiais) {
			String[] estadoFilial = RelacaoGiroService.obtemEstadoFilial(territoryId, filialCliente.filialExpedicao.codigoFilial, filialCliente.filialFaturamento.codigoFilial);
			filialCliente.siglaEstadoOrigem  = estadoFilial[0];
			filialCliente.siglaEstadoDestino = estadoFilial[1];
		}
		
		return new ActionResult(Status.OK, JsonUtil.toJson("filiais", filiais));
	}

}