package br.com.martins.vendas.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.PosicaoVendaDAO;
import br.com.martins.vendas.vo.PosicaoVendas;

import com.brq.mobile.framework.util.Util;

public class PosicaoVendasService {
	
	public static PosicaoVendas obtemDataDefault() {
		PosicaoVendas vendas = new PosicaoVendas();
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();
		dataInicial.set(Calendar.DAY_OF_MONTH, dataFinal.get(Calendar.DAY_OF_MONTH) + 1);
		dataInicial.set(Calendar.MONTH,dataFinal.get(Calendar.MONTH) - 1);
		
		vendas.dataInicio = dataInicial.getTime();
		vendas.dataFim    = dataFinal.getTime();		
		
		return vendas;
	}
	
	public static List<PosicaoVendas> obterPosicaoVendasPorPrazoDePagamento(Date dataInicio, Date dataFim){
		return carregarListaPosicaoVendasPorPrazoDePagamento(PosicaoVendaDAO.obterPosicaoVendasPorPrazoDePagamento(dataInicio, dataFim));
	}
	
	public static List<PosicaoVendas> obterPosicaoVendasPorCliente(Date dataInicio, Date dataFim){
		return carregarListaPosicaoVendasPorCliente(PosicaoVendaDAO.obterPosicaoVendasPorCliente(dataInicio, dataFim));
	}
	
	private static List<PosicaoVendas> carregarListaPosicaoVendasPorCliente(List<Map<String,String>> resultQuery){
		List<PosicaoVendas> posicaoVendas = new ArrayList<PosicaoVendas>();
		BigDecimal valorLiquidoTotal = BigDecimal.ZERO;;
		BigDecimal valorEfetivoTotal = BigDecimal.ZERO;
		
		if(!resultQuery.isEmpty()){
			for (Map<String,String> registro : resultQuery) {
				PosicaoVendas pv = new PosicaoVendas();
					for (String key : registro.keySet()) {
						if (key.equalsIgnoreCase("codcli")) {
							pv.cliente.codigoCliente = Integer.parseInt(registro.get(key));
						} else if (key.equalsIgnoreCase("nomcli")) {
							pv.cliente.nomeCliente = registro.get(key);
						} else if (key.equalsIgnoreCase("LIQUIDO")) {
							pv.pedido.valorLiquidoMercadoria = Util.getBigDecimal(registro.get(key));
							valorLiquidoTotal = valorLiquidoTotal.add(pv.pedido.valorLiquidoMercadoria);
							pv.pedido.valorTotalPedido = valorLiquidoTotal;
						} else if (key.equalsIgnoreCase("EFETIVO")) {
							pv.pedido.valorSemEncargos= Util.getBigDecimal(registro.get(key));
							valorEfetivoTotal = valorEfetivoTotal.add(pv.pedido.valorSemEncargos);
							pv.pedido.valorTotalSemEncargos = valorEfetivoTotal;
						} 
					}
				posicaoVendas.add(pv);	
			}
		}
		return posicaoVendas;
	}
	
	private static List<PosicaoVendas> carregarListaPosicaoVendasPorPrazoDePagamento(List<Map<String,String>> resultQuery){
		List<PosicaoVendas> posicaoVendas = new ArrayList<PosicaoVendas>();
		BigDecimal valorLiquidoTotal = BigDecimal.ZERO;;
		BigDecimal valorEfetivoTotal = BigDecimal.ZERO;
		
		if(!resultQuery.isEmpty())
			for (Map<String,String> registro : resultQuery) {
				PosicaoVendas pv = new PosicaoVendas();
					for (String key : registro.keySet()) {
						if (key.equalsIgnoreCase("CODCNDPGT")) {
							pv.pedido.condicaoPagamento = Integer.parseInt(registro.get(key));
						} else if (key.equalsIgnoreCase("LIQUIDO")) {
							pv.pedido.valorLiquidoMercadoria = Util.getBigDecimal(registro.get(key));
							valorLiquidoTotal = valorLiquidoTotal.add(pv.pedido.valorLiquidoMercadoria);
							pv.pedido.valorTotalPedido = valorLiquidoTotal;
						} else if (key.equalsIgnoreCase("EFETIVO")) {
							pv.pedido.valorSemEncargos= Util.getBigDecimal(registro.get(key));
							valorEfetivoTotal = valorEfetivoTotal.add(pv.pedido.valorSemEncargos);
							pv.pedido.valorTotalSemEncargos = valorEfetivoTotal;
						} 
					}
					
				pv.condicaoPagamento.descricaoCondicao = carregarDescricaoPosicaoVendasPorPrazoDePagamento(PosicaoVendaDAO.obterDescricaoPosicaoVendasPorPrazoDePagamento(pv.pedido.condicaoPagamento)).condicaoPagamento.descricaoCondicao;	
					
				posicaoVendas.add(pv);	
			}
		
		return posicaoVendas;
	}
	
	private static PosicaoVendas carregarDescricaoPosicaoVendasPorPrazoDePagamento(List<Map<String,String>> resultQuery){
		PosicaoVendas posicaoVendas = new PosicaoVendas();
		
		if(!resultQuery.isEmpty())
			for (Map<String,String> registro : resultQuery) {
				PosicaoVendas pv = new PosicaoVendas();
					for (String key : registro.keySet()) {
						if (key.equalsIgnoreCase("descricaoCondicao")) {
							pv.condicaoPagamento.descricaoCondicao = registro.get(key);
						} 
					}
					
				posicaoVendas = pv;	
			}
		
		return posicaoVendas;
	}
	

}
