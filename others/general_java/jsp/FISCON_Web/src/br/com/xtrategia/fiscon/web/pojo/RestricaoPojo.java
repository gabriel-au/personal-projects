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
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Restricao
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "restricao", schema = "public")
@Proxy(lazy=false)
public class RestricaoPojo extends TransferObject implements
		java.io.Serializable {

	private Integer id;
	private Integer idVeiculo;
	private Date dataInclusao;
	private String placa;
	private RestricaoTipoPojo restricaoTipoPojo;

	public RestricaoPojo() {
	}

	public RestricaoPojo(Integer id) {
		this.id = id;
	}

	public RestricaoPojo(Integer id, Integer idVeiculo, Date dataInclusao, String placa) {
		this.id = id;
		this.idVeiculo = idVeiculo;
		this.dataInclusao = dataInclusao;
		this.placa = placa;
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

	@Column(name = "id_veiculo")
	public Integer getIdVeiculo() {
		return this.idVeiculo;
	}

	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inclusao", length = 29)
	public Date getDataInclusao() {
		return this.dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Column(name = "placa", length = 7)
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restricao_tipo")
	public RestricaoTipoPojo getRestricaoTipoPojo() {
		return restricaoTipoPojo;
	}

	public void setRestricaoTipoPojo(RestricaoTipoPojo restricaoTipoPojo) {
		this.restricaoTipoPojo = restricaoTipoPojo;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

	

}
