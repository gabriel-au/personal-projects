package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;

/**
 * Tratamento das Regras de CodigoJava do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class CodigoJava extends Componente {

	private String fonte;
	
	public CodigoJava(String fonte) {
		super();
		this.fonte=fonte;
	}

	@Override
	public String getCode() {
		String texto = "<%";
		texto += "\n";
		texto+=fonte;
		texto += "\n";
		texto += "%>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
