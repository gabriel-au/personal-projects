package com.brq.mobile.framework.log;

/**
 * Interface referente ao gerenciamento de log para todos os ambientes.
 * 
 * @author BRQ Mobile Team
 * 
 */
public interface LogManager {

	/**
	 * Método responsável por gerar log no nível DEBUG.
	 * 
	 * @param tag objeto do tipo <code>java.lang.String</code> referente à tag,
	 *            indicativo do ambiente operacional.
	 * @param msg objeto do tipo <code>java.lang.String</code> referente à
	 *            mensagem a ser gravada.
	 */
	public void LogDebug(String tag, String msg);

	public void LogDebug(String tag, String msg, Throwable throwable);

	/**
	 * Método responsável por gerar log no nível ERROR.
	 * 
	 * @param tag objeto do tipo <code>java.lang.String</code> referente à tag,
	 *            indicativo do ambiente operacional.
	 * @param msg objeto do tipo <code>java.lang.String</code> referente à
	 *            mensagem a ser gravada.
	 */
	public void LogError(String tag, String msg);

	public void LogError(String tag, String msg, Throwable throwable);

	/**
	 * Método responsável por gerar log no nível INFO.
	 * 
	 * @param tag objeto do tipo <code>java.lang.String</code> referente à tag,
	 *            indicativo do ambiente operacional.
	 * @param msg objeto do tipo <code>java.lang.String</code> referente à
	 *            mensagem a ser gravada.
	 */
	public void LogInfo(String tag, String msg);

	public void LogInfo(String tag, String msg, Throwable throwable);

	public void LogVerbose(String tag, String msg);

	public void LogVerbose(String tag, String msg, Throwable throwable);

	public void LogWarn(String tag, String msg);

	public void LogWarn(String tag, String msg, Throwable throwable);

}
