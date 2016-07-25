package br.com.martins.vendas.services;

import java.math.BigDecimal;

import org.json.JSONArray;

import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.ItemPedidoTemporario;
import br.com.martins.vendas.vo.RelacaoGiro;
import br.com.martins.vendas.vo.SimulacaoBeneficio;
import br.com.martins.vendas.vo.ValorMinimoItem;

public class SimulacaoBeneficioService {

	/**
	 * 
	 * @param array
	 * @return
	 * @throws Exception
	 */
	public static SimulacaoBeneficio criaObjetoSimulacaoBeneficio(JSONArray array) throws Exception {
		SimulacaoBeneficio simulacaoBeneficio = new SimulacaoBeneficio();
		//valores da tela
		
		simulacaoBeneficio.codigoMercadoria 	   = !array.isNull(0) ? array.getInt(0) : null;
		simulacaoBeneficio.codigoCliente 		   = !array.isNull(1) ? array.getInt(1) : null;
		simulacaoBeneficio.codigoFilialExpedicao   = !array.isNull(2) ? array.getInt(2) : null;
		simulacaoBeneficio.codigoFilialFaturamento = !array.isNull(3) ? array.getInt(3) : null;
		simulacaoBeneficio.tipoVendaPedido 		   = !array.isNull(4) ? "normal".equalsIgnoreCase(array.getString(4)) ? TipoVendaPedido.NORMAL.getValue() : TipoVendaPedido.SIMPLIFICADA.getValue() : null;
		simulacaoBeneficio.codigoTerritorioVenda   = !array.isNull(5) ? array.getInt(5) : null;
		simulacaoBeneficio.tipoNegociacao          = !array.isNull(6) ? array.getInt(6) : null;
		simulacaoBeneficio.percentualSimulacaoBeneficio = !array.isNull(7) && !"".equals(array.getString(7))? BigDecimal.valueOf(Double.parseDouble(array.getString(7).replaceAll(",", "."))) : BigDecimal.ZERO;
		simulacaoBeneficio.continuacaoSimulacaoPreco    = !array.isNull(8) ? array.getBoolean(8) : null;

		//TODO: DEFINIR A ORIGEM DESTE INFORMACAO
		simulacaoBeneficio.isSimulacaoCaixa = false;
		
		//valores para calculos posteriores
		simulacaoBeneficio.percentualDescontoFlexivel   = !array.isNull(9) ? BigDecimal.valueOf(array.getDouble(9)) : null;
		simulacaoBeneficio.percentualDescontoItem       = !array.isNull(10) ? BigDecimal.valueOf(array.getDouble(10)) : null;
		simulacaoBeneficio.percentualAcrescimoConcedido = !array.isNull(11) ? BigDecimal.valueOf(array.getDouble(11)) : null;
		return simulacaoBeneficio;
	}
	
	/**
	 * Faz o calculo da simulacao do beneficio
	 * metodo CDlgPopupItem::OnCalculaVlrLiqSimulado() classe DlgPopupItem.cpp
	 * @param itemPedidoTemporario 
	 * @param percentualSimulacaoBeneficio 
	 * @param continuaSimulacaoPreco 
	 * @param mercadoria 
	 * @param tipoNegocicaoMercadoria 
	 * @param valorBrutoMercadoria 
	 * @param valorBrutoCaixa 
	 * @param valorBrutoFracionado 
	 * @throws Exception 
	 */
	public static void calculaSimulacao(SimulacaoBeneficio simulacaoBeneficio) throws Exception {
		Cliente cliente = ClienteService.obterDetalheCliente(simulacaoBeneficio.codigoCliente);
		cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(simulacaoBeneficio.codigoFilialExpedicao, simulacaoBeneficio.codigoFilialFaturamento, cliente.codigoCidadePreco, simulacaoBeneficio.codigoTerritorioVenda);
		ItemPedidoTemporario itemPedidoTemporario = ItemPedidoTemporarioService.buscaItemTemporario(MercadoriaService.obtemCodigoMercadoria(simulacaoBeneficio.codigoMercadoria), simulacaoBeneficio.codigoFilialExpedicao, simulacaoBeneficio.codigoFilialFaturamento);
		
		if (itemPedidoTemporario.quantidadeMercadoria <= 0){
			itemPedidoTemporario.quantidadeMercadoria = 1;
		}

		if (simulacaoBeneficio.percentualSimulacaoBeneficio.doubleValue() > 50){
			simulacaoBeneficio.percentualSimulacaoBeneficio = new BigDecimal(50);
		}
		
		ValorMinimoItem valorMinimoItem = ItemPedidoService.verificaValorMinimoItemBeneficio(simulacaoBeneficio.percentualSimulacaoBeneficio, false, false, itemPedidoTemporario, cliente);
		
		//guarda os valores para caso necessite continuar apos confirmacao do usuario
		simulacaoBeneficio.percentualDescontoFlexivel = valorMinimoItem.percentualDescontoFlexivel;
		simulacaoBeneficio.percentualAcrescimoConcedido = valorMinimoItem.percentualAcrescimoConcedido;
		simulacaoBeneficio.percentualDescontoItem = valorMinimoItem.percentualDescontoItem;
		
		if(valorMinimoItem.mensagemValorMinimoItem != null){
			simulacaoBeneficio.mensagemSimulacaoBeneficio = valorMinimoItem.mensagemValorMinimoItem.mensagem;
			simulacaoBeneficio.tipoMensagemSimulacaoBeneficio = valorMinimoItem.mensagemValorMinimoItem.tipoMensagem;
		}else{
			calculaSimulacaoBeneficio(simulacaoBeneficio, itemPedidoTemporario, cliente);	
		}
	}
	
	/**
	 * Continuacao do calculo 
	 * void CDlgPopupItem::OnCalculaVlrLiqSimulado()
	 * @param itemPedidoTemporario 
	 * @param itemPedidoTemporario 
	 * @param mercadoria 
	 * @param mercadoria
	 * @param cliente 
	 * @throws Exception
	 */
	private static void calculaSimulacaoBeneficio(SimulacaoBeneficio simulacaoBeneficio, ItemPedidoTemporario itemPedidoTemporario, Cliente cliente) throws Exception {
		Item item = SimulacaoBeneficioService.executaChamadaCalculoPreco(simulacaoBeneficio, itemPedidoTemporario, cliente);
		if(simulacaoBeneficio.isSimulacaoCaixa){
			simulacaoBeneficio.precoLiquidoUnitarioSimulado = item.valorUnitarioCaixaComImposto;
		}else{
			simulacaoBeneficio.precoLiquidoUnitarioSimulado = item.valorUnitarioComImposto;
		}
		simulacaoBeneficio.precoLiquidoSimulado = simulacaoBeneficio.precoLiquidoUnitarioSimulado.multiply(BigDecimal.valueOf(item.quantidadeMercadoria));
	}

	/**
	 * Continuacao do calculo da simulacao do beneficio apos confirmacao do usuario
	 * a partir da linha 4414 da classe clsIte.cpp método 
	 * BOOL clsIte::VerificaValorMinimoItemBeneficio(double dblDscBfcIte, double &dblPerDscAnt, double &dblPerDscIte, BOOL bFechamento, double &dblVlrLiqNvo, BOOL bReajusta)
	 * @param simulacaoBeneficio 
	 * @param itemPedidoTemporario 
	 * @throws Exception 
	 */
	public static void continuaSimulacao(SimulacaoBeneficio simulacaoBeneficio) throws Exception {
		ItemPedidoTemporario itemPedidoTemporario = ItemPedidoTemporarioService.buscaItemTemporario(MercadoriaService.obtemCodigoMercadoria(simulacaoBeneficio.codigoMercadoria), simulacaoBeneficio.codigoFilialExpedicao, simulacaoBeneficio.codigoFilialFaturamento);
		Cliente cliente = ClienteService.obterDetalheCliente(simulacaoBeneficio.codigoCliente);
		cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(simulacaoBeneficio.codigoFilialExpedicao, simulacaoBeneficio.codigoFilialFaturamento, cliente.codigoCidadePreco, simulacaoBeneficio.codigoTerritorioVenda);
		RelacaoGiro relacaoGiro = RelacaoGiroService.recuperarPorFilial(cliente.codigoTerritorio, itemPedidoTemporario.codigoFilialExpedicao, itemPedidoTemporario.codigoFilialFaturamento);
		
		if (simulacaoBeneficio.percentualDescontoItem.doubleValue() < 0.00){											
			if (simulacaoBeneficio.percentualDescontoFlexivel.doubleValue() > 0.00){
				simulacaoBeneficio.percentualDescontoFlexivel = BigDecimal.ZERO;
				ItemPedidoService.alteraFlexibilizacao(0.00, itemPedidoTemporario, cliente, relacaoGiro, new ValorMinimoItem());
			}
			ItemPedidoService.alteraAcrescimoPreco((-1) * simulacaoBeneficio.percentualDescontoItem.doubleValue(), itemPedidoTemporario, cliente, relacaoGiro, new ValorMinimoItem());
		}
		else{
			if (simulacaoBeneficio.percentualAcrescimoConcedido.doubleValue() > 0.00){
				simulacaoBeneficio.percentualDescontoItem = BigDecimal.ZERO;
				ItemPedidoService.alteraAcrescimoPreco(0.00, itemPedidoTemporario, cliente, relacaoGiro, new ValorMinimoItem());
			}
			ItemPedidoService.alteraFlexibilizacao(simulacaoBeneficio.percentualDescontoItem.doubleValue(), itemPedidoTemporario, cliente, relacaoGiro, new ValorMinimoItem());
		}
		calculaSimulacaoBeneficio(simulacaoBeneficio, itemPedidoTemporario, cliente);
	}
	
	public static Item executaChamadaCalculoPreco(SimulacaoBeneficio simulacaoBeneficio, ItemPedidoTemporario itemPedidoTemporario, Cliente cliente) throws Exception {
		itemPedidoTemporario.percentualAcrescimoConcedido = simulacaoBeneficio.percentualDescontoItem != null && simulacaoBeneficio.percentualDescontoItem.doubleValue() < 0 ? simulacaoBeneficio.percentualDescontoItem.abs() : itemPedidoTemporario.percentualAcrescimoConcedido;
		itemPedidoTemporario.percentualDescontoFlexivel = simulacaoBeneficio.percentualSimulacaoBeneficio;
		Item item = new Item();
		item.populaObjeto(itemPedidoTemporario);
		return CalculoService.calculaPreco(
				item, 
				cliente);
	}


}