package br.com.codequest.mobile.ui.components;

import org.j4me.ui.components.TextBox;

/**
 * 
 */
public class TextCPF extends TextBox {

	public TextCPF(String label) {
		setLabel(label);
	}
	
	/**
	 * Called when a key is pressed.
	 * 
	 * @param keyCode
	 *            is the key code of the key that was pressed.
	 */
	public void keyPressed(int keyCode) {
		//System.out.println(new Character((char) keyCode).toString() + "="
		//		+ keyCode);
		String contents = getString();

		if (keyCode >=48 && keyCode<=57) {


			if (contents == null) {
				contents = "";
			} 
			
			if(contents.length()<=13){
				contents += new Character((char) keyCode).toString();
			}
			
		}else if(keyCode==-8){
			if(contents.length()==4 || contents.length()==8|| contents.length()==12){
				contents = contents.substring(0, contents.length() - 2);				
			}else{
				contents = contents.substring(0, contents.length() - 1);				
			}
		}

		if(contents.length()==3 || contents.length()==7){
			contents+=".";
		}else if(contents.length()==11){
			contents+="-";
		}
			
		
		setString(contents);
		repaint();
	}

}
