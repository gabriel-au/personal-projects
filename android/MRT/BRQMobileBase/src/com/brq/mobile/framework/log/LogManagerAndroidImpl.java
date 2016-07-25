package com.brq.mobile.framework.log;

/**
 * Classe responsavel pelo gerenciamento de log no ambiente Android.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class LogManagerAndroidImpl implements LogManager {

	@Override
	public void LogDebug(String tag, String msg) {
		android.util.Log.d(tag, msg);
	}

	@Override
	public void LogDebug(String tag, String msg, Throwable throwable) {
		android.util.Log.d(tag, msg, throwable);

	}

	@Override
	public void LogInfo(String tag, String msg) {
		android.util.Log.i(tag, msg);
	}

	@Override
	public void LogInfo(String tag, String msg, Throwable throwable) {
		android.util.Log.i(tag, msg, throwable);
	}

	@Override
	public void LogError(String tag, String msg) {
		android.util.Log.e(tag, msg);
	}

	@Override
	public void LogError(String tag, String msg, Throwable throwable) {
		android.util.Log.e(tag, msg, throwable);
	}

	@Override
	public void LogWarn(String tag, String msg) {
		android.util.Log.w(tag, msg);
	}

	@Override
	public void LogWarn(String tag, String msg, Throwable throwable) {
		android.util.Log.w(tag, msg, throwable);

	}

	@Override
	public void LogVerbose(String tag, String msg) {
		android.util.Log.v(tag, msg);

	}

	@Override
	public void LogVerbose(String tag, String msg, Throwable throwable) {
		android.util.Log.v(tag, msg, throwable);

	}
}
