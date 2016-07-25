package br.com.martins.vendas.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.FaixaMargem;
import br.com.martins.vendas.services.calculodepreco.FatorAjuste;
import br.com.martins.vendas.services.calculodepreco.Fracionamento;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.DiretrizSTB;
import br.com.martins.vendas.vo.Kit;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PercentualICMS;
import br.com.martins.vendas.vo.PercentualIPI;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.StringUtil;
import com.brq.mobile.framework.util.Util;

/**
 * Classe respons‡vel por acesso a base de dados para recuperar e/ou inserir dados referentes ao
 * calculo de pre�os
 */
public class CalculoPrecoDAO {

	private static final String	TAG	= CalculoPrecoDAO.class.getName();
	
	/**
	 * Para variáveis que não podem ser inicializadas com BigDecimal.ZERO
	 */
	private static final BigDecimal	MENOS_UM	= new BigDecimal(-1);

	private static Database		db;

	/**
	 * Obtem o preco Bruto da Mercadoria na tabela de livro de pre�os(PCALVR)
	 * 
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoGrupoCliente
	 * @return
	 */
	public static Preco obtemPrecoBrutoMercadoriaNoLivroDePreco(Mercadoria mercadoria, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer codigoGrupoCliente) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT [CODFILEPD],     ");
		sql.append("       [CODFILFAT],     ");
		sql.append("       [NUMRLCCID],     ");
		sql.append("       [CODMER],        ");
		sql.append("       [TIPICTMER],     ");
		sql.append("       [CODSMBSIT],     ");
		sql.append("       [CODFLXPCO],     ");
		sql.append("       [FLGMEREXV],     ");
		sql.append("       [INDMERMTD],     ");
		sql.append("       [CODTBTICM],     ");
		sql.append("       [VLRUNTBRT],     ");
		sql.append("       [FLGPEE],        ");
		sql.append("       [TIPMCOREP],     ");
		sql.append("       [CSTCSTLGT],     ");
		sql.append("       [PERTBTMER],     ");
		sql.append("       [PERCMSMER]      ");
		sql.append("  FROM PCALVR           ");
		sql.append(" WHERE [CODMER]    = ?  ");
		sql.append("   AND [CODFILEPD] = ?  ");
		sql.append("   AND [CODFILFAT] = ?  ");
		sql.append("   AND [NUMRLCCID] = ?  ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(mercadoria.codigo);
		parameters[1] = Util.getStringValue(codigoFilialExpedicao);
		parameters[2] = Util.getStringValue(codigoFilialFaturamento);
		parameters[3] = Util.getStringValue(codigoGrupoCliente);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			if (!queryResult.isEmpty()) {
				return convertResultToPreco(queryResult);
			}

		} catch (SQLException e) {
			Log.e("CalculoPrecoDAO - buscaPrecoBrutomercadoria ", e.getMessage());
		}
		return null;
	}

	/**
	 * Busca na tabela PCALGC se a mercadoria tem preco especial
	 * 
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoGrupoCliente
	 * @param siglaEstado
	 */
	public static Preco buscaPrecoEspecial(final Integer codigoMercadoria, 
			final Integer codigoFilialExpedicao, 
			final Integer codigoFilialFaturamento, 
			final Integer codigoGrupoCliente, 
			final String siglaEstado) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT [CODFILEPD],       ");
		sql.append("		[CODFILFAT],       ");
		sql.append("		[CODESTUNI],       ");
		sql.append("		[CODGRPCLI],       ");
		sql.append("		[CODMER],          ");
		sql.append("		[VLRUNTBRT],       ");
		sql.append("		[DATINIVLD],       ");
		sql.append("		[DATFIMVLD],       ");
		sql.append("		[INDUTZACO],       ");
		sql.append("		[INDUTZFLX],       ");
		sql.append("		[INDUTZBFC],       ");
		sql.append("		[INDUTZMNM],       ");
		sql.append("		[INDUTZFRC]        ");
		sql.append("   FROM PCALGC             ");
		sql.append("  WHERE [CODFILEPD] = ?    ");
		sql.append("    AND [CODFILFAT] = ?    ");
		sql.append("    AND [CODESTUNI] = ?    ");
		sql.append("    AND [CODGRPCLI] = ?    ");
		sql.append("    AND [CODMER]    = ?    ");
		sql.append("    AND STRFTIME('%Y%m%d', DATETIME('NOW')) BETWEEN [DATINIVLD] AND [DATFIMVLD] ");

		String[] parameters = new String[5];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoFilialFaturamento);
		parameters[2] = Util.getStringValue(siglaEstado);
		parameters[3] = Util.getStringValue(codigoGrupoCliente);
		parameters[4] = Util.getStringValue(codigoMercadoria);

		db = DatabaseFactory.getInstance();

		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			if (queryResult.size() > 0) {
				return convertResultToPreco(queryResult);
			}
		} catch (SQLException e) {
			Log.e("CalculoPrecoDAO - buscaPrecoEspecial", e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param mercadoria
	 * @param territorioVenda
	 * @param codigoFilialFaturamento
	 * @param codigoFilialExpedicao
	 */
	public static BigDecimal obtemPrecoMinimoDaMercadoria(Integer codigoMercadoria, 
			String territorioVenda, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT VLRMNMMER      ");
		sql.append("   FROM PCAMNM         ");
		sql.append("  WHERE CODESTUNI = ?  ");
		sql.append("    AND CODMER    = ?  ");
		sql.append("    AND CODFILEPD = ?  ");
		sql.append("    AND CODFILFAT = ?  ");
		sql.append("    AND STRFTIME( '%Y%m%d', DATETIME('NOW')) BETWEEN DATINIVLD AND DATFIMVLD ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(territorioVenda);
		parameters[1] = Util.getStringValue(codigoMercadoria);
		parameters[2] = Util.getStringValue(codigoFilialExpedicao);
		parameters[3] = Util.getStringValue(codigoFilialFaturamento);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			if (queryResult.size() > 0) {
				return BigDecimal.valueOf(Double.parseDouble(queryResult.get(0).get("VLRMNMMER")));
			}
		} catch (SQLException e) {
			Log.e("CalculoPrecoDAO - obtemPrecoMinimoDaMercadoria", e.getMessage());
		}

		return BigDecimal.ZERO;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoGrupoFracionamento
	 * @param valorFaixa
	 * @return
	 */
	public static Fracionamento obtemCustoFracionado(Integer codigoFilialExpedicao, 
			Integer codigoMercadoria,
			BigDecimal custoFracionado) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT VLRCSTFRC, PERCSTFRC, VLRINIFXA, VLRFIMFXA ");
		sql.append("  FROM  PCAFRC frc, PCAECM ecm ");
		sql.append("  WHERE frc.CODGRPFRC = ecm.CODGRPFRC ");
		sql.append("    AND frc.CODFILEPD = ECM.CODFILEPD ");
		sql.append("  	AND frc.CODFILEPD = ? ");
		sql.append("    AND ecm.CODMER = ? ");
		sql.append("    AND ? BETWEEN VLRINIFXA AND VLRFIMFXA ");

		double custo = custoFracionado.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoMercadoria);
		parameters[2] = Util.getStringValue(custo);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			return convertResultToFracionamento(result);
		} catch (SQLException e) {
			Log.e("CalculoPrecoDAO - obemCustoFracionamento", e.getMessage());
		}
		return null;
	}

	/**
	 * Verifica se condicao de pagamento valida.
	 *
	 * @param codigoCondicaoPagamento the codigo condicao pagamento
	 * @param mercadoria the mercadoria
	 * @return true, if successful
	 */
	public static boolean verificaSeCondicaoDePagamentoValida(Integer codigoCondicaoPagamento, 
			Mercadoria mercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT 1      ");
		sql.append("   FROM PCACPT ");
		sql.append("  WHERE CODCNDPGT = ?  ");
		sql.append("    AND TIPMER    = ?  ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(codigoCondicaoPagamento);
		parameters[1] = Util.getStringValue(mercadoria.tipo);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			return queryResult.size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoCidadePreco
	 * @return
	 */
	public static Integer obtemCodigoRelacionamentoCliente(Integer codigoFilialExpedicao, 
			Integer codigoCidadePreco) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NUMRLCCID     ");
		sql.append("   FROM PCACPC        ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODCIDPCO = ? ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoCidadePreco);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			return Integer.parseInt(queryResult.get(0).get("NUMRLCCID"));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return BigDecimal.ZERO.intValue();
	}

	/**
	 * Verifica se a mercadoria tem IPI.
	 * 
	 * @param mercadoria
	 * @return
	 */
	public static boolean mercadoriaTemIPI(Mercadoria mercadoria) {
		db = DatabaseFactory.getInstance();
		final String query = "SELECT 1 FROM PCAIPI WHERE CODMER = ?";
		try {
			List<Map<String, String>> queryResult = db.executeQuery(query, Util.getStringValue(mercadoria.codigo));
			return queryResult.size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * Busca valor do IPI da mercadoria
	 * 
	 * @param mercadoria
	 * @return
	 */
	public static PercentualIPI obtemIPIMercadoria(Mercadoria mercadoria) {
		db = DatabaseFactory.getInstance();
		final String query = "SELECT PERIPIMER, VLRPLTIPI FROM PCAIPI WHERE CODMER = ?";
		PercentualIPI percentualIPI = new PercentualIPI();
		percentualIPI.percentualIPI = BigDecimal.ZERO;
		percentualIPI.valorPalletIPI = BigDecimal.ZERO;
		try {
			List<Map<String, String>> queryResult = db.executeQuery(query, Util.getStringValue(mercadoria.codigo));
			if (queryResult.size() > 0) {
				percentualIPI = new PercentualIPI();
				percentualIPI.percentualIPI = BigDecimal.valueOf(Double.parseDouble(queryResult.get(0).get("PERIPIMER")));
				percentualIPI.valorPalletIPI = BigDecimal.valueOf(Double.parseDouble(queryResult.get(0).get("VLRPLTIPI")));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return percentualIPI;

	}

	/**
	 * 
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param numeroRelacaoCidade
	 * @return
	 */
	public static List<Kit> buscaMercadoriaKit(Mercadoria mercadoria, 
			Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer numeroRelacaoCidade) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODMERKIT, PERPCOMER, CODFILEPD, CODFILFAT, CODMER, NUMRLCCID ");
		sql.append("   FROM PCAKIT ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODFILFAT = ? ");
		sql.append("    AND CODMERKIT = ? ");
		sql.append("    AND NUMRLCCID = ? ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoFilialFaturamento);
		parameters[2] = Util.getStringValue(mercadoria.codigo);
		parameters[3] = Util.getStringValue(numeroRelacaoCidade);

		db = DatabaseFactory.getInstance();

		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			return convertResultToKit(result);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	/**
	 * Mercadoria tem icms.
	 *
	 * @param codigoGrupoNCM the codigo grupo ncm
	 * @param codigoAtividade the codigo atividade
	 * @return true, if successful
	 */
	public static boolean mercadoriaTemICMS(String codigoGrupoNCM, 
			Integer codigoAtividade) {
		db = DatabaseFactory.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT 1 FROM PCACSM WHERE CODATI = ? AND CODGRPNCM = ?");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(codigoAtividade);
		parameters[1] = Util.getStringValue(codigoGrupoNCM.substring(0, 8));
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			return queryResult.size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @param codigoTributacaoMercadoria
	 * @param codigoAtividade
	 */
	public static boolean verificaSeMercadoriaConsumo(String siglaEstadoOrigem, 
			String siglaEstadoDestino, 
			Integer codigoTributacaoMercadoria, 
			Integer codigoAtividade) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT 1 FROM PCACTA ");
		sql.append("  WHERE CODESTORI = ? ");
		sql.append("    AND CODESTDSN = ? ");
		sql.append("    AND CODTBTICM = ? ");
		sql.append("    AND CODATI    = ? ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(siglaEstadoOrigem);
		parameters[1] = Util.getStringValue(siglaEstadoDestino);
		parameters[2] = Util.getStringValue(codigoTributacaoMercadoria);
		parameters[3] = Util.getStringValue(codigoAtividade);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			return queryResult.size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * @param mercadoria
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @param codigoClassificacaoCliente
	 * @return
	 */
	public static Double obtemPercentualPrecoReduzidoDaMercadoria(Mercadoria mercadoria, 
			String siglaEstadoOrigem, 
			String siglaEstadoDestino, 
			String codigoClassificacaoCliente) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PERTBTICM ");
		sql.append("   FROM PCARDC ");
		sql.append("  WHERE CODMER    = ? ");
		sql.append("    AND CODESTORI = ? ");
		sql.append("    AND CODESTDSN = ? ");
		sql.append("    AND CODCLFCLI = ? ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(mercadoria.codigo);
		parameters[1] = Util.getStringValue(siglaEstadoOrigem);
		parameters[2] = Util.getStringValue(siglaEstadoDestino);
		parameters[3] = Util.getStringValue(codigoClassificacaoCliente);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			if (queryResult.size() > 0) {
				return Double.parseDouble(queryResult.get(0).get("PERTBTICM"));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return -1.00;

	}

	/**
	 * Obtem o percentual de ICMS da mercadoria
	 * 
	 * @param mercadoria
	 * @param isMercadoriaConsumo
	 * @param siglaEstadoDestino
	 * @param siglaEstadoOrigem
	 */
	public static PercentualICMS obtemPercentualICMDaMercadoria(Mercadoria mercadoria, 
			String siglaEstadoOrigem, 
			String siglaEstadoDestino) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PERICMMER, PERICMCSM, TIPUTZICM, PERICMCSMA ");
		sql.append("   FROM PCAICM ");
		sql.append("  WHERE CODESTORI = ? ");
		sql.append("    AND CODESTDSN = ? ");
		sql.append("    AND CODMER    = ? ");

		db = DatabaseFactory.getInstance();

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(siglaEstadoOrigem);
		parameters[1] = Util.getStringValue(siglaEstadoDestino);
		parameters[2] = Util.getStringValue(mercadoria.codigo);

		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			return convertResultToPercentualICMS(queryResult);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param numeroRelacaoCliente
	 * @return
	 */
	public static BigDecimal obtemICMPadraoDoEstado(Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer numeroRelacaoCliente) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PERICMMER ");
		sql.append("   FROM PCAAGE ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODFILFAT = ? ");
		sql.append("    AND NUMRLCCID = ? ");

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoFilialFaturamento);
		parameters[2] = Util.getStringValue(numeroRelacaoCliente);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return BigDecimal.valueOf(Double.parseDouble(result.get(0).get("PERICMMER")));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return BigDecimal.ZERO;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param numeroRelacaoCliente
	 * @return
	 */
	public static PercentualICMS obtemPercentualPadraoICMSConsumo(Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer numeroRelacaoCliente) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ICMMERCSM, TIPUTZICM, ICMORICSM, PERICMORI, PERICMMER ");
		sql.append("   FROM PCAAGE ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODFILFAT = ? ");
		sql.append("    AND NUMRLCCID = ? ");

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoFilialFaturamento);
		parameters[2] = Util.getStringValue(numeroRelacaoCliente);
		
		PercentualICMS percentual = new PercentualICMS();

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return convertResultToPercentualICMS(result);	
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return percentual;
	}

	/**
	 * Obtem percentual padrao icms consumo original.
	 * 
	 * @param codigoFilialExpedicao
	 *            the codigo filial expedicao
	 * @param codigoFilialFaturamento
	 *            the codigo filial faturamento
	 * @param numeroRelacaoCliente
	 *            the numero relacao cliente
	 * @return the big decimal
	 */
	public static BigDecimal obtemPercentualPadraoICMSConsumoOriginal(Integer codigoFilialExpedicao, 
			Integer codigoFilialFaturamento, 
			Integer numeroRelacaoCliente) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ICMORICSM ");
		sql.append("   FROM PCAAGE ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODFILFAT = ? ");
		sql.append("    AND NUMRLCCID = ? ");

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoFilialFaturamento);
		parameters[2] = Util.getStringValue(numeroRelacaoCliente);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return BigDecimal.valueOf(Double.parseDouble(result.get(0).get("ICMORICSM")));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return BigDecimal.ZERO;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param numeroRelacaoCliente
	 * @return
	 */
	public static BigDecimal obtemPercentualICMSOrigem(Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, Integer numeroRelacaoCliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ICMMERCSM ");
		sql.append("   FROM PCAAGE ");
		sql.append("  WHERE CODFILEPD = ? ");
		sql.append("    AND CODFILFAT = ? ");
		sql.append("    AND NUMRLCCID = ? ");

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoFilialFaturamento);
		parameters[2] = Util.getStringValue(numeroRelacaoCliente);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return BigDecimal.valueOf(Double.parseDouble(result.get(0).get("ICMMERCSM")));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return BigDecimal.ZERO;
	}

	/**
	 * Obtem o fator de ajuste
	 * 
	 * @param siglaEstadoOrigem
	 * @param codigoTributoICM
	 * @return
	 */
	public static FatorAjuste obtemFatorAjuste(String siglaEstadoOrigem, String siglaEstadoDestino, Integer codigoTributoICM) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODESTORI, CODESTDSN, FTRAJTICM ");
		sql.append("   FROM PCAAJT ");
		sql.append("  WHERE ( ");
		sql.append(" 	    (CODESTORI = ? AND CODESTDSN = ?) ");
		sql.append(" 	 OR (CODESTORI = ? AND CODESTDSN = ?) ");
		sql.append("        ) ");
		sql.append("     AND CODTBTICM = ? ");

		String[] parameters = new String[5];
		parameters[0] = Util.getStringValue(siglaEstadoOrigem);
		parameters[1] = Util.getStringValue(siglaEstadoOrigem);
		parameters[2] = Util.getStringValue(siglaEstadoOrigem);
		parameters[3] = Util.getStringValue(siglaEstadoDestino);
		parameters[4] = Util.getStringValue(codigoTributoICM);

		db = DatabaseFactory.getInstance();

		FatorAjuste fatorAjuste = new FatorAjuste();
		fatorAjuste.fatorOrigem = BigDecimal.ONE;
		fatorAjuste.fatorDestino = BigDecimal.ONE;

		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			for (Map<String, String> registro : result) {

				String origem = registro.get("CODESTORI");
				String destino = registro.get("CODESTDSN");
				String value = registro.get("FTRAJTICM");

				if (origem.equals(destino)) {
					fatorAjuste.fatorOrigem = (value != null) ? BigDecimal.valueOf(Double.parseDouble(value)) : BigDecimal.ONE;
				} else {
					fatorAjuste.fatorDestino = (value != null) ? BigDecimal.valueOf(Double.parseDouble(value)) : BigDecimal.ONE;
				}
			}

			if (siglaEstadoOrigem.equals(siglaEstadoDestino)) {
				fatorAjuste.fatorDestino = fatorAjuste.fatorOrigem;
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return fatorAjuste;
	}

	/**
	 * Obtem percentual ICM reduzido da mercadoria
	 * 
	 * @param cliente
	 * @param mercadoria
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @return
	 */
	public static BigDecimal obtemPercentualICMReduzido(Cliente cliente, Mercadoria mercadoria, String siglaEstadoOrigem, String siglaEstadoDestino) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT PERTBTICM ");
		sql.append("  FROM PCARDC ");
		sql.append(" WHERE CODESTORI = ? ");
		sql.append("   AND CODESTDSN = ? ");
		sql.append("   AND CODCLFCLI = ? ");
		sql.append("   AND CODMER    = ? ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(siglaEstadoOrigem);
		parameters[1] = Util.getStringValue(siglaEstadoDestino);
		parameters[2] = Util.getStringValue(cliente.codigoClassificacao);
		parameters[3] = Util.getStringValue(mercadoria.codigo);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return BigDecimal.valueOf(Double.parseDouble(result.get(0).get("PERTBTICM")));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param mercadoria
	 * @param codigoCanalCliente
	 */
	public static BigDecimal obtemMargemCanalCliente(Mercadoria mercadoria, Integer codigoCanalCliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT FTRMRGADI ");
		sql.append("   FROM PCAMCN    ");
		sql.append("  WHERE CODCNL    = ? ");
		sql.append("    AND CODTERCHV = ? ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(codigoCanalCliente);
		parameters[1] = Util.getStringValue(mercadoria.codigoChaveCategoria);

		db = DatabaseFactory.getInstance();
		double fatorMargem = 0.00;
		try {
			List<Map<String, String>> queryResult = db.executeQuery(sql.toString(), parameters);
			if (queryResult.size() > 0) {
				fatorMargem = Double.parseDouble(queryResult.get(0).get("FTRMRGADI"));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return BigDecimal.valueOf(fatorMargem);
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoRegraDistribuicao
	 * @return
	 */
	public static RegraDistribuicao obtemRegraDeDistribuicao(Mercadoria mercadoria, Integer codigoFilialExpedicao, Integer codigoFilialFaturamento, Integer codigoRegraDistribuicao,
			Integer codigoCliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT RGR.CODTABFRT, RGR.PERPADFRT, RGR.VLRFRTMIN, FRT.PERFRT, FRT.VLRUNTFRT, RGR.INDCNTDSN, RGR.INDTABMAN ");
		sql.append("   FROM PCARGR RGR LEFT JOIN PCAFRT FRT ON RGR.CODTABFRT = FRT.CODTABFRT AND FRT.CODMER = ? ");
		sql.append("  WHERE RGR.CODFILEPD   = ? ");
		sql.append("    AND RGR.CODFILFAT   = ? ");
		sql.append("    AND RGR.CODRGRDTB   = ? ");
		sql.append("    AND RGR.CODCLI      = ? ");

		String[] parameters = new String[5];
		parameters[0] = Util.getStringValue(mercadoria.codigo);
		parameters[1] = Util.getStringValue(codigoFilialExpedicao);
		parameters[2] = Util.getStringValue(codigoFilialFaturamento);
		parameters[3] = Util.getStringValue(codigoRegraDistribuicao);
		parameters[4] = Util.getStringValue(codigoCliente);

		db = DatabaseFactory.getInstance();

		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return convertResultToRegraDistribuicao(result);
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 * @param mercadoria
	 * @param codigoFilialExpedicao
	 * @return
	 */
	public static Map<String, String> obtemQuantidadesMinimasDeVenda(Mercadoria mercadoria, Integer codigoFilialExpedicao) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT QDEMNMVND, FLGMPLQDE, CODGRPFRC, QDEMNMKIT ");
		sql.append("   FROM PCAECM        ");
		sql.append("  WHERE CODMER    = ? ");
		sql.append("    AND CODFILEPD = ? ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(mercadoria.codigo);
		parameters[1] = Util.getStringValue(codigoFilialExpedicao);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString());
			if (result.size() > 0) {
				return result.get(0);
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return Collections.emptyMap();
	}

	/**
	 * 
	 * @param mercadoria
	 * @return
	 */
	public static BigDecimal obtemValorMinimoEstadual(Mercadoria mercadoria, String siglaEstadoOrigem, String siglaEstadoDestino) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT VLRMNMEST ");
		sql.append("   FROM PCASGM    ");
		sql.append("  WHERE CODMER    = ? ");
		sql.append("    AND CODESTORI = ? ");
		sql.append("    AND CODESTDSN = ? ");

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(mercadoria.codigo);
		parameters[1] = Util.getStringValue(siglaEstadoOrigem);
		parameters[2] = Util.getStringValue(siglaEstadoDestino);

		db = DatabaseFactory.getInstance();
		
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return BigDecimal.valueOf(Double.parseDouble(result.get(0).get("VLRMNMEST")));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 
	 * @param mercadoria
	 * @return
	 */
	public static BigDecimal obtemValorMinimoNacional(Mercadoria mercadoria) {
		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery("SELECT VLRMNMNAC FROM PCASMN WHERE CODMER = ?", Util.getStringValue(mercadoria.codigo));
			if (result.size() > 0) {
				return Util.getBigDecimal(result.get(0).get("VLRMNMNAC"));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 
	 * @param mercadoria
	 * @return
	 */
	public static Integer obtemGrupoSTBMercadoria(String codigoGrupoNCM, String siglaEstadoOrigem, String siglaEstadoDestino) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT GRPMERSTB ");
		sql.append("   FROM PCASVS    ");
		sql.append("  WHERE CODESTORI = ? ");
		sql.append("    AND CODESTDSN = ? ");
		sql.append("    AND CODGRPNCM = ? ");

		String[] parameters = new String[3];
		parameters[0] = Util.getStringValue(siglaEstadoOrigem);
		parameters[1] = Util.getStringValue(siglaEstadoDestino);
		parameters[2] = Util.getStringValue(codigoGrupoNCM);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0) {
				return Util.getInteger(result.get(0).get("GRPMERSTB"));
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param grupoMercadoriaSTB
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @return
	 */
	public static List<DiretrizSTB> obtemDiretrizesDeSTB(Integer grupoMercadoriaSTB, String siglaEstadoOrigem, String siglaEstadoDestino, Cliente cliente) {

		StringBuilder sql = new StringBuilder();
		sql.append("   SELECT SDT.CODESTORI, SDT.CODESTDSN, SDT.GRPMERSTB, SDT.GRPCLISTB, SDT.CODPRRGRP, ");
		sql.append("          SDT.DATINIVGR, SDT.PERCRDSTB, SDT.PERDBTSTB, SDT.PERMRGLCR, SDT.FLGVLRMNM, SDT.TIPDTZSTB, SDT.FTRRDCPCO ");
		sql.append("     FROM PCASDT SDT INNER JOIN PCASGC SGC ON SGC.CODESTORI = SDT.CODESTORI AND SGC.GRPCLISTB = SDT.GRPCLISTB ");
		sql.append("    WHERE SDT.GRPMERSTB   = ? ");
		sql.append("      AND SDT.CODESTORI   = ? ");
		sql.append("      AND SDT.CODESTDSN   = ? ");
		sql.append("      AND SGC.CODCLI      = ? ");
		//sql.append("      AND STRFTIME('%y%m%d', DATETIME('NOW')) >= SGC.DATLIMLMN ");
		sql.append(" ORDER BY SDT.CODPRRGRP ");

		String[] parameters = new String[4];
		parameters[0] = Util.getStringValue(grupoMercadoriaSTB);
		parameters[1] = Util.getStringValue(siglaEstadoOrigem);
		parameters[2] = Util.getStringValue(siglaEstadoDestino);
		parameters[3] = Util.getStringValue(cliente.codigoCliente);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0)
				return convertResultToDiretrizSTB(result);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return Collections.emptyList();
	}

	/**
	 * 
	 * @param grupoClienteSTB
	 * @param cliente
	 * @return
	 */
	public static boolean existeGrupoSTBParaCliente(Integer grupoClienteSTB, Cliente cliente) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DATLIMLMN, TIPEDESTB ");
		sql.append("   FROM PCASGC               ");
		sql.append("  WHERE CODCLI    = ?        ");
		sql.append("    AND GRPCLISTB = ?        ");
		sql.append("    AND STRFTIME('%y%m%d', DATETIME('NOW')) >= DATLIMLMN ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(cliente.codigoCliente);
		parameters[1] = Util.getStringValue(grupoClienteSTB);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			return result.size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * @param codigoFilialExpedicao
	 * @param codigoMercadoria
	 * @return
	 */
	public static boolean verificaItemImuneDePontuacao(Integer codigoFilialExpedicao, Integer codigoMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT 1              ");
		sql.append("   FROM PCAIMU         ");
		sql.append("  WHERE CODFILEPD = ?  ");
		sql.append("    AND CODMER    = ?  ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(codigoFilialExpedicao);
		parameters[1] = Util.getStringValue(codigoMercadoria);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			return result.size() > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	/**
	 * Obtem faixa de limites para colorir pontuacao do item.
	 * 
	 * @param siglaEstadoDestino
	 * @param codigoTermoChave
	 * @return
	 */
	public static FaixaMargem obtemFaixaParaColorirPontuacaoItem(String siglaEstadoDestino, String codigoTermoChave) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PERLIMINF, PERLIMSUP ");
		sql.append("   FROM PCAFME ");
		sql.append("  WHERE CODESTDSN = ? ");
		sql.append("    AND CODTERCHV = ? ");

		String[] parameters = new String[2];
		parameters[0] = Util.getStringValue(siglaEstadoDestino);
		parameters[1] = Util.getStringValue(codigoTermoChave);

		db = DatabaseFactory.getInstance();
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString(), parameters);
			if (result.size() > 0)
				return convertResultToFaixaMargemItem(result);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private static FaixaMargem convertResultToFaixaMargemItem(List<Map<String, String>> result) {
		FaixaMargem faixa = null;
		if (result.size() > 0) {
			Map<String, String> registro = result.get(0);
			faixa = new FaixaMargem();
			String valorInferior = registro.get("PERLIMINF");
			String valorSuperior = registro.get("PERLIMSUP");

			faixa.percentualLimiteInferior = (valorInferior != null) ? new BigDecimal(valorInferior) : null;
			faixa.percentualLimiteSuperior = (valorSuperior != null) ? new BigDecimal(valorSuperior) : null;
		}
		return faixa;
	}
	
	/**
	 * Obtem indicativo venda direta.
	 * 
	 * @param codigoFilialExpedicao
	 *            the codigo filial expedicao
	 * @return the integer
	 * @throws SQLException
	 *             the sQL exception
	 */
	public static Integer obtemIndicativoVendaDireta(Integer codigoFilialExpedicao) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODFILEPD, NOMFILEPD, FLGVNDFOB, VLRLIMVRF, TIPNIVREP, INDVNDDTO ");
		sql.append("   FROM PCAFIL ");
		sql.append("  WHERE CODFILEPD = ? ");

		db = DatabaseFactory.getInstance();
		List<Map<String, String>> result = db.executeQuery(sql.toString(), Util.getStringValue(codigoFilialExpedicao));
		if (!result.isEmpty()) {
			return Util.getInteger(result.get(0).get("INDVNDDTO"));
		}
		return null;
	}

	/**
	 * Converte o resultado de uma query no objeto Preco.
	 * 
	 * @see br.com.martins.vendas.vo.Preco
	 * @param queryResult
	 * @return
	 */
	private static Preco convertResultToPreco(List<Map<String, String>> queryResult) {
		List<Preco> precos = new ArrayList<Preco>();

		Preco preco = null;
		for (Map<String, String> registro : queryResult) {
			preco = new Preco();

			preco.tipoIncentivoMercadoria = registro.get("TIPICTMER");
			preco.codigoSimboloSituacao   = registro.get("CODSMBSIT");
			preco.codigoFlexivelPreco     = registro.get("CODFLXPCO");
			preco.flagMercadoriaExclusiva = registro.get("FLGMEREXV");
			
			if (registro.containsKey("INDMERMTD")) {
				preco.flagPrecoMonitorado = Util.getInteger(registro.get("INDMERMTD"));
			}
			if (registro.containsKey("CODTBTICM")) {
				preco.codigoTributacaoICM = Util.getInteger(registro.get("CODTBTICM"));
			}
			
			preco.valorBrutoUnitario = Util.getBigDecimal(registro.get("VLRUNTBRT"));

			preco.flagPontoEncontroEletronico = registro.get("FLGPEE");
			if (preco.flagPontoEncontroEletronico == null) {
				preco.flagPontoEncontroEletronico = StringUtil.EMPTY;
			}

			preco.tipoMarcacaoRepresentante = registro.get("TIPMCOREP");
			if (preco.tipoMarcacaoRepresentante == null) {
				preco.tipoMarcacaoRepresentante = StringUtil.EMPTY;
			}
			
			preco.custoLogistica = Util.getBigDecimal(registro.get("CSTCSTLGT"));
			preco.percentualTributacao = Util.getBigDecimal(registro.get("PERTBTMER"));
			preco.percentualICMS = Util.getBigDecimal(registro.get("PERCMSMER"));
			preco.flagUtilizaMinimo = Util.getInteger(registro.get("INDUTZMNM"), 1);
			preco.percentualComissaoMercadoria = Util.getBigDecimal(registro.get("PERCMSMER"));
			preco.flagUtilizaFracionado = Util.getInteger(registro.get("INDUTZFRC"), 1);
			preco.flagUtilizaFlex = Util.getInteger(registro.get("FLGUTZFLX"), 1);
			preco.flagUtilizaBeneficios = MENOS_UM.intValue();
			
			if (registro.containsKey("INDUTZBFC")) {
			preco.flagUtilizaBeneficios = Util.getInteger(registro.get("INDUTZBFC"),1);
			}

//			preco.custoLogistica = Util.getBigDecimal(registro.get("CSTCSTLGT"));
//			preco.percentualTributacao = Util.getBigDecimal(registro.get("PERTBTMER"));
//			preco.percentualICMS = Util.getBigDecimal(registro.get("PERCMSMER"));
//			preco.flagUtilizaMinimo = Util.getInteger(registro.get("INDUTZMNM"), 0);
//			preco.percentualComissaoMercadoria = Util.getBigDecimal(registro.get("PERCMSMER"));
//			
//			preco.flagUtilizaBeneficios = MENOS_UM.intValue();
//			if (registro.containsKey("INDUTZBFC")) {
//				preco.flagUtilizaBeneficios = Util.getInteger(registro.get("INDUTZBFC"));
//			}

			precos.add(preco);
		}

		return precos.get(0);
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private static PercentualICMS convertResultToPercentualICMS(List<Map<String, String>> result) {
		PercentualICMS percentual = null;

		for (Map<String, String> registro : result) {
			percentual = new PercentualICMS();
			percentual.percentualICMSMercadoria = Util.getBigDecimal(registro.get("PERICMMER"));
			percentual.percentualICMSConsumo = Util.getBigDecimal(registro.get("PERICMCSM"));
			// verificar se está correto essa validação.Na base 07 estava retornando
			// NullPointerException
			percentual.tipoUtilizacaoICMS = Util.getInteger(registro.get("TIPUTZICM")) != null 
					? Util.getInteger(registro.get("TIPUTZICM")) : 0;
					
		    percentual.percentualPadraoConsumo = Util.getBigDecimal(registro.get("ICMMERCSM"));
		    percentual.percentualPadraoConsumoOriginal = Util.getBigDecimal(registro.get("ICMORICSM"));
		    percentual.percentualOriginal = Util.getBigDecimal(registro.get("PERICMORI"));
		    percentual.percentualICMConsumidorAcordo = Util.getBigDecimal(registro.get("PERICMCSMA"));

		}

		return percentual;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private static Fracionamento convertResultToFracionamento(List<Map<String, String>> result) {
		Fracionamento fracionamento = null;

		if (result.size() > 0) {
			fracionamento = new Fracionamento();
			for (String key : result.get(0).keySet()) {
				if (key.equalsIgnoreCase("VLRCSTFRC")) {
					fracionamento.valorCustoFracionado = BigDecimal.valueOf(Double.parseDouble(result.get(0).get(key)));
				} else if (key.equalsIgnoreCase("PERCSTFRC")) {
					fracionamento.percentualCustoFracionado = BigDecimal.valueOf(Double.parseDouble(result.get(0).get(key)));
				}
			}
		} else {
			fracionamento = new Fracionamento();
			fracionamento.valorCustoFracionado = BigDecimal.ZERO;
			fracionamento.percentualCustoFracionado = BigDecimal.ZERO;
		}

		return fracionamento;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private static RegraDistribuicao convertResultToRegraDistribuicao(List<Map<String, String>> result) {
		RegraDistribuicao regra = null;
		for (Map<String, String> registro : result) {
			regra = new RegraDistribuicao();
			for (String key : registro.keySet()) {
				if (key.equalsIgnoreCase("CODTABFRT")) {
					regra.codigoTabelaFrete = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("PERPADFRT")) {
					regra.percentualPadraoFrete = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				} else if (key.equalsIgnoreCase("VLRFRTMIN")) {
					regra.valorFreteMinimo = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				} else if (key.equalsIgnoreCase("PERFRT")) {
					regra.percentualFrete = (registro.get(key) != null) ? BigDecimal.valueOf(Double.parseDouble(registro.get(key))) : null;
				} else if (key.equalsIgnoreCase("VLRUNTFRT")) {
					regra.valorUnitarioFrete = (registro.get(key) != null) ? BigDecimal.valueOf(Double.parseDouble(registro.get(key))) : null;
				} else if (key.equalsIgnoreCase("INDCNTDSN")) {
					regra.indicadorContaDestino = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("INDTABMAN")) {
					regra.indicadorTabelaManual = Integer.parseInt(registro.get(key));
				}
			}
		}

		return regra;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	private static List<Kit> convertResultToKit(List<Map<String, String>> result) {
		List<Kit> kits = new ArrayList<Kit>();
		Kit kit = null;
		for (Map<String, String> registro : result) {
			kit = new Kit();
			// for(String key : registro.keySet()){

			kit.codigoMercadoriaKit = Integer.parseInt(registro.get("CODMERKIT"));
			kit.percentualPrecoMercadoria = BigDecimal.valueOf(Double.parseDouble(registro.get("PERPCOMER")));
			kit.codigoFilialExpedicao = Integer.parseInt(registro.get("CODFILEPD"));
			kit.codigoFilialFaturamento = Integer.parseInt(registro.get("CODFILFAT"));
			kit.codigoMercadoria = Integer.parseInt(registro.get("CODMER"));
			kit.numeroRelacaoCidade = Integer.parseInt(registro.get("NUMRLCCID"));
			// }

			kits.add(kit);
		}

		return kits;
	}

	public static List<DiretrizSTB> convertResultToDiretrizSTB(List<Map<String, String>> result) {
		List<DiretrizSTB> lista = new ArrayList<DiretrizSTB>();
		DiretrizSTB diretriz = null;

		for (Map<String, String> registro : result) {
			diretriz = new DiretrizSTB();
			for (String key : registro.keySet()) {
				if (key.equalsIgnoreCase("CODESTORI")) {
					diretriz.siglaEstadoOrigem = registro.get(key);
				} else if (key.equalsIgnoreCase("CODESTDSN")) {
					diretriz.siglaEstadoDestino = registro.get(key);
				} else if (key.equalsIgnoreCase("GRPMERSTB")) {
					diretriz.grupoMercadoriaSTB = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("GRPCLISTB")) {
					diretriz.grupoClienteSTB = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("CODPRRGRP")) {
					diretriz.codigoPrioridadeGrupo = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("PERCRDSTB")) {
					diretriz.percentualCreditoSTB = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				} else if (key.equalsIgnoreCase("PERDBTSTB")) {
					diretriz.percentualDebitoSTB = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				} else if (key.equalsIgnoreCase("PERMRGLCR")) {
					diretriz.percentualMargemDeLucro = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				} else if (key.equalsIgnoreCase("FLGVLRMNM")) {
					diretriz.flagValorMinimo = registro.get(key);
				} else if (key.equalsIgnoreCase("TIPDTZSTB")) {
					diretriz.tipoDiretrizSTB = Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("FTRRDCPCO")) {
					diretriz.fatorReducaoPreco = BigDecimal.valueOf(Double.parseDouble(registro.get(key)));
				} else if (key.equalsIgnoreCase("DATINIVGR")) {
					diretriz.dataInicioVigorar = registro.get(key);
				}
			}
			lista.add(diretriz);
		}

		return lista;
	}

}
