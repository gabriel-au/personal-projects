package br.gov.infraero.site.ui;

import br.gov.infraero.site.Componente;

/**
 * Tratamento das Regras de Meta dos HTMLS
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class Meta extends Componente {

	public static final String CHARSET_UTF8="UTF-8";
	public static final String CHARSET_UTF16="UTF-16";
	public static final String CHARSET_ISO88591="ISO8859-1";
	public static final String CHARSET_ISO88592="ISO8859-2";
	
	private String name;
	private String content;
	private String charset;
	
	public Meta(String name, String content) {
		super();
		this.name=name;
		this.content=content;
	}
	public Meta(String charset) {
		super();
		this.charset=charset;
	}

	public Meta() {
		super();
		this.name="viewport";
		this.content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0";
	}
	
	@Override
	public String getCode() {
		String texto = "<meta ";
		if(charset!=null)
			texto+="http-equiv=\"Content-Type\" content=\"text/html; charset="+charset+"\" /> ";
		else
			texto+="name=\""+name+"\" content=\""+content+"\"/> ";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {

	}

}
