package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

public class DeviceBluetooth {
	private String bluetoothAddress;
	private String friendlyName;
	protected String id;
	public static final String FRIENDLYNAME = "friendlyName";
	public static final String BLUETOOTHADDRESS = "bluetoothAddress";
	public static final String IDENTIFICADOR = "identificador";

	public String getBluetoothAddress() {
		return bluetoothAddress;
	}

	public DeviceBluetooth(String bluetoothAddress, String friendlyName) {
		super();
		this.bluetoothAddress = bluetoothAddress;
		this.friendlyName = friendlyName;
	}

	public DeviceBluetooth() {
	}

	public void setBluetoothAddress(String bluetoothAddress) {
		this.bluetoothAddress = bluetoothAddress;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getId() {
		return "0";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return getFriendlyName() + ":" + getBluetoothAddress();
	}

	public DeviceBluetooth carregar(Hashtable registro) {
		if (registro!=null && !registro.isEmpty()) {
			setId((String) registro.get(IDENTIFICADOR));
			setFriendlyName((String) registro.get(FRIENDLYNAME));
			setBluetoothAddress((String) registro.get(BLUETOOTHADDRESS));
		}

		return this;
	}

	public Hashtable serializar() {
		Hashtable registro = new Hashtable();
			registro.put(IDENTIFICADOR, String.valueOf(getId()));
		if(getFriendlyName()!=null)
			registro.put(FRIENDLYNAME, getFriendlyName());
		if(getBluetoothAddress()!=null)
			registro.put(BLUETOOTHADDRESS, getBluetoothAddress());
		return registro;
	}
}
