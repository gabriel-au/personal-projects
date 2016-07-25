package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoFoto1Confirma extends MobileDialog {
	
	public AutoInfracaoFoto1Confirma(MobileDialog prevScreen) {
		super("Confirmar a Foto da placa");
		Picture picture = new Picture();
		picture.setImage(Image.createImage(AutoInfracaoPojo.getInstance().getPrimeiraFotoPojo().getDado(),0,AutoInfracaoPojo.getInstance().getPrimeiraFotoPojo().getDado().length));
		
		append(picture);
		this.setPrevScreen(prevScreen);
		
	}

	public void init() {
		append(new Label("Confirma a foto da placa?"));
		AutoInfracaoFoto2 aif2 = new AutoInfracaoFoto2(this);
		
		setLastScreen(aif2);
	}

	protected void definirMenu() {
		setMenuText("Não", "Sim");
	}
}
