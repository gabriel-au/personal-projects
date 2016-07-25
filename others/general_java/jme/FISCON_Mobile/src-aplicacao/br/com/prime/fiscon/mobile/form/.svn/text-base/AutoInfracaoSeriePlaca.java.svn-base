package br.com.prime.fiscon.mobile.form;

import java.util.Enumeration;
import java.util.Vector;

import org.j4me.ui.components.HorizontalRule;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextPlaca;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoSeriePlaca extends MobileDialog {
	public static final String TEXTO_PLACA = "Placa";
	private static TextPlaca placa;

	public AutoInfracaoSeriePlaca(MobileDialog prevScreen) {
		super("Placas");
		this.setPrevScreen(prevScreen);
	}

	protected void init() {
		placa = new TextPlaca(TEXTO_PLACA);
		append(placa, true);
		Vector veiculos = AutoInfracaoPojo.getInstance().getVeiculos();
		if (veiculos != null && !veiculos.isEmpty()) {
			append(new Whitespace(5));
			append(new HorizontalRule());
			append(new Whitespace(5));
			// lista as placas adicionadas

			if (veiculos != null) {
				for (Enumeration e = veiculos.elements(); e.hasMoreElements();) {
					VeiculoPojo vp = (VeiculoPojo) e.nextElement();
					append(new Label(vp.getVeiculoPlaca() + " - "
							+ vp.getMarcaModelo()));
				}
			}
		}

	}

	public void show() {
		placa.setString("");
		super.show();
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Adicionar");
	}

	public void executarRight() {
		new AutoInfracaoConsultaPlacaSerieAguarde(this).show();
	}

	public void executarLeft() {
		// limpa as placas
		AutoInfracaoPojo.getInstance().setVeiculos(new Vector());
		// volta para prevscreen
		super.executarLeft();
	}

}
