package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

public class H2 extends Componente {

	private String conteudo;
	
	public H2(String conteudo) {
		super();
		this.conteudo=conteudo;
	}

	@Override
	public String getCode() {
		String texto = "<h2>";
		texto += UtilString.converter(conteudo);
		texto += "</h2>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(A.class);
	}

}
