package br.com.prime.fiscon.mobile.pojo;

public class EspecieVeiculoPojo {
	private String id;
	private String especie;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public EspecieVeiculoPojo(String id, String especie) {
		super();
		this.id = id;
		this.especie = especie;
	}

	public String toString() {
		return getEspecie();
	}

}
