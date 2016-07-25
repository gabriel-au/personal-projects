package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.BeneficiosService;
import br.com.martins.vendas.vo.Beneficio;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class BeneficiosCustomizadosDetalheAction {

	private static final String TAG = BeneficiosCustomizadosDetalheAction.class.getName();

	public ActionResult execute(JSONArray args) {	
		ActionResult result;
		Beneficio beneficioSelecionado;
		Integer codigoCliente, codigoTerritorio, scoreCliente, numeroPedido, codigoBeneficio;
		
		try {
			numeroPedido = args.getInt(0);
			codigoBeneficio = args.getInt(1);
			codigoCliente = args.getInt(2);
			codigoTerritorio = args.getInt(3);
			scoreCliente = args.getInt(4);
			beneficioSelecionado = BeneficiosService.buscaBeneficio((int) codigoBeneficio);
			List<Beneficio> beneficiosCustomizados = BeneficiosService.montaDetalheBeneficiosCliente(beneficioSelecionado, (int)numeroPedido, codigoCliente, scoreCliente, codigoTerritorio);
			JSONObject jsonBeneficios = new JSONObject();
			jsonBeneficios.put("beneficiosCustomizados", JsonUtil.toJsonArray(beneficiosCustomizados));
			jsonBeneficios.put("beneficioAtual", JsonUtil.toJson(beneficioSelecionado));
			result = new ActionResult(Status.OK, jsonBeneficios);
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			result = new ActionResult(Status.ERROR, e.getMessage());
		}
		
		return result;
	}

}