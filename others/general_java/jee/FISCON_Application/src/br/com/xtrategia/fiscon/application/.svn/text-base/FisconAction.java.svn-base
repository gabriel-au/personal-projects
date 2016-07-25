package br.com.xtrategia.fiscon.application;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Classe para implementar as ações dos botões e menus
 * @author Gustavo
 *
 */
public abstract class FisconAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public FisconAction(String titulo){
		super(titulo);
	}
	
	
	protected ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Não encontrou o arquivo: " + path);
	        return null;
	    }
	}

}
