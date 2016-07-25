package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;
import br.gov.infraero.site.util.UtilString;

/**
 * Tratamento das Regras de Script do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Option extends Componente {

	private String value;
	private String name;
	private boolean selected;
	
	public Option(String name,String value){
		this.name=name;
		this.value=value;
		this.selected=false;
	}
	
	public Option(String name,String value, boolean selected){
		this.name=name;
		this.value=value;
		this.selected=selected;
	}

	
	@Override
	public String getCode() {
		String texto = "<option ";
		texto+=" value=\""+name+"\"";
		if(selected)
			texto+=" SELECTED";
		texto+=" >";
		texto+= UtilString.converter(value)+"</option>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

}
