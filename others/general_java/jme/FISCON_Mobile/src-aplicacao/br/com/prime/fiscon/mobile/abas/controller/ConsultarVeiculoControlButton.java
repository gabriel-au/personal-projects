package br.com.prime.fiscon.mobile.abas.controller;

import br.com.codequest.mobile.Controll;
import br.com.codequest.mobile.MobileDialog;

public class ConsultarVeiculoControlButton extends Controll {
	private MobileDialog dialog;
	public ConsultarVeiculoControlButton(MobileDialog dialog){
		super();
		this.dialog = dialog;
	}
	
	public void executar() {
		dialog.showAlert(this.dialog.getTitle(),"Localizando Veiculo.\n Favor Aguarde", new ConsultaVeiculoThreadAguarde(this.dialog));
	}

}
