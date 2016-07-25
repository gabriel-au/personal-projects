package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.MarcaModeloDao;
import br.com.xtrategia.fiscon.application.pojo.MarcaModeloPojo;

public class MarcaModeloFile extends FileLoad {

	private MarcaModeloPojo pojo;
	private MarcaModeloDao dao;
	
	public MarcaModeloFile(String path) {
		super(path);
		dao = new MarcaModeloDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new MarcaModeloPojo();
		pojo.setCodigo(linha.substring(0, 6).trim());
		pojo.setNome(linha.substring(6).trim());

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
