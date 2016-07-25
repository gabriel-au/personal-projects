package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.nucleo.AutoInfracao;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoSerieConfirmaPlaca extends MobileDialog {

	public AutoInfracaoSerieConfirmaPlaca(MobileDialog prev) {
		super("Confirmação");
		this.setPrevScreen(prev);

	}

	public AutoInfracaoSerieConfirmaPlaca(MobileDialog prev, VeiculoPojo vp) {
		this(prev);

	}

	protected void init() {
		// append(new Label("Placa adicionada: "+placa));

		// Se houver veículo no pojo, mostra as informações
		if (AutoInfracaoPojo.getInstance().getVeiculoPojo() != null) {
			append(new Whitespace(10));
			VeiculoPojo v = AutoInfracaoPojo.getInstance().getVeiculoPojo();
			append(new Label("Placa: " + v.getVeiculoPlaca()));
			append(new Label("Modelo: " + v.getMarcaModelo()));
			append(new Label("Cor: " + v.getCor()));
			append(new Label("Categoria: " + v.getCategoria()));
			append(new Label("Ano: " + v.getAnoFabricacao() + "/"
					+ v.getAnoModelo()));

			try {
				Picture questionMark = new Picture();
				questionMark.setImage("/icones/pergunta.png");
				questionMark.setHorizontalAlignment(Graphics.HCENTER);
				append(questionMark);
			} catch (Exception e) {
			}

			Label lblMaisUma = new Label("Deseja adicionar este veículo?");
			lblMaisUma.setHorizontalAlignment(Graphics.HCENTER);
			append(lblMaisUma);
			setMenuText("Não", "Sim");
		} else {
			// caso contrário, veículo nao foi encontrado
			append(new Whitespace(10));
			append(new Label("Veículo não encontrado!", Graphics.HCENTER));
			append(new Whitespace(20));
			try {
				Picture questionMark = new Picture();
				questionMark.setImage("/icones/pergunta.png");
				questionMark.setHorizontalAlignment(Graphics.HCENTER);
				append(questionMark);
			} catch (Exception e) {
			}
			append(new Label("Deseja adicionar mais uma placa?",
					Graphics.HCENTER));
			setMenuText("Não", "Sim");
		}
		append(new Whitespace(20));

	}

	public void executarLeft() {
		if (AutoInfracaoPojo.getInstance().getVeiculoPojo() != null) {
			AutoInfracao.getInstance().setVeiculoPojo(null);
			new AutoInfracaoSeriePlaca(this.getPrevScreen().getPrevScreen())
					.show();
		} else {
			new AutoInfracaoSerieAguardeSalvar(this.getPrevScreen()).show();
		}

	}

	public void executarRight() {
		if (AutoInfracaoPojo.getInstance().getVeiculoPojo() != null) {
			VeiculoPojo vp = AutoInfracaoPojo.getInstance().getVeiculoPojo();
			if (!AutoInfracaoPojo.getInstance().getVeiculos().contains(vp)) {
				AutoInfracaoPojo.getInstance().getVeiculos().addElement(vp);
			}
			AutoInfracaoPojo.getInstance().setVeiculoPojo(null);
			new AutoInfracaoSerieConfirmaNovaPlaca(this.getPrevScreen()).show();
		} else {
			this.getPrevScreen().show();
		}
	}
}
