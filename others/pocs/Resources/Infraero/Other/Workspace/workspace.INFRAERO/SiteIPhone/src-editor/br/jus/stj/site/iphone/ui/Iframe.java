package br.jus.stj.site.iphone.ui;

import br.jus.stj.site.iphone.Componente;
import br.jus.stj.site.iphone.util.UtilString;


public class Iframe extends Componente {

	private int marginwidth=0;
	private int marginheight=0;
	private int hspace=0;
	private int vspace=0;
	private int frameborder=0;
	private boolean scrolling=false;
	private int height=10;
	private int width=300;
	private String src;
	private boolean isFullPah;
	
	public Iframe(String src, boolean isFullPath) {
		super();
		this.src= src;
		this.isFullPah=isFullPath;
	}


	@Override
	public String getCode() {
		String texto ="<iframe ";
		texto+= "marginwidth=\""+marginwidth+"\" ";
		texto+= "marginheight=\""+marginheight+"\" ";
		texto+= "hspace=\""+hspace+"\" ";
		texto+= "vspace=\""+vspace+"\" ";
		texto+= "frameborder=\""+frameborder+"\" ";
		texto+= "scrolling=\""+(scrolling?"yes":"no")+"\" ";
		texto+= "height=\""+height+"\" ";
		texto+= "width=\""+width+"\" ";
		if(isFullPah)
			texto+= "src=\""+UtilString.getFullPath()+src+"\" ";
		else
			texto+= "src=\""+src+"\" ";
		texto+= ">";
		texto+= "</iframe>";
		return texto;
	}

	@Override
	protected void getMontarItensObrigatoriosOpcionais() {
	}
	public int getMarginwidth() {
		return marginwidth;
	}
	public void setMarginwidth(int marginwidth) {
		this.marginwidth = marginwidth;
	}
	public int getMarginheight() {
		return marginheight;
	}
	public void setMarginheight(int marginheight) {
		this.marginheight = marginheight;
	}
	public int getHspace() {
		return hspace;
	}
	public void setHspace(int hspace) {
		this.hspace = hspace;
	}
	public int getVspace() {
		return vspace;
	}
	public void setVspace(int vspace) {
		this.vspace = vspace;
	}
	public int getFrameborder() {
		return frameborder;
	}
	public void setFrameborder(int frameborder) {
		this.frameborder = frameborder;
	}
	public boolean isScrolling() {
		return scrolling;
	}
	public void setScrolling(boolean scrolling) {
		this.scrolling = scrolling;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public boolean isFullPah() {
		return isFullPah;
	}
	public void setFullPah(boolean isFullPah) {
		this.isFullPah = isFullPah;
	}

}
