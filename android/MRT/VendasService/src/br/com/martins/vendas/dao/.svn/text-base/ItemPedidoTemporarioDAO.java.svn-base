package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.martins.vendas.vo.ItemPedidoTemporario;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class ItemPedidoTemporarioDAO {
	
	private static final String TAG = ItemPedidoTemporarioDAO.class.getName();
	
	public static void atualiza(ItemPedidoTemporario itemPedidoTemporario, TransactionManager transactionManager) throws Exception {

		Map<String, String> values;
		String whereSQL;
		String[] whereValues;
		int updated;
		try {
			values = new TreeMap<String, String>();
			values.put("QDEPRZBFC", Util.getStringValue(itemPedidoTemporario.prazoBeneficio));
			values.put("PERDSCBFC", Util.getStringValue(itemPedidoTemporario.percentualDescontoBeneficio));
			values.put("PERACRCNS", Util.getStringValue(itemPedidoTemporario.percentualAcrescimoConcedido));
			values.put("VLRLIQMER", Util.getStringValue(itemPedidoTemporario.valorLiquidoMercadoria));
			values.put("VLRBRTMER", Util.getStringValue(itemPedidoTemporario.valorBrutoMercadoria));
			values.put("VLRBRTCXA", Util.getStringValue(itemPedidoTemporario.valorBrutoCaixa));
			values.put("CMSCNSMER", Util.getStringValue(itemPedidoTemporario.comissaoConcessaoMercadoria));
			values.put("CMSMAXMER", Util.getStringValue(itemPedidoTemporario.comissaoMaximaMercadoria));
			values.put("LIQCXAIMP", Util.getStringValue(itemPedidoTemporario.valorCaixaComImposto));
			values.put("VLRUNTIMP", Util.getStringValue(itemPedidoTemporario.valorUnitarioComImposto));
			values.put("UNTCXAIMP", Util.getStringValue(itemPedidoTemporario.valorUnitarioCaixaComImposto));
			values.put("VLRLIQIMP", Util.getStringValue(itemPedidoTemporario.valorLiquidoComImposto));
			values.put("VLRTOTLIQ", Util.getStringValue(itemPedidoTemporario.valorLiquidoTotal));
			values.put("VLRLIQCXA", Util.getStringValue(itemPedidoTemporario.valorLiquidoCaixa)); 
			
			whereSQL = "CODFILEPD = ? AND CODFILFAT = ? AND CODMER = ? AND CODCNDPGT = ?";
			whereValues = new String[] { itemPedidoTemporario.codigoFilialExpedicao.toString(), 
					itemPedidoTemporario.codigoFilialFaturamento.toString(), 
					itemPedidoTemporario.codigoMercadoria.toString(), 
					itemPedidoTemporario.codigoCondicaoPagamento.toString() };

			updated = transactionManager.update("TMPITEPE", values, whereSQL, whereValues);
			Log.i(TAG, String.format("atualiza:%s registros afetados", updated));

		} catch (SQLException e) {
			Log.e(TAG, e.getLocalizedMessage());
		}
	}

	public static List<ItemPedidoTemporario> buscaItem(Integer codigoMercadoria, Integer codigoFilialExpedicao, Integer codigoFilialFaturamento) throws Exception {
		
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			query.append(" select ITEM.CODMER, ITEM.LIQCXAIMP, ITEM.CMSCNSMER, ITEM.VLRUNTIMP, ITEM.VLRTOTLIQ, ITEM.UNTCXAIMP, ITEM.VLRLIQIMP, ITEM.CODFLXPCO, ITEM.VLRLIQCXA, ITEM.VLRIPIMER, ITEM.VLRBRTCXA, ITEM.VLRSTBMER, ITEM.VLRBRTTMP, ITEM.PERACRVTL, ITEM.CODFILEPD, ITEM.VLRBRTFRC, ")
			.append(" ITEM.PERDSCFLX, ITEM.PERACRCNS, ITEM.INDITEVND, ITEM.CODMERPCP, ITEM.VLRSTBTOT, ITEM.PERDSCBFC, ITEM.VLRBRTMER, ITEM.VLRPTOMER, ITEM.VLRIPITOT, ITEM.VLRFRTMER, ")
			.append(" ITEM.TIPPMC, ITEM.CODPMC, ITEM.DESMER, ITEM.QDEMERPED, ITEM.CODFILFAT, ITEM.CODCNDPGT, ITEM.TIPNGCMER, ITEM.INDRTCBFV, ")
			.append(" ITEM.INDRTCBFB, ITEM.INDRTCBNF, ITEM.RTCBFVMER, ITEM.VLRLIQMER, ITEM.VLRMAXISN, ITEM.QDEPRZBFC, ITEM.NUMNOTFSC, ITEM.CODRGRDTB, ITEM.VLRRDCITE, ")
			.append(" ITEM.VLRBNFMER, ITEM.CMSMAXMER, ITEM.VLRPTOBFC, PRC.DESPMC ")
			.append(" FROM TMPITEPE ITEM ")
			.append(" LEFT JOIN PCAPRC PRC ON ITEM.CODPMC = PRC.CODPMC AND ITEM.CODFILEPD = PRC.CODFILEPD ")
			.append(" WHERE ITEM.CODFILEPD = %s ");
			
			if(codigoMercadoria != null){
				query.append(" AND (ITEM.CODMERPCP = %s OR (ITEM.CODMER = %s AND ITEM.CODMERPCP = 0))");
			}
			if(codigoFilialFaturamento != null){
				query.append(" AND ITEM.CODFILFAT = %s");
			}
			List<Map<String, String>> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoFilialExpedicao, codigoMercadoria, codigoMercadoria, codigoFilialFaturamento));
			return criaListaItensPedidosTemporarios(result);
		
		} catch(Exception e){
			throw e;
		}
	}
	
	private static List<ItemPedidoTemporario> criaListaItensPedidosTemporarios(List<Map<String, String>> itensPedido) throws Exception {
		
		ItemPedidoTemporario itemPedidoTemporario;
		List<ItemPedidoTemporario> listaItensPedido = new ArrayList<ItemPedidoTemporario>();
		for(Map<String, String> pedidoConsulta : itensPedido){
			itemPedidoTemporario = new ItemPedidoTemporario();
			itemPedidoTemporario.codigoMercadoria = Util.getInteger(pedidoConsulta.get("CODMER"));
			itemPedidoTemporario.descricaoMercadoria = pedidoConsulta.get("DESMER");
			itemPedidoTemporario.codigoCondicaoPagamento = Util.getInteger(pedidoConsulta.get("CODCNDPGT"));
			itemPedidoTemporario.codigoFilialFaturamento = Util.getInteger(pedidoConsulta.get("CODFILFAT"));
			itemPedidoTemporario.quantidadeMercadoria = Util.getInteger(pedidoConsulta.get("QDEMERPED"));
			itemPedidoTemporario.tipoNegocicaoMercadoria = Util.getInteger(pedidoConsulta.get("TIPNGCMER"));
			itemPedidoTemporario.indicaoRestricaoBonificacaoSemBrinde = Util.getInteger(pedidoConsulta.get("INDRTCBNF"));
			itemPedidoTemporario.indicaRestricaoBeneficioCustomizadoBrinde = Util.getInteger(pedidoConsulta.get("INDRTCBFB"));
			itemPedidoTemporario.indicaRestricaoBeneficioCustomizado = Util.getInteger(pedidoConsulta.get("INDRTCBFV"));
			itemPedidoTemporario.indicaRestricaoBeneficioCustomizadoMercadoria = Util.getInteger(pedidoConsulta.get("RTCBFVMER"));
			itemPedidoTemporario.codigoMercadoriaPrincipal = Util.getInteger(pedidoConsulta.get("CODMERPCP"));
			itemPedidoTemporario.valorSTBTotal = Util.getBigDecimal(pedidoConsulta.get("VLRSTBTOT"));
			itemPedidoTemporario.valorIPITotal = Util.getBigDecimal(pedidoConsulta.get("VLRIPITOT"));
			itemPedidoTemporario.valorFreteMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRFRTMER"));
			itemPedidoTemporario.valorLiquidoMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRLIQMER"));
			itemPedidoTemporario.valorLiquidoComImposto = Util.getBigDecimal(pedidoConsulta.get("VLRLIQIMP"));
			itemPedidoTemporario.valorPontoMercadoria = Util.getInteger(pedidoConsulta.get("VLRPTOMER"));
			itemPedidoTemporario.valorBrutoMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRBRTMER"));
			itemPedidoTemporario.percentualDescontoBeneficio = Util.getBigDecimal(pedidoConsulta.get("PERDSCBFC"));
			itemPedidoTemporario.valorBrutoCaixa = Util.getBigDecimal(pedidoConsulta.get("VLRBRTCXA"));
			itemPedidoTemporario.valorBrutoFracionado = Util.getBigDecimal(pedidoConsulta.get("VLRBRTFRC"));
			itemPedidoTemporario.percentualDescontoFlexivel = Util.getBigDecimal(pedidoConsulta.get("PERDSCFLX"));
			itemPedidoTemporario.percentualAcrescimoConcedido = Util.getBigDecimal(pedidoConsulta.get("PERACRCNS"));
			itemPedidoTemporario.codigoFilialExpedicao = Util.getInteger(pedidoConsulta.get("CODFILEPD"));
			itemPedidoTemporario.valorBrutoTemporario = Util.getBigDecimal(pedidoConsulta.get("VLRBRTTMP"));
			itemPedidoTemporario.percentualAcrescimoVirtual = Util.getBigDecimal(pedidoConsulta.get("PERACRVTL"));
			itemPedidoTemporario.valorSTBMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRSTBMER"));
			itemPedidoTemporario.codigoPromocao = Util.getInteger(pedidoConsulta.get("CODPMC"));
			itemPedidoTemporario.valorLiquidoCaixa = Util.getBigDecimal(pedidoConsulta.get("VLRLIQCXA"));
			itemPedidoTemporario.valorIPIMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRIPIMER"));
			itemPedidoTemporario.codigoPrecoFlexivel = pedidoConsulta.get("CODFLXPCO");
			itemPedidoTemporario.tipoVendaItem = Util.getInteger(pedidoConsulta.get("INDITEVND"));
			itemPedidoTemporario.valorIPIMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRIPIMER"));
			itemPedidoTemporario.codigoPrecoFlexivel = pedidoConsulta.get("CODFLXPCO");
			itemPedidoTemporario.tipoVendaItem = Util.getInteger(pedidoConsulta.get("INDITEVND"));
			itemPedidoTemporario.descricaoPromocao = pedidoConsulta.get("DESPMC");
			itemPedidoTemporario.valorMaximoIsento = Util.getBigDecimal(pedidoConsulta.get("VLRMAXISN"));
			itemPedidoTemporario.indicativoSemMargemLucro = Util.getInteger(pedidoConsulta.get("INDSEMMRGL"));
			itemPedidoTemporario.quantidadePrazoBonificacao = Util.getInteger(pedidoConsulta.get("QDEPRZBFC"));
			itemPedidoTemporario.numeroNotaFiscal = Util.getInteger(pedidoConsulta.get("NUMNOTFSC"));
			itemPedidoTemporario.codigoRegraDistribuicao = Util.getInteger(pedidoConsulta.get("CODRGRDTB"));
			itemPedidoTemporario.valorReducaoItem = Util.getInteger(pedidoConsulta.get("VLRRDCITE"));
			itemPedidoTemporario.valorBonificacaoMercadoria = Util.getBigDecimal(pedidoConsulta.get("VLRBNFMER"));
			itemPedidoTemporario.comissaoMaximaMercadoria = Util.getBigDecimal(pedidoConsulta.get("CMSMAXMER"));
			itemPedidoTemporario.valorPontosBeneficio = Util.getInteger(pedidoConsulta.get("VLRPTOBFC"));
			itemPedidoTemporario.valorUnitarioCaixaComImposto = Util.getBigDecimal(pedidoConsulta.get("UNTCXAIMP"));
			itemPedidoTemporario.valorTotalLiquido  = Util.getBigDecimal(pedidoConsulta.get("VLRTOTLIQ"));
			itemPedidoTemporario.valorUnitarioComImposto = Util.getBigDecimal(pedidoConsulta.get("VLRUNTIMP"));
			itemPedidoTemporario.comissaoConcessaoMercadoria = Util.getBigDecimal(pedidoConsulta.get("CMSCNSMER"));
			itemPedidoTemporario.valorCaixaComImposto = Util.getBigDecimal(pedidoConsulta.get("LIQCXAIMP"));
			itemPedidoTemporario.valorLiquidoTotal = Util.getBigDecimal(pedidoConsulta.get("VLRTOTLIQ"));
			listaItensPedido.add(itemPedidoTemporario);
		}
		return listaItensPedido;
	}
}