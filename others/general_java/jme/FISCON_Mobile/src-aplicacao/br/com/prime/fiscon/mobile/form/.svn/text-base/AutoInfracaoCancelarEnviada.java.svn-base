package br.com.prime.fiscon.mobile.form;

import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.util.BancoDados;
import br.com.codequest.mobile.util.DateConverter;
import br.com.prime.fiscon.mobile.negocio.AutoInfracaoNegocio;
import br.com.prime.fiscon.mobile.pojo.AutoInfracaoPojo;

public class AutoInfracaoCancelarEnviada extends MobileDialog {
	private ListBox autoInfracaoIncompletoBox;
	private Label veiculoPojo = new Label();
	private Label proprietario = new Label();
	private Label infracaoTipoPojo = new Label();
	private Label dataInfracao = new Label();
	private Label enderecoLogradouro = new Label();
	private Label enderecoComplemento = new Label();
	private Label enderecoBairro = new Label();
	private Label observacao = new Label();
	private Label numeroAutoInfracao = new Label();
	//private static final Logger log = LoggerFactory.getLogger(AutoInfracaoFoto1.class);
	public AutoInfracaoCancelarEnviada() {
		super("Cancelar Auto de Infração");
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
		
		numeroAutoInfracao = new Label();
		append(numeroAutoInfracao);
		
		veiculoPojo = new Label();
		append(veiculoPojo);
		
		proprietario = new Label();
		append(proprietario);

		infracaoTipoPojo = new Label();
		append(infracaoTipoPojo);

		dataInfracao = new Label();
		append(dataInfracao);


		enderecoLogradouro = new Label();
		append(enderecoLogradouro);

		enderecoComplemento = new Label();
		append(enderecoComplemento);

		enderecoBairro = new Label();
		append(enderecoBairro);

		observacao = new Label();
		append(observacao);

		super.show();
		//configureMicroLog();
	}
	
	protected synchronized void paint(Graphics g) {
		carregarTela();
		super.paint(g);
	}
	
	public void carregarTela(){
		if(!AutoInfracaoPojo.getId().equals(autoInfracaoIncompletoBox.getItemSelecionado())&& !autoInfracaoIncompletoBox.getItemSelecionado().equals("Selecione")){
			AutoInfracaoPojo.limparCampos();
			AutoInfracaoNegocio autoInfracaoNegocio = new AutoInfracaoNegocio();
			AutoInfracaoPojo.setId(autoInfracaoIncompletoBox.getItemSelecionado());
			
			try {
				autoInfracaoNegocio.carregar();
			} catch (Exception e) {
				showMensagemErro(getTitle(), "Falha ao carregar tela", this);
			}
			
				try {
					if(AutoInfracaoPojo.getInstance().getNumeroAutoInfracao()!=null){
						numeroAutoInfracao.setLabel("Número Auto Infração: "+AutoInfracaoPojo.getInstance().getNumeroAutoInfracao());
					}
					
					if( AutoInfracaoPojo.getInstance().getVeiculoPojo()!=null)
						if( AutoInfracaoPojo.getInstance().getVeiculoPojo().getVeiculoPlaca()!=null)
							veiculoPojo.setLabel("Placa: "
									+ AutoInfracaoPojo.getInstance().getVeiculoPojo()
										.getVeiculoPlaca());
					if(AutoInfracaoPojo.getInstance().getCnh()!=null)
						if(AutoInfracaoPojo.getInstance().getCnh().getNome()!=null)
							proprietario.setLabel("Condutor: "+AutoInfracaoPojo.getInstance().getCnh().getNome());
					if(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo()!=null)
						if(AutoInfracaoPojo.getInstance().getInfracaoTipoPojo().getNome()!=null)
							infracaoTipoPojo.setLabel("Infração: "
								+ AutoInfracaoPojo.getInstance().getInfracaoTipoPojo()
								.getNome());
					if(AutoInfracaoPojo.getInstance().getDataInfracao()!=null)
						dataInfracao.setLabel("Data Infração: "
							+ DateConverter.dateToString(AutoInfracaoPojo.getInstance().getDataInfracao(),"dd/MM/yyyy hh:mm")
									.toString());
					if(AutoInfracaoPojo.getInstance().getEndereco()!=null){
						if(AutoInfracaoPojo.getInstance().getEndereco().getEndereco()!=null)
							enderecoLogradouro.setLabel("Logradouro: "
									+ AutoInfracaoPojo.getInstance().getEndereco().getEndereco());
						if(AutoInfracaoPojo.getInstance().getEndereco().getComplemento()!=null)
						enderecoComplemento.setLabel("Complemento: "
								+ AutoInfracaoPojo.getInstance().getEndereco().getComplemento());
						if(AutoInfracaoPojo.getInstance().getEndereco().getBairro()!=null)
							enderecoBairro.setLabel("Bairro: "
								+ AutoInfracaoPojo.getInstance().getEndereco().getBairro());
					}
					if(AutoInfracaoPojo.getInstance().getObservacao()!=null)
						observacao.setLabel("Observacao: "
							+ AutoInfracaoPojo.getInstance().getObservacao());
				} catch (Exception e) {
					showMensagemErro(getTitle(), "Falha na criação dos campos", this);
					e.printStackTrace();
					
				}

		}		
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
			e.printStackTrace();
		}
		
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Cancelar");
	}
	
	public void executarRight() {
		//Solicitar cancelamento
		if(AutoInfracaoPojo.getInstance().getNumeroAutoInfracao()!=null || !AutoInfracaoPojo.getInstance().getNumeroAutoInfracao().equals("0")){
			new AguardeCancelarAutoInfracaoComFoto(this).show();
		}else{
			AutoInfracaoNegocio autoInfracaoNegocio = new AutoInfracaoNegocio();
			try {
				autoInfracaoNegocio.apagar();
			} catch (Exception e) {
				e.printStackTrace();
				showMensagemErro(getTitle(), "Falha ao carregar a lista de auto de infração", this);
			}
		}
	}
	public void executarLeft() {
		new MenuAutoInfracao().show();
	}
}
