package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class Gondola extends BaseVo {

	private static final long serialVersionUID = -7446479631756583676L;

	public String tipoPromocao; // TIPPMC
	public Integer posicaoMercadoria; // POSMER
	public Integer codigoMercadoria; // CODMER
	public Integer quantidadeMercadoriaPedido; // QDEMERPED
	public String descricaoMercadoria; // DESMER
	public Integer codigoFilialFaturamento; // CODFILFAT
	public String tipoMercadoriaEsgotamento; // TIPMERESG
	public BigDecimal valorUnitarioImposto; // VLRUNTIMP
	public BigDecimal precoCliente; // PCOCLI
	public BigDecimal margemLucroCliente; // MRGLCRCLI
	public Integer quantidadeMercadoriaAnterior; // QDEMERANT
	public Integer quantidadeMercadoriaAtual; // QDEMERATU
	public Integer quantidadeCaixaFornecedor; // QDECXAFRN
	public BigDecimal valorLiquidoMercadoria; // VLRLIQMER
	public BigDecimal valorLiquidoImposto; // VLRLIQIMP
	public BigDecimal valorCaixaImposto; // UNTCXAIMP
	public String tipoMarcacaoRepresentante; // TIPMCOREP
	public String flagPee; // FLGPEE
	public Integer codigoCondicaoPagamento; // CODCNDPGT
	
	// DATULTVND - Vem formatada do banco (dd/MM/yyyy) - tabela TMPMEGO , na tabela PCABGM (yyyyMMdd)
	public String dataUltimaVenda; // DATULTVND
	
	public BigDecimal valorFreteMercadoria; // VLRFRTMER
	public Integer numeroNotaFiscal; // NUMNOTFSC
	public String tipoIncentivo; // TIPICT
	public String codigoFlexivelPreco; // CODFLXPCO
	public String codigoSimboloSituacao; // CODSMBSIT
	public BigDecimal percentualIcmsMercadoria; // PERICMMER
	public Integer codigoFilialExpedicao; // CODFILEPD
	public BigDecimal valorStbMercadoria; // VLRSTBMER
	public BigDecimal comissaoConcessaoMercadoria; // CMSCNSMER
	public String descricaoNegociacaoMercadoria; // DESNGCMER
	public String valorPontosMercadoria; // VLRPTOMER
	public String descricaoComplementarMercadoria; // DESMER
	public Integer codigoCliente; // CODCLI
	public Date dataAtual;
	public String codigoChaveRegistro;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tipoPromocao", tipoPromocao);
		map.put("posicaoMercadoria", posicaoMercadoria);
		map.put("codigoMercadoria", codigoMercadoria);
		map.put("quantidadeMercadoriaPedido", quantidadeMercadoriaPedido);
		map.put("descricaoMercadoria", descricaoMercadoria);
		map.put("codigoFilialFaturamento", codigoFilialFaturamento);
		map.put("tipoMercadoriaEsgotamento", tipoMercadoriaEsgotamento);
		map.put("valorUnitarioImposto", StringUtil.formataDecimal(valorUnitarioImposto, 4));
		map.put("precoCliente", StringUtil.formataDecimal(precoCliente));
		map.put("margemLucroCliente", StringUtil.formataDecimal(margemLucroCliente));
		map.put("quantidadeMercadoriaAnterior", quantidadeMercadoriaAnterior);
		map.put("quantidadeMercadoriaAtual", quantidadeMercadoriaAtual);
		map.put("quantidadeCaixaFornecedor", quantidadeCaixaFornecedor);
		map.put("valorLiquidoMercadoria", StringUtil.formataDecimal(valorLiquidoMercadoria));
		map.put("valorLiquidoImposto", StringUtil.formataDecimal(valorLiquidoImposto));
		map.put("valorCaixaImposto", StringUtil.formataDecimal(valorCaixaImposto, 4));
		map.put("tipoMarcacaoRepresentante", tipoMarcacaoRepresentante);
		map.put("flagPee", flagPee);
		map.put("codigoCondicaoPagamento", codigoCondicaoPagamento);
		map.put("dataUltimaVenda", dataUltimaVenda);
		map.put("valorFreteMercadoria", StringUtil.formataDecimal(valorFreteMercadoria));
		map.put("numeroNotaFiscal", numeroNotaFiscal);
		map.put("tipoIncentivo", tipoIncentivo);
		map.put("codigoFlexivelPreco", codigoFlexivelPreco);
		map.put("codigoSimboloSituacao", codigoSimboloSituacao);
		map.put("percentualIcmsMercadoria", StringUtil.formataDecimal(percentualIcmsMercadoria));
		map.put("codigoFilialExpedicao", codigoFilialExpedicao);
		map.put("valorStbMercadoria", StringUtil.formataMonetario(valorStbMercadoria));
		map.put("comissaoConcessaoMercadoria", StringUtil.formataDecimal(comissaoConcessaoMercadoria));
		map.put("descricaoNegociacaoMercadoria", descricaoNegociacaoMercadoria);
		map.put("valorPontosMercadoria", StringUtil.formataDecimal(valorPontosMercadoria));
		map.put("descricaoComplementarMercadoria", descricaoComplementarMercadoria);
		map.put("codigoCliente", codigoCliente);
		return map;
	}

}