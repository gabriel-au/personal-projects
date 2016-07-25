package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class NavigationConfig extends BaseVo {

	private static final long serialVersionUID = -5293868179429307572L;

	public String name;
	public String page;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("name", name);
		propriedades.put("page", page);
		return propriedades;
	}

}
