package br.com.martins.vendas.dao.temp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Imposto;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.services.desconto.Desconto;
import br.com.martins.vendas.vo.Beneficio;
import br.com.martins.vendas.vo.Comissao;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PercentualIPI;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class TabelaTmpItePeDAO {

	private static final String	TAG	= TabelaTmpItePeDAO.class.getName();
	
	private static Database db;

	/**
	 * Método responsável por remover todos os dados da tabela TMPITEPE.
	 * 
	 * @return <code>true</code> se os dados da tabela foram removidos com
	 *         sucesso, e <code>false</code> caso tenha ocorrido algum problema
	 *         que tenha impedido a remoção dos dados da tabela.
	 */
	public static boolean limpaTabela() {
		String sql = " delete from TMPITEPE ";
		boolean resultado = false;

		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(sql);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - limpaTabela - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean insereItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into TMPITEPE ( ");
		sql.append(" CMSCNSMER, "); // Comissao Concessao Mercadoria
		sql.append(" CMSMAXMER, "); // Maximo Comissao Mercadoria
		sql.append(" CODCNDPGT, "); // Codigo Condicao de Pagamento
		sql.append(" CODCORMRG, "); // Codigo Cor de Margem
		sql.append(" CODCORMRGB, "); // Codigo Cor de Margem B
		sql.append(" CODFILEPD, "); // Codigo Filial Expedicao
		sql.append(" CODFILFAT, "); // Codigo de Filial de Faturamento
		sql.append(" CODFLXPCO, "); // Codigo Preco Flexivel
		sql.append(" CODMER, "); // Codigo da Mercadoria
		sql.append(" CODMERDIG, "); // *** Codigo Mercadoria Digito
		sql.append(" CODMERPCP, "); // Codigo Mercadoria Principal
		sql.append(" CODPMC, "); // Codigo Promocao
		sql.append(" CODRGRDTB, "); // Codigo Regra Distribuicao
		sql.append(" CODSMBSIT, "); // Codigo Simbolo Situacao
		sql.append(" DESMER, "); // Descricao da Mercadoria
		sql.append(" DESNGCMER, "); // Descricao Negociacao Mercadoria
		sql.append(" FLGPEE, "); // Flag PEE
		sql.append(" FLGSTSITE, "); // *** Flag STS Item
		sql.append(" INDITEIMU, "); // Indica Item Imune
		sql.append(" INDITEVND, "); // Indica Item de Venda
		sql.append(" INDMERKIT, "); // Indica Mercadoria Kit
		sql.append(" INDNVO, "); // Indica Novo
		sql.append(" INDRTCBFB, "); // Indica Restricao Beneficio Customizado no Brinde
		sql.append(" INDRTCBFV, "); // Indica Restricao Beneficio Customizado
		sql.append(" INDRTCBNF, "); // Indica Restricao de Item como Brinde
		sql.append(" INDSEMMRGL, "); // Indica Sem Margem de Lucro
		sql.append(" IPITOTBNF, "); // Valor IPI Total Bonificacao
		sql.append(" LIQCXAIMP, "); // Valor Liquido Caixa com Imposto
		sql.append(" NUMNOTFSC, "); // Numero da Nota Fiscal
		sql.append(" NUMSEQITE, "); // *** Numero Sequencia Item
		sql.append(" PERACOCLI, "); // Percentual de Acao do Cliente
		sql.append(" PERACOTTC, "); // Percentual de Acao Tatica
		sql.append(" PERACRCNS, "); // Percentual Acrescimo Concedido
		sql.append(" PERACRVTL, "); // Percentual de Acrescimo Virtual
		sql.append(" PERCMSADI, "); // *** Percentual Comissao Adicional
		sql.append(" PERDSCBFC, "); // *** Percentual Desconto Beneficio
		sql.append(" PERDSCFLX, "); // Percentual Desconto Flexivel
		sql.append(" PERDSCMNM, "); // Percentual Desconto Minimo
		sql.append(" PERECOBFC, "); // *** Percentual Economico Beneficio
		sql.append(" PERICMMER, "); // Percentual de ICM da Mercadoria
		sql.append(" PERMAXFLX, "); // Percentual Maximo Flexivel
		sql.append(" PERMAXSMP, "); // Percentual Maximo Simplificado
		sql.append(" PERVLRMNM, "); // Percentual Valor Minimo
		sql.append(" QDECXAFRN, "); // Quantidade Caixa Fornecedor
		sql.append(" QDEMERPED, "); // Quantidade da Mercadoria no Pedido
		sql.append(" QDEPRZBFC, "); // *** Quantidade Prazo Beneficio
		sql.append(" RTCBFVMER, "); // Restricao de Beneficio de Mercadoria
		sql.append(" STBCPLTOT, "); // *** STB Complemento Total
		sql.append(" STBTOTBNF, "); // STB Total Bonificacao
		sql.append(" TIPICT, "); // Tipo de Incentivo
		sql.append(" TIPMCOREP, "); // Tipo Marcacao Representante (Listas Customizadas)
		sql.append(" TIPMERDSC, "); // Tipo Desconto Mercadoria
		sql.append(" TIPMERESG, "); // Tipo de Esgotamento da Mercadoria
		sql.append(" TIPNGCMER, "); // Tipo Negociacao Mercadoria
		sql.append(" TIPPMC, "); // Tipo Promocao
		sql.append(" UNTCXAIMP, "); // Valor Unitario da Caixa com Imposto
		sql.append(" VLRBNFMER, "); // Valor Bonificacao Mercadoria
		sql.append(" VLRBRTCXA, "); // Valor Bruto Caixa
		sql.append(" VLRBRTFRC, "); // Valor Bruto Fracionado
		sql.append(" VLRBRTMER, "); // Valor Bruto Mercadoria
		sql.append(" VLRBRTTMP, "); // Valor Bruto Temporario
		sql.append(" VLRDSCESP, "); // Valor Desconto Especial
		sql.append(" VLRFRTMER, "); // Valor do Frete da Mercadoria
		sql.append(" VLRIPIMER, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT, "); // Valor IPI Total Mercadoria
		sql.append(" VLRLIQCXA, "); // Valor Liquido Caixa
		sql.append(" VLRLIQIMP, "); // Valor Liquido da Mercadoria com Imposto
		sql.append(" VLRLIQMER, "); // Valor Liquido Mercadoria
		sql.append(" VLRMAXISN, "); // Valor Maximo Isencao
		sql.append(" VLRMNMMER, "); // Valor Minimo Mercadoria
		sql.append(" VLRPTOBFC, "); // Valor Pontos de Beneficio
		sql.append(" VLRPTOMER, "); // Valor Pontos
		sql.append(" VLRRDCITE, "); // Valor Reducao Item
		sql.append(" VLRSTBCPL, "); // *** Valor STB Complemento
		sql.append(" VLRSTBMER, "); // Valor STB (Substituicao Tributaria) Mercadoria
		sql.append(" VLRSTBTOT, "); // Valor STB Total
		sql.append(" VLRTOTFRT, "); // *** Valor Total Frete
		sql.append(" VLRTOTLIQ, "); // *** Valor Total Liquido
		sql.append(" VLRUNTIMP "); // Valor Unitario com Imposto
		sql.append(" ) values ( ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s ");
		sql.append(" ) ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		String query = montaQuery(item, sql);

		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - insere - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean alteraItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE TMPITEPE SET ");
		sql.append(" CMSCNSMER		= %s, "); // Comissao Concessao Mercadoria
		sql.append(" CMSMAXMER		= %s, "); // Maximo Comissao Mercadoria
		sql.append(" CODCNDPGT		= %s, "); // Codigo Condicao de Pagamento
		sql.append(" CODCORMRG		= %s, "); // Codigo Cor de Margem
		sql.append(" CODCORMRGB		= %s, "); // Codigo Cor de Margem B
		sql.append(" CODFILEPD		= %s, "); // Codigo Filial Expedicao
		sql.append(" CODFILFAT		= %s, "); // Codigo de Filial de Faturamento
		sql.append(" CODFLXPCO		= %s, "); // Codigo Preco Flexivel
		sql.append(" CODMER			= %s, "); // Codigo da Mercadoria
		sql.append(" CODMERDIG		= %s, "); // *** Codigo Mercadoria Digito
		sql.append(" CODMERPCP		= %s, "); // Codigo Mercadoria Principal
		sql.append(" CODPMC			= %s, "); // Codigo Promocao
		sql.append(" CODRGRDTB		= %s, "); // Codigo Regra Distribuicao
		sql.append(" CODSMBSIT		= %s, "); // Codigo Simbolo Situacao
		sql.append(" DESMER			= %s, "); // Descricao da Mercadoria
		sql.append(" DESNGCMER		= %s, "); // Descricao Negociacao Mercadoria
		sql.append(" FLGPEE			= %s, "); // Flag PEE
		sql.append(" FLGSTSITE		= %s, "); // *** Flag STS Item
		sql.append(" INDITEIMU		= %s, "); // Indica Item Imune
		sql.append(" INDITEVND		= %s, "); // Indica Item de Venda
		sql.append(" INDMERKIT		= %s, "); // Indica Mercadoria Kit
		sql.append(" INDNVO			= %s, "); // Indica Novo
		sql.append(" INDRTCBFB		= %s, "); // Indica Restricao Beneficio Customizado no Brinde
		sql.append(" INDRTCBFV		= %s, "); // Indica Restricao Beneficio Customizado
		sql.append(" INDRTCBNF		= %s, "); // Indica Restricao de Item como Brinde
		sql.append(" INDSEMMRGL		= %s, "); // Indica Sem Margem de Lucro
		sql.append(" IPITOTBNF		= %s, "); // Valor IPI Total Bonificacao
		sql.append(" LIQCXAIMP		= %s, "); // Valor Liquido Caixa com Imposto
		sql.append(" NUMNOTFSC		= %s, "); // Numero da Nota Fiscal
		sql.append(" NUMSEQITE		= %s, "); // *** Numero Sequencia Item
		sql.append(" PERACOCLI		= %s, "); // Percentual de Acao do Cliente
		sql.append(" PERACOTTC		= %s, "); // Percentual de Acao Tatica
		sql.append(" PERACRCNS		= %s, "); // Percentual Acrescimo Concedido
		sql.append(" PERACRVTL		= %s, "); // Percentual de Acrescimo Virtual
		sql.append(" PERCMSADI		= %s, "); // *** Percentual Comissao Adicional
		sql.append(" PERDSCBFC		= %s, "); // *** Percentual Desconto Beneficio
		sql.append(" PERDSCFLX		= %s, "); // Percentual Desconto Flexivel
		sql.append(" PERDSCMNM		= %s, "); // Percentual Desconto Minimo
		sql.append(" PERECOBFC		= %s, "); // *** Percentual Economico Beneficio
		sql.append(" PERICMMER		= %s, "); // Percentual de ICM da Mercadoria
		sql.append(" PERMAXFLX		= %s, "); // Percentual Maximo Flexivel
		sql.append(" PERMAXSMP		= %s, "); // Percentual Maximo Simplificado
		sql.append(" PERVLRMNM		= %s, "); // Percentual Valor Minimo
		sql.append(" QDECXAFRN		= %s, "); // Quantidade Caixa Fornecedor
		sql.append(" QDEMERPED		= %s, "); // Quantidade da Mercadoria no Pedido
		sql.append(" QDEPRZBFC		= %s, "); // *** Quantidade Prazo Beneficio
		sql.append(" RTCBFVMER		= %s, "); // Restricao de Beneficio de Mercadoria
		sql.append(" STBCPLTOT		= %s, "); // *** STB Complemento Total
		sql.append(" STBTOTBNF		= %s, "); // STB Total Bonificacao
		sql.append(" TIPICT			= %s, "); // Tipo de Incentivo
		sql.append(" TIPMCOREP		= %s, "); // Tipo Marcacao Representante (Listas Customizadas)
		sql.append(" TIPMERDSC		= %s, "); // Tipo Desconto Mercadoria
		sql.append(" TIPMERESG		= %s, "); // Tipo de Esgotamento da Mercadoria
		sql.append(" TIPNGCMER		= %s, "); // Tipo Negociacao Mercadoria
		sql.append(" TIPPMC			= %s, "); // Tipo Promocao
		sql.append(" UNTCXAIMP		= %s, "); // Valor Unitario da Caixa com Imposto
		sql.append(" VLRBNFMER		= %s, "); // Valor Bonificacao Mercadoria
		sql.append(" VLRBRTCXA		= %s, "); // Valor Bruto Caixa
		sql.append(" VLRBRTFRC		= %s, "); // Valor Bruto Fracionado
		sql.append(" VLRBRTMER		= %s, "); // Valor Bruto Mercadoria
		sql.append(" VLRBRTTMP		= %s, "); // Valor Bruto Temporario
		sql.append(" VLRDSCESP		= %s, "); // Valor Desconto Especial
		sql.append(" VLRFRTMER		= %s, "); // Valor do Frete da Mercadoria
		sql.append(" VLRIPIMER		= %s, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT		= %s, "); // Valor IPI Total Mercadoria
		sql.append(" VLRLIQCXA		= %s, "); // Valor Liquido Caixa
		sql.append(" VLRLIQIMP		= %s, "); // Valor Liquido da Mercadoria com Imposto
		sql.append(" VLRLIQMER		= %s, "); // Valor Liquido Mercadoria
		sql.append(" VLRMAXISN		= %s, "); // Valor Maximo Isencao
		sql.append(" VLRMNMMER		= %s, "); // Valor Minimo Mercadoria
		sql.append(" VLRPTOBFC		= %s, "); // Valor Pontos de Beneficio
		sql.append(" VLRPTOMER		= %s, "); // Valor Pontos
		sql.append(" VLRRDCITE		= %s, "); // Valor Reducao Item
		sql.append(" VLRSTBCPL		= %s, "); // *** Valor STB Complemento
		sql.append(" VLRSTBMER		= %s, "); // Valor STB (Substituicao Tributaria) Mercadoria
		sql.append(" VLRSTBTOT		= %s, "); // Valor STB Total
		sql.append(" VLRTOTFRT		= %s, "); // *** Valor Total Frete
		sql.append(" VLRTOTLIQ		= %s, "); // *** Valor Total Liquido
		sql.append(" VLRUNTIMP		= %s "); // Valor Unitario com Imposto

		// Codigo da Mercadoria
		sql.append(" WHERE CODMER    = ").append(item.mercadoria.codigo);
		sql.append("   AND CODFILEPD = ").append(item.codigoFilialExpedicaoAux);
		sql.append("   AND CODFILFAT = ").append(item.codigoFilialFaturamentoAux);
		sql.append("   AND NUMNOTFSC = ").append(item.notaFiscalAux);
		//sql.append("   AND CODCNDPGT = ").append(item.condicaoPagamento.codigoCondicaoPagamento);
		sql.append("   AND TIPNGCMER = ").append(item.tipoNegociacaoMercadoriaAux);

		String query = montaQuery(item, sql);
		db = DatabaseFactory.getInstance();
		
		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - alteraItem - ", e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return resultado;
	}
	
	public static boolean excluiItem(Integer codigoMercadoria, Integer numeroNotaFiscal) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from TMPITEPE where CODMER = %s ");
		sql.append(" and NUMNOTFSC= %s");
		
		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria,numeroNotaFiscal);
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - excluiItem - ", e.getMessage());
		}

		return resultado;
	}
	
	public static Item obtemItem(Integer codigoMercadoria) {
		StringBuilder sql = obtemQuerySelect();
		sql.append(" where CODMER = %s ");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - obtemItem - ", e.getMessage());
		}

		List<Item> listaItens = obtemListaItens(maps);
		if (listaItens.isEmpty()) {
			return null;
		} else {
			return listaItens.get(0);
		}
	}
	
	public static Item obtemItem(Integer codigoMercadoria,
			Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			Integer numeroNotaFiscal,
			Integer codigoTipoNegociacao) {
		
		StringBuilder sql = obtemQuerySelect();
		sql.append(" WHERE CODMER    = ? ");
		sql.append("   AND CODFILEPD = ? ");
		sql.append("   AND CODFILFAT = ? ");
		sql.append("   AND NUMNOTFSC = ? ");
		sql.append("   AND TIPNGCMER = ? ");
				
		String[] parameters = new String[5];
		parameters[0] = Util.getStringValue(codigoMercadoria);
		parameters[1] = Util.getStringValue(codigoFilialExpedicao);
		parameters[2] = Util.getStringValue(codigoFilialFaturamento);
		parameters[3] = Util.getStringValue(numeroNotaFiscal);
		parameters[4] = Util.getStringValue(codigoTipoNegociacao);
		
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(sql.toString(), parameters);
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - obtemItem - ", e.getMessage());
		}

		List<Item> listaItens = obtemListaItens(maps);
		if (listaItens.isEmpty()) {
			return null;
		} else {
			return listaItens.get(0);
		}
	}
	
	public static List<Item> obtemTodosItens() {
		StringBuilder sql = obtemQuerySelect();
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(sql.toString());
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - obtemTodosItens - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}

	public static List<Item> obtemTodosItens(Integer numeroPedido) {
		StringBuilder sql = obtemQuerySelect();
		sql.append(" WHERE NUMPED = %s ");
		
		String query = DatabaseUtil.montaQuery(sql, numeroPedido);
		db = DatabaseFactory.getInstance();
		
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpItePeDAO - obtemTodosItens(numeroPedido) - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}
	
	/**
	 * Obtem itens da lista customizada. Campo de referencia (TIPMCOREP)
	 *
	 * @param codigoListaCustomizada the codigo lista customizada
	 * @return the list
	 */
	public static List<Item> obtemItensDaListaCustomizada(Integer codigoListaCustomizada){
		StringBuilder sql = obtemQuerySelect();
		sql.append(" WHERE TIPMCOREP = ? ");
		
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(sql.toString(), Util.getStringValue(codigoListaCustomizada));
		} catch (SQLException e) {
			Log.e(TAG + " - obtemItem - ", e.getMessage());
		}
		List<Item> listaItens = obtemListaItens(maps);
		if(listaItens.isEmpty()){
			return null;
		}else {
			return listaItens;
		}
	}
	
	private static String montaQuery(Item item, StringBuilder sql) {
		String query = DatabaseUtil.montaQueryGenerica(sql, //
				
				item.comissao.comissaoMercadoria, // Comissao Concessao Mercadoria
				item.comissao.comissaoMaximaMercadoria, // Maximo Comissao Mercadoria
				item.condicaoPagamento.codigoCondicaoPagamento, // Codigo Condicao de Pagamento
				item.codigoCorMargem, // Codigo Cor de Margem
				item.codigoCorMargemB, // Codigo Cor de Margem B
				item.codigoFilialExpedicao, // Codigo Filial Expedicao
				item.codigoFilialFaturamento, // Codigo de Filial de Faturamento
				item.preco.codigoFlexivelPreco, // Codigo Preco Flexivel
				item.mercadoria.codigo, // Codigo da Mercadoria
				item.mercadoria.digito, // *** Codigo Mercadoria Digito
				item.mercadoria.codigoMercadoriaPrincipal, // Codigo Mercadoria Principal
				item.promocao.codigo, // Codigo Promocao
				item.regraDistribuicao.codigoRegra, // Codigo Regra Distribuicao
				item.preco.codigoSimboloSituacao, // Codigo Simbolo Situacao
				item.mercadoria.descricao, // Descricao da Mercadoria
				item.mercadoria.descricaoNegocioMercadoria, // Descricao Negociacao Mercadoria
				item.mercadoria.flagPee, // Flag PEE
				item.preco.flagSimboloSituacao, // *** Flag STS Item
				item.indicaItemImune, // Indica Item Imune
				item.indicaItemVenda, // Indica Item de Venda
				item.mercadoria.indicaMercadoriaKit, // Indica Mercadoria Kit
				item.indicaItemNovo, // Indica Novo
				item.indicaRestricaoBeneficioCustomizadoBrinde, // Indica Restricao Beneficio Customizado no Brinde
				item.indicaRestricaoBeneficioCustomizado, // Indica Restricao Beneficio Customizado
				item.indicaRestricaoItemBrinde, // Indica Restricao de Item como Brinde
				item.stb.obterMargemDeLucroNumerica(), // Indica Sem Margem de Lucro
				item.valorIPITotalBonificacao, // Valor IPI Total Bonificacao
				item.valorCaixaComImposto, // Valor Liquido Caixa com Imposto
				item.notaFiscal, // Numero da Nota Fiscal
				item.numeroSequenciaItem, // *** Numero Sequencia Item
				item.desconto.percentualDescontoAcao, // Percentual de Acao do Cliente
				item.percentualDescontoAcaoTatica, // Percentual de Acao Tatica
				item.percentualAcrescimoConcedido, // Percentual Acrescimo Concedido
				item.percentualAcrescimoVirtual, // Percentual de Acrescimo Virtual
				item.beneficio.percentualComissao,// PERCMSADI - *** Percentual Comissao Adicional
				item.percentualDescontoBeneficio, // *** Percentual Desconto Beneficio
				item.percentualDescontoFlex, // Percentual Desconto Flexivel
				item.desconto.percentualMinimoDesconto, // Percentual Desconto Minimo
				item.percentualEconomicoBeneficio, // PERECOBFC - *** Percentual Economico Beneficio
				item.preco.percentualICMS, // Percentual de ICM da Mercadoria
				item.desconto.percentualDescontoFlexivel, // Percentual Maximo Flexivel
				item.percentualDescontoSimplificado, // Percentual Maximo Simplificado
				item.stb.percentualValorMinimo, // Percentual Valor Minimo
				item.mercadoria.quantidadeCaixaFornecedor, // Quantidade Caixa Fornecedor
				item.quantidadeMercadoria, // Quantidade da Mercadoria no Pedido
				item.quantidadePrazoBeneficiario, // *** Quantidade Prazo Beneficio
				item.indicaRestricaoBeneficioCustomizadoMercadoria, // Restricao de Beneficio de Mercadoria
				item.stb.valorSTBComplementar, // *** STB Complemento Total
				item.stb.valorSTBTotalBonificacao, // STB Total Bonificacao
				item.preco.tipoIncentivoMercadoria, // Tipo de Incentivo
				item.preco.tipoMarcacaoRepresentante, // Tipo Marcacao Representante (Listas Customizadas)
				item.desconto.tipo, // Tipo Desconto Mercadoria
				item.mercadoria.tipoEsgotamento, // Tipo de Esgotamento da Mercadoria
				item.tipoNegociacaoMercadoria, // Tipo Negociacao Mercadoria
				item.promocao.tipo, // Tipo Promocao
				item.valorUnitarioCaixaComImposto, // Valor Unitario da Caixa com Imposto
				item.valorBonificacao, // Valor Bonificacao Mercadoria
				item.preco.valorBrutoCaixa, // Valor Bruto Caixa
				item.preco.valorBrutoFracionado, // Valor Bruto Fracionado
				item.preco.valorBrutoMercadoria, // Valor Bruto Mercadoria
				item.preco.valorBrutoTMP, // Valor Bruto Temporario
				item.desconto.valorDescontoEspecial, // Valor Desconto Especial
				item.mercadoria.valorFrete, // Valor do Frete da Mercadoria
				item.mercadoria.percentualIPI.percentualIPI, // Valor IPI Mercadoria
				item.valorIPITotal, // Valor IPI Total Mercadoria
				item.valorLiquidoCaixa, // Valor Liquido Caixa
				item.valorLiquidoComImposto, // Valor Liquido da Mercadoria com Imposto
				item.valorLiquidoMercadoria, // Valor Liquido Mercadoria
				item.mercadoria.valorMaximoMercadoria, // Valor Maximo Isencao
				item.mercadoria.valorMinimoMercadoria, // Valor Minimo Mercadoria
				item.valorPontoBeneficioMercadoria, // Valor Pontos de Beneficio
				item.valorPontuacaoMercadoria, // Valor Pontos
				item.valorReducao, // Valor Reducao Item
				item.stb.valorSTBComplementarTotal,// *** Valor STB Complemento
				item.stb.valorSTBUnitario, // Valor STB (Substituicao Tributaria) Mercadoria
				item.stb.valorSTBTotal, // Valor STB Total
				item.mercadoria.valorFrete, // *** Valor Total Frete
				item.calculaValorLiquidoTotal(), // *** Valor Total Liquido
				item.valorUnitarioComImposto // Valor Unitario com Imposto
				);
		return query;
	}
	
	private static StringBuilder obtemQuerySelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" CMSCNSMER	as comissaoConcessaoMercadoria, "); // Comissao Concessao Mercadoria
		sql.append(" CMSMAXMER	as comissaoMaximaMercadoria, "); // Maximo Comissao Mercadoria
		sql.append(" CODCNDPGT	as codigoCondicaoPagamento, "); // Codigo Condicao de Pagamento
		sql.append(" CODCORMRG	as codigoCorMargem, "); // Codigo Cor de Margem
		sql.append(" CODCORMRGB	as codigoCorMargemB, "); // Codigo Cor de Margem B
		sql.append(" CODFILEPD	as codigoFilialExpedicao, "); // Codigo Filial Expedicao
		sql.append(" CODFILFAT	as codigoFilialFaturamento, "); // Codigo de Filial de Faturamento
		sql.append(" CODFLXPCO	as codigoFlexivelPreco, "); // Codigo Flexivel Preco
		sql.append(" CODMER		as codigoMercadoria, "); // Codigo da Mercadoria
		sql.append(" CODMERDIG	as digito, ");// *** Codigo Mercadoria Digito
		sql.append(" CODMERPCP	as codigoMercadoriaPrincipal, "); // Codigo Mercadoria Principal
		sql.append(" CODPMC		as codigoPromocao, "); // Codigo Promocao
		sql.append(" CODRGRDTB	as codigoRegra, "); // Codigo Regra Distribuicao
		sql.append(" CODSMBSIT	as codigoSimboloSituacao, "); // Codigo Simbolo Situacao
		sql.append(" DESMER		as descricaoMercadoria, "); // Descricao da Mercadoria
		sql.append(" DESNGCMER	as descricaoNegocioMercadoria, "); // Descricao Negociacao Mercadoria
		sql.append(" FLGPEE		as flagPee, "); // Flag PEE
		sql.append(" FLGSTSITE	as flagSimboloSituacao, "); // *** Flag Situacao Item
		sql.append(" INDITEIMU	as indicaItemImune, "); // Indica Item Imune
		sql.append(" INDITEVND	as indicaItemVenda, "); // Indica Item de Venda
		sql.append(" INDMERKIT	as indicaMercadoriaKit, "); // Indica Mercadoria Kit
		sql.append(" INDNVO		as indicaItemNovo, "); // Indica Novo
		sql.append(" INDRTCBFB	as indicaRestricaoBeneficioCustomizadoBrinde, "); // Indica Restricao Beneficio Customizado no Brinde
		sql.append(" INDRTCBFV	as indicaRestricaoBeneficioCustomizado, "); // Indica Restricao Beneficio Customizado
		sql.append(" INDRTCBNF	as indicaRestricaoItemBrinde, "); // Indica Restricao de Item como Brinde
		sql.append(" INDSEMMRGL	as indicativoSemMargemLucro, "); // Indica Sem Margem de Lucro
		sql.append(" IPITOTBNF	as valorIPITotalBonificacao, "); // Valor IPI Total Bonificacao
		sql.append(" LIQCXAIMP	as valorCaixaComImposto, "); // Valor Liquido Caixa com Imposto
		sql.append(" NUMNOTFSC	as notaFiscal, "); // Numero da Nota Fiscal
		sql.append(" NUMSEQITE	as numeroSequenciaItem, "); // *** Numero Sequencia Item
		sql.append(" PERACOCLI	as percentualAcaoCliente, "); // Percentual de Acao do Cliente
		sql.append(" PERACOTTC	as percentualDescontoAcao, "); // Percentual de Acao Tatica
		sql.append(" PERACRCNS	as percentualAcrescimoConcedido, "); // Percentual Acrescimo Concedido
		sql.append(" PERACRVTL	as percentualAcrescimoVirtual, "); // Percentual de Acrescimo Virtual
		sql.append(" PERCMSADI	as percentualComissao, "); // *** Percentual Comissao Adicional
		sql.append(" PERDSCBFC	as percentualDescontoBeneficio, "); // *** Percentual Desconto Beneficio
		sql.append(" PERDSCFLX	as percentualDescontoFlex, "); // Percentual Desconto Flexivel
		sql.append(" PERDSCMNM	as percentualMinimoDesconto, "); // Percentual Desconto Minimo
		sql.append(" PERECOBFC	as percentualEconomicoBeneficio, "); // *** Percentual Economico Beneficio
		sql.append(" PERICMMER	as percentualICMS, "); // Percentual de ICM da Mercadoria
		sql.append(" PERMAXFLX	as percentualDescontoFlexivel, "); // Percentual Maximo Flexivel
		sql.append(" PERMAXSMP	as percentualDescontoSimplificado, "); // Percentual Maximo Simplificado
		sql.append(" PERVLRMNM	as percentualValorMinimo, "); // Percentual Valor Minimo
		sql.append(" QDECXAFRN	as quantidadeCaixaFornecedor, "); // Quantidade Caixa Fornecedor
		sql.append(" QDEMERPED	as qtdMercadoriaPedida, "); // Quantidade da Mercadoria no Pedido
		sql.append(" QDEPRZBFC	as quantidadePrazoBeneficiario, "); // *** Quantidade Prazo Beneficio
		sql.append(" RTCBFVMER	as indicaRestricaoBeneficioCustomizadoMercadoria, "); // Restricao de Beneficio de Mercadoria
		sql.append(" STBCPLTOT	as valorSTBComplementarTotal, "); // *** STB Complementar Total
		sql.append(" STBTOTBNF	as valorSTBTotalBonificacao, "); // STB Total Bonificacao
		sql.append(" TIPICT		as tipoIncentivoMercadoria, "); // Tipo de Incentivo
		sql.append(" TIPMCOREP	as tipoMarcacaoRepresentante, "); // Tipo Marcacao Representante (Listas Customizadas)
		sql.append(" TIPMERDSC	as tipoDesconto, "); // Tipo Desconto Mercadoria
		sql.append(" TIPMERESG	as tipoEsgotamento, "); // Tipo de Esgotamento da Mercadoria
		sql.append(" TIPNGCMER	as tipoNegociacaoMercadoria, "); // Tipo Negociacao Mercadoria
		sql.append(" TIPPMC		as tipoPromocao, "); // Tipo Promocao
		sql.append(" UNTCXAIMP	as valorUnitarioCaixaComImposto, "); // Valor Unitario da Caixa com Imposto
		sql.append(" VLRBNFMER	as valorBonificacao, "); // Valor Bonificacao Mercadoria
		sql.append(" VLRBRTCXA	as valorBrutoCaixa, "); // Valor Bruto Caixa
		sql.append(" VLRBRTFRC	as valorBrutoFracionado, "); // Valor Bruto Fracionado
		sql.append(" VLRBRTMER	as valorBrutoMercadoria, "); // Valor Bruto Mercadoria
		sql.append(" VLRBRTTMP	as valorBrutoTMP, "); // Valor Bruto Temporario
		sql.append(" VLRDSCESP	as valorDescontoEspecial, "); // Valor Desconto Especial
		sql.append(" VLRFRTMER	as valorFrete, "); // Valor do Frete da Mercadoria
		sql.append(" VLRIPIMER	as percentualIPI, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT	as valorIPITotal, "); // Valor IPI Total Mercadoria
		sql.append(" VLRLIQCXA	as valorLiquidoCaixa, "); // Valor Liquido Caixa
		sql.append(" VLRLIQIMP	as valorLiquidoComImposto, "); // Valor Liquido da Mercadoria com Imposto
		sql.append(" VLRLIQMER	as valorLiquidoMercadoria, "); // Valor Liquido Mercadoria
		sql.append(" VLRMAXISN	as valorMaximoMercadoria, "); // Valor Maximo Isencao
		sql.append(" VLRMNMMER	as valorMinimoMercadoria, "); // Valor Minimo Mercadoria
		sql.append(" VLRPTOBFC	as valorPontosBeneficio, "); // Valor Pontos de Beneficio
		sql.append(" VLRPTOMER	as valorPontos, "); // Valor Pontos
		sql.append(" VLRRDCITE	as valorReducao, "); // Valor Reducao Item
		sql.append(" VLRSTBCPL	as valorSTBComplementar, "); // *** Valor STB Complemento
		sql.append(" VLRSTBMER	as valorSTBUnitario, "); // Valor STB (Substituicao Tributaria) Mercadoria
		sql.append(" VLRSTBTOT	as valorSTBTotal, "); // Valor STB Total
		sql.append(" VLRTOTFRT	as valorTotalFrete, "); // *** Valor Total Frete
		sql.append(" VLRTOTLIQ	as valorLiquidoTotal, "); // *** Valor Liquido Total 
		sql.append(" VLRUNTIMP	as valorUnitarioComImposto "); // Valor Unitario com Imposto
		sql.append(" from TMPITEPE ");
		return sql;
	}
	
	private static List<Item> obtemListaItens(List<Map<String, String>> itens) {
		List<Item> listaItens = new ArrayList<Item>();
		try {
			for (Map<String, String> itemAux : itens) {
				Item item = new Item();
				item.mercadoria = new Mercadoria();
				
				item.condicaoPagamento = new CondicaoPagamento();
				item.mercadoria.percentualIPI = new PercentualIPI();
				
				item.preco = new Preco();
				item.stb = new Imposto();
				item.desconto = new Desconto();
				item.promocao = new Promocao();
				item.regraDistribuicao = new RegraDistribuicao();
				item.beneficio = new Beneficio();
				item.comissao = new Comissao();
				
				
				item.comissao.comissaoMercadoria= Util.getBigDecimal(itemAux.get("comissaoConcessaoMercadoria")); // Comissao Concessao Mercadoria
				item.comissao.comissaoMaximaMercadoria = Util.getBigDecimal(itemAux.get("comissaoMaximaMercadoria")); // Maximo Comissao Mercadoria
				item.condicaoPagamento.codigoCondicaoPagamento = Util.getInteger(itemAux.get("codigoCondicaoPagamento")); // Codigo Condicao de Pagamento
				item.codigoCorMargem = Util.getInteger(itemAux.get("codigoCorMargem")); // Codigo Cor de Margem
				item.codigoCorMargemB = Util.getInteger(itemAux.get("codigoCorMargemB")); // Codigo Cor de Margem B
				item.codigoFilialExpedicao = Util.getInteger(itemAux.get("codigoFilialExpedicao")); // Codigo Filial Expedicao
				item.codigoFilialFaturamento = Util.getInteger(itemAux.get("codigoFilialFaturamento")); // Codigo de Filial de Faturamento
				item.preco.codigoFlexivelPreco = itemAux.get("codigoFlexivelPreco"); // Codigo Flexivel Preco
				item.mercadoria.codigo = Util.getInteger(itemAux.get("codigoMercadoria")); // Codigo da Mercadoria
				item.mercadoria.digito = Util.getInteger(itemAux.get("digito")); // *** Codigo Mercadoria Digito
				item.mercadoria.codigoMercadoriaPrincipal = Util.getInteger(itemAux.get("codigoMercadoriaPrincipal")); // Codigo Mercadoria Principal
				item.promocao.codigo = Util.getInteger(itemAux.get("codigoPromocao")); // Codigo Promocao
				item.regraDistribuicao.codigoRegra = Util.getInteger(itemAux.get("codigoRegra")); // Codigo Regra Distribuicao
				item.preco.codigoSimboloSituacao = itemAux.get("codigoSimboloSituacao"); // Codigo Simbolo Situacao
				item.mercadoria.descricao = itemAux.get("descricaoMercadoria"); // Descricao da Mercadoria
				item.mercadoria.descricaoNegocioMercadoria = itemAux.get("descricaoNegocioMercadoria"); // Descricao Negociacao Mercadoria
				item.mercadoria.flagPee = itemAux.get("flagPee"); // Flag PEE
				item.preco.flagSimboloSituacao = Util.getInteger(itemAux.get("flagSimboloSituacao")); // *** Flag Situacao Item
				item.indicaItemImune = Util.getInteger(itemAux.get("indicaItemImune")); // Indica Item Imune
				item.indicaItemVenda = Util.getInteger(itemAux.get("indicaItemVenda")); // Indica Item de Venda
				item.mercadoria.indicaMercadoriaKit = Util.getInteger(itemAux.get("indicaMercadoriaKit")); // Indica Mercadoria Kit
				item.indicaItemNovo = itemAux.get("indicaItemNovo"); // Indica Novo
				item.indicaRestricaoBeneficioCustomizadoBrinde = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizadoBrinde")); // Indica Restricao Beneficio Customizado no Brinde
				item.indicaRestricaoBeneficioCustomizado = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizado")); // Indica Restricao Beneficio Customizado
				item.indicaRestricaoItemBrinde = Util.getInteger(itemAux.get("indicaRestricaoItemBrinde")); // Indica Restricao de Item como Brinde
				item.indicativoSemMargemLucro = Util.getInteger(itemAux.get("indicativoSemMargemLucro")); // Indica Sem Margem de Lucro
				item.valorIPITotalBonificacao = Util.getBigDecimal(itemAux.get("valorIPITotalBonificacao")); // Valor IPI Total Bonificacao
				item.valorCaixaComImposto = Util.getBigDecimal(itemAux.get("valorCaixaComImposto")); // Valor Liquido Caixa com Imposto
				item.notaFiscal = Util.getInteger(itemAux.get("notaFiscal")); // Numero da Nota Fiscal
				item.numeroSequenciaItem = Util.getInteger(itemAux.get("numeroSequenciaItem")); // *** Numero Sequencia Item
				item.comissao.percentualAcaoCliente = Util.getBigDecimal(itemAux.get("percentualAcaoCliente")); // Percentual de Acao do Cliente
				item.percentualDescontoAcaoTatica = Util.getBigDecimal(itemAux.get("percentualDescontoAcaoTatica")); // Percentual de Acao Tatica
				item.percentualAcrescimoConcedido = Util.getBigDecimal(itemAux.get("percentualAcrescimoConcedido")); // Percentual Acrescimo Concedido
				item.percentualAcrescimoVirtual = Util.getBigDecimal(itemAux.get("percentualAcrescimoVirtual")); // Percentual de Acrescimo Virtual
				item.beneficio.percentualComissao = Util.getBigDecimal(itemAux.get("percentualComissao")); // *** Percentual Comissao Adicional
				item.percentualDescontoBeneficio = Util.getBigDecimal(itemAux.get("percentualDescontoBeneficio")); // *** Percentual Desconto Beneficio
				item.percentualDescontoFlex = Util.getBigDecimal(itemAux.get("percentualDescontoFlex")); // Percentual Desconto Flexivel
				item.desconto.percentualMinimoDesconto = Util.getBigDecimal(itemAux.get("percentualMinimoDesconto")); // Percentual Desconto Minimo
				item.percentualEconomicoBeneficio = Util.getBigDecimal(itemAux.get("percentualEconomicoBeneficio")); // *** Percentual Economico Beneficio
				item.preco.percentualICMS = Util.getBigDecimal(itemAux.get("percentualICMS")); // Percentual de ICM da Mercadoria
				item.desconto.percentualDescontoFlexivel = Util.getBigDecimal(itemAux.get("percentualDescontoFlexivel")); // Percentual Maximo Flexivel
				item.percentualDescontoSimplificado = Util.getBigDecimal(itemAux.get("percentualDescontoSimplificado"));
				item.stb.percentualValorMinimo = Util.getBigDecimal(itemAux.get("percentualValorMinimo"));
				item.mercadoria.quantidadeCaixaFornecedor = Util.getInteger(itemAux.get("quantidadeCaixaFornecedor"));
				item.quantidadeMercadoria = Util.getInteger(itemAux.get("qtdMercadoriaPedida")); // Quantidade da Mercadoria no Pedido
				item.quantidadePrazoBeneficiario = Util.getInteger(itemAux.get("quantidadePrazoBeneficiario")); // *** Quantidade Prazo Beneficio
				item.indicaRestricaoBeneficioCustomizadoMercadoria = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizadoMercadoria")); // Restricao de Beneficio de Mercadoria
				item.stb.valorSTBComplementarTotal = Util.getBigDecimal(itemAux.get("valorSTBComplementarTotal")); // *** STB Complementar Total
				item.stb.valorSTBTotalBonificacao = Util.getBigDecimal(itemAux.get("stbTotalBonificacao")); // STB Total Bonificacao
				item.preco.tipoIncentivoMercadoria = itemAux.get("tipoIncentivoMercadoria"); // Tipo de Incentivo
				item.preco.tipoMarcacaoRepresentante = itemAux.get("tipoMarcacaoRepresentante"); // Tipo Marcacao Representante (Listas Customizadas)
				item.desconto.tipo = itemAux.get("tipoDesconto"); // Tipo Desconto Mercadoria
				item.mercadoria.tipoEsgotamento = itemAux.get("tipoEsgotamento"); // Tipo de Esgotamento da Mercadoria
				item.tipoNegociacaoMercadoria = Util.getInteger(itemAux.get("tipoNegociacaoMercadoria")); // Tipo Negociacao Mercadoria
				item.promocao.tipo = itemAux.get("tipoPromocao"); // Tipo Promocao
				item.valorUnitarioCaixaComImposto = Util.getBigDecimal(itemAux.get("valorUnitarioCaixaComImposto")); // Valor Unitario da Caixa com Imposto
				item.valorBonificacao = Util.getBigDecimal(itemAux.get("valorBonificacao")); // Valor Bonificacao Mercadoria
				item.preco.valorBrutoCaixa = Util.getBigDecimal(itemAux.get("valorBrutoCaixa")); // Valor Bruto Caixa
				item.preco.valorBrutoFracionado = Util.getBigDecimal(itemAux.get("valorBrutoFracionado")); // Valor Bruto Fracionado
				
				item.preco.valorBrutoMercadoria = Util.getBigDecimal(itemAux.get("valorBrutoMercadoria")); // Valor Bruto Mercadoria
				
				item.preco.valorBrutoTMP = Util.getBigDecimal(itemAux.get("valorBrutoTMP")); // Valor Bruto Temporario
				item.desconto.valorDescontoEspecial = Util.getBigDecimal(itemAux.get("valorDescontoEspecial")); // Valor Desconto Especial
				item.mercadoria.valorFrete = Util.getBigDecimal(itemAux.get("valorFrete")); // Valor do Frete da Mercadoria
				item.mercadoria.percentualIPI.percentualIPI = Util.getBigDecimal(itemAux.get("percentualIPI")); // Valor IPI Mercadoria
				item.valorIPITotal = Util.getBigDecimal(itemAux.get("valorIPITotal")); // Valor IPI Total Mercadoria
				item.valorLiquidoCaixa = Util.getBigDecimal(itemAux.get("valorLiquidoCaixa")); // Valor Liquido Caixa
				item.valorLiquidoComImposto = Util.getBigDecimal(itemAux.get("valorLiquidoComImposto")); // Valor Liquido da Mercadoria com Imposto
				item.valorLiquidoMercadoria = Util.getBigDecimal(itemAux.get("valorLiquidoMercadoria")); // Valor Liquido Mercadoria
				item.mercadoria.valorMaximoMercadoria = Util.getBigDecimal(itemAux.get("valorMaximoMercadoria")); // Valor Maximo Isencao
				item.mercadoria.valorMinimoMercadoria = Util.getBigDecimal(itemAux.get("valorMinimoMercadoria")); // Valor Minimo Mercadoria
				item.valorPontoBeneficioMercadoria = Util.getInteger(itemAux.get("valorPontosBeneficio")); // Valor Pontos de Beneficio
				item.valorPontuacaoMercadoria = Util.getInteger(itemAux.get("valorPontos")); // Valor Pontos
				item.valorReducao = Util.getInteger(itemAux.get("valorReducao")); // Valor Reducao Item
				item.stb.valorSTBComplementar = Util.getBigDecimal(itemAux.get("valorSTBComplementar")); // *** Valor STB Complemento
				item.stb.valorSTBUnitario = Util.getBigDecimal(itemAux.get("valorSTBUnitario")); // Valor STB (Substituicao Tributaria) Mercadoria
				item.stb.valorSTBTotal = Util.getBigDecimal(itemAux.get("valorSTBTotal")); // Valor STB Total
				item.frete = Util.getBigDecimal(itemAux.get("valorTotalFrete")); // *** Valor Total Frete
				item.valorLiquidoTotal = Util.getBigDecimal(itemAux.get("valorLiquidoTotal")); // *** Valor Liquido Total 
				item.valorUnitarioComImposto = Util.getBigDecimal(itemAux.get("valorUnitarioComImposto")); // Valor Unitario com Imposto
				
				// Inclui o Item na lista de Itens
				listaItens.add(item);
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage());
		}

		return listaItens;
	}

}
