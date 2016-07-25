package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;

import br.com.martins.vendas.services.MailService;
import br.com.martins.vendas.vo.Pedido;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class EnviarRelatorioEmailPedidoAction {
	
	public ActionResult execute(JSONArray array) throws Exception {
		boolean sucesso= false;
		
		Pedido pedido					= 	new Pedido();
		String nomeCliente				=   array.getString(0);
		Integer codigoCliente 			= 	new Integer(array.getString(1));
//		String emailRepresentante		=   array.getString(2);
		String emailRepresentante		=   "rmarinheiro@brq.com"; // TODO REMOVER QUANDO GERAR A VERSAO
		
		boolean valorFrete 		  = (0 == array.getInt(3));
		boolean totalImpostos	  = (0 == array.getInt(4));
		boolean filialExp		  = (0 == array.getInt(5));
		boolean filialFaturamento = (0 == array.getInt(6));
		boolean percentualIcms	  = (0 == array.getInt(7));
		pedido.numeroPedido 	  = array.getString(8);
		pedido.preparado 		  = array.getString(9);
		pedido.dataPedido		  = DateUtil.formataData(array.getString(10), DateUtil.DEFAULT_DATE_PATTERN);
		pedido.horaPedido         =  array.getString(11);
		pedido.dataResultado      = DateUtil.formataData(array.getString(12), DateUtil.DEFAULT_DATE_PATTERN);
		pedido.quantidadeTotalItens    = array.getInt(13);
		pedido.valorTotalPedido   = Util.getBigDecimal(array.getString(14));
		
		sucesso = MailService.enviarEmailRelatorioPedido(codigoCliente,nomeCliente, emailRepresentante, valorFrete, totalImpostos, filialExp, filialFaturamento, percentualIcms,pedido);
		
		return new ActionResult(sucesso ? Status.OK : Status.ERROR);
		
	}

}