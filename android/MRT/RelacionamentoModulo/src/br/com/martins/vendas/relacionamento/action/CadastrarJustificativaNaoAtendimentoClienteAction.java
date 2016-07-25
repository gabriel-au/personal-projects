package br.com.martins.vendas.relacionamento.action;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.JustificativaNaoAtendimentoService;
import br.com.martins.vendas.vo.JustificativaNaoAtendimento;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;

public class CadastrarJustificativaNaoAtendimentoClienteAction {

	private static final String TAG = CadastrarJustificativaNaoAtendimentoClienteAction.class.getName();

	public ActionResult execute(JSONArray args) throws Exception {
		try {

			boolean sucesso = false;

			JustificativaNaoAtendimento justificativaNaoAtendimento = preencherJustificativa(args);

			JustificativaNaoAtendimento justificativaNaoAtendimentoCliente = JustificativaNaoAtendimentoService.obterUltimaJustificativaCliente(justificativaNaoAtendimento.codigoCliente);

			if (justificativaNaoAtendimentoCliente != null) {
				sucesso = JustificativaNaoAtendimentoService.atualizarJustificativaCliente(justificativaNaoAtendimento);
			} else {
				sucesso = JustificativaNaoAtendimentoService.cadastrarJustificativaCliente(justificativaNaoAtendimento);
			}

			if (sucesso) {
				return new ActionResult(Status.OK);
			} else {
				return new ActionResult(Status.ERROR, "Não foi possível atualizar justificativa de não atendimento do cliente.");
			}

		} catch (Exception e) {
			Log.e(TAG, "Erro ao atualiza justificativa de não atendimento do cliente.", e);
			return new ActionResult(Status.ERROR, e.getMessage());
		}

	}

	/**
	 * Preenchimento do objeto JustificativaNaoAtendimento a partir dos parâmetros recebidos do JSONArray
	 * 
	 * @param args - argumentos do JSONArray
	 * @return JustificativaNaoAtendimento - informações do JSONArray
	 * @throws JSONException - erro ao recuperar informações do JSONArray
	 */
	private JustificativaNaoAtendimento preencherJustificativa(JSONArray args) throws JSONException {
		JustificativaNaoAtendimento justificativaNaoAtendimento = new JustificativaNaoAtendimento();

		justificativaNaoAtendimento.codigoCliente = args.getInt(0);
		justificativaNaoAtendimento.codigoJustificativa = args.getInt(1);
		justificativaNaoAtendimento.descricaoJustificativa = args.getString(2);
		justificativaNaoAtendimento.dataJustificativa = DateUtil.obterDataAtual();

		return justificativaNaoAtendimento;
	}

}