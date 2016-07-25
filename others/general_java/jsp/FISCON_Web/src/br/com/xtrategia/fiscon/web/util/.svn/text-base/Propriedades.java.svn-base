package br.com.xtrategia.fiscon.web.util;

import java.util.Map;

/**
 * Classe para armazenar as configurações do sistema
 * @author 64732436153
 *
 */
public class Propriedades {

	private static Map<String, String> cache;
	
	private Propriedades(){
		
	}
	
	public static void init (Map<String, String> cache){
		Propriedades.cache=cache;
	}
	
	public static String getValor(String chave){
		return cache.get(chave);
	}
	
}
