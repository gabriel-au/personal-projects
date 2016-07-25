package br.com.codequest.mobile.ui.components;

import org.j4me.ui.components.TextBox;

/**
 * 
 */
public class TextTeste extends TextBox {
	public TextTeste() {

	}

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {
		// System.out.println(new Character((char) keyCode).toString() + "="
		// + keyCode);
		String contents = getString();
		if (contents == null)
			contents = "";

		contents += "(" + keyCode + ")";
		setString(contents);
		repaint();
	}

}
