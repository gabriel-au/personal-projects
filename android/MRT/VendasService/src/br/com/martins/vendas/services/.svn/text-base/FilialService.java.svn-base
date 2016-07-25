package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Filial;
import br.com.martins.vendas.vo.FilialCliente;
import br.com.martins.vendas.vo.ItemFiltro;
import br.com.martins.vendas.vo.ParametroMinimoFilial;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class FilialService {

	private static final String TAG = FilialService.class.getName();

	private static Database db;
	
	private static final String CCLI = "CCLI";
	private static final String NFAT = "NFAT";
	private static final String CFAT = "CFAT";
	private static final String NEPD = "NEPD";
	private static final String CEPD = "CEPD";
	private static final String ICXA = "ICXA";
	private static final String IND_EPL = "IND_EPL";

	/**
	 * Recupera relacao Filial de Expedicao / Filial de Faturamento do Cliente. <br />
	 * <strong> OBS: Caso exista a relacao na tabela PCACOP (CAD's Opcionais do Cliente), por�m a filial de faturamento n�o esteja associada ao territorio na tabela PCAAGE (Agenda do Livro de Pre�os) a relacao Filial de Expedicao / Filial de Faturamento
	 * deve ser desconsiderada. </strong>
	 * 
	 * @param clientId codigo identificador do cliente
	 * 
	 * @param territorioId codigo identificador do territorio
	 * 
	 * @return [null] em caso de erro
	 */
	public static List<FilialCliente> obterFiliaisPorCliente(Integer clientId, Integer territoryId) {
		if (clientId == null || territoryId == null) {
			throw new IllegalArgumentException("parameters cannot be null");
		}

		StringBuilder tmp = new StringBuilder();
		tmp.
		append(" SELECT COP.CODCLI AS CCLI, ")
		.append("       FILE.CODFILEPD AS CEPD, FILE.NOMFILEPD AS NEPD, FILE.INDVNDDTO AS IND_EPL, ")
		.append("       FATU.CODFILEPD AS CFAT, FATU.NOMFILEPD AS NFAT, ")
		.append("       COP.INDVNDCXA AS ICXA  ")
		.append("  FROM  PCACOP COP ")
		.append(" INNER JOIN PCAFIL FILE ON FILE.CODFILEPD = COP.CODFILEPD ")
		.append(" INNER JOIN PCAFIL FATU ON FATU.CODFILEPD = COP.CODFILFAT ")
		.append(" WHERE CODCLI = %s ")
		.append(" ORDER BY CODPRRFIL ");

		try {
			String query = DatabaseUtil.montaQuery(tmp, clientId);
			db = DatabaseFactory.getInstance();
			
			/*
			 * recupar filiais associadas ao cliente
			 */
			List<Map<String, String>> lstFiliais = db.executeQuery(query);
			for (int count = 0; count < lstFiliais.size(); count++) {
				Map<String, String> map = lstFiliais.get(count);

				/*
				 * validar se filial de faturamento pertence a agenda de roterizacao
				 */
				if (!validarAssociacaoTerritorioFilialFaturamento(territoryId, Util.getInteger(map.get(CFAT)))) {
					lstFiliais.remove(map);
				}
			}

			return converterParaListaVO(lstFiliais);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Verifica se existe uma associacao de territorio com a filial de faturamento na agenda de roterizacao
	 * 
	 * @param terriorioId corresponde ao codigo do territorio
	 * 
	 * @param filialFaturamentoId corresponde ao codigo da filial
	 * 
	 * @return true caso exista uma associacao false caso contr�rio
	 * 
	 * @throws SQLException em caso de falha ao executar o SQL
	 */
	public static boolean validarAssociacaoTerritorioFilialFaturamento(Integer terriorioId, Integer filialFaturamentoId) {
		String tmp = "SELECT COUNT(*) QTD FROM PCAAGE AGE WHERE AGE.CODGIR = %s AND AGE.CODFILFAT = %s";
		boolean resultado = false;
		
		try {
			String query = DatabaseUtil.montaQuery(tmp, terriorioId, filialFaturamentoId);
			db = DatabaseFactory.getInstance();
			
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				String quantidade = result.get(0).get("QTD");
				resultado = Util.getInteger(quantidade) > 0;
			}			
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return resultado;
	}

	/**
	 * Convert List[Map[String, String]] em List[FilialCliente]
	 * 
	 * @param list List[Map[String, String]]
	 * 
	 * @return ArrayList[FilialCliente]
	 * @see br.com.martins.vendas.vo.FilialCliente
	 */
	private static List<FilialCliente> converterParaListaVO(List<Map<String, String>> list) {

		List<FilialCliente> returnList = new ArrayList<FilialCliente>();
		if (list != null && !list.isEmpty()) {

			for (int count = 0; count < list.size(); count++) {
				Map<String, String> map = list.get(count);
				//
				FilialCliente filialCliente = new FilialCliente();

				Cliente cliente = new Cliente();

				cliente.codigoCliente = Integer.parseInt(map.get(CCLI));

				Filial filialExpedicao = new Filial();
				filialExpedicao.codigoFilial = Util.getInteger(map.get(CEPD));
				filialExpedicao.nomeFilial = map.get(NEPD);
				filialExpedicao.indicativoVendaDireta = Util.getInteger(map.get(IND_EPL));

				Filial filialFaturamento = new Filial();
				filialFaturamento.codigoFilial = Util.getInteger(map.get(CFAT));
				filialFaturamento.nomeFilial = map.get(NFAT);

				filialCliente.cliente = cliente;
				filialCliente.filialExpedicao = filialExpedicao;
				filialCliente.filialFaturamento = filialFaturamento;
				
				filialCliente.indicadorVendaCaixa = map.get(ICXA).equals("*");
				
				returnList.add(filialCliente);
			}

		}
		return returnList;
	}

	/**
	 * 
	 * @param expeditionBranchId
	 * @param customerState
	 * @param customerChannel
	 * @return
	 */
	public static ParametroMinimoFilial obterParametroMinimoFilialPorFilial(Integer codigoFilialExpedicao, String codigoEstadoUniao, Integer codigoCanal) {
//		if (codigoFilialExpedicao == null || uf == null || canal == null) {
//			throw new IllegalArgumentException("");
//		}

		StringBuilder tmp = new StringBuilder();
		tmp.append(" SELECT CODFILEPD, CODESTUNI, CODCNL, VLRMNMEPD, VLRMNMBLT ");
		tmp.append(" FROM PCAPMN ");
		tmp.append(" WHERE CODFILEPD = %s ");
		tmp.append("   AND CODESTUNI = '%s' ");
		tmp.append("   AND CODCNL = %s ");

		try {
			String query = DatabaseUtil.montaQuery(tmp, codigoFilialExpedicao, codigoEstadoUniao, codigoCanal);
			db = DatabaseFactory.getInstance();
			
			Map<String, String> result = db.executeQuery(query).get(0);
			ParametroMinimoFilial pmf = new ParametroMinimoFilial();

			pmf.codigoFilial = Util.getInteger(result.get("CODFILEPD"));
			pmf.siglaEstado = result.get("CODESTUNI");
			pmf.canal = Util.getInteger(result.get("CODCNL"));
			pmf.valorMinimoExpedicao = Util.getBigDecimal(result.get("VLRMNMEPD"));
			pmf.valorMinimoBoletoBancario = Util.getBigDecimal(result.get("VLRMNMBLT"));

			return pmf;

		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
	/**
	 * Verifica se a Filials é somente venda
	 *  
	 * @param codFilialExpedicao código filial expedição do item (mercadoria)
	 * 
	 * @param codFilialFaturamento código filial faturamento do item (mercadoria)
	 * 
	 * @param codCliente código do cliente
	 * 
	 * @param terCliente código do território do cliente
	 * 
	 * @return <code>true</code> caso o par Filial Expedição / Filial Faturamento seja somente venda <code>false</code> caso contrário.
	 */
	public static boolean verificaFilialSomenteVenda(Integer codFilialExpedicao, Integer codFilialFaturamento, Integer codCliente, Integer terCliente) {
		List<FilialCliente> list = FilialService.obterFiliaisPorCliente(codCliente, terCliente);
		boolean indicador = false;
		
		for (int count = 0; count < list.size(); count++) {
			FilialCliente fc = list.get(0);		
			if (fc.filialExpedicao.codigoFilial.equals(codFilialExpedicao) && fc.filialFaturamento.codigoFilial.equals(codFilialFaturamento)) {
				indicador = fc.indicadorVendaCaixa;
			}
		}
		
		return indicador;
	}
	
	public static String obtemNomeFilialFaturamento(Integer codigoFilial){
		String registro=null;
		StringBuilder sql = new StringBuilder();
		sql.append("select nomfilepd from pcafil where codfilepd=%s "); 
		
		try {
			String query = DatabaseUtil.montaQuery(sql, codigoFilial);
			db = DatabaseFactory.getInstance();
			
			Map<String, String>result = db.executeQuery(query).get(0);
		    registro = result.get("NOMFILEPD");
		} catch (SQLException e) {
			Log.e("PedidoService - obtemNomeFilialFaturamento - ", e.getMessage());
		}
		
		return registro;
	}
	
	/**
	 * 
	 * @param codigoCliente
	 * @param codigoFilialExpedicao
	 * @param codigoFilialFaturamento
	 * @param codRelacionamentoCliente
	 * @param codigoGrupamentoCliente
	 * @param flagAlvaraPsicotropico
	 * @param codigoRepresentante
	 * @param codigoCidade
	 * @param codigoAtividade
	 * @param codigoSupervisor
	 * @param codigoEstadoDestino
	 * @param codigoCondicaoPagamento
	 * @param itemFiltro
	 * @return
	 */
	public static Item verificaItemPorFilial(
				Integer codigoCliente,
				Integer codigoFilialExpedicao,
				Integer codigoFilialFaturamento,
				Integer codRelacionamentoCliente,
				Integer codigoGrupamentoCliente,
				String  flagAlvaraPsicotropico,
				Integer codigoRepresentante,
				Integer codigoCidade,
				Integer codigoAtividade,
				Integer codigoSupervisor,
				String  codigoEstadoDestino,
				Integer codigoCondicaoPagamento,
				ItemFiltro itemFiltro) {
		
		List<Item> itens = ItensDisponiveisServiceAux.listaItensDisponiveis(
				codigoCliente,
				codigoFilialExpedicao,
				codigoFilialFaturamento, 
				codRelacionamentoCliente, 
				codigoGrupamentoCliente,
				flagAlvaraPsicotropico,
				codigoRepresentante, 
				codigoCidade,
				codigoAtividade,
				codigoSupervisor, 
				codigoEstadoDestino,
				codigoCondicaoPagamento,
				itemFiltro);
		
		if (!itens.isEmpty()) {
			
			Item item = itens.get(0);
			if (CondicaoPagamentoService.verificaSeCondicaoDePagamentoValida(
				codigoCondicaoPagamento,
				item.mercadoria)) {
			
				return item;
			}
		}
		
		return null;
	}
	
	public static Item verificaItemPromocaoPorFilial(
			boolean ignoraBloqueioItemSimiliar, 
			Integer codigoCliente,
			Integer codigoFilialExpedicao,
			Integer codigoFilialFaturamento,
			Integer codigoRelacionamentoCliente,
			Integer codigoGrupamentoCliente, 
			String  flagAlvaraClientePsicotropico,
			Integer codigoRepresentante, 
			Integer codigoCidade,
			Integer codigoAtividade, 
			Integer codigoSupervisor, 
			String  codigoEstadoDestino,
			Integer codigoCondicaoPagamento,
			boolean isMercadoriaExclusiva,
			String  palavraMeio,
			String  codigoMercadoria,
			Integer numeroListaCustomizada, 
			ItemFiltro itemFiltro,
			Integer codigoPromocao,
			String origem) {
		
		List<Item> itens = null;
		
		if("ITEMPROMOCAO".equalsIgnoreCase(origem)){
			itens = PromocaoService.listaItensDisponiveisDaPromocao(codigoCliente, codigoFilialExpedicao, codigoFilialFaturamento, codigoRelacionamentoCliente, codigoGrupamentoCliente, flagAlvaraClientePsicotropico, codigoRepresentante, codigoCidade, codigoAtividade, codigoSupervisor, codigoEstadoDestino, codigoCondicaoPagamento, isMercadoriaExclusiva, palavraMeio, codigoMercadoria, numeroListaCustomizada, itemFiltro, codigoPromocao);
		}else if("ITEMPROMOCAOPREMIO".equalsIgnoreCase(origem)){
			itens = PromocaoService.listarPremiosDisponiveisDaPromocao(codigoCliente, codigoFilialExpedicao, codigoFilialFaturamento, codigoRelacionamentoCliente, codigoGrupamentoCliente, flagAlvaraClientePsicotropico, codigoRepresentante, codigoCidade, codigoAtividade, codigoSupervisor, codigoEstadoDestino, codigoCondicaoPagamento, isMercadoriaExclusiva, palavraMeio, codigoMercadoria, numeroListaCustomizada, itemFiltro, codigoPromocao);
		}
		
	
	if (!itens.isEmpty()) {
		Item item = itens.get(0);
		if (CondicaoPagamentoService.verificaSeCondicaoDePagamentoValida(
			codigoCondicaoPagamento,
			item.mercadoria)) {
			return item;
		}
	}
	
	return null;
}
	
}