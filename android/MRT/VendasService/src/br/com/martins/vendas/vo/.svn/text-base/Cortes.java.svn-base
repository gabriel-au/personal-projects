package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class Cortes  extends BaseVo {

	private static final long serialVersionUID = -6507457724099604375L;

	public Integer codigoMercadoria;
	
	public Integer filialExpedicao;
	
	public Integer filialFaturamento;
	
	public BigDecimal valorLiqMercadoria;
	
	public String descricaoMercadoria;
	
	public Integer qtdMercadoriaPedido;
	
	public Integer qtdMercadoriaCortado;
	
	public Integer qtdMercadoriaRecuperada;
	
	public String descMotivoCorte;
	
	public Double valorLiquidoCalculado;
	
	
	@Override
	public Map<String, Object> toMap() {
		
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoMercadoria", codigoMercadoria);
		propriedades.put("filialExpedicao", filialExpedicao);
		propriedades.put("filialFaturamento", filialFaturamento);
		propriedades.put("valorLiqMercadoria", valorLiqMercadoria);
		propriedades.put("descricaoMercadoria", descricaoMercadoria);
		propriedades.put("qtdMercadoriaPedido", qtdMercadoriaPedido);
		propriedades.put("qtdMercadoriaCortado", qtdMercadoriaCortado);
		propriedades.put("qtdMercadoriaRecuperada", qtdMercadoriaRecuperada);
		propriedades.put("descMotivoCorte", descMotivoCorte);
		propriedades.put("valorLiquidoCalculado", StringUtil.formataMonetario(valorLiquidoCalculado));
		return propriedades;
	}

	

}
