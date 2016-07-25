package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

/**
 * Tabela: PCAPCH
 * 
 * @author BRQ Mobile Team
 */
public class PalavraChave extends BaseVo {

	private static final long serialVersionUID = -4671821962448919828L;

	/**
	 * TIPTERCHV
	 */
	public Integer codigoTermoChave;

	/**
	 * NOMFILEPD
	 */
	public Integer tipoTermoChave;
	
	/**
	 * DESTERCHV
	 */
	public String descricaoTermoChave;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoTermoChave"		, codigoTermoChave);
		propriedades.put("tipoTermoChave"		, tipoTermoChave);
		propriedades.put("descricaoTermoChave"	, descricaoTermoChave);
		return propriedades;
	}

}
