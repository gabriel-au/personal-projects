package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.util.GravarEstatistica;
import br.com.prime.fiscon.mobile.nucleo.AutoInfracao;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class ResultadoConsultaVeiculos extends MobileDialog {
	private VeiculoPojo veiculo;

	public ResultadoConsultaVeiculos(VeiculoPojo veiculo) {
		super("Dados do Veículo");
		this.veiculo = veiculo;
		append(new Label("Os dados do veículo estão corretos?"));

		append(new Label("Placa: " + veiculo.getVeiculoPlaca()));
		append(new Label("Marca/Modelo: " + veiculo.getMarcaModelo()));
		append(new Label("Cor: " + veiculo.getCor()));
		append(new Label("Ano Fabricação: " + veiculo.getAnoFabricacao()));
		append(new Label("Ano Modelo: " + veiculo.getAnoModelo()));
		append(new Label("Tipo: " + veiculo.getTipo()));
		append(new Label("Categoria: " + veiculo.getCategoria()));
		append(new Label("Especie: " + veiculo.getEspecie()));
		append(new Label("Município: " + veiculo.getMunicipio()));
		String restricao = veiculo.getRestricao();
		if (restricao != null && !restricao.trim().equals("")) {
			append(new Label("Restrição: " + restricao));
		}
		append(new Label(""));
	}

	public void init() {
		GravarEstatistica.gravarConsulta(0, 1, 0);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Multar");
	}

	public void executarRight() {
		//AutoInfracao.getInstance().setVeiculoPojo(veiculo);
		new ConsultaVeiculoMultar(this,veiculo).show();
	}

}
