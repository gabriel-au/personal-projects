/**
 * 
 */
package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import com.brq.mobile.framework.core.BaseVo;

/**
 * 
 * TABLE: PCAAGE
 * 
 * @author BRQ Mobile Team
 */
public class RelacaoGiro extends BaseVo {
	
	private static final long serialVersionUID = 8708753722396513576L;

	/**
	 * CODGIR
	 * (Código do Território)
	 */
	public Integer codTerritorio;
	
	/**
	 * CODFILFAT 
	 */
	public Integer codFilialFaturamento;
	
	/**
	 * CODFILEPD	 
	 */
	public Integer codFilialExpedicao;
	
	/**
	 * NUMRLCCID
	 */
	public Integer numRelacaoCidade;
	
	/**
	 * PERDCRICM
	 */
	public BigDecimal  perDecresimoICM;
	
	/**
	 * CODESTDSN 
	 */
	public String codEstadoDestino;
	
	/**
	 * CODSUP 
	 */
	public Integer codSupervisor;
	
	/**
	 * CODGER
	 */
	public Integer codGerente;
	
	/**
	 * CODVRSSIS
	 */
	public BigDecimal codVersaoSistema;
	 
	/**
	 * CODESTORI
	 */
	public String codEstadoOrigem;
	
	/**
	 * PERICMMER
	 */
	public BigDecimal perIcmMercadoria; 
	
	/**
	 * CODEDEVND
	 */
	public Integer codEntidadeVenda;
	
	/**
	 * PERICMORI 
	 */
	public BigDecimal perIcmOrigem;
	
	/**
	 * ICMMERCSM 
	 */
	public BigDecimal imcMecadoriaConsumo;
	
	/**
	 * ICMORICSM 
	 */
	public BigDecimal imcOrigemConsumo;
	
	/**
	 * TIPUTZICM
	 */
	public Integer tipUtilizacaoICM;
 
	/**
	 * INDESTPRC
	 */
	public String indDestinoProcesso;


	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> properties = new TreeMap<String, Object>();
		
		properties.put("codTerritorio",codTerritorio);
		properties.put("codFilialFaturamento",codFilialFaturamento);
		properties.put("codFilialExpedicao",codFilialExpedicao);
		properties.put("numRelacaoCidade",numRelacaoCidade);
		properties.put("perDecresimoICM",perDecresimoICM);
		properties.put("codEstadoDestino",codEstadoDestino);
		properties.put("codSupervisor",codSupervisor);
		properties.put("codGerente",codGerente);
		properties.put("codVersaoSistema",codVersaoSistema);
		properties.put("codEstadoOrigem",codEstadoOrigem);
		properties.put("perIcmMercadoria",perIcmMercadoria);
		properties.put("codEntidadeVenda",codEntidadeVenda);
		properties.put("perIcmOrigem",perIcmOrigem);
		properties.put("imcMecadoriaConsumo",imcMecadoriaConsumo);
		properties.put("imcOrigemConsumo",imcOrigemConsumo);
		properties.put("tipUtilizacaoICM",tipUtilizacaoICM);
		properties.put("indDestinoProcesso",indDestinoProcesso);
		
		return properties;
	}
	
	

}