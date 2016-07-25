package br.com.codequest.mobile;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.j4me.ui.UIManager;

public class MobileMain extends MIDlet {
	/**
	 * The one and only instance of this class.
	 */
	public static MobileMain instance;
	private Display display;
	private Splash splash;

	/**
	 * Creates several screens and navigates between them.
	 */
	public MobileMain() {
		instance = this;
	}

	/**
	 * Called when the application is minimized. For example when their is an
	 * incoming call that is accepted or when the phone's hangup key is pressed.
	 * 
	 * @see javax.microedition.midlet.MIDlet#pauseApp()
	 */
	protected void pauseApp() {
	}

	/**
	 * Called when the application starts. Shows the first screen.
	 * 
	 * @see javax.microedition.midlet.MIDlet#startApp()
	 */
	protected void startApp() throws MIDletStateChangeException {
		this.display = Display.getDisplay(this);
		this.splash = new Splash("/logo/logo.png", 0xFFFFFF);

		Displayable main = this.getMainScreen();
		this.splash.show(this.display, main);

		UIManager.init(this);
	}

	/**
	 * Gets the application main screen.
	 * 
	 * @return main screen.
	 */
	private Displayable getMainScreen() {
		return new Canvas() {
			protected void paint(Graphics g) {
			}
		};
	}

	/**
	 * Called when the application is exiting.
	 * 
	 * @see javax.microedition.midlet.MIDlet#destroyApp(boolean)
	 */
	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// Add cleanup code here.

		// Exit the application.
		notifyDestroyed();
	}

	/**
	 * sair da aplicação
	 */
	public static void exit() {
		try {
			instance.destroyApp(true);
		} catch (MIDletStateChangeException e) {
			// Ignore
		}
	}
}
