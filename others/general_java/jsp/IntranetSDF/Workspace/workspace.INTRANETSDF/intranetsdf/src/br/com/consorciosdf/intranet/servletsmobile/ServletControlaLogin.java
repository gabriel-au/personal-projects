package br.com.consorciosdf.intranet.servletsmobile;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.OS;
import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.seguranca.Criptography;

/**
 * Servlet implementation class ServletControlaLogin
 */
public class ServletControlaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = null;

		if (!request.getParameter("user").equals("")
				&& !request.getParameter("pass").equals("")) {
			usuario = new Usuario();
			usuario.setUser(request.getParameter("user"));
			usuario.setPassword(Criptography.criptografar(request
					.getParameter("pass")));

			ManterUserControl control = new ManterUserControl();

			usuario = control.loginWeb(usuario);

			if (usuario != null) {
				request.getSession(true).setAttribute("usuarioFilter", usuario);
				// response.sendRedirect(request.getContextPath()+"/mobile/main.jsp");
				response.sendRedirect("controlaListaOS");
			} else {
				response.sendRedirect(request.getContextPath()
						+ "/mobile/index.jsp?erro=2");
			}
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/mobile/index.jsp?erro=1");
		}

	}

}
