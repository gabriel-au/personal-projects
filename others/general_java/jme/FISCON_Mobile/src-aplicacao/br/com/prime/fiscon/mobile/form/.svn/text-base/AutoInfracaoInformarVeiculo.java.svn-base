package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
import br.com.codequest.mobile.ui.components.TextPlaca;
import br.com.prime.fiscon.mobile.lista.ListaEspecieVeiculo;
import br.com.prime.fiscon.mobile.lista.ListaUF;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.EspecieVeiculoPojo;
import br.com.prime.fiscon.mobile.pojo.UFPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoInformarVeiculo extends MobileDialog {
	private TextPlaca placa;
	private TextAlfabeto modeloMarca;
	private ListBox listBoxUF;
	private ListBox listBoxVeiculoEspecie;
	public AutoInfracaoInformarVeiculo() {
		super("Informar Veículo");
	}

	public void init() {
		placa = new TextPlaca("Placa");
		
		modeloMarca = new TextAlfabeto("Modelo/Marca");
		
		ListaUF.load();
		ListaEspecieVeiculo.load();
		listBoxUF = new ListBox(ListaUF.listaUF, this);
		listBoxVeiculoEspecie = new ListBox(ListaEspecieVeiculo.listaVeiculoEspecie, this);
		append(placa, true);
		append(listBoxUF);
		append(modeloMarca);
		append(listBoxVeiculoEspecie);
		setPrevScreen(new AutoInfracaoConsultaVeiculo());
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		VeiculoPojo veiculoPojo = new VeiculoPojo();
		veiculoPojo.setVeiculoPlaca(placa.getString());
		veiculoPojo.setUf(((UFPojo)listBoxUF.getValorSelecionado()).getUf());
		veiculoPojo.setEspecie(((EspecieVeiculoPojo)listBoxVeiculoEspecie.getValorSelecionado()).getEspecie());
		veiculoPojo.setMarcaModelo(modeloMarca.getString());
		AutoInfracaoPojo.getInstance().setVeiculoPojo(veiculoPojo);
		AutoInfracaoPojo.getInstance().setFlag_veiculo(true);
		AutoInfracaoConsultaCNH autoInfracaoConsultaCNH =new AutoInfracaoConsultaCNH();
		autoInfracaoConsultaCNH.setPrevScreen(this);
		autoInfracaoConsultaCNH.show();
	}
}