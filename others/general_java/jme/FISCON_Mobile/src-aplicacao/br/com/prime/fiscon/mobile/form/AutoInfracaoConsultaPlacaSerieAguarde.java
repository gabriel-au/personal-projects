package br.com.prime.fiscon.mobile.form;

import java.util.Vector;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.AguardeForm;
import br.com.prime.fiscon.mobile.negocio.ConsultarVeiculoNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoConsultaPlacaSerieAguarde extends AguardeForm {

	public AutoInfracaoConsultaPlacaSerieAguarde(MobileDialog dialog) {
		super(dialog);
	}

	public void processar() {
		ConsultarVeiculoNegocio c = new ConsultarVeiculoNegocio();
		if (c.consultar(dialog.getMapeamentoAtributos())) {
			VeiculoPojo vp = c.getVeiculo();
			System.out.println(vp);
			AutoInfracaoPojo.getInstance().setVeiculoPojo(vp);
			if (AutoInfracaoPojo.getInstance().getVeiculos() == null)
				AutoInfracaoPojo.getInstance().setVeiculos(new Vector());
		} 
		MobileDialog d = new AutoInfracaoSerieConfirmaPlaca(dialog);
		d.show();
	}
}