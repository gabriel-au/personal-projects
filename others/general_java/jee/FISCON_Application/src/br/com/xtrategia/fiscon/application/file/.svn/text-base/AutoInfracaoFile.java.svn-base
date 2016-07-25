package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.dao.AutoInfracaoDao;

public class AutoInfracaoFile {

	private String path;
	private AutoInfracaoDao dao;
	
	public AutoInfracaoFile(String path) {
		this.path=path;
		dao = new AutoInfracaoDao();
	}
	
	public void iniciarBD() {
		dao.abrir();
		try {
			dao.iniciar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarBD() {
		try {
			dao.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void exportar() {
		LogApplicacao.gravar("Estabelecendo conexão com o Banco");
		iniciarBD();
		
		try {
			LogApplicacao.gravar("Gerando o arquivo");
			dao.executar(path);
		} catch (Exception e) {
			LogApplicacao.gravar("Erro: "+ e.getMessage());
		}
		
		LogApplicacao.gravar("Finalizando conexão com o Banco");
		finalizarBD();
		
	}

}
