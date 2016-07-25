package br.com.martins.vendas.dao.temp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Imposto;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.services.desconto.Desconto;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Pedido;
import br.com.martins.vendas.vo.PercentualIPI;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class TabelaTmpMerRecDAO {

	private static Database db;

	/**
	 * Método responsável por remover todos os dados da tabela TMPMERREC.
	 * 
	 * @return <code>true</code> se os dados da tabela foram removidos com
	 *         sucesso, e <code>false</code> caso tenha ocorrido algum problema
	 *         que tenha impedido a remoção dos dados da tabela.
	 */
	public static boolean limpaTabela() {
		String sql = " delete from TMPMERREC ";
		boolean resultado = false;

		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(sql);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpMerRecDAO - limpaTabela - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean insereItens(Pedido pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO TMPMERREC ( ");
		sql.append("	CODMER, "); // Codigo da Mercadoria   
		sql.append("	DESCPLMER, "); // Descricao Complementar Mercadoria
		sql.append("	DESMER, "); // Descricao da Mercadoria   
		sql.append("	NUMPED, ");   
		sql.append("	VLRLIQANT, ");
		sql.append("	VLRLIQATU ");
		sql.append(" ) VALUES ( ");
		sql.append("	%s, %s, %s, %s, %s, %s ");
		sql.append(" ); ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		
//		for(Item item : pedido.itensPedido) {
//			String query = montaQuery(item, sql);
//			try {
//				db.executeSQL(query);
//				resultado = true;
//			} catch (SQLException e) {
//				Log.e("TabelaTmpMerRecDAO - insereItem - ", e.getMessage());
//			}
//		}

		return resultado;
	}
	
	/**
	 * 
	 * @param codigoMercadoria
	 * @return
	 */
	public static Item obtemItem(Integer codigoMercadoria) {
		StringBuilder sql = obtemQuerySelect();
		sql.append(" WHERE CODMER = %s ");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpMerRecDAO - obtemItem - ", e.getMessage());
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
			Log.e("TabelaTmpMerRecDAO - obtemTodosItens - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}
	
	private static StringBuilder obtemQuerySelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" CODMER		as codigoMercadoria, "); // Codigo da Mercadoria	
		sql.append(" DESCPLMER	as descricaoComplementarProduto, "); // Descricao Complementar Mercadoria
		sql.append(" DESMER		as descricaoMercadoria, "); // Descricao da Mercadoria   
		sql.append(" NUMPED		as numeroPedido, "); // Numero Pedido
		sql.append(" VLRLIQANT	as valorLiquidoAnterior, "); // Valor Liquido Anterior
		sql.append(" VLRLIQATU	as valorLiquidoAtual "); // Valor Liquido Atual
		sql.append(" from TMPMERREC ");
		return sql;
	}
	
//	private static String montaQuery(Item item, StringBuilder sql) {
//		String query = DatabaseUtil.montaQueryGenerica(sql, //
//
//				// CODMER - Codigo da Mercadoria
//				item.mercadoria.codigo,
//
//				// DESCPLMER - Descricao Complementar Mercadoria
//				item.mercadoria.descricaoComplementarProduto,
//
//				// DESMER - Descricao da Mercadoria
//				item.mercadoria.descricao
//
//				// sql.append(" NUMPED		as numeroPedido, "); // Numero Pedido
//				// sql.append(" VLRLIQANT	as valorLiquidoAnterior, "); // Valor Liquido Anterior
//				// sql.append(" VLRLIQATU	as valorLiquidoAtual "); // Valor Liquido Atual
//				);
//		return query;
//	}
	
	private static List<Item> obtemListaItens(List<Map<String, String>> itens) {
		List<Item> listaItens = new ArrayList<Item>();

		for (Map<String, String> itemAux : itens) {
			Item item = new Item();
			
			Mercadoria mercadoria = item.mercadoria;
			mercadoria.percentualIPI = new PercentualIPI();
			item.preco = new Preco();
			item.condicaoPagamento = new CondicaoPagamento();
			item.stb = new Imposto();
			item.desconto = new Desconto();
			item.promocao = new Promocao();
			item.regraDistribuicao = new RegraDistribuicao();
			
			mercadoria.codigo = Util.getInteger(itemAux.get("codigoMercadoria"));
			mercadoria.digito = Mercadoria.obtemDigitoVerificador(mercadoria.codigo);
			mercadoria.descricao = itemAux.get("descricaoMercadoria");

			listaItens.add(item);
		}

		return listaItens;
	}
	
}
