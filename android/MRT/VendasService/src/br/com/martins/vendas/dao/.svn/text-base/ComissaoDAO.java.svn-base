package br.com.martins.vendas.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Comissao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;

public class ComissaoDAO {
	
	private static final String TAG = ComissaoDAO.class.getName();
	
	private static Database db;
	
	/**
	 * Busca os parametros de comissao
	 * @return Objeto Comissao
	 */
	public static Comissao obtemParametrosComissao(){
		StringBuilder query = new StringBuilder();
		query.append(" select PERBSESMB, RATCMSACO, RATCMSSMP, RATCMSACR, FTRACRCMS, ");
		query.append(" PERAPPTLV, PERRATBINF, PERRATBSUP, PERRATBACO ");
		query.append(" from PCAPCM ");
		
		db = DatabaseFactory.getInstance();
		Comissao comissao = null;
		
		try {
			List<Map<String, String>> result = db.executeQuery(query.toString());
			if(result.size() == 1){
				for(Map<String, String> reg : result){
					comissao = new Comissao();
					comissao.percentualBaseSimbolo = BigDecimal.valueOf(Double.parseDouble(reg.get("PERBSESMB")));	
					comissao.rateioComissaoAcao = BigDecimal.valueOf(Double.parseDouble(reg.get("RATCMSACO")));	
					comissao.rateioComissaoSimplificado = BigDecimal.valueOf(Double.parseDouble(reg.get("RATCMSSMP")));	
					comissao.rateioComissaoAcrescimo = BigDecimal.valueOf(Double.parseDouble(reg.get("RATCMSACR")));	
					comissao.fatorAcrescimoComissao = BigDecimal.valueOf(Double.parseDouble(reg.get("FTRACRCMS")));	
					comissao.percentualApropriacaoTLV = BigDecimal.valueOf(Double.parseDouble(reg.get("PERAPPTLV")));	
					comissao.percentualRateioBandaInferior = BigDecimal.valueOf(Double.parseDouble(reg.get("PERRATBINF")));	
					comissao.percentualRateioBandaSuperior = BigDecimal.valueOf(Double.parseDouble(reg.get("PERRATBSUP")));	
					comissao.percentualRateioBandaAcao = BigDecimal.valueOf(Double.parseDouble(reg.get("PERRATBACO")));	
				}
			}
			
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return comissao;
		
	}
	
	/**
	 * Busca o percentual de comissao Simbolo na tabela PCACMS 
	 * @param codigoFilialExpedicao
	 * @param codigoSimboloSituacao
	 */
	public static BigDecimal buscaPercentualComissaoSimbolo(Integer codigoFilialExpedicao, String codigoSimboloSituacao){
		StringBuilder sql = new StringBuilder();
		sql.append(" select PERCMSSMB ");
		sql.append(" from PCACMS ");
		sql.append(" where CODFILEPD = %s ");
		sql.append(" and CODSMBSIT = '%s' ");
		
		String query = DatabaseUtil.montaQuery(sql, codigoFilialExpedicao, codigoSimboloSituacao);
		db = DatabaseFactory.getInstance();
		
		BigDecimal percentual = BigDecimal.ZERO;
		
		try {
			List<Map<String, String>> result  = db.executeQuery(query);
			if(result.size() > 0){
				percentual = new BigDecimal(result.get(0).get("PERCMSSMB"));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return percentual;
	}
	
	/**
	 * 
	 * @param codigoMercadoria
	 * @param siglaEstado
	 * @return
	 */
	public static Integer obtemCodigoPiramide(int codigoMercadoria, String siglaEstado){
		StringBuilder sql = new StringBuilder();
		sql.append(" select CODPIR from PCAPIM ");
		sql.append(" where CODMER = %s ");
		sql.append(" and CODESTUNI = '%s' ");
		
		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria, siglaEstado);
		db = DatabaseFactory.getInstance();
		
		Integer codigoPiramide = null;
		
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			if(result.size() > 0){
				String value = result.get(0).get("CODPIR");
				codigoPiramide = (value != null) ? Integer.parseInt(value) : null;
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return codigoPiramide;
	}
	
	/**
	 * Busca o codigo de simbolo para um segmento de cliente. caso este exista, 
	 * substituir o codigoSimboloSituacao(CODSMBSIT) do livro de pre�o pelo
	 * codigo resultante deste metodo.
	 *
	 * @param codigoEstadoDestino the codigo estado destino
	 * @param codigoSegmentoCliente the codigo segmento cliente
	 * @param codigoSimboloSituacao the codigo simbolo situacao
	 * @return the string
	 * @throws SQLException the sQL exception
	 */
	public static String obtemCodigoSimboloSubstituto(Cliente cliente, 
			String codigoSimboloSituacao) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("select ssb.CODSMBSBT           ");
		sql.append("from PCASSB ssb, PCACLT clt    ");
		sql.append("where                          ");
		sql.append("ssb.CODSGMCLI = clt.CODSGMCLI  ");
		sql.append("and clt.CODCLI = %s            ");
		sql.append("and clt.CODTETVND = %s         ");
		sql.append("and ssb.CODESTUNI = '%s'       ");
		sql.append("and ssb.CODSMBSIT = '%s'       ");
		
		String query = DatabaseUtil.montaQuery(sql, cliente.codigoCliente,
				cliente.codigoTerritorio,
				cliente.codigoEstadoDestino, 
				codigoSimboloSituacao);
		db = DatabaseFactory.getInstance();
		
		List<Map<String, String>> result = db.executeQuery(query);
		if(result.size() > 0){
			return result.get(0).get("CODSMBSBT");
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param codigoCanal
	 * @return
	 */
	public static BigDecimal obtemPercentualRateioComisssaoSimbolo(Integer codigoCanal){
		StringBuilder sql = new StringBuilder();
		sql.append(" select RATCMSSMB ");
		sql.append(" from PCACNL");
		sql.append(" where CODCNL = %s ");
		sql.append("");
		
		String query = DatabaseUtil.montaQuery(sql, codigoCanal);
		db = DatabaseFactory.getInstance();
		
		BigDecimal percentual = BigDecimal.ZERO;
		
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			if(result.size() > 0){
				percentual = new BigDecimal(result.get(0).get("RATCMSSMB"));
			}
			
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}		
		return percentual;
	}
	
//	public static List<Comissao> buscaSimboloMercadoria(Integer codigoFilialExp) {
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append("select distinct epe.codsmbsit,epe.vlrbrtmer,epe.tipngcmer,epe.cmscnsmer,epe.qdemerped,epe.peracottc,epe.permaxsmp,epe.perdscflx,epe.perdscbfc,epe.peracrcns,epe.vlrbrttmp,epe.vlrliqmer, ");
//		sql.append("epe.codmer,cad.codterchv1,cnd.TipFnmCnd,cnd.ftrcndpgt,cnd.ftrencvdr,cnd.IndCndTbo,age.codestdsn,age.codestori,cad.indrtcbnf,cad.indrtcbfb ");
//		sql.append("from tmpitepe epe ");
//		sql.append("left join cadmer cad ");
//		sql.append("on epe.codmer= cad.codmer ");
//		sql.append("left join pcacnd cnd ");
//		sql.append("on epe.codcndpgt=cnd.codcndpgt ");
//		sql.append("left join pcaage age ");
//		sql.append("on epe.codfilepd=age.codfilepd ");
//		sql.append("where epe.codfilepd=%s ");
//				
//		String query = DatabaseUtil.montaQuery(sql, codigoFilialExp);
//		db = DatabaseFactory.getInstance();
//		
//		List<Comissao> listaComissao = new ArrayList<Comissao>();
//		Comissao comissao;
//	
//		try {		
//			
//			CondicaoPagamento condicaoPagamento;
//			Preco preco;
//			Pedido pedido;
//			Mercadoria mercadoria;
//			
//			List<Map<String, String>> result = db.executeQuery(query);
//			if(result.size() > 0){
//				
//				for(Map<String, String> r : result){
//					condicaoPagamento = new CondicaoPagamento();
//					preco = new Preco();
//					pedido = new Pedido();
//					mercadoria = new Mercadoria();
//					comissao=new Comissao();
//					preco.codigoSimboloSituacao = r.get("CODSMBSIT");
//					preco.valorLiquidoMercadoria=Util.getBigDecimal(r.get("VLRLIQMER"));
//					preco.valorBrutoMercadoria=Util.getBigDecimal(r.get("VLRBRTMER"));
//					comissao.valorComissaoTotal=Util.getBigDecimal(r.get("CMSCNSMER"));
//					pedido.quantidadeMercadoria=Util.getInteger(r.get("QDEMERPED"));
//					pedido.percAcaoTatica=Util.getBigDecimal(r.get("PERACOTTC"));
//					pedido.perMaxSimples=Util.getBigDecimal(r.get("PERMAXSMP"));
//					pedido.perDescontoFlex=Util.getBigDecimal(r.get("PERDSCFLX"));
//					pedido.perDescontoBeneficio=Util.getBigDecimal(r.get("PERDSCBFC"));
//					pedido.perDescontoBeneficio=Util.getBigDecimal(r.get("PERDSCBFC"));
//					comissao.percentualAcrescimoConcedido=Util.getBigDecimal(r.get("PERACRCNS"));
//					comissao.valorBrutoTMP=Util.getBigDecimal(r.get("VLRBRTTMP"));
//					mercadoria.codigo=Util.getInteger(r.get("CODMER"));
//					mercadoria.codigoChaveCategoria=r.get("CODTERCHV1");
//					mercadoria.temRestricaoBeneficioCustomizado=Util.getInteger(r.get("INDRTCBNF"));
//					mercadoria.temRestricaoBeneficioCustomizadoNoBrinde=Util.getInteger(r.get("INDRTCBFB"));
//					comissao.tipoNegociacao=Util.getInteger(r.get("TIPNGCMER"));
//					condicaoPagamento.tipoFinanciamento=Util.getInteger(r.get("TIPFNMCND"));
//					condicaoPagamento.fatorCondicaoPagamento=Util.getBigDecimal(r.get("FTRCNDPGT"));
//					condicaoPagamento.fatorEncargoVendor=Util.getBigDecimal(r.get("FTRENCVDR"));
//					condicaoPagamento.indicaCondicaoTribanco=Util.getInteger(r.get("INDCNDTBO"));
//					comissao.codigoDestino=r.get("CODESTDSN");
//					comissao.codigoOrigem=r.get("CODESTORI");
//					listaComissao.add(comissao);
//				}				
//			}
//			
//		} catch (SQLException e) {
//			Log.e(TAG, e.getMessage());
//		}
//		return listaComissao;
//	}
	
}
