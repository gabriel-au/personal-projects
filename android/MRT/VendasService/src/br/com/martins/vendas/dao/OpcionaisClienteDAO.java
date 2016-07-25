package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class OpcionaisClienteDAO {

	private static final String	TAG	= OpcionaisClienteDAO.class.getName();

	/**
	 * 
	 * @param codCliente
	 * @param codFilialExpedicao
	 * @param codFilialFaturamento
	 * @return
	 * @throws SQLException
	 */
	public static Integer recuperaCodigoModeloDistribuicao(Integer codCliente, Integer codFilialExpedicao, Integer codFilialFaturamento) throws SQLException {

		String sql = "SELECT TIPDTBFIL FROM PCACOP COP WHERE COP.CODCLI = %s AND COP.CODFILEPD = %s AND COP.CODFILFAT";

		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery(sql, codCliente, codFilialExpedicao, codFilialFaturamento));
			if (!result.isEmpty()) {
				return Util.getInteger(result.get(0).get("TIPDTBFIL"));
			}
			
		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());
			throw e;

		}

		return null;
	}

}
