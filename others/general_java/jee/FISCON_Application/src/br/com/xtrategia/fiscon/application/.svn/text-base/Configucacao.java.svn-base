package br.com.xtrategia.fiscon.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Configucacao {

	//objeto único da classe
	private static Configucacao conf;
	//objeto para arqmazenar as propriedades
	private Map<String,String> mapa = new HashMap<String, String>();
	
	/**
	 * Construtor privado para implementar singleton
	 */
	private Configucacao(){
	}
	/**
	 * Retorna a instancia do objeto
	 * @return
	 */
	public static Configucacao getInstance(){
		if(conf==null){
			conf = new Configucacao();
		}
		return conf;
	}
	
	public boolean load() {
		File f = new File("fiscon.properties");
		Properties prop = new Properties();
		if (f.exists()) {
			try {
				prop.load(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mapa.put("driver",prop.getProperty("driver"));
			mapa.put("url",prop.getProperty("url"));
			mapa.put("user",prop.getProperty("user"));
			mapa.put("diretorio",prop.getProperty("diretorio"));
			return abrirTelaSenha();
		}else{
			return abrirTelaConfiguracao();
		}
	}
	public boolean possuiConfiguracao(){
		return !mapa.isEmpty();
	}
	
	/**
	 * retorna o valor da configuração
	 * @param key
	 * @return
	 */
	public String getValor(String key){
		return mapa.get(key);
	}
	public void setValor(String key, String valor){
		mapa.put(key,valor);
		salvarPropriedades();
	}
	
	public boolean abrirTelaConfiguracao(){
		JTextField bdDriver = new JTextField(getValor("driver"));
		JTextField bdUrl = new JTextField(getValor("url"));
		JTextField bdUser= new JTextField(getValor("user"));
		JPasswordField bdPassword = new JPasswordField();
		
		String sd = "Driver do Banco de Dados";
		String s0 = "Url do Banco de Dados";
		String s1 = "Usuário do Banco de Dados";
		String s2 = "Senha do Banco de Dados";

		Object[] options = { "Sim", "Não" };

		int i = JOptionPane.showOptionDialog(null, new Object[] { sd, bdDriver, s0, bdUrl,
				s1, bdUser, s2, bdPassword}, "Configurações", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (i == 0) {
			mapa.put("driver",bdDriver.getText());
			mapa.put("url",bdUrl.getText());
			mapa.put("user",bdUser.getText());
			mapa.put("password",new String(bdPassword.getPassword()));
			salvarPropriedades();
			return true;
			
		}else{
			return false;
		}
	}
	
	private void salvarPropriedades() {
		Properties prop = new Properties();
		prop.put("driver",getValor("driver"));
		prop.put("url",getValor("url"));
		prop.put("user",getValor("user"));
		try {
			prop.put("diretorio",getValor("diretorio"));
		} catch (Exception e1) {
			prop.put("diretorio",System.getProperty("user.dir"));
		}

		try {
			File f = new File("fiscon.properties");
			prop.store(new FileWriter(f), "FISCON");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean abrirTelaSenha(){
		String s = "Senha do Banco de Dados";
		JPasswordField  bdPassword = new JPasswordField ();
		Object[] options = { "Entrar", "Sair" };
		int i = JOptionPane.showOptionDialog(null, new Object[] { s, bdPassword}, 
				"Configurações", JOptionPane.OK_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(i==0){
			mapa.put("password",new String(bdPassword.getPassword()));
			try {
				return ConexaoBanco.testarConexao();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * Método de teste
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Configucacao.getInstance().getValor("user"));
	}
}
