package br.com.xtrategia.fiscon.infra;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;


/**
 * Classe fábrica de <code>Command<code>s, de acordo com o padrão 
 * <i>Factory</i> e <i>Singleton</i> (Erich Gamma, et. al - <i>Design Patterns: 
 * Elements of Reusable Object-Oriented Software </i>)
 *
 *  
 */
public class CommandFactory {
	
	static private Log logging = LogFactory.getLog(CommandFactory.class);
	
	/**
	 * 
	 * Classe que implementa o <i>Cache</i> de commands
	 * instanciados (retirados do .xml de configuração)
	 *
	 */
	protected class Cache {

		private Map<String, Object> cache;
		
		public Cache() throws Exception {
			
			
			cache = Collections.synchronizedMap(new HashMap<String,  Object>());
			XStream xstream = new XStream();
			
			xstream.processAnnotations(Configuracao.class);
			Configuracao con = (Configuracao) xstream.fromXML(this.getClass()
					.getResourceAsStream("/configuracao.xml"));
			
			//if(logging.isDebugEnabled()){
				for(ConfiguracaoParametro p: con.getParametros()){
					logging.debug(p.getAlias()+"-"+p.getClasse());
				}
			//}
			cache.putAll(instancia(con.getParametros()));			
		}
		
		/*
		 * Instancia os Commands referenciados no properties
		 */
		private Map<String,  Object> instancia(List<ConfiguracaoParametro> lista) throws Exception {

			Map<String,  Object> retorno = new HashMap<String, Object>();
			
			for(ConfiguracaoParametro p: lista){
				logging.info(p.getAlias()+"-"+p.getClasse());
				
				String classeCommand = p.getClasse();
				String alias = p.getAlias();
				
				Object instanciaCommand = Class.forName((String)classeCommand.trim()).newInstance();
				retorno.put(alias.trim(),instanciaCommand);
			}
			
			return retorno;
		}

		/**
		 * @param alias
		 * @return
		 */
		public Command get(String alias) {
			
			return (Command) cache.get(alias);
		}		
	}
	
	/**
	 * Utilitário de <i>logging </i>
	 */
	static private Log log;

	/**
	 * Instância <i>Singleton </i>
	 */
	static private CommandFactory instancia;

	//inicializa instância singleton
	static {

		try {
			log = LogFactory.getLog(CommandFactory.class);
			log.info("Inicializando fábrica de Commands");

			CommandFactory.instancia = new CommandFactory();

		} catch (Exception e) {

			log.fatal("Erro ao inicializar fábrica de Commands", e);
			throw new InstantiationError("Não carregou mapeamento de commands!!");
		}
	}
	/** Instancia de cache para armazenamento dos <code>Command</code> s */
	private Cache commandCache;

	/**
	 * Construtor padrão
	 * @throws IOException
	 */
	private CommandFactory() throws Exception {
		
		this.commandCache = new CommandFactory.Cache();
	}

	/**
	 * Retorna instância <i>Singleton </i>
	 * 
	 * @return instância <i>Singleton </i>
	 */
	static public CommandFactory getInstancia() {
		return instancia;
	}

	/**
	 * Cria e retorna um <code>Command</code> de acordo com o <i>alias </i> passado como
	 * argumento. Recupera a instância da classe <code>Command</code> a partir do <code>Cache</code>.
	 * 
	 * @param alias
	 *            Apelido do <code>Command</code>
	 * @return <code>Command</code>
	 */
	public Command getCommand(String alias) {
		log.debug("Command requisitado: " + alias);

		if (alias != null && !alias.equals("")) {
			try {
				
				Command retorno = commandCache.get(alias);
				return retorno;
				
			} catch (Exception e) {
				log.error("Erro ao criar Command " + alias, e);
				throw new RuntimeException(e);
			}
		}
		log.error("Command inválido: " + alias);
		throw new IllegalArgumentException("Nome de Command Inválido!");
	}
}