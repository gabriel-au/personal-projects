package br.com.prime.fiscon.mobile.pojo;

import java.util.Hashtable;

import br.com.codequest.mobile.util.BancoDados;


/**
 * Classe para implementar o POJO br.com.xtrategia.fiscon.web.pojo.Usuario
 * @author Gustavo Marcelo Costa
 */
public class UsuarioPojo  extends TransactionObject{

	private static final long serialVersionUID = 1L;
	public static final String MATRICULA = "MATRICULA";
	public static final String NOME = "NOME";
	public static final String SOBRENOME = "SOBRENOME";
	
	
	private String matricula;
	private String nome;
	private String sobrenome;
	
	private String horaUlimoAcesso;

	public UsuarioPojo(String id) {
		setId(id);
	}


	public UsuarioPojo() {
	}


	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getHoraUlimoAcesso() {
		return horaUlimoAcesso;
	}

	public void setHoraUlimoAcesso(String horaUlimoAcesso) {
		this.horaUlimoAcesso = horaUlimoAcesso;
	}

	public void carregar(Hashtable infracaoTipo) {
		setId((String)infracaoTipo.get(UsuarioPojo.ID));
		setMatricula((String)infracaoTipo.get(UsuarioPojo.MATRICULA));
		setNome((String)infracaoTipo.get(UsuarioPojo.NOME));
		setSobrenome((String)infracaoTipo.get(UsuarioPojo.SOBRENOME));
	}

	public String getBD() {
		return BancoDados.USUARIO;
	}

	public Hashtable getHashtable() {
		Hashtable registro = new Hashtable();
		registro.put(UsuarioPojo.ID, getId());
		if(getMatricula()!=null)
			registro.put(UsuarioPojo.MATRICULA, getMatricula());
		if(getNome()!=null)
			registro.put(UsuarioPojo.NOME, getNome());
		if(getSobrenome()!=null)
			registro.put(UsuarioPojo.SOBRENOME, getSobrenome());
		return registro;
	}

}
