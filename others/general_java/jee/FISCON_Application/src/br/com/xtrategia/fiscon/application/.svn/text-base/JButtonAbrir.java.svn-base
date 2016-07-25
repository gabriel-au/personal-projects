package br.com.xtrategia.fiscon.application;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JButtonAbrir extends JButton {

	private static final long serialVersionUID = 1L;

	private JTextField tf;

	public JButtonAbrir(String titulo, JTextField tf) {
		super(titulo);
		this.tf = tf;
	}

	public void abrir() {
		String caminho = Configucacao.getInstance().getValor("diretorio");
		String caminhoNovo = caminho;
		if (caminho == null) {
			caminho = System.getProperty("user.dir");
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File(caminho));
		int resp = jfc.showOpenDialog(null);
		if (resp != JFileChooser.APPROVE_OPTION)
			return;

		File arquivo = jfc.getSelectedFile();
		tf.setText(arquivo.getAbsolutePath());
		caminho = jfc.getCurrentDirectory().getPath();
		if (!caminhoNovo.equals(caminho)) {
			Configucacao.getInstance().setValor("diretorio", caminho);
		}
	}

	public void abrirPath() {
		String caminho = Configucacao.getInstance().getValor("diretorio");
		String caminhoNovo = caminho;
		if (caminho == null) {
			caminho = System.getProperty("user.dir");
		}
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setCurrentDirectory(new File(caminho));
		int resp = jfc.showOpenDialog(null);
		if (resp != JFileChooser.APPROVE_OPTION)
			return;

		File arquivo = jfc.getSelectedFile();
		tf.setText(arquivo.getAbsolutePath());
		caminho = jfc.getCurrentDirectory().getPath();
		if (!caminhoNovo.equals(caminho)) {
			Configucacao.getInstance().setValor("diretorio", caminho);
		}
	}
	
	/**
	 * Executa a carga dos arquivos
	 * @param tipo
	 */
	public void executar(int tipo) {
		if(tf.getText()==null || tf.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Informe o caminho do arquivo.");
			tf.setFocusable(true);
			tf.requestFocus();
		}else{
			setText("Aguarde");
			setEnabled(false);
			NegocioCarga negocio = new NegocioCarga(tipo, tf.getText(),this);
			negocio.start();
		}
	}
	
	public void executar(int tipo, String chave) {
		if(tf.getText()==null || tf.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Informe o caminho do arquivo.");
			tf.setFocusable(true);
			tf.requestFocus();
		}else{
			setText("Aguarde");
			setEnabled(false);
			NegocioCarga negocio = new NegocioCarga(tipo, tf.getText(),this, chave);
			negocio.start();
		}
	}
	
	public void habilitar(){
		setEnabled(true);
		setText("Executar");
	}
}