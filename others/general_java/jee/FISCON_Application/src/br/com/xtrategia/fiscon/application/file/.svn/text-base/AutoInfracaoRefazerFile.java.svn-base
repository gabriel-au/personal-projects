package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.dao.AutoInfracaoRefazerDao;

public class AutoInfracaoRefazerFile {

	private String path;
	private String chave;
	private AutoInfracaoRefazerDao dao;
	
	public AutoInfracaoRefazerFile(String path, String chave) {
		this.path=path;
		this.chave=chave;
		dao = new AutoInfracaoRefazerDao();
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
			dao.executar(path, chave);
		} catch (Exception e) {
			LogApplicacao.gravar("Erro: "+ e.getMessage());
		}
		
		LogApplicacao.gravar("Finalizando conexão com o Banco");
		finalizarBD();
		
	}

}
