package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

/**
 * Tabela PCAMNM
 * 
 * @author BRQ Mobile Team
 */
public class ParametroMinimoFilial extends BaseVo {

	private static final long serialVersionUID = -7799818918809384843L;

	public Integer codigoFilial;

	public String siglaEstado;

	public Integer canal;

	public BigDecimal valorMinimoExpedicao;

	public BigDecimal valorMinimoBoletoBancario;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoFilial", codigoFilial);
		propriedades.put("siglaEstado", siglaEstado);
		propriedades.put("canal", canal);
		propriedades.put("valorMinimoExpedicao", StringUtil.formataMonetario(valorMinimoExpedicao));
		propriedades.put("valorMinimoBoletoBancario", StringUtil.formataMonetario(valorMinimoBoletoBancario));
		return propriedades;
	}
}