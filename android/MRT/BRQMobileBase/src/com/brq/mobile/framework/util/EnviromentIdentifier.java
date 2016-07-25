package com.brq.mobile.framework.util;


/**
 * Classe respons�vel por identificar em que ambiente o sistema principal est� funcionando.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class EnviromentIdentifier {

	private static boolean		macosx	= false;
	private static boolean		windows	= false;
	private static boolean		android	= false;

	/*
	 * private static final String	TAG		= EnviromentIdentifier.class.getName();
	 */

	static {

		try {
			if (System.getProperty("os.name").matches(".*(?i)Mac OS X.*")) {
				macosx = true;
			}

			if (System.getProperty("os.name").matches(".*(?i)windows.*")) {
				windows = true;
			}

			Class.forName("android.os.Build");

			if (System.getProperty("java.runtime.name").matches("(?i)Android Runtime") || System.getProperty("java.vm.name").matches("(?i)Dalvik")
					|| System.getProperty("java.specification.name").matches("(?i)Dalvik Core Library") || System.getProperty("java.vendor").matches("(?i)The Android Project")
					|| System.getProperty("java.vm.specification.name").matches("(?i)Dalvik Virtual Machine Specification")) {

				android = true;
			}

		} catch (ClassNotFoundException ex) {

		} finally {

		}

	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo windows.
	 * 
	 * @return <code>true</code> caso o ambiente operacional seja Windows, e <code>false</code> caso
	 *         n�o seja.
	 */
	public static boolean isWindows() {
		return windows;

	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo IOS.
	 * 
	 * @return <code>true</code> caso o ambiente operacional seja IOS, e <code>false</code> caso
	 *         n�o seja.
	 */
	public static boolean isMacOsx() {
		return macosx;
	}

	/**
	 * M�todo respons�vel por retornar o valor do atributo android.
	 * 
	 * @return <code>true</code> caso o ambiente operacional seja Android, e <code>false</code> caso
	 *         n�o seja.
	 */
	public static boolean isAndroid() {
		return android;
	}

}
