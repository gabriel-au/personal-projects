package br.com.xtrategia.fiscon;

/**
 * Tratamento das Regras de Negocio
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class FISCONException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FISCONException() {
		super();
	}

	public FISCONException(String message) {
		super(message);
	}

	public FISCONException(Throwable cause) {
		super(cause);
	}

	public FISCONException(String message, Throwable cause) {
		super(message, cause);
	}

	public FISCONException(Exception e){
		super(e);
	}
	
}
