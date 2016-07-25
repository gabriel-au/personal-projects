/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.consorciosdf.intranet.persistencia;

import br.com.consorciosdf.intranet.modelo.UsuarioRule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class ManterUserRuleDAO {

    private Conexao conexao;
    private Connection connection;

    public boolean addUserRule(UsuarioRule usuarioRule, int idUser) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs;

        int prioridade;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "";


            sql = "INSERT INTO usuario_rules (id_usuario, id_rule, ativo) " +
                    "VALUES (?, ?, 'y');";

            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, idUser);
            pstm.setInt(2, usuarioRule.getId());

            pstm.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("Erro(UsuarioRuleDAO.salvarUserRule): " + e.getMessage());
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

    public List<UsuarioRule> listUserRules(int idUser) throws SQLException {

        List list = null;
        PreparedStatement pstm = null;
        ResultSet rs;
        UsuarioRule usuarioRule;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT r.*, ur.id_usuario FROM usuario_rules AS ur, rules AS r WHERE ur.id_rule=r.id AND ur.id_usuario=" + idUser + ";";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    usuarioRule = new UsuarioRule();
                    usuarioRule.setId(rs.getInt("id"));
                    usuarioRule.setNome(rs.getString("nome"));
                    usuarioRule.setDescRule(rs.getString("dsc"));
                    usuarioRule.setNomeExibicao(rs.getString("nome_exibicao"));

                    list.add(usuarioRule);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UsuarioRuleDAO.listUserRules): " + e.getMessage());
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

    public List<UsuarioRule> listRules() throws SQLException {

        List list = null;
        PreparedStatement pstm = null;
        ResultSet rs;
        UsuarioRule usuarioRule;

        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "SELECT r.* FROM rules AS r ;";

            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs != null) {
                list = new ArrayList();

                while (rs.next()) {
                    usuarioRule = new UsuarioRule();
                    usuarioRule.setId(rs.getInt("id"));
                    usuarioRule.setNome(rs.getString("nome"));
                    usuarioRule.setDescRule(rs.getString("dsc"));
                    usuarioRule.setNomeExibicao(rs.getString("nome_exibicao"));
                    list.add(usuarioRule);

                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro(UsuarioRuleDAO.listRules): " + e.getMessage());
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

    public boolean removeUserRule(UsuarioRule removedUserRule, int idUser) throws SQLException {

        List list = null;
        PreparedStatement pstm = null;


        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "DELETE FROM usuario_rules WHERE id_usuario='" + idUser + "' AND id_rule='" + removedUserRule.getId() + "';";

            pstm = connection.prepareStatement(sql);
            pstm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro(UsuarioRuleDAO.removeUserRules): " + e.getMessage());
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

    public boolean removeAllUserRules(int idUser) throws SQLException {

        List list = null;
        PreparedStatement pstm = null;


        if (conexao == null || connection == null) {
            conexao = new Conexao();
            connection = conexao.abreConexao();
        }

        try {
            String sql = "DELETE FROM usuario_rules WHERE id_rule='" + idUser + "';";

            pstm = connection.prepareStatement(sql);
            pstm.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro(UsuarioRuleDAO.removeAllUserRules): " + e.getMessage());
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

    public boolean editUserRules(List<UsuarioRule> newRules, int idUser) {
        try {

            List<UsuarioRule> oldRules = listUserRules(idUser);

            
            /**
             * Adiciona as rules que não estão na lista oldRules
             */
            for (int i = 0; i < newRules.size(); i++) {
                boolean isNew = true;
                for (int j = 0; j < oldRules.size(); j++) {
                    if (oldRules.get(j).getId() == newRules.get(i).getId()) {
                        isNew = false;
                    }
                }
                if (isNew == true) {
                    addUserRule(newRules.get(i), idUser);
                    
                }
            }
            /**
             * Remove as rules que não estão na lista newRules
             */
            for (int i = 0; i < oldRules.size(); i++) {
                boolean isRemoved = true;
                for (int j = 0; j < newRules.size(); j++) {
                    if (oldRules.get(i).getId() == newRules.get(j).getId()) {
                        isRemoved = false;
                        
                    }
                }
                if (isRemoved == true) {
                    removeUserRule(oldRules.get(i), idUser);
                    System.out.println("Entrou");
                }
            }


        } catch (SQLException ex) {
            Logger.getLogger(ManterUserRuleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
