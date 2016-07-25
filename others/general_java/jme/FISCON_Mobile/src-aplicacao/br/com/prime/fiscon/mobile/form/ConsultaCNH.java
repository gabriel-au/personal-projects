package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextData;
import br.com.codequest.mobile.ui.components.TextNumerico;

public class ConsultaCNH extends MobileDialog {
	private TextNumerico cnh;
	private TextData  data;
	
	public ConsultaCNH() {
		super("Consultar CNH");
	}

	public void init() {
		cnh= new TextNumerico("Número da CNH");
		data = new TextData("Data Nascimento");
		append(cnh,true);
		append(data);
	}

	public void show () {
		cnh.setString("");
		data.setString("");
		super.show();
	}
	protected void definirMenu() {
		setMenuText("Voltar", "Consultar");
	}
	
	public void executarRight() {
		new ConsultaCNHAguarde(this).show();
	}
}