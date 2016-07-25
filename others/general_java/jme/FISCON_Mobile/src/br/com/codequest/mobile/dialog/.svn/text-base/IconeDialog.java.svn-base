package br.com.codequest.mobile.dialog;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.UIManager;

import br.com.codequest.mobile.MobileDialog;

public abstract class IconeDialog extends MobileDialog {

	/** imagem padrão do logotipo */
	private static Image picture;


	/**
	 * Construtor padrão
	 */
	public IconeDialog(String titulo) {
		super(titulo);
		try {
			picture = Image.createImage("/icones/cancelar.png");
			setTitle(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void initDialog(String titulo) {
		adicionarLogo();
		init();
	}

	public void setIcone(String scrImage) {
		try {
			picture = Image.createImage(scrImage);
			setTitle(" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setIcone(String scrImage, String titulo) {
		try {
			picture = Image.createImage(scrImage);
			setTitle(titulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void adicionarLogo() {
		// verifica se a imagem foi definida
		if (MobileDialog.picture == null) {
			return;
		}
			if (picture == null) {
				picture =MobileDialog.picture.getImage();
			}

	}

	protected void paintTitleBar(Graphics g, String title, int width, int height) {
		// Fill the background of the title bar.
		//g.setColor(UIManager.getTheme().getBackgroundColor());
		//g.fillRect(0, 0, width, height);
		int primary = UIManager.getTheme().getTitleBarBackgroundColor();
		int secondary = UIManager.getTheme().getTitleBarHighlightColor();
		
		UIManager.getTheme().gradientFill( g, 0, 0, width, height, true, primary, secondary, 0.60 );
		

		// Draw a line below the title bar to separate it from the canvas.
		g.setColor(UIManager.getTheme().getTitleBarBorderColor());
		g.drawLine(0, height - 1, width, height - 1);

		g.drawImage(picture, 0, 0, 0);
		int tamanho = picture.getWidth()+ 2;
		g.setColor(UIManager.getTheme().getTitleFontColor());
		g.drawString(getTitle(), tamanho, 2, 0);
	}

	public int getTitleHeight() {
		return picture.getHeight() + 1;
	}

}
