package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class RepresentanteDAO {
	
	private static final String TAG = RepresentanteDAO.class.getName();
	private static Database db;
	private static final String	INDICATIVO_GERENTE_MERCADO = "6";
	
	/**
	 * Lista equipe representante.
	 *
	 * @return the list
	 * @throws SQLException the sQL exception
	 */
	public static List<Representante> listaEquipeRepresentante() throws SQLException {

		try {
			
			List<Representante> listaRepresentantes = new ArrayList<Representante>();
			
			Representante representante = null;
			db = DatabaseFactory.getInstance();

			StringBuilder query = new StringBuilder();
			query.append("SELECT CODREP, NOMREP ");
			query.append("FROM PCAEQP ");
			query.append("ORDER BY NOMREP");
			
			List<Map<String, String>> result = db.executeQuery(query.toString());
			
			if (!result.isEmpty()) {
				
				for (Map<String, String> map : result) {
					representante = new Representante();
					representante.codigoRepresentante = map.get("CODREP");
					representante.nomeRepresentante = map.get("NOMREP");
					listaRepresentantes.add(representante);
				}
			}
			
			return listaRepresentantes;

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Método que retorna o tipo de venda (tipo do pedido) do RCA (Tipo de pedido Normal ou Simplificado)
	 * Tipo Nivel representante (0 - Normal, 1 - Simplificado e 2 - Misto) 
	 * @param represenante - informações do representante
	 * @return String - tipo de venda permitido para o RCA
	 */
	/*public static String obterTipoVendaRepresentante(String tipoEntidade, String tipoNivelRepresentante, String tipoNivelRepresentanteEquipe){
		String tipoVenda = tipoNivelRepresentante == null ? "" : tipoNivelRepresentante;
		if(tipoEntidade != null && "6".equals(tipoEntidade) && tipoNivelRepresentanteEquipe != null){
			tipoVenda = tipoNivelRepresentanteEquipe;
		}
		return TipoVenda.toString(Integer.valueOf(tipoVenda));
	}*/
	
	public static void atualizaRepresentante(Representante representante) throws SQLException{

		Database db = DatabaseFactory.getInstance();	
		
		StringBuilder query = new StringBuilder();
		query.append("update pcarep set CODCEP= '%s',DESTIPLGR = '%s',TAMCAM= '%s',EMLREP='%s',DESLGR= '%s', NUMENDLGR= %s ,");
		query.append("DESCPLEND= '%s',NOMBAIEND = '%s',TLFRES= '%s',TLFCEL1= '%s',TLFCEL2='%s',INDACTEML= %s ,");
		query.append("INDACTSMS= %s where codrep= %s ");
		String sql = DatabaseUtil.montaQuery(query,representante.cep, representante.tipoLogradouro, representante.tamanhoCamiseta,representante.email, representante.descLogradouro,representante.numeroEndereco,representante.complemento,representante.bairro,representante.telResidencial,representante.numCelular1,representante.numCelular2,representante.flagEmail,representante.flagSms,representante.codigoRepresentante);		
		db.executeSQL(sql.toString());

	}
	
	
	/**
	 * Find representante.
	 *
	 * @param codigoRepresentanteEquipe the codigo representante equipe
	 * @return the representante
	 * @throws SQLException the sQL exception
	 */
	public static Representante findRepresentante(Integer codigoRepresentanteEquipe) throws SQLException {
		try {

			Representante rep = new Representante();
			Database db = DatabaseFactory.getInstance();

			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("  REPRESENTANTE.CODREP, ");
			query.append("  REPRESENTANTE.NOMREP, ");
			query.append("  REPRESENTANTE.INDCALCMS, ");
			query.append("  REPRESENTANTE.TIPNIVREP, ");
			query.append("	REPRESENTANTE.PSWREPTMK, ");
			query.append("  REPRESENTANTE.CODGRPAFD, ");
			query.append("  REPRESENTANTE.VLRPRVREP, ");
			query.append("  REPRESENTANTE.PERPVTEMP, ");
			query.append("  REPRESENTANTE.PERPVTREP, ");
			query.append("  REPRESENTANTE.PERTXAJUR, ");
			query.append("  REPRESENTANTE.DATCADREP, ");
			query.append("  REPRESENTANTE.TIPEDEVND, ");
			query.append("  REPRESENTANTE.PERTXACDI, ");
			query.append("  REPRESENTANTE.PERTXADPZ, ");
			query.append("  REPRESENTANTE.NUMDIANVO, ");
			query.append("  REPRESENTANTE.VLRDLR, ");
			query.append("  REPRESENTANTE.FTRBNFBDE, ");
			query.append("  REPRESENTANTE.FTRBNFBFC, ");
			query.append("  REPRESENTANTE.FTRBNFFLX, ");
			query.append("  REPRESENTANTE.FTRITEVND, ");
			query.append("  REPRESENTANTE.FTRBFCCST, ");
			query.append("  REPRESENTANTE.FTRGAPVDR, ");
			query.append("  REPRESENTANTE.PERVLRBNF, ");
			query.append("  REPRESENTANTE.PERBNFPED, ");
			query.append("  REPRESENTANTE.NROMAXNOT, ");
			query.append("  REPRESENTANTE.CODCEP, ");
			query.append("  REPRESENTANTE.DESTIPLGR, ");
			query.append("  REPRESENTANTE.DESLGR, ");
			query.append("  REPRESENTANTE.NUMENDLGR, ");
			query.append("  REPRESENTANTE.NOMBAIEND, ");
			query.append("  REPRESENTANTE.TAMCAM, ");
			query.append("  REPRESENTANTE.TLFCEL1, ");
			query.append("  REPRESENTANTE.DATULTATZ, ");
			query.append("  REPRESENTANTE.TLFRES, ");
			query.append("  REPRESENTANTE.TLFCEL2, ");
			query.append("  REPRESENTANTE.DESCPLEND, ");
			query.append("  REPRESENTANTE.EMLREP, ");
			query.append("  REPRESENTANTE.INDACTEML, ");
			query.append("  REPRESENTANTE.INDACTSMS, ");
			query.append("  REPRESENTANTE.TIPNATREP, ");
			query.append("  REPRESENTANTE.FTRKMRG, ");
			query.append("  REPRESENTANTE.PERUTZFLX, ");
			query.append("  REPRESENTANTE.VLRMAXBDEE, ");
			query.append("  EQUIPE.CODREP EQP_CODREP, ");
			query.append("  EQUIPE.NOMREP EQP_NOMREP, ");
			query.append("  EQUIPE.INDCALCMS EQP_INDCALCMS, ");
			query.append("  EQUIPE.TIPNIVREP EQP_TIPNIVREP  ");
			query.append(" FROM PCAREP REPRESENTANTE ");
			query.append(" LEFT JOIN PCAEQP EQUIPE ON EQUIPE.CODREP = %s ");
			
			Map<String, String> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoRepresentanteEquipe)).get(0);
			
			rep.codigoRepresentante     = result.get("CODREP");
			rep.nomeRepresentante       = result.get("NOMREP");
			rep.indicativoCalculoIcms   = result.get("INDCALCMS");
			rep.tipoVenda 				= result.get("TIPNIVREP");
			
			rep.tipoEntidade = result.get("TIPEDEVND");
			
			rep.isGerenteMercado = INDICATIVO_GERENTE_MERCADO.equalsIgnoreCase(rep.tipoEntidade) ? true : false;
			if (rep.isGerenteMercado) {
				rep.codigoGerenteMercado = result.get("CODREP");
				rep.nomeGerenteMercado = result.get("NOMREP");
				rep.codigoRepresentante     = result.get("EQP_CODREP");
				rep.nomeRepresentante       = result.get("EQP_NOMREP");
				rep.indicativoCalculoIcms   = result.get("EQP_INDCALCMS");
				rep.tipoVenda 				= result.get("EQP_TIPNIVREP");
			}
			
			rep.cep=result.get("CODCEP");
			rep.tipoLogradouro=result.get("DESTIPLGR");
			rep.tamanhoCamiseta=result.get("TAMCAM");
			rep.descLogradouro=result.get("DESLGR");
			rep.numeroEndereco=Util.getInteger(result.get("NUMENDLGR"));
			rep.complemento=result.get("DESCPLEND");
			rep.bairro=result.get("NOMBAIEND");
			rep.telResidencial=result.get("TLFRES");
			rep.numCelular1=result.get("TLFCEL1");
			rep.numCelular2=result.get("TLFCEL2");
			rep.flagEmail=Util.getInteger(result.get("INDACTEML"));
			rep.flagSms=Util.getInteger(result.get("INDACTSMS"));
			rep.codigoAfinidade = Util.getInteger(result.get("CODGRPAFD"));
			rep.fatorKMargem = Util.getBigDecimal(result.get("FTRKMRG"));
			rep.valorMaxBrinde = Util.getBigDecimal(result.get("VLRMAXBDEE"));
			rep.percentualBonificacaoPedido = Util.getDouble(result.get("PERBNFPED"));
			rep.percentualValorBonificacao = Util.getBigDecimal(result.get("PERVLRBNF"));
			rep.pecentualTaxaCDI = Util.getBigDecimal(result.get("PERTXACDI"));
			rep.pecentualTaxaDPZ = Util.getBigDecimal(result.get("PERTXADPZ"));
			rep.naturezaRepresentante = result.get("TIPNATREP");
			rep.email = result.get("EMLREP");
			rep.numeroMaximoNotaFiscal = Util.getInteger(result.get("NROMAXNOT"));
			rep.previsaoSemanal = Util.getBigDecimal(result.get("VLRPRVREP"));
			rep.percentualPrevisto = Util.getBigDecimal(result.get("PERPVTREP"));
			rep.percentualUtilizacaoFlex = Util.getBigDecimal(result.get("PERUTZFLX"));
			
			return rep;

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

}
