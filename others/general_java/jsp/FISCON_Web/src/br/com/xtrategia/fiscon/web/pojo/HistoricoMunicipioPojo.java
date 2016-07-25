package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.HistoricoMunicipio
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "historico_municipio", schema = "public")
@Proxy(lazy=false)
public class HistoricoMunicipioPojo extends TransferObject implements
		java.io.Serializable {

	private Integer id;
	private Boolean ativo;
	private Date dataModificacao;
	private String codigo;
	private String nome;
	private String uf;

	public HistoricoMunicipioPojo() {
	}

	public HistoricoMunicipioPojo(Integer id) {
		this.id = id;
	}

	public HistoricoMunicipioPojo(Integer id, Boolean ativo,
			Date dataModificacao, String codigo, String nome, String uf) {
		this.id = id;
		this.ativo = ativo;
		this.dataModificacao = dataModificacao;
		this.codigo = codigo;
		this.nome = nome;
		this.uf = uf;
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

	@Column(name = "codigo", length = 4)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome", length = 40)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "uf", length = 2)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}