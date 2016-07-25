package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class ModeloDistribuicao extends BaseVo {

	private static final long serialVersionUID = 1954129772831615505L;

	/**
	 * CODRGRDTB
	 */
	public Integer codRegraTabela;

	/**
	 * DESRGRDTB
	 */
	public String descRegraTabela;

	/**
	 * RAZSOCTRP
	 */
	public String razSocialTransportadora;

	/**
	 * INDTRPPPO
	 */
	public Integer transporteProprio;

	/**
	 * INDRGROBR
	 */
	public Integer regraObrigatoria;

	/**
	 * INDTABMAN
	 */
	public String indicadorTabelaManual;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codRegraTabela", codRegraTabela);
		propriedades.put("descRegraTabela", descRegraTabela);
		propriedades.put("razSocialTransportadora", razSocialTransportadora);
		propriedades.put("transporteProprio", transporteProprio);
		propriedades.put("regraObrigatoria", regraObrigatoria);
		propriedades.put("indicadorTabelaManual", indicadorTabelaManual);

		return propriedades;
	}

}
