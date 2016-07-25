package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.UnidadeNegocio;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;

public class UnidadeNegocioDAO {

	private static final String	TAG	= GrupoMercadoriaDAO.class.getName();
	private static Database db;
	
	
	public static List<UnidadeNegocio> listarUnidadeNegocio(){
		List<Map<String, String>> resultQuery = new ArrayList<Map<String,String>>();
		String sql = "SELECT CODUNDESR,DESUNDESR,DESABVUND from PCACBU";
		
		try {
			
			db = DatabaseFactory.getInstance();
			resultQuery = db.executeQuery(sql);
			
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return carregarListaUnidadeNegocio(resultQuery);
	}
	
	private static List<UnidadeNegocio> carregarListaUnidadeNegocio(List<Map<String, String>> resultQuery){
		List<UnidadeNegocio> listaUnidadeNegocio = new ArrayList<UnidadeNegocio>() ;
		
		for (Map<String, String> key : resultQuery) {
			UnidadeNegocio unidadeNegocio = new UnidadeNegocio(); 
			unidadeNegocio.codigoUnidadeEstrategica = Util.getInteger(key.get("CODUNDESR"));
			unidadeNegocio.descricaoUnidadeEstrategica = key.get("DESUNDESR");
			unidadeNegocio.descricaoAbreviaturaEstrategica = key.get("DESABVUND");
			
			listaUnidadeNegocio.add(unidadeNegocio);
		}
		
		return listaUnidadeNegocio;
		
	}
	
}
