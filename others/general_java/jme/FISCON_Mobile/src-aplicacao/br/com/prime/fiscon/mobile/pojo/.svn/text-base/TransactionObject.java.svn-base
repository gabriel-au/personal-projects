package br.com.prime.fiscon.mobile.pojo;

import java.util.Date;
import java.util.Hashtable;

public abstract class TransactionObject {
	private static String id;
	public static final String ID = "identificador";

	public abstract Hashtable getHashtable();

	public abstract void carregar(Hashtable infracaoTipo);

	public abstract String getBD();
	
	
	
	public static void setId(String id) {
		TransactionObject.id = id;
	}

	public static String getId() {
		if ( id == null || id.equals("")) {
			setId(String.valueOf(new Date().getTime()));
			return id;
		} else {
			return id;
		}
	}
}
