package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;
import br.com.codequest.mobile.util.Base64Coder;
import br.com.codequest.mobile.util.MD5;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

public class FotoPojo extends TransactionObject {
	public static final String AUTOINFRACAOPOJO = "AUTOINFRACAOPOJO";
	public static final String DADO = "DADO";
	public static final String PLACA = "PLACA";
	public static final String VEICULO = "VEICULO";
	public static final String TIPOFOTO = "TIPOFOTO";

	private String tipoFoto;
	private AutoInfracaoPojo autoInfracaoPojo;
	private byte[] dado;

	public FotoPojo() {
	}

	public FotoPojo(String id) {
		setId(id);
	}

	public FotoPojo(String id, AutoInfracaoPojo autoInfracaoPojo, byte[] dado) {
		setId(id);
		this.autoInfracaoPojo = autoInfracaoPojo;
		this.dado = dado;
	}
	public FotoPojo(String id, String tipoFoto){
		setId(id);
		setTipoFoto(tipoFoto);
	}
	
	public AutoInfracaoPojo getAutoInfracaoPojo() {
		return this.autoInfracaoPojo;
	}

	public void setAutoInfracaoPojo(AutoInfracaoPojo autoInfracaoPojo) {
		this.autoInfracaoPojo = autoInfracaoPojo;
	}

	public byte[] getDado() {
		return this.dado;
	}

	public void setDado(byte[] dado) {
		this.dado = dado;
	}

	public String getTipoFoto() {
		return tipoFoto;
	}

	public void setTipoFoto(String tipoFoto) {
		this.tipoFoto = tipoFoto;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;

	// end of extra code specified in the hbm.xml files
	public Hashtable getHashtable() {

		Hashtable foto = new Hashtable();
		foto.put(FotoPojo.ID, getId());
		if (getDado() != null)
			foto.put(FotoPojo.DADO, MD5.toBase64(getDado()));
		if(getTipoFoto()!=null)
			foto.put(FotoPojo.TIPOFOTO, getTipoFoto());
		return foto;
	}

	public void carregar(Hashtable foto) {
		if (foto != null) {
			setId((String) foto.get(FotoPojo.ID));
			setDado(Base64Coder.decode((String) foto.get(FotoPojo.DADO)));
			setTipoFoto((String) foto.get(FotoPojo.TIPOFOTO));
		}
	}

	public String getBD() {

		return BancoDados.FOTO;
	}
}
