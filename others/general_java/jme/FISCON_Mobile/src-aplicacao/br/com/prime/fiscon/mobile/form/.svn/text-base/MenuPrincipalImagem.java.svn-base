package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.Logout;
import br.com.codequest.mobile.ui.components.Menu;
import br.com.codequest.mobile.ui.components.MenuItem;

public class MenuPrincipalImagem extends MobileDialog {

	public MenuPrincipalImagem() {
		super("Menu");
	}

	public void init() {
		Menu menu = new Menu(this);
		
		menu.add(new MenuItem("/icones/transacao_efetivada.png",
				"Consultas", new MenuConsultas(), this));
		menu.add(new MenuItem("/icones/transacao_efetivada.png",
				"Auto de Infração", new MenuAutoInfracao(), this));
		menu.add(new MenuItem("/icones/transacao_efetivada.png",
				"Relatório Diário", new MenuRelatorioDiario(), this));
		menu.add(new MenuItem("/icones/transacao_efetivada.png",
				"Configurações", new MenuConfiguracoes(), this));
		menu.add(new MenuItem("/icones/transacao_efetivada.png",
				"Ajuda", new Ajuda(), this));
		menu.add(new MenuItem("/icones/transacao_efetivada.png",
				"Logout", new Logout(), this));
		/*append(new LabelMenu("Consultas", new MenuConsultas(), this), true);
		append(new LabelMenu("Auto de Infração", new MenuAutoInfracao(), this));
		append(new LabelMenu("Relatório Diário", new MenuRelatorioDiario(),
				this));
		append(new LabelMenu("Configurações", new MenuConfiguracoes(), this));
		append(new LabelMenu("Ajuda", new Ajuda(), this));
		append(new LabelMenu("Logout", new Logout(), this));*/
		append(menu);
	}

	protected void definirMenu() {
		setMenuText("Sair", "");
	}

}
