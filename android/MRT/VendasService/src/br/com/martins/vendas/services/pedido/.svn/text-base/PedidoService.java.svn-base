package br.com.martins.vendas.services.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.pedido.PedidoDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpGrpDeDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpImpPedDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMeGoDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMePmcDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMePrmDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMeSmrDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMemCoDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpMerRecDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpPcaBcdDAO;
import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.services.ItemPedidoService;
import br.com.martins.vendas.services.calculodepreco.CalculoUtil;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Pedido;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class PedidoService {

	private static final String TAG = PedidoService.class.getName();

	/**
	 * Método responsável por obter o último pedido de um cliente.
	 * 
	 * @param codigoCliente
	 *            objeto do tipo <code>java.lang.Integer</code> que corresponde
	 *            ao código de um cliente.
	 * @return objeto do tipo <code>br.com.martins.vendas.vo.Pedido</code> que
	 *         corresponde a último pedido de um cliente.
	 */
	public static Pedido obtemUltimoPedido(Integer codigoCliente) {
		return PedidoDAO.obtemUltimoPedido(codigoCliente);
	}

	public static String[] obtemDadosPcasos() {
		return PedidoDAO.obtemDadosPcasos();
	}
	
	/**
	 * Método responsável por obter todos os pedidos de um cliente.
	 * 
	 * @param codigoCliente
	 *            objeto do tipo <code>java.lang.Integer</code> que corresponde
	 *            ao código de um cliente.
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.vo.Pedido></code> que
	 *         corresponde a lista de pedidos de um cliente.
	 */
	public static List<Pedido> obtemTodosPedidos(Integer codigoCliente) {
		// TODO Verificar se há a necessidade de trazer os itens do pedido
		// também. No momento não estão sendo anexados. Para trazê-los, basta
		// trocar o parametro "false" para "true".
		return obtemListaPedidos(PedidoDAO.obtemTodosPedidos(codigoCliente), false);
	}

	/**
	 * Método responsável por excluir um pedido e seus itens.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            código do pedido.
	 * @return literal <code>boolean</code> que corresponde ao sucesso ou falha
	 *         da exclusão de um pedido e de seus itens.
	 */
	public static boolean excluiPedido(Integer codigoPedido) {
		return PedidoDAO.excluiPedido(codigoPedido);
	}

	/**
	 * 
	 * @param numeroPedido
	 * @return
	 */
	public static boolean isPedidoPreparado(Integer numeroPedido) {
		return PedidoDAO.isPedidoPreparado(numeroPedido);
	}

//	public static PedidoStatus obtemStatusPedido(Integer codigoPedido) {
//		return PedidoDAO.obtemStatusPedido(codigoPedido);
//	}
	

	/**
	 * Método responsável por obter os itens vendidos na semana.
	 * 
	 * @param inicioSemana
	 *            objeto do tipo <code>java.util.Date</code> correspondente a
	 *            data inicial.
	 * @param fimSemana
	 *            objeto do tipo <code>java.util.Date</code> correspondente a
	 *            data final.
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.vo.Pedido></code> que
	 *         corresponde a lista de pedidos de um cliente.
	 */
	public static List<Pedido> obtemItensVendidosSemana(Date inicioSemana, Date fimSemana) {
		return obtemListaPedidos(PedidoDAO.obtemItensVendidosSemana(inicioSemana, fimSemana), true);
	}

	/**
	 * Método responsável por carregar os detalhes de um determinado pedido.
	 * 
	 * @param numeroPedido
	 *            objeto do tipo <code>java.lang.Integer</code> referente ao
	 *            número do pedido.
	 * @param codigoCanal
	 *            objeto do tipo <code>java.lang.Integer</code> referente ao
	 *            código do canal.
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.vo.Pedido></code>
	 *         referente aos detalhes do pedido.
	 */
	public static List<Pedido> carregaDetalhesPedido() {
		List<Pedido> lista = defineDetalhesPedidos(PedidoDAO.carregaDetalhesPedido());
		return criaMapaValoresCad(lista);
	}

	/**
	 * 
	 * @param lista
	 * @return
	 */
	public static List<Pedido> criaMapaValoresCad(List<Pedido> lista) {
		List<Pedido> listaDetalhes = new ArrayList<Pedido>();
		List<Integer> listaCad = new ArrayList<Integer>();
		Map<Integer, BigDecimal> mapaLiqImpFrete = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> mapaPoupeCerto = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> mapaPoupeCertoBnd = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> mapaBonificacao = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> mapaValorMin = new HashMap<Integer, BigDecimal>();

		for (Pedido p : lista) {
			BigDecimal valorLiqImpFrete = mapaLiqImpFrete.get(p.filialExpedicao);
			BigDecimal valorPoupeCerto = mapaPoupeCerto.get(p.filialExpedicao);
			BigDecimal valorPoupeCertoBnd = mapaPoupeCertoBnd.get(p.filialExpedicao);
			BigDecimal bonificacao = mapaBonificacao.get(p.filialExpedicao);

			if (!listaCad.contains(p.filialExpedicao)) {
				listaCad.add(p.filialExpedicao);
			}

			if (mapaLiqImpFrete.containsKey(p.filialExpedicao)) {
				valorLiqImpFrete = valorLiqImpFrete.add(p.valorLiqImpFrete);
				mapaLiqImpFrete.put(p.filialExpedicao, valorLiqImpFrete);
			} else {
				mapaLiqImpFrete.put(p.filialExpedicao, p.valorLiqImpFrete);
			}

			if (mapaPoupeCerto.containsKey(p.filialExpedicao)) {
				valorPoupeCerto = valorPoupeCerto.add(p.valorPoupeCerto);
				mapaPoupeCerto.put(p.filialExpedicao, valorPoupeCerto);
			} else {
				mapaPoupeCerto.put(p.filialExpedicao, p.valorPoupeCerto);
			}

			if (mapaPoupeCertoBnd.containsKey(p.filialExpedicao)) {
				valorPoupeCertoBnd = valorPoupeCertoBnd.add(p.valorPoupeCertoBND);
				mapaPoupeCertoBnd.put(p.filialExpedicao, valorPoupeCertoBnd);
			} else {
				mapaPoupeCertoBnd.put(p.filialExpedicao, p.valorPoupeCertoBND);
			}

			if (mapaBonificacao.containsKey(p.filialExpedicao)) {
				bonificacao = bonificacao.add(p.valorBonificacao);
				mapaBonificacao.put(p.filialExpedicao, bonificacao);
			} else {
				mapaBonificacao.put(p.filialExpedicao, p.valorBonificacao);
			}
			if (!mapaValorMin.containsKey(p.filialExpedicao)) {
				mapaValorMin.put(p.filialExpedicao, p.valorMinimo);
			}
		}

		Pedido ped;

		for (Integer cad : listaCad) {
			ped = new Pedido();
			ped.filialExpedicao = cad;
			ped.valorLiqImpFrete = mapaLiqImpFrete.get(cad);
			ped.valorPoupeCerto = mapaPoupeCerto.get(cad);
			ped.valorPoupeCertoBND = mapaPoupeCertoBnd.get(cad);
			ped.valorBonificacao = mapaBonificacao.get(cad);
			ped.valorMinimo = mapaValorMin.get(cad);
			listaDetalhes.add(ped);
		}

		return listaDetalhes;
	}

	/**
	 * Método responsável por carregar os detalhes de um pedido do caminhão.
	 * 
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.services.calculodepreco.Item></code>
	 *         referente aos detalhes do pedido.
	 */
	public static List<Item> carregaDetalhesPedidoCaminhao() {
		return TabelaTmpItePeDAO.obtemTodosItens();
	}

	/**
	 * Método responsável por calcular os valores CAD.
	 * 
	 * @param listaPedido
	 *            objeto do tipo
	 *            <code>java.util.List<br.com.martins.vendas.vo.Pedido></code>
	 *            referente a lista de pedidos para efetuar o cálculo dos
	 *            valores CAD.
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.vo.Pedido></code>
	 *         referente a lista de pedidos com seus valores CAD atualizados.
	 */
	public static List<Pedido> calculaValoresCAD(List<Pedido> listaPedido) {
		List<Pedido> lista = new ArrayList<Pedido>();
		BigDecimal cento = new BigDecimal(100);

		for (Pedido pedido : listaPedido) {
			BigDecimal percentualCnsEconomico = new BigDecimal(0);
			BigDecimal percentualEconomicoFlex = new BigDecimal(0);
			BigDecimal valorRepresentante = new BigDecimal(80);
			BigDecimal totalImposto = new BigDecimal(0);

			pedido.valorSemEncargos = pedido.valorLiquidoMercadoria.divide(pedido.fatorCondicaoPagamento, RoundingMode.HALF_UP);

			if (pedido.valorSemEncargos.doubleValue() <= 0.00) {
				pedido.valorSemEncargos = new BigDecimal("0.01");
			}

			percentualCnsEconomico = pedido.perDescontoFlex.subtract(pedido.percAcaoTatica.add(pedido.perMaxSimples));
			percentualEconomicoFlex = pedido.perMaxFlex.subtract(percentualCnsEconomico);
			BigDecimal quantidade = new BigDecimal(pedido.quantidadeMercadoria);
			pedido.valorBonificacao = pedido.valorBeneficioMercadoria.multiply(quantidade);
			pedido.valorPoupeCerto = (percentualEconomicoFlex.multiply(quantidade.multiply(pedido.valorSemEncargos))).divide(cento).subtract(pedido.valorBonificacao);
			pedido.valorPoupeCertoBND = (((((percentualEconomicoFlex.multiply(quantidade.multiply(pedido.valorSemEncargos)).divide(cento))).multiply(valorRepresentante)).divide(cento)).subtract(pedido.valorBonificacao));
			pedido.valorFrete = pedido.valorFrete.multiply(quantidade);
			totalImposto = pedido.valorTotalIpi.add(pedido.valorTotalStb);
			pedido.valorLiqImpFrete = pedido.valorLiquidoMercadoria.multiply(quantidade).add(pedido.valorFrete).add(totalImposto);
			lista.add(pedido);
		}

		return lista;
	}

	public static Pedido calculaValoresCAD(Pedido pedido) {

		BigDecimal cento = new BigDecimal(100);

		BigDecimal percentualCnsEconomico = new BigDecimal(0);
		BigDecimal percentualEconomicoFlex = new BigDecimal(0);
		BigDecimal valorRepresentante = new BigDecimal(80);
		BigDecimal totalImposto = new BigDecimal(0);

		pedido.valorSemEncargos = pedido.valorLiquidoMercadoria.divide(pedido.fatorCondicaoPagamento, RoundingMode.HALF_UP);

		if (pedido.valorSemEncargos.doubleValue() <= 0.00) {
			pedido.valorSemEncargos = new BigDecimal("0.01");
		}

		percentualCnsEconomico = pedido.perDescontoFlex.subtract(pedido.percAcaoTatica.add(pedido.perMaxSimples));
		percentualEconomicoFlex = pedido.perMaxFlex.subtract(percentualCnsEconomico);
		BigDecimal quantidade = new BigDecimal(pedido.quantidadeMercadoria);
		pedido.valorBonificacao = pedido.valorBeneficioMercadoria.multiply(quantidade);
		pedido.valorPoupeCerto = (percentualEconomicoFlex.multiply(quantidade.multiply(pedido.valorSemEncargos))).divide(cento).subtract(pedido.valorBonificacao);
		pedido.valorPoupeCertoBND = (((((percentualEconomicoFlex.multiply(quantidade.multiply(pedido.valorSemEncargos)).divide(cento))).multiply(valorRepresentante)).divide(cento)).subtract(pedido.valorBonificacao));
		pedido.valorFrete = pedido.valorFrete.multiply(quantidade);
		totalImposto = pedido.valorTotalIpi.add(pedido.valorTotalStb);
		pedido.valorLiqImpFrete = pedido.valorLiquidoMercadoria.multiply(quantidade).add(pedido.valorFrete).add(totalImposto);

		return pedido;
	}

	/**
	 * Método responsável por calcular os valores relativos a Filial.
	 * 
	 * @param valorIpi
	 *            valor do IPI
	 * @param valorSemEncargos
	 *            valor sem encargos
	 * @param fatorCondPagamento
	 *            fator de condição de pagamento
	 * @param valorStb
	 *            valor STB
	 * @param valorLiqMercadoria
	 *            valor líquido da mercadoria
	 * @param qtdMercadoria
	 *            quantidade da mercadoria
	 * @param filialFaturamento
	 *            filial de faturamento
	 * @param numParcelas
	 *            número de parcelas
	 * @param qtdDiasPrazo
	 *            dias de prazo
	 * @param diasPrazoBeneficiario
	 *            dias de prazo para o beneficiário
	 * @param periodoParc
	 *            período de parcelas
	 * @param condPagamento
	 *            condição de pagamento
	 * @param numNotaFiscal
	 *            número da nota fiscal
	 * @param filialExp
	 *            filial de expedição
	 * @return
	 */
	public static List<Pedido> calculaValoresFilial(Integer filialExp) {
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		List<Pedido> lista = PedidoDAO.carregaDetalhesFilialFat(filialExp);

		for (Pedido pedido : lista) {
			pedido.valorSemEncargos = pedido.valorLiquidoMercadoria.divide(pedido.fatorCondicaoPagamento, RoundingMode.HALF_UP);

			if (pedido.valorSemEncargos.doubleValue() <= 0) {
				pedido.valorSemEncargos = new BigDecimal("0.01");
			}
			BigDecimal valorSemEncargosImposto = (pedido.valorTotalIpi.divide(pedido.fatorCondicaoPagamento, RoundingMode.HALF_EVEN)).add((pedido.valorTotalStb.divide(pedido.fatorCondicaoPagamento, RoundingMode.HALF_EVEN)));
			pedido.valorLiquidoImp = pedido.valorLiquidoMercadoria.multiply(new BigDecimal(pedido.quantidadeMercadoria));
			pedido.valorNotaFiscalSemEncargos = ((pedido.valorSemEncargos.multiply(new BigDecimal(pedido.quantidadeMercadoria))).add(valorSemEncargosImposto));
			listaPedido.add(pedido);
		}
		return criaMapaDetalhes(listaPedido);
	}

	private static List<Pedido> criaMapaDetalhes(List<Pedido> listaPedido) {
		List<Integer> filialFat = new ArrayList<Integer>();
		Map<Integer, BigDecimal> mapaLiqImp = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> mapaValorNF = new HashMap<Integer, BigDecimal>();
		Map<Integer, Integer> mapaNF = new HashMap<Integer, Integer>();

		for (Pedido p : listaPedido) {
			BigDecimal valorLiqImp = mapaLiqImp.get(p.filialFaturamento);
			BigDecimal valorNF = mapaValorNF.get(p.filialFaturamento);

			if (!filialFat.contains(p.filialFaturamento)) {
				filialFat.add(p.filialFaturamento);
			}
			if (mapaLiqImp.containsKey(p.filialFaturamento)) {
				valorLiqImp = valorLiqImp.add(p.valorLiquidoMercadoria).add(p.valorTotalIpi).add(p.valorTotalStb);
				mapaLiqImp.put(p.filialFaturamento, valorLiqImp);
			} else {
				mapaLiqImp.put(p.filialFaturamento, (p.valorLiquidoMercadoria).add(p.valorTotalIpi).add(p.valorTotalStb));
			}

			if (mapaValorNF.containsKey(p.filialFaturamento)) {
				valorNF = valorNF.add(p.valorNotaFiscalSemEncargos);
				mapaValorNF.put(p.filialFaturamento, valorNF);
			} else {
				mapaValorNF.put(p.filialFaturamento, p.valorNotaFiscalSemEncargos);
			}
			if (!mapaNF.containsKey(p.filialFaturamento)) {
				mapaNF.put(p.filialFaturamento, p.filialFaturamento);
			}
		}
		List<Pedido> lista = new ArrayList<Pedido>();
		Pedido p;

		for (Integer fat : filialFat) {
			p = new Pedido();
			p.filialFaturamento = fat;
			p.valorLiquidoImp = mapaLiqImp.get(fat);
			p.valorNotaFiscalSemEncargos = mapaValorNF.get(fat);
			lista.add(p);
		}
		return lista;
	}

	/**
	 * Método responsável por calcular os valores da nota fiscal.
	 * 
	 * @param pedido
	 *            objeto do tipo <code></code> referente aos dados do pedido
	 * @param quantidadeMercadoria
	 *            quantidade de uma determinada mercadoria
	 * @return objeto do tipo <code></code> referente aos dados do pedido
	 *         atualizados com o cáculo de valores da nota fiscal
	 */
	public static List<Pedido> calculaValoresNF(Integer codigoFilial) {
		List<Pedido> listaValores = new ArrayList<Pedido>();
		List<Pedido> lista = PedidoDAO.carregaDetalhesNotaFiscal(codigoFilial);

		for (Pedido pedido : lista) {
			pedido.valorSemEncargos = pedido.valorLiquidoMercadoria.divide(pedido.fatorCondicaoPagamento, RoundingMode.HALF_UP);

			if (pedido.valorSemEncargos.doubleValue() <= 0.00) {
				pedido.valorSemEncargos = new BigDecimal("0.01");
			}

			pedido.valorNotaFiscal = pedido.valorSemEncargos.multiply(new BigDecimal(pedido.quantidadeMercadoria));
			pedido.valorEncargosVendor = pedido.valorLiquidoMercadoria.multiply(new BigDecimal(pedido.quantidadeMercadoria));
			listaValores.add(calculaValoresTotalPagar(pedido));
		}

		List<Integer> listaNF = new ArrayList<Integer>();

		Map<Integer, BigDecimal> mapaValorNota = new HashMap<Integer, BigDecimal>();
		Map<Integer, BigDecimal> mapaEncargos = new HashMap<Integer, BigDecimal>();
		Map<Integer, Integer> mapaCondPagamento = new HashMap<Integer, Integer>();
		Map<Integer, Integer> mapaNumParcelas = new HashMap<Integer, Integer>();
		Map<Integer, BigDecimal> mapaValorParcelas = new HashMap<Integer, BigDecimal>();
		Map<Integer, Integer> mapaEntradas = new HashMap<Integer, Integer>();
		Map<Integer, Integer> mapaPeriodo = new HashMap<Integer, Integer>();

		for (Pedido p : listaValores) {
			BigDecimal valorNota = mapaValorNota.get(p.numNotaFiscal);
			BigDecimal valorEncargos = mapaEncargos.get(p.numNotaFiscal);

			if (!listaNF.contains(p.numNotaFiscal)) {
				listaNF.add(p.numNotaFiscal);
			}

			if (mapaValorNota.containsKey(p.numNotaFiscal)) {
				valorNota = valorNota.add(p.valorNotaFiscal);
				mapaValorNota.put(p.numNotaFiscal, valorNota);
			} else {
				mapaValorNota.put(p.numNotaFiscal, p.valorNotaFiscal);
			}
			if (mapaEncargos.containsKey(p.numNotaFiscal)) {
				valorEncargos = valorEncargos.add(p.valorEncargosVendor);
				mapaEncargos.put(p.numNotaFiscal, valorEncargos);
			} else {
				mapaEncargos.put(p.numNotaFiscal, p.valorEncargosVendor);
			}

			if (!mapaCondPagamento.containsKey(p.numNotaFiscal)) {
				mapaCondPagamento.put(p.numNotaFiscal, p.condicaoPagamento);
			}
			if (!mapaNumParcelas.containsKey(p.numNotaFiscal)) {
				mapaNumParcelas.put(p.numNotaFiscal, p.numParcelas);
			}
			if (!mapaValorParcelas.containsKey(p.numNotaFiscal)) {
				mapaValorParcelas.put(p.numNotaFiscal, p.totalParcelas);
			}
			if (!mapaEntradas.containsKey(p.numNotaFiscal)) {
				mapaEntradas.put(p.numNotaFiscal, p.qtdDiasPrazo);
			}
			if (!mapaPeriodo.containsKey(p.numNotaFiscal)) {
				mapaPeriodo.put(p.numNotaFiscal, p.periodo);
			}
		}

		List<Pedido> valores = new ArrayList<Pedido>();
		Pedido pedido;

		for (Integer i : listaNF) {
			pedido = new Pedido();
			pedido.numNotaFiscal = i;
			pedido.valorEncargosVendor = mapaEncargos.get(i);
			pedido.valorNotaFiscal = mapaValorNota.get(i);
			pedido.condicaoPagamento = mapaCondPagamento.get(i);
			pedido.numParcelas = mapaNumParcelas.get(i);
			pedido.totalParcelas = mapaValorParcelas.get(i);
			pedido.qtdDiasPrazo = mapaEntradas.get(i);
			pedido.periodo = mapaPeriodo.get(i);
			valores.add(pedido);
		}

		return valores;
	}

	/**
	 * Método responsável por calcular todos os valores para encontrar o valor
	 * total a ser pago.
	 * 
	 * @param pedido
	 *            objeto do tipo <code></code> referente aos dados do pedido
	 * @param quantidade
	 *            quantidade de itens do pedido
	 * @return
	 */
	private static Pedido calculaValoresTotalPagar(Pedido pedido) {
		BigDecimal totalParcelas = new BigDecimal(0.);
		BigDecimal totalImposto = new BigDecimal(0.);

		BigDecimal numParc = new BigDecimal(pedido.numParcelas);
		totalImposto = pedido.valorTotalIpi.add(pedido.valorTotalStb);
		totalParcelas = pedido.valorLiquidoMercadoria.multiply(new BigDecimal(pedido.quantidadeMercadoria)).add(totalImposto);

		pedido.totalParcelas = totalParcelas.divide(numParc, RoundingMode.HALF_EVEN);
		pedido.qtdDiasPrazo += pedido.diasPrazoBeneficiario;

		if (pedido.numParcelas.compareTo(0) == 0) {
			pedido.periodo = pedido.periodoParcelas + pedido.diasPrazoBeneficiario;
		} else {
			pedido.periodo = pedido.periodoParcelas;
		}
		return pedido;
	}

	/**
	 * Método responsável por definir os detalhes dos pedidos.
	 * 
	 * @param pedidos
	 *            objeto do tipo
	 *            <code>java.util.List<java.util.Map<java.lang.String, java.lang.String>></code>
	 *            referente a lista de pedidos
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.vo.Pedido></code>
	 *         referente a lista de pedidos que obtiveram seus detalhes
	 *         definidos
	 */
	private static List<Pedido> defineDetalhesPedidos(List<Map<String, String>> pedidos) {
		List<Pedido> lista = new ArrayList<Pedido>();

		for (Map<String, String> p : pedidos) {
			Pedido pedido = new Pedido();
			pedido.filialExpedicao = Integer.valueOf(p.get("Cad"));
			pedido.filialFaturamento = Integer.valueOf(p.get("Fat"));
			pedido.percAcaoTatica = Util.getBigDecimal(p.get("percAcaoTatica"));
			pedido.quantidadeMercadoria = Integer.valueOf(p.get("QuantidadeMercadoria"));
			pedido.valorLiquidoMercadoria = Util.getBigDecimal(p.get("valorLiquido"));
			pedido.valorTotalStb = Util.getBigDecimal(p.get("STBTotal"));
			pedido.valorTotalIpi = Util.getBigDecimal(p.get("IPITotal"));
			pedido.valorFrete = Util.getBigDecimal(p.get("valorFrete"));
			pedido.perMaxFlex = Util.getBigDecimal(p.get("percMaximoFlex"));
			pedido.perDescontoFlex = Util.getBigDecimal(p.get("porcDSC"));
			pedido.perMaxSimples = Util.getBigDecimal(p.get("porcMaximaSmp"));
			pedido.valorBeneficioMercadoria = Util.getBigDecimal(p.get("valorBenMer"));
			pedido.valorMinimo = Util.getBigDecimal(p.get("valorMinimoEPD"));
			pedido.fatorCondicaoPagamento = Util.getBigDecimal(p.get("FatorCndPagamento"));
			lista.add(pedido);
		}
		return calculaValoresCAD(lista);
	}

	/**
	 * Método responsável por criar uma lista de pedidos.
	 * 
	 * @param pedidos
	 *            objeto do tipo
	 *            <code>java.util.List<java.util.Map<java.lang.String, java.lang.String>></code>
	 *            que corresponde a um mapa de dados de um ou mais pedidos.
	 * @return objeto do tipo
	 *         <code>java.util.List<br.com.martins.vendas.vo.Pedido></code> que
	 *         corresponde a lista gerada de um ou mais pedidos.
	 */
	private static List<Pedido> obtemListaPedidos(List<Map<String, String>> pedidos, boolean comItens) {
		List<Pedido> listaPedidos = new ArrayList<Pedido>();

		try {
			for (Map<String, String> pedidoConsulta : pedidos) {
				Pedido pedido = new Pedido();
				pedido.numeroPedido = pedidoConsulta.get("numPedido");
				pedido.codigoCliente = pedidoConsulta.get("codigoCliente");
				pedido.nomeCliente = pedidoConsulta.get("nomeCliente");
				pedido.preparado = "*".equals(pedidoConsulta.get("flagPreparado")) ? "Sim" : "Não";
				pedido.horaPedido = pedidoConsulta.get("horaPedido");
				pedido.valorTotalPedido = Util.getBigDecimal(pedidoConsulta.get("valorTotalPedido"));
				pedido.quantidadeTotalItens = Util.getInteger(pedidoConsulta.get("quantidadeItem"));
				pedido.qtdMercadoriaNegociada = Util.getInteger(pedidoConsulta.get("qdtMercadoriaNegociado"));
				// pedido.qtdMercadoriaPedida =
				// Util.getInteger(pedidoConsulta.get("qdtMercadoriaPedida"));
				// pedido.valorLiquidoMercadoria =
				// Util.getBigDecimal(pedidoConsulta.get("valorLiqMercadoria"));
				pedido.dataPedido = DateUtil.formataData(pedidoConsulta.get("dataPedido"), "yyyyMMdd");
				pedido.dataResultado = DateUtil.formataData(pedidoConsulta.get("dataResultado"), "yyyyMMdd");
				pedido.mercadoriaCortada = pedidoConsulta.get("cortes");
				pedido.codigoSegmento = pedidoConsulta.get("codigoSegmento");
				pedido.codigoTerritorio = Util.getInteger(pedidoConsulta.get("codigoTerritorio"));

				if (comItens) {
					pedido.itensPedido = ItemPedidoService.obtemItensPedido(pedido.numeroPedido);
				}

				listaPedidos.add(pedido);
			}
		} catch (ParseException e) {
			Log.e(TAG, "criaListaPedidos() - Erro inesperado " +  e.getMessage());
		} catch (Exception e) {
			Log.e(TAG, "criaListaPedidos() - Erro inesperado - " + e.getMessage());
		}

		return listaPedidos;
	}

	/**
	 * Método responsável por limpar as tabelas temporárias.
	 * 
	 * @return <code>true</code> se os dados da tabela foram removidos com
	 *         sucesso, e <code>false</code> caso tenha ocorrido algum problema
	 *         que tenha impedido a remoção dos dados da tabelas temporárias.
	 */
	public static boolean limpaTabelasTemporarias() {
		boolean resultadoTabelaTmpGrpDe = TabelaTmpGrpDeDAO.limpaTabela();
		boolean resultadoTmpImpPed = TabelaTmpImpPedDAO.limpaTabela();
		boolean resultadoTmpItePe = TabelaTmpItePeDAO.limpaTabela();
		boolean resultadoTmpMeGo = TabelaTmpMeGoDAO.limpaTabela();
		boolean resuladoTmpMemCo = TabelaTmpMemCoDAO.limpaTabela("TMPMEMCO");
		boolean resultadoTmpMePmc = TabelaTmpMePmcDAO.limpaTabela("TMPMEPMC");
		boolean resultadoTmpMePrm = TabelaTmpMePrmDAO.limpaTabela();
		boolean resultadoTmpMerRec = TabelaTmpMerRecDAO.limpaTabela();
		boolean resultadoTmpMesMr = TabelaTmpMeSmrDAO.limpaTabela();
		boolean resultadoTmpPcaBcd = TabelaTmpPcaBcdDAO.limpaTabela();
		return resultadoTabelaTmpGrpDe && resultadoTmpImpPed && resultadoTmpItePe && resultadoTmpMeGo && resuladoTmpMemCo && resultadoTmpMePmc && resultadoTmpMePrm && resultadoTmpMerRec && resultadoTmpMesMr && resultadoTmpPcaBcd;
	}

	public static boolean atualizaStatusPcasos(PedidoStatus status) {
		return PedidoDAO.atualizaStatusPcasos(status);
	}

	public static boolean atualizaStatusPedidoPcasos(Pedido pedido, PedidoStatus status) {
		return PedidoDAO.atualizaStatusPcasos(pedido, status);
	}
	
	public static PedidoStatus obtemStatusPcasos() {
		return PedidoDAO.obtemStatusPcasos();
	}
	
	public static Integer obtemNumeroUltimoPedidoPcapar() {
		return PedidoDAO.obtemNumeroUltimoPedidoPcapar();
	}

	public static boolean verificaTabelasStatusPedido() {
		boolean resultado = false;
		
		// Verifica se ha uma linha na tabela PCASOS e se nao houver a cria
		if (!PedidoDAO.verificaTabelaPcaSos()) {
			resultado = PedidoDAO.criaLinhaTabelaPcaSos();
		}
		
		// Verifica se ha uma linha na tabela PCAPAR e se nao houver a cria
		if (!PedidoDAO.verificaTabelaPcaPar()) {
			resultado = PedidoDAO.criaLinhaTabelaPcaPar();
		}
		
		return resultado;
	}

	public static BigDecimal obtemPontuacaoMinimaPedido(Representante representante, Integer codigoFilialExpedicao, String estadoUF, Integer canal) {
		BigDecimal pontuacaoMinima = BigDecimal.valueOf(PedidoDAO.obtemPontuacaoMinimaPedido(codigoFilialExpedicao, estadoUF, canal));
		return CalculoUtil.roundUP(representante.fatorKMargem.multiply(pontuacaoMinima), 1).multiply(BigDecimal.TEN);
	}

	public static Integer criaNovoPedido() {
		// Gera um novo numero de pedido
		Integer novoNumero = PedidoDAO.geraNumeroPedido();

		Pedido pedido = new Pedido();
		pedido.numeroPedido = novoNumero.toString();
		
		// Altera o status do novo pedido na tabela PCASOS
		PedidoService.atualizaStatusPcasos(PedidoStatus.EM_CRIACAO);

		// Limpa as tabelas temporarias
		PedidoService.limpaTabelasTemporarias();

		return novoNumero;
	}

	public static boolean isPedidoExiste(Integer numeroPedido) {
		return PedidoDAO.isPedidoExiste(numeroPedido);
	}

	public static boolean isPedidoPreparadoReprovado(Integer numeroPedido) {
		return PedidoDAO.isPedidoPreparadoReprovado(numeroPedido);
	}
	public static boolean verificaMotivoCortes(Integer numeroPedido) {
		return PedidoDAO.verificaMotivoCortes(numeroPedido);
	}
}
