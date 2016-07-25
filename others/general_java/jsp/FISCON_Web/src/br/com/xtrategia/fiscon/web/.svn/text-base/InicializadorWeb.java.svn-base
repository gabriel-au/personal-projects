package br.com.xtrategia.fiscon.web;

import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.xtrategia.fiscon.infra.CommandFactory;
import br.com.xtrategia.fiscon.infra.HibernateUtil;

/**
 * Classe para inicializar o hibernate loga após o deploy
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class InicializadorWeb extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static private Log logging = LogFactory.getLog(InicializadorWeb.class);
	static {
		try {
			//foça a inicialização do hibernate
			HibernateUtil.loadCFG();
			//inicia a fábrica de commands
			CommandFactory.getInstancia();

		} catch (Exception ex) {
			logging.error(ex.getMessage());
		}
	}

}
