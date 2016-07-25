package com.brq.mobile.framework.log;

import com.brq.mobile.framework.util.EnviromentIdentifier;

/**
 * Classe respons�vel pela grava��o do log, independente do ambiente
 * operacional.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class Log {

	private static LogManager logManager;

	/**
	 * Metodo est�tico com a responsabilidade de identificar o ambiente
	 * operacional e instanciar a classe correta para que o sistema possa gravar
	 * mensagens de log.
	 */
	static {
		if (EnviromentIdentifier.isAndroid()) {
			logManager = new LogManagerAndroidImpl();
		} else if (EnviromentIdentifier.isWindows() || EnviromentIdentifier.isMacOsx()) {
			logManager = new LogManagerWindowsImpl();

		} else {
			throw new RuntimeException();
		}
	}

	public static void d(String tag, String msg) {
		logManager.LogDebug(tag, msg);
	}

	public static void d(String tag, String msg, Throwable throwable) {
		logManager.LogDebug(tag, msg, throwable);
	}

	public static void e(String tag, String msg) {
		logManager.LogError(tag, msg);
	}

	public static void e(String tag, String msg, Throwable throwable) {
		logManager.LogError(tag, msg, throwable);
	}

	public static void i(String tag, String msg) {
		logManager.LogInfo(tag, msg);
	}

	public static void i(String tag, String msg, Throwable throwable) {
		logManager.LogInfo(tag, msg, throwable);
	}

	public static void v(String tag, String msg) {
		logManager.LogVerbose(tag, msg);
	}

	public static void v(String tag, String msg, Throwable throwable) {
		logManager.LogVerbose(tag, msg, throwable);
	}

	public static void w(String tag, String msg) {
		logManager.LogWarn(tag, msg);
	}

	public static void w(String tag, String msg, Throwable throwable) {
		logManager.LogWarn(tag, msg, throwable);
	}
}
