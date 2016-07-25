package br.com.martins.vendas.services;

import java.util.List;

import br.com.martins.vendas.dao.RelacaoGiroDAO;
import br.com.martins.vendas.vo.RelacaoGiro;

public class RelacaoGiroService {

	public static RelacaoGiro recuperarPorFilial(Integer codTerritorio, Integer codFilialExpedicao, Integer codFilialFaturamento) {
		return RelacaoGiroDAO.recuperarPorFilial(codTerritorio, codFilialExpedicao, codFilialFaturamento);
	}

	/**
	 * Obtem a lista de relacoes de giro do representante
	 * 
	 * @param codigoRepresentante - código do representante
	 * @return List<RelacaoGiro> - lista de relacao de giro do representante
	 * @throws Exception 
	 */
	public static List<RelacaoGiro> listaRelacaoGiroRepresentante(Integer codigoRepresentante) throws Exception {
		return RelacaoGiroDAO.listaRelacaoGiroRepresentante(codigoRepresentante);
	}
	
	public static boolean isEstadoPrice(RelacaoGiro relacaoGiro){
		return ("1").equals(relacaoGiro.indDestinoProcesso);
	}
	
	/**
	 * Obtem estado de origem e destino
	 * 
	 * @param codTerritorio território do cliente
	 * 
	 * @param codFilialExpedicao código filial de expedição 
	 * 
	 * @param codFilialFaturamento código filial de faturamento
	 * 
	 * @return string vetor de 2 posições. Posição 1: CODESTORI e Posição2: CODESTDSN 
	 */
	public static String[] obtemEstadoFilial(final Integer codTerritorio, final Integer codFilialExpedicao, final Integer codFilialFaturamento) {
		return RelacaoGiroDAO.obtemEstadoFilial(codTerritorio, codFilialExpedicao, codFilialFaturamento);
	}
}
