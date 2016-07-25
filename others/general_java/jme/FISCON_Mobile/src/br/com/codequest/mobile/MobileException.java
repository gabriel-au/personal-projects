package br.com.codequest.mobile;

public class MobileException extends Exception {

    public MobileException() {
        super();
    }
    
    public MobileException(Exception ex) {
        super(ex.getMessage());
    }
	
}
