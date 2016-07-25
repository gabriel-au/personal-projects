package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;

public class MunicipioAction extends CRUDAction<MunicipioPojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected MunicipioPojo iniciarPojo() {
		return new MunicipioPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

	@Override
	public String listar() {
		commandListar="ListarMunicipio";
		commandQuantidadeItens="QuantidadeItensMunicipio";
		return super.listar();
	}
	
	public String consultar(){
		return SUCCESS;
	}
	
}