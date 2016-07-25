package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class AtualizacaoCadastral extends BaseVo {

	private static final long serialVersionUID = -200385232154424327L;

	/**
	 * DESMSGCAD
	 */
	public String descMensagem;

	/**
	 * DATINIVLD
	 */
	public Date dataInicioValida;

	/**
	 * DATFIMVLD
	 */
	public Date dataFimValida;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("descMensagem", descMensagem);
		propriedades.put("dataInicioValida", dataInicioValida);
		propriedades.put("dataFimValida", dataFimValida);
		return propriedades;
	}
}
