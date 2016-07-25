package br.com.prime.fiscon.mobile.form;

import java.util.Date;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class ConsultaVeiculoMultar extends MobileDialog {
	private VeiculoPojo veiculo;
	public ConsultaVeiculoMultar(MobileDialog prev, VeiculoPojo v) {
		super("");
		this.setPrevScreen(prev);
		this.veiculo=v;
	}

	protected void init() {
		append(new Whitespace(40));
		try {
			append(new Picture(Image.createImage("/icones/pergunta.png"),
					Graphics.HCENTER));
		} catch (Exception e) {
		}
		append(new Whitespace(10));
		append(new Label("Deseja utilizar infração com foto?", Graphics.HCENTER));
	}

	protected void definirMenu() {
		setMenuText("Não", "Sim");
	}

	public void executarRight() {
		AutoInfracaoPojo.getInstance().setVeiculoPojo(veiculo);
		AutoInfracaoPojo.getInstance().setFlag_consultaV(true);
		new AutoInfracaoFoto1(this).show();;
	}

	public void executarLeft() {
		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
			AutoInfracaoPojo.getInstance().setVeiculoPojo(veiculo);
			AutoInfracaoPojo.getInstance().setDataInfracao(new Date());
			AutoInfracaoPojo.getInstance().setFoto(true);
			AutoInfracaoPojo.getInstance().setFlag_condutor(false);
			AutoInfracaoPojo.getInstance().setFlag_endereco(false);
			AutoInfracaoPojo.getInstance().setFlag_veiculo(false);
			AutoInfracaoPojo.getInstance().setObservacao("");
			// Salvar localmente
			CRUDNegocio crudNegocio = new CRUDNegocio();
			try {
				crudNegocio.gravar(AutoInfracaoPojo.getInstance());
			} catch (BancoDadosException e) {
				showMensagemErro(getTitle(),
						"Falha ao salvar Auto de Infracao", this);
			}
		}
		AutoInfracaoConsultaCNH autoInfracaoConsultaCNH = new AutoInfracaoConsultaCNH();
		autoInfracaoConsultaCNH.setPrevScreen(this);
		autoInfracaoConsultaCNH.show();
	}

}
