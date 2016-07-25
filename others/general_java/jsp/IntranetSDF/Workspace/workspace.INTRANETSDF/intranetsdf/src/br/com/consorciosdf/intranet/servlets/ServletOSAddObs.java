package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.modelo.OS;
import br.com.consorciosdf.intranet.modelo.Usuario;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletOSAddObs extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ManterOSControl manterOSControl = new ManterOSControl();

        OS os = new OS();
        java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
        Date data = calendar.getTime();
        os.setDataServico(data);
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario((String) session.getAttribute("userName"));
        usuario.setSobrenomeUsuario((String) session.getAttribute("userLastname"));

        if (request.getParameter("id") != null ||
                request.getParameter("observacao") != null) {
            if (!request.getParameter("id").trim().equals("") ||
                    !request.getParameter("observacao").trim().equals("")) {
                os.setUsuarioAutor(usuario);
                os.setId(Integer.parseInt(request.getParameter("id")));
                os.setDescObservacao(request.getParameter("observacao"));

                if (manterOSControl.incluirObsOS(os)) {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + os.getId() + "&sucess=5");
                } else {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + os.getId() + "&sucess=2");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + os.getId() + "&sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + os.getId() + "&sucess=1");
        }

    }
}
