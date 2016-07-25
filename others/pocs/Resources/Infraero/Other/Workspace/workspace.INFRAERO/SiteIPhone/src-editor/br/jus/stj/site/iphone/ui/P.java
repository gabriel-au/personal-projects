package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

/**
 * Tratamento das Regras de P do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class P extends Componente {

	private String conteudo;
	private String clazz;
	
	public P(String conteudo) {
		super();
		this.conteudo=conteudo;
	}
	public P(String conteudo, String clazz) {
		super();
		this.conteudo=conteudo;
		this.clazz=clazz;
	}

	@Override
	public String getCode() {
		String texto = "<p";
		if(clazz!=null)
			texto +=" class=\""+clazz+"\"";
		texto +=">";
		texto += UtilString.converter(conteudo);
		texto += "</p>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(A.class);
		itensOpcionais.add(H1.class);
		itensOpcionais.add(H2.class);
		itensOpcionais.add(Img.class);
	}

}
