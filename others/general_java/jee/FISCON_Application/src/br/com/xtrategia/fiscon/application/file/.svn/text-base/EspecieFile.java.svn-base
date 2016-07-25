package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.EspecieDao;
import br.com.xtrategia.fiscon.application.pojo.EspeciePojo;

public class EspecieFile extends FileLoad {

	private EspeciePojo pojo;
	private EspecieDao dao;
	
	public EspecieFile(String path) {
		super(path);
		dao = new EspecieDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new EspeciePojo();
		pojo.setCodigo(linha.substring(0, 1).trim());
		pojo.setNome(linha.substring(1).trim());

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
