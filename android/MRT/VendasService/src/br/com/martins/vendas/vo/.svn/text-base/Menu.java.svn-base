package br.com.martins.vendas.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Menu extends BaseVo {

	private static final long serialVersionUID = -7654787083439397092L;

	public String name;
	public String title;
	public String[] roles;
	public String styleClass;
	public List<ItemMenu> itens = new ArrayList<ItemMenu>();

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("name", name);
		propriedades.put("title", title);
		propriedades.put("styleClass", styleClass);
		propriedades.put("roles", roles);
		propriedades.put("itens", itens);
		return propriedades;
	}

	public void addItem(ItemMenu item) {
		itens.add(item);
	}

}
