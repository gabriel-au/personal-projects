package br.com.codequest.mobile;

import java.io.IOException;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.Dialog;
import org.j4me.ui.Theme;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.TextBox;
import org.j4me.ui.components.Whitespace;


public abstract class AbstractDialog extends Dialog {

    public void appendString(String text) {
    	Label label = new Label();
        label.setLabel(text);
        label.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        label.setFontColor(Theme.BLACK);
        append(label);
    }

    public void appendString(String text, int alignment) {
    	Label label = new Label();
        label.setLabel(text);
        label.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        label.setFontColor(Theme.BLACK);
        label.setHorizontalAlignment(alignment);
        append(label);
    }

    public void appendString(String text, int color, int alignment) {
    	Label label = new Label();
        label.setLabel(text);
        label.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        label.setFontColor(color);
        label.setHorizontalAlignment(alignment);
        append(label);
    }

    public void appendString(String text, boolean isTitle) {
    	Label label = new Label();
        label.setLabel(text);

        if (isTitle) {
            label.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
        } else {
            label.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        }
        
        label.setFontColor(Theme.BLACK);
        append(label);
    }

    public void appendPicture(String src, int alignment) {
    	Picture picture = new Picture();
        
        try {
            picture.setImage(Image.createImage(src));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        picture.setHorizontalAlignment(alignment);

        append(picture);
    }

    public void appendWhitespace(int size) {
    	Whitespace whitespace = new Whitespace(size);
        append(whitespace);
    }

    public void appendPassword(int digits) {
    	TextBox textBoxPassword = new TextBox();
        textBoxPassword.setLabel("Senha (" + digits + " dígitos):");
        textBoxPassword.setForNumericOnly();
        textBoxPassword.setPassword(true);
        textBoxPassword.setMaxSize(digits);
        append(textBoxPassword);
    }

    public void appendTransacaoEfetivada() {
        Label labelTransacaoEfetivada = new Label("Transação efetivada");
        Font font = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        labelTransacaoEfetivada.setFont(font);
        labelTransacaoEfetivada.setFontColor(Theme.BLACK);
        labelTransacaoEfetivada.setHorizontalAlignment(Graphics.HCENTER);

        appendWhitespace(30);
        appendPicture("/icones/transacao_efetivada.png", Graphics.HCENTER);

        appendWhitespace(10);
        append(labelTransacaoEfetivada);
        appendWhitespace(10);
    }

    public void appendTransacaoEfetivada(String mensagem) {
        Label labelTransacaoEfetivada = new Label(mensagem);
        Font font = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        labelTransacaoEfetivada.setFont(font);
        labelTransacaoEfetivada.setFontColor(Theme.BLACK);
        labelTransacaoEfetivada.setHorizontalAlignment(Graphics.HCENTER);

        appendWhitespace(30);
        appendPicture("/icones/transacao_efetivada.png", Graphics.HCENTER);

        appendWhitespace(10);
        append(labelTransacaoEfetivada);
        appendWhitespace(10);
    }
    
    public abstract boolean run();

    /**
	 * @param erroForm the erroForm to set
	 */
	public void setErroForm(Dialog lastScreen, String mensagem, String title) {
		//this.erroForm = new ErroForm(lastScreen, transacao, title);
	}


}