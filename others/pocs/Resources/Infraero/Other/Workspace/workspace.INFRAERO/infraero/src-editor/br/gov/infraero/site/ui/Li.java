package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

/**
 * Tratamento das Regras de Li dos HTMLS
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Li extends Componente {

	private String conteudo;
	
	public Li(String conteudo) {
		super();
		this.conteudo=conteudo;
	}

	@Override
	public String getCode() {
		String texto = "<li>";
		texto += UtilString.converter(conteudo);
		texto += "</li>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(A.class);
		itensOpcionais.add(P.class);
	}

}
