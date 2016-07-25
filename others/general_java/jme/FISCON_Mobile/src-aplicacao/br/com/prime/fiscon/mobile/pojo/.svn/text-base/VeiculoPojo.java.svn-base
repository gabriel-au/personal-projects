package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;


public class VeiculoPojo extends TransactionObject{
	
	
	public static final String COR = "cor";
	public static final String CATEGORIA = "categoria";
	public static final String MUNICIPIO = "municipio";
	public static final String TIPO = "tipo";
	public static final String ESPECIE = "especie";
	public static final String MARCAMODELO = "marcamodelo";
	public static final String VEICULOPLACA = "veiculoplaca";
	public static final String ANOMODELO = "ANOMODELO";
	public static final String ANOFABRICACAO = "anofabricacao";
	public static final String RESTRICAO = "restricao";
	public static final String UF = "uf";
	
	
	private String cor;
	private String categoria;
	private String municipio;
	private String tipo;
	private String especie;
	private String marcaModelo;
	private String veiculoPlaca;
	private String anoModelo;
	private String anoFabricacao;
	private String restricao;
	private String uf;
	
	private String numeroAutoInfracao;
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroAutoInfracao() {
		return numeroAutoInfracao;
	}

	public void setNumeroAutoInfracao(String numeroAutoInfracao) {
		this.numeroAutoInfracao = numeroAutoInfracao;
	}

	public VeiculoPojo(String id) {
		setId(id);
	}
	
	public VeiculoPojo() {
	}

	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getMarcaModelo() {
		return marcaModelo;
	}
	public void setMarcaModelo(String marcaModelo) {
		this.marcaModelo = marcaModelo;
	}
	public String getVeiculoPlaca() {
		return veiculoPlaca;
	}
	public void setVeiculoPlaca(String veiculoPlaca) {
		this.veiculoPlaca = veiculoPlaca;
	}
	public String getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public String getRestricao() {
		return restricao;
	}
	public void setRestricao(String restricao) {
		this.restricao = restricao;
	}
	
	public Hashtable getHashtable(){
		Hashtable veiculo = new Hashtable();
		veiculo.put(VeiculoPojo.ID,getId());
		if(getCor()!=null)
			veiculo.put(VeiculoPojo.COR,getCor());
		if(getTipo()!=null)
			veiculo.put(VeiculoPojo.TIPO, getTipo());
		if(getAnoFabricacao()!=null)
			veiculo.put(VeiculoPojo.ANOFABRICACAO, getAnoFabricacao());
		if(getAnoModelo()!=null)
			veiculo.put(VeiculoPojo.ANOMODELO, getAnoModelo());
		if(getCategoria()!=null)
			veiculo.put(VeiculoPojo.CATEGORIA, getCategoria());
		if(getEspecie()!=null)
			veiculo.put(VeiculoPojo.ESPECIE, getEspecie());
		if(getMarcaModelo()!=null)
			veiculo.put(VeiculoPojo.MARCAMODELO, getMarcaModelo());
		if(getMunicipio()!=null)
			veiculo.put(VeiculoPojo.MUNICIPIO, getMunicipio());
		if(getRestricao()!=null)
			veiculo.put(VeiculoPojo.RESTRICAO, getRestricao());
		if(getUf()!=null)
			veiculo.put(VeiculoPojo.UF, getUf());
		if(getVeiculoPlaca()!=null)
			veiculo.put(VeiculoPojo.VEICULOPLACA, getVeiculoPlaca());
		return veiculo;
	}
	
	public void carregar(Hashtable veiculo){
		setId((String)veiculo.get(VeiculoPojo.ID));
		setCor((String)veiculo.get(VeiculoPojo.COR));
		setCategoria((String)veiculo.get(VeiculoPojo.CATEGORIA));
		setTipo((String)veiculo.get(VeiculoPojo.TIPO));
		setAnoFabricacao((String)veiculo.get(VeiculoPojo.ANOFABRICACAO));
		setAnoModelo((String)veiculo.get(VeiculoPojo.ANOMODELO));
		setEspecie((String)veiculo.get(VeiculoPojo.ESPECIE));
		setMarcaModelo((String)veiculo.get(VeiculoPojo.MARCAMODELO));
		setMunicipio((String)veiculo.get(VeiculoPojo.MUNICIPIO));
		setRestricao((String)veiculo.get(VeiculoPojo.RESTRICAO));
		setUf((String)veiculo.get(VeiculoPojo.UF));
		setVeiculoPlaca((String)veiculo.get(VeiculoPojo.VEICULOPLACA));
	}
	public String getBD() {
		return BancoDados.VEICULO;
	}
	
}
