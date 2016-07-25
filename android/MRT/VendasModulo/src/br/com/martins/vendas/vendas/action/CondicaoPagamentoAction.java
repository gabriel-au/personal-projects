package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.FilialService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class CondicaoPagamentoAction {

	private static final String TAG = CondicaoPagamentoAction.class.getName();

	public ActionResult execute(JSONArray array) throws Exception {
		try {
			boolean isFinanciamentoVendor, isCobrancaBancaria, isVendaCash;
			int numeroParcelas, prazo, periodicidade,filialExpedicao;
		
			numeroParcelas = array.getInt(0);
			prazo          = array.getInt(1);
			periodicidade  = array.getInt(2);
			isFinanciamentoVendor = array.getBoolean(3);
			isCobrancaBancaria    = array.getBoolean(4);
			isVendaCash           = array.getBoolean(5);
			
			Cliente cliente = ClienteService.obterDetalheCliente(array.getInt(6));
			
			filialExpedicao = array.getInt(7);
			if (filialExpedicao == -1) {
				filialExpedicao =	FilialService.obterFiliaisPorCliente(cliente.codigoCliente, cliente.codigoTerritorio).get(0).filialExpedicao.codigoFilial;
			}
			
			List<CondicaoPagamento> condicaoPagamento = CondicaoPagamentoService.obterDadosCondicaoPagamento(
					filialExpedicao, 
					cliente.uf, 
					cliente.canal, 
					cliente.codigoAtividade, 
					cliente.codigoGrupoCliente, 
					numeroParcelas, 
					prazo, 
					periodicidade, 
					cliente.restricaoCondicaoPagamento, 
					cliente.flagClienteEspecial, 
					cliente.tipoContratoVendor, 
					cliente.tipoCobrancaBanco, 
					cliente.tipoLimiteCredito, 
					isFinanciamentoVendor, 
					isCobrancaBancaria, 
					isVendaCash);
			
			JSONObject jsonCondPag = new JSONObject();
			jsonCondPag.put("consultaCondicaoPagamento", JsonUtil.toJson("itensCondicaoPagamento", condicaoPagamento));
			
			return new ActionResult(Status.OK, jsonCondPag);
			
		} catch (Exception e) {
			
			Log.e(TAG, e.getLocalizedMessage(), e);
			
			JSONObject jsonObject = new JSONObject();			
			jsonObject.put("msg", e.getLocalizedMessage());
			
			return new ActionResult(Status.ERROR, jsonObject);
		}
	}
}
