package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.ClassificacaoNegociacao;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.DetalhePEE;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Preco;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class ItemDisponivelDAO {

	private static final String TAG = ItemDisponivelDAO.class.getName();
	private static Database db;

	private static final String QUERY_BUSCA_SE_MERCADORIA_EH_PSICOTROPICO = "SELECT codMer FROM pcapsc WHERE codmer = %s";
	private static final String QUERY_BUSCA_SE_GRUPO_EH_APENAS_DO_MIX = "SELECT flgacsmix FROM pcacgr where codgrpcli = %s";

	public static List<Item> listaItensDisponiveis(Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, Integer codigoRelacionamentoCliente, Integer codigoMercadoria) {
		
		List<String> parameters = new ArrayList<String>(4);
		StringBuilder sql = new StringBuilder();
		sql
		.append("      SELECT DISTINCT                                  ")
		.append("             MER.CODMER,                               ")
		.append("             MER.DESMER,                               ")
		.append("     	      MER.CODESLNGC,                            ")
		.append("     	      MER.TIPPDCMER,                            ")
		.append("     	      MER.QDECXAFRN,                            ")
		.append("     	      MER.INDRTCBFV,                            ")
		.append("     	      MER.INDRTCBFB,                            ")
		.append("     	      MER.INDRTCBNF,                            ")
		.append("     	      MER.FTRCNVUNT,                            ")
		.append("     	      MER.INDMERKIT,                            ")
		.append("     	      MER.INDRSTKIT,                            ")
		.append("     	      MER.TIPMER,                               ")
		.append("         	  COALESCE(ETQ.TIPESGMER, ' ') TIPESGMER,   ")
		.append("     	      LVR.CODMER LVR_CODMER,                    ")
		.append("     	      LVR.TIPICTMER,                            ")
		.append("     	      LVR.CODSMBSIT,                            ")
		.append("     	      LVR.CODFLXPCO,                            ")
		.append("     	      LVR.FLGPEE,                               ")
		.append("     	      LVR.TIPMCOREP,                            ")
		.append("     	      LVR.FLGMEREXV,                            ")
		.append("     	      (CASE WHEN                                ")
		.append("                  (SELECT COUNT(CODMER) FROM PCASMR SMR WHERE SMR.CODMER = MER.CODMER) == 0 THEN 'N' ELSE 'S' END ")
		.append("		      ) TEM_SIMILAR                             ")
		.append("        FROM CADMER MER                                ")
		.append("   LEFT JOIN PCALVR LVR ON LVR.CODMER = MER.CODMER     ")
		.append("         AND LVR.NUMRLCCID   = ?                       ")
		.append("	      AND LVR.CODFILEPD   = ?                       ")
		.append("         AND LVR.CODFILFAT   = ?                       ")		
		.append("   LEFT JOIN PCAETQ ETQ                                ")
		.append("          ON LVR.CODFILEPD   = ETQ.CODFILEPD           ")
		.append("   	  AND LVR.CODMER      = ETQ.CODMER              ")
		.append("       WHERE COALESCE(ETQ.TIPESGMER, ' ') <> 'F'       ");
		
		parameters.add(Util.getStringValue(codigoRelacionamentoCliente));
		parameters.add(Util.getStringValue(codigoFilialExpedicao));
		parameters.add(Util.getStringValue(codigoFilialFaturamento));
		
		if (codigoMercadoria != null) {                                    
			sql.append("  AND MER.CODMER      = ?                       ");
			parameters.add(Util.getStringValue(codigoMercadoria));         
		}                                                                  
		                                                                   
		
		String query = DatabaseUtil.montaQuery(sql);

		db = DatabaseFactory.getInstance();

		try {
			List<Map<String, String>> resultQuery = db.executeQuery(query, parameters.toArray(new String[]{}));
			if (!resultQuery.isEmpty()) {
				return convertResultToItem(resultQuery);
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return Collections.emptyList();
	}
	
	/**
	 * Obtem itens do kit.
	 *
	 * @param codigoMercadoriaKit the codigo mercadoria kit
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoFilialFaturamento the codigo filial faturamento
	 * @return the list
	 */
	public static List<Item> obtemItensDoKit(final Integer codigoMercadoriaKit, 
			final Integer codigoFilialExpedicao,
			final Integer codigoFilialFaturamento){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select mer.CODMER, mer.DESMER || cpl.DESCPLMER as Descricao ");
		sql.append(" from CADMER mer, PCAKIT kit, CADCPL cpl ");
		sql.append(" where kit.CODMER = mer.CODMER ");
		sql.append(" and cpl.CODMER = mer.CODMER ");
		sql.append(" and kit.CODFILEPD = %s ");
		sql.append(" and kit.CODFILFAT = %s  ");
		sql.append(" and kit.CODMERKIT = %s ");
		
		String query = DatabaseUtil.montaQuery(sql, codigoFilialExpedicao, 
				codigoFilialFaturamento, 
				codigoMercadoriaKit);
		db = DatabaseFactory.getInstance();
		
		List<Item> itensDoKit = null;
		
		try {
			itensDoKit = convertResultToItem(db.executeQuery(query));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return itensDoKit;
	}

	/**
	 * Método criado para ser utilizado em testes unitários para gerar massa de
	 * dados para os testes.
	 * 
	 * @return retorna uma lista de items.
	 */
	public static List<Item> listaTodosItensDisponiveis() {
		StringBuilder sql = new StringBuilder();
		sql.append("      SELECT preco.codmer, ");
		sql.append("             mercadoria.desmer, ");
		sql.append("     	     mercadoria.codeslngc, ");
		sql.append("     	     mercadoria.tippdcmer, ");
		sql.append("     	     mercadoria.qdecxafrn, ");
		sql.append("     	     mercadoria.indrtcbfv, ");
		sql.append("     	     mercadoria.indrtcbfb, ");
		sql.append("     	     mercadoria.indrtcbnf, ");
		sql.append("     	     mercadoria.ftrcnvunt, ");
		sql.append("     	     mercadoria.indmerkit, ");
		sql.append("     	     mercadoria.indrstkit, ");
		sql.append("     	     mercadoria.tipmer, ");
		sql.append("     	     preco.tipictmer, ");
		sql.append("     	     preco.codsmbsit, ");
		sql.append("     	     preco.codflxpco, ");
		sql.append("     	     preco.flgpee, ");
		sql.append("     	     preco.tipmcorep, ");
		sql.append("     	     (CASE WHEN ( ");
		sql.append("							SELECT COUNT(CODMER)");
		sql.append("							FROM PCASMR SMR ");
		sql.append("							WHERE SMR.CODMER = MERCADORIA.CODMER ");
		sql.append("			  			)== 0 THEN 'N' ELSE 'S' END ");
		sql.append("			  ) TEM_SIMILAR ");
		sql.append("        FROM CADMER mercadoria LEFT JOIN PCALVR preco ON mercadoria.codmer = preco.codmer ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		List<Item> itensDisponiveis = null;
		try {

			List<Map<String, String>> resultQuery = db.executeQuery(query);

			if (resultQuery.isEmpty()) {
				itensDisponiveis = Collections.emptyList();
			} else {
				itensDisponiveis = convertResultToItem(resultQuery);
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return itensDisponiveis;
	}

	/**
	 * Busca mercadoria por codigo.
	 * 
	 * @param codigoMercadoria
	 *            the codigo mercadoria
	 * @return the mercadoria
	 */
	public static Mercadoria buscaMercadoriaPorCodigo(Integer codigoMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select DESMER, QDECXAFRN, CODTERCHV1, TIPMER, CODGRPNCM, CODGRPFRC, QDEMNMVND, FLGMPLQDE, ");
		sql.append(" QDEMNMKIT ");
		sql.append(" from CADMER ");
		sql.append("where CODMER =  %s");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		Mercadoria mercadoria = null;
		try {
			List<Map<String, String>> result = db.executeQuery(query);

			List<Item> itens = convertResultToItem(result);

			if (itens.size() > 0) {
				mercadoria = itens.get(0).mercadoria;
				mercadoria.codigo = codigoMercadoria;
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return mercadoria;
	}

	/**
	 * Verifica se a mercadoria est� no MIX do grupamento do cliente.
	 * 
	 * @param codigo
	 * @param codigoGrupamentoCliente
	 * @return
	 */
	public static int consultaMercadoriaNoMixDoGrupoCliente(Integer codigo, Integer codigoGrupamentoCliente) {
		Database db = DatabaseFactory.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append(" FROM pcamix ");
		sql.append(" WHERE codmer = %s ");
		sql.append(" AND CODGRPCLI = %s");

		String query = DatabaseUtil.montaQuery(sql, codigo, codigoGrupamentoCliente);
		List<Map<String, String>> resultQuery = null;
		int codigoMix = 0;

		try {
			resultQuery = db.executeQuery(query);
			codigoMix = resultQuery.size();
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return codigoMix;
	}

	/**
	 * Busca se o grupamento do cliente busca as mercadorias apenas do MIX.
	 * 
	 * @param codigoGrupamentoCliente
	 * @return
	 */
	public static String buscaSeGrupoEhApenasDoMix(Integer codigoGrupamentoCliente) {
		StringBuilder sql = new StringBuilder(QUERY_BUSCA_SE_GRUPO_EH_APENAS_DO_MIX);

		List<Map<String, String>> resultQuery = null;

		String query = DatabaseUtil.montaQuery(sql, codigoGrupamentoCliente);
		db = DatabaseFactory.getInstance();

		String flagMix = "";
		try {
			resultQuery = db.executeQuery(query);

			if (resultQuery.size() > 0) {
				flagMix = resultQuery.get(0).get("FLGACSMIX");
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return flagMix;
	}

	/**
	 * Busca mercadoria no mix.
	 * 
	 * @param codigo
	 *            the codigo
	 * @param codigoGrupamentoCliente
	 *            the codigo grupamento cliente
	 * @return the int
	 */
	public static int buscaMercadoriaNoMix(Integer codigo, Integer codigoGrupamentoCliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT codmer ");
		sql.append(" FROM PCAMIX ");
		sql.append(" WHERE codmer = %s ");
		sql.append(" AND codgrpcli = %s ");

		List<Map<String, String>> resultQuery = null;
		int codigoMercadoriaMix = 0;
		db = DatabaseFactory.getInstance();

		try {
			String query = DatabaseUtil.montaQuery(sql, codigo, codigoGrupamentoCliente);
			resultQuery = db.executeQuery(query);

			codigoMercadoriaMix = resultQuery.size();
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
		}

		return codigoMercadoriaMix;
	}

	/**
	 * Busca se mercadoria eh psicotropico.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return true, if successful
	 */
	public static boolean buscaSeMercadoriaEhPsicotropico(Integer codigo) {
		String sql = QUERY_BUSCA_SE_MERCADORIA_EH_PSICOTROPICO;

		String query = DatabaseUtil.montaQuery(sql, codigo);
		db = DatabaseFactory.getInstance();
		boolean isPsicotropico = false;
		try {
			List<Map<String, String>> resultQuery = db.executeQuery(query);

			if (resultQuery.size() > 0) {
				isPsicotropico = true;
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
		}

		return isPsicotropico;
	}

	/**
	 * Busca classificacao negociacao do cliente.
	 * 
	 * @param codigoCliente
	 *            the codigo cliente
	 * @param codigoRepresentante
	 *            the codigo representante
	 * @return the lista de classificacao Negociacao
	 */
	public static List<ClassificacaoNegociacao> buscaClassificacaoNegociacaoDoCliente(Integer codigoCliente, Integer codigoRepresentante) {

		db = DatabaseFactory.getInstance();

		// TODO Verificar se ha lentidao
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT TET.CODCLI, TET.CODTETVND, TET.CODEDEVND, CLF.CODCLFNGC, CLF.CODESLNGC, CLF.NUMPRRVND ");
		sql.append("   FROM PCATET TET, PCACLF CLF,                                                               ");
		sql.append("     (                                                                                        ");
		sql.append(" 		  SELECT CLS.CODESLNGC, MIN(NUMPRRVND) AS NUMPRRVND                                   ");
		sql.append(" 			FROM PCACLF CLS, PCATET TERRIT                                                    ");
		sql.append(" 		   WHERE TERRIT.CODCLI    = ?                                                         ");
		sql.append(" 			 AND TERRIT.CODEDEVND = ?                                                         ");
		sql.append(" 		GROUP BY CLS.CODESLNGC                                                                ");
		sql.append("      ) TAB                                                                                   ");
		sql.append("  WHERE TET.CODCLI    = ?                                                                     ");
		sql.append("    AND TET.CODEDEVND = ?                                                                     ");
		sql.append("    AND TET.CODCLFNGC = CLF.CODCLFNGC                                                         ");
		sql.append("    AND CLF.CODESLNGC = TAB.CODESLNGC                                                         ");
		sql.append("  ORDER BY CLF.NUMPRRVND                                                                      ");

		List<ClassificacaoNegociacao> lista = new ArrayList<ClassificacaoNegociacao>();

		try {
			
			String query = DatabaseUtil.montaQuery(sql);
			
			String[] parameters = new String[4];
			parameters[0] = Util.getStringValue(codigoCliente);
			parameters[1] = Util.getStringValue(codigoRepresentante);
			parameters[2] = Util.getStringValue(codigoCliente);
			parameters[3] = Util.getStringValue(codigoRepresentante);
					
			List<Map<String, String>> resultQuery = db.executeQuery(query, parameters);
			lista = convertResultQueryToClassificacaoNegociacaoVO(resultQuery);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return lista;

	}

	/**
	 * Converte o resultado de uma query no objeto do tipo
	 * ClassificacaoNegociacao
	 * 
	 * @see ClassificacaoNegociacao
	 * @param resultQuery
	 * @return
	 */
	private static List<ClassificacaoNegociacao> convertResultQueryToClassificacaoNegociacaoVO(List<Map<String, String>> resultQuery) {

		List<ClassificacaoNegociacao> lista = new ArrayList<ClassificacaoNegociacao>();
		for (Map<String, String> registro : resultQuery) {
			ClassificacaoNegociacao classificacao = new ClassificacaoNegociacao();
		
			classificacao.codigoRepresentante 			= Integer.parseInt(registro.get("CODEDEVND"));
			
			classificacao.codigoClassificacaoNegociacao = Integer.parseInt(registro.get("CODCLFNGC"));

			classificacao.codigoEspecializacaoNegociacao = Integer.parseInt(registro.get("CODESLNGC"));
						
			lista.add(classificacao);
		}
		return lista;

	}

	/**
	 * Converte o resultado de uma query em um objeto do tipo
	 * <b>MercadoriaVO</b>
	 * 
	 * @see Mercadoria
	 * @param resultQuery
	 *            que � uma 'List<Map<String,String>>' onde cada Map tem a linha
	 *            do registro
	 * @return
	 */
	private static List<Item> convertResultToItem(List<Map<String, String>> resultQuery) {

		List<Item> itens = new ArrayList<Item>();

		for (Map<String, String> registro : resultQuery) {
			Item item = new Item();

			final Preco preco = item.preco;
			final Mercadoria mercadoria = item.mercadoria;			
				
			mercadoria.codigo 	 = Util.getInteger(registro.get("CODMER"));
			
			mercadoria.descricao = registro.get("Descricao");				
				
			mercadoria.codigoEspecialistaNegociacao = Util.getInteger(registro.get("CODESLNGC"), 0); 

			mercadoria.quantidadeCaixaFornecedor    = Util.getInteger(registro.get("QDECXAFRN"), 0);
			
			mercadoria.quantidadeMinimaKit          = Util.getInteger(registro.get("QDEMNMKIT"), 0);
			
			mercadoria.quantidadeMinimaVenda        = Util.getInteger(registro.get("QDEMNMVND"), 0);

			mercadoria.codigoChaveCategoria         = registro.get("CODTERCHV1");

			mercadoria.tipo                         = registro.get("TIPMER");

			mercadoria.flagMultiplicadorQuantidade  = registro.get("FLGMPLQDE");

			mercadoria.codigoGrupoFracionado        = Util.getInteger(registro.get("CODGRPFRC"));
			
			mercadoria.indicadorRestricaoKit        = Util.getInteger(registro.get("INDRSTKIT"));
			
			mercadoria.tipoProducaoMercadoria       = Util.getInteger(registro.get("TIPPDCMER"));
			
			mercadoria.tipoEsgotamento              = registro.get("TIPESGMER");

			mercadoria.temMercadoriaSimilar         = "S".equals(registro.get("TEM_SIMILAR")) ? true : false;

			preco.tipoIncentivoMercadoria   = registro.get("TIPICTMER");
			
			preco.codigoFlexivelPreco       = registro.get("CODFLXPCO");
				
			preco.codigoSimboloSituacao 	= registro.get("CODSMBSIT");
			
			preco.flagPontoEncontroEletronico = registro.get("FLGPEE");
			
			preco.tipoMarcacaoRepresentante = registro.get("TIPMCOREP");
			
			mercadoria.temRestricaoBrinde 	= Util.getInteger(registro.get("INDRTCBFV"), 0);
			
			mercadoria.temRestricaoBeneficioCustomizadoNoBrinde	= Util.getInteger(registro.get("INDRTCBFB"), 0);
			
			mercadoria.temRestricaoBeneficioCustomizado = Util.getInteger(registro.get("INDRTCBNF"), 0);

			mercadoria.fatorConversaoUnitario =  Util.getInteger(registro.get("FTRCNVUNT"));
			
			mercadoria.indicaMercadoriaKit    =  Util.getInteger(registro.get("INDMERKIT"));
			
			preco.flagMercadoriaExclusiva     = registro.get("FLGMEREXV");
			

			itens.add(item);

		}

		return itens;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoCliente
	 * @param codigoRepresentante
	 * @param codigoCidade
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 */
	public static Integer verificaAtendimentoMercadoria(Integer codigoFilialExpedicao, Mercadoria mercadoria, Integer codigoCliente, Integer codigoRepresentante, Integer codigoCidade, Integer codigoSupervisor, Integer codigoAtividade) {
		Database db = DatabaseFactory.getInstance();
		/*
		 * Necessário utilizar %% no formato da data para realizar o scape do
		 * caracter '%' no string format
		 */
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT TIPPNDMER FROM PCABLQ ");
		sql.append("  WHERE CODFILEPD = ? "); // codigo filial de expedicao
		sql.append("    AND CODMER    = ? "); // codigo Mercadoria
		sql.append("    AND ( ");
		sql.append("        ((tipabgpnd = 60) AND (codede = ?)) OR ");	// codCli - codigoCliente
		sql.append("        ((tipabgpnd = 50) AND (codede = ?)) OR ");	// codRep - codigoRepresentante
		sql.append("        ((tipabgpnd = 40) AND (codede = ?)) OR ");	// codCid - codigoCidade
		sql.append("        ((tipabgpnd = 3)  AND (codede = ?)) OR ");	// codSup - codigoSupervisor
		sql.append("        ((tipabgpnd = 2)  AND (codede = ?)) OR ");  // codAti - codigoAtividade
		sql.append("        ((tipabgpnd = 1)  AND (codede = 0))    ");
		sql.append("        ) ");
		sql.append("    AND STRFTIME('%%y%%m%%d', DATETIME('NOW')) < DATVLDPND ");
		sql.append("  ORDER BY codfilepd DESC, codmer DESC, tipabgpnd DESC, codede DESC, tippndmer DESC ");

		String query = DatabaseUtil.montaQuery(sql);
		String[] parameters = new String[7];
		parameters[0] = (Util.getStringValue(codigoFilialExpedicao));
		parameters[1] = (Util.getStringValue(mercadoria.codigo));
		parameters[2] = (Util.getStringValue(codigoCliente));
		parameters[3] = (Util.getStringValue(codigoRepresentante));
		parameters[4] = (Util.getStringValue(codigoCidade));
		parameters[5] = (Util.getStringValue(codigoSupervisor));
		parameters[6] = (Util.getStringValue(codigoAtividade));

		try {
			List<Map<String, String>> resultQuery = db.executeQuery(query, parameters);
			return resultQuery.size();
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Atualiza item pee.
	 * 
	 * @param codigoMercadoria
	 *            the codigo mercadoria
	 * @param codigoFilialExpedicao
	 *            the codigo filial expedicao
	 * @param codigoFilialFaturamento
	 *            the codigo filial faturamento
	 * @param numeroRelacaoCidade
	 *            the numero relacao cidade
	 */
	public static void atualizaItemPEE(final Integer codigoMercadoria, final Integer codigoFilialExpedicao, final Integer codigoFilialFaturamento, final Integer numeroRelacaoCidade, final String flag) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update FLGPEE set '%s' ");
		sql.append(" from PCALVR ");
		sql.append(" where CODMER = %s ");
		sql.append(" and CODFILEPD = %s ");
		sql.append(" amd CODFILFAT = %s ");
		sql.append(" amd NUMRLCCID = %s ");

		String query = DatabaseUtil.montaQuery(sql, flag, codigoMercadoria, codigoFilialExpedicao, codigoFilialFaturamento, numeroRelacaoCidade);
		db = DatabaseFactory.getInstance();

		try {
			db.executeQuery(query);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
	}
	
	public static List<DetalhePEE> obtemDetalhePEE(Item item, Cliente cliente) {
		
		StringBuilder sql = new StringBuilder();
		sql
		.append("    SELECT PRR.CODPRRPEE, PRR.IDTINFPEE, PRR.DESINFPEE, 0 DESCONTO, '' DATA_VIGORACAO                                   ")
		.append("      FROM PCAPRR PRR                                                                                                   ")
		.append("INNER JOIN PCALST LST ON PRR.IDTINFPEE = LST.IDTINFPEE                                                                  ")
		.append("INNER JOIN PCACMP CMP ON LST.CODMIXMER = CMP.CODMIXMER                                                                  ")
		.append(" WHERE LST.CODESTUNI  = ? ")                                                                           
		.append("       AND LST.CODATI = ? ")                                                              
		.append("       AND CMP.CODMER = ? ")                                                               
		.append("       AND PRR.INDMIXCMP = '*'                                                                                          ")
		.append("       AND (STRFTIME('%%Y%%m%%d', DATETIME('NOW')) BETWEEN PRR.DATINIVLD and PRR.DATFIMVLD OR TRIM(PRR.DATINIVLD) = '') ")
		.append("     UNION                                                                                                              ")
		.append("    SELECT PRR.CODPRRPEE, PRR.IDTINFPEE, PRR.DESINFPEE, 0 DESCONTO, '' DATA_VIGORACAO                                   ")
		.append("      FROM PCAPRR PRR                                                                                                   ")
		.append("INNER JOIN PCARAF RAF ON PRR.IDTINFPEE = RAF.IDTINFPEE                                                                  ")
		.append("     WHERE RAF.CODFILEPD = ? ")                                                        
		.append("       AND RAF.CODFILFAT = ? ")                                                      
		.append("       AND RAF.NUMRLCCID = ? ")                                               
		.append("       AND PRR.INDMIXCMP <> '*'                                                                                         ")
		.append("       AND RAF.CODMER    = ? ")                                                               
		.append("       AND (STRFTIME('%%Y%%m%%d', DATETIME('NOW')) < RAF.DATVALAFD )                                                    ")
		.append("     UNION                                                                                                              ")
		.append("    SELECT PRR.CODPRRPEE, PRR.IDTINFPEE, PRR.DESINFPEE, 0 DESCONTO, '' DATA_VIGORACAO                                   ")
		.append("      FROM PCAPRR PRR                                                                                                   ")
		.append("INNER JOIN PCALVR LVR ON PRR.IDTINFPEE = LVR.TIPMCOREP                                                                  ")
		.append("     WHERE LVR.CODFILEPD = ? ")                                                        
		.append("       AND LVR.CODFILFAT = ? ")                                                      
		.append("       AND LVR.NUMRLCCID = ? ")                                               
		.append("       AND PRR.INDMIXCMP <> '*'                                                                                         ")
		.append("       AND LVR.CODMER    = ? ")
		.append("       AND (STRFTIME('%%Y%%m%%d', DATETIME('NOW')) BETWEEN PRR.DATINIVLD and PRR.DATFIMVLD OR TRIM(PRR.DATINIVLD) = '') ")
		.append("     UNION                                                                                                              ")
		.append("    SELECT PRR.CODPRRPEE, PRR.IDTINFPEE, PRR.DESINFPEE, 0 DESCONTO, '' DATA_VIGORACAO                                   ")
		.append("      FROM PCAPRR PRR                                                                                                   ")
		.append("INNER JOIN PCAMIX MIX ON PRR.CODMIXBFC = MIX.CODGRPCLI                                                                  ")
		.append("     WHERE MIX.CODMER =  ?                                                                                              ")                                                                
		.append("       AND PRR.INDMIXCMP <> '*'                                                                                         ")
		.append("       AND PRR.CODMIXBFC > 0                                                                                            ")
		.append("       AND (STRFTIME('%%Y%%m%%d', DATETIME('NOW')) BETWEEN PRR.DATINIVLD and PRR.DATFIMVLD OR TRIM(PRR.DATINIVLD) = '') ")
		.append("	 UNION                                                                                                               ")
		.append("    SELECT MIN(1) AS CODPRRPEE, MIN('T') AS IDTINFPEE, MIN('ACAO TATICA') DESINFPEE,                                    ")
		.append("           SUM((1- FTRDSC) * 100) DESCONTO, MIN(DATFIMVGR) DATA_VIGORACAO                                               ")
		.append("      FROM PCAGDE GDE                                                                                                   ")
		.append("INNER JOIN PCAGDM GDM ON GDE.CODFILEPD = GDM.CODFILEPD                                                                  ")
		.append("       AND GDE.CODGRPDSC = GDM.CODGRPDSC                                                                                ")
		.append(" LEFT JOIN PCADUF DUF ON GDE.CODFILEPD = DUF.CODFILEPD                                                                  ")
		.append("       AND GDE.CODGRPDSC  = DUF.CODGRPDSC                                                                                ")
		.append("	    AND (DUF.CODESTDSN = '99' OR DUF.CODESTDSN = ?)                                                               ")
		.append(" LEFT JOIN PCADCD DCD ON GDE.CODFILEPD = DCD.CODFILEPD                                                                  ")
		.append("       AND GDE.CODGRPDSC = DCD.CODGRPDSC AND DUF.CODFILEPD = ?                                                          ")
		.append(" LEFT JOIN PCADAT DAT ON GDE.CODFILEPD = DAT.CODFILEPD                                                                  ")
		.append("       AND GDE.CODGRPDSC = DAT.CODGRPDSC                                                                                ")
		.append("	    AND DAT.CODATI = ?                                                                                               ")
		.append(" LEFT JOIN PCADGR DGR ON GDE.CODFILEPD = DGR.CODFILEPD                                                                  ")
		.append("       AND GDE.CODGRPDSC = DGR.CODGRPDSC AND DGR.CODGRPCLI = ?                                                          ")
		.append(" LEFT JOIN PCADCL DCL ON GDE.CODFILEPD = DCL.CODFILEPD                                                                  ")
		.append("       AND GDE.CODGRPDSC = DCL.CODGRPDSC                                                                                ")
		.append("	    AND (DCL.CODCLI = ? OR DCL.CODCLI = 9999999)                                                                     ")
		.append("     WHERE STRFTIME('%%Y%%m%%d', DATETIME('NOW')) BETWEEN GDE.DATINIVGR AND GDE.DATFIMVGR                               ")
		.append("       AND GDE.CODFILEPD = ?                                                                                            ")
		.append("       AND GDM.CODMER = ?                                                                                               ")
		.append("       AND (DUF.CODESTDSN IS NOT NULL OR DCD.CODCID IS NOT NULL)                                                        ")
		.append("       AND (DAT.CODATI IS NOT NULL OR DGR.CODGRPCLI IS NOT NULL OR DCL.CODCLI IS NOT NULL)                              ")
		.append("  ORDER BY 1                                                                                                            ");
		
		List<String> parameters = new ArrayList<String>(20);
		parameters.add(cliente.uf);
		parameters.add(Util.getStringValue(cliente.codigoAtividade));
		parameters.add(Util.getStringValue(item.mercadoria.codigo));
		//
		parameters.add(Util.getStringValue(item.codigoFilialExpedicao));
		parameters.add(Util.getStringValue(item.codigoFilialFaturamento));
		parameters.add(Util.getStringValue(cliente.numeroRelacionamentoCliente));
		parameters.add(Util.getStringValue(item.mercadoria.codigo));
		//
		parameters.add(Util.getStringValue(item.codigoFilialExpedicao));
		parameters.add(Util.getStringValue(item.codigoFilialFaturamento));
		parameters.add(Util.getStringValue(cliente.numeroRelacionamentoCliente));
		parameters.add(Util.getStringValue(item.mercadoria.codigo));
		//
		parameters.add(Util.getStringValue(item.mercadoria.codigo));
		parameters.add(cliente.uf);
		parameters.add(Util.getStringValue(item.codigoFilialExpedicao));
		parameters.add(Util.getStringValue(cliente.codigoAtividade));
		parameters.add(Util.getStringValue(cliente.codigoGrupoCliente));
		//
		parameters.add(Util.getStringValue(cliente.codigoCliente));
		parameters.add(Util.getStringValue(item.codigoFilialExpedicao));
		parameters.add(Util.getStringValue(item.mercadoria.codigo));

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> resultSet = db.executeQuery(query, parameters.toArray(new String[]{}));
			if (resultSet.isEmpty()) {
				return Collections.emptyList();
			}
			
			final int size = resultSet.size();
			List<DetalhePEE> detalhePEEs = new ArrayList<DetalhePEE>(size);
			for (int count = 0; count < size; count++) {
				
				Map<String, String> registro = resultSet.get(count);
				if (registro.get("PRR.CODPRRPEE")         == null 	   
						&& registro.get("PRR.IDTINFPEE")  == null 
						&& registro.get("PRR.DESINFPEE")  == null
						&& registro.get("DESCONTO")       == null
						&& registro.get("DATA_VIGORACAO") == null) {
						continue;
				}
				
				DetalhePEE detalhePEE = new DetalhePEE();
				
				detalhePEE.codigoPrioridade 	   = Util.getInteger(registro.get("PRR.CODPRRPEE"));
				detalhePEE.identidadeInformacaoPEE = Util.getStringValue(registro.get("PRR.IDTINFPEE"), null);
				detalhePEE.descricaoInformacaoPEE  = Util.getStringValue(registro.get("PRR.DESINFPEE"), null);
				detalhePEE.descontoAcaoTatica      = Util.getStringValue(registro.get("DESCONTO"), null);
				detalhePEE.dataVigoracao 		   = DateUtil.converteStringToDate(registro.get("DATA_VIGORACAO"));

				detalhePEEs.add(detalhePEE);
			}
			
			return detalhePEEs;
			
		} catch (SQLException e) {
			
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
			
		}
	}	
	
}