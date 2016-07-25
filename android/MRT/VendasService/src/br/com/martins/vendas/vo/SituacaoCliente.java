package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class SituacaoCliente extends BaseVo {

	private static final long serialVersionUID = -4496416857076665098L;

	/**
	 * Tabela PCACLI
	 */

	/**
	 * CODCLI
	 */
	public Integer codigoCliente;

	/**
	 * NOMCLI
	 */
	public String nomeCliente;

	/**
	 * Tabela PCAMSC
	 */
	
	/**
	 * DATALTSIT
	 */
	public Date dataSituacao;

	/**
	 * Tabela PCAMTV
	 */
	
	/**
	 * TIPANLCRD (L - Liberado , B - Bloqueado ou Análise)
	 */
	public String situacaoPedido;

	/**
	 * TIPMTVSIT
	 */
	public String tipoSituacaoCliente;

	/**
	 * Descrição da coluna TIPMTVSIT (C - Cortado, S - Suspenso ou Demais - Normal)
	 */
	public String situacaoCliente;

	/**
	 * Tabela PCACOP
	 */
	
	/**
	 * CODFILEPD
	 */
	public Integer codigoFilialExpedicao;

	public String nomeFantasia;
	
	public String descricaoSituacao;
	
	public String descricaoJustificativa;
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigoCliente", codigoCliente);
		map.put("nomeCliente", nomeCliente);
		map.put("dataSituacao", DateUtil.formataData(dataSituacao, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("situacaoPedido", situacaoPedido);
		map.put("tipoSituacaoCliente", tipoSituacaoCliente);
		map.put("situacaoCliente", situacaoCliente);
		map.put("codigoFilialExpedicao", codigoFilialExpedicao);
		map.put("nomeFantasia", nomeFantasia);
		map.put("descricaoSituacao", descricaoSituacao);
		map.put("descricaoJustificativa", descricaoJustificativa);
		return map;
	}

}