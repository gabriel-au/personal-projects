package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

/**
 * @a
 */
public class Img extends Componente {

	private String src;
	private String title;
	
	public Img(String src) {
		super();
		this.src= UtilString.getFullPath()+ src;
	}
	public Img(String src,String title) {
		super();
		this.src=src;
		this.title=title;
	}

	@Override
	public String getCode() {
		String texto = "<img src=\""+src+"\"";
		if(title!=null)
			texto+=" title=\""+title+"\"";
		texto += " />";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
