package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class AvisosCliente extends BaseVo implements Comparable<AvisosCliente>{

	private static final long serialVersionUID = 5056974437880122282L;

	/**
	 * CODCLI
	 */
	public String codigoCliente;

	/**
	 * NOMCLI
	 */
	public String nomeCliente;

	/**
	 * DATRVSCAD
	 */
	public Date dataRevisaoCadastro;

	/**
	 * DESMTVRVS
	 */
	public String descricaoMotivoRevisao;

	/**
	 * QDEMESRVS
	 */
	public Integer quantidadeMesRevisao;

	/**
	 * DIASRESTANTES
	 */
	public Integer diasRestantes;

	/**
	 * VLRLIMCRD
	 */
	public Double valorLimiteCredito;

	/**
	 * NOMATI
	 */
	public String nomeAtividade;
	
	public String tituloAviso;

	public String conteudoAviso;

	public Date dataAviso;

	@Override
	public Map<String, Object> toMap() {
	
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoCliente", codigoCliente);
		propriedades.put("nomeCliente", nomeCliente);
		propriedades.put("dataRevisaoCadastro", StringUtil.formataData(dataRevisaoCadastro, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("nomeRepresentante", descricaoMotivoRevisao);
		propriedades.put("quantidadeMesRevisao", quantidadeMesRevisao);
		propriedades.put("diasRestantes", diasRestantes);
		propriedades.put("tituloAviso", tituloAviso);
		propriedades.put("conteudoAviso", conteudoAviso);
		propriedades.put("dataAviso", StringUtil.formataData(dataAviso, "dd/MM/yyyy HH:mm"));
		return propriedades;

	}

	@Override
	public int compareTo(AvisosCliente aviso) {
		return this.nomeCliente.compareToIgnoreCase(aviso.nomeCliente);
	}
}
