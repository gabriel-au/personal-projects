package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
/**
 * Tela para complementar o endereço
 * @author Gustavo
 *
 */
public class AutoInfracaoComplemento extends MobileDialog {

	private TextAlfabeto textoComplemento;
	
	public AutoInfracaoComplemento(MobileDialog dialog) {
		super("Complemento do endereço");
		
	}

	public void init() {
		textoComplemento = new TextAlfabeto("Complemento");
		append(textoComplemento,true);
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Próximo");
	}
	
	public void executarRight() {
		new AutoInfracaoComplementoConfirma(this,textoComplemento.getString().toUpperCase()).show();
	}
	public void executarLeft() {
		new AutoInfracaoConfirmaEndereco().show();
	}
}
