package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;

/**
 * Tratamento das Regras de Ul do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Ul extends Componente {

	
	public Ul() {
		super();
	}

	@Override
	public String getCode() {
		String texto = "<ul>";
		texto += "\n";
		texto += getCodeChild();
		texto += "\n";
		texto += "</ul>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensObrigatorio.add(Li.class);
	}

}
