package br.com.martins.vendas.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.AgendaRoteirizacao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class AgendaRoteirizacaoDAO {
	
	/**
	 * Método que lista agenda de roteirização de um determinado cliente
	 * @param codigoCliente
	 * @param codigoFilialExpedicao
	 * @param codigoCidade
	 * @param codigoRegraDistribuicao
	 * @return
	 * @throws Exception
	 */
	public static List<AgendaRoteirizacao> findAgendaRoteirizacao(Integer codigoCliente, Integer codigoFilialExpedicao, Integer codigoCidade, 
			Integer codigoRegraDistribuicao, Integer codigoFilialFaturamento, Integer codigoBairro, Integer codigoUnidadeEstrategia, 
			boolean isOrdenacaoCrescente, boolean isOrdenacaoDecrescente) throws Exception {

		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		query.append(" select DISTINCT AGR.CODFILEPD, AGR.DATRTZ, AGR.HRARTZ, AGR.QDEPRVENT, AGR.DIASMN, AGR.HRASEGPRP, AGR.QDESEGPED, ") 
		.append(" CASE WHEN AGR.CODFILFAT = 0 THEN '-' ELSE AGR.CODFILFAT end AS CODFILFAT, ")
		.append(" CASE WHEN AGR.DATRTZ = '' THEN 'N' ELSE 'S' end AS 'CIDADEEXCESSAO ', ")
		.append(" SUBSTR(AGR.HRARTZ,1,2) || ':' || SUBSTR(AGR.HRARTZ,3,4) AS 'HORA', ")
		.append(" strftime('%%H:%%M', julianday(strftime('%%H:%%M',SUBSTR(AGR.HRARTZ,1,2) || ':' || SUBSTR(AGR.HRARTZ,3,4)), ") 
		.append(" '-' || CAST(AGR.HRASEGPRP AS Integer) || ' minutes')) AS ENVIO, ")
		.append(" AGR.CODCLI, AGR.CODUNDESR, AGR.CODBAI  ")
		.append(" from PCACLI CLI ")
		.append(" JOIN PCABAI BAI ON CLI.CODBAIENT = BAI.CODBAI ")
		.append(" JOIN PCACOP COP ON CLI.CODCLI = COP.CODCLI ")
		.append(" JOIN PCAAGR AGR ON BAI.CODCID = AGR.CODCID AND COP.CODFILEPD = AGR.CODFILEPD AND COP.CODREGDTB = AGR.CODREGDTB ")
		.append(" WHERE ")
		.append(" CLI.CODCLI = %s ");
		
		if(codigoFilialExpedicao != null){
			query.append(" AND AGR.CODFILEPD = %s ");
		}

		if(codigoCidade != null){
			query.append(" AND AGR.CODCID = %s ");
		}
		
		if(codigoRegraDistribuicao != null){
			query.append(" AND AGR.CODREGDTB = %s ");
		}
		
		if(codigoFilialFaturamento != null){
			query.append(" AND COP.CODFILFAT = %s ");
		} 
		
		if(codigoBairro != null){
			query.append(" AND CLI.CODBAIENT = %s ");
		}
		
		if(codigoUnidadeEstrategia != null){
			query.append(" AGR.CODUNDESR = %s ");
		}
		
		if(isOrdenacaoCrescente){
			query.append(" ORDER BY AGR.DATRTZ ASC, AGR.DIASMN ASC , AGR.HRARTZ ASC ");
		}else if(isOrdenacaoDecrescente){
			query.append(" ORDER BY AGR.DATRTZ DESC, AGR.DIASMN DESC , AGR.HRARTZ DESC ");
		}
		
		List<Map<String, String>> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoCliente, codigoFilialExpedicao, 
				codigoCidade, codigoRegraDistribuicao, codigoFilialFaturamento, codigoBairro, codigoUnidadeEstrategia));
		return criaListaAgendaRoteirizacao(result);
	}

	/**
	 * Método que cria a lista da agenda de roteirização a partir do retorno do banco
	 * @param listaAgendaRoteirizacaoBanco - lista retornada pelo banco
	 * @return  List<AgendaRoteirizacao> - lista com os dados preenchidos de agenda de roteirização
	 * @throws Exception
	 */
	private static List<AgendaRoteirizacao> criaListaAgendaRoteirizacao(List<Map<String, String>> listaAgendaRoteirizacaoBanco) throws Exception {	
			
		AgendaRoteirizacao agendaRot = null;
		List<AgendaRoteirizacao> listaAgendaRoteirizacao = new ArrayList<AgendaRoteirizacao>();
		for (Map<String, String> agendaRoteirizacao : listaAgendaRoteirizacaoBanco) {
			agendaRot = new AgendaRoteirizacao();
			agendaRot.codigoCliente = Util.getInteger(agendaRoteirizacao.get("CODCLI"));
			agendaRot.codigoUnidadeEstrategia = Util.getInteger(agendaRoteirizacao.get("CODUNDESR"));
			agendaRot.codigoBairro = Util.getInteger(agendaRoteirizacao.get("CODBAI"));
			agendaRot.codigoFilialClienteExpedicao = Util.getInteger(agendaRoteirizacao.get("CODFILEPD"));
			agendaRot.codigoFilialClienteFaturamento = agendaRoteirizacao.get("CODFILFAT");
			agendaRot.horaSegurancaProposta = agendaRoteirizacao.get("HRASEGPRP");
			agendaRot.isCidadeExcessao = "S".equals(agendaRoteirizacao.get("CIDADEEXCESSAO"));
			agendaRot.diaSemana = Util.getInteger(agendaRoteirizacao.get("DIASMN"));
			agendaRot.hora = agendaRoteirizacao.get("HORA");
			agendaRot.envio = agendaRoteirizacao.get("ENVIO");
			agendaRot.dataRoteirizacao = DateUtil.formataData(agendaRoteirizacao.get("DATRTZ"), DateUtil.DEFAULT_DATE_DATABASE);
			agendaRot.horaRoteirizacao = agendaRoteirizacao.get("HRARTZ");
			agendaRot.quantidadeDiasPrevisaoEntrega = Util.getInteger(agendaRoteirizacao.get("QDEPRVENT"));
			agendaRot.quantidadeDiasSeguroPedido = Util.getInteger(agendaRoteirizacao.get("QDESEGPED"));
			agendaRot.entrega = calculaDataEntrega(agendaRot);
			agendaRot.descricaDiaSemana = obterDescricaoDiaSemana(agendaRot.diaSemana, agendaRot.dataRoteirizacao, agendaRot.isCidadeExcessao);
			listaAgendaRoteirizacao.add(agendaRot);
		}
		
		Collections.sort(listaAgendaRoteirizacao);
		return listaAgendaRoteirizacao;
	}

	/**
	 * Método que obtem a descricao do dia da semana
	 * @param diaSemana - inteiro contendo o parâmetro do dia da semana (0 a 7)
	 * @param dataRoteirizacao - parâmetro data de roteirização
	 * @param isCidadeExcessao - indicativo se a data de roteirização está preenchida do banco ou não
	 * @return String - descrição do dia da semana
	 */
	private static String obterDescricaoDiaSemana(Integer diaSemana, Date dataRoteirizacao, boolean isCidadeExcessao){
		if(isCidadeExcessao){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataRoteirizacao);
			diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		}
		return DateUtil.getDescricaoAbreviadaDiaSemana(diaSemana);
	}
	
	/**
	 * Método que calcula a data de entrega e preenche a data de roteirização caso não esteja preenchida
	 * @param agendaRot - informações de roteirização
	 * @return Date - data de entrega
	 */
	private static Date calculaDataEntrega(AgendaRoteirizacao agendaRot) {
			
		int iQuantDiasEntrega = 0, iQdePrz = 0, DiaSemana = agendaRot.diaSemana, QDEDIASEM = 7;
		double dHraAtu = 0.00, dHraRtz = 0.00, dHraSeg = 0.00;

		Calendar date4Hoje = Calendar.getInstance();
		Calendar horaAtual = Calendar.getInstance();
		Calendar dateRtz = Calendar.getInstance();

		iQdePrz = agendaRot.quantidadeDiasPrevisaoEntrega;

		dHraAtu = Double.parseDouble(horaAtual.get(Calendar.HOUR_OF_DAY) + "" + horaAtual.get(Calendar.MINUTE));
		dHraRtz = Double.parseDouble(agendaRot.horaRoteirizacao);
		dHraSeg = Double.parseDouble(agendaRot.horaSegurancaProposta);
		
		//Verifica se cidade é de excessão (Quinzenal) - DATRTZ com data preenchida
		if(agendaRot.isCidadeExcessao){
			
			dateRtz.setTime(agendaRot.dataRoteirizacao);
			
			int iDiasFaltamRtz = (int) DateUtil.diferencaEmDias(date4Hoje.getTime(), dateRtz.getTime());
			
			if (iDiasFaltamRtz > 0)	{
				
				iQuantDiasEntrega = iDiasFaltamRtz + iQdePrz;
				
			}else{
				
				if (iDiasFaltamRtz == 0) {
					if (dHraAtu < (dHraRtz - dHraSeg)) {
						// Roteiriza ainda nesta data
						iQuantDiasEntrega = iQdePrz;
					}else{
						// Não roteriza no dia
						return null;
					}
				}
				
			}

			date4Hoje.add(Calendar.DAY_OF_MONTH, iQuantDiasEntrega);
			
			return date4Hoje.getTime();
			
		} else {
			
			int iDiaDataHoje = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			int iDiasFaltamRtz = DiaSemana - iDiaDataHoje;

			if (iDiasFaltamRtz > 0)	{
				iQuantDiasEntrega = iDiasFaltamRtz + iQdePrz;
				dateRtz.add(Calendar.DAY_OF_MONTH, iDiasFaltamRtz);
			} else {

				if (iDiasFaltamRtz < 0)	{
					iDiasFaltamRtz = QDEDIASEM + iDiasFaltamRtz;
					iQuantDiasEntrega = iDiasFaltamRtz + iQdePrz;
					dateRtz.add(Calendar.DAY_OF_MONTH, iDiasFaltamRtz);
				} else {
					if (iDiasFaltamRtz == 0) {
						if(dHraAtu < (dHraRtz - dHraSeg)){
							// Roteiriza ainda nesta data
							iQuantDiasEntrega = iQdePrz;
						}else{
							// Roteiriza somente na próxima semana, após 7 dias
							iQuantDiasEntrega = iQdePrz + QDEDIASEM;
							
							dateRtz.add(Calendar.DAY_OF_MONTH, QDEDIASEM);
						}
					}
				}
			}

			date4Hoje.add(Calendar.DAY_OF_MONTH, iQuantDiasEntrega);
			agendaRot.dataRoteirizacao = dateRtz.getTime();
			
			return date4Hoje.getTime();
		}

	}

}