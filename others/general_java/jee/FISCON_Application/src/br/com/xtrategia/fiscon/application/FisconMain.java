package br.com.xtrategia.fiscon.application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import br.com.xtrategia.fiscon.application.action.ConfigurarAction;
import br.com.xtrategia.fiscon.application.action.SalvarAction;
import br.com.xtrategia.fiscon.application.action.SobreAction;

/**
 * Classe de implementação da interface gráfica
 * 
 * @author Gustavo
 * 
 */
public class FisconMain extends JFrame {

	private static final long serialVersionUID = 1L;
	public static boolean INTERROMPER_EXECUCAO=false;

	// Componentes
	private JToolBar toolbar = new JToolBar("Ferramentas");
	private JMenuBar menubar = new JMenuBar();
	private JTabbedPaneFiscon paineis;

	// Acoes
	private Action salvar;
	private Action configurar;
	private Action sobre;

	public FisconMain() {
		super("Fiscon - 0.1");

		// verificar se existe acesso ao banco
		if (!Configucacao.getInstance().load()) {
			System.exit(0);
		}
			

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container interno = this.getContentPane();

		this.montarAcoes();
		this.montaMenu();
		this.montaToolBar();
		this.montaGUI(interno);
		new Helper();
	}

	private void montarAcoes() {
		salvar = new SalvarAction(this);
		configurar = new ConfigurarAction();
		sobre = new SobreAction();
	}

	private void montaMenu() {
		JMenu arquivo = new JMenu("Arquivo");
		JMenuItem itemSalvar = new JMenuItem(this.salvar);

		JMenu ajuda = new JMenu("Ajuda");
		JMenuItem configurar = new JMenuItem(this.configurar);
		JMenuItem sobre = new JMenuItem(this.sobre);

		// retira os icones dos menus
		itemSalvar.setIcon(null);
		configurar.setIcon(null);
		sobre.setIcon(null);

		arquivo.add(itemSalvar);
		ajuda.add(configurar);
		ajuda.add(sobre);
		this.menubar.add(arquivo);
		this.menubar.add(ajuda);
		this.setJMenuBar(this.menubar);
	}

	private void montaToolBar() {
		this.toolbar.add(this.salvar);
		this.toolbar.add(new JSeparator());
		this.toolbar.add(this.configurar);
		this.toolbar.add(this.sobre);
	}

	private void montaGUI(Container interno) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/imagens/icone.gif")));

		paineis = new JTabbedPaneFiscon();
		LogApplicacao.setSaida(paineis);

		interno.setLayout(new BorderLayout());
		interno.add(this.toolbar, BorderLayout.NORTH);
		interno.add(paineis, BorderLayout.CENTER);

		setSize(850, 600);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
		//setResizable(false); 
		setVisible(true);
	}
	
	public String getTexto(){
		return paineis.getTextoAtivo();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FisconMain();
	}

}
