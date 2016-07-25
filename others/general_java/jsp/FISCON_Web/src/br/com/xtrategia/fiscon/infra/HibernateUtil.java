package br.com.xtrategia.fiscon.infra;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Implementação do HibernateUtil
 * 
 * @author Gustavo Marcelo Costa
 */
public class HibernateUtil {

	/**
	 * Utilitário de <i>logging </i>
	 */
	static private Log log = LogFactory.getLog(HibernateUtil.class);

	/**
	 * Location of hibernate.cfg.xml file. NOTICE: Location should be on the
	 * classpath as Hibernate uses #resourceAsStream style lookup for its
	 * configuration file. That is place the config file in a Java package - the
	 * default location is the default Java package. <br>
	 * <br>
	 * Examples: <br>
	 * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
	 * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code>
	 */

	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

	/** Holds a single instance of Session */
	private static final ThreadLocal<Session> sessaoThreadLocal = new ThreadLocal<Session>();

	/**
	 * Segura uma Transacao [p/ um thread, detalhes consultar javadoc de
	 * ThreadLocal]
	 */
	private static final ThreadLocal<Transaction> transacaoThreadLocal = new ThreadLocal<Transaction>();

	/** The single instance of hibernate configuration */
	private static final AnnotationConfiguration cfg = new AnnotationConfiguration();

	/** The single instance of hibernate SessionFactory */
	private static SessionFactory sessionFactory;

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session session = sessaoThreadLocal.get();

		if (session == null) {
			if (sessionFactory == null) {
				try {

					loadCFG();

				} catch (Exception e) {

					log.fatal("Impossível carregar o Hibernate!!", e);
					e.printStackTrace();
					
					throw new Error("Impossível carregar o Hibernate!!");
				}
			}
			session = sessionFactory.openSession();
			sessaoThreadLocal.set(session);
		}

		return session;
	}

	public static void loadCFG() {
		cfg.configure(CONFIG_FILE_LOCATION);
		
		sessionFactory = cfg.buildSessionFactory();
		
	}

	/**
	 * Inicializa uma transação, associada com o <code>Thread</code>
	 * executando no momento
	 * 
	 * @return transacao
	 * @throws HibernateException
	 *             Caso ocorram erros durante a criação da transação
	 */
	static public Transaction currentTransaction() throws HibernateException {

		Transaction transacao = transacaoThreadLocal.get();

		if (transacao == null) {

			transacao = currentSession().beginTransaction();
			transacaoThreadLocal.set(transacao);
		}

		return transacao;
	}

	/**
	 * Close the single hibernate session instance.
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session session = (Session) sessaoThreadLocal.get();
		sessaoThreadLocal.set(null);

		if (session != null) {
			session.close();
		}
	}

	/**
	 * Encerra a transação associada com o <code>Thread</code> executando no
	 * momento, sinalizando para o SGBD que a transação terminou com sucesso e
	 * quaisquer alterações devem ser efetivadas na persistência
	 * 
	 * @throws HibernateException
	 *             Caso ocorram erros durante o commit()
	 */
	static public void commitTransaction() throws HibernateException {

		Transaction transacao = (Transaction) transacaoThreadLocal.get();

		if (transacao != null) {
			transacao.commit();
		}
		transacaoThreadLocal.set(null);
	}

	/**
	 * Encerra a transação associada com o <code>Thread</code> executando no
	 * momento, sinalizando para o SGBD que a transação <i>não </i> terminou com
	 * sucesso e quaisquer alterações <i>não</i> devem ser efetivadas na
	 * persistência
	 * 
	 * @throws HibernateException
	 *             Caso ocorram erros durante o rollback()
	 */
	static public void rollbackTransaction() throws HibernateException {

		Transaction transacao = (Transaction) transacaoThreadLocal.get();
		transacaoThreadLocal.set(null);

		if (transacao != null) {
			transacao.rollback();
		}
	}

	/**
	 * Default constructor.
	 */
	private HibernateUtil() {
	}

}