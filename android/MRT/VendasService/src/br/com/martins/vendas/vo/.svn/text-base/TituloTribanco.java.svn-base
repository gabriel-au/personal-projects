package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class TituloTribanco extends BaseVo {

	private static final long serialVersionUID = -4480406674873034421L;

	public String nomeCliente;
	
	public Integer codigoCliente;

	public BigDecimal valorVencer;

	public BigDecimal valorVencido;

	public Integer quantidadeTitulos;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("nomeCliente", nomeCliente);
		propriedades.put("codigoCliente", codigoCliente);
		propriedades.put("valorVencer", valorVencer == null ? "0,00" : StringUtil.formataMonetario(valorVencer));
		propriedades.put("valorVencido", valorVencido == null ? "0,00" : StringUtil.formataMonetario(valorVencido));
		propriedades.put("quantidadeTitulos",quantidadeTitulos);
		
		return propriedades;
	}

}
