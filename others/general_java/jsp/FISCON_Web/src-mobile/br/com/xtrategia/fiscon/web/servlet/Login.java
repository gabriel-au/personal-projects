package br.com.xtrategia.fiscon.web.servlet;

import java.util.Date;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.MobileAction;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

/**
 * Classe para atender as consultas de veiculos do mobile
 * @author Gustavo
 *
 */
public class Login extends MobileAction{
	
	public String consultar(String usuario, String senha){
		
		UsuarioPojo pojo = new UsuarioPojo(usuario, senha);
		try {
			pojo=(UsuarioPojo) getFachada().execute(pojo, "LoginUsuario");
		} catch (FISCONException e) {
			pojo=null;
		}
		
		String retorno =((pojo!=null)?"sucesso=ok;":"falha;");
		
		
		if(pojo!=null){
			retorno+= "id="+pojo.getId()+";"+
					"matricula="+pojo.getMatricula()+";"+
					"nome="+pojo.getNome()+";"+
					"sobrenome="+pojo.getSobrenome()+";"+
					"horaUlimoAcesso="+new Date().getTime()+";";
		}
		
		return retorno;
	}
	
	
}
