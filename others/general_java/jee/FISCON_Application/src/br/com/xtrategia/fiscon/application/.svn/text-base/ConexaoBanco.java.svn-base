package br.com.xtrategia.fiscon.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Classe destinada para gerenciar as conexões com o banco de dados
 * 
 * @author Gustavo
 * 
 */
public abstract class ConexaoBanco<T extends Pojo> {

	protected Connection conn;
	protected PreparedStatement pstmConsulta;
	protected PreparedStatement pstmUpdate;
	protected PreparedStatement pstmInsert;
	protected PreparedStatement pstmInsertHistorico;

	public abstract void iniciar() throws SQLException;

	public abstract void executar(T pojo) throws SQLException;

	public abstract String getSqlConsulta();

	public abstract String getSqlUpdate();

	public abstract String getSqlInsert();

	public abstract String getSqlInsertHistorico();
	
	public static boolean isMysql=false;
	public static boolean isPostgres=false;

	/**
	 * método para abrir a conexao
	 */
	public void abrir() {
		try {
			String driver = Configucacao.getInstance().getValor("driver");
			if(driver.toLowerCase().indexOf("mysql")>0){
				isMysql=true;
			}else if(driver.toLowerCase().indexOf("postgresql")>0){
				isPostgres=true;
			}else{
				JOptionPane.showMessageDialog(null, "Versão não compatível com o Driver");
				System.exit(0);
			}
			
			Class.forName(driver);
			String user = Configucacao.getInstance().getValor("user");
			String password = Configucacao.getInstance().getValor("password");
			String url = Configucacao.getInstance().getValor("url");

			conn = DriverManager.getConnection(url, user, password);

			// inicializar os statements
			prepararStatements();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			LogApplicacao
					.gravar("[ERRO] A Biblioteca do Mysql não foi encontrada");
		} catch (SQLException e) {
			e.printStackTrace();
			LogApplicacao
					.gravar("[ERRO] Erro na autenticação do Banco de dados");
		} catch (Exception e) {
			LogApplicacao.gravar(e.getMessage());
			e.printStackTrace();
		}
	}

	public static boolean testarConexao() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = Configucacao.getInstance().getValor("user");
			String password = Configucacao.getInstance().getValor("password");
			String url = Configucacao.getInstance().getValor("url");

			Connection conn = DriverManager.getConnection(url, user, password);

			boolean teste = conn != null;

			conn.close();
			return teste;
		} catch (ClassNotFoundException e) {
			throw new Exception("[ERRO] Driver não encontrado");
		} catch (SQLException e) {
			throw new Exception("[ERRO] URL, Usuário ou Senha inválidos");
		}
	}

	/**
	 * método para fechar a conexao
	 */
	public void fechar() throws SQLException {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inicialia o {@link PreparedStatement}
	 * 
	 * @throws Exception
	 */
	public void prepararStatements() throws Exception {
		try {
			if (getSqlConsulta() != null)
				pstmConsulta = conn.prepareStatement(getSqlConsulta());
			if (getSqlUpdate() != null)
				pstmUpdate = conn.prepareStatement(getSqlUpdate());
			if (getSqlInsert() != null)
				pstmInsert = conn.prepareStatement(getSqlInsert());
			if (getSqlInsertHistorico() != null)
				pstmInsertHistorico = conn
						.prepareStatement(getSqlInsertHistorico());
		} catch (SQLException e) {
			throw new Exception("[ERRO] Falha ao criar os PrepareStatements");
		}
	}

}
