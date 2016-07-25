package br.com.xtrategia.fiscon.application.file;

import java.sql.SQLException;

import br.com.xtrategia.fiscon.application.FileLoad;
import br.com.xtrategia.fiscon.application.LogApplicacao;
import br.com.xtrategia.fiscon.application.dao.VeiculoDao;
import br.com.xtrategia.fiscon.application.pojo.VeiculoPojo;

public class VeiculoFile extends FileLoad {

	private VeiculoPojo pojo;
	private VeiculoDao dao;
	
	public VeiculoFile(String path) {
		super(path);
		dao = new VeiculoDao();
	}

	@Override
	public void carregar(String linha) {
		pojo = new VeiculoPojo();
		try {
		    pojo.setPlaca(linha.substring(0, 7).trim());
		    
		    pojo.getMarcaModelo().setCodigo(linha.substring(7, 13).trim());
		    pojo.getCor().setCodigo(linha.substring(13, 15).trim());
		    pojo.getTipo().setCodigo(linha.substring(15, 17).trim());
		    pojo.getCategoria().setCodigo(linha.substring(17, 19).trim());
		    pojo.getEspecie().setCodigo(linha.substring(19, 20).trim());
		    pojo.getMunicipio().setCodigo(linha.substring(20, 24).trim());
		    
		    pojo.setAnoFabricacao(Integer.parseInt(linha.substring(24, 28).trim()));
		    pojo.setAnoModelo(Integer.parseInt(linha.substring(28, 32).trim()));

		
			dao.executar(pojo);
		} catch (Exception e) {
			LogApplicacao.gravar("Erro no processamento do registro "+ linha + ": "+ e.getMessage());
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
