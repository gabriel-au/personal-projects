package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoConsultaVeiculoConfirma extends MobileDialog {
	public AutoInfracaoConsultaVeiculoConfirma() {
		super("Dados do Veículo");
		append(new Label("Os dados do veículo estão corretos?"));

		append(new Label("Placa: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getVeiculoPlaca()));
		append(new Label("Marca/Modelo: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getMarcaModelo()));
		append(new Label("Cor: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo().getCor()));
		append(new Label("Ano Modelo: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getAnoModelo()));
		append(new Label("Ano Fabricação: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getAnoFabricacao()));
		append(new Label("Tipo: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo().getTipo()));
		append(new Label("Categoria: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getCategoria()));
		append(new Label("Especie: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo().getEspecie()));
		append(new Label("Município: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getMunicipio()));
		String restricao = AutoInfracaoPojo.getInstance().getVeiculoPojo()
				.getRestricao();
		if (restricao != null && !restricao.trim().equals("")) {
			append(new Label("Restrição: " + restricao));
		}
		append(new Label(""));

	}

	public AutoInfracaoConsultaVeiculoConfirma(MobileDialog prevScreen) {
		this();
		this.setPrevScreen(prevScreen);
	}

	public void init() {

	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		AutoInfracaoConsultaCNH autoInfracaoConsultaCNH = new AutoInfracaoConsultaCNH();
		autoInfracaoConsultaCNH.setPrevScreen(this);
		autoInfracaoConsultaCNH.show();
	}

	public void executarLeft() {
		new AutoInfracaoConsultaVeiculo().show();
	}
}