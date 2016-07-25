package br.com.xtrategia.fiscon.application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelExportacao extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField path = new JTextField(35);

	private JButtonAbrir abrir = new JButtonAbrir("Abrir",path);
	private JButtonAbrir executar = new JButtonAbrir("Executar", path);
	private JButtonAbrir parar = new JButtonAbrir("Parar",path);

	public JPanelExportacao() {
		montaGui();
	}

	private void montaGui() {
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		JPanel p1 = new JPanel();

		JLabel l1 = new JLabel("Path :");

		l1.setFont(font);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		p1.add(l1);

		p1.add(path);
		p1.add(abrir);
		p1.add(executar);
		p1.add(parar);

		add(p1);

		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir.abrirPath();
			}
		});
		

		executar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executar.executar(NegocioCarga.exportacao);
			}
		});
		
		parar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
	}
}
