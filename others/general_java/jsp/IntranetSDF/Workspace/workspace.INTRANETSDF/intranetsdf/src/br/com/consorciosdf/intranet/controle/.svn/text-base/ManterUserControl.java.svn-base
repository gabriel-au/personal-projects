package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.modelo.UsuarioRule;
import br.com.consorciosdf.intranet.persistencia.ManterUserDAO;
import br.com.consorciosdf.intranet.persistencia.ManterUserRuleDAO;
import br.com.consorciosdf.intranet.seguranca.Criptography;

import java.sql.SQLException;
import java.util.List;

public class ManterUserControl {

    private ManterUserDAO dao;
    private ManterUserRuleDAO userRulesDao;

    public Usuario loginWeb(Usuario user) {
        dao = new ManterUserDAO();
        Usuario usuario = null;

        try {
            usuario = dao.loginWeb(user);
        } catch (SQLException e) {
            System.out.println("Erro(LoginWeb.login): " + e.getMessage());
        }

        return usuario;
    }

    public List listUsers() {
        List lista = null;

        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            lista = dao.listUsers();
            return lista;
        } catch (SQLException e) {
            System.out.println("*Erro(UserControl.listUsers): " + e.getMessage());
        }

        return lista;
    }

    public List listTecnicos(int perfil) {
        List lista = null;

        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            lista = dao.listTecnicos(perfil);
            return lista;
        } catch (SQLException e) {
            System.out.println("*Erro(UserControl.listTecnicos): " + e.getMessage());
        }

        return lista;
    }

    public Usuario viewUser(Usuario user) {
        Usuario usuario = null;

        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            usuario = dao.viewUser(user);
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.viewUser): " + e.getMessage());
        }

        return usuario;
    }

    public boolean addUser(Usuario user) {
        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            return dao.addUser(user);

        } catch (SQLException e) {
            System.out.println("Erro(UserControl.addUser): " + e.getMessage());
        }

        return false;
    }

    public boolean editUser(Usuario user) {
        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            return dao.editUser(user);
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.editUser): " + e.getMessage());
        }

        return false;
    }

    public boolean editPassword(Usuario user) {
        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            return dao.editPassword(user);
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.editPassword): " + e.getMessage());
        }

        return false;
    }

    public boolean deleteUser(Usuario user) {
        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            return dao.deleteUser(user);
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.deleteUser): " + e.getMessage());
        }

        return false;
    }

    public List listPerfis() {
        List lista = null;

        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            return dao.listPerfis();
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.listPerfis): " + e.getMessage());
        }

        return lista;
    }

    public List<UsuarioRule> listRules() {
        List lista = null;

        if (userRulesDao == null) {
            userRulesDao = new ManterUserRuleDAO();
        }

        try {
            return userRulesDao.listRules();
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.listRules): " + e.getMessage());
        }

        return lista;
    }
    
    public List listPerfilTecnico() {
        List lista = null;

        if (dao == null) {
            dao = new ManterUserDAO();
        }

        try {
            return dao.listPerfilTecnico();
        } catch (SQLException e) {
            System.out.println("Erro(UserControl.listPerfilTecnico): " + e.getMessage());
        }

        return lista;
    }
}
