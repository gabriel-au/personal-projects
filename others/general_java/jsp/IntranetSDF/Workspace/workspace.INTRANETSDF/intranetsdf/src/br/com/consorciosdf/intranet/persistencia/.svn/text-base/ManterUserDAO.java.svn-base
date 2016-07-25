package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.modelo.UsuarioPerfil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ManterUserDAO {

    private Conexao conexao;
    private Connection connection;

    public Usuario loginWeb(Usuario user) throws SQLException {
        ManterUserRuleDAO userRulesDAO = new ManterUserRuleDAO();
        Usuario usuario = null;
        PreparedStatement pstm = null;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT * FROM usuario " +
                    "WHERE user = '" + user.getUser() + "' " +
                    "AND password = '" + user.getPassword() + "' " +
                    "AND ativo='y'";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUser(rs.getString("user"));
                    usuario.setCodUsuario(rs.getInt("id"));
                    usuario.setMatriculaUsuario(rs.getString("matricula"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome"));
                    usuario.setEmailUsuario(rs.getString("email"));
                    usuario.setPerfilUsuario(rs.getInt("perfil"));
                    usuario.setModificarPrefUsuario(rs.getString("modif_pref"));
                    usuario.setUsuarioRules(userRulesDAO.listUserRules(usuario.getCodUsuario()));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.loginWeb): " + e.getMessage());
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

        return usuario;
    }

    public List listUsers() throws SQLException {
        
        List list = null;
        PreparedStatement pstm = null;
        Usuario usuario;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT * FROM usuario ORDER BY nome";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUser(rs.getString("user"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome"));
                    usuario.setMatriculaUsuario(rs.getString("matricula"));
                    usuario.setSkype(rs.getString("skype"));
                    
                    list.add(usuario);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.listUsersMobile): " + e.getMessage());
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

    public List listTecnicos(int perfil) throws SQLException {
        
        List list = null;
        PreparedStatement pstm = null;
        Usuario usuario;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "";

            if (perfil < 0) {
                sql = "SELECT userw.* FROM usuario userw, usuario_perfil userp " +
                        "WHERE userw.perfil = userp.id " +
                        "AND userw.ativo = 'y' " +
                        "AND userp.descricao like '%T�c%' " +
                        "ORDER BY userw.nome";
            } else {
                sql = "SELECT * FROM usuario userw " +
                        "WHERE perfil = " + perfil + " " +
                        "ORDER BY nome";
            }

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUser(rs.getString("user"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setSobrenomeUsuario(rs.getString("sobrenome"));
                    usuario.setMatriculaUsuario(rs.getString("matricula"));
                    
                    list.add(usuario);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.listTecnicos): " + e.getMessage());
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

    public List listPerfis() throws SQLException {
        List list = null;
        PreparedStatement pstm = null;
        UsuarioPerfil usuarioPerfil;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT * FROM usuario_perfil " +
                    "WHERE ativo = 'y' ORDER BY id";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    usuarioPerfil = new UsuarioPerfil();
                    usuarioPerfil.setCodPerfil(rs.getInt("id"));
                    usuarioPerfil.setDescricaoPerfil(rs.getString("descricao"));

                    list.add(usuarioPerfil);
                }

                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.listPerfil): " + e.getMessage());
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

    public List listPerfilTecnico() throws SQLException {
        List list = null;
        PreparedStatement pstm = null;
        UsuarioPerfil usuarioPerfil;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT * FROM usuario_perfil " +
                    "WHERE ativo = 'y' " +
                    "AND descricao like '%Téc%' " +
                    "ORDER BY id";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    usuarioPerfil = new UsuarioPerfil();
                    usuarioPerfil.setCodPerfil(rs.getInt("id"));
                    usuarioPerfil.setDescricaoPerfil(rs.getString("descricao"));

                    list.add(usuarioPerfil);
                }

                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.listPerfil): " + e.getMessage());
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

    public Usuario viewUser(Usuario user) throws SQLException {

        ManterUserRuleDAO userRulesDAO = new ManterUserRuleDAO();
        PreparedStatement pstm = null;
        Usuario usuario = null;
        ResultSet rs;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "";

            if (user.getUser() != null && user.getMatriculaUsuario() == null) {
                sql = "SELECT nome, id, sobrenome, email, matricula, perfil, skype FROM usuario " +
                        "WHERE user = '" + user.getUser() + "'";
            } else if (user.getUser() == null && user.getMatriculaUsuario() != null) {
                sql = "SELECT nome, id, sobrenome, email, matricula, perfil, skype FROM usuario " +
                        "WHERE matricula = '" + user.getMatriculaUsuario() + "'";
            } else if (user.getUser() != null && user.getMatriculaUsuario() != null) {
                sql = "SELECT nome, id, sobrenome, email, matricula, perfil, skype FROM usuario " +
                        "WHERE user = '" + user.getUser() + "' " +
                        "AND matricula = '" + user.getMatriculaUsuario() + "'";
            }

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                rs.last();

                usuario = new Usuario();
                usuario.setCodUsuario(rs.getInt("id"));
                usuario.setNomeUsuario(rs.getString("nome"));
                usuario.setSobrenomeUsuario(rs.getString("sobrenome"));
                usuario.setMatriculaUsuario(rs.getString("matricula"));
                usuario.setPerfilUsuario(rs.getInt("perfil"));
                usuario.setEmailUsuario(rs.getString("email"));
                usuario.setSkype(rs.getString("skype"));
                usuario.setUsuarioRules(userRulesDAO.listUserRules(usuario.getCodUsuario()));
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.userView): " + e.getMessage());
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

        return usuario;
    }

    public boolean addUser(Usuario user) throws SQLException {
        ManterUserRuleDAO userRulesDAO = new ManterUserRuleDAO();
        PreparedStatement pstm = null;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "INSERT INTO usuario (matricula, user, password, " +
                    "nome, sobrenome, email, perfil, skype) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, user.getMatriculaUsuario());
            pstm.setString(2, user.getUser());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getNomeUsuario());
            pstm.setString(5, user.getSobrenomeUsuario());
            pstm.setString(6, user.getEmailUsuario());
            pstm.setInt(7, user.getPerfilUsuario());
            pstm.setString(8, user.getSkype());

            pstm.execute();
            ResultSet rs = (ResultSet) pstm.getGeneratedKeys();
            while (rs.next()) {
                user.setCodUsuario(rs.getInt(1));
            }
            for (int i = 0; i < user.getUsuarioRules().size(); i++) {
                userRulesDAO.addUserRule(user.getUsuarioRules().get(i), user.getCodUsuario());

            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.addUserWeb): " + e.getMessage());
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

    public boolean editUser(Usuario user) throws SQLException {
        PreparedStatement pstm = null;
        ManterUserRuleDAO userRulesDAO = new ManterUserRuleDAO();
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "";

            if (!user.getPassword().trim().equals("")) {
                sql = "UPDATE usuario SET nome = '" + user.getNomeUsuario() + "', " +
                        "sobrenome = '" + user.getSobrenomeUsuario() + "', password = '" + user.getPassword() + "', " +
                        "email = '" + user.getEmailUsuario() + "', perfil = " + user.getPerfilUsuario() + ", skype = '"+user.getSkype()+"' " +
                        "WHERE user = '" + user.getUser() + "'";
            } else if (user.getPassword().trim().equals("")) {
                sql = "UPDATE usuario SET nome = '" + user.getNomeUsuario() + "', " +
                        "sobrenome = '" + user.getSobrenomeUsuario() + "', " +
                        "email = '" + user.getEmailUsuario() + "', perfil = " + user.getPerfilUsuario() + ", skype = '"+user.getSkype()+"' " +
                        "WHERE user = '" + user.getUser() + "'";
            }

            pstm = connection.prepareStatement(sql);
            pstm.execute();
            /**
             * Atualização das rules do usuário.
             */
            userRulesDAO.editUserRules(user.getUsuarioRules(), viewUser(user).getCodUsuario());
            return true;
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.editUser): " + e.getMessage());
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

    public boolean editPassword(Usuario user) throws SQLException {
        PreparedStatement pstm = null;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "UPDATE usuario SET password = '" + user.getPassword() +
                    "' WHERE user = '" + user.getUser() + "'";

            pstm = connection.prepareStatement(sql);
            pstm.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.editPassWeb): " + e.getMessage());
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

    public boolean deleteUser(Usuario user) throws SQLException {
        PreparedStatement pstm = null;
        ManterUserRuleDAO userRulesDAO = new ManterUserRuleDAO();
        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "DELETE FROM usuario WHERE user = '" + user.getUser() + "'";

            pstm = connection.prepareStatement(sql);
            pstm.execute();
            userRulesDAO.removeAllUserRules(user.getCodUsuario());
            return true;
        } catch (SQLException e) {
            System.out.println("Erro(UserDAO.deleteUser): " + e.getMessage());
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
