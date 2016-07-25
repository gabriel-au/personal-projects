package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Foto
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "foto", schema = "public")
@Proxy(lazy=false)
public class FotoPojo extends TransferObject implements java.io.Serializable {

	private Integer id;
	private AutoInfracaoPojo autoInfracaoPojo;
	private String dado;
	private String foto;
	

	public FotoPojo() {
	}

	public FotoPojo(Integer id, AutoInfracaoPojo autoInfracaoPojo, String dado) {
		this.id = id;
		this.autoInfracaoPojo = autoInfracaoPojo;
		this.dado = dado;
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
	@JoinColumn(name = "id_auto_infracao", nullable = false)
	public AutoInfracaoPojo getAutoInfracaoPojo() {
		return this.autoInfracaoPojo;
	}

	public void setAutoInfracaoPojo(AutoInfracaoPojo autoInfracaoPojo) {
		this.autoInfracaoPojo = autoInfracaoPojo;
	}

	@Column(name = "dado", nullable = false)
	public String getDado() {
		return this.dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files


	@Transient
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
