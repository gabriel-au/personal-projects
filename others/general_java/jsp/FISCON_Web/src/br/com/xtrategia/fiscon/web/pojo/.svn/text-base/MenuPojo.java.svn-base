package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Cor
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "menu", schema = "public")
@Proxy(lazy=false)
public class MenuPojo extends TransferObject implements java.io.Serializable, Comparable<MenuPojo> {

	private Integer id;
	private String codigo;
	private String nome;
	private String acao;

	public MenuPojo() {
	}

	public MenuPojo(Integer id) {
		this.id = id;
	}

	public MenuPojo(Integer id, String codigo, String nome, String acao) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
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

	@Column(name = "codigo", length = 45)
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "acao", length = 45)
	public String getAcao() {
		return this.acao;
	}
	
	public void setAcao(String acao) {
		this.acao = acao;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(MenuPojo o) {
		if(codigo==null || o==null || o.getCodigo()==null){
			return 1;
		}
		return codigo.compareTo(o.getCodigo());
	}

}
