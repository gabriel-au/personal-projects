package br.com.martins.vendas.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.TipoNegociacaoMercadoria;
import br.com.martins.vendas.services.calculodepreco.Imposto;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.Mensagem;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PontoMinimoPedidoSegmento;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RelacaoGiro;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;
import com.brq.mobile.framework.util.Util;

public class PromocaoDAO {

	private static final String TAG = PromocaoDAO.class.getName();
	
	private static Database db;

	/**
	 * Busca informacoes promocao.
	 * 
	 * @param codigoMercadoria
	 *            the codigo mercadoria
	 * @return the integer
	 */
	public static Promocao buscaInformacoesPromocao(Integer codigoMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select CODPMC, VLRVNDPRM ");
		sql.append(" from PCAPRM ");
		sql.append(" where CODMER = %s");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		Promocao promocao = null;

		try {
			List<Map<String, String>> result = db.executeQuery(query);
			promocao = convertResultToPromocao(result);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		return promocao;
	}

	/**
	 * Convert result to promocao.
	 * 
	 * @param result
	 *            the result
	 * @return the promocao
	 */
	private static Promocao convertResultToPromocao(
			List<Map<String, String>> result) {
		Promocao promocao = null;

		for (Map<String, String> registro : result) {
			promocao = new Promocao();
			for (String key : registro.keySet()) {
				String value = registro.get(key);
				if (key.equalsIgnoreCase("CODPMC")) {
					promocao.codigo = Integer.parseInt(value);
				} else if (key.equalsIgnoreCase("VLRVNDPRM")) {
					promocao.valorVenda = BigDecimal.valueOf(Double
							.parseDouble(value));
				} else if (key.equalsIgnoreCase("CODPMC")) {
					promocao.tipo = value;
				}
			}
		}

		return promocao;
	}

	public static List<Promocao> listarPromocoesValidas(
			Integer codigoFilialExpedicao, Integer codigoUnidadeEstrategia,
			Cliente cliente, RelacaoGiro relacaoGiro,
			PontoMinimoPedidoSegmento segmento, String codigoEstadoDestino,
			Integer codigoFilialFaturamento) {

		List<Promocao> listaPromocao = new ArrayList<Promocao>();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT PRC.CODPMC, PRC.CODFILEPD, PRC.DATFIMPMC, PRC.DESPMC, PRC.TIPPMC, PRC.INDPRMFRC, PRC.CODUNDESR, ");
		sql.append("SUM(CASE WHEN PPA.CODPMC IS NULL THEN 0 ELSE 1 END) NROPUBALV ");
		sql.append("FROM PCAPRC PRC    LEFT JOIN PCAPPA PPA ON PRC.CODPMC = PPA.CODPMC   ");
		sql.append(" WHERE ");
		sql.append("      PRC.CODFILEPD =").append(codigoFilialExpedicao);
		sql.append(" AND strftime('%%Y%%m%%d', DATETIME('NOW')) BETWEEN PRC.DATINIPMC AND PRC.DATFIMPMC");
		// Pesquisa por BU - codigoUnidadeEstrategia
		if (codigoUnidadeEstrategia != 0) {
			sql.append("      AND PRC.CODUNDESR =").append(
					codigoUnidadeEstrategia);
		}

		sql.append(" GROUP BY PRC.CODPMC, PRC.CODFILEPD, PRC.DATFIMPMC, PRC.DESPMC, PRC.TIPPMC, PRC.INDPRMFRC, PRC.CODUNDESR ");
		sql.append(" HAVING NROPUBALV = 0  OR NROPUBALV = ( ");

		sql.append("SELECT COALESCE( SUM(CASE WHEN PUF.CODPMC NOT NULL OR ");
		sql.append("                     PGA.CODPMC NOT NULL OR ");
		sql.append("                     PGM.CODPMC NOT NULL OR ");
		sql.append("                     PAT.CODPMC NOT NULL OR ");
		sql.append("                     PGC.CODPMC NOT NULL OR ");
		sql.append("                     PNG.CODPMC NOT NULL OR ");
		sql.append("                     PSG.CODPMC NOT NULL OR ");
		sql.append("                     PCL.CODPMC NOT NULL THEN 1 ELSE 0 END), 0) NROPUBALV ");
		sql.append("FROM ");
		sql.append("     PCAPPA PPA LEFT JOIN PCAPUF PUF ON ");
		sql.append(
				"            PPA.CODPMC = PUF.CODPMC AND PPA.CODCTRSEL = 1 AND PUF.CODESTDSN = '")
				.append(codigoEstadoDestino).append("'");
		sql.append("     LEFT JOIN PCAPGA PGA ON  ");
		sql.append(
				"          PPA.CODPMC = PGA.CODPMC AND PPA.CODCTRSEL = 2 AND PGA.CODGER =")
				.append(relacaoGiro.codGerente);
		sql.append("     LEFT JOIN PCAPGM PGM ON ");
		sql.append(
				"          PPA.CODPMC = PGM.CODPMC AND PPA.CODCTRSEL = 3 AND PGM.CODSUP =")
				.append(relacaoGiro.codSupervisor);
		sql.append("     LEFT JOIN PCAPAT PAT ON ");
		sql.append(
				"          PPA.CODPMC = PAT.CODPMC AND PPA.CODCTRSEL = 4 AND PAT.CODATI =")
				.append(cliente.codigoAtividade);
		sql.append("     LEFT JOIN PCAPGC PGC ON ");
		sql.append(
				"          PPA.CODPMC = PGC.CODPMC AND PPA.CODCTRSEL = 5 AND PGC.CODGRPCLI =")
				.append(cliente.codigoGrupoCliente);
		sql.append("     LEFT JOIN PCAPNG PNG ON ");
		sql.append("          PPA.CODPMC = PNG.CODPMC AND PPA.CODCTRSEL = 7 AND PNG.CODUNDESR = (SELECT CNL.CODUNDESR FROM PCACLI CLI, PCACNL CNL");
		sql.append("   																					   WHERE CNL.CODCNL = CLI.CODCNL");
		sql.append("   																					   AND CODCLI =").append(
				cliente.codigoCliente);
		sql.append(" 																			 )");
		sql.append("     LEFT JOIN PCAPSG PSG ON ");
		sql.append(
				"          PPA.CODPMC = PSG.CODPMC AND PPA.CODCTRSEL = 8 AND PSG.CODSGMCLI =")
				.append(segmento.codigoSegmentoCliente);
		sql.append("     LEFT JOIN PCAPCL PCL ON ");
		sql.append(
				"          PPA.CODPMC = PCL.CODPMC AND PPA.CODCTRSEL = 9 AND PCL.CODCLI =")
				.append(cliente.codigoCliente);
		sql.append(" WHERE ");
		sql.append("      PPA.CODPMC = PRC.CODPMC )");

		db = DatabaseFactory.getInstance();

		try {
			String query = DatabaseUtil.montaQuery(sql);
			List<Map<String, String>> result = db.executeQuery(query);

			for (Map<String, String> key : result) {
				Promocao promocao = new Promocao();
				promocao.codigo = Integer.parseInt(key.get("CODPMC"));
				promocao.codigoFilialExpedicao = Integer.parseInt(key
						.get("CODFILEPD"));
				promocao.dataTerminoPromocao = DateUtil.formataData(
						key.get("DATFIMPMC"), "yyyyMMdd");
				promocao.descricaoPromocao = key.get("DESPMC");
				promocao.tipo = key.get("TIPPMC");
				promocao.indicaPremioFracionado = key.get("INDPRMFRC");
				promocao.codigoUnidadeEstrategia = Integer.parseInt(key
						.get("CODUNDESR"));
				promocao.numeroPublicoAlvo = Integer.parseInt(key
						.get("NROPUBALV"));
				listaPromocao.add(promocao);
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		return listaPromocao;
	}

	public static BigDecimal obtemPremioDaPromocao(Integer codigoPromocao,
			Integer codigoMercadoria) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" select VLRVNDPRM ");
		sql.append(" from PCAPRM ");
		sql.append(" where CODPMC = %s ");
		sql.append(" and CODMER = %s ");

		db = DatabaseFactory.getInstance();
		String query = DatabaseUtil.montaQuery(sql, codigoPromocao,
				codigoMercadoria);

		BigDecimal valorPremio = BigDecimal.ZERO;
		List<Map<String, String>> result = db.executeQuery(query);

		if (!result.isEmpty()) {
			String value = result.get(0).get("VLRVNDPRM");
			valorPremio = new BigDecimal(value);
		}

		return valorPremio;

	}

	public static List<Item> listarItensValidosPromocao(Integer codigoPromocao,
			Integer codigoFilialExpedicao, Integer codigoFilialFaturamento,
			Integer codigoRelacionamentoCliente, String codigoMercadoria) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT  COALESCE(smr.codmer, mer.codmer) CODMER, ");
		sql.append(" COALESCE(MER.DESMER,(COALESCE(MER2.desmer,''))) || ' ' ||  coalesce(descplmer,'') || ' ' || coalesce(dessctgrp,'') desmer,  ");
		sql.append(" mer.codeslngc,  ");
		sql.append(" mer.tippdcmer,  ");
		sql.append(" mer.qdecxafrn,  ");
		sql.append(" mer.indrtcbfv,  ");
		sql.append(" mer.indrtcbfb,  ");
		sql.append(" mer.indrtcbnf,  ");
		sql.append(" mer.ftrcnvunt,  ");
		sql.append(" mer.indmerkit,  ");
		sql.append(" mer.indrstkit,  ");
		sql.append(" mer.tipmer, ");
		sql.append(" lvr.tipictmer, ");
		sql.append(" lvr.codsmbsit,   ");
		sql.append(" lvr.flgmerexv,   ");
		sql.append(" lvr.codflxpco,   ");
		sql.append(" lvr.flgpee,      ");
		sql.append(" lvr.tipmcorep, ");
		sql.append("(CASE WHEN ( ");
		sql.append("	SELECT COUNT(CODMER)  ");
		sql.append("	FROM PCASMR SMR  ");
		sql.append("	WHERE SMR.CODMER = mer.CODMER  ");
		sql.append("	)== 0 THEN 'N' ELSE 'S' END  ");
		sql.append(") TEM_SIMILAR  ");
		sql.append("FROM PCAMPR MPR ");
		sql.append("     LEFT JOIN CADMER MER ON MPR.CODMER = MER.CODMER  ");
		sql.append("		 LEFT JOIN PCASMR SMR ON SMR.CODMER = MPR.CODMER  ");
		sql.append("     LEFT JOIN CADMER MER2 ON SMR.CODMERPCP = MER2.CODMER ");
		sql.append("		 LEFT JOIN CADCPL CPL ON (MER.CODMER IS NOT NULL AND MER.CODMER = CPL.CODMER) OR (MER2.CODMER IS NOT NULL AND MER2.CODMER = CPL.CODMER)  ");
		sql.append("     INNER JOIN pcalvr lvr ON mer.codmer = lvr.codmer  ");
		sql.append("     LEFT JOIN pcaetq etq ON lvr.codfilepd = etq.codfilepd AND lvr.codmer = etq.codmer ");
		sql.append("WHERE  ");
		sql.append("      MPR.CODPMC = %s AND COALESCE(smr.codmer, mer.codmer) IS NOT NULL ");
		sql.append("      AND  lvr.codfilepd  = %s ");
		sql.append("      AND lvr.codfilfat   = %s  ");
		sql.append("      AND lvr.numrlccid   = %s  ");
		sql.append("      AND COALESCE(etq.tipesgmer, ' ') <> 'F'  ");
		sql.append("ORDER BY CODMER LIMIT 0, 100  ");

		String query = DatabaseUtil.montaQuery(sql, codigoPromocao,
				codigoFilialExpedicao, codigoFilialFaturamento,
				codigoRelacionamentoCliente);

		db = DatabaseFactory.getInstance();
		List<Item> itensDisponiveis = null;

		try {
			List<Map<String, String>> resultQuery = db.executeQuery(query);

			if (resultQuery.isEmpty()) {
				itensDisponiveis = Collections.emptyList();
			} else {
				itensDisponiveis = convertResultToItem(resultQuery);
			}

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}

		return itensDisponiveis;
	}

	/**
	 * Converte o resultado de uma query em um objeto do tipo
	 * <b>MercadoriaVO</b>
	 * 
	 * @see Mercadoria
	 * @param resultQuery
	 *            que � uma 'List<Map<String,String>>' onde cada Map tem a linha
	 *            do registro
	 * @return
	 */
	private static List<Item> convertResultToItem(
			List<Map<String, String>> resultQuery) {

		List<Item> itens = new ArrayList<Item>();

		for (Map<String, String> registro : resultQuery) {
			Item item = new Item();

			final Preco preco = item.preco;
			final Mercadoria mercadoria = item.mercadoria;

			for (String key : registro.keySet()) {
				if (key.equalsIgnoreCase("CODMER")) {
					mercadoria.codigo = (null == registro.get(key) || ""
							.equals(registro.get(key))) ? 0 : Integer
							.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("DESMER")) {
					mercadoria.descricao = registro.get(key);
				} else if (key.equalsIgnoreCase("CODESLNGC")) {
					mercadoria.codigoEspecialistaNegociacao = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("QDECXAFRN")) {
					mercadoria.quantidadeCaixaFornecedor = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("QDEMNMKIT")) {
					mercadoria.quantidadeMinimaKit = (null == registro.get(key) || ""
							.equals(registro.get(key))) ? 0 : Integer
							.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("CODESLNGC")) {
					mercadoria.quantidadeMinimaVenda = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("CODTERCHV1")) {
					mercadoria.codigoChaveCategoria = registro.get(key);
				} else if (key.equalsIgnoreCase("TIPMER")) {
					mercadoria.tipo = registro.get(key);
				} else if (key.equalsIgnoreCase("FLGMPLQDE")) {
					mercadoria.flagMultiplicadorQuantidade = registro.get(key);
				} else if (key.equalsIgnoreCase("CODGRPFRC")) {
					mercadoria.codigoGrupoFracionado = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Integer.parseInt(registro.get(key));
				} else if (key.equalsIgnoreCase("INDRSTKIT")) {
					mercadoria.indicadorRestricaoKit = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Util.getInteger(registro.get("INDRSTKIT"));
				} else if (key.equalsIgnoreCase("flgmerexv")) {
					preco.flagMercadoriaExclusiva = registro.get(key);
				} else if (key.equalsIgnoreCase("TIPESGMER")) {
					mercadoria.tipoEsgotamento = registro.get(key);
				} else if (key.equalsIgnoreCase("TEM_SIMILAR")) {
					mercadoria.temMercadoriaSimilar = "S".equals(registro
							.get(key)) ? true : false;
				} else if (key.equalsIgnoreCase("TIPICTMER")) {
					preco.tipoIncentivoMercadoria = registro.get(key);
				} else if (key.equalsIgnoreCase("CODFLXPCO")) {
					preco.codigoFlexivelPreco = registro.get(key);
				} else if (key.equalsIgnoreCase("CODSMBSIT")) {
					preco.codigoSimboloSituacao = registro.get(key);
				} else if (key.equalsIgnoreCase("FLGPEE")) {
					preco.flagPontoEncontroEletronico = registro.get(key);
				} else if (key.equalsIgnoreCase("TIPMCOREP")) {
					preco.tipoMarcacaoRepresentante = registro.get(key);
				} else if (key.equalsIgnoreCase("INDRTCBFV")) {
					mercadoria.temRestricaoBrinde = (null == registro.get(key) || ""
							.equals(registro.get(key))) ? 0 : Util
							.getInteger(registro.get(key));
				} else if (key.equalsIgnoreCase("INDRTCBFB")) {
					mercadoria.temRestricaoBeneficioCustomizadoNoBrinde = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Util.getInteger(registro.get(key));
				} else if (key.equalsIgnoreCase("INDRTCBNF")) {
					mercadoria.temRestricaoBeneficioCustomizado = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Util.getInteger(registro.get(key));
				} else if (key.equalsIgnoreCase("FTRCNVUNT")) {
					mercadoria.fatorConversaoUnitario = (null == registro
							.get(key) || "".equals(registro.get(key))) ? 0
							: Util.getInteger(registro.get(key));
				} else if (key.equalsIgnoreCase("INDMERKIT")) {
					mercadoria.indicaMercadoriaKit = (null == registro.get(key) || ""
							.equals(registro.get(key))) ? 0 : Util
							.getInteger(registro.get(key));
				}
			}

			itens.add(item);

		}

		return itens;
	}

	public static List<Item> listaItensDisponiveisDaPromocao(
			Integer codigoCliente, Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			Integer codigoRelacionamentoCliente,
			Integer codigoGrupamentoCliente, String flagAlvaraPsicotropico,
			Integer codigoRepresentante, boolean isMercadoriaExclusiva,
			String palavraMeio, String codigoMercadoria,
			Integer numeroListaCustomizada, ItemFiltro itemFiltro,
			Integer codigoPromocao) {

		final String flagAcessoExclusivoMix = ItemDisponivelDAO
				.buscaSeGrupoEhApenasDoMix(codigoGrupamentoCliente);

		StringBuilder sql = new StringBuilder();
		sql.append(
				"    SELECT DISTINCT                                                                                                          ")
				.append("           MER.CODMER ,                                                                                                      ")
				.append("           MER.DESMER || '' || COALESCE (CPL.DESCPLMER,'')  || ' ' || COALESCE (SMR.DESSCTGRP,'') DESMER,                    ")
				.append("           MER.DESUNDVND          ,                                                                                          ")
				.append("           MER.FLGCPLDES          ,                                                                                          ")
				.append("           MER.FTRCNVUNT          ,                                                                                          ")
				.append("           MER.QDECXAFRN          ,                                                                                          ")
				.append("           MER.TIPMER             ,                                                                                          ")
				.append("           MER.CODGRPNCM          ,                                                                                          ")
				.append("           MER.TIPPDCMER          ,                                                                                          ")
				.append("           MER.CODTERCHV1         ,                                                                                          ")
				.append("           MER.CODTERCHV2         ,                                                                                          ")
				.append("           MER.CODCPRMER          ,                                                                                          ")
				.append("           MER.CODGRPFRC          ,                                                                                          ")
				.append("           MER.FLGPCPSMR          ,                                                                                          ")
				.append("           MER.QDEMNMVND          ,                                                                                          ")
				.append("           MER.FLGMPLQDE          ,                                                                                          ")
				.append("           MER.CODESLNGC          ,                                                                                          ")
				.append("           MER.INDMERPRL          ,                                                                                          ")
				.append("           MER.FLGINDSER          ,                                                                                          ")
				.append("           MER.CODBRRMER          ,                                                                                          ")
				.append("           MER.INDIMPFTE          ,                                                                                          ")
				.append("           MER.INDRTCBFV          ,                                                                                          ")
				.append("           MER.INDRTCBFB          ,                                                                                          ")
				.append("           MER.INDRTCBNF          ,                                                                                          ")
				.append("           MER.INDMERKIT          ,                                                                                          ")
				.append("           MER.INDRSTKIT          ,                                                                                          ")
				.append("           MER.CODGRPMER          ,                                                                                          ")
				.append("           MER.QDEMNMKIT          ,                                                                                          ")
				.append("           MER.CODFMLMER          ,                                                                                          ")
				.append("           MER.CODCLSMER          ,                                                                                          ")
				.append("           LVR.CODFILEPD          ,                                                                                          ")
				.append("           LVR.CODFILFAT          ,                                                                                          ")
				.append("           LVR.NUMRLCCID          ,                                                                                          ")
				.append("           LVR.CODMER    LVRMER   ,                                                                                          ")
				.append("           LVR.TIPICTMER          ,                                                                                          ")
				.append("           LVR.CODSMBSIT          ,                                                                                          ")
				.append("           LVR.CODFLXPCO          ,                                                                                          ")
				.append("           LVR.FLGMEREXV          ,                                                                                          ")
				.append("           LVR.INDMERMTD          ,                                                                                          ")
				.append("           LVR.CODTBTICM          ,                                                                                          ")
				.append("           LVR.VLRUNTBRT          ,                                                                                          ")
				.append("           LVR.FLGPEE             ,                                                                                          ")
				.append("           LVR.TIPMCOREP          ,                                                                                          ")
				.append("           LVR.CSTCSTLGT          ,                                                                                          ")
				.append("           LVR.PERTBTMER          ,                                                                                          ")
				.append("           LVR.PERCMSMER          ,                                                                                          ")
				.append("           COALESCE(ETQ.CODFILEPD, '') EQTFILEPD,                                                                            ")
				.append("           COALESCE(ETQ.CODMER, '')	 EQTMER,                                                                              ")
				.append("           COALESCE(ETQ.TIPESGMER, '') TIPESGMER,                                                                            ")
				.append("           COALESCE(ETQ.DATRPSMER, '') DATRPSMER,                                                                            ")
				.append("  	        ")
				.append(codigoGrupamentoCliente)
				.append("   CODGRPCLI, ")
				.append("           '")
				.append(flagAlvaraPsicotropico)
				.append("'  FLGAVRPSC, ")
				.append("	        '")
				.append(flagAcessoExclusivoMix)
				.append("'  FLGACSMIX,  ")
				.append("	   '9999' CODPMC")
				.append("      FROM CADMER MER                                                                                                        ")
				.append("      JOIN PCALVR LVR ON MER.CODMER = LVR.CODMER                                                                             ")
				.append("	   LEFT JOIN PCAMPR  MPR ON MPR.CODMER = MER.CODMER																		  ")
				.append("		 LEFT JOIN PCASMR SMR ON SMR.CODMER = MPR.CODMER																	  ")
				.append("	   LEFT JOIN CADCPL CPL ON (MER.CODMER = CPL.CODMER) OR (SMR.CODMER = CPL.CODMER)										  ");

		sql.append(
				" LEFT JOIN PCAETQ ETQ ON LVR.CODFILEPD = ETQ.CODFILEPD                                                                       ")
				.append("	    AND LVR.CODMER = ETQ.CODMER                                                                                           ")
				.append("     WHERE MER.INDRSTKIT = 0                                                                                                 ")
				.append("      AND  MPR.CODPMC = ")
				.append(codigoPromocao)
				.append(" AND COALESCE(smr.codmer, mer.codmer) IS NOT NULL                  ");
		// filtro por grupo de mercadoria
		if (itemFiltro.codigoGrupo != null) {
			sql.append("                          AND MER.CODGRPMER = ")
					.append(itemFiltro.codigoGrupo);
		}
		sql.append(
				"       AND COALESCE(ETQ.TIPESGMER, ' ') <> 'F'                                                                               ")
				.append("       AND LVR.CODFILEPD = ")
				.append(codigoFilialExpedicao)
				.append("   	AND LVR.CODFILFAT = ")
				.append(codigoFilialFaturamento)
				.append("	    AND LVR.NUMRLCCID = ")
				.append(codigoRelacionamentoCliente)
				.append("       AND (                                                                                                                 ")
				.append("    		     ( LVR.FLGMEREXV  = '*'                                                                                       ")
				.append("    			   AND CODGRPCLI != 0                                                                                         ")
				.append("    			   AND (SELECT COUNT(*)                                                                                       ")
				.append("    			          FROM PCAMIX MIX                                                                                     ")
				.append("                        WHERE MIX.CODMER = MER.CODMER                                                                        ")
				.append("                          AND MIX.CODGRPCLI = CODGRPCLI) > 0                                                                 ")
				.append("    		     )                                                                                                            ")
				.append("    		     OR                                                                                                           ")
				.append("    		     ( LVR.FLGMEREXV != '*'                                                                                       ")
				.append("    			   AND CODGRPCLI = 0                                                                                          ")
				.append("    			    OR ( CODGRPCLI != 0 AND FLGACSMIX == 'N')                                                                 ")
				.append("    			    OR ( CODGRPCLI != 0                                                                                       ")
				.append("                        AND FLGACSMIX == 'S'                                                                                 ")
				.append("    			         AND (SELECT COUNT(*) FROM PCAMIX MIX                                                                 ")
				.append("                                            WHERE MIX.CODMER = MER.CODMER                                                    ")
				.append("                                              AND MIX.CODGRPCLI = CODGRPCLI) > 0)                                            ")
				.append("    		     )                                                                                                            ")
				.append("    	   )                                                                                                                  ")
				.append("       AND                                                                                                                   ")
				.append("         (                                                                                                                   ")
				.append("             ( EXISTS (SELECT 1 FROM PCAPSC WHERE CODMER = MER.CODMER) AND FLGAVRPSC = '*')                                  ")
				.append("             OR                                                                                                              ")
				.append("             ( NOT EXISTS (SELECT 1 FROM PCAPSC WHERE CODMER = MER.CODMER) )                                                 ")
				.append("         )                                                                                                                   ")
				.append("       AND                                                                                                                   ")
				.append("    	   (                                                                                                                  ")
				.append("    			  ( MER.CODESLNGC = 0 )                                                                                       ")
				.append("    			  OR                                                                                                          ")
				.append("    				MER.CODESLNGC <> 0                                                                                        ")
				.append("    				AND EXISTS (SELECT 1 FROM PCATET TET                                                                      ")
				.append("    					   	             JOIN PCACLF CLF ON TET.CODCLFNGC = CLF.CODCLFNGC                                     ")
				.append("    								    WHERE TET.CODCLI    = ")
				.append(codigoCliente)
				.append("                					      AND TET.CODEDEVND = ")
				.append(codigoRepresentante)
				.append("    									  AND CLF.CODESLNGC = MER.CODESLNGC                                                   ")
				.append("    									  AND CLF.NUMPRRVND = ( SELECT MIN(NUMPRRVND) FROM PCATET TETIN                       ")
				.append("                                              						                  JOIN PCACLF CLFIN                       ")
				.append("                                                      							    	ON TETIN.CODCLFNGC = CLFIN.CODCLFNGC  ")
				.append("                                               									 WHERE TETIN.CODCLI    = TET.CODCLI       ")
				.append("                                              										   AND CLFIN.CODESLNGC = MER.CODESLNGC    ")
				.append("                                              										   AND TETIN.CODEDEVND = TET.CODEDEVND)   ")
				.append("    		)                                                                                                                 ")
				.append("    					         )                                                                                            ");

		if (itemFiltro.codigoBarras != null) {
			sql.append(" AND MER.CODBRRMER = '")
					.append(itemFiltro.codigoBarras).append("'");
		}

		if (itemFiltro.codigoFornecedor != null) {
			sql.append(" AND MER.CODTERCHV2 = ").append(
					itemFiltro.codigoFornecedor);
		}

		if (itemFiltro.mex) {
			sql.append(" AND LVR.FLGMEREXV = '*' ");
		}

		if (itemFiltro.descricaoMercadoria != null) {
			sql.append(" AND MER.DESMER LIKE '%%")
					.append(itemFiltro.descricaoMercadoria).append("%%'");
		}

		if (codigoMercadoria != null) {
			sql.append(" AND MER.CODMER    = ").append(
					codigoMercadoria);
		}

		// filtro lista customizada
		if (numeroListaCustomizada != null) {
			sql.append(" AND LVR.TIPMCOREP = ").append(numeroListaCustomizada);
		}

		sql.append(" ORDER BY MER.CODMER ");

		// Calculando Paginação
		int pagesize = 20;
		int numPagina = itemFiltro.numPagina;
		int ini = numPagina == 1 ? 0 : (numPagina - 1) * pagesize;

		sql.append(" LIMIT ").append(pagesize).append(" OFFSET ").append(ini);

		Database db = DatabaseFactory.getInstance();

		try {

			List<Map<String, String>> resultQuery = db
					.executeQuery(DatabaseUtil.montaQuery(sql.toString()));
			if (resultQuery.size() == 0) {
				return Collections.emptyList();
			}
			return convertResultQueryToItemVO(resultQuery);

		} catch (SQLException e) {

			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * Método responsável por listar os premios disponives da promocao
	 * 
	 * @param codigoCliente
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codigoRelacionamentoCliente
	 * @param codigoGrupamentoCliente
	 * @param flagAlvaraPsicotropico
	 * @param codigoRepresentante
	 * @param isMercadoriaExclusiva
	 * @param listarPremios
	 * @param palavraMeio
	 * @param codigoMercadoria
	 * @param numeroListaCustomizada
	 * @param itemFiltro
	 * @param codigoPromocao
	 * @return
	 */
	public static List<Item> listarPremiosDisponiveisDaPromocao(
			Integer codigoCliente, Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			Integer codigoRelacionamentoCliente,
			Integer codigoGrupamentoCliente, String flagAlvaraPsicotropico,
			Integer codigoRepresentante, boolean isMercadoriaExclusiva,
			String palavraMeio, String codigoMercadoria,
			Integer numeroListaCustomizada, ItemFiltro itemFiltro,
			Integer codigoPromocao) {

		final String flagAcessoExclusivoMix = ItemDisponivelDAO
				.buscaSeGrupoEhApenasDoMix(codigoGrupamentoCliente);

		StringBuilder sql = new StringBuilder();
		sql.append(
				"    SELECT DISTINCT                                                                                                          ")
				.append("           MER.CODMER ,                                                                                                      ")
				.append("           MER.DESMER || '' || COALESCE (CPL.DESCPLMER,'')  || ' ' || COALESCE (SMR.DESSCTGRP,'') DESMER,                   ")
				.append("			PRM.VLRVNDPRM,																									  ")
				.append("			PRM.CODPMC, 																									  ")
				.append("           MER.DESUNDVND          ,                                                                                          ")
				.append("           MER.FLGCPLDES          ,                                                                                          ")
				.append("           MER.FTRCNVUNT          ,                                                                                          ")
				.append("           MER.QDECXAFRN          ,                                                                                          ")
				.append("           MER.TIPMER             ,                                                                                          ")
				.append("           MER.CODGRPNCM          ,                                                                                          ")
				.append("           MER.TIPPDCMER          ,                                                                                          ")
				.append("           MER.CODTERCHV1         ,                                                                                          ")
				.append("           MER.CODTERCHV2         ,                                                                                          ")
				.append("           MER.CODCPRMER          ,                                                                                          ")
				.append("           MER.CODGRPFRC          ,                                                                                          ")
				.append("           MER.FLGPCPSMR          ,                                                                                          ")
				.append("           MER.QDEMNMVND          ,                                                                                          ")
				.append("           MER.FLGMPLQDE          ,                                                                                          ")
				.append("           MER.CODESLNGC          ,                                                                                          ")
				.append("           MER.INDMERPRL          ,                                                                                          ")
				.append("           MER.FLGINDSER          ,                                                                                          ")
				.append("           MER.CODBRRMER          ,                                                                                          ")
				.append("           MER.INDIMPFTE          ,                                                                                          ")
				.append("           MER.INDRTCBFV          ,                                                                                          ")
				.append("           MER.INDRTCBFB          ,                                                                                          ")
				.append("           MER.INDRTCBNF          ,                                                                                          ")
				.append("           MER.INDMERKIT          ,                                                                                          ")
				.append("           MER.INDRSTKIT          ,                                                                                          ")
				.append("           MER.CODGRPMER          ,                                                                                          ")
				.append("           MER.QDEMNMKIT          ,                                                                                          ")
				.append("           MER.CODFMLMER          ,                                                                                          ")
				.append("           MER.CODCLSMER          ,                                                                                          ")
				.append("           LVR.CODFILEPD          ,                                                                                          ")
				.append("           LVR.CODFILFAT          ,                                                                                          ")
				.append("           LVR.NUMRLCCID          ,                                                                                          ")
				.append("           LVR.CODMER    LVRMER   ,                                                                                          ")
				.append("           LVR.TIPICTMER          ,                                                                                          ")
				.append("           LVR.CODSMBSIT          ,                                                                                          ")
				.append("           LVR.CODFLXPCO          ,                                                                                          ")
				.append("           LVR.FLGMEREXV          ,                                                                                          ")
				.append("           LVR.INDMERMTD          ,                                                                                          ")
				.append("           LVR.CODTBTICM          ,                                                                                          ")
				.append("           LVR.VLRUNTBRT          ,                                                                                          ")
				.append("           LVR.FLGPEE             ,                                                                                          ")
				.append("           LVR.TIPMCOREP          ,                                                                                          ")
				.append("           LVR.CSTCSTLGT          ,                                                                                          ")
				.append("           LVR.PERTBTMER          ,                                                                                          ")
				.append("           LVR.PERCMSMER          ,                                                                                          ")
				.append("           COALESCE(ETQ.CODFILEPD, '') EQTFILEPD,                                                                           ")
				.append("           COALESCE(ETQ.CODMER, '')	 EQTMER,                                                                             ")
				.append("           COALESCE(ETQ.TIPESGMER, '') TIPESGMER,                                                                           ")
				.append("           COALESCE(ETQ.DATRPSMER, '')  DATRPSMER,                                                                          ")
				.append("  	        ")
				.append(codigoGrupamentoCliente)
				.append("   CODGRPCLI, ")
				.append("           '")
				.append(flagAlvaraPsicotropico)
				.append("'  FLGAVRPSC, ")
				.append("	        '")
				.append(flagAcessoExclusivoMix)
				.append("'  FLGACSMIX  ")
				.append("      FROM CADMER MER                                                                                                        ")
				.append("      JOIN PCALVR LVR ON MER.CODMER = LVR.CODMER                                                                             ")
				.append("    LEFT JOIN PCAPRM PRM ON PRM.CODMER = MER.CODMER ")
				.append("     LEFT JOIN PCASMR SMR ON SMR.CODMER = MER.CODMER                                                                         ")
				.append("     LEFT JOIN CADCPL CPL ON (MER.CODMER = CPL.CODMER) OR (SMR.CODMER = CPL.CODMER)                                          ");

		/*
		 * Busca Avançada, Por Grupo, Categoria e SubCategoria
		 */
		if (itemFiltro.codigoGrupo != null) {

			sql.append(
					" JOIN PCAGRP GRP ON MER.CODGRPMER  = GRP.CODGRPMER AND GRP.CODGRPMER = ")
					.append(itemFiltro.codigoGrupo);

			if (itemFiltro.codigoCategoria != null) {

				sql.append(
						" JOIN PCAFML FML ON GRP.CODGRPMER  = FML.CODGRPMER AND FML.CODFMLMER = ")
						.append(itemFiltro.codigoCategoria);

				if (itemFiltro.codigoSubCategoria != null) {

					sql.append(
							" JOIN PCACLS CLS ON FML.CODFMLMER  = FML.CODFMLMER AND GRP.CODGRPMER = CLS.CODGRPMER AND CLS.CODCLSMER = ")
							.append(itemFiltro.codigoSubCategoria);

				}
			}
		}

		sql.append(
				" LEFT JOIN PCAETQ ETQ ON LVR.CODFILEPD = ETQ.CODFILEPD                                                                       ")
				.append("	    AND LVR.CODMER = ETQ.CODMER                                                                                           ")
				.append("     WHERE MER.INDRSTKIT = 0                                                                                                 ")
				.append("    AND  PRM.CODPMC = ")
				.append(codigoPromocao)
				.append(" AND COALESCE(smr.codmer, mer.codmer) IS NOT NULL                    ")
				.append("       AND COALESCE(ETQ.TIPESGMER, ' ') <> 'F'                                                                               ")
				.append("       AND LVR.CODFILEPD = ")
				.append(codigoFilialExpedicao)
				.append("   	AND LVR.CODFILFAT = ")
				.append(codigoFilialFaturamento)
				.append("	    AND LVR.NUMRLCCID = ")
				.append(codigoRelacionamentoCliente)
				.append("       AND (                                                                                                                 ")
				.append("    		     ( LVR.FLGMEREXV  = '*'                                                                                       ")
				.append("    			   AND CODGRPCLI != 0                                                                                         ")
				.append("    			   AND (SELECT COUNT(*)                                                                                       ")
				.append("    			          FROM PCAMIX MIX                                                                                     ")
				.append("                        WHERE MIX.CODMER = MER.CODMER                                                                        ")
				.append("                          AND MIX.CODGRPCLI = CODGRPCLI) > 0                                                                 ")
				.append("    		     )                                                                                                            ")
				.append("    		     OR                                                                                                           ")
				.append("    		     ( LVR.FLGMEREXV != '*'                                                                                       ")
				.append("    			   AND CODGRPCLI = 0                                                                                          ")
				.append("    			    OR ( CODGRPCLI != 0 AND FLGACSMIX == 'N')                                                                 ")
				.append("    			    OR ( CODGRPCLI != 0                                                                                       ")
				.append("                        AND FLGACSMIX == 'S'                                                                                 ")
				.append("    			         AND (SELECT COUNT(*) FROM PCAMIX MIX                                                                 ")
				.append("                                            WHERE MIX.CODMER = MER.CODMER                                                    ")
				.append("                                              AND MIX.CODGRPCLI = CODGRPCLI) > 0)                                            ")
				.append("    		     )                                                                                                            ")
				.append("    	   )                                                                                                                  ")
				.append("       AND                                                                                                                   ")
				.append("         (                                                                                                                   ")
				.append("             ( EXISTS (SELECT 1 FROM PCAPSC WHERE CODMER = MER.CODMER) AND FLGAVRPSC = '*')                                  ")
				.append("             OR                                                                                                              ")
				.append("             ( NOT EXISTS (SELECT 1 FROM PCAPSC WHERE CODMER = MER.CODMER) )                                                 ")
				.append("         )                                                                                                                   ")
				.append("       AND                                                                                                                   ")
				.append("    	   (                                                                                                                  ")
				.append("    			  ( MER.CODESLNGC = 0 )                                                                                       ")
				.append("    			  OR                                                                                                          ")
				.append("    				MER.CODESLNGC <> 0                                                                                        ")
				.append("    				AND EXISTS (SELECT 1 FROM PCATET TET                                                                      ")
				.append("    					   	             JOIN PCACLF CLF ON TET.CODCLFNGC = CLF.CODCLFNGC                                     ")
				.append("    								    WHERE TET.CODCLI    = ")
				.append(codigoCliente)
				.append("                					      AND TET.CODEDEVND = ")
				.append(codigoRepresentante)
				.append("    									  AND CLF.CODESLNGC = MER.CODESLNGC                                                   ")
				.append("    									  AND CLF.NUMPRRVND = ( SELECT MIN(NUMPRRVND) FROM PCATET TETIN                       ")
				.append("                                              						                  JOIN PCACLF CLFIN                       ")
				.append("                                                      							    	ON TETIN.CODCLFNGC = CLFIN.CODCLFNGC  ")
				.append("                                               									 WHERE TETIN.CODCLI    = TET.CODCLI       ")
				.append("                                              										   AND CLFIN.CODESLNGC = MER.CODESLNGC    ")
				.append("                                              										   AND TETIN.CODEDEVND = TET.CODEDEVND)   ")
				.append("    		)                                                                                                                 ")
				.append("    					         )                                                                                            ");

		if (itemFiltro.codigoBarras != null) {
			sql.append(" AND MER.CODBRRMER = '")
					.append(itemFiltro.codigoBarras).append("'");
		}

		if (itemFiltro.mex) {
			sql.append(" AND LVR.FLGMEREXV = '*' ");
		}

		if (itemFiltro.descricaoMercadoria != null) {
			sql.append(" AND MER.DESMER LIKE '%%")
					.append(itemFiltro.descricaoMercadoria).append("%%'");
		}

		if (itemFiltro.codigoMercadoria != null) {
			sql.append(" AND MER.CODMER    = ").append(
					itemFiltro.codigoMercadoria);
		}

		// filtro lista customizada
		if (numeroListaCustomizada != null) {
			sql.append(" AND LVR.TIPMCOREP = ").append(numeroListaCustomizada);
		}

		sql.append(" ORDER BY MER.CODMER ");

		// Calculando Paginação
		int pagesize = 20;
		int numPagina = itemFiltro.numPagina;
		int ini = numPagina == 1 ? 0 : (numPagina - 1) * pagesize;

		sql.append(" LIMIT ").append(pagesize).append(" OFFSET ").append(ini);

		Database db = DatabaseFactory.getInstance();

		try {

			List<Map<String, String>> resultQuery = db
					.executeQuery(DatabaseUtil.montaQuery(sql));
			if (resultQuery.size() == 0) {
				return Collections.emptyList();
			}
			return convertResultQueryToItemPromocaoPremioVO(resultQuery);

		} catch (SQLException e) {

			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	private static List<Item> convertResultQueryToItemVO(
			List<Map<String, String>> resultQuery) {

		List<Item> lstItem = new ArrayList<Item>();
		for (Map<String, String> registro : resultQuery) {

			Item item = new Item();

			Imposto stb = item.stb;
			Preco preco = item.preco;
			Mercadoria mercadoria = item.mercadoria;
			Promocao promocao = item.promocao;

			item.valorLiquidoComImposto = BigDecimal.ZERO;
			item.valorUnitarioCaixaComImposto = BigDecimal.ZERO;
			item.valorCaixaComImposto = BigDecimal.ZERO;
			item.valorUnitarioComImposto = BigDecimal.ZERO;
			item.frete = BigDecimal.ZERO;

			stb.valorSTBUnitario = BigDecimal.ZERO;

			item.quantidadeMercadoria = BigDecimal.ZERO.intValue();

			item.notaFiscal = BigDecimal.ONE.intValue();

			item.codigoFilialExpedicao = Util.getInteger(registro
					.get("CODFILEPD"));

			item.codigoFilialFaturamento = Util.getInteger(registro
					.get("CODFILFAT"));

			mercadoria.codigo = Util.getInteger(registro.get("CODMER"));

			mercadoria.descricao = registro.get("DESMER");

			mercadoria.codigoEspecialistaNegociacao = Util.getInteger(registro
					.get("CODESLNGC"));

			mercadoria.quantidadeCaixaFornecedor = Util.getInteger(registro
					.get("QDECXAFRN"));

			mercadoria.quantidadeMinimaKit = Util.getInteger(registro
					.get("QDEMNMKIT"));

			mercadoria.quantidadeMinimaVenda = Util.getInteger(registro
					.get("QDEMNMVND"));

			mercadoria.codigoChaveCategoria = registro.get("CODTERCHV1");

			mercadoria.tipo = registro.get("TIPMER");

			mercadoria.flagMultiplicadorQuantidade = registro.get("FLGMPLQDE");

			String codigoGrupoFracionado = registro.get("CODGRPFRC");
			mercadoria.codigoGrupoFracionado = (codigoGrupoFracionado == null || codigoGrupoFracionado
					.equals("")) ? 0 : Util.getInteger(codigoGrupoFracionado);

			mercadoria.indicadorRestricaoKit = Util.getInteger(registro
					.get("INDRSTKIT"));

			mercadoria.tipoProducaoMercadoria = Util.getInteger(registro
					.get("TIPPDCMER"));

			mercadoria.tipoEsgotamento = Util.getStringValue(registro
					.get("TIPESGMER"));

			mercadoria.temMercadoriaSimilar = MercadoriaSimilarDAO
					.temSimilar(mercadoria.codigo);
			if (mercadoria.temMercadoriaSimilar) {
				mercadoria.codigoMercadoriaPrincipal = mercadoria.codigo;
			}

			mercadoria.temRestricaoBeneficioCustomizado = Util
					.getInteger(registro.get("INDRTCBFV"));

			mercadoria.temRestricaoBeneficioCustomizadoNoBrinde = Util
					.getInteger(registro.get("INDRTCBFB"));

			mercadoria.temRestricaoBrinde = Util.getInteger(registro
					.get("INDRTCBNF"));

			mercadoria.fatorConversaoUnitario = Util.getInteger(registro
					.get("FTRCNVUNT"));

			mercadoria.indicaMercadoriaKit = Util.getInteger(registro
					.get("INDMERKIT"));

			item.mercadoria.descricaoNegocioMercadoria = TipoNegociacaoMercadoria.VENDA.descTipoNegociacaoMercadoria;

			preco.valorBrutoUnitario = BigDecimal.valueOf(Double
					.parseDouble(registro.get("VLRUNTBRT")));

			preco.flagPrecoMonitorado = Integer.parseInt(registro
					.get("INDMERMTD"));

			preco.percentualTributacao = Util.getBigDecimal(registro
					.get("PERTBTMER"));

			preco.codigoTributacaoICM = Util.getInteger(registro
					.get("CODTBTICM"));

			preco.codigoSimboloSituacao = registro.get("CODSMBSIT");

			preco.custoLogistica = BigDecimal.valueOf(Double
					.parseDouble(registro.get("CSTCSTLGT")));

			preco.percentualICMS = BigDecimal.valueOf(Double
					.parseDouble(registro.get("PERCMSMER")));

			preco.tipoIncentivoMercadoria = registro.get("TIPICTMER");

			preco.codigoFlexivelPreco = registro.get("CODFLXPCO");

			preco.codigoSimboloSituacao = registro.get("CODSMBSIT");

			preco.flagMercadoriaExclusiva = registro.get("FLGMEREXV");

			preco.flagPontoEncontroEletronico = registro.get("FLGPEE");
			if (preco.flagPontoEncontroEletronico == null) {
				preco.flagPontoEncontroEletronico = StringUtil.EMPTY;
			}

			preco.tipoMarcacaoRepresentante = registro.get("TIPMCOREP");
			if (preco.tipoMarcacaoRepresentante == null) {
				preco.tipoMarcacaoRepresentante = StringUtil.EMPTY;
			}

			promocao.codigo = Util.getInteger(registro.get("CODPMC"));

			item.indicaRestricaoBeneficioCustomizado = mercadoria.temRestricaoBeneficioCustomizado;
			item.indicaRestricaoBeneficioCustomizadoBrinde = mercadoria.temRestricaoBeneficioCustomizadoNoBrinde;
			item.indicaRestricaoItemBrinde = mercadoria.temRestricaoBrinde;

			lstItem.add(item);
		}

		return lstItem;
	}

	private static List<Item> convertResultQueryToItemPromocaoPremioVO(
			List<Map<String, String>> resultQuery) {

		List<Item> lstItem = new ArrayList<Item>();
		for (Map<String, String> registro : resultQuery) {

			Item item = new Item();

			Imposto stb = item.stb;
			Preco preco = item.preco;
			Mercadoria mercadoria = item.mercadoria;
			Promocao promocao = item.promocao;

			item.valorLiquidoComImposto = BigDecimal.ZERO;
			item.valorUnitarioCaixaComImposto = BigDecimal.ZERO;
			item.valorCaixaComImposto = BigDecimal.ZERO;
			item.valorUnitarioComImposto = BigDecimal.ZERO;
			item.frete = BigDecimal.ZERO;

			stb.valorSTBUnitario = BigDecimal.ZERO;

			item.quantidadeMercadoria = BigDecimal.ZERO.intValue();

			item.notaFiscal = BigDecimal.ONE.intValue();

			item.codigoFilialExpedicao = Util.getInteger(registro
					.get("CODFILEPD"));

			item.codigoFilialFaturamento = Util.getInteger(registro
					.get("CODFILFAT"));

			mercadoria.codigo = Util.getInteger(registro.get("CODMER"));

			mercadoria.descricao = registro.get("DESMER");

			mercadoria.codigoEspecialistaNegociacao = Util.getInteger(registro
					.get("CODESLNGC"));

			mercadoria.quantidadeCaixaFornecedor = Util.getInteger(registro
					.get("QDECXAFRN"));

			mercadoria.quantidadeMinimaKit = Util.getInteger(registro
					.get("QDEMNMKIT"));

			mercadoria.quantidadeMinimaVenda = Util.getInteger(registro
					.get("QDEMNMVND"));

			mercadoria.codigoChaveCategoria = registro.get("CODTERCHV1");

			mercadoria.tipo = registro.get("TIPMER");

			mercadoria.flagMultiplicadorQuantidade = registro.get("FLGMPLQDE");

			String codigoGrupoFracionado = registro.get("CODGRPFRC");
			mercadoria.codigoGrupoFracionado = (codigoGrupoFracionado == null || codigoGrupoFracionado
					.equals("")) ? 0 : Util.getInteger(codigoGrupoFracionado);

			mercadoria.indicadorRestricaoKit = Util.getInteger(registro
					.get("INDRSTKIT"));

			mercadoria.tipoProducaoMercadoria = Util.getInteger(registro
					.get("TIPPDCMER"));

			mercadoria.tipoEsgotamento = Util.getStringValue(registro
					.get("TIPESGMER"));

			mercadoria.temMercadoriaSimilar = MercadoriaSimilarDAO
					.temSimilar(mercadoria.codigo);
			if (mercadoria.temMercadoriaSimilar) {
				mercadoria.codigoMercadoriaPrincipal = mercadoria.codigo;
			}

			mercadoria.temRestricaoBeneficioCustomizado = Util
					.getInteger(registro.get("INDRTCBFV"));

			mercadoria.temRestricaoBeneficioCustomizadoNoBrinde = Util
					.getInteger(registro.get("INDRTCBFB"));

			mercadoria.temRestricaoBrinde = Util.getInteger(registro
					.get("INDRTCBNF"));

			mercadoria.fatorConversaoUnitario = Util.getInteger(registro
					.get("FTRCNVUNT"));

			mercadoria.indicaMercadoriaKit = Util.getInteger(registro
					.get("INDMERKIT"));

			item.mercadoria.descricaoNegocioMercadoria = TipoNegociacaoMercadoria.PROMOCAO.descTipoNegociacaoMercadoria;
			
			item.tipoNegociacaoMercadoria = TipoNegociacaoMercadoria.PROMOCAO.idTipoNegociacaoMercadoria;

			preco.valorBrutoUnitario = BigDecimal.valueOf(Double
					.parseDouble(registro.get("VLRUNTBRT")));

			preco.flagPrecoMonitorado = Integer.parseInt(registro
					.get("INDMERMTD"));

			preco.percentualTributacao = Util.getBigDecimal(registro
					.get("PERTBTMER"));

			preco.codigoTributacaoICM = Util.getInteger(registro
					.get("CODTBTICM"));

			preco.codigoSimboloSituacao = registro.get("CODSMBSIT");

			preco.custoLogistica = BigDecimal.valueOf(Double
					.parseDouble(registro.get("CSTCSTLGT")));

			preco.percentualICMS = BigDecimal.valueOf(Double
					.parseDouble(registro.get("PERCMSMER")));

			preco.tipoIncentivoMercadoria = registro.get("TIPICTMER");

			preco.codigoFlexivelPreco = registro.get("CODFLXPCO");

			preco.codigoSimboloSituacao = registro.get("CODSMBSIT");

			preco.flagMercadoriaExclusiva = registro.get("FLGMEREXV");

			preco.flagPontoEncontroEletronico = registro.get("FLGPEE");
			if (preco.flagPontoEncontroEletronico == null) {
				preco.flagPontoEncontroEletronico = StringUtil.EMPTY;
			}

			preco.tipoMarcacaoRepresentante = registro.get("TIPMCOREP");
			if (preco.tipoMarcacaoRepresentante == null) {
				preco.tipoMarcacaoRepresentante = StringUtil.EMPTY;
			}

			promocao.valorVenda = Util.getBigDecimal(registro.get("VLRVNDPRM"));
			promocao.codigo = Util.getInteger(registro.get("CODPMC"));

			item.indicaRestricaoBeneficioCustomizado = mercadoria.temRestricaoBeneficioCustomizado;
			item.indicaRestricaoBeneficioCustomizadoBrinde = mercadoria.temRestricaoBeneficioCustomizadoNoBrinde;
			item.indicaRestricaoItemBrinde = mercadoria.temRestricaoBrinde;

			lstItem.add(item);
		}

		return lstItem;
	}
	
	
	public static Mensagem obterValorRestantePromocao(Integer codigoPromocao){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT MIN(MPR.CODPMC) CODPMC, ")
				.append("       SUM( ")
				.append("       CASE WHEN ITE.qdemerped IS NOT NULL THEN 1 ELSE 0 END + ")
				.append("       CASE WHEN GRP.qdemerped IS NOT NULL THEN 1 ELSE 0 END + ")
				.append("       CASE WHEN GON.qdemerped IS NOT NULL THEN 1 ELSE 0 END + ")
				.append("       CASE WHEN PMC.qdemerped IS NOT NULL THEN 1 ELSE 0 END + ")
				.append("       CASE WHEN SMR.qdemerped IS NOT NULL THEN 1 ELSE 0 END ")
				.append("       ) QDE_ITENS, ")
				.append("       SUM( ")
				.append("       (COALESCE(ITE.VLRLIQMER,0) + COALESCE(ITE.vlrfrtmer,0)) * COALESCE(ITE.qdemerped,0) + COALESCE(ite.vlrstbtot,0) + COALESCE(ite.vlripitot,0) + ")
				.append("       (COALESCE(GRP.VLRLIQMER,0) + COALESCE(GRP.vlrfrtmer,0)) * COALESCE(GRP.qdemerped,0) + COALESCE(GRP.vlrstbtot,0) + COALESCE(GRP.vlripitot,0) + ")
				.append("       (COALESCE(GON.VLRLIQMER,0) + COALESCE(GON.vlrfrtmer,0)) * COALESCE(GON.qdemerped,0) + COALESCE(GON.vlrstbtot,0) + COALESCE(GON.vlripitot,0) + ")
				.append("       (COALESCE(PMC.VLRLIQMER,0) + COALESCE(PMC.vlrfrtmer,0)) * COALESCE(PMC.qdemerped,0) + COALESCE(PMC.vlrstbtot,0) + COALESCE(PMC.vlripitot,0) + ")
				.append("       (COALESCE(SMR.VLRLIQMER,0) + COALESCE(SMR.vlrfrtmer,0)) * COALESCE(SMR.qdemerped,0) + COALESCE(SMR.vlrstbtot,0) + COALESCE(SMR.vlripitot,0) ")
				.append("       ) VENDA,PPR.CODCTRPMC, PPR.TAMIVLFXA, PPR.QDEPTOFXA, PPR.VLRINIFXA ")
				.append(" FROM  PCAMPR MPR LEFT JOIN TMPITEPE ITE ON ")
				.append("           ITE.CODMER = MPR.CODMER AND ITE.CODPMC = 9999 ")
				.append("      LEFT JOIN TMPGRPDE GRP ON                          ")
				.append("           GRP.CODMER = MPR.CODMER AND GRP.CODPMC = 9999 ")
				.append("      LEFT JOIN TMPMEGO GON ON                           ")
				.append("           GON.CODMER = MPR.CODMER AND GON.CODPMC = 9999 ")
				.append("      LEFT JOIN TMPMEPMC PMC ON                          ")
				.append("           PMC.CODMER = MPR.CODMER AND PMC.CODPMC = 9999 ")
				.append("      LEFT JOIN TMPMESMR SMR ON                          ")
				.append("           SMR.CODMER = MPR.CODMER AND SMR.CODPMC = 9999 ");
		sql.append("      INNER JOIN PCAPPR PPR ON  PPR.CODPMC = ")
				.append(codigoPromocao)
				.append(" WHERE                                                   ");
		sql.append("       MPR.CODPMC = ").append(codigoPromocao);

		
		Database db = DatabaseFactory.getInstance();

		try {

			List<Map<String, String>> resultQuery = db.executeQuery(DatabaseUtil.montaQuery(sql.toString()));
			if (resultQuery.size() == 0) {
				return null;
			}
			
//			Integer codPromocao;
			Integer quantidadeItens;
			BigDecimal venda;
			Integer codigoCriterioPromocao;
			BigDecimal tamanhoIntervaloFaixa;
//			BigDecimal quantidadePontosFaixa;
//			BigDecimal valorInicialFaixa;
			BigDecimal restante = BigDecimal.ZERO;
			
//			codPromocao = Util.getInteger(resultQuery.get(0).get("CODPMC"));
			quantidadeItens = Util.getInteger(resultQuery.get(0).get("QDE_ITENS"));
			venda = Util.getBigDecimal(resultQuery.get(0).get("VENDA"));
			codigoCriterioPromocao = Util.getInteger(resultQuery.get(0).get("CODCTRPMC"));
			tamanhoIntervaloFaixa = Util.getBigDecimal(resultQuery.get(0).get("TAMIVLFXA"));
//			quantidadePontosFaixa = Util.getBigDecimal(resultQuery.get(0).get("QDEPTOFXA"));
//			valorInicialFaixa = Util.getBigDecimal(resultQuery.get(0).get("VLRINIFXA"));
			
			Mensagem mensagem = new Mensagem();
			
			if(codigoCriterioPromocao == 2){
				restante = new BigDecimal(quantidadeItens.doubleValue());
			}else if(codigoCriterioPromocao == 5){
				restante = new BigDecimal(venda.doubleValue());
			}
			if(restante.compareTo(tamanhoIntervaloFaixa) == 1){
				if(codigoCriterioPromocao == 2 ){
					mensagem.texto = "QUANTIDADE MINIMA DA PROMOÇÃO JÁ ALCANÇADO";
				}else if(codigoCriterioPromocao == 5){
					mensagem.texto  = "VALOR MINIMO DA PROMOÇÃO JÁ ALCANÇADO\n Valor vendido: ";
					mensagem.valor = StringUtil.formataMonetario(restante, 2);
				}
			}else{
				
				if(codigoCriterioPromocao == 2){
					mensagem.texto = "QUANTIDADE QUE FALTA PARA COMPLETAR ";
					restante = tamanhoIntervaloFaixa.subtract(restante);
					mensagem.valor = restante.toString();
				}else{
					mensagem.texto = "VALOR QUE FALTA PARA COMPLETAR ";
					restante = tamanhoIntervaloFaixa.subtract(restante);
					mensagem.valor = StringUtil.formataMonetario(restante, 2);
				}
			}
				return mensagem;
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
		
	} 

}
