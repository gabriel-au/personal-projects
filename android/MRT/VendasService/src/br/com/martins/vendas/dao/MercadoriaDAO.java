package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.ECMService;
import br.com.martins.vendas.services.FilialService;
import br.com.martins.vendas.vo.DetalheMercadoria;
import br.com.martins.vendas.vo.ECM;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Quota;
import br.com.martins.vendas.vo.RestricaoMercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class MercadoriaDAO {

	private static final String	TAG	= MercadoriaDAO.class.getName();

	/**
	 * Recupera dados de livro de preço
	 * 
	 * @param codFilialExp
	 *            código filial expedição
	 * @param codFilialFat
	 *            código filial faturamento
	 * @param numRelacaoCidade
	 *            numero relação cidade
	 * @param codMercadoria
	 *            código da mercadoria
	 * @return instância da classe LivroPreco
	 * @throws SQLException
	 *             em caso de erro na execução da query
	 */
	public static DetalheMercadoria recupera(Integer codMercadoria, Integer codFilialExpedicaoMercadoria, Integer codFilialFaturamentoMercadoria, Integer codCliente, Integer codTerCliente, String codEstadoDestino) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("     SELECT MER.CODMER, 					            ");
		sql.append("            MER.DESMER, 					            ");
		sql.append("            MER.CODGRPMER, 					            ");
		sql.append("            MER.CODBRRMER, 					            ");
		sql.append("            MER.INDMERKIT, 					            ");
		sql.append("            MER.DESUNDVND, 					            ");
		sql.append("            FML.DESFMLMER, 					            ");
		sql.append("            SUBCAT.DESTERCHV PCHSUB,		            ");
		sql.append("            FORNEC.DESTERCHV PCHFOR, 		            ");
		sql.append("            LVR.TIPICTMER,  					        "); 	
		sql.append("            CASE                                        ");
		sql.append("				 WHEN MER.QDECXAFRN <= 0 THEN 1         ");
		sql.append("		         ELSE MER.QDECXAFRN                     ");
		sql.append("            END QDECXAFRN                               ");
		sql.append("       	    FROM CADMER MER                             ");
		sql.append("INNER JOIN  PCALVR LVR    				                ");
		sql.append("         ON LVR.CODMER = MER.CODMER                     ");
		sql.append("        AND LVR.CODFILEPD = %s           		        ");
		sql.append("        AND LVR.CODFILFAT = %s                          ");
		sql.append("INNER JOIN  PCAFML FML 									");
		sql.append("         ON FML.CODGRPMER = MER.CODGRPMER               ");
		sql.append("        AND FML.CODFMLMER = MER.CODFMLMER               ");
		sql.append(" INNER JOIN PCAPCH FORNEC                               ");
		sql.append("         ON FORNEC.CODTERCHV = MER.CODTERCHV2           ");
		sql.append("        AND FORNEC.TIPTERCHV = 2                        ");
		sql.append("  LEFT JOIN PCAPCH SUBCAT                               ");
		sql.append("         ON SUBCAT.CODTERCHV = MER.CODTERCHV2           ");
		sql.append("        AND SUBCAT.TIPTERCHV = 1                        ");
		sql.append("      WHERE MER.CODMER = %s                             ");

		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery(sql, codFilialExpedicaoMercadoria,codFilialFaturamentoMercadoria, codMercadoria));
			if (!result.isEmpty()) {

				Map<String, String> map = result.get(0);

				DetalheMercadoria detalhe = new DetalheMercadoria();

				detalhe.codigo 		 = Util.getInteger(map.get("CODMER"));
				detalhe.descricao 	 = map.get("DESMER");
				detalhe.fornecedor 	 = map.get("PCHFOR");
				detalhe.categoria 	 = map.get("DESFMLMER");
				detalhe.subCategoria = map.get("PCHSUB");
				detalhe.codigoGrupo  = Util.getInteger(map.get("CODGRPMER"));

				detalhe.quantidadeCaixaFechada 	= Util.getInteger(map.get("QDECXAFRN"));
				
				detalhe.unidadeVenda 			= map.get("DESUNDVND");

				detalhe.esgotamentoIncentivo 	= map.get("TIPICTMER");

				detalhe.kit 					= map.get("INDMERKIT").equals("1");
				
				detalhe.codigoBarras 			= map.get("CODBRRMER");
		
				detalhe.dataVencimento			= recuperaDataVencimento(codFilialExpedicaoMercadoria, codMercadoria);

				detalhe.qtdMinima 				= recuperaQuantidadeMinima(codFilialExpedicaoMercadoria, codFilialFaturamentoMercadoria, codMercadoria, codCliente, codTerCliente, detalhe.quantidadeCaixaFechada, detalhe.kit).quantidadeMininaVenda;
				
				detalhe.quota 					= recuperaQuota(codFilialExpedicaoMercadoria, codEstadoDestino, codMercadoria);
				
				return detalhe;
			}

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());

		}
		return null;
	}
	
	/**
	 * Recupera dados de livro de preço
	 * 
	 * @param codFilialExp
	 *            código filial expedição
	 * @param codFilialFat
	 *            código filial faturamento
	 * @param numRelacaoCidade
	 *            numero relação cidade
	 * @param codMercadoria
	 *            código da mercadoria
	 * @return instância da classe LivroPreco
	 */
	public static List<Integer> recuperaComportamentoDeCompras(Integer codCliente) {
	
		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery("SELECT CODMER FROM PCASGS WHERE CODCLI = %s ORDER BY NUMPRRMER", codCliente));
			if (result.isEmpty()) {
				return Collections.emptyList();
			}		
			
			int size = result.size();
			List<Integer> list = new ArrayList<Integer>(size); 
			for (int count = 0; count < size; count++) {
				list.add(Integer.parseInt(result.get(count).get("CODMER")));
			}
			
			return list;
			
		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}	
			
	}
	/**
	 * Recuperar dadaVencimento da Mercadoria
	 * 
	 * @param codigoFilialExpedicao
	 *            codigo da filial do item
	 * 
	 * @param codigoProduto
	 *            código da mercadoria
	 * 
	 * @return objeto date correspondente a data do vencimento
	 */
	public static Date recuperaDataVencimento(Integer codigoFilialExpedicao, Integer codigoProduto) {

		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase
					.executeQuery(DatabaseUtil.montaQuery("SELECT DATVNCMER FROM PCAVNC WHERE CODFILEPD = %s AND CODMER = %s", codigoFilialExpedicao, codigoProduto));

			if (!result.isEmpty()) {

				return DateUtil.converteStringToDate(result.get(0).get("DATVNCMER"));

			}

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage(), e);
		}

		return null;
	}
	
	/**
	 * Recupera quantidade de cotas para a mercadoria
	 * 
	 * @param codFilialExpedicao
	 *            código filial de expedição da agenda
	 * 
	 * @param codEstadoDestino
	 *            código estado de destino da agenda
	 * 
	 * @param codMercadoria
	 *            código da mercadoria
	 * 
	 * @return
	 */
	public static Integer recuperaQuota(Integer codFilialExpedicao, String codEstadoDestino, Integer codMercadoria) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT CODFILEPD, ");
		sql.append("	     CODESTDSN, ");
		sql.append("		 CODMER, ");
		sql.append("		 QDEQTAMER ");
		sql.append("    FROM PCAQTA ");
		sql.append("   WHERE CODFILEPD = %s ");
		sql.append("     AND CODESTDSN = '%s' ");
		sql.append("	 AND CODMER    = %s ");

		try {

			Database dataBase = DatabaseFactory.getInstance();
			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery(sql.toString(), codFilialExpedicao, codEstadoDestino, codMercadoria));

			if (!result.isEmpty()) {

				Map<String, String> map = result.get(0);

				Quota quota = new Quota();

				quota.codFilialExpedicao 	= Util.getInteger(map.get("CODFILEPD"));
				quota.codEstadoDestino 		= map.get("CODESTDSN");
				quota.codMercadoria 		= Util.getInteger(map.get("CODMER"));
				quota.qtdQuotaMercadoria 	= Util.getInteger(map.get("QDEQTAMER"));

				return quota.qtdQuotaMercadoria;
			}

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage(), e);
		}

		return null;
	}
	
	/**
	 * Recupera quantidade mínima de venda
	 * 
	 * @param codFilialExpedicao
	 *            código filial expedição do item (mercadoria)
	 * 
	 * @param codFilialFaturamento
	 *            código filial faturamento do item (mercadoria)
	 * 
	 * @param codMercadoria
	 *            código da mercadoria
	 * 
	 * @param codCliente
	 *            código do cliente
	 * 
	 * @param terCliente
	 *            código do território do cliente
	 * 
	 * @param qtdCaixaFechada
	 *            quantidade de mercadorias em uma caixa fechada
	 * 
	 * @param kit
	 *            <code>true</code> Mercadoria faz para de KIT, <code>false<code> não faz.
	 * 
	 * @return quantidade minima de venda
	 */
	public static ECM recuperaQuantidadeMinima(Integer codFilialExpedicao, Integer codFilialFaturamento, Integer codMercadoria, Integer codCliente, Integer terCliente, Integer qtdCaixaFechada,
			boolean kit) {

		int quantidadeMininaVenda = 0;

		ECM ecm = ECMService.recuperarECM(codFilialExpedicao, codMercadoria);
		if (ecm == null) {
			ecm = new ECM(); 
			ecm.quantidadeMininaVenda = quantidadeMininaVenda;
			return ecm;
		}

		quantidadeMininaVenda = ecm.quantidadeMininaVenda;

		if (FilialService.verificaFilialSomenteVenda(codFilialExpedicao, codFilialFaturamento, codCliente, terCliente)) {

			if (kit) {

				if (ecm.quantidadeMinimaKit > 0) {
					quantidadeMininaVenda = ecm.quantidadeMinimaKit;
				}

			} else {

				if (ecm.quantidadeMinimaKit > 0) {

					quantidadeMininaVenda = ecm.quantidadeMinimaKit;

				} else {

					if (ecm.quantidadeMininaVenda <= qtdCaixaFechada) {

						quantidadeMininaVenda = qtdCaixaFechada;

					} else {

						int rest = ecm.quantidadeMininaVenda % qtdCaixaFechada;
						if (rest != 0) {

							int div = ecm.quantidadeMininaVenda / qtdCaixaFechada;

							quantidadeMininaVenda = (div * qtdCaixaFechada) + qtdCaixaFechada;
						}
					}

				}

			}

		}

		ecm.quantidadeMininaVenda = quantidadeMininaVenda; 
		return ecm;
	}
	
	public static Mercadoria buscaMercadoria(Integer codigoMercadoria) throws SQLException {
		StringBuilder query = new StringBuilder("");
		query
		.append("    SELECT DISTINCT IFNULL(SMR.CODMER, MER.CODMER) AS CODMER,                  ")
		.append("           MER.TIPMER,                                                         ")
		.append("    	    TRIM(MER.DESMER) || ' ' || IFNULL(SMR.DESSCTGRP, '') AS DESMER,     ")
		.append("    	    MER.QDECXAFRN,                                                      ")
		.append("    	    MER.FTRCNVUNT, IFNULL(SMR.INDMERKIT, MER.INDMERKIT) AS INDMERKIT,   ")
		.append("    	    MER.CODGRPNCM,                                                      ")
		.append("    	    MER.CODTERCHV1,                                                     ")
		.append("    	    SMR.CODMERPCP,                                                      ")
		.append("    	    MER.FLGPCPSMR,                                                      ")
		.append("    	    MER.INDRTCBNF                                                       ")
		.append("      FROM CADMER MER                                                          ")
		.append(" LEFT JOIN PCASMR SMR ON MER.CODMER = SMR.CODMERPCP AND MER.FLGPCPSMR = '*'    ")
		.append("     WHERE (MER.CODMER = %S OR SMR.CODMER = %S)                                "); 
		Database db = DatabaseFactory.getInstance();
		
		String queryAux = DatabaseUtil.montaQuery(query.toString(), codigoMercadoria, codigoMercadoria);
		List<Map<String, String>> result = db.executeQuery(queryAux);
		return carregarListaMercadoria(result).get(0);
	}
	
	public static List<Mercadoria> carregarListaMercadoria(List<Map<String,String>> resultQuery){

		Mercadoria mercadoria;
		List<Mercadoria> listaMercadoria = new ArrayList<Mercadoria>();
		for (Map<String,String> mercadoriaRs : resultQuery) {
			mercadoria = new Mercadoria();
			mercadoria.codigo   = Integer.parseInt(mercadoriaRs.get("CODMER"));
			mercadoria.tipo  = mercadoriaRs.get("TIPMER");
			mercadoria.descricao  = mercadoriaRs.get("DESMER");
			mercadoria.quantidadeCaixaFornecedor  = Util.getInteger(mercadoriaRs.get("QDECXAFRN"));
			mercadoria.fatorConversaoUnitario = Util.getInteger(mercadoriaRs.get("FTRCNVUNT"));
			mercadoria.indicaMercadoriaKit  = Util.getInteger(mercadoriaRs.get("INDMERKIT"));
			mercadoria.codigoGrupoNCM = mercadoriaRs.get("CODGRPNCM");
			mercadoria.codigoChaveCategoria = mercadoriaRs.get("CODTERCHV1");
			mercadoria.codigoGrupoFracionado  = Util.getInteger(mercadoriaRs.get("CODGRPFRC"));
			mercadoria.codigoMercadoriaPrincipal  = Util.getInteger(mercadoriaRs.get("CODMERPCP"));
			mercadoria.temMercadoriaSimilar = mercadoriaRs.get("flgpcpsmr") != null && "*".equals(mercadoriaRs.get("flgpcpsmr")) ? true : false;
			mercadoria.temRestricaoBeneficioCustomizado  = Util.getInteger(mercadoriaRs.get("INDRTCBNF"));
			listaMercadoria.add(mercadoria);
		}
		return listaMercadoria;
	}
	
	public static RestricaoMercadoria obtemRestricaoMercadoria(Integer codigoMercadoria, Integer codigoFilialExpedicao, Integer codigoAtividade, String siglaEstadoDestino){
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT RTM.CODMER, RTM.QDERTCITE, RTM.CODGRPRTC, ")
		.append("RTF.CODESTUNI, RTF.CODFILEPD, RTF.PERRTC, ")
		.append("RTA.CODATI ")
		.append("FROM PCARTM RTM ")
		.append("INNER JOIN PCARTF RTF ON RTM.CODGRPRTC = RTF.CODGRPRTC ")
		.append("LEFT JOIN PCARTA RTA ON RTM.CODGRPRTC = RTA.CODGRPRTC ")
		.append("WHERE RTM.CODMER = %s ")
		.append("AND RTF.CODFILEPD = %s ")
		.append("AND RTF.CODESTUNI = %s ")
		.append("AND RTA.CODATI = %s ");

		RestricaoMercadoria restricaoMercadoria = null;
		
		try {
			List<Map<String, String>> result =  DatabaseFactory.getInstance().executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoMercadoria, codigoFilialExpedicao, 
					siglaEstadoDestino, codigoAtividade));
			
			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				restricaoMercadoria = new RestricaoMercadoria();
				restricaoMercadoria.codigoMercadoria = Util.getInteger(map.get("CODMER"));
				restricaoMercadoria.quantidadeRestricaoItem = Util.getInteger(map.get("QDERTCITE"));
				restricaoMercadoria.codigoGrupoRestricao = Util.getInteger(map.get("CODGRPRTC"));
				restricaoMercadoria.codigoEstadoUnidade = map.get("CODESTUNI");
				restricaoMercadoria.codigoFilialExpedicao = Util.getInteger(map.get("CODFILEPD"));
				restricaoMercadoria.percentualRestricao = Util.getBigDecimal(map.get("PERRTC"));
				restricaoMercadoria.codigoAtividade = Util.getInteger(map.get("CODATI"));
			}
			
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		return restricaoMercadoria;
	}
	
}