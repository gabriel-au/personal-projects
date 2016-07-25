package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Quota extends BaseVo {

	private static final long serialVersionUID = -7215998551400729222L;

	public Integer				codFilialExpedicao;

	public String				codEstadoDestino;

	public Integer				codMercadoria;

	public Integer				qtdQuotaMercadoria;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codFilialExpedicao", codFilialExpedicao);
		propriedades.put("codEstadoDestino", codEstadoDestino);
		propriedades.put("codMercadoria", codMercadoria);
		propriedades.put("qtdQuotaMercadoria", qtdQuotaMercadoria);
		return propriedades;
	}
}
