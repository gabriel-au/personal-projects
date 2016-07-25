package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class GondolaDAO {
	
	private final static String TAG = GondolaDAO.class.getName();

	/**
	 * Obtem a lista de itens da gondola do cliente
	 * @return List<Gondola> - lista com os itens da gondola do cliente
	 * @throws Exception
	 */
	public static List<Gondola> listarItensGondolaCliente(Integer codigoCliente) throws Exception {

		List<Gondola> listarItensGondola = new ArrayList<Gondola>();
		Gondola gondola = null;
		
		try {
			
			StringBuilder queryTmp = new StringBuilder();
			
			queryTmp.append("SELECT GONDOLA.CODMER, GONDOLA.POSMER, GONDOLA.PCOCLI, GONDOLA.QDEMERANT, GONDOLA.QDEMERATU, GONDOLA.DATULTVND, ");
			queryTmp.append("GONDOLA.CODFILEPD, GONDOLA.CODFILFAT, GONDOLA.CODCHVRGT, MERCADORIA.DESMER, ");
			queryTmp.append("CASE WHEN (COMPLEMENTO.DESCPLMER IS NOT NULL) THEN COMPLEMENTO.DESCPLMER ELSE SIMILAR.DESSCTGRP END AS DESCRICAO_COMPLEMENTO ");
			queryTmp.append("FROM PCABGM GONDOLA ");
			queryTmp.append("LEFT JOIN CADMER MERCADORIA ON GONDOLA.CODMER = MERCADORIA.CODMER ");
			queryTmp.append("LEFT JOIN CADCPL COMPLEMENTO ON MERCADORIA.CODMER = COMPLEMENTO.CODMER ");
			queryTmp.append("LEFT JOIN PCASMR SIMILAR ON MERCADORIA.CODMER = SIMILAR.CODMERPCP ");
			queryTmp.append("AND ((SIMILAR.CODMER IS NULL AND MERCADORIA.CODMER = GONDOLA.CODMER) OR (MERCADORIA.CODMER = SIMILAR.CODMERPCP AND SIMILAR.CODMER = GONDOLA.CODMER)) ");
			queryTmp.append("WHERE CODCLI = %s ");
			queryTmp.append("ORDER BY GONDOLA.POSMER ");
			
			String query = DatabaseUtil.montaQuery(queryTmp.toString(), codigoCliente);

			List<Map<String, String>> result = DatabaseFactory.getInstance().executeQuery(query);
			
			if (!result.isEmpty()) {
				for (Map<String, String> map : result) {
					gondola = new Gondola();
					
					gondola.codigoCliente = codigoCliente;
					gondola.codigoMercadoria = Util.getInteger(map.get("CODMER"));
					gondola.posicaoMercadoria = Util.getInteger(map.get("POSMER"));
					gondola.precoCliente = Util.getBigDecimal(map.get("PCOCLI"));
					gondola.quantidadeMercadoriaAnterior = Util.getInteger(map.get("QDEMERANT"));
					gondola.quantidadeMercadoriaAtual = Util.getInteger(map.get("QDEMERATU"));
					gondola.dataUltimaVenda = DateUtil.formataData(DateUtil.formataData(map.get("DATULTVND"), DateUtil.DEFAULT_DATE_DATABASE), DateUtil.DEFAULT_DATE_PATTERN);
					gondola.descricaoMercadoria = map.get("DESMER");
					gondola.descricaoComplementarMercadoria = map.get("DESCRICAO_COMPLEMENTO");
					gondola.codigoFilialExpedicao = Util.getInteger(map.get("CODFILEPD"));
					gondola.codigoFilialFaturamento =Util.getInteger(map.get("CODFILFAT"));
					gondola.codigoChaveRegistro = map.get("CODCHVRGT");
					
					listarItensGondola.add(gondola);
				}
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}

		return listarItensGondola;
	}
	
	/**
	 * Método que obtem um determinado item na gôndola do cliente
	 * @param codigoCliente - código do cliente
	 * @param posicaoMercadoria - posição da mercadoria
	 * @return Gondola - informações da gôndola
	 * @throws Exception
	 */
	public static Gondola obterItemGondolaCliente(Integer codigoCliente, Integer posicaoMercadoria) throws Exception {

		Gondola gondola = null;
		try {
			
			StringBuilder queryTmp = new StringBuilder();
			
			queryTmp.append("SELECT GONDOLA.PCOCLI, GONDOLA.QDEMERANT, GONDOLA.QDEMERATU, GONDOLA.DATULTVND, "); //GONDOLA.CODMER, GONDOLA.CODFILEPD, GONDOLA.CODFILFAT, ");
			queryTmp.append("TEMPORARIA.VLRUNTIMP ");
			queryTmp.append("FROM PCABGM GONDOLA ");
			queryTmp.append("LEFT JOIN TMPMEGO TEMPORARIA ON GONDOLA.CODCLI = TEMPORARIA.CODCLI AND GONDOLA.POSMER = TEMPORARIA.POSMER ");
			queryTmp.append("WHERE GONDOLA.CODCLI = %s ");
			queryTmp.append("AND GONDOLA.POSMER = %s ");
			
			String query = DatabaseUtil.montaQuery(queryTmp.toString(), codigoCliente, posicaoMercadoria);

			List<Map<String, String>> result = DatabaseFactory.getInstance().executeQuery(query);
			
			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				gondola = new Gondola();
				
//				gondola.codigoCliente = codigoCliente;
//				gondola.posicaoMercadoria = posicaoMercadoria;
//				gondola.codigoMercadoria = Util.getInteger(map.get("CODMER"));
				gondola.precoCliente = Util.getBigDecimal(map.get("PCOCLI"));
				gondola.quantidadeMercadoriaAnterior = Util.getInteger(map.get("QDEMERANT"));
				gondola.quantidadeMercadoriaAtual = Util.getInteger(map.get("QDEMERATU"));
				gondola.dataUltimaVenda = DateUtil.formataData(DateUtil.formataData(map.get("DATULTVND"), DateUtil.DEFAULT_DATE_DATABASE), DateUtil.DEFAULT_DATE_PATTERN);
//				gondola.codigoFilialExpedicao = Util.getInteger(map.get("CODFILEPD"));
//				gondola.codigoFilialFaturamento =Util.getInteger(map.get("CODFILFAT"));
				gondola.valorUnitarioImposto = Util.getBigDecimal(map.get("VLRUNTIMP"));
				
				//jgomes - pendência para recuperar o campo
				//gondola.valorUnitarioImposto = BigDecimal.valueOf(0.3037d);
				
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}

		return gondola;
	}
	
	/**
	 * Obtem a lista de itens da gondola temporária do cliente
	 * @return List<Gondola> - lista com os itens da gondola do cliente
	 * @throws Exception
	 */
	public static List<Gondola> listarItensTemporariosGondolaCliente(Integer codigoCliente) throws Exception {

		List<Gondola> listarItensGondola = new ArrayList<Gondola>();
		Gondola gondola = null;
		
		try {

			StringBuilder queryTmp = new StringBuilder();
			queryTmp.append("SELECT TEMPORARIA.TIPPMC, TEMPORARIA.POSMER, TEMPORARIA.QDEMERPED, TEMPORARIA.DESMER, TEMPORARIA.CODFILFAT, TEMPORARIA.TIPMERESG, ");
			queryTmp.append("TEMPORARIA.VLRUNTIMP, TEMPORARIA.PCOCLI, TEMPORARIA.MRGLCRCLI, TEMPORARIA.QDEMERANT, TEMPORARIA.QDEMERATU, TEMPORARIA.QDECXAFRN, ");
			queryTmp.append("TEMPORARIA.VLRLIQMER, TEMPORARIA.VLRLIQIMP, TEMPORARIA.UNTCXAIMP, TEMPORARIA.TIPMCOREP, TEMPORARIA.FLGPEE, TEMPORARIA.CODCNDPGT, ");
			queryTmp.append("TEMPORARIA.DATULTVND, TEMPORARIA.VLRFRTMER, TEMPORARIA.NUMNOTFSC, TEMPORARIA.TIPICT, TEMPORARIA.CODFLXPCO, TEMPORARIA.CODSMBSIT, ");
			queryTmp.append("TEMPORARIA.PERICMMER, TEMPORARIA.CODFILEPD, TEMPORARIA.VLRSTBMER, TEMPORARIA.CMSCNSMER, TEMPORARIA.DESNGCMER, TEMPORARIA.VLRPTOMER, ");
			queryTmp.append("GONDOLA.CODMER ");
			queryTmp.append("FROM PCABGM GONDOLA ");
			queryTmp.append("INNER JOIN TMPMEGO TEMPORARIA ON GONDOLA.CODCLI = TEMPORARIA.CODCLI AND GONDOLA.POSMER = TEMPORARIA.POSMER ");
			queryTmp.append("WHERE GONDOLA.CODCLI = %s ");
			
			String query = DatabaseUtil.montaQuery(queryTmp.toString(), codigoCliente);

			List<Map<String, String>> result = DatabaseFactory.getInstance().executeQuery(query);
			
			if (!result.isEmpty()) {
				for (Map<String, String> map : result) {
					gondola = new Gondola();
					
					gondola.tipoPromocao = map.get("TIPPMC");
					gondola.posicaoMercadoria = Util.getInteger(map.get("POSMER"));
					
					gondola.codigoMercadoria = Util.getInteger(map.get("CODMER"));
					
					gondola.quantidadeMercadoriaPedido = Util.getInteger(map.get("QDEMERPED"));
					gondola.descricaoMercadoria = map.get("DESMER");
					gondola.codigoFilialFaturamento = Util.getInteger(map.get("CODFILFAT"));
					gondola.tipoMercadoriaEsgotamento = map.get("TIPMERESG");
					
					gondola.valorUnitarioImposto = Util.getBigDecimal(map.get("VLRUNTIMP"));
					gondola.precoCliente = Util.getBigDecimal(map.get("PCOCLI"));
					gondola.margemLucroCliente = Util.getBigDecimal(map.get("MRGLCRCLI"));
					gondola.quantidadeMercadoriaAnterior = Util.getInteger(map.get("QDEMERANT"));
					gondola.quantidadeMercadoriaAtual = Util.getInteger(map.get("QDEMERATU"));
					gondola.quantidadeCaixaFornecedor =Util.getInteger(map.get("QDECXAFRN"));
					
					gondola.valorLiquidoMercadoria = Util.getBigDecimal(map.get("VLRLIQMER"));
					gondola.valorLiquidoImposto = Util.getBigDecimal(map.get("VLRLIQIMP"));
					gondola.valorCaixaImposto = Util.getBigDecimal(map.get("UNTCXAIMP"));
					gondola.tipoMarcacaoRepresentante = map.get("TIPMCOREP");
					gondola.flagPee = map.get("FLGPEE");
					gondola.codigoCondicaoPagamento = Util.getInteger(map.get("CODCNDPGT"));
					
					gondola.dataUltimaVenda = map.get("DATULTVND");
					gondola.valorFreteMercadoria = Util.getBigDecimal(map.get("VLRFRTMER"));
					gondola.numeroNotaFiscal = Util.getInteger(map.get("NUMNOTFSC"));
					gondola.tipoIncentivo = map.get("TIPICT");
					gondola.codigoFlexivelPreco = map.get("CODFLXPCO");
					gondola.codigoSimboloSituacao = map.get("CODSMBSIT");
					
					gondola.percentualIcmsMercadoria = Util.getBigDecimal(map.get("PERICMMER"));
					gondola.codigoFilialExpedicao = Util.getInteger(map.get("CODFILEPD"));
					gondola.valorStbMercadoria = Util.getBigDecimal(map.get("VLRSTBMER"));
					gondola.comissaoConcessaoMercadoria = Util.getBigDecimal(map.get("CMSCNSMER"));
					gondola.descricaoNegociacaoMercadoria = map.get("DESNGCMER");
					gondola.valorPontosMercadoria = map.get("VLRPTOMER");
					
					listarItensGondola.add(gondola);
				}
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}

		return listarItensGondola;
	}

	/**
	 * Deletar item da gondola do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @param posicaoMercadoria - posicao da mercadoria na gondola
	 * @return boolean - delecao efetuada com sucesso ou falha
	 */
	public static boolean deletarItemGondola(Integer codigoCliente, Integer posicaoMercadoria) throws Exception {
		boolean sucesso = true;
		
		Database database = DatabaseFactory.getInstance();
		TransactionManager transactionManager = null;
		
		try {

			transactionManager = database.getTransactionManager();
			transactionManager.beginTransaction();
			
			deletarGondola(transactionManager, "TMPMEGO" , codigoCliente, posicaoMercadoria);
			deletarGondola(transactionManager, "PCABGM" , codigoCliente, posicaoMercadoria);
			
			transactionManager.commitTransaction();
			
		} catch (SQLException e) {
			transactionManager.rollbackTransaction();
			Log.e(TAG, e.getLocalizedMessage(), e);
			sucesso = false;
		} finally {
			transactionManager.endTransaction();
		}	
			
		return sucesso;

	}

	/**
	 * Deletar item da gondola do cliente
	 * @param transactionManager - transação ativa
	 * @param nomeTabela - nome da tabela
	 * @param codigoCliente - codigo do cliente
	 * @param posicaoMercadoria - posicao da mercadoria na gondola
	 * @throws Exception - delecao efetuada com sucesso ou falha
	 */
	private static void deletarGondola(TransactionManager transactionManager, String nomeTabela, Integer codigoCliente, Integer posicaoMercadoria) throws SQLException {
		try {

			String whereClause = "CODCLI = ? AND POSMER = ?";
			String[] whereValues = new String[] { codigoCliente.toString(), posicaoMercadoria.toString()};

			int deleted = transactionManager.delete(nomeTabela, whereClause, whereValues);
			Log.i(TAG, String.format("deleta:%s registros afetados", deleted));
			 
		} catch (SQLException e) {
			Log.e(TAG, e.getLocalizedMessage());
			throw e;
		}

	}
	
	/**
	 * Método para obter a última posição da mercadoria na gondola do cliente
	 * @param codigoCliente - codigo do cliente
	 * @return int - última posição da mercadoria
	 * @throws SQLException
	 */
	public static int obterUltimaPosicaoMercadoriaGondola(Integer codigoCliente) throws SQLException {		
		StringBuilder queryTmp = new StringBuilder();
		queryTmp.append("SELECT MAX(POSMER) AS POSICAO ");
		queryTmp.append("FROM PCABGM ");
		queryTmp.append("WHERE CODCLI = %s ");
		
		Integer posicaoGondola = 0;
		
		try {
			
			String query = DatabaseUtil.montaQuery(queryTmp.toString(), codigoCliente);

			List<Map<String, String>> result = DatabaseFactory.getInstance().executeQuery(query);
			
			if (!result.isEmpty() && result.get(0).get("POSICAO") != null) {
				posicaoGondola = Util.getInteger(result.get(0).get("POSICAO"));
			}
				
		} catch (SQLException e) {
			Log.e(TAG, e.getLocalizedMessage());
			throw e;
		}
		
		return posicaoGondola;		
	}
	
	/**
	 * Método para cadastrar item na gondola do cliente
	 * @param gondola - informações do item
	 * @return boolean - true para sucesso ou false para erro
	 * @throws Exception
	 */
	public static boolean cadastrarItemGondola(Gondola gondola) throws Exception {
		boolean sucesso = true;
		
		Database database = DatabaseFactory.getInstance();
		TransactionManager transactionManager = null;
		
		try {

			transactionManager = database.getTransactionManager();
			transactionManager.beginTransaction();

			Map<String, String> values = new TreeMap<String, String>();
			values.put("CODMER", gondola.codigoMercadoria.toString());
			values.put("CODCLI", gondola.codigoCliente.toString());
			values.put("POSMER", gondola.posicaoMercadoria.toString());
			values.put("PCOCLI", gondola.precoCliente.toString());
			values.put("DATULTVND", gondola.dataUltimaVenda);
			values.put("QDEMERANT", gondola.quantidadeMercadoriaAnterior.toString());
			values.put("QDEMERATU", gondola.quantidadeMercadoriaAtual.toString());
			values.put("DATETQATU", DateUtil.formataData(gondola.dataAtual, DateUtil.DEFAULT_DATE_DATABASE));
			values.put("CODFILEPD", gondola.codigoFilialExpedicao.toString());
			values.put("CODFILFAT", gondola.codigoFilialFaturamento.toString());
			values.put("CODCHVRGT", gondola.codigoChaveRegistro);
			
			/*
			CREATE TABLE [PCABGM] (
					  [CODMER] Integer, 
					  [CODCLI] Integer, 
					  [POSMER] Integer, 
					  [PCOCLI] Numeric(8,2), 
					  [DATULTVND] Character(8), 
					  [QDEMERANT] Integer, 
					  [QDEMERATU] Integer, 
					  [DATETQATU] Character(8), 
					  [CODFILEPD] Integer, 
					  [CODFILFAT] Integer, 
					  [CODCHVRGT] Character(30), 
					  CONSTRAINT [] PRIMARY KEY ([CODCLI], [POSMER]));
			
			*/
			
			transactionManager.insert("PCABGM", values);
			transactionManager.commitTransaction();
			
		} catch (SQLException e) {
			transactionManager.rollbackTransaction();
			Log.e(TAG, e.getLocalizedMessage(), e);
			sucesso = false;
		} finally {
			transactionManager.endTransaction();
		}	

		return sucesso;
	}
	
	/**
	 * Método que altera um determinado item na gôndola do cliente
	 * @param gondola - informações a serem alteradas
	 * @return boolean - true para sucesso ou false para erro
	 * @throws Exception
	 */
	public static boolean alterarItemGondola(Gondola gondola) throws Exception {
		boolean sucesso = true;
		
		Database database = DatabaseFactory.getInstance();
		TransactionManager transactionManager = null;
		
		try {
			  
			transactionManager = database.getTransactionManager();
			transactionManager.beginTransaction();

			Map<String, String> values = new TreeMap<String, String>();
			values.put("PCOCLI", gondola.precoCliente.toString());
			values.put("QDEMERATU", gondola.quantidadeMercadoriaAtual.toString());
			values.put("DATETQATU", DateUtil.formataData(gondola.dataAtual, DateUtil.DEFAULT_DATE_DATABASE));
			
			String whereClause = "CODCLI = ? AND POSMER = ?";
			String[] whereValues = new String[] {gondola.codigoCliente.toString(), gondola.posicaoMercadoria.toString()};
			
			transactionManager.update("PCABGM", values, whereClause, whereValues);
			transactionManager.commitTransaction();
			
		} catch (SQLException e) {
			transactionManager.rollbackTransaction();
			Log.e(TAG, e.getLocalizedMessage(), e);
			sucesso = false;
		} finally {
			transactionManager.endTransaction();
		}	

		return sucesso;
	}

/*	private static void cadastrarItemGondolaTemporaria(TransactionManager transactionManager, Gondola gondola) throws Exception {
		try {

			Map<String, String> values = new TreeMap<String, String>();
				values.put("TIPPMC", gondola.tipoPromocao.toString());
			values.put("POSMER", gondola.posicaoMercadoria.toString());
			values.put("QDEMERPED", gondola.quantidadeMercadoriaPedido.toString());
			values.put("DESMER", gondola.descricaoMercadoria);
			values.put("CODFILFAT", gondola.codigoFilialFaturamento.toString());
				values.put("TIPMERESG", gondola.tipoMercadoriaEsgotamento.toString());
				values.put("VLRUNTIMP", gondola.valorUnitarioImposto.toString());
			values.put("PCOCLI", gondola.precoCliente.toString());
				values.put("MRGLCRCLI", gondola.margemLucroCliente.toString());
			values.put("QDEMERANT", gondola.quantidadeMercadoriaAnterior.toString());
			values.put("QDEMERATU", gondola.quantidadeMercadoriaAtual.toString());
			values.put("QDECXAFRN", gondola.quantidadeCaixaFornecedor.toString());
				values.put("VLRLIQMER", gondola.valorLiquidoMercadoria.toString());
				values.put("VLRLIQIMP", gondola.valorLiquidoImposto.toString());
				values.put("UNTCXAIMP", gondola.valorCaixaImposto.toString());
				values.put("TIPMCOREP", gondola.tipoMarcacaoRepresentante.toString());
				values.put("FLGPEE", gondola.flagPee.toString());
			values.put("CODCNDPGT", gondola.codigoCondicaoPagamento.toString());
			values.put("DATULTVND", gondola.dataUltimaVenda);
				values.put("VLRFRTMER", gondola.valorFreteMercadoria.toString());
			values.put("NUMNOTFSC", gondola.numeroNotaFiscal.toString());
				values.put("TIPICT", gondola.tipoIncentivo);
				values.put("CODFLXPCO", gondola.codigoFlexivelPreco.toString());
				values.put("CODSMBSIT", gondola.codigoSimboloSituacao.toString());
				values.put("PERICMMER", gondola.percentualIcmsMercadoria.toString());
			values.put("CODFILEPD", gondola.codigoFilialExpedicao.toString());
			//values.put("PERVLRMNM", gondola.v.toString());
			values.put("CMSCNSMER", gondola.comissaoConcessaoMercadoria.toString());
			values.put("DESNGCMER", gondola.descricaoNegociacaoMercadoria);
			values.put("VLRPTOMER", gondola.valorPontosMercadoria.toString());
			//values.put("INDNVO", gondola..toString());
			values.put("CODMER", gondola.codigoMercadoria.toString());
			//values.put("VLRBRTMER", gondola.valorb);
		//	values.put("PERDSCFLX", gondola..toString());
			//values.put("CODPMC", gondola.codigop.toString());
		//	values.put("TIPMERDSC", gondola.topomer);
			values.put("CODCLI", gondola.codigoCliente.toString());
		//	values.put("FLGSTSITE", gondola..toString());
		//	values.put("CODRGRDTB", gondola.codigor.toString());
		//	values.put("VLRBRTCXA", gondola.valorb);
		//	values.put("VLRLIQCXA", gondola.valorl.toString());
		//	values.put("VLRBRTFRC", gondola.valorbru.toString());
			values.put("CODCHVRGT", gondola.codigoChaveRegistro);
			//values.put("PERACOTTC", gondola..toString());
		//	values.put("PERMAXSMP", gondola.codigoFilialExpedicao.toString());
		//	values.put("PERACRCNS", gondola.codigoFilialFaturamento.toString());
		//	values.put("VLRBRTTMP", gondola.);
			values.put("VLRSTBMER", gondola.valorStbMercadoria.toString());
		//	values.put("CMSMAXMER", gondola.);
		//	values.put("CODMERPCP", gondola..toString());
		//	values.put("VLRRDCITE", gondola.v.toString());
		//	values.put("LIQCXAIMP", gondola.);
		//	values.put("VLRDSCESP", gondola..toString());
		//	values.put("VLRSTBTOT", gondola..toString());
		//	values.put("STBTOTBNF", gondola.s.toString());
		//	values.put("PERDSCMNM", gondola.codigoChaveRegistro);
		//	values.put("VLRMAXISN", gondola..toString());
		//	values.put("VLRBNFMER", gondola.codigoChaveRegistro);
		//	values.put("TIPNGCMER", gondola..toString());
		//	values.put("PERMAXFLX", gondola.codigoFilialFaturamento.toString());
		//	values.put("VLRIPIMER", gondola.);
		//	values.put("VLRIPITOT", gondola.dataAtual.toString());
		//	values.put("IPITOTBNF", gondola.codigoFilialExpedicao.toString());
		//	values.put("INDRTCBFV", gondola..toString());
		//	values.put("INDRTCBFB", gondola.codigoChaveRegistro);
		//	values.put("INDRTCBNF", gondola.codigoFilialFaturamento.toString());
		//	values.put("INDITEVND", gondola.codigoChaveRegistro);
		//	values.put("INDMERKIT", gondola.codigoFilialExpedicao.toString());
		//	values.put("RTCBFVMER", gondola..toString());
		//	values.put("INDSEMMRGL", gondola.);
	//		values.put("CODCORMRG", gondola.codigo.toString());
		//	values.put("PERACOCLI", gondola.codigoFilialExpedicao.toString());
		//	values.put("VLRPTOBFC", gondola.valorp.toString());
		//	values.put("CODCORMRGB", gondola.cod);
		//	values.put("VLRMNMMER", gondola.valor.toString());
		//	values.put("PERACRVTL", gondola.codigoFilialFaturamento.toString());
		//	values.put("INDITEIMU", gondola.codigoChaveRegistro);
			
			CREATE TABLE TMPMEGO (
			
			transactionManager.insert("TMPMEGO", values);
			 
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw new Exception(e.getMessage());
		}

	}*/
		
}