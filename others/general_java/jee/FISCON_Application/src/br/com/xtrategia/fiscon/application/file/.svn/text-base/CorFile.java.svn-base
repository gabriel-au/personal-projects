package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.CorDao;
import br.com.xtrategia.fiscon.application.pojo.CorPojo;

public class CorFile extends FileLoad {

	private CorPojo pojo;
	private CorDao dao;
	
	public CorFile(String path) {
		super(path);
		dao = new CorDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new CorPojo();
		pojo.setCodigo(linha.substring(1, 3).trim());
		pojo.setNome(linha.substring(3).trim());

		try {
			dao.executar(pojo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void iniciarBD() {
		dao.abrir();
		try {
			dao.iniciar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void finalizarBD() {
		try {
			dao.fechar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




}
