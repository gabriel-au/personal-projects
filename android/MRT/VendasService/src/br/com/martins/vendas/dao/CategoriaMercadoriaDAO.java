package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.CategoriaMercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;

public class CategoriaMercadoriaDAO {
	
	private static final String	TAG	= CategoriaMercadoriaDAO.class.getName();

	private static Database db;
	
	
	public static CategoriaMercadoria recuparCategoriaMercadoria(Integer codigoGrupo, Integer codigoCategoria) {
		StringBuilder sql = new StringBuilder();
		sql
		.append("SELECT CODGRPMER,               ")
		.append("       CODFMLMER,               ")
		.append("       DESFMLMER                ")
		.append("  FROM PCAFML FML               ")
		.append(" WHERE CODGRPMER = %s           ")
		.append("   AND CODFMLMER = %s           ");
		try {			
			db = DatabaseFactory.getInstance();
			List<Map<String, String>> resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql, codigoGrupo, codigoCategoria));
			if (!resultQuery.isEmpty()) {
				return carregarCategoriaMercadoria(resultQuery.get(0));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return null;
	}
	
	public static CategoriaMercadoria recuparCategoriaMercadoria(Integer codigoCategoria, String descricaoCategoria) {
		StringBuilder sql = new StringBuilder();
		sql
		.append("SELECT CODGRPMER,               ")
		.append("       CODFMLMER,               ")
		.append("       DESFMLMER                ")
		.append("  FROM PCAFML FML               ")
		.append(" WHERE CODFMLMER = %s           ")
		.append("   AND DESFMLMER LIKE '%s'      ");
		try {			
			db = DatabaseFactory.getInstance();
			List<Map<String, String>> resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql, codigoCategoria, descricaoCategoria));
			if (!resultQuery.isEmpty()) {
				return carregarCategoriaMercadoria(resultQuery.get(0));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return null;
	}
	
	public static List<CategoriaMercadoria> listarCategoriaMercadoria() {
		return listarCategoriaMercadoria(null);
	}
	
	public static List<CategoriaMercadoria> listarCategoriaMercadoria(Integer codigoGrupoMercadoria) {
		boolean restricao = codigoGrupoMercadoria != null;
		
		List<Map<String, String>> resultQuery = new ArrayList<Map<String,String>>();
		StringBuilder sql = new StringBuilder();
		sql
		.append("SELECT CODGRPMER,               ")
		.append("       CODFMLMER,               ")
		.append("       DESFMLMER                ")
		.append("  FROM PCAFML FML               ");
		if (restricao) {
			sql
			.append(" WHERE CODGRPMER = %s       ");
		}
		sql
		.append(" ORDER BY 3                     ");
		try {			
			db = DatabaseFactory.getInstance();
			resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql, restricao ? codigoGrupoMercadoria : new String[0]));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return carregarListaCategoriaMercadoria(resultQuery);
	}
	
	private static List<CategoriaMercadoria> carregarListaCategoriaMercadoria(List<Map<String, String>> resultQuery){
		List<CategoriaMercadoria> list = new ArrayList<CategoriaMercadoria>(resultQuery.size());
		for (Map<String, String> key : resultQuery) {
			list.add(carregarCategoriaMercadoria(key));
		}
		return list;
	}

	private static CategoriaMercadoria carregarCategoriaMercadoria(Map<String, String> key) {
		CategoriaMercadoria categoria = new CategoriaMercadoria(); 
		categoria.codigoGrupoMercadoria 		= Integer.parseInt(key.get("CODGRPMER"));
		categoria.codigoCategoriaMercadoria 	= Integer.parseInt(key.get("CODFMLMER"));
		categoria.descricaoCategoriaMercadoria 	= key.get("DESFMLMER");
		return categoria;
	}
}
