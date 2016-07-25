package br.jus.stj.site.iphone.ui;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.site.iphone.Componente;

/**
 * Tratamento das Regras de Div do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Div extends Componente {

	private boolean toolbar;
	private String style; 
	private String id; 
	
	public Div() {
		super();
	}
	public Div(String style) {
		super();
		this.style=style;
	}
	public Div(Texto texto) throws SystemException {
		super();
		add(texto);
	}
	public Div(String style, Texto texto) throws SystemException {
		super();
		this.style=style;
		add(texto);
	}
	public Div(String style, Componente componente) throws SystemException {
		super();
		this.style=style;
		add(componente);
	}
	public Div(boolean toolbar) {
		super();
		this.toolbar=toolbar;
	}

	@Override
	public String getCode() {
		String texto = "<div";
		if(toolbar)
			texto += " class=\"toolbar\"";		
		if(style!=null)		
			texto += " class=\""+style+"\"";
		if(id!=null)		
			texto += " id=\""+id+"\"";
		texto += ">";
		texto += getCodeChild();
		texto += "\n";
		texto += "</div>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(A.class);
		itensOpcionais.add(H1.class);
		itensOpcionais.add(H2.class);
		itensOpcionais.add(Img.class);
		itensOpcionais.add(Ul.class);
		itensOpcionais.add(P.class);
		itensOpcionais.add(Div.class);
		itensOpcionais.add(Texto.class);
		itensOpcionais.add(Input.class);
		itensOpcionais.add(Form.class);
		itensOpcionais.add(Select.class);
		itensOpcionais.add(Script.class);
		itensOpcionais.add(Style.class);
		itensOpcionais.add(Br.class);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
