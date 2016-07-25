package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.calculodepreco.ComissaoService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Comissao;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

/**
 * The Class ComissaoAction.
 */
public class ComissaoAction {
	
	public ActionResult execute(JSONArray array) throws JSONException {
		//parametros criados para teste
		Integer codigoFilialExp = 1;//array.getInt(0);
		String siglaEstado = null;
		Cliente cliente = new Cliente();
		Representante representante = new Representante();
		BigDecimal percentualDescontoConcedido = null;
		boolean isUltimoItem = true;
		boolean isComissaoBruta = false;	
		
	
		cliente.canal=array.getInt(0);
		cliente.tipoLimiteCredito=array.getString(1);
		representante.naturezaRepresentante=array.getString(2);					
		
		List<Comissao>listaComissao = ComissaoService.buscaSimboloMercadoria(codigoFilialExp,
				siglaEstado,
				cliente,				
				representante, 
				percentualDescontoConcedido, 
				isUltimoItem, 
				isComissaoBruta);			
		
		return new ActionResult(Status.OK, JsonUtil.toJson("listaComissao", listaComissao));			
	}	
}