package br.com.codequest.mobile.util;

/**
 * Classe responsável por localizar os dispositivos bluetooth
 * @author Gustavo L Costa
 *
 */
import java.util.Hashtable;
import java.util.Vector;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.codequest.mobile.MobileDialog;

public class DiscoverDeviceBluetooth extends Thread implements CommandListener,
		DiscoveryListener {
	private LocalDevice local = null;
	private DiscoveryAgent agent = null;
	private Vector devicesFound = null;
	private Hashtable retorno = new Hashtable();
	private MobileDialog telaDevice;

	public MobileDialog getTelaDevice() {
		return telaDevice;
	}

	public void setTelaDevice(MobileDialog telaDevice) {
		this.telaDevice = telaDevice;
	}

	public void commandAction(Command c, Displayable d) {
	}

	public void deviceDiscovered(RemoteDevice remoteDevice,
			DeviceClass deviceClass) {
		devicesFound.addElement(remoteDevice);
	}

	public void inquiryCompleted(int param) {
		/*
		 * Alertar o usuário com base no estado do inquiry
		 */
		switch (param) {
		case DiscoveryListener.INQUIRY_COMPLETED:
			/*
			 * Acontece quando completa com sucesso.
			 */
			System.out.println("INQUIRY_COMPLETED");
			if (devicesFound.size() > 0) {
				retorno.put("mensagem", "");
				retorno.put("devicesFound", devicesFound);

			} else {
				retorno.put("mensagem", "Nenhum dispositivo encontrado");

			}
			getTelaDevice().carregarTela(retorno);
			break;
		case DiscoveryListener.INQUIRY_ERROR:
			/*
			 * Acontece quando ocorre um erro na procura.
			 */
			System.out.println("INQUIRY_ERROR");
			retorno.put("mensagem", "Falha na procura por dispositivo.");
			getTelaDevice().carregarTela(retorno);
			break;
		case DiscoveryListener.INQUIRY_TERMINATED:
			/*
			 * Acontece quando o usuário cancela a procura.
			 */
			retorno.put("mensagem", "Pesquisa cancelada.");
			getTelaDevice().carregarTela(retorno);
			break;
		}
	}

	public void servicesDiscovered(int transID, ServiceRecord[] serviceRecord) {
		/*
		 * Dispositivos encontrados. Mante referencia para o ServiceRecord
		 */
		// servicesFound = serviceRecord;
	}

	public void serviceSearchCompleted(int transID, int respCode) {
		switch (respCode) {
		case DiscoveryListener.SERVICE_SEARCH_COMPLETED:
			/*
			 * Procura completada com sucesso
			 */
			System.out.println("SERVICE_SEARCH_COMPLETED");

			break;
		case DiscoveryListener.SERVICE_SEARCH_DEVICE_NOT_REACHABLE:
			// Nenhum dispositivo possível de ser acessado.
			System.out.println("SERVICE_SEARCH_DEVICE_NOT_REACHABLE");
			break;
		case DiscoveryListener.SERVICE_SEARCH_ERROR:
			// Erro na procura por dispositivo
			System.out.println("SERVICE_SEARCH_ERROR");
			break;
		case DiscoveryListener.SERVICE_SEARCH_NO_RECORDS:
			// Nenhum dispositivo encontrado
			System.out.println("SERVICE_SEARCH_NO_RECORDS");
			retorno.put("mensagem", "Nenhum dispositivo encontrado.");
			getTelaDevice().carregarTela(retorno);
			break;
		case DiscoveryListener.SERVICE_SEARCH_TERMINATED:

			/*
			 * Procura interrompida pelo usuário
			 */
			System.out.println("SERVICE_SEARCH_NO_RECORDS");
			break;
		}
	}

	public void doDeviceDiscovery() {
		try {
			local = LocalDevice.getLocalDevice();
		} catch (BluetoothStateException bse) {
			// Celular nao possui bluetooth
		}
		agent = local.getDiscoveryAgent();
		devicesFound = new Vector();
		try {
			if (!agent.startInquiry(DiscoveryAgent.GIAC, this)) {
				System.out.println("Not startInquiry");
			}
		} catch (BluetoothStateException bse) {
			// Erro ao iniciar o Inquiry
		}
	}

	/*
	 * private void doServiceSearch(RemoteDevice device) {
	 * 
	 * int[] attributes = { 0x100, 0x101, 0x102 };
	 * 
	 * UUID[] uuids = new UUID[1]; uuids[0] = new UUID(0x1002); try {
	 * agent.searchServices(attributes, uuids, device, this); } catch
	 * (BluetoothStateException e) { // TODO } }
	 */
	public void run() {
		doDeviceDiscovery();
	}

	public void cancelarProcura() {
		agent.cancelInquiry(this);
	}
}