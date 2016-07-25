package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

/**
 * Tratamento das Regras de Texto do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Texto extends Componente {

	private String conteudo;
	
	public Texto(String conteudo) {
		super();
		this.conteudo=UtilString.converter(conteudo,false,false);;
	}

	public Texto(String conteudo, boolean contemQuebraLinha) {
		super();
		this.conteudo=UtilString.converter(conteudo,contemQuebraLinha,false);
	}
	public Texto(String conteudo, boolean contemQuebraLinha, boolean contemTagHtml) {
		super();
		this.conteudo=UtilString.converter(conteudo,contemQuebraLinha,contemTagHtml);
	}
	
	@Override
	public String getCode() {
		return conteudo;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
