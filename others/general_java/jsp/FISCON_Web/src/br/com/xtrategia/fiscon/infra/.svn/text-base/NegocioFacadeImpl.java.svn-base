package br.com.xtrategia.fiscon.infra;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.SystemException;

/**
 * Implementação <i>POJO</i> (Plain Ordinary Java Object) de
 * <code>CodeQuestFacade</code>
 * 
 * 
 */
public class NegocioFacadeImpl implements NegocioFacade {

	/**
	 * Utilitário de <i>logging</i>
	 */
	static private Log log = LogFactory.getLog(NegocioFacadeImpl.class);

	/**
	 * Construtor padrão
	 */
	public NegocioFacadeImpl() {
		super();
	}

	/**
	 * executa o command com a opção de autocommit
	 */
	public TransferObject execute(TransferObject pojo, String alias,
			boolean autocommit) throws FISCONException {

		return (TransferObject) doExecute(pojo, alias, autocommit);
	}

	/**
	 * executa o command, este Método executa com autocommit=true
	 */
	public TransferObject execute(TransferObject pojo, String alias)
			throws FISCONException {

		return (TransferObject) doExecute(pojo, alias, true);
	}

	/*
	 * Método auxiliar p/ execução dos Commands
	 */
	private Object doExecute(TransferObject TO, String alias, boolean autocommit)
			throws FISCONException {

		Object retorno = null;
		log.debug("Preparando para executar Command " + alias);

		try {
			Session sessao = HibernateUtil.currentSession();
			Command comando = CommandFactory.getInstancia().getCommand(alias);

			if (comando == null) {

				throw new SystemException("Command desconhecido: " + alias);
			}

			// garantindo acesso a somente um thread :P
			synchronized (comando) {

				Transaction transacao = HibernateUtil.currentTransaction();

				log.debug("Transação inicializada: " + transacao);
				log.debug("Executando Command: " + alias);

				// processa o Command
				comando.setSession(sessao);
				retorno = comando.execute(TO);

				// foi retirado para deixar o commit no nível de negocio
				if (autocommit) {
					HibernateUtil.commitTransaction();
					HibernateUtil.closeSession();
				}

				log.debug("Command " + alias + " executado com sucesso");
			}

		} catch (Exception e) {
			throw trataExcecao(e, alias);
		}

		return retorno;
	}

	/**
	 * faz limpeza dos recursos que estavam sendo utilizados
	 * e joga uma exceção para ser tratada por camadas superiores
	 */
	private FISCONException trataExcecao(Exception ex, String alias) {

		try {

			HibernateUtil.rollbackTransaction();
			HibernateUtil.closeSession();

		} catch (HibernateException e) {

			log.error(e);
		}

		if (ex instanceof FISCONException) {

			if (ex instanceof SystemException) {

				log.warn("Exceção de Negócio ocorreu ao executar Command: "
						+ alias, ex);

			} else {

				log.error("Exceção de Sistema ocorreu ao executar Command: "
						+ alias, ex);
			}

			return (FISCONException) ex;
		}

		return new FISCONException(ex);
	}
}