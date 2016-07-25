package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.web.pojo.EspeciePojo;

public class EspecieAction extends CRUDAction<EspeciePojo> {

	private static final long serialVersionUID = 1L;

	@Override
	protected EspeciePojo iniciarPojo() {
		return new EspeciePojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

}