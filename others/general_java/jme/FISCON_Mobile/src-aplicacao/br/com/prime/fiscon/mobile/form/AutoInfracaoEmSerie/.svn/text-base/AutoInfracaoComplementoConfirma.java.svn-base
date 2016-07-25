package br.com.prime.fiscon.mobile.form.AutoInfracaoEmSerie;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

public class AutoInfracaoComplementoConfirma extends MobileDialog {
	private String complemento;

	public AutoInfracaoComplementoConfirma(MobileDialog dialog, String texto) {
		super("Complemento do endereço");
		

		append(new Label("Complemento"));
		append(new Label(texto));
		this.complemento = texto;
	}

	public void init() {
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		AutoInfracaoPojo.getInstance().getEndereco()
				.setComplemento(complemento);
		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")
				&& (AutoInfracaoPojo.getInstance().getEndereco()
						.getComplemento() != null && !AutoInfracaoPojo
						.getInstance().getEndereco().getComplemento()
						.equals(""))) {
			try {
				
				CRUDNegocio crudNegocio = new CRUDNegocio();
				crudNegocio.gravar(AutoInfracaoPojo.getInstance().getEndereco());
				
				//Grava o proximo passo
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AUTOINFRACAOOBSERVACAO));
				
			} catch (BancoDadosException be) {
				showMensagemErro(getTitle(),
						"Falha ao salvar dados localmente", this);
			} catch (Exception e) {
				showMensagemErro(getTitle(), "Falha ao salvar dados locamente",
						this);
			}
		}

		new AutoInfracaoFoto().show();
	}
	public void executarLeft() {
		new AutoInfracaoComplemento(null).show();
	}
}
