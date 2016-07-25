package br.com.prime.fiscon.mobile.form;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;

import org.j4me.ui.components.Label;

import br.com.codequest.mobile.MobileDialog;
import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.ui.components.LabelMenu;
import br.com.codequest.mobile.ui.components.ListBox;
import br.com.codequest.mobile.util.DiscoverDeviceBluetooth;
import br.com.prime.fiscon.mobile.pojo.DeviceBluetooth;
import br.com.prime.fiscon.mobile.pojo.Impressora;

public class ListaDeviceBluetooth extends MobileDialog {
	private Label estado;
	private DiscoverDeviceBluetooth discoverDeviceBluetooth;
	private ListBox listaDispositivos;

	public ListaDeviceBluetooth() {
		super("Lista de dispositivo");
	}

	public void init() {
		estado = new Label();
		append(estado);
	}

	protected void definirMenu() {
		setMenuText("Voltar", "Pesquisar");
	}

	public void executarRight() {
		if(getRightMenuText().equals("Salvar")){
			try {
				Impressora.getInstance().setBluetoothAddress(((DeviceBluetooth)listaDispositivos.getValorSelecionado()).getBluetoothAddress());
				Impressora.getInstance().setFriendlyName(((DeviceBluetooth)listaDispositivos.getValorSelecionado()).getFriendlyName());
			} catch (BancoDadosException e) {
				showMensagemErro(getTitle(), "Falha ao salvar impressora", this);
				e.printStackTrace();
			}
			
		}else{
			discoverDeviceBluetooth = new DiscoverDeviceBluetooth();
			discoverDeviceBluetooth.setTelaDevice(this);
			discoverDeviceBluetooth.start();
		}
	}

	public void executarLeft() {
		if (getLeftMenuText().equals("Interromper")) {
			setMenuText("Voltar", "Pesquisar");
			alteraEstado("Interrompendo a procura...");
			repaint();
			discoverDeviceBluetooth.cancelarProcura();
			discoverDeviceBluetooth = null;
			alteraEstado("");

		} else {
			super.executarLeft();
		}
	}

	public void alteraEstado(String msg) {
		estado.setLabel(msg);
		repaint();
	}

	public void iniciar() {
		estado.setLabel("Localizando dispositivos...");
		setMenuText("Interromper", "");
		repaint();
	}

	public void carregarTela(Hashtable objeto) {
		if (objeto.get("devicesFound") == null) {
			alteraEstado((String) objeto.get("mensagem"));
		} else {
			Vector records = (Vector) objeto.remove("devicesFound");
			for (int i = 0; i < records.size(); i++) {
				try {
					append(new LabelMenu(((RemoteDevice) records.elementAt(i))
							.getFriendlyName(true)
							+ ": "
							+ ((RemoteDevice) records.elementAt(i))
									.getBluetoothAddress(),
							new AutoInfracaoImprimir(this,
									((RemoteDevice) records.elementAt(i))
											.getBluetoothAddress()), this));
				} catch (IOException e) {
					e.printStackTrace();
					showMensagemErro(getTitle(), e.getMessage(), this);

				}
			}
			setMenuText("Voltar", "Pesquisar");
			repaint();
		}
	}

	public void carregarTela(Vector records) {
		
		listaDispositivos = new ListBox(records, this);
		setMenuText("Voltar", "Salvar");
		/*for (int i = 0; i < records.size(); i++) {
			try {
				append(new LabelMenu(((RemoteDevice) records.elementAt(i))
						.getFriendlyName(true)
						+ ": "
						+ ((RemoteDevice) records.elementAt(i))
								.getBluetoothAddress(),
						new AutoInfracaoImprimir(this,
								((RemoteDevice) records.elementAt(i))
										.getBluetoothAddress()), this));
			} catch (IOException e) {
				e.printStackTrace();
				showMensagemErro(getTitle(), e.getMessage(), this);

			}
		}
		setMenuText("Voltar", "Pesquisar");
		repaint();
*/
	}
}
