package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;

public class PosicaoVendaDAO {
	
	private static Database db;
	
	
	public static List<Map<String,String>>obterPosicaoVendasPorCliente(Date dataInicio, Date dataFim){
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select ped.codcli,cli.nomcli,sum((ITE.QDEMERPED - coalesce(QDEMERCTD,0)) * ITE.VLRLIQMER) LIQUIDO, sum((ITE.QDEMERPED - coalesce(QDEMERCTD,0)) * ITE.VLRSEMENC) EFETIVO ");
		sql.append("from ");
		sql.append("pcaped ped inner join pcacli cli on ");
		sql.append("cli.CODCLI = ped.codCli ");      
		sql.append("inner join pcaite ite on ");
		sql.append("ped.numped = ite.numped ");   
		sql.append("left join pcacrt crt on ");
		sql.append("ite.numped = crt.numped and     "); 
		sql.append("ite.codfilepd = crt.codfilepd and  ");   
		sql.append("ite.codfilfat = crt.codfilfat and  ");  
		sql.append("ite.codmer = crt.codmer and    ");
		sql.append("ite.codcndpgt = crt.codcndpgt and ");    
		sql.append("ite.numnotfsc = crt.numnotfsc and ");   
		sql.append("ite.codpmc = crt.codpmc and ");
		sql.append("ite.tipngcmer = crt.tipngcmer ");
		sql.append("WHERE ");
		sql.append("ped.datped BETWEEN  %s and %s ");    
		sql.append("AND ped.numped in (select numped from pcasit where numped = ped.numped) ");
		sql.append("GROUP BY ped.codcli");
		
		String query = DatabaseUtil.montaQuery(sql, DateUtil.formataData(dataInicio, "yyyyMMdd"), DateUtil.formataData(dataFim,"yyyyMMdd"));
		
		db = DatabaseFactory.getInstance();
		
		try {
			listMap = db.executeQuery(query);
		} catch (Exception e) {
			Log.e("PesicaoVendaDAO - obterPosicaoVendasPorCliente - ", e.getMessage());
		}
		
		return listMap;
	}
	
	public static List<Map<String,String>>obterPosicaoVendasPorPrazoDePagamento(Date dataInicio, Date dataFim){
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select ite.CODCNDPGT,sum((ITE.QDEMERPED - coalesce(QDEMERCTD,0)) * ITE.VLRLIQMER) LIQUIDO, sum((ITE.QDEMERPED - coalesce(QDEMERCTD,0)) * ITE.VLRSEMENC) EFETIVO ");
		sql.append("from ");
		sql.append("pcaped ped inner join pcacli cli on ");
		sql.append("cli.CODCLI = ped.codCli ");            
		sql.append("inner join pcaite ite on ");
		sql.append("ped.numped = ite.numped ");      
		sql.append("left join pcacrt crt on ");
		sql.append("ite.numped = crt.numped and ");      
		sql.append("ite.codfilepd = crt.codfilepd and ");    
		sql.append("ite.codfilfat = crt.codfilfat and ");   
		sql.append("ite.codmer = crt.codmer and ");   
		sql.append("ite.codcndpgt = crt.codcndpgt and ");    
		sql.append("ite.numnotfsc = crt.numnotfsc and ");   
		sql.append("ite.codpmc = crt.codpmc and ");   
		sql.append("ite.tipngcmer = crt.tipngcmer "); 
		sql.append("WHERE ");     
		sql.append("ped.datped BETWEEN  %s and %s ");    
		sql.append("AND ped.numped in (select numped from pcasit where numped = ped.numped) ");
		sql.append("GROUP BY ite.CODCNDPGT");
		
//		sql.append("select cnd.QDEDIAPRZ,cnd.NUMPCLCND,cnd.NUMPODPCL,cnd.TipFnmCnd,cnd.TipCobCnd,ite.CODCNDPGT,sum((ITE.QDEMERPED - coalesce(QDEMERCTD,0)) * ITE.VLRLIQMER) LIQUIDO, sum((ITE.QDEMERPED - coalesce(QDEMERCTD,0)) * ITE.VLRSEMENC) EFETIVO ");
//		sql.append("from ");
//		sql.append("pcaped ped inner join pcacli cli on ");
//		sql.append("       cli.CODCLI = ped.codCli ");           
//		sql.append("inner join pcaite ite on ");
//		sql.append("       ped.numped = ite.numped ");              
//		sql.append("inner join pcacnd cnd on ");
//		sql.append("      cnd.CODCNDPGT = ite.codcndpgt and ");     
//		sql.append("      cnd.codfilepd = ite.codfilepd ");
//		sql.append("left join pcacrt crt on ");
//		sql.append("     ite.numped = crt.numped and ");     
//		sql.append("     ite.codfilepd = crt.codfilepd and ");     
//		sql.append("     ite.codfilfat = crt.codfilfat and ");   
//		sql.append("     ite.codmer = crt.codmer and ");   
//		sql.append("     ite.codcndpgt = crt.codcndpgt and ");    
//		sql.append("     ite.numnotfsc = crt.numnotfsc and ");   
//		sql.append("     ite.codpmc = crt.codpmc and ");   
//		sql.append("     ite.tipngcmer = crt.tipngcmer ");
//		sql.append("   WHERE ");     
//		sql.append("ped.datped BETWEEN  %s and %s ");    
//		sql.append("     AND ped.numped in (select numped from pcasit where numped = ped.numped) ");
//		sql.append("GROUP BY ite.CODCNDPGT");
		
		String query = DatabaseUtil.montaQuery(sql, DateUtil.formataData(dataInicio, "yyyyMMdd"), DateUtil.formataData(dataFim,"yyyyMMdd"));
		
		db = DatabaseFactory.getInstance();
		
		try {
			listMap = db.executeQuery(query);
		} catch (Exception e) {
			Log.e("PosicaoVendaDAO - obterPosicaoVendasPorCliente - ", e.getMessage());
		}
		
		return listMap;
	}
	
	
	public static List<Map<String,String>>obterDescricaoPosicaoVendasPorPrazoDePagamento(Integer codigoCondicaoPagamento){
		List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select  distinct numpclcnd || ' parcela(s) em ' || cnd.QDEDIAPRZ || ' Dia(s) ' || ");
		sql.append("     case when numpclcnd > 1 then ' Demais parcelas de ' || numpodpcl || ' em ' || numpodpcl || ' dias ' ");
		sql.append("          else '' ");
		sql.append("      end         ");
		sql.append("      || ' / ' || case when tipfnmcnd = 1 and codcndpgt <> 111 then 'Financ. Proprio' ");
		sql.append("                  when tipfnmcnd = 2 then 'Financ. Vendor'                   ");
		sql.append("                  else 'Financ. Proprio / Antecipado '                   ");
		sql.append("                  end as descricaoCondicao ");
		sql.append("     from pcacnd cnd where cnd.codcndpgt = %s ");
		String query = DatabaseUtil.montaQuery(sql,codigoCondicaoPagamento);
		
		db = DatabaseFactory.getInstance();
		
		try {
			listMap = db.executeQuery(query);
		} catch (Exception e) {
			Log.e("PosicaoVendaDAO - obterDescricaoPosicaoVendasPorPrazoDePagamento - ", e.getMessage());
		}
		
		return listMap;
	}
	
	
	
}
