package br.com.martins.vendas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.RetornosCliente;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class RetornosClienteService {

	public static List<RetornosCliente> obterRetornosCliente(Integer numeroPedido) throws Exception {
		Database db = DatabaseFactory.getInstance();
		
		StringBuilder query = new StringBuilder();
		query.append("select CLI.CODCLI,CLI.NOMCLI,PED.NUMPED,SIT.DATOCOANL,SIT.HRAOCOANL,SIT.DESOBSANL,SIT.DESSITPED, ")
		.append("SIT.CODSITPED FROM PCACLI CLI ")
		.append("inner join PCAPED PED " )
		.append("on CLI.CODCLI = PED.CODCLI ")
		.append("inner join PCASIT SIT ")
		.append("on SIT.NUMPED=PED.NUMPED ")
		.append("where ped.numped=" + numeroPedido);
		
		List<Map<String, String>> result = db.executeQuery(query.toString());
		
		return criaListaRetornos(result);
	}
	
	private static List<RetornosCliente> criaListaRetornos(List<Map<String, String>> listaRetornos) throws Exception {
			
		Integer codigoSituacaoPedido=null;
		List<RetornosCliente> lista= new ArrayList<RetornosCliente>();
		RetornosCliente retorno; 
		Cliente cliente = new Cliente();
		
		for(Map<String, String> r : listaRetornos){
			retorno = new RetornosCliente();
			cliente.codigoCliente=Util.getInteger(r.get("CODCLI"));
			cliente.nomeCliente=r.get("NOMCLI");
			retorno.numPedido=Util.getInteger(r.get("NUMPED"));
			retorno.dataOcorrencia=DateUtil.formataData(r.get("DATOCOANL"), "yyyyMMdd");
			retorno.horaOcorrencia=r.get("HRAOCOANL");
			retorno.descObservacao=r.get("DESOBSANL");
			retorno.descricaoSituacaoPedido=r.get("DESSITPED");
			codigoSituacaoPedido=Util.getInteger(r.get("CODSITPED"));
			retorno.cliente=cliente;
			retorno.codigoSituacaoPedido=verificarSituacaoPedido(codigoSituacaoPedido);
			lista.add(retorno);
		}
		
		return lista;
	}
	
	public static String verificarSituacaoPedido(Integer codigoSituacaoPedido){
		String situacao="";
		
		if(codigoSituacaoPedido.compareTo(0)==0){			
			situacao="OK";
		}else if(codigoSituacaoPedido.compareTo(1)==0){
			situacao="PND";
		}else if(codigoSituacaoPedido.compareTo(2)==0){
			situacao="ANL";
		}else if(codigoSituacaoPedido.compareTo(3)==0 || codigoSituacaoPedido.compareTo(4)==0){
			situacao="REP";
		}else{
			situacao="????";
		}	
		return situacao;
	}
	
}
