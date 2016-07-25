package br.com.codequest.mobile.ui;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;

public class Logout extends MobileDialog {
	
	public Logout(){
		super("Atenção");
		append(new Label("Deseja Sair da Aplicação?"));
	}

	public void init() {
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Sair");
	}

	
	public void executarRight() {
		sair();
	}
	
}
