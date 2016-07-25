package br.com.codequest.mobile.dialog;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.j4me.ui.UIManager;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.PadraoTheme;

public abstract class AbaDialog extends MobileDialog{

	private AbaControle controle;
	private Font f;
	private PadraoTheme theme;
	private int offset = 2;
	protected int altura=0;
	
	public AbaDialog(AbaControle controle){
		this.controle=controle;
		theme = (PadraoTheme)UIManager.getTheme();
		f= Font.getFont(theme.getFont().getFace(),theme.getFont().getStyle(), Font.SIZE_SMALL) ;
		
		//theme.setTitleHeight(f.getHeight()+offset*2);
		setMenuText(null, null);
		setTitle("ABA");
	}

	public void init() {
	}
	
	public void paintTitleBar(Graphics g, String title, int width, int height) {
		if(controle==null){
			return;
		}
		int primary = theme.getTitleBarBackgroundColor();
		int secondary = theme.getTitleBarHighlightColor();
		
		theme.gradientFill( g, 0, 0, width, height, true, primary, secondary, 0.60 );
		
		g.setColor( UIManager.getTheme().getTitleBarBorderColor() );
		g.drawLine( 0, height - 1, width, height - 1 );

		String []titulos = controle.getTitulos();
		
		int x = 0; 
		int tamanhoTotal=0;
		int tamanhoAtivo=0;
		int ativo = controle.getIdAtivo();
		
		for (int i = 0; i < titulos.length; i++) {
			int size = f.charsWidth(titulos[i].toCharArray(), 0, titulos[i].length())+offset*4;
			tamanhoTotal+=size;
			if(i<ativo-1){
				tamanhoAtivo+=size;
			}
		}
		//System.out.println(tamanhoTotal +"-"+width+"-"+ tamanhoAtivo);
		if( tamanhoTotal > width){
			x = tamanhoAtivo*-1;
		}
		
		for (int i = 0; i < titulos.length; i++) {
			int size = f.charsWidth(titulos[i].toCharArray(), 0, titulos[i].length())+offset*4;
			
			g.setColor(theme.getBackgroundColor());
			g.drawRect(x, altura, size, height);
			if(i==ativo){
				g.setColor( theme.getMenuFontColor() );
				g.fillRect(x, altura, size, height);
				g.setColor(theme.getFontColor());
			}else {
				g.setColor( theme.getMenuFontHighlightColor() );
			};
			g.drawString(titulos[i], x+offset*2, altura+offset, 0 );
			
			x = x + size;
		}
		
	}
	
	public void keyPressed(int keyCode) {
		switch (keyCode) {
		case (MENU_LEFT): {
			controle.anterior();
			break;
		}
		case (MENU_RIGHT): {
			controle.proximo();
			break;
		}
		default:
			super.keyPressed(keyCode);
		}
		montarComponentes();
	}
		
	private void montarComponentes(){
		MobileDialog abaAtiva = controle.getAbaAtiva();
		deleteAll();
		setComponents(abaAtiva.getComponents());
		invalidate();
	}
	
	public void repaint() {
		montarComponentes();
		super.repaint();
	}

	public void show() {
		if(controle==null){
			return;
		}
		montarComponentes();
		repaint();
		super.show();
	}
	
	public int getTitleHeight() {
		return f.getHeight()+offset*2;
	}
}
