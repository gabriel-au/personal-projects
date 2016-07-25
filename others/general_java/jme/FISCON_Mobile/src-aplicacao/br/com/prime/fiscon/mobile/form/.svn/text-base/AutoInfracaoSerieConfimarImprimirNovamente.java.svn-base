package br.com.prime.fiscon.mobile.form;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.VeiculoPojo;

public class AutoInfracaoSerieConfimarImprimirNovamente extends MobileDialog {

	public AutoInfracaoSerieConfimarImprimirNovamente(MobileDialog prevScreen) {
		super("Confirmação");
	}

	protected void init() {
		Integer indice = AutoInfracaoPojo.getInstance().getIndice();

		VeiculoPojo v = (VeiculoPojo) AutoInfracaoPojo.getInstance()
				.getVeiculos().elementAt(indice.intValue());
		append(new Label("Placa: " + v.getVeiculoPlaca()));
		append(new Label("Modelo: " + v.getMarcaModelo()));
		append(new Label("Cor: " + v.getCor()));
		append(new Label("Categoria: " + v.getCategoria()));
		append(new Label("Ano: " + v.getAnoFabricacao() + "/" + v.getAnoModelo()));
		append(new Whitespace(20));
		try {
			Picture p = new Picture(Image.createImage("/icones/pergunta.png"), Graphics.HCENTER);
			append(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		append(new Label("Deseja imprimir outra cópia?", Graphics.HCENTER));
	}

	protected void definirMenu() {
		setMenuText("Não", "Sim");
	}

	public void executarLeft() {
		Integer i = AutoInfracaoPojo.getInstance().incIndice();
		if (i.intValue() != -1) {
		new AutoInfracaoSerieConfirmaImprimir(this).show();
		}else {
			new AutoInfracaoSerieFim().show();
		}
	}

	public void executarRight() {
		Picture p = null;
		try {
			p = new Picture();
			p.setImage("/icones/impressora32.png");
			p.setHorizontalAlignment(Graphics.HCENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new AutoInfracaoSerieAguardeImprimindo(this, p).show();
	}

}
