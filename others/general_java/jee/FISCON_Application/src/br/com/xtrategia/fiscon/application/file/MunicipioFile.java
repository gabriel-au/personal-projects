package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.MunicipioDao;
import br.com.xtrategia.fiscon.application.pojo.MunicipioPojo;

public class MunicipioFile extends FileLoad {

	private MunicipioPojo pojo;
	private MunicipioDao dao;
	
	public MunicipioFile(String path) {
		super(path);
		dao = new MunicipioDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new MunicipioPojo();
		pojo.setNome(linha.substring(0,40).trim());
		pojo.setUf(linha.substring(40, 42).trim());
		pojo.setCodigo(linha.substring(42).trim());

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
