package br.com.prime.fiscon.mobile.form;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.j4me.ui.components.Component;
import org.j4me.ui.components.HorizontalRule;
import org.j4me.ui.components.Whitespace;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.ui.components.LabelMenu;
import br.com.codequest.mobile.ui.components.TextAlfabeto;
import br.com.codequest.mobile.ui.components.TextTeste;
import br.com.codequest.mobile.util.StringUtils;
import br.com.prime.fiscon.mobile.lista.ListaInfracaoTipo;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.InfracaoTipoPojo;

/**
 * Classe para exibir a escolha das multas
 * 
 * @author Gustavo
 * 
 */
public class AutoInfracaoConsultaInfracaoTipo extends MobileDialog {
	private HorizontalRule horizontalRule;
	private Whitespace whitespace;
	private static Vector labels;
	private TextAlfabeto textoMulta;

	private static final String TEXTO_MULTA = "Tipo de infração";

	public AutoInfracaoConsultaInfracaoTipo(MobileDialog dialog) {
		super("Consulta de Tipo de Infração");
		this.setPrevScreen(dialog);
	}

	public void init() {
		textoMulta = new TextAlfabeto(TEXTO_MULTA);
		append(textoMulta);
		horizontalRule = new HorizontalRule();
		whitespace = new Whitespace(4);
		append(whitespace);
		append(horizontalRule);
		append(whitespace);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Consultar");
	}

	public void executarRight() {
		AutoInfracaoPojo.limparCampos();
		AutoInfracaoPojo.getInstance().setFlag_serie(true);
		
		Hashtable att = getMapeamentoAtributos();
		System.out.println((String) att.get(StringUtils.calculaStringCanonica(TEXTO_MULTA)));
		this.search((String) att.get(StringUtils.calculaStringCanonica(TEXTO_MULTA)));
	}

	public void search(String content) {
		if (content != null)
			content = content.toUpperCase();
		// deleta os componentes da ultima pesquisa
		if (labels != null) {
			for (Enumeration e = labels.elements(); e.hasMoreElements();) {
				Component c = (Component) e.nextElement();
				delete(c);
			}
		}
		// pega a lista de resultados
		labels = new Vector();
		ListaInfracaoTipo listaInfracaoTipo = new ListaInfracaoTipo();
		Vector resultado;
		if (content != null && !content.equals(""))
			resultado = listaInfracaoTipo.procurarPorCodigoEnome(textoMulta
					.getString());
		else
			resultado = ListaInfracaoTipo.getListaInfracaoTipo();

		for (int i = 0; i < resultado.size(); i++) {
			InfracaoTipoPojo itPojo = (InfracaoTipoPojo) resultado.elementAt(i);
			MobileDialog aguardeLerGPS = new AguardeLerGPS(this, itPojo);
			aguardeLerGPS.setPrevScreen(this);
			// cria um labelmenu para cada resultado
			LabelMenu c = new LabelMenu(itPojo.getCodigo() + " - "
					+ itPojo.getNome(), aguardeLerGPS, this);
			// adiciona na lista estatica os labels para serem deletados depois
			labels.addElement(c);
			append(c);
		}
		repaint();
	}
}