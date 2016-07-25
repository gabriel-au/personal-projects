package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.FiliaisBeneficioCustomizado;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.Util;

public class FiliaisBeneficioCustomizadoDAO {
	
	private static final String TAG = FiliaisBeneficioCustomizadoDAO.class.getName();

	public static List<FiliaisBeneficioCustomizado> findFiliaisBeneficioCustomizado(Integer codigoBeneficio) throws Exception {

		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			query.append(" SELECT CODBFC, CODFILEPD") 
			.append(" from pcabfe ")  
			.append(" where CODBFC = " + codigoBeneficio);
			List<Map<String, String>> result = db.executeQuery(query.toString());
			return criaListaFiliaisBeneficioDisponivel(result);
		} catch(Exception e){
			Log.e(TAG, "Erro metodo findFiliaisBeneficioCustomizado()", e);
			throw e;
		}
	}
	
	private static List<FiliaisBeneficioCustomizado> criaListaFiliaisBeneficioDisponivel(List<Map<String, String>> filiaisBeneficioCustomizadoMap) throws Exception {
		FiliaisBeneficioCustomizado filiaisBeneficioCustomizado;
		List<FiliaisBeneficioCustomizado> listaFiliaisBeneficioCustomizado = new ArrayList<FiliaisBeneficioCustomizado>();
		for (Map<String, String> beneficioDisponivelAux : filiaisBeneficioCustomizadoMap) {
			filiaisBeneficioCustomizado = new FiliaisBeneficioCustomizado();
			filiaisBeneficioCustomizado.codigoBeneficio = Util.getInteger(beneficioDisponivelAux.get("CODBFC"));
			filiaisBeneficioCustomizado.codigoFilialExpedicao = Util.getInteger(beneficioDisponivelAux.get("CODFILEPD"));
			listaFiliaisBeneficioCustomizado.add(filiaisBeneficioCustomizado);
		}
		return listaFiliaisBeneficioCustomizado;
	}
}
