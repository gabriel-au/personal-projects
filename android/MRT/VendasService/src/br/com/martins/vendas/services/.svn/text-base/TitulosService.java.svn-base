package br.com.martins.vendas.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.TipoFinanciamento;
import br.com.martins.vendas.vo.Titulo;
import br.com.martins.vendas.vo.TituloTribanco;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class TitulosService {

	private static final String TAG = TitulosService.class.getName();
	private static final int TITULO_VENCIDO = 4;
	private static final int TITULO_A_VENCER = 5;

	/**
	 * Busca os titulos em aberto que nao sao do tribanco Titulos com: IDTTIPREG
	 * = 1 = PROVIOSIONADO IDTTIPREG = 3 = TITULOS FINANCIADOS PELO TRIBANCO
	 * IDTTIPREG = 2 = FAZ O CALCULO PARA DESCOBRIR A SITUACAO DO TITULO 4 =
	 * VENCIDO 5 = A VENCER
	 * 
	 * @param idSituacaoTitulos
	 * @param nomeCliente
	 * @param codigoCliente
	 * @param codigoTerritorio
	 * @return
	 * @throws Exception
	 */
	public static List<Titulo> findTitulosAberto(Integer idSituacaoTitulos, String nomeCliente, Integer codigoCliente, Integer codigoTerritorio) {

		List<Titulo> titulos = new ArrayList<Titulo>();

		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			query.append(" select * from ").append(" (select TIT.DATVNCTIT,CLI.NOMCLI, TIT.VLRSLDTIT,TIT.NUMDOCTIT,CLI.CODCLI,TIT.TIPDOCTIT,TIT.CODESTTIT,").append(" CASE WHEN TIT.IDTTIPREG = 2 THEN (")
					.append(" CASE WHEN julianday(date('now')) - julianday(date(substr(TIT.DATVNCTIT,1,4) || '-' || substr(TIT.DATVNCTIT,5,2) || '-' || substr(TIT.DATVNCTIT,7,2)))").append(" > 0 THEN 4 ELSE 5 END) ELSE TIT.IDTTIPREG END as 'IDSITUACAOTITULO'").append(" from PCATIT TIT ")
					.append(" join PCACLI CLI ON TIT.CODCLI = CLI.CODCLI ");

			if (codigoTerritorio != null) {
				query.append(" LEFT JOIN PCACLT CLT ON CLI.CODCLI = CLT.CODCLI ");
				query.append(" where CLT.CODTETVND = " + codigoTerritorio + " AND ");
			} else {
				query.append(" where ");
			}

			query.append(" IDTTIPREG <> " + TipoFinanciamento.TRIBANCO.tipoFinanciamento + ") ").append(" WHERE 1 = 1 ");

			if (codigoCliente != null) {
				query.append(" and CODCLI = " + codigoCliente);
			}
			if (idSituacaoTitulos != null) {
				query.append(" and IDSITUACAOTITULO = " + idSituacaoTitulos);
			}
			if (nomeCliente != null) {
				query.append(" and NOMCLI LIKE '%" + nomeCliente + "%'");
			}
			titulos = criaListaTitulos(db.executeQuery(query.toString()));

		} catch (SQLException e) {
			Log.e(TAG, "TitulosService - Erro no metodo findTitulosAberto() : " + e.getMessage());
		} catch (Exception e) {
			Log.e(TAG, "TitulosService - Erro no metodo findTitulosAberto() : " + e.getMessage());
		}
		return titulos;
	}

	/**
	 * Busca os titulos em aberto financiados pelo tribanco
	 * 
	 * @param idSituacaoTitulos
	 * @param nomeCliente
	 * @param codigoCliente
	 * @param codigoTerritorio
	 * @return
	 * @throws Exception
	 */
	public static List<TituloTribanco> findTitulosTribanco(Integer idSituacaoTitulos, String nomeCliente, Integer codigoCliente, Integer codigoTerritorio) {

		List<TituloTribanco> titulos = new ArrayList<TituloTribanco>();
		
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			query.append(" select ");
			query.append(" CODCLI, NOMCLI, IDSITUACAOTITULO, SUM(QDETITTBO) AS QTDTIT, SUM(VLRTITVNC) AS VLRTIT ");
			query.append(" from  ( ");
			query.append(" select CLI.NOMCLI,  ");
			query.append(" TIT.VLRTITVNC, ");
			query.append(" CLI.CODCLI,  ");
			query.append(" TIT.IDTTIPREG, ");
			query.append(" CASE WHEN julianday(date('now')) - julianday(date(substr(TIT.DATVNCTIT,1,4) || '-' || substr(TIT.DATVNCTIT,5,2) || '-' || substr(TIT.DATVNCTIT,7,2))) > 0 ");
			query.append(" THEN " + TITULO_VENCIDO + " ELSE " + TITULO_A_VENCER + " END as 'IDSITUACAOTITULO',QDETITTBO  ");
			query.append(" from PCATIT TIT   ");
			query.append(" join PCACLI CLI ON TIT.CODCLI = CLI.CODCLI ");

			if (codigoTerritorio != null) {
				query.append(" LEFT JOIN PCACLT CLT ON CLI.CODCLI = CLT.CODCLI ");
				query.append(" where CLT.CODTETVND = " + codigoTerritorio + " AND ");
			} else {
				query.append(" where ");
			}

			query.append(" IDTTIPREG = " + TipoFinanciamento.TRIBANCO.tipoFinanciamento);
			query.append(" )   ");
			query.append(" WHERE 1 = 1");

			if (codigoCliente != null) {
				query.append(" and CODCLI = " + codigoCliente);
			}
			if (idSituacaoTitulos != null) {
				query.append(" and IDSITUACAOTITULO = " + idSituacaoTitulos);
			}
			if (nomeCliente != null) {
				query.append(" and NOMCLI LIKE '%" + nomeCliente + "%'");
			}
			query.append(" GROUP BY CODCLI, NOMCLI, IDSITUACAOTITULO,QDETITTBO ").append(" ORDER BY NOMCLI");
			titulos = criaListaTitulosTribanco(db.executeQuery(query.toString()));

		} catch (SQLException e) {
			Log.e(TAG, "TitulosService - Erro no metodo findTitulosTribanco() : " + e.getMessage());
		} catch (Exception e) {
			Log.e(TAG, "TitulosService - Erro no metodo findTitulosTribanco() : " + e.getMessage());
		}
		
		return titulos;
	}

	private static List<TituloTribanco> criaListaTitulosTribanco(List<Map<String, String>> titulos) {
		
		int codigoCliente = 0;

		TituloTribanco tituloTribanco = null;
		List<TituloTribanco> listaTitulosTribanco = new ArrayList<TituloTribanco>();
		for (Map<String, String> tituloIt : titulos) {
			if (codigoCliente != Util.getInteger(tituloIt.get("CODCLI"))) {
				if (codigoCliente != 0) {
					listaTitulosTribanco.add(tituloTribanco);
				}
				tituloTribanco = new TituloTribanco();
				codigoCliente = Util.getInteger(tituloIt.get("CODCLI"));
			}
			tituloTribanco.nomeCliente = tituloIt.get("NOMCLI");
			tituloTribanco.codigoCliente = codigoCliente;
			tituloTribanco.quantidadeTitulos = Util.getInteger(tituloIt.get("QTDTIT"));
			if (Util.getInteger(tituloIt.get("QTDTIT")).intValue() == TITULO_VENCIDO) {
				tituloTribanco.valorVencer = Util.getBigDecimal(tituloIt.get("VLRTIT"));
			} else {
				tituloTribanco.valorVencido = Util.getBigDecimal(tituloIt.get("VLRTIT"));
			}
		}
		if (codigoCliente != 0) {
			listaTitulosTribanco.add(tituloTribanco);
		}
		return listaTitulosTribanco;

	}

	private static List<Titulo> criaListaTitulos(List<Map<String, String>> titulos) {

		Titulo titulo;
		List<Titulo> listaTitulos = new ArrayList<Titulo>();
		
		try {
			for (Map<String, String> tituloIt : titulos) {
				titulo = new Titulo();
				titulo.numeroCodTitulo = Util.getInteger(tituloIt.get("NUMDOCTIT"));
				if (titulo.numeroCodTitulo > 0) {
					titulo.nomeCliente = tituloIt.get("NOMCLI");
					titulo.dataVencimento = DateUtil.formataData(tituloIt.get("DATVNCTIT"), "yyyyMMdd");
					titulo.idSituacaoTitulo = Util.getInteger(tituloIt.get("IDSITUACAOTITULO"));
					titulo.valorSaldoAberto = Util.getBigDecimal(tituloIt.get("VLRSLDTIT"));
					titulo.codCliente = Util.getInteger(tituloIt.get("CODCLI"));
					titulo.codEstadoTitulo = tituloIt.get("CODESTTIT");
					titulo.tipoTitulo = tituloIt.get("TIPDOCTIT");
					titulo.idTipoRegiao = tituloIt.get("IDTTIPREG");
					listaTitulos.add(titulo);
				}
			}
			listaTitulos = calcularDiasVencidos(listaTitulos);

		} catch (ParseException e) {
			Log.e(TAG, "TitulosService - Erro no metodo criaListaTitulos() : " + e.getMessage());
		}
		
		return listaTitulos;
	}

	public static List<Titulo> calcularDiasVencidos(List<Titulo> listaTitulos) {
		Calendar hoje = Calendar.getInstance();
		Calendar dataVencimento = Calendar.getInstance();
		// Quantidade de milissegundos em um dia
		Integer tempoDia = 1000 * 60 * 60 * 24;

		for (Titulo titulo : listaTitulos) {
			if (titulo.dataVencimento != null && !"".equals(titulo.dataVencimento)) {
				dataVencimento.setTime(titulo.dataVencimento);
				Long dia = hoje.getTimeInMillis() - dataVencimento.getTimeInMillis();
				titulo.diasVencidos = (int) (dia / tempoDia);

				if (titulo.diasVencidos < 0) {
					titulo.diasVencidos = null;
				}
			}
		}
		return listaTitulos;
	}

	/**
	 * Busca saldo aberto do cliente com base nos titulos em aberto TODO:
	 * confirmar se a logica esta correta, buscando sempre o titulo com data de
	 * vencimento mais antigo
	 * 
	 * @param codigoCliente
	 * @return
	 */
	public static BigDecimal obterSaldoAbertoCliente(Integer codigoCliente) {
		BigDecimal resultado = new BigDecimal(0.0);
		try {
			Database db = DatabaseFactory.getInstance();
			StringBuilder query = new StringBuilder();
			query.append(" select VLRSLDABT ").append(" from PCATIT TIT ").append(" where CODCLI = " + codigoCliente).append(" order by IDTTIPREG ASC ");
			List<Map<String, String>> lista = db.executeQuery(query.toString());
			resultado = lista.isEmpty() ? BigDecimal.ZERO : new BigDecimal(lista.get(0).get("VLRSLDABT"));
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return resultado;
	}

}