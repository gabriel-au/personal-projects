package br.com.prime.fiscon.mobile.nucleo;

import br.com.prime.fiscon.mobile.pojo.UsuarioPojo;
/**
 * Classe para unificar os dados do usuário
 * 
 * @author Gustavoo Marcelo
 *
 */
public class Usuario {

	private static UsuarioPojo pojo;
	
	private Usuario(){
		
	}
	
	public static UsuarioPojo getInstance(){
		if(pojo==null){
			pojo = new UsuarioPojo();
			return pojo;
		}else{
			return pojo;
		}
	}
	
	/**
	 * este método grava o horário do ultimo acesso
	 * @param pojo
	 */
	public static void setIntance(UsuarioPojo pojo){
		Usuario.pojo=pojo;
		//gravar o horário do acesso
	}
	
	/**
	 * retorna o usuário gravado
	 * @return
	 */
	public static boolean carregarUsuario(){
		//verifica se está gravado em disco
		
		//se gravado verificar o horário do login
		
		//verificar se o horário está dentro do limite estabelecido
		
		return true;
	}
	
}
