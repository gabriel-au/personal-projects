package br.com.prime.fiscon.mobile.negocio;

import java.util.Hashtable;

import br.com.codequest.mobile.MobileException;
import br.com.codequest.mobile.util.ServerConnect;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.nucleo.Usuario;
import br.com.prime.fiscon.mobile.pojo.TransactionObject;
import br.com.prime.fiscon.mobile.pojo.UsuarioPojo;

/**
 * Autenticar o usuário e guardar a sessão
 * @author Gustavo
 *
 */
public class AutenticarUsuarioNegocio extends ServerConnect{


	public boolean autenticar(Hashtable mapeamentoAtributos){
		String usuario = (String) mapeamentoAtributos.get("usuario");
		String senha = (String) mapeamentoAtributos.get("senha");
		
		addProperty("transacao", "login");
		addProperty("usuario",usuario);
		addProperty("senha", senha);
		
		String resultado="";

		try {
			resultado =connect();
		} catch (MobileException e) {
			resultado="falha";
		}
		System.out.println("RESULT: "+resultado);
		if( resultado.startsWith("falha")){
			return false;
		}
		
		try {
			System.out.println("RESULT: "+resultado);
			Hashtable mapa = StringUtils.stringToHashtable(resultado);
			UsuarioPojo pojo = new UsuarioPojo();
			TransactionObject.setId((String)mapa.get("id"));
			pojo.setMatricula((String)mapa.get("matricula"));
			pojo.setNome((String)mapa.get("nome"));
			pojo.setSobrenome((String)mapa.get("sobrenome"));
			pojo.setHoraUlimoAcesso((String)mapa.get("horaUlimoAcesso"));
			
			Usuario.setIntance(pojo);
			
			return true;
		} catch (Exception e) {
			//TODO deve ser falso, está true apenas para demonstrar o produto
			return true;
		}
		
		
	}
	
}
