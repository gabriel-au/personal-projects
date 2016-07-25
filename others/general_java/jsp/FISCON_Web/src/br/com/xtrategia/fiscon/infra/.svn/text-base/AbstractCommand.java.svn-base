package br.com.xtrategia.fiscon.infra;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.hibernate.Session;



/**
 * Superclasse das <code>Command</code>s do sistema. é possével
 * ainda continuar implementando a interface <code>Command</code>
 * diretamente, esta classe existe somente para reduzir a implementação
 * a um método (o <i>execute(...)</i>) e talvez futuramente incluir
 * comportamentos adicionais a serem compartilhados pelos descendentes
 * @see br.com.xtrategia.fiscon.infra.Command para maiores detalhes
 * 
 * @author Gustavo Marcelo Costa
 */
public abstract class AbstractCommand implements Command {

	/**
	 * Sessão do hibernate
	 */
	protected Session sessao;
	
	static private NegocioFacade fachada = new NegocioFacadeImpl();
	/**
	 * Nome do command
	 */
	private String nome;
	
	/**
	 * Formatador de datas
	 */
	static public DateFormat formatadorDiaMesAno = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Construtor padrão
	 */
	public AbstractCommand() {
		super();
		nome = getClass().getName();
		nome = nome.substring(nome.lastIndexOf(".")+1);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		return nome;
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {

		if(obj instanceof AbstractCommand) {
			
			return ((AbstractCommand)obj).nome.equals(this.nome);
		}
		
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {

		return nome.hashCode();
	}
	
	/**
	 * @see br.com.xtrategia.fiscon.infra.Command#setSession(net.sf.hibernate.Session)
	 */
	public void setSession(Session sessao) {
		this.sessao = sessao;
	}
	
	/**
	 * @return Instância da fachada
	 */
	static public NegocioFacade getFachada() {

		return AbstractCommand.fachada;
	}
	
}