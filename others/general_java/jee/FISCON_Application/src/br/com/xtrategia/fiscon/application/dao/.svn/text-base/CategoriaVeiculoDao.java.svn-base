package br.com.xtrategia.fiscon.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.xtrategia.fiscon.application.ConexaoBanco;
import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.pojo.CategoriaVeiculoPojo;

public class CategoriaVeiculoDao extends ConexaoBanco<CategoriaVeiculoPojo> {

	@Override
	public void iniciar() throws SQLException {
		//Inativar todos os registros
		LogApplicacao.gravar("Processo de Inativar os registros");
		String sql="update categoria set ativo= false where ativo=true";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
	
	@Override
	public void executar(CategoriaVeiculoPojo pojo)throws SQLException {
		//consultar se existe
		pstmConsulta.setString(1, pojo.getCodigo());
		ResultSet rs = pstmConsulta.executeQuery();
		//se existir
		if(rs.next()){
			CategoriaVeiculoPojo pojoOld = new CategoriaVeiculoPojo();
			pojoOld.setId(rs.getInt(1));
			pojoOld.setAtivo(rs.getBoolean(2));
			pojoOld.setDataModificacao(rs.getTimestamp(3));
			pojoOld.setCodigo(rs.getString(4));
			pojoOld.setNome(rs.getString(5));
			
			//verificar se é diferente e gravar o historio
			if(!pojoOld.equals(pojo)){
				pstmInsertHistorico.setInt(1, pojoOld.getId());
				pstmInsertHistorico.setBoolean(2, pojoOld.isAtivo());
				pstmInsertHistorico.setTimestamp(3, pojoOld.getDataModificacao());
				pstmInsertHistorico.setString(4, pojoOld.getCodigo());
				pstmInsertHistorico.setString(5, pojoOld.getNome());
				pstmInsertHistorico.execute();
			}
			//atualiza o registro para ficar ativo novamente
			pstmUpdate.setBoolean(1, true);
			pstmUpdate.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmUpdate.setString(3, pojo.getNome());
			pstmUpdate.setInt(4, pojoOld.getId());
			pstmUpdate.execute();
		}else{
			//se não existir, inserir
			pstmInsert.setBoolean(1, true);
			pstmInsert.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmInsert.setString(3, pojo.getCodigo());
			pstmInsert.setString(4, pojo.getNome());
			pstmInsert.execute();
		}
			
		
	}

	@Override
	public String getSqlConsulta() {
		return "Select id,ativo,data_modificacao,codigo,nome from categoria where codigo = ?";
	}

	@Override
	public String getSqlInsert() {
		return "insert into categoria (ativo,data_modificacao,codigo,nome)"
				+ "values (?,?,?,?)";

	}

	@Override
	public String getSqlInsertHistorico() {
		return "insert into historico_categoria (id,ativo,data_modificacao,codigo,nome)"
				+ "values (?,?,?,?,?)";
	}

	@Override
	public String getSqlUpdate() {
		return "update categoria set " +
				"ativo=?, data_modificacao=?, nome=? " +
				"where id = ?";
	}


}
