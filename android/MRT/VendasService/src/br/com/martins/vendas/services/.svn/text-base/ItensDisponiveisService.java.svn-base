package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brq.mobile.framework.log.Log;

import br.com.martins.vendas.dao.ComissaoDAO;
import br.com.martins.vendas.dao.CondicaoPagamentoDAO;
import br.com.martins.vendas.dao.ItemDisponivelDAO;
import br.com.martins.vendas.dao.ItemSimilarDAO;
import br.com.martins.vendas.dao.RelacaoGiroDAO;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.ClassificacaoNegociacao;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.DetalhePEE;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.RegraBloqueio;
import br.com.martins.vendas.vo.RelacaoGiro;
import br.com.martins.vendas.vo.Representante;

/**
 * Classe de servicos de listagem de Itens Disponiveis
 * 
 */
public class ItensDisponiveisService {

	private static final Integer		RESTRICAO_KIT				= 1;
	
	private static final String			MERCADORIA_EXCLUSIVA_MIX	= "*";
	
	private static final String			TEM_ALVARA_DE_PSICOTROPICO	= MERCADORIA_EXCLUSIVA_MIX;
	
	/**
	 * Lista as mercadorias disponiveis sem status de estoque 'F'que significa 'problemas com
	 * fornecedor'.
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
			String  codigoEstadoDestino) {

		List<Item> itensValidos = new ArrayList<Item>();
		
		Cliente cliente = ClienteService.obterDetalheCliente(codigoCliente);
		cliente.numeroRelacionamentoCliente = codigoRelacionamentoCliente;
		
		Representante representante;
		try {	
			representante = RepresentanteService.findRepresentante(codigoRepresentante);
		} catch (SQLException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
		
		List<Item> itens = ItemDisponivelDAO.listaItensDisponiveis(
				codigoFilialExpedicao, 
				codigoFilialFaturamento, 
				codigoRelacionamentoCliente, 
				null);
		
		for (int count = 0; count < itens.size(); count++) {
			Item item = itens.get(count);
			
			try {
				String simboloSubstituto = ComissaoDAO.obtemCodigoSimboloSubstituto(cliente, 
						item.preco.codigoSimboloSituacao);
				if(simboloSubstituto != null){
					item.preco.codigoSimboloSituacao = simboloSubstituto;
				}
			} catch (SQLException e) {
				Log.e("ItensDisponiveisService", e.getMessage());
			}
			
			if (validaItemDisponivel(cliente, representante, item, codigoFilialExpedicao, codigoFilialFaturamento, true)) {
				itensValidos.add(item);
			}
		}

		return itensValidos;
	}
	
	
	/**
	 * Obtem itens do kit.
	 *
	 * @param codigoMercadoriaKit the codigo mercadoria kit
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @return the list
	 */
	public static List<Item> obtemItensDoKit(final Integer codigoMercadoriaKit, 
			final Integer codigoFilialExpedicao,
			final Integer codigoFilialFaturamento){
		
		return ItemDisponivelDAO.obtemItensDoKit(codigoMercadoriaKit, codigoFilialExpedicao, codigoFilialFaturamento);
	}

	
	/**
	 * Recupera item disponivel.
	 *
	 * @param codigoCliente the codigo cliente
	 * @param codigoMercadoria the codigo mercadoria
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @param codigoCondicaoPagamento 
	 * @return the item
	 * @throws IntegrationException the integration exception
	 */
	public static Item recuperaItemDisponivel(
			Integer codigoCliente, 
			Integer codigoMercadoria, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer codigoRepresente, 
			Integer codigoCondicaoPagamento,
			boolean ignoraBloqueioItemSimiliar) throws IntegrationException {

		
		Cliente cliente = ClienteService.obterDetalheCliente(codigoCliente);
		try {
			Integer obtemNumeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(
					codigoFilialExpedicao,
					codigoFilialFaturamento, 
					cliente.codigoCidadePreco, 
					cliente.codigoTerritorio);
			
			RelacaoGiro relacaoGiro = RelacaoGiroDAO.recuperarPorFilial(cliente.codigoTerritorio, codigoFilialExpedicao, codigoFilialFaturamento);
			
			cliente.numeroRelacionamentoCliente = obtemNumeroRelacionamentoCliente;
			cliente.codigoSupervisor            = relacaoGiro.codSupervisor;
			
		} catch (Exception e) {
			throw new IntegrationException(e.getLocalizedMessage(), e);
		}
		
		Representante representante;
		try {	
			representante = RepresentanteService.findRepresentante(codigoRepresente);
		} catch (SQLException e) {
			throw new IntegrationException(e.getLocalizedMessage(), e);
		}
		
		
		
		List<Item> itens = ItemDisponivelDAO.listaItensDisponiveis(codigoFilialExpedicao,
				codigoFilialFaturamento,
				cliente.numeroRelacionamentoCliente,
				codigoMercadoria);
		
		if (itens.isEmpty()) {
			
			itens = ItemSimilarDAO.listaItens(
					codigoFilialExpedicao,
					codigoFilialFaturamento,
					cliente.codigoCliente,
					cliente.numeroRelacionamentoCliente,
					cliente.codigoGrupoCliente,
					cliente.flagAlvaraPsicotropico,
					Integer.parseInt(representante.codigoRepresentante),
					new ItemFiltro(codigoMercadoria)
			);
			
			if (itens.isEmpty()) {
				return null;
			}
		}
		
		final Item item = itens.get(0);
		if (validaItemDisponivel(cliente, representante, item, codigoFilialExpedicao, codigoFilialFaturamento, ignoraBloqueioItemSimiliar)) {
			
			item.flagPrecificavel = CondicaoPagamentoDAO.verificaSeCondicaoDePagamentoValida(codigoCondicaoPagamento, item.mercadoria);
			
			return item;
		}		
		
		return null;
	}
	
	/**
	 * Valida se o representante tem prioridade de venda na mercadoria, para o cliente desejado.
	 * 
	 * @param mercadoria
	 * @param codigoCliente
	 * @param codigoRepresentante
	 * @return
	 */
	public static Boolean verificaAtentidmentoCategoriazado(Mercadoria mercadoria, Integer codigoCliente, Integer codigoRepresentante) {
		
		if (mercadoria.codigoEspecialistaNegociacao == 0) {
			return Boolean.TRUE.booleanValue();
		}
		
		List<ClassificacaoNegociacao> listaClassificacao = 
				ItemDisponivelDAO.buscaClassificacaoNegociacaoDoCliente(codigoCliente, codigoRepresentante);
		
		for (ClassificacaoNegociacao classificacao : listaClassificacao) {
			if (classificacao.codigoEspecializacaoNegociacao == mercadoria.codigoEspecialistaNegociacao) {
				return Boolean.TRUE.booleanValue();
			}
		}
		
		return Boolean.FALSE.booleanValue();
	}

	/**
	 * Valida se a mercadoria � piscotropica e se o cliente tem alvar� para compra-la
	 * 
	 * @param flagAlvaraClientePsicotropico
	 * @param mercadoria
	 * @return
	 */
	public static Boolean isMercadoriaPsicotropico(String flagAlvaraClientePsicotropico, Mercadoria mercadoria) {
		
		boolean mercadoriaIsPsicotropico = ItemDisponivelDAO.buscaSeMercadoriaEhPsicotropico(mercadoria.codigo);
		if (mercadoriaIsPsicotropico) {
			
			return flagAlvaraClientePsicotropico.equals(TEM_ALVARA_DE_PSICOTROPICO);
			
		} 
			
		return true;
		
	}

	/**
	 * Valida se a mercadoria pertence ao grupo do cliente
	 * 
	 * @param mercadoria
	 * @param codigoGrupamentoCliente
	 * @return
	 */
	public static Boolean isMercadoriaNoMixDoCliente(Mercadoria mercadoria, Integer codigoGrupamentoCliente) {
		if (codigoGrupamentoCliente != 0) {
			int codigoMix = ItemDisponivelDAO.consultaMercadoriaNoMixDoGrupoCliente(mercadoria.codigo, codigoGrupamentoCliente);
			if (codigoMix != 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Valida se a mercadoria est� esta contida apenas no grupo do cliente.
	 * 
	 * @param mercadoria
	 * @param codigoGrupamentoCliente
	 * @return
	 */
	public static Boolean isGrupoClienteApenasDoMix(Mercadoria mercadoria, Integer codigoGrupamentoCliente) {
		String flagMix = ItemDisponivelDAO.buscaSeGrupoEhApenasDoMix(codigoGrupamentoCliente);
		if (flagMix.equalsIgnoreCase("S")) {
			int codigoMercadoriaNoMix = ItemDisponivelDAO.buscaMercadoriaNoMix(mercadoria.codigo, codigoGrupamentoCliente);
			if (codigoMercadoriaNoMix > 0) {
				return true;
			}
			return false;
		}
		return true;
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
	public static boolean isMercadoriaBloqueada(Mercadoria mercadoria, Integer codigoFilialExpedicao, Integer codigoCliente, Integer codigoRepresentante, Integer codigoCidade,
			Integer codigoSupervisor, Integer codigoAtividade) {

		Integer numeroBloqueios = ItemDisponivelDAO.verificaAtendimentoMercadoria(codigoFilialExpedicao, mercadoria, codigoCliente, codigoRepresentante, codigoCidade, codigoSupervisor,
				codigoAtividade);

		if (numeroBloqueios > 0) {
			return true;
		}

		return false;
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
	public static Boolean validaRegrasDeBloqueioDaMercadoria(Mercadoria mercadoria, 
			Integer codigoCliente, 
			Integer codigoGrupoCliente, 
			Integer codigoRepresentante,
			Integer codigoSupervisor,
			Integer codigoAtividade, 
			Integer codigoCidade, 
			String codigoEstadoDestino, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento) {
		
		boolean mercadoriaLiberada = true;
		
		List<RegraBloqueio> regrasBloqueioGestaoMix = new ArrayList<RegraBloqueio>();
		
		mercadoria.codigosDeBloqueio.addAll(GestaoMixService.buscaCodigoDeBloqueioDaMercadoria(mercadoria.codigo));
			
		List<RegraBloqueio> regraGestaoMix = GestaoMixService.buscaRegrasDeBloqueio(codigoCliente, codigoGrupoCliente, codigoRepresentante, codigoSupervisor, codigoAtividade, codigoCidade, codigoEstadoDestino, null);

		for (Integer codigoBloqueioMercadoria : mercadoria.codigosDeBloqueio) {

 			for (RegraBloqueio regraBloqueio : regraGestaoMix) {
 				
				if (regraBloqueio.codigoBloqueioMercadoria.equals(codigoBloqueioMercadoria)) {

					if (GestaoMixService.buscaBloqueioPorFilialExpedicao(codigoFilialExpedicao, codigoFilialFaturamento, codigoBloqueioMercadoria)) {
						
						regrasBloqueioGestaoMix.add(regraBloqueio);
						
					}

				}
				
			}
		}
		
		if (regrasBloqueioGestaoMix.size() > 0) {
			
			String valorReduzido = "0";
			RegraBloqueio regraMenorValorReduzido = new RegraBloqueio();
			for (RegraBloqueio regra : regrasBloqueioGestaoMix) {
				String valorReduzidoAux = regra.toString();
				// 01112004 REGRA PRIORIDADE

				if (new Long(valorReduzidoAux) > new Long(valorReduzido)) {
					regraMenorValorReduzido = regra;
					valorReduzido = valorReduzidoAux;
				}
			}
			
			 // liberacao
			if (regraMenorValorReduzido.tipoBloqueioMercadoria == 1) {
				mercadoriaLiberada = true;
				
			// bloqueio
			} else if (regraMenorValorReduzido.tipoBloqueioMercadoria == 5) {
				
				mercadoriaLiberada = false;
				
			} else {
				
				// Reducao de cota
				if (regraMenorValorReduzido.tipoBloqueioMercadoria == 3) { 
					valorReduzido.substring(4, valorReduzido.length());
					
					mercadoriaLiberada = true;
				}
			}
			
		} else {
			
			mercadoriaLiberada = true;
		}

		return mercadoriaLiberada;
	}
	
	/**
	 * Marca item no pee.
	 *
	 * @param flag the flag
	 * @param codigoMercadoria the codigo mercadoria
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @param numeroRelacaoCidade the numero relacao cidade
	 */
	public static void marcaItemNoPEE(String flag,
			Integer codigoMercadoria,
			Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			Integer numeroRelacaoCidade){
		
		ItemDisponivelDAO.atualizaItemPEE(codigoMercadoria, 
				codigoFilialExpedicao, 
				codigoFilialFaturamento, 
				numeroRelacaoCidade, 
				flag);
	}

	/**
	 * Valida Item
	 * 
	 * @param cliente
	 * @param representante
	 * @param item
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param ignoraBloqueioItemSimiliar 
	 * @return
	 */
	public static boolean validaItemDisponivel(Cliente cliente, Representante representante, Item item, Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, boolean ignoraBloqueioItemSimiliar) {
		
		boolean isMercadoriaExclusivaMix 				= true;
		boolean isGrupoClienteApenasDoMix 				= true;
		boolean clienteTemAlvaraDePsicotropico 			= true;
		boolean atendimentoCategorizado 	= true;
		boolean mercadoriaLiberadaDeBloqueio 			= true;
		
		Preco preco = item.preco;
		Mercadoria mercadoria = item.mercadoria;

		if (!RESTRICAO_KIT.equals(mercadoria.indicadorRestricaoKit)) {
		
			if (MERCADORIA_EXCLUSIVA_MIX.equalsIgnoreCase(preco.flagMercadoriaExclusiva)) {
				
				if (cliente.codigoGrupoCliente == 0 || (cliente.codigoGrupoCliente != 0 && !isMercadoriaNoMixDoCliente(mercadoria, cliente.codigoGrupoCliente))) {				
					
					isMercadoriaExclusivaMix = false;
				}
				
			} else {
				
				
				if (cliente.codigoGrupoCliente != 0 && !isGrupoClienteApenasDoMix(mercadoria, cliente.codigoGrupoCliente)) {
					
					isGrupoClienteApenasDoMix = false;
					
				}
				
			}
			
			if (!isMercadoriaPsicotropico(cliente.flagAlvaraPsicotropico, mercadoria)) {
				
				clienteTemAlvaraDePsicotropico = false;
			}
			
			/*
			 * Atendimento Categorizado
			 */
			if (mercadoria.codigoEspecialistaNegociacao != 0) {	
				/*
				 * TODO verificar metodo PrecisaAvaliar no martins
				 */
				atendimentoCategorizado = verificaAtentidmentoCategoriazado(mercadoria, cliente.codigoCliente, Integer.parseInt(representante.codigoRepresentante));
			}
			
			/*
			 * Valida Bloqueio Mercadoria
			 */
			if (atendimentoCategorizado && !isMercadoriaBloqueada(mercadoria, codigoFilialExpedicao, cliente.codigoCliente, Integer.parseInt(representante.codigoRepresentante), cliente.codigoCidade, cliente.codigoSupervisor, cliente.codigoAtividade)) {
	
				/*
				 * Verifica Gestão do Mix
				 */
				mercadoriaLiberadaDeBloqueio = validaRegrasDeBloqueioDaMercadoria(mercadoria, cliente.codigoCliente, cliente.codigoGrupoCliente, Integer.parseInt(representante.codigoRepresentante), cliente.codigoSupervisor, cliente.codigoAtividade, cliente.codigoCidade, cliente.uf, codigoFilialExpedicao, codigoFilialFaturamento);
			}
			
			return ignoraBloqueioItemSimiliar || isMercadoriaExclusivaMix && isGrupoClienteApenasDoMix && clienteTemAlvaraDePsicotropico && atendimentoCategorizado && mercadoriaLiberadaDeBloqueio;
		}

		
		return false;
	}
	
	/**
	 * Recupera Detalhe PEE
	 * 
	 * @param item
	 *            item
	 * @param cliente
	 *            cliente
	 * @return List de DetalhePEE
	 */
	public static List<DetalhePEE> obtemDetalhePEE(Item item, Cliente cliente) {
		return ItemDisponivelDAO.obtemDetalhePEE(item, cliente);
	}
}