package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterComprasControl;
import br.com.consorciosdf.intranet.modelo.Compras;
import br.com.consorciosdf.intranet.modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletComprasSave extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
                       throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ManterComprasControl manterComprasControl = new ManterComprasControl();
        
        Compras compras = new Compras();
        Usuario usuario = new Usuario();
        usuario.setMatriculaUsuario((String) session.getAttribute("userMatricula"));
        
        if (request.getParameter("descricao") != null ||
                request.getParameter("empresas") != null){
            if (!request.getParameter("descricao").trim().equals("") ||
                    !request.getParameter("empresas").trim().equals("")) {
                
                compras.setUsuarioInclusao(usuario);
                compras.setDescricao(request.getParameter("descricao"));
                compras.setEmpresasParticipantes(request.getParameter("empresas"));
                
                if (manterComprasControl.salvarCompra(compras)) {
                    response.sendRedirect(request.getContextPath() + "/comprasAdd.jsp?sucess=1");
                } else {
                    response.sendRedirect(request.getContextPath() + "/comprasAdd.jsp?sucess=2");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/comprasAdd.jsp?sucess=3");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/comprasAdd.jsp?sucess=3");
        }
        
    }
    
}
