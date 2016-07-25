package br.com.martins.vendas.services;

import br.com.martins.vendas.dao.LivroPrecoGrupamentoClienteDAO;
import br.com.martins.vendas.vo.LivroPrecoGrupamentoCliente;

/**
 * @author BRQ Mobile Team
 * 
 */
public class LivroPrecoGrupamentoClienteService {

	//private final static String	TAG	= LivroPrecoGrupamentoClienteService.class.getName();

	public static LivroPrecoGrupamentoCliente obtemLivroPrecoGrupamento(Integer codigoFilialExpedicao, Integer codFilialFaturamento, String codigoEstadoDestino, Integer codigoGrupamentoCliente, Integer codigoMercadoria) throws Exception {
		return LivroPrecoGrupamentoClienteDAO.obtemLivroPrecoGrupamento(codigoFilialExpedicao, codFilialFaturamento, codigoEstadoDestino, codigoGrupamentoCliente, codigoMercadoria);		
	}
	
}