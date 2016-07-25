package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class Promocao extends BaseVo {

	private static final long serialVersionUID = 1630452588636026396L;

	/**
	 * CODPMC - Tabela PCAPRM
	 */
	public Integer codigo;
	
	/**
	 * NUMSEQGRP
	 */
	public Integer numeroSequencialGrupo;
	
	/**
	 *  TIPPMC - Tabela PCAPRC
	 */
	public String tipo;
	
	/**
	 *  CODPMCPAD - Tabela PCAPRC
	 */
	public Integer codigoPromocaoPadrao;
	
	/**
	 *  NUMMNMITE - Tabela PCAPGP
	 */
	public Integer numeroMinimoItem; 
	
	/**
	 * VLRVNDPRM
	 */
	public BigDecimal valorVenda;
	
	/**
	 * CODFILEPD - Tabela CODFILEPD
	 */
	public Integer codigoFilialExpedicao;
	
	/**
	 * DATFIMPMC
	 */
	public Date dataTerminoPromocao;
	
	/**
	 * DESPMC
	 */
	public String descricaoPromocao;
	
	/**
	 * INDPRMFRC
	 */
	public String indicaPremioFracionado;
	
	/**
	 * CODUNDESR
	 */
	public Integer codigoUnidadeEstrategia;
	
	/**
	 * NROPUBALV
	 */
	public Integer numeroPublicoAlvo;

	
	public BigDecimal m_dblQdeItePrm;
	public BigDecimal m_dblVlrItePmc;
	public BigDecimal m_dblQdeItePmc;
	public Integer m_iCodCtrPmc;
	public BigDecimal m_dblQdeVlrRto;
	
	public Map<String, Promocao> mapaPromocaoGrupo;
	
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigo", codigo);
		map.put("tipo", tipo);
		map.put("valorVenda", valorVenda);
		map.put("codigoFilialExpedicao", codigoFilialExpedicao);
		map.put("dataTerminoPromocao", DateUtil.formataData(dataTerminoPromocao, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("descricaoPromocao", descricaoPromocao);
		map.put("indicaPremioFracionado", indicaPremioFracionado);
		map.put("codigoUnidadeEstrategia", codigoUnidadeEstrategia);
		map.put("numeroPublicoAlvo", numeroPublicoAlvo);
		return map;
	}

}
