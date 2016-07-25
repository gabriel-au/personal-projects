package br.com.codequest.mobile.ui;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.form.MenuPrincipal;
import br.com.prime.fiscon.mobile.negocio.AutenticarUsuarioNegocio;

/**
 * Formulário para exibir uma mensagem de espera
 * @author Gustavo
 *
 */
public class LoginAguardeForm extends AguardeForm{

	public LoginAguardeForm(MobileDialog dialog) {
		super(dialog);
	}

	public void processar() {
		AutenticarUsuarioNegocio au = new AutenticarUsuarioNegocio();
		if(au.autenticar(getMapeamentoAtributos())){
			new MenuPrincipal().show();
		}else{
			showMensagemErro("Autenticação", "Usuário ou senha inválidos", dialog);
		}
	}

	
}
