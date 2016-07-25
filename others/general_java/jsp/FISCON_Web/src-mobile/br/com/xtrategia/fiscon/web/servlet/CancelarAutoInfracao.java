package br.com.xtrategia.fiscon.web.servlet;

import javax.servlet.http.HttpServletRequest;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * 
 * @author Gustavo Luis
 * 
 */
public class CancelarAutoInfracao extends MobileAction {

	public String cancelar(HttpServletRequest request) {
		
		AutoInfracaoPojo autoInfracaoPojo = new AutoInfracaoPojo();

		// Recuperação das fotos
		if(request.getParameter("NUMEROAUTOINFRACAO")!=null && !request.getParameter("NUMEROAUTOINFRACAO").equals("")){
			System.out.println("dsd: "+request.getParameter("NUMEROAUTOINFRACAO"));
			autoInfracaoPojo.setNumeroAutoInfracao(request.getParameter("NUMEROAUTOINFRACAO"));
			
		}
		try {
			autoInfracaoPojo = (AutoInfracaoPojo) getFachada().execute(
					autoInfracaoPojo, "CancelarInfracao");
		} catch (FISCONException e) {
			e.printStackTrace();
			return "Falha na Inclusao";
		}
		return "numeroautoinfracao="+autoInfracaoPojo.getNumeroAutoInfracao();

	}


}
