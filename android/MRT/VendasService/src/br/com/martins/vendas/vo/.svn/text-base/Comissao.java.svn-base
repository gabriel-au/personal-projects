package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Comissao extends BaseVo{

	private static final long serialVersionUID = 7894869176606138751L;


	public BigDecimal percentualBaseSimbolo;
	public BigDecimal rateioComissaoAcao;
	public BigDecimal rateioComissaoSimplificado;
	public BigDecimal rateioComissaoAcrescimo;
	public BigDecimal fatorAcrescimoComissao;
	public BigDecimal percentualApropriacaoTLV;
	public BigDecimal percentualRateioBandaInferior;
	public BigDecimal percentualRateioBandaSuperior;
	public BigDecimal percentualRateioBandaAcao;
	
	// TODO OS ATRIBUTOS ABAIXO NAO PERTENCEM A COMISSAO
	// TODO REFATORAR E UTILIZAR OS OBJETOS QUE JA POSSUEM TAIS ATRIBUTOS
	public BigDecimal comissaoMercadoria;
	public BigDecimal comissaoMaximaMercadoria;
	public BigDecimal percentualComissaoSimbolo;
	public String codigoSimboloSituacao;
	public BigDecimal valorVendaMercadoria;
	public BigDecimal valorComissaoSimbolo;
	public BigDecimal comissaoConcedida;
	public BigDecimal valorComissaoVariavel;
	public BigDecimal valorComissaoTotal;
	public BigDecimal resultado;
	public BigDecimal percentualAcrescimoConcedido;
	public BigDecimal valorBrutoTMP;
	public Integer tipoNegociacao;
	public String codigoDestino;
	public String codigoOrigem;
	public BigDecimal percentualAcaoCliente;
	
	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("percentualBaseSimbolo", percentualBaseSimbolo);
		propriedades.put("rateioComissaoAcao", rateioComissaoAcao);
		propriedades.put("rateioComissaoSimplificado", rateioComissaoSimplificado);
		propriedades.put("rateioComissaoAcrescimo", rateioComissaoAcrescimo);
		propriedades.put("fatorAcrescimoComissao", fatorAcrescimoComissao);
		propriedades.put("percentualApropriacaoTLV", percentualApropriacaoTLV);
		propriedades.put("percentualRateioBandaInferior", percentualRateioBandaInferior);
		propriedades.put("percentualRateioBandaSuperior", percentualRateioBandaSuperior);
		propriedades.put("percentualRateioBandaAcao", percentualRateioBandaAcao);
		propriedades.put("comissaoMaximaMercadoria", comissaoMaximaMercadoria);
		propriedades.put("comissaoMercadoria", comissaoMercadoria);
		propriedades.put("percentualComissaoSimbolo", percentualComissaoSimbolo);
		propriedades.put("codigoSimboloSituacao", codigoSimboloSituacao);
		propriedades.put("valorVendaMercadoria", valorVendaMercadoria);
		propriedades.put("valorComissaoSMB", valorComissaoSimbolo);
		propriedades.put("comissaoConcedida", comissaoConcedida);
		propriedades.put("valorComissaoVariavel", valorComissaoVariavel);
		propriedades.put("valorComissaoTotal", valorComissaoTotal);
		propriedades.put("resultado", resultado);
		propriedades.put("percentualAcrescimoConcedido", percentualAcrescimoConcedido);
		propriedades.put("valorBrutoTMP", valorBrutoTMP);
		propriedades.put("tipoNegociacao", tipoNegociacao);
		propriedades.put("codigoDestino", codigoDestino);
		propriedades.put("codigoOrigem", codigoOrigem);
		propriedades.put("percentualAcaoCliente", percentualAcaoCliente);
		return propriedades;

	}

}
