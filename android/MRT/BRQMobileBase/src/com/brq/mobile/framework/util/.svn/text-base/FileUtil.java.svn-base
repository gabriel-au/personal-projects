package com.brq.mobile.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import android.content.Context;

import com.brq.mobile.framework.dao.DatabaseHelper;
import com.brq.mobile.framework.log.Log;

/**
 * Classe utilit�ria relacionada ao tratamento de arquivos.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class FileUtil {

	private static final String TAG = FileUtil.class.getName();

	/**
	 * M�todo respons�vel por copiar o banco de dados da aplica��o para o dispositivo.
	 * 
	 * Uso: FileUtil.copyFile(this, "databases/poc.sqlite", "poc.sqlite");
	 * 
	 * @param ctx objeto do tipo <code>android.content.Context</code>.
	 * @param source objeto do tipo <code>java.lang.String</code>.
	 * @param name objeto do tipo <code>java.lang.String</code>.
	 * @throws IOException objeto do tipo <code>java.io.IOException</code>.
	 */
	public static void copyDatabase(Context ctx, String source, String name) throws IOException {
		File f = ctx.getDatabasePath(name);

		if (!f.exists()) {
			DatabaseHelper helper = new DatabaseHelper(ctx, null, null, 3);
			helper.close();
			ctx.deleteDatabase(name);
		}

		InputStream is = ctx.getAssets().open(source);
		OutputStream os = new FileOutputStream(f, true);

		final int buffer_size = 1024 * 1024;

		byte[] bytes = new byte[buffer_size];
		for (;;) {
			int count = is.read(bytes, 0, buffer_size);
			if (count == -1)
				break;
			os.write(bytes, 0, count);
		}

		is.close();
		os.close();
	}

	/**
	 * 
	 * @param ctx
	 * @param sdcard 
	 * @param String
	 */
	public static void copyDatabaseFromSD(Context ctx, File sdcard, String complSdCard, String name) throws IOException {		
		File[] files = new File(sdcard.getAbsolutePath().concat(complSdCard)).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				return name.matches(".*\\.sqlite$");
			}
		});
		
		if (files != null && files.length > 0) {

			File file = files[0];
			try {
	
				File f = ctx.getDatabasePath(name);
				if (!f.exists()) {
					DatabaseHelper helper = new DatabaseHelper(ctx, null, null, 3);
					helper.close();
				}
				ctx.deleteDatabase(name);
				
				InputStream is = new FileInputStream(file);
				OutputStream os = new FileOutputStream(f, true);
	
				final int buffer_size = 1024 * 1024;
	
				byte[] bytes = new byte[buffer_size];
				for (;;) {
					int count = is.read(bytes, 0, buffer_size);
					if (count == -1)
						break;
					os.write(bytes, 0, count);
				}
	
				is.close();
				os.close();
				
				file.length();
				f.length();
			} catch (IOException e) {
				throw new IOException(e);
			}
			file.delete();
		}
	}
	
	/**
	 * Metodo para gravacao de arquivo
	 * 
	 * @param textoAppend
	 * @param arquivo
	 * @param append
	 * @throws Exception
	 */
	public static void gravaArquivo(String conteudo, String arquivo, boolean append) throws Exception {
		FileOutputStream f = null;
		PrintStream p = null;
		try {
			f = new FileOutputStream(arquivo, append);
			p = new PrintStream(f);
			p.println(conteudo);
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage());
			throw new Exception("Erro na gravacao do arquivo " + arquivo);
		} finally {
			if (p != null) {
				p.close();
			}
			if (f != null) {
				f.close();
			}
		}
	}

}
