package br.com.codequest.mobile.ui.components;

import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.util.Propriedades;

/**
 * 
 */
public class TextData extends TextBox {

	public TextData(String label) {
		setLabel(label);
		setMaxSize(10);
	}

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	int numeros[] = { 109, 114, 116, 121, 102, 103, 104, 118, 98, 110 };

	public int findKey(int keyCode) {
		for (int i = 0; i < numeros.length; i++) {
			if (keyCode == numeros[i])
				return i;
		}
		return -1;
	}

	public void keyPressed(int keyCode) {
		if (!Propriedades.getProperty("EMULADOR").equals("true")) {
			String contents = (getString() == null ? "" : getString());
			int len = contents.length();
			if (keyCode == 8 && contents != null && !contents.equals("")) {
				int apaga = (len == 4 || len == 7 ? 2 : 1);
				contents = contents.substring(0, contents.length() - apaga);
			} else {
				int n = findKey(keyCode);
				if (n >= 0 && len <= 10) {
					contents += n;
					len = contents.length();
					if (len == 2 || len == 5)
						contents += "/";
				}
			}
			setString(contents);
			repaint();
		} else {

			System.out.println(new Character((char) keyCode).toString() + "="
					+ keyCode);
			String contents = getString();

			if (keyCode >= 48 && keyCode <= 57) {

				if (contents == null) {
					contents = "";
				}
				// validações de data
				// o primeiro dígito pode ser 0 ou 1
				if (contents.length() == 0) {
					if (keyCode >= 48 && keyCode <= 51) {
						contents += new Character((char) keyCode).toString();
					}
				}
				// se o dia começa com 3, só aceita 0 ou 1
				else if (contents.length() == 1) {
					if (contents.equals("3")) {
						if (keyCode >= 48 && keyCode <= 49) {
							contents += new Character((char) keyCode)
									.toString();
						}
					} else {
						contents += new Character((char) keyCode).toString();
					}
				} else if (contents.length() == 3) {
					if (keyCode >= 48 && keyCode <= 49) {
						contents += new Character((char) keyCode).toString();
					}
				} else if (contents.length() == 4) {
					if (contents.charAt(3) == '1') {
						if (keyCode >= 48 && keyCode <= 50) {
							contents += new Character((char) keyCode)
									.toString();
						}
					} else {
						contents += new Character((char) keyCode).toString();
					}
				} else if (contents.length() == 6) {
					if (keyCode >= 49 && keyCode <= 50) {
						contents += new Character((char) keyCode).toString();
					}
				} else if (contents.length() > 6 && contents.length() < 10) {
					contents += new Character((char) keyCode).toString();
				}

			} else if (keyCode == -8) {
				if (contents.length() == 3 || contents.length() == 6) {
					contents = contents.substring(0, contents.length() - 2);
				} else {
					contents = contents.substring(0, contents.length() - 1);
				}
			}

			if (contents.length() == 2 || contents.length() == 5) {
				contents += "/";
			}

			setString(contents);
			repaint();

		}
	}

	public void keyPressesd(int keyCode) {
		System.out.println(new Character((char) keyCode).toString() + "="
				+ keyCode);
		String contents = getString();

		if (keyCode >= 48 && keyCode <= 57) {

			if (contents == null) {
				contents = "";
			}
			// validações de data
			// o primeiro dígito pode ser 0 ou 1
			if (contents.length() == 0) {
				if (keyCode >= 48 && keyCode <= 51) {
					contents += new Character((char) keyCode).toString();
				}
			}
			// se o dia começa com 3, só aceita 0 ou 1
			else if (contents.length() == 1) {
				if (contents.equals("3")) {
					if (keyCode >= 48 && keyCode <= 49) {
						contents += new Character((char) keyCode).toString();
					}
				} else {
					contents += new Character((char) keyCode).toString();
				}
			} else if (contents.length() == 3) {
				if (keyCode >= 48 && keyCode <= 49) {
					contents += new Character((char) keyCode).toString();
				}
			} else if (contents.length() == 4) {
				if (contents.charAt(3) == '1') {
					if (keyCode >= 48 && keyCode <= 50) {
						contents += new Character((char) keyCode).toString();
					}
				} else {
					contents += new Character((char) keyCode).toString();
				}
			} else if (contents.length() == 6) {
				if (keyCode >= 49 && keyCode <= 50) {
					contents += new Character((char) keyCode).toString();
				}
			} else if (contents.length() > 6 && contents.length() < 10) {
				contents += new Character((char) keyCode).toString();
			}

		} else if (keyCode == -8) {
			if (contents.length() == 3 || contents.length() == 6) {
				contents = contents.substring(0, contents.length() - 2);
			} else {
				contents = contents.substring(0, contents.length() - 1);
			}
		}

		if (contents.length() == 2 || contents.length() == 5) {
			contents += "/";
		}

		setString(contents);
		repaint();
	}

}
