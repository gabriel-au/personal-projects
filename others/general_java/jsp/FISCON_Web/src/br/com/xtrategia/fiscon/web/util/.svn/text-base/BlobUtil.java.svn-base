package br.com.xtrategia.fiscon.web.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;

/**
 * Utilitário para manipular BLOBs na persistência
 * 
 * @author Gustavo Marcelo Costa
 */
public class BlobUtil extends ReadWriteStreamUtil {

	/**
	 * Converte um File em um array de bytes
	 * @param inFile
	 * @return
	 * @throws IOException
	 */
	public static byte[] fileToBytes(File inFile) throws IOException {

		byte[] fileBlob;

		java.io.FileInputStream fis;
		int fileLength = 0;

		fis = new java.io.FileInputStream(inFile);
		fileLength = (int) inFile.length() + 1;
		fileBlob = new byte[fileLength];
		fis.read(fileBlob);
		fis.close();

		return fileBlob;

	}

	/**
	 * Retorna o conteúdo do Blob como array de bytes
	 * 
	 * @param campo
	 *            Blob com o conteúdo a ser extraído
	 * @return array de bytes com o conteúdo do blob
	 * @throws Exception
	 */
	public static byte[] getConteudoAsByteArray(Blob campo) throws Exception {

		ByteArrayOutputStream streamBuffer = new ByteArrayOutputStream(
				(int) campo.length());

		BufferedInputStream streamLeitura = new BufferedInputStream(campo
				.getBinaryStream());

		writeIn(streamLeitura, streamBuffer);

		streamLeitura.close();

		return streamBuffer.toByteArray();
	}

	/**
	 * Retorna o conteúdo do Blob como String
	 * 
	 * @param senhaBlob
	 *            Blob com o conteúdo a ser extraído
	 * @return array de bytes com o conteúdo do blob
	 * @throws Exception
	 */
	public static String getConteudoAsString(Blob campo) throws Exception {

		ByteArrayOutputStream streamBuffer = new ByteArrayOutputStream(
				(int) campo.length());

		BufferedInputStream streamLeitura = new BufferedInputStream(campo
				.getBinaryStream());

		writeIn(streamLeitura, streamBuffer);

		streamLeitura.close();

		return new String(streamBuffer.toByteArray());
	}

}
