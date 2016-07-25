package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.RelacaoGiro;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

/**
 * Tabela PCAAGE
 * @author floliveira
 *
 */
public class RelacaoGiroDAO {
	
	private static final String TAG = RelacaoGiroDAO.class.getName();

	/**
	 * Obtem a lista de clientes
	 * 
	 * @return List<Cliente> - lista de clientes
	 * @throws Exception 
	 */
	public static RelacaoGiro recuperarPorFilial(Integer codTerritorio, Integer codFilialExpedicao, Integer codFilialFaturamento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODGIR, ");
		sql.append(" 		CODFILFAT,  ");
		sql.append(" 		CODFILEPD,  ");
		sql.append(" 		NUMRLCCID,  ");
		sql.append(" 		PERDCRICM,  ");
		sql.append(" 		CODESTDSN,  ");
		sql.append(" 		CODSUP,     ");
		sql.append(" 		CODGER,     ");
		sql.append(" 		CODVRSSIS,  ");
		sql.append(" 		CODESTORI,  ");
		sql.append(" 		PERICMMER,  ");
		sql.append(" 		CODEDEVND,  ");
		sql.append(" 		PERICMORI,  ");
		sql.append(" 		ICMMERCSM,  ");
		sql.append(" 		ICMORICSM,  ");
		sql.append(" 		TIPUTZICM,  ");
		sql.append(" 		INDESTPRC   ");
		sql.append("   FROM PCAAGE AGE  ");
		sql.append("  WHERE AGE.CODGIR    = ? ");
		sql.append("    AND AGE.CODFILFAT = ? ");
		sql.append("    AND AGE.CODFILEPD = ? ");
		try {
			Database database = DatabaseFactory.getInstance();
			String query = DatabaseUtil.montaQuery(sql.toString());
			
			String[] parameters = new String[3];
			parameters[0] = Util.getStringValue(codTerritorio);
			parameters[1] = Util.getStringValue(codFilialFaturamento);
			parameters[2] = Util.getStringValue(codFilialExpedicao);
			
			List<Map<String, String>> result = database.executeQuery(query, parameters);
			List<RelacaoGiro> listaRelacaoGiro = criaListaRelacaoGiro(result);
			return !listaRelacaoGiro.isEmpty() ? listaRelacaoGiro.get(0) : null;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
	
	
	private static List<RelacaoGiro> criaListaRelacaoGiro(List<Map<String, String>> mapaRelacaoGiroPar) throws Exception {
		
		List<RelacaoGiro> listaAgendaLivroPreco = new ArrayList<RelacaoGiro>();

		RelacaoGiro relacaoGiro;
		for (Map<String, String> mapaRelacaoGiro : mapaRelacaoGiroPar) {
			relacaoGiro = new RelacaoGiro();
			relacaoGiro.codTerritorio        = Util.getInteger(mapaRelacaoGiro.get("CODGIR"));
			relacaoGiro.codFilialFaturamento = Util.getInteger(mapaRelacaoGiro.get("CODFILFAT"));
			relacaoGiro.codFilialExpedicao   = Util.getInteger(mapaRelacaoGiro.get("CODFILEPD"));
			relacaoGiro.numRelacaoCidade     = Util.getInteger(mapaRelacaoGiro.get("NUMRLCCID"));
			relacaoGiro.perDecresimoICM      = Util.getBigDecimal(mapaRelacaoGiro.get("PERDCRICM"));
			relacaoGiro.codEstadoDestino     = mapaRelacaoGiro.get("CODESTDSN");
			relacaoGiro.codSupervisor        = Util.getInteger(mapaRelacaoGiro.get("CODSUP"));
			relacaoGiro.codGerente           = Util.getInteger(mapaRelacaoGiro.get("CODGER"));
			relacaoGiro.codVersaoSistema     = Util.getBigDecimal(mapaRelacaoGiro.get("CODVRSSIS"));
			relacaoGiro.codEstadoOrigem      = mapaRelacaoGiro.get("CODESTORI");
			relacaoGiro.perIcmMercadoria     = Util.getBigDecimal(mapaRelacaoGiro.get("PERICMMER"));
			relacaoGiro.codEntidadeVenda     = Util.getInteger(mapaRelacaoGiro.get("CODEDEVND"));
			relacaoGiro.perIcmOrigem         = Util.getBigDecimal(mapaRelacaoGiro.get("PERICMORI"));
			relacaoGiro.imcMecadoriaConsumo  = Util.getBigDecimal(mapaRelacaoGiro.get("ICMMERCSM"));
			relacaoGiro.imcOrigemConsumo     = Util.getBigDecimal(mapaRelacaoGiro.get("ICMORICSM"));
			relacaoGiro.tipUtilizacaoICM     = Util.getInteger(mapaRelacaoGiro.get("TIPUTZICM"));
			relacaoGiro.indDestinoProcesso   = mapaRelacaoGiro.get("INDESTPRC");
			listaAgendaLivroPreco.add(relacaoGiro);
		}
		return listaAgendaLivroPreco;
	}

	/**
	 * Obtem a lista de relacoes de giro do representante
	 * 
	 * @param codigoRepresentante - código do representante
	 * @return List<RelacaoGiro> - lista de relacao de giro do representante
	 * @throws Exception 
	 */
	public static List<RelacaoGiro> listaRelacaoGiroRepresentante(Integer codigoRepresentante) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT CODGIR ");
		sql.append("FROM PCAAGE ");
		sql.append("WHERE CODEDEVND = %s ");
		sql.append("ORDER BY CODGIR");

		try {
			
			List<RelacaoGiro> listaRelacaoGiro = new ArrayList<RelacaoGiro>();
			RelacaoGiro relacaoGiro = null;
			
			Database database = DatabaseFactory.getInstance();
			List<Map<String, String>> result = database.executeQuery(DatabaseUtil.montaQuery(sql.toString(), codigoRepresentante));
			
			if (!result.isEmpty()) {
				for (Map<String, String> map : result) {
					relacaoGiro = new RelacaoGiro();
					relacaoGiro.codTerritorio = Util.getInteger(map.get("CODGIR"));
					listaRelacaoGiro.add(relacaoGiro);
				}
			}
			
			return listaRelacaoGiro;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Obtem estado de origem e destino
	 * 
	 * @param codTerritorio território do cliente
	 * 
	 * @param codFilialExpedicao código filial de expedição 
	 * 
	 * @param codFilialFaturamento código filial de faturamento
	 * 
	 * @return string vetor de 2 posições. Posição 1: CODESTORI e Posição2: CODESTDSN 
	 */
	public static String[] obtemEstadoFilial(final Integer codTerritorio, final Integer codFilialExpedicao, final Integer codFilialFaturamento) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODESTORI, CODESTDSN FROM PCAAGE AGE ");
		sql.append("  WHERE AGE.CODGIR    = ? ");
		sql.append("    AND AGE.CODFILFAT = ? ");
		sql.append("    AND AGE.CODFILEPD = ? ");
		try {
			Database database = DatabaseFactory.getInstance();
			String query = DatabaseUtil.montaQuery(sql.toString());
			
			String[] parameters = new String[3];
			parameters[0] = Util.getStringValue(codTerritorio);
			parameters[1] = Util.getStringValue(codFilialFaturamento);
			parameters[2] = Util.getStringValue(codFilialExpedicao);
			
			List<Map<String, String>> result = database.executeQuery(query, parameters);
			if (result.size() == 0) {return null;}
			
			Map<String, String> map = result.get(0);
			
			String[] estados = new String[2];
			estados[0] = map.get("CODESTORI");
			estados[1] = map.get("CODESTDSN");
			
			return estados;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

}