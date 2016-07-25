package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.RestricaoPojo;

public class RestricaoAction extends CRUDAction<RestricaoPojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected RestricaoPojo iniciarPojo() {
		return new RestricaoPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

}