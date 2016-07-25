package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.vo.Beneficio;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class BeneficiosDAO {
	
	private static final String TAG = BeneficiosDAO.class.getName();

	public static List<Beneficio> findFiliaisExpedicaoBeneficio(Integer codigoBeneficio) throws Exception {
		try {
			Database db = DatabaseFactory.getInstance();
			List<Map<String, String>> result = db.executeQuery("SELECT CODBFC, CODFILEPD FROM PCABFE WHERE CODBFC = ?", Util.getStringValue(codigoBeneficio));
			
			return criaListaBeneficiosDisponiveis(result);
		} catch(Exception e) {
			Log.e(TAG, "Erro metodo findBeneficiosDisponiveis()", e);
			throw e;
		}
	}
	
	public static List<Beneficio> buscaBeneficiosDisponiveis(boolean indicaDescontoSimplificado, Integer codigoCliente, Integer scoreCliente, Integer codigoTerritorioVenda, Integer tipoVenda) throws Exception {

		try {
			
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
 			query.append(" SELECT DISTINCT BFX.CODBFC, BFX.DESBFC, BFX.TIPCNDPGT ");
			query.append("   FROM PCABCL BCL ");
			query.append("   JOIN PCABFX BFX ON BCL.CODBFC = BFX.CODBFC ");
			query.append("  WHERE BCL.CODCLI = ?");
			query.append("    AND (BCL.CODTETVND = ? OR BCL.CODTETVND = 0) ");
			query.append("    AND (STRFTIME('%Y%m%d','now') BETWEEN BFX.DATINIVLD AND BFX.DATFIMVLD) ");
			query.append("    AND (BFX.NUMMNMECR <= ? ) ");
			query.append("    AND ((BCL.NUMSEQFXA <= 0) || (BCL.NUMSEQFXA > 0 AND BCL.NUMSEQFXA = BFX.NUMSEQFXA)) ");
			query.append("    AND ( ");
			query.append("            (?)");
			query.append(" 	       OR (BFX.INDUTZBFC = ?) ");
			query.append(" 	       OR (BFX.INDUTZBFC IN (?, ?) AND BFX.INDUTZBFC = ?) ");
			query.append("        ) ");
			query.append(" ORDER BY BFX.DESBFC, BCL.CODBFC ");
			
			String[] parameters = new String[8];
			parameters[0] = Util.getStringValue(codigoCliente);
			parameters[1] = Util.getStringValue(codigoTerritorioVenda);
			parameters[2] = Util.getStringValue(scoreCliente);
			parameters[3] = Util.getStringValue((indicaDescontoSimplificado ? 1 : 0));
			parameters[4] = Util.getStringValue(TipoVendaPedido.MISTA.valor);
			parameters[5] = Util.getStringValue(TipoVendaPedido.NORMAL.valor);
			parameters[6] = Util.getStringValue(TipoVendaPedido.SIMPLIFICADA.valor);
			parameters[7] = Util.getStringValue(tipoVenda);
			
			List<Map<String, String>> result = db.executeQuery(query.toString(), parameters);
			return criaListaBeneficiosDisponiveis(result);
		}catch(Exception e){
			Log.e(TAG, "Erro metodo findBeneficiosDisponiveis()", e);
			throw e;
		}
	}
	
	public static Beneficio buscaBeneficio(Integer codigoBeneficio) throws Exception {
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			query
			.append(" SELECT BCL.CODBFC, BCL.CODCLI, BCL.CODTETVND, BCL.VLRBSECMP, BCL.QDEBSECMP, BCL.NUMSEQFXA, BCL.PERUTZDSC, BCL.PERUTZPRZ ")
			.append("        BFX.CODBFC,    ")
			.append("        BFX.NUMSEQFXA, ")
			.append("        BFX.DESBFC,    ")
			.append("        BFX.DATINIVLD, ")
			.append("        BFX.DATFIMVLD, ")
			.append("        BFX.TIPCNDPGT, ")
			.append("        BFX.TIPBFC,    ")
			.append("        BFX.NUMMNMECR, ")
			.append("        BFX.INDUTZCNT, ")
			.append("        BFX.PERMNMCMP, ")
			.append("        BFX.PERMAXCMP, ")
			.append("        BFX.VLRMNMCMP, ")
			.append("        BFX.VLRMAXCMP, ")
			.append("        BFX.VLRMNMPED, ")
			.append("        BFX.VLRMAXPED, ")
			.append("        BFX.PERMNMQDE, ")
			.append("        BFX.PERMAXQDE, ")
			.append("        BFX.QDEMNMCMP, ")
			.append("        BFX.QDEMAXCMP, ")
			.append("        BFX.QDEMNMPED, ")
			.append("        BFX.QDEMAXPED, ")
			.append("        BFX.INDOBRFXA, ")
			.append("        BFX.QDEDIAPRZ, ")
			.append("        BFX.INDPRZFIX, ")
			.append("        BFX.PERDSCBFC, ")
			.append("        BFX.INDDSCFIX, ")
			.append("        BFX.PERCMSADI, ")
			.append("        BFX.CODGRPCLI, ")
			.append("        BFX.PERDSCSMP, ")
			.append("        BFX.INDUTZBFC, ")
			.append("        BFX.PERMNMPTO, ")
			.append("        BFX.PERMAXPTO, ")
			.append("        BFX.QDEMNMPTO, ")
			.append("        BFX.QDEMAXPTO, ")
			.append("        BFX.DTLBFCCST, ")
            .append("   FROM PCABCL BCL     ")
			.append("   JOIN PCABFX BFX ON BCL.CODBFC = BFX.CODBFC")
			.append("  WHERE BCL.CODBFC = ? ");
			
			List<Map<String, String>> result = db.executeQuery(query.toString(), Util.getStringValue(codigoBeneficio));
			return criaListaBeneficiosDisponiveis(result).get(0);
		}catch(Exception e){
			Log.e(TAG, "Erro metodo findBeneficio()", e);
			throw e;
		}
	}
	
	public static List<Beneficio> buscaDetalheBeneficiosDisponiveis(Integer codigoCliente, Integer codigoBeneficio, Integer scoreCliente, Integer codigoTerritorioVenda) throws Exception {		
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			
			query
			.append("   SELECT BFX.CODBFC, BCL.VLRBSECMP, BFX.QDEMAXPED, BFX.INDPRZFIX, BFX.TIPBFC, BFX.PERCMSADI, BFX.VLRMAXCMP, BFX.INDOBRFXA, BFX.QDEMNMPED, BFX.PERMNMQDE, BFX.QDEMAXCMP, BFX.QDEMNMCMP, BFX.PERMNMPTO, BFX.VLRMNMCMP, BFX.PERMAXPTO, BCL.QDEBSECMP, BCL.NUMSEQFXA, BFX.NUMMNMECR, BFX.PERMNMCMP, BCL.VLRBSECMP, ")
			.append("          BFX.VLRMNMPED, BFX.VLRMAXPED, BFX.PERMAXCMP, BFX.PERMAXQDE, BFX.QDEMNMPTO, BFX.QDEMAXPTO, BFX.NUMSEQFXA as NUMSEQFXABFX,")
			.append("          BFX.QDEDIAPRZ - (BFX.QDEDIAPRZ * BCL.PERUTZPRZ) / 100 AS PRAZO, ")
			.append("          BFX.PERDSCBFC - (BFX.PERDSCBFC * BCL.PERUTZDSC) / 100 AS DESCONTOBENEFICIO, ")
			.append("          BFX.PERDSCSMP - (BFX.PERDSCSMP * BCL.PERUTZDSC) / 100 AS  DESCONTOSIMPLIFICADO ")
			.append("     FROM PCABCL BCL ")
			.append("     JOIN PCABFX BFX ON BCL.CODBFC = BFX.CODBFC ")
			.append("    WHERE BCL.CODBFC     = ? ")
			.append("      AND BCL.CODCLI     = ? ")
			.append("      AND (BCL.CODTETVND = ? OR BCL.CODTETVND = 0) ")
			.append("      AND (STRFTIME('%Y%m%d','now') BETWEEN BFX.DATINIVLD AND BFX.DATFIMVLD) ")
			.append("      AND BFX.NUMMNMECR <= ? ")  
			.append(" ORDER BY BFX.NUMSEQFXA DESC ");
			
			String[] parameters = new String[4];
			parameters[0] = Util.getStringValue(codigoBeneficio);
			parameters[1] = Util.getStringValue(codigoCliente);
			parameters[2] = Util.getStringValue(codigoTerritorioVenda);
			parameters[3] = Util.getStringValue(scoreCliente);
			
			List<Map<String, String>> result = db.executeQuery(query.toString(), parameters);
			return criaListaBeneficiosDisponiveis(result);
		}catch(Exception e){
			Log.e(TAG, "Erro metodo findBeneficiosDisponiveis()", e);
			throw e;
		}
	}
	
	/**
	 * Busca na Tabela Temporária de Benefícios Customizados dos Clientes TMPPCABCD
	 * @param numeroPedido
	 * @param codigoCliente
	 * @param codigoBeneficio
	 * @param numeroSequencialFaixa
	 * @return
	 * @throws Exception
	 */
	public static Beneficio buscaBeneficioCustomizadoTemporaria(Integer numeroPedido, Integer codigoCliente, Integer codigoBeneficio, Integer numeroSequencialFaixa) throws Exception {
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			
			query
			.append(" SELECT QDEDIAPRZ, QDEPRZREAL, PERDSCBFC FROM TMPPCABCD ")
			.append("  WHERE NUMPED    = ? ")
			.append("    AND CODCLI    = ? ")
			.append("    AND CODBFC    = ? ")
			.append("    AND NUMSEQFXA = ? ");
			
			String[] parameters = new String[4];
			parameters[0] = Util.getStringValue(numeroPedido);
			parameters[1] = Util.getStringValue(codigoCliente);
			parameters[2] = Util.getStringValue(codigoBeneficio);
			parameters[3] = Util.getStringValue(numeroSequencialFaixa);
			
			List<Map<String, String>> result = db.executeQuery(query.toString(), parameters);
			if (result.size() > 0) {
				return criaListaBeneficiosDisponiveis(result).get(0);
			}			
			return null;
		} catch(Exception e) {
			Log.e(TAG, "Erro metodo findBeneficiosDisponiveis()", e);
			throw e;
		}
	}
	
	private static List<Beneficio> criaListaBeneficiosDisponiveis(List<Map<String, String>> beneficioMap) throws Exception {
		
		Beneficio beneficio;
		List<Beneficio> listaBeneficio = new ArrayList<Beneficio>();
		for (Map<String, String> beneficioAux : beneficioMap) {
			beneficio = new Beneficio();
			beneficio.codigoBeneficio = Util.getInteger(beneficioAux.get("CODBFC"));
			beneficio.descricaoBeneficio = beneficioAux.get("DESBFC");
			beneficio.dataInicioValidade = DateUtil.formataData(beneficioAux.get("DATINIVLD"), DateUtil.DEFAULT_DATE_DATABASE);
			beneficio.dataFimValidade = DateUtil.formataData(beneficioAux.get("DATFIMVLD"), DateUtil.DEFAULT_DATE_DATABASE);
			beneficio.prazo = Util.getInteger(beneficioAux.get("PRAZO"));
			beneficio.valorMaximoCompra = Util.getBigDecimal(beneficioAux.get("VLRMAXCMP"));
			beneficio.valorMinimoCompra = Util.getBigDecimal(beneficioAux.get("VLRMNMCMP"));
			beneficio.descontoBeneficio = Util.getBigDecimal(beneficioAux.get("DESCONTOBENEFICIO"));
			beneficio.descontoSimplificado = Util.getBigDecimal(beneficioAux.get("DESCONTOSIMPLIFICADO"));
			beneficio.numeroSequencialFaixa = Util.getInteger(beneficioAux.get("NUMSEQFXA"));
			beneficio.numeroSequencialFaixaBFX = Util.getInteger(beneficioAux.get("NUMSEQFXABFX"));
			beneficio.tipoCondicaoPagamento = Util.getInteger(beneficioAux.get("TIPCNDPGT"));
			beneficio.quantidadeMinimaPontos = Util.getInteger(beneficioAux.get("QDEMNMPTO"));
			beneficio.quantidadeMaximaPontos = Util.getInteger(beneficioAux.get("QDEMAXPTO"));
			beneficio.percentualMinimoPontos = Util.getBigDecimal(beneficioAux.get("PERMNMPTO"));
			beneficio.percentualMaximoPontos = Util.getBigDecimal(beneficioAux.get("PERMAXPTO"));
			beneficio.quantidadeMaximaCompra = Util.getInteger(beneficioAux.get("QDEMAXCMP"));
			beneficio.quantidadeMinimaCompra = Util.getInteger(beneficioAux.get("QDEMNMCMP"));
			beneficio.percentualMinimoCompra = Util.getBigDecimal(beneficioAux.get("PERMNMCMP"));
			beneficio.valorBaseCompra = Util.getBigDecimal(beneficioAux.get("VLRBSECMP"));
			beneficio.valorMinimoPedido = Util.getBigDecimal(beneficioAux.get("VLRMNMPED"));
			beneficio.valorMaximoPedido = Util.getBigDecimal(beneficioAux.get("VLRMAXPED"));
			beneficio.percentualMaximoCompra = Util.getBigDecimal(beneficioAux.get("PERMAXCMP"));
			beneficio.percentualMinimoQuantidade = Util.getBigDecimal(beneficioAux.get("PERMNMQDE"));
			beneficio.quantidadeMinimaPedido = Util.getInteger(beneficioAux.get("QDEMNMPED"));
			beneficio.percentualMaximoQuantidade = Util.getBigDecimal(beneficioAux.get("PERMAXQDE"));
			beneficio.quantidadeBaseCompra = Util.getInteger(beneficioAux.get("QDEBSECMP"));
			beneficio.quantidadeMaximaPedido = Util.getInteger(beneficioAux.get("QDEMAXPED"));
			beneficio.indicaFaixaObrigatoria = Util.getInteger(beneficioAux.get("INDOBRFXA"));
			beneficio.codigoGrupoCliente = Util.getInteger(beneficioAux.get("CODGRPCLI"));
			beneficio.percentualComissao = Util.getBigDecimal(beneficioAux.get("PERCMSADI"));
			beneficio.tipoBeneficio = Util.getInteger(beneficioAux.get("TIPBFC"));
			beneficio.tipoBeneficio = Util.getInteger(beneficioAux.get("TIPBFC"));
			beneficio.isPrazoFixo = Util.getInteger(beneficioAux.get("INDPRZFIX"));
			
			
			//tabela temporaria TMPPCABCD
			beneficio.quantidadeDiaPrazoTemp = Util.getInteger(beneficioAux.get("QDEDIAPRZ"));
			beneficio.quantidadePrazoRealTemp = Util.getInteger(beneficioAux.get("QDEPRZREAL"));
			beneficio.percentualDescontoTemp = Util.getBigDecimal(beneficioAux.get("PERDSCBFC"));
			listaBeneficio.add(beneficio);
		}
		return listaBeneficio;
	}

	
}
