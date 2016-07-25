package br.com.martins.vendas.relacionamento;

import org.json.JSONArray;

import br.com.martins.vendas.relacionamento.action.CadastrarJustificativaNaoAtendimentoClienteAction;
import br.com.martins.vendas.relacionamento.action.ClienteFavoritoAction;
import br.com.martins.vendas.relacionamento.action.DetalharClienteAction;
import br.com.martins.vendas.relacionamento.action.ListarClientesAction;
import br.com.martins.vendas.relacionamento.action.ListarItensGondolaClienteAction;
import br.com.martins.vendas.relacionamento.action.ListarJustificasNaoAtendimentoClienteAction;
import br.com.martins.vendas.relacionamento.action.ListarSituacaoClienteAction;
import br.com.martins.vendas.relacionamento.action.RelacionamentoClienteAction;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.core.DispatcherBase;
import com.brq.mobile.framework.log.Log;

public class Dispatcher extends DispatcherBase {

	private static final String TAG = Dispatcher.class.getName();

	public ActionResult execute(String action, JSONArray array, String arg2) {
		try {
			if (action == null) {
				return new ActionResult(Status.INVALID_ACTION);
			}

			if ("listarClientes".equalsIgnoreCase(action)) {
				return new ListarClientesAction().execute(array);
			} else if ("detalharCliente".equalsIgnoreCase(action)) {
				return new DetalharClienteAction().execute(array);
			} else if ("atualizarFavorito".equalsIgnoreCase(action)) {
				return new ClienteFavoritoAction().execute(array);
			} else if ("listarSituacaoCliente".equalsIgnoreCase(action)) {
				return new ListarSituacaoClienteAction().execute(array);
			} else if ("relacionamentoCliente".equalsIgnoreCase(action)) {
				return new RelacionamentoClienteAction().execute(array);
			} else if ("justificativaAtendimento".equalsIgnoreCase(action)) {
				return new ListarJustificasNaoAtendimentoClienteAction().execute(array);
			} else if ("cadastrarJustificativaAtendimento".equalsIgnoreCase(action)) {
				return new CadastrarJustificativaNaoAtendimentoClienteAction().execute(array);
			} else if ("listarItensGondolaCliente".equalsIgnoreCase(action)) {
				return new ListarItensGondolaClienteAction().execute(array);
			} else {
				return new ActionResult(Status.INVALID_ACTION);
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return new ActionResult(Status.JSON_EXCEPTION, e.getMessage());
		}
	}
}