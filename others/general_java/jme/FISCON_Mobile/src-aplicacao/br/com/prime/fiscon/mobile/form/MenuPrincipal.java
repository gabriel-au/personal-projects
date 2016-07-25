package br.com.prime.fiscon.mobile.form;

import java.util.Calendar;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.Logout;
import br.com.codequest.mobile.ui.components.Menu;
import br.com.codequest.mobile.ui.components.MenuItem;
import br.com.prime.fiscon.mobile.nucleo.Usuario;

public class MenuPrincipal extends MobileDialog {
	Label saudacao;

	public MenuPrincipal() {
		super("");
	}

	public void show() {
		Calendar cal = Calendar.getInstance();
		String msg;
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		if (hora >= 0 && hora < 12) {
			msg = "Bom dia";
		} else if (hora >= 12 && hora < 18) {
			msg = "Boa tarde";
		} else {
			msg = "Boa noite";
		}

		msg += ", ";
		msg += Usuario.getInstance().getNome();
		msg += " ";
		msg += Usuario.getInstance().getSobrenome();

		if (saudacao != null) {
			saudacao.setLabel(msg);
		}

		super.show();
	}

	public void init() {
		append(new Whitespace(2));
		saudacao = new Label("", Graphics.HCENTER);
		append(saudacao);
		Menu menu = new Menu(this);
		menu.add(new MenuItem("/icones/Lupa32.png", "Consultas",
				new MenuConsultas(), this));
		menu.add(new MenuItem("/icones/policial32.png", "A.I.",
				new MenuAutoInfracao(), this));
		menu.add(new MenuItem("/icones/relatorio32.png", "Relatorio",
				new MenuRelatorioDiario(), this));
		menu.add(new MenuItem("/icones/configure-32.png", "Config.",
				new MenuConfiguracoes(), this));
		menu
				.add(new MenuItem("/icones/ajuda32.png", "Ajuda", new Ajuda(),
						this));
		menu
				.add(new MenuItem("/icones/sair32.png", "Sair", new Logout(),
						this));
		append(menu, true);

		/*
		 * append(new LabelMenu("Consultas", new MenuConsultas(),this),true);
		 * append(new LabelMenu("Auto de Infração", new
		 * MenuAutoInfracao(),this)); append(new LabelMenu("Relatório Diário",
		 * new MenuRelatorioDiario(),this)); append(new
		 * LabelMenu("Configurações", new MenuConfiguracoes(),this)); append(new
		 * LabelMenu("Ajuda", new Ajuda(),this)); append(new LabelMenu("Logout",
		 * new Logout(),this));
		 */
	}

	protected void definirMenu() {
		setMenuText("Sair", "");
	}

}
