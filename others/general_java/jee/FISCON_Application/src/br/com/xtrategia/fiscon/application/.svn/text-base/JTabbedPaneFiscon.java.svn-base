package br.com.xtrategia.fiscon.application;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
/**
 * Montar as telas de importação e exportação
 * @author Gustavo
 *
 */
public class JTabbedPaneFiscon extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	
	PainelImportacao pImportacao = new PainelImportacao();
	PainelExportacao pExportacao = new PainelExportacao();
	PainelExportacaoRefazer pExportacaoRefazer = new PainelExportacaoRefazer();
	
	
	public JTabbedPaneFiscon() {
		super(JTabbedPane.BOTTOM);
		criarAbaImportacao();
		criarAbaExportacao();
		criarAbaExportacaoRefazer();
		setSelectedComponent(pImportacao);
	}	
	
    public void criarAbaImportacao(){
    	add("Importação",pImportacao);
    	setSelectedComponent(pImportacao);
    }
    public void criarAbaExportacao(){
    	add("Exportação",pExportacao);
    	setSelectedComponent(pExportacao);
    }
    public void criarAbaExportacaoRefazer(){
    	add("Refazer Exportação",pExportacaoRefazer);
    	setSelectedComponent(pExportacaoRefazer);
    }
    
    public String getTextoAtivo(){
    	Painel p = (Painel)getSelectedComponent();
    	return p.getJTextArea().getText();
    }
    
    public void setTextoAtivo(String mensagem){
    	Painel p = (Painel)getSelectedComponent();
    	p.getJTextArea().setText(mensagem);
    	p.getJTextArea().setCaretPosition( p.getJTextArea().getText().length() );
    }
}

class PainelImportacao extends Painel {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component getJPanel() {
		return new JPanelImportacao();
	}
	
}
class PainelExportacao extends Painel {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Component getJPanel() {
		return new JPanelExportacao();
	}
}
class PainelExportacaoRefazer extends Painel {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Component getJPanel() {
		return new JPanelExportacaoRefazer();
	}
}



abstract class Painel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea texto = new JTextArea("");
	
	public Painel() {
		montarGUI();
	}

	public void montarGUI(){
		JScrollPane scroll = new JScrollPane(texto);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		setLayout(new BorderLayout());
		add(getJPanel(), BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
	}
	
	protected abstract Component getJPanel();

	public JTextArea getJTextArea(){
		return texto;
	}
	
}

	
