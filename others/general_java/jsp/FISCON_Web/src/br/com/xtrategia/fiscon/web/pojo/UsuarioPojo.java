package br.com.xtrategia.fiscon.web.pojo;

// Generated 15/02/2010 18:12:05 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

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
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Usuario
 * @author Gustavo Marcelo Costa
 */
@Entity
@Table(name = "usuario", schema = "public")
@Proxy(lazy=false)
public class UsuarioPojo extends TransferObject implements java.io.Serializable {

	private Integer id;
	private Boolean ativo;
	private String matricula;
	private String nome;
	private String sobrenome;
	private String username;
	private String passwordWeb;
	private String passwordMob;
	private String email;
	private Set<PerfilPojo> perfilPojos = new HashSet<PerfilPojo>(0);

	public UsuarioPojo() {
	}

	public UsuarioPojo(Integer id, boolean ativo, String nome, String sobrenome,
			String username, String passwordWeb) {
		this.id = id;
		this.ativo = ativo;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.username = username;
		this.passwordWeb = passwordWeb;
	}
	public UsuarioPojo(String username, String passwordWeb) {
		this.username = username;
		this.passwordWeb = passwordWeb;
	}

	public UsuarioPojo(Integer id, boolean ativo, String matricula, String nome,
			String sobrenome, String username, String passwordWeb,
			String passwordMob, String email) {
		this.id = id;
		this.ativo = ativo;
		this.matricula = matricula;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.username = username;
		this.passwordWeb = passwordWeb;
		this.passwordMob = passwordMob;
		this.email = email;
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

	@Column(name = "ativo", nullable = false, length = 1)
	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "matricula", length = 50)
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Column(name = "nome", nullable = false, length = 20)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "sobrenome", nullable = false, length = 20)
	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Column(name = "username", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password_web", nullable = false)
	public String getPasswordWeb() {
		return this.passwordWeb;
	}

	public void setPasswordWeb(String passwordWeb) {
		this.passwordWeb = passwordWeb;
	}

	@Column(name = "password_mob")
	public String getPasswordMob() {
		return this.passwordMob;
	}

	public void setPasswordMob(String passwordMob) {
		this.passwordMob = passwordMob;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(  
         targetEntity=PerfilPojo.class,  
         fetch=FetchType.EAGER
     )  
     @JoinTable(  
         name="usuario_perfil",  
         joinColumns=@JoinColumn(name="id_usuario"),  
         inverseJoinColumns=@JoinColumn(name="id_perfil")  
     )
	public Set<PerfilPojo> getPerfilPojos() {
		return this.perfilPojos;
	}

	public void setPerfilPojos(Set<PerfilPojo> perfilPojos) {
		this.perfilPojos = perfilPojos;
	}

	// The following is extra code specified in the hbm.xml files
	private static final long serialVersionUID = 1L;
	// end of extra code specified in the hbm.xml files

}
