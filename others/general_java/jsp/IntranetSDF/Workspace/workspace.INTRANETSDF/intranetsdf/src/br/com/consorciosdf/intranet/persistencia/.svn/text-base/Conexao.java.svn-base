package br.com.consorciosdf.intranet.persistencia;

import java.sql.*;

public class Conexao {

	private String driver = "org.gjt.mm.mysql.Driver";
	private String usuario = "intranetsdf";
	private String senha = "1ntr4n3t5df";
	private String url = "jdbc:mysql://localhost:3306/intranetsdf";

	private Connection connection = null;

    public Connection abreConexao() {
        if (connection == null) {
            try {
                Class.forName(driver).newInstance();
                connection = DriverManager.getConnection(url, usuario, senha);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public boolean fechaConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return false;
    }
}
