package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

public class Head extends Componente {

	private String titulo;
	
	public Head(String titulo) {
		super();
		this.titulo=titulo;
	}

	@Override
	public String getCode() {
		String texto = "<head>";
		texto += "\n";
		texto += "<title>"+UtilString.converter(titulo)+"</title>";
		texto += getCodeChild();
		texto += "\n";
		texto += "</head>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(Style.class);
		itensOpcionais.add(Meta.class);
		itensOpcionais.add(ImageLink.class);
		itensOpcionais.add(Link.class);
		itensOpcionais.add(Script.class);
		itensOpcionais.add(ScriptCode.class);

	}

}
