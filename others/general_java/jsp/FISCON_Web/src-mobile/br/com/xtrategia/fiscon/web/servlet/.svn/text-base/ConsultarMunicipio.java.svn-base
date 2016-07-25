package br.com.xtrategia.fiscon.web.servlet;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * @author Gustavo
 *
 */
public class ConsultarMunicipio extends MobileAction{

	@SuppressWarnings("unchecked")
	public String consultar(String nome){
		MunicipioPojo pojo = new MunicipioPojo();
		pojo.setNome(nome);
		List<MunicipioPojo> lista;
		
		try {
			lista = (List<MunicipioPojo>)  getFachada().execute(pojo, "ListarMunicipio").getConteudo();
		} catch (FISCONException e) {
			return "Erro=Falha na consulta";
		}		
			
		if(lista==null){
			return "Falha";
		}
		
		String nomes="";
		String codigos="";		
		for(MunicipioPojo p: lista ){
			nomes+=p.getNome()+"#";
			codigos+=p.getId()+"#";
		}
		
		String retorno=	"nomes="+nomes+";ids="+codigos;
		
		return retorno;
	}
	
}