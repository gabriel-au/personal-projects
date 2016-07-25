package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.prime.fiscon.mobile.negocio.ConsultarVeiculoNegocio;

public class ConsultaVeiculosAguarde extends AguardeForm {

	public ConsultaVeiculosAguarde(MobileDialog dialog) {
		super(dialog);
	}

	public void processar() {
		ConsultarVeiculoNegocio c = new ConsultarVeiculoNegocio();
		if(c.consultar(getMapeamentoAtributos())){
			ResultadoConsultaVeiculos rcv =new ResultadoConsultaVeiculos(c.getVeiculo());
			rcv.setPrevScreen(dialog);
			rcv.show();
		}else{
			showMensagemErro("Consulta de Veículos", "Veículo não encontrado.", dialog);
		}
	}

}
