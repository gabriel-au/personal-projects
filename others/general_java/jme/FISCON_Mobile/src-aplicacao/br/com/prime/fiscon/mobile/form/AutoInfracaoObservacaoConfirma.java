package br.com.prime.fiscon.mobile.form;

/*import net.sf.microlog.core.Logger;
 import net.sf.microlog.core.LoggerFactory;
 import net.sf.microlog.core.format.PatternFormatter;
 import net.sf.microlog.midp.bluetooth.BluetoothSerialAppender;*/

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AutoInfracaoObservacaoConfirma extends MobileDialog {
	private String texto;

	public AutoInfracaoObservacaoConfirma(MobileDialog dialog, String texto) {
		super("Observações");
		append(new Label("Texto"));
		append(new Label(texto));
		setPrevScreen(dialog);
		this.texto = texto;
	}

	public void init() {
		// configureMicroLog();
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		AutoInfracaoPojo.getInstance().setObservacao(texto);
		/*
		 * configureMicroLog(); log.debug("iniciou");
		 * log.debug("Antes de armazenar localmente");
		 */
		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")
				&& (AutoInfracaoPojo.getInstance().getObservacao() != null && !AutoInfracaoPojo
						.getInstance().getObservacao().equals(""))) {
			try {
				CRUDNegocio crudNegocio = new CRUDNegocio();
				crudNegocio.gravar(AutoInfracaoPojo.getInstance());
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(),
						Passo.AUTOINFRACAOFINALIZACAOCOMFOTO));
				// log.debug("Depois de armazenar localmente");
				/*
				 * BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
				 * bd.salvarOuAtulizar
				 * (AutoInfracaoPojo.getInstance().serializarHashtable()); new
				 * RecuperarAutoInfracao().gravar(new
				 * Passo(AutoInfracaoPojo.getInstance().getId(),
				 * Passo.AUTOINFRACAOFINALIZACAOCOMFOTO));
				 */
			} catch (BancoDadosException be) {
				// log.trace("Falha ao salvar dados localmente", be);
				showMensagemErro(getTitle(),
						"Falha ao salvar dados localmente", this);
			} catch (Exception e) {
				// log.trace("Falha ao salvar dados localmente", e);
				showMensagemErro(getTitle(), "Falha ao salvar dados locamente",
						this);
			}
		}
		// log.debug("Chama AutoInfracaoFinalizacaoComFoto().show()");
		boolean isSerie = AutoInfracaoPojo.getInstance().isFlag_serie();
		if (isSerie)
			new AutoInfracaoSeriePlaca(this).show();
		else
			new AutoInfracaoFinalizacaoComFoto(this).show();
	}
	// public void executarLeft() {
	// new AutoInfracaoObservacao().show();
	// }
}
