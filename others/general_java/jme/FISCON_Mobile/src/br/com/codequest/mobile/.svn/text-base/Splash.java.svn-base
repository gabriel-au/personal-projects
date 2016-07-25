package br.com.codequest.mobile;
import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.UIManager;

import br.com.codequest.mobile.ui.LoginForm;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.lista.ListaBairro;
import br.com.prime.fiscon.mobile.lista.ListaInfracaoTipo;

/**
 * Splash Class extends a Canvas for exibiting
 * a splash screen with an image in it.
 */
public class Splash extends Canvas {

	private Image image;
	private int   color;
	private int   centerX;
	private int   centerY;

	/**
	 * Creates a splash screen to display the specified
	 * image and fills the background with the specified
	 * color.
	 * 
	 * @param background
	 */
	public Splash(String image, int background) {
		this.setFullScreenMode(true);
		this.color = background;
		
		try {
			this.image = Image.createImage(image);
			
			/*Calculate center position*/
			this.centerX = (this.getWidth() - this.image.getWidth())/2;
			this.centerY = (this.getHeight() - this.image.getHeight())/2;
		} catch (IOException e) {
			/* Unable to load image. Do not display it. */
		}
	}

	/**
	 * Shows the splash and changes to the specified
	 * displayable after the specified number of milliseconds.
	 * 
	 * @param display Current Display.
	 * @param next new Displayable.
	 * @param millis splash time in milliseconds.
	 * @param mobileMain 
	 */
	public void show(final Display display, final Displayable next) {
		display.setCurrent(this);

		// Schedule next Displayable
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1);
					UIManager.setTheme(new PadraoTheme());
					Propriedades.loadProperties("/microedition.properties");
					//carregar listas
					ListaInfracaoTipo.load();
					ListaBairro.load();
					new LoginForm().show();
					//new MenuPrincipalImagem().show();
					//new AutoInfracaoConsultaInfracaoTipo(null).show();
					//new AutoInfracaoFinalizacaoComFoto("teste").show();
					//new AutoInfracaoObservacaoConfirma(null, "teste").show();
					//new AutoInfracaoCancelarEnviada().show();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					
				}
			}
		});
		t.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.microedition.lcdui.Canvas#paint(javax.microedition.lcdui.Graphics)
	 */
	protected void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(0x00, 0x00, getWidth(), getHeight());

		if (this.image != null) {
			g.drawImage(this.image, this.centerX, this.centerY, 0x00);
		}
	}
}
