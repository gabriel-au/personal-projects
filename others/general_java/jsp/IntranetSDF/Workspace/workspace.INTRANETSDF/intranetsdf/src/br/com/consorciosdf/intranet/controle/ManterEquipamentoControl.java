package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.Equipamento;
import br.com.consorciosdf.intranet.persistencia.ManterEquipamentoDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterEquipamentoControl {

    private ManterEquipamentoDAO dao;

    public List recuperarEquipamentos() {
        List list = null;

        if (dao == null) {
            dao = new ManterEquipamentoDAO();
        }

        try {
            list = dao.recuperarEquipamentos();
        } catch (SQLException e) {
            System.out.println("*Erro(ManterEquipamentoControl.recuperarEquipamentos): " + e.getMessage());
        }

        return list;
    }

    public Equipamento recuperarEquipamentos(int id) {
        Equipamento equipamento = null;

        if (dao == null) {
            dao = new ManterEquipamentoDAO();
        }

        try {
            try {
                equipamento = dao.recuperarEquipamentos(id);
            } catch (ParseException ex) {
                Logger.getLogger(ManterEquipamentoControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            System.out.println("*Erro(ManterEquipamentoControl.recuperarEquipamentos): " + e.getMessage());
        }

        return equipamento;
    }

        

    public List recuperarEquipamentosPag(String ativo, String equipamento, String endereco, String cidade, int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;

        if (dao == null) {
            dao = new ManterEquipamentoDAO();
        }

        try {
            list = dao.recuperarEquipamentosPag(ativo, equipamento, endereco, cidade, pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterEquipamentoControl.recuperarEquipamentos): " + e.getMessage());
        }

        return list;
    }

    public int recuperarQuantidadeEquipamentos(boolean ativo, String tipo) {
        if (dao == null) {
            dao = new ManterEquipamentoDAO();
        }

        try {
            return dao.recuperarQuantidadeEquipamentos(ativo, tipo);
        } catch (SQLException e) {
            System.out.println("*Erro(ManterEquipamentoControl.recuperarQuantidadeEquipamentos): " + e.getMessage());
        }

        return 0;
    }

    public boolean salvarEquipamento(Equipamento equipamento) {
        if (dao == null) {
            dao = new ManterEquipamentoDAO();
        }
        try {
            return dao.salvarEquipamento(equipamento);

        } catch (Exception e) {
            System.out.println("*Erro(ManterEquipamentoControl.salvarEquipamento): " + e.getMessage());
        }
        return false;
    }

    public boolean alterarEquipamento(Equipamento equipamento) {
        if (equipamento != null) {
            if (dao == null) {
                dao = new ManterEquipamentoDAO();
            }

            try {
                return dao.alterarEquipamento(equipamento);
            } catch (SQLException e) {
                System.out.println("*Erro(EquipamentoControl.alterarEquipamento): " + e.getMessage());
            }
        }

        return false;
    }
    
    public List<String> recuperaEquipamentosCidades(){

    	if(dao == null){
    		dao = new ManterEquipamentoDAO();
    	}
    	try{
    		return  dao.recuperaEquipamentosCidades();
    	} catch(SQLException e){
    		System.out.println("*Erro(EquipamentoControl.recuperarEquipamentosCidades): " + e.getMessage());
    	}
    	return null;
    }

//    public String buscaNomeIdPai(int idEquip){
//        Equipamento equipamento = null;
//
//        for (int aux = 1; aux < idEquip ; aux++) {
//            if(equipamento.getIdPai() == idEquip){
//
//            }
//        }
//    return "";
//    }
}
