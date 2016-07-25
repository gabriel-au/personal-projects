package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class PercentualIPI extends BaseVo {

	private static final long serialVersionUID = 3658512401922916926L;
	
	public PercentualIPI() {
		percentualIPI  = BigDecimal.ZERO;
		valorPalletIPI = BigDecimal.ZERO;
	}

	public BigDecimal percentualIPI;
	
	public BigDecimal valorPalletIPI;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("percentualIPI", percentualIPI);
		map.put("valorPalletIPI", valorPalletIPI);
		return map;
	}

}
