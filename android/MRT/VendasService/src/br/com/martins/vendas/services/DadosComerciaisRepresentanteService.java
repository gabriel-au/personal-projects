package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.Map;

import br.com.martins.vendas.vo.DadosComerciaisRepresentante;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.util.Util;

public class DadosComerciaisRepresentanteService {

	public static DadosComerciaisRepresentante findDadosComerciaisRepresentante() throws SQLException {
		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		query.append("select VLRPRVREP, PERPVTEMP, PERPVTREP, PERTXACDI, PERTXADPZ from PCAREP");
		Map<String, String> result = db.executeQuery(query.toString()).get(0);
		DadosComerciaisRepresentante dadosComerciaisRepresentante = new DadosComerciaisRepresentante();
		dadosComerciaisRepresentante.previsaoSemanal = Double.valueOf(result.get("VLRPRVREP"));
		dadosComerciaisRepresentante.aproveitamentoIdeal = Util.getDouble(result.get("PERPVTEMP"));
		dadosComerciaisRepresentante.aproveitamentoAtual = Util.getDouble(result.get("PERPVTREP"));
		dadosComerciaisRepresentante.pecentualTaxaCDI = Util.getBigDecimal(result.get("PERTXACDI"));
		dadosComerciaisRepresentante.pecentualTaxaDPZ = Util.getBigDecimal(result.get("PERTXADPZ"));
		return dadosComerciaisRepresentante;
	}

}
