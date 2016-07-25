package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.AvisosCliente;
import br.com.martins.vendas.vo.AvisosPedido;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;

public class AvisosService {

	//TODO - jgomes verificar utilidade do método
	public static List<AvisosPedido> findAvisosPedidos() throws Exception {
		return carregaAvisosPedido();
	}

	public static List<AvisosCliente> findAvisosClientes(Integer codigoTerritorio) throws Exception {
		List<AvisosCliente> listaAvisosCliente = new ArrayList<AvisosCliente>();
		listaAvisosCliente.addAll(carregaAvisosRevisaoCadastral(codigoTerritorio));
		listaAvisosCliente.addAll(carregaAvisosClientesSuspensos(codigoTerritorio));
		listaAvisosCliente.addAll(carregaAvisosClientesPFSemLimCredito(codigoTerritorio));
		Collections.sort(listaAvisosCliente);
		return listaAvisosCliente;	
	}
	
	private static List<AvisosPedido> carregaAvisosPedido() throws SQLException {
		List<AvisosPedido> listaAvisos = new ArrayList<AvisosPedido>();
		AvisosPedido ap = new AvisosPedido();
		ap.codigoCliente  = "123";
		ap.nomeCliente  = "TESTE AVISO PEDIDO";
		ap.mensagemAviso = "TESTE MENSAGEM AVISO PEDIDO";
		ap.dataAviso = new Date();
		listaAvisos.add(ap);
		return listaAvisos;
	}
	
	private static List<AvisosCliente> carregaAvisosClientesPFSemLimCredito(Integer codigoTerritorio) throws SQLException {
		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT CLI.CODCLI, CLI.VLRLIMCRD, CLI.NOMCLI, CLI.CODATI, ATI.NOMATI ");
		query.append(" FROM PCACLI CLI ");
		query.append(" JOIN PCACLT CLT ON CLI.CODCLI = CLT.CODCLI ");
		query.append(" JOIN PCAATI ATI ON CLI.CODATI = ATI.CODATI ");
		query.append(" WHERE CLI.TIPSITJUR = 'F' ");
		query.append(" AND CLI.VLRLIMCRD = 0 ");
		query.append(" AND CLI.CODATI IN(8,10,99,50) ");
			
		if(codigoTerritorio != null){
			query.append(" AND CLT.CODTETVND = %s ");
		}
		
		query.append(" ORDER BY CLI.NOMCLI ");
		
		List<Map<String, String>> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoTerritorio));
		
		return criaListaAvisosClientesPFSemLimCredito(result);		
	}

	private static List<AvisosCliente> carregaAvisosClientesSuspensos(Integer codigoTerritorio) throws Exception {
			
		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT CLI.CODCLI, CLI.NOMCLI, MTV.DESMTVSIT")
		.append(" FROM PCACLI CLI")
		.append(" JOIN PCACLT CLT ON CLI.CODCLI = CLT.CODCLI")
		.append(" JOIN PCAMSC MSC ON CLI.CODCLI = MSC.CODCLI")
		.append(" JOIN PCAMTV MTV ON MSC.CODMTVSIT = MTV.CODMTVSIT")
		.append(" where MTV.TIPMTVSIT <> 'C'")
		.append(" AND MTV.TIPANLCRD = 'B'");
		
		if(codigoTerritorio != null){
			query.append(" AND CLT.CODTETVND = %s ");
		}
		
		query.append(" ORDER BY CLI.NOMCLI ");
		
		List<Map<String, String>> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoTerritorio));
		
		return criaListaAvisosClienteSuspenso(result);
	}

	private static List<AvisosCliente> carregaAvisosRevisaoCadastral(Integer codigoTerritorio) throws Exception {				
		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT DISTINCT CLI.CODCLI, CLI.DATRVSCAD, CLI.NOMCLI, CLI.DESMTVRVS, ATI.QDEMESRVS,")
		.append(" (julianday(date(substr(CLI.DATRVSCAD,1,4) || '-' || substr(CLI.DATRVSCAD,5,2) || '-' || substr(CLI.DATRVSCAD,7,2),")
		.append(" '+' || (ATI.QDEMESRVS * 30) ||' day')) ")
		.append(" - julianday(date('now'))) as 'DIASRESTANTES'")
		.append(" FROM PCACLT CLT")
		.append(" JOIN PCACLI CLI ON CLT.CODCLI = CLI.CODCLI")
		.append(" JOIN PCAATI ATI ON CLI.CODATI = ATI.CODATI")
		.append(" WHERE")
		.append(" ATI.QDEMESRVS is not null")
		.append(" AND ATI.QDEMESRVS <> ''")
		.append(" AND CLI.DATRVSCAD is not null")   
		.append(" AND CLI.DATRVSCAD <> ''")
		.append(" AND (julianday(date(substr(CLI.DATRVSCAD,1,4) || '-' || substr(CLI.DATRVSCAD,5,2) || '-' || substr(CLI.DATRVSCAD,7,2),")
		.append(" '+' || (ATI.QDEMESRVS * 30) ||' day'))")
		.append(" - julianday(date('now'))) < 30 ");
		
		if(codigoTerritorio != null){
			query.append(" AND CLT.CODTETVND = %s ");
		}
		
		query.append(" ORDER BY CLI.NOMCLI ");
		
		List<Map<String, String>> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoTerritorio));
		return criaListaAvisosRevisaoCadatralCliente(result);
	}
	
	private static List<AvisosCliente> criaListaAvisosClientesPFSemLimCredito(
		List<Map<String, String>> avisosClientes) {

		AvisosCliente avisosCliente;
		List<AvisosCliente> listaAvisosCliente = new ArrayList<AvisosCliente>();
		
		for(Map<String, String> avisoCli : avisosClientes){
			avisosCliente = new AvisosCliente();
			avisosCliente.codigoCliente = avisoCli.get("CODCLI");
			avisosCliente.nomeCliente = avisoCli.get("NOMCLI");
			avisosCliente.valorLimiteCredito = Double.valueOf(avisoCli.get("VLRLIMCRD"));
			avisosCliente.nomeAtividade = avisoCli.get("NOMATI");
			criaMensagemClientesPFSemLimCredito(avisosCliente);
			avisosCliente.dataAviso = new Date();
			listaAvisosCliente.add(avisosCliente);
		}
		return listaAvisosCliente;
	}
	
	private static List<AvisosCliente> criaListaAvisosClienteSuspenso(List<Map<String, String>> avisosClientes) throws Exception {	
		AvisosCliente avisosCliente;
		List<AvisosCliente> listaAvisosCliente = new ArrayList<AvisosCliente>();
		
		for(Map<String, String> avisoCli : avisosClientes){
			avisosCliente = new AvisosCliente();
			avisosCliente.codigoCliente = avisoCli.get("CODCLI");
			avisosCliente.nomeCliente = avisoCli.get("NOMCLI");
			avisosCliente.descricaoMotivoRevisao = avisoCli.get("DESMTVSIT");
			criaMensagemClienteSuspenso(avisosCliente);
			avisosCliente.dataAviso = new Date();
			listaAvisosCliente.add(avisosCliente);
		}
		
		return listaAvisosCliente;
	}

	private static List<AvisosCliente> criaListaAvisosRevisaoCadatralCliente(List<Map<String, String>> avisosClientes) throws Exception {			
		AvisosCliente avisosCliente;
		List<AvisosCliente> listaAvisosCliente = new ArrayList<AvisosCliente>();
		for(Map<String, String> avisoCli : avisosClientes){
			avisosCliente = new AvisosCliente();
			avisosCliente.codigoCliente = avisoCli.get("CODCLI");
			avisosCliente.nomeCliente = avisoCli.get("NOMCLI");
			avisosCliente.dataRevisaoCadastro = DateUtil.formataData(avisoCli.get("DATRVSCAD"), "yyyyMMdd");
			avisosCliente.descricaoMotivoRevisao = avisoCli.get("DESMTVRVS");
			avisosCliente.quantidadeMesRevisao = Integer.valueOf(avisoCli.get("QDEMESRVS"));
			avisosCliente.diasRestantes = Double.valueOf(avisoCli.get("DIASRESTANTES")).intValue();
			criaMensagemAvisoRevisaoCadastral(avisosCliente);
			avisosCliente.dataAviso = new Date();
			listaAvisosCliente.add(avisosCliente);
		}
		return listaAvisosCliente;
	}

	private static void criaMensagemAvisoRevisaoCadastral(AvisosCliente avisoCliente) {
		avisoCliente.tituloAviso = avisoCliente.nomeCliente;
		StringBuilder sb = new StringBuilder();
		if (avisoCliente.diasRestantes < 0){
			sb.append("O prazo para a revisão cadastral foi esgotado !!!\nOs novos pedidos estão bloqueados. \nPara fazer o desbloqueio, basta revisar o cadastro do cliente !!!");
		}else if(avisoCliente.diasRestantes == 0){
			sb.append("Hoje é o último dia para a revisão cadastral. \nA partir de amanhã, os novos pedidos para ele serão bloqueados !!!");
		}else{
			sb.append("O prazo para a revisão cadastral é de " + avisoCliente.diasRestantes + " dia(s). \nApós este prazo, os novos pedidos para ele serão bloqueados !!!");
		}
		sb.append(avisoCliente.descricaoMotivoRevisao);
		avisoCliente.conteudoAviso = sb.toString();
	}
	
	private static void criaMensagemClienteSuspenso(AvisosCliente avisoCliente) {
		avisoCliente.tituloAviso = avisoCliente.nomeCliente;
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("O cliente está com o crédito bloqueado no ")
		.append("sistema. Os pedidos enviados estarão sujeitos à reprovação ")
		.append("automática.\n\nMotivo do bloqueio: ")
		.append(avisoCliente.descricaoMotivoRevisao);
		avisoCliente.conteudoAviso = mensagem.toString();
	}
	
	private static void criaMensagemClientesPFSemLimCredito(AvisosCliente avisoCliente) {	
		
		avisoCliente.tituloAviso = avisoCliente.nomeCliente;
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("O cliente Pessoa fisica, atividade " + avisoCliente.nomeAtividade)
		.append(",\n e sem limite de crédito, poderá ter seu pedido cortado!\n")
		.append("\nFavor utilizar o Efacil para passagem de pedido!");
		avisoCliente.conteudoAviso = mensagem.toString();
	}
	
}
