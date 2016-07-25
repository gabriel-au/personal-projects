package br.com.martins.vendas.services;

import br.com.martins.vendas.dao.RegraDistribuicaoDAO;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.log.Log;

public class RegraDistribuicaoService {

	private static final String	TAG										= RegraDistribuicaoService.class.getName();

	public static final Integer	CODIGO_REGRA_DISTRIBUICAO_DEFAULT		= 0;
	
	public static final String	DESCRICAO_REGRA_DISTRIBUICAO_DEFAULT	= "TRANSPORTE PRÓPRIO MARTINS";

	
	public static RegraDistribuicao obtemRegraDistribuicaoDefault() {
		RegraDistribuicao regraDistribuicao = new RegraDistribuicao();
		regraDistribuicao.codigoRegra = CODIGO_REGRA_DISTRIBUICAO_DEFAULT;
		regraDistribuicao.descricaoRegraDistribuiocao = DESCRICAO_REGRA_DISTRIBUICAO_DEFAULT;
		return regraDistribuicao;		
	}
	
	public static RegraDistribuicao obtemRegraDistribuicao(Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, Integer codigoCliente) throws IntegrationException {
		try {

			RegraDistribuicao regraDistribuicao = obtemRegraDistribuicao(codigoFilialExpedicao, codigoFilialFaturamento, codigoCliente, null);
			if (regraDistribuicao == null) {
				regraDistribuicao = new RegraDistribuicao();
				regraDistribuicao.codigoRegra = CODIGO_REGRA_DISTRIBUICAO_DEFAULT;
				regraDistribuicao.descricaoRegraDistribuiocao = DESCRICAO_REGRA_DISTRIBUICAO_DEFAULT;
			}
			
			return regraDistribuicao;
		} catch (IntegrationException e) {
			Log.e(TAG, e.getLocalizedMessage());
			throw e;
		}
		
	}
	
	public static RegraDistribuicao obtemRegraDistribuicao(Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, Integer codigoCliente, 
			Integer codigoRegraDistribuicao) throws IntegrationException {
		try {
			return RegraDistribuicaoDAO.obtemRegraDistribuicao(codigoFilialExpedicao, codigoFilialFaturamento, codigoCliente, codigoRegraDistribuicao);
		} catch (IntegrationException e) {
			Log.e(TAG, e.getLocalizedMessage());
			throw e;
		}
	}

}