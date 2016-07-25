package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.modelo.OS;
import br.com.consorciosdf.intranet.modelo.Usuario;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletOSAddServ extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ManterOSControl manterOSControl = new ManterOSControl();

        OS os = new OS();

        java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
        Date data = calendar.getTime();
        os.setDataServico(data);
        os.setStatus("an");
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario((String) session.getAttribute("userName"));
        usuario.setSobrenomeUsuario((String) session.getAttribute("userLastname"));

        if (request.getParameter("id") != null ||
                request.getParameter("servico") != null) {
            if (!request.getParameter("id").trim().equals("") ||
                    !request.getParameter("servico").trim().equals("")) {
                os.setUsuarioAutor(usuario);
                os.setId(Integer.parseInt(request.getParameter("id")));
                os.setDescServicoExecutado(request.getParameter("servico"));

                if (manterOSControl.incluirServOS(os)) {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + os.getId() + "&sucess=6");
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
