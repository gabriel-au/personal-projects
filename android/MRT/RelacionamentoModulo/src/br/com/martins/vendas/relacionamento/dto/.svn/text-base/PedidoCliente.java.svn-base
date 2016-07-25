package br.com.martins.vendas.relacionamento.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class PedidoCliente extends BaseVo {

	private static final long serialVersionUID = -8479315485640301254L;

	public Integer codigoCliente;
	public String nomeFantasia;
	public String nomeCliente;
	public String indicativoFavorito;
	public String statusAtendimento;
	public Date positivar;
	public String indicativoTop;
	public String situacao;
	public Integer canal;
	public String mensagem;
	public boolean bloqueado;
	public String motivoBloqueio;
	public String telefone;
	public Integer codigoTerritorio;
	public String codigoSegmento;
	public Integer diferencaDias;
	public String tipoContratoVendor;
	public String tipoAnaliseCredito;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("codigoCliente", codigoCliente);
		map.put("nomeFantasia", nomeFantasia);
		map.put("nomeCliente", nomeCliente);
		map.put("indicativoFavorito", indicativoFavorito);
		map.put("statusAtendimento", statusAtendimento);
		map.put("positivar", DateUtil.formataData(positivar, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("indicativoTop", indicativoTop);
		map.put("situacao", situacao);
		map.put("canal", canal);
		map.put("mensagem", mensagem);
		map.put("bloqueado", bloqueado);
		map.put("motivoBloqueio", motivoBloqueio);
		map.put("telefone", telefone);
		map.put("codigoTerritorio", codigoTerritorio);
		map.put("codigoSegmento", codigoSegmento);
		map.put("diferencaDias", diferencaDias);
		map.put("tipoContratoVendor", tipoContratoVendor);
		map.put("tipoAnaliseCredito", tipoAnaliseCredito);
		
		return map;
	}

}
