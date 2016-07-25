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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Cnh
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "cnh", schema = "public")
@Proxy(lazy=false)
public class CnhPojo extends TransferObject implements java.io.Serializable {

	private Integer id;
	private String cnh;
	private String cpf;
	private String nome;
	private String categoria;
	private String filiacaoMae;
	private String filiacaoPai;
	private Date habilitacao;
	private Date emissao;
	private Date validade;
	private Date dataNascimento;
	private String obs;
	private String uf;
	private Integer pontuacao;
	private String tipo;
	private Set<AutoInfracaoPojo> autoInfracaoPojos = new HashSet<AutoInfracaoPojo>(
			0);

	public CnhPojo() {
	}

	public CnhPojo(Integer id, String cpf, String nome, String filiacaoMae,
			Date habilitacao, Date emissao, Date validade) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.filiacaoMae = filiacaoMae;
		this.habilitacao = habilitacao;
		this.emissao = emissao;
		this.validade = validade;
	}

	public CnhPojo(Integer id, String cnh, String cpf, String nome,
			String categoria, String filiacaoMae, String filiacaoPai,
			Date habilitacao, Date emissao, Date validade, String obs,
			Integer pontuacao, Set<AutoInfracaoPojo> autoInfracaoPojos) {
		this.id = id;
		this.cnh = cnh;
		this.cpf = cpf;
		this.nome = nome;
		this.categoria = categoria;
		this.filiacaoMae = filiacaoMae;
		this.filiacaoPai = filiacaoPai;
		this.habilitacao = habilitacao;
		this.emissao = emissao;
		this.validade = validade;
		this.obs = obs;
		this.pontuacao = pontuacao;
		this.autoInfracaoPojos = autoInfracaoPojos;
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

	@Column(name = "cnh", length = 45)
	public String getCnh() {
		return this.cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	@Column(name = "cpf", nullable = true, length = 11)
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "categoria", length = 2)
	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Column(name = "filiacao_mae", nullable = true)
	public String getFiliacaoMae() {
		return this.filiacaoMae;
	}

	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}

	@Column(name = "filiacao_pai")
	public String getFiliacaoPai() {
		return this.filiacaoPai;
	}

	public void setFiliacaoPai(String filiacaoPai) {
		this.filiacaoPai = filiacaoPai;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "habilitacao", nullable = true, length = 13)
	public Date getHabilitacao() {
		return this.habilitacao;
	}

	public void setHabilitacao(Date habilitacao) {
		this.habilitacao = habilitacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "emissao", nullable = true, length = 13)
	public Date getEmissao() {
		return this.emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = true, length = 13)
	public Date getDataNascimento() {
		return this.dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "validade", nullable = true, length = 13)
	public Date getValidade() {
		return this.validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	@Column(name = "obs")
	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	@Column(name = "uf")
	public String getUf() {
		return this.uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "pontuacao")
	public Integer getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	@Column(name = "tipo")
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cnhPojo", cascade={CascadeType.ALL})
	public Set<AutoInfracaoPojo> getAutoInfracaoPojos() {
		return this.autoInfracaoPojos;
	}

	public void setAutoInfracaoPojos(Set<AutoInfracaoPojo> autoInfracaoPojos) {
		this.autoInfracaoPojos = autoInfracaoPojos;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
