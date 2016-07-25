package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

/**
 * Tratamento das Regras de Script do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Script extends Componente {

	private String fileName;
	
	public Script(String fileName) {
		super();
		this.fileName=UtilString.getFullPath()+fileName;
	}

	
	@Override
	public String getCode() {
		String texto = "<script type=\"text/javascript\" language=\"javascript\" src=\""+fileName+"\"></script> ";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
