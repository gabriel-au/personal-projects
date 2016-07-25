package br.com.prime.fiscon.mobile.teste;

import java.util.Vector;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.ComboBox;
import br.com.codequest.mobile.ui.components.ListBox;

public class TesteListBox extends MobileDialog {

	
	public TesteListBox() {
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
		
		append(new ListBox(lista,this),true);
		append(new ListBox(lista,this));
		append(new ListBox(lista,this));
		append(new ComboBox(lista));
	}

	protected void definirMenu() {
		setMenuText("Voltar", "");
	}
	
}
