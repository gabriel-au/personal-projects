package br.com.xtrategia.fiscon.web.servlet;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.InfracaoTipoPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * @author Gustavo
 *
 */
public class ConsultarInfracaoTipo extends MobileAction{

	@SuppressWarnings("unchecked")
	public String consultar(){
		InfracaoTipoPojo pojo = new InfracaoTipoPojo();
		List<InfracaoTipoPojo> lista;
		
		try {
			lista = (List<InfracaoTipoPojo>)  getFachada().execute(pojo, "ListarInfracaoTipo").getConteudo();
		} catch (FISCONException e) {
			return "Erro=Falha na consulta";
		}		
			
		if(lista==null){
			return "Falha";
		}
		
		String nome="";
		String codigo="";		
		for(InfracaoTipoPojo p: lista ){
			nome+=p.getNome()+"#";
			codigo+=p.getId()+"#";
		}
		
		String retorno=	"nomes="+nome+";codigos="+codigo;
		
		return retorno;
	}
	
}
