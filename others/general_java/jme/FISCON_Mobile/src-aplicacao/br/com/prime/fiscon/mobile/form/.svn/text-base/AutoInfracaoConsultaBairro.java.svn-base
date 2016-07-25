package br.com.prime.fiscon.mobile.form;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.lista.ListaBairro;
import br.com.prime.fiscon.mobile.lista.ListaUF;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.BairroPojo;
import br.com.prime.fiscon.mobile.pojo.EnderecoPojo;
import br.com.prime.fiscon.mobile.pojo.UFPojo;
/**
 * Classe para exibir a escolha dos Bairros
 * @author Gustavo
 *
 */
public class AutoInfracaoConsultaBairro extends MobileDialog {
	private TextAlfabeto municipio;
	private TextAlfabeto logradouro;
	private ListBox listBox;
	private ListBox listBoxUF;
	private Label uf;
	private Label bairro;
	public AutoInfracaoConsultaBairro(MobileDialog dialog) {
		super("Endereço");
		setPrevScreen(dialog);
	}

	public void init() {
		uf = new Label("UF");
		bairro = new Label("Bairro");
		
		ListaBairro.load();
		listBox = new ListBox(ListaBairro.listaBairro, this);
		ListaUF.load();
		listBoxUF = new ListBox(ListaUF.listaUF, this);
		logradouro = new TextAlfabeto("Logradouro");
		municipio = new TextAlfabeto("Cidade/Município");
		
		
		append(logradouro, true);
		append(bairro);
		append(listBox);
		append(municipio);
		append(uf);
		append(listBoxUF);
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}
	
	public void executarRight() {
		EnderecoPojo enderecoPojo = new EnderecoPojo();
		enderecoPojo.setEndereco(StringUtils.removeCaracterEspecial(logradouro.getString().toUpperCase()));
		enderecoPojo.setBairro(StringUtils.removeCaracterEspecial(((BairroPojo)listBox.getValorSelecionado()).getNome().toUpperCase()));
		enderecoPojo.setCidade(StringUtils.removeCaracterEspecial(municipio.getString()));
		enderecoPojo.setUf(((UFPojo)listBoxUF.getValorSelecionado()).getUf().toUpperCase());
		AutoInfracaoPojo.getInstance().setEndereco(enderecoPojo);
		AutoInfracaoPojo.getInstance().setFlag_endereco(true);
		AutoInfracaoConfirmaEndereco autoInfracaoConfirmaEndereco = new AutoInfracaoConfirmaEndereco();
		autoInfracaoConfirmaEndereco.setPrevScreen(this);
		autoInfracaoConfirmaEndereco.show();
	}
	
}