package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.Usuario;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletUserDelete extends HttpServlet  {
    
    private ManterUserControl manterUserControl;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((request.getParameter("user")) != null) {
            if (!request.getParameter("user").trim().equals("")) {
                if (manterUserControl == null) {
                    manterUserControl = new ManterUserControl();
                }
                
                Usuario usuario = new Usuario();
                usuario.setUser(request.getParameter("user"));
                
                if (manterUserControl.deleteUser(usuario)) {
                    response.sendRedirect(request.getContextPath() + "/userDelete.jsp?sucess=3");
                } else {
                    response.sendRedirect(request.getContextPath() + "/userDelete.jsp?sucess=1");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/userDelete.jsp?sucess=2");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/userDelete.jsp?sucess=1");
        }
    }
}