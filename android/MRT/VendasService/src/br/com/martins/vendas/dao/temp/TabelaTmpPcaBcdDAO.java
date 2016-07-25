package br.com.martins.vendas.dao.temp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Beneficio;
import br.com.martins.vendas.vo.Pedido;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class TabelaTmpPcaBcdDAO {

	private static Database db;

	/**
	 * Método responsável por remover todos os dados da tabela TMPPCABCD.
	 * 
	 * @return <code>true</code> se os dados da tabela foram removidos com
	 *         sucesso, e <code>false</code> caso tenha ocorrido algum problema
	 *         que tenha impedido a remoção dos dados da tabela.
	 */
	public static boolean limpaTabela() {
		String sql = " delete from TMPPCABCD ";
		boolean resultado = false;

		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(sql);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpPcaBcdDAO - limpaTabela - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean insere(Pedido pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO TMPPCABCD ( ");
		sql.append(" CODBFC, "); // Codigo Beneficio - Beneficio.codigoBeneficio
		sql.append(" CODCLI, "); // Codigo Cliente
		sql.append(" COLDEFAULT, "); // Coluna Default
		sql.append(" FLGAPLBNF, "); // Flag Aplicacao Beneficio
		sql.append(" NUMPED, "); // Numero Pedido
		sql.append(" NUMSEQFXA, "); // Numero Sequencia Faixa - Beneficio.numeroSequencialFaixa
		sql.append(" PERCMSADI, "); // Percentual Comissao Adicional - Beneficio.percentualComissao
		sql.append(" PERDSCBFC, "); // Percentual Desconto Beneficio - Beneficio.percentualDescontoTemp
		sql.append(" PERDSCREAL, "); // Percentual Desconto Real
		sql.append(" QDEDIAPRZ, "); // Quantidade Dia Prazo - Beneficio.quantidadeDiaPrazoTemp
		sql.append(" QDEPRZREAL "); // Quantidade Prazo Real - Beneficio.quantidadePrazoRealTemp
		sql.append(" ) values ( ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s ");
		sql.append(" ); ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		String query = montaQuery(pedido, sql);

		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpPcaBcdDAO - insereItem - ", e.getMessage());
		}

		return resultado;
	}
	
	private static StringBuilder obtemQuerySelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" CODBFC		as codigoBeneficio, "); // Codigo Beneficio
		sql.append(" CODCLI		as codigoCliente, "); // Codigo Cliente
		sql.append(" COLDEFAULT	as colunaDefault, "); // Coluna Default
		sql.append(" FLGAPLBNF	as flagAplicacaoBeneficio, "); // Flag Aplicacao Beneficio
		sql.append(" NUMPED		as numeroPedido, "); // Numero Pedido
		sql.append(" NUMSEQFXA	as numeroSequenciaFaixa, "); // Numero Sequencia Faixa
		sql.append(" PERCMSADI	as percentualComissaoAdicional, "); // Percentual Comissao Adicional
		sql.append(" PERDSCBFC	as percentualDescontoBeneficio, "); // Percentual Desconto Beneficio
		sql.append(" PERDSCREAL	as percentualDescontoReal, "); // Percentual Desconto Real
		sql.append(" QDEDIAPRZ	as quantidadeDiaPrazo, "); // Quantidade Dia Prazo
		sql.append(" QDEPRZREAL	as quantidadePrazoReal "); // Quantidade Prazo Real
		sql.append(" FROM TMPPCABCD ");
		return sql;
	}
	
	public static List<Item> obtemBeneficio(Integer codigoBeneficio, Integer codigoCliente, Integer numeroPedido) {
		StringBuilder sql = obtemQuerySelect();
		sql.append(" WHERE CODBFC = %s AND CODCLI = %s AND NUMPED = %s");
		
		String query = DatabaseUtil.montaQueryGenerica(sql, codigoBeneficio, codigoCliente, numeroPedido);
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpPcaBcdDAO - obtemBeneficio - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}
	
	private static String montaQuery(Pedido pedido, StringBuilder sql) {
		// TODO APENAS PARA TESTES. VERIFICAR.
		Beneficio beneficio = new Beneficio();
					
		String query = DatabaseUtil.montaQueryGenerica(sql, //

				// Codigo Beneficio
				pedido.beneficio.codigoBeneficio,
				
				// Codigo Cliente
				pedido.codigoCliente,
				
				// Coluna Default
				// ??? 
				
				// Flag Aplicacao Beneficio
				pedido.beneficio.isAplicaBeneficio ? "*" : "",
				
				// Numero Pedido
				pedido.numeroPedido,
				
				// Numero Sequencia Faixa
				pedido.beneficio.numeroSequencialFaixa,
				
				// Percentual Comissao Adicional
				pedido.beneficio.percentualComissao,
				
				// Percentual Desconto Beneficio
				pedido.beneficio.percentualDescontoTemp,
				
				// Percentual Desconto Real
//				pedido.per
				
				// Quantidade Dia Prazo
				beneficio.quantidadeDiaPrazoTemp,
				
				// Quantidade Prazo Real
				beneficio.quantidadePrazoRealTemp

				);
		return query;
	}
	
	private static List<Item> obtemListaItens(List<Map<String, String>> itens) {
		List<Item> listaItens = new ArrayList<Item>();

		for (Map<String, String> itemAux : itens) {
			Item item = new Item();
			
			// TODO APENAS PARA TESTES. VERIFICAR.
			Beneficio beneficio = new Beneficio();
			
			// Codigo Beneficio
			Util.getInteger(itemAux.get("codigoBeneficio"));
			
			// Codigo Cliente
			item.codigoCliente = Util.getInteger(itemAux.get("codigoCliente"));
			
			// Coluna Default
			// ??? Util.getInteger(itemAux.get("colunaDefault"));
			
			// Flag Aplicacao Beneficio
			beneficio.isAplicaBeneficio = "".equals(itemAux.get("flagAplicacaoBeneficio")) ? false : true;
			
			// Numero Pedido
			// ??? Util.getInteger(itemAux.get("numeroPedido"));
			
			// Numero Sequencia Faixa
			beneficio.numeroSequencialFaixa = Util.getInteger(itemAux.get("numeroSequenciaFaixa"));
			
			// Percentual Comissao Adicional
			beneficio.percentualComissao = Util.getBigDecimal(itemAux.get("percentualComissaoAdicional"));
			
			// Percentual Desconto Beneficio
			beneficio.percentualDescontoTemp = Util.getBigDecimal(itemAux.get("percentualDescontoBeneficio"));
			
			// Percentual Desconto Real
			// ??? Util.getInteger(itemAux.get("percentualDescontoReal"));
			
			// Quantidade Dia Prazo
			beneficio.quantidadeDiaPrazoTemp = Util.getInteger(itemAux.get("quantidadeDiaPrazo"));
			
			// Quantidade Prazo Real
			beneficio.quantidadePrazoRealTemp = Util.getInteger(itemAux.get("quantidadePrazoReal"));

			listaItens.add(item);
		}

		return listaItens;
	}
	
}
