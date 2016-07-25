package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;

/**
 * Tratamento das Regras de Input do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Input extends Componente {

	public static final String INPUT_TEXT ="text";
	public static final String INPUT_BUTTON ="button";
	public static final String INPUT_SUBMIT="submit";
	public static final String INPUT_PASSWORD="password";
	public static final String INPUT_HIDDEN="hidden";
	public static final String INPUT_RADIO="radio";

	private String type;
	private String name;
	private String id;
	private String value;
	private String clazz;
	private String size;
	
	private boolean selecionado;
	
	public Input(String type, String name) {
		super();
		this.type=type;
		this.name=name;
	}
	public Input(String type, String name, String value) {
		super();
		this.type=type;
		this.name=name;
		this.value=value;
	}
	public Input(String type, String name, String clazz, String value, String size) {
		super();
		this.type=type;
		this.name=name;
		this.clazz=clazz;
		this.value=value;
		this.size=size;
	}
	public Input(String type, String name, String clazz, String value, String size, String id) {
		super();
		this.type=type;
		this.name=name;
		this.clazz=clazz;
		this.value=value;
		this.size=size;
		this.id=id;
	}

	@Override
	public String getCode() {
		if(id==null)
			id=name;
		if(value==null)
			value="";
		String texto = "<input";
		texto += " type=\""+type+"\"";
		texto += " name=\""+name+"\"";
		texto += " id=\""+id+"\"";
		texto += " value=\""+value+"\"";
		if(clazz!=null)
			texto += " class=\""+clazz+"\"";
		if(size!=null)
			texto += " size=\""+size+"\"";
		
		if(selecionado)
			texto += " checked=\"true\"";
		texto += " />";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public boolean isSelecionado() {
		return selecionado;
	}
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}



}
