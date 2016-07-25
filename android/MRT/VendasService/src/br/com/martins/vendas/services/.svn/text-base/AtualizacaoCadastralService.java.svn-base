package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import br.com.martins.vendas.vo.AtualizacaoCadastral;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.util.DateUtil;

/**
 * Atualizacao Cadastral
 * <p />
 * Arquivo fonte C++ clsRgr.cpp
 * <p />
 * Modulo Sistema Pedidos
 * 
 * @author BRQ Mobile Team
 * @since 23/01/12
 */
public class AtualizacaoCadastralService {

	public static Boolean isRegraValida() throws SQLException {
		final String dataInicioValida = "DATINIVLD";
		final String dataFimValida = "DATFIMVLD";
		final String tabela = "PCACFV";

		Date dataAtual = new Date();

		AtualizacaoCadastral atc = new AtualizacaoCadastral();

		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();

		query.append("SELECT " + dataInicioValida + "," + dataFimValida + " FROM " + tabela);

		Map<String, String> result = db.executeQuery(query.toString()).get(0);

		atc.dataInicioValida = DateUtil.converteStringToDate(result.get(dataInicioValida));
		atc.dataFimValida = DateUtil.converteStringToDate(result.get(dataFimValida));

		if ((dataAtual.compareTo(atc.dataInicioValida) >= 0) && (dataAtual.compareTo(atc.dataFimValida) <= 0)) {
			return true;
		} else {
			return false;
		}
	}
}
