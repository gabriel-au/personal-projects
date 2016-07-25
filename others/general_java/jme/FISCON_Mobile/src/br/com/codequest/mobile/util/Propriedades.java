package br.com.codequest.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Arquivo para manipular os arquivos properties
 * 
 * @author Gustavo Marcelo
 *
 */
public class Propriedades {
	
	/** | Dados do Aplicativo | */
	public static String VERSAO_APLICATIVO;
    
	public static String URL_SERVER;
    
	
	private static Hashtable propTable;

	/**
	 * carrega as propriedades do arquivo
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static void loadProperties(String fileName)
			throws IOException {
		propTable = new Hashtable();
		
		Propriedades prop = new Propriedades();

		Class classObject = prop.getClass();
		InputStream stream = classObject.getResourceAsStream(fileName);
		InputStreamReader reader = new InputStreamReader(stream);

		StringBuffer sBuf = new StringBuffer();
		char[] buff = new char[1024];

		/* [2] */
		int pos = reader.read(buff, 0, 1024);
		while (pos != -1) {
			sBuf.append(buff, 0, pos);
			pos = reader.read(buff, 0, 1024);
		}

		String[] lines = StringUtils.split(sBuf.toString(), '\n', 0);
		for (int i = 0; i < lines.length; i++) {
			String[] kv = StringUtils
					.split(StringUtils.separarNomePropriedade(lines[i]), '=', 2);
			if (kv.length == 1) {
				setProperty(kv[0], "");
			}
			if (kv.length == 2) {
				setProperty(kv[0].trim(), kv[1].trim());
			}
		}
		loadParameters();
	}
	
	public static void loadParameters() {
		VERSAO_APLICATIVO = getProperty("VERSAO_APLICATIVO");
	    
		URL_SERVER = getProperty("URL_SERVER");
	}

	private static void setProperty(String key, String val) {
		propTable.put(key, val);
	}

	public static String getProperty(String key) {
		return (String) propTable.get(key);
	}

	public static int getPropertyCount() {
		return propTable.size();
	}

	public static String[] getPropertyNames() {
		String[] result = new String[propTable.size()];
		int c = 0;
		for (Enumeration e = propTable.keys(); e.hasMoreElements();) {
			result[c] = (String) e.nextElement();
			c++;
		}
		
		
		return result;
	}

}
