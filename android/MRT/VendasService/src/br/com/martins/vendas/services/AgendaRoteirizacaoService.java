package br.com.martins.vendas.services;

import java.util.List;

import br.com.martins.vendas.dao.AgendaRoteirizacaoDAO;
import br.com.martins.vendas.vo.AgendaRoteirizacao;

public class AgendaRoteirizacaoService {

	/**
	 * Método que lista agenda de roteirização de um determinado cliente
	 * @param codigoCliente - código do cliente
	 * @return  List<AgendaRoteirizacao> - informações da agenda de roteirização
	 * @throws Exception
	 */
	public static List<AgendaRoteirizacao> findAgendaRoteirizacao(Integer codigoCliente) throws Exception {
		return findAgendaRoteirizacao(codigoCliente, null, null, null, null, null, null, false, false);
	}
	
	/**
	 * Método que lista agenda de roteirização de um determinado cliente
	 * @param codigoCliente
	 * @param codigoFilialExpedicao
	 * @param codigoCidade
	 * @param codigoRegraDistribuicao
	 * @param codigoFilialFaturamento
	 * @param codigoBairro
	 * @param codigoUnidadeEstrategia
	 * @param isOrdenacaoCrescente
	 * @param isOrdenacaoDecrescente
	 * @return
	 * @throws Exception
	 */
	public static List<AgendaRoteirizacao> findAgendaRoteirizacao(Integer codigoCliente, Integer codigoFilialExpedicao, Integer codigoCidade, 
			Integer codigoRegraDistribuicao, Integer codigoFilialFaturamento, Integer codigoBairro, Integer codigoUnidadeEstrategia, 
			boolean isOrdenacaoCrescente, boolean isOrdenacaoDecrescente) throws Exception {
		return AgendaRoteirizacaoDAO.findAgendaRoteirizacao(codigoCliente, codigoFilialExpedicao, codigoCidade, codigoRegraDistribuicao, 
				codigoFilialFaturamento, codigoBairro, codigoUnidadeEstrategia, isOrdenacaoCrescente, isOrdenacaoDecrescente);
	}

}