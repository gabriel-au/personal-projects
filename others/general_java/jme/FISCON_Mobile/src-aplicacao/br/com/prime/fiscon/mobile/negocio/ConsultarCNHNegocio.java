package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;

import br.com.codequest.mobile.MobileException;
import br.com.codequest.mobile.util.ServerConnect;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.pojo.CNHPojo;

public class ConsultarCNHNegocio extends ServerConnect{

	private CNHPojo cnh;
	
	public boolean consultar(Hashtable atributos){
		addProperty("transacao", "consultarCNH");
		addProperty("cnh", (String) atributos.get("numero_da_cnh"));
		addProperty("dataNascimento", (String) atributos.get("data_nascimento"));
		return montarPojo();
	}
	
	public boolean consultarPorCPF(Hashtable atributos){
		addProperty("transacao", "consultarCNHporCPF");
		addProperty("cpf", (String) atributos.get("cpf"));
		return montarPojo();
	}
	
	private boolean montarPojo() {
		cnh = new CNHPojo();
		cnh.setRegistro("03302674321");
		cnh.setDataNascimento("17/04/1967");
		cnh.setNome("ALEXANDRE GUEDES");
		cnh.setCategoria("AB");
		cnh.setDataValidade("04/10/2015");
		cnh.setTipo("CONDUTOR");
		cnh.setTotalPontos("17");
		
		return true;
	}
	
	/*private boolean montarPojo() {
		try {
			String resultado =connect();
			
			if (resultado.startsWith("Falha")) {
				return false;
			}
			
			Hashtable mapa = StringUtils.stringToHashtable(resultado);
			
			if (mapa.get("Erro")!=null) {
				return false;
			}
			cnh = new CNHPojo();
			cnh.setRegistro((String)mapa.get("registro"));
			cnh.setDataNascimento((String)mapa.get("dataNascimento"));
			cnh.setNome((String)mapa.get("nome"));
			cnh.setCategoria((String)mapa.get("categoria"));
			cnh.setDataValidade((String)mapa.get("dataValidade"));
			cnh.setTipo((String)mapa.get("tipo"));
			cnh.setTotalPontos((String)mapa.get("totalPontos"));
			
		} catch (MobileException e) {
			return false;
		}
		
		return true;
	}*/
	
	public CNHPojo getCnh() {
		return cnh;
	}

	public void setCnh(CNHPojo cnh) {
		this.cnh = cnh;
	}
	
}
