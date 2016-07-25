package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
/**
 * Tela para complementar o endereço
 * @author Gustavo
 *
 */
public class AutoInfracaoObservacao extends MobileDialog {

	private TextAlfabeto textoObservacao;
	public AutoInfracaoObservacao() {
		super("Observações");
	}
	public AutoInfracaoObservacao(MobileDialog dialog) {
		this();
		this.setPrevScreen(dialog);
	}

	public void init() {
		textoObservacao = new TextAlfabeto("Texto");
		append(textoObservacao,true);
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Próximo");
	}
	
	public void executarRight() {
		//log.debug("Apertei botao direito");
		new AutoInfracaoObservacaoConfirma(this,textoObservacao.getString().toUpperCase()).show();
	}
	
//	public void executarLeft() {
//		//log.debug("Apertei botao esquerdo");
//		new AutoInfracaoComplementoConfirma(null, AutoInfracaoPojo.getInstance().getEndereco().getComplemento()).show();
//	}
}
