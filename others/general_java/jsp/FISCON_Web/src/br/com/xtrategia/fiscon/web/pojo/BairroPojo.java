package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Municipio
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "bairro", schema = "public")
@Proxy(lazy=false)
public class BairroPojo extends TransferObject implements
		java.io.Serializable {

	private Integer id;
	private Boolean ativo;
	private Date dataModificacao;
	private String nome;
	private MunicipioPojo municipioPojo;

	public BairroPojo() {
	}

	public BairroPojo(Integer id) {
		this.id = id;
	}
	public BairroPojo(String nome) {
		this.nome = nome;
	}

	public BairroPojo(Integer id, Boolean ativo, Date dataModificacao, String nome) {
		this.id = id;
		this.ativo = ativo;
		this.dataModificacao = dataModificacao;
		this.nome = nome;
	}
	public BairroPojo(Boolean ativo, Date dataModificacao, String nome) {
		this.ativo = ativo;
		this.dataModificacao = new Date();
		this.nome = nome;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ativo")
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_modificacao", length = 29)
	public Date getDataModificacao() {
		return this.dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	@Column(name = "nome", length = 40)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio")
	public MunicipioPojo getMunicipioPojo() {
		return this.municipioPojo;
	}

	public void setMunicipioPojo(MunicipioPojo municipioPojo) {
		this.municipioPojo = municipioPojo;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
