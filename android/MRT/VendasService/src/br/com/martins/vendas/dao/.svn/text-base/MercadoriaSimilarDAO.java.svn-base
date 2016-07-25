package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.MercadoriaSimilar;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;

/**
 * Classe responsavel pelos servicos da funcionalidade de Produtos Similares.
 * 
 */
public class MercadoriaSimilarDAO {

	private static Database		db;
	private static final String	TAG							= MercadoriaSimilarDAO.class.getName();

	private static final String	CODIGO_MERCADORIA_PRINCIPAL	= "CODMERPCP";
	
	private static final String	DESCRICAO_SIMILAR			= "DESSCTGRP";
	
	private static final String	CODIGO_MERCADORIA			= "CODMER";

	/**
	 * 
	 * @param mercadoria
	 * @return
	 */
	public static List<MercadoriaSimilar> obtemSimilares(Mercadoria mercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select CODMERPCP, CODMER, DESSCTGRP, FLGMPLQDE, CODBRRMER, INDIMPFTE, INDRTCBFV, ");
		sql.append(" INDRTCBFV, INDRTCBFBM INDRTCBNF, INDMERKIT, INDRSTKIT, CODGRPMER, QDEMNMKIT, ");
		sql.append(" CODFMLMER, CODCLSMER ");
		sql.append(" from PCASMR");
		sql.append(" where CODMERPCP = %s ");

		String query = DatabaseUtil.montaQuery(sql, mercadoria.codigo);
		db = DatabaseFactory.getInstance();

		List<MercadoriaSimilar> lista = null;

		try {
			List<Map<String, String>> result = db.executeQuery(query);
			lista = convertResultToMercadoriaSimilar(result);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return lista;

	}

	/**
	 * Converte o resultado de uma query em um objeto do tipo <b>MercadoriaVO</b>
	 * 
	 * @param resultQuery
	 *            que � uma 'List<Map<String,String>>' onde cada Map tem a linha do registro
	 * @return
	 */
	private static List<MercadoriaSimilar> convertResultToMercadoriaSimilar(List<Map<String, String>> resultQuery) {
		List<MercadoriaSimilar> listaProdutosSimilares = new ArrayList<MercadoriaSimilar>();
		MercadoriaSimilar similar = null;
		for (Map<String, String> registro : resultQuery) {
			similar = new MercadoriaSimilar();
			for (String key : registro.keySet()) {
				if (key.equalsIgnoreCase(CODIGO_MERCADORIA)) {
					similar.codigo = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase(DESCRICAO_SIMILAR)) {
					similar.descricao = registro.get(key);
				} else if (key.equalsIgnoreCase(CODIGO_MERCADORIA_PRINCIPAL)) {
					similar.codigoPrincipal = Integer.parseInt(registro.get(key));
				}
			}
			listaProdutosSimilares.add(similar);
		}

		return listaProdutosSimilares;
	}

	/**
	 * Verifica se a mercadoria é uma mercadoria principal
	 * 
	 * @param codigoMercadoria
	 *            código da mercadoria
	 *            
	 * @return true em caso positivo, false em caso contrário
	 */
	public static boolean temSimilar(Integer codigoMercadoria) {
		String query = DatabaseUtil.montaQuery("SELECT CODMERPCP FROM PCASMR WHERE CODMERPCP = %s", codigoMercadoria);
		db = DatabaseFactory.getInstance();
		try {
			return db.executeQuery(query).size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
}
