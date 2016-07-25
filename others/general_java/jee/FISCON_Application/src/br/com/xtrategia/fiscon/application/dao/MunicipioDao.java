package br.com.xtrategia.fiscon.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import br.com.xtrategia.fiscon.application.ConexaoBanco;
import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.pojo.MunicipioPojo;

public class MunicipioDao extends ConexaoBanco<MunicipioPojo> {

	@Override
	public void iniciar() throws SQLException {
		//Inativar todos os registros
		LogApplicacao.gravar("Processo de Inativar os registros");
		String sql="update municipio set ativo= false where ativo=true";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}
	
	@Override
	public void executar(MunicipioPojo pojo)throws SQLException {
		//consultar se existe
		pstmConsulta.setString(1, pojo.getCodigo());
		ResultSet rs = pstmConsulta.executeQuery();
		//se existir
		if(rs.next()){
			MunicipioPojo pojoOld = new MunicipioPojo();
			pojoOld.setId(rs.getInt(1));
			pojoOld.setAtivo(rs.getBoolean(2));
			pojoOld.setDataModificacao(rs.getTimestamp(3));
			pojoOld.setCodigo(rs.getString(4));
			pojoOld.setNome(rs.getString(5));
			pojoOld.setUf(rs.getString(6));
			
			//verificar se é diferente e gravar o historio
			if(!pojoOld.equals(pojo)){
				pstmInsertHistorico.setInt(1, pojoOld.getId());
				pstmInsertHistorico.setBoolean(2, pojoOld.isAtivo());
				pstmInsertHistorico.setTimestamp(3, pojoOld.getDataModificacao());
				pstmInsertHistorico.setString(4, pojoOld.getCodigo());
				pstmInsertHistorico.setString(5, pojoOld.getNome());
				pstmInsertHistorico.setString(6, pojoOld.getUf());
				pstmInsertHistorico.execute();
			}
			//atualiza o registro para ficar ativo novamente
			pstmUpdate.setBoolean(1, true);
			pstmUpdate.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmUpdate.setString(3, pojo.getNome());
			pstmUpdate.setString(4, pojo.getUf());
			pstmUpdate.setInt(5, pojoOld.getId());
			pstmUpdate.execute();
		}else{
			//se não existir, inserir
			pstmInsert.setBoolean(1, true);
			pstmInsert.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmInsert.setString(3, pojo.getCodigo());
			pstmInsert.setString(4, pojo.getNome());
			pstmInsert.setString(5, pojo.getUf());
			pstmInsert.execute();
		}
			
		
	}

	@Override
	public String getSqlConsulta() {
		return "Select id,ativo,data_modificacao,codigo,nome,uf from municipio where codigo = ?";
	}

	@Override
	public String getSqlInsert() {
		return "insert into municipio (ativo,data_modificacao,codigo,nome,uf)"
				+ "values (?,?,?,?,?)";

	}

	@Override
	public String getSqlInsertHistorico() {
		return "insert into historico_municipio (id,ativo,data_modificacao,codigo,nome,uf)"
				+ "values (?,?,?,?,?,?)";
	}

	@Override
	public String getSqlUpdate() {
		return "update municipio set " +
				"ativo=?, data_modificacao=?, nome=?, uf=? " +
				"where id = ?";
	}


}
