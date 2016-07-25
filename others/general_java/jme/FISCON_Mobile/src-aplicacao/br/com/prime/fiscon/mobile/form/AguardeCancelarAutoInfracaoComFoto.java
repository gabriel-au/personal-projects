package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.prime.fiscon.mobile.negocio.AutoInfracaoNegocio;
import br.com.prime.fiscon.mobile.negocio.CancelarAutoInfracao;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AguardeCancelarAutoInfracaoComFoto extends AguardeForm {

	public AguardeCancelarAutoInfracaoComFoto(MobileDialog dialog) {
		super(dialog);

	}

	public void processar() {
		CancelarAutoInfracao cancelarAutoInfracao = new CancelarAutoInfracao();
		
		try {
			if (cancelarAutoInfracao.enviar(AutoInfracaoPojo.getInstance()
					.serializarCancelamentoHashtable())) {
				
				AutoInfracaoNegocio autoInfracaoNegocio = new AutoInfracaoNegocio();
				autoInfracaoNegocio.apagar();
				
				new AutoInfracaoCancelarEnviada().show();
			} else {
				showMensagemErro(getTitle(), "Falha no cancelamento do auto de infração.", new AutoInfracaoCancelarEnviada());
			}
		} catch (Exception e) {
			showMensagemErro(getTitle(), "Falha no cancelamento do auto de infração.", new AutoInfracaoCancelarEnviada());
		}
		setMenuText("Cancelar", "");
	}

	public void executarLeft() {
		new MenuAutoInfracao().show();
	}
}
