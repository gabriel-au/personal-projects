package br.com.codequest.mobile.ui;

import java.util.Hashtable;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.Loading;

/**
 * Formulário para exibir uma mensagem de espera
 * 
 * @author Gustavo
 * 
 */
public abstract class AguardeForm extends MobileDialog implements Runnable {

	protected MobileDialog dialog;
	private Hashtable valores;

	private int primeiroEspaco = 80;
	private int segundoEspaco = 40;

	public AguardeForm(MobileDialog dialog) {
		super("Em Processamento");
		this.dialog = dialog;
		valores = dialog.getMapeamentoAtributos();
		appendTudo();
	}

	public AguardeForm(MobileDialog dialog, Picture icone, String msg) {
		super("Aguarde");
		this.dialog = dialog;
		valores = dialog.getMapeamentoAtributos();
		appendTudoComIcone(icone,msg);
	}

	public void appendTudo() {
		append(new Whitespace(primeiroEspaco));
		append(new Loading(8));
		append(new Whitespace(segundoEspaco));
		append(new Label("Aguarde um momento...", Graphics.HCENTER));
	}
	public void appendTudoComIcone(Picture icone, String msg) {
		append(new Whitespace(primeiroEspaco-40));
		append(icone);
		append(new Label(msg, Graphics.HCENTER));
		append(new Whitespace(segundoEspaco-50));
		append(new Loading(8));
	}

	public Hashtable getMapeamentoAtributos() {
		return valores;
	}

	public void init() {
	}

	protected void definirMenu() {
		setMenuText("", "");
	}

	public void show() {
		Thread t = new Thread(this);
		t.start();
		super.show();
	}

	public void close() {
		dialog.show();
	}

	public void run() {
		processar();
	}

	public abstract void processar();
}
