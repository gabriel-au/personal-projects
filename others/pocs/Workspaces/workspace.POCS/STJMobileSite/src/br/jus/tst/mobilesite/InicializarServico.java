package br.jus.tst.mobilesite;

import javax.servlet.http.HttpServlet;

public class InicializarServico extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static {
		try {
			br.jus.stj.mobile.bd.Consulta.modoTeste = true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}