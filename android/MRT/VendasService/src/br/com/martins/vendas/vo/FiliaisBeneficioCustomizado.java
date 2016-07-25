package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

/**
 * TABELA PCABFE
 * @author BRQ Mobile Team
 *
 */
public class FiliaisBeneficioCustomizado extends BaseVo {

	private static final long serialVersionUID = -9144744818111452347L;

	/**
	 * CODBFC
	 */
	public Integer codigoBeneficio;
	
	/**
	 * CODFILEPD
	 */
	public Integer codigoFilialExpedicao;


	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoBeneficio", codigoBeneficio);
		propriedades.put("codigoFilialExpedicao", codigoFilialExpedicao);
		return propriedades;
	}
}