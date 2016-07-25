package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Imposto;

import com.brq.mobile.framework.core.BaseVo;

public class Preco extends BaseVo {

	private static final long serialVersionUID = 2944008771743465759L;
	
	public BigDecimal valorBrutoUnitario; // Campo VLRUNTBRT tabela PCALVR
	public BigDecimal custoLogistica; // Campo CSTCSTLGT tabela PCALVR
	public BigDecimal percentualICMS; // Campo PERCMSMER tabela PCALVR
	public BigDecimal percentualTributacao; // Campo PERTBTICM
	public BigDecimal valorBrutoCaixa;
	public BigDecimal valorBrutoFracionado;
	public BigDecimal valorBrutoBeneficio;
	public BigDecimal valorBrutoMercadoria;
	public BigDecimal valorBrutoTMP;
	public BigDecimal valorLiquidoMercadoria; // VLRLIQMER
//	public BigDecimal valorLiquidoCaixa;

	public int flagPrecoMonitorado;
	public int flagUtilizaAcaoTatica;
	public int flagUtilizaFlex;
	public int flagUtilizaBeneficios;
	public int flagUtilizaMinimo;
	public int flagUtilizaFracionado;
	public int flagSimboloSituacao;

	public Integer codigoTributacaoICM;

	public String tipoIncentivoMercadoria;
	public String codigoFlexivelPreco;   // CODFLXPCO
	public String codigoSimboloSituacao; // CODSMBSIT

	public Imposto impostoMercadoria;
	public Imposto impostoCaixa;
	public Imposto impostoFlexivel;

	public String flagMercadoriaExclusiva; // FLGMEREXV
	public String flagPontoEncontroEletronico;
	public String tipoMarcacaoRepresentante;

	public BigDecimal percentualComissaoMercadoria; // PERCMSMER

	public BigDecimal percentualComissaoAdicional; // PERCMSADI
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("valorBrutoUnitario", valorBrutoUnitario);
		map.put("custoLogistica", custoLogistica);
		map.put("percentualICMS", percentualICMS);
		map.put("percentualTributacao", percentualTributacao);
		map.put("valorBrutoCaixa", valorBrutoCaixa);
		map.put("valorBrutoFracionado", valorBrutoFracionado);
		map.put("valorBrutoBeneficio", valorBrutoBeneficio);
		map.put("valorBrutoMercadoria", valorBrutoMercadoria);
		map.put("valorBrutoTMP", valorBrutoTMP);
		map.put("flagPrecoMonitorado", flagPrecoMonitorado);
		map.put("flagUtilizaAcaoTatica", flagUtilizaAcaoTatica);
		map.put("flagUtilizaFlex", flagUtilizaFlex);
		map.put("flagUtilizaBeneficios", flagUtilizaBeneficios);
		map.put("flagUtilizaMinimo", flagUtilizaMinimo);
		map.put("flagUtilizaFracionado", flagUtilizaFracionado);
		map.put("flagSimboloSituacao", flagSimboloSituacao);
		map.put("valorLiquidoMercadoria", valorLiquidoMercadoria);
//		map.put("valorLiquidoCaixa", valorLiquidoCaixa);
		map.put("codigoTributacaoICM", codigoTributacaoICM);
		map.put("tipoIncentivoMercadoria", tipoIncentivoMercadoria);
		map.put("codigoFlexivelPreco", codigoFlexivelPreco);
		map.put("codigoSimboloSituacao", codigoSimboloSituacao);
		map.put("impostoMercadoria", impostoMercadoria);
		map.put("impostoCaixa", impostoCaixa);
		map.put("impostoFlexivel", impostoFlexivel);
		map.put("flagPontoEncontroEletronico", flagPontoEncontroEletronico);
		map.put("flagMercadoriaExclusica", flagMercadoriaExclusiva);
		map.put("tipoMarcacaoRepresentante", tipoMarcacaoRepresentante);
		map.put("percentualComissaoMercadoria", percentualComissaoMercadoria);
		return map;
	}

}
