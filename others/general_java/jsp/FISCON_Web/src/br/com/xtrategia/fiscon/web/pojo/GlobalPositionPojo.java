package br.com.xtrategia.fiscon.web.pojo;

import br.com.xtrategia.fiscon.infra.TransferObject;

public class GlobalPositionPojo extends TransferObject implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double latitude;
	private double longitude;
	
	
	public GlobalPositionPojo(){
		super();
	}
	public GlobalPositionPojo(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		
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
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
}
