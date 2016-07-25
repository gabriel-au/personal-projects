package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.InfracaoTipo
 * 
 * @author Gustavo Luis Costa
 */
public class InfracaoTipoPojo extends TransactionObject{
	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String AMPAROLEGAL = "amparolegal";
	public static final String GRAVIDADE = "gravidade";
	
	
	private String codigo;
	private String nome;
	private String amparoLegal;
	private String gravidade;

	public InfracaoTipoPojo(String id) {
		setId(id);
	}

	public InfracaoTipoPojo(String codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);

	}
	public InfracaoTipoPojo(String codigo, String nome, String amparoLegal, String gravidade) {
		setCodigo(codigo);
		setNome(nome);
		setAmparoLegal(amparoLegal);
		setGravidade(gravidade);

	}
	public InfracaoTipoPojo(String id, String codigo, String nome) {
		setId(id);
		this.codigo = codigo;
		this.nome = nome;
	}

	public String toString() {
		return codigo;
	}
	

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAmparoLegal() {
		return amparoLegal;
	}

	public void setAmparoLegal(String amparoLegal) {
		this.amparoLegal = amparoLegal;
	}

	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}
	
	public Hashtable getHashtable(){
		Hashtable infracaoTipo = new Hashtable();
		if(getAmparoLegal()!=null)
			infracaoTipo.put(InfracaoTipoPojo.AMPAROLEGAL, getAmparoLegal());
		if(getCodigo()!=null)
			infracaoTipo.put(InfracaoTipoPojo.CODIGO, getCodigo());
		if(getGravidade()!=null)
			infracaoTipo.put(InfracaoTipoPojo.GRAVIDADE, getGravidade());
		infracaoTipo.put(InfracaoTipoPojo.ID, getId());
		if(getNome()!=null)
			infracaoTipo.put(InfracaoTipoPojo.NOME, getNome());
		return infracaoTipo;
	}
	
	public void carregar(Hashtable infracaoTipo){
		setAmparoLegal((String)infracaoTipo.get(InfracaoTipoPojo.AMPAROLEGAL));
		setCodigo((String)infracaoTipo.get(InfracaoTipoPojo.CODIGO));
		setGravidade((String)infracaoTipo.get(InfracaoTipoPojo.GRAVIDADE));
		setId((String)infracaoTipo.get(InfracaoTipoPojo.ID));
		setNome((String)infracaoTipo.get(InfracaoTipoPojo.NOME));
	}

	public String getBD() {
		return BancoDados.INFRACAOTIPO;
	}
}
