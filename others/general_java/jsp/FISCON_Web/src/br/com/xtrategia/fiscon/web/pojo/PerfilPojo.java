package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import br.com.xtrategia.fiscon.infra.TransferObject;

/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Perfil
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "perfil", schema = "public")
@Proxy(lazy=false)
public class PerfilPojo extends TransferObject implements java.io.Serializable {

	private Integer id;
	private String nome;
	private Set<MenuPojo> menuPojos = new HashSet<MenuPojo>(0);

	public PerfilPojo() {
	}

	public PerfilPojo(Integer id) {
		this.id = id;
	}

	public PerfilPojo(Integer id, String nome) {
		this.id = id;
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

	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToMany(  
         targetEntity=MenuPojo.class,  
         cascade={CascadeType.PERSIST}  ,
         fetch=FetchType.EAGER
     )  
     @JoinTable(  
         name="perfil_menu",  
         joinColumns=@JoinColumn(name="id_perfil"),  
         inverseJoinColumns=@JoinColumn(name="id_menu")  
     )
	public Set<MenuPojo> getMenuPojos() {
		return this.menuPojos;
	}

	public void setMenuPojos(Set<MenuPojo> menuPojos) {
		this.menuPojos = menuPojos;
	}
	
	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
