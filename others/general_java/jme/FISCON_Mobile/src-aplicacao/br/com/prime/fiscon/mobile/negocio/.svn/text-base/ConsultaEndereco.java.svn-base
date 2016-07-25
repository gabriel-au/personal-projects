package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;

import br.com.codequest.mobile.util.ServerConnect;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.pojo.EnderecoPojo;

public class ConsultaEndereco extends ServerConnect {
	// http://maps.google.com/maps/geo?ll=-15.823932,-47.90198&output=xml&sensor=true_or_false&key=ABQIAAAAFnCArmTyWSW8wqBRWkg7QhTagTsArHU_La9eEjO8OEF9eLywJhS9z-OAcQRa3VUfGhLT8eeR-ifwiQ
	private EnderecoPojo enderecoPojo ;
	public boolean consultar(Hashtable atributos) {
		addProperty("transacao", "recuperarEndereco");
		addProperty("latitude", (String) atributos.get("latitude"));
		addProperty("longitude", (String) atributos.get("longitude"));

		try {
			String resultado = connect();
			if (resultado.startsWith("Falha")) {
				return false;
			}

			Hashtable mapa = StringUtils.stringToHashtable(resultado);

			if (mapa.get("Erro") != null) {
				return false;
			}
			enderecoPojo = new EnderecoPojo();
			enderecoPojo.setEndereco(StringUtils.removeCaracterEspecial((String) mapa.get("endereco")));
			enderecoPojo.setBairro(StringUtils.removeCaracterEspecial((String) mapa.get("bairro")));
			enderecoPojo.setCidade(StringUtils.removeCaracterEspecial((String) mapa.get("cidade")));
			enderecoPojo.setUf(StringUtils.removeCaracterEspecial((String) mapa.get("uf")));

		} catch (Exception e) {
			return false;
		}

		return true;
	}
	public EnderecoPojo getEnderecoPojo() {
		return enderecoPojo;
	}
	public void setEnderecoPojo(EnderecoPojo enderecoPojo) {
		this.enderecoPojo = enderecoPojo;
	}
	
}
