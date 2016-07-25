package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;

public class Ajuda extends MobileDialog {

	
	public Ajuda() {
		super("Ajuda");
	}

	public void init() {
		
		Label titulo1 =new Label("Consultas");
		Label titulo2 =new Label("Auto de Infração");
		Label titulo3 =new Label("Relatório Diário");
		Label titulo4 =new Label("Logout");
		titulo1.setHorizontalAlignment(Graphics.HCENTER);
		titulo2.setHorizontalAlignment(Graphics.HCENTER);
		titulo3.setHorizontalAlignment(Graphics.HCENTER);
		titulo4.setHorizontalAlignment(Graphics.HCENTER);
		
		append(titulo1);
		append(new Label("As Consultas servem para pesquisar dados dos veículos " +
				"ou dos dados do condutos através da CNH"));
		append(titulo2);
		append(new Label("A tela de Auto de Infração tem como funcionalidade emitir um multa"));
		append(titulo3);
		append(new Label("A Tela de Relatório Diário fornece estatísticas do uso da aplicação pelo periodo de um dia."));
		append(titulo4);
		append(new Label("A tela de Logout encerra a sessão da aplicação"));
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}
