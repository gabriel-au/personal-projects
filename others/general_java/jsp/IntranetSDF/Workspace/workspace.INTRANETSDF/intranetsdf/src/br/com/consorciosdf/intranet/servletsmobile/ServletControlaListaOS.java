package br.com.consorciosdf.intranet.servletsmobile;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.modelo.Equipamento;
import br.com.consorciosdf.intranet.modelo.OS;
import br.com.consorciosdf.intranet.modelo.Usuario;

/**
 * Servlet implementation class ServletControlaListaOS
 */
public class ServletControlaListaOS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioFilter");
		List<OS> listOS = null;
		ManterOSControl osControl = new ManterOSControl();
		
		int pageStart = 1;
		int numberPage = 1;
		
		if(request.getParameter("page")!= null && !request.getParameter("page").equals("")){
			pageStart = Integer.parseInt(request.getParameter("page"));
		}
		
		double numberPageAux = osControl.recuperarQtdOSMobile(usuario);
		
		if(numberPageAux > 0){
			numberPageAux /= 10;
			numberPage = (int) Math.ceil(numberPageAux);
			listOS = osControl.recuperarOSMobile(usuario, pageStart);
		}
		
		request.getSession(true).setAttribute("numberPage", numberPage);
		request.getSession(true).setAttribute("pageStart", pageStart);
		request.getSession(true).setAttribute("listaOSMobile", listOS);
		
		response.sendRedirect(request.getContextPath()+"/mobile/listaOS.jsp");		
	}


}
