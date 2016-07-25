package br.com.codequest.mobile.ui;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;

public class MensagemDialog extends MobileDialog {

	public MensagemDialog(String titulo, String texto, MobileDialog form) {
		super(titulo);
		append(new Whitespace(50));
		appendPicture("/icones/transacao_efetivada.png", Graphics.HCENTER);
		append(new Whitespace(10));
		append(new Label(texto, Graphics.HCENTER));
		setPrevScreen(form);
	}

	public void init() {
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}