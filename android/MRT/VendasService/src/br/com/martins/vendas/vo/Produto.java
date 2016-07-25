package br.com.martins.vendas.vo;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Produto extends BaseVo{

	private static final long serialVersionUID = 2203420119737424328L;

	public Integer codigo;
	
	public String nomeProduto;
	
	public Double precoLiquido;
	
	public Double valorFrete;
	
	public Integer caixa;
	
	public Integer filial;
	
	public Integer cad;
	
	public Double icm;
	
	public Integer notaFiscal;
	
	public String tipoEstoque;
	
	public String simbolo;
	
	public Integer prazo;
	
	public String stb;
	
	public Integer modeloDistribuicao;
	
	public String incentivo;
	
	public Integer codigoFlex;
	
	public Double valorComissao;
	
	public String descNegociacao;
	
	public Integer pontos;
	
	public Integer condPagamento;
	
	public Double valorIpiMercadoria;
	
	public Double valorStbMercadoria;
	
	public Integer fatorConversao;
	
	public Double valorLiqCaixa;
	
	public Double valorPltIpi;
	
	public Date dataInicialVigencia;
	
	public Integer tipoSTB;
	
	public Double perCredito;
	
	public Integer qtdMercadoria;
	
	public Double perIpiMercadoria;
	
	public Double perDesconto;
	
	public String flagValorMnm;
	
	public Double perCreditoStb;
	
	public Double perDebitoStb;
	
	public Double fatorReducaoValor;
	
	public Double margemLucro;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigo", codigo);
		propriedades.put("nomeMercadoria", nomeProduto);
		propriedades.put("precoLiquido", precoLiquido);
		propriedades.put("valorFrete", valorFrete);
		propriedades.put("caixa", caixa);
		propriedades.put("cad", cad);
		propriedades.put("icm", icm);
		propriedades.put("notaFiscal", notaFiscal);
		propriedades.put("tipoEstoque", tipoEstoque);
		propriedades.put("simbolo", simbolo);
		propriedades.put("prazo", prazo);
		propriedades.put("stb", stb);
		propriedades.put("modeloDistribuicao", modeloDistribuicao);
		propriedades.put("incentivo", incentivo);
		propriedades.put("codigoFlex", codigoFlex);
		propriedades.put("valorComissao", valorComissao);
		propriedades.put("descNegociacao", descNegociacao);
		propriedades.put("pontos", pontos);
		propriedades.put("condPagamento", condPagamento);
		propriedades.put("valorIpiMercadoria", valorIpiMercadoria);
		propriedades.put("valorStbMercadoria", valorStbMercadoria);
		propriedades.put("fatorConversao", fatorConversao);
		propriedades.put("valorLiqCaixa", valorLiqCaixa);
		propriedades.put("valorPltIpi", valorPltIpi);
		propriedades.put("dataInicialVigencia", dataInicialVigencia);
		propriedades.put("tipoSTB", tipoSTB);
		propriedades.put("perCredito", perCredito);
		propriedades.put("qtdMercadoria", qtdMercadoria);
		propriedades.put("perIpiMercadoria", perIpiMercadoria);
		propriedades.put("perDesconto", perDesconto);
		propriedades.put("flagValorMnm", flagValorMnm);
		propriedades.put("perCreditoStb", perCreditoStb);
		propriedades.put("perDebitoStb", perDebitoStb);
		propriedades.put("fatorReducaoValor", fatorReducaoValor);
		propriedades.put("margemLucro", margemLucro);
		
		return propriedades;
	}

}
