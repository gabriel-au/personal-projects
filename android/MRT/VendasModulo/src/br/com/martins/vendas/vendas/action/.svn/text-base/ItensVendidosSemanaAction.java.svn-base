package br.com.martins.vendas.vendas.action;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.calculodepreco.Item;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.JsonUtil;

public class ItensVendidosSemanaAction {

	public ActionResult execute(JSONArray array) throws Exception {
		JSONObject js = new JSONObject();
		Date dataAtual = new Date();
		Date dataInicioSemana = resolveDiaSemana(dataAtual, true);
		Date dataFimSemana = resolveDiaSemana(dataAtual, false);
		List<Item> itensVendidosSemana = ItemPedidoService.obtemItensPedidoSemana(dataInicioSemana, dataFimSemana);
		js.put("periodoVenda", DateUtil.formataData(dataInicioSemana, DateUtil.DEFAULT_DATE_PATTERN) + " a " + DateUtil.formataData(dataFimSemana, DateUtil.DEFAULT_DATE_PATTERN));
		js.put("itensVendidosSemana", JsonUtil.toJsonArray(itensVendidosSemana));
		return new ActionResult(Status.OK, js);
	}

	private Date resolveDiaSemana(Date data, boolean isPrimeiro) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		if (isPrimeiro) {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		} else {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}
}
