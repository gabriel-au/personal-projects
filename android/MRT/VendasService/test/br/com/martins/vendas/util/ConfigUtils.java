package br.com.martins.vendas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.brq.mobile.framework.core.Config;
import com.brq.mobile.framework.core.ConfigAccess;

public class ConfigUtils {

	/**
	 * Método responsável por configurar o ambiente de testes para a realização
	 * dos mesmos.
	 */
	public static void config() {
		try {
			Properties properties = new Properties();
			InputStream inStream = new FileInputStream(new File("..\\VendasDesenvWindows\\resources\\config.properties"));
			properties.load(inStream);
			properties.setProperty("directory.sqlite.data", "/mnt/sdcard/database/");
			ConfigAccess.setConfig(new Config(properties));
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
