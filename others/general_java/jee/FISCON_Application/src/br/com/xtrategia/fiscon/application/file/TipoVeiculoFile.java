package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.dao.TipoVeiculoDao;
import br.com.xtrategia.fiscon.application.pojo.TipoVeiculoPojo;

public class TipoVeiculoFile extends FileLoad {

	private TipoVeiculoPojo pojo;
	private TipoVeiculoDao dao;
	
	public TipoVeiculoFile(String path) {
		super(path);
		dao = new TipoVeiculoDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new TipoVeiculoPojo();
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
