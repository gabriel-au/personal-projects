package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.ModeloDistribuicao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

/**
 * Modelo de Distribuicao
 * <p />
 * Arquivo fonte C++ DlgRgrDtb.cpp
 * <p />
 * Modulo Sistema Pedidos
 * 
 * @author BRQ Mobile Team
 * @since 23/01/12
 */
public class ModeloDistribuicaoService {
	
	private static final String  TAG = ModeloDistribuicaoService.class.getName();
	private static final String  MARTINS = "MARTINS";
	private static final String  TRANSPORTE_PROPRIO_MARTINS = "TRANSPORTE PRÓPRIO MARTINS";
	private static final Integer CODIGO_TABELA_TRANSPORTE_PROPRIO_MARTINS = 0; 

	/**
	 * Metodo responsavel por buscar todos os modelos de distribuicao valido
	 * 
	 * @param codCli
	 *            codigo do cliente
	 * @param codFilExp
	 *            filial de expedicao
	 * @param codFilFat
	 *            filial de faturamento
	 * @param isExcecao
	 *            flag responsavel por indicar se a busca ira retornar somente os modelos de distribuicao normais ou somente os de excecao 
	 * @return
	 */
	public static List<ModeloDistribuicao> listarModeloDistricao(Integer codCli,Integer codFilExp, Integer codFilFat, Boolean isExcecao) {
		
		List<ModeloDistribuicao> listaModeloDistribuicao = new ArrayList<ModeloDistribuicao>();
		Database db = DatabaseFactory.getInstance();
		final int regraValida = 1;
		final int indExcecao = isExcecao ? 1 : 0;
		StringBuilder query = new StringBuilder();

		query.append("SELECT CODRGRDTB, RAZSOCTRP, DESRGRDTB, INDTRPPPO, INDRGROBR, INDTABMAN")
				.append(" FROM PCARGR ")
				.append(" WHERE ")
				.append(" CODCLI = "     			+ codCli)
				.append(" AND CODFILEPD = "		 	+ codFilExp)
				.append(" AND CODFILFAT = "     	+ codFilFat)
				.append(" AND INDRGRVLD = "     	+ regraValida)
				.append(" AND INDRGRECC = "     	+ indExcecao)
				.append(" AND ")
				.append(" strftime('%Y%m%d','now')")
				.append(" BETWEEN DATINIVLD AND DATFIMVLD")
				.append(" ORDER BY INDRGROBR DESC");
	
				try {
					List<Map<String, String>> result =  db.executeQuery(query.toString());
					listaModeloDistribuicao = carregarListaModeloDistribuicao(result,indExcecao);
				} catch (Exception e) {
					Log.e(TAG,e.getMessage());
				}
						
	
		return listaModeloDistribuicao;

	}
	
	
	/**
	 * Metodo Responsavel por carregar a lista de modelo de distribuicao
	 * @param modelosDistribuicao
	 * @return Lista de Modelos de distribuicao validados
	 * @throws Exception 
	 */
	private static List<ModeloDistribuicao> carregarListaModeloDistribuicao(List<Map<String,String>> modelosDistribuicao, Integer indExcecao) throws Exception{
	
		try {
			
			List<ModeloDistribuicao> listaModeloDistribuicao = new ArrayList<ModeloDistribuicao>();
			
			for(Map<String, String> modelos : modelosDistribuicao){
				
				ModeloDistribuicao md = new ModeloDistribuicao();	
				md.codRegraTabela = Util.getInteger(modelos.get("CODRGRDTB"));
				md.descRegraTabela = modelos.get("DESRGRDTB");
				md.razSocialTransportadora = modelos.get("RAZSOCTRP");
				md.regraObrigatoria = Util.getInteger(modelos.get("INDRGROBR"));
				md.transporteProprio = Util.getInteger(modelos.get("INDTRPPPO"));
				md.indicadorTabelaManual = modelos.get("INDTABMAN");
				listaModeloDistribuicao.add(md);
			}
			
			return validaRegra(listaModeloDistribuicao, indExcecao);
		
		}catch (Exception e) {
			Log.e(TAG, "Erro ao buscar modelo de distribuicao", e);
			throw e;
		}
	}
	
	/**
	 * Metodo resposavel por validar as regras na lista de modelo de distribuicao
	 * 
	 * @param listaModeloDistribuicao
	 * @param indExcecao
	 * @return
	 * @throws Exception 
	 */
	private static List<ModeloDistribuicao> validaRegra(List<ModeloDistribuicao> listaModeloDistribuicao, Integer indExcecao) throws Exception{
		
		try {
			
			int tempTransporteProprio = 0;
				
			List<ModeloDistribuicao> listaModeloDistribuicaoValidada = new ArrayList<ModeloDistribuicao>();
			
			for (ModeloDistribuicao modeloDistribuicao : listaModeloDistribuicao) {
					
				if(modeloDistribuicao.regraObrigatoria == 1){
					listaModeloDistribuicaoValidada.clear();
					listaModeloDistribuicaoValidada.add(modeloDistribuicao);
					break;
				}
				if(modeloDistribuicao.transporteProprio == 1){
					tempTransporteProprio = modeloDistribuicao.transporteProprio;
				}
					
				listaModeloDistribuicaoValidada.add(modeloDistribuicao);
			
			}
			
			if((tempTransporteProprio == 1 || listaModeloDistribuicaoValidada.isEmpty()) && indExcecao == 0 ){
				listaModeloDistribuicaoValidada.add(0,preencheTransporteProprio());
			}	
			
			return listaModeloDistribuicaoValidada;
			
		}catch (Exception e) {
			Log.e(TAG, "Erro ao buscar validar regra de modelo de distribuicao", e);
			throw e;
		}
	}
	
	/**
	 * Metodo responsavel por retornar um objeto com os valores fixos definido pelo martins.
	 * 
	 * @return
	 */
	public static ModeloDistribuicao preencheTransporteProprio(){
		ModeloDistribuicao md = new ModeloDistribuicao();
		
		md.codRegraTabela = CODIGO_TABELA_TRANSPORTE_PROPRIO_MARTINS;
		md.descRegraTabela = TRANSPORTE_PROPRIO_MARTINS;
		md.razSocialTransportadora = MARTINS;
		
		return md;		
	}

	
	/**
	 * Recupera dados do modelo de distribuição mediante ao dados chave da mesma
	 * 
	 * @param codCliente código do cliente
	 * 
	 * @param codRegraDistribuicao código da regra de distribuição
	 * 
	 * @return instância de ModeloDistribuicao com os dados preenchidos
	 */
	public static ModeloDistribuicao recuperaModeloDistribuicao(Integer codCliente, Integer codRegraDistribuicao) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("   SELECT CODRGRDTB, ");
		sql.append("		  CODCLI, ");
/*		sql.append("		  CODFILEPD, ");
		sql.append("		  CODFILFAT, ");
		sql.append("		  INDTABMAN, ");
		sql.append("		  INDTRPPPO, ");
		sql.append("		  INDRGROBR, ");
		sql.append("		  INDRGRECC, ");
		sql.append("		  INDCNTDSN, ");
		sql.append("		  DATINIVLD, ");
		sql.append("		  DATFIMVLD, ");
		sql.append("		  CODTABFRT, ");
		sql.append("		  PERPADFRT, ");
		sql.append("		  VLRFRTMIN, ");
		sql.append("		  INDRGRVLD, ");*/
		sql.append("		  RAZSOCTRP ");
		//sql.append("		  ,DESRGRDTB, ");
		sql.append("	 FROM PCARGR RGR ");
		sql.append("	WHERE RGR.CODRGRDTB = %s ");
		sql.append("	  AND RGR.CODCLI = %s ");
		
		
		ModeloDistribuicao modeloDistribuicao = new ModeloDistribuicao();

		Database dataBase = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery(sql, codRegraDistribuicao, codCliente));
			if (result.isEmpty()) {
				
				Map<String, String> reg = result.get(0);
				
				modeloDistribuicao.codRegraTabela = Integer.parseInt(reg.get("CODRGRDTB"));
				modeloDistribuicao.descRegraTabela = reg.get("DESRGRDTB");
				modeloDistribuicao.razSocialTransportadora = reg.get("RAZSOCTRP");
	
				//TODO, Caso necessário preencher os demais campos
			}
		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage(), e);
		}
		return modeloDistribuicao;
	}

}