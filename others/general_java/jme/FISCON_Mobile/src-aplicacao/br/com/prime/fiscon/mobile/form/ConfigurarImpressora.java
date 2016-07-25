package br.com.prime.fiscon.mobile.form;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.util.BancoDados;
import br.com.prime.fiscon.mobile.pojo.DeviceBluetooth;

public class ConfigurarImpressora extends MobileDialog {
	private Label nome;
	private Label endereco;
	private Label estado;
	private ListBox listaImpressoras;
	private boolean automaticReturn=false;
	private MobileDialog prevDialog;

	public ConfigurarImpressora(MobileDialog dialog, boolean automaticReturn) {
		super("Lista de dispositivo");
		setPrevScreen(dialog);
		this.automaticReturn = automaticReturn;
		this.prevDialog = dialog;
	}

	public void init() {
		estado = new Label("");

	}

	protected void definirMenu() {
		setMenuText("Voltar", "Pesquisar");
		setPrevScreen(new MenuConfiguracoes());
	}

	public void executarRight() {
		if (getRightMenuText().equals("Salvar")) {

			try {
				DeviceBluetooth impressora = ((DeviceBluetooth) listaImpressoras
						.getValorSelecionado());
				BancoDados bd = new BancoDados(BancoDados.IMPRESSORA);
				bd.salvarOuAtulizar(impressora.serializar());

			} catch (BancoDadosException e) {
				showMensagemErro(getTitle(), "Falha ao salvar impressora", this);
				e.printStackTrace();
			}
			if(automaticReturn){
				prevDialog.show();
			}else{
				recuperarImpressora();
			}
		} else if (getRightMenuText().equals("Tentar Novamente")) {
			new AguardeConfigurarImpressora(this, this.automaticReturn).show();
		}
	}

	public void carregarTela(Hashtable objeto) {
		
		Vector impressoras = new Vector();
		Vector records = (Vector) objeto.remove("devicesFound");
		if (records != null) {
			for (int i = 0; i < records.size(); i++) {
				try {
					impressoras.addElement(new DeviceBluetooth(
							((RemoteDevice) records.elementAt(i))
									.getBluetoothAddress(),
							((RemoteDevice) records.elementAt(i))
									.getFriendlyName(true)));

				} catch (IOException e) {
					e.printStackTrace();
					showMensagemErro(getTitle(), e.getMessage(), this);

				}
			}
			listaImpressoras = new ListBox(impressoras, this);
			nome = new Label();
			endereco = new Label();
			append(listaImpressoras);
			append(nome);
			append(endereco);
			setMenuText("Voltar", "Salvar");
			
		} else {
			estado.setLabel((String) objeto.get("mensagem"));
			setMenuText("Voltar", "Tentar Novamente");
		}

		recuperarImpressora();
		show();

	}

	public void recuperarImpressora() {
		try {
			DeviceBluetooth impressora = new DeviceBluetooth();
			BancoDados bd = new BancoDados(BancoDados.IMPRESSORA);
			Vector lista;
			lista = bd.listar();
			for (int i = 0; i < lista.size(); i++) {
				impressora.carregar((Hashtable)lista.elementAt(i));
				
				nome.setLabel("Apelido: "+impressora.getFriendlyName());
				endereco.setLabel("Endereço: "+impressora.getBluetoothAddress());
				repaint();
			}

		} catch (BancoDadosException e) {
			// TODO Auto-generated catch block
			showMensagemErro(getTitle(), "Falha ao carregar pré-configuração. "
					+ e.getMessage(), this);
		} catch (Exception e) {
			showMensagemErro(getTitle(),
					"Falha desconhecida " + e.getMessage(), this);
		}
	}
}
