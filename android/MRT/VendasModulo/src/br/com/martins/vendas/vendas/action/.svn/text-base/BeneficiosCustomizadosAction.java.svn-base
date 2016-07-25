package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;

import br.com.martins.vendas.services.BeneficiosService;
import br.com.martins.vendas.vo.Beneficio;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class BeneficiosCustomizadosAction {

	private static final String TAG = BeneficiosCustomizadosAction.class.getName();

	public ActionResult execute(JSONArray args) {		
		ActionResult result = new ActionResult(Status.ERROR);
		Integer codigoCliente, codigoTerritorio, scoreCliente, tipoVenda;
		
		try {
			codigoCliente = args.getInt(0);
			codigoTerritorio = args.getInt(1);
			scoreCliente = args.getInt(2);
			tipoVenda = args.getInt(3);
			//o parametro indicaDescontoSimplificado será sempre falso, conforme ObtemBeneficiosValidos(LstBnfVal &lstBnf, BOOL bIndDscSmp)
			//da classe DlgCustBnf
			//o parametro tipoVenda é referente ao campo TipVndPed da tabela de pedidos(PCAPED)
			List<Beneficio> beneficiosDisponiveis = BeneficiosService.buscaBeneficiosDisponiveis(false, codigoCliente, scoreCliente, codigoTerritorio, tipoVenda);
			result = new ActionResult(Status.OK, JsonUtil.toJson("beneficiosDisponiveis", beneficiosDisponiveis));
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

}