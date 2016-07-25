package br.com.xtrategia.fiscon.web.servlet;


import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.EnderecoPojo;
import br.com.xtrategia.fiscon.web.pojo.GlobalPositionPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * 
 * @author Gustavo Luis
 * 
 */
public class RecuperarEndereco extends MobileAction {

	public String consultar(String latitude, String longitude) {
		GlobalPositionPojo pojo;
		try {
			pojo = new GlobalPositionPojo(Double
					.parseDouble(latitude), Double.parseDouble(longitude));
		} catch (NumberFormatException e1) {
			return "Falha";
		}
		EnderecoPojo enderecoPojo;
		try {
			enderecoPojo = (EnderecoPojo) getFachada().execute(pojo,
					"RecuperarEndereco");
		} catch (FISCONException e) {
			e.printStackTrace();
			return "Erro=Falha na consulta";
		}

		if (enderecoPojo == null) {
			return "Falha";
		}

		String retorno = "endereco=" + enderecoPojo.getEndereco();
		retorno += ";bairro=" + enderecoPojo.getBairroPojo().getNome();
		retorno += ";cidade=" + enderecoPojo.getMunicipioPojo().getNome();
		retorno += ";uf=" + enderecoPojo.getMunicipioPojo().getUf();

		return retorno;
	}

}
