package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.ErroDialog;

public class FalhaConsultaCHN extends ErroDialog {

	public FalhaConsultaCHN(String titulo, String texto, MobileDialog form) {
		super(titulo, texto, form);
	}
	public void init() {
		super.init();
		setMenuText("Tentar Novamente", "Prosseguir");
	}
	public void executarRight() {
		AutoInfracaoInformarCNH informarCNH = new AutoInfracaoInformarCNH();
		informarCNH.setPrevScreen(this);
		informarCNH.show();
	}
	public void executarLeft() {
		new AutoInfracaoConsultaCNH().show();
	}
	
}
