package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.SubCategoriaMercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;

public class SubCategoriaMercadoriaDAO {
	
	private static final String	TAG	= SubCategoriaMercadoriaDAO.class.getName();

	private static Database db;
	
	public static SubCategoriaMercadoria recuperarSubCategoriaMercadoria(Integer codigoSubCategoria, String descricaoSubCategoria) {
		StringBuilder sql = new StringBuilder();
		sql
		.append(" SELECT CODGRPMER,               ")
		.append("        CODFMLMER,               ")
		.append("        CODCLSMER,               ")
		.append("        DESCLSMER                ")
		.append("  FROM PCACLS CLS                ")
		.append(" WHERE CODCLSMER = %s            ")
		.append("   AND DESCLSMER = '%s'          ");

		try {			
			db = DatabaseFactory.getInstance();
			List<Map<String, String>> lst = db.executeQuery(DatabaseUtil.montaQuery(sql, codigoSubCategoria, descricaoSubCategoria));
			return carregarSubCategoriaMercadoria(lst.get(0));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public static List<SubCategoriaMercadoria> listarSubCategoriaMercadoria() {
		return listarSubCategoriaMercadoria(null, null);
	}
	
	public static List<SubCategoriaMercadoria> listarSubCategoriaMercadoria(Integer codigoGrupoMercadoria, Integer codigoCategoriaMercadoria) {
		
		boolean restricao = codigoCategoriaMercadoria != null && codigoGrupoMercadoria != null;
		
		List<Map<String, String>> resultQuery = new ArrayList<Map<String,String>>();
		
		StringBuilder sql = new StringBuilder();
		sql
		.append(" SELECT CODGRPMER,               ")
		.append("        CODFMLMER,               ")
		.append("        CODCLSMER,               ")
		.append("        DESCLSMER                ")
		.append("  FROM PCACLS CLS                ");
		 
		if (restricao) {
			sql
			.append(" WHERE CODGRPMER = %s            ")
			.append("   AND CODFMLMER = %s ORDER BY 4 ");
		 }
		
		try {			
			db = DatabaseFactory.getInstance();
			resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql, restricao ? new Object[]{codigoGrupoMercadoria, codigoCategoriaMercadoria} : new String[0]));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return carregarListaCategoriaMercadoria(resultQuery);
	}
	
	private static List<SubCategoriaMercadoria> carregarListaCategoriaMercadoria(List<Map<String, String>> resultQuery){
		List<SubCategoriaMercadoria> list = new ArrayList<SubCategoriaMercadoria>(resultQuery.size());
		for (Map<String, String> key : resultQuery) {
			SubCategoriaMercadoria subCategoria = carregarSubCategoriaMercadoria(key);	
			list.add(subCategoria);
		}
		return list;
	}

	private static SubCategoriaMercadoria carregarSubCategoriaMercadoria(Map<String, String> key) {
		SubCategoriaMercadoria subCategoria = new SubCategoriaMercadoria(); 
		subCategoria.codigoGrupoMercadoria 				= Integer.parseInt(key.get("CODGRPMER"));
		subCategoria.codigoCategoriaMercadoria 			= Integer.parseInt(key.get("CODFMLMER"));
		subCategoria.codigoSubCategoriaMercadoria 		= Integer.parseInt(key.get("CODCLSMER"));
		subCategoria.descricaoSubCategoriaMercadoria 	= key.get("DESCLSMER");
		return subCategoria;
	}
}
