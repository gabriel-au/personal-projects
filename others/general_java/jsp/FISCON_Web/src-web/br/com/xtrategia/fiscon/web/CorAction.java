package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.CorPojo;

public class CorAction extends CRUDAction<CorPojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected CorPojo iniciarPojo() {
		return new CorPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

}