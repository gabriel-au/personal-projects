package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.persistencia.ManterUserDAO;
import br.com.consorciosdf.intranet.seguranca.Criptography;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletUserEditPass extends HttpServlet {

    private ManterUserControl manterUserControl;
    private Usuario usuario;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String passOld = request.getParameter("passOld");
        String passNew = request.getParameter("passNew");
        String passNewConfirm = request.getParameter("passNewConfirm");
        
        if (user != null && passOld != null &&
            passNew != null && passNewConfirm != null) {
            
            if (!user.trim().equals("") && !passOld.trim().equals("") &&
                !passNew.trim().equals("") && !passNewConfirm.trim().equals("")) {
                
                manterUserControl = new ManterUserControl();
                Usuario userOld = new Usuario();
                userOld.setUser(user);
                userOld.setPassword(Criptography.criptografar(passOld));
                
                usuario = manterUserControl.loginWeb(userOld);

                if (usuario != null) {
                    if (passNew.equals(passNewConfirm)) {
                        usuario.setPassword(Criptography.criptografar(passNew));

                        if (manterUserControl.editPassword(usuario)) {
                            //senha alterada
                            response.sendRedirect(request.getContextPath() + "/userEditPass.jsp?sucess=6");
                        } else {
                            //senha nao pode ser alterada
                            response.sendRedirect(request.getContextPath() + "/userEditPass.jsp?sucess=5");
                        }
                    } else {
                        //senhas nao conferem
                        response.sendRedirect(request.getContextPath() + "/userEditPass.jsp?sucess=4");
                    }
                } else {
                    //senha incorreta
                    response.sendRedirect(request.getContextPath() + "/userEditPass.jsp?sucess=3");
                }
            } else {
                //dados nao digitados
                response.sendRedirect(request.getContextPath() + "/userEditPass.jsp?sucess=1");
            }
        } else {
            //n�o pode inserir
            response.sendRedirect(request.getContextPath() + "/userEditPass.jsp?sucess=2");
        }
    }

}