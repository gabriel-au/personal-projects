package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.negocio.EnviarAutoInfracao;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AguardeSalvarAutoInfracaoComFoto extends AguardeForm {

	public AguardeSalvarAutoInfracaoComFoto(MobileDialog dialog) {
		super(dialog);

	}

	public void processar() {
		EnviarAutoInfracao enviarAutoInfracao = new EnviarAutoInfracao();
		try {
			if (enviarAutoInfracao.enviar(AutoInfracaoPojo.getInstance()
					.serializarHashtable())) {
				
				CRUDNegocio crudNegocio = new CRUDNegocio();
				AutoInfracaoPojo.getInstance().setNumeroAutoInfracao(enviarAutoInfracao.getMapa().get("numeroautoinfracao")+"");
				AutoInfracaoPojo.getInstance().setStatus(AutoInfracaoPojo.ENVIADO);
				crudNegocio.gravar(AutoInfracaoPojo.getInstance());
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOIMPRIMIR));
				new AutoInfracaoImprimir(this).show();
			} else {
				showMensagemErro(getTitle(), "Falha no envio "+enviarAutoInfracao.getMapa().toString(), new AutoInfracaoImprimir(this));
			}
		} catch (Exception e) {
			showMensagemErro(getTitle(), "Falha no envio. \n Essa infração será armazenada com incompleta.", new AutoInfracaoImprimir(this));
		}
		setMenuText("Cancelar", "");
	}

	public void executarLeft() {
		new AutoInfracaoEmSerieFinalizacao().show();
	}
}
