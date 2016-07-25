package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class RetornosCliente  extends BaseVo {

	private static final long serialVersionUID = 4497968897449020152L;

	public Cliente cliente;
	
	public Integer numPedido;
	
	public Date dataOcorrencia;
	
	public String horaOcorrencia;
	
	public String descObservacao;
	
	public String descricaoSituacaoPedido;
	
	public String codigoSituacaoPedido;
	
	
	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("cliente", cliente);
		propriedades.put("numPedido", numPedido);
		propriedades.put("dataOcorrencia", DateUtil.formataData(dataOcorrencia, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("horaOcorrencia", horaOcorrencia);
		propriedades.put("descricaoSituacaoPedido", descricaoSituacaoPedido);
		propriedades.put("codigoSituacaoPedido", codigoSituacaoPedido);
		propriedades.put("descObservacao", descObservacao);
		return propriedades;
	}
}
