package br.com.codequest.mobile.ui.components;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.Controll;
import br.com.codequest.mobile.MobileDialog;

/**
 * Componente para criar Botões
 * 
 * @author gustavomarcelo
 * 
 */
public class Button extends TextBox {

	public static final int ALIGN_CENTER=1;
	public static final int ALIGN_LEF=2;
	public static final int ALIGN_RIGTH=3;
	
	private Controll controll;
	private int align;
	private MobileDialog mobileDialog;
	private MobileDialog mobileDialogPai;

	public Button(String label, MobileDialog mobileDialog, MobileDialog mobileDialogPai) {
		setString(label);
		if(label.length()>30){
			System.out.println("Texto muito grande, pode não ser compatível com todos os celulares");
		}
		this.mobileDialog=mobileDialog;
		this.mobileDialogPai=mobileDialogPai;
	}
	public Button(String label, Controll controll) {
		setString(label);
		if(label.length()>30){
			System.out.println("Texto muito grande, pode não ser compatível com todos os celulares");
		}
		
		this.controll = controll;
	}

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {
		if ((keyCode == -8)) {
			if(controll!=null){
				controll.executar();
			}else{
				mobileDialog.setPrevScreen(mobileDialogPai);
				mobileDialog.show();
			}
		}
		invalidate();
		repaint();
	}

	protected void paintComponent(Graphics g, Theme theme, int width,
			int height, boolean selected) {
		String display = " "+getString()+" ";
		int tamanhoTexto= theme.getFont().charsWidth(display.toCharArray(), 0, display.length());

		int anchor = Graphics.LEFT | Graphics.TOP;
		int offset=2;
		
		int left = offset;
		if(align==ALIGN_CENTER){
			left = (width+offset*2-tamanhoTexto)/2;
		}else if(align==ALIGN_RIGTH){
			left = width-offset-tamanhoTexto;
		}else{
			left = offset;
		}
		
		int top = offset;
		width -= 2 * offset;
		height -= 2 * offset;
		
		//theme.gradientFill(g, left, top, tamanhoTexto, height, true, 
		//		theme.getMenuBarBackgroundColor(), theme.getMenuBarHighlightColor(), 0);
		g.setColor(theme.getHighlightColor());
		g.fillRect(left, top, tamanhoTexto, height);
		
		if(selected){
			g.setColor(theme.getBorderColor());
			g.drawRect(left-1, top-1, tamanhoTexto+1, height+1);
			g.setColor( theme.getMenuFontColor() );
		}else {
			g.setColor( theme.getMenuFontHighlightColor() );
		}
		g.drawString( display, left, top+2, anchor );
	}

	public Controll getControll() {
		return controll;
	}

	public void setControll(Controll controll) {
		this.controll = controll;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

}
