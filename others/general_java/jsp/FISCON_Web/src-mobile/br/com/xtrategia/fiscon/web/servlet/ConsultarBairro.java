package br.com.xtrategia.fiscon.web.servlet;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.BairroPojo;
import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * @author Gustavo
 *
 */
public class ConsultarBairro extends MobileAction{

	@SuppressWarnings("unchecked")
	public String consultar(String idMunicipio){
		BairroPojo pojo = new BairroPojo();
		try {
			pojo.setMunicipioPojo(new MunicipioPojo(new Integer(idMunicipio)));
		} catch (NumberFormatException e1) {
			return "Falha";
		}
		List<BairroPojo> lista;
		
		try {
			lista = (List<BairroPojo>)  getFachada().execute(pojo, "ListarBairro").getConteudo();
		} catch (FISCONException e) {
			return "Erro=Falha na consulta";
		}		
			
		if(lista==null){
			return "Falha";
		}
		
		String nome="";
		String codigo="";		
		for(BairroPojo p: lista ){
			nome+=p.getNome()+"#";
			codigo+=p.getId()+"#";
		}
		
		String retorno=	"nomes="+nome+";ids="+codigo;
		
		return retorno;
	}
	
}
