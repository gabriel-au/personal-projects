package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;

import br.com.martins.vendas.services.AgendaRoteirizacaoService;
import br.com.martins.vendas.vo.AgendaRoteirizacao;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class AgendaRoteirizacaoAction {

	public ActionResult execute(JSONArray array) throws Exception {
		List<AgendaRoteirizacao> agendaRoteirizacao = AgendaRoteirizacaoService.findAgendaRoteirizacao(array.getInt(0));
		return new ActionResult(Status.OK, JsonUtil.toJson("agendaRoteirizacao", agendaRoteirizacao));
	}

}
