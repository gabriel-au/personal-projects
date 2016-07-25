package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

public class Link extends Componente {

	private String rel;
	private String href;
	private String type;
	
	public Link(String rel, String href, String type) {
		super();
		this.rel=rel;
		this.href=UtilString.getFullPath()+href;
		this.type=type;
	}

	@Override
	public String getCode() {
		String texto = "<link ";
		texto +=" rel=\""+rel+"\"";
		texto +=" href=\""+href+"\"";
		texto +=" type=\""+type+"\"";
		texto +=" /> ";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {

	}

}
