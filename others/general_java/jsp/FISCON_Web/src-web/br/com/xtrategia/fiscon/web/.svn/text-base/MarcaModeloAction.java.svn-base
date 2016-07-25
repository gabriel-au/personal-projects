package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.MarcaModeloPojo;

public class MarcaModeloAction extends CRUDAction<MarcaModeloPojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected MarcaModeloPojo iniciarPojo() {
		return new MarcaModeloPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

	@Override
	public String listar() {
		commandListar="ListarMarcaModelo";
		commandQuantidadeItens="QuantidadeItensMarcaModelo";
		return super.listar();
	}
	
	public String consultar(){
		return SUCCESS;
	}
	
}