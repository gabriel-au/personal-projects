package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class DadosComerciaisRepresentante extends BaseVo {

	private static final long serialVersionUID = -997658597444992704L;

	/**
	 * VLRPRVREP
	 */
	public Double previsaoSemanal;

	/**
	 * PERPVTEMP
	 */
	public Double aproveitamentoIdeal;

	/**
	 * PERPVTREP
	 */
	public Double aproveitamentoAtual;

	public BigDecimal pecentualTaxaCDI;

	public BigDecimal pecentualTaxaDPZ;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("previsaoSemanal", StringUtil.formataMonetario(previsaoSemanal));
		propriedades.put("aproveitamentoIdeal", aproveitamentoIdeal);
		propriedades.put("aproveitamentoAtual", aproveitamentoAtual);
		
		propriedades.put("pecentualTaxaCDI", pecentualTaxaCDI);
		propriedades.put("pecentualTaxaDPZ", pecentualTaxaDPZ);
		return propriedades;
	}

}
