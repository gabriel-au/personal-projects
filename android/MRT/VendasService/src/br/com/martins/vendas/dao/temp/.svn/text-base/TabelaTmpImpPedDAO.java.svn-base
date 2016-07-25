package br.com.martins.vendas.dao.temp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Imposto;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PercentualICMS;
import br.com.martins.vendas.vo.PercentualIPI;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class TabelaTmpImpPedDAO {

	private static Database db;

	/**
	 * Método responsável por remover todos os dados da tabela TMPIMPPED.
	 * 
	 * @return <code>true</code> se os dados da tabela foram removidos com
	 *         sucesso, e <code>false</code> caso tenha ocorrido algum problema
	 *         que tenha impedido a remoção dos dados da tabela.
	 */
	public static boolean limpaTabela() {
		String sql = " delete from TMPIMPPED ";
		boolean resultado = false;

		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(sql);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpImpPedDAO - limpaTabela - ", e.getMessage());
		}

		return resultado;
	}

	public static boolean insereItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into TMPIMPPED ( ");
		sql.append(" CODCNDPGT, "); // Codigo Condicao Pagamento
		sql.append(" CODFILEPD, "); // Codigo Filial Expedicao
		sql.append(" CODFILFAT, "); // Codigo Filial Faturamento
		sql.append(" CODMERDIG, "); // Digito Codigo Mercadoria
		sql.append(" DESMER, "); // Descricao Mercadoria
		sql.append(" FTRCNVUNT, "); // Fator Conversao Unitario
		sql.append(" PERDSCBFC, "); // Percentual Desconto Beneficiario
		sql.append(" PERICMMER, "); // Percentual ICM Mercadoria
		sql.append(" QDEMERPED, "); // Quantidade Mercadoria Pedido
		sql.append(" STBCPLTOT, "); // STB Complementar Total
		sql.append(" TIPNGCMER, "); // Tipo Negocio Mercadoria
		sql.append(" VLRFRTMER, "); // Valor Frete Mercadoria
		sql.append(" VLRIPIMER, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT, "); // Valor IPI Total
		sql.append(" VLRLIQMER, "); // Valor Liquido Mercadoria
		sql.append(" VLRSTBCPL, "); // Valor STB Complementar
		sql.append(" VLRSTBMER, "); // Valor STB Mercadoria
		sql.append(" VLRSTBTOT "); // Valor STB Total
		sql.append(" ) values ( ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s ");
		sql.append(" ); ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		String query = montaQuery(item, sql);

		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpImpPedDAO - insereItem - ", e.getMessage());
		}

		return resultado;
	}

	public static boolean insereItem(Item item, TransactionManager transactionManager) {
		Map<String, String> values = new HashMap<String, String>();

		// Codigo Condicao Pagamento
		values.put("CODCNDPGT", Util.getStringValue(item.condicaoPagamento.codigoCondicaoPagamento));

		// Codigo Filial Expedicao
		values.put("CODFILEPD", Util.getStringValue(item.codigoFilialExpedicao));

		// Codigo Filial Faturamento
		values.put("CODFILFAT", Util.getStringValue(item.codigoFilialFaturamento));

		// Digito Codigo Mercadoria
		values.put("CODMERDIG", Util.getStringValue(item.mercadoria.digito));

		// Descricao Mercadoria
		values.put("DESMER", item.mercadoria.descricao);

		// Fator Conversao Unitario
		values.put("FTRCNVUNT", Util.getStringValue(item.mercadoria.fatorConversaoUnitario));

		// Percentual Desconto Beneficiario
		values.put("PERDSCBFC", Util.getStringValue(item.percentualDescontoBeneficio));

		// Percentual ICM Mercadoria
		values.put("PERICMMER", Util.getStringValue(item.mercadoria.percentualICMS.percentualICMSMercadoria));

		// Quantidade Mercadoria Pedido
		values.put("QDEMERPED", Util.getStringValue(item.quantidadeMercadoria));

		// STB Complementar Total
		values.put("STBCPLTOT", Util.getStringValue(item.stb.valorSTBComplementarTotal));

		// Tipo Negocio Mercadoria
		values.put("TIPNGCMER", Util.getStringValue(item.tipoNegociacaoMercadoria));

		// Valor Frete Mercadoria
		values.put("VLRFRTMER", Util.getStringValue(item.mercadoria.valorFrete));

		// Valor IPI Mercadoria
		values.put("VLRIPIMER", Util.getStringValue(item.mercadoria.percentualIPI.percentualIPI));

		// Valor IPI Total
		values.put("VLRIPITOT", Util.getStringValue(item.valorIPITotal));

		// Valor Liquido Mercadoria
		values.put("VLRLIQMER", Util.getStringValue(item.valorLiquidoMercadoria));

		// Valor STB Complementar
		values.put("VLRSTBCPL", Util.getStringValue(item.stb.valorSTBComplementar));

		// Valor STB Mercadoria
		values.put("VLRSTBMER", Util.getStringValue(item.stb.valorSTBUnitario));

		// Valor STB Total
		values.put("VLRSTBTOT", Util.getStringValue(item.stb.valorSTBTotal));

		try {
			transactionManager.insert("TMPIMPPED", values);
		} catch (SQLException e) {
			Log.e("TabelaTmpImpPedDAO - insereItem - ", e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return true;
	}

	public static boolean alteraItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE TMPIMPPED SET ");
		sql.append(" CODCNDPGT	= %s, "); // Codigo Condicao Pagamento
		sql.append(" CODFILEPD	= %s, "); // Codigo Filial Expedicao
		sql.append(" CODFILFAT	= %s, "); // Codigo Filial Faturamento
		sql.append(" CODMERDIG	= %s, "); // Digito Codigo Mercadoria
		sql.append(" DESMER		= %s, "); // Descricao Mercadoria
		sql.append(" FTRCNVUNT	= %s, "); // Fator Conversao Unitario
		sql.append(" PERDSCBFC	= %s, "); // Percentual Desconto Beneficiario
		sql.append(" PERICMMER	= %s, "); // Percentual ICM Mercadoria
		sql.append(" QDEMERPED	= %s, "); // Quantidade Mercadoria Pedido
		sql.append(" STBCPLTOT	= %s, "); // STB Complementar Total
		sql.append(" TIPNGCMER	= %s, "); // Tipo Negocio Mercadoria
		sql.append(" VLRFRTMER	= %s, "); // Valor Frete Mercadoria
		sql.append(" VLRIPIMER	= %s, "); // Valor IPI Mercadoria
		sql.append(" VLRIPITOT	= %s, "); // Valor IPI Total
		sql.append(" VLRLIQMER	= %s, "); // Valor Liquido Mercadoria
		sql.append(" VLRSTBCPL	= %s, "); // Valor STB Complementar
		sql.append(" VLRSTBMER	= %s, "); // Valor STB Mercadoria
		sql.append(" VLRSTBTOT	= %s "); // Valor STB Total
		sql.append(" WHERE UPPER(DESMER) = '").append(item.mercadoria.descricao.trim().toUpperCase()).append("'");

		boolean resultado = false;
		String query = montaQuery(item, sql);
		db = DatabaseFactory.getInstance();
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpImpPedDAO - alteraItem - ", e.getMessage());
		}

		return resultado;
	}

	public static Item obtemItem(String descricaoMercadoria) {
		StringBuilder sql = obtemQuerySelect();
		sql.append(" where UPPER(DESMER) = '%s' ");

		String query = DatabaseUtil.montaQuery(sql, descricaoMercadoria.trim().toUpperCase());
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpImpPedDAO - obtemItem - ", e.getMessage());
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
			Log.e("TabelaTmpImpPedDAO - obtemTodosItens - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}

	public static boolean excluiItem(String descricaoMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from TMPIMPPED where UPPER(DESMER) = '%s' ");

		String query = DatabaseUtil.montaQuery(sql, descricaoMercadoria.trim().toUpperCase());
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpImpPedDAO - excluiItem - ", e.getMessage());
		}

		return resultado;
	}
	
	private static StringBuilder obtemQuerySelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" CODCNDPGT	as codigoCondicaoPagamento, "); // CODCNDPGT - Codigo Condicao Pagamento
		sql.append(" CODFILEPD	as codigoFilialExpedicao, "); // CODFILEPD - Codigo Filial Expedicao
		sql.append(" CODFILFAT	as codigoFilialFaturamento, "); // CODFILFAT - Codigo Filial Faturamento
		sql.append(" DESMER		as descricaoMercadoria, "); // DESMER - Descricao Mercadoria
		sql.append(" FTRCNVUNT	as fatorConversaoUnitario, "); // FTRCNVUNT - Fator Conversao Unitario
		sql.append(" PERDSCBFC	as percentualDescontoBeneficio, "); // PERDSCBFC - Percentual Desconto Beneficiario
		sql.append(" PERICMMER	as percentualICMS, "); // PERICMMER - Percentual ICM Mercadoria
		sql.append(" QDEMERPED	as quantidadeMercadoria, "); // QDEMERPED - Quantidade Mercadoria Pedido
		sql.append(" STBCPLTOT	as valorSTBComplementarTotal, "); // STBCPLTOT - STB Complementar Total
		sql.append(" TIPNGCMER	as tipoNegociacaoMercadoria, "); // TIPNGCMER - Tipo Negocio Mercadoria
		sql.append(" VLRFRTMER	as valorFrete, "); // VLRFRTMER - Valor Frete Mercadoria
		sql.append(" VLRIPIMER	as percentualIPI, "); // VLRIPIMER - Valor IPI Mercadoria
		sql.append(" VLRIPITOT	as valorIPITotal, "); // VLRIPITOT - Valor IPI Total
		sql.append(" VLRLIQMER	as valorLiquidoMercadoria, "); // VLRLIQMER - Valor Liquido Mercadoria
		sql.append(" VLRSTBCPL	as valorSTBComplementar, "); // VLRSTBCPL - Valor STB Complementar
		sql.append(" VLRSTBMER	as valorSTBUnitario, "); // VLRSTBMER - Valor STB Mercadoria
		sql.append(" VLRSTBTOT	as valorSTBTotal "); // VLRSTBTOT - Valor STB Total
		sql.append(" from TMPIMPPED ");
		
		return sql;
	}
	
	private static String montaQuery(Item item, StringBuilder sql) {
		String query = DatabaseUtil.montaQueryGenerica(sql, //
				
				// CODCNDPGT - Codigo Condicao Pagamento
				item.condicaoPagamento.codigoCondicaoPagamento,
				
				// CODFILEPD - Codigo Filial Expedicao
				item.codigoFilialExpedicao, 
				
				// CODFILFAT - Codigo Filial Faturamento
				item.codigoFilialFaturamento,

				// CODMERDIG - Digito Codigo Mercadoria
				item.mercadoria.digito,

				// DESMER - Descricao Mercadoria
				item.mercadoria.descricao,
				
				// FTRCNVUNT - Fator Conversao Unitario
				item.mercadoria.fatorConversaoUnitario,
				
				// PERDSCBFC - Percentual Desconto Beneficiario
				item.percentualDescontoBeneficio,

				// PERICMMER - Percentual ICM Mercadoria
				item.mercadoria.percentualICMS.percentualICMSMercadoria,
				
				// QDEMERPED - Quantidade Mercadoria Pedido
				item.quantidadeMercadoria,
				
				// STBCPLTOT - STB Complementar Total
				item.stb.valorSTBComplementarTotal,
				
				// TIPNGCMER - Tipo Negocio Mercadoria
				item.tipoNegociacaoMercadoria,
				
				// VLRFRTMER - Valor Frete Mercadoria
				item.mercadoria.valorFrete,
				
				// VLRIPIMER - Valor IPI Mercadoria
				item.mercadoria.percentualIPI.percentualIPI,

				// VLRIPITOT - Valor IPI Total
				item.valorIPITotal,
				
				// VLRLIQMER - Valor Liquido Mercadoria
				item.valorLiquidoMercadoria,

				// VLRSTBCPL - Valor STB Complementar
				item.stb.valorSTBComplementar,
				
				// VLRSTBMER - Valor STB Mercadoria
				item.stb.valorSTBUnitario,
				
				// VLRSTBTOT - Valor STB Total
				item.stb.valorSTBTotal
				);
		return query;
	}

	private static List<Item> obtemListaItens(List<Map<String, String>> itens) {
		List<Item> listaItens = new ArrayList<Item>();

		for (Map<String, String> itemAux : itens) {
			Item item = new Item();

			item.mercadoria = new Mercadoria();
			item.mercadoria.percentualICMS = new PercentualICMS();
			item.mercadoria.percentualIPI = new PercentualIPI();
			item.stb = new Imposto();
			item.condicaoPagamento = new CondicaoPagamento();
			
			// Codigo Condicao Pagamento
			item.condicaoPagamento.codigoCondicaoPagamento = Util.getInteger(itemAux.get("codigoCondicaoPagamento"));

			// Codigo Filial Expedicao
			item.codigoFilialExpedicao = Util.getInteger(itemAux.get("codigoFilialExpedicao"));

			// Codigo Filial Faturamento
			item.codigoFilialFaturamento = Util.getInteger(itemAux.get("codigoFilialFaturamento"));

			// Descricao Mercadoria
			item.mercadoria.descricao = itemAux.get("descricaoMercadoria");

			// Fator Conversao Unitario
			item.mercadoria.fatorConversaoUnitario = Util.getInteger(itemAux.get("fatorConversaoUnitario"));

			// Percentual Desconto Beneficiario
			item.percentualDescontoBeneficio = Util.getBigDecimal(itemAux.get("percentualDescontoBeneficio"));

			// Percentual ICM Mercadoria
			item.mercadoria.percentualICMS.percentualICMSMercadoria = Util.getBigDecimal(itemAux.get("percentualICMSMercadoria"));

			// Quantidade Mercadoria Pedido
			item.quantidadeMercadoria = Util.getInteger(itemAux.get("quantidadeMercadoria"));

			// STB Complementar Total
			item.stb.valorSTBComplementarTotal = Util.getBigDecimal(itemAux.get("valorSTBComplementarTotal"));

			// Tipo Negocio Mercadoria
			item.tipoNegociacaoMercadoria = Util.getInteger(itemAux.get("tipoNegociacaoMercadoria"));

			// Valor Frete Mercadoria
			item.mercadoria.valorFrete = Util.getBigDecimal(itemAux.get("valorFrete"));
			item.frete = Util.getBigDecimal(itemAux.get("valorFrete"));

			// Valor IPI Mercadoria
			item.mercadoria.percentualIPI.percentualIPI = Util.getBigDecimal(itemAux.get("percentualIPI"));

			// Valor IPI Total
			item.valorIPITotal = Util.getBigDecimal(itemAux.get("valorIPITotal"));

			// Valor Liquido Mercadoria
			item.valorLiquidoMercadoria = Util.getBigDecimal(itemAux.get("valorLiquidoMercadoria"));

			// Valor STB Complementar
			item.stb.valorSTBComplementar = Util.getBigDecimal(itemAux.get("valorSTBComplementar"));

			// Valor STB Mercadoria
			item.stb.valorSTBUnitario = Util.getBigDecimal(itemAux.get("valorSTBUnitario"));

			// Valor STB Total
			item.stb.valorSTBTotal = Util.getBigDecimal(itemAux.get("valorSTBTotal"));

			listaItens.add(item);
		}

		return listaItens;
	}
	
}
