package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;



public class EnderecoPojo extends TransactionObject{
	public static final String ENDERECO = "ENDERECO";
	public static final String BAIRRO = "BAIRRO";
	public static final String CIDADE = "CIDADE";
	public static final String UF = "UF";
	public static final String COMPLEMENTO = "COMPLEMENTO";
	
	
	private String endereco;
	private String bairro;
	private String cidade;
	private String uf;
	private String Complemento;

	public EnderecoPojo(String id){
		setId(id);
	}
	
	public EnderecoPojo() {
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public String getComplemento() {
		return Complemento;
	}
	public Hashtable getHashtable(){
		Hashtable endereco = new Hashtable();
		endereco.put(EnderecoPojo.ID, getId());
		if(getBairro()!=null)
			endereco.put(EnderecoPojo.BAIRRO, getBairro());
		if(getCidade()!=null)
			endereco.put(EnderecoPojo.CIDADE, getCidade());
		if(getEndereco()!=null)
			endereco.put(EnderecoPojo.ENDERECO, getEndereco());
		if(getUf()!=null)
			endereco.put(EnderecoPojo.UF, getUf());
		if(getComplemento()!=null)
			endereco.put(EnderecoPojo.COMPLEMENTO, getComplemento());
		
		return endereco;
	}
	
	public void carregar(Hashtable endereco){
		setId((String)endereco.get(EnderecoPojo.ID));
		setBairro((String)endereco.get(EnderecoPojo.BAIRRO));
		setCidade((String)endereco.get(EnderecoPojo.CIDADE));
		setEndereco((String)endereco.get(EnderecoPojo.ENDERECO));
		setUf((String)endereco.get(EnderecoPojo.UF));
		setComplemento((String)endereco.get(EnderecoPojo.COMPLEMENTO));
	}

	public String getBD() {
		return BancoDados.ENDERECO;
	}

	

}
