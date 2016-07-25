package br.com.codequest.mobile.ui.components;

import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.MobileDialog;

/**
 * Componente para criar itens de Menu
 * 
 * @author Gustavo Marcelo
 * @author Gustavo Luis
 *
 */
public class Menu extends TextBox {

	private MobileDialog dialog;
	
	private Vector itens = new Vector(); 
	private int posicao =0;
	
	private int borda;
	private int tamanho;
	private int quantItens;
	private int colunas;
	private int linhas;
	private int espaco;
	private int alturaTotal;
	private int larguraTotal;
	
	private boolean scrollModoRepaint=false;
	private boolean scrollDirecionamento=false;
	private int scrollPaginaAtual=1;
	
	public Menu(MobileDialog dialog) {
		this.dialog=dialog;
		larguraTotal =dialog.getWidth();
	}

	public void add(MenuItem item){
		itens.addElement(item);
		calcular();
	}
	public void remove(MenuItem item){
		itens.removeElement(item);
		calcular();
	}
	
	private void calcular(){
		borda = 14;
		tamanho = 40;
		quantItens = itens.size();
		colunas = larguraTotal / (tamanho+2*borda);
		linhas = quantItens / colunas;
		if(linhas==0 || (quantItens % colunas)>0){
			linhas++;
		}
		
		espaco = (larguraTotal - tamanho*colunas)/(colunas+1);
		
		alturaTotal =borda+ linhas*(borda + tamanho + borda);
		posicao=0;
	}
	
	protected int getPrefContentHeight(int i) {
        return alturaTotal;
    }
	
	protected int[] getPreferredComponentSize (Theme theme, int viewportWidth, int viewportHeight)
	{
		calcular();
		return new int[] { larguraTotal, alturaTotal };
	}
	
	
	/**
	 * Paints the text box.
	 * 
	 * @param g is the <code>Graphics</code> object to be used for rendering the item.
	 * @param theme is the application's theme.  Use it to get fonts and colors.
	 * @param width is the width, in pixels, to paint the component.
	 * @param height is the height, in pixels, to paint the component.
	 * @param selected is <code>true</code> when this components is currently selected
	 *  and <code>false</code> when it is not.
	 * 
	 * @see org.j4me.ui.components.Component#paintComponent(Graphics, Theme, int, int, boolean)
	 */
	protected void paintComponent (Graphics g, Theme theme, int width, int height, boolean selected)
	{
		if(scrollModoRepaint){
			dialog.ativarScroll(scrollDirecionamento);
			scrollModoRepaint=false;
			return;
		}
		
		int espacoTexto = (2* borda+ tamanho);
		//Font f = theme.getFont();
		
		Font f= Font.getFont(theme.getFont().getFace(),theme.getFont().getStyle(), Font.SIZE_SMALL) ;

		g.setFont(f);
		
		int itemAtual =0;
		
		try {
			int y = borda;
			
			for(int l = 1; l<= linhas;l++){
				int x = espaco;
				
				for(int i = 0; i< colunas;i++){
					
					if(itemAtual >=itens.size()){
						return;
					}
					
					MenuItem item = (MenuItem)itens.elementAt(itemAtual);

					if(posicao== itemAtual){
						g.setColor( theme.getHighlightColor());
						//g.fillRect(x-borda-1, y-borda+2+1, tamanho +borda*2+2, tamanho+borda*2+2);
						g.fillRect(x-2, y-2, 42 , 42);
						g.drawRect(x-2-1, y-2-1, 42+2 , 42+2);

						g.setColor( theme.getBorderColor() );
						//g.drawRect(x-borda, y-borda+2+2, tamanho +borda*2, tamanho+borda*2);
						g.drawRect(x-2, y-2, 42 , 42);
						
						//g.setColor( theme.getMenuFontColor() );
						//int size = (espacoTexto - f.charsWidth(item.getTexto().toCharArray(), 0, item.getTexto().length()))/2;
						//g.drawString(item.getTexto(), x-borda+size, y+40, 0 );
					}
					g.setColor( theme.getFontColor() );
					int size = (espacoTexto - f.charsWidth(item.getTexto().toCharArray(), 0, item.getTexto().length()))/2;
					g.drawString(item.getTexto(), x-borda+size+1, y+40, 0 );
						
					
					if(posicao != itemAtual || item.getImagemHover() == null){
						g.drawImage(item.getImagem(), x, y, 0);
					} else {
						g.drawImage(item.getImagemHover(), x, y, 0);
					}
					
					x = x + tamanho + espaco;
					itemAtual++;
				}
				
				y = y + tamanho+borda*2;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void keyPressed(int keyCode) {
		
		switch (keyCode) {
		case -21: dialog.executarLeft();break;
		case -22: dialog.executarRight();break;
		case -8:((MenuItem)itens.elementAt(posicao)).show();break;
		case -6: 
			posicao+=colunas;
			break;
		case -5: posicao++;break;
		case -2: posicao--;break;
		case -1: 
			posicao-=colunas;
			break;
		case 50: posicao-=colunas;break;
		case 52: posicao--;break;
		case 54: posicao++;break;
		case 56: posicao+=colunas;break;
		default:
			break;
		}
		
		if(posicao<0)
			posicao=itens.size()-1;
		if(posicao>=itens.size())
			posicao=0;
		
		//verificar o scroll
		double valorAltura = (1+posicao/colunas)*(tamanho+borda*2);
		//System.out.println("altura: "+valorAltura + ", tela"+ dialog.getHeight());
		if(valorAltura >= dialog.getHeight()*scrollPaginaAtual){
			 scrollModoRepaint=true;
			 scrollDirecionamento=true;
			 scrollPaginaAtual++;
		}else if(valorAltura<dialog.getHeight()*(scrollPaginaAtual-1)){
			 scrollModoRepaint=true;
			 scrollDirecionamento=false;
			 scrollPaginaAtual--;
		} 
		
		
		repaint();
	}
	
}
