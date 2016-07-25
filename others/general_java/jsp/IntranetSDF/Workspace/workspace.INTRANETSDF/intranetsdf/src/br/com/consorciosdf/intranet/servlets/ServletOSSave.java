package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.modelo.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletOSSave extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ManterOSControl manterOSControl = new ManterOSControl();
        OS os = null;
        CheckEquipamento checkEquipamento = null;
        Equipamento equipamento = null;
        Usuario usuarioAutor = null;
        Usuario usuarioDest = null;
        
        if (request.getParameter("tipo_os") != null ||
                request.getParameter("tecnico") != null ||
                request.getParameter("checagem") != null ||
                request.getParameter("equipamento") != null ||
                request.getParameter("numSerie") != null ||
                request.getParameter("numUPR") != null ||
                request.getParameter("numCamera") != null ||
                request.getParameter("numFlash") != null ||
                request.getParameter("defeito") != null ||
                request.getParameter("prioridade_os") != null ||
                request.getParameter("dt_ano_hr") != null ||
                request.getParameter("dt_ano_min") != null ||
                request.getParameter("dt_ano_dia") != null ||
                request.getParameter("dt_ano_mes") != null ||
                request.getParameter("dt_ano_ano") != null ||
                request.getParameter("dt_ini_hr") != null ||
                request.getParameter("dt_ini_min") != null ||
                request.getParameter("dt_ini_dia") != null ||
                request.getParameter("dt_ini_mes") != null ||
                request.getParameter("dt_ini_ano") != null) {
            if (!request.getParameter("equipamento").trim().equals("") ||
                    !request.getParameter("defeito").trim().equals("")) {
                
                String tipoOS = request.getParameter("tipo_os");
                
                checkEquipamento = new CheckEquipamento();
                
                if (!request.getParameter("checagem").trim().equals("")) {
                    int idCheck = Integer.parseInt(request.getParameter("checagem"));
                    checkEquipamento.setId(idCheck);
                }
                
                equipamento = new Equipamento();
                
                if (!request.getParameter("equipamento").trim().equals("")) {
                    int idEquipamento = Integer.parseInt(request.getParameter("equipamento"));
                    equipamento.setIdEquipamento(idEquipamento);
                }
                
                if (!request.getParameter("numSerie").trim().equals("")) {
                    int numSerie = Integer.parseInt(request.getParameter("numSerie"));
                    equipamento.setNumSerie(numSerie);
                }
                
                if (!request.getParameter("numUPR").trim().equals("")) {
                    int numUPR = Integer.parseInt(request.getParameter("numUPR"));
                    equipamento.setNumUPR(numUPR);
                }
                
                if (!request.getParameter("numCamera").trim().equals("")) {
                    int numCamera = Integer.parseInt(request.getParameter("numCamera"));
                    equipamento.setNumCamera(numCamera);
                }
                
                equipamento.setNumFlash(request.getParameter("numFlash"));
                
                usuarioAutor = new Usuario();
                usuarioAutor.setMatriculaUsuario((String) session.getAttribute("userMatricula"));
                
                if (!request.getParameter("tecnico").trim().equals("")) {
                    usuarioDest = new Usuario();
                    usuarioDest.setMatriculaUsuario(request.getParameter("tecnico"));
                }
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

                String strDataAnomalia = request.getParameter("dt_ano_hr") + ":" +
                                        request.getParameter("dt_ano_min") + ":00 " +
                                        request.getParameter("dt_ano_dia") + "/" +
                                        request.getParameter("dt_ano_mes") + "/" +
                                        request.getParameter("dt_ano_ano");
                Date dataAnomalia = null;

                if(!strDataAnomalia.equals("00:00:00 00/00/0000")){
                       try{
                        dataAnomalia = dateFormat.parse(strDataAnomalia);
                    }catch(ParseException e){
                        System.out.println("Erro(ServletOSSave.parseDate: " + e.getMessage());
                    }
                }
                

                String strDataInicio = request.getParameter("dt_ini_hr") + ":" +
                                        request.getParameter("dt_ini_min") + ":00 " +
                                        request.getParameter("dt_ini_dia") + "/" +
                                        request.getParameter("dt_ini_mes") + "/" +
                                        request.getParameter("dt_ini_ano");
                
                Date dataInicio = new Date();
                
                try {
                    dataInicio = dateFormat.parse(strDataInicio);
                } catch (ParseException e) {
                    System.out.println("Erro(ServletOSSave.parseDate: " + e.getMessage());
                } 
                
                
                os = new OS();
                os.setTipoOS(tipoOS);
                os.setCheckEquipamento(checkEquipamento);
                os.setEquipamento(equipamento);
                os.setUsuarioAutor(usuarioAutor);
                os.setUsuarioDest(usuarioDest);
                os.setDescDefeito(request.getParameter("defeito"));
                os.setPrioridade(Integer.parseInt(request.getParameter("prioridade_os")));
                os.setDataAnomalia(dataAnomalia);
                os.setDataAbertura(dataInicio);
                
                if (manterOSControl.salvarOS(os)) {
                    response.sendRedirect(request.getContextPath() + "/osAdd.jsp?sucess=3");
                } else {
                    response.sendRedirect(request.getContextPath() + "/osAdd.jsp?sucess=2");
                }
                
            } else {
                response.sendRedirect(request.getContextPath() + "/osAdd.jsp?sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/osAdd.jsp?sucess=1");
        }
    }
}
