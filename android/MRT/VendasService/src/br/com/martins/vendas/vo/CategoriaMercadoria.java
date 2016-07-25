package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class CategoriaMercadoria extends BaseVo {
	
	private static final long	serialVersionUID	= 1L;

	public Integer				codigoGrupoMercadoria;

	public Integer				codigoCategoriaMercadoria;

	public String				descricaoCategoriaMercadoria;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigoGrupoMercadoria", codigoGrupoMercadoria);
		map.put("codigoCategoriaMercadoria", codigoCategoriaMercadoria);
		map.put("descricaoCategoriaMercadoria", descricaoCategoriaMercadoria);
		return map;
	}

	
	
}
