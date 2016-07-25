package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.RestricaoDao;
import br.com.xtrategia.fiscon.application.pojo.RestricaoPojo;

public class RestricaoFile extends FileLoad {

	private RestricaoPojo pojo;
	private RestricaoDao dao;
	
	public RestricaoFile(String path) {
		super(path);
		dao = new RestricaoDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new RestricaoPojo();
		pojo.setPlaca(linha.substring(0, 7).trim());
		pojo.setCodigo(linha.substring(7).trim());

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
