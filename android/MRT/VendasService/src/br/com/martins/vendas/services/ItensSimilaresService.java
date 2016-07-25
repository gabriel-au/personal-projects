package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.List;

import br.com.martins.vendas.dao.ItemSimilarDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMeSmrDAO;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.ItemFiltro;

import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;

/**
 * Classe de servicos de listagem de Itens Disponiveis
 * 
 */
public class ItensSimilaresService {

	private static final String	TAG	= ItensSimilaresService.class.getName();

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoRelacionamentoCliente
	 * @param condicaoPagamento
	 * @param itemFiltro
	 * @return
	 */
	public static List<Item> listaItens( 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer codigoCliente,
			Integer codigoRelacionamentoCliente, 
			Integer codigoGrupoCliente,
			String  flagAlvaraPsicotropico,
			Integer codigoRepresentante,
			CondicaoPagamento condicaoPagamento,
			ItemFiltro itemFiltro) {
		
		/*
		 * Recupera lista de mercadorias válidas
		 */
		List<Item> itens = ItemSimilarDAO.listaItens( 
				codigoFilialExpedicao, 
				codigoFilialFaturamento,
				codigoCliente,
				codigoRelacionamentoCliente,
				codigoGrupoCliente,
				flagAlvaraPsicotropico,
				codigoRepresentante,
				itemFiltro);
		
		if (itemFiltro.numPagina == 1) {
			TabelaTmpMeSmrDAO.limpaTabela();
		}
		
		TransactionManager transactionManager = null;
		try {
			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();

			for (Item item : itens) {
	
				item.condicaoPagamento = condicaoPagamento;
				TabelaTmpMeSmrDAO.insereItem(item, transactionManager);
			}
			
			transactionManager.commitTransaction();

			return itens;

		} catch (SQLException e) {
			
			try {
				transactionManager.rollbackTransaction();
			} catch (SQLException ex) {
				Log.e(TAG, ex.getLocalizedMessage());
			}
			
			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
			
		} finally {
			
			try {
				transactionManager.endTransaction();
			} catch (SQLException ex) {
				Log.e(TAG, ex.getLocalizedMessage());
			}
		}
	}
}