package br.com.martins.vendas.services.pedido;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.martins.vendas.dao.RelacaoGiroDAO;
import br.com.martins.vendas.dao.pedido.PedidoDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.enums.TipoCondicaoPagamento;
import br.com.martins.vendas.enums.TipoPedidoMensagem;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.FilialService;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.services.RepresentanteService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.GrupoAfinidadePEE;
import br.com.martins.vendas.vo.ParametroMinimoFilial;
import br.com.martins.vendas.vo.Pedido;
import br.com.martins.vendas.vo.PedidoMensagem;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class FecharPedidoService {

	private static final String TAG = FecharPedidoService.class.getName();
	
	private static final Integer QUANTIDADE_MINIMA_MERCADORIA = 1;
	private static final Integer QUANTIDADE_MAXIMA_MERCADORIA = 9999;
	private static final String STRING_ZERO = "0";
	private static final Integer TAMANHO_COD_MERCADORIA = 7;
	private static final String PEDIDO = "P";
	private static final String MENSAGEM_SUCESSO = "O Pedido foi preparado com sucesso !!!";
	private static final String MENSAGEM_FALHA = "Ocorreu um erro inesperando na preparação do Pedido.";
	private static final String MENSAGEM_PEDIDO_DEFINIDO_COTACAO = "O Pedido está definido como Cotação !!!";
	// private static final String MENSAGEM_PEDIDO_JA_PREPARADO = "O Pedido já está Preparado !!!";

	private static List<PedidoMensagem> mensagens = new ArrayList<PedidoMensagem>();

	/**
	 * 
	 * @param pedido
	 * @return
	 */
	public static Pedido fechaPedido(Pedido pedido, Representante representante, String versaoSistema, boolean seraValidado) {

		// Limpa a lista de mensagens
		List<PedidoMensagem> mensagens = new ArrayList<PedidoMensagem>();

		// Obtem os itens do pedido na tabela temporaria
		pedido.listaItens = TabelaTmpItePeDAO.obtemTodosItens();

		// Obtem o status do pedido.
		pedido.statusPedido = PedidoDAO.obtemStatusPcasos();

		// Obtem pontuacao minima do pedido
		pedido.pontuacaoMinima = PedidoDAO.obtemPontuacaoMinimaPedido(pedido.filialExpedicao, pedido.cliente.uf, pedido.cliente.canal);

		// Verifica se há itens no pedido.
		if (pedido.listaItens.isEmpty()) {
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_SEM_ITENS.codigo));
		}

		// Verifica se o pedido é somente leitura
		if (PedidoStatus.SOMENTE_LEITURA.getValue().equals(pedido.statusPedido.getValue())) {
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_PEDIDO_SO_LEITURA.codigo));
		}

		Integer quantidadeTotalItens = 0;
		Integer valorTotalPontuacaoPedido = 0;
		
		BigDecimal valorTotalSemEncargos = BigDecimal.ZERO;
		BigDecimal valorTotalPedido = BigDecimal.ZERO;

		BigDecimal valorTotalStb = BigDecimal.ZERO;
		BigDecimal valorTotalIpi = BigDecimal.ZERO;

		BigDecimal valorTotalDescontoItem = BigDecimal.ZERO;
		BigDecimal valorTotalDescontoPedido = BigDecimal.ZERO;
		
		BigDecimal valorTotalVendor = BigDecimal.ZERO;

		// Verifica se o pedido está preparado e o status do pedido. Caso o pedido NAO tenha sido preparado
		// e seu status NAO seja de "somente leitura", o processo do fechamento do pedido prossegue.
		if (seraValidado && !PedidoService.isPedidoPreparado(Integer.valueOf(pedido.numeroPedido)) && mensagens.isEmpty()) {

			mensagens.addAll(fechaPedidoPorFilial(pedido, representante, seraValidado));
			
			for (Item item : pedido.listaItens) {
				
				// Preparacao dos itens
				item.cliente = pedido.cliente;
				item.condicaoPagamento = CondicaoPagamentoService.buscaCondicaoPagamento(item.condicaoPagamento.codigoCondicaoPagamento);
				
				// Total de Itens
				quantidadeTotalItens++;

				// Total Pontuacao
				valorTotalPontuacaoPedido += calculaPontuacao(item);

				// Total Vendor
				valorTotalVendor = calculaTotalVendor(item);
				
				valorTotalPedido = valorTotalPedido.add(item.valorLiquidoMercadoria.multiply(BigDecimal.valueOf(item.quantidadeMercadoria)));
				valorTotalSemEncargos = valorTotalSemEncargos.add(item.preco.valorBrutoMercadoria.multiply(BigDecimal.valueOf(item.quantidadeMercadoria)));

				valorTotalStb = valorTotalStb.add(item.stb.valorSTBTotal);
				valorTotalIpi = valorTotalIpi.add(item.mercadoria.percentualIPI.percentualIPI);

				valorTotalDescontoItem = valorTotalDescontoItem.add(item.desconto.percentualDescontoSimplificado);
				valorTotalDescontoPedido = valorTotalDescontoPedido.add(item.desconto.valorDesconto);
			}

		} else {
			for (Item item : pedido.listaItens) {

				// Preparacao dos itens
				item.cliente = pedido.cliente;
				item.condicaoPagamento = CondicaoPagamentoService.buscaCondicaoPagamento(item.condicaoPagamento.codigoCondicaoPagamento);

				// Total de Itens
				quantidadeTotalItens++;

				// Total Pontuacao
				valorTotalPontuacaoPedido += calculaPontuacao(item);

				// Total Vendor
				valorTotalVendor = calculaTotalVendor(item);

				valorTotalPedido = valorTotalPedido.add(item.valorLiquidoMercadoria.multiply(BigDecimal.valueOf(item.quantidadeMercadoria)));
				valorTotalSemEncargos = valorTotalSemEncargos.add(item.preco.valorBrutoMercadoria.multiply(BigDecimal.valueOf(item.quantidadeMercadoria)));

				valorTotalStb = valorTotalStb.add(item.stb.valorSTBTotal);
				valorTotalIpi = valorTotalIpi.add(item.mercadoria.percentualIPI.percentualIPI);
				
				valorTotalDescontoItem = valorTotalDescontoItem.add(item.desconto.percentualDescontoSimplificado);
				valorTotalDescontoPedido = valorTotalDescontoPedido.add(item.desconto.valorDesconto);
			}
		}

		System.out.println("totalVendor: " + valorTotalVendor);
		System.out.println("valorTotalPedido: " + valorTotalPedido);
		System.out.println("valorTotalSemEncargos: " + valorTotalSemEncargos);
		System.out.println("valorTotalStb: " + valorTotalStb);
		System.out.println("valorTotalIpi: " + valorTotalIpi);

//		pedido.valor
		pedido.valorTotalSemEncargos = valorTotalSemEncargos;
		pedido.valorTotalPedido = valorTotalPedido;
		pedido.valorTotalPontuacaoPedido = valorTotalPontuacaoPedido;
		pedido.quantidadeTotalItens = quantidadeTotalItens;
		pedido.mensagens = mensagens;

		return pedido;
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	private static Integer calculaPontuacao(Item item) {
		Integer pontuacao = 0;

		if (item.indicaItemImune == 0) {
			pontuacao = item.valorPontuacaoMercadoria * item.quantidadeMercadoria;
		}

		return pontuacao;
	}

	/**
	 * 
	 * @param condicaoPagamento
	 * @param item
	 * @param cliente
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @return
	 */
	private static BigDecimal calculaTotalVendor(Item item) {
		BigDecimal valorVendor = BigDecimal.ZERO;
		
		String[] estadoFilial = RelacaoGiroDAO.obtemEstadoFilial(item.cliente.codigoTerritorio, item.codigoFilialExpedicao, item.codigoFilialFaturamento);
		item.siglaEstadoOrigem = estadoFilial[0];
		item.siglaEstadoDestino = estadoFilial[1];
		
		BigDecimal fatorCondicaoPagamento = CondicaoPagamentoService.obtemFatorCondicaoPagamento(item.condicaoPagamento, item.mercadoria, item.cliente, item.siglaEstadoOrigem, item.siglaEstadoDestino);
		boolean isFatorVendor = CondicaoPagamentoService.isUtilizadoFatorVendor(item.condicaoPagamento, item.mercadoria, item.cliente, item.siglaEstadoOrigem, item.siglaEstadoDestino);
		
		if (isFatorVendor) {
			valorVendor = item.valorLiquidoComImposto.multiply(fatorCondicaoPagamento);
			valorVendor = item.valorLiquidoComImposto.subtract(item.valorLiquidoComImposto.multiply(BigDecimal.valueOf(item.quantidadeMercadoria)));
		}
		
		return valorVendor;
	}

//	private static BigDecimal obterValorMinimoPontoPedido(BigDecimal fatorKMargem, Integer valorLancamentoPedido) {
//		return RepresentanteService.obterValorMinimoPontoPedido(fatorKMargem, valorLancamentoPedido);
//	}

	private static List<PedidoMensagem> fechaPedidoPorFilial(Pedido pedido, Representante representante, boolean seraValidado) {

		if (seraValidado) {
			realizaVerificacoes(pedido, representante); //(pedido, pedido.cliente);
		}

		// TODO PARA FINS DE TESTE DA TELA DE MENSAGENS
		
		// List<PedidoMensagem> mensagens = criaMensagens();
		// if (mensagens.isEmpty()) {
		// mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_TEST1.codigo));
		// mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_TEST2.codigo));
		// }

		return mensagens;
	}

	// Verifica se o pedido devera apenas ser fechado ou fechado/preparado.
	public static boolean gravaPedido(Pedido pedido, Integer codigoRepresentante, String versaoSistema, boolean seraPreparado) {
		boolean resultado = false;

		try {
			// Obtem dados do Representante
			Representante representante = RepresentanteService.findRepresentante(codigoRepresentante);

			// Insere ou Altera um pedido
			if (PedidoDAO.isPedidoExiste(Integer.valueOf(pedido.numeroPedido))) {
				resultado = PedidoDAO.alteraPedido(pedido);
				resultado = PedidoDAO.excluiItensPedido(pedido);
				resultado = PedidoDAO.insereItensPedido(pedido);
			} else {
				resultado = PedidoDAO.inserePedido(pedido);
				resultado = PedidoDAO.insereItensPedido(pedido);
				PedidoDAO.atualizaUltimoPedidoFechado(Integer.valueOf(pedido.numeroPedido));
			}

			// Prepara o Pedido
			if (seraPreparado) {
				// Obtem a mensagem de retorno da preparacao do pedido (geracao
				// de arquivos do pedido e pee)
				// mensagemRetornoPreparacao = preparaPedido(representante,
				// Integer.valueOf(pedido.numeroPedido), versaoSistema);
				
//				pedido = fechaPedido(pedido, representante, versaoSistema, false);
				preparaPedido(pedido, representante, versaoSistema);

				// Atualiza do total de pedidos preparados e o ultimo numero do pedido na tabela PCAPAR
				Integer totalPedidosPreparados = PedidoDAO.obtemTotalPedidosPreparados() + 1;
				PedidoDAO.atualizaUltimoPedidoPreparado(totalPedidosPreparados, pedido.numeroPedido);

				// Atualiza o Status na tabela PCASOS
				PedidoDAO.atualizaStatusPcasos(pedido, PedidoStatus.PREPARADO);

				// Atualiza a tabela PCAPED
				PedidoDAO.marcaPedidoPreparado(Integer.valueOf(pedido.numeroPedido));
				
			} else {
				// Atualiza o Status na tabela PCASOS
				PedidoDAO.atualizaStatusPcasos(pedido, PedidoStatus.GRAVADO);
				
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return resultado;
	}

	// SE O OBJETO ITEM NAO ESTIVER COM O OBJETO CLIENTE PREENCHIDO DENTRO DELE
	// O OBJETO CLIENTE DEVERA SER PASSADO COMO ARGUMENTO PARA O METODO ABAIXO.
	private static void realizaVerificacoes(Pedido pedido, Representante representante) { //(Pedido pedido, Cliente cliente)

		// Validacoes
		verificaMinimosPorCondicaoPagamento(pedido);
		pedido.listaItens = verificaRepeticao(pedido); // TODO VERIFICAR A NECESSIDADE DE METODO
		verificaQuantidade(pedido);
		
		for (Item item : pedido.listaItens) {
			item.cliente = pedido.cliente;
			
			// Validacoes
			validaVolumeQuantidade(item);
			validaFilialFaturamento(item); // TODO VERIFICAR A MENSAGEM QUE DEVERA SER EXIBIDA
			verificaQuota(item);
			validaTipoContratoFilial(item);
			
			// Calculos
			item.valorTotalMercadoriasComRestricao = calculaRestricaoMercadoria(item); // TODO VERIFICAR ONDE DEVERA SER ARMAZENADO ESSE VALOR NO ITEM
			
			// TODO A construir e testar
			acumulaMercadoriaIsentoPisCofins(item);
			armazenaPromocoes(item);
			verificaReducaoItem(item);
			verificaMinimosPorFrete(item);
			verificaEsgotamento(item);
			verificaValorMaximoBrindeFlex(item, representante);
			verificaPromocoes(item);
			verificaBonificacaoAplicada(item);
			verificaVencimentoCondicao(item);
			verificaMercadoriaIsentoPisCofins(item);
			verificaParticipacaoBrindeBeneficio(item);
			verificaMinimoBoletos(item);
		}
		
	}

	private static void verificaQuota(Item item) {
		Integer quotaMercadoria = MercadoriaService.recuperaQuota(
				item.codigoFilialExpedicao,
				item.condicaoPagamento.codigoEstadoUniao,
				item.mercadoria.codigo);

		if (item.quantidadeMercadoria > quotaMercadoria) {
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_QUOTA_EXCEDIDA.codigo));
		}
		
	}

	private static void verificaReducaoItem(Item item) {
		if ((item.quantidadeMercadoria > item.valorReducao) && (item.valorReducao.compareTo(0) != 0)) {
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_QUOTA_EXCEDIDA.codigo));
		}
	}

	private static Set<CondicaoPagamento> obtemCondicoesPagamentoPedido(Pedido pedido) {
		Set<CondicaoPagamento> listaCondicoesPagamento = new HashSet<CondicaoPagamento>();
		
		for (Item item : pedido.listaItens) {
			listaCondicoesPagamento.add(item.condicaoPagamento);
		}
		
		return listaCondicoesPagamento;
	}
	
	private static void verificaMinimosPorCondicaoPagamento(Pedido pedido) {
		Set<CondicaoPagamento> listaCondicoesPagamento = obtemCondicoesPagamentoPedido(pedido);
		List<ParametroMinimoFilial> listaParametroMinimoFilial = new ArrayList<ParametroMinimoFilial>();
		
		for (CondicaoPagamento condicaoPagamento : listaCondicoesPagamento) {
			listaParametroMinimoFilial.add(FilialService.obterParametroMinimoFilialPorFilial(
					condicaoPagamento.codigoFilialExpedicao,
					condicaoPagamento.codigoEstadoUniao,
					Integer.valueOf(condicaoPagamento.codigoCanal)));
		}
		
		for (Item item : pedido.listaItens) {
			for (ParametroMinimoFilial parametroMinimoFilial : listaParametroMinimoFilial) {
				if (item.valorLiquidoTotal.compareTo(parametroMinimoFilial.valorMinimoExpedicao) < 0) {
					mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_CP_VALORMINIMO.codigo, ""));
				}
			}
		}

	}

	private static void validaTipoContratoFilial(Item item) {
		if (ClienteService.validaTipoContratoFilial(
				item.codigoCliente, // item.cliente.codigoCliente
				item.codigoFilialExpedicao,
				item.condicaoPagamento.tipoContratoCondicao)) {
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_CP_COMPROR_CLI_SEM_CTT.codigo));
		}
	}
	
	private static void verificaMinimosPorFrete(Item item) {
		// ddoFchVlrFrt.m_dblVlrTotFrt < ddoFchVlrFrt.m_dblVlrFrtMin
		if (item.regraDistribuicao.valorTotalFrete.compareTo(item.regraDistribuicao.valorFreteMinimo) == -1) {
			PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_FRETE_VALOR_MINIMO.codigo);
		}
	}

	// Método que totaliza os valores para mercadorias com restrições
	// TODO VERIFICAR ONDE DEVERA SER ARMAZENADO ESSE VALOR NO ITEM
	private static BigDecimal calculaRestricaoMercadoria(Item item) {
		BigDecimal valorTotalMercadoriasComRestricao = BigDecimal.ZERO;

		if (item.indicaRestricaoBeneficioCustomizado == 1 || //
				item.indicaRestricaoBeneficioCustomizadoBrinde == 1 || //
				item.indicaRestricaoBeneficioCustomizadoMercadoria == 1 || //
				item.indicaRestricaoBonificacaoSemBrinde == 1 || //
				item.indicaRestricaoItemBrinde == 1) {
			valorTotalMercadoriasComRestricao.add(item.valorLiquidoMercadoria.multiply(new BigDecimal(item.quantidadeMercadoria)));
		}

		return valorTotalMercadoriasComRestricao;
	}

	/**
	 * Este metodo realiza as verificações das regras de Metal Grampo e Oleo de
	 * Soja.
	 * 
	 * @param item
	 * @return qde_metalgrampo = 1 - entra no RN_ERRO_PORCENTAGEM_SOJA
	 *         qde_metalgrampo = 2 - RN_ERRO_PORCENTAGEM_METALGRAMPO
	 *         qde_metalgrampo = 0 - NAO FAZ NADA
	 */
	private static void validaVolumeQuantidade(Item item) {
		Integer resultado = PedidoDAO.validaVolumeQuantidade( //
				item.cliente.codigoAtividade, // Codigo Atividade
				item.mercadoria.codigo, // Codigo Mercadoria
				item.codigoFilialExpedicao, // Codigo Filial Expedicao
				item.cliente.uf // Codigo Estado (UF)
				);

		switch (resultado) {
		case 1:
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_PORCENTAGEM_SOJA.codigo));
			break;
		case 2:
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_PORCENTAGEM_METALGRAMPO.codigo));
			break;
		default:
			break;
		}
	}

	/**
	 * Verifica se a mercadoria esta esgotada.
	 * 
	 * Tipos de esgotamento:<br>
	 * "" - Nao ha problemas de estoque;<br>
	 * "B" - Estoque baixo; "F" - Problema de fornecimento; (Nao devera ser
	 * tratado como problema)<br>
	 * "Z" - Estoque zerado;<br>
	 * 
	 * @param item
	 *            objeto do tipo
	 *            <code>br.com.martins.vendas.services.calculodepreco.Item</code>
	 */
	private static void verificaEsgotamento(Item item) {
		// TODO VERIFICAR SE ESSE DADO ESTA CHEGANDO, CASO NAO ESTEJA,
		// CRIAR METODO PARA PREENCHIMENTO DO TipoEsgotamento
		if ("B".equalsIgnoreCase(item.mercadoria.tipoEsgotamento) || "Z".equalsIgnoreCase(item.mercadoria.tipoEsgotamento)) {
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_ITEM_ESGOTADO.codigo));
		}
	}

	private static void verificaValorMaximoBrindeFlex(Item item, Representante representante) {
		if (TipoCondicaoPagamento.BRINDES_DESCONTO_FLEXIVEL.equals(item.condicaoPagamento.codigoCondicaoPagamento)
				&& representante.valorMaxBrinde.compareTo(item.mercadoria.valorBeneficio) == -1) {
			item.valorLiquidoMercadoria = item.mercadoria.valorBeneficio; // TODO VALIDAR
			mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_VALOR_MAXIMO_BRINDE_FLEX.codigo));
		}
	}

	private static void acumulaMercadoriaIsentoPisCofins(Item item) {
		if (item.desconto.valorDescontoIsencao.doubleValue() > 0
				&& item.condicaoPagamento.codigoCondicaoPagamento != TipoCondicaoPagamento.BRINDES_PROMOCOES.getValue()
				&& item.condicaoPagamento.codigoCondicaoPagamento != TipoCondicaoPagamento.BRINDES_DESCONTO_FLEXIVEL.getValue()
				&& item.condicaoPagamento.codigoCondicaoPagamento != TipoCondicaoPagamento.BRINDES_BENEFICIO_CUSTOMIZADO.getValue()) {
			item.quantidadeTotalMercadoriasComIsencao  += item.quantidadeMercadoria;
		}
	}

	// TODO VERIFICAR SE SERA NECESSARIO ESSE METODO
	private static ArrayList<Item> verificaRepeticao(Pedido pedido) {
		Set<Item> listaItensSemRepeticao = new HashSet<Item>();
		for (Item item : pedido.listaItens) {
			listaItensSemRepeticao.add(item);
		}
		return new ArrayList<Item>(listaItensSemRepeticao);
	}

	private static void armazenaPromocoes(Item item) {
		// void clsPed::ArmazenaPromocoes(const DdoFchIte &ddoFchIte, DdoStaFch
		// &ddoStaFch)
		// {
		// BOOL boolPmc, boolPmcAux;
		// CString strCodPmc, strDesPmc;
		//
		// MapPmc * mapPmc;
		// DdoTipPmc ddoTipPmc;
		//
		// DdoFchPmc ddoFchPmc;
		// DdoFchPmcAux ddoFchPmcAux;
		//
		// clsPmcVld * pclsPmcVld = m_pclsPmcMerPmc->ObtemClassePromocaoValida();
		// pclsPmcVld->InformaFilialDeExpedicao(m_strCodFilEpd);
		//
		// if (ddoFchIte.m_strTipNgcMer == TIPNGCPMC)
		// {
		// boolPmc = m_mapFchPmc.Lookup(ddoFchIte.m_strCodPmc, ddoFchPmc);
		// if (! boolPmc)
		// {
		// if (RN_OK == pclsPmcVld->Busca(ddoFchIte.m_strCodPmc))
		// {
		// pclsPmcVld->ObtemDescricao(strDesPmc);
		// }
		//
		// // Não existem dados desta promoção, inicializa valores
		// ddoFchPmc.m_dblQdeItePrm = 0.00;
		// ddoFchPmc.m_dblVlrItePmc = 0.00;
		// ddoFchPmc.m_dblQdeItePmc = 0.00;
		// ddoFchPmc.m_strCodPmc = ddoFchIte.m_strCodPmc;
		// ddoFchPmc.m_strDesPmc = strDesPmc;
		// ddoFchPmc.m_iTipPmc = pclsPmcVld->ObtemTipoPromocao();
		// ddoFchPmc.m_iCodCtrPmc = 0;
		// ddoFchPmc.m_dblQdeVlrRto = 0.00;
		//
		// ddoFchPmc.m_mapFchPmcAux = NULL;
		// delete ddoFchPmc.m_mapFchPmcAux;
		// ddoFchPmc.m_mapFchPmcAux = new MapFchPmcAux();
		// ddoFchPmc.m_mapFchPmcAux->RemoveAll();
		// }
		//
		// ddoFchPmc.m_dblQdeItePrm += ddoFchIte.m_iQdeMerPed;
		// m_mapFchPmc.SetAt(ddoFchPmc.m_strCodPmc, ddoFchPmc);
		//
		// return;
		// }
		//
		// if (ddoFchIte.m_strTipNgcMer == TIPNGCVND)
		// {
		// m_pclsPmcMerPmc->GeraPromocoesMercadoria(ddoFchIte.m_strCodMer);
		// m_pclsPmcMerPmc->ObtemMapaPromocao(&mapPmc);
		//
		// POSITION iPos = mapPmc->GetStartPosition();
		// while (NULL != iPos)
		// {
		// mapPmc->GetNextAssoc(iPos, strCodPmc, ddoTipPmc);
		//
		// boolPmc = m_mapFchPmc.Lookup(strCodPmc, ddoFchPmc);
		// if (! boolPmc)
		// {
		// // Não existem dados desta promoção, inicializa valores
		// ddoFchPmc.m_dblQdeItePrm = 0.00;
		// ddoFchPmc.m_dblVlrItePmc = 0.00;
		// ddoFchPmc.m_dblQdeItePmc = 0.00;
		// ddoFchPmc.m_strCodPmc = strCodPmc;
		// ddoFchPmc.m_strDesPmc = ddoTipPmc.m_strDesPmc;
		// ddoFchPmc.m_iTipPmc = ddoTipPmc.m_iTipPmc;
		// ddoFchPmc.m_iCodCtrPmc = 0;
		// ddoFchPmc.m_dblQdeVlrRto = 0.00;
		//
		// ddoFchPmc.m_mapFchPmcAux = NULL;
		// delete ddoFchPmc.m_mapFchPmcAux;
		// ddoFchPmc.m_mapFchPmcAux = new MapFchPmcAux();
		// ddoFchPmc.m_mapFchPmcAux->RemoveAll();
		// }
		//
		// if (ddoTipPmc.m_iTipPmc == 1)
		// {
		// boolPmcAux = ddoFchPmc.m_mapFchPmcAux->Lookup(ddoTipPmc.m_iNumSeqGrp,
		// ddoFchPmcAux);
		// if (! boolPmcAux)
		// {
		// // Não existem dados deste GRUPO da promoção, inicializa valores
		// ddoFchPmcAux.m_dblNumMnmIte = ddoTipPmc.m_dblNumMnmIte;
		// ddoFchPmcAux.m_dblVlrItePmc = 0.00;
		// ddoFchPmcAux.m_dblQdeItePmc = 0.00;
		// }
		//
		// ddoFchPmcAux.m_dblVlrItePmc += ((ddoFchIte.m_dblVlrLiqMer *
		// ddoFchIte.m_iQdeMerPed) + ddoFchIte.m_dblVlrTotImp +
		// (ddoFchIte.m_dblVlrFrtMer * ddoFchIte.m_iQdeMerPed));
		// ddoFchPmcAux.m_dblQdeItePmc += ddoFchIte.m_iQdeMerPed;
		// ddoFchPmc.m_mapFchPmcAux->SetAt(ddoTipPmc.m_iNumSeqGrp,
		// ddoFchPmcAux);
		// }
		//
		// ddoFchPmc.m_dblVlrItePmc += ((ddoFchIte.m_dblVlrLiqMer *
		// ddoFchIte.m_iQdeMerPed) + ddoFchIte.m_dblVlrTotImp +
		// (ddoFchIte.m_dblVlrFrtMer * ddoFchIte.m_iQdeMerPed));
		// ddoFchPmc.m_dblQdeItePmc += ddoFchIte.m_iQdeMerPed;
		// m_mapFchPmc.SetAt(strCodPmc, ddoFchPmc);
		// }
		// }
	}

	private static void verificaPromocoes(Item item) {
		// void clsPed::VerificaPromocoes(DdoStaFch &ddoStaFch, CPtrArray *
		// array)
		// {
		// CString strCodPmc;
		// DdoFchPmc ddoFchPmc;
		//
		// POSITION posPmc = m_mapFchPmc.GetStartPosition();
		// while (NULL != posPmc)
		// {
		// m_mapFchPmc.GetNextAssoc(posPmc, strCodPmc, ddoFchPmc);
		// if (ddoFchPmc.m_dblQdeItePrm > 0.00)
		// {
		// ddoStaFch.m_iSta = m_clsPmcVldFchPmc.PromocaoValida(strCodPmc,
		// &ddoFchPmc);
		// if (RN_OK != ddoStaFch.m_iSta)
		// {
		// ddoStaFch.m_dblQdeVlrRto = ddoFchPmc.m_dblQdeVlrRto;
		// ddoStaFch.m_strCodPmc = ddoFchPmc.m_strCodPmc;
		// ddoStaFch.m_strDesPmc = ddoFchPmc.m_strDesPmc;
		// ddoStaFch.m_iTipPmc = ddoFchPmc.m_iTipPmc;
		// ddoStaFch.m_iCodCtrPmc = ddoFchPmc.m_iCodCtrPmc;
		// AdicionaErroNaLista(array, &ddoStaFch);
		// }
		// }
		// }
	}

	private static void verificaQuantidade(Pedido pedido) {
		if (pedido.quantidadeMercadoria < QUANTIDADE_MINIMA_MERCADORIA || pedido.quantidadeMercadoria > QUANTIDADE_MAXIMA_MERCADORIA) {
			
			// Regra: Se for cotacao e a quantidade for igual a zero = OK
			//        Se NAO for cotacao e a quantidade for diferente de zero = ERRO
			if (!"C".equalsIgnoreCase(pedido.tipoPedido) && pedido.quantidadeMercadoria != 0){
				mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_ERRO_QDE_ITEM_INVALIDA.codigo));
			}
		}
	}

	private static void verificaBonificacaoAplicada(Item item) {
		// int clsPed::VerificaBonificacaoAplicada(DdoStaFch &ddoStaFch, double
		// dblVlrTotEco)
		// {
		// DdoBnfApl ddoBnfApl;
		// double dblVlrTotBnf = 0.00;
		//
		// if (m_mapBnfApl->Lookup(m_strCodFilEpd, ddoBnfApl))
		// {
		// TCHAR * szTerm = TEXT("\0");
		// dblVlrTotBnf = _tcstod((LPCTSTR) ddoBnfApl.m_strVlrBnfIte, &szTerm);
		// }
		//
		// double dblPerUtzEcoFlx = P_CLSREP->ObterPerUtzEcoFlx();
		//
		// if (dblVlrTotBnf > dblVlrTotEco * (dblPerUtzEcoFlx / 100))
		// {
		// ddoStaFch.m_dblVlrBnfFil = dblVlrTotBnf;
		// ddoStaFch.m_dblVlrMaxBnf = dblVlrTotEco * (dblPerUtzEcoFlx / 100);
		//
		// return (RN_ERRO_BONIFICACAO_INDEVIDA);
		// }
		//
		// return (RN_OK);
	}

	private static void verificaVencimentoCondicao(Item item) {
		// int clsPed::VerificaVencimentoCondicao(const DdoFchIte &ddoFchIte,
		// DdoStaFch &ddoStaFch)
		// {
		// CString strCodCid, strEscopo, strHraSegPrp,strCodCli, strCodBai,
		// strCodUndEsr;
		// CString strPrxData, strHraRtz, strQdeDias, strQdeDiasRtz,
		// strCodCndPgt, strChvMap, strTmpPrz;
		//
		// int iVlrRet, iQdeDias, iCodCli, iCodBai,iCodUndEsr;
		// double dVlrSegPed;
		//
		// POSITION pos;
		// BOOL bTestVerif = FALSE;
		// DdoFchCndPgt ddoFchCndPgt;
		//
		// TCHAR * szTerm = TEXT("\0");
		//
		// m_Cli.ObtemCodigoDaCidadeEntrega(strCodCid);
		// m_Cli.ObtemCodigoDoCliente(strCodCli);
		// m_Cli.ObtemCodigoBairroEntrega(strCodBai);
		// m_Cli.ObtemUnidadeNegocio(strCodUndEsr);
		// strEscopo = ddoFchIte.m_strCodFilEpd + strCodCid;
		//
		// clsAgr PCAAGR;
		// PCAAGR.AbreTabela();
		//
		// iCodCli = _ttoi((LPCTSTR) strCodCli);
		// iCodBai = _ttoi((LPCTSTR) strCodBai);
		// iCodUndEsr = _ttoi((LPCTSTR) strCodUndEsr );
		//
		// BOOL bRtzFilFatPed;
		// CString strCodRegDtb;
		// m_Cli.ObtemRegiaoDistribuicao(ddoFchIte.m_strCodFilEpd,
		// strCodRegDtb);
		//
		// strEscopo = ddoFchIte.m_strCodFilEpd + strCodRegDtb + strCodCid;
		//
		//
		// iVlrRet = PCAAGR.ObtemDadosProximaDataRoteirizacao(strEscopo,
		// strPrxData, strHraRtz, strQdeDias, strQdeDiasRtz, strHraSegPrp,
		// iCodCli, iCodBai, iCodUndEsr,
		// _ttoi((LPCTSTR)ddoFchIte.m_strCodFilFat), bRtzFilFatPed);
		// if (RN_OK == iVlrRet)
		// {
		// strHraSegPrp.TrimLeft();
		// dVlrSegPed = _tcstod((LPCTSTR) strHraSegPrp, &szTerm);
		//
		// if ((strHraSegPrp == TEXT("")) || (dVlrSegPed == 0.00) ||
		// ddoFchIte.m_lTipCobCnd == TIPCOBCNDCASH)
		// {
		// PCAAGR.FechaTabela();
		//
		// return (iVlrRet);
		// }
		// else
		// {
		// strQdeDias.TrimLeft();
		// strQdeDias.TrimRight();
		// iQdeDias = _ttoi((LPCTSTR) strQdeDias);
		//
		// pos = m_mapFchCndPgtEpd.GetStartPosition();
		// while ((NULL != pos) && (bTestVerif == FALSE))
		// {
		// m_mapFchCndPgtEpd.GetNextAssoc(pos, strChvMap, ddoFchCndPgt);
		// strCodCndPgt = strChvMap.Right(4);
		//
		// if (ddoFchIte.m_strCodCndPgt == strCodCndPgt)
		// {
		// if (ddoFchIte.m_iDiasPrazo < iQdeDias)
		// {
		// bTestVerif = TRUE;
		//
		// iVlrRet = RN_ERRO_VENCIMENTO_CONDICAO;
		// ddoStaFch.m_iSta = iVlrRet;
		// strTmpPrz.Format(TEXT("%2d"), ddoFchIte.m_iDiasPrazo);
		// ddoStaFch.m_strQdeVncCnd = strTmpPrz + TEXT(" dias");
		// ddoStaFch.m_strQdeVncEnt = strQdeDias + TEXT(" dias");
		// }
		// }
		// }
		// }
		// }
		//
		// PCAAGR.FechaTabela();
		//
		// return (iVlrRet);
	}

	private static void verificaMercadoriaIsentoPisCofins(Item item) {
		// void clsPed::VerificaMercadoriaIsentoPisCofins(CPtrArray * array)
		// {
		// int iQdeMerIsnResMes, iQdeMerIsnResAno;
		// P_CLSCLI->ObtemQdeMerIsentaPisCofins(iQdeMerIsnResMes, iQdeMerIsnResAno);
		//
		// if ((m_iQdeMerIsnPisCofins > iQdeMerIsnResMes) ||
		// (m_iQdeMerIsnPisCofins > iQdeMerIsnResAno))
		// {
		// CString strChv;
		// DdoFchIte ddoFchIte;
		// DdoStaFch ddoStaFch;
		//
		// if (m_iQdeMerIsnPisCofins > iQdeMerIsnResAno)
		// {
		// ddoStaFch.m_bIndRstAno = TRUE;
		// ddoStaFch.m_iQdeMaxMer = iQdeMerIsnResAno;
		// }
		// else
		// {
		// ddoStaFch.m_bIndRstAno = FALSE;
		// ddoStaFch.m_iQdeMaxMer = iQdeMerIsnResMes;
		// }
		//
		// POSITION pos;
		// pos = m_mapItemIsn.GetStartPosition();
		//
		// while (NULL != pos)
		// {
		// m_mapItemIsn.GetNextAssoc(pos, strChv, ddoFchIte);
		//
		// ddoStaFch.m_iSta = RN_ERRO_QUOTA_MP_DO_BEM;
		// ddoStaFch.m_iQdeMerPed = ddoFchIte.m_iQdeMerPed;
		// ddoStaFch.m_strCodMer = ddoFchIte.m_strCodMer;
		// ddoStaFch.m_strDesMer = ddoFchIte.m_strDesMer;
		// ddoStaFch.m_strCodFilFat = ddoFchIte.m_strCodFilFat;
		// ddoStaFch.m_strCodCndPgt = ddoFchIte.m_strCodCndPgt;
		// ddoStaFch.m_strNumNotFsc.Format(TEXT("%02d"),
		// ddoFchIte.m_iNumNotFsc);
		// AdicionaErroNaLista(array, &ddoStaFch);
		// }
		// }
	}

	private static void verificaParticipacaoBrindeBeneficio(Item item) {
		// void clsPed::VerificaParticipacaoBrindeBeneficio(CPtrArray * array,
		// CString strCodFilEpd)
		// {
		// CString strChv;
		// double dblVlrVndLiq, dblVlrBfcLiq, dblPerVlrBnf, dblPerBnfPed;
		//
		// DdoFchTipNgc ddoFchTipNgc;
		// DdoStaFch ddoStaFchAuxiliar;
		//
		// strChv = strCodFilEpd + TIPNGCVND;
		// if (m_mapTipNgcMer.Lookup(strChv, ddoFchTipNgc))
		// {
		// dblVlrVndLiq = ddoFchTipNgc.m_dblVlrBnfLiq;
		// }
		// else
		// {
		// dblVlrVndLiq = 0.00;
		// }
		//
		// strChv = strCodFilEpd + TIPNGCBFC;
		// if (m_mapTipNgcMer.Lookup(strChv, ddoFchTipNgc))
		// {
		// dblVlrBfcLiq = ddoFchTipNgc.m_dblVlrBnfLiq;
		// }
		// else
		// {
		// dblVlrBfcLiq = 0.00;
		// }
		//
		// P_CLSREP->ObtemPercentuaisBonificacoes(dblPerVlrBnf, dblPerBnfPed);
		//
		// if ((dblVlrVndLiq <= 0.00) || (ROUNDDBL(((dblVlrBfcLiq /
		// dblVlrVndLiq) * 100), NUM_CSA_DEC_DSC) > dblPerBnfPed))
		// {
		// ddoStaFchAuxiliar.m_iSta = RN_ERRO_PARTICIPACAO_BENEFICIO;
		// ddoStaFchAuxiliar.m_dblVlrTotalPedido = dblVlrVndLiq;
		// ddoStaFchAuxiliar.m_dblVlrMerRestrita = dblVlrBfcLiq;
		// ddoStaFchAuxiliar.m_dblPerSoja = dblPerBnfPed;
		// ddoStaFchAuxiliar.m_strCodFilEpd = strCodFilEpd;
		// AdicionaErroNaLista(array, &ddoStaFchAuxiliar);
		// }
	}

	private static void verificaMinimoBoletos(Item item) {
		// void clsPed::VerificaMinimoBoletos(CString strCodFilEpdAtu, DdoStaFch
		// &ddoStaFch, CPtrArray * array)
		// {
		// double dblVlrEntCnd, dblVlrPclCnd, dblVlrTotPed, dblVlrTrfCob;
		//
		// CString strChv, strCodFilEpd, strCodFilFat, strCodCndPgt,
		// strVlrEntCnd, strVlrPclCnd, strParcelas;
		// DdoFchCndPgt ddoFchCndPgt;
		//
		// POSITION posMapBlt = m_mapFchCndPgtBlt.GetStartPosition();
		// while (NULL != posMapBlt)
		// {
		// m_mapFchCndPgtBlt.GetNextAssoc(posMapBlt, strChv, ddoFchCndPgt);
		//
		// strCodFilEpd = strChv.Left(3);
		// strCodFilFat = strChv.Mid(3, 3);
		// strCodCndPgt = strChv.Mid(6, 4);
		//
		// if (strCodFilEpd == strCodFilEpdAtu)
		// {
		// ddoStaFch.m_bIndRtcBfc0 = ddoFchCndPgt.m_bIndRtcBfc0;
		// ddoStaFch.m_bIndRtcBfc1 = ddoFchCndPgt.m_bIndRtcBfc1;
		//
		// dblVlrTrfCob = 0.00;
		// if ((ddoFchCndPgt.m_bFlgCndTrf) && (_ttoi((LPCTSTR) strCodCndPgt) !=
		// CODCNDPGTPRM) &&
		// (_ttoi((LPCTSTR) strCodCndPgt) != CODCNDPGTFLX) && (_ttoi((LPCTSTR)
		// strCodCndPgt) != CODCNDPGTBFC))
		// {
		// if (ddoFchCndPgt.m_iNumPclCnd > 0)
		// {
		// dblVlrTrfCob =
		// P_CLSCLI->ObtemValorTarifaCobranca(ddoFchCndPgt.m_lTipCobCnd);
		// }
		// }
		//
		// dblVlrTotPed = ddoFchCndPgt.m_dblVlrTotLiq +
		// ddoFchCndPgt.m_dblVlrTotImp;
		// CalculaValorParcelamentoMercadoria(dblVlrEntCnd, dblVlrPclCnd,
		// strCodCndPgt, ddoFchCndPgt.m_dblPerPriPcl, ddoFchCndPgt.m_iNumPclCnd,
		// dblVlrTotPed);
		//
		// FormatNumber(strVlrEntCnd, (dblVlrEntCnd + dblVlrTrfCob), 0);
		// FormatNumber(strVlrPclCnd, (dblVlrPclCnd + dblVlrTrfCob), 0);
		//
		// if (strVlrEntCnd == strVlrPclCnd)
		// {
		// strParcelas.Format(TEXT("%d x %s"), ddoFchCndPgt.m_iNumPclCnd,
		// strVlrPclCnd);
		// }
		// else
		// {
		// strParcelas.Format(TEXT("1x%s + %dx%s"), strVlrEntCnd,
		// (ddoFchCndPgt.m_iNumPclCnd - 1), strVlrPclCnd);
		// }
		//
		// if (((dblVlrEntCnd + dblVlrTrfCob) < ddoFchCndPgt.m_dblVlrMnmBlt) ||
		// ((dblVlrPclCnd + dblVlrTrfCob) < ddoFchCndPgt.m_dblVlrMnmBlt))
		// {
		// ddoStaFch.m_iSta = RN_ERRO_PED_VALORMINIMO_BOLETO;
		// ddoStaFch.m_strParcelas = strParcelas;
		// ddoStaFch.m_dblVlrMnm = ddoFchCndPgt.m_dblVlrMnmBlt;
		// ddoStaFch.m_strCodFilFat = strCodFilFat;
		// ddoStaFch.m_strCodCndPgt = strCodCndPgt;
		// AdicionaErroNaLista(array, &ddoStaFch);
		// }
		// }
		// }
	}

	private static void validaFilialFaturamento(Item item) {
		boolean retorno = FilialService.validarAssociacaoTerritorioFilialFaturamento(item.cliente.codigoTerritorio, item.codigoFilialFaturamento);
		if (!retorno) {
			// TODO VERIFICAR A MENSAGEM QUE DEVERA SER EXIBIDA
			//mensagens.add(PedidoMensagem.criaMensagem(TipoPedidoMensagem.RN_CLIENTE_SEM_LIVRO.codigo));
		}
	}

	private static String preparaPedido(Pedido ped, Representante representante, String versaoSistema) {
		Pedido pedido = PedidoDAO.carregaDadosPreparaPedido(Integer.valueOf(ped.numeroPedido));

		try {
			if (pedido.tipoPedido.equalsIgnoreCase(PEDIDO)) {
				String resultado = exportaDados(pedido, representante, versaoSistema);

				// Mudar nome de ARQUIVO para PEDIDO00
				BufferedWriter arquivo = new BufferedWriter(new FileWriter("\\Martins\\Transmit\\PEDIDO00.NOT", true));
				arquivo.write(resultado);
				arquivo.close();

				GrupoAfinidadePEE grupo = PedidoDAO.obtemDadosCabecalhoArquivoPEE();
				if (grupo != null) {
					geraArquivoPEE(grupo, representante.codigoRepresentante);
				}

				return MENSAGEM_SUCESSO;

			} else {
				// Cotação não pode ser preparada
				return MENSAGEM_PEDIDO_DEFINIDO_COTACAO;
			}

		} catch (Exception e) {
			return MENSAGEM_FALHA;
		}

	}

	private static String exportaDados(Pedido pedido, Representante representante, String versaoSistema) throws Exception {
		StringBuilder cabecalho = new StringBuilder();
		String quantidadeItens = pedido.quantidadeMercadoria.toString(); //pedido.quantidadeItens.toString();
		BigDecimal valorSTBMer;
		String representanteAgencia = PedidoDAO.obtemCodigoRepresentanteAgencia();
		String dataLivro = "0000-00-00";
		Integer delta;
		BigDecimal percentualUtilizacao;

		if (!cabecalho.equals("")) {
			cabecalho.append("\r\n");
		}

		// Formata registro de dados - Tipo C
		cabecalho.append("C");
		cabecalho.append(pedido.codigoCliente);
		// Percentual de Decréscimo de ICM (Desativado)
		cabecalho.append("0000");

		while (quantidadeItens.length() < 3) {
			quantidadeItens = STRING_ZERO.concat(quantidadeItens);
		}
		cabecalho.append(quantidadeItens);

		while (representante.codigoRepresentante.length() < 5) {
			representante.codigoRepresentante = STRING_ZERO.concat(representante.codigoRepresentante);
		}
		
		while (representanteAgencia.length() < 5) {
			representanteAgencia = STRING_ZERO.concat(representanteAgencia);
		}
		
		while (representante.tipoEntidade.length() < 2) {
			representante.tipoEntidade = STRING_ZERO.concat(representante.tipoEntidade);
		}

		if (representante.tipoEntidade.equals("06")) {
			if (!representante.equals(representanteAgencia)) {
				representante.tipoEntidade = "03";
				representante.codigoRepresentante = representanteAgencia;
			}
		}
		cabecalho.append(representante.codigoRepresentante);

		// Número do Pedido
		String numPedido = pedido.numeroPedido;
		while (numPedido.length() < 5) {
			numPedido = STRING_ZERO.concat(numPedido);
		}
		cabecalho.append(numPedido);

		// Número do Pedido do Cliente (apenas as 14 primeiras posições são gravadas)
		if (pedido.numeroPedidoCliente == null) {
			pedido.numeroPedidoCliente = "              ";
		} else {
			while (pedido.numeroPedidoCliente.length() < 14) {
				pedido.numeroPedidoCliente = pedido.numeroPedidoCliente.concat(" ");
			}
		}
		cabecalho.append(pedido.numeroPedidoCliente);

		cabecalho.append(dataLivro);

		// Número total de pedidos preparados
		Integer totalPedidosPreparados = PedidoDAO.obtemTotalPedidosPreparados();

		if (totalPedidosPreparados.compareTo(99999) == 0) {
			totalPedidosPreparados = 00000;
		}

		String numUltimoPreparado = totalPedidosPreparados.toString();

		while (numUltimoPreparado.length() < 5) {
			numUltimoPreparado = STRING_ZERO.concat(numUltimoPreparado);
		}
		cabecalho.append(numUltimoPreparado);

		// Número da Versão do Sistema
		while (versaoSistema.length() < 4) {
			versaoSistema = STRING_ZERO.concat(versaoSistema);
		}
		cabecalho.append(versaoSistema);

		// Data da Preparação do Pedido
		cabecalho.append(DateUtil.formataData(new Date(), "yyyy-MM-ddHHmm"));

		// Código da Negociação
		if (null == pedido.codigoNegociacao) {
			cabecalho.append(0);
		} else {
			cabecalho.append(pedido.codigoNegociacao);
		}

		// Tipo de Entidade de Venda
		cabecalho.append(representante.tipoEntidade);

		// Código do Território do Cliente
		if (pedido.codigoTerritorio != null) {
			String codigoTerritorioCliente = pedido.codigoTerritorio.toString();
			while (codigoTerritorioCliente.length() < 5) {
				codigoTerritorioCliente = STRING_ZERO.concat(codigoTerritorioCliente);
			}
			cabecalho.append(codigoTerritorioCliente);
		} else {
			cabecalho.append(00000);
		}

		cabecalho.append(pedido.tipoVendaPedido.getValue());


		// Obtem a lista de itens do pedido
		pedido.listaItens = ItemPedidoService.obtemItensPedido(pedido.numeroPedido);

		for (Item item : pedido.listaItens) {
			cabecalho.append("\r\n");
			cabecalho.append("D");

			delta = TAMANHO_COD_MERCADORIA - item.mercadoria.codigo.toString().length();

			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.mercadoria.codigo);

			// Condição de Pagamento
			delta = 4 - item.condicaoPagamento.codigoCondicaoPagamento.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.condicaoPagamento.codigoCondicaoPagamento);

			// Quantidade do Item
			delta = 5 - item.mercadoria.qtdMercadoriaPedida.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.mercadoria.qtdMercadoriaPedida);

			// Seqüência da Nota Fiscal
			delta = 2 - item.notaFiscal.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.notaFiscal);

			// Valor Bruto da Mercadoria
			item.preco.valorBrutoMercadoria = trataValoresDecimais(item.preco.valorBrutoMercadoria.doubleValue());
			String valorBrutoMercadoria = item.preco.valorBrutoMercadoria.toString().replace(".", "");
			delta = 11 - valorBrutoMercadoria.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(valorBrutoMercadoria);

			// Cálculo de todos os percentuais de desconto concedidos no item
			BigDecimal percentualDesconto = obtemValoresPercentuais(item.percentualDescontoFlex.doubleValue());
			BigDecimal percentualAcaoTatica = obtemValoresPercentuais(item.percentualDescontoAcaoTatica.doubleValue());
			BigDecimal percentualSimplificado = obtemValoresPercentuais(item.percentualDescontoSimplificado.doubleValue());

			BigDecimal percentualDescontoFlex = percentualDesconto.subtract(percentualAcaoTatica.add(percentualSimplificado));

			if (percentualDescontoFlex.doubleValue() < 0) {
				percentualDescontoFlex = new BigDecimal(0);
			}

			BigDecimal percentualConcedido = percentualDesconto.subtract((percentualDescontoFlex.add(percentualSimplificado)));

			if (percentualConcedido.doubleValue() < 0) {
				percentualConcedido = new BigDecimal(0);
			} else {
				if (percentualConcedido.doubleValue() >= percentualAcaoTatica.doubleValue()) {
					percentualConcedido = percentualAcaoTatica;
				}
			}

			BigDecimal percentualConcedidoSimplificado = percentualDesconto.subtract((percentualConcedido.add(percentualDescontoFlex)));

			if (percentualConcedidoSimplificado.doubleValue() < 0.00) {
				percentualConcedidoSimplificado = new BigDecimal(0);
			}

			BigDecimal percentualMaximoAcao = obtemValoresPercentuais(item.comissao.percentualAcaoCliente.doubleValue());

			if (percentualConcedido.doubleValue() <= (percentualAcaoTatica.subtract(percentualMaximoAcao).doubleValue())) {
				percentualUtilizacao = new BigDecimal(0);
			} else {
				percentualUtilizacao = percentualConcedido.subtract((percentualAcaoTatica.subtract(percentualMaximoAcao)));
			}

			// Percentual Concedido de Desconto Flexível (2a. Prioridade)
			String percentual = percentualDescontoFlex.toString().replace(".", "");
			Integer result = percentual.length();

			delta = 5 - result;
			while (delta > 0) {
				percentual = STRING_ZERO.concat(percentual);
				delta--;
			}
			cabecalho.append(percentual);

			// Código de Flexibilização do Preço
			delta = 1 - item.preco.codigoFlexivelPreco.toString().length();
			while (delta > 0) {
				cabecalho.append(" ");
				delta--;
			}

			// Código da Promoção
			if (null == item.promocao.codigo) {
				cabecalho.append("0000");
			} else {
				delta = 4 - item.promocao.codigo.toString().length();
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
			}

			// Valor sem Encargos Vendor da Mercadoria
			item.valorSemEncargos = trataValoresDecimais(item.valorSemEncargos.doubleValue());
			String valorSemEncargos = item.valorSemEncargos.toString().replace(".", "");
			delta = 11 - valorSemEncargos.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(valorSemEncargos);

			// Valor Líquido da Mercadoria
			item.preco.valorLiquidoMercadoria = trataValoresDecimais(item.preco.valorLiquidoMercadoria.doubleValue());
			String valorLiquidoMercadoria = item.preco.valorLiquidoMercadoria.toString().replace(".", "");
			delta = 11 - valorLiquidoMercadoria.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(valorLiquidoMercadoria);

			// Código do Símbolo da Situação
			delta = 1 - item.preco.codigoSimboloSituacao.toString().length();
			while (delta > 0) {
				cabecalho.append(" ");
				delta--;
			}
			cabecalho.append(item.preco.codigoSimboloSituacao);

			// Tipo de Distribuição do Pedido
//			delta = 2 - item.tipoDistribuicaoMercadoria.toString().length();
			// TODO VERIFICAR SE item.tipoDistribuicaoMercadoria EH A MESMA COISA QUE item.codigoRegraDistribuicao,
			delta = 2 - item.codigoRegraDistribuicao.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.codigoRegraDistribuicao);

			// Código da Região de Distribuição
			// String codigoFilialExpedicao =
			// item.codigoFilialExpedicao.toString();
			//
			// while(codigoFilialExpedicao.length()<3){
			// codigoFilialExpedicao=" ".concat(codigoFilialExpedicao);
			// }

			Integer codigoRegionalDistribuicao = PedidoDAO.obtemRegiaoDistribuicao(pedido.codigoCliente, item.codigoFilialExpedicao);

			// cabecalho.append(codigoRegionalDistribuicao.toString().length() +
			// 1);

			delta = 4 - codigoRegionalDistribuicao.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(codigoRegionalDistribuicao);

			// Código da Filial de Expedição
			delta = 3 - item.codigoFilialExpedicao.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.codigoFilialExpedicao);

			// Código da Filial de Faturamento
			delta = 3 - item.codigoFilialFaturamento.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.codigoFilialFaturamento);

			// Código da Regra de Distribuição da Mercadoria
			delta = 5 - item.codigoRegraDistribuicao.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.codigoRegraDistribuicao);

			// Indicador de Recuperação Automática de Corte / Pedido Dinâmico
			// (Desativado)
			cabecalho.append(0);

			// Valor da Taxa de Entrega (Desativado)
			cabecalho.append("00000");

			if (brindeIntegral(item) && (!brindeIntegralPromocao(item))) {
				BigDecimal valorSTBTotal = obtemValoresPercentuais(item.stb.valorSTBTotalBonificacao.doubleValue());
				valorSTBMer = valorSTBTotal.divide(new BigDecimal(item.mercadoria.qtdMercadoriaPedida));
				percentual = valorSTBMer.toString().replace(".", "");
				result = percentual.length();
				
			} else {
				valorSTBMer = obtemValoresPercentuais(item.stb.valorSTBUnitario.doubleValue());
				BigDecimal valorSTBComplementar = obtemValoresPercentuais(item.stb.valorSTBComplementar.doubleValue());
				valorSTBMer = valorSTBMer.add(valorSTBComplementar);
				percentual = valorSTBMer.toString().replace(".", "");
				result = percentual.length();

			}

			delta = 7 - result;
			while (delta > 0) {
				percentual = STRING_ZERO.concat(percentual);
				delta--;
			}
			cabecalho.append(percentual);

			// Valor do Frete da Mercadoria
			item.mercadoria.valorFrete = trataValoresDecimais(item.mercadoria.valorFrete.doubleValue());
			String valorFrete = item.mercadoria.valorFrete.toString().replace(".", "");
			delta = 7 - valorFrete.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(valorFrete);

			// Percentual de Economia dos Benefícios Customizados
			String percentualEconomicoBeneficio = item.percentualEconomicoBeneficio.toString().replace(".", "");
			delta = 5 - percentualEconomicoBeneficio.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(percentualEconomicoBeneficio);

			// Percentual de Desconto dos Benefícios Customizados
			String percentualDescontoBeneficio = item.percentualDescontoBeneficio.toString().replace(".", "");
			delta = 5 - percentualDescontoBeneficio.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(percentualDescontoBeneficio);

			// Prazo Adicional dos Benefícios Customizados
			item.condicaoPagamento = CondicaoPagamentoService.buscaCondicaoPagamento(item.condicaoPagamento.codigoCondicaoPagamento);
			delta = 4 - item.condicaoPagamento.quantidadeDiaPrazo.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.condicaoPagamento.quantidadeDiaPrazo);

			// Percentual de Comissão Adicional dos Benefícios Customizados
			String percentualComissaoAdicional = item.preco.percentualComissaoAdicional.toString().replace(".", "");
			delta = 5 - percentualComissaoAdicional.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(percentualComissaoAdicional);

			// Percentual de Bonificação do Flexível Inteligente (Desativado)
			cabecalho.append("00000");

			// Percentual Máximo de Desconto das Ações Táticas
			String percentualDescontoAcaoTatica = item.percentualDescontoAcaoTatica.toString().replace(".", "");
			delta = 5 - percentualDescontoAcaoTatica.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(percentualDescontoAcaoTatica);

			// Percentual Concedido de Desconto das Ações Táticas (1a.
			// Prioridade)
			percentual = percentualConcedido.toString().replace(".", "");
			result = percentual.length();

			delta = 5 - result;
			while (delta > 0) {
				percentual = STRING_ZERO.concat(percentual);
				delta--;
			}
			cabecalho.append(percentual);

			// Percentual Máximo de Desconto do Tipo de Venda Simplificada
			delta = 5 - item.percentualDescontoSimplificado.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.percentualDescontoSimplificado);

			// Percentual Concedido de Desconto do Tipo de Venda Simplificada
			// (3a. Prioridade)
			percentual = percentualConcedidoSimplificado.toString().replace(".", "");
			result = percentual.length();
			delta = 5 - result;
			while (delta > 0) {
				percentual = STRING_ZERO.concat(percentual);
				delta--;
			}
			cabecalho.append(percentual);

			// Percentual de Acréscimo de Preço (SuperFlex) Concedido
			String percentualAcrescimoConcedido = item.percentualAcrescimoConcedido.toString().replace(".", "");
			delta = 5 - percentualAcrescimoConcedido.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(percentualAcrescimoConcedido);

			// Tipo de Esgotamento da Mercadoria
			delta = 1 - item.mercadoria.tipoEsgotamento.toString().length();
			while (delta > 0) {
				cabecalho.append(" ");
				delta--;
			}
			cabecalho.append(item.mercadoria.tipoEsgotamento);

			// Comissão Máxima pela Venda da Mercadoria
			item.comissao.comissaoMaximaMercadoria = trataValoresDecimais(item.comissao.comissaoMaximaMercadoria.doubleValue());
			String comissaoMaximaMercadoria = item.comissao.comissaoMaximaMercadoria.toString().replace(".", "");
			delta = 8 - comissaoMaximaMercadoria.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(comissaoMaximaMercadoria);

			// Comissão Concedida pela Venda da Mercadoria
			item.comissao.comissaoMercadoria = trataValoresDecimais(item.comissao.comissaoMercadoria.doubleValue());
			String comissaoConcessaoMercadoria = item.comissao.comissaoMercadoria.toString().replace(".", "");
			delta = 8 - comissaoConcessaoMercadoria.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(comissaoConcessaoMercadoria);

			// Valor do Desconto Especial da Mercadoria
			item.valorDescontoEspecial = trataValoresDecimais(item.valorDescontoEspecial.doubleValue());
			String valorDescontoEspecial = item.valorDescontoEspecial.toString().replace(".", "");
			delta = 7 - valorDescontoEspecial.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(valorDescontoEspecial);

			// Valor Total da Substituição Tributária (STB x Quantidade) da
			// Mercadoria
			BigDecimal valorSTBTotal;
			if (brindeIntegral(item) && (!brindeIntegralPromocao(item))) {
				valorSTBTotal = obtemValoresPercentuais(item.stb.valorSTBTotalBonificacao.doubleValue());
				percentual = valorSTBTotal.toString().replace(".", "");
				result = percentual.length();
			} else {
				valorSTBTotal = obtemValoresPercentuais(item.stb.valorSTBTotal.doubleValue());
				BigDecimal valorSTBComplemento = obtemValoresPercentuais(item.stb.valorSTBComplementar.doubleValue());
				valorSTBTotal = valorSTBTotal.add(valorSTBComplemento);
				percentual = valorSTBTotal.toString().replace(".", "");
				result = percentual.length();
			}
			delta = 7 - result;
			while (delta > 0) {
				percentual = STRING_ZERO.concat(percentual);
				delta--;
			}
			cabecalho.append(percentual);

			// Tipo de Negociação da Mercadoria
			delta = 1 - item.tipoNegociacaoMercadoria.toString().length();
			if (delta > 0) {
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
			} else {
				cabecalho.append(item.tipoNegociacaoMercadoria);
			}

			// Valor Bonificado no Preço da Mercadoria
			item.mercadoria.valorBeneficio = trataValoresDecimais(item.mercadoria.valorBeneficio.doubleValue());
			String valorBeneficio = item.mercadoria.valorBeneficio.toString().replace(".", "");
			delta = 11 - valorBeneficio.length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(valorBeneficio);

			// Valor Total do Imposto de Importação (IPI x Quantidade) da
			// Mercadoria
			if (brindeIntegral(item) && (!brindeIntegralPromocao(item))) {
				delta = 7 - item.valorIPITotal.toString().length();
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
				cabecalho.append(item.valorIPITotal);
			} else {
				delta = 7 - item.valorIPITotal.toString().length();
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
				cabecalho.append(item.valorIPITotalBonificacao);
			}

			// Indicador de Restrição de Benefícios Customizados (Venda ou
			// Bonificação)if
			if (item.tipoNegociacaoMercadoria.equals(0)) {
				delta = 1 - item.indicaRestricaoBeneficioCustomizado.toString().length();
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
				cabecalho.append(item.indicaRestricaoBeneficioCustomizado);
			} else {
				delta = 1 - item.indicaRestricaoBeneficioCustomizadoBrinde.toString().length();
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
				cabecalho.append(item.indicaRestricaoBeneficioCustomizadoBrinde);
			}

			// Indicador de Mercadoria KIT
			delta = 1 - item.mercadoria.indicaMercadoriaKit.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(0);

			// Os Pontos do item são inteiros
			delta = 7 - item.mercadoria.valorPontosBonificacao.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			// Sao necessarias 2 casas decimais do ponto zeradas
			cabecalho.append("00");

			// Indicador de Sinal dos pontos do item
			delta = 1 - item.mercadoria.valorPontosBonificacao.toString().length();
			if (delta > 0) {
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
			} else {
				cabecalho.append(item.mercadoria.valorPontosBonificacao);
			}

			// Desconto de Ação tática de Cliente concedida
			percentual = percentualUtilizacao.toString().replace(".", "");
			result = percentual.length();

			delta = 5 - result;
			while (delta > 0) {
				percentual = STRING_ZERO.concat(percentual);
				delta--;
			}
			cabecalho.append(" ");
			cabecalho.append(percentual);

			// Cor da margem do item
			delta = 1 - item.codigoCorMargemB.toString().length();
			if (delta > 0) {
				while (delta > 0) {
					cabecalho.append(0);
					delta--;
				}
			} else {
				cabecalho.append(item.codigoCorMargemB);
			}

			// Os Pontos do item são inteiros
			delta = 7 - item.valorPontuacaoMercadoria.toString().length();
			while (delta > 0) {
				cabecalho.append(0);
				delta--;
			}
			cabecalho.append(item.valorPontuacaoMercadoria);

			// String sinalPontoMercadoria;
			//
			// if(item.valorPontoMercadoria < 0){
			// sinalPontoMercadoria="";
			// }else{
			// sinalPontoMercadoria="N";
			// }

			// Preciso mandar 2 casas decimais do ponto zeradas
			cabecalho.append("00");

			// //Indicador de Sinal dos pontos do item
			// delta = 1 - sinalPontoMercadoria.toString().length();
			//
			// if(delta>0){
			// while(delta>0){
			// cabecalho.append(0);
			// delta--;
			// }
			// }else{
			// cabecalho.append(sinalPontoMercadoria);
			// }
		}
		PedidoDAO.atualizaUltimoPedidoPreparado((totalPedidosPreparados + 1), pedido.numeroPedido);

		return cabecalho.toString();
	}

	public static boolean brindeIntegral(Item item) {
		return (brindeIntegralPromocao(item) || (((item.tipoNegociacaoMercadoria.equals(2) || item.tipoNegociacaoMercadoria.equals(3))) && item.mercadoria.valorBeneficio.doubleValue() != 0.));
	}

	public static boolean brindeIntegralPromocao(Item item) {
		return !item.condicaoPagamento.codigoCondicaoPagamento.equals(
				TipoCondicaoPagamento.BRINDES_PROMOCOES.codigoCondicaoPagamento);
	}

	public static BigDecimal trataValoresDecimais(Double valor) {
		Integer tamanhoCampo = valor.toString().length();
		String val = valor.toString().substring(tamanhoCampo - 1, tamanhoCampo);
		if (val.length() < 2) {
			val = val.concat("0");
		}
		String valorPercentual = valor.toString().substring(0, tamanhoCampo - 2) + val;
		return Util.getBigDecimal(valorPercentual);
	}

	public static BigDecimal obtemValoresPercentuais(Double valor) {
		Integer tamanhoCampo = valor.toString().length();
		String val = valor.toString().substring(tamanhoCampo - 1, tamanhoCampo);
		if (val.length() < 2) {
			val = val.concat("0");
		}
		String valorPercentual = valor.toString().substring(0, tamanhoCampo - 2) + "." + val;
		return Util.getBigDecimal(valorPercentual);
	}

	private static void geraArquivoPEE(GrupoAfinidadePEE grupo, String codigoRepresentante) throws IOException {
		StringBuilder linha = new StringBuilder();

		if (!"".equals(linha.toString())) {
			linha.append("\r\n");
		}

		linha.append("C");

		String filialExp = grupo.codFilialExpedicao.toString();
		while (filialExp.length() < 3) {
			filialExp = STRING_ZERO.concat(filialExp);
		}
		linha.append(filialExp);

		String filialFat = grupo.codFilialFaturamento.toString();
		while (filialFat.length() < 3) {
			filialFat = STRING_ZERO.concat(filialFat);
		}
		linha.append(filialFat);

		String relacaoCidade = grupo.numRelacaoCidade.toString();
		while (relacaoCidade.length() < 5) {
			relacaoCidade = STRING_ZERO.concat(relacaoCidade);
		}
		linha.append(relacaoCidade);

		String codigoGrupo = grupo.codGrupoAfinidade.toString();
		while (codigoGrupo.length() < 4) {
			codigoGrupo = STRING_ZERO.concat(codigoGrupo);
		}
		linha.append(codigoGrupo);

		while (codigoRepresentante.length() < 5) {
			codigoRepresentante = STRING_ZERO.concat(codigoRepresentante);
		}
		linha.append(codigoRepresentante);

		List<GrupoAfinidadePEE> lista = PedidoDAO.obtemItensArquivoPEE();

		for (GrupoAfinidadePEE p : lista) {
			linha.append("\r\n");

			String codigoMercadoria = p.codMercadoria.toString();
			while (codigoMercadoria.length() < TAMANHO_COD_MERCADORIA) {
				codigoMercadoria = STRING_ZERO.concat(codigoMercadoria);
			}
			linha.append(codigoMercadoria);
			PedidoDAO.atualizaTabelaArquivoPEE(p.codMercadoria);
		}

		// Mudar nome de RAF para PCAAFD00
		BufferedWriter arquivo = new BufferedWriter(new FileWriter("\\Martins\\Transmit\\RAF.NOT", true));
		arquivo.write(linha.toString());
		arquivo.close();
	}

}