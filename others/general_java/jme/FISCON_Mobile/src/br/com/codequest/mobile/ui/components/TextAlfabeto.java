package br.com.codequest.mobile.ui.components;

import org.j4me.ui.DeviceScreen;
import org.j4me.ui.components.TextBox;

/**
 * 
 */
public class TextAlfabeto extends TextBox {
	private static final int ZERO = 48;
	public TextAlfabeto() {

	}

	public TextAlfabeto(String label) {
		setLabel(label);
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
		if (keyCode == 8 && contents != null && !contents.equals("")) {
			contents = contents.substring(0, contents.length() - 1);
		} else if (keyCode == 32) {
			contents += " ";
		} // NUMEROS
		else if (keyCode >= ZERO && keyCode <= ZERO + 9) {
			contents += keyCode - ZERO;
		}
		else if (keyCode > 0 || keyCode == DeviceScreen.FIRE) {

			if (contents == null) {
				contents = LeitorTeclado.getTecla("", keyCode);
			} else {
				contents = LeitorTeclado.getTecla(contents, keyCode);
			}
		} 

		setString(contents.toUpperCase());
		repaint();
	}

}
