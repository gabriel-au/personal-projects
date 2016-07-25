package br.com.consorciosdf.intranet.modelo;
import java.util.List;

public class Usuario {

    private int codUsuario;
    private int perfilUsuario;
    private String matriculaUsuario;
    private String user;
    private String password;
    private String passwordConfirm;
    private String nomeUsuario;
    private String sobrenomeUsuario;
    private String emailUsuario;
    private String modificarPrefUsuario;
    private String skype;
    private List<UsuarioRule> usuarioRules;

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(int perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public String getModificarPrefUsuario() {
        return modificarPrefUsuario;
    }

    public void setModificarPrefUsuario(String modificarPrefUsuario) {
        this.modificarPrefUsuario = modificarPrefUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    public void setSobrenomeUsuario(String sobrenomeUsuario) {
        this.sobrenomeUsuario = sobrenomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(String matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public List<UsuarioRule> getUsuarioRules() {
        return usuarioRules;
    }

    public void setUsuarioRules(List<UsuarioRule> usuarioRules) {
        this.usuarioRules = usuarioRules;
    }

}
