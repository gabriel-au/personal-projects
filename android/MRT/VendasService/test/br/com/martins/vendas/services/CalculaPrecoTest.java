package br.com.martins.vendas.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.martins.vendas.dao.ItemDisponivelDAO;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.ComissaoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Comissao;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.RegraDistribuicao;
import br.com.martins.vendas.vo.Representante;

public class CalculaPrecoTest {

	Item item;
	Mercadoria mercadoria;
	CondicaoPagamento condicaoPagamento;
	Cliente cliente;
	RegraDistribuicao regraDistribuicao;
	Representante representante;

	int codigoFilialExpedicao;
	int codigoFilialFaturamento;
	int numeroRelacaoCidade;
	int quantidadeMercadoria;
	int quantidadeMercadoriasSimilares;
	int codigoAtividade;
	TipoVendaPedido tipoVendaPedido;
	int tipoNegociacao;

	String siglaEstado;
	String flagPrecoEspecial;
	String siglaEstadoDestino;
	String siglaEstadoOrigem;

	BigDecimal valorDescontoBeneficio;
	BigDecimal percentualAcrescimoConcedido;
	BigDecimal percentualDescontoConcedido;
	BigDecimal percentualAcaoTatica;
	BigDecimal percentualDescontoFlex;
	BigDecimal percentualDescontoSimplificado;

	int codigoTerritorioVenda;

	@BeforeClass
	public static void setUpBeforeClass() {
//		ConfigUtils.config();
	}

	@Before
	public void setUp() {
		mercadoria = new Mercadoria();
		mercadoria.codigo = 1500068;
		condicaoPagamento = new CondicaoPagamento();
		condicaoPagamento.codigoCondicaoPagamento = 1080;
		condicaoPagamento.tipoCobrancaCondicao = 2;
		condicaoPagamento.fatorCondicaoPagamento = BigDecimal.valueOf(1.164);
		condicaoPagamento.fatorEncargoVendor = BigDecimal.valueOf(1.164);
		condicaoPagamento.tipoFinanciamento = 2;
		condicaoPagamento.tipoPrazoCondicao = 4;
		condicaoPagamento.indicaCondicaoTribanco = 0; // 1= true / 0 = false

		cliente = new Cliente();
		cliente.canal = 9;
		cliente.codigoCliente = 1381181;// 1954599;
		cliente.tipoLimiteCredito = "E";
		cliente.codigoGrupoCliente = 533; // 400;
		cliente.codigoClassificacao = "0";
		cliente.flagClienteConsumidor = "*";

		regraDistribuicao = new RegraDistribuicao();
		regraDistribuicao.codigoRegra = 1;
		regraDistribuicao.codigoTabelaFrete = 1;
		regraDistribuicao.indicadorTabelaManual = 0;
		regraDistribuicao.percentualPadraoFrete = BigDecimal.TEN;
		regraDistribuicao.indicadorContaDestino = 1;
		regraDistribuicao.valorFreteMinimo = BigDecimal.valueOf(100);

		codigoFilialExpedicao = 1;
		codigoFilialFaturamento = 1;
		numeroRelacaoCidade = 52; // 35;
		siglaEstado = "AL";
		siglaEstadoOrigem = "MG";
		siglaEstadoDestino = "SP";
		tipoNegociacao = 0;
		quantidadeMercadoria = 1;
		quantidadeMercadoriasSimilares = 0;
		flagPrecoEspecial = "1";

		codigoAtividade = 1;

		valorDescontoBeneficio = null;
		percentualAcrescimoConcedido = BigDecimal.ZERO;// BigDecimal.TEN;
		percentualDescontoConcedido = BigDecimal.valueOf(5);

		// setado para '1' para pegar o tipo de venda de pedido simplificado
		tipoVendaPedido = TipoVendaPedido.SIMPLIFICADA;
		codigoTerritorioVenda = 0;

		representante = new Representante();
		representante.naturezaRepresentante = "F";
		
		item = new Item();
		item.mercadoria = mercadoria;
	}

	@Test
	public void testaCalculoPrecoBruto() {
		mercadoria = ItemDisponivelDAO.buscaMercadoriaPorCodigo(mercadoria.codigo);
		assertNotNull(mercadoria);
		
		Item item = calculaPreco();
		assertNotNull(item);
		assertEquals(item.mercadoria.codigo, mercadoria.codigo);
	}

	@Test
	public void testaCalculoDeComissao() {
		mercadoria = ItemDisponivelDAO.buscaMercadoriaPorCodigo(mercadoria.codigo);
		mercadoria.indicaMercadoriaKit = 0;
		assertNotNull(mercadoria);

		Item item = calculaPreco();
		assertNotNull(item);

		boolean isUltimoItem = false;
		boolean isComissaoBruta = true;

		Comissao comissao = ComissaoService.calculaComissaoMercadoria(mercadoria, 
				item,
				tipoNegociacao,
				condicaoPagamento, 
				cliente, 
				siglaEstadoOrigem, 
				quantidadeMercadoria, 
				codigoFilialExpedicao,
				representante, 
				isUltimoItem, 
				isComissaoBruta,
				true,
				true,
				true);
		assertNotNull(comissao.resultado);
		assertFalse(comissao.resultado.doubleValue() != 0.0);
	}

	/**
	 * Calcula preco.
	 * 
	 * @return the item
	 */
	private Item calculaPreco() {
		try {
			return CalculoService.calculaPreco(item, cliente);
		} catch (IntegrationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
