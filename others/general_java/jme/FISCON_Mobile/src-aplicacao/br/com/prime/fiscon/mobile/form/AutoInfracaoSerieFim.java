package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;

public class AutoInfracaoSerieFim extends MobileDialog {

	public AutoInfracaoSerieFim() {
		super("Aviso");
		// TODO Auto-generated constructor stub
	}

	protected void init() {
		try {
			append(new Whitespace(35));
			append(new Picture(Image.createImage("/icones/transacao_efetivada.png"), Graphics.HCENTER));
		} catch( Exception e) {
			
		}
		append(new Whitespace(20));
		append(new Label("Infrações geradas com sucesso!", Graphics.HCENTER));
	}
	
	protected void definirMenu() {
		setMenuText("", "OK");
	}

	public void executarLeft() {
	}

	public void executarRight() {
		new MenuPrincipal().show();
	}

}
