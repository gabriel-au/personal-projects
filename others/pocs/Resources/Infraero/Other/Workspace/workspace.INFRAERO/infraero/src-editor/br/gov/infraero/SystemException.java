package br.gov.infraero;

/**
 * Tratamento das Regras de Sistema
 * 
 * @author Gustavo Marcelo Costa
 *
 */
public class SystemException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SystemException() {
		super();
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
