package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.prime.fiscon.mobile.negocio.ConsultarCNHNegocio;

public class AutoInfracaoConsultaCNHAguarde extends AguardeForm {

	public AutoInfracaoConsultaCNHAguarde(MobileDialog dialog) {
		super(dialog);
	}

	public void processar() {
		
		ConsultarCNHNegocio c = new ConsultarCNHNegocio();
		if(c.consultar(getMapeamentoAtributos())){
			AutoInfracaoResultadoConsultaCNH rc =new AutoInfracaoResultadoConsultaCNH(c.getCnh());
			rc.setPrevScreen(dialog);
			rc.show();
		}else{
			new FalhaConsultaCHN(getTitle(), "CNH não encontrada", this).show();
			//showMensagemErro("Consulta CNH", "CNH não encontrada.", dialog);
		}
	}

}
