package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.Menu;
import br.com.codequest.mobile.ui.components.MenuItem;

public class MenuConfiguracoes extends MobileDialog {

	
	public MenuConfiguracoes() {
		super("Configurações");
		
	}

	public void init() {
		Menu menu = new Menu(this);
		
		menu.add(new MenuItem("/icones/impressora32.png",
				"Impressora", new AguardeConfigurarImpressora(this, false), this));
		menu.add(new MenuItem("/icones/lixeira32.png",
				"Limpar", new LimparBD(this), this));
		append(menu,true);
		/*append(new LabelMenu("Configurar Impressora", new AguardeConfigurarImpressora(this, false),this),true);
		append(new LabelMenu("Limpar Base de dados local", new LimparBD(this),this),true);*/
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}
