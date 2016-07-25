package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.CheckEquipamento;
import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.persistencia.ManterCheckEquipamentoDAO;
import java.sql.SQLException;
import java.util.List;

public class ManterCheckEquipamentoControl {
    
    private ManterCheckEquipamentoDAO dao;
    
    public int salvarChecagem(CheckEquipamento checkEquipamento) {
        if (checkEquipamento != null) {
            if (dao == null) {
                dao = new ManterCheckEquipamentoDAO();
            }
            
            try {
                return dao.salvarChecagem(checkEquipamento);
            } catch (SQLException e) {
                System.out.println("*Erro(CheckEquipamentoControl.salvarChecagem): " + e.getMessage());
            }
        }
        
        return 0;
    }
    
    public boolean salvarProcedimentoChecagem(CheckEquipamento checkEquipamento) {
        if (checkEquipamento != null) {
            if (dao == null) {
                dao = new ManterCheckEquipamentoDAO();
            }
            
            try {
                return dao.salvarProcedimentoChecagem(checkEquipamento);
            } catch (SQLException e) {
                System.out.println("*Erro(CheckEquipamentoControl.salvarProcedimentoChecagem): " + e.getMessage());
            }
        }
        
        return false;
    }
    
    public List recuperarPerfis() {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        try {
            list = dao.recuperarPerfis();
        } catch (SQLException e) {
            System.out.println("*Erro(CheckEquipamentoControl.recuperarPerfis): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarEquipamentos() {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        try {
            list = dao.recuperarEquipamentos();
        } catch (SQLException e) {
            System.out.println("*Erro(CheckEquipamentoControl.recuperarEquipamentos): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarProcedimentos(int perfil) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        try {
            list = dao.recuperarProcedimentos(perfil);
        } catch (SQLException e) {
            System.out.println("*Erro(CheckEquipamentoControl.recuperarProcedimentos): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarStatusProcedimentos(String tipo) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        try {
            list = dao.recuperarStatusProcedimentos(tipo);
        } catch (SQLException e) {
            System.out.println("*Erro(CheckEquipamentoControl.recuperarStatusProcedimentos): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarChecagens() {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        try {
            list = dao.recuperarChecagens();
        } catch (SQLException e) {
            System.out.println("*Erro(CheckEquipamentoControl.recuperarChecagens): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarChecagensPag(int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        try {
            list = dao.recuperarChecagensPag(pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(CheckEquipamentoControl.recuperarChecagens): " + e.getMessage());
        }
        
        return list;
    }
    
    public List recuperarChecagem(int idCheck) {
        List list = null;
        
        if (dao == null) {
            dao = new ManterCheckEquipamentoDAO();
        }
        
        if (idCheck != 0) {
            try {
                return dao.recuperarChecagem(idCheck);
            } catch (SQLException e) {
                System.out.println("*Erro(CheckEquipamentoControl.recuperarChecagem): " + e.getMessage());
            }
        }
        
        return list;
    }
    
}
