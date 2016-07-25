package br.com.martins.vendas.services;

import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.services.calculodepreco.Item;

public class CaminhaoService {

	public static boolean atualizaCaminhao(Item item) {
		boolean result = false;		
		result = TabelaTmpItePeDAO.alteraItem(item);		
		return result;
	}
	
	/**
	 * Recupera item da tabela temporaria de pedido
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static Item obtemItemPedido(Integer codigoMercadoria) {
		return TabelaTmpItePeDAO.obtemItem(codigoMercadoria);
	}
	
	/**
	 * 
	 * @param codigoMercadoria
	 * @param numeroNotaFiscal
	 * @return
	 */
	public static boolean excluiMercadoriaLista(Integer codigoMercadoria, Integer numeroNotaFiscal) {
		return TabelaTmpItePeDAO.excluiItem(codigoMercadoria, numeroNotaFiscal);
	}
	
}
