
package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.enums.DadoTemporario;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.PromocaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Mensagem;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class AlterarQuantidadeAction {

	private static final String	TAG	= AlterarQuantidadeAction.class.getName();

	public ActionResult execute(JSONArray args) {
		try {
			
			JSONObject jsonControl = args.getJSONObject(0);
			String origem          = jsonControl.getString("origem");
			
			JSONObject jsonData    = args.getJSONObject(1);
			int codigoMercadoria   = jsonData.getInt("codigoMercadoria");
			
			Item item = null;
			if ("ITEMDISPONIVEL".equals(origem) || "CAMINHAO".equalsIgnoreCase(origem)) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria);
			} else if ("ITEMSIMILAR".equals(origem)) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria, DadoTemporario.TMPMESMR);
			} else if ("ITEMPROMOCAO".equals(origem)) {
				item = PromocaoService.obtemItemPromocaoTemporario(codigoMercadoria);
			} else if ("ITEMPROMOCAOPREMIO".equals(origem)) {
				item = PromocaoService.obtemItemPromocaoPremioTemporario(codigoMercadoria);
			}
	
			Cliente cliente								= ClienteService.obterDetalheCliente(jsonData.getInt("codigoCliente"));
			
			Integer 			codigoFilialExpedicao 	= jsonData.getInt("codigoFilialExpedicao");
			Integer 			codigoFilialFaturamento = jsonData.getInt("codigoFilialFaturamento");
			item.codigoFilialExpedicao   = codigoFilialExpedicao;
			item.codigoFilialFaturamento = codigoFilialFaturamento;
				
			// CondicaoPagamento   condicaoPagamento 	= CondicaoPagamentoService.buscaCondicaoPagamento(jsonData.getInt("codigoCondicaoPagamento"));
			
			Integer quantidadeAnterior					= jsonData.getInt("quantidadeAnterior");
			
			Integer quantidade							= jsonData.getInt("quantidadeAtual");
			
			Mensagem mensagem						 	= ItemPedidoService.verificarQuantidade(item, cliente, quantidadeAnterior, quantidade);
			
			JSONObject json = new JSONObject();
			json.put("control" , jsonControl);
			json.put("data"    , jsonData);
			json.put("mensagem", JsonUtil.toJson(mensagem));
			
			return new ActionResult(Status.OK, json);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			return new ActionResult(Status.ERROR, e.getLocalizedMessage());
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return new ActionResult(Status.ERROR, e.getLocalizedMessage());
		}
	}
}