package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

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
