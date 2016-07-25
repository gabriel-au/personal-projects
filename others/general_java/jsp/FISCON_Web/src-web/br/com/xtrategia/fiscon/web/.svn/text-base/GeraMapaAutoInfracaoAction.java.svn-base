package br.com.xtrategia.fiscon.web;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoExtendidoPojo;

public class GeraMapaAutoInfracaoAction extends CRUDAction<AutoInfracaoExtendidoPojo> {

	private static final long serialVersionUID = 1L;

	
	@Override
	protected AutoInfracaoExtendidoPojo iniciarPojo() {
		return new AutoInfracaoExtendidoPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}
	
	
	public String gerarMapaInfracao(){
		try {
		setPojo((AutoInfracaoExtendidoPojo)getFachada().execute(getPojo(), "GerarMapaInfracao"));
			return SUCCESS;
		} catch (FISCONException e) {
			e.printStackTrace();
			setMensagem("Erro na criação do mapa.");
			return ERROR;
		}
		
		
	}
}
