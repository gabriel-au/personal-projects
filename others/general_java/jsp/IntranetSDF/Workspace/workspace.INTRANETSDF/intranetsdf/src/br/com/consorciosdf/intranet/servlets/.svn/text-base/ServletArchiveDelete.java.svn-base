package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterArchiveControl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletArchiveDelete extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                       throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ManterArchiveControl manterArchiveControl = null;
        
        if (request.getParameter("id") != null) {
            if (!request.getParameter("id").trim().equals("")) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                manterArchiveControl = new ManterArchiveControl();
                
                if (manterArchiveControl.apagarArquivo(id)) {
                    response.sendRedirect(request.getContextPath() + "/archiveList.jsp?sucess=1");
                } else {
                    response.sendRedirect(request.getContextPath() + "/archiveList.jsp?sucess=2");
                }
            }
        }
    }
    
}
