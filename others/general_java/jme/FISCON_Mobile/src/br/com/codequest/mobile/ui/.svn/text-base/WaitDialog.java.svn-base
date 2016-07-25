package br.com.codequest.mobile.ui;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;

public class WaitDialog extends MobileDialog {

	public WaitDialog(String titulo, String texto, MobileDialog form) {
		super(titulo);
		append(new Label(texto));
		setPrevScreen(form);
		appendPicture("/icones/aguarde.gif", Graphics.HCENTER);
	}

	public void init() {
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}