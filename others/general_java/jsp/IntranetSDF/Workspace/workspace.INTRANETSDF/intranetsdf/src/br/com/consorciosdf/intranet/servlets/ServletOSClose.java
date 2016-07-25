package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.modelo.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletOSClose extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ManterOSControl manterOSControl = new ManterOSControl();
        OS os = null;
        Usuario usuarioDest = null;
        
        if (request.getParameter("idOS") != null ||
                request.getParameter("userDest") != null ||
                request.getParameter("reparo") != null ||
                request.getParameter("serv_exec") != null ||
                request.getParameter("fechamento") != null ||
                request.getParameter("dt_fim_hr") != null ||
                request.getParameter("dt_fim_min") != null ||
                request.getParameter("dt_fim_dia") != null ||
                request.getParameter("dt_fim_mes") != null ||
                request.getParameter("dt_fim_ano") != null) {
            if (!request.getParameter("idOS").trim().equals("") ||
                    !request.getParameter("reparo").trim().equals("") ||
                    !request.getParameter("serv_exec").trim().equals("")) {
                
                if (!request.getParameter("userDest").trim().equals("")) {
                    usuarioDest = new Usuario();
                    usuarioDest.setMatriculaUsuario(request.getParameter("userDest"));
                }
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                String strDataFim = request.getParameter("dt_fim_hr") + ":" +
                                    request.getParameter("dt_fim_min") + ":00 " +
                                    request.getParameter("dt_fim_dia") + "/" +
                                    request.getParameter("dt_fim_mes") + "/" +
                                    request.getParameter("dt_fim_ano");
                
                Date dataFim = new Date();
                
                try {
                    dataFim = dateFormat.parse(strDataFim);
                } catch (ParseException e) {
                    System.out.println("Erro(ServletOSClose.parseDate: " + e.getMessage());
                }
                
                os = new OS();
                os.setId(Integer.parseInt(request.getParameter("idOS")));
                os.setUsuarioDest(usuarioDest);
                os.setDescReparo(request.getParameter("reparo"));
                os.setDescServicoExecutado(request.getParameter("serv_exec"));
                os.setDataFechamento(dataFim);
                os.setDescFechamento(request.getParameter("fechamento"));
                
                if (manterOSControl.fecharOS(os)) {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" +
                            request.getParameter("idOS") + "&sucess=3");
                } else {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" +
                            request.getParameter("idOS") + "&sucess=2");
                }
                
            } else {
                response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" +
                        request.getParameter("idOS") + "&sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" +
                    request.getParameter("idOS") + "&sucess=1");
        }
    }
    
}
