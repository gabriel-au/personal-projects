
package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.enums.DadoTemporario;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.CaminhaoService;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.PromocaoService;
import br.com.martins.vendas.services.RegraDistribuicaoService;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.services.desconto.DescontoService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.RegraDistribuicao;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;
import com.brq.mobile.framework.util.Util;

public class CalculaPrecoAction {

	private static final String	TAG	= CalculaPrecoAction.class.getName();

	public ActionResult execute(JSONArray args) throws Exception {
		ActionResult result = new ActionResult(Status.ERROR);
		
		Date tempo = new Date();
		long tempoInicio = tempo.getTime();
		System.out.println("Action - Inicio metodo calcula preco: " + tempoInicio);
		try {
			
			JSONObject jsonControl  = args.getJSONObject(0);
			String origem           = jsonControl.getString("origem");
			boolean incluirNoPedido = jsonControl.getBoolean("incluirNoPedido");
			
			JSONObject jsonData       = args.getJSONObject(1);
			int codigoMercadoria      = jsonData.getInt("codigoMercadoria");
			int codCondicaoPagamento  = jsonData.getInt("codigoCondicaoPagamento");
			int codCliente            = jsonData.getInt("codigoCliente");
			
			Item item = null;
			if ("ITEMDISPONIVEL".equals(origem)) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria);
			} else if ("ITEMSIMILAR".equals(origem)) {
				item = ItemPedidoService.obtemItemTemporario(codigoMercadoria, DadoTemporario.TMPMESMR);
			} else if ("ITEMPROMOCAO".equals(origem)) {
				item = PromocaoService.obtemItemPromocaoTemporario(codigoMercadoria);
			} else if ("ITEMPROMOCAOPREMIO".equals(origem)) {
				item = PromocaoService.obtemItemPromocaoPremioTemporario(codigoMercadoria);
			}else if ("CAMINHAO".equals(origem)) {
				item = CaminhaoService.obtemItemPedido(codigoMercadoria);
			}
			
			//guarda valores antigos para passar como parametros a alteração de caminhão
			item.codigoFilialExpedicaoAux=item.codigoFilialExpedicao;
			item.codigoFilialFaturamentoAux=item.codigoFilialFaturamento;
			item.notaFiscalAux=item.notaFiscal;
			item.tipoNegociacaoMercadoriaAux=item.tipoNegociacaoMercadoria;
			
			item.condicaoPagamento.codigoCondicaoPagamento = codCondicaoPagamento;
			item.codigoFilialExpedicao   = jsonData.getInt("codigoFilialExpedicao");
			item.codigoFilialFaturamento = jsonData.getInt("codigoFilialFaturamento");
			item.siglaEstadoOrigem   	 = jsonData.getString("siglaEstadoOrigem");
			item.siglaEstadoDestino 	 = jsonData.getString("siglaEstadoDestino");

			Cliente cliente = ClienteService.obterDetalheCliente(codCliente);
			cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(
							item.codigoFilialExpedicao,
							item.codigoFilialFaturamento,
							cliente.codigoCidadePreco, 
							cliente.codigoTerritorio);

			JSONObject jsonRepresentante = jsonData.getJSONObject("representante");
			Representante representante = new Representante();
			representante.codigoRepresentante = jsonRepresentante.getString("codigoRepresentante");
			representante.naturezaRepresentante = jsonRepresentante.getString("naturezaRepresentante");

			
			RegraDistribuicao regraDistribuicao = null;
			if (RegraDistribuicaoService.CODIGO_REGRA_DISTRIBUICAO_DEFAULT.equals(jsonData.getInt("codigoModeloDistribuicao"))) {
				
				regraDistribuicao = RegraDistribuicaoService.obtemRegraDistribuicaoDefault();
				
			} else {
				
				regraDistribuicao = RegraDistribuicaoService.obtemRegraDistribuicao(
						cliente.codigoCliente,
						item.codigoFilialExpedicao,
						item.codigoFilialFaturamento);
			}
			item.regraDistribuicao = regraDistribuicao;

			int quantidadeEnviada = jsonData.getInt("quantidadeMercadoria");
			item.tipoVendaPedido = "normal".equalsIgnoreCase(jsonData.getString("tipoPedido")) 
					? TipoVendaPedido.NORMAL.getValue() : TipoVendaPedido.SIMPLIFICADA.getValue();
					
			item.quantidadeMercadoria = quantidadeEnviada;
			item.tipoNegociacaoMercadoria = jsonData.getInt("codigoTipoNegociacao");
			item.notaFiscal = jsonData.getInt("notaFiscal");
			item.valorDescontoBeneficio = Util.getBigDecimal(jsonData.get("descontoBeneficio").toString());
			item.percentualAcrescimoConcedido = Util.getBigDecimal(jsonData.get("percentualAcrescimo").toString());
			item.percentualDescontoConcedido = Util.getBigDecimal(jsonData.get("percentualDesconto").toString());
			
			
			item = CalculoService.calculaPreco(item, cliente);
			
			//Se a compra de caixa(s) os valores liquido e unitario recebem o valor de caixa
//			if(item.mercadoria.quantidadeCaixaFornecedor.compareTo(1)!=0 
//					&& (item.quantidadeMercadoria % item.mercadoria.quantidadeCaixaFornecedor==0)){
//				item.valorLiquidoComImposto=item.valorCaixaComImposto;
//				item.valorUnitarioComImposto=item.valorCaixaComImposto;
//			}

			/*
			 * Quando a quantidade enviada é 0 é necessário para calculo de preço mudar esse valor para 1,
			 * sendo assim, após o calculo é necessário corrigir esse número novamente para 0
			 */
			if (quantidadeEnviada == 0) { item.quantidadeMercadoria = 0; }
			
			if ("ITEMPROMOCAO".equals(origem)) {
				PromocaoService.atualizaItemPromocaoTemporario(item, incluirNoPedido);
			} else if ("ITEMPROMOCAOPREMIO".equals(origem)) {
				PromocaoService.atualizaItemPremioTemporario(item, incluirNoPedido);
			} else if ("CAMINHAO".equals(origem)) {
				CaminhaoService.atualizaCaminhao(item);
			}else {
				ItemPedidoService.atualizaItemTemporario(item, incluirNoPedido);
			}
			
			JSONObject json = new JSONObject();
			json.put("control" , jsonControl);
			json.put("data"    , jsonData);
			json.put("item"	   , JsonUtil.toJson(item));
			
			result = new ActionResult(Status.OK, json);

 		} catch (IntegrationException e) {
 			Log.e(TAG, e.getLocalizedMessage(), e);
 			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getLocalizedMessage());
			
			return new ActionResult(Status.ERROR, jsonObject);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}
		
		Long tempoFim = new Date().getTime();
		
		System.out.println("Action CalculaPreco - Tempo Gasto: " + TimeUnit.MILLISECONDS.toSeconds((tempoFim - tempoInicio)) + "segs. - Milisegundos: " +  TimeUnit.MILLISECONDS.toMillis((tempoFim - tempoInicio)));
		
		return result;
	}

	
	/**
	 * Aplica desconto.
	 *
	 * @param args the args
	 * @return the action result
	 * @throws Exception the exception
	 */
	public ActionResult aplicaDesconto(JSONArray args) throws Exception {
		
		JSONObject jsonResult   = new JSONObject();
		JSONObject jsonControl  = args.getJSONObject(0);
		JSONObject jsonData     = args.getJSONObject(1);
		
		int codigoMercadoria            = jsonData.getInt("codigoMercadoria");
		BigDecimal percentualConcedido  = Util.getBigDecimal(jsonData.get("percentualConcedido").toString());
		int quantidadeMercadoriaSimilar = jsonData.getInt("quantidadeMercadoria");
		boolean incluirNoPedido		    = jsonData.getBoolean("incluirNoPedido");
		
		try{
			Item item = ItemPedidoService.obtemItemTemporario(codigoMercadoria);

			if (percentualConcedido.doubleValue() < 0) {
				
				item = DescontoService.aplicaDescontoPreco(item.preco, item, percentualConcedido.negate(), quantidadeMercadoriaSimilar);			
				item.percentualAcrescimoConcedido = BigDecimal.ZERO;
				item.percentualDescontoFlex = percentualConcedido.negate();
				
			} else if (percentualConcedido.doubleValue() > 0) {
				
				item = DescontoService.aplicaAcrecismoPreco(item, percentualConcedido);
				
				item.percentualAcrescimoConcedido = percentualConcedido;
				item.percentualDescontoFlex = BigDecimal.ZERO;
			}

			jsonResult.put("control", jsonControl);
			jsonResult.put("data"   , jsonData);
			jsonResult.put("item"   , JsonUtil.toJson(item));
			
			ItemPedidoService.atualizaItemTemporario(item, incluirNoPedido);
			
			return new ActionResult(Status.OK, jsonResult);
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			jsonResult.put("msg", e.getLocalizedMessage());
			return new ActionResult(Status.ERROR, jsonResult);
		}
	}
}