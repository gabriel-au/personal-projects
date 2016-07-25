package com.brq.mobile.framework.dao;

import com.brq.mobile.framework.util.EnviromentIdentifier;

/**
 * Classe responsável por criar as instâncias de objetos que permitem o
 * acesso ao banco de dados, independente do ambiente operacional que se
 * encontra.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class DatabaseFactory {

	private static Database database;
	/**
	 * M�todo respons�vel por criar as inst�ncias de objetos que permitem
	 * o acesso ao banco de dados, independente do ambiente operacional que se
	 * encontra.
	 * 
	 * @return objeto do tipo <code>com.brq.mobile.framework.dao.Database</code>
	 *         .
	 */
	public static Database getInstance() {
		if (database != null) {
			return database;
		}
		if (EnviromentIdentifier.isAndroid()) {
			database = new DatabaseAndroidImpl();
		} else if (EnviromentIdentifier.isWindows() || EnviromentIdentifier.isMacOsx()) {
			database = new DatabaseWindowsImpl();
		} else {
			throw new RuntimeException("Environment NOT Identified");
		}
		return database;
	}

}