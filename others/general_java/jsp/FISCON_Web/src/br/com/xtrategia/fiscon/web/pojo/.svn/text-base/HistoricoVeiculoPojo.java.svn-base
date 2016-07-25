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
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.HistoricoVeiculo
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "historico_veiculo", schema = "public")
@Proxy(lazy=false)
public class HistoricoVeiculoPojo extends TransferObject implements
		java.io.Serializable {

	private Integer id;
	private Integer idCor;
	private Integer idCategoria;
	private Integer idMarcaModelo;
	private Integer idMunicipio;
	private Integer idEspecie;
	private Integer idTipo;
	private Boolean ativo;
	private Date dataModificacao;
	private String veiculoPlaca;
	private Integer anoModelo;
	private Integer anoFabricacao;

	public HistoricoVeiculoPojo() {
	}

	public HistoricoVeiculoPojo(Integer id, String veiculoPlaca) {
		this.id = id;
		this.veiculoPlaca = veiculoPlaca;
	}

	public HistoricoVeiculoPojo(Integer id, Integer idCor, Integer idCategoria,
			Integer idMarcaModelo, Integer idMunicipio, Integer idEspecie,
			Integer idTipo, Boolean ativo, Date dataModificacao,
			String veiculoPlaca, Integer anoModelo, Integer anoFabricacao) {
		this.id = id;
		this.idCor = idCor;
		this.idCategoria = idCategoria;
		this.idMarcaModelo = idMarcaModelo;
		this.idMunicipio = idMunicipio;
		this.idEspecie = idEspecie;
		this.idTipo = idTipo;
		this.ativo = ativo;
		this.dataModificacao = dataModificacao;
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

	@Column(name = "id_cor")
	public Integer getIdCor() {
		return this.idCor;
	}

	public void setIdCor(Integer idCor) {
		this.idCor = idCor;
	}

	@Column(name = "id_categoria")
	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Column(name = "id_marca_modelo")
	public Integer getIdMarcaModelo() {
		return this.idMarcaModelo;
	}

	public void setIdMarcaModelo(Integer idMarcaModelo) {
		this.idMarcaModelo = idMarcaModelo;
	}

	@Column(name = "id_municipio")
	public Integer getIdMunicipio() {
		return this.idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	@Column(name = "id_especie")
	public Integer getIdEspecie() {
		return this.idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

	@Column(name = "id_tipo")
	public Integer getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
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

	@Column(name = "veiculo_placa", nullable = false, length = 7)
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

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
