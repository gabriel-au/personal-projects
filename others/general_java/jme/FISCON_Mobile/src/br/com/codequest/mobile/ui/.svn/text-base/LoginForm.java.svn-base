package br.com.codequest.mobile.ui;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextNumerico;
import br.com.codequest.mobile.ui.components.TextTeste;

/**
 * Formulário para o Login do usuário
 * @author Gustavo
 *
 */
public class LoginForm extends MobileDialog {

	
	public LoginForm() {
		super("");
	}

	public void init() {
		/*Picture cadeado = new Picture();
		try {
			cadeado.setImage(Image.createImage("/icones/encrypted-64.png"));
			cadeado.setHorizontalAlignment(Graphics.HCENTER);
			append(cadeado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//paintBackground(Graphics)
		append(new Label(""));
		append(new TextNumerico("Usuário"),true);
		appendPassword("Senha");
	}
	
	protected void definirMenu() {
		setMenuText(SAIR, ENTRAR);
	}
	
	/**
	 * regras de negocio
	 */
	public void executarRight() {
		new LoginAguardeForm(this).show();
	}
}
