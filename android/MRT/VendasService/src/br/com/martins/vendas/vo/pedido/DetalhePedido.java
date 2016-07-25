package br.com.martins.vendas.vo.pedido;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class DetalhePedido extends BaseVo {

	private static final long	serialVersionUID	= 8960336286130810103L;

	public BigDecimal			valorTotalBrindePedido;
	public BigDecimal			valorTotalLiquido;
	public BigDecimal			valorTotalDescontoItem;
	public BigDecimal			valorTotalDescontoPedido;
	public BigDecimal			valorTotalDescontoPedidoFaturado;
	public BigDecimal			valorTotalImposto;
	public BigDecimal			valorTotalBoletoBanco;
	public BigDecimal			valorTotalFrete;
	public BigDecimal			valorTotalNotaFiscal;
	public BigDecimal			valorStbComplementar;
	public BigDecimal			valorTotalPontosPedido;
	public Integer				quantidadeTotalItens;

	// CABECALHO

	// numeroPedido
	// quantidadeItens
	// pontosMinimos
	// pontos
	// cliente
	// roteirizacaoCAD
	// previsaoEntrega

	// VALORES DO PEDIDO

	// valor bruto do pedido -----------------
	// desconto nos itens
	// desconto no pedido
	// valor impostos (stb+ipi)
	// totais das notas fiscais ----------------
	// vendor
	// valor frete FOB
	// despesas acessorias
	// total a pagar -----------------------------

	// COMISSAO

	// comissao bruta --------------
	// desconto nos itens
	// desconto no pedido
	// bonificacao (e.flex)
	// adicional (pessoa juridica)
	// comissao liquida -----------------

	public Map<String, Object> toMap() {

		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("mensagens", mensagens);

		map.put("quantidadeTotalItens", quantidadeTotalItens);

		map.put("valorTotalBrindePedido", valorTotalBrindePedido);
		map.put("valorTotalLiquido", valorTotalLiquido);
		map.put("valorTotalDescontoItem", valorTotalDescontoItem);
		map.put("valorTotalDescontoPedido", valorTotalDescontoPedido);
		map.put("valorTotalDescontoPedidoFaturado", valorTotalDescontoPedidoFaturado);
		map.put("valorTotalImposto", valorTotalImposto);
		map.put("valorTotalBoletoBanco", valorTotalBoletoBanco);
		map.put("valorTotalFrete", valorTotalFrete);
		map.put("valorTotalNotaFiscal", valorTotalNotaFiscal);
		map.put("valorStbComplementar", valorStbComplementar);
		map.put("valorTotalPontosPedido", valorTotalPontosPedido);
		return map;
	}

}