package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class Titulo extends BaseVo {

	private static final long serialVersionUID = 7968921066472888701L;

	/**
	 * DATVNCTIT
	 */
	public Date dataVencimento;

	/**
	 * NOMCLI
	 */
	public String nomeCliente;

	/**
	 * VLRSLDABT
	 */
	public BigDecimal valorSaldoAberto;

	/**
	 * VENCIDO = 2 A VENCER = 3
	 * 
	 */
	public Integer idSituacaoTitulo;

	/*
	 * CODCLI
	 */
	public Integer codCliente;
	
	/*
	 * TIPDOCTIT
	 */
	public String tipoTitulo;
	
	/*
	 * CODESTTIT
	 */
	public String codEstadoTitulo;
	
	/*
	 * NUMDOCTIT
	 */
	public Integer numeroCodTitulo;
	
	public String idTipoRegiao;
	
	public Integer diasVencidos;
	
	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("dataVencimento", StringUtil.formataData(dataVencimento, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("nomeCliente", nomeCliente);
		propriedades.put("idSituacaoTitulo", idSituacaoTitulo);
		propriedades.put("valorSaldoAberto", StringUtil.formataMonetario(valorSaldoAberto));
		propriedades.put("codCliente", codCliente);
		propriedades.put("tipoTitulo", tipoTitulo);
		propriedades.put("codEstadoTitulo", codEstadoTitulo);
		propriedades.put("numeroCodTitulo",numeroCodTitulo);
		propriedades.put("idTipoRegiao",idTipoRegiao);
		propriedades.put("diasVencidos",diasVencidos);
		
		return propriedades;

	}

}
