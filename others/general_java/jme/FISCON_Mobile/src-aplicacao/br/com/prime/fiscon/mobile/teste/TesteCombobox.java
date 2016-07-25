package br.com.prime.fiscon.mobile.teste;

import java.util.Vector;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.ComboBox;

public class TesteCombobox extends MobileDialog {

	
	public TesteCombobox() {
		super("Ajuda");
	}

	public void init() {
		Vector lista = new Vector();
		lista.addElement("a");
		lista.addElement("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"); 
		lista.addElement("ccccc"); 
		lista.addElement("ddddd"); 
		lista.addElement("e"); 
		lista.addElement("f"); 
		lista.addElement("g");
		
		append(new ComboBox(lista),true);
		append(new ComboBox(lista));
		append(new ComboBox(lista));
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}
