package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextData;
import br.com.codequest.mobile.ui.components.TextNumerico;

public class AutoInfracaoConsultaCNH extends MobileDialog {
	
	public AutoInfracaoConsultaCNH() {
		super("Consultar CNH");
	}

	public void init() {
		append(new TextNumerico("Número da CNH"),true);
		append(new TextData("Data Nascimento"));
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Consultar");
	}
	
	public void executarRight() {
		new AutoInfracaoConsultaCNHAguarde(this).show();
	}
	
}