package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;

public class AutoInfracaoSerieConfirmaNovaPlaca extends MobileDialog {
	Label lblPlaca;

	public AutoInfracaoSerieConfirmaNovaPlaca(MobileDialog dialog) {
		super("Confirmação");
		this.setPrevScreen(dialog);
	}

	protected void init() {
		append(new Whitespace(30));
		try {
			append(new Picture(Image.createImage("/icones/pergunta.png"),
					Graphics.HCENTER));
		} catch (Exception e) {
		}
		append(new Label("Deseja adicionar uma nova placa?", Graphics.HCENTER));
	}
	
	protected void definirMenu() {
		setMenuText("Não", "Sim");
	}
	public void executarLeft () {
		new AutoInfracaoSerieAguardeSalvar(this).show();
	}
	public void executarRight() {
		new AutoInfracaoSeriePlaca(this.getPrevScreen().getPrevScreen()).show();
	}

}
