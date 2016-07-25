package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class PercentualICMS extends BaseVo{
	
	private static final long serialVersionUID = 1844634198933772410L;

	public PercentualICMS(){
		percentualICMSConsumo = BigDecimal.ZERO;
		percentualICMSMercadoria = BigDecimal.ZERO;
		percentualPadraoConsumo = BigDecimal.ZERO;
		percentualPadraoConsumoOriginal = BigDecimal.ZERO;
		percentualOriginal = BigDecimal.ZERO;
	}

	/** PERICMMER  - tabela PCAICM **/
	public BigDecimal percentualICMSMercadoria;
	
	/** PERICMCSM **/
	public BigDecimal percentualICMSConsumo;
	
	/** TIPUTZICM - tabelas PCAICM e PCAAGE **/
	public int tipoUtilizacaoICMS;
	
	/** ICMMERCSM - tabela PCAAGE **/
	public BigDecimal percentualPadraoConsumo;
	
	/** ICMORICSM - tabela PCAAGE **/
	public BigDecimal percentualPadraoConsumoOriginal;
	
	/**  PERICMORI - tabela PCAAGE **/
	public BigDecimal percentualOriginal;

	/** PERICMCSNARD - tabela PCAICM  **/
	public BigDecimal percentualICMConsumidorAcordo;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("percentualICMSMercadoria", percentualICMSMercadoria);
		map.put("percentualICMSConsumo", percentualICMSConsumo);
		map.put("tipoUtilizacaoICMS", tipoUtilizacaoICMS);
		return map;
	}
}
