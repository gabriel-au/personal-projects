package com.brq.mobile.framework.log;

import org.apache.log4j.Logger;

/**
 * Classe responsável pelo gerenciamento de log no ambiente Windows.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class LogManagerWindowsImpl implements LogManager {

	@Override
	public void LogDebug(String tag, String msg) {
		Logger.getLogger(tag).debug(msg);
	}

	@Override
	public void LogDebug(String tag, String msg, Throwable throwable) {
		Logger.getLogger(tag).debug(msg, throwable);

	}

	@Override
	public void LogInfo(String tag, String msg) {
		Logger.getLogger(tag).info(msg);
	}

	@Override
	public void LogInfo(String tag, String msg, Throwable throwable) {
		Logger.getLogger(tag).info(msg, throwable);

	}

	@Override
	public void LogError(String tag, String msg) {
		Logger.getLogger(tag).error(msg);
	}

	@Override
	public void LogError(String tag, String msg, Throwable throwable) {
		Logger.getLogger(tag).error(msg, throwable);
	}

	@Override
	public void LogWarn(String tag, String msg) {
		Logger.getLogger(tag).warn(msg);

	}

	@Override
	public void LogWarn(String tag, String msg, Throwable throwable) {
		Logger.getLogger(tag).warn(msg, throwable);

	}

	@Override
	public void LogVerbose(String tag, String msg) {
		Logger.getLogger(tag).trace(msg);

	}

	@Override
	public void LogVerbose(String tag, String msg, Throwable throwable) {
		Logger.getLogger(tag).trace(msg, throwable);

	}

}
