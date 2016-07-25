package br.com.consorciosdf.intranet.controle;

import br.com.consorciosdf.intranet.modelo.*;
import br.com.consorciosdf.intranet.persistencia.ManterOSDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManterOSControl {

    private ManterOSDAO dao;

    public boolean salvarOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.salvarOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.salvarOS): " + e.getMessage());
            }
        }

        return false;
    }

    public boolean alterarOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.alterarOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.alterarOS): " + e.getMessage());
            }
        }

        return false;
    }

    public boolean assumirOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.assumirOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.assumirOS): " + e.getMessage());
            }
        }

        return false;
    }

    public boolean fecharOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.fecharOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.fecharOS): " + e.getMessage());
            }
        }

        return false;
    }

    public boolean incluirObsOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.incluirObsOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.incluirObsOS): " + e.getMessage());
            }
        }

        return false;
    }
    public boolean incluirDefeitoOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.incluirDefeitoOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.incluirDefeitoOS): " + e.getMessage());
            }
        }

        return false;
    }

    public boolean incluirServOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.incluirServicoExecutadoOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.incluirServOS): " + e.getMessage());
            }
        }

        return false;
    }
    public boolean incluirMatUtilOS(OS os) {
        if (os != null) {
            if (dao == null) {
                dao = new ManterOSDAO();
            }

            try {
                return dao.incluirMaterialUtilizadoOS(os);
            } catch (SQLException e) {
                System.out.println("*Erro(OSControl.incluirMatUtilOS): " + e.getMessage());
            }
        }

        return false;
    }
    public OS recuperarOS(int id) {
        OS os = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            return dao.recuperarOS(id);
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarOS): " + e.getMessage());
        }

        return os;
    }

    public List recuperarOSs() {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarOSs();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarOS): " + e.getMessage());
        }

        return list;
    }

    public List recuperarOSsPag(int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarOSsPag(pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarOS): " + e.getMessage());
        }

        return list;
    }

    public List recuperarOSsPagFiltro(int idOS, String status, String prioridade, String tipoOS, int idEquipamento, String tecnico, String dataAnomalia ,String dataInicial, String dataServico, String dataFinal, int pageNumber, int pageLimit, String orderBy, String order) {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarOSsPagFiltro(idOS, status, prioridade, tipoOS, idEquipamento, tecnico, dataAnomalia, dataInicial, dataServico,dataFinal, pageNumber, pageLimit, orderBy, order);
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarOSFiltro): " + e.getMessage());
        }

        return list;
    }

    public Equipamento recuperarEquipamento(int id) {
        Equipamento equipamento = null;

        return equipamento;
    }

    public List recuperarEquipamentos() {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarEquipamentos();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarEquipamentos): " + e.getMessage());
        }

        return list;
    }

    public Andamento recuperarAndamento(int id) {
        Andamento andamento = null;

        return andamento;
    }

    public List recuperarAndamentos() {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarAndamentos();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarAndamentos): " + e.getMessage());
        }

        return list;
    }

    public List recuperarAndamentosOS(int idOS) {
        List list = null;

        return list;
    }

    public Defeito recuperarDefeito(int id) {
        Defeito defeito = null;

        return defeito;
    }

    public List recuperarDefeitos() {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarDefeitos();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarDefeitos): " + e.getMessage());
        }

        return list;
    }

    public List recuperarDefeitosOS(int idOS) {
        List list = null;

        return list;
    }

    public Reparo recuperarReparo() {
        Reparo reparo = null;

        return reparo;
    }

    public List recuperarReparos() {
        List list = null;

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            list = dao.recuperarReparos();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarReparos): " + e.getMessage());
        }

        return list;
    }

    public List recuperarReparosOS(int idOS) {
        List list = null;

        return list;
    }

    public List recuperarQuantidadeOS(int tipo, String tipoOs, int mes, int ano) {
        List list = new ArrayList();
        ContadorMain contadorMain;

        String[] mesesAno = {"Jan",
            "Fev",
            "Mar",
            "Abr",
            "Mai",
            "Jun",
            "Jul",
            "Ago",
            "Set",
            "Out",
            "Nov",
            "Dez"};

        if (dao == null) {
            dao = new ManterOSDAO();
        }

        try {
            if (mes == 1) {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);

                mes += 11;
                --ano;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);

                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);
            } else if (mes == 2) {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);

                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);

                mes += 10;
                --ano;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);
            } else {
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);

                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);

                --mes;
                contadorMain = new ContadorMain();
                contadorMain.setIdMes(mes);
                contadorMain.setIdAno(ano);
                contadorMain.setMes(mesesAno[mes - 1]);
                contadorMain.setQuantidade(dao.recuperarQuantidadeOS(tipo, tipoOs, mes, ano));

                list.add(contadorMain);
            }
            //listdao.recuperarQuantidadeOS();
        } catch (SQLException e) {
            System.out.println("*Erro(OSControl.recuperarQuantidadeOS): " + e.getMessage());
        }

        return list;
    }
    
    public List<OS> recuperarOSMobile(Usuario usuario, int pageStart){
    	List<OS> lista = null;
    	
    	if(dao == null){
    		dao = new ManterOSDAO();
    	}
    	
    	try {
    		lista = dao.recuperarOSMobile(usuario, pageStart);
		} catch (Exception e) {
			System.out.println("*Erro(OSControl.recuperarMobileOS): " + e.getMessage());
		}
		
		return lista;
    	
    }
    
    public int recuperarQtdOSMobile(Usuario usuario){
    	int qtd = 0;
    	if(dao == null){
    		dao = new ManterOSDAO();
    	}
    	try {
			qtd = dao.recuperarQtdOSMobile(usuario);
		} catch (Exception e) {
			System.out.print("*Erro(OSControl.recuperarQtdOSMoblile): " +e.getMessage());
		}
    	return qtd;
    }
}