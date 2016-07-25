package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

/**
 * Tabela: PCAFIL
 * 
 * @author BRQ Mobile Team
 */
public class Filial extends BaseVo {

	private static final long serialVersionUID = -4671821962448919828L;

	/**
	 * CODFILEPD
	 */
	public Integer codigoFilial;

	/**
	 * NOMFILEPD
	 */
	public String nomeFilial;
	
	/**
	 * INDVNDDTO
	 */
	public Integer indicativoVendaDireta;
	

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoFilial", codigoFilial);
		propriedades.put("nomeFilial", nomeFilial);
		propriedades.put("indicativoVendaDireta", indicativoVendaDireta);
		return propriedades;
	}

}
