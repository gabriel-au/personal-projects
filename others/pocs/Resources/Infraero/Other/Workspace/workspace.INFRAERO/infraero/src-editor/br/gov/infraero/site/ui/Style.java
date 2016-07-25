package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

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
