package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Veiculo
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "veiculo", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "veiculo_placa"))
@Proxy(lazy=false)
public class VeiculoPojo extends TransferObject implements java.io.Serializable {

	private Integer id;
	private CorPojo corPojo;
	private CategoriaPojo categoriaPojo;
	private MunicipioPojo municipioPojo;
	private TipoPojo tipoPojo;
	private EspeciePojo especiePojo;
	private MarcaModeloPojo marcaModeloPojo;
	private Boolean ativo;
	private Date dataModificacao;
	private String veiculoPlaca;
	private Integer anoModelo;
	private Integer anoFabricacao;
	private Set<RestricaoPojo> restricaoPojos =new HashSet<RestricaoPojo>();

	public VeiculoPojo() {
	}

	public VeiculoPojo(Integer id, String veiculoPlaca) {
		this.id = id;
		this.veiculoPlaca = veiculoPlaca;
	}

	public VeiculoPojo(Integer id, CorPojo corPojo,
			CategoriaPojo categoriaPojo, MunicipioPojo municipioPojo,
			TipoPojo tipoPojo, EspeciePojo especiePojo,
			MarcaModeloPojo marcaModeloPojo, Boolean ativo,
			Date dataModificacao, String veiculoPlaca, Integer anoModelo,
			Integer anoFabricacao) {
		this.id = id;
		this.corPojo = corPojo;
		this.categoriaPojo = categoriaPojo;
		this.municipioPojo = municipioPojo;
		this.tipoPojo = tipoPojo;
		this.especiePojo = especiePojo;
		this.marcaModeloPojo = marcaModeloPojo;
		this.ativo = ativo;
		this.dataModificacao = dataModificacao;
		this.veiculoPlaca = veiculoPlaca;
		this.anoModelo = anoModelo;
		this.anoFabricacao = anoFabricacao;
	}
	public VeiculoPojo(CorPojo corPojo,
			CategoriaPojo categoriaPojo, MunicipioPojo municipioPojo,
			TipoPojo tipoPojo, EspeciePojo especiePojo,
			MarcaModeloPojo marcaModeloPojo, Boolean ativo,
			String veiculoPlaca, Integer anoModelo,
			Integer anoFabricacao) {
		this.corPojo = corPojo;
		this.categoriaPojo = categoriaPojo;
		this.municipioPojo = municipioPojo;
		this.tipoPojo = tipoPojo;
		this.especiePojo = especiePojo;
		this.marcaModeloPojo = marcaModeloPojo;
		this.ativo = ativo;
		this.dataModificacao = new Date();
		this.veiculoPlaca = veiculoPlaca;
		this.anoModelo = anoModelo;
		this.anoFabricacao = anoFabricacao;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cor")
	public CorPojo getCorPojo() {
		return this.corPojo;
	}

	public void setCorPojo(CorPojo corPojo) {
		this.corPojo = corPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	public CategoriaPojo getCategoriaPojo() {
		return this.categoriaPojo;
	}

	public void setCategoriaPojo(CategoriaPojo categoriaPojo) {
		this.categoriaPojo = categoriaPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio")
	public MunicipioPojo getMunicipioPojo() {
		return this.municipioPojo;
	}

	public void setMunicipioPojo(MunicipioPojo municipioPojo) {
		this.municipioPojo = municipioPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo")
	public TipoPojo getTipoPojo() {
		return this.tipoPojo;
	}

	public void setTipoPojo(TipoPojo tipoPojo) {
		this.tipoPojo = tipoPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especie")
	public EspeciePojo getEspeciePojo() {
		return this.especiePojo;
	}

	public void setEspeciePojo(EspeciePojo especiePojo) {
		this.especiePojo = especiePojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marca_modelo")
	public MarcaModeloPojo getMarcaModeloPojo() {
		return this.marcaModeloPojo;
	}

	public void setMarcaModeloPojo(MarcaModeloPojo marcaModeloPojo) {
		this.marcaModeloPojo = marcaModeloPojo;
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

	@Column(name = "veiculo_placa", unique = true, nullable = false, length = 7)
	public String getVeiculoPlaca() {
		return this.veiculoPlaca;
	}

	public void setVeiculoPlaca(String veiculoPlaca) {
		this.veiculoPlaca = veiculoPlaca;
	}

	@Column(name = "ano_modelo")
	public Integer getAnoModelo() {
		return this.anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	@Column(name = "ano_fabricacao")
	public Integer getAnoFabricacao() {
		return this.anoFabricacao;
	}

	public void setAnoFabricacao(Integer anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="id_veiculo")
	public Set<RestricaoPojo> getRestricaoPojos() {
		return restricaoPojos;
	}
	
	
	public void setRestricaoPojos(Set<RestricaoPojo> restricaoPojos) {
		this.restricaoPojos = restricaoPojos;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
