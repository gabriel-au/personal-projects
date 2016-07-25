package br.com.codequest.mobile.ui.components;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

import br.com.codequest.mobile.MobileDialog;

public class CameraCanvas extends Canvas {

	private MobileDialog mobileDialog;
	private VideoControl videoControl1;
	private int top;
	private Player mPlayer;

	public CameraCanvas(MobileDialog mobileDialog) throws Exception {
		this.mobileDialog = mobileDialog;
		top = mobileDialog.getTamanho();
	}

	public void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		// Draw a green border around the VideoControl.
		g.setColor(0x0000ff);
		g.drawRect(0, 0, width - 1, height - 1);
		g.drawRect(1, 1, width - 3, height - 3);
	}

	public void keyPressed(int keyCode) {
		System.out.println(keyCode);
		if (keyCode == 7) {
			mobileDialog.executarRight();
		} else if (keyCode == 6) {
			mobileDialog.executarLeft();
		}
	}

	public Image createThumbnail(Image image) {
		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();

		int thumbWidth = 128;
		int thumbHeight = -1;

		if (thumbHeight == -1)
			thumbHeight = thumbWidth * sourceHeight / sourceWidth;
		thumbHeight = image.getHeight() / 2;
		thumbWidth = image.getWidth() / 2;

		Image thumb = Image.createImage(thumbWidth, thumbHeight);
		Graphics g = thumb.getGraphics();

		for (int y = 0; y < thumbHeight; y++) {
			for (int x = 0; x < thumbWidth; x++) {
				g.setClip(x, y, 1, 1);
				int dx = x * sourceWidth / thumbWidth;
				int dy = y * sourceHeight / thumbHeight;
				g
						.drawImage(image, x - dx, y - dy, Graphics.LEFT
								| Graphics.TOP);
			}
		}

		Image immutableThumb = Image.createImage(thumb);

		return immutableThumb;
	}

	public Image capturar() throws Exception {
		if (videoControl1 != null) {

			byte[] raw = videoControl1.getSnapshot("encoding=jpeg");

			return Image.createImage(raw, 0, raw.length);
		} else
			pararCamera();
			throw new Exception("Camera não iniciada.");

	}

	public byte[] capturarByte() throws Exception {
		if (videoControl1 != null) {

			byte[] raw = videoControl1.getSnapshot("encoding=jpeg");

			return raw;
		} else
			pararCamera();
			throw new Exception("Camera não iniciada.");

	}

	private VideoControl getVideoControl() throws Exception {
		if (mPlayer == null || mPlayer.getState() == Player.CLOSED) {
			try {
				mPlayer = Manager.createPlayer("capture://image");
			} catch (Exception e) {
				mPlayer = Manager.createPlayer("capture://video");
			}
			if (mPlayer.getState() == Player.UNREALIZED) {
				mPlayer.realize();

			}
			return (VideoControl) mPlayer.getControl("VideoControl");
		} else {

			return this.videoControl1;
		}

	}

	public void inicializarCamera() throws Exception {
		this.videoControl1 = getVideoControl();

		this.videoControl1.initDisplayMode(VideoControl.USE_DIRECT_VIDEO,
				mobileDialog.getCanvas());

		if (mPlayer.getState() == Player.REALIZED
				|| mPlayer.getState() == Player.PREFETCHED) {
			mPlayer.start();
		} else {
			pararCamera();
			throw new Exception("mPlayer já inicializado ou fechado.");
		}
		videoControl1.setVisible(true);
		int width = this.getWidth();
		int height = this.getHeight() - (top);
		int tamanho = 0;
		if (width >= height) {
			tamanho = height - 4;
			System.out.println("width >= height");
		} else {
			System.out.println("else");
			tamanho = width;
		}
		
		int espaco = (int)(getHeight() *0.5f) ;

		try {
			videoControl1.setDisplayLocation(0, top + 5);
			videoControl1.setDisplaySize(width, espaco);
		} catch (MediaException me) {
			try {
				videoControl1.setDisplayLocation(0, 0);
				videoControl1.setDisplayFullScreen(true);
			} catch (Exception me2) {
				pararCamera();
				throw new Exception("Falha no dimensionamento da tela de captura.");
			}
		}

	}

	public void pararCamera() throws Exception {
		// videoControl1 = null;
		if(mPlayer.getState() != Player.CLOSED)
			mPlayer.close();
	}

}