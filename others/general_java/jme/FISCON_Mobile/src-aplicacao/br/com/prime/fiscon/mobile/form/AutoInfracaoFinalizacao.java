package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
/**
 * Tela para complementar o endereço
 * @author Gustavo
 *
 */
public class AutoInfracaoFinalizacao extends MobileDialog {

	private TextAlfabeto textoObservacao;
	
	public AutoInfracaoFinalizacao() {
		super("Finalização");
	}

	public void init() {
		append(new Label("Tela com o resumo dos dados para efetivar a multa"));
		append(new Label("Veiculo"));
		append(new Label("Veiculo"));
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Enviar");
	}
	
	public void executarRight() {
		new AutoInfracaoObservacaoConfirma(this,textoObservacao.getString()).show();
	}
}
