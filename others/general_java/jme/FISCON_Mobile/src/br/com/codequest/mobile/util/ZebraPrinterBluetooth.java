package br.com.codequest.mobile.util;

import java.io.DataInputStream;
import java.io.PrintStream;

import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import br.com.codequest.mobile.exceptions.BancoDadosException;
import br.com.codequest.mobile.exceptions.ConexaoException;
import br.com.codequest.mobile.exceptions.PareadoException;
import br.com.prime.fiscon.mobile.pojo.Impressora;

public class ZebraPrinterBluetooth {
	UUID RFCOMM_UUID = new UUID(0x0003);
	private StreamConnection m_StrmConn = null;
	private DataInputStream m_Input = null;
	private PrintStream m_Output = null;

	public boolean m_bIsServer = false, m_bServerFound = false,
			m_bInitServer = false, m_bInitClient = false;

	private final String CLIENTE_DESCONECTADO = "0";
	private boolean conectado = false;
	private static String url;
	private String envio;

	public ZebraPrinterBluetooth() {

	}

	private String urlFormatter(String unformatUrl) {
		if (unformatUrl == null || unformatUrl == "") {
			return null;
		} else if (unformatUrl.startsWith("btspp://")) {
			return unformatUrl;
		}

		return "btspp://" + unformatUrl + ":1";
	}

	public void connect(String urlExterna) throws PareadoException,
			ConexaoException {
		// URL direta para a impressora. Retirar para permitir identificar
		// automaticamente.
		//urlExterna = "btspp://00037a678a85:1";
		try {
			if(urlExterna==null || urlExterna.equals("")){
				urlExterna = Impressora.getInstance().getBluetoothAddress();
			}else{
				setUrl(urlExterna);
			}
		} catch (BancoDadosException e1) {
			e1.printStackTrace();
		}
		if ((url == null && urlExterna == null)) {
			throw new PareadoException();
		} else {
			setUrl(urlExterna);
		}
		try {
			m_StrmConn = (StreamConnection) Connector.open(urlFormatter(url));
			m_Output = new PrintStream(m_StrmConn.openDataOutputStream());
			m_Input = m_StrmConn.openDataInputStream();
			conectado = true;
			SendMessages(getEnvio());
			CloseAll();
		} catch (Exception e) {
			throw new ConexaoException();
		}

	}

	public void connect() throws PareadoException, ConexaoException {
		connect(null);
	}


	public String SendMessages(String v_strData) throws Exception {

		if (!conectado) {
			return CLIENTE_DESCONECTADO;
		} else {

			m_Output.println(v_strData);
			
			m_Output.flush();

		}

		return null;
	}

	public void CloseAll() {
		try {
			conectado = false;

			if (m_Output != null)
				m_Output.close();

			if (m_Input != null)
				m_Input.close();

			if (m_StrmConn != null) {

				m_StrmConn.close();

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public String getEnvio() {
		return envio;
	}

	public void setEnvio(String envio) {
		this.envio = envio;
	}

	public boolean isConectado() {
		return conectado;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ZebraPrinterBluetooth.url = url;
	}

}
