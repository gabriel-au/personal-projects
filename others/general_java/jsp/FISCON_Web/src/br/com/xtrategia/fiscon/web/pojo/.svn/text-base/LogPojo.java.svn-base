package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Log
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "log", schema = "public")
@Proxy(lazy=false)
public class LogPojo extends TransferObject implements java.io.Serializable {

	public static final String ACESSO_MW="ACESSO_MW";
	public static final String ACESSO_WEB="ACESSO_WEB";
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dataEvento;
	private String tipo;
	private Integer idUsuario;
	private String ip;
	private String acao;

	public LogPojo() {
	}

	public LogPojo(String tipo,String ip, Integer idUsuario, String acao) {
		this.dataEvento = new Date();
		this.tipo = tipo;
		this.ip = ip;
		this.idUsuario = idUsuario;
		this.acao = acao;
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
	
	@Column(name = "data_evento")
	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	@Column(name = "tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "acao")
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	@Column(name = "id_usuario")
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
