package br.com.martins.vendas.relacionamento.dto;

import java.util.HashMap;
import java.util.Map;

import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.JustificativaNaoAtendimento;

import com.brq.mobile.framework.core.BaseVo;

public class DetalheCliente extends BaseVo {

	private static final long serialVersionUID = -844546504843105665L;

	public Cliente cliente;
	public JustificativaNaoAtendimento justificativaNaoAtendimento;
	public Integer totalTitulosAbertos;
	public Integer totalPedidos;
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cliente", cliente);
		map.put("justificativaNaoAtendimento", justificativaNaoAtendimento);
		map.put("totalTitulosAbertos", totalTitulosAbertos);
		map.put("totalPedidos", totalPedidos);
		return map;
	}

}