package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextCPF;
import br.com.prime.fiscon.mobile.negocio.ConsultarCNHNegocio;

public class ConsultaCNHporCPF extends MobileDialog {

	public ConsultaCNHporCPF() {
		super("Consultar CNH por CPF");
	}

	public void init() {
		append(new TextCPF("CPF"),true);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Consultar");
	}
	
	public void executarRight() {
		ConsultarCNHNegocio c = new ConsultarCNHNegocio();
		if(c.consultarPorCPF(getMapeamentoAtributos())){
			ResultadoConsultaCNH rc =new ResultadoConsultaCNH(c.getCnh());
			rc.setPrevScreen(this);
			rc.show();
		}else{
			showMensagemErro("Consulta CNH por CPF", "CNH não encontrada.", this);
		}
	}
}