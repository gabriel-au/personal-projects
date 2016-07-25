package br.com.codequest.mobile.ui.components;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.MobileDialog;

/**
 * Componente para criar itens de Menu
 * 
 * @author gustavomarcelo
 *
 */
public class LabelMenu extends TextBox {

	private MobileDialog mobileDialog;
	private MobileDialog mobileDialogPai;
	
	public LabelMenu(String label, MobileDialog mobileDialog, MobileDialog mobileDialogPai) {
		setString(label);
		this.mobileDialog=mobileDialog;
		this.mobileDialogPai=mobileDialogPai;
	}
	
	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {
		//System.out.println(new Character((char) keyCode).toString() + "="
		//		+ keyCode);
		if(keyCode==-8 && mobileDialog!=null){
			mobileDialog.setPrevScreen(mobileDialogPai);
			mobileDialog.show();
		}
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
		int y = 0;
		
		String contents = getString();
		
		int offset =2;
		// Paint the text box.
		if(selected){
			offset = paintRect( g, theme, 0, y, width, height, selected );
		}
		
		// Paint the contents of the text box.
		if ( contents != null )
		{
			// Indent the text a bit from the sides of the component's interior.
			offset += 2;//TEXT_OFFSET;
			
			// Calculate the layout of the text inside the text box.
			int left = offset;
			int top = y + offset;
			width -= 2 * offset;
			height -= 2 * offset;
	
			int anchor = Graphics.LEFT | Graphics.TOP;
	
			//g.clipRect( left, top, width, height );
			g.setColor( theme.getFontColor() );
			
			// Determine the text to display inside the text box.
			String display = contents;
			
			
			// Paint the text inside the text box.
			g.drawString( display, left, top, anchor );
		}
	}

}
