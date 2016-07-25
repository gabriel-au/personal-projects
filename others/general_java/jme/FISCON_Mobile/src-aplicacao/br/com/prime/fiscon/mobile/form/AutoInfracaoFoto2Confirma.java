package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoFoto2Confirma extends MobileDialog {

	public AutoInfracaoFoto2Confirma() {
		super("Confirmar a Foto do veículo");

	}

	public void init() {
		Picture picture = new Picture();
		picture.setImage(Image.createImage(AutoInfracaoPojo.getInstance()
				.getSegundaFotoPojo().getDado(), 0, AutoInfracaoPojo
				.getInstance().getSegundaFotoPojo().getDado().length));
		append(new Label("Confirma a Foto do veículo?"));
		append(picture);

	}

	protected void definirMenu() {
		setMenuText("Voltar", "Próximo");
	}

	public void executarRight() {
		if (AutoInfracaoPojo.getInstance().getVeiculoPojo() == null) {
			AutoInfracaoConsultaVeiculo aic = new AutoInfracaoConsultaVeiculo();
			aic.setPrevScreen(this);
			aic.show();
		} else {
			AutoInfracaoConsultaCNH autoInfracaoConsultaCNH = new AutoInfracaoConsultaCNH();
			autoInfracaoConsultaCNH.setPrevScreen(this);
			autoInfracaoConsultaCNH.show();
		}
	}

	public void executarLeft() {
		new AutoInfracaoFoto2(this.getPrevScreen().getPrevScreen()).show();
	}
}
