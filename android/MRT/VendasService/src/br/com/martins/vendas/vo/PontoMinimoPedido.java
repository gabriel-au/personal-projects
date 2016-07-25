package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

/**
 * Tabela PCAPMN -- Pontos Minimos do Pedidos
 * 
 * @author BRQ Mobile Team
 */
public class PontoMinimoPedido extends BaseVo {

	private static final long serialVersionUID = 467543380815043542L;

	/**
	 * CODFILEPD
	 */
	public Integer codigoFilialExpedicao;

	/**
	 * CODESTUNI
	 */
	public String codigoEstadoUnidade;

	/**
	 * CODCNL
	 */
	public Integer codigoCanal;

	/**
	 * VLRMNMEPD
	 */
	public BigDecimal valorMinimoExpedicao;

	/**
	 * VLRMNMBLT
	 */
	public BigDecimal valorMinimoBoleto;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoFilialExpedicao", codigoFilialExpedicao);
		propriedades.put("codigoEstadoUnidade", codigoEstadoUnidade);
		propriedades.put("codigoCanal", codigoCanal);
		propriedades.put("valorMinimoExpedicao", valorMinimoExpedicao);
		propriedades.put("valorMinimoBoleto", valorMinimoBoleto);
		return propriedades;
	}
}