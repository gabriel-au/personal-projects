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
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Cor
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "atualizacoes", schema = "public")
@Proxy(lazy=false)
public class AtualizacaoPojo extends TransferObject implements java.io.Serializable {

	private Integer id;
	private Boolean producao;
	private Date dataInclusao;
	private String descricao;
;

	public AtualizacaoPojo() {
	}

	public AtualizacaoPojo(Integer id) {
		this.id = id;
	}

	public AtualizacaoPojo(Integer id, Boolean producao, Date dataInclusao,
			String descricao) {
		this.id = id;
		this.producao = producao;
		this.dataInclusao = dataInclusao;
		this.descricao = descricao;
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

	@Column(name = "producao")
	public Boolean getProducao() {
		return this.producao;
	}

	public void setProducao(Boolean producao) {
		this.producao = producao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inclusao")
	public Date getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}


	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
