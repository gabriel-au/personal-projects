package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class ItemPedido extends BaseVo {

	private static final long serialVersionUID = 3030115186006073742L;

	/**
	 * CODMER
	 */
	public Integer codigoMercadoria;

	/**
	 * DESMER
	 */
	public String descricaoMercadoria;

	public Map<String, Object> toMap() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("descricaoMercadoria", descricaoMercadoria);
		map.put("codigoMercadoria", codigoMercadoria);
		return map;
	}

}
