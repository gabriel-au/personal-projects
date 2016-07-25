package br.com.xtrategia.fiscon.application.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JOptionPane;

import br.com.xtrategia.fiscon.application.FisconAction;

public class SobreAction extends FisconAction{

	private static final long serialVersionUID = 1L;
	
	public SobreAction() {
		super("Sobre...");
		this.putValue(Action.SMALL_ICON, createImageIcon("/imagens/sobre.gif"));
		this.putValue(Action.SHORT_DESCRIPTION, "Sobre...");
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		JOptionPane.showMessageDialog(null,
				"FISCON..." +
				"\n      XTRATEGIA", "Sobre...",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
