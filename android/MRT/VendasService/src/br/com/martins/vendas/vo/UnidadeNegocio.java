package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class UnidadeNegocio extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CODUNDESR
	 */
	public Integer codigoUnidadeEstrategica;	
	
	/**
	 * DESUNDESR
	 */
	public String descricaoUnidadeEstrategica; 
	
	/**
	 * DESABVUND
	 */
	public String descricaoAbreviaturaEstrategica; 

	@Override
	public Map<String, Object> toMap() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("codigoUnidadeEstrategica", codigoUnidadeEstrategica);
		map.put("descricaoUnidadeEstrategica", descricaoUnidadeEstrategica);
		map.put("descricaoAbreviaturaEstrategica", descricaoAbreviaturaEstrategica);
		return map;
	}

}
