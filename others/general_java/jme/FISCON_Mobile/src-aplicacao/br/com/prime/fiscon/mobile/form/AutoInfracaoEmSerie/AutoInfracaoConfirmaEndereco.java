package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoConfirmaEndereco extends MobileDialog {
	public AutoInfracaoConfirmaEndereco() {
		super("Confirmar Endereço");
		append(new Label("A localização está correta?"));
		
		append(new Label("Logradouro: "
				+ AutoInfracaoPojo.getInstance().getEndereco().getEndereco()));
		append(new Label("Bairro: "
				+ AutoInfracaoPojo.getInstance().getEndereco().getBairro()));
		append(new Label("Municipio: "
				+ AutoInfracaoPojo.getInstance().getEndereco().getCidade()));
		append(new Label("UF: "
				+ AutoInfracaoPojo.getInstance().getEndereco().getUf()));
		
		append(new Label(""));
		
	}

	public void init() {
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		
		new AutoInfracaoComplemento(this).show();
	}
	public void executarLeft() {
		new AutoInfracaoConsultaCodigoInfracao(null).show();
	}
}