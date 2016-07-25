package br.com.codequest.mobile.ui.components;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.TextBox;

/**
 * 
 */
public class ComboBox extends TextBox {

	private Vector lista = new Vector();
	private int posicao;

	public ComboBox(Vector lista) {
		this.lista=lista;
		setString(lista.elementAt(0).toString());
	}


	public String getItemSelecionado(int posicao){
		return getString();
	}
	public Object getValorSelecionado(int posicao){
		return lista.elementAt(posicao);
	}
	
	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {
		System.out.println(new Character((char) keyCode).toString() + "="
				+ keyCode);
		// avançar
		if (keyCode == -5 && posicao < (lista.size()-1)) {
			posicao++;
			setString(lista.elementAt(posicao).toString());
			repaint();
		}
		// retroceder
		if (keyCode == -2 && posicao>0) {
			posicao--;
			setString(lista.elementAt(posicao).toString());
			repaint();
		}
	}

	protected void paintComponent(Graphics g, Theme theme, int width,
			int height, boolean selected) {
		super.paintComponent(g, theme, width, height, selected);

		int altura = height * 20 / 100;
		int base = height * 80 / 100;
		int centro = height * 50 / 100;

		int centroLargura = width - 17;

		// retangulo
		g.setColor(255, 255, 255);
		g.fillRect(centroLargura - 2 - centro, 0, width, height);

		//triangulo direita
		g.setColor(theme.getBorderColor());
		g.fillTriangle(centroLargura, altura, centroLargura - centro, centro,
				centroLargura, base);

		//triangulo esqueda
		centroLargura += 2;
		g.fillTriangle(centroLargura, altura, centroLargura + centro, centro,
				centroLargura, base);
	}

}
