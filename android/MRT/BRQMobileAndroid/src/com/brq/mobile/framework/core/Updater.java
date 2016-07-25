package com.brq.mobile.framework.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import com.brq.mobile.framework.log.Log;

import dalvik.system.DexClassLoader;

public class Updater {

	/**
	 * Inter-Process Communication
	 */
	public static final int						IPC_ID				= 11223;

	private static final String					DEX_FILES			= ".*.jar$|";

	private static final String					SEPARATOR			= System.getProperty("path.separator");

	private static final String					APP_DEX_ALIAS		= "dex";

	private static DexClassLoader				DEX_CLASS_LOADER	= null;

	private static List<Map<String, String>>	PLUGINS				= null;

	private static boolean						UPDATED				= false;

	private Updater() {

	}

	/**
	 * 
	 * @param context
	 * @param jarPath
	 * @param xmlPath
	 * @param htmlPath
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static void execute(final Activity context, final String jarPath, final String xmlPath, final String htmlPath) throws IOException, XmlPullParserException {
		
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String htmlFullPath = sdcard.concat(htmlPath);

		// Carregar Classloader
		DEX_CLASS_LOADER = load(context, jarPath);

		// Carregar XML Plugins
		PLUGINS = readXML(xmlPath);

		// Descompactar zip contendo HTML
		String[] files = new File(htmlFullPath).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*.zip$");
			}
		});
		if (files != null && files.length > 0) {
			for (int count = 0; count < files.length; count++) {
				String fileName = files[count];
				decompressFiles(htmlFullPath.concat("/").concat(fileName), context.getFilesDir().getAbsolutePath());
			}
		}
		
	}

	/**
	 * 
	 * @return
	 */
	public static DexClassLoader getDexClassLoader() {
		return DEX_CLASS_LOADER;
	}

	/**
	 * 
	 * @return
	 */
	public static List<Map<String, String>> getPlugins() {
		return PLUGINS;
	}

	/**
	 * 
	 * @param context
	 *            android context
	 * 
	 * @param pluginsPath
	 *            caminho do xml do arquivo plugins.xml no sdcard
	 * 
	 * @param jarPath
	 *            caminho dos arquivos .jar ou .zip que contem classes.dex
	 * 
	 * @return DexClassLoaders
	 * 
	 * @see android.content.Context
	 */
	private static DexClassLoader load(Context context, String jarPath) throws IOException, XmlPullParserException {

		final File dexDir = context.getDir(APP_DEX_ALIAS, Context.MODE_PRIVATE);
		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String jarsDir = sdcard.concat(jarPath);

		// get jar/zip names
		String[] files = new File(jarsDir).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(DEX_FILES);
			}
		});

		if (files != null && files.length > 0) {
			StringBuilder newClassPath = new StringBuilder();
			for (int count = 0; count < files.length; count++) {
				newClassPath.append(jarsDir).append("/").append(files[count]);
				if (count < files.length - 1) {
					newClassPath.append(SEPARATOR);
				}
			}

			return new DexClassLoader(newClassPath.toString(), dexDir.getAbsolutePath(), null, context.getClassLoader());

		}
		return new DexClassLoader(context.getApplicationInfo().sourceDir, dexDir.getAbsolutePath(), null, context.getClassLoader());
	}

	/**
	 * Responsável pela leitura do arquivo Plugins XML
	 * 
	 * @param xmlPath
	 *            caminho do xml no sdcard
	 * 
	 * @throws IOException
	 *             caso ocorra erro na leitura do xml
	 * 
	 * @throws XmlPullParserException
	 *             caso ocorra erro no parse do xml para objeto
	 */
	private static List<Map<String, String>> readXML(String xmlPath) throws IOException, XmlPullParserException {

		final String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		final String pluginsFile = sdcard.concat(xmlPath).concat("/plugins.xml");

		List<Map<String, String>> plugins = new ArrayList<Map<String, String>>();

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

		factory.setValidating(false);

		XmlPullParser xml = factory.newPullParser();

		File file = new File(pluginsFile);

		InputStream raw = new BufferedInputStream(new FileInputStream(file));
		xml.setInput(raw, null);

		int eventType = xml.getEventType();

		while (eventType != XmlPullParser.END_DOCUMENT) {

			if (eventType == XmlPullParser.START_TAG) {

				String strNode = xml.getName();

				Map<String, String> plugin;

				if (strNode.equals("plugin")) {
					plugin = new HashMap<String, String>();
					plugin.put("name", xml.getAttributeValue(null, "name"));
					plugin.put("value", xml.getAttributeValue(null, "value"));
					plugins.add(plugin);
				}
			}

			eventType = xml.next();
		}

		return plugins;
	}

	/**
	 * @throws IOException
	 */
	private static void decompressFiles(String zipPath, String dirDst) throws IOException {

		final int BUFFER = 1024;

		File zipFile = new File(zipPath);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(zipFile);
		} catch (FileNotFoundException e) {
			Log.e("[Logger Error]", e.getLocalizedMessage());
		}

		BufferedOutputStream dest = null;
		ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			int countRead;
			byte data[] = new byte[BUFFER];
			/*
			 * Definindo diretório de arquivos
			 */
			File file = new File(dirDst, entry.getName());
			if (entry.isDirectory()) {
				file.mkdir();
			} else {
				file.createNewFile();
			}
			if (!entry.isDirectory()) {
				FileOutputStream fileOutStream = new FileOutputStream(file);
				dest = new BufferedOutputStream(fileOutStream, BUFFER);
				while ((countRead = zipInputStream.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, countRead);
				}
				dest.flush();
				dest.close();
			}
		}
		zipInputStream.close();
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isUpdated() {
		return UPDATED;
	}
}