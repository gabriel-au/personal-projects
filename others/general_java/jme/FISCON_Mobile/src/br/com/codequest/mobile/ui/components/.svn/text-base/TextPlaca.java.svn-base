package br.com.codequest.mobile.ui.components;

import org.j4me.ui.DeviceScreen;
import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.util.Propriedades;

/**
 * 
 */
public class TextPlaca extends TextBox {

	public TextPlaca(String label) {
		setLabel(label);
	}

	int numeros[] = { 109, 114, 116, 121, 102, 103, 104, 118, 98, 110 };

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {

		if (Propriedades.getProperty("EMULADOR").equals("true")) {
			String contents = getString();

			if (keyCode >= 49 && keyCode <= 57) {

				if (contents == null) {
					contents = LeitorTeclado.getTecla("", keyCode);
				} else if (contents.length() < 3) {
					contents = LeitorTeclado.getTecla(contents, keyCode);
				} else if (contents.length() == 3) {
					contents = LeitorTeclado.getTeclaFinal(contents, keyCode);
				} else {
					contents += new Character((char) keyCode).toString();
				}

			} else if (keyCode == 8) {
				contents = contents.substring(0, contents.length() - 1);
			}

			setString(contents.toUpperCase());
			repaint();

		} else {
			String contents = getString();
			if (keyCode == 8 && contents != null && !contents.equals("")) {
				contents = contents.substring(0, contents.length() - 1);
			} else if (contents == null
					|| (contents != null && contents.length() < 3)) {
				// PEGA TODAS AS TECLAS
				if (keyCode > 0 || keyCode == DeviceScreen.FIRE) {

					if (contents == null) {
						contents = LeitorTeclado.getTecla("", keyCode);
					} else {
						contents = LeitorTeclado.getTecla(contents, keyCode);
					}
				}
			} else if (keyCode >= 98 && keyCode <= 121) {
				int i = findKey(keyCode);
				if (i >= 0) {
					if (contents == null)
						contents = "";
					contents += i;
				}
			}
			setString(contents.toUpperCase());
			repaint();
		}
	}

	public int findKey(int keyCode) {
		for (int i = 0; i < numeros.length; i++) {
			if (keyCode == numeros[i])
				return i;
		}
		return -1;
	}

}
