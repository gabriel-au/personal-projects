package br.com.prime.fiscon.mobile.abas;

import java.util.Hashtable;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.Button;
import br.com.codequest.mobile.ui.components.TextPlaca;
import br.com.prime.fiscon.mobile.abas.controller.ConsultarVeiculoControlButton;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoVeiculo extends MobileDialog{
	private Label placa;
	private Label marcaModelo;
	private Label anoModelo;
	private Label anoFabricacao;
	private Label cor;
	private Label categoria;
	private Label tipo;
	private Label especie;
	private Label municipio;
	private Label restricao;
	
	public AutoInfracaoVeiculo(String title){
		super(title);
		setTitle(title);
		
	}
	
	
	
	protected void init() {
		
		TextPlaca placaText = new TextPlaca("Placa");
		append(placaText, true);
		ConsultarVeiculoControlButton controll = new ConsultarVeiculoControlButton(this);
		Button consultar = new Button("Consultar", controll);
		consultar.setAlign(Button.ALIGN_CENTER);
		append(consultar);

		/*carregarTela(null);
		
		append(marcaModelo);
		append(anoModelo);
		append(anoFabricacao);
		append(cor);
		append(placa);
		append(categoria);
		append(tipo);
		append(especie);
		append(municipio);
		append(restricao);*/
		
	}
	
	
	public void carregarTela(Hashtable objeto) {
		placa = new Label("Placa: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getVeiculoPlaca());
		cor = new Label("Cor: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo().getCor());
		categoria = new Label("Categoria: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getCategoria());
		municipio = new Label("Município: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getMunicipio());
		tipo = new Label("Tipo: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo().getTipo());
		especie = new Label("Especie: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo().getEspecie());
		marcaModelo =new Label("Marca/Modelo: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getMarcaModelo());
		anoModelo = new Label("Ano Modelo: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getAnoModelo());
		anoFabricacao = new Label("Ano Fabricação: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getAnoFabricacao());
		restricao = new Label("Restrição: "
				+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
						.getRestricao());

	}
}
