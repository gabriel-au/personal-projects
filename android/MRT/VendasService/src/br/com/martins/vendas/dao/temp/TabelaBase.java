package br.com.martins.vendas.dao.temp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.MercadoriaSimilarDAO;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Mercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class TabelaBase {
	
	private static Database db;

	public static boolean limpaTabela(String nomeTabela) {
		boolean resultado = false;
		db = DatabaseFactory.getInstance();

		try {
			db.eraseTable(nomeTabela);
			resultado = true;
		} catch (SQLException e) {
			Log.e(nomeTabela + " - limpaTabela - ", e.getMessage());
		}

		return resultado;
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public static boolean insereItem(String nomeTabela, Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO ").append(nomeTabela).append(" ( ");
		sql.append(" CMSCNSMER, "); // Comissao Concessao Mercadoria
		sql.append(" CMSMAXMER, "); // Maximo Comissao Mercadoria
		sql.append(" CODCNDPGT, "); // Codigo Condição de Pagamento
		sql.append(" CODCORMRG, "); // Codigo Cor de Margem
		sql.append(" CODCORMRGB, "); // Codigo Cor de Margem B
		sql.append(" CODFILEPD, "); // Codigo Filial
		sql.append(" CODFILFAT, "); // Codigo de Filial de Faturamento
		sql.append(" CODFLXPCO, "); // Codigo Preco Flexivel
		sql.append(" CODMER, "); // Codigo da Mercadoria
		sql.append(" CODMERPCP, "); // Codigo Mercadoria Principal
		sql.append(" CODPMC, "); // Codigo Promocao
		sql.append(" CODRGRDTB, "); // Codigo Regra Distribuicao
		sql.append(" CODSMBSIT, "); // Codigo Simbolo Situacao
		sql.append(" DESMER, "); // Descricao da Mercadoria
		sql.append(" DESNGCMER, "); // Descricao Negociacao Mercadoria
		sql.append(" FLGPEE, "); // Flag PEE
		sql.append(" FLGSTSITE, "); // Flag Simbolo Situacao
		sql.append(" INDITEIMU, "); // Indica Item Imune
		sql.append(" INDITEVND, "); // Indica Item de Venda
		sql.append(" INDMERKIT, "); // Indica Mercadoria Kit
		sql.append(" INDNVO, "); // Idicador de item novo

		// Indica Restricao Beneficio Customizado no Brinde
		sql.append(" INDRTCBFB, ");

		sql.append(" INDRTCBFV, "); // Indica Restricao Beneficio Customizado
		sql.append(" INDRTCBNF, "); // Indica Restricao de Item como Brinde
		sql.append(" INDSEMMRGL, "); // Indica Sem Margem de Lucro
		sql.append(" IPITOTBNF, "); // Valor IPI Total Bonificacao
		sql.append(" LIQCXAIMP, "); // Valor Liquido Caixa com Imposto
		sql.append(" NUMNOTFSC, "); // Numero da Nota Fiscal
		sql.append(" PERACOCLI, "); // Percentual de Acao do Cliente
		sql.append(" PERACOTTC, "); // Percentual de Acao Tatica
		sql.append(" PERACRCNS, "); // Percentual Acrescimo Concedido
		sql.append(" PERACRVTL, "); // Percentual de Acrescimo Virtual
		sql.append(" PERDSCFLX, "); // Percentual Desconto Flexivel
		sql.append(" PERDSCMNM, "); // Percentual Desconto Minimo
		sql.append(" PERICMMER, "); // Percentual de ICM da Mercadoria
		sql.append(" PERMAXFLX, "); // Percentual Maximo Flexivel
		sql.append(" PERMAXSMP, "); // Percentual Maximo Simplificado
		sql.append(" PERVLRMNM, "); // Expedicao Percentual Valor Minimo
		sql.append(" QDECXAFRN, "); // Quantidade Caixa Fornecedor
		sql.append(" QDEMERPED, "); // Quantidade da Mercadoria no Pedido
		sql.append(" RTCBFVMER, "); // Restricao de Beneficio de Mercadoria
		sql.append(" STBTOTBNF, "); // STB Total Bonificacao
		sql.append(" TIPICT, "); // Tipo de Incentivo

		// Tipo Marcação Representante (Listas Customizadas)
		sql.append(" TIPMCOREP, ");

		sql.append(" TIPMERDSC, "); // Tipo Desconto Mercadoria
		sql.append(" TIPMERESG, "); // Tipo de Esgotamento da Mercadoria
		sql.append(" TIPNGCMER, "); // Mercadoria Tipo Negociacao Mercadoria
		sql.append(" TIPPMC, "); // Tipo Promocao
		sql.append(" UNTCXAIMP, "); // Valor Unitario da Caixa com Imposto
		sql.append(" VLRBNFMER, "); // Valor Bonificacao
		sql.append(" VLRBRTCXA, "); // Valor Bruto Caixa
		sql.append(" VLRBRTFRC, "); // Caixa Valor Bruto Fracionado
		sql.append(" VLRBRTMER, "); // Valor Bruto Mercadoria
		sql.append(" VLRBRTTMP, "); // Valor Bruto Temporario
		sql.append(" VLRDSCESP, "); // Valor Desconto Especial
		sql.append(" VLRFRTMER, "); // Valor do Frete da Mercadoria
		sql.append(" VLRIPIMER, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT, "); // Valor IPI Total Mercadoria
		sql.append(" VLRLIQCXA, "); // Valor Liquido
		sql.append(" VLRLIQIMP, "); // Valor Liquido da Mercadoria com Imposto
		sql.append(" VLRLIQMER, "); // Valor Liquido Mercadoria
		sql.append(" VLRMAXISN, "); // Valor Maximo Isencao
		sql.append(" VLRMNMMER, "); // Valor Minimo Mercadoria
		sql.append(" VLRPTOBFC, "); // Valor Pontos de Beneficio
		sql.append(" VLRPTOMER, "); // Valor Pontos
		sql.append(" VLRRDCITE, "); // Valor Reducao Item

		// Valor STB (Substituicao Tributaria) Mercadoria
		sql.append(" VLRSTBMER, ");

		sql.append(" VLRSTBTOT, "); // Valor STB Total
		sql.append(" VLRUNTIMP ");  // Valor Unitario com Imposto
		sql.append(" ) VALUES ( ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ? ");
		sql.append(" ); ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		try {
			db.executeUpdate(sql.toString(), getParametros(item));
			resultado = true;
		} catch (SQLException e) {
			Log.e(nomeTabela + " - insereItem - ", e.getMessage());
		}

		return resultado;
	}

	/**
	 * 
	 * @param item
	 * @param transactionManager
	 * @return
	 */
	public static boolean insereItem(String nomeTabela, Item item, TransactionManager transactionManager) {
		Map<String, String> values = new HashMap<String, String>();

		// Comissao Concessao Mercadoria
		values.put("CMSCNSMER", Util.getStringValue(item.comissao.comissaoMercadoria));

		// Maximo Comissao Mercadoria
		values.put("CMSMAXMER", Util.getStringValue(item.comissao.comissaoMaximaMercadoria));

		// Codigo Condição de Pagamento
		values.put("CODCNDPGT", Util.getStringValue(item.condicaoPagamento.codigoCondicaoPagamento));

		// Codigo Cor de Margem
		values.put("CODCORMRG", Util.getStringValue(item.codigoCorMargem));

		// Codigo Cor de Margem B
		values.put("CODCORMRGB", Util.getStringValue(item.codigoCorMargemB));

		// Codigo Filial
		values.put("CODFILEPD", Util.getStringValue(item.codigoFilialExpedicao));

		// Codigo de Filial de Faturamento
		values.put("CODFILFAT", Util.getStringValue(item.codigoFilialFaturamento));

		// Codigo Preco Flexivel
		values.put("CODFLXPCO", item.preco.codigoFlexivelPreco);

		// Codigo da Mercadoria
		values.put("CODMER", Util.getStringValue(item.mercadoria.codigo));

		// Codigo Mercadoria Principal
		values.put("CODMERPCP", Util.getStringValue(item.mercadoria.codigoMercadoriaPrincipal));

		// Codigo Promocao
		values.put("CODPMC", Util.getStringValue(item.promocao.codigo));

		// Codigo Regra Distribuicao
		values.put("CODRGRDTB", Util.getStringValue(item.regraDistribuicao.codigoRegra));

		// Codigo Simbolo Situacao
		values.put("CODSMBSIT", item.preco.codigoSimboloSituacao);

		// Descricao da Mercadoria
		values.put("DESMER", item.mercadoria.descricao);

		// Descricao Negociacao Mercadoria
		values.put("DESNGCMER", item.mercadoria.descricaoNegocioMercadoria);

		// Flag PEE
		values.put("FLGPEE", Util.getStringValue(item.mercadoria.flagPee));

		// Flag Simbolo Situacao
		values.put("FLGSTSITE", Util.getStringValue(item.preco.flagSimboloSituacao));

		// Indica Item Imune
		values.put("INDITEIMU", Util.getStringValue(item.indicaItemImune));

		// Indica Item de Venda
		values.put("INDITEVND", Util.getStringValue(item.indicaItemVenda));

		// Indica Mercadoria Kit
		values.put("INDMERKIT", Util.getStringValue(item.mercadoria.indicaMercadoriaKit));

		// Idicador de item novo
		values.put("INDNVO", item.indicaItemNovo);

		// Indica Restricao Beneficio Customizado no Brinde
		values.put("INDRTCBFB", Util.getStringValue(item.indicaRestricaoBeneficioCustomizadoBrinde));

		// Indica Restricao Beneficio Customizado
		values.put("INDRTCBFV", Util.getStringValue(item.indicaRestricaoBeneficioCustomizado));

		// Indica Restricao de Item como Brinde
		values.put("INDRTCBNF", Util.getStringValue(item.indicaRestricaoItemBrinde));

		// Indica Sem Margem de Lucro
		values.put("INDSEMMRGL", Util.getStringValue((item.stb.temMargemDeLucro ? '1' : '0')));

		// Valor IPI Total Bonificacao
		values.put("IPITOTBNF", Util.getStringValue(item.valorIPITotalBonificacao));

		// Valor Liquido Caixa com Imposto
		values.put("LIQCXAIMP", Util.getStringValue(item.valorCaixaComImposto));

		// Numero da Nota Fiscal
		values.put("NUMNOTFSC", Util.getStringValue(item.notaFiscal));

		// Percentual de Acao do Cliente
		values.put("PERACOCLI", Util.getStringValue(item.desconto.percentualDescontoAcao));

		// Percentual de Acao Tatica
		values.put("PERACOTTC", Util.getStringValue(item.percentualDescontoAcaoTatica));

		// Percentual Acrescimo Concedido
		values.put("PERACRCNS", Util.getStringValue(item.percentualAcrescimoConcedido));

		// Percentual de Acrescimo Virtual
		values.put("PERACRVTL", Util.getStringValue(item.percentualAcrescimoVirtual));

		// Percentual Desconto Flexivel
		values.put("PERDSCFLX", Util.getStringValue(item.percentualDescontoConcedido));

		// Percentual Desconto Minimo
		values.put("PERDSCMNM", Util.getStringValue(item.desconto.percentualMinimoDesconto));

		// Percentual de ICM da Mercadoria
		values.put("PERICMMER", Util.getStringValue(item.percentualICM));

		// Percentual Maximo Flexivel
		values.put("PERMAXFLX", Util.getStringValue(item.desconto.percentualDescontoFlexivel));

		// Percentual Maximo Simplificado
		values.put("PERMAXSMP", Util.getStringValue(item.percentualDescontoSimplificado));

		// Expedicao Percentual Valor Minimo
		values.put("PERVLRMNM", Util.getStringValue(item.stb.percentualValorMinimo));

		// Quantidade Caixa Fornecedor
		values.put("QDECXAFRN", Util.getStringValue(item.mercadoria.quantidadeCaixaFornecedor));

		// Quantidade da Mercadoria no Pedido
		values.put("QDEMERPED", Util.getStringValue(item.quantidadeMercadoria));

		// Restricao de Beneficio de Mercadoria
		values.put("RTCBFVMER", Util.getStringValue(item.indicaRestricaoBeneficioCustomizadoMercadoria));

		// STB Total Bonificacao
		values.put("STBTOTBNF", Util.getStringValue(item.stb.valorSTBTotalBonificacao));

		// Tipo de Incentivo
		values.put("TIPICT", item.preco.tipoIncentivoMercadoria);

		// Tipo Marcação Representante (Listas Customizadas)
		values.put("TIPMCOREP", item.preco.tipoMarcacaoRepresentante);

		// Tipo Desconto Mercadoria
		values.put("TIPMERDSC", item.desconto.tipo);

		// Tipo de Esgotamento da Mercadoria
		values.put("TIPMERESG", item.mercadoria.tipoEsgotamento);

		// Mercadoria Tipo Negociacao Mercadoria
		values.put("TIPNGCMER", Util.getStringValue(item.tipoNegociacaoMercadoria));

		// Tipo Promocao
		values.put("TIPPMC", Util.getStringValue(item.promocao.tipo));

		// Valor Unitario da Caixa com Imposto
		values.put("UNTCXAIMP", Util.getStringValue(item.valorUnitarioCaixaComImposto));

		// Valor Bonificacao
		values.put("VLRBNFMER", Util.getStringValue(item.valorBonificacao));

		// Valor Bruto Caixa
		values.put("VLRBRTCXA", Util.getStringValue(item.preco.valorBrutoCaixa));

		// Caixa Valor Bruto Fracionado
		values.put("VLRBRTFRC", Util.getStringValue(item.preco.valorBrutoFracionado));

		// Valor Bruto Mercadoria
		values.put("VLRBRTMER", Util.getStringValue(item.preco.valorBrutoMercadoria));

		// Valor Bruto Temporario
		values.put("VLRBRTTMP", Util.getStringValue(item.preco.valorBrutoTMP));

		// Valor Desconto Especial
		values.put("VLRDSCESP", Util.getStringValue(item.desconto.valorDescontoEspecial));

		// Valor do Frete da Mercadoria
		values.put("VLRFRTMER", Util.getStringValue(item.frete));

		// Valor IPI Mercadoria
		values.put("VLRIPIMER", Util.getStringValue(item.mercadoria.percentualIPI.percentualIPI));

		// Valor IPI Total Mercadoria
		values.put("VLRIPITOT", Util.getStringValue(item.valorIPITotal));

		// Valor Liquido
		values.put("VLRLIQCXA", Util.getStringValue(item.valorLiquidoCaixa));

		// Valor Liquido da Mercadoria com Imposto
		values.put("VLRLIQIMP", Util.getStringValue(item.valorLiquidoComImposto));

		// Valor Liquido Mercadoria
		values.put("VLRLIQMER", Util.getStringValue(item.valorLiquidoMercadoria));

		// Valor Maximo Isencao
		values.put("VLRMAXISN", Util.getStringValue(item.desconto.valorDescontoIsencao));

		// Valor Minimo Mercadoria
		values.put("VLRMNMMER", Util.getStringValue(item.mercadoria.valorMinimoMercadoria));

		// Valor Pontos de Beneficio
		values.put("VLRPTOBFC", Util.getStringValue(item.valorPontoBeneficioMercadoria));

		// Valor Pontos
		values.put("VLRPTOMER", Util.getStringValue(item.valorPontuacaoMercadoria));

		// Valor Reducao Item
		values.put("VLRRDCITE", Util.getStringValue(item.valorReducao));

		// Valor STB (Substituicao Tributaria) Mercadoria
		values.put("VLRSTBMER", Util.getStringValue(item.stb.valorSTBUnitario));

		// Valor STB Total
		values.put("VLRSTBTOT", Util.getStringValue(item.stb.valorSTBTotal));

		// Valor Unitario com Imposto
		values.put("VLRUNTIMP", Util.getStringValue(item.valorUnitarioComImposto));

		boolean resultado = false;
		try {
			transactionManager.insert(nomeTabela, values);
			resultado = true;
		} catch (SQLException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return resultado;
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	public static boolean alteraItem(String nomeTabela, Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE ").append(nomeTabela).append(" SET ");
		sql.append(" CMSCNSMER  = ?, "); // Comissao Concessao Mercadoria
		sql.append(" CMSMAXMER  = ?, "); // Maximo Comissao Mercadoria
		sql.append(" CODCNDPGT  = ?, "); // Codigo Condicao de Pagamento
		sql.append(" CODCORMRG  = ?, "); // Codigo Cor de Margem
		sql.append(" CODCORMRGB = ?, "); // Codigo Cor de Margem B
		sql.append(" CODFILEPD  = ?, "); // Codigo Filial Expedicao
		sql.append(" CODFILFAT  = ?, "); // Codigo de Filial de Faturamento
		sql.append(" CODFLXPCO  = ?, "); // Codigo Preco Flexivel
		sql.append(" CODMER     = ?, "); // Código da Mercadoria
		sql.append(" CODMERPCP  = ?, "); // Codigo Mercadoria Principal
		sql.append(" CODPMC     = ?, "); // Codigo Promocao
		sql.append(" CODRGRDTB  = ?, "); // Codigo Regra Distribuicao
		sql.append(" CODSMBSIT  = ?, "); // Codigo Simbolo Situacao
		sql.append(" DESMER     = ?, "); // Descricao da Mercadoria
		sql.append(" DESNGCMER  = ?, "); // Descricao Negociacao Mercadoria
		sql.append(" FLGPEE     = ?, "); // Flag PEE
		sql.append(" FLGSTSITE  = ?, "); // Flag Simbolo Situacao
		sql.append(" INDITEIMU  = ?,  "); // Indica Item Imune
		sql.append(" INDITEVND  = ?, "); // Indica Item de Venda
		sql.append(" INDMERKIT  = ?, "); // Indica Mercadoria Kit
		sql.append(" INDNVO     = ?, "); // Indicador item novo

		// Indica Restricao Beneficio Customizado no Brinde
		sql.append(" INDRTCBFB  = ?, ");

		// Indica Restricao Beneficio Customizado
		sql.append(" INDRTCBFV  = ?, ");

		// Indica Restricao de Item como Brinde
		sql.append(" INDRTCBNF  = ?, ");

		sql.append(" INDSEMMRGL = ?, "); // Indica Sem Margem de Lucro
		sql.append(" IPITOTBNF  = ?, "); // Valor IPI Total Bonificacao
		sql.append(" LIQCXAIMP  = ?, "); // Valor Liquido Caixa com Imposto
		sql.append(" NUMNOTFSC  = ?, "); // Numero da Nota Fiscal
		sql.append(" PERACOCLI  = ?, "); // Percentual de Acao do Cliente
		sql.append(" PERACOTTC  = ?, "); // Percentual de Acao Tatica
		sql.append(" PERACRCNS  = ?, "); // Percentual Acrescimo Concedido
		sql.append(" PERACRVTL  = ?, "); // Percentual de Acrescimo Virtual
		sql.append(" PERDSCFLX  = ?, "); // Percentual Desconto Flexivel
		sql.append(" PERDSCMNM  = ?, "); // Percentual Desconto Minimo
		sql.append(" PERICMMER  = ?, "); // Percentual de ICM da Mercadoria
		sql.append(" PERMAXFLX  = ?, "); // Percentual Maximo Flexivel
		sql.append(" PERMAXSMP  = ?, "); // Percentual Maximo Simplificado
		sql.append(" PERVLRMNM  = ?, "); // Percentual Valor Minimo
		sql.append(" QDECXAFRN  = ?, "); // Quantidade Caixa Fornecedor
		sql.append(" QDEMERPED  = ?, "); // Quantidade da Mercadoria no Pedido

		// Restricao de Beneficio de Mercadoria
		sql.append(" RTCBFVMER  = ?, ");

		sql.append(" STBTOTBNF  = ?, "); // STB Total Bonificacao
		sql.append(" TIPICT     = ?, "); // Tipo de Incentivo

		// Tipo Marcacao Representante (Listas Customizadas)
		sql.append(" TIPMCOREP  = ?, ");

		sql.append(" TIPMERDSC  = ?, "); // Tipo Desconto Mercadoria
		sql.append(" TIPMERESG  = ?, "); // Tipo de Esgotamento da Mercadoria
		sql.append(" TIPNGCMER  = ?, "); // Tipo Negociacao Mercadoria
		sql.append(" TIPPMC     = ?, "); // Tipo Promocao
		sql.append(" UNTCXAIMP  = ?, "); // Valor Unitario da Caixa com Imposto
		sql.append(" VLRBNFMER  = ?, "); // Valor Bonificacao Mercadoria
		sql.append(" VLRBRTCXA  = ?, "); // Valor Bruto Caixa
		sql.append(" VLRBRTFRC  = ?, "); // Valor Bruto Fracionado
		sql.append(" VLRBRTMER  = ?, "); // Valor Bruto Mercadoria
		sql.append(" VLRBRTTMP  = ?, "); // Valor Bruto Temporario
		sql.append(" VLRDSCESP  = ?, "); // Valor Desconto Especial
		sql.append(" VLRFRTMER  = ?, "); // Valor do Frete da Mercadoria
		sql.append(" VLRIPIMER  = ?, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT  = ?, "); // Valor IPI Total Mercadoria
		sql.append(" VLRLIQCXA  = ?, "); // Valor Liquido Caixa
		sql.append(" VLRLIQIMP  = ?, "); // Valor Liquido da Mercadoria com Imposto
		sql.append(" VLRLIQMER  = ?, "); // Valor Liquido Mercadoria
		sql.append(" VLRMAXISN  = ?, "); // Valor Maximo Isencao
		sql.append(" VLRMNMMER  = ?, "); // Valor Minimo Mercadoria
		sql.append(" VLRPTOBFC  = ?, "); // Valor Pontos de Beneficio
		sql.append(" VLRPTOMER  = ?, "); // Valor Pontos
		sql.append(" VLRRDCITE  = ?, "); // Valor Reducao Item
		sql.append(" VLRSTBMER  = ?, "); // Valor STB (Substituicao Tributaria) Mercadoria
		sql.append(" VLRSTBTOT  = ?, "); // Valor STB Total
		sql.append(" VLRUNTIMP  = ?  "); // Valor Unitario com Imposto

		// Codigo da Mercadoria
		sql.append(" WHERE CODMER = ").append(item.mercadoria.codigo);

		db = DatabaseFactory.getInstance();		
		boolean resultado = false;
		try {
			db.executeUpdate(sql.toString(), getParametros(item));
			resultado = true;
		} catch (SQLException e) {
			Log.e(nomeTabela + " - alteraItem - ", e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return resultado;
	}

	/**
	 * 
	 * @param codigoMercadoria
	 * @return
	 */
	public static Item obtemItem(String nomeTabela, Integer codigoMercadoria) {
		StringBuilder sql = obtemQuerySelect(nomeTabela);
		sql.append(" WHERE TMP.CODMER = ? ");
		sql.append(" ORDER BY CAD.DESMER ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query, Util.getStringValue(codigoMercadoria));
		} catch (SQLException e) {
			Log.e(nomeTabela + " - obtemItem - ", e.getMessage());
		}

		List<Item> listaItens = obtemListaItens(maps);
		if (listaItens.isEmpty()) {
			return null;
		} else {
			return listaItens.get(0);
		}
	}
	
	/**
	 * Obtem itens da lista customizada.
	 *
	 * @param nomeTabela the nome tabela
	 * @param codigoListaCustomizada the codigo lista customizada
	 * @return the list
	 */
	public static List<Item> obtemItensDaListaCustomizada(String nomeTabela, Integer codigoListaCustomizada) {
		StringBuilder sql = obtemQuerySelect(nomeTabela);
		sql.append(" WHERE TMP.TIPMCOREP = ? ");
		sql.append(" ORDER BY CAD.DESMER ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query, Util.getStringValue(codigoListaCustomizada));
		} catch (SQLException e) {
			Log.e(nomeTabela + " - obtemItem - ", e.getMessage());
		}

		List<Item> listaItens = obtemListaItens(maps);
		if (listaItens.isEmpty()) {
			return null;
		} else {
			return listaItens;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static List<Item> obtemTodosItens(String nomeTabela) {
		db = DatabaseFactory.getInstance();
		StringBuilder sql = obtemQuerySelect(nomeTabela);
		if("TMPMEPMC".equalsIgnoreCase(nomeTabela)){
			sql.append(" ORDER BY CAD.CODMER ");
		} else if ("TMPGRPDE".equalsIgnoreCase(nomeTabela)) {
			// Na tabela grupo de pesquisa sempre ordenar conforme inclusão
			sql.append(" ORDER BY TMP.ROWID ");
		} else {
			sql.append(" ORDER BY CAD.DESMER ");
		}
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
 			maps = db.executeQuery(sql.toString());
		} catch (SQLException e) {
			Log.e(nomeTabela + " - obtemTodosItens - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}

	/**
	 * 
	 * @param codigoMercadoria
	 * @return
	 */
	public static boolean excluiItem(String nomeTabela, Integer codigoMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from " + nomeTabela + " where CODMER = ? ");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e(nomeTabela + " - excluiItem - ", e.getMessage());
		}

		return resultado;
	}
	
	/**
	 * 
	 * @return
	 */
	private static StringBuilder obtemQuerySelect(String nomeTabela) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT ");
		sql.append(" TMP.CODMER		as codigoMercadoria, "); // Codigo da Mercadoria	
		sql.append(" TMP.TIPPMC		as tipoPromocao, "); // Tipo Promocao	
		sql.append(" TMP.FLGPEE		as flagPee, "); // Flag PEE	
		sql.append(" TMP.QDEMERPED	as qtdMercadoriaPedida, "); 
		sql.append(" TMP.DESMER		as descricaoMercadoria, "); 	
		sql.append(" TMP.CODFILFAT	as codigoFilialFaturamento, "); 
		sql.append(" TMP.TIPMERESG	as tipoEsgotamento, "); 
		sql.append(" TMP.QDECXAFRN	as quantidadeCaixaFornecedor, "); 
		sql.append(" TMP.VLRLIQMER	as valorLiquidoMercadoria, "); 
		sql.append(" TMP.VLRLIQIMP	as valorLiquidoComImposto, "); 
		sql.append(" TMP.UNTCXAIMP	as valorUnitarioCaixaComImposto, "); 
		sql.append(" TMP.VLRUNTIMP	as valorUnitarioComImposto, "); 
		sql.append(" TMP.TIPMCOREP	as tipoMarcacaoRepresentante, "); 
		sql.append(" TMP.CODCNDPGT	as codigoCondicaoPagamento, "); 
		sql.append(" TMP.VLRFRTMER	as valorFrete, "); 
		sql.append(" TMP.NUMNOTFSC	as notaFiscal, "); // Numero da Nota Fiscal
		sql.append(" TMP.TIPICT		as tipoIncentivoMercadoria, "); 	
		sql.append(" TMP.CODFLXPCO	as codigoFlexivelPreco, "); 
		sql.append(" TMP.CODSMBSIT	as codigoSimboloSituacao, "); 
		sql.append(" TMP.PERICMMER	as percentualICMS, "); 
		sql.append(" TMP.CODFILEPD	as codigoFilialExpedicao, "); 
		sql.append(" TMP.PERVLRMNM	as percentualValorMinimo, "); 
		sql.append(" TMP.CMSCNSMER	as comissaoConcessaoMercadoria, "); 
		sql.append(" TMP.DESNGCMER	as descricaoNegocioMercadoria, "); 
		sql.append(" TMP.VLRPTOMER	as valorPontos, "); // Valor Pontos Mercadoria
		sql.append(" TMP.INDNVO		as indicaItemNovo, "); //	
		sql.append(" TMP.VLRBRTMER	as valorBrutoMercadoria, "); 
		sql.append(" TMP.PERDSCFLX	as percentualDescontoConcedido, "); 
		sql.append(" TMP.CODPMC		as codigoPromocao, "); // Codigo Promocao	
		sql.append(" TMP.TIPMERDSC	as tipoDesconto, "); // Tipo Desconto Mercadoria
		sql.append(" TMP.FLGSTSITE	as flagSimboloSituacao, "); 
		sql.append(" TMP.CODRGRDTB	as codigoRegra, "); // Codigo Regra Distribuicao
		sql.append(" TMP.VLRBRTCXA	as valorBrutoCaixa, "); // Valor Bruto Caixa
		sql.append(" TMP.VLRLIQCXA	as valorLiquidoCaixa, "); // Valor Liquido Caixa
		sql.append(" TMP.VLRBRTFRC	as valorBrutoFracionado, "); 
		sql.append(" TMP.PERACOTTC	as percentualDescontoAcaoTatica, "); 
		sql.append(" TMP.PERMAXSMP	as percentualDescontoSimplificado, "); 
		sql.append(" TMP.PERACRCNS	as percentualAcrescimoConcedido, "); 
		sql.append(" TMP.VLRBRTTMP	as valorBrutoTMP, "); // Valor Bruto Temporario
		sql.append(" TMP.VLRSTBMER	as valorSTBUnitario, "); 
		sql.append(" TMP.CMSMAXMER	as comissaoMaximaMercadoria, "); 
		sql.append(" TMP.CODMERPCP	as codigoMercadoriaPrincipal, "); 
		sql.append(" TMP.VLRRDCITE	as valorReducao, "); // Valor Reducao Item
		sql.append(" TMP.LIQCXAIMP	as valorCaixaComImposto, "); 
		sql.append(" TMP.VLRDSCESP	as valorDescontoEspecial, "); 
		sql.append(" TMP.VLRSTBTOT	as valorSTBTotal, "); // Valor STB Total
		sql.append(" TMP.STBTOTBNF	as stbTotalBonificacao, "); 
		sql.append(" TMP.PERDSCMNM	as percentualMinimoDesconto, ");
		sql.append(" TMP.VLRMAXISN	as valorMaximoIsencao, "); 
		sql.append(" TMP.VLRBNFMER	as valorBonificacao, "); 
		sql.append(" TMP.TIPNGCMER	as tipoNegociacaoMercadoria, "); 
		sql.append(" TMP.PERMAXFLX	as percentualDescontoFlexivel, "); 
		sql.append(" TMP.VLRIPIMER	as percentualIPI, "); // Valor IPI Mercadoria
		sql.append(" TMP.VLRIPITOT	as valorIPITotal, "); 
		sql.append(" TMP.IPITOTBNF	as valorIPITotalBonificacao, "); 
		sql.append(" TMP.INDRTCBFV	as indicaRestricaoBeneficioCustomizado, "); 
		sql.append(" TMP.INDRTCBFB	as indicaRestricaoBeneficioCustomizadoBrinde, "); 
		sql.append(" TMP.INDRTCBNF	as indicaRestricaoItemBrinde, "); 
		sql.append(" TMP.INDITEVND	as indicaItemVenda, "); // Indica Item de Venda
		sql.append(" TMP.INDMERKIT	as indicaMercadoriaKit, "); 
		sql.append(" TMP.RTCBFVMER	as indicaRestricaoBeneficioCustomizadoMercadoria, "); 
		sql.append(" TMP.INDSEMMRGL	as temMargemDeLucro, "); 
		sql.append(" TMP.CODCORMRG	as codigoCorMargem, "); // Codigo Cor de Margem
		sql.append(" TMP.PERACOCLI	as percentualDescontoAcao, "); 
		sql.append(" TMP.VLRPTOBFC	as valorPontosBeneficio, "); 
		sql.append(" TMP.CODCORMRGB	as codigoCorMargemB, "); 
		sql.append(" TMP.VLRMNMMER	as valorMinimoMercadoria, "); 
		sql.append(" TMP.PERACRVTL	as percentualAcrescimoVirtual, "); 
		sql.append(" TMP.INDITEIMU	as indicaItemImune, "); // Indica Item Imune
		// Dados Auxiliares de Mercadoria
		sql.append(" CAD.CODGRPNCM	as codigoGrupoNCM ");
		sql.append(" FROM ").append(nomeTabela).append(" TMP ");
		sql.append(" LEFT JOIN CADMER CAD ON CAD.CODMER  = TMP.CODMER  ");
		sql.append(" LEFT JOIN PCASMR SMR ON (SMR.CODMER = CAD.CODMER OR SMR.CODMERPCP = CAD.CODMER) ");
				
		return sql;
	}

	/**
	 * 
	 * @param item
	 * @param sql
	 * @return
	 */
	private static String[] getParametros(Item item) {
		
		String[] parameters = new String[69];
		parameters[0]=Util.getStringValue(item.comissao.comissaoMercadoria);

		//CMSMAXMER-MaximoComissaoMercadoria
		parameters[1]=Util.getStringValue(item.comissao.comissaoMaximaMercadoria);

		//CODCNDPGT-CodigoCondicaodePagamento
		parameters[2]=Util.getStringValue(item.condicaoPagamento.codigoCondicaoPagamento);

		//CODCORMRG-CodigoCordeMargem
		parameters[3]=Util.getStringValue(item.codigoCorMargem);

		//CODCORMRGB-CodigoCordeMargemB
		parameters[4]=Util.getStringValue(item.codigoCorMargemB);

		//CODFILEPD-CodigoFilialExpedicao
		parameters[5]=Util.getStringValue(item.codigoFilialExpedicao);

		//CODFILFAT-CodigodeFilialdeFaturamento
		parameters[6]=Util.getStringValue(item.codigoFilialFaturamento);

		//CODFLXPCO-CodigoPrecoFlexivel
		parameters[7]=Util.getStringValue(item.preco.codigoFlexivelPreco);

		//CODMER-CodigodaMercadoria
		parameters[8]=Util.getStringValue(item.mercadoria.codigo);

		//CODMERPCP-CodigoMercadoriaPrincipal
		parameters[9]=Util.getStringValue(item.mercadoria.codigoMercadoriaPrincipal);

		//CODPMC-CodigoPromocao
		parameters[10]=Util.getStringValue(item.promocao.codigo);

		//CODRGRDTB-CodigoRegraDistribuicao
		parameters[11]=Util.getStringValue(item.regraDistribuicao.codigoRegra);

		//CODSMBSIT-CodigoSimboloSituacao
		parameters[12]=Util.getStringValue(item.preco.codigoSimboloSituacao);

		//DESMER-DescricaodaMercadoria								
		parameters[13]=Util.getStringValue(item.mercadoria.descricao);

		//DESNGCMER-DescricaoNegociacaoMercadoria
		parameters[14]=Util.getStringValue(item.mercadoria.descricaoNegocioMercadoria);

		//FLGPEE-FlagPEE
		parameters[15]=Util.getStringValue(item.mercadoria.flagPee);

		//FLGSTSITE-FlagSimboloSituacao
		parameters[16]=Util.getStringValue(item.preco.flagSimboloSituacao);

		//INDITEIMU-IndicaItemImune
		parameters[17]=Util.getStringValue(item.indicaItemImune);

		//INDITEVND-IndicaItemdeVenda
		parameters[18]=Util.getStringValue(item.indicaItemVenda);

		//INDMERKIT-IndicaMercadoriaKit
		parameters[19]=Util.getStringValue(item.mercadoria.indicaMercadoriaKit);

		//INDNVO-Indicaqueénovo																			
		parameters[20]=Util.getStringValue(item.indicaItemNovo);

		//INDRTCBFB-IndicaRestricaoBeneficioCustomizadonoBrinde
		parameters[21]=Util.getStringValue(item.indicaRestricaoBeneficioCustomizadoBrinde);

		//INDRTCBFV-IndicaRestricaoBeneficioCustomizado
		parameters[22]=Util.getStringValue(item.indicaRestricaoBeneficioCustomizado);

		//INDRTCBNF-IndicaRestricaodeItemcomoBrinde
		parameters[23]=Util.getStringValue(item.indicaRestricaoItemBrinde);

		//INDSEMMRGL-IndicaSemMargemdeLucro
		parameters[24]=Util.getStringValue(item.stb.temMargemDeLucro?'1':'0');

		//IPITOTBNF-ValorIPITotalBonificacao
		parameters[25]=Util.getStringValue(item.valorIPITotalBonificacao);

		//LIQCXAIMP-ValorLiquidoCaixacomImposto
		parameters[26]=Util.getStringValue(item.valorCaixaComImposto);
		
		//NUMNOTFSC-NumerodaNotaFiscal
		parameters[27]=Util.getStringValue(item.notaFiscal);

		//PERACOCLI-PercentualdeAcaodoCliente
		parameters[28]=Util.getStringValue(item.desconto.percentualDescontoAcao);

		//PERACOTTC-PercentualdeAcaoTatica
		parameters[29]=Util.getStringValue(item.percentualDescontoAcaoTatica);

		//PERACRCNS-PercentualAcrescimoConcedido																							
		parameters[30]=Util.getStringValue(item.percentualAcrescimoConcedido);

		//PERACRVTL-PercentualdeAcrescimoVirtual
		parameters[31]=Util.getStringValue(item.percentualAcrescimoVirtual);

		//PERDSCFLX-PercentualDescontoConcedido
		parameters[32]=Util.getStringValue(item.percentualDescontoConcedido);

		//PERDSCMNM-PercentualDescontoMinimo
		parameters[33]=Util.getStringValue(item.desconto.percentualMinimoDesconto);

		//PERICMMER-PercentualdeICMdaMercadoria
		parameters[34]=Util.getStringValue(item.percentualICM);

		//PERMAXFLX-PercentualMaximoFlexivel
		parameters[35]=Util.getStringValue(item.desconto.percentualMaximoDesconto);

		//PERMAXSMP-PercentualMaximoSimplificado
		parameters[36]=Util.getStringValue(item.percentualDescontoSimplificado);

		//PERVLRMNM-PercentualValorMinimo
		parameters[37]=Util.getStringValue(item.stb.percentualValorMinimo);

		//QDECXAFRN-QuantidadeCaixaFornecedor
		parameters[38]=Util.getStringValue(item.mercadoria.quantidadeCaixaFornecedor);

		//QDEMERPED-QuantidadedaMercadorianoPedido
		parameters[39]=Util.getStringValue(item.quantidadeMercadoria);

		//RTCBFVMER-RestricaodeBeneficiodeMercadoria
		parameters[40]=Util.getStringValue(item.indicaRestricaoBeneficioCustomizadoMercadoria);

		//STBTOTBNF-STBTotalBonificacao
		parameters[41]=Util.getStringValue(item.stb.valorSTBTotalBonificacao);

		//TIPICT-TipodeIncentivo
		parameters[42]=Util.getStringValue(item.preco.tipoIncentivoMercadoria);

		//TIPMCOREP-TipoMarcacaoRepresentante(ListasCustomizadas)
		parameters[43]=Util.getStringValue(item.preco.tipoMarcacaoRepresentante);

		//TIPMERDSC-TipoDescontoMercadoria
		parameters[44]=Util.getStringValue(item.desconto.tipo);

		//TIPMERESG-TipodeEsgotamentodaMercadoria
		parameters[45]=Util.getStringValue(item.mercadoria.tipoEsgotamento);

		//TIPNGCMER-TipoNegociacaoMercadoria
		parameters[46]=Util.getStringValue(item.tipoNegociacaoMercadoria);

		//TIPPMC-TipoPromocao
		parameters[47]=Util.getStringValue(item.promocao.tipo);

		//UNTCXAIMP-ValorUnitariodaCaixacomImposto
		parameters[48]=Util.getStringValue(item.valorUnitarioCaixaComImposto);

		//VLRBNFMER-ValorBonificacaoMercadoria
		parameters[49]=Util.getStringValue(item.valorBonificacao);

		//VLRBRTCXA-ValorBrutoCaixa
		parameters[50]=Util.getStringValue(item.preco.valorBrutoCaixa);

		//VLRBRTFRC-ValorBrutoFracionado
		parameters[51]=Util.getStringValue(item.preco.valorBrutoFracionado);

		//VLRBRTMER-ValorBrutoMercadoria
		parameters[52]=Util.getStringValue(item.preco.valorBrutoMercadoria);

		//VLRBRTTMP-ValorBrutoTemporario
		parameters[53]=Util.getStringValue(item.preco.valorBrutoTMP);

		//VLRDSCESP-ValorDescontoEspecial
		parameters[54]=Util.getStringValue(item.desconto.valorDescontoEspecial);

		//VLRFRTMER-ValordoFretedaMercadoria
		parameters[55]=Util.getStringValue(item.frete);

		//VLRIPIMER-ValorIPIMercadoria
		parameters[56]=Util.getStringValue(item.mercadoria.percentualIPI.percentualIPI);

		//VLRIPITOT-ValorIPITotalMercadoria
		parameters[57]=Util.getStringValue(item.valorIPITotal);

		//VLRLIQCXA-ValorLiquidoCaixa
		parameters[58]=Util.getStringValue(item.valorLiquidoCaixa);

		//VLRLIQIMP-ValorLiquidodaMercadoriacomImposto
		parameters[59]=Util.getStringValue(item.valorLiquidoComImposto);

		//VLRLIQMER-ValorLiquidoMercadoria
		parameters[60]=Util.getStringValue(item.valorLiquidoMercadoria);

		//VLRMAXISN-ValorMaximoMercadoria
		parameters[61]=Util.getStringValue(item.desconto.valorDescontoIsencao);

		//VLRMNMMER-ValorMinimoMercadoria
		parameters[62]=Util.getStringValue(item.mercadoria.valorMinimoMercadoria);

		//VLRPTOBFC-ValorPontosdeBeneficio
		parameters[63]=Util.getStringValue(item.valorPontoBeneficioMercadoria);

		//VLRPTOMER-ValorPontos
		parameters[64]=Util.getStringValue(item.valorPontuacaoMercadoria);

		//VLRRDCITE-ValorReducaoItem
		parameters[65]=Util.getStringValue(item.valorReducao);

		//VLRSTBMER-ValorSTB(SubstituicaoTributaria)Mercadoria
		parameters[66]=Util.getStringValue(item.stb.valorSTBUnitario);

		//VLRSTBTOT-ValorSTBTotal
		parameters[67]=Util.getStringValue(item.stb.valorSTBTotal);

		//VLRUNTIMP-ValorUnitariocomImposto
		parameters[68]=Util.getStringValue(item.valorUnitarioComImposto);
		
		return parameters;
	}

	/**
	 * 
	 * @param itens
	 * @return
	 */
	private static List<Item> obtemListaItens(List<Map<String, String>> itens) {
		List<Item> listaItens = new ArrayList<Item>();

		for (Map<String, String> itemAux : itens) {
			
			final Item item = new Item();
			final Mercadoria mercadoria = item.mercadoria;
			
			// DADOS DO ITEM
			item.notaFiscal = Util.getInteger(itemAux.get("notaFiscal"));
			item.codigoFilialExpedicao = Util.getInteger(itemAux.get("codigoFilialExpedicao"));
			item.stb.percentualValorMinimo = Util.getBigDecimal(itemAux.get("percentualValorMinimo"));
			item.comissao.comissaoMercadoria = Util.getBigDecimal(itemAux.get("comissaoConcessaoMercadoria"));
			item.indicaItemNovo = itemAux.get("indicaItemNovo");
			item.preco.valorBrutoMercadoria = Util.getBigDecimal(itemAux.get("valorBrutoMercadoria"));
			item.percentualDescontoConcedido = Util.getBigDecimal(itemAux.get("percentualDescontoConcedido"));
			item.preco.valorBrutoCaixa = Util.getBigDecimal(itemAux.get("valorBrutoCaixa"));
			item.valorLiquidoCaixa = Util.getBigDecimal(itemAux.get("valorLiquidoCaixa"));
			item.preco.valorBrutoFracionado = Util.getBigDecimal(itemAux.get("valorBrutoFracionado"));
			item.percentualAcrescimoConcedido = Util.getBigDecimal(itemAux.get("percentualAcrescimoConcedido"));
			item.valorReducao = Util.getInteger(itemAux.get("valorReducao"));
			item.valorCaixaComImposto = Util.getBigDecimal(itemAux.get("valorCaixaComImposto"));
			item.valorBonificacao = Util.getBigDecimal(itemAux.get("valorBonificacao"));
			item.tipoNegociacaoMercadoria = Util.getInteger(itemAux.get("tipoNegociacaoMercadoria"));
			item.valorIPITotal = Util.getBigDecimal(itemAux.get("valorIPITotal"));
			item.valorIPITotalBonificacao = Util.getBigDecimal(itemAux.get("valorIPITotalBonificacao"));

			mercadoria.temRestricaoBrinde = Util.getInteger(itemAux.get("indicaRestricaoItemBrinde"));
			mercadoria.temRestricaoBeneficioCustomizado =  Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizado"));
			mercadoria.temRestricaoBeneficioCustomizadoNoBrinde = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizadoBrinde"));
			
			item.indicaRestricaoItemBrinde = Util.getInteger(itemAux.get("indicaRestricaoItemBrinde"));
			item.indicaItemVenda = Util.getInteger(itemAux.get("indicaItemVenda"));
			item.codigoCorMargem = Util.getInteger(itemAux.get("codigoCorMargem"));
			item.codigoCorMargemB = Util.getInteger(itemAux.get("codigoCorMargemB"));
			item.percentualDescontoAcaoTatica = Util.getBigDecimal(itemAux.get("percentualDescontoAcaoTatica"));
			item.percentualAcrescimoVirtual = Util.getBigDecimal(itemAux.get("percentualAcrescimoVirtual"));
			item.indicaItemImune = Util.getInteger(itemAux.get("indicaItemImune"));
			item.percentualDescontoSimplificado = Util.getBigDecimal(itemAux.get("percentualDescontoSimplificado"));
			item.comissao.comissaoMaximaMercadoria = Util.getBigDecimal(itemAux.get("comissaoMaximaMercadoria"));
			item.quantidadeMercadoria = Util.getInteger(itemAux.get("qtdMercadoriaPedida"));

			// MERCADORIA
			item.valorLiquidoMercadoria = Util.getBigDecimal(itemAux.get("valorLiquidoMercadoria"));
			item.valorLiquidoComImposto = Util.getBigDecimal(itemAux.get("valorLiquidoComImposto"));
			item.valorUnitarioCaixaComImposto = Util.getBigDecimal(itemAux.get("valorUnitarioCaixaComImposto"));
			item.valorUnitarioComImposto = Util.getBigDecimal(itemAux.get("valorUnitarioComImposto"));
			item.frete = Util.getBigDecimal(itemAux.get("valorFrete"));
			
			item.percentualICM = Util.getBigDecimal(itemAux.get("percentualICMS"));
			
			mercadoria.codigo = Util.getInteger(itemAux.get("codigoMercadoria"));
			mercadoria.digito = Mercadoria.obtemDigitoVerificador(mercadoria.codigo);
			
			mercadoria.codigoMercadoriaPrincipal = Util.getInteger(itemAux.get("codigoMercadoriaPrincipal"));
			
			mercadoria.descricao = itemAux.get("descricaoMercadoria");
			
			mercadoria.flagPee = itemAux.get("flagPee");
			item.codigoFilialFaturamento = Util.getInteger(itemAux.get("codigoFilialFaturamento"));
			mercadoria.tipoEsgotamento = itemAux.get("tipoEsgotamento");
			mercadoria.quantidadeCaixaFornecedor = Util.getInteger(itemAux.get("quantidadeCaixaFornecedor"));
			mercadoria.valorFrete = Util.getBigDecimal(itemAux.get("valorFrete"));
			mercadoria.descricaoNegocioMercadoria = itemAux.get("descricaoNegocioMercadoria");
			
//			item.mercadoria.valorPontos = Util.getBigDecimal(itemAux.get("valorPontos"));
			item.valorPontuacaoMercadoria = Util.getInteger(itemAux.get("valorPontos"));
			
			item.valorPontoBeneficioMercadoria = Util.getInteger(itemAux.get("valorPontosBeneficio"));
			mercadoria.valorMinimoMercadoria = Util.getBigDecimal(itemAux.get("valorMinimoMercadoria"));
			
//			item.mercadoria.valorMaximoMercadoria = Util.getBigDecimal(itemAux.get("valorMaximoMercadoria"));
			item.desconto.percentualMaximoDesconto = Util.getBigDecimal(itemAux.get("valorMaximoMercadoria"));
			
			mercadoria.indicaMercadoriaKit  = Util.getInteger(itemAux.get("indicaMercadoriaKit"));
			mercadoria.temMercadoriaSimilar = MercadoriaSimilarDAO.temSimilar(mercadoria.codigoMercadoriaPrincipal);

			// PRECO
			item.preco.tipoMarcacaoRepresentante = itemAux.get("tipoMarcacaoRepresentante");
			item.preco.tipoIncentivoMercadoria = itemAux.get("tipoIncentivoMercadoria");
			item.preco.codigoFlexivelPreco = itemAux.get("codigoFlexivelPreco");
			item.preco.codigoSimboloSituacao = itemAux.get("codigoSimboloSituacao");
			item.preco.flagSimboloSituacao = null == itemAux.get("flagSimboloSituacao") ? 0 : Util.getInteger(itemAux.get("flagSimboloSituacao"));
			item.preco.valorBrutoTMP = Util.getBigDecimal(itemAux.get("valorBrutoTMP"));

			// TODO: Verificar qual é a diferença dos atributos
			// percentualICMS e percentualTributacao
			item.preco.percentualTributacao = item.preco.percentualICMS;

			// CONDICAO DE PAGAMENTO
			item.condicaoPagamento.codigoCondicaoPagamento = Util.getInteger(itemAux.get("codigoCondicaoPagamento"));

			// PERCENTUAL IPI
			mercadoria.percentualIPI.percentualIPI = Util.getBigDecimal(itemAux.get("percentualIPI"));

			// STB
			item.stb.valorSTBUnitario = Util.getBigDecimal(itemAux.get("valorSTBUnitario"));
			item.stb.valorSTBTotal = Util.getBigDecimal(itemAux.get("valorSTBTotal"));
			item.stb.valorSTBTotalBonificacao = Util.getBigDecimal(itemAux.get("stbTotalBonificacao"));
			// TODO VERIFICAR SE A REGRA ESTA CORRETA
			item.stb.temMargemDeLucro = Util.getInteger(itemAux.get("temMargemDeLucro")) == 0 ? false : true;

			// DESCONTO
			item.desconto.tipo = itemAux.get("tipoDesconto");
			item.desconto.valorDescontoEspecial = Util.getBigDecimal(itemAux.get("valorDescontoEspecial"));
			item.desconto.percentualMinimoDesconto = Util.getBigDecimal(itemAux.get("percentualMinimoDesconto"));
			item.desconto.percentualDescontoAcao = Util.getBigDecimal(itemAux.get("percentualDescontoAcao"));

			// PROMOCAO
			item.promocao.tipo = itemAux.get("tipoPromocao");
			item.promocao.codigo = Util.getInteger(itemAux.get("codigoPromocao"));

			// REGRA DE DISTRIBUICAO
			item.regraDistribuicao.codigoRegra = Util.getInteger(itemAux.get("codigoRegra"));
			
			// DADOS DE MERCADORIA
			mercadoria.codigoGrupoNCM = itemAux.get("codigoGrupoNCM");

			listaItens.add(item);
		}

		return listaItens;
	}
	
}
