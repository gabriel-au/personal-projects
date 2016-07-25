package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class JustificativaNaoAtendimento extends BaseVo {

	private static final long serialVersionUID = 2396082038637889917L;

	/**
	 * Tabela: PCAGPC
	 */
	/**
	 * CODJSTNATD
	 */
	public Integer codigoJustificativa;

	/**
	 * DESJSTNATD
	 */
	public String descricaoJustificativa;

	/**
	 * Tabela: PCAJCL
	 */
	/**
	 * CODCLI
	 */
	public Integer codigoCliente;

	/**
	 * DATJSTNATD
	 */
	public Date dataJustificativa;

	@Override
	public Map<String, Object> toMap() {
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigoJustificativa", codigoJustificativa);
		map.put("descricaoJustificativa", descricaoJustificativa);
		map.put("codigoCliente", codigoCliente);
		map.put("dataJustificativa", DateUtil.formataData(dataJustificativa, DateUtil.DEFAULT_DATE_PATTERN));
		return map;
	}

}