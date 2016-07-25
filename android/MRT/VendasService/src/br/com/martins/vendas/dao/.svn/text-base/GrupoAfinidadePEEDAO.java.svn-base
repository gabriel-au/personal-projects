package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import br.com.martins.vendas.vo.GrupoAfinidadePEE;

import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;

public class GrupoAfinidadePEEDAO {

	private static final String	TAG	= GrupoAfinidadePEEDAO.class.getName();

	/**
	 * 
	 * @param transactionManager
	 * @param pee
	 * @return
	 * @throws Exception
	 */
	public static void insereOuAtualiza(TransactionManager transactionManager, GrupoAfinidadePEE pee) throws SQLException {
		try {

			final String tableName = "PCARAF";

			Map<String, String> values = new TreeMap<String, String>();
			values.put("CODMER", pee.codMercadoria.toString());
			values.put("CODFILEPD", pee.codFilialExpedicao.toString());
			values.put("CODFILFAT", pee.codFilialFaturamento.toString());
			values.put("NUMRLCCID", pee.numRelacaoCidade.toString());
			values.put("CODGRPAFD", pee.codGrupoAfinidade.toString());
			values.put("FLGTRN", pee.flgTransferencia);
			values.put("FLGATU", pee.flgAtualizacao);
			values.put("DATPEE", pee.datPontoEncontroEletronico);
			values.put("IDTINFPEE", pee.idtInformcaoPEE);
			values.put("DATVALAFD", pee.datValidadeAfinidade);

			String whereClause = "CODMER = ? AND CODFILEPD = ? AND CODFILFAT = ? AND NUMRLCCID = ? AND IDTINFPEE = ?";
			String[] whereValues = new String[] { pee.codMercadoria.toString(), pee.codFilialExpedicao.toString(), pee.codFilialFaturamento.toString(), pee.numRelacaoCidade.toString(),
					pee.idtInformcaoPEE };

			int updated = transactionManager.update(tableName, values, whereClause, whereValues);
			Log.i(TAG, String.format("insereOuAtualiza:%s registros afetados", updated));
			
			if (updated == 0) {
				transactionManager.insert(tableName, values);
			}

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());
			throw e;
		}
	}

	/**
	 * 
	 * @param transactionManager
	 * @param pee
	 * @throws SQLException 
	 */
	public static void deleta(TransactionManager transactionManager, GrupoAfinidadePEE pee) throws SQLException {

		try {

			String whereClause = "CODMER = ? AND CODFILEPD = ? AND CODFILFAT = ? AND NUMRLCCID = ? AND IDTINFPEE = ?";
			String[] whereValues = new String[] { pee.codMercadoria.toString(), pee.codFilialExpedicao.toString(), pee.codFilialFaturamento.toString(), pee.numRelacaoCidade.toString(), pee.idtInformcaoPEE };

			int deleted = transactionManager.delete("PCARAF", whereClause, whereValues);
			Log.i(TAG, String.format("deleta:%s registros afetados", deleted));
			
		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());
			throw e;

		}
	}
}
