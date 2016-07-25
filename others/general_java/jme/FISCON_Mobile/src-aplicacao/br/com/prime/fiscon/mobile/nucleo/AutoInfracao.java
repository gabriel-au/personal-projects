package br.com.prime.fiscon.mobile.nucleo;

import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracao extends Thread {

	private static AutoInfracaoPojo pojo;
	
	private AutoInfracao(){
		
	}
	
	public void run() {
		//gerar o serial único
		
		//enviar as infrações
	}
	/**
	 * Só pode ser chamado quando salvar a multa
	 */
	public static String getSerialInfracao(){
		//criar um valor único para a multa
		return "00001010101";
	}
	
	public static AutoInfracaoPojo getInstance(){
		return pojo;
	}
	
	public static void gravar(AutoInfracaoPojo pojo){
		//gravar em disco
		
		//enviar se possível
		
		//apagar do disco
		
	}
	
	public static void gravarParcial(AutoInfracaoPojo pojo){
		//gravar em disco
		
	}
	
}
