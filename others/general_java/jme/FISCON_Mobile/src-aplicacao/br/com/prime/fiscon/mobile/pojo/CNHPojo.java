package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;

public class CNHPojo extends TransactionObject{
	
	public static final String REGISTRO = "registro";
	public static final String DATANASCIMENTO = "datanascimento";
	public static final String NOME = "nome";
	public static final String CATEGORIA = "categoria";
	public static final String DATAVALIDADE = "datavalidade";
	public static final String TIPO = "tipo";
	public static final String TOTALPONTOS = "totalpontos";
	
	
	private String registro;
	private String dataNascimento;
	private String nome;
	private String categoria;
	private String dataValidade;
	private String tipo;
	private String totalPontos;
	
	public CNHPojo(String id) {
		setId(id);
	}
	
	public CNHPojo() {
	}

	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTotalPontos() {
		return totalPontos;
	}
	public void setTotalPontos(String totalPontos) {
		this.totalPontos = totalPontos;
	}
	
	public Hashtable getHashtable(){
		Hashtable cnh = new Hashtable();
		cnh.put(CNHPojo.ID, getId());
		if(getCategoria()!=null)
			cnh.put(CNHPojo.CATEGORIA, getCategoria());
		if(getDataNascimento()!=null)
			cnh.put(CNHPojo.DATANASCIMENTO, getDataNascimento());
		if(getDataValidade()!=null)
			cnh.put(CNHPojo.DATAVALIDADE, getDataValidade());
		if(getNome()!=null)
			cnh.put(CNHPojo.NOME, getNome());
		if(getRegistro()!=null)
			cnh.put(CNHPojo.REGISTRO, getRegistro());
		if(getTipo()!=null)
			cnh.put(CNHPojo.TIPO, getTipo());
		if(getTotalPontos()!=null)
			cnh.put(CNHPojo.TOTALPONTOS, getTotalPontos());
		return cnh;
	}
	
	public void carregar(Hashtable cnh){
		setId((String)cnh.get(CNHPojo.ID));
		setCategoria((String)cnh.get(CNHPojo.CATEGORIA));
		setDataNascimento((String)cnh.get(CNHPojo.DATANASCIMENTO));
		setDataValidade((String)cnh.get(CNHPojo.DATAVALIDADE));
		setNome((String)cnh.get(CNHPojo.NOME));
		setRegistro((String)cnh.get(CNHPojo.REGISTRO));
		setTipo((String)cnh.get(CNHPojo.TIPO));
		setTotalPontos((String)cnh.get(CNHPojo.TOTALPONTOS));
	}
	public String getBD() {
		return BancoDados.CNH;
	}
	
}
