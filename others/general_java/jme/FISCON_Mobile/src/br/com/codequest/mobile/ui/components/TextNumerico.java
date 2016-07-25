package br.com.codequest.mobile.ui.components;

import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.util.Propriedades;

/**
 * 
 */
public class TextNumerico extends TextBox {

	public TextNumerico(String label) {
		setLabel(label);
	}

	int numeros[] = { 109, 114, 116, 121, 102, 103, 104, 118, 98, 110 };

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	// public void keyPressed(int keyCode) {
	// //System.out.println(new Character((char) keyCode).toString() + "="
	// // + keyCode);
	// String contents = getString();
	//
	// if (keyCode >=40 && keyCode<=57) {
	//
	//
	// if (contents == null) {
	// contents = "";
	// }
	//			
	// contents += new Character((char) keyCode).toString();
	//			
	// }else if(keyCode==-8){
	// contents = contents.substring(0, contents.length() - 1);
	// }
	//
	// setString(contents);
	// repaint();
	// }

	public void keyPressed(int keyCode) {
		if (Propriedades.getProperty("EMULADOR").equals("true")) {
			String contents = getString();

			if (keyCode >= 40 && keyCode <= 57) {

				if (contents == null) {
					contents = "";
				}

				contents += new Character((char) keyCode).toString();

			} else if (keyCode == -8) {
				contents = contents.substring(0, contents.length() - 1);
			}

			setString(contents);
			repaint();
		} else {
			String contents = getString();
			if (keyCode == 8 && contents != null && !contents.equals("")) {
				contents = contents.substring(0, contents.length() - 1);
			} else if (keyCode >= 98 && keyCode <= 121) {

				if (contents == null) {
					contents = "";
				}
				int i = findKey(keyCode);

				if (i >= 0) {
					if (contents == null)
						contents = "";
					contents += i;
				}
			}

			setString(contents);
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
