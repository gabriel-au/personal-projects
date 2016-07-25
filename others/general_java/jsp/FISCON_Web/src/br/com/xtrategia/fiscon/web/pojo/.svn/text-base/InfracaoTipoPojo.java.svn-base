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
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.InfracaoTipo
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "infracao_tipo", schema = "public")
@Proxy(lazy=false)
public class InfracaoTipoPojo extends TransferObject implements
		java.io.Serializable {

	private Integer id;
	private Boolean ativo;
	private Date dataModificacao;
	private String codigo;
	private String nome;
	private String amparoLegal;
	private String infrator;
	private String gravidade;
	private String orgao;

	public InfracaoTipoPojo() {
	}

	public InfracaoTipoPojo(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public InfracaoTipoPojo(Integer id, Boolean ativo, Date dataModificacao,
			String codigo, String nome) {
		this.id = id;
		this.ativo = ativo;
		this.dataModificacao = dataModificacao;
		this.codigo = codigo;
		this.nome = nome;
	}
	public InfracaoTipoPojo(Boolean ativo,
			String codigo, String nome) {
		this.ativo = ativo;
		this.dataModificacao = new Date();
		this.codigo = codigo;
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

	@Column(name = "codigo", length = 15)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "amparoLegal", nullable = false, length = 45)
	public String getAmparoLegal() {
		return amparoLegal;
	}

	public void setAmparoLegal(String amparoLegal) {
		this.amparoLegal = amparoLegal;
	}
	
	@Column(name = "infrator", nullable = false, length = 45)
	public String getInfrator() {
		return infrator;
	}

	public void setInfrator(String infrator) {
		this.infrator = infrator;
	}
	@Column(name = "gravidade", nullable = false, length = 45)
	public String getGravidade() {
		return gravidade;
	}

	public void setGravidade(String gravidade) {
		this.gravidade = gravidade;
	}
	
	@Column(name = "orgao", nullable = false, length = 45)
	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	
	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
