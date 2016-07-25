package com.brq.mobile.framework.core;

import java.util.Properties;

import android.content.Context;

import com.brq.mobile.framework.log.Log;

/**
 * Classe respons�vel pelos dados relacionados � configura��es dos sistema.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class Config {

	private static final String TAG = Config.class.getName();
	public static final String BASE_DIR = "directory.base";
	public static final String DATABBASE_DIR = "directory.sqlite.data";
	public static final String DATABBASE_NAME = "directory.sqlite.name";
	private Properties properties = null;

	/**
	 * Construtor.
	 * 
	 * @param properties objeto do tipo <code>java.util.Properties</code>
	 *            referente �s propriedades de uma determinada configura��o.
	 */
	public Config(Properties properties) {
		if (properties == null) {
			throw new IllegalArgumentException("properties cannot be null");
		}
		this.properties = properties;
	}

	public static void startAndroid(Context context) {
		try {

			Properties properties = new Properties();
			properties.load(context.getAssets().open("config.properties"));
			ConfigAccess.setConfig(new Config(properties));

		} catch (Exception e) {
			Log.e(TAG, "Arquivo config.properties n�o encontrado", e);

		}
	}

	/**
	 * M�todo respons�vel por retornar o valor do diret�rio base.
	 * 
	 * @return objeto do tipo <code>java.lang.String</code> referente ao
	 *         diret�rio base.
	 */
	public String diretoryBase() {
		return properties.getProperty(BASE_DIR);
	}

	/**
	 * M�todo respons�vel por retornar o valor do diret�rio base do banco de
	 * dados.
	 * 
	 * @return objeto do tipo <code>java.lang.String</code> referente ao
	 *         diret�rio base do banco de dados.
	 */
	public String diretoryData() {
		return properties.getProperty(DATABBASE_DIR);
	}

	/**
	 * M�todo respons�vel por retornar o nome do banco de dados.
	 * 
	 * @return objeto do tipo <code>java.lang.String</code> referente ao nome do
	 *         banco de dados.
	 */
	public String dataBaseName() {
		return properties.getProperty(DATABBASE_NAME);
	}

	/**
	 * M�todo respons�vel por retornar as propriedades de uma determinada
	 * configura��o.
	 * 
	 * @return objeto do tipo <code>java.util.Properties</code> referente �s
	 *         propriedades de uma determinada configura��o.
	 */
	public Properties getProperties() {
		return properties;
	}

}