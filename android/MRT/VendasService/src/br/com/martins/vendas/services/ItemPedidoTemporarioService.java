package br.com.martins.vendas.services;

import java.util.List;

import br.com.martins.vendas.dao.ItemPedidoTemporarioDAO;
import br.com.martins.vendas.vo.ItemPedidoTemporario;

import com.brq.mobile.framework.dao.TransactionManager;

/**
 * Classe de servico referente aos pedidos temporarios na tabela TMPITEPE
 * @author BRQ Mobile Team
 *
 */
public class ItemPedidoTemporarioService {
	
	public static List<ItemPedidoTemporario> buscaItensPedidosTemporario(Integer codigoFilialExpedicao) throws Exception {
		return ItemPedidoTemporarioDAO.buscaItem(null, codigoFilialExpedicao, null);
	}
	
	public static List<ItemPedidoTemporario> buscaItem(Integer codigoMercadoria, Integer codigoFilialExpedicao, Integer codigoFilialFaturamento) throws Exception {
		return ItemPedidoTemporarioDAO.buscaItem(codigoMercadoria, codigoFilialExpedicao, codigoFilialFaturamento);
	}
	
	public static void atualiza(ItemPedidoTemporario itemPedidoTemporario, TransactionManager transactionManager) throws Exception {
		ItemPedidoTemporarioDAO.atualiza(itemPedidoTemporario, transactionManager);
	}
	
	public static ItemPedidoTemporario buscaItemTemporario(
			Integer codigoMercadoria, Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento) throws Exception {
		List<ItemPedidoTemporario> itensPedidoTemporario = buscaItem(codigoMercadoria, codigoFilialExpedicao, codigoFilialFaturamento);
		if(itensPedidoTemporario == null || itensPedidoTemporario.isEmpty()){
			return null;
		}
		return itensPedidoTemporario.get(0);
	}

}