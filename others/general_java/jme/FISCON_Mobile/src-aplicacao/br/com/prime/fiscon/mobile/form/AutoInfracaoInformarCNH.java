package br.com.prime.fiscon.mobile.form;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
import br.com.codequest.mobile.ui.components.TextNumerico;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.CNHPojo;

public class AutoInfracaoInformarCNH extends MobileDialog {
	private TextAlfabeto proprietario;
	private TextNumerico registroCNH;

	public AutoInfracaoInformarCNH() {
		super("Informar Proprietário");
	}

	public void init() {
		proprietario = new TextAlfabeto("Proprietário");
		registroCNH = new TextNumerico("N. Registro CNH");

		append(proprietario, true);
		append(registroCNH);
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		CNHPojo cnhPojo = new CNHPojo();
		cnhPojo.setNome(proprietario.getString());
		cnhPojo.setRegistro(registroCNH.getString());
		AutoInfracaoPojo.getInstance().setCnh(cnhPojo);
		AutoInfracaoPojo.getInstance().setFlag_condutor(true);
		new AutoInfracaoConsultaCodigoInfracao(this).show();
	}
}