package br.com.xtrategia.fiscon.infra;


import org.hibernate.Session;

import br.com.xtrategia.fiscon.FISCONException;


/**
 * Interface dos componentes de negócio (executores de regras de negócio), de 
 * acordo com o padrão <i>Command</i> (Erich Gamma, et. al - <i>Design Patterns: 
 * Elements of Reusable Object-Oriented Software </i>)
 *
 * 
 */
public interface Command {
	
	/**
	 * Executa operação de negócio. 
	 * @param transferObject Objeto contendo os dados necessários para a execução de uma
	 * dada instância de <code>Command</code>
	 * @return <code>TransferObject</code> Resultado do processamento.
	 * @throws Portal Mercosul EducacionalException Caso ocorra infrigimento de regras de negócio
	 * ou erros inesperados de infra-estrutura 
	 */
	public TransferObject execute(TransferObject transferObject) throws FISCONException;
	
	/**
	 * Define o objeto <code>Session<code> do <i>Hibernate</i>. Como 
	 * � imposs�vel refor�ar sem�nticas para construtores via interface,
	 * foi decidido criar este Método para suprir as subclasses de <code>Command</code>
	 * para que os mesmos possam interagir com o <i>Hibernate</i>
	 * @param sessao <code>Session<code> do <i>Hibernate</i>
	 */
	void setSession(Session sessao);
}
