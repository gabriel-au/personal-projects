package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;

public class AutoInfracaoSalvar extends MobileDialog {

	
	public AutoInfracaoSalvar() {
		super("Tirar Foto 2");
	}

	public void init() {
		AutoInfracaoConsultaVeiculo aic = new AutoInfracaoConsultaVeiculo();
		aic.setPrevScreen(this);
		
		setLastScreen(aic);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Próximo");
	}
	
}
