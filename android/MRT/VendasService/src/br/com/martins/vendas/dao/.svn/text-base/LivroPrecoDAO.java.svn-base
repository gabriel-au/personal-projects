package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.martins.vendas.vo.LivroPreco;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;

public class LivroPrecoDAO {

	private static final String	TAG	= LivroPrecoDAO.class.getName();

	/**
	 * Recupera dados de livro de preço
	 * 
	 * @param codFilialExp
	 *            código filial expedição
	 * @param codFilialFat
	 *            código filial faturamento
	 * @param numRelacaoCidade
	 *            numero relação cidade
	 * @param codMercadoria
	 *            código da mercadoria
	 * @return instância da classe LivroPreco
	 */
	public static LivroPreco recupera(Integer codFilialExp, Integer codFilialFat, Integer numRelacaoCidade, Integer codMercadoria) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODFILEPD,     ");
		sql.append("        CODFILFAT,     ");
		sql.append("        NUMRLCCID,     ");
		sql.append("        CODMER,        ");
		sql.append("        TIPICTMER,     ");
		sql.append("        CODSMBSIT,     ");
		sql.append("        CODFLXPCO,     ");
		sql.append("        FLGMEREXV,     ");
		sql.append("        INDMERMTD,     ");
		sql.append("        CODTBTICM,     ");
		sql.append("        VLRUNTBRT,     ");
		sql.append("        FLGPEE,        ");
		sql.append("        TIPMCOREP,     ");
		sql.append("        CSTCSTLGT,     ");
		sql.append("        PERTBTMER,     ");
		sql.append("        PERCMSMER      ");
		sql.append("   FROM PCALVR         ");
		sql.append("  WHERE CODFILEPD = ?  ");
		sql.append("    AND CODFILFAT = ?  ");
		sql.append("    AND NUMRLCCID = ?  ");
		sql.append("    AND CODMER    = ?  ");
		try {
			Database dataBase = DatabaseFactory.getInstance();
			
			String[] parameters = new String[4];
			parameters[0] = Util.getStringValue(codFilialExp);
			parameters[1] = Util.getStringValue(codFilialFat);
			parameters[2] = Util.getStringValue(numRelacaoCidade);
			parameters[3] = Util.getStringValue(codMercadoria);
			
			List<Map<String, String>> result = dataBase.executeQuery(sql.toString(), parameters);
			List<LivroPreco> listaLivroPreco = criaListaLivroPreco(result);
			return !listaLivroPreco.isEmpty() ? listaLivroPreco.get(0) : null;
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
	
	
	private static List<LivroPreco> criaListaLivroPreco(List<Map<String, String>> livroPrecoMap) {
		
		LivroPreco livroPreco;
		List<LivroPreco> listaLivroPreco;
		
		listaLivroPreco = new ArrayList<LivroPreco>();
		for(Map<String, String> livroPrecoAux : livroPrecoMap){
			livroPreco = new LivroPreco();
			livroPreco.codFilialExpedicao = Util.getInteger(livroPrecoAux.get("CODFILEPD"));
			livroPreco.codFilialFaturamento = Util.getInteger(livroPrecoAux.get("CODFILFAT"));
			livroPreco.numRelacaoCidade = Util.getInteger(livroPrecoAux.get("NUMRLCCID"));
			livroPreco.codMercadoria = Util.getInteger(livroPrecoAux.get("CODMER"));
			livroPreco.tipoIncentivoMercadoria = livroPrecoAux.get("TIPICTMER");
			livroPreco.codSimboloSituacao = livroPrecoAux.get("CODSMBSIT");
			livroPreco.codFlexivelPreco = livroPrecoAux.get("CODFLXPCO");
			livroPreco.flgMercadoriaExclusivo = livroPrecoAux.get("FLGMEREXV");
			livroPreco.indMercadoriaMonitorada = livroPrecoAux.get("INDMERMTD");
			livroPreco.codTributacaoICM = Util.getInteger(livroPrecoAux.get("CODTBTICM"));
			livroPreco.valorUnitarioBruto = Util.getBigDecimal(livroPrecoAux.get("VLRUNTBRT"));
			livroPreco.flgPontoEncontroEletronico = livroPrecoAux.get("FLGPEE");
			livroPreco.tipMarcacaoRepresentante = livroPrecoAux.get("TIPMCOREP");
			livroPreco.cstCustoLogistica = Util.getBigDecimal(livroPrecoAux.get("CSTCSTLGT"));
			livroPreco.perTributacaoMercadoria = Util.getBigDecimal(livroPrecoAux.get("PERTBTMER"));
			livroPreco.perComissaoMercadoria = Util.getBigDecimal(livroPrecoAux.get("PERCMSMER"));
			listaLivroPreco.add(livroPreco);
		}
		return listaLivroPreco;
 	}

	/**
	 * 
	 * @param transactionManager
	 * @param codFilialExp
	 * @param codFilialFat
	 * @param numRelacaoCidade
	 * @param codMercadoria
	 * @throws SQLException
	 */
	public static void atualizaPEE(TransactionManager transactionManager, Integer codFilialExp, Integer codFilialFat, Integer numRelacaoCidade, Integer codMercadoria, String flagPee)
			throws SQLException {

		try {

			Map<String, String> values = new TreeMap<String, String>();
			values.put("FLGPEE", flagPee);

			String whereSQL = "CODFILEPD = ? AND CODFILFAT = ? AND NUMRLCCID = ? AND CODMER = ?";
			String[] whereValues = new String[] { codFilialExp.toString(), codFilialFat.toString(), numRelacaoCidade.toString(), codMercadoria.toString() };

			int updated = transactionManager.update("PCALVR", values, whereSQL, whereValues);
			Log.i(TAG, String.format("atualizaPEE:%s registros afetados", updated));

		} catch (SQLException e) {
			Log.e(TAG, e.getLocalizedMessage());
		}
	}

	/**
	 * 
	 * @param codFilialExp
	 * @param codFilialFat
	 * @param numRelacaoCidade
	 * @param codMercadoria
	 * @throws SQLException
	 */
	public static void atualizaMarcacaoRepresentante(Integer codFilialExp, Integer codFilialFat, Integer numRelacaoCidade, Integer codMercadoria, Integer tipMarcacaoRepresentante) throws SQLException {

		TransactionManager transactionManager = null;
		try {

			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();

			Map<String, String> values = new TreeMap<String, String>();
			values.put("TIPMCOREP", String.valueOf(tipMarcacaoRepresentante));
			
			
			String whereSQLTemp =  "CODFILEPD = ? AND CODFILFAT = ?  AND CODMER = ?";
			String[] whereValuesTempPedido = new String[] { codFilialExp.toString(), codFilialFat.toString(), codMercadoria.toString() };
			
			int updatedTempPedido = transactionManager.update("TMPGRPDE", values, whereSQLTemp, whereValuesTempPedido);
			
			String whereSQL = "CODFILEPD = ? AND CODFILFAT = ? AND NUMRLCCID = ? AND CODMER = ?";
			String[] whereValues = new String[] { codFilialExp.toString(), codFilialFat.toString(), numRelacaoCidade.toString(), codMercadoria.toString() };

			int updated = transactionManager.update("PCALVR", values, whereSQL, whereValues);
			Log.i(TAG, String.format("atualizaMarcacaoRepresentante:%s , atualizaMarcacaoRepresentante tabela temporaria :%s registros afetados", updated, updatedTempPedido));

			transactionManager.commitTransaction();

		} catch (SQLException e) {

			transactionManager.rollbackTransaction();
			Log.e(TAG, e.getLocalizedMessage());

		} finally {
			transactionManager.endTransaction();
		}
	}
}
