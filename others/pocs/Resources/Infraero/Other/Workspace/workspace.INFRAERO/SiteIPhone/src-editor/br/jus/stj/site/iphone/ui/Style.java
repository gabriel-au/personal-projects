package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

/**
 * Tratamento das Regras de Style do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Style extends Componente {

	private String cssFile;
	
	public Style(String cssFile) {
		super();
		this.cssFile=UtilString.getFullPath()+cssFile;
	}

	@Override
	public String getCode() {
		String texto = "<style type=\"text/css\" media=\"screen\">@import \""+cssFile+"\";</style>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
