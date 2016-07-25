package br.com.martins.vendas.dao.temp;

import java.util.List;

import br.com.martins.vendas.services.calculodepreco.Item;

import com.brq.mobile.framework.dao.TransactionManager;

public class TabelaTmpMePrmDAO extends TabelaBase {

	private static final String NOME_TABELA = "TMPMEPRM";

	public static boolean limpaTabela() {
		return limpaTabela(NOME_TABELA);
	}

	public static boolean insereItem(Item item) {
		return insereItem(NOME_TABELA, item);
	}

	public static boolean insereItem(Item item, TransactionManager transactionManager) {
		return insereItem(NOME_TABELA, item, transactionManager);
	}

	public static boolean alteraItem(Item item) {
		return alteraItem(NOME_TABELA, item);
	}

	public static Item obtemItem(Integer codigoMercadoria) {
		return obtemItem(NOME_TABELA, codigoMercadoria);
	}

	public static boolean excluiItem(Integer codigoMercadoria) {
		return excluiItem(NOME_TABELA, codigoMercadoria);
	}

	public static List<Item> obtemTodosItens() {
		return obtemTodosItens(NOME_TABELA);
	}
	
	/**
	 * Obtem itens da lista customizada.
	 *
	 * @param codigoListaCustomizada the codigo lista customizada
	 * @return the list
	 */
	public static List<Item>obtemItensDaListaCustomizada(Integer codigoListaCustomizada){
		return obtemItensDaListaCustomizada(NOME_TABELA, codigoListaCustomizada);
	}

}
