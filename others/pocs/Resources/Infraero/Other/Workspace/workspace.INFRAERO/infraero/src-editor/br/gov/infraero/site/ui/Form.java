package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;

/**
 * Tratamento das Regras de Form do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Form extends Componente {

	private String title;
	private String clazz;
	private String action;
	private String method="post";
	private String selected;
	
	public Form(String action) {
		super();
		this.action=action;
	}

	@Override
	public String getCode() {
		String texto = "<form";
		texto+=" action=\""+action+"\"";
		texto+=" method=\""+method+"\"";
		if(clazz!=null)
			texto+=" class=\""+clazz+"\"";
		if(title!=null)
			texto+=" title=\""+title+"\"";
		texto+= ">\n";
		texto+=getCodeChild();
		texto+= "</form>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(Input.class);
		itensOpcionais.add(H1.class);
		itensOpcionais.add(H2.class);
		itensOpcionais.add(Img.class);
		itensOpcionais.add(P.class);
		itensOpcionais.add(Div.class);
		itensOpcionais.add(Texto.class);
		itensOpcionais.add(Texto.class);
		itensOpcionais.add(Select.class);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

}
