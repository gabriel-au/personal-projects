package br.gov.infraero.prototipo;

import javax.servlet.http.HttpServlet;

public class InicializarServico extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static {
		try {
			//aqui coloca rotinas que inicializam o site
			
			//exemplo: pool de conex‹o
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
