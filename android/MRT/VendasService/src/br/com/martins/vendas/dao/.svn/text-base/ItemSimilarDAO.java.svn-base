package br.com.martins.vendas.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.com.martins.vendas.enums.TipoNegociacaoMercadoria;
import br.com.martins.vendas.services.calculodepreco.Imposto;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.StringUtil;
import com.brq.mobile.framework.util.Util;

public class ItemSimilarDAO {
	
	private static final String TAG =  ItemSimilarDAO.class.getName();

	public static List<Item> listaItens (
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento,
			Integer codigoCliente,
			Integer codigoRelacionamentoCliente, 
			Integer codigoGrupoCliente,
			String  flagAlvaraPsicotropico,
			Integer codigoRepresentante,
			ItemFiltro itemFiltro) {
		
		final String flagAcessoExclusivoMix = ItemDisponivelDAO.buscaSeGrupoEhApenasDoMix(codigoGrupoCliente);
		
		List<String> parameters = new ArrayList<String>(3);
		StringBuilder sql       = new StringBuilder();
		sql		
		.append("    SELECT DISTINCT                                                                                                          ")
		.append("           SMR.CODMER,                                                                                                       ")
		.append("           SMR.DESSCTGRP DESMER   ,                                                                                          ")
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
		.append("           SMR.QDEMNMVND          ,                                                                                          ")
		.append("           SMR.FLGMPLQDE          ,                                                                                          ")
		.append("           MER.CODESLNGC          ,                                                                                          ")
		.append("           MER.INDMERPRL          ,                                                                                          ")
		.append("           MER.FLGINDSER          ,                                                                                          ")
		.append("           SMR.CODBRRMER          ,                                                                                          ")
		.append("           SMR.INDIMPFTE          ,                                                                                          ")
		.append("           SMR.INDRTCBFV          ,                                                                                          ")
		.append("           SMR.INDRTCBFB          ,                                                                                          ")
		.append("           SMR.INDRTCBNF          ,                                                                                          ")
		.append("           SMR.INDMERKIT          ,                                                                                          ")
		.append("           SMR.INDRSTKIT          ,                                                                                          ")
		.append("           SMR.CODGRPMER          ,                                                                                          ")
		.append("           SMR.QDEMNMKIT          ,                                                                                          ")
		.append("           SMR.CODFMLMER          ,                                                                                          ")
		.append("           SMR.CODCLSMER          ,                                                                                          ")
		.append("           SMR.CODMERPCP          ,                                                                                          ")
		.append("           SMR.DESSCTGRP          ,                                                                                          ")
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
		.append("           ETQ.CODMER	  EQTMER   ,                                                                                          ")
		.append("           ETQ.TIPESGMER          ,                                                                                          ")
		.append("           ETQ.DATRPSMER, (SELECT CODPMC FROM PCAMPR WHERE CODMER= MER.CODMER) AS PROMOCAO                                                                                                      ")
		.append("      FROM PCASMR SMR                                                                                                        ")
		.append("INNER JOIN CADMER MER ON MER.CODMER = SMR.CODMERPCP                                                                          ")
		.append(" LEFT JOIN PCALVR LVR ON LVR.CODMER = SMR.CODMER                                                                             ")
		.append(" LEFT JOIN CADCPL CPL ON CPL.CODMER = SMR.CODMER                                                                             ")
		.append(" LEFT JOIN PCAETQ ETQ ON LVR.CODFILEPD = ETQ.CODFILEPD                                                                       ")
		.append("	    AND LVR.CODMER = ETQ.CODMER                                                                                           ")
		.append("     WHERE MER.INDRSTKIT = 0                                                                                                 ")
		.append("       AND COALESCE(ETQ.TIPESGMER, ' ') <> 'F'                                                                               ")
		.append("       AND LVR.CODFILEPD = ?                                                                                                 ")
		.append("   	AND LVR.CODFILFAT = ?                                                                                                 ")
		.append("	    AND LVR.NUMRLCCID = ?                                                                                                 ");
	
		parameters.add(String.valueOf(codigoFilialExpedicao));		
		parameters.add(String.valueOf(codigoFilialFaturamento));
		parameters.add(String.valueOf(codigoRelacionamentoCliente));
		
		sql
     	.append("       AND ( ")
		.append("              (LVR.FLGMEREXV  = '*' AND ? <> 0 AND (SELECT COUNT(*) FROM PCAMIX MIX WHERE MIX.CODMER = MER.CODMER AND MIX.CODGRPCLI = ?) > 0)             ")              
		.append("           OR (LVR.FLGMEREXV <> '*' AND ? =  0)                                                                                                           ")
		.append("           OR (LVR.FLGMEREXV <> '*' AND ? <> 0 AND ? = 'N')                                                                                               ")
		.append("           OR (LVR.FLGMEREXV <> '*' AND ? <> 0 AND ? = 'S' AND (SELECT COUNT(*) FROM PCAMIX MIX WHERE MIX.CODMER = MER.CODMER AND MIX.CODGRPCLI = ?) > 0) ")
		.append("           )  ");		
		
		String grp = String.valueOf(codigoGrupoCliente);
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
			.append("   AND (SELECT COUNT(*) FROM PCAPSC PSC WHERE PSC.CODMER = SMR.CODMER) = 0                                                    ");
		}
		
		sql
		.append("    	AND (                                                                                                                      ")
		.append("    	      	  ( MER.CODESLNGC = 0 )                                                                                             ")
		.append("    	      	  OR                                                                                                                ")
		.append("    	      		MER.CODESLNGC <> 0                                                                                              ")
		.append("    	      		AND (SELECT COUNT(*) FROM PCATET TET                                                                            ")
		.append("    	      			   	             JOIN PCACLF CLF ON TET.CODCLFNGC = CLF.CODCLFNGC                                           ")
		.append("    	      						    WHERE TET.CODCLI    = ?                                                                     ")
		.append("                  					      AND TET.CODEDEVND = ?                                                                 ")
		.append("    	      							  AND CLF.CODESLNGC = MER.CODESLNGC                                                         ")
		.append("    	      							  AND CLF.NUMPRRVND = ( SELECT MIN(NUMPRRVND) FROM PCATET TETIN                             ")
		.append("                                                						                  JOIN PCACLF CLFIN                         ")
		.append("                                                        							    	ON TETIN.CODCLFNGC = CLFIN.CODCLFNGC    ")
		.append("                                                 							   	     WHERE TETIN.CODCLI    = TET.CODCLI             ")  
		.append("                                                										   AND CLFIN.CODESLNGC = MER.CODESLNGC      ")
		.append("                                                										   AND TETIN.CODEDEVND = TET.CODEDEVND )    ")
		.append("    	                  ) > 0                                                                                                    ")
		.append("           )                                                                                                                      ");
		parameters.add(String.valueOf(codigoCliente));
		parameters.add(String.valueOf(codigoRepresentante));
		
		if ( itemFiltro.codigoMercadoria != null ) {
			sql.append(" AND SMR.CODMER = ? ");
			parameters.add(String.valueOf(itemFiltro.codigoMercadoria));
		}
		
		if ( itemFiltro.codigoMercadoriaPrincipal != null ) {
			sql.append(" AND SMR.CODMERPCP = ? ");
			parameters.add(String.valueOf(itemFiltro.codigoMercadoriaPrincipal));
		}
		
		sql.append(" ORDER BY MER.DESMER ");

		// Calculando Paginação
		int pagesize  = 20;
		int numPagina = itemFiltro.numPagina;		
		int ini = numPagina == 1 ? 0 : (numPagina - 1) * pagesize;
		
		sql.append(" LIMIT ? OFFSET ? ");
		parameters.add(String.valueOf(pagesize));
		parameters.add(String.valueOf(ini));
		
		Database db = DatabaseFactory.getInstance();
			
		try {
			
			long i = new Date().getTime();
			List<Map<String, String>> resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql), parameters.toArray(new String[parameters.size()]));
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

	
	private static List<Item> convertResultQueryToItemVO(List<Map<String, String>> resultQuery) {
		
		List<Item> lstItem = new ArrayList<Item>();
		for (Map<String, String> registro : resultQuery) {
			
			Item item = new Item();
		
			Imposto stb           = item.stb;
			Preco preco           = item.preco;
			Mercadoria mercadoria = item.mercadoria;
			Promocao promocao	  = item.promocao;
			
			item.valorLiquidoComImposto       = BigDecimal.ZERO;
			item.valorUnitarioCaixaComImposto = BigDecimal.ZERO;
			item.valorCaixaComImposto         = BigDecimal.ZERO;
			item.valorUnitarioComImposto      = BigDecimal.ZERO;
			item.frete      				  = BigDecimal.ZERO;
			
			stb.valorSTBUnitario			  = BigDecimal.ZERO;
			
			item.quantidadeMercadoria    = BigDecimal.ZERO.intValue();
			
			item.notaFiscal 		     = BigDecimal.ONE.intValue();
			
			item.codigoFilialExpedicao   = Util.getInteger(registro.get("CODFILEPD"));
			
			item.codigoFilialFaturamento = Util.getInteger(registro.get("CODFILFAT"));
			
			mercadoria.codigo            = Util.getInteger(registro.get("CODMER"));
		 	
			mercadoria.descricao         = registro.get("DESMER");
			
			mercadoria.codigoEspecialistaNegociacao = Util.getInteger(registro.get("CODESLNGC"));
			                                        
			mercadoria.quantidadeCaixaFornecedor    = Util.getInteger(registro.get("QDECXAFRN"));
			                                        
			mercadoria.quantidadeMinimaKit          = Util.getInteger(registro.get("QDEMNMKIT"));
			                                        
			mercadoria.quantidadeMinimaVenda        = Util.getInteger(registro.get("QDEMNMVND"));
			                                        
			mercadoria.codigoChaveCategoria         = registro.get("CODTERCHV1");
			                                        
			mercadoria.tipo                         = registro.get("TIPMER");
			                                        
			mercadoria.flagMultiplicadorQuantidade  = registro.get("FLGMPLQDE");
					                                        
			String codigoGrupoFracionado 		    = registro.get("CODGRPFRC");
			mercadoria.codigoGrupoFracionado        = (codigoGrupoFracionado == null || codigoGrupoFracionado.equals("")) ? 0 : Util.getInteger(codigoGrupoFracionado);
			                                        
			mercadoria.indicadorRestricaoKit        = Util.getInteger(registro.get("INDRSTKIT"));					
			
			promocao.codigo							= Util.getInteger(registro.get("PROMOCAO"));
			
			mercadoria.tipoProducaoMercadoria       = Util.getInteger(registro.get("TIPPDCMER"));
			                                        
			mercadoria.tipoEsgotamento              = Util.getStringValue(registro.get("TIPESGMER"));
			                                        
			mercadoria.codigoMercadoriaPrincipal    = Util.getInteger(registro.get("CODMERPCP"));
						                                        
			mercadoria.temRestricaoBeneficioCustomizado         = Util.getInteger(registro.get("INDRTCBFV"));

			mercadoria.temRestricaoBeneficioCustomizadoNoBrinde = Util.getInteger(registro.get("INDRTCBFB"));
			
			mercadoria.temRestricaoBrinde        				= Util.getInteger(registro.get("INDRTCBNF"));
			
			mercadoria.fatorConversaoUnitario                   = Util.getInteger(registro.get("FTRCNVUNT"));
			
			mercadoria.indicaMercadoriaKit                      = Util.getInteger(registro.get("INDMERKIT"));
			
			item.mercadoria.descricaoNegocioMercadoria			= TipoNegociacaoMercadoria.VENDA.descTipoNegociacaoMercadoria;
						
			preco.valorBrutoUnitario    = BigDecimal.valueOf(Double.parseDouble(registro.get("VLRUNTBRT")));

			preco.flagPrecoMonitorado   = Integer.parseInt(registro.get("INDMERMTD"));

			preco.percentualTributacao  = Util.getBigDecimal(registro.get("PERTBTMER"));
			
			preco.codigoTributacaoICM   = Util.getInteger(registro.get("CODTBTICM"));
						
			preco.codigoSimboloSituacao = registro.get("CODSMBSIT");
			
			preco.custoLogistica 		= BigDecimal.valueOf(Double.parseDouble(registro.get("CSTCSTLGT")));
			
			preco.percentualICMS 		= BigDecimal.valueOf(Double.parseDouble(registro.get("PERCMSMER")));
			 
			preco.tipoIncentivoMercadoria          = registro.get("TIPICTMER");
			
			preco.codigoFlexivelPreco              = registro.get("CODFLXPCO");
			
			preco.codigoSimboloSituacao            = registro.get("CODSMBSIT");
			
			preco.flagMercadoriaExclusiva          = registro.get("FLGMEREXV");
			
			preco.flagPontoEncontroEletronico      = registro.get("FLGPEE");
			if (preco.flagPontoEncontroEletronico == null) {
				preco.flagPontoEncontroEletronico = StringUtil.EMPTY;
			}
			
			preco.tipoMarcacaoRepresentante        = registro.get("TIPMCOREP");
			if (preco.tipoMarcacaoRepresentante == null) {
				preco.tipoMarcacaoRepresentante = StringUtil.EMPTY;
			}
			
			item.indicaRestricaoBeneficioCustomizado       = mercadoria.temRestricaoBeneficioCustomizado;
			item.indicaRestricaoBeneficioCustomizadoBrinde = mercadoria.temRestricaoBeneficioCustomizadoNoBrinde;
			item.indicaRestricaoItemBrinde                 = mercadoria.temRestricaoBrinde;
			
			lstItem.add(item);
		}

		return lstItem;
	}
	
}
