package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.Menu;
import br.com.codequest.mobile.ui.components.MenuItem;

public class MenuAutoInfracao extends MobileDialog {

	public MenuAutoInfracao() {
		super("");
	}

	public void init() {
		Menu menu = new Menu(this);
		menu.add(new MenuItem("/icones/camera32.png",
				"Em Serie", new AutoInfracaoConsultaInfracaoTipo(this), this));
		menu.add(new MenuItem("/icones/cameraUnica32.png",
				"Com Foto", new AutoInfracaoFoto1(this), this));
		menu.add(new MenuItem("/icones/cameraUnicaSem32.png",
				"Sem Foto", new AutoInfracaoConsultaVeiculo(true), this));
		menu.add(new MenuItem("/icones/recuperar32.png",
				"Recuperar", new AutoInfracaoIncompleto(), this));
		menu.add(new MenuItem("/icones/cancel32.png",
				"Cancelar", new AutoInfracaoCancelarEnviada(), this));
		append(menu,true);
		/*append(new LabelMenu("Auto de Infração em série", new AutoInfracaoConsultaInfracaoTipo(this), this));
		append(new LabelMenu("Auto de Infração com Foto", new AutoInfracaoFoto1(), this),true);
		append(new LabelMenu("Auto de Infração Sem Foto", new AutoInfracaoConsultaVeiculo(true), this));
		append(new LabelMenu("Auto de Infração Incompletos", new AutoInfracaoIncompleto(), this));
		append(new LabelMenu("Cancelar Auto de Infração", new AutoInfracaoCancelarEnviada(), this));*/
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	public void executarLeft() {
		new MenuPrincipal().show();
	}
}
