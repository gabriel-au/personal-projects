package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;

/**
 * Tratamento das Regras de ScriptCode do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class ScriptCode extends Componente {

	private String code;
	
	public ScriptCode(String code) {
		super();
		this.code=code;
	}

	
	@Override
	public String getCode() {
		String texto = "<script type=\"application/x-javascript\">";
		texto+="\n";
		texto+=code;
		texto+="\n";
		texto+="</script>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
