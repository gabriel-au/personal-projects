package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.ECM;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

/**
 * Operações na Tabela que possuei os mínimos, multiplos e grupos fracionados dos itens
 */
public class ECMService {

	/**
	 * TAG
	 */
	private static final String	TAG	= ECMService.class.getName();
	
	public static ECM recuperarECM(Integer codFilialExpedicao, Integer codMercadoria) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT CODFILEPD, ");
		sql.append(" 	    CODMER,    ");
		sql.append(" 	    QDEMNMVND, ");
		sql.append(" 	    FLGMPLQDE, ");
		sql.append(" 	    CODGRPFRC, ");
		sql.append(" 	    QDEMNMKIT  ");
		sql.append("   FROM PCAECM ECM ");
		sql.append("  WHERE ECM.CODFILEPD = %s ");
		sql.append("    AND ECM.CODMER = %s ");
		
		try {
			
			Database dataBase = DatabaseFactory.getInstance();
			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery(sql.toString(), codFilialExpedicao, codMercadoria));
			
			if (!result.isEmpty()) {

				Map<String, String> map = result.get(0);
				
				ECM ecm = new ECM();
				
				ecm.codFilialExpedicao 			= Util.getInteger(map.get("CODFILEPD"));
				ecm.codMercadoria 				= Util.getInteger(map.get("CODMER"));
				ecm.quantidadeMininaVenda 		= Util.getInteger(map.get("QDEMNMVND"));
				ecm.flagMultiplicadorQuantidade = map.get("FLGMPLQDE");
				ecm.codGrupoFracionado 			= Util.getInteger(map.get("CODGRPFRC"));
				ecm.quantidadeMinimaKit 		= Util.getInteger(map.get("QDEMNMKIT"));
	
				return ecm;
			}

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage(), e);
		}
		
		return null;
	}

}