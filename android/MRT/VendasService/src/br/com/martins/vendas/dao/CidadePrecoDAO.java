package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.vo.CidadePreco;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class CidadePrecoDAO {

	private static final String	TAG	= CidadePrecoDAO.class.getName();

	private static Database		db;

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codCidadePreco
	 * @return
	 * @throws IntegrationException 
	 */
	public static CidadePreco obtemCidadePreco(Integer codigoFilialExpedicao, Integer codCidadePreco) throws IntegrationException {
			
		String query = DatabaseUtil.montaQuery(" SELECT CODFILEPD, CODCIDPCO, NUMRLCCID FROM PCACPC WHERE CODFILEPD = %s AND CODCIDPCO = %s ", codigoFilialExpedicao, codCidadePreco);

		db = DatabaseFactory.getInstance();
		 
		try {
			
			List<Map<String, String>> result = db.executeQuery(query);
			if (result.isEmpty()) {
				return null;
			}
			
			return convertResultToCidadePreco(result);

		} catch (SQLException e) {
			
			Log.e(TAG, e.getMessage());
			
			throw new IntegrationException(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private static CidadePreco convertResultToCidadePreco(List<Map<String, String>> result) {
	
		CidadePreco cidadePreco = null;
		
		for (Map<String, String> registro : result) {
			cidadePreco = new CidadePreco();
			for (String key : registro.keySet()) {
				if (key.equalsIgnoreCase("CODFILEPD")) {
					cidadePreco.codigoFilialExpedicao = Util.getInteger(registro.get(key));
				} else if (key.equalsIgnoreCase("CODCIDPCO")) {
					cidadePreco.codigoCidadePreco = Util.getInteger(registro.get(key));
				} else if (key.equalsIgnoreCase("NUMRLCCID")) {
					cidadePreco.numeroRelacionamentoCliente = Util.getInteger(registro.get(key));
				}
			}
		}

		return cidadePreco;
	}

}
