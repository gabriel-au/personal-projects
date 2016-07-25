package br.com.martins.vendas.vendas.action;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.PosicaoVendasService;
import br.com.martins.vendas.vo.PosicaoVendas;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.JsonUtil;

public class PosicaoVendasAction {

	private static final String TAG = PosicaoVendasAction.class.getName();
	private static final String MSG_ERRO = "Não foi encontrado nenhum registro para a consulta escolhida, favor escolher outra data .";

	public ActionResult execute()  {
		ActionResult result = new ActionResult(Status.OK);
		
		try {
			PosicaoVendas posicao = PosicaoVendasService.obtemDataDefault();
			result =  new ActionResult(Status.OK, JsonUtil.toJson("posicao", posicao));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public ActionResult obterPosicaoVendasPorPrazoDePagamento(JSONArray array){
		ActionResult result = new ActionResult(Status.OK);

		try {
			Date dataInicio = DateUtil.formataData(array.getString(0).trim(),"dd/MM/yy");
			Date dataFim    = DateUtil.formataData(array.getString(1).trim(),"dd/MM/yy");
			
			if(dataInicio != null  ||dataFim != null){
				List<PosicaoVendas> posicaoVendas = PosicaoVendasService.obterPosicaoVendasPorPrazoDePagamento(dataInicio, dataFim);
				
				if(posicaoVendas.isEmpty()){
					result = new ActionResult(Status.ERROR,new JSONObject().put("mensagem", MSG_ERRO));
				}else{
					result = new ActionResult(Status.OK, JsonUtil.toJson("listaPosicaoVendasPorPrazoDePagamento", posicaoVendas));
				}
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
		return result;
	}
	
	public ActionResult obterPosicaoVendasPorCliente(JSONArray array){
		ActionResult result = new ActionResult(Status.OK);
		
		try {
			Date dataInicio = DateUtil.formataData(array.getString(0).trim(),"dd/MM/yy");
			Date dataFim    = DateUtil.formataData(array.getString(1).trim(),"dd/MM/yy");
			
			if(dataInicio != null  ||dataFim != null){
				List<PosicaoVendas> posicaoVendas = PosicaoVendasService.obterPosicaoVendasPorCliente(dataInicio, dataFim);
				
				if(posicaoVendas.isEmpty()){
					result = new ActionResult(Status.ERROR,new JSONObject().put("mensagem", MSG_ERRO));
				}else{
					result = new ActionResult(Status.OK, JsonUtil.toJson("listaPosicaoVendasPorCliente", posicaoVendas));
				}
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
		
		return result;
	}	
}