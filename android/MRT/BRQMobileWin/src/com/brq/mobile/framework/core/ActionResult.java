package com.brq.mobile.framework.core;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Classe respons�vel por representar o retorno da execu��o de uma chamada �
 * camada Action.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class ActionResult {

	private final int status;
	private final String message;

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 */
	public ActionResult(Status status) {
		this.status = status.ordinal();
		this.message = "'" + ActionResult.StatusMessages[this.status] + "'";
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 * @param message mensagem que ser� exibida.
	 */
	public ActionResult(Status status, String message) {
		this.status = status.ordinal();
		this.message = JSONObject.quote(message);
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 * @param message objeto do tipo <code>JSONArray</code> contendo dados e
	 *            mensagens.
	 */
	public ActionResult(Status status, JSONArray message) {
		this.status = status.ordinal();
		this.message = message.toString();
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 * @param message objeto do tipo <code>JSONObject</code> contendo dados e
	 *            mensagens.
	 */
	public ActionResult(Status status, JSONObject message) {
		this.status = status.ordinal();
		this.message = message.toString();
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 * @param i valor do tipo <code>int</code>.
	 */
	public ActionResult(Status status, int i) {
		this.status = status.ordinal();
		this.message = "" + i;
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 * @param f valor do tipo <code>float</code>.
	 */
	public ActionResult(Status status, float f) {
		this.status = status.ordinal();
		this.message = "" + f;
	}

	/**
	 * Construtor.
	 * 
	 * @param status objeto <code>Status</code> que ser� convertido.
	 * @param b valor do tipo <code>boolean</code>.
	 */
	public ActionResult(Status status, boolean b) {
		this.status = status.ordinal();
		this.message = "" + b;
	}

	/**
	 * Objeto do tipo <code>String[]</code> que cont�m as mensagens referentes
	 * ao Status.
	 */
	public static String[] StatusMessages = new String[] {
			//
			"No result", //
			"OK", //
			"Class not found", //
			"Illegal access", //
			"Instantiation error", //
			"Malformed url", //
			"IO error", //
			"Invalid action", //
			"JSON error", //
			"Error" };

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
	 * M�todo respons�vel por retornar o Status atual.
	 * 
	 * @return objeto do tipo <code>int</code>.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * M�todo respons�vel por retornar a mensagem.
	 * 
	 * @return objeto do tipo <code>String</code>.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * M�todo respons�vel por gerar o conte�do do objeto em um objeto do tipo
	 * <code>String</code>, com seu valor em nota��o JSON.
	 * 
	 * @return objeto do tipo <code>String</code>.
	 */
	public String getJSONString() {
		return "{status:" + this.status + ",message:" + this.message + "}";
	}

}