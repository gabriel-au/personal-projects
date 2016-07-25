package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class Beneficio extends BaseVo {

	private static final long serialVersionUID = 3631142859961984337L;

	public BigDecimal descontoBeneficio;
	public BigDecimal descontoSimplificado;
	public BigDecimal percentualComissao; // PERCMSADI
	public BigDecimal percentualDescontoTemp; // PERDSCBFC
	public BigDecimal percentualMaximoCompra; // PERMAXCMP
	public BigDecimal percentualMaximoPontos; // PERMAXPTO
	public BigDecimal percentualMaximoQuantidade; // PERMAXQDE
	public BigDecimal percentualMinimoCompra; // PERMNMCMP
	public BigDecimal percentualMinimoPontos; // PERMNMPTO
	public BigDecimal percentualMinimoQuantidade; // PERMNMQDE
	public BigDecimal razaoCompraPedidoCalculado;
	public BigDecimal valorBaseCompra; // VLRBSECMP
	public BigDecimal valorMaximoCompra; // VLRMAXCMP
	public BigDecimal valorMaximoPedido; // VLRMAXPED
	public BigDecimal valorMinimoCompra; // VLRMNMCMP
	public BigDecimal valorMinimoPedido; // VLRMNMPED
	public BigDecimal valorReferenciaTotalPedido;
	
	public Boolean isAplicaBeneficio;
	public Boolean isFaixaLiberada;
	
	public Date dataFimValidade; // DATFIMVLD
	public Date dataInicioValidade; // DATINIVLD
	
	public Integer codigoBeneficio; // CODBFC
	public Integer codigoGrupoCliente; // CODGRPCLI
	public Integer indicaFaixaObrigatoria; // INDOBRFXA
	public Integer isPrazoFixo;
	public Integer numeroSequencialFaixa; // NUMSEQFXA
	public Integer numeroSequencialFaixaBFX;
	public Integer prazo;
	public Integer quantidadeBaseCompra; // QDEBSECMP
	public Integer quantidadeDiaPrazoTemp; // QDEINFPRZ
	public Integer quantidadeItensPedido; // Totais conforme criacao do pedido
	public Integer quantidadeMaximaCompra; // QDEMAXCMP
	public Integer quantidadeMaximaPedido; // QDEMAXPED
	public Integer quantidadeMaximaPontos; // QDEMAXPTO
	public Integer quantidadeMinimaCompra; // QDEMNMCMP
	public Integer quantidadeMinimaPedido; // QDEMNMPED
	public Integer quantidadeMinimaPontos; // QDEMNMPTO
	public Integer quantidadePrazoRealTemp; // QDEPRZREAL
	public Integer quantidadeTotalPontosPedido; // Totais conforme criacao do pedido
	public Integer tipoBeneficio; // TIPBFC
	public Integer tipoCondicaoPagamento; // TIPCNDPGT
	
	public String descricaoBeneficio; // DESBFC
	public String descricaoBeneficioTemp; // PERDSCBFX

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoBeneficio", codigoBeneficio);
		propriedades.put("descricaoBeneficio", descricaoBeneficio);
		propriedades.put("dataInicioValidade", StringUtil.formataData(dataInicioValidade, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("dataFimValidade", StringUtil.formataData(dataFimValidade, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("prazo", prazo);
		propriedades.put("descontoBeneficio", descontoBeneficio != null ? StringUtil.formataDecimal(descontoBeneficio, 2) : .0 );
		propriedades.put("descontoSimplificado", descontoSimplificado);
		propriedades.put("tipoCondicaoPagamento", tipoCondicaoPagamento);
		propriedades.put("codigoGrupoCliente", codigoGrupoCliente);
		propriedades.put("isFaixaLiberada", isFaixaLiberada);
		propriedades.put("isAplicaBeneficio", isAplicaBeneficio);
		propriedades.put("numeroSequencialFaixaBFX", numeroSequencialFaixaBFX);
		propriedades.put("numeroSequencialFaixa", numeroSequencialFaixa);
		propriedades.put("valorMinimoCompra", StringUtil.formataMonetario(valorMinimoCompra));
		propriedades.put("quantidadeMinimaPontos", quantidadeMinimaPontos);
		propriedades.put("quantidadeMinimaPedido", quantidadeMinimaPedido);
		propriedades.put("indicaFaixaObrigatoria", indicaFaixaObrigatoria);
		propriedades.put("percentualMaximoPontos", percentualMaximoPontos);
		propriedades.put("percentualMinimoPontos", percentualMinimoPontos);
		propriedades.put("percentualDescontoTemp", percentualDescontoTemp != null ? StringUtil.formataDecimal(percentualDescontoTemp, 2) : .0 );
		propriedades.put("percentualComissao", percentualComissao != null ? StringUtil.formataDecimal(percentualComissao, 2) : .0);
		propriedades.put("tipoBeneficio", tipoBeneficio);
		propriedades.put("isPrazoFixo", isPrazoFixo);
		propriedades.put("valorReferenciaTotalPedido", StringUtil.formataMonetario(valorReferenciaTotalPedido));
		propriedades.put("quantidadeTotalPontosPedido", quantidadeTotalPontosPedido);
		propriedades.put("quantidadeItensPedido", quantidadeItensPedido);
		propriedades.put("razaoCompraPedidoCalculado", razaoCompraPedidoCalculado != null ? StringUtil.formataDecimal(razaoCompraPedidoCalculado, 2) : .0);
		propriedades.put("quantidadeMinimaCompra", quantidadeMinimaCompra);
		return propriedades;
	}
}
