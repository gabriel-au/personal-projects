package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;

/**
 * Tratamento das Regras de Script do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Select extends Componente {

	private String clazz;
	private String name;
	private String onchange;
	private String style;
	
	public Select(String clazz, String name) {
		super();
		this.clazz=clazz;
		this.name=name;
	}

	
	
	@Override
	public String getCode() {
		String texto = "<select";
		texto+=" class=\""+clazz+"\"";
		if (getStyle() != null) {
			texto += " style=\""+getStyle()+"\"";				
		}
		texto+=" name=\""+name+"\"";
		if(onchange!=null)
			texto+=" onchange=\""+onchange+"\"";
		texto+=">";
		texto+=getCodeChild();
		texto+="</select>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensObrigatorio.add(Option.class);
	}



	public String getStyle() {
		return style;
	}



	public void setStyle(String style) {
		this.style = style;
	}



	public String getClazz() {
		return clazz;
	}



	public void setClazz(String clazz) {
		this.clazz = clazz;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getOnchange() {
		return onchange;
	}



	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

}
