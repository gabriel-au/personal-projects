package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.PalavraChave;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class PalavraChaveDAO {

	private static final String	TAG	= PalavraChaveDAO.class.getName();

	public static List<PalavraChave> obtemFornecedores() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT TIPTERCHV,    ");
		sql.append("       CODTERCHV,    ");
		sql.append(" 	   DESTERCHV     ");
		sql.append("  FROM PCAPCH        ");
		sql.append(" WHERE TIPTERCHV = 2 ");
		sql.append(" ORDER BY 3;         ");
		
		return recupera(sql);
	}
	
	public static List<PalavraChave> obtemSubCategorias() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT TIPTERCHV,    ");
		sql.append("       CODTERCHV,    ");
		sql.append(" 	   DESTERCHV     ");
		sql.append("  FROM PCAPCH        ");
		sql.append(" WHERE TIPTERCHV = 1 ");
		sql.append(" ORDER BY 3;         ");
		
		return recupera(sql);
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	private static List<PalavraChave> recupera(StringBuilder sql) {
		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery(sql));
			if (!result.isEmpty()) {

				final int size = result.size();
				
				List<PalavraChave> chaves = new ArrayList<PalavraChave>(size);
				for (int count = 0; count < size; count++) {
					
					Map<String, String> map = result.get(count);
	
					PalavraChave palavraChave = new PalavraChave();
	
					palavraChave.tipoTermoChave 	     = Util.getInteger(map.get("TIPTERCHV"));
					palavraChave.codigoTermoChave 		 = Util.getInteger(map.get("CODTERCHV"));
					palavraChave.descricaoTermoChave 	 = map.get("DESTERCHV");
					chaves.add(palavraChave);
				}
				return chaves;
			}

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getMessage(), e);

		}

		return Collections.emptyList();
	}
	
	/**
	 * Recupera dados de livro de preço
	 * 
	 * @param codFilialExp
	 *            código filial expedição
	 * @param codFilialFat
	 *            código filial faturamento
	 * @param numRelacaoCidade
	 *            numero relação cidade
	 * @param codMercadoria
	 *            código da mercadoria
	 * @return instância da classe LivroPreco
	 */
	public static List<Integer> recuperaComportamentoDeCompras(Integer codCliente) {
	
		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery("SELECT CODMER FROM PCASGS WHERE CODCLI = %s ORDER BY NUMPRRMER", codCliente));
			if (result.isEmpty()) {
				return Collections.emptyList();
			}		
			
			int size = result.size();
			List<Integer> list = new ArrayList<Integer>(size); 
			for (int count = 0; count < size; count++) {
				list.add(Integer.parseInt(result.get(count).get("CODMER")));
			}
			
			return list;
			
		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}	
			
	}
}