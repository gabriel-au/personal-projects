package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextPlaca;

public class ConsultaVeiculos extends MobileDialog {
	private TextPlaca txtPlaca;
	
	public ConsultaVeiculos() {
		super("Consulta de Veículos");
	}
	
	public void init() {
		txtPlaca = new TextPlaca("Placa");
		append(txtPlaca, true);
	}
	
	public void show() {
		txtPlaca.setString("");
		repaint();
		super.show();
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Consultar");
	}
	
	public void executarRight() {
		new ConsultaVeiculosAguarde(this).show();
	}
}
