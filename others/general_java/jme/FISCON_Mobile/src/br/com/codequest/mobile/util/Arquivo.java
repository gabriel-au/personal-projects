package br.com.codequest.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
/**
 * Classe para manipular dados em um arquivo texto
 * @author gustavomarcelo
 *
 */
public class Arquivo {

	/**
	 * Gravar dados
	 * @param path
	 * @param name
	 * @param string
	 */
	public static void saveFile(String fileName, String texto) {
		saveFile(fileName, texto.getBytes());
	}
	
	/**
	 * Gravar dados
	 * @param path
	 * @param name
	 * @param string
	 */
	public static void saveFile(String fileName, byte data[]) {
		try {
			FileConnection fconn = (FileConnection) Connector.open(fileName,
					Connector.READ_WRITE);
			if (!fconn.exists()) {
				fconn.create();
			}
			OutputStream ops = fconn.openOutputStream();
			ops.write(data);
			ops.close();
			fconn.close();
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		} catch (SecurityException se) {
			System.out.println("Exceção de segurança:" + se.getMessage());
		}
	}

	/**
	 * Carregar os arquivos
	 */
	public static String loadFile(String fileName) {
		String retorno = "";
		try {
			FileConnection fc = (FileConnection) Connector.open(fileName);
			if (!fc.exists()) {
				throw new IOException("Arquivo não existe");
			}
			InputStream is = fc.openInputStream();
			byte b[] = new byte[1024];
			int length = is.read(b, 0, 1024);
			retorno = new String(b, 0, length);
			is.close();
			fc.close();
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
			return null;
		} catch (SecurityException se) {
			System.out.println("Exceção de segurança:" + se.getMessage());
			return null;
		}
		return retorno;

	}

}
