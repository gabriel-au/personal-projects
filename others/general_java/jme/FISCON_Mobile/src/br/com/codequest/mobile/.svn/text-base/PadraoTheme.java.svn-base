package br.com.codequest.mobile;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;

/**
 * Classe para corrigir o bug de gradient da classe theme
 * 
 * @author 64732436153
 * 
 */
public class PadraoTheme extends Theme {

	private int titleHeight=0;
	
	/**
	 * Fills a rectangle with linear gradient. The gradient colors go from
	 * <code>primaryColor</code> to <code>secondaryColor</code> at
	 * <code>maxSecondary</code>. So if <code>maxSecondary == 0.70</code> then a
	 * line across the fill rectangle 70% of the way would be
	 * <code>secondaryColor</code>.
	 * 
	 * @param g
	 *            is the <code>Graphics</code> object for painting.
	 * @param x
	 *            is the left edge of the rectangle.
	 * @param y
	 *            is the top edge of the rectangle.
	 * @param width
	 *            is the width of the rectangle.
	 * @param height
	 *            is the height of the rectangle.
	 * @param fillVertically
	 *            is <code>true</code> if the gradient goes from top-to-bottom
	 *            or <code>false</code> for left-to-right.
	 * @param primaryColor
	 *            is the main color.
	 * @param secondaryColor
	 *            is the highlight color.
	 * @param maxSecondary
	 *            is between 0.00 and 1.00 and says how far down the fill will
	 *            <code>secondaryColor</code> peak.
	 */
	public void gradientFill (
			Graphics g,
			int x, int y, int width, int height,
			boolean fillVertically,
			int primaryColor, int secondaryColor, double maxSecondary)
	{
		// Break the primary color into red, green, and blue.
		int pr = (primaryColor & 0x00FF0000) >> 16;
		int pg = (primaryColor & 0x0000FF00) >> 8;
		int pb = (primaryColor & 0x000000FF);

		// Break the secondary color into red, green, and blue.
		int sr = (secondaryColor & 0x00FF0000) >> 16;
		int sg = (secondaryColor & 0x0000FF00) >> 8;
		int sb = (secondaryColor & 0x000000FF);

		// Draw a horizonal line for each pixel from the top to the bottom.
		int end = (fillVertically ? height : width);

		int tr = (sr - pr) / end;
		int tg = (sg - pg) / end;
		int tb = (sb - pb) / end;

		for (int i = 0; i < end; i++) {
			int red = pr + tr * i;
			int green = pg + tg * i;
			int blue = pb + tb * i;

			g.setColor(red, green, blue);

			// Draw the line.
			if (fillVertically) {
				g.drawLine(x, y + i, x + width, y + i);
			} else // horizontal
			{
				g.drawLine(x + i, y, x + i, y + height);
			}
		}
	}

	
	public int getTitleHeight (){
		return ((titleHeight!=0)?titleHeight:getTitleFont().getHeight() + 2);
	}
	
	public void setTitleHeight(int titleHeight){
		this.titleHeight=titleHeight;
	}
}
