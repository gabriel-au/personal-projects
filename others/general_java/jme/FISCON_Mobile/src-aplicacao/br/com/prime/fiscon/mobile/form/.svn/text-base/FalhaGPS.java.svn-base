package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.ErroDialog;

public class FalhaGPS extends ErroDialog {

	public FalhaGPS(String titulo, String texto, MobileDialog form) {
		super(titulo, texto, form);
		setPrevScreen(form);
	}
	public void init() {
		super.init();
		setMenuText("Tentar Novamente", "Prosseguir");
	}
	public void executarRight() {
		new AutoInfracaoConsultaBairro(this.getPrevScreen()).show();
	}
	
	public void executarLeft() {
		new AguardeLerGPS(this).show();
	}
}
