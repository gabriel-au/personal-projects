package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;

/**
 * Tratamento das Regras de A do HTML
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class A extends Componente {

	private String clazz;
	private String href;
	private String onclick;
	private boolean fullPath;
	
	public A(String href,boolean fullPath) {
		super();
		 this.href=href;
		 this.fullPath=fullPath;
	}

	public A(String href,boolean fullPath,String clazz) {
		super();
		this.href=href;
		this.fullPath=fullPath;
		this.clazz=clazz;
	}
	public A(String href,boolean fullPath,String clazz,String onclick) {
		super();
		this.href=href;
		this.fullPath=fullPath;
		this.clazz=clazz;
		this.onclick=onclick;
	}

	@Override
	public String getCode() {
		if(fullPath)
			href= UtilString.getFullPath()+href;
		
		String texto = "<a ";
		if(href!=null)
			texto += "href=\""+href+"\" ";
		if(clazz!=null)
			texto += "clazz=\""+clazz+"\" ";
		if(onclick!=null)
			texto += "onclick=\""+onclick+"\" ";
		
		texto += ">";
		texto += getCodeChild();
		texto += "</a>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
		itensOpcionais.add(Texto.class);
		itensOpcionais.add(Img.class);
		itensOpcionais.add(Div.class);
		itensOpcionais.add(H1.class);
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public boolean isFullPath() {
		return fullPath;
	}

}
