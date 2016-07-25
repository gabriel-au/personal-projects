package br.com.xtrategia.fiscon.web.servlet;

import java.util.Iterator;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.RestricaoPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;
import br.com.xtrategia.fiscon.web.util.StringUtility;

/**
 * Classe para atender as consultas de veiculos do mobile
 * @author Gustavo
 *
 */
public class ConsultarVeiculo extends MobileAction{

	public String consultar(String placa){
		VeiculoPojo pojo = new VeiculoPojo();
		pojo.setVeiculoPlaca(placa);
		
		try {
			pojo = (VeiculoPojo) getFachada().execute(pojo,"ConsultarVeiculo");
			
			if(pojo==null){
				return "Erro=Falha na consulta";
			}
		} catch (FISCONException e) {
			return "Erro=Falha na consulta";
		}		
		String restricao="";
		if(pojo.getRestricaoPojos()!=null){
			Iterator<RestricaoPojo> tipos = pojo.getRestricaoPojos().iterator();
			
			while (tipos.hasNext()) {
				RestricaoPojo restricaoPojo = tipos.next();
				if(!restricao.equals("")){
					restricao += ", ";
				}
				restricao += restricaoPojo.getRestricaoTipoPojo().getNome();
			}
			restricao = StringUtility.calculaStringCanonica(restricao).toLowerCase();
		}		
		
		String retorno=	"cor=" + pojo.getCorPojo().getNome() +";"+
					"categoria=" + pojo.getCategoriaPojo().getNome() +";"+
					"municipio=" + pojo.getMunicipioPojo().getNome() +";"+
					"uf=" + pojo.getMunicipioPojo().getUf() +";"+
					"tipo=" + pojo.getTipoPojo().getNome() +";"+
					"especie=" + pojo.getEspeciePojo().getNome() +";"+
					"marcaModelo=" + pojo.getMarcaModeloPojo().getNome() +";"+
					"veiculoPlaca=" + pojo.getVeiculoPlaca() +";"+
					"anoModelo=" + pojo.getAnoModelo() +";"+
					"anoFabricacao=" + pojo.getAnoFabricacao()+";"+
					"restricao=" + restricao;
		
		
		return retorno;
	}
	
}
