package br.com.codequest.mobile.util;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;

import br.com.prime.fiscon.mobile.pojo.GlobalPosition;

public class GPS {
	private LocationProvider lp = null;
	private Location location = null;
	private Coordinates coordinates = null;

	public GPS() {
		

	}
	public boolean localizar(){
		Criteria criteria = new Criteria();
		criteria.setHorizontalAccuracy(Integer.parseInt(Propriedades.getProperty("GPS_PRECISAO_HORIZONTAL")));
		criteria.setVerticalAccuracy(Integer.parseInt(Propriedades.getProperty("GPS_PRECISAO_VERTICAL")));
		try {
			lp = LocationProvider.getInstance(criteria);
		} catch (LocationException e1) {
			e1.printStackTrace();
			return false;
		}

		try {
			location = lp.getLocation(Integer.parseInt(Propriedades.getProperty("GPS_TIMEOUT_PESQUISA")));
			coordinates = location.getQualifiedCoordinates();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				location = lp.getLocation(Integer.parseInt(Propriedades.getProperty("GPS_TIMEOUT_PESQUISA_RETRY")));
				coordinates = location.getQualifiedCoordinates();
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			} 
		}
		
	}
	public double getLatitude() {
		if(coordinates!=null){
			return coordinates.getLatitude();
		}
		return 0;
	}
	public double getLongitude() {
		if(coordinates!=null){
			return coordinates.getLongitude();
		}
		return 0;
	}
	
	public float getAltura(){
		if(coordinates!=null){
			return coordinates.getAltitude();
		}
		return 0;
	}
	public String getLatitudeFormatado(){
		if(coordinates!=null){
			return Coordinates.convert(getLatitude(),Coordinates.DD_MM_SS);
		}
		return null;
	}
	public String getLongitudeFormatado(){
		if(coordinates!=null){
			return Coordinates.convert(getLongitude(),Coordinates.DD_MM_SS);
		}
		return null;
	}
	public GlobalPosition getGlobalPosition(){
		return new GlobalPosition(getLatitude(), getLongitude(), getAltura());
	}
}
