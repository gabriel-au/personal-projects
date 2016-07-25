package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.LivroPrecoGrupamentoCliente;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class LivroPrecoGrupamentoClienteDAO {

	private static final String TAG = LivroPrecoGrupamentoClienteDAO.class.getName();

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codCidadePreco
	 * @return
	 */
	public static LivroPrecoGrupamentoCliente obtemLivroPrecoGrupamento(
			Integer codigoFilialExpedicao, Integer codFilialFaturamento,
			String codigoEstadoDestino, Integer codigoGrupamentoCliente,
			Integer codigoMercadoria) throws Exception {
		StringBuilder queryBuilder;
		String query;
		List<Map<String, String>> result;
		Database db;
		try {
			queryBuilder = new StringBuilder();
			queryBuilder.append(" select * from PCALGC")
			.append(" WHERE CODFILEPD = %s ")
			.append(" AND CODFILFAT = %s ")
			.append(" AND CODESTUNI = '%s' ")
			.append(" AND CODGRPCLI = %s ")
			.append(" AND CODMER = %s ")
			.append(" and STRFTIME('%%Y%%m%%d', DATETIME('NOW')) between DATINIVLD and DATFIMVLD ");
			query = DatabaseUtil.montaQuery(queryBuilder.toString(),
					codigoFilialExpedicao, codFilialFaturamento,
					codigoEstadoDestino, codigoGrupamentoCliente,
					codigoMercadoria);
			db = DatabaseFactory.getInstance();
			result = db.executeQuery(query);
			List<LivroPrecoGrupamentoCliente> listaLivroPrecoGrupamentoCliente = carregarListaLivroPrecoGrupamento(result);
			return !listaLivroPrecoGrupamentoCliente.isEmpty() ? listaLivroPrecoGrupamentoCliente.get(0) : null;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	private static List<LivroPrecoGrupamentoCliente> carregarListaLivroPrecoGrupamento(List<Map<String, String>> resultQuery) throws Exception {
		Log.e(TAG, "iniciando metodo carregarListaLivroPrecoGrupamento()");
		LivroPrecoGrupamentoCliente livroPrecoGrupamentoCliente;
		List<LivroPrecoGrupamentoCliente> listaLivroPrecoGrupamentoCliente = new ArrayList<LivroPrecoGrupamentoCliente>();
		for (Map<String,String> livroPrecoGrupamentoClienteAux : resultQuery) {
			livroPrecoGrupamentoCliente = new LivroPrecoGrupamentoCliente();
			livroPrecoGrupamentoCliente.codFilialExpedicao = Util.getInteger(livroPrecoGrupamentoClienteAux.get("CODFILEPD"));
			livroPrecoGrupamentoCliente.codFilialFaturamento = Util.getInteger(livroPrecoGrupamentoClienteAux.get("CODFILFAT"));
			livroPrecoGrupamentoCliente.codigoEstadoDestino = Util.getInteger(livroPrecoGrupamentoClienteAux.get("CODESTUNI"));
			livroPrecoGrupamentoCliente.codigoGrupamentoCliente = Util.getInteger(livroPrecoGrupamentoClienteAux.get("CODGRPCLI"));
			livroPrecoGrupamentoCliente.codigoMercadoria = Util.getInteger(livroPrecoGrupamentoClienteAux.get("CODMER"));
			livroPrecoGrupamentoCliente.valorUnitarioBruto = Util.getBigDecimal(livroPrecoGrupamentoClienteAux.get("CODCNL"));
			livroPrecoGrupamentoCliente.dataInicioValidade = DateUtil.formataData(livroPrecoGrupamentoClienteAux.get("DATFIMVLD"), DateUtil.DEFAULT_DATE_DATABASE);
			livroPrecoGrupamentoCliente.dataFimValidade = DateUtil.formataData(livroPrecoGrupamentoClienteAux.get("DATFIMVLD"), DateUtil.DEFAULT_DATE_DATABASE);
			livroPrecoGrupamentoCliente.flagUtilizaFlexivel = Util.getBoolean(livroPrecoGrupamentoClienteAux.get("INDUTZFLX"));
			livroPrecoGrupamentoCliente.flagUtilizaBeneficio = Util.getBoolean(livroPrecoGrupamentoClienteAux.get("INDUTZBFC"));
			livroPrecoGrupamentoCliente.flagUtilizaMinimo = Util.getBoolean(livroPrecoGrupamentoClienteAux.get("INDUTZMNM"));
			livroPrecoGrupamentoCliente.flagUtilizaFracao = Util.getBoolean(livroPrecoGrupamentoClienteAux.get("INDUTZFRC"));
			listaLivroPrecoGrupamentoCliente.add(livroPrecoGrupamentoCliente);
		}
		return listaLivroPrecoGrupamentoCliente;
	}
}
