package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.InfracaoTipoPojo;

public class InfracaoTipoAction extends CRUDAction<InfracaoTipoPojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected InfracaoTipoPojo iniciarPojo() {
		return new InfracaoTipoPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

	public String consultar(){
		return SUCCESS;
	}
	
	@Override
	public String listar() {
		commandListar="ListarInfracaoTipo";
		commandQuantidadeItens="QuantidadeItensInfracaoTipo";
		return super.listar();
	}
	
}