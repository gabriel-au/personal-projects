package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class FilialCliente extends BaseVo {

	private static final long serialVersionUID = -7261674470274261239L;

	/**
	 * CODCLI
	 */
	public Cliente cliente;

	/**
	 * CODFILEPD
	 */
	public Filial filialExpedicao;

	/**
	 * CODFILFAT
	 */
	public Filial filialFaturamento;

	/**
	 * CODREGDTB
	 */

	/**
	 * CODPRRFIL
	 */

	/**
	 * CODPRRFIL
	 */

	/**
	 * TIPDTBFIL
	 */

	/**
	 * INDVNDCXA
	 */
	public boolean indicadorVendaCaixa;
	
	
	public String siglaEstadoOrigem;
	
	public String siglaEstadoDestino;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		
		propriedades.put("cliente", cliente.toMap());
		propriedades.put("filialExpedicao", filialExpedicao.toMap());
		propriedades.put("filialFaturamento", filialFaturamento.toMap());
		propriedades.put("indicadorVendaCaixa", indicadorVendaCaixa);
		
		propriedades.put("siglaEstadoOrigem", siglaEstadoOrigem);
		propriedades.put("siglaEstadoDestino", siglaEstadoDestino);
		
		
		return propriedades;
	}
}