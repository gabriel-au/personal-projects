package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class RestricaoMercadoria extends BaseVo {

	private static final long serialVersionUID = -2480299141752313345L;

	/**
	 * PCARTM - CODMER
	 */
	public Integer codigoMercadoria;
	
	/**
	 * PCARTM - QDERTCITE
	 */
	public Integer quantidadeRestricaoItem;
	
	/**
	 * PCARTM - CODGRPRTC
	 */
	public Integer codigoGrupoRestricao;
	
	/**
	 * PCARTF - CODESTUNI
	 */
	public String codigoEstadoUnidade;
	
	/**
	 * PCARTF - CODFILEPD
	 */
	public Integer codigoFilialExpedicao;
	
	/**
	 * PCARTF - PERRTC
	 */
	public BigDecimal percentualRestricao;
	
	/**
	 * PCARTA - CODATI
	 */
	public Integer codigoAtividade;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		return propriedades;
	}
	
}