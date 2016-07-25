package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

public class H1 extends Componente {

	private String conteudo;
	private String style;
	
	public H1(String conteudo) {
		super();
		this.conteudo=conteudo;
	}
	public H1(String conteudo,String style) {
		super();
		this.conteudo=conteudo;
		this.style=style;
	}

	@Override
	public String getCode() {
		String texto = "<h1";
		if(style!=null)
			texto +=" style=\""+style+"\"";
		texto +=">";
		texto += UtilString.converter(conteudo);
		texto += "</h1>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
