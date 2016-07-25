package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.Menu;
import br.com.codequest.mobile.ui.components.MenuItem;

public class MenuConsultas extends MobileDialog {

	
	public MenuConsultas() {
		super("Consultas");
	}

	public void init() {
		Menu menu = new Menu(this);
		
		menu.add(new MenuItem("/icones/fusca32.png",
				"Veículos", new ConsultaVeiculos(), this));
		menu.add(new MenuItem("/icones/cnh32.png",
				"CNH", new ConsultaCNH(), this));
		append(menu,true);
		/*append(new LabelMenu("Consultar Veículos", new ConsultaVeiculos(),this),true);
		append(new LabelMenu("Consultar CNH", new ConsultaCNH(),this));
		append(new LabelMenu("Consultar por CPF",new ConsultaCNHporCPF(),this));*/
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}
