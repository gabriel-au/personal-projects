package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.CidadesTerritorios;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;

public class CidadesTerritoriosDAO {

private static final String TAG = CidadesTerritoriosDAO.class.getName();
	
	private static Database db;

	public static List<CidadesTerritorios> obtemCidades(){
		db = DatabaseFactory.getInstance();
		StringBuilder sql = new StringBuilder();
		/*sql.append("select distinct tet.codtetvnd,cid.codestuni,cid.nomcid")
		.append(" from pcatet tet ")
		.append(" inner join pcaclt clt on tet.codtetvnd=clt.codtetvnd ")
		.append(" inner join pcacli cli on clt.codcli = cli.codcli ")
		.append(" inner join pcabai bai on cli.codbai = bai.codbai ")
		.append(" inner join pcacid cid on cid.codcid = bai.codcid");*/

		sql.append("SELECT DISTINCT CLT.CODTETVND, CIDADE.NOMCID, CIDADE.CODESTUNI ");
		sql.append("FROM PCACLT CLT ");
		sql.append("INNER JOIN PCACLI CLI ON CLT.CODCLI = CLI.CODCLI  ");
		sql.append("INNER JOIN PCABAI BAIRRO ON CLI.CODBAI = BAIRRO.CODBAI  ");
		sql.append("INNER JOIN PCACID CIDADE ON BAIRRO.CODCID = CIDADE.CODCID  ");
		sql.append("ORDER BY CLT.CODTETVND ");

		List<CidadesTerritorios>lista=null;
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString());
			lista=criaListaCidades(result);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	
		return lista;
	}
	
	public static  List<CidadesTerritorios> criaListaCidades(List<Map<String, String>> result) {
		
		CidadesTerritorios cidade;
		List<CidadesTerritorios> listaCidades = new ArrayList<CidadesTerritorios>();
		for (Map<String, String> cid : result) {
			cidade = new CidadesTerritorios();
			cidade.codigoTerritorio=Util.getInteger(cid.get("CODTETVND"));
			cidade.codigoCidade=Util.getInteger(cid.get("CODCID"));
			cidade.nomeCidade=cid.get("NOMCID");
			cidade.unidadeFederal=cid.get("CODESTUNI");
			listaCidades.add(cidade);	
		}
		return listaCidades;
	}
}
