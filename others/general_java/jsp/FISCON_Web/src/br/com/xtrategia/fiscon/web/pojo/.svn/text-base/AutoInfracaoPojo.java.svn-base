package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.AutoInfracao
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "auto_infracao", schema = "public")
@Proxy(lazy=false)
public class AutoInfracaoPojo extends TransferObject implements
		java.io.Serializable {

	private Integer id;
	//relacionamentos
	private VeiculoPojo veiculoPojo;
	private UsuarioPojo usuarioPojo;
	private MunicipioPojo municipioPojo;
	private InfracaoTipoPojo infracaoTipoPojo;
	private CnhPojo cnhPojo;
	
	private Date dataInfracao;
	private Date dataEnvio;
	private String orgaoAutuador;
	private String codigoOrgaoAutuador;
	private String numeroAutoInfracao;
	private String enderecoLogradouro;
	private String enderecoComplemento;
	private String enderecoBairro;
	private String enderecoUF;
	
	private String equipInstrumento;
	private String certificado;
	private Date dataVerificacao;
	private String medicaoRealizada;
	private String limiteRegulamentado;
	private String valorConsiderado;
	private String codigoRenainf;
	private Date dataExpedicao;
	private String valor;
	private String latitude;
	private String longitude;
	
	//dados de controle
	private Boolean flagEndereco;
	private Boolean flagVeiculo;
	private Boolean flagCondutor;
	private Date dataHomologacao;
	private UsuarioPojo usuarioHomologacao;
	private String chaveExtracao;
	private String cnh;
	private String situacao;
	
	
	private Boolean foto;
	private Set<FotoPojo> fotoPojos = new HashSet<FotoPojo>(0);
	
	public AutoInfracaoPojo() {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_veiculo", nullable = false)
	public VeiculoPojo getVeiculoPojo() {
		return this.veiculoPojo;
	}

	public void setVeiculoPojo(VeiculoPojo veiculoPojo) {
		this.veiculoPojo = veiculoPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	public UsuarioPojo getUsuarioPojo() {
		return this.usuarioPojo;
	}

	public void setUsuarioPojo(UsuarioPojo usuarioPojo) {
		this.usuarioPojo = usuarioPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio", nullable = false)
	public MunicipioPojo getMunicipioPojo() {
		return this.municipioPojo;
	}

	public void setMunicipioPojo(MunicipioPojo municipioPojo) {
		this.municipioPojo = municipioPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_infracao_tipo", nullable = false)
	public InfracaoTipoPojo getInfracaoTipoPojo() {
		return this.infracaoTipoPojo;
	}

	public void setInfracaoTipoPojo(InfracaoTipoPojo infracaoTipoPojo) {
		this.infracaoTipoPojo = infracaoTipoPojo;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "id_cnh")
	public CnhPojo getCnhPojo() {
		return this.cnhPojo;
	}

	public void setCnhPojo(CnhPojo cnhPojo) {
		this.cnhPojo = cnhPojo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_infracao", length = 29)
	public Date getDataInfracao() {
		return this.dataInfracao;
	}

	public void setDataInfracao(Date dataInfracao) {
		this.dataInfracao = dataInfracao;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_envio", length = 29)
	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	@Column(name = "foto")
	public Boolean getFoto() {
		return this.foto;
	}

	public void setFoto(Boolean foto) {
		this.foto = foto;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "autoInfracaoPojo", cascade={CascadeType.ALL})
	public Set<FotoPojo> getFotoPojos() {
		return this.fotoPojos;
	}

	public void setFotoPojos(Set<FotoPojo> fotoPojos) {
		this.fotoPojos = fotoPojos;
	}



	@Column(name = "orgao_autuador")
	public String getOrgaoAutuador() {
		return orgaoAutuador;
	}

	public void setOrgaoAutuador(String orgaoAutuador) {
		this.orgaoAutuador = orgaoAutuador;
	}

	@Column(name = "codigo_orgao_autuador")
	public String getCodigoOrgaoAutuador() {
		return codigoOrgaoAutuador;
	}

	public void setCodigoOrgaoAutuador(String codigoOrgaoAutuador) {
		this.codigoOrgaoAutuador = codigoOrgaoAutuador;
	}

	@Column(name = "numero_auto_infracao")
	public String getNumeroAutoInfracao() {
		return numeroAutoInfracao;
	}

	public void setNumeroAutoInfracao(String numeroAutoInfracao) {
		this.numeroAutoInfracao = numeroAutoInfracao;
	}

	@Column(name = "endereco_logradouro")
	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}

	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}

	@Column(name = "endereco_complemento")
	public String getEnderecoComplemento() {
		return enderecoComplemento;
	}

	public void setEnderecoComplemento(String enderecoComplemento) {
		this.enderecoComplemento = enderecoComplemento;
	}

	@Column(name = "endereco_bairro")
	public String getEnderecoBairro() {
		return enderecoBairro;
	}

	public void setEnderecoBairro(String enderecoBairro) {
		this.enderecoBairro = enderecoBairro;
	}

	@Column(name = "endereco_uf")
	public String getEnderecoUF() {
		return enderecoUF;
	}

	public void setEnderecoUF(String enderecoUF) {
		this.enderecoUF = enderecoUF;
	}

	@Column(name = "equip_instrumento")
	public String getEquipInstrumento() {
		return equipInstrumento;
	}

	public void setEquipInstrumento(String equipInstrumento) {
		this.equipInstrumento = equipInstrumento;
	}

	@Column(name = "certificado")
	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_verificacao")
	public Date getDataVerificacao() {
		return dataVerificacao;
	}

	public void setDataVerificacao(Date dataVerificacao) {
		this.dataVerificacao = dataVerificacao;
	}

	@Column(name = "medicao_realizada")
	public String getMedicaoRealizada() {
		return medicaoRealizada;
	}

	public void setMedicaoRealizada(String medicaoRealizada) {
		this.medicaoRealizada = medicaoRealizada;
	}

	@Column(name = "limite_regulamentado")
	public String getLimiteRegulamentado() {
		return limiteRegulamentado;
	}

	public void setLimiteRegulamentado(String limiteRegulamentado) {
		this.limiteRegulamentado = limiteRegulamentado;
	}

	@Column(name = "valor_considerado")
	public String getValorConsiderado() {
		return valorConsiderado;
	}

	public void setValorConsiderado(String valorConsiderado) {
		this.valorConsiderado = valorConsiderado;
	}

	@Column(name = "codigo_renainf")
	public String getCodigoRenainf() {
		return codigoRenainf;
	}

	public void setCodigoRenainf(String codigoRenainf) {
		this.codigoRenainf = codigoRenainf;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_expedicao")
	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	@Column(name = "valor")
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	private static final long serialVersionUID = 1L;

	@Column(name = "flag_endereco")
	public Boolean getFlagEndereco() {
		return flagEndereco;
	}


	public void setFlagEndereco(Boolean flagEndereco) {
		this.flagEndereco = flagEndereco;
	}

	@Column(name = "flag_veiculo")
	public Boolean getFlagVeiculo() {
		return flagVeiculo;
	}


	public void setFlagVeiculo(Boolean flagVeiculo) {
		this.flagVeiculo = flagVeiculo;
	}


	@Column(name = "flag_condutor")
	public Boolean getFlagCondutor() {
		return flagCondutor;
	}

	public void setFlagCondutor(Boolean flagCondutor) {
		this.flagCondutor = flagCondutor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_homologacao", length = 29)
	public Date getDataHomologacao() {
		return dataHomologacao;
	}


	public void setDataHomologacao(Date dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_homologacao", nullable = true)
	public UsuarioPojo getUsuarioHomologacao() {
		return usuarioHomologacao;
	}


	public void setUsuarioHomologacao(UsuarioPojo usuarioHomologacao) {
		this.usuarioHomologacao = usuarioHomologacao;
	}

	@Column(name = "chave_extracao")
	public String getChaveExtracao() {
		return chaveExtracao;
	}


	public void setChaveExtracao(String chaveExtracao) {
		this.chaveExtracao = chaveExtracao;
	}

	@Column(name = "cnh")
	public String getCnh() {
		return cnh;
	}


	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	@Column(name = "situacao")
	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
