package br.com.martins.vendas.services.calculodepreco;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Imposto extends BaseVo {

	private static final long serialVersionUID = -4974722145653568816L;

	public Imposto() {
		valorSTBUnitario = BigDecimal.ZERO;
		valorSTBTotal = BigDecimal.ZERO;
		valorSTBTotalBonificacao = BigDecimal.ZERO;
		valorSTBComplementar = BigDecimal.ZERO;
		valorSTBComplementarTotal = BigDecimal.ZERO;
		percentualValorMinimo = BigDecimal.ZERO;
	}

	public BigDecimal valorSTBUnitario; // VLRSTBMER - Valor STB Mercadoria
	public BigDecimal valorSTBTotal; // VLRSTBTOT - Valor STB Total
	public BigDecimal valorSTBTotalBonificacao;
	public BigDecimal valorSTBComplementar; // VLRSTBCPL - Valor STB Complementar
	public BigDecimal valorSTBComplementarTotal; // STBCPLTOT - STB Complementar Total
	public BigDecimal percentualValorMinimo;
	public boolean temMargemDeLucro;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("valorSTBUnitario", valorSTBUnitario);
		map.put("valorSTBTotal", valorSTBTotal);
		map.put("valorSTBTotalBonificacao", valorSTBTotalBonificacao);
		map.put("valorSTBComplementar", valorSTBComplementar);
		map.put("valorSTBComplementarTotal", valorSTBComplementarTotal);
		map.put("percentualValorMinimo", percentualValorMinimo);
		map.put("temMargemDeLucro", temMargemDeLucro);
		return map;
	}

	public Integer obterMargemDeLucroNumerica() {
		return temMargemDeLucro ? 1 : 0;
	}
	
	public boolean obterMargemDeLucroBoleana(Integer margemDeLucro) {
		temMargemDeLucro = margemDeLucro == 1 ? true : false;
		return temMargemDeLucro;
	}

}
