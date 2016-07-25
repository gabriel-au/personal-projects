package br.com.codequest.mobile.ui.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.j4me.ui.Theme;
import org.j4me.ui.components.Picture;

/**
 * Classe para Implementar um gráfico
 *
 */
public class LineChartCustom extends Picture{
    private int width;
    private int heightTotal;
    private int heightGraph;
    
    private short[] data;
    
    private Font font;
    
    public final static byte RED = 0;
    public final static byte LIME = 1;
    public final static byte BLUE = 2;
    public final static byte YELLOW = 3;
    public final static byte AQUA = 4;
    public final static byte FUCHSIA = 5;
    public final static byte WHITE = 6;
    public final static byte BLACK = 7;
    public final static byte MAROON = 8;
    public final static byte GREEN = 9;
    public final static byte NAVY = 10;
    public final static byte OLIVE = 11;
    public final static byte TEAL = 12;
    public final static byte PURPLE = 13;
    public final static byte SILVER = 14;
    public final static byte GRAY = 15;
    
    private int colorHexaBack;
    private int colorHexaLine;
    
    //representa o valor m?ximo, de posso deste valor, podemos fazer uma escala vertical do gr?fico
    private short maxValue;
    
    //n?mero de linhas horizontais
    private short nHorLines;
    private boolean horLines;
    
    private Font minorFont;
    
    private short indVerticalBar;
    
    /**
     * Creates a new instance of PieChart.
     * @param width - width of chart
     * @param height - height of chart
     * @param data - values of data
     * @param legend - labels of data
     * @param idData - identification of the data set
     * @param sizeFont - size of fonte of text
     * @param colorLine - color of line
     * @param colorBack - color of background
     * @param maxValue - bigger value of the data set
     */
    public LineChartCustom(int width, int height, short[] data, String[] legend, String idData, int sizeFont, byte colorLine, 
            byte colorBack, short maxValue) {
        //super("");
        
        this.width = width;  
        this.data = data;  
        
        switch (colorLine)
        {
            case RED:
                colorHexaLine = 0xff0000;
                break;
            case LIME:
                colorHexaLine = 0x00ff00;
                break;
            case BLUE:
                colorHexaLine = 0x0000ff;
                break;
            case YELLOW:
                colorHexaLine = 0xffff00;
                break;
            case AQUA:
                colorHexaLine = 0x00ffff;
                break;
            case FUCHSIA:
                colorHexaLine = 0xff00ff;
                break;
            case WHITE:
                colorHexaLine = 0xffffff;
                break;
            case BLACK:
                colorHexaLine = 0x000000;
                break;
            case MAROON:
                colorHexaLine = 0x800000;
                break;
            case GREEN:
                colorHexaLine = 0x008000;
                break;
            case NAVY:
                colorHexaLine = 0x000080;
                break;
            case OLIVE:
                colorHexaLine = 0x808000;
                break;
            case TEAL:
                colorHexaLine = 0x008080;
                break;
            case PURPLE:
                colorHexaLine = 0x800080;
                break;
            case SILVER:
                colorHexaLine = 0xc0c0c0;
                break;
            case GRAY:
                colorHexaLine = 0x808080;
        }
        
        switch (colorBack)
        {
            case RED:
                colorHexaBack = 0xff0000;
                break;
            case LIME:
                colorHexaBack = 0x00ff00;
                break;
            case BLUE:
                colorHexaBack = 0x0000ff;
                break;
            case YELLOW:
                colorHexaBack = 0xffff00;
                break;
            case AQUA:
                colorHexaBack = 0x00ffff;
                break;
            case FUCHSIA:
                colorHexaBack = 0xff00ff;
                break;
            case WHITE:
                colorHexaBack = 0xffffff;
                break;
            case BLACK:
                colorHexaBack = 0x000000;
                break;
            case MAROON:
                colorHexaBack = 0x800000;
                break;
            case GREEN:
                colorHexaBack = 0x008000;
                break;
            case NAVY:
                colorHexaBack = 0x000080;
                break;
            case OLIVE:
                colorHexaBack = 0x808000;
                break;
            case TEAL:
                colorHexaBack = 0x008080;
                break;
            case PURPLE:
                colorHexaBack = 0x800080;
                break;
            case SILVER:
                colorHexaBack = 0xc0c0c0;
                break;
            case GRAY:
                colorHexaBack = 0x808080;
        }
        
        this.maxValue = maxValue;
        
        nHorLines = 4;
        horLines = true;
        
        indVerticalBar = 0;
        
        font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);  
        this.heightGraph = height;
        
        minorFont = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);  
        this.heightTotal = height+(font.getHeight())+3+minorFont.getHeight();
    }

    /**
     * Implemented by the subclass of CustomItem to return the minimum width of the content area, in pixels. This method is called by the implementation as part of its layout algorithm. The actual width granted is reported in the sizeChanged and paint methods.
     * @return the minimum content width in pixels
     */
    protected int getMinContentWidth() {
        return width;
    }

    /**
     * Implemented by the subclass of CustomItem to return the minimum height of the content area, in pixels. This method is called by the implementation as part of its layout algorithm. The actual height granted is reported in the sizeChanged and paint methods.
     * @return the minimum content height in pixels
     */
    protected int getMinContentHeight() {
        return heightTotal;
    }

    /**
     * Implemented by the subclass of CustomItem to return the preferred width of the content area, in pixels. This method is called by the implementation as part of its layout algorithm.
     * The height parameter is the tentative height assigned to the content area. The subclass code may use this value in its computation of the preferred width. The height parameter will be -1 if the implementation has not assigned a tentative value for the height. Otherwise, height will have a specific value if the application has locked the height of the CustomItem or if the container's layout algorithm has already computed a tentative height at the time of this call. The subclass must not assume that the tentative height passed or the preferred width returned will be granted. The actual size granted is reported in the sizeChanged and paint methods. <p>
     * @param height - the tentative content height in pixels, or -1 if a tentative height has not been computed
     * @return the preferred content width in pixels
     */
    protected int getPrefContentWidth(int i) {
        return width;
    }

    /**
     * Implemented by the subclass of CustomItem to return the preferred height of the content area, in pixels. This method is called by the implementation as part of its layout algorithm.
     * The width parameter is the tentative width assigned to the content area. The subclass code may use this value in its computation of the preferred height. The width parameter will be -1 if the implementation has not assigned a tentative value for the width. Otherwise, width will have a specific value if the application has locked the width of the CustomItem or if the container's layout algorithm has already computed a tentative width at the time of this call. The subclass must not assume that the tentative width passed or the preferred height returned will be granted. The actual size granted is reported in the sizeChanged and paint methods.<p>
     * @param width - the tentative content width in pixels, or -1 if a tentative width has not been computed
     * @return the preferred content height in pixels
     */
    protected int getPrefContentHeight(int i) {
        return heightTotal;
    }

    /**
     * Implemented by the subclass of CustomItem to render the item within its container. At the time of the call, the Graphics context's destination is the content area of this CustomItem  (or back buffer for it). The Translation is set so that the upper left corner of the content area is at (0,0), and the clip is set to the area to be painted. The application must paint every pixel within the given clip area. The item is allowed to modify the clip area, but the system must not allow any modification to result in drawing outside the bounds of the item's content area. The w and h passed in are the width and height of the content area of the item. These values will always be equal to the values passed with the most recent call to sizeChanged(); they are passed here as well for convenience.
     * @param g - the Graphics object to be used for rendering the item
     * @param w - current width of the item in pixels
     * @param h - current height of the item in pixels
     */
    //protected void paint(Graphics g, int w, int h) {
    protected void paintComponent (Graphics g, Theme theme, int width, int height, boolean selected){
        float aux = minorFont.stringWidth("99,9");
        float aux2 = aux;
        float aux3;
        float calculo = 0;
        
        int comGra = minorFont.getHeight()/2;
      
        g.setColor(colorHexaBack);
        g.fillRect(minorFont.stringWidth("99,9"), comGra, (int)(width-(9+aux)), heightGraph);
        
        g.setColor(0, 0, 0);
        g.drawRect(minorFont.stringWidth("99,9"), comGra, width-(9+minorFont.stringWidth("99,9")), heightGraph);
        
        float pontos = ((float)(width-(9+aux))/(data.length-1));
        
        //pontos = (float)Math.ceil(pontos);
        pontos = ceil(pontos);
        
        g.setFont(font);
        
        g.setColor(0, 0 ,0);
        aux2 = (float)((float)maxValue/(float)nHorLines);
        aux3 = (float)((float)heightGraph/(float)nHorLines);
        
        g.drawString(""+maxValue, 0, 0, Graphics.LEFT|Graphics.TOP);
        for (int l = nHorLines-1, m = 1; l != 0; l--, m++)
        {
            g.drawString(new String(""+(float)(aux2*l)).substring(0, 4), 0, (int)(aux3*m)-minorFont.getHeight()/2+comGra, Graphics.LEFT|Graphics.TOP);
        }
        g.drawString("0", 0, heightGraph, Graphics.LEFT|Graphics.TOP);
        
        
        if (horLines)
        {
            aux2 = heightGraph/nHorLines;
            
            g.setStrokeStyle(Graphics.DOTTED);
             for (int l = nHorLines-1; l != 0; l--)
            {
                 g.drawLine(minorFont.stringWidth("99,9"), (int)(aux2*l)+comGra, width-9, (int)(aux2*l)+comGra);
            }
        }
        
        g.setStrokeStyle(Graphics.DOTTED);
        for (int j = 1; j <= data.length; j++)
        {    
            if (indVerticalBar == j)
                g.setColor(colorHexaLine);
            else
                g.setColor(0, 0, 250);
            
            
            g.drawLine(((int)(j*pontos))+minorFont.stringWidth("99,9"), heightGraph+comGra, ((int)(j*pontos))+minorFont.stringWidth("99,9"), comGra);
                      
            
        }
        g.setStrokeStyle(Graphics.SOLID);
        
        
        g.setColor(colorHexaLine);
        
        float backup = heightGraph;
        
        calculo = (Float.parseFloat(""+data[0]))*((float)((float)heightGraph/(float)maxValue));
        calculo = ceil(calculo);
        calculo = heightGraph-(int)calculo;
        backup = calculo+comGra;
        
        
        for (int j = 1; j < data.length; j++)
        {
            calculo = (Float.parseFloat(""+data[j]))*((float)((float)heightGraph/(float)maxValue));
            calculo = ceil(calculo);
            calculo = heightGraph-(int)calculo+comGra;
            g.drawLine((int)aux, (int)backup, (int)(aux+pontos), (int)calculo);
            
            backup = calculo;
            aux += pontos;
        }
        
        
    }
    protected int[] getPreferredComponentSize (Theme theme, int viewportWidth, int viewportHeight)
	{
		return new int[] { width, heightTotal };
	}

    public int ceil(float valor){
    	int i = (int)valor;
    	return ++i;
    }
    
}
