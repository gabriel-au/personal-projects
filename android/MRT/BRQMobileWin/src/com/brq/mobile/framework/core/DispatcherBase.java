package com.brq.mobile.framework.core;

import org.json.JSONArray;

/**
 * Classe abstrata respons�vel pela camada Dispatcher.
 * 
 * @author BRQ Mobile Team
 */
public abstract class DispatcherBase {

	/**
	 * Metodo responsavel pela execucao da acao chamada.
	 * 
	 * @param action
	 *            objeto do tipo <code>String</code> com a descricao da acao
	 *            chamada.
	 * @param args
	 *            objeto do tipo <code>JSONArray</code> com os argumentos
	 *            necessarios para a execucao da acao chamada.
	 * @param callbackId
	 *            objeto do tipo <code>String</code> que indica o ID de retorno.
	 * @return objeto do tipo <code>ActionResult</code> com o resultado da
	 *         chamada a acao.
	 */
	public abstract ActionResult execute(String action, JSONArray args, String callbackId);

}
