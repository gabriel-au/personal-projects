package br.com.xtrategia.fiscon.application.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.xtrategia.fiscon.application.ConexaoBanco;
import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.pojo.RestricaoPojo;

public class RestricaoDao extends ConexaoBanco<RestricaoPojo> {

	@Override
	public void iniciar() throws SQLException {
		// Inativar todos os registros
		LogApplicacao.gravar("Processo de Inativar os registros");
		String sql = "delete from restricao";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}

	@Override
	public void fechar() throws SQLException {
		String sql;
		Statement stmt;
		// executar a query para ataulizar os id
		sql = "update restricao  set id_veiculo = " +
				"(select v.id from veiculo v where veiculo_placa = placa)";
		stmt = conn.createStatement();
		stmt.execute(sql);
		sql = "update restricao set id_restricao_tipo = " +
				"(select tr.id from restricao_tipo tr where tr.codigo = codigo_tipo_restricao)";
		stmt = conn.createStatement();
		stmt.execute(sql);
		super.fechar();
	}

	@Override
	public void executar(RestricaoPojo pojo) throws SQLException {
		// não precisa fazer consultas, pois todos os dados foram apagados
		// inserir
		pstmInsert.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		pstmInsert.setString(2, pojo.getPlaca());
		pstmInsert.setString(3, pojo.getCodigo());
		pstmInsert.execute();

	}

	@Override
	public String getSqlConsulta() {
		return null;
	}

	@Override
	public String getSqlInsert() {
		return "insert into restricao (data_inclusao,placa,codigo_tipo_restricao)"
				+ "values (?,?,?)";

	}

	@Override
	public String getSqlInsertHistorico() {
		return null;
	}

	@Override
	public String getSqlUpdate() {
		return null;
	}

}
