package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

/**
 * Tabela PCAFPE --Pontos Minimos do Pedidos por Segmento
 * 
 * @author BRQ Mobile Team
 */
public class PontoMinimoPedidoSegmento extends BaseVo {

	private static final long serialVersionUID = -1464936002100133734L;

	/**
	 * CODESTDSN
	 */
	public String siglaEstadoDestino;

	/**
	 * CODUNDESR
	 */
	public Integer codigoUnidadeEstrategia;

	/**
	 * CODSGMCLI
	 */
	public Integer codigoSegmentoCliente;

	/**
	 * VLRLMTPED
	 */
	public Integer valorLancamentoPedido;

	/**
	 * VLRPTOFLX
	 */
	public Integer valorPontoFlexivel;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("siglaEstadoDestino", siglaEstadoDestino);
		propriedades.put("codigoUnidadeEstrategia", codigoUnidadeEstrategia);
		propriedades.put("codigoSegmentoCliente", codigoSegmentoCliente);
		propriedades.put("valorLancamentoPedido", StringUtil.formataMonetario(valorLancamentoPedido));
		propriedades.put("valorPontoFlexivel", StringUtil.formataMonetario(valorPontoFlexivel));
		return propriedades;
	}
}