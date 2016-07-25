package br.com.codequest.mobile.ui.components;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.Component;

public class Loading extends Component {
	public int change;
	private Timer timer;
	private int squares = 5;
	private boolean border = false;
	private int width= 25;
	private int height=15;
	private static final int MAX_CHANGE = 80;
	private static final int CHANGE_INTERVAL = 1500 / 5;
	private int color =0x006699CC;
	private int activeColor = 0x00003366;
	private int borderColor = Theme.BLACK;

	public Loading() {
		super();
		change = 1;
		this.setHorizontalAlignment(Graphics.HCENTER);
	}

	public Loading(int squares) {
		this();
		this.squares = squares;
	}

	public int getWidth() {
		return width;
	}

	public int getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isBorder() {
		return border;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getActiveColor() {
		return activeColor;
	}

	public void setActiveColor(int backgroundColor) {
		this.activeColor = backgroundColor;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	protected int[] getPreferredComponentSize(Theme theme, int viewportWidth,
			int viewportHeight) {
		return new int[] { viewportWidth, 15 };
	}

	protected void paintComponent(Graphics g, Theme theme, int width,
			int height, boolean selected) {
		int x = 0;
		int y = 0;
		int w = this.width;
		int h = this.height;
		int space = 3;
		int barSize = squares * (space + w);
		int round1 = 5;
		int round2 = round1;
		// Get the location of the progress bar.

		int horizontalAlignment = getHorizontalAlignment();

		if (horizontalAlignment == Graphics.HCENTER) {
			x += (width - barSize) / 2;
		}

		int fill = change % squares;
		for (int i = 0; i < squares; i++) {
			if (this.isBorder()) {
				g.setColor(this.getBorderColor());
				g.drawRect(space * i + x + i * w, y, w, h);
			}
			if (fill == i) {
				g.setColor(this.getActiveColor());
				g.fillRoundRect((space * i + x + i * w) + 1, y + 1, w - 1,
						h - 1, round1, round2);
			} else {
				g.setColor(this.getColor());
				g.fillRoundRect((space * i + x + i * w) + 1, y + 1, w - 1,
						h - 1, round1, round2);
			}

		}
		startTimer();
	}

	private void startTimer() {
		if (timer == null) {
			timer = new Timer();
			timer.schedule(new LoadingTask(), 0, CHANGE_INTERVAL);
		}
	}

	public void start() {
		startTimer();
	}
	public void stop() {
		if (timer != null) {
			timer.cancel();
		}
	}

	public class LoadingTask extends TimerTask {
		public void run() {
			change++;
			if (change > MAX_CHANGE) {
				this.cancel();
			}
			Loading.this.repaint();
		}
	}

}
