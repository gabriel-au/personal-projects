package br.com.martins.vendas.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.desconto.DescontoAcaoTatica;
import br.com.martins.vendas.services.desconto.DescontoBanda;
import br.com.martins.vendas.services.desconto.DescontoSimplificado;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;

public class DescontoDAO {

	private static final String TAG = DescontoDAO.class.getName();
	
	private static Database db;
	
	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param siglaEstadoDestino
	 * @param codigoAtividade
	 * @param cliente
	 * @param mercadoria
	 */
	public static List<DescontoAcaoTatica> obtemDescontoPorAcaoTatica(
			Integer codigoFilialExpedicao,
			String siglaEstadoDestino,
			Integer codigoAtividade,
			Cliente cliente,
			Mercadoria mercadoria){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select FLGDSCEXV, TIPACOTTC, INDACOFND, FTRDSC, VLRDSCUNT, VLRMAXISN, CODCLI ");
		sql.append(" from PCAGDE GDE inner join PCAGDM GDM on ");
		sql.append("  GDE.CODFILEPD = GDM.CODFILEPD and GDE.CODGRPDSC = GDM.CODGRPDSC ");
		sql.append(" left join PCADUF DUF on GDE.CODFILEPD = DUF.CODFILEPD ");
		sql.append("  and GDE.CODGRPDSC = DUF.CODGRPDSC and (DUF.CODESTDSN = '99' or DUF.CODESTDSN = ?) ");
		sql.append(" left join PCADCD DCD on GDE.CODFILEPD = DCD.CODFILEPD ");
		sql.append("  and GDE.CODGRPDSC = DCD.CODGRPDSC and DUF.CODFILEPD = ? ");
		sql.append(" left join PCADAT DAT on GDE.CODFILEPD = DAT.CODFILEPD ");
		sql.append("  and GDE.CODGRPDSC = DAT.CODGRPDSC and DAT.CODATI = ? ");
		sql.append(" left join PCADGR DGR on GDE.CODFILEPD = DGR.CODFILEPD ");
		sql.append("  and GDE.CODGRPDSC = DGR.CODGRPDSC and DGR.CODGRPCLI = ? ");
		sql.append(" left join PCADCL DCL on GDE.CODFILEPD = DCL.CODFILEPD ");
		sql.append("  and GDE.CODGRPDSC = DCL.CODGRPDSC and (DCL.CODCLI = ? ");
		sql.append("  or DCL.CODCLI = 9999999) ");
		sql.append(" WHERE ");
		sql.append(" STRFTIME('%Y%m%d', DATETIME('NOW')) between GDE.DATINIVGR and GDE.DATFIMVGR ");
		sql.append(" and GDE.CODFILEPD = ? ");
		sql.append(" and GDM.CODMER = ? ");
		sql.append(" and (DUF.CODESTDSN is not NULL or DCD.CODCID is not NULL) ");
		sql.append(" and (DAT.CODATI is not NULL or DGR.CODGRPCLI is not NULL or DCL.CODCLI is not NULL) ");
		
		String[] parameters = new String[7];
		parameters[0] = Util.getStringValue(siglaEstadoDestino);
		parameters[1] = Util.getStringValue(codigoFilialExpedicao);
		parameters[2] = Util.getStringValue(codigoAtividade);
		parameters[3] = Util.getStringValue(cliente.codigoGrupoCliente);
		parameters[4] = Util.getStringValue(cliente.codigoCliente);
		parameters[5] = Util.getStringValue(codigoFilialExpedicao);
		parameters[6] = Util.getStringValue(mercadoria.codigo);
		
		db = DatabaseFactory.getInstance();		
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (!result.isEmpty())
				return convertResultToDescontoAcaoTatica(result);
			
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return Collections.emptyList();
		
	}
	
	/**
	 * 
	 * @param mercadoria
	 * @param siglaEstadoDestino
	 * @return 
	 */
	public static DescontoBanda obtemDescontoPorBanda(Mercadoria mercadoria, String siglaEstadoDestino){
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PERLIMINF, PERLIMSUP ");
		sql.append("   FROM PCABPR BPR, ");
		sql.append("        (SELECT COALESCE(MIN(CODPIR),0) CODPIR FROM PCAPIM WHERE CODESTDSN = ? AND CODMER  = ?) PIM ");
		sql.append("  WHERE BPR.CODESTDSN = ? ");
		sql.append("    AND BPR.CODTERCHV = ?  ");
		sql.append("    AND BPR.CODPIR = PIM.CODPIR ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(siglaEstadoDestino);
		parameters[1] = Util.getStringValue(mercadoria.codigo);
		parameters[2] = Util.getStringValue(siglaEstadoDestino);
		parameters[3] = Util.getStringValue(mercadoria.codigoChaveCategoria);
		
		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (!result.isEmpty())
				return convertResultToDescontoBanda(result);
			
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @param cliente
	 * @param codigoTerritorioVenda
	 * @param numeroSequenciaFaixa
	 * @param codigoFilialExpedicao
	 * @param condicaoPagamento
	 * @return 
	 */
	public static DescontoSimplificado obtemDescontoSimplificado(Cliente cliente, Integer codigoTerritorioVenda,
			 Integer codigoFilialExpedicao, CondicaoPagamento condicaoPagamento){
		StringBuilder sql = new StringBuilder();
		sql.append(" select SUM(BFX.PERDSCSMP * (1 - (BCL.PERUTZDSC * 0.01))) PERDSCSML ");
		sql.append(" from PCABCL BCL inner join PCABFX BFX on BCL.CODBFC = BFX.CODBFC ");
		sql.append(" inner join PCABFE BFE on BCL.CODBFC =  BFE.CODBFC ");
		sql.append(" left join  PCABCP BCP on BCL.CODBFC =  BCP.CODBFC ");
		sql.append(" where BCL.CODCLI = ? ");
		sql.append(" and STRFTIME('%Y%m%d', DATETIME('NOW')) BETWEEN DATINIVLD AND DATFIMVLD ");
		sql.append(" and BCL.CODTETVND = ? ");
		sql.append(" and BFX.INDUTZBFC in (1,2) ");
		sql.append(" and BFX.NUMSEQFXA = 1 ");
		sql.append(" and BFE.CODFILEPD = ?  ");
		sql.append(" AND ( ");
		sql.append(" 	     (BCP.CODCNDPGT = ? AND BFX.TIPCNDPGT = 99) ");
		sql.append(" 	  OR (BFX.TIPCNDPGT = ? AND BFX.TIPCNDPGT NOT IN (0,99) ");
		sql.append("     ) ");
		sql.append("  OR BFX.TIPCNDPGT = 0) ");
			
		String[] parameters = new String[5];
		parameters[0] = Util.getStringValue(cliente.codigoCliente);
		parameters[1] = Util.getStringValue(codigoTerritorioVenda);
		parameters[2] = Util.getStringValue(codigoFilialExpedicao);
		parameters[3] = Util.getStringValue(condicaoPagamento.codigoCondicaoPagamento);
		parameters[4] = Util.getStringValue(condicaoPagamento.tipoCondicaoPagamento);
		
		
		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (!result.isEmpty())
				return convertResultToDescontoSimplificado(result);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}		
		return null;
	}
	
	/**
	 * Converte Resultado de uma query no objeto DescontoAcaoTatica
	 * @param result
	 * @return
	 */
	private static List<DescontoAcaoTatica> convertResultToDescontoAcaoTatica(List<Map<String, String>> result){
		List<DescontoAcaoTatica> lista = new ArrayList<DescontoAcaoTatica>();
		DescontoAcaoTatica desconto = null;
		
		for(Map<String, String> registro : result){
			desconto = new DescontoAcaoTatica();
			for(String key : registro.keySet()){
				if(key.equalsIgnoreCase("FTRDSC")){
					desconto.fatorDesconto = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				}else if (key.equalsIgnoreCase("VLRDSCUNT")){
					desconto.valorDescontoUnitario = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				}else if (key.equalsIgnoreCase("TIPACOTTC")){
					desconto.tipoAcaoTatica = registro.get(key);
				}else if (key.equalsIgnoreCase("VLRMAXISN")){
					desconto.valorMaximoIsencao = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				}else if (key.equalsIgnoreCase("INDACOFND")){
					desconto.indicaAcaoFundada = Integer.parseInt(registro.get(key));
				}else if (key.equalsIgnoreCase("FLGDSCEXV")){
					desconto.flagDescontoExclusivo = registro.get(key);
				}else if (key.equalsIgnoreCase("CODCLI")){
					String value = registro.get(key);
					desconto.codigoCliente = (value != null) ? Integer.parseInt(registro.get(key)) : 0;
				}
			}
			lista.add(desconto);	
		}
		
		return lista;
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	private static DescontoSimplificado convertResultToDescontoSimplificado(List<Map<String, String>> result){		
		DescontoSimplificado desconto = null;
		
		for(Map<String, String> registro : result){
			desconto = new DescontoSimplificado();
			String value = registro.get("PERDSCSML");
			desconto.percentualDescontoSimulado = (value != null) ?
					BigDecimal.valueOf(Double.parseDouble(registro.get("PERDSCSML"))): null;
		
		}
		
		return desconto;
	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	private static DescontoBanda convertResultToDescontoBanda(List<Map<String, String>> result){
		DescontoBanda desconto = null;
		
		for(Map<String, String> registro : result){
			desconto = new DescontoBanda();
			desconto.percentualLimiteInferior = BigDecimal.valueOf(Double.parseDouble(registro.get("PERLIMINF")));
			desconto.percentualLimiteSuperior = BigDecimal.valueOf(Double.parseDouble(registro.get("PERLIMSUP")));
		}
		
		
		return desconto;
	}
	
	
}
