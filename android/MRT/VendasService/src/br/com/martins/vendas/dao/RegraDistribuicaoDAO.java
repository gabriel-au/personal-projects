package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class RegraDistribuicaoDAO {

	private static final String	TAG	= RegraDistribuicaoDAO.class.getName();

	private static Database		db;

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codregraDistribuicao
	 * @return
	 * @throws IntegrationException 
	 */
	public static RegraDistribuicao obtemRegraDistribuicao(Integer codigoCliente, Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, 
			Integer codigoRegraDistribuicao) throws IntegrationException {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT	RGR.CODRGRDTB,     ");
		sql.append("		RGR.CODCLI,        ");
		sql.append("		RGR.CODFILEPD,     ");
		sql.append("		RGR.CODFILFAT,     ");
		sql.append("		RGR.INDTABMAN,     ");
		sql.append("		RGR.INDTRPPPO,     ");
		sql.append("		RGR.INDRGROBR,     ");
		sql.append("		RGR.INDRGRECC,     ");
		sql.append("		RGR.INDCNTDSN,     ");
		sql.append("		RGR.DATINIVLD,     ");
		sql.append("		RGR.DATFIMVLD,     ");
		sql.append("		RGR.CODTABFRT,     ");
		sql.append("		RGR.PERPADFRT,     ");
		sql.append("		RGR.VLRFRTMIN,     ");
		sql.append("		RGR.INDRGRVLD,     ");
		sql.append("		RGR.RAZSOCTRP,     ");
		sql.append("		RGR.DESRGRDTB      ");
		sql.append("   FROM PCARGR RGR         ");
		sql.append("  WHERE RGR.CODCLI = %s    ");
		sql.append("    AND RGR.CODFILEPD = %s ");
		sql.append("    AND RGR.CODFILFAT = %s ");
		
		if(codigoRegraDistribuicao != null){
			sql.append(" AND RGR.CODRGRDTB = %s ");
		}

		String query = DatabaseUtil.montaQuery(sql.toString(), codigoCliente, codigoFilialExpedicao, codigoFilialFaturamento, codigoRegraDistribuicao);
		db = DatabaseFactory.getInstance();

		try {

			List<Map<String, String>> result = db.executeQuery(query);
			if (result.isEmpty()) {
				return null;
			}
			
			return convertResultToRegraDistribuicao(result.get(0));

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new IntegrationException(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	private static RegraDistribuicao convertResultToRegraDistribuicao(Map<String, String> registro) {

		if (registro.isEmpty()) {
			return null;
		}

		RegraDistribuicao regraDistribuicao = new RegraDistribuicao();
		for (String key : registro.keySet()) {
			if (key.equalsIgnoreCase("CODRGRDTB")) {
				
				regraDistribuicao.codigoRegra = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("CODCLI")) {
				
				regraDistribuicao.codigoCliente = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("CODFILEPD")) {
				
				regraDistribuicao.codigoFilialExpedicao = Util.getInteger(registro.get(key));

			} else if (key.equalsIgnoreCase("CODFILFAT")) {
				
				regraDistribuicao.codigoFilialFaturamento = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("INDTABMAN")) {
				
				regraDistribuicao.indicadorTabelaManual = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("INDTRPPPO")) {
				
				regraDistribuicao.indicadorTransporteProprio = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("INDRGROBR")) {
				
				regraDistribuicao.indicadorRegraObrigatorio = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("INDRGRECC")) {
				
				regraDistribuicao.indicadorRegraExcessao = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("INDCNTDSN")) {
				
				regraDistribuicao.indicadorContaDestino = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("DATINIVLD")) {
				
				regraDistribuicao.dataInicioValidade = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("DATFIMVLD")) {
				
				regraDistribuicao.dataFimValidade = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("CODTABFRT")) {
				
				regraDistribuicao.codigoTabelaFrete = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("PERPADFRT")) {
				
				regraDistribuicao.percentualPadraoFrete = Util.getBigDecimal(registro.get(key));
				
			} else if (key.equalsIgnoreCase("VLRFRTMIN")) {
				
				regraDistribuicao.valorFreteMinimo = Util.getBigDecimal(registro.get(key));
				
			} else if (key.equalsIgnoreCase("INDRGRVLD")) {
				
				regraDistribuicao.indicadorRegraValida = Util.getInteger(registro.get(key));
				
			} else if (key.equalsIgnoreCase("RAZSOCTRP")) {
				
				regraDistribuicao.razaoSocialTransportadora = registro.get(key);
				
			} else if (key.equalsIgnoreCase("DESRGRDTB")) {
				
				regraDistribuicao.descricaoRegraDistribuiocao = registro.get(key);
			}
		}
		return regraDistribuicao;
	}

}
