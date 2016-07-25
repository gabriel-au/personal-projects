package br.com.codequest.mobile.dialog;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.Theme;
import org.j4me.ui.UIManager;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.PadraoTheme;

public abstract class AbaImagemDialog extends AbaDialog{

	/** imagem padrão do logotipo */
	private static Image picture;
	
	public AbaImagemDialog(AbaControle controle){
		super(controle);
	}

	public void paintTitleBar(Graphics g, String title, int width, int height) {
		altura=picture.getHeight();
		super.paintTitleBar(g, title, width, height);
		int tamanho = (width - picture.getWidth()) / 2;
		g.drawImage(picture, tamanho, 0, 0);
	}

	
	protected void initDialog(String titulo) {
		adicionarLogo();
		init();
	}

	public void setImage(String scrImage) {
		try {
			picture = Image.createImage(scrImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Theme t = UIManager.getTheme();
		if (t instanceof PadraoTheme) {
			((PadraoTheme) t).setTitleHeight(getTitleHeight());
			if (UIManager.getScreen() != null)
				UIManager.getScreen().repaint();
		}
	}

	protected void adicionarLogo() {
		// verifica se a imagem foi definida
		if (MobileDialog.picture == null) {
			return;
		}
		
		if (picture == null) {
			picture = MobileDialog.picture.getImage();
		}

			setTitle("IMAGEM");
		
		// insert(picture,0);
	}
	
	public int getTitleHeight() {
		int soma = super.getTitleHeight();
		soma +=picture.getHeight() + 1;
		return soma;
	}
	
	
}
