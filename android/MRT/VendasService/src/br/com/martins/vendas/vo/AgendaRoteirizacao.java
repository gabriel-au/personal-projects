package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class AgendaRoteirizacao extends BaseVo implements Comparable<AgendaRoteirizacao> {

	private static final long serialVersionUID = -5445621067056686799L;

	/**
	 * CODCLI
	 */
	public Integer codigoCliente;
	
	/**
	 * CODUNDESR
	 */
	public Integer codigoUnidadeEstrategia;
	
	/**
	 * CODBAI
	 */
	public Integer codigoBairro;
	
	/**
	 * CODFILEPD
	 */
	public Integer codigoFilialClienteExpedicao;

	/**
	 * CODFILFAT
	 */
	public String codigoFilialClienteFaturamento;

	/**
	 * DATRTZ
	 */
	public Date dataRoteirizacao;

	/**
	 * HRARTZ
	 */
	public String horaRoteirizacao;

	/**
	 * DIASMN
	 */
	public Integer diaSemana;

	public String descricaDiaSemana;

	/**
	 * DIASMN
	 */
	public boolean isCidadeExcessao;

	/**
	 * QDEPRVENT
	 */
	public Integer quantidadeDiasPrevisaoEntrega;
	
	public Integer quantidadeDiasSeguroPedido;

	public String hora;

	public String envio;

	public Date entrega;

	/**
	 * HRASEGPRP
	 */
	public String horaSegurancaProposta;

	@Override
	public Map<String, Object> toMap() {
	
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoFilialClienteExpedicao", codigoFilialClienteExpedicao);
		propriedades.put("codigoFilialClienteFaturamento", codigoFilialClienteFaturamento);
		propriedades.put("dataRoteirizacao", StringUtil.formataData(dataRoteirizacao, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("diaSemana", diaSemana);
		propriedades.put("descricaDiaSemana", descricaDiaSemana);
		propriedades.put("hora", hora);
		propriedades.put("envio", envio);
		propriedades.put("entrega", StringUtil.formataData(entrega, DateUtil.DEFAULT_DATE_PATTERN));
		return propriedades;
	}

	/**
	 * Método utilizado para ordenação do objeto a partir de código expedição e data de roteirização (crescente)
	 * @param agendaRoteirizacao - objeto a ser comparado
	 * @param int - retorno da comparação de igualdade
	 */
	@Override
	public int compareTo(AgendaRoteirizacao agendaRoteirizacao) {
		if(agendaRoteirizacao.codigoFilialClienteExpedicao.compareTo(this.codigoFilialClienteExpedicao) == 0){
			return agendaRoteirizacao.dataRoteirizacao.compareTo(this.dataRoteirizacao)*-1;
		}
		return agendaRoteirizacao.codigoFilialClienteExpedicao.compareTo(this.codigoFilialClienteExpedicao)*-1;
	}

}
