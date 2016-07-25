package br.com.martins.vendas.vendas.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.BaseVo;

public class GondolaCliente extends BaseVo {

	private static final long serialVersionUID = 9154770307660500649L;

	public List<Gondola> listaItensGondola = new ArrayList<Gondola>();
	public boolean isVisualizaComissao;
	public BigDecimal margemLucro;
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listaItensGondola", listaItensGondola);
		map.put("isVisualizaComissao", isVisualizaComissao);
		map.put("margemLucro", margemLucro);
		return map;
	}

}