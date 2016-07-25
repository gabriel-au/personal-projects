package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.martins.vendas.dao.ClienteDAO;
import br.com.martins.vendas.dao.GrupoMercadoriaDAO;
import br.com.martins.vendas.dao.MercadoriaDAO;
import br.com.martins.vendas.dao.PromocaoDAO;
import br.com.martins.vendas.dao.RelacaoGiroDAO;
import br.com.martins.vendas.dao.UnidadeNegocioDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMePmcDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMePrmDAO;
import br.com.martins.vendas.enums.TipoCondicaoPagamento;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.ECM;
import br.com.martins.vendas.vo.GrupoMercadoria;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.Mensagem;
import br.com.martins.vendas.vo.PontoMinimoPedidoSegmento;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RelacaoGiro;
import br.com.martins.vendas.vo.UnidadeNegocio;

import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;


public class PromocaoService {
	
	private static final String TAG = PromocaoService.class.getName();
	
	/**
	 * Método que retorna apenas a lista de promoções válidas da mercadoria
	 * @param codigoMercadoria
	 * @param cliente
	 * @param relacaoGiro
	 * @param codigoUnidadeEstrategia
	 * @return
	 * @throws ParseException
	 * @throws IntegrationException
	 */
//	public static List<Promocao> listarPromocoesValidasPorMercadoria(Integer codigoMercadoria, Cliente cliente, RelacaoGiro relacaoGiro, Integer codigoUnidadeEstrategia) throws ParseException, IntegrationException {
//		return PromocaoDAO.listarPromocoesValidasPorMercadoria(codigoMercadoria, cliente, relacaoGiro, codigoUnidadeEstrategia);
//	}
	
	
	/**
	 * Método responsável por listar os tipos de grupo de mercadoria
	 * @throws ParseException
	 */
	public static List<GrupoMercadoria> listarGrupoMercadoria() throws ParseException{
		return GrupoMercadoriaDAO.listarGrupoMercadoria();
	}
	
	
	/**
	 * Método responsável por retornar o valor restante da promocao
	 * @param codigoPromocao
	 * @return
	 * @throws ParseException
	 */
	public static Mensagem obterValorRestantePromocao(Integer codigoPromocao) throws ParseException{
		return PromocaoDAO.obterValorRestantePromocao(codigoPromocao);
	}
	
	
	/**
	 * Método responsável por listar os tipos de Unidade Estrategica
	 * @throws ParseException
	 */
	public static List<UnidadeNegocio> listarUnidadeEstrategica() throws ParseException{
		return UnidadeNegocioDAO.listarUnidadeNegocio();
	}
	
	
	public static List<Promocao>  listarPromocoesValidas(Integer codigoFilialExpedicao, Integer  codigoUnidadeEstrategia, Integer codigoCliente, String codigoEstadoDestino, Integer codigoFilialFaturamento) throws ParseException, IntegrationException {		
		Cliente cliente = ClienteDAO.obterDetalheCliente(codigoCliente);
		RelacaoGiro relacaoGiro = RelacaoGiroDAO.recuperarPorFilial(cliente.codigoTerritorio, codigoFilialExpedicao, codigoFilialFaturamento);
		PontoMinimoPedidoSegmento segmento = ClienteDAO.recuperaPontoMinimoPedidoSegmento(codigoEstadoDestino, cliente.canal,Integer.parseInt(cliente.codigoSegmento));
		
		List<Promocao> listaValidada =  PromocaoDAO.listarPromocoesValidas(codigoFilialExpedicao, codigoUnidadeEstrategia, cliente, relacaoGiro, segmento, codigoEstadoDestino, codigoFilialFaturamento);
		
			
		return listaValidada;
	}
	
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
	 * @param isMercadoriaExclusiva
	 * @param palavraMeio
	 * @param codigoMercadoria
	 * @param codigoGrupoMercadoria
	 * @param codigoCategoria
	 * @param codigoSubCategoria
	 * @param numeroListaCustomizada
	 * @param comportamentoDeCompras 
	 * @return
	 */
	public static List<Item> listaItensDisponiveisDaPromocao( 
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
			boolean isMercadoriaExclusiva,
			String  palavraMeio,
			String  codigoMercadoria,
			Integer numeroListaCustomizada, 
			ItemFiltro itemFiltro,
			Integer codigoPromocao) {
				
		/*Validação em métodos
		 * Recupera lista de mercadorias válidas
		 
		List<Item> itens = PromocaoDAO.listarItensValidosPromocao(codigoPromocao,codigoFilialExpedicao,codigoFilialFaturamento,codigoRelacionamentoCliente, codigoMercadoria);
		List<Item> itensValidados= new ArrayList<Item>();
		
		Cliente cliente = ClienteDAO.obterDetalheCliente(codigoCliente);
		Representante representante = RepresentanteDAO.findRepresentante(codigoRepresentante);
		
		
		for (Item item : itens) {
			if(ItensDisponiveisService.validaItemDisponivel(cliente, representante, item, codigoFilialExpedicao, codigoFilialFaturamento)){
				itensValidados.add(item);
			}
		}
		*/
		
		
		List<Item> itens = PromocaoDAO.listaItensDisponiveisDaPromocao(
				codigoCliente,
				codigoFilialExpedicao,
				codigoFilialFaturamento, 
				codigoRelacionamentoCliente, 
				codigoGrupamentoCliente, 
				flagAlvaraClientePsicotropico, 
				codigoRepresentante,
				isMercadoriaExclusiva, 
				palavraMeio, 
				codigoMercadoria,
				numeroListaCustomizada, 
				itemFiltro,
				codigoPromocao);
		
		

		if (itemFiltro.numPagina == 1) {
			TabelaTmpMePmcDAO.limpaTabela();
		}	
		
		TransactionManager transactionManager = null;
		try {
			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();
			
			List<Item> tmp = new ArrayList<Item>(itens.size());
			boolean mercadoriaLiberadaDeBloqueio = true;
			for (Item item : itens) {
	
					item.condicaoPagamento.codigoCondicaoPagamento = codigoCondicaoPagamento;
	
					if (!ItensDisponiveisServiceAux.isMercadoriaBloqueadaAtendimentoCaterogizado(item.mercadoria.codigo, codigoFilialExpedicao, codigoCliente, codigoRepresentante, codigoCidade, codigoSupervisor, codigoAtividade)) {
	
						mercadoriaLiberadaDeBloqueio = ItensDisponiveisServiceAux.validaRegrasDeBloqueioClientePorMercadoria(item.mercadoria.codigo, codigoCliente, codigoGrupamentoCliente, codigoRepresentante, codigoSupervisor, codigoAtividade,
								codigoCidade, codigoEstadoDestino, codigoFilialExpedicao, codigoFilialFaturamento);
	
					}
	
					if (mercadoriaLiberadaDeBloqueio) {
						TabelaTmpMePmcDAO.insereItem(item, transactionManager);
						tmp.add(item);
					}
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
	 * 
	 * Lista os premios disponiveis sem status de estoque 'F' que significa 'problemas com fornecedor'
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
	 * @param isMercadoriaExclusiva
	 * @param palavraMeio
	 * @param codigoMercadoria
	 * @param codigoGrupoMercadoria
	 * @param codigoCategoria
	 * @param codigoSubCategoria
	 * @param numeroListaCustomizada
	 * @param itemFiltro 
	 * @return
	 */
	public static List<Item> listarPremiosDisponiveisDaPromocao( 
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
			boolean isMercadoriaExclusiva,
			String  palavraMeio,
			String  codigoMercadoria,
			Integer numeroListaCustomizada, 
			ItemFiltro itemFiltro,
			Integer codigoPromocao) {
				
		/*
		 * Recupera lista de mercadorias válidas
		 */
		List<Item> itens = PromocaoDAO.listarPremiosDisponiveisDaPromocao(
				codigoCliente,
				codigoFilialExpedicao,
				codigoFilialFaturamento, 
				codigoRelacionamentoCliente, 
				codigoGrupamentoCliente, 
				flagAlvaraClientePsicotropico, 
				codigoRepresentante,
				isMercadoriaExclusiva, 
				palavraMeio, 
				codigoMercadoria,
				numeroListaCustomizada, 
				itemFiltro,
				codigoPromocao);
			
		
		if (itemFiltro.numPagina == 1) {
			TabelaTmpMePrmDAO.limpaTabela();
		}
		
		TransactionManager transactionManager = null;
		try {
			transactionManager = DatabaseFactory.getInstance().getTransactionManager();
			transactionManager.beginTransaction();
			
			boolean mercadoriaLiberadaDeBloqueio = true;
			List<Item> tmp = new ArrayList<Item>(itens.size());
			for (Item item : itens) {
				
				
				//Validação necessária para saber se o valor do premio sera cobrado 
				if (item.promocao.valorVenda.signum() == 0){
					item.condicaoPagamento.codigoCondicaoPagamento = TipoCondicaoPagamento.BRINDES_PROMOCOES.codigoCondicaoPagamento;
				}else{
					item.condicaoPagamento.codigoCondicaoPagamento = codigoCondicaoPagamento;
				} 
						
					
					if (!ItensDisponiveisServiceAux.isMercadoriaBloqueadaAtendimentoCaterogizado(item.mercadoria.codigo, codigoFilialExpedicao, codigoCliente, codigoRepresentante, codigoCidade, codigoSupervisor, codigoAtividade)) {
	
						mercadoriaLiberadaDeBloqueio = ItensDisponiveisServiceAux.validaRegrasDeBloqueioClientePorMercadoria(item.mercadoria.codigo, codigoCliente, codigoGrupamentoCliente, codigoRepresentante, codigoSupervisor, codigoAtividade,
								codigoCidade, codigoEstadoDestino, codigoFilialExpedicao, codigoFilialFaturamento);
	
					}
	
					if (mercadoriaLiberadaDeBloqueio) {
						
						TabelaTmpMePrmDAO.insereItem(item, transactionManager);
						tmp.add(item);
					}
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
	 * Recupera item da tabela temporaria de premio
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static Item obtemItemPromocaoPremioTemporario(Integer codigoMercadoria) {
		return TabelaTmpMePrmDAO.obtemItem(codigoMercadoria);
	}
	
	

	/**
	 * obtem todos os premios da tabela temporaria
	 * @param codigoMercadoria
	 * @return
	 */
	public static List<Item> obtemItensPromocaoPremioTemporarios() {
		return TabelaTmpMePrmDAO.obtemTodosItens();
	}
	
	
	/**
	 * obtem todos os itens da promocao da tabela temporaria
	 * @param codigoMercadoria
	 * @return
	 */
	public static List<Item> obtemItensPromocaoTemporarios() {
		return TabelaTmpMePmcDAO.obtemTodosItens();
	}
	
	
	/**
	 * Recupera item da tabela temporaria de Promocao
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static Item obtemItemPromocaoTemporario(Integer codigoMercadoria) {
		return TabelaTmpMePmcDAO.obtemItem(codigoMercadoria);
	}
	
	
	
	
	/**
	 * Recupera item da tabela temporaria item da promocao
	 * @param incluirNoPedido 
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static boolean atualizaItemPromocaoTemporario(Item item, boolean incluirNoPedido) {
		boolean result = TabelaTmpMePmcDAO.alteraItem(item);
		if (incluirNoPedido) {
			
			Item itemTemp = TabelaTmpItePeDAO.obtemItem(
					item.mercadoria.codigo,
					item.codigoFilialExpedicao,
					item.codigoFilialFaturamento,
					item.notaFiscal,
					item.tipoNegociacaoMercadoria);
			
			if (itemTemp == null) {
				
				result = TabelaTmpItePeDAO.insereItem(item);
				
			} else {
				
				result = TabelaTmpItePeDAO.alteraItem(item);
			}
			
		}
		return result;
	}
	
	
	/**
	 * Recupera item da tabela temporaria de premio da promocao
	 * @param incluirNoPedido 
	 * 
	 * @param codigoMercadoria código da mercadoria
	 * 
	 * @return item
	 */
	public static boolean atualizaItemPremioTemporario(Item item, boolean incluirNoPedido) {
		boolean result = TabelaTmpMePrmDAO.alteraItem(item);
		if (incluirNoPedido) {
			
			Item itemTemp = TabelaTmpItePeDAO.obtemItem(
					item.mercadoria.codigo,
					item.codigoFilialExpedicao,
					item.codigoFilialFaturamento,
					item.notaFiscal,
					item.tipoNegociacaoMercadoria);
			
			if (itemTemp == null) {
				
				result = TabelaTmpItePeDAO.insereItem(item);
				
			} else {
				
				result = TabelaTmpItePeDAO.alteraItem(item);
			}
			
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * @param item
	 * @param cliente
	 * @param quantidadeAnt
	 * @param quantidade
	 * @return
	 */
	public static Mensagem verificarQuantidade(Item item, Cliente cliente, Integer quantidadeAnt, Integer quantidade) {

		ECM ecm = MercadoriaDAO.recuperaQuantidadeMinima(
				item.codigoFilialExpedicao,
				item.codigoFilialFaturamento,
				item.mercadoria.codigo,
				cliente.codigoCliente,
				cliente.codigoTerritorio,
				item.mercadoria.qtdMercadoriaPedida,
				Constants.TRUE.equals(item.mercadoria.indicadorRestricaoKit)
		);

		Mensagem mensagem = ItemPedidoService.verificarQuantidadeMinima(ecm, quantidadeAnt, quantidade);
		if (mensagem == null) {
			mensagem = ItemPedidoService.verificarCaixaFechadaPotencial(item, cliente, quantidade);
			if (mensagem == null) {
				mensagem = new Mensagem();
				mensagem.tipo  = Mensagem.TIPO.JS_NONE.value;
				mensagem.valor = Util.getStringValue(quantidade);
			}
		}
		
		return mensagem;
	}

}
