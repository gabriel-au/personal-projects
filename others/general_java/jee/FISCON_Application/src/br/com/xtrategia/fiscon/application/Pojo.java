package br.com.xtrategia.fiscon.application;

import java.sql.Timestamp;


/**
 * Classe base dos pojos
 * @author Gustavo
 *
 */
public abstract class Pojo {

	private int id;
	private boolean ativo;
	private Timestamp dataModificacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Timestamp getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(Timestamp dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	
}
