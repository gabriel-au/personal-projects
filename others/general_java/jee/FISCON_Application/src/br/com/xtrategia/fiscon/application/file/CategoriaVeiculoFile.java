package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.CategoriaVeiculoDao;
import br.com.xtrategia.fiscon.application.pojo.CategoriaVeiculoPojo;

public class CategoriaVeiculoFile extends FileLoad {

	private CategoriaVeiculoPojo pojo;
	private CategoriaVeiculoDao dao;
	
	public CategoriaVeiculoFile(String path) {
		super(path);
		dao = new CategoriaVeiculoDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new CategoriaVeiculoPojo();
		pojo.setCodigo(linha.substring(0, 2).trim());
		pojo.setNome(linha.substring(2).trim());

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
