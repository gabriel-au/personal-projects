package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class NotaFiscal extends BaseVo {

	private static final long serialVersionUID = -1698225962591696418L;

	public NotaFiscal() {
		valorEfetivoFaturamento = BigDecimal.ZERO;
		valorEfetivoImposto = BigDecimal.ZERO;
		valorTotalPedido = BigDecimal.ZERO;
	}
	
	public Integer codigoFilialExpedicao;
	public Integer codigoFilialFaturamento;
	public Integer numeroNotaFiscal;
	public BigDecimal valorEfetivoFaturamento;
	public BigDecimal valorEfetivoImposto;
	public BigDecimal valorTotalPedido;
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigoFilialExpedicao", codigoFilialExpedicao);
		map.put("codigoFilialFaturamento", codigoFilialFaturamento);
		map.put("numeroNotaFiscal", numeroNotaFiscal);
		map.put("valorEfetivoFaturamento",  null != valorEfetivoFaturamento ? valorEfetivoFaturamento.toString() : valorEfetivoFaturamento); // StringUtil.formataMonetario(valorEfetivoFaturamento));
		map.put("valorEfetivoImposto", null != valorEfetivoImposto ? valorEfetivoImposto.toString() : valorEfetivoImposto); // StringUtil.formataMonetario(valorEfetivoImposto));
		map.put("valorTotalPedido", null != valorTotalPedido ? valorTotalPedido.toString() : valorTotalPedido); // StringUtil.formataMonetario(valorTotalPedido));
		return map;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codigoFilialExpedicao == null) ? 0 : codigoFilialExpedicao.hashCode());
//		result = prime * result + ((codigoFilialFaturamento == null) ? 0 : codigoFilialFaturamento.hashCode());
//		result = prime * result + ((numeroNotaFiscal == null) ? 0 : numeroNotaFiscal.hashCode());
//		return result;
//	}

}