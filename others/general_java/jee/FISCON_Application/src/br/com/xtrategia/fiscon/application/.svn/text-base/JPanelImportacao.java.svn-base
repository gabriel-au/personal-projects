package br.com.xtrategia.fiscon.application;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelImportacao extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField pathCategoria = new JTextField(35);
	private JTextField pathCores = new JTextField(35);
	private JTextField pathEspecies = new JTextField(35);
	private JTextField pathMarcaModelo = new JTextField(35);
	private JTextField pathMunicipio = new JTextField(35);
	private JTextField pathVeiculo = new JTextField(35);
	private JTextField pathTipoVeiculo = new JTextField(35);
	private JTextField pathRestricoes = new JTextField(35);

	private JButtonAbrir abrirCategoria = new JButtonAbrir("Abrir",
			pathCategoria);
	private JButtonAbrir abrirCores = new JButtonAbrir("Abrir", pathCores);
	private JButtonAbrir abrirEspecies = new JButtonAbrir("Abrir", pathEspecies);
	private JButtonAbrir abrirMarcaModelo = new JButtonAbrir("Abrir",
			pathMarcaModelo);
	private JButtonAbrir abrirMunicipio = new JButtonAbrir("Abrir",
			pathMunicipio);
	private JButtonAbrir abrirVeiculo = new JButtonAbrir("Abrir", pathVeiculo);
	private JButtonAbrir abrirTipoVeiculo = new JButtonAbrir("Abrir",
			pathTipoVeiculo);
	private JButtonAbrir abrirRestricoes = new JButtonAbrir("Abrir",
			pathRestricoes);

	private JButtonAbrir executarCategoria = new JButtonAbrir("Executar",
			pathCategoria);
	private JButtonAbrir executarCores = new JButtonAbrir("Executar", pathCores);
	private JButtonAbrir executarEspecies = new JButtonAbrir("Executar",
			pathEspecies);
	private JButtonAbrir executarMarcaModelo = new JButtonAbrir("Executar",
			pathMarcaModelo);
	private JButtonAbrir executarMunicipio = new JButtonAbrir("Executar",
			pathMunicipio);
	private JButtonAbrir executarVeiculo = new JButtonAbrir("Executar",
			pathVeiculo);
	private JButtonAbrir executarTipoVeiculo = new JButtonAbrir("Executar",
			pathTipoVeiculo);
	private JButtonAbrir executarRestricoes = new JButtonAbrir("Executar",
			pathRestricoes);
	
	private JButtonAbrir pararCategoria = new JButtonAbrir("Parar",
			pathCategoria);
	private JButtonAbrir pararCores = new JButtonAbrir("Parar", pathCores);
	private JButtonAbrir pararEspecies = new JButtonAbrir("Parar",
			pathEspecies);
	private JButtonAbrir pararMarcaModelo = new JButtonAbrir("Parar",
			pathMarcaModelo);
	private JButtonAbrir pararMunicipio = new JButtonAbrir("Parar",
			pathMunicipio);
	private JButtonAbrir pararVeiculo = new JButtonAbrir("Parar",
			pathVeiculo);
	private JButtonAbrir pararTipoVeiculo = new JButtonAbrir("Parar",
			pathTipoVeiculo);
	private JButtonAbrir pararRestricoes = new JButtonAbrir("Parar",
			pathRestricoes);

	public JPanelImportacao() {
		montaGui();
	}

	private void montaGui() {
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();

		JLabel l1 = new JLabel("Categoria      :");
		JLabel l2 = new JLabel("Cores          :");
		JLabel l3 = new JLabel("Especies       :");
		JLabel l4 = new JLabel("Marcas-Modelos :");
		JLabel l5 = new JLabel("Municípios     :");
		JLabel l6 = new JLabel("Tipo Veículo   :");
		JLabel l7 = new JLabel("Veículos       :");
		JLabel l8 = new JLabel("Restrições     :");

		l1.setFont(font);
		l2.setFont(font);
		l3.setFont(font);
		l4.setFont(font);
		l5.setFont(font);
		l6.setFont(font);
		l7.setFont(font);
		l8.setFont(font);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		p1.add(l1);
		p2.add(l2);
		p3.add(l3);
		p4.add(l4);
		p5.add(l5);
		p6.add(l6);
		p7.add(l7);
		p8.add(l8);

		p1.add(pathCategoria);
		p1.add(abrirCategoria);
		p1.add(executarCategoria);
		p1.add(pararCategoria);

		p2.add(pathCores);
		p2.add(abrirCores);
		p2.add(executarCores);
		p2.add(pararCores);

		p3.add(pathEspecies);
		p3.add(abrirEspecies);
		p3.add(executarEspecies);
		p3.add(pararEspecies);

		p4.add(pathMarcaModelo);
		p4.add(abrirMarcaModelo);
		p4.add(executarMarcaModelo);
		p4.add(pararMarcaModelo);

		p5.add(pathMunicipio);
		p5.add(abrirMunicipio);
		p5.add(executarMunicipio);
		p5.add(pararMunicipio);

		p6.add(pathTipoVeiculo);
		p6.add(abrirTipoVeiculo);
		p6.add(executarTipoVeiculo);
		p6.add(pararTipoVeiculo);

		p7.add(pathVeiculo);
		p7.add(abrirVeiculo);
		p7.add(executarVeiculo);
		p7.add(pararVeiculo);

		p8.add(pathRestricoes);
		p8.add(abrirRestricoes);
		p8.add(executarRestricoes);
		p8.add(pararRestricoes);

		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		add(p7);
		add(p8);

		abrirCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCategoria.abrir();
			}
		});
		abrirCores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCores.abrir();
			}
		});
		abrirEspecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirEspecies.abrir();
			}
		});
		abrirMarcaModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMarcaModelo.abrir();
			}
		});
		abrirMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirMunicipio.abrir();
			}
		});
		abrirVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVeiculo.abrir();
			}
		});
		abrirTipoVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirTipoVeiculo.abrir();
			}
		});
		abrirRestricoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirRestricoes.abrir();
			}
		});

		executarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCategoria.executar(NegocioCarga.tipoCategoria);
			}
		});
		executarCores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarCores.executar(NegocioCarga.tipoCores);
			}
		});
		executarEspecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarEspecies.executar(NegocioCarga.tipoEspecies);
			}
		});
		executarMarcaModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarMarcaModelo.executar(NegocioCarga.tipoMarcaModelo);
			}
		});
		executarMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarMunicipio.executar(NegocioCarga.tipoMunicipio);
			}
		});
		executarVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarVeiculo.executar(NegocioCarga.tipoVeiculo);
			}
		});
		executarTipoVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarTipoVeiculo.executar(NegocioCarga.tipoTipoVeiculo);
			}
		});
		executarRestricoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarRestricoes.executar(NegocioCarga.tipoRestricoes);
			}
		});
		
		pararCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararCores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararEspecies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararMarcaModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararTipoVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
		pararRestricoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FisconMain.INTERROMPER_EXECUCAO=true;
			}
		});
	}
}
