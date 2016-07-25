package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoLerGPSConfirma extends MobileDialog {
	private Label latitude;
	private Label longitude;
	private Label altitude;

	public AutoInfracaoLerGPSConfirma(MobileDialog dialog) {
		super("Confirmar Posicionamento Geográfico");
		setPrevScreen(dialog);
	}

	public void init() {
		AutoInfracaoConsultaVeiculo aic = new AutoInfracaoConsultaVeiculo();
		aic.setPrevScreen(this);

		setLastScreen(aic);

		latitude = new Label();
		longitude = new Label();
		altitude = new Label();

		append(altitude);
		append(latitude);
		append(longitude);

	}

	public void show() {
		super.show();

		latitude.setLabel("Latitude:"
				+ AutoInfracaoPojo.getInstance().getGlobalPosition()
						.getLatitude());
		longitude.setLabel("Longitude: "
				+ AutoInfracaoPojo.getInstance().getGlobalPosition()
						.getLongitude());
		altitude.setLabel("Altitude: "
				+ AutoInfracaoPojo.getInstance().getGlobalPosition()
						.getAltitude());
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		new AutoInfracaoComplemento(this).show();
	}
}
