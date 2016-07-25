package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.enums.DadoTemporario;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.ItensDisponiveisService;
import br.com.martins.vendas.services.PromocaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.DetalhePEE;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class RecuperaDetalhePEEAction {

	private static final String TAG = RecuperaDetalhePEEAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			JSONObject jsonControl      = array.getJSONObject(0);
			JSONObject jsonData         = array.getJSONObject(1);
			
			String origem               = jsonControl.getString("origem");
			int codigoMercadoria        = jsonData.getInt("codigoMercadoria");
			
			Item item = null;
			if ("ITEMDISPONIVEL".equalsIgnoreCase(origem)) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria);
			} else if ("ITEMSIMILAR".equalsIgnoreCase(origem)) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria, DadoTemporario.TMPMESMR);
			} else if ("ITEMPROMOCAO".equalsIgnoreCase(origem)) {
				item = PromocaoService.obtemItemPromocaoTemporario(codigoMercadoria);
			} else if ("ITEMPROMOCAOPREMIO".equalsIgnoreCase(origem)) {
				item = PromocaoService.obtemItemPromocaoPremioTemporario(codigoMercadoria);
			}
			
			Cliente cliente = ClienteService.obterDetalheCliente(jsonData.getInt("codigoCliente"));
			cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(item.codigoFilialExpedicao, item.codigoFilialFaturamento, cliente.codigoCidadePreco, cliente.codigoTerritorio);
			
			List<DetalhePEE> detalhePEE = ItensDisponiveisService.obtemDetalhePEE(item, cliente);
			
			JSONObject json = new JSONObject();
			json.put("control"   , jsonControl);
			json.put("data"      , jsonData);
			json.put("detalhePEE", JsonUtil.toJsonArray(detalhePEE));
			
			result = new ActionResult(Status.OK, json);

		} catch (Exception e) {
			
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());

			result = new ActionResult(Status.ERROR, jsonObject);
		}
		
		return result;
	}
	
}