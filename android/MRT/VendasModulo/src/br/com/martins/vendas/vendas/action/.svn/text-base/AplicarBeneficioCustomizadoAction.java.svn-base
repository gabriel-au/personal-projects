package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.BeneficiosService;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.ConfirmacaoMensagemAplicaBeneficio;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class AplicarBeneficioCustomizadoAction {

	private static final String TAG = AplicarBeneficioCustomizadoAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result;
		Integer origem, prazoBeneficio, codigoFilialExpedicao, codigoCliente, codigoTerritorioVenda, codigoFilialFaturamento;
		Double descontoBeneficio;
		boolean reajustarPreco;
		List<ConfirmacaoMensagemAplicaBeneficio> listaConfirmacaoMensagemAplicaBeneficio;
		Cliente cliente;
		JSONObject jsonAplicaBeneficio;
		
		try {
			origem = args.getInt(0);
			prazoBeneficio = args.getInt(1);
			descontoBeneficio = args.getDouble(2);
			codigoFilialExpedicao = args.getInt(3);
			codigoFilialFaturamento = args.getInt(4);
			codigoCliente = args.getInt(5);
			codigoTerritorioVenda = args.getInt(6);
			reajustarPreco = args.getBoolean(7);
			jsonAplicaBeneficio = new JSONObject();
			
			cliente = ClienteService.obterDetalheCliente(codigoCliente, codigoTerritorioVenda);
			cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(codigoFilialExpedicao, codigoFilialFaturamento, cliente.codigoCidadePreco, codigoTerritorioVenda);
			
			if (!reajustarPreco) {
				listaConfirmacaoMensagemAplicaBeneficio = BeneficiosService.aplicaBeneficioCliente(origem, codigoFilialExpedicao, BigDecimal.valueOf(descontoBeneficio), prazoBeneficio, cliente, codigoTerritorioVenda);
				if (!listaConfirmacaoMensagemAplicaBeneficio.isEmpty()) {
					jsonAplicaBeneficio.put("confirmacaoMensagensAplicaBeneficio", JsonUtil.toJsonArray(listaConfirmacaoMensagemAplicaBeneficio));	
				}
			} else {
				BeneficiosService.reajustaPrecoBeneficioCliente(codigoFilialExpedicao, BigDecimal.valueOf(descontoBeneficio), prazoBeneficio, cliente, codigoTerritorioVenda);
			}
			
			result = new ActionResult(Status.OK, jsonAplicaBeneficio);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			result = new ActionResult(Status.ERROR, e.getMessage());
		}
		
		return result;
	}

}