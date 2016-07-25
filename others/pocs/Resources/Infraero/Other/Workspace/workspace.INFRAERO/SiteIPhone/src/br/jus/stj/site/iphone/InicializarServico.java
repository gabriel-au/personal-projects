package br.jus.stj.site.iphone;

import javax.servlet.http.HttpServlet;

import br.jus.stj.mobile.bd.Consulta;

public class InicializarServico extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static {
		try {
			Consulta.modoTeste=true;
			//aqui vai forçar para abrir os DS
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
