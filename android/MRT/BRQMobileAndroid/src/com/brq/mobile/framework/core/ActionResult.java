package com.brq.mobile.framework.core;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe responsavel pelo retorno dos dados de uma determinada chamada.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class ActionResult extends com.phonegap.api.PluginResult {

	/**
	 * Objeto Status do tipo <code>enum</code> contendo seus valores.
	 * 
	 * @author BRQ Mobile Team
	 * 
	 */
	public enum Status {
		NO_RESULT, //
		OK, //
		CLASS_NOT_FOUND_EXCEPTION, //
		ILLEGAL_ACCESS_EXCEPTION, //
		INSTANTIATION_EXCEPTION, //
		MALFORMED_URL_EXCEPTION, //
		IO_EXCEPTION, //
		INVALID_ACTION, //
		JSON_EXCEPTION, //
		ERROR
	}

	/**
	 * Metodo responsavel por converter o tipo de status de retorno.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @return objeto do tipo <code>com.phonegap.api.PluginResult.Status</code>.
	 */
	public static com.phonegap.api.PluginResult.Status statusConverter(Status status) {

		switch (status) {
		case NO_RESULT:
			return com.phonegap.api.PluginResult.Status.NO_RESULT;
		case OK:
			return com.phonegap.api.PluginResult.Status.OK;
		case CLASS_NOT_FOUND_EXCEPTION:
			return com.phonegap.api.PluginResult.Status.CLASS_NOT_FOUND_EXCEPTION;
		case ILLEGAL_ACCESS_EXCEPTION:
			return com.phonegap.api.PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION;
		case INSTANTIATION_EXCEPTION:
			return com.phonegap.api.PluginResult.Status.INSTANTIATION_EXCEPTION;
		case MALFORMED_URL_EXCEPTION:
			return com.phonegap.api.PluginResult.Status.MALFORMED_URL_EXCEPTION;
		case IO_EXCEPTION:
			return com.phonegap.api.PluginResult.Status.IO_EXCEPTION;
		case INVALID_ACTION:
			return com.phonegap.api.PluginResult.Status.INVALID_ACTION;
		case JSON_EXCEPTION:
			return com.phonegap.api.PluginResult.Status.JSON_EXCEPTION;
		case ERROR:
			return com.phonegap.api.PluginResult.Status.ERROR;
		}
		
		return null;
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @param message mensagem que sera exibida.
	 */
	public ActionResult(Status status, String message) {
		super(statusConverter(status), message);
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 */
	public ActionResult(Status status) {
		super(statusConverter(status));
	}

	/**
	 * Construtor
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @param b valor do tipo <code>boolean</code>.
	 */
	public ActionResult(Status status, boolean b) {
		super(statusConverter(status), b);
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @param f valor do tipo <code>float</code>.
	 */
	public ActionResult(Status status, float f) {
		super(statusConverter(status), f);
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @param i valor do tipo <code>int</code>.
	 */
	public ActionResult(Status status, int i) {
		super(statusConverter(status), i);
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @param message objeto do tipo <code>JSONArray</code> contendo dados e
	 *            mensagens.
	 */
	public ActionResult(Status status, JSONArray message) {
		super(statusConverter(status), message);
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que sera convertido.
	 * @param message objeto do tipo <code>JSONObject</code> contendo dados e
	 *            mensagens.
	 */
	public ActionResult(Status status, JSONObject message) {
		super(statusConverter(status), message);
	}

}
