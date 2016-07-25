package br.com.xtrategia.fiscon.application;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controle de log
 * @author Gustavo
 *
 */
public class LogApplicacao {

	private static JTabbedPaneFiscon saida;
	
	private LogApplicacao(){
		
	}

	public static void setSaida(JTabbedPaneFiscon saida){
		LogApplicacao.saida=saida;
	}
	
	public static void gravar(String mensagem){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mensagem = "["+sdf.format(new Date())+"] "+ mensagem;
		
		mensagem = saida.getTextoAtivo()+"\n"+mensagem;
		saida.setTextoAtivo(mensagem);
		
	}
}
