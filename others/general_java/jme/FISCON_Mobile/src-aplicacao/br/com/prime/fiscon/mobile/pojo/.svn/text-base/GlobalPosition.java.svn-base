package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;

public class GlobalPosition extends TransactionObject{
	public static final String LATITUDE = "LATITUDE";
	public static final String LONGITUDE = "LONGITUDE";
	public static final String ALTITUDE = "ALTITUDE";
	
	
	private double latitude=0;
	private double longitude=0;
	private float altitude=0;
	
	public GlobalPosition(String id){
		setId(id);
	}
	
	public GlobalPosition(double latitude, double longitude, float altitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public void carregar(Hashtable infracaoTipo) {
		setId((String)infracaoTipo.get(GlobalPosition.ID));
		setAltitude(Float.parseFloat((String)infracaoTipo.get(GlobalPosition.ALTITUDE)));
		setLatitude(Double.parseDouble((String)infracaoTipo.get(GlobalPosition.LATITUDE)));
		setLongitude(Double.parseDouble((String)infracaoTipo.get(GlobalPosition.LONGITUDE)));
	}
	public String getBD() {
		return BancoDados.GLOBALPOSITION;
	}
	public Hashtable getHashtable() {
		Hashtable registro = new Hashtable();
		registro.put(GlobalPosition.ID, getId());
		registro.put(GlobalPosition.ALTITUDE, Float.toString(getAltitude()));
		registro.put(GlobalPosition.LATITUDE, Double.toString(getLatitude()));
		registro.put(GlobalPosition.LONGITUDE, Double.toString(getLongitude()));
		return registro;
	}
	
}
