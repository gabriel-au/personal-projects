package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoFotoConfirma extends MobileDialog {
	
	public AutoInfracaoFotoConfirma() {
		super("Confirmar a Foto da placa");
		Picture picture = new Picture();
		picture.setImage(Image.createImage(AutoInfracaoPojo.getInstance().getPrimeiraFotoPojo().getDado(),0,AutoInfracaoPojo.getInstance().getPrimeiraFotoPojo().getDado().length));
		
		append(picture);
		
		
	}

	public void init() {
		append(new Label("Confirma a foto da placa?"));
		
	}

	protected void definirMenu() {
		setMenuText("Não", "Sim");
	}
	public void executarRight() {
		new AutoInfracaoFoto().show();
	}
	
	public void executarLeft() {
		new AutoInfracaoFoto().show();
	}
}
