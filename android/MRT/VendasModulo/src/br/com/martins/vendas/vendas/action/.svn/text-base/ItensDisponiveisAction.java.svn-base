package br.com.martins.vendas.vendas.action;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.enums.DadoTemporario;
import br.com.martins.vendas.services.CaminhaoService;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.ItensDisponiveisServiceAux;
import br.com.martins.vendas.services.PromocaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.ItemFiltro;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;
import com.brq.mobile.framework.util.Util;

public class ItensDisponiveisAction {

	private static final String TAG = ItensDisponiveisAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		try {
			long tempoInicio = new Date().getTime();
			List<Item> itens = null;

			ItemFiltro itemFiltro = new ItemFiltro();
			
			JSONObject json = array.getJSONObject(0);
			JSONObject jsonDadosPadrao = json.getJSONObject("dadosPadrao");
			JSONObject jsonDadosFiltro = null;
			
			if (!json.isNull("dadosFiltro")) {
				jsonDadosFiltro = json.getJSONObject("dadosFiltro");	
			}		

			boolean recuperaPesqAnterior = jsonDadosPadrao.getBoolean("pesquisaAnterior");
			if (recuperaPesqAnterior) {
				
				itens = ItemPedidoService.obtemTodosItensTemporarios();
			
			} else {
				
				itemFiltro.comportamentoCompras = jsonDadosPadrao.getBoolean("primeiraPesquisa");
				itemFiltro.numPagina			= jsonDadosPadrao.getInt("numPagina");
				itemFiltro.isListaMercadoria    = true;				
				
				int codFilialExpedicao 	 = jsonDadosPadrao.getInt("codigoFilialExpedicao");
				int codFilialFaturamento = jsonDadosPadrao.getInt("codigoFilialFaturamento");
				
				JSONObject jsonRepresentante = jsonDadosPadrao.getJSONObject("representante");
				int codRepresentante         = jsonRepresentante.getInt("codigoRepresentante");
				
				JSONObject jsonCondPagamento = jsonDadosPadrao.getJSONObject("condicaoPagamento");
				int codigoCondicaoPagamento  = jsonCondPagamento.getInt("codigo");
				
				JSONObject jsonCliente	        = jsonDadosPadrao.getJSONObject("cliente");
				int codigoCliente			 	= jsonCliente.getInt("codigoCliente");
				int codRelacionamentoCliente 	= jsonCliente.getInt("numeroRelacionamentoCliente");
				int codigoGrupamentoCliente  	= jsonCliente.getInt("codigoGrupoCliente");
				String flagAlvaraPsicotropico	= jsonCliente.getString("flagAlvaraPsicotropico");
				int codigoCidade 				= jsonCliente.getInt("codigoCidade");
				int codigoAtividade 			= jsonCliente.getInt("codigoAtividade");
				int codigoSupervisor 			= jsonCliente.getInt("codigoSupervisor");
				String codigoEstadoDestino 		= jsonCliente.getString("uf");
				////////////
				// FILTRO //
				////////////
				if (jsonDadosFiltro != null && jsonDadosFiltro.length() > 0) {			
					itemFiltro.preencheCodigoDescricao(jsonDadosFiltro.getString("valor"));
					itemFiltro.mex 			 = jsonDadosFiltro.getBoolean("mex");
					itemFiltro.meio			 = jsonDadosFiltro.getBoolean("meio");
					itemFiltro.buscaAvancada = jsonDadosFiltro.getBoolean("buscaAvancada");
					
					if (itemFiltro.buscaAvancada) {
						itemFiltro.codigoGrupo        = Util.getInteger(jsonDadosFiltro.getString("codigoGrupo"), null);
						itemFiltro.codigoCategoria    = Util.getInteger(jsonDadosFiltro.getString("codigoCategoria"), null);
						itemFiltro.codigoSubCategoria = Util.getInteger(jsonDadosFiltro.getString("codigoSubCategoria"), null);
						itemFiltro.codigoFornecedor   = Util.getInteger(jsonDadosFiltro.getString("codigoFornecedor"), null);
					}

					
				}

			
				itens = ItensDisponiveisServiceAux.listaItensDisponiveis(
						codigoCliente,
						codFilialExpedicao,
						codFilialFaturamento,
						codRelacionamentoCliente,
						codigoGrupamentoCliente,
						flagAlvaraPsicotropico,
						codRepresentante,
						codigoCidade,
						codigoAtividade,
						codigoSupervisor,
						codigoEstadoDestino,
						codigoCondicaoPagamento, 
						itemFiltro);
			}
			
			json.put("tamanhoPagina", Constants.TAMANHO_PAGINAO_CONSULTA);
			json.put("itemFiltro"   , JsonUtil.toJson(itemFiltro));
			json.put("itens"        , JsonUtil.toJsonArray(itens));
			
			long tempoFim = new Date().getTime();
			System.out.println("Action - Item Disponivel Tempo Gasto: " + TimeUnit.MILLISECONDS.toSeconds((tempoFim - tempoInicio)) + " segs. - Milisegundos: " +  TimeUnit.MILLISECONDS.toMillis((tempoFim - tempoInicio)));
			
			
			return new ActionResult(Status.OK, json);

		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());
			
			return new ActionResult(Status.ERROR, jsonObject);
			
		}
	}
	
	public ActionResult obtemItem(JSONArray array) throws Exception {
		try {
			JSONObject jsonControl  = array.getJSONObject(0);
			JSONObject jsonData     = array.getJSONObject(1);
			Item item = new Item();
			
			int codigoMercadoria    = jsonData.getInt("codigoMercadoria");
			
			
			if (("ITEMPROMOCAO").equalsIgnoreCase(jsonControl.getString("origem"))) {
				item = PromocaoService.obtemItemPromocaoTemporario(codigoMercadoria);
			} else if (("ITEMPROMOCAOPREMIO").equalsIgnoreCase(jsonControl.getString("origem"))) {
				item = PromocaoService.obtemItemPromocaoPremioTemporario(codigoMercadoria);
			} else if (("ITEMSIMILAR").equalsIgnoreCase(jsonControl.getString("origem"))) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria, DadoTemporario.TMPMESMR);
			} else if (("ITEMDISPONIVEL").equalsIgnoreCase(jsonControl.getString("origem"))) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria);
			}else if (("CAMINHAO").equalsIgnoreCase(jsonControl.getString("origem"))) {
				item = CaminhaoService.obtemItemPedido(codigoMercadoria);
			}
			
			JSONObject jsonResult   = new JSONObject();
			jsonResult.put("control", jsonControl);
			jsonResult.put("data"   , jsonData);
			jsonResult.put("item"   , JsonUtil.toJson(item));
			return new ActionResult(Status.OK, jsonResult);
	
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());
			
			return new ActionResult(Status.ERROR, jsonObject);
			
		}
	}

}
