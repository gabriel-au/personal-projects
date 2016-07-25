package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.GrupoMercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;

public class GrupoMercadoriaDAO {
	
	private static final String	TAG	= GrupoMercadoriaDAO.class.getName();
	
	private static Database db;
	
	/**
	 * 
	 * @return
	 */
	public static GrupoMercadoria buscarGrupoMercadoria(Integer codigoGrupo) {
		StringBuilder sql = new StringBuilder();
		sql
		.append(" SELECT CODGRPMER,      ")
		.append("        DESGRPMER       ")
		.append("   FROM PCAGRP          ")
		.append("  WHERE CODGRPMER = %s  ");
		try {			
			db = DatabaseFactory.getInstance();
			List<Map<String, String>> resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql, codigoGrupo));
			if (!resultQuery.isEmpty()) {
				return carregarGrupoMercadoria(resultQuery.get(0));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<GrupoMercadoria> listarGrupoMercadoria() {
		List<Map<String, String>> resultQuery = new ArrayList<Map<String,String>>();
		StringBuilder sql = new StringBuilder();
		sql
		.append(" SELECT CODGRPMER,       ")
		.append("        DESGRPMER        ")
		.append("   FROM PCAGRP           ")
		.append(" ORDER BY 2              ");
		try {			
			db = DatabaseFactory.getInstance();
			resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return carregarListaGrupoMercadoria(resultQuery);
	}
	
	/**
	 * 
	 * @param codigoCategoria
	 * @return
	 */
	public static List<GrupoMercadoria> listarGrupoMercadoria(Integer codigoCategoria) {
		List<Map<String, String>> resultQuery = new ArrayList<Map<String,String>>();
		StringBuilder sql = new StringBuilder();
		sql
		.append(" SELECT CODGRPMER,       ")
		.append("        DESGRPMER        ")
		.append("   FROM PCAGRP           ")
		.append("  WHERE CODGRPMER IN (SELECT CODGRPMER FROM PCAFML FML WHERE FML.CODFMLMER = %s) ")
		.append(" ORDER BY 2 ");
		try {			
			db = DatabaseFactory.getInstance();
			resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql, codigoCategoria));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
		}
		return carregarListaGrupoMercadoria(resultQuery);
	}
	
	/**
	 * 
	 * @param resultQuery
	 * @return
	 */
	private static List<GrupoMercadoria> carregarListaGrupoMercadoria(List<Map<String, String>> resultQuery){
		List<GrupoMercadoria> list = new ArrayList<GrupoMercadoria>() ;
		for (Map<String, String> key : resultQuery) {
			list.add(carregarGrupoMercadoria(key));
		}
		return list;
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	private static GrupoMercadoria carregarGrupoMercadoria(Map<String, String> key) {
		GrupoMercadoria grupoMercadoria = new GrupoMercadoria(); 
		grupoMercadoria.codigoGrupoMercadoria = Integer.parseInt(key.get("CODGRPMER"));
		grupoMercadoria.descricaoGrupoMercadoria = key.get("DESGRPMER");
		return grupoMercadoria;
	}
}
