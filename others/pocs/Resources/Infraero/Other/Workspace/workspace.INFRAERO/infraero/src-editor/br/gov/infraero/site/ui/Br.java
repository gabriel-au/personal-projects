package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;

/**
 * Tratamento das Regras de A do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Br extends Componente {

	
	public Br() {
		super();
	}

	@Override
	public String getCode() {
		String texto ="<br/>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
