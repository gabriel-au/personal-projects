package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Pedido;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class AlteraPedidoDAO {

	private static final String TAG = AlteraPedidoDAO.class.getName();
	private static Database db;
	
	public static Pedido carregaDadosInfoGerais(Integer numeroPedido) throws SQLException {
		StringBuilder querySB = new StringBuilder();
		querySB.append(" SELECT DISTINCT PED.CODFILFAT, PED.CODFILEPD, PED.TIPVNDPED, ITE.CODCNDPGT, ");
		querySB.append(" ITE.CODRGRDTB, CND.NUMPCLCND, CND.QDEDIAPRZ, CND.TIPFNMCND, CND.TIPCOBCND ");
		querySB.append(" FROM PCAPED PED ");
		querySB.append(" INNER JOIN PCAITE ITE ON PED.NUMPED = ITE.NUMPED ");
		querySB.append(" INNER JOIN PCACND CND ON ITE.CODCNDPGT = CND.CODCNDPGT ");
		querySB.append(" WHERE PED.NUMPED = %s ");
		
		db = DatabaseFactory.getInstance();
		String query = DatabaseUtil.montaQuery(querySB, numeroPedido);
		
		try {
			return montaPedido(db.executeQuery(query));
		} catch (SQLException e) {
			Log.e(TAG, "Erro metodo carregaDadosInfoGerais()", e);
			throw e;
		}
	}

	private static Pedido montaPedido(List<Map<String, String>> pedidoMap) {

		Pedido pedido = new Pedido();
		CondicaoPagamento condicao = new CondicaoPagamento();
		Cliente cliente = new Cliente();
		
		if (!pedidoMap.isEmpty()) {
			Map<String, String> p = pedidoMap.get(0);
			pedido.filialExpedicao = Util.getInteger(p.get("CODFILEPD"));
			pedido.filialFaturamento = Util.getInteger(p.get("CODFILFAT"));
			pedido.condicaoPagamento = Util.getInteger(p.get("CODCNDPGT"));
			pedido.codigoModeloDistribuicao = Util.getInteger(p.get("CODRGRDTB"));
			
			// TODO VERIFICAR SE ESSA EH A MELHOR ABORDAGEM
			// Pedido selecionado pode ser normal ou simplificado
			int tipoVenda = Util.getInteger(p.get("TIPVNDPED"));
			switch (tipoVenda) {
			case 0:
				pedido.tipoVendaPedido = TipoVendaPedido.NORMAL;
				break;
			case 1:
				pedido.tipoVendaPedido = TipoVendaPedido.SIMPLIFICADA;
				break;
			default:
				pedido.tipoVendaPedido = null;
				break;
			}
			
			condicao.numeroParcelasCondicao = Util.getInteger(p.get("NUMPCLCND"));
			condicao.quantidadeDiaPrazo = Util.getInteger(p.get("QDEDIAPRZ"));
			condicao.tipoCobrancaCondicao = Util.getInteger(p.get("TIPCOBCND"));
			condicao.tipoFinanciamento = Util.getInteger(p.get("TIPFNMCND"));
			condicao.codigoCondicaoPagamento = Util.getInteger(p.get("CODCNDPGT"));
			cliente.condicaoPagamento = condicao;
			pedido.cliente = cliente;
		}
		
		return pedido;
	}

	public static List<Item> carregaDadosItens(Integer numeroPedido, Integer codigoFilialExpedicao) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" ITE.CODFILEPD, ITE.CODFILFAT, ITE.CODMER, ITE.CODCNDPGT, ITE.VLRFRTMER, ITE.NUMNOTFSC, ");
		sql.append(" ITE.QDEMERPED, ITE.VLRLIQMER, ITE.VLRBRTMER, ITE.PERDSCFLX, ITE.CODPMC, ITE.VLRSEMENC, ");
		sql.append(" ITE.VLRSTBMER, ITE.VLRSTBCPL, ITE.CODSMBSIT, ITE.VLRLIQCXA, ITE.QDEPRZBFC, ITE.PERACOTTC, ");
		sql.append(" ITE.PERMAXSMP, ITE.PERACRCNS, ITE.CMSMAXMER, ITE.CMSCNSMER, ITE.DESNGCMER, ITE.TIPNGCMER, ");
		sql.append(" ITE.TIPESGMER, ITE.CODFLXPCO, ITE.PERICMMER, ITE.PERECOBFC, ITE.PERCMSADI, ITE.INDMERKIT, ");
		sql.append(" ITE.TIPICTMER, ITE.CODRGRDTB, ITE.VLRBRTCXA, ITE.VLRBRTFRC, ITE.VLRMNMMER, ITE.VLRBRTTMP, ");
		sql.append(" ITE.CODMERPCP, ITE.NUMSEQITE, ITE.VLRMAXISN, ITE.VLRPTOMER, ITE.CODCORMRG, ITE.CODCORMRGB, ");
		sql.append(" ITE.PERDSCBFC, ITE.VLRBNFMER, MER.DESMER, MER.QDECXAFRN, PED.TIPVNDPED ");
		sql.append(" FROM PCAITE ITE ");
		sql.append(" LEFT JOIN CADMER MER ON ITE.CODMER = MER.CODMER ");
		sql.append(" LEFT JOIN PCAPED PED ON ITE.NUMPED = PED.NUMPED ");
		sql.append(" WHERE ITE.NUMPED = %s ");
				
		if (codigoFilialExpedicao != null) {
			sql.append(" AND ITE.CODFILEPD = %s ");
		}
		
		db = DatabaseFactory.getInstance();
		String query = DatabaseUtil.montaQuery(sql, numeroPedido, codigoFilialExpedicao);
		
		try {
			return criaListaItem(db.executeQuery(query));
		} catch (Exception e) {
			Log.e(TAG, "Erro metodo carregaDadosItens()", e);
			throw e;
		}
	}
	
	public static List<Item> carregaDadosItensRegeraByCortes(Integer numeroPedido) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" ITE.CODFILEPD, ITE.CODFILFAT, ITE.CODMER, ITE.CODCNDPGT, ITE.VLRFRTMER, ITE.NUMNOTFSC, ");
		sql.append(" ITE.QDEMERPED, ITE.VLRLIQMER, ITE.VLRBRTMER, ITE.PERDSCFLX, ITE.CODPMC, ITE.VLRSEMENC, ");
		sql.append(" ITE.VLRSTBMER, ITE.VLRSTBCPL, ITE.CODSMBSIT, ITE.VLRLIQCXA, ITE.QDEPRZBFC, ITE.PERACOTTC, ");
		sql.append(" ITE.PERMAXSMP, ITE.PERACRCNS, ITE.CMSMAXMER, ITE.CMSCNSMER, ITE.DESNGCMER, ITE.TIPNGCMER, ");
		sql.append(" ITE.TIPESGMER, ITE.CODFLXPCO, ITE.PERICMMER, ITE.PERECOBFC, ITE.PERCMSADI, ITE.INDMERKIT, ");
		sql.append(" ITE.TIPICTMER, ITE.CODRGRDTB, ITE.VLRBRTCXA, ITE.VLRBRTFRC, ITE.VLRMNMMER, ITE.VLRBRTTMP, ");
		sql.append(" ITE.CODMERPCP, ITE.NUMSEQITE, ITE.VLRMAXISN, ITE.VLRPTOMER, ITE.CODCORMRG, ITE.CODCORMRGB, ");
		sql.append(" ITE.PERDSCBFC, ITE.VLRBNFMER, MER.DESMER, MER.QDECXAFRN, PED.TIPVNDPED ");
		sql.append(" FROM PCAITE ITE ");
		sql.append(" LEFT JOIN CADMER MER ON ITE.CODMER = MER.CODMER ");
		sql.append(" LEFT JOIN PCAPED PED ON ITE.NUMPED = PED.NUMPED ");
		sql.append(" LEFT JOIN PCACRT CRT ON ITE.CODMER = CRT.CODMER ");
		sql.append(" WHERE ITE.NUMPED = %s ");
		sql.append(" AND (CRT.CODMTVCTS=3 OR CRT.CODMTVCTS=8 OR CRT.CODMTVCTS=9 OR CRT.CODMTVCTS=16 OR CRT.CODMTVCTS=20)");
						
		db = DatabaseFactory.getInstance();
		String query = DatabaseUtil.montaQuery(sql, numeroPedido);
		
		try {
			return criaListaItem(db.executeQuery(query));
		} catch (Exception e) {
			Log.e(TAG, "Erro metodo carregaDadosItensRegeraByCortes()", e);
			throw e;
		}
	}

	private static List<Item> criaListaItem(List<Map<String, String>> itemMap) {
		Log.d(TAG, "Iniciando metodo criaListaItem()");

		List<Item> lista = new ArrayList<Item>();
		
		for (Map<String, String> map : itemMap) {
			Item item = new Item();
			item.codigoFilialExpedicao = Util.getInteger(map.get("CODFILEPD"));
			item.codigoFilialFaturamento = Util.getInteger(map.get("CODFILFAT"));
			item.mercadoria.codigo = Util.getInteger(map.get("CODMER"));
			item.condicaoPagamento.codigoCondicaoPagamento = Util.getInteger(map.get("CODCNDPGT"));
			item.mercadoria.valorFrete = Util.getBigDecimal(map.get("VLRFRTMER"));
			item.notaFiscal = Util.getInteger(map.get("NUMNOTFSC"));
			item.quantidadeMercadoria = Util.getInteger(map.get("QDEMERPED"));
			item.valorLiquidoMercadoria = Util.getBigDecimal(map.get("VLRLIQMER"));
			item.preco.valorBrutoMercadoria = Util.getBigDecimal(map.get("VLRBRTMER"));
			item.percentualDescontoFlex = Util.getBigDecimal(map.get("PERDSCFLX"));
			item.promocao.codigo = Util.getInteger(map.get("CODPMC"));
			item.valorSemEncargos = Util.getBigDecimal(map.get("VLRSEMENC"));
			item.stb.valorSTBUnitario = Util.getBigDecimal(map.get("VLRSTBMER"));
			item.stb.valorSTBComplementar = Util.getBigDecimal(map.get("VLRSTBCPL"));
			item.preco.codigoSimboloSituacao = map.get("CODSMBSIT");
			item.valorLiquidoCaixa = Util.getBigDecimal(map.get("VLRLIQCXA"));
			item.quantidadePrazoBeneficiario = Util.getInteger(map.get("QDEPRZBFC"));
			item.percentualDescontoAcaoTatica = Util.getBigDecimal(map.get("PERACOTTC"));
			item.percentualDescontoSimplificado = Util.getBigDecimal(map.get("PERMAXSMP"));
			item.percentualAcrescimoConcedido = Util.getBigDecimal(map.get("PERACRCNS"));
			item.comissao.comissaoMaximaMercadoria = Util.getBigDecimal(map.get("CMSCNSMER"));
			item.mercadoria.descricaoNegocioMercadoria = map.get("DESNGCMER");
			item.tipoNegociacaoMercadoria = Util.getInteger(map.get("TIPNGCMER"));
			item.mercadoria.tipoEsgotamento = map.get("TIPESGMER");
			item.preco.codigoFlexivelPreco = map.get("CODFLXPCO");
			item.preco.percentualICMS = Util.getBigDecimal(map.get("PERICMMER"));
			item.percentualEconomicoBeneficio = Util.getBigDecimal(map.get("PERECOBFC"));
			item.beneficio.percentualComissao = Util.getBigDecimal(map.get("PERCMSADI"));
			item.mercadoria.indicaMercadoriaKit = Util.getInteger(map.get("INDMERKIT"));
			item.preco.tipoIncentivoMercadoria = map.get("TIPICTMER");
			item.regraDistribuicao.codigoRegra = Util.getInteger(map.get("CODRGRDTB"));
			item.preco.valorBrutoCaixa = Util.getBigDecimal(map.get("VLRBRTCXA"));
			item.preco.valorBrutoFracionado = Util.getBigDecimal(map.get("VLRBRTFRC"));
			item.mercadoria.valorMinimoMercadoria = Util.getBigDecimal(map.get("VLRMNMMER"));
			item.preco.valorBrutoTMP = Util.getBigDecimal(map.get("VLRBRTTMP"));
			item.mercadoria.codigoMercadoriaPrincipal = Util.getInteger(map.get("CODMERPCP"));
			item.numeroSequenciaItem = Util.getInteger(map.get("NUMSEQITE"));
			item.mercadoria.valorMaximoMercadoria = Util.getBigDecimal(map.get("VLRMAXISN"));
			item.valorPontuacaoMercadoria = Util.getInteger(map.get("VLRPTOMER"));
			item.codigoCorMargem = Util.getInteger(map.get("CODCORMRG"));
			item.codigoCorMargemB = Util.getInteger(map.get("CODCORMRGB"));
			item.percentualDescontoBeneficio = Util.getBigDecimal(map.get("PERDSCBFC"));
			item.valorDescontoBeneficio = Util.getBigDecimal(map.get("PERDSCBFC"));
			item.valorBonificacao = Util.getBigDecimal(map.get("VLRBNFMER"));
			item.mercadoria.descricao = map.get("DESMER");
			item.mercadoria.quantidadeCaixaFornecedor = Util.getInteger(map.get("QDECXAFRN"));
			item.tipoVendaPedido = Util.getInteger(map.get("TIPVNDPED"));

			lista.add(item);
		}
			
		return lista;
	}

	public static List<Item> carregaFiliaisPedidoReprovado(Integer numeroPedido) throws Exception {
		List<Item> lista = new ArrayList<Item>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUBSTR(DESSITPED, 2, 2) AS FILIAL ");
		sql.append(" FROM PCASIT ");
		sql.append(" WHERE NUMPED = %s ");
		
		db = DatabaseFactory.getInstance();
		String query = DatabaseUtil.montaQuery(sql, numeroPedido);
		
		try {
			List<Map<String, String>> result = db.executeQuery(query);

			for (Map<String, String> map : result) {
				Item item = new Item();
				item.codigoFilialExpedicao = Util.getInteger(map.get("FILIAL"));
				lista.add(item);
			}
			
		} catch(Exception e){
			Log.e(TAG, "Erro metodo carregaFiliaisPedidoReprovado()", e);
			throw e;
		}
		
		return lista;
	}

}
