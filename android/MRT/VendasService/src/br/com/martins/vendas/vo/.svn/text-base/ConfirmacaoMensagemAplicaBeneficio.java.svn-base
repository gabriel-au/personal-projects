package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class ConfirmacaoMensagemAplicaBeneficio extends BaseVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BigDecimal precoBC;
	public String percentualVariacaoPreco;
	public Integer codigoMercadoria;
	public String descricaoMercadoria;
	public Integer filialExpedicao;
	public Integer codigoCondicaoPagamento;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new  HashMap<String, Object>();
		propriedades.put("precoBC", precoBC );
		propriedades.put("percentualVariacaoPreco", percentualVariacaoPreco );
		propriedades.put("codigoMercadoria", codigoMercadoria );
		propriedades.put("descricaoMercadoria", descricaoMercadoria );
		propriedades.put("filialExpedicao", filialExpedicao );
		propriedades.put("codigoCondicaoPagamento", codigoCondicaoPagamento );
		return propriedades;
	}
}
