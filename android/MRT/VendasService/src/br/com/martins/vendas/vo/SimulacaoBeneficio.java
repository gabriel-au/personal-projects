package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class SimulacaoBeneficio extends BaseVo {

	private static final long serialVersionUID = -8187420767004562839L;

	public BigDecimal percentualSimulacaoBeneficio;
	
	public String mensagemSimulacaoBeneficio;
	
	public Integer tipoMensagemSimulacaoBeneficio;
	
	public BigDecimal percentualDescontoFlexivel;
	public BigDecimal percentualDescontoItem;
	public BigDecimal percentualAcrescimoConcedido;

	public Integer codigoMercadoria;

	public Integer codigoCliente;

	public Integer codigoFilialExpedicao;

	public Integer codigoFilialFaturamento;

	public Integer codigoTerritorioVenda;

	public Boolean continuacaoSimulacaoPreco;
	
	public Boolean isSimulacaoCaixa;

	public Integer tipoVendaPedido;

	public Integer tipoNegociacao;
	
	public BigDecimal precoLiquidoSimulado;
	public BigDecimal precoLiquidoUnitarioSimulado;

	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("percentualSimulacaoBeneficio", percentualSimulacaoBeneficio);
		propriedades.put("percentualSimulacaoBeneficioFormatado", StringUtil.formataDecimal(percentualSimulacaoBeneficio,2));
		propriedades.put("mensagemSimulacaoBeneficio", mensagemSimulacaoBeneficio);
		propriedades.put("tipoMensagemSimulacaoBeneficio", tipoMensagemSimulacaoBeneficio);
		propriedades.put("percentualDescontoFlexivel", percentualDescontoFlexivel);
		propriedades.put("percentualDescontoItem", percentualDescontoItem);
		propriedades.put("percentualAcrescimoConcedido", percentualAcrescimoConcedido);
		propriedades.put("codigoMercadoria", codigoMercadoria);
		propriedades.put("codigoCliente", codigoCliente);
		propriedades.put("codigoFilialExpedicao", codigoFilialExpedicao);
		propriedades.put("codigoFilialFaturamento", codigoFilialFaturamento);
		propriedades.put("codigoTerritorioVenda", codigoTerritorioVenda);
		propriedades.put("continuacaoSimulacaoPreco", continuacaoSimulacaoPreco);
		propriedades.put("tipoVendaPedido", tipoVendaPedido);
		propriedades.put("tipoNegociacao", tipoNegociacao);
		propriedades.put("precoLiquidoSimulado", StringUtil.formataMonetario(precoLiquidoSimulado));
		propriedades.put("precoLiquidoUnitarioSimulado", StringUtil.formataMonetario(precoLiquidoUnitarioSimulado, 4));
		return propriedades;
	}

}
