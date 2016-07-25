package br.com.martins.vendas.vendas.action;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.ItensSimilaresService;
import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.Mercadoria;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ItensSimilaresAction {

	private static final String TAG = ItensSimilaresAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		try {
			
			long tempoInicio = new Date().getTime();
			List<Item> itens = null;
			
			JSONObject json  = array.getJSONObject(0);
			/*
			 * Dados Filtro
			 */
			JSONObject jsonDadosFiltro = json.getJSONObject("dadosFiltro");

			ItemFiltro itemFiltro = new ItemFiltro();
			Mercadoria mercadoria = MercadoriaService.buscaMercadoria(jsonDadosFiltro.getInt("codigoMercadoria"));
			
			itemFiltro.codigoMercadoriaPrincipal  = mercadoria.codigoMercadoriaPrincipal;
			itemFiltro.numPagina  				  = jsonDadosFiltro.getInt("numPagina");
			itemFiltro.isListaMercadoriaSimilar   = true;
			/*
			 * Dados Padrão
			 */
			JSONObject jsonDadosPadrao      = json.getJSONObject("dadosPadrao");
			int codFilialExpedicao          = jsonDadosPadrao.getInt("codigoFilialExpedicao");
			int codFilialFaturamento        = jsonDadosPadrao.getInt("codigoFilialFaturamento");
			
			JSONObject jsonCondPagamento    = jsonDadosPadrao.getJSONObject("condicaoPagamento");
			CondicaoPagamento condPagamento = CondicaoPagamentoService.buscaCondicaoPagamento(jsonCondPagamento.getInt("codigo"));
			
			JSONObject jsonRepresentante  = jsonDadosPadrao.getJSONObject("representante");
			int codigoRepresentante       = jsonRepresentante.getInt("codigoRepresentante");
			
			JSONObject jsonCliente        = jsonDadosPadrao.getJSONObject("cliente");
			int codigoCliente             = jsonCliente.getInt("codigoCliente");
			int codigoGrupoCliente        = jsonCliente.getInt("codigoGrupoCliente");
			int codRelacionamentoCliente  = jsonCliente.getInt("numeroRelacionamentoCliente");
			String flagAlvaraPsicotropico = jsonCliente.getString("flagAlvaraPsicotropico");
			
			itens = ItensSimilaresService.listaItens(
					codFilialExpedicao, 
					codFilialFaturamento,
					codigoCliente,
					codRelacionamentoCliente,
					codigoGrupoCliente,
					flagAlvaraPsicotropico,
					codigoRepresentante,
					condPagamento,
					itemFiltro);
			
			json.put("mercadoria"   , JsonUtil.toJson(mercadoria));
			
			json.put("tamanhoPagina", Constants.TAMANHO_PAGINAO_CONSULTA);
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

}
