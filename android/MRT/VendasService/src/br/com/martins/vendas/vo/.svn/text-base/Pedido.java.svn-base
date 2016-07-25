package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.services.calculodepreco.Item;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class Pedido extends BaseVo {

	private static final long serialVersionUID = 7718650610075339841L;

	/**
	 * NUMPED
	 */
	public String numeroPedido;

	/**
	 * CODCLI
	 */
	public String codigoCliente;

	/**
	 * NOMCLI
	 */
	public String nomeCliente;

	/**
	 * DATPED
	 */
	public Date dataPedido;

	/**
	 * HRAPED
	 */
	public String horaPedido;

	/**
	 * FLGTRS
	 */
	public String preparado;

	/**
	 * VLRTOTPED
	 */
	public BigDecimal valorTotalPedido;

	/**
	 * QDEITE
	 */
	public Integer quantidadeTotalItens;

	/**
	 * PERICMMER
	 */
	public BigDecimal percentualIcmsMercadoria;

	/**
	 * QDEMERPED
	 */
	public Integer qtdMercadoriaPedida;

	/**
	 * TIPMERESG
	 */
	public String tipoEsgotamento;

	/**
	 * QDECXAFRN
	 */
	public Integer quantidadeCaixaFracionada;

	public Date dataResultado;

	public Integer qtdMercadoriaNegociada;

	public List<Item> itensPedido;

	public List<Item> listaItens;

	public Integer filialExpedicao;

	public Integer filialFaturamento;

	public Integer numNotaFiscal;

	/**
	 * CODCNDPGT
	 */
	public Integer condicaoPagamento;

	public BigDecimal percAcaoTatica;

	public BigDecimal valorSemEncargos;

	public BigDecimal valorTotalSemEncargos;

	public Integer quantidadeMercadoria;

	public BigDecimal valorLiquidoImp;

	public BigDecimal valorTotalStb;

	public BigDecimal valorTotalIpi;

	public Integer diasPrazoBeneficiario;

	public BigDecimal valorFrete;

	public BigDecimal perMaxFlex;

	public BigDecimal perDescontoFlex;

	public BigDecimal perMaxSimples;

	public BigDecimal valorBeneficioMercadoria;

	public Integer numParcelas;

	public Integer qtdDiasPrazo;

	public Integer periodoParcelas;

	public BigDecimal valorMinimo;

	public BigDecimal fatorCondicaoPagamento;

	public BigDecimal valorPoupeCerto;

	public BigDecimal valorPoupeCertoBND;

	public BigDecimal valorBonificacao;

	public BigDecimal valorLiqImpFrete;

	public BigDecimal valorLiquidoMercadoria;

	public BigDecimal valorNotaFiscal;

	public BigDecimal valorNotaFiscalSemEncargos;

	public BigDecimal valorEncargosVendor;

	public BigDecimal totalParcelas;

	public Integer periodo;

	public String mercadoriaCortada;

	public String codigoSegmento;

	public Integer codigoTerritorio;

	public BigDecimal perDescontoBeneficio;

	public Beneficio beneficio;

	public String numeroPedidoCliente;

	public String codigoNegociacao;

	// TIPVNDPED
	// public Integer tipoVendaPedido;
	public TipoVendaPedido tipoVendaPedido ;

	public PedidoStatus statusPedido;

	public Integer codigoModeloDistribuicao;

	// TIPPEDCOT
	public String tipoPedido;

	public Cliente cliente;

	// Lista de mensagens que serao exibidas caso ocorram erro na validacao do
	// pedido.
	public List<PedidoMensagem> mensagens = new ArrayList<PedidoMensagem>();

	public Integer pontuacaoMinima;

	public Integer valorTotalPontuacaoPedido;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numeroPedido", numeroPedido);
		map.put("codigoCliente", codigoCliente);
		map.put("positivar", DateUtil.formataData(dataPedido, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("nomeCliente", nomeCliente);
		map.put("dataPedido", DateUtil.formataData(dataPedido, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("preparado", preparado);
		map.put("horaPedido", horaPedido);
		map.put("dataResultado", DateUtil.formataData(dataResultado, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("valorTotalPedido", null != valorTotalPedido ? valorTotalPedido.toString() : valorTotalPedido); // StringUtil.formataMonetario(valorTotalPedido));
		map.put("quantidadeItens", quantidadeTotalItens);
		map.put("itensPedido", itensPedido);
		map.put("filialExpedicao", filialExpedicao);
		map.put("filialFaturamento", filialFaturamento);
		map.put("numNotaFiscal", numNotaFiscal);
		map.put("condicaoPagamento", condicaoPagamento);
		map.put("percAcaoTatica", percAcaoTatica);
		map.put("valorSemEncargos", null != valorSemEncargos ? valorSemEncargos.toString() : valorSemEncargos); // StringUtil.formataMonetario(valorSemEncargos));
		map.put("valorTotalSemEncargos", null != valorTotalSemEncargos ? valorTotalSemEncargos.toString() : valorTotalSemEncargos); // StringUtil.formataMonetario(valorTotalSemEncargos));
		map.put("qtdeMercadoria", quantidadeMercadoria);
		map.put("valorLiquidoImp", null != valorLiquidoImp ? valorLiquidoImp.toString() : valorLiquidoImp);
		map.put("valorTotalStb", null != valorTotalStb ? valorTotalStb.toString() : valorTotalStb);
		map.put("valorTotalIpi", null != valorTotalIpi ? valorTotalIpi.toString() : valorTotalIpi);
		map.put("diasPrazoBeneficiario", diasPrazoBeneficiario);
		map.put("valorFrete", null != valorFrete ? valorFrete.toString() : valorFrete);
		map.put("perMaxFlex", perMaxFlex);
		map.put("perDescontoFlex", perDescontoFlex);
		map.put("perMaxSimples", perMaxSimples);
		map.put("valorBeneficioMercadoria", null != valorBeneficioMercadoria ? valorBeneficioMercadoria.toString() : valorBeneficioMercadoria);
		map.put("numParcelas", numParcelas);
		map.put("qtdDiasPrazo", qtdDiasPrazo);
		map.put("periodoParcelas", periodoParcelas);
		map.put("valorMinimo", null != valorMinimo ? valorMinimo.toString() : valorMinimo);
		map.put("fatorCondicaoPagamento", fatorCondicaoPagamento);
		map.put("valorPoupeCerto", null != valorPoupeCerto ? valorPoupeCerto.toString() : valorPoupeCerto);
		map.put("valorPoupeCertoBND", null != valorPoupeCertoBND ? valorPoupeCertoBND.toString() : valorPoupeCertoBND);
		map.put("valorLiqImpFrete", null != valorLiqImpFrete ? valorLiqImpFrete.toString() : valorLiqImpFrete);
		map.put("valorLiquidoMercadoria", null != valorLiquidoMercadoria ? valorLiquidoMercadoria.toString() : valorLiquidoMercadoria);
		map.put("valorNotaFiscal", null != valorNotaFiscal ? valorNotaFiscal.toString() : valorNotaFiscal);
		map.put("valorNotaFiscalSemEncargos", null != valorNotaFiscalSemEncargos ? valorNotaFiscalSemEncargos.toString() : valorNotaFiscalSemEncargos);
		map.put("valorEncargosVendor", null != valorEncargosVendor ? valorEncargosVendor.toString() : valorEncargosVendor);
		map.put("totalParcelas", StringUtil.formataMonetario(totalParcelas));
		map.put("periodo", periodo);
		map.put("qtdMercadoriaNegociada", qtdMercadoriaNegociada);
		map.put("qtdMercadoriaPedida", qtdMercadoriaPedida);
		map.put("valorBonificacao", null != valorBonificacao ? valorBonificacao.toString() : valorBonificacao);
		map.put("percentualIcmsMercadoria", percentualIcmsMercadoria);
		map.put("tipoEsgotamento", tipoEsgotamento);
		map.put("quantidadeCaixaFracionada", quantidadeCaixaFracionada);
		map.put("mercadoriaCortada", mercadoriaCortada);
		map.put("codigoSegmento", codigoSegmento);
		map.put("codigoTerritorio", codigoTerritorio);
		map.put("beneficio", beneficio);
		map.put("numeroPedidoCliente", numeroPedidoCliente);
		map.put("codigoNegociacao", codigoNegociacao);
		map.put("tipoVendaPedido", null != tipoVendaPedido ? tipoVendaPedido.getValue() : tipoVendaPedido);
		map.put("tipoPedido", tipoPedido);
		map.put("codigoModeloDistribuicao", codigoModeloDistribuicao);
		map.put("mensagens", mensagens);
		map.put("pontuacaoMinima", pontuacaoMinima);
		map.put("pontuacaoTotalPedido", valorTotalPontuacaoPedido);
		return map;
	}

}
