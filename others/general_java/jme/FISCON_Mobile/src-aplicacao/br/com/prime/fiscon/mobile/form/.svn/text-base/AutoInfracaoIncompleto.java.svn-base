package br.com.prime.fiscon.mobile.form;

import java.util.Hashtable;
import java.util.Vector;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.util.BancoDados;
import br.com.prime.fiscon.mobile.negocio.RecuperarAutoInfracao;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoIncompleto extends MobileDialog {
	private ListBox autoInfracaoIncompletoBox;
	//private static final Logger log = LoggerFactory.getLogger(AutoInfracaoFoto1.class);
	public AutoInfracaoIncompleto() {
		super("Recuperar Auto de Infração");
	}
	/*private void configureMicroLog()
	{
		BluetoothSerialAppender bluetoothSerialAppender = new BluetoothSerialAppender("btspp://001F81000250:3;authenticate=false;encrypt=false;master=false");
		PatternFormatter parFormatter = new PatternFormatter();
		parFormatter.setPattern("%c [%P] %m %T");
		bluetoothSerialAppender.setFormatter(parFormatter);
		log.addAppender(bluetoothSerialAppender);
	}*/
	public void show() {
		super.show();
		//configureMicroLog();
	}
	public void init() {
		
		try {
			BancoDados bd = new BancoDados(BancoDados.AUTOINFRACAO);
			Vector lista;
			Vector listaValores = new Vector();
			listaValores.addElement("Selecione");
			lista = bd.listar();
			for (int i = 0; i < lista.size(); i++) {
				listaValores.addElement((String)((Hashtable)lista.elementAt(i)).get(BancoDados.IDENTIFICADOR));
			}
			
			autoInfracaoIncompletoBox = new ListBox(listaValores, this);
			append(autoInfracaoIncompletoBox);
		} catch (BancoDadosException e) {
			showMensagemErro(getTitle(), "Falha ao carregar a lista de auto de infração", this);
		}
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Recuperar");
	}
	
	public void executarRight() {
		RecuperarAutoInfracao recuperarAutoInfracao = new RecuperarAutoInfracao();
		AutoInfracaoPojo.setId(autoInfracaoIncompletoBox.getItemSelecionado());
		try {
			recuperarAutoInfracao.recuperarAutoInfracao(AutoInfracaoPojo.getInstance());
		} catch (BancoDadosException e) {
			//log.trace(getClass().getName(), e);
			showMensagemErro(getTitle(), "Falha na recuperação do Auto de Infração", this);
			e.printStackTrace();
		} catch (Exception e) {
		/*	try {
				log.debug(AutoInfracaoPojo.getInstance().serializarHashtable().toString());
			} catch (Exception e1) {
				log.trace(getClass().getName(), e1);
			}
			log.trace(getClass().getName(), e);*/
			showMensagemErro(getTitle(), "Falha na recuperação do Auto de Infração "+e.getMessage()+" id "+AutoInfracaoPojo.getId(), this);
			e.printStackTrace();
		}
	}
}
