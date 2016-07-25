package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.ItemDisponivelAuxDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpGrpDeDAO;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.RegraBloqueio;

import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;

/**
 * Classe de servicos de listagem de Itens Disponiveis
 * 
 */
public class ItensDisponiveisServiceAux {

	private static final String	TAG	= ItensDisponiveisServiceAux.class.getName();

	/**
	 * 
	 * Lista as mercadorias disponiveis sem status de estoque 'F' que significa 'problemas com fornecedor'
	 *  
	 * @param codigoCliente
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoRelacionamentoCliente
	 * @param codigoGrupamentoCliente
	 * @param flagAlvaraClientePsicotropico
	 * @param codigoRepresentante
	 * @param codigoCidade
	 * @param codigoAtividade
	 * @param codigoSupervisor
	 * @param codigoEstadoDestino
	 * @param palavraMeio
	 * @param codigoMercadoria
	 * @param codigoGrupoMercadoria
	 * @param codigoCategoria
	 * @param codigoSubCategoria
	 * @param numeroListaCustomizada
	 * @param itemFiltro 
	 * @return
	 */
	public static List<Item> listaItensDisponiveis( 
			Integer codigoCliente,
			Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			Integer codigoRelacionamentoCliente,
			Integer codigoGrupamentoCliente, 
			String  flagAlvaraClientePsicotropico,
			Integer codigoRepresentante, 
			Integer codigoCidade,
			Integer codigoAtividade, 
			Integer codigoSupervisor, 
			String  codigoEstadoDestino,
			Integer codigoCondicaoPagamento,
			ItemFiltro itemFiltro) {

		
		/*
		 * Recupera lista de mercadorias válidas
		 */
		List<Item> itens = ItemDisponivelAuxDAO.listaItensDisponiveis2(
				codigoFilialExpedicao, 
				codigoFilialFaturamento,
				codigoCliente,
				codigoRelacionamentoCliente, 
				codigoGrupamentoCliente, 
				flagAlvaraClientePsicotropico,
				codigoCidade,
				codigoAtividade, 
				codigoSupervisor,
				codigoRepresentante,
				codigoEstadoDestino,
				itemFiltro);
		
		if (itemFiltro.numPagina == 1) {
			TabelaTmpGrpDeDAO.limpaTabela();
		}
		
		TransactionManager transactionManager = null;
		try {
			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();
			
			List<Item> tmp = new ArrayList<Item>(itens.size());
			for (Item item : itens) {

				item.condicaoPagamento.codigoCondicaoPagamento = codigoCondicaoPagamento;					

				TabelaTmpGrpDeDAO.insereItem(item, transactionManager);
				
				tmp.add(item);
			}
			transactionManager.commitTransaction();
						
			return tmp;

		} catch (SQLException e) {
			
			try {
				transactionManager.rollbackTransaction();
			} catch (SQLException e1) {
				Log.e(TAG, e.getLocalizedMessage());
			}
			
			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
			
		} finally {
			
			try {
				transactionManager.endTransaction();
			} catch (SQLException e) {
				Log.e(TAG, e.getLocalizedMessage());
			}
		}
	}

		
	/**
	 * Verifica se a mercadoria � bloqueada
	 * 
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @param codigoCliente
	 * @param codigoRepresentante
	 * @param codigoCidade
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 * @return
	 */
	public static boolean isMercadoriaBloqueadaAtendimentoCaterogizado(Integer codigoMercadoria, 
			Integer codigoFilialExpedicao, 
			Integer codigoCliente, 
			Integer codigoRepresentante,
			Integer codigoCidade, 
			Integer codigoSupervisor, 
			Integer codigoAtividade) {

		Integer numeroBloqueios = ItemDisponivelAuxDAO.verificaAtendimentoMercadoria(codigoFilialExpedicao, codigoMercadoria, codigoCliente, codigoRepresentante, codigoCidade, codigoSupervisor,
				codigoAtividade);

		return numeroBloqueios > 0;
	}

	/**
	 * 
	 * @param regrasDeBloqueioGestaoMix
	 * @param codigoCliente
	 * @param codigoGrupoCliente
	 * @param codigoRepresentante
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 * @param codigoCidade
	 * @param codigoEstadoDestino
	 */
	public static List<RegraBloqueio> obtemRegrasDeBloqueio(Integer codigoCliente, 
			Integer codigoGrupoCliente, 
			Integer codigoRepresentante, 
			Integer codigoSupervisor,
			Integer codigoAtividade, 
			Integer codigoCidade, String 
			codigoEstadoDestino) {
		
		List<RegraBloqueio>regrasDeBloqueioGestaoMix = GestaoMixService.buscaRegrasDeBloqueio(codigoCliente, 
				codigoGrupoCliente, 
				codigoRepresentante, 
				codigoSupervisor, 
				codigoAtividade, 
				codigoCidade,
				codigoEstadoDestino, 
				null);
	
		return regrasDeBloqueioGestaoMix;
		
	}

	/**
	 * 
	 * @param mercadoria
	 * @param codigoCliente
	 * @param codigoGrupoCliente
	 * @param codigoRepresentante
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 * @param codigoCidade
	 * @param codigoEstadoDestino
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @return
	 */
	public static Boolean validaRegrasDeBloqueioClientePorMercadoria(
			Integer codigoMercadoria, 
			Integer codigoCliente, 
			Integer codigoGrupoCliente, 
			Integer codigoRepresentante, 
			Integer codigoSupervisor,
			Integer codigoAtividade, 
			Integer codigoCidade, 
			String  codigoEstadoDestino, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento) {
		
		List<RegraBloqueio> regrasBloqueioGestaoMix = new ArrayList<RegraBloqueio>();

		List<Integer> codigosBloqueio = GestaoMixService.buscaCodigoDeBloqueioDaMercadoria(codigoMercadoria);

		Map<Integer, List<RegraBloqueio>> regrasBloqueioPorMercadoria = GestaoMixService.buscaRegrasDeBloqueioPorMercadoria(codigoGrupoCliente, codigoRepresentante, codigoSupervisor,
				codigoAtividade, codigoCidade, codigoFilialFaturamento, codigoEstadoDestino);

		int qtdBloqueio = codigosBloqueio.size();
		for (int count = 0; count < qtdBloqueio; count++) {

			int codigoBloqueio = codigosBloqueio.get(count);

			List<RegraBloqueio> regraGestaoMix = regrasBloqueioPorMercadoria.get(codigoBloqueio);
			if (regraGestaoMix == null)
				continue;

			int qtdRegraMix = regraGestaoMix.size();
			for (int j = 0; j < qtdRegraMix; j++) {

				RegraBloqueio regraBloqueio = regraGestaoMix.get(j);

				if (GestaoMixService.buscaBloqueioPorFilialExpedicao(codigoFilialExpedicao, codigoFilialFaturamento, codigoBloqueio)) {

					regrasBloqueioGestaoMix.add(regraBloqueio);
				}

			}
		}

		int size = regrasBloqueioGestaoMix.size();
		if (size > 0) {
			String valorReduzido = "0";
			RegraBloqueio regraMenorValorReduzido = new RegraBloqueio();

			for (int count = 0; count < size; count++) {

				RegraBloqueio regra = regrasBloqueioGestaoMix.get(0);
				String valorReduzidoAux = regra.toString();

				// 01112004 REGRA PRIORIDADE
				if (new Long(valorReduzidoAux) > new Long(valorReduzido)) {
					regraMenorValorReduzido = regra;
					valorReduzido = valorReduzidoAux;
				}
			}
			// liberacao
			if (regraMenorValorReduzido.tipoBloqueioMercadoria == 1) {
				return true;
				// bloqueio
			} else if (regraMenorValorReduzido.tipoBloqueioMercadoria == 5) {
				return false;
			} else {
				// Reducao de cota
				if (regraMenorValorReduzido.tipoBloqueioMercadoria == 3) {
					valorReduzido.substring(4, valorReduzido.length());
					return true;
				}
			}
		} else {
			return true;
		}
		return false;
	}
}
