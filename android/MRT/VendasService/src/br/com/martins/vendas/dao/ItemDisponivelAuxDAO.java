package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.com.martins.vendas.enums.TipoNegociacaoMercadoria;
import br.com.martins.vendas.services.GestaoMixService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.LivroPreco;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RegraBloqueio;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.StringUtil;
import com.brq.mobile.framework.util.Util;

public class ItemDisponivelAuxDAO {
	
	private static final String TAG =  ItemDisponivelAuxDAO.class.getName();

	public static List<Item> listaItensDisponiveis(
			Integer codigoCliente,
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer codigoRelacionamentoCliente, 
			Integer codigoGrupamentoCliente, 
			String  flagAlvaraPsicotropico, 
			Integer codigoRepresentante,
			ItemFiltro itemFiltro) {
	
		final String flagAcessoExclusivoMix = ItemDisponivelDAO.buscaSeGrupoEhApenasDoMix(codigoGrupamentoCliente);
		
		List<String> parameters = new ArrayList<String>(10);
		StringBuilder sql       = new StringBuilder();
		
		sql		
		.append("    SELECT DISTINCT                                                                                                          ")
		.append("           MER.CODMER ,                                                                                                      ")
		.append("           SMR.CODMERPCP,                                                                                                    ")
		.append("           MER.DESMER || ' ' || CASE WHEN MER.FLGCPLDES = '*' THEN (SELECT DESCPLMER FROM CADCPL CPL WHERE MER.CODMER = CPL.CODMER) ELSE '' END  DESMER, ")
		.append("           MER.DESUNDVND          ,                                                                                          ")
		.append("           MER.FLGCPLDES          ,                                                                                          ")
		.append("           MER.FTRCNVUNT          ,                                                                                          ")
		.append("           MER.QDECXAFRN          ,                                                                                          ")
		.append("           MER.TIPMER             ,                                                                                          ")
		.append("           MER.CODGRPNCM          ,                                                                                          ")
		.append("           MER.TIPPDCMER          ,                                                                                          ")
		.append("           MER.CODTERCHV1         ,                                                                                          ")
		.append("           MER.CODTERCHV2         ,                                                                                          ")
		.append("           MER.CODCPRMER          ,                                                                                          ")
		.append("           MER.CODGRPFRC          ,                                                                                          ")
		.append("           MER.FLGPCPSMR          ,                                                                                          ")
		.append("           MER.QDEMNMVND          ,                                                                                          ")
		.append("           MER.FLGMPLQDE          ,                                                                                          ")
		.append("           MER.CODESLNGC          ,                                                                                          ")
		.append("           MER.INDMERPRL          ,                                                                                          ")
		.append("           MER.FLGINDSER          ,                                                                                          ")
		.append("           MER.CODBRRMER          ,                                                                                          ")
		.append("           MER.INDIMPFTE          ,                                                                                          ")
		.append("           MER.INDRTCBFV          ,                                                                                          ")
		.append("           MER.INDRTCBFB          ,                                                                                          ")
		.append("           MER.INDRTCBNF          ,                                                                                          ")
		.append("           MER.INDMERKIT          ,                                                                                          ")
		.append("           MER.INDRSTKIT          ,                                                                                          ")
		.append("           MER.CODGRPMER          ,                                                                                          ")
		.append("           MER.QDEMNMKIT          ,                                                                                          ")
		.append("           MER.CODFMLMER          ,                                                                                          ")
		.append("           MER.CODCLSMER          ,                                                                                          ")
		.append("           LVR.CODFILEPD          ,                                                                                          ")
		.append("           LVR.CODFILFAT          ,                                                                                          ")
		.append("           LVR.NUMRLCCID          ,                                                                                          ")
		.append("           LVR.CODMER    LVRMER   ,                                                                                          ")
		.append("           LVR.TIPICTMER          ,                                                                                          ")
		.append("           LVR.CODSMBSIT          ,                                                                                          ")
		.append("           LVR.CODFLXPCO          ,                                                                                          ")
		.append("           LVR.FLGMEREXV          ,                                                                                          ")
		.append("           LVR.INDMERMTD          ,                                                                                          ")
		.append("           LVR.CODTBTICM          ,                                                                                          ")
		.append("           LVR.VLRUNTBRT          ,                                                                                          ")
		.append("           LVR.FLGPEE             ,                                                                                          ")
		.append("           LVR.TIPMCOREP          ,                                                                                          ")
		.append("           LVR.CSTCSTLGT          ,                                                                                          ")
		.append("           LVR.PERTBTMER          ,                                                                                          ")
		.append("           LVR.PERCMSMER          ,                                                                                          ")
		.append("           ETQ.CODFILEPD EQTFILEPD,                                                                                          ")
		.append("           ETQ.CODMER	 EQTMER    ,                                                                                          ")
		.append("           ETQ.TIPESGMER          ,                                                                                          ")
		.append("           ETQ.DATRPSMER,                                                                                                    ")
		.append("           (SELECT CODPMC FROM PCAMPR MPR WHERE MPR.CODMER = MER.CODMER) AS PROMOCAO                                         ")
		.append("      FROM CADMER MER                                                                                                        ")
		.append(" LEFT JOIN PCALVR LVR ON MER.CODMER    = LVR.CODMER                                                                          ")
        .append("       AND LVR.CODFILEPD = ?                                                                                                 ")
		.append("   	AND LVR.CODFILFAT = ?                                                                                                 ")
		.append("	    AND LVR.NUMRLCCID = ?                                                                                                 ")		
		.append( "LEFT JOIN PCASMR SMR ON SMR.CODMER   = MER.CODMER                                                                           ")
		.append(" LEFT JOIN PCASGS SGS ON SGS.CODMER   = MER.CODMER                                                                           ")
        .append(" LEFT JOIN PCAETQ ETQ ON ETQ.CODMER   = MER.CODMER AND LVR.CODFILEPD = ETQ.CODFILEPD AND COALESCE(ETQ.TIPESGMER, ' ') <> 'F' ");
		
		parameters.add(String.valueOf(codigoFilialExpedicao));		
		parameters.add(String.valueOf(codigoFilialFaturamento));
		parameters.add(String.valueOf(codigoRelacionamentoCliente));
			
		/*
		 * Busca Avançada, Por Grupo, Categoria e SubCategoria
		 */
		if(itemFiltro.codigoGrupo != null) {
			
			sql.append(" JOIN PCAGRP GRP ON MER.CODGRPMER = GRP.CODGRPMER AND GRP.CODGRPMER = ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoGrupo));
			
			if (itemFiltro.codigoCategoria !=  null) {
				
				sql.append(" JOIN PCAFML FML ON GRP.CODGRPMER  = FML.CODGRPMER AND FML.CODFMLMER = ? ");
				parameters.add(Util.getStringValue(itemFiltro.codigoCategoria));
				
				if (itemFiltro.codigoSubCategoria != null) {

					sql.append(" JOIN PCACLS CLS ON FML.CODFMLMER  = FML.CODFMLMER AND GRP.CODGRPMER = CLS.CODGRPMER AND CLS.CODCLSMER = ?");
					parameters.add(Util.getStringValue(itemFiltro.codigoSubCategoria));
					
				}
			}
		}
		
		sql
		.append("      WHERE MER.INDRSTKIT = 0                                                                                                ");
//		sql.append("     AND +MER.DESMER LIKE 'A%'");
		
		if (itemFiltro.comportamentoCompras) {
			sql.append(" AND SGS.CODCLI = ?                                                                                                   ");
			parameters.add(Util.getStringValue(codigoCliente));
		}
		
		sql
		.append("        AND (  SMR.CODMERPCP IS NOT NULL  OR  ")
		.append("                ( ")
		.append("                   (LVR.FLGMEREXV  = '*' AND ? <> 0 AND (SELECT COUNT(*) FROM PCAMIX MIX WHERE MIX.CODMER = MER.CODMER AND MIX.CODGRPCLI = ?) > 0)             ")              
		.append("                OR (LVR.FLGMEREXV <> '*' AND ? =  0)                                                                                                           ")
		.append("                OR (LVR.FLGMEREXV <> '*' AND ? <> 0 AND ? = 'N')                                                                                               ")
		.append("                OR (LVR.FLGMEREXV <> '*' AND ? <> 0 AND ? = 'S' AND (SELECT COUNT(*) FROM PCAMIX MIX WHERE MIX.CODMER = MER.CODMER AND MIX.CODGRPCLI = ?) > 0) ")
		.append("                )  ");		
		
		String grp = Util.getStringValue(codigoGrupamentoCliente);
		parameters.add(grp);
		parameters.add(grp);
		parameters.add(grp);
		parameters.add(grp);
		parameters.add(flagAcessoExclusivoMix);
		parameters.add(grp);
		parameters.add(flagAcessoExclusivoMix);
		parameters.add(grp);

		if (!Constants.FLG_ASTERISTICO.equalsIgnoreCase(flagAlvaraPsicotropico)) {
			sql
			.append("            AND (SELECT COUNT(*) FROM PCAPSC PSC WHERE PSC.CODMER = MER.CODMER) = 0                                                    ");
		}
		
		sql
		.append("                AND                                                                                                                        ")
		.append("    	             (                                                                                                                      ")
		.append("    		           	  ( MER.CODESLNGC = 0 )                                                                                             ")
		.append("    		           	  OR                                                                                                                ")
		.append("    		           		MER.CODESLNGC <> 0                                                                                              ")
		.append("    		           		AND (SELECT COUNT(*) FROM PCATET TET                                                                            ")
		.append("    		           			   	             JOIN PCACLF CLF ON TET.CODCLFNGC = CLF.CODCLFNGC                                           ")
		.append("    		           						    WHERE TET.CODCLI    = ? AND TET.CODEDEVND = ?                                               ")
		.append("    		           							  AND CLF.CODESLNGC = MER.CODESLNGC                                                         ")
		.append("    		           							  AND CLF.NUMPRRVND = ( SELECT MIN(NUMPRRVND) FROM PCATET TETIN                             ")
		.append("                                                         						                  JOIN PCACLF CLFIN                         ")
		.append("                                                                 							    	ON TETIN.CODCLI    = TET.CODCLI         ")
		.append("                                                          							   	         WHERE TETIN.CODCLFNGC = CLFIN.CODCLFNGC    ")  
		.append("                                                         										   AND CLFIN.CODESLNGC = MER.CODESLNGC      ")
		.append("                                                         										   AND TETIN.CODEDEVND = TET.CODEDEVND )    ")
		.append("    		                    ) > 0                                                                                                       ")
		.append("                    )                                                                                                                      ")
		.append("            )                                                                                                                              ");
		parameters.add(Util.getStringValue(codigoCliente));
		parameters.add(Util.getStringValue(codigoRepresentante));
		
		if ( itemFiltro.codigoMercadoria != null ) {
			sql.append(" AND +MER.CODMER   =  ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoMercadoria));
		}
		
		if ( itemFiltro.codigoBarras != null ) {
			sql.append(" AND MER.CODBRRMER = ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoBarras));
		}
		
		if (itemFiltro.codigoFornecedor != null) {
			sql.append(" AND MER.CODTERCHV2 = ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoFornecedor));
		}
		
		if ( itemFiltro.mex ) {
			sql.append(" AND LVR.FLGMEREXV = ? ");
			parameters.add("*");
		}
		
		if ( itemFiltro.descricaoMercadoria != null) {
			sql.append(" AND +DESMER LIKE  ? ");
			parameters.add(itemFiltro.meio ? "%".concat(itemFiltro.descricaoMercadoria).concat("%") : itemFiltro.descricaoMercadoria.concat("%"));
		}
		
		if( itemFiltro.codigoListaCustomizada != null ){
			sql.append(" AND LVR.TIPMCOREP =  ? ");
			parameters.add(itemFiltro.codigoListaCustomizada);
		}
		
		if (itemFiltro.comportamentoCompras) {
			sql.append(" ORDER BY SGS.NUMPRRMER ");
		} else {
			sql.append(" ORDER BY MER.DESMER    ");
		}
		

		// Calculando Paginação
		int pagesize  = Constants.TAMANHO_PAGINAO_CONSULTA;
		int numPagina = itemFiltro.numPagina;		
		int ini = numPagina == 1 ? 0 : (numPagina - 1) * pagesize;
		
		sql.append(" LIMIT ? OFFSET ? ");
		parameters.add(String.valueOf(pagesize));
		parameters.add(String.valueOf(ini));
		
		Database db = DatabaseFactory.getInstance();
			
		try {
			long i = new Date().getTime();
			List<Map<String, String>> resultQuery = db.executeQuery(sql.toString(), parameters.toArray(new String[parameters.size()]));
			long f = new Date().getTime();
			
			System.out.println("Query Itens - Tempo Gasto: " + TimeUnit.MILLISECONDS.toSeconds((f - i)) + "segs. - Milisegundos: " +  TimeUnit.MILLISECONDS.toMillis((f - i)));
			
			
			if (resultQuery.size() == 0) {
				return Collections.emptyList();
			}
			return convertResultQueryToItemVO(resultQuery);
			
		} catch (SQLException e) {
			
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
	
	
	
	public static List<Item> listaItensDisponiveis2(
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer codigoCliente,
			Integer codigoRelacionamentoCliente, 
			Integer codigoGrupamentoCliente, 
			String  flagAlvaraPsicotropico,
			Integer codigoCidade,
			Integer codigoAtividade, 
			Integer codigoSupervisor, 
			Integer codigoRepresentante,
			String  codigoEstadoDestino,
			ItemFiltro itemFiltro) {
		
		Database db = DatabaseFactory.getInstance();
		
		// Quando pesquisarMercadoria for igual à false é necessário buscar a mesma em mercadoria similar
		boolean pesquisarMercadoria = !itemFiltro.isListaMercadoriaSimilar;
		if (!itemFiltro.isListaMercadoriaSimilar || itemFiltro.isBuscaPorCodigo) {
			try {
				pesquisarMercadoria = !(db.executeCount("SELECT COUNT(*) QTD FROM PCASMR WHERE CODMER = ? AND CODMERPCP != CODMER", Util.getStringValue(itemFiltro.codigoMercadoria)) > 0);
			} catch (SQLException e) {
				Log.e(TAG, e.getMessage(), e);
				throw new RuntimeException(e.getLocalizedMessage(), e);
			}
		}

		List<String> parameters = new ArrayList<String>(10);
		StringBuilder sql       = new StringBuilder();
		
		if (pesquisarMercadoria) {
		
		sql		
		.append("SELECT DISTINCT       ")
		.append("       MER.CODMER ,   ")
		.append("       MER.DESMER || ' ' || COALESCE(CPL.DESCPLMER, ' ') DESMER, ")
	    .append("       MER.QDEMNMVND, ")
		.append("       MER.FLGMPLQDE, ")
		.append("       MER.CODBRRMER, ")
		.append("       MER.INDIMPFTE, ")
		.append("       MER.INDRTCBFV, ")
		.append("       MER.INDRTCBFB, ")
		.append("       MER.INDRTCBNF, ")
		.append("       MER.INDMERKIT, ")
		.append("       MER.INDRSTKIT, ")
		.append("       MER.CODGRPMER, ")
		.append("       MER.QDEMNMKIT, ")
		.append("       MER.CODFMLMER, ")
		.append("       MER.CODCLSMER, ")
		.append("       MER.CODESLNGC, ")
		.append("       MER.QDECXAFRN, ")
		.append("       MER.TIPMER,    ")
		.append("       MER.TIPPDCMER  ")
		.append("      FROM CADMER MER ");
		
		} else {
		
		sql
		.append("SELECT DISTINCT       ")
		.append("       MER.CODMERPCP, ")
		.append("       MER.CODMER   , ")
		.append("       CAD.DESMER  || ' ' || COALESCE(CPL.DESCPLMER, ' ') DESMER, ")
		.append("       MER.QDEMNMVND, ")
		.append("       MER.FLGMPLQDE, ")
		.append("       MER.CODBRRMER, ")
		.append("       MER.INDIMPFTE, ")
		.append("       MER.INDRTCBFV, ")
		.append("       MER.INDRTCBFB, ")
		.append("       MER.INDRTCBNF, ")
		.append("       MER.INDMERKIT, ")
		.append("       MER.INDRSTKIT, ")
		.append("       MER.CODGRPMER, ")
		.append("       MER.QDEMNMKIT, ")
		.append("       MER.CODFMLMER, ")
		.append("       MER.CODCLSMER, ")
		.append("       CAD.CODESLNGC, ")
		.append("       CAD.QDECXAFRN, ")
		.append("       CAD.TIPMER,    ")
		.append("       CAD.TIPPDCMER  ")
		.append("  FROM PCASMR MER     ")
		.append("  JOIN CADMER CAD ON CAD.CODMER = MER.CODMERPCP ");

		}
		
	if (itemFiltro.comportamentoCompras) {
		sql	
		.append("INNER JOIN PCASGS SGS                               ")
		.append("        ON SGS.CODMER    = MER.CODMER               ")
		.append("       AND SGS.CODCLI = ?                           ");
		parameters.add(Util.getStringValue(codigoCliente));
	}
	
		sql
		.append( "LEFT JOIN CADCPL CPL ON CPL.CODMER    = MER.CODMER ");
		
	if (!itemFiltro.isBuscaPorCodigo) {
		sql	
		.append("  WHERE MER.INDRSTKIT = 0 ");
	} else {
		sql	
		.append("  WHERE 1 = 1 ");			
	}

		if  (itemFiltro.codigoGrupo != null ) {
			sql.append(" AND MER.CODGRPMER = ? ");
			parameters.add(String.valueOf(itemFiltro.codigoGrupo));
		}
		if  (itemFiltro.codigoCategoria != null ) {
			sql.append(" AND MER.CODFMLMER = ? ");
			parameters.add(String.valueOf(itemFiltro.codigoCategoria));
		}
		if  (itemFiltro.codigoSubCategoria != null ) {
			sql.append(" AND MER.CODCLSMER = ? ");
			parameters.add(String.valueOf(itemFiltro.codigoSubCategoria));
		}

		if ( itemFiltro.codigoMercadoria != null ) {
			sql.append(" AND MER.CODMER   =  ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoMercadoria));
		}
		
		if ( itemFiltro.codigoBarras != null ) {
			sql.append(" AND MER.CODBRRMER = ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoBarras));
		}
		
		if (itemFiltro.codigoFornecedor != null) {
			sql.append(" AND MER.CODTERCHV2 = ? ");
			parameters.add(Util.getStringValue(itemFiltro.codigoFornecedor));
		}
		
		if ( itemFiltro.mex ) {
			sql.append(" AND TIPPDCMER = 1 ");
		}
		
		if ( itemFiltro.descricaoMercadoria != null) {
			sql.append(" AND DESMER LIKE  ? ");
			parameters.add(itemFiltro.meio ? "%".concat(itemFiltro.descricaoMercadoria).concat("%") : itemFiltro.descricaoMercadoria.concat("%"));
		}
		
		if( itemFiltro.codigoListaCustomizada != null ){
			sql.append(" AND LVR.TIPMCOREP =  ? ");
			parameters.add(itemFiltro.codigoListaCustomizada);
		}
		
		if (itemFiltro.comportamentoCompras) {
			sql.append(" ORDER BY SGS.NUMPRRMER ");
		} else {
			sql.append(" ORDER BY DESMER    ");
		}

		// Calculando Paginação
		int pagesize  = Constants.TAMANHO_PAGINAO_CONSULTA;
		int numPagina = itemFiltro.numPagina;		
		int ini = numPagina == 1 ? 0 : (numPagina - 1) * pagesize;
		
		sql.append(" LIMIT ? OFFSET ? ");
		parameters.add(String.valueOf(pagesize));
		parameters.add(String.valueOf(ini));
		
		try {
			
			long i = new Date().getTime();
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters.toArray(new String[parameters.size()]));
			long f = new Date().getTime();
			System.out.println("ITENS QUERY, SEC: " + TimeUnit.MILLISECONDS.toSeconds((f - i)) + " MILISEC: " +  TimeUnit.MILLISECONDS.toMillis((f - i)));
			
			if (result.size() == 0) {
				return Collections.emptyList();
			}
			
			boolean verificarSimilar = (itemFiltro.isListaMercadoria) || (!itemFiltro.isListaMercadoriaSimilar && !itemFiltro.isBuscaPorCodigo);

			i = new Date().getTime();
			for (int j = 0; j < result.size(); j++) {
							
				Map<String, String> registro        = result.get(j);
				Integer codMercadoria               = Util.getInteger(registro.get("CODMER"));
				Integer codigoEspecialidadeNegocio  = Util.getInteger(registro.get("CODESLNGC"));

				boolean isSimilar = false;
				if (verificarSimilar) {
					// Verifica se a mercadoria é similar, em caso positivo nenhuma validação deve ser realizada
					isSimilar = db.executeCount("SELECT COUNT(*) FROM PCASMR WHERE CODMER = ?", registro.get("CODMER")) > 0;
					if (isSimilar) {
						registro.put("CODMERPCP", registro.get("CODMER"));
						continue;
					}
				}
										
				LivroPreco lvr = LivroPrecoDAO.recupera(codigoFilialExpedicao, codigoFilialFaturamento, codigoRelacionamentoCliente, codMercadoria);
				if (lvr == null) {
					if (!isSimilar) {
						result.remove(j);
						j--;
					}
					continue;
				}
			
				String[] pEstoque = new String[2];
				pEstoque[0] = Util.getStringValue(codigoFilialExpedicao);
				pEstoque[1] = Util.getStringValue(codMercadoria);
				List<Map<String, String>> temEstoque = db.executeQuery("SELECT TIPESGMER FROM PCAETQ WHERE CODFILEPD = ? AND CODMER = ?", pEstoque);
				if (!temEstoque.isEmpty()) {
					String tipoEsgotamento = temEstoque.get(0).get("TIPESGMER");
					if ("F".equals(tipoEsgotamento)) {
	
						result.remove(j);
						j--;
						continue;
					} else {
						registro.put("TIPESGMER", tipoEsgotamento);	
					}
				}
				
				
				if (Constants.FLG_ASTERISTICO.equals(lvr.flgMercadoriaExclusivo)) {
					
					if (codigoGrupamentoCliente == 0) {
						
						result.remove(j);
						j--;
						continue;
						
					} else {
						
						int mix = db.executeCount("SELECT COUNT(*) QTD FROM PCAMIX MIX WHERE MIX.CODMER = ? AND MIX.CODGRPCLI = ?", Util.getStringValue(codMercadoria), Util.getStringValue(codigoGrupamentoCliente));
						if (mix == 0) {
							
							result.remove(j);
							j--;
							continue;
							
						}
					}
					
				} else {
					
					
					if (codigoGrupamentoCliente != 0) {
						
						List<Map<String, String>> flgAcsMix = db.executeQuery("SELECT FLGACSMIX FROM PCACGR WHERE CODGRPCLI = ?", Util.getStringValue(codigoGrupamentoCliente));
						if (Constants.FLG_SIM.equals(flgAcsMix.get(0).get("FLGACSMIX"))) {
							
							int mix = db.executeCount("SELECT COUNT(*) QTD FROM PCAMIX MIX WHERE MIX.CODMER = ? AND MIX.CODGRPCLI = ?", Util.getStringValue(codMercadoria), Util.getStringValue(codigoGrupamentoCliente));
							if (mix == 0) {
								
								result.remove(j);
								j--;
								continue;
							}
							
						}
						
					}
					
				}
				
				if (!Constants.FLG_ASTERISTICO.equalsIgnoreCase(flagAlvaraPsicotropico)) {
					
					int count = db.executeCount("SELECT COUNT(*) FROM PCAPSC PSC WHERE PSC.CODMER = ?", Util.getStringValue(codMercadoria));
					if  (count > 0) {

						result.remove(j);
						j--;
						continue;
					}
				}
				
				if (codigoEspecialidadeNegocio > 0) {
					
					StringBuilder sqlEspecialidadeNegocio = new StringBuilder();
					sqlEspecialidadeNegocio
					.append(" SELECT COUNT(*)                                                    ")
					.append("   FROM PCATET TET                                                  ")
					.append("   JOIN PCACLF CLF                                                  ")
					.append("     ON TET.CODCLFNGC = CLF.CODCLFNGC                               ")
					.append("  WHERE TET.CODCLI    = ?                                           ")
					.append("    AND TET.CODEDEVND = ?                                           ")
					.append("    AND CLF.CODESLNGC = ?                                           ")
					.append("    AND CLF.NUMPRRVND = ( SELECT MIN(NUMPRRVND)                     ")
					.append("                            FROM PCATET TETIN                       ")
					.append("                            JOIN PCACLF CLFIN                       ")
					.append("                       	   ON TETIN.CODCLFNGC  = CLFIN.CODCLFNGC ")
					.append("                       	  AND TETIN.CODCLI     = TET.CODCLI      ")
					.append("                       	  AND TETIN.CODEDEVND  = TET.CODEDEVND   ")
					.append("                           WHERE CLFIN.CODESLNGC  = ? )             ");  
					                            
					if ( db.executeCount(
							sqlEspecialidadeNegocio.toString(),
							Util.getStringValue(codigoCliente), 
							Util.getStringValue(codigoRepresentante),
							Util.getStringValue(codigoEspecialidadeNegocio),
							Util.getStringValue(codigoEspecialidadeNegocio)
							
				       ) == 0) {
					
						result.remove(j);
						j--;
						continue;
					}
				}
				
				if (verificaAtendimentoMercadoria(codigoFilialExpedicao, codMercadoria, codigoCliente, codigoRepresentante, codigoCidade, codigoSupervisor, codigoAtividade) == 0) {

					if (!validaRegrasDeBloqueioClientePorMercadoria(codMercadoria, codigoCliente, codigoGrupamentoCliente, codigoRepresentante, codigoSupervisor, codigoAtividade, codigoCidade, codigoEstadoDestino, codigoFilialExpedicao, codigoFilialFaturamento)) {
						
						result.remove(j);
						j--;
						continue;
					}
					
				}
			}
			f = new Date().getTime();
			System.out.println(" Query Itens - Tempo Gasto: " + TimeUnit.MILLISECONDS.toSeconds((f - i)) + " Segs. - Milisegundos: " +  TimeUnit.MILLISECONDS.toMillis((f - i)));
			
			return convertResultQueryToItemVO(result);
			
		} catch (Exception e) {
			
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
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
	public static Integer verificaAtendimentoMercadoria(
			Integer codigoFilialExpedicao,
			Integer codigoMercadoria,
			Integer codigoCliente,
			Integer codigoRepresentante,
			Integer codigoCidade, 
			Integer codigoSupervisor, 
			Integer codigoAtividade) {
		
		Database db = DatabaseFactory.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT TIPPNDMER FROM PCABLQ ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODMER    = ? ");
		sql.append("    AND (  ");
		sql.append("        ((TIPABGPND = 60) AND (CODEDE = ? )) OR "); // codCli - codigoCliente
		sql.append("        ((TIPABGPND = 50) AND (CODEDE = ? )) OR "); // codRep - codigoRepresentante
		sql.append("        ((TIPABGPND = 3)  AND (CODEDE = ? )) OR "); // codCid - codigoCidade
		sql.append("        ((TIPABGPND = 2)  AND (CODEDE = ? )) OR "); // codSup - codigoSupervisor
		sql.append("        ((TIPABGPND = 1)  AND (CODEDE = ? ))    "); // codAti - codigoAtividade
		sql.append("    ) ");
		sql.append("    AND STRFTIME('%%y%%m%%d', DATETIME('NOW')) < DATVLDPND ");
		sql.append("  ORDER BY CODFILEPD DESC, CODMER DESC, TIPABGPND DESC, CODEDE DESC, TIPPNDMER DESC ");

		String[] parameters = new String[7];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);		
		parameters[1] = Util.getStringValue(codigoMercadoria);		
		parameters[2] = Util.getStringValue(codigoCliente);		
		parameters[3] = Util.getStringValue(codigoRepresentante);		
		parameters[4] = Util.getStringValue(codigoCidade);		
		parameters[5] = Util.getStringValue(codigoSupervisor);		
		parameters[6] = Util.getStringValue(codigoAtividade);		
		
		try {
			List<Map<String, String>> resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql), parameters);
			return resultQuery.size();
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @param codigoCliente
	 * @param codigoGrupoCliente
	 * @param codigoRepresentante
	 * @param codigoSupervisor
	 * @param codigoAtividade
	 * @param codigoCidade
	 * @param codigoEstadoDestino
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @return
	 */
	public static Boolean validaRegrasDeBloqueioClientePorMercadoria(
			Integer codigoMercadoria, 
			Integer codigoCliente, 
			Integer codigoGrupoCliente, 
			Integer codigoRepresentante, 
			Integer codigoSupervisor,
			Integer codigoAtividade, 
			Integer codigoCidade, 
			String  codigoEstadoDestino, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento) {
				
		List<RegraBloqueio> regrasBloqueioGestaoMix = new ArrayList<RegraBloqueio>();

		List<Integer> codigosBloqueio = GestaoMixService.buscaCodigoDeBloqueioDaMercadoria(codigoMercadoria);

		Map<Integer, List<RegraBloqueio>> regrasBloqueioPorMercadoria = GestaoMixService.buscaRegrasDeBloqueioPorMercadoria(
				codigoCliente,
				codigoGrupoCliente,
				codigoRepresentante,
				codigoSupervisor,
				codigoAtividade,
				codigoCidade,
				codigoEstadoDestino);			
		
		int qtdBloqueio = codigosBloqueio.size();
		for (int count = 0; count < qtdBloqueio; count++) {

			int codigoBloqueio = codigosBloqueio.get(count);

			List<RegraBloqueio> regraGestaoMix = regrasBloqueioPorMercadoria.get(codigoBloqueio);
			if (regraGestaoMix == null) {
				continue;
			}

			int qtdRegraMix = regraGestaoMix.size();
			for (int j = 0; j < qtdRegraMix; j++) {

				if (GestaoMixService.buscaBloqueioPorFilialExpedicao(codigoFilialExpedicao, codigoFilialFaturamento, codigoBloqueio)) {

					regrasBloqueioGestaoMix.add(regraGestaoMix.get(j));
				}

			}
		}

		int size = regrasBloqueioGestaoMix.size();
		if (size > 0) {
			String valorReduzido = "0";
			RegraBloqueio regraMenorValorReduzido = new RegraBloqueio();

			for (int count = 0; count < size; count++) {

				RegraBloqueio regra = regrasBloqueioGestaoMix.get(count);
				String valorReduzidoAux = regra.toString();

				// 01112004 REGRA PRIORIDADE
				if (new Long(valorReduzidoAux) > new Long(valorReduzido)) {
					regraMenorValorReduzido = regra;
					valorReduzido = valorReduzidoAux;
				}
			}
			// liberacao
			if (regraMenorValorReduzido.tipoBloqueioMercadoria == 1) {
				return true;
				// bloqueio
			} else if (regraMenorValorReduzido.tipoBloqueioMercadoria == 5) {
				return false;
			} else {
				// Reducao de cota
				if (regraMenorValorReduzido.tipoBloqueioMercadoria == 3) {
					valorReduzido.substring(4, valorReduzido.length());
					return true;
				}
			}
		} else {
			return true;
		}
		return false;
	}
	
	private static List<Item> convertResultQueryToItemVO(List<Map<String, String>> resultQuery) {
		
		List<Item> lstItem = new ArrayList<Item>();
		for (Map<String, String> registro : resultQuery) {
			
			Item item = new Item();
		
			Preco preco           = item.preco;
			Mercadoria mercadoria = item.mercadoria;
			Promocao promocao	  = item.promocao;
			
			item.codigoFilialExpedicao   = Util.getInteger(registro.get("CODFILEPD"));
			
			item.codigoFilialFaturamento = Util.getInteger(registro.get("CODFILFAT"));
			
			mercadoria.codigo            = Util.getInteger(registro.get("CODMER"));
		 			
			mercadoria.descricao         = registro.get("DESMER");
			
			mercadoria.codigoEspecialistaNegociacao = Util.getInteger(registro.get("CODESLNGC"));
			                                        
			mercadoria.quantidadeCaixaFornecedor    = Util.getInteger(registro.get("QDECXAFRN"), 0);
			                                        
			mercadoria.quantidadeMinimaKit          = Util.getInteger(registro.get("QDEMNMKIT"));
			                                        
			mercadoria.quantidadeMinimaVenda        = Util.getInteger(registro.get("QDEMNMVND"));
			                                        
			mercadoria.codigoChaveCategoria         = registro.get("CODTERCHV1");
			                                        
			mercadoria.tipo                         = registro.get("TIPMER");
			                                        
			mercadoria.flagMultiplicadorQuantidade  = registro.get("FLGMPLQDE");
					                                        
			String codigoGrupoFracionado 		    = registro.get("CODGRPFRC");
			mercadoria.codigoGrupoFracionado        = (codigoGrupoFracionado == null || codigoGrupoFracionado.equals("")) ? 0 : Util.getInteger(codigoGrupoFracionado);
			                                        
			mercadoria.indicadorRestricaoKit        = Util.getInteger(registro.get("INDRSTKIT"));					
			                                        
			mercadoria.tipoProducaoMercadoria       = Util.getInteger(registro.get("TIPPDCMER"));
			                                        
			mercadoria.tipoEsgotamento              = Util.getStringValue(registro.get("TIPESGMER"));
			                                        
			mercadoria.codigoMercadoriaPrincipal    = Util.getInteger(registro.get("CODMERPCP"));
			if (mercadoria.codigoMercadoriaPrincipal == null) {
				mercadoria.codigoMercadoriaPrincipal = mercadoria.codigo;
			} else {
				mercadoria.temMercadoriaSimilar = true;
			}
						                                        
			mercadoria.temRestricaoBeneficioCustomizado         = Util.getInteger(registro.get("INDRTCBFV"));

			mercadoria.temRestricaoBeneficioCustomizadoNoBrinde = Util.getInteger(registro.get("INDRTCBFB"));
			
			mercadoria.temRestricaoBrinde        				= Util.getInteger(registro.get("INDRTCBNF"));
			
			mercadoria.fatorConversaoUnitario                   = Util.getInteger(registro.get("FTRCNVUNT"));
			
			mercadoria.indicaMercadoriaKit                      = Util.getInteger(registro.get("INDMERKIT"));
					
			mercadoria.descricaoNegocioMercadoria			    = TipoNegociacaoMercadoria.VENDA.descTipoNegociacaoMercadoria;
			
			promocao.codigo										= Util.getInteger(registro.get("PROMOCAO"));
						
			preco.valorBrutoUnitario    = Util.getBigDecimal(registro.get("VLRUNTBRT"));

			preco.flagPrecoMonitorado   = Util.getInteger(registro.get("INDMERMTD"), Integer.MIN_VALUE);

			preco.percentualTributacao  = Util.getBigDecimal(registro.get("PERTBTMER"));
			
			preco.codigoTributacaoICM   = Util.getInteger(registro.get("CODTBTICM"));
			
			preco.custoLogistica 		= Util.getBigDecimal(registro.get("CSTCSTLGT"));
			
			preco.percentualICMS 		= Util.getBigDecimal(registro.get("PERCMSMER"));
			 
			preco.tipoIncentivoMercadoria          = registro.get("TIPICTMER");
			
			preco.codigoFlexivelPreco              = registro.get("CODFLXPCO");
			
			preco.codigoSimboloSituacao            = registro.get("CODSMBSIT");
			
			preco.flagMercadoriaExclusiva          = registro.get("FLGMEREXV");
			
			preco.flagPontoEncontroEletronico      = Util.getStringValue(registro.get("FLGPEE"), StringUtil.EMPTY);
			
			preco.tipoMarcacaoRepresentante        = Util.getStringValue(registro.get("TIPMCOREP"), StringUtil.EMPTY);
			
			item.indicaRestricaoBeneficioCustomizado       = mercadoria.temRestricaoBeneficioCustomizado;
			item.indicaRestricaoBeneficioCustomizadoBrinde = mercadoria.temRestricaoBeneficioCustomizadoNoBrinde;
			item.indicaRestricaoItemBrinde                 = mercadoria.temRestricaoBrinde;
			
			lstItem.add(item);
		}

		return lstItem;
	}
	
}