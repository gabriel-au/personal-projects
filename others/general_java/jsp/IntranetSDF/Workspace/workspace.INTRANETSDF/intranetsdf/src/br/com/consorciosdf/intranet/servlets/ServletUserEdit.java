package br.com.consorciosdf.intranet.servlets;

import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.modelo.UsuarioRule;
import br.com.consorciosdf.intranet.seguranca.Criptography;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletUserEdit extends HttpServlet {

    private ManterUserControl manterUserControl;
    private Usuario usuario;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String tipo = request.getParameter("tipo");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String matricula = request.getParameter("matricula");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String passConfirm = request.getParameter("passConfirm");
        String perfil = request.getParameter("perfil");

        if (name != null && lastName != null &&
                email != null && matricula != null &&
                user != null && pass != null &&
                passConfirm != null && perfil != null) {

            if (!name.trim().equals("") &&
                    !lastName.trim().equals("") &&
                    !email.trim().equals("") &&
                    !matricula.trim().equals("") &&
                    !user.trim().equals("") &&
                    !perfil.trim().equals("")) {


                pass = request.getParameter("pass").trim();
                passConfirm = request.getParameter("passConfirm").trim();

                if (pass.equals(passConfirm)) {
                    if (manterUserControl == null) {
                        manterUserControl = new ManterUserControl();
                    }
                    List<UsuarioRule> userRules = new ArrayList();
                    List<UsuarioRule> rules = manterUserControl.listRules();
                    for (int i = 0; i < rules.size(); i++) {
                        if ((request.getParameter(rules.get(i).getNome())) != null) {
                            UsuarioRule userRule = new UsuarioRule();
                            userRule.setId(Integer.parseInt(request.getParameter(rules.get(i).getNome())));
                            userRules.add(userRule);
                        }
                    }
                    usuario = new Usuario();

                    usuario.setNomeUsuario(name);
                    usuario.setSobrenomeUsuario(lastName);
                    usuario.setEmailUsuario(email);
                    usuario.setMatriculaUsuario(matricula);
                    usuario.setUser(user);
                    usuario.setPerfilUsuario(Integer.parseInt(perfil));
                    usuario.setUsuarioRules(userRules);
                    if (!pass.trim().equals("")) {
                        usuario.setPassword(Criptography.criptografar(pass));
                    } else {
                        usuario.setPassword(pass);
                    }

                    if (manterUserControl.editUser(usuario)) {
                        response.sendRedirect(request.getContextPath() + "/userEdit.jsp?sucess=4");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/userEdit.jsp?sucess=2");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/userEdit.jsp?sucess=3");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/userEdit.jsp?sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/userEdit.jsp?sucess=2");
        }

    }
}
