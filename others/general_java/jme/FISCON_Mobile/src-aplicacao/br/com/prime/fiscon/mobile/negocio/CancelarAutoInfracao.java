package br.com.prime.fiscon.mobile.negocio;

import java.util.Enumeration;
import java.util.Hashtable;

import br.com.codequest.mobile.util.ServerConnect;
import br.com.codequest.mobile.util.StringUtils;

public class CancelarAutoInfracao extends ServerConnect {
	private Hashtable mapa;
	public boolean enviar(Hashtable atributos) throws Exception {
		addProperty("transacao", "cancelarInfracao");
		Enumeration keys = atributos.keys();
		while(keys.hasMoreElements()){
			String key = (String)keys.nextElement();
			addProperty(key,(String)atributos.get(key));
		}
		try {
			String resultado = connect();
			if (resultado.startsWith("Falha")) {
				return false;
			}
			 mapa = StringUtils.stringToHashtable(resultado);
			if (mapa.get("Erro") != null) {
				return false;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	public Hashtable getMapa(){
		return mapa;
	}
}
