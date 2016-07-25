package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Cortes;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class PedidoCortesDAO {

	private static final String TAG = PedidoCortesDAO.class.getName();

	public static List<Cortes> carregaListaCortes(Integer numPedido) throws Exception {
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder querySB = new StringBuilder();
			querySB.append("SELECT CRT.NUMPED, CRT.CODFILEPD, CRT.CODFILFAT, CRT.CODMER, CRT.QDEMERPED, MER.DESMER, ");
			querySB.append("CRT.QDEMERCTD, CRT.QDEMERNGC, CRT.CODMTVCTS, MCT.DESMTVCTS, ITE.VLRLIQMER ");
			querySB.append("from  PCACRT CRT ");
			querySB.append("left join PCAMCT MCT on MCT.CODMTVCTS = CRT.CODMTVCTS ");
			querySB.append("left join PCAITE ITE on CRT.CODMER = ITE.CODMER AND ITE.NUMPED = CRT.NUMPED ");
			querySB.append("left join CADMER MER on ITE.CODMER = MER.CODMER ");
			querySB.append("where ITE.NUMPED =" + numPedido + " order by ITE.CODMER");

			String query = DatabaseUtil.montaQuery(querySB);
			List<Map<String, String>> result = db.executeQuery(query);

			return criaListaCortes(result);

		} catch (Exception e) {
			Log.e(TAG, "Erro metodo carregaListaCortes()", e);
			throw e;
		}
	}

	private static List<Cortes> criaListaCortes(List<Map<String, String>> cortesMap) throws Exception {
		List<Cortes> listaCortes = new ArrayList<Cortes>();
		
		try {
			for (Map<String, String> cortesAux : cortesMap) {
				Cortes corte = new Cortes();
				corte.codigoMercadoria = Util.getInteger(cortesAux.get("CODMER"));
				corte.filialExpedicao = Util.getInteger(cortesAux.get("CODFILEPD"));
				corte.filialFaturamento = Util.getInteger(cortesAux.get("CODFILFAT"));
				corte.valorLiqMercadoria = Util.getBigDecimal(cortesAux.get("VLRLIQMER"));
				corte.descricaoMercadoria = cortesAux.get("DESMER");
				corte.qtdMercadoriaPedido = Util.getInteger(cortesAux.get("QDEMERPED"));
				corte.qtdMercadoriaCortado = Util.getInteger(cortesAux.get("QDEMERCTD"));
				corte.qtdMercadoriaRecuperada = Util.getInteger(cortesAux.get("QDEMERNGC"));
				corte.descMotivoCorte = cortesAux.get("DESMTVCTS");
				listaCortes.add(corte);
			}
			
		} catch (Exception e) {
			Log.e(TAG, "Erro metodo criaListaCortes()", e);
			throw e;
		}
		
		return listaCortes;
	}

	public static List<Cortes> calculaValorLiquido(List<Cortes> listaCortes) {
		List<Cortes> lista = new ArrayList<Cortes>();
		
		for (Cortes cortes : listaCortes) {
			cortes.valorLiquidoCalculado = (cortes.qtdMercadoriaPedido - cortes.qtdMercadoriaCortado - cortes.qtdMercadoriaRecuperada) * cortes.valorLiqMercadoria.doubleValue();
			lista.add(cortes);
		}
		
		return lista;
	}
	
}
