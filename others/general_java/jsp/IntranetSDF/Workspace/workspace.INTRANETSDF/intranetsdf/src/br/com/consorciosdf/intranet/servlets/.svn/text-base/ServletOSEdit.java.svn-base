package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.modelo.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletOSEdit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ManterOSControl manterOSControl = new ManterOSControl();
        OS os = null;
        //CheckEquipamento checkEquipamento = null;
        Equipamento equipamento = null;
        Usuario usuarioAutor = null;
        Usuario usuarioDest = null;

        int idOs = 0;

        if (request.getParameter("id") != null ||
                request.getParameter("tipo_os") != null ||
                request.getParameter("usuario_autor") != null ||
                request.getParameter("usuario_dest") != null ||
                request.getParameter("equipamento") != null ||
                request.getParameter("numSerie") != null ||
                request.getParameter("numUPR") != null ||
                request.getParameter("numCamera") != null ||
                request.getParameter("numFlash") != null ||
                request.getParameter("defeito") != null ||
                request.getParameter("prioridade_os") != null ||
                request.getParameter("observacao") != null ||
                request.getParameter("reparo") != null ||
                request.getParameter("serv_exec") != null ||
                request.getParameter("fechamento") != null ||
                request.getParameter("dt_ano_hr") != null ||
                request.getParameter("dt_ano_min") != null ||
                request.getParameter("dt_ano_dia") != null ||
                request.getParameter("dt_ano_mes") != null ||
                request.getParameter("dt_ano_ano") != null ||
                request.getParameter("dt_ini_hr") != null ||
                request.getParameter("dt_ini_min") != null ||
                request.getParameter("dt_ini_dia") != null ||
                request.getParameter("dt_ini_mes") != null ||
                request.getParameter("dt_ini_ano") != null ||
                request.getParameter("dt_serv_hr") != null ||
                request.getParameter("dt_serv_min") != null ||
                request.getParameter("dt_serv_dia") != null ||
                request.getParameter("dt_serv_mes") != null ||
                request.getParameter("dt_serv_ano") != null ||
                request.getParameter("dt_fim_hr") != null ||
                request.getParameter("dt_fim_min") != null ||
                request.getParameter("dt_fim_dia") != null ||
                request.getParameter("dt_fim_mes") != null ||
                request.getParameter("dt_fim_ano") != null ||
                request.getParameter("status") != null) {
            if (!request.getParameter("equipamento").trim().equals("") ||
                    !request.getParameter("defeito").trim().equals("")) {

                if (!request.getParameter("id").trim().equals("")) {
                    idOs = Integer.parseInt(request.getParameter("id"));
                }

                //checkEquipamento = new CheckEquipamento();

                /*if (!request.getParameter("checagem").trim().equals("")) {
                int idCheck = Integer.parseInt(request.getParameter("checagem"));
                checkEquipamento.setId(idCheck);
                }*/

                equipamento = new Equipamento();

                String tipoOS = request.getParameter("tipo_os");

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

                if (!request.getParameter("usuario_autor").trim().equals("")) {
                    usuarioAutor = new Usuario();
                    usuarioAutor.setMatriculaUsuario(request.getParameter("usuario_autor"));
                }

                if (!request.getParameter("usuario_dest").trim().equals("")) {
                    usuarioDest = new Usuario();
                    usuarioDest.setMatriculaUsuario(request.getParameter("usuario_dest"));
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

                String strDataAnomalia = request.getParameter("dt_ano_hr") + ":" +
                        request.getParameter("dt_ano_min") + ":00 " +
                        request.getParameter("dt_ano_dia") + "/" +
                        request.getParameter("dt_ano_mes") + "/" +
                        request.getParameter("dt_ano_ano");
                String strDataInicio = request.getParameter("dt_ini_hr") + ":" +
                        request.getParameter("dt_ini_min") + ":00 " +
                        request.getParameter("dt_ini_dia") + "/" +
                        request.getParameter("dt_ini_mes") + "/" +
                        request.getParameter("dt_ini_ano");
                String strDataServico = request.getParameter("dt_serv_hr") + ":" +
                        request.getParameter("dt_serv_min") + ":00 " +
                        request.getParameter("dt_serv_dia") + "/" +
                        request.getParameter("dt_serv_mes") + "/" +
                        request.getParameter("dt_serv_ano");
                String strDataFim = request.getParameter("dt_fim_hr") + ":" +
                        request.getParameter("dt_fim_min") + ":00 " +
                        request.getParameter("dt_fim_dia") + "/" +
                        request.getParameter("dt_fim_mes") + "/" +
                        request.getParameter("dt_fim_ano");

                Date dataAnomalia = null;
                Date dataInicio = null;
                Date dataServico = null;
                Date dataFim = null;

                try {
                    if (!strDataAnomalia.equals("0:0:00 0/0/0")) {
                        dataAnomalia= dateFormat.parse(strDataAnomalia);
                    }
                    if (!strDataInicio.equals("0:0:00 0/0/0")) {
                        dataInicio = dateFormat.parse(strDataInicio);
                    }
                    if (!strDataServico.equals("0:0:00 0/0/0")) {
                        dataServico = dateFormat.parse(strDataServico);
                    }

                    if (!strDataFim.equals("0:0:00 0/0/0")) {
                        dataFim = dateFormat.parse(strDataFim);
                    }

                } catch (ParseException e) {
                    System.out.println("Erro(ServletOSSave.parseDate: " + e.getMessage());
                }

                os = new OS();
                os.setId(idOs);
                os.setTipoOS(tipoOS);
                //os.setCheckEquipamento(checkEquipamento);
                os.setEquipamento(equipamento);
                os.setUsuarioAutor(usuarioAutor);
                os.setUsuarioDest(usuarioDest);
                os.setDescDefeito(request.getParameter("defeito"));
                os.setDescObservacao(request.getParameter("observacao"));
                os.setDescReparo(request.getParameter("reparo"));
                os.setDescFechamento(request.getParameter("fechamento"));
                os.setDescServicoExecutado(request.getParameter("serv_exec"));
                os.setDataAnomalia(dataAnomalia);
                os.setDataAbertura(dataInicio);
                os.setDataServico(dataServico);
                os.setDataFechamento(dataFim);
                os.setStatus(request.getParameter("status"));
                os.setPrioridade(Integer.parseInt(request.getParameter("prioridade_os")));

                if (manterOSControl.alterarOS(os)) {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + idOs + "&sucess=4");
                } else {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + idOs + "&sucess=2");
                }

            } else {
                response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + idOs + "&sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + idOs + "&sucess=1");
        }
    }
}
