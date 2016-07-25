package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.ErroDialog;

public class FalhaConsultaVeiculo extends ErroDialog {

	public FalhaConsultaVeiculo(String titulo, String texto, MobileDialog form) {
		super(titulo, texto, form);
	}
	public void init() {
		super.init();
		setMenuText("Tentar Novamente", "Prosseguir");
	}
	public void executarRight() {
		new AutoInfracaoInformarVeiculo().show();
	}
	public void executarLeft() {
		new AutoInfracaoConsultaVeiculo().show();
	}
	
}
