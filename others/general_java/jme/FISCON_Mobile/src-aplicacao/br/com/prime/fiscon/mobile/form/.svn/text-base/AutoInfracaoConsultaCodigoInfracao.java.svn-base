package br.com.prime.fiscon.mobile.form;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.util.Propriedades;
import br.com.prime.fiscon.mobile.lista.ListaInfracaoTipo;
import br.com.prime.fiscon.mobile.negocio.CRUDNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;
import br.com.prime.fiscon.mobile.pojo.InfracaoTipoPojo;
import br.com.prime.fiscon.mobile.pojo.Passo;

/**
 * Classe para exibir a escolha das multas
 * 
 * @author Gustavo
 * 
 */
public class AutoInfracaoConsultaCodigoInfracao extends MobileDialog {

	private ListBox listBox;
	private Label textoMulta;
	private boolean autoInfracaoEmSerie;

	public AutoInfracaoConsultaCodigoInfracao(MobileDialog dialog) {
		super("Consulta de Tipo de Infração");
		setAutoInfracaoEmSerie(false);
		
	}
	public AutoInfracaoConsultaCodigoInfracao(MobileDialog dialog, boolean autoInfracaoEmSerie) {
		super("Consulta de Tipo de Infração");
		setPrevScreen(dialog);
		setAutoInfracaoEmSerie(autoInfracaoEmSerie);
		
	}

	public void init() {
		append(new Label("Tipo de Infração"));
		ListaInfracaoTipo.load();
		listBox = new ListBox(ListaInfracaoTipo.listaInfracaoTipo, this);
		append(listBox, true);

		InfracaoTipoPojo pojo = (InfracaoTipoPojo) listBox
				.getValorSelecionado();
		textoMulta = new Label(pojo.getNome());
		append(textoMulta);
		
	}

	protected synchronized void paint(Graphics g) {
		InfracaoTipoPojo pojo = (InfracaoTipoPojo) listBox
				.getValorSelecionado();
		textoMulta.setLabel(pojo.getNome());
		super.paint(g);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Confirmar");
	}

	public void executarRight() {
		AutoInfracaoPojo.getInstance().setInfracaoTipoPojo((InfracaoTipoPojo)listBox.getValorSelecionado());
		
		if (Propriedades.getProperty("ARMAZENAGEM_LOCAL").equals("true")) {
			try {
				CRUDNegocio crudNegocio = new CRUDNegocio();
				crudNegocio.gravar(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo());
				crudNegocio.gravar(new Passo(AutoInfracaoPojo.getId(), Passo.AGUARDELERGPS));
			}catch(BancoDadosException be){
				showMensagemErro(getTitle(), "Falha ao salvar dados localmente", this);
			}catch (Exception e) {
				showMensagemErro(getTitle(), "Falha ao salvar dados locamente", this);
			}
		}
		
			new AguardeLerGPS(this).show();	
		
	}
	public void executarLeft() {
		if(isAutoInfracaoEmSerie()){
			getPrevScreen().show();
		}else{
			new AutoInfracaoResultadoConsultaCNH(AutoInfracaoPojo.getInstance().getCnh()).show();
		}
	}
	private boolean isAutoInfracaoEmSerie() {
		return autoInfracaoEmSerie;
	}
	private void setAutoInfracaoEmSerie(boolean autoInfracaoEmSerie) {
		this.autoInfracaoEmSerie = autoInfracaoEmSerie;
	}
	
	
}