package br.com.xtrategia.fiscon.infra;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Superclasse dos objetos que transportam dados entre as camadas
 * de negócio e de controle, de acordo com o padrão <i>DTO</i>, 
 * Transfer Object (Deepak Alur et al, <i>Core J2EE Patterns</i>)
 *
 * @author Gustavo Marcelo Costa
 *  
 */
public abstract class TransferObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Conteúdo do <code>TransferObject</code>
	 */
	private Object conteudo;
	private int inicio;
	private int quantidadeRegistros;
	private Long pagina;
	

	/**
	 * Construtor padrão
	 */
	public TransferObject() {
		super();
	}
	
	public Object getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(Object conteudo) {
		this.conteudo = conteudo;
	}
	
	public abstract Integer getId();

	public abstract void setId(Integer id);
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		try {
			
			return PropertyUtils.describe(this).toString();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransferObject) {
			TransferObject teste = (TransferObject) obj;
			return obj.getClass().equals(this.getClass()) && teste.getId()==getId();
			
		}
		return false;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getQuantidadeRegistros() {
		return quantidadeRegistros;
	}

	public void setQuantidadeRegistros(int quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}

	public Long getPagina() {
		return pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

}
