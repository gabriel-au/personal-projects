package br.com.xtrategia.fiscon.application;

public class FisconException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FisconException(Exception erro){
		LogApplicacao.gravar(erro.getMessage());
	}

}
