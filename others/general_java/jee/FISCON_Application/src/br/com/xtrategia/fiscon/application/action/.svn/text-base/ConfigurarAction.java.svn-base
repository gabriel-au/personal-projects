package br.com.xtrategia.fiscon.application.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import br.com.xtrategia.fiscon.application.Configucacao;
import br.com.xtrategia.fiscon.application.FisconAction;

public class ConfigurarAction extends FisconAction{

	private static final long serialVersionUID = 1L;
	
	public ConfigurarAction() {
		super("Configurar");
		this.putValue(Action.SMALL_ICON,createImageIcon("/imagens/configurar.gif"));
		this.putValue(Action.SHORT_DESCRIPTION, "Configurações");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Configucacao.getInstance().abrirTelaConfiguracao();
	}

}
