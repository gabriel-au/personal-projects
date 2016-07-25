package br.com.martins.vendas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.JustificativaNaoAtendimento;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class JustificativaNaoAtendimentoService {

	private final static String TAG = JustificativaNaoAtendimentoService.class.getName();

	/**
	 * Obtem a lista com todas as justificativas de atendimento cadastradas
	 * 
	 * @return List<JustificativaAtendimento> - lista de justificativas de atendimento
	 */
	public static List<JustificativaNaoAtendimento> listarJustificativas() throws Exception {
		Database db = DatabaseFactory.getInstance();

		List<JustificativaNaoAtendimento> listarJustificativas = new ArrayList<JustificativaNaoAtendimento>();
		JustificativaNaoAtendimento justificativaAtendimento = null;

		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM PCAGPC ORDER BY DESJSTNATD");

		try {

			List<Map<String, String>> result = db.executeQuery(query.toString());

			if (!result.isEmpty()) {
				for (Map<String, String> map : result) {
					justificativaAtendimento = new JustificativaNaoAtendimento();
					justificativaAtendimento.codigoJustificativa = Util.getInteger(map.get("CODJSTNATD"));
					justificativaAtendimento.descricaoJustificativa = map.get("DESJSTNATD");
					listarJustificativas.add(justificativaAtendimento);
				}
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}

		return listarJustificativas;
	}

	/**
	 * Obtem a ultima justificativa de atendimento de um cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @return JustificativaAtendimento - informacoes da ultima justificativa do cliente
	 * @throws Exception - qualquer erro durante o processamento
	 */
	public static JustificativaNaoAtendimento obterUltimaJustificativaCliente(Integer codigoCliente) throws Exception {

		Database db = DatabaseFactory.getInstance();
		JustificativaNaoAtendimento justificativaAtendimento = null;

		StringBuilder query = new StringBuilder();
		query.append("SELECT JUSTIF_CLIENTE.CODCLI, JUSTIF_CLIENTE.CODJSTNATD, JUSTIF_CLIENTE.DATJSTNATD, ");
		query.append("CASE WHEN JUSTIF_CLIENTE.CODJSTNATD = 9999 THEN JUSTIF_CLIENTE.DESJSTNATD ELSE CADASTRO_JUSTIF.DESJSTNATD END AS DESJSTNATD ");
		query.append("FROM PCAJCL JUSTIF_CLIENTE ");
		query.append("INNER JOIN PCAGPC CADASTRO_JUSTIF ON JUSTIF_CLIENTE.CODJSTNATD = CADASTRO_JUSTIF.CODJSTNATD ");
		query.append("WHERE JUSTIF_CLIENTE.CODCLI = " + codigoCliente);

		try {

			List<Map<String, String>> result = db.executeQuery(query.toString());

			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				justificativaAtendimento = new JustificativaNaoAtendimento();
				justificativaAtendimento.codigoCliente = Util.getInteger(map.get("CODCLI"));
				justificativaAtendimento.codigoJustificativa = Util.getInteger(map.get("CODJSTNATD"));
				justificativaAtendimento.descricaoJustificativa = map.get("DESJSTNATD");
				justificativaAtendimento.dataJustificativa = DateUtil.formataData(map.get("DATJSTNATD"), DateUtil.DEFAULT_DATE_DATABASE);
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
		
		return justificativaAtendimento;
	}

	/**
	 * Realiza o cadastro da justificativa de nao atendimento ao cliente
	 * @param justificativaNaoAtendimento - informacoes da justificativa
	 * @return boolean - sucesso ou falha
	 */
	public static boolean cadastrarJustificativaCliente(JustificativaNaoAtendimento justificativaNaoAtendimento) {

		Database db = DatabaseFactory.getInstance();
		boolean sucesso = true;
		try {

			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO PCAJCL (CODCLI, CODJSTNATD, DATJSTNATD, DESJSTNATD) ");
			query.append("VALUES (" + justificativaNaoAtendimento.codigoCliente + ", ");
			query.append(justificativaNaoAtendimento.codigoJustificativa + ", ");
			query.append("'" + DateUtil.formataData(justificativaNaoAtendimento.dataJustificativa, DateUtil.DEFAULT_DATE_DATABASE) + "', ");
			query.append(" '" + justificativaNaoAtendimento.descricaoJustificativa + "' )");

			db.executeSQL(query.toString());

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			sucesso = false;
		}

		return sucesso;
	}

	/**
	 * Realiza a atualizacao do registro de justificativa
	 * @param justificativaNaoAtendimento - informacoes da justificativa
	 * @return boolean - sucesso ou falha
	 */
	public static boolean atualizarJustificativaCliente(JustificativaNaoAtendimento justificativaNaoAtendimento) {

		Database db = DatabaseFactory.getInstance();
		boolean sucesso = true;
		try {

			StringBuilder query = new StringBuilder();
			query.append("UPDATE PCAJCL SET ");
			query.append("CODJSTNATD = " + justificativaNaoAtendimento.codigoJustificativa);
			query.append(", DATJSTNATD = '" + DateUtil.formataData(justificativaNaoAtendimento.dataJustificativa, DateUtil.DEFAULT_DATE_DATABASE) + "' ");
			query.append(", DESJSTNATD = '" + justificativaNaoAtendimento.descricaoJustificativa + "' ");
			query.append("WHERE CODCLI = " + justificativaNaoAtendimento.codigoCliente);

			db.executeSQL(query.toString());

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			sucesso = false;
		}

		return sucesso;
	}

}