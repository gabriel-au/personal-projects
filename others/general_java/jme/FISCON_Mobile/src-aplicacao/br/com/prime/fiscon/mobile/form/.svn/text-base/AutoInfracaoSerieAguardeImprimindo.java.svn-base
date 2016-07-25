package br.com.prime.fiscon.mobile.form;

import java.io.IOException;

import org.j4me.ui.components.Picture;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.ConexaoException;
import br.com.codequest.mobile.exceptions.PareadoException;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.codequest.mobile.util.Propriedades;
import br.com.codequest.mobile.util.ZebraPrinterBluetooth;

public class AutoInfracaoSerieAguardeImprimindo extends AguardeForm {


	public AutoInfracaoSerieAguardeImprimindo(MobileDialog dialog,
			Picture picture) {
		super(dialog, picture, "Imprimindo...");
	}

	private ZebraPrinterBluetooth zebraPrinterBluetooth;
	private String addressDevice;

	public void init() {
	}

	public void processar() {
		try {
			zebraPrinterBluetooth = new ZebraPrinterBluetooth();
			zebraPrinterBluetooth.setEnvio(AutoInfracaoSerieImprimir
					.getFormulario());
			zebraPrinterBluetooth.connect(this.addressDevice);
		} catch (PareadoException e) {
			e.printStackTrace();
			showMensagemErro(getTitle(), "Falha ao parear: " + e.getMessage(),
					this);
			new AguardeConfigurarImpressora(this, true).show();
		} catch (ConexaoException e) {
			e.printStackTrace();
			showMensagemErro(getTitle(),
					"Falha ao conectar: " + e.getMessage(), this);
			setMenuText("Finalizar", "Tentar Novamente");
		} catch (IOException e) {
			e.printStackTrace();
			showMensagemErro(getTitle(), "Falha ao carregar o logo."
					+ e.getMessage(), this);
		} catch (Exception e) {

			showMensagemErro(getTitle(), "Desconhecido\n" + e, this);
		}
		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
			try {
				// CRUDNegocio crudNegocio = new CRUDNegocio();
				// AutoInfracaoPojo.getInstance().getVeiculoPojo().setStatus(
				// AutoInfracaoPojo.IMPRESSO);

				// ACABOU A IMPRESSAO
					new AutoInfracaoSerieConfimarImprimirNovamente(dialog)
							.show();
				

			} /*
			 * catch (BancoDadosException be) { showMensagemErro(getTitle(),
			 * "Falha ao excluir dados localmente", this); }
			 */catch (Exception e) {
				showMensagemErro(getTitle(),
						"Falha ao remover dados localmente", this);
			}
		}

	}

}
