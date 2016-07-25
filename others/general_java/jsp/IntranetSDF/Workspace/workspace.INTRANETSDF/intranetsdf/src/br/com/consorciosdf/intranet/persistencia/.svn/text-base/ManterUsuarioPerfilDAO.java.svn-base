package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.modelo.UsuarioPerfil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class ManterUsuarioPerfilDAO {

    private Conexao conexao;
    private Connection connection;

    public List listUsuarioPerfil(int codUsuario) throws SQLException {
        List list = null;
        PreparedStatement pstm = null;
        UsuarioPerfil perfil;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT ur. * , up.id_usuario FROM usuario_rules AS ur, usuario_perfil AS up WHERE up.id_rule = ur.id AND up.id_usuario = '"+codUsuario+"' ORDER BY ur.id";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    perfil = new UsuarioPerfil();
                    perfil.setCodPerfil(rs.getInt("id"));
                    perfil.setDescricaoPerfil(rs.getString("descricao"));
                    

                    list.add(perfil);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UsuarioPerfilDAO.listUsuarioPerfil): " + e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    conexao.fechaConexao(connection);
                    conexao = null;
                    connection = null;
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return list;
    }
    public boolean addUsuarioPerfil(int codUser, List<UsuarioPerfil> list) throws SQLException {
        PreparedStatement pstm = null;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }
        
        try {
            for(int i=0; i<list.size(); i++){
            String sql = "INSERT INTO perfil_usuario (id_usuario,id_rule) VALUES (?, ?)";

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, codUser);
            pstm.setInt(2, list.get(i).getCodPerfil());
            pstm.execute();

            }return true;
        } catch (SQLException e) {
                System.out.println("Erro(UserDAO.addUsuarioPerfil): " + e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    conexao.fechaConexao(connection);
                    conexao = null;
                    connection = null;
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return false;
    }
}
