package br.com.xtrategia.fiscon.teste;

public class TesteString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String linhas = "aaaa=bbbbb";
		
		int index = linhas.indexOf("=");
		System.out.println(linhas.substring(0, index)+":"+ linhas.substring(index+1));

	}

}
