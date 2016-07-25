package br.com.martins.vendas.vendas.action;

import java.text.ParseException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.PromocaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.GrupoMercadoria;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.Mensagem;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.UnidadeNegocio;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;
import com.brq.mobile.framework.util.Util;

public class PromocaoAction {

	private static final String TAG = PromocaoAction.class.getName();

	/**
	 * @param array
	 * @return
	 */
	public ActionResult listarPromocoes(JSONArray array) {
		ActionResult result = new ActionResult(Status.OK);

		try {
			Integer codigoCliente = array.getInt(0);
			String codigoEstadoDestino = array.getString(1);
			Integer codigoFilialExpedicao = array.getInt(2);
			Integer codigoFilialFaturamento = array.getInt(3);
			Integer codigoUnidadeEstrategia = array.getInt(4);
			
			List<Promocao> promocoes = PromocaoService.listarPromocoesValidas(codigoFilialExpedicao, codigoUnidadeEstrategia, codigoCliente, codigoEstadoDestino, codigoFilialFaturamento);
			
			result = new ActionResult(Status.OK, JsonUtil.toJson("promocoes", promocoes));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		} catch (IntegrationException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}
	
	
	public ActionResult listarGrupoMercadoria() {
		ActionResult result = new ActionResult(Status.OK);

		try {
			List<GrupoMercadoria> listaGrupoMercadoria = PromocaoService.listarGrupoMercadoria();
			result = new ActionResult(Status.OK, JsonUtil.toJson("listaGrupoMercadoria", listaGrupoMercadoria));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}
	
	public ActionResult listarUnidadeNegocio() {
		ActionResult result = new ActionResult(Status.OK);
		
		try {
			List<UnidadeNegocio> listaUnidadeNegocio = PromocaoService.listarUnidadeEstrategica();
			result = new ActionResult(Status.OK, JsonUtil.toJson("listaUnidadeNegocio", listaUnidadeNegocio));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}
	
	public ActionResult listarItensDisponiveisDaPromocao(JSONArray array) throws Exception {
		try {
				
			List<Item> itens = null;
			
			JSONObject json = array.getJSONObject(0);
			
			JSONObject jsonDadosPadrao = json.getJSONObject("dadosPadrao");
			JSONObject jsonDadosFiltro = null;
			if (!json.isNull("dadosFiltro")) {
				jsonDadosFiltro = json.getJSONObject("dadosFiltro");	
			}
			
			boolean recuperaPesqAnterior = jsonDadosPadrao.getBoolean("pesquisaAnterior");
			if (recuperaPesqAnterior) {
				
				itens = PromocaoService.obtemItensPromocaoTemporarios();
			
			} else {
				
				ItemFiltro itemFiltro = new ItemFiltro();
				itemFiltro.comportamentoCompras = jsonDadosPadrao.getBoolean("primeiraPesquisa");
				itemFiltro.numPagina			= jsonDadosPadrao.getInt("numPagina");
				
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
				int codigoPromocao 			    = Util.getInteger(jsonDadosPadrao.getString("codigoPromocao"));
				
				////////////
				// FILTRO //
				////////////
				if (jsonDadosFiltro != null) {			
					//itemFiltro.preencheCodigoDescricao(jsonDadosFiltro.getString("valor"));
					//itemFiltro.mex = jsonDadosFiltro.getBoolean("mex");
					int codigoGrupo = jsonDadosFiltro.getInt("codigoGrupoMercadoria");
					itemFiltro.codigoGrupo        = codigoGrupo != 0 ? codigoGrupo : null;
				//	itemFiltro.codigoCategoria    = Util.getInteger(jsonDadosFiltro.getString("codigoCategoria"), null);
					//itemFiltro.codigoSubCategoria = Util.getInteger(jsonDadosFiltro.getString("codigoSubCategoria"), null);
				}
		
					 itens = PromocaoService.listaItensDisponiveisDaPromocao(
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
								false, 
								null,
								null,
								null,
								itemFiltro,
								codigoPromocao);
				}			
			
			JSONObject jsonObject = JsonUtil.toJson("listaItensPromocao", itens);
			
			return new ActionResult(Status.OK, jsonObject);

		} catch (Exception e) {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());
			
			return new ActionResult(Status.ERROR, jsonObject);
			
		}
	}
	
	public ActionResult listarPremiosDisponiveisDaPromocao(JSONArray array) throws Exception {
		try {
			List<Item> itens = null;
			
			JSONObject json = array.getJSONObject(0);
			
			JSONObject jsonDadosPadrao = json.getJSONObject("dadosPadrao");
			JSONObject jsonDadosFiltro = null;
			if (!json.isNull("dadosFiltro")) {
				jsonDadosFiltro = json.getJSONObject("dadosFiltro");	
			}
			
			boolean recuperaPesqAnterior = jsonDadosPadrao.getBoolean("pesquisaAnterior");
			if (recuperaPesqAnterior) {
				
				itens = PromocaoService.obtemItensPromocaoPremioTemporarios();
			
			} else {
				
				ItemFiltro itemFiltro = new ItemFiltro();
				itemFiltro.comportamentoCompras = jsonDadosPadrao.getBoolean("primeiraPesquisa");
				itemFiltro.numPagina			= jsonDadosPadrao.getInt("numPagina");
				
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
				int codigoPromocao 			    = jsonDadosPadrao.getInt("codigoPromocao");
				////////////
				// FILTRO //
				////////////
				if (jsonDadosFiltro != null) {			
					itemFiltro.preencheCodigoDescricao(jsonDadosFiltro.getString("valor"));
					itemFiltro.mex = jsonDadosFiltro.getBoolean("mex");
					
					itemFiltro.codigoGrupo        = Util.getInteger(jsonDadosFiltro.getString("codigoGrupo"), null);
					itemFiltro.codigoCategoria    = Util.getInteger(jsonDadosFiltro.getString("codigoCategoria"), null);
					itemFiltro.codigoSubCategoria = Util.getInteger(jsonDadosFiltro.getString("codigoSubCategoria"), null);
				}

			
				itens = PromocaoService.listarPremiosDisponiveisDaPromocao(
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
						false, 
						null,
						null,
						null,
						itemFiltro,
						codigoPromocao);
			}
			
			json.put("listaPremiosPromocao", JsonUtil.toJsonArray(itens));
			return new ActionResult(Status.OK, json);

		} catch (Exception e) {
			
			Log.e(TAG, e.getMessage(), e);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());
			
			return new ActionResult(Status.ERROR, jsonObject);	
		}
	}
	
	public ActionResult obterValorRestantePromocao (JSONArray args) {
		try {
			
			Mensagem mensagem = PromocaoService.obterValorRestantePromocao(args.getInt(0));
			
			JSONObject json = new JSONObject();
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
