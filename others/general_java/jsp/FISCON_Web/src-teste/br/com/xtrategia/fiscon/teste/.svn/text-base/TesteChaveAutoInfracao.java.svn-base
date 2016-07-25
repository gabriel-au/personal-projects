package br.com.xtrategia.fiscon.teste;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.NegocioFacade;
import br.com.xtrategia.fiscon.infra.NegocioFacadeImpl;
import br.com.xtrategia.fiscon.web.pojo.AutoInfracaoPojo;
import br.com.xtrategia.fiscon.web.pojo.ChaveAutoInfracaoPojo;

public class TesteChaveAutoInfracao {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException {
		NegocioFacade fachada = new NegocioFacadeImpl();
		
		
		try {
			
			List<ChaveAutoInfracaoPojo> lista = (List<ChaveAutoInfracaoPojo>) fachada.execute(new AutoInfracaoPojo(), "ListarChavesAutoInfracao").getConteudo();
			
			for(ChaveAutoInfracaoPojo pojo:lista){
				System.out.println(pojo.getCodigo() +":"+pojo.getQuantidade()+":"+ pojo.getData());
			}
			
			
			
		} catch (FISCONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
