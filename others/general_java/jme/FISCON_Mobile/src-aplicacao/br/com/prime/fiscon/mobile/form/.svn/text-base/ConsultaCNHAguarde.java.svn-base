package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.prime.fiscon.mobile.negocio.ConsultarCNHNegocio;

public class ConsultaCNHAguarde extends AguardeForm {

	public ConsultaCNHAguarde(MobileDialog dialog) {
		super(dialog);
	}

	public void processar() {
		
		ConsultarCNHNegocio c = new ConsultarCNHNegocio();
		if(c.consultar(getMapeamentoAtributos())){
			ResultadoConsultaCNH rc =new ResultadoConsultaCNH(c.getCnh());
			rc.setPrevScreen(dialog);
			rc.show();
		}else{
			showMensagemErro("Consulta CNH", "CNH não encontrada.", dialog);
		}
	}

}
