package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.RestricaoTipoPojo;

public class RestricaoTipoAction extends CRUDAction<RestricaoTipoPojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected RestricaoTipoPojo iniciarPojo() {
		return new RestricaoTipoPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

}