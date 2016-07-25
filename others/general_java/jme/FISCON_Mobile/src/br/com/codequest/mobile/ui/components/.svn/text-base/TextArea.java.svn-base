package br.com.codequest.mobile.ui.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.TextBox;

/**
 * 
 */
public class TextArea extends TextBox {

	private int linhasVisiveis;
	private int linhasAtual;
	private String linhas[];
	
	private int TEXT_OFFSET = 2;
	private int widthForLines;
	
	private boolean temBarraRolagem;
	
	
	public TextArea(String texto, int linhasVisiveis, boolean temBarraRolagem) {
		setLabel(texto);
		this.linhasVisiveis=linhasVisiveis;
		this.linhasAtual=0;
		this.temBarraRolagem = temBarraRolagem;
	}
	public TextArea(String texto, int linhasVisiveis) {
		setLabel(texto);
		this.linhasVisiveis=linhasVisiveis;
		this.linhasAtual=0;
		this.temBarraRolagem = false;
	}
	
	
	public void keyPressed(int keyCode) {
		System.out.println(keyCode);
		if((keyCode==-5 || keyCode==35) && linhasAtual<(linhas.length-linhasVisiveis)){
			linhasAtual++;
		}if((keyCode==-2 || keyCode==42) && linhasAtual>0){
			linhasAtual--;
		}
		repaint();
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
		
		Font font = theme.getFont();
		g.setFont( font );
		int fontHeight = font.getHeight();
		
		int y = 0;
		String contents = getLabel();
		
		// Paint the text box.
		int offset = paintRect( g, theme, 0, y, width, height, selected );
		
		// Paint the contents of the text box.
		if ( contents != null )
		{
			// Indent the text a bit from the sides of the component's interior.
			offset += TEXT_OFFSET;
			
			// Calculate the layout of the text inside the text box.
			int left = offset;
			int top = y + offset;
			width -= 2 * offset;
			height -= 2 * offset;
	
			int anchor = Graphics.LEFT | Graphics.TOP;
	
			g.clipRect( left, top, width, height );
			g.setColor( theme.getFontColor() );
			
			if ( (linhas == null) || (widthForLines != width) )
			{
				linhas = Label.breakIntoLines( font, contents, width -3 );
				widthForLines = width;
			}
			
			int contador=0;
			for(int i=linhasAtual;i<linhasVisiveis+linhasAtual && i<linhas.length;i++){
				if(linhas[i]==null)
					linhas[i]="";
				g.drawString( linhas[i], left, top + (contador++ * fontHeight), anchor );
			}
			int posicao = height*linhasAtual/(linhas.length-linhasVisiveis+2);
			if(temBarraRolagem){
				g.fillArc(width - 3, top+posicao, 6, 6, 0, 360);
			}else{
				g.fillRect(width - 3, top+posicao, 6, 6);
				g.drawLine(width - 3, -3, width - 3, height+3);
				g.drawLine(width+3, -6, width+3, height+6);
			}
		}
	}
	
	/**
	 * Retorna a dimensão do componente
	 * 
	 * @see org.j4me.ui.components.Component#getPreferredComponentSize(org.j4me.ui.Theme, int, int)
	 */
	protected int[] getPreferredComponentSize (Theme theme, int viewportWidth, int viewportHeight)
	{
		if(linhasVisiveis==0){
			linhasVisiveis=1;
		}
		
		int fontHeight = theme.getFont().getHeight();
		int height = fontHeight * linhasVisiveis + 2 * (HIGHLIGHTED_BORDER_WIDTH + TEXT_OFFSET);
		
		return new int[] { viewportWidth, height };
	}
	
}
