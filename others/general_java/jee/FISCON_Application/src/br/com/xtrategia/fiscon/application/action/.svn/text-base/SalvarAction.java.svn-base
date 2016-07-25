package br.com.xtrategia.fiscon.application.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.com.xtrategia.fiscon.application.FisconAction;
import br.com.xtrategia.fiscon.application.FisconMain;

public class SalvarAction extends FisconAction{

	private static final long serialVersionUID = 1L;
	private FisconMain frame;
	
	public SalvarAction(FisconMain frame) {
		super("Salvar Relatório");
		this.frame=frame;
		this.putValue(Action.SMALL_ICON,createImageIcon("/imagens/salvar.gif"));
		this.putValue(Action.SHORT_DESCRIPTION, "Salvar o relatório");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Salvar Relatório");
		int resp = jfc.showSaveDialog(frame);
		if (resp != JFileChooser.APPROVE_OPTION)
			return;

		File arquivo = jfc.getSelectedFile();
		this.saveFile(arquivo);
	}
	
	private void saveFile(File f) {
		try {
			if (f.exists()) {

				Object[] options = { "Sim", "Não" };
				int i = JOptionPane
						.showOptionDialog(null, "Substitui o aquivo " + f.getName() + " existente?",
								"Salvar Arquivo", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (i == JOptionPane.NO_OPTION) {
					return;
				}
			}
			FileWriter out = new FileWriter(f);
			out.write(frame.getTexto());
			out.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
