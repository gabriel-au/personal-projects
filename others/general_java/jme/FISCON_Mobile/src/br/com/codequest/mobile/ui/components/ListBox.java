package br.com.codequest.mobile.ui.components;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.Component;
import org.j4me.ui.components.TextBox;

import br.com.codequest.mobile.MobileDialog;

/**
 * 
 */
public class ListBox extends TextBox {

	private Vector lista = new Vector();
	private int posicao;
	private MobileDialog dialog;

	public ListBox(Vector lista, MobileDialog dialog) {
		this.lista = lista;
		this.dialog = dialog;
		setString(lista.elementAt(0).toString());
	}

	public String getItemSelecionado() {
		return getString();
	}

	public Object getValorSelecionado() {
		return lista.elementAt(posicao);
	}

	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {
		// avançar
		if (keyCode == -5 && posicao < (lista.size() - 1)) {
			posicao++;
			setString(lista.elementAt(posicao).toString());
			repaint();
		}
		// retroceder
		if (keyCode == -2 && posicao > 0) {
			posicao--;
			setString(lista.elementAt(posicao).toString());
			repaint();
		}
		// abrir tela
		if (keyCode == -8) {
			TelaLimpa tl = new TelaLimpa(this);
			tl.setPrevScreen(dialog);
			tl.show();
		}
	}

	protected void paintComponent(Graphics g, Theme theme, int width,
			int height, boolean selected) {
		setString(lista.elementAt(posicao).toString());
		super.paintComponent(g, theme, width, height, selected);

		int altura = height * 20 / 100;
		int base = height * 80 / 100;
		int centro = height * 50 / 100;

		int centroLargura = width - 17;

		// retangulo
		g.setColor(255, 255, 255);
		g.fillRect(centroLargura - 2 - centro, 0, width, height);

		// triangulo direita
		g.setColor(theme.getBorderColor());
		centro--;
		g.fillTriangle(centroLargura, altura, centroLargura - centro, centro,
				centroLargura + centro, centro);

		// triangulo esqueda
		centro += 2;
		g.fillTriangle(centroLargura, base, centroLargura + 1 - centro, centro,
				centroLargura - 1 + centro, centro);
	}

	public Vector getLista() {
		return lista;
	}

	public void setLista(Vector lista) {
		this.lista = lista;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

}

class TelaLimpa extends MobileDialog {

	private ListBox pai;

	public TelaLimpa(ListBox pai) {
		super("Lista");
		this.pai = pai;
	}

	protected void init() {
		setMenuText("Cancelar", "Selecionar");
	}

	public void show() {
		deleteAll();
		Vector lista = pai.getLista();
		int posicao = pai.getPosicao();

		for (int i = 0; i < lista.size(); i++) {
			if(posicao==i){
				append(new ListBoxMenu(i,lista.elementAt(i).toString(), this),true);
			}else{
				append(new ListBoxMenu(i,lista.elementAt(i).toString(), this));
			}
		}
		super.show();
	}
	
	public void posicionar(int posicao){
		pai.setPosicao(posicao);
		executarLeft();
	}

	public void executarRight() {
		pai.setPosicao(getComponeteSelecionado());
		executarLeft();
	}

	public int getComponeteSelecionado(){
		Enumeration e = components();
		
		while ( e.hasMoreElements() )
		{
			Component c = (Component)e.nextElement();
			
			if(c instanceof ListBoxMenu){
				ListBoxMenu item = (ListBoxMenu)c;
				if(item.isSelecionado()){
					return item.getId();
				}
			}
		}
		
		return 0;
	}
	
}

class ListBoxMenu extends TextBox {

	private TelaLimpa pai;
	private int id;
	private boolean selecionado;

	public ListBoxMenu(int id, String label, TelaLimpa pai) {
		setString(label);
		this.pai = pai;
		this.id = id;
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
		if (keyCode == -8) {
			pai.posicionar(id);
		}
	}
	
	public int getId(){
		return id;
	}
	public boolean isSelecionado(){
		return selecionado;
	}

	/**
	 * Paints the text box.
	 * 
	 * @param g
	 *            is the <code>Graphics</code> object to be used for rendering
	 *            the item.
	 * @param theme
	 *            is the application's theme. Use it to get fonts and colors.
	 * @param width
	 *            is the width, in pixels, to paint the component.
	 * @param height
	 *            is the height, in pixels, to paint the component.
	 * @param selected
	 *            is <code>true</code> when this components is currently
	 *            selected and <code>false</code> when it is not.
	 * 
	 * @see org.j4me.ui.components.Component#paintComponent(Graphics, Theme,
	 *      int, int, boolean)
	 */
	protected void paintComponent(Graphics g, Theme theme, int width,
			int height, boolean selected) {
		selecionado=selected;
		int y = 0;

		String contents = getString();

		int offset = 2;
		// Paint the text box.
		if (selected) {
			offset = paintRect(g, theme, 0, y, width, height, selected);
		}

		// Paint the contents of the text box.
		if (contents != null) {
			// Indent the text a bit from the sides of the component's interior.
			offset += 2;// TEXT_OFFSET;

			// Calculate the layout of the text inside the text box.
			int left = offset;
			int top = y + offset;
			width -= 2 * offset;
			height -= 2 * offset;

			int anchor = Graphics.LEFT | Graphics.TOP;

			// g.clipRect( left, top, width, height );
			g.setColor(theme.getFontColor());

			// Determine the text to display inside the text box.
			String display = contents;

			// Paint the text inside the text box.
			g.drawString(display, left, top, anchor);
		}
	}

}
