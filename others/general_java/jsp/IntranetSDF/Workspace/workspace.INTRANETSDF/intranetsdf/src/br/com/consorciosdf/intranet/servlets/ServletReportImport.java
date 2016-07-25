package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterReportControl;
import br.com.consorciosdf.intranet.modelo.RelatorioTipo;
import br.com.consorciosdf.intranet.modelo.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletReportImport extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ManterReportControl manterReportControl = new ManterReportControl();
        
        RelatorioTipo relatorioTipo = new RelatorioTipo();
        relatorioTipo.setId(Integer.parseInt(request.getParameter("idRelatorio")));
        
        Usuario usuarioInclusao = new Usuario();
        usuarioInclusao.setMatriculaUsuario((String) session.getAttribute("userMatricula"));
        
        String arquivo = request.getParameter("arquivo");
        
        if (manterReportControl.importReport(getServletContext().getRealPath("/tmp"), arquivo, relatorioTipo, usuarioInclusao)) {
            response.sendRedirect(request.getContextPath() + "/reportImport.jsp?sucess=3");
        } else {
            response.sendRedirect(request.getContextPath() + "/reportImport.jsp?sucess=2");
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
}
