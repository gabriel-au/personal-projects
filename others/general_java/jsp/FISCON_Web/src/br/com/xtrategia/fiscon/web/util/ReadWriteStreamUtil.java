package br.com.xtrategia.fiscon.web.util;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Encapsula lógica básica de gravar de um <code>InputStream</code>
 * em um <code>OutputStream</code>
 * 
 * @author Gustavo Marcelo Costa
 */
public class ReadWriteStreamUtil {

	/**
	 * Retira o conteúdo do <i>stream</i> in e joga no <i>stream</i> out
	 */
	public static void writeIn(InputStream in, OutputStream out) throws Exception {
		
		byte[] buffer = new byte[in.available()];
		int qtdeLida = 0;

		if(in.available() > 0) {
		
			while ((qtdeLida = in.read(buffer)) != -1) {
				out.write(buffer, 0, qtdeLida);
			}
		}
	}
}
