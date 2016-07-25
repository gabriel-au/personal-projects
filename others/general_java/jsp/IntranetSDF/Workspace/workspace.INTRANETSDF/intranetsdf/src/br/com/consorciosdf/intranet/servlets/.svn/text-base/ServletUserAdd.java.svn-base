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

public class ServletUserAdd extends HttpServlet {

    private ManterUserControl manterUserControl;
    private Usuario usuario;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ((request.getParameter("name")) != null &&
                (request.getParameter("lastName")) != null &&
                (request.getParameter("email")) != null &&
                (request.getParameter("matricula")) != null &&
                (request.getParameter("user")) != null &&
                (request.getParameter("pass")) != null &&
                (request.getParameter("passConfirm")) != null &&
                (request.getParameter("perfil")) != null) {

            if (!request.getParameter("name").trim().equals("") &&
                    !request.getParameter("lastName").trim().equals("") &&
                    !request.getParameter("email").trim().equals("") &&
                    !request.getParameter("matricula").trim().equals("") &&
                    !request.getParameter("user").trim().equals("") &&
                    !request.getParameter("pass").trim().equals("") &&
                    !request.getParameter("passConfirm").trim().equals("") &&
                    !request.getParameter("perfil").trim().equals("")) {


                String pass = request.getParameter("pass");
                String passConfirm = request.getParameter("passConfirm");

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

                    usuario.setNomeUsuario(request.getParameter("name"));
                    usuario.setSobrenomeUsuario(request.getParameter("lastName"));
                    usuario.setEmailUsuario(request.getParameter("email"));
                    usuario.setMatriculaUsuario(request.getParameter("matricula"));
                    usuario.setUser(request.getParameter("user"));
                    usuario.setPassword(Criptography.criptografar(request.getParameter("pass")));
                    usuario.setPerfilUsuario(Integer.parseInt(request.getParameter("perfil")));
                    usuario.setSkype(request.getParameter("skype"));
                    usuario.setUsuarioRules(userRules);

                    if (manterUserControl.addUser(usuario)) {
                        response.sendRedirect(request.getContextPath() + "/userAdd.jsp?sucess=4");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/userAdd.jsp?sucess=2");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/userAdd.jsp?sucess=3");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/userAdd.jsp?sucess=1");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/userWebAdd.jsp?sucess=2");
        }
    }
}
