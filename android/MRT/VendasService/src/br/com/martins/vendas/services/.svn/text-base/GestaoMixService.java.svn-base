package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.RegraBloqueio;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class GestaoMixService {
	
	private static final String			TAG								= GestaoMixService.class.getName();

	private static List<RegraBloqueio>	regras							= new ArrayList<RegraBloqueio>();
	
	private static Map<Integer,List<RegraBloqueio>>	regrasPorMercadoria	= new HashMap<Integer, List<RegraBloqueio>>();

	public static Database				db;

	/**
	 * Busca as regras de bloqueio do representante para o cliente ou grupo de clientes informado.
	 * 
	 * @param codigoCliente
	 * @param codigoGrupoCliente
	 * @param codigoRepresentante
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 * @param codigoCidade
	 * @param codigoEstadoDestino
	 * @return lista de Regras de Bloqueio de Mercadoria.
	 */
	public static List<RegraBloqueio> buscaRegrasDeBloqueio(Integer codigoCliente, 
			Integer codigoGrupoCliente, 
			Integer codigoRepresentante, 
			Integer codigoSupervisor, 
			Integer codigoAtividade,
			Integer codigoCidade, 
			String codigoEstadoDestino,
			List<Integer> codigosBloqueio) {
		
		if (!regras.isEmpty()) {
			return regras;
		}
		
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT GMP.CODBLQMER, GMP.TIPEDEPUB, GMP.CODEDEPUB, GMR.TIPEDEREG, GMX.TIPBLQMER, GMX.VLRRDCITE");
		sql.append("  FROM PCAGMP GMP ");
		sql.append(" INNER JOIN PCAGMR GMR ON GMP.CODBLQMER = GMR.CODBLQMER ");
		sql.append(" INNER JOIN PCAGMX GMX ON GMP.CODBLQMER = GMX.CODBLQMER	");
		sql.append(" WHERE ");
		sql.append("      ( ");
		sql.append("      	((GMP.TIPEDEPUB = 60) AND (GMP.CODEDEPUB = ?)) OR "); // CODCLI    - ex 15168173
		sql.append("   		((GMP.TIPEDEPUB = 55) AND (GMP.CODEDEPUB = ?)) OR "); // CODGRPCLI - ex 533
		sql.append("   		((GMP.TIPEDEPUB = 50) AND (GMP.CODEDEPUB = ?)) OR "); // CODREP    - ex 47355
		sql.append("   		((GMP.TIPEDEPUB = 3)  AND (GMP.CODEDEPUB = ?)) OR "); // CODSUP    - ex 76135
		sql.append("   		((GMP.TIPEDEPUB = 2)  AND (GMP.CODEDEPUB = ?)) OR "); // CODATI    - ex 80
		sql.append("   		(GMP.TIPEDEPUB =  1) ");
		sql.append("      )      ");
		sql.append("      AND 	 ");
		sql.append("      (		 ");
		sql.append("      	(GMR.TIPEDEREG = 30 AND TRIM(GMR.CODEDEREG) = ?) OR "); // CODCID - ex '1789'
		sql.append("      	(GMR.TIPEDEREG = 20 AND TRIM(GMR.CODEDEREG) = ?) OR "); // CODESTDSN - 'AL'
		sql.append("		(GMR.TIPEDEREG = 10) ");
		sql.append("	  )       ");
		sql.append("      AND GMX.DATFIMBLQ >= STRFTIME('%%Y%%m%%d', DATETIME('NOW')) ");
		
		String[] parameters = new String[7];
		parameters[0] = (Util.getStringValue(codigoCliente));
		parameters[1] = (Util.getStringValue(codigoGrupoCliente));
		parameters[2] = (Util.getStringValue(codigoRepresentante));
		parameters[3] = (Util.getStringValue(codigoSupervisor));
		parameters[4] = (Util.getStringValue(codigoAtividade));
		parameters[5] = (Util.getStringValue(codigoCidade));
		parameters[6] = (Util.getStringValue(codigoEstadoDestino));
		
		if (codigosBloqueio != null && !codigosBloqueio.isEmpty()) {
			sql.append("  AND GMP.CODBLQMER IN (");
			for (int count = 0; count < codigosBloqueio.size(); count++) {
				sql.append(codigosBloqueio.get(count));
				if (count < codigosBloqueio.size() - 1) {
					sql.append(",");	
				}
			}
			sql.append(")");
		}
		
		
		sql.append(" ORDER BY GMP.TIPEDEPUB DESC, GMP.CODEDEPUB DESC, GMX.CODBLQMER DESC , GMR.TIPEDEREG , GMR.CODEDEREG ");
		String query = DatabaseUtil.montaQuery(sql);

		db = DatabaseFactory.getInstance();
				
		try {
			
			List<Map<String, String>> queryResult = db.executeQuery(query, parameters);			
			regras.addAll(convertResultQueryToRegraBloqueioList(queryResult));
			
		} catch (Exception e) {
			
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
			
		}

		return regras;
	}

	/**
	 * Busca as regras de bloqueio do representante para o cliente ou grupo de clientes informado.
	 * 
	 * @param codigoCliente
	 * @param codigoGrupoCliente
	 * @param codigoRepresentante
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 * @param codigoCidade
	 * @param codigoEstadoDestino
	 * @return lista de Regras de Bloqueio de Mercadoria.
	 */
	public static Map<Integer,List<RegraBloqueio>> buscaRegrasDeBloqueioPorMercadoria(
			Integer codigoCliente, 
			Integer codigoGrupoCliente, 
			Integer codigoRepresentante, 
			Integer codigoSupervisor, 
			Integer codigoAtividade,
			Integer codigoCidade, 
			String  codigoEstadoDestino) {
		
		if (!regrasPorMercadoria.isEmpty()) {
			return regrasPorMercadoria;
		}
		
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT GMP.CODBLQMER, GMP.TIPEDEPUB, GMP.CODEDEPUB, GMR.TIPEDEREG, GMX.TIPBLQMER, GMX.VLRRDCITE");
		sql.append("  FROM PCAGMP GMP ");
		sql.append(" INNER JOIN PCAGMR GMR ON GMP.CODBLQMER = GMR.CODBLQMER ");
		sql.append(" INNER JOIN PCAGMX GMX ON GMP.CODBLQMER = GMX.CODBLQMER	");
		sql.append(" WHERE ");
		sql.append("      ( ");
		sql.append("      	((GMP.TIPEDEPUB = 60) AND (GMP.CODEDEPUB = ?)) OR "); // CODCLI    - ex 15168173
		sql.append("   		((GMP.TIPEDEPUB = 55) AND (GMP.CODEDEPUB = ?)) OR "); // CODGRPCLI - ex 533
		sql.append("   		((GMP.TIPEDEPUB = 50) AND (GMP.CODEDEPUB = ?)) OR "); // CODREP    - ex 47355
		sql.append("   		((GMP.TIPEDEPUB = 3)  AND (GMP.CODEDEPUB = ?)) OR "); // CODSUP    - ex 76135
		sql.append("   		((GMP.TIPEDEPUB = 2)  AND (GMP.CODEDEPUB = ?)) OR "); // CODATI    - ex 80
		sql.append("   		(GMP.TIPEDEPUB =  1) ");
		sql.append("      )      ");
		sql.append("      AND 	 ");
		sql.append("      (		 ");
		sql.append("      	(GMR.TIPEDEREG = 30 AND TRIM(GMR.CODEDEREG) = ?) OR "); // CODCID - ex '1789'
		sql.append("      	(GMR.TIPEDEREG = 20 AND TRIM(GMR.CODEDEREG) = ?) OR "); // CODESTDSN - 'AL'
		sql.append("		(GMR.TIPEDEREG = 10) ");
		sql.append("	  )       ");
		sql.append("      AND GMX.DATFIMBLQ >= STRFTIME('%%Y%%m%%d', DATETIME('NOW')) ");
		sql.append(" ORDER BY GMP.TIPEDEPUB DESC, GMP.CODEDEPUB DESC, GMX.CODBLQMER DESC , GMR.TIPEDEREG , GMR.CODEDEREG ");
		
		String[] parameters = new String[7];
		parameters[0] = (Util.getStringValue(codigoCliente));
		parameters[1] = (Util.getStringValue(codigoGrupoCliente));
		parameters[2] = (Util.getStringValue(codigoRepresentante));
		parameters[3] = (Util.getStringValue(codigoSupervisor));
		parameters[4] = (Util.getStringValue(codigoAtividade));
		parameters[5] = (Util.getStringValue(codigoCidade));
		parameters[6] = (Util.getStringValue(codigoEstadoDestino));

		String query = DatabaseUtil.montaQuery(sql);

		db = DatabaseFactory.getInstance();
		try {
			int codigoBloqueio = -1;
			
			List<Map<String, String>> bloqueiosPorCliente = db.executeQuery(query, parameters);
			
			int size = bloqueiosPorCliente.size();
			
			if (size > 0) {
			
				List<RegraBloqueio> regras = new ArrayList<RegraBloqueio>();
				for (int i = 0; i < size; i++) {
					
					RegraBloqueio regra = convertResultQueryToRegraBloqueio(bloqueiosPorCliente.get(i));
					
					if (codigoBloqueio == -1 || codigoBloqueio == regra.codigoBloqueioMercadoria) {
						
						regras.add(regra);
						
					} else {
						
						regrasPorMercadoria.put(codigoBloqueio, regras);
						regras = new ArrayList<RegraBloqueio>();
						regras.add(regra);
						
					}
					
					codigoBloqueio      = regra.codigoBloqueioMercadoria;
				}
				
				return regrasPorMercadoria;
			
			}
		} catch (Exception e) {
			
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getMessage(), e);
			
		}

		return regrasPorMercadoria;
	}

	
	/**
	 * Busca os codigos de bloqueio da mercadoria.
	 * 
	 * @param codigoMercadoria
	 * @return
	 */
	public static List<Integer> buscaCodigoDeBloqueioDaMercadoria(Integer codigoMercadoria) {
		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> resultQuery = db.executeQuery("SELECT CODBLQMER FROM PCAGMM GMM WHERE CODMER = ?", Util.getStringValue(codigoMercadoria));
			if (resultQuery.size() > 0) {
				List<Integer> codigos = new ArrayList<Integer>();
				for (Map<String, String> registro : resultQuery) {
					codigos.add(Util.getInteger(registro.get("CODBLQMER")));
				}
				return codigos;
			}		
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return Collections.emptyList();

	}

	/**
	 * Verifica se o codigo de bloqueio da mercadoria esta bloqueado pela filial de expedicao e
	 * faturamento.
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @return
	 */
	public static boolean buscaBloqueioPorFilialExpedicao(Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, Integer codigoBloqueioMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1 ");
		sql.append("  FROM PCAGME GME INNER JOIN PCAGMF GMF ");
		sql.append("    ON GME.CODBLQMER = GMF.CODBLQMER  ");
		sql.append("   AND GME.CODBLQMER = ? ");
		sql.append("   AND GME.CODFILEPD IN (0, ?) ");
		sql.append("   AND GMF.CODFILFAT IN (0, ?) ");

		db = DatabaseFactory.getInstance();
		String query = DatabaseUtil.montaQuery(sql, codigoBloqueioMercadoria, codigoFilialExpedicao, codigoFilialFaturamento);

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(codigoBloqueioMercadoria);
		parameters[1] = Util.getStringValue(codigoFilialExpedicao);
		parameters[2] = Util.getStringValue(codigoFilialFaturamento);
		try {
			
			return db.executeQuery(query, parameters).size() > 0;

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

	}

	/**
	 * 
	 * @param resultQuery
	 * @return lista de objetos do tipo RegraBloqueio
	 */
	private static List<RegraBloqueio> convertResultQueryToRegraBloqueioList(List<Map<String, String>> queryResult) {		
		List<RegraBloqueio> regras = new ArrayList<RegraBloqueio>();
		for (Map<String, String> registro : queryResult) {
			regras.add(convertResultQueryToRegraBloqueio(registro));
		}
		return regras;
	}
	
	/**
	 * 
	 * @param resultQuery
	 * @return lista de objetos do tipo RegraBloqueio
	 */
	private static RegraBloqueio convertResultQueryToRegraBloqueio(Map<String, String> registro) {

		RegraBloqueio regra = new RegraBloqueio();
					
		regra.codigoBloqueioMercadoria = Util.getInteger(registro.get("CODBLQMER"));
		
		regra.tipoPublicoAlvo          = Util.getInteger(registro.get("TIPEDEPUB"));

		regra.codigoPublicoAlvo        = Util.getInteger(registro.get("CODEDEPUB"));

		regra.codigoRegional           = Util.getInteger(registro.get("TIPEDEREG"));

		regra.tipoBloqueioMercadoria   = Util.getInteger(registro.get("TIPBLQMER"));

		regra.valorReducaoItem         = Util.getInteger(registro.get("VLRRDCITE"));

		return regra;
	}


}
