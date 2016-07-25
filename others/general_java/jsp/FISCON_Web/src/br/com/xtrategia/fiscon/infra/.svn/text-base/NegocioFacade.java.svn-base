package br.com.xtrategia.fiscon.infra;

import br.com.xtrategia.fiscon.FISCONException;



/**
 * Interface fachada para acesso à camada de negócio, de acordo com o padrão
 * <i>Façade</i> (Erich Gamma, et. al - <i>Design Patterns: 
 * Elements of Reusable Object-Oriented Software </i>)
 *
 *  
 */
public interface NegocioFacade {
	
	/**
	 * Executa uma operação de negócio , com autocommit sendo <i>true</i>
	 * @param TO Objeto com os dados necessários para realizar a operação
	 * @param alias <i>Alias</i> do objeto <code>Command</code> responsável
	 * pela execução da operação
	 * @return <code>TransferObject</code> Resultado do processamento
	 * @throws FISCONException Caso ocorra infrigimento de regras de negócio
	 * ou erros inesperados de infra-estrutura  
	 */
	public TransferObject execute(TransferObject pojo, String alias) 
		throws FISCONException;
	
	/**
	 * Executa uma operação de negócio 
	 * @param TO Objeto com os dados necessários para realizar a operação
	 * @param alias <i>Alias</i> do objeto <code>Command</code> responsável
	 * pela execução da operação
	 * @return <code>TransferObject</code> Resultado do processamento
	 * @throws FISCONException Caso ocorra infrigimento de regras de negócio
	 * ou erros inesperados de infra-estrutura  
	 */
	public TransferObject execute(TransferObject pojo, String alias, boolean autocomit) 
		throws FISCONException;
	

}
