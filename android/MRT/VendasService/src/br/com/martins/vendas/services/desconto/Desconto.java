package br.com.martins.vendas.services.desconto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

/**
 * Classe responsavel por agrupar as informacoes de desconto.
 */
public class Desconto extends BaseVo {

	private static final long serialVersionUID = -8221575418645379024L;

	public Desconto() {
		tipo = "";
		
		valorDescontoIsencao = BigDecimal.ZERO;
		valorDescontoEspecial = BigDecimal.ZERO;
		valorDesconto = BigDecimal.ZERO;

		percentualDescontoAcao = BigDecimal.ZERO;
		percentualDescontoIsencao = BigDecimal.ZERO;
		percentualDescontoFlexivel = BigDecimal.ZERO;
		percentualDescontoAcaoTatica = BigDecimal.ZERO;
		percentualDescontoSimplificado = BigDecimal.ZERO;
		percentualDescontoBanda = BigDecimal.ZERO;
		percentualMaximoDesconto = BigDecimal.ZERO;
		percentualMinimoDesconto = BigDecimal.ZERO;
		percentualDescontoCaixa = BigDecimal.ZERO;
		
		percentualMaximoAcrescimo = BigDecimal.ZERO;
	}

	public String tipo;
	
	public BigDecimal valorDesconto;
	public BigDecimal valorDescontoIsencao;
	public BigDecimal valorDescontoEspecial;

	public BigDecimal percentualDescontoAcao;
	public BigDecimal percentualDescontoIsencao;
	public BigDecimal percentualDescontoFlexivel;
	public BigDecimal percentualDescontoAcaoTatica;
	public BigDecimal percentualDescontoSimplificado;
	public BigDecimal percentualDescontoBanda;
	public BigDecimal percentualMaximoDesconto;
	public BigDecimal percentualMinimoDesconto;

	public BigDecimal percentualDescontoCaixa;

	public BigDecimal percentualMaximoAcrescimo;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("valorDesconto", valorDesconto);
		map.put("valorDescontoIsencao", valorDescontoIsencao);
		map.put("valorDescontoEspecial", valorDescontoEspecial);
		map.put("percentualDescontoAcao", percentualDescontoAcao);
		map.put("percentualDescontoIsencao",percentualDescontoIsencao);
		map.put("percentualDescontoFlexivel",percentualDescontoFlexivel);
		map.put("percentualDescontoAcaoTatica",percentualDescontoAcaoTatica);
		map.put("percentualDescontoSimplificado",percentualDescontoSimplificado);
		map.put("percentualDescontoBanda",percentualDescontoBanda);
		map.put("percentualMaximoDesconto",percentualMaximoDesconto);
		map.put("percentualMinimoDesconto",percentualMinimoDesconto);
		map.put("percentualDescontoCaixa", percentualDescontoCaixa);
		map.put("percentualMaximoAcrescimo", percentualMaximoAcrescimo);
		return map;
	}

}
