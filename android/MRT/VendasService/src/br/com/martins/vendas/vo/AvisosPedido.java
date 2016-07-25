package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class AvisosPedido extends BaseVo {

	private static final long serialVersionUID = 1391347478860947022L;

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

	public String mensagemAviso;

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
		propriedades.put("mensagemAviso", mensagemAviso);
		propriedades.put("dataAviso", StringUtil.formataData(dataAviso, "HH:mm dd/MM/yyyy"));
		return propriedades;
	}

}
