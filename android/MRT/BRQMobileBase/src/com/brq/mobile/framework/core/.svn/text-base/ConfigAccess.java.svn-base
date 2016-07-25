package com.brq.mobile.framework.core;


/**
 * Classe responsavel por permitir acesso aos dados de configuracao.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class ConfigAccess {

	private static Config config;

	/**
	 * Metodo responsavel por retornar o valor do atributo config.
	 * 
	 * @return objeto do tipo <code>com.brq.mobile.framework.core.Config</code>.
	 */
	public static Config getConfig() {
		if (config == null) {
			throw new RuntimeException("Config not initialized");
		}
		return config;
	}

	/**
	 * Metodo responsavel por efetivar o valor do atributo config.
	 * 
	 * @param config objeto do tipo
	 *            <code>com.brq.mobile.framework.core.Config</code>.
	 */
	public static void setConfig(Config config) {
		ConfigAccess.config = config;
	}

}