package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.util.GravarEstatistica;

public class MenuRelatorioDiario extends MobileDialog {

	private Label l1;
	private Label l2;
	private Label l3;
	
	public MenuRelatorioDiario() {
		super("Relatório Diário");
	}

	public void init() {
		String parametros[] = GravarEstatistica.LerConsula();
		l1 = new Label("Multas: "+parametros[0]);
		l2 = new Label("Consultas Veículos: "+parametros[1]);
		l3 = new Label("Consultas CNH: "+parametros[2]);
		append(l1);
		append(l2);
		append(l3);
	}
	
	public void show() {
		String parametros[] = GravarEstatistica.LerConsula();
		l1.setLabel("Multas: "+parametros[0]);
		l2.setLabel("Consultas Veículos: "+parametros[1]);
		l3.setLabel("Consultas CNH: "+parametros[2]);
		super.show();
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}
