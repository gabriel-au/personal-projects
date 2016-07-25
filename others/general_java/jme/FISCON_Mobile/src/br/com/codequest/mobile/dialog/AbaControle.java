package br.com.codequest.mobile.dialog;

import java.util.Enumeration;
import java.util.Vector;

import br.com.codequest.mobile.MobileDialog;


public class AbaControle{

	private int posicao;
	private Vector dialogs = new Vector();
	
	public AbaControle(){
		posicao=0;
	}
	
	public String[] getTitulos(){
		String []titulos = new String[dialogs.size()];
		int contador=0;
		
		Enumeration itens = dialogs.elements();
		while (itens.hasMoreElements()) {
			MobileDialog dialog = (MobileDialog) itens.nextElement();
			titulos[contador++]= dialog.getTitle();
			
		}
		
		return titulos;
	}
	
	public void add(MobileDialog aba){
		if(aba instanceof MobileDialog){
			dialogs.addElement(aba);
		}
	}
	
	public MobileDialog getAbaAtiva() {
		return ((MobileDialog)dialogs.elementAt(posicao));
	}

	public void anterior() {
		posicao--;
		if(posicao<0){
			posicao= dialogs.size()-1;;
		}
	}
	
	public void proximo() {
		posicao++;
		if(posicao>=dialogs.size()){
			posicao=0;
		}
	}
	
	public int getIdAtivo(){
		return posicao;
	}
	
}
