package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

/**
 * Tratamento das Regras de Commentary do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Commentary extends Componente {

	private String text;
	
	public Commentary(String text) {
		super();
		this.text=text;
	}

	@Override
	public String getCode() {
		String texto = "<!-- "+UtilString.converter(text)+" -->";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
