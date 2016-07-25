package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class PosicaoVendas extends BaseVo {

	private static final long serialVersionUID = -6356054254831144170L;

	public Date dataInicio;
	public Date dataFim;
	
	public Cliente cliente;
	public Cortes corte;
	public Pedido pedido;
	public CondicaoPagamento condicaoPagamento;
	
	public PosicaoVendas() {
		this.cliente = new Cliente();
		this.corte 	 = new Cortes();
		this.pedido  = new Pedido();
		this.condicaoPagamento = new CondicaoPagamento();
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();			
		map.put("dataInicio", DateUtil.formataData(dataInicio, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("dataFim"	, DateUtil.formataData(dataFim, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("cliente"	, cliente);
		map.put("corte"		, corte);
		map.put("pedido"	, pedido);
		map.put("condicaoPagamento"	, condicaoPagamento);
		return map;
	}
}
