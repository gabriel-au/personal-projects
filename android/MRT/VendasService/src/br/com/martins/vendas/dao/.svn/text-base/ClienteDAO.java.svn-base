package br.com.martins.vendas.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.PontoMinimoPedidoSegmento;
import br.com.martins.vendas.vo.SituacaoCliente;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

/**
 * Classe responsavel por acesso a base de dados para recuperar e/ou inserir dados referentes aos clientes.
 */
public class ClienteDAO {
	
	private static final String TAG = ClienteDAO.class.getName();
	
	public static final String ATENDIMENTO_FORA_PRAZO = "0"; // Vermelho
	public static final String ATENDIMENTO_DENTRO_PRAZO = "1"; // Verde
	public static final String ATENDIMENTO_LIMITE_PRAZO = "2"; // Amarelo
	
	private static Database db;
	
	/**
	 * Método responsável por gerar a lista de clientes
	 * 
	 * @param codigoTerritorio - código do território selecionado
	 * @return objeto do tipo <code>java.util.List<Cliente></code>.
	 * @throws Exception
	 */
	public static List<Cliente> listarClientes(Integer codigoTerritorio) throws Exception {
		db = DatabaseFactory.getInstance();
		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		query.append("SELECT DISTINCT CLI.CODCLI, CLI.CODSGMCLI, CIDADE.TIPCOBCND, CLI.TIPCTTVDR, CLI.NOMCLI,CLI.CODCNL,CLI.DATRVSCAD, CLI.MEDDIASATD, CLI.MAXDIASATD, CLI.INDCLIFAV, ");
		query.append("CLI.INDCLITOP, CLI.NUMTLFCLI, CLI.PERDSCFLX, ATI.QDEMESRVS, MTV.DESMTVSIT, MTV.TIPANLCRD, ");
		query.append("CASE WHEN TRIM(CLI.NOMFNTCLI) = '' THEN CLI.NOMCLI ELSE CLI.NOMFNTCLI END AS NOMFNTCLI, CLT.CODTETVND, ");
		query.append("CASE WHEN (UPPER(MTV.TIPMTVSIT) <> 'S' AND UPPER(MTV.TIPMTVSIT) <> 'C') THEN 'N' ELSE UPPER(MTV.TIPMTVSIT) END AS TIPMTVSIT, ");
		query.append("PED.DATPED, CIDADE.NOMCID, CIDADE.CODESTUNI ");
		query.append("FROM PCACLI CLI ");
		query.append("LEFT JOIN PCABAI BAIRRO ON CLI.CODBAI = BAIRRO.CODBAI ");
		query.append("LEFT JOIN PCACID CIDADE ON BAIRRO.CODCID = CIDADE.CODCID ");
		query.append("LEFT JOIN PCAPED PED ON (CLI.CODCLI = PED.CODCLI AND PED.DATPED = (SELECT MAX(IPED.DATPED) FROM PCAPED IPED WHERE PED.CODCLI = IPED.CODCLI)) ");
		query.append("LEFT JOIN PCAATI ATI ON CLI.CODATI = ATI.CODATI ");
		query.append("LEFT JOIN PCACLT CLT ON CLI.CODCLI = CLT.CODCLI ");
		query.append("LEFT JOIN PCAMSC MSC ON CLI.CODCLI = MSC.CODCLI ");
		query.append("LEFT JOIN PCAMTV MTV ON MSC.CODMTVSIT = MTV.CODMTVSIT ");
		
		if(codigoTerritorio != null){
			query.append("WHERE CLT.CODTETVND = %s ");
		}
		
		query.append("ORDER BY CLI.CODCLI");
		
		String sql = DatabaseUtil.montaQuery(query.toString(), codigoTerritorio);
		try {
			List<Map<String, String>> result = db.executeQuery(sql);
			listaClientes = convertResultToListaClientes(result);
		} catch (SQLException e) {
			Log.e("ClienteDAO - listarClientes ", e.getMessage());
		}
		
		return listaClientes;
	}
	
	public static Cliente obterDetalheCliente(Integer codigoCliente) {
		return obterDetalheCliente(codigoCliente, null);
	}
	
	/**
	 * Obtem detalhes do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @return Cliente - informacoes do cliente
	 */
	public static Cliente obterDetalheCliente(Integer codigoCliente, Integer codigoTerritorioVenda) {
		db = DatabaseFactory.getInstance();

		StringBuilder query = new StringBuilder();
		query.append("SELECT CLIENTE.CODCLI, CLIENTE.CODCLFCLI, CLIENTE.CODBAI, CLIENTE.CODATI, CLIENTE.FLGCLICSM, CLIENTE.CODGRPCLI, CLIENTE.NOMCLI, CLIENTE.MAXDIASATD, CLIENTE.VLRLIMCRD, ");
		query.append("CASE WHEN TRIM(CLIENTE.NOMFNTCLI) = '' THEN CLIENTE.NOMCLI ELSE CLIENTE.NOMFNTCLI END AS NOMFNTCLI, ");
		query.append("CLIENTE.NUMTLFCLI, CLIENTE.EMLCLI, CLIENTE.DESTIPLGR, CLIENTE.DESLGR, CLIENTE.INDCLIFAV, CLIENTE.TIPCTTVDR, CLIENTE.PERDSCFLX, ");
		query.append("CLIENTE.CODCIDPCO,  ");
		query.append("CLIENTE.FLGAVRPSC,  ");
		query.append("BAIRRO.NOMBAI, ");
		query.append("CIDADE.CODCID, CIDADE.NOMCID, CIDADE.CODESTUNI, ");
		query.append("SEGMENTO.DESSGMCLI, ");
		query.append("TERRITORIO.CODTETVND, ");
		query.append("CIDADE.TIPCOBCND, ");
		query.append("CLIENTE.CODCNL, SEGMENTO.CODSGMCLI, CGR.INDRTCCND, CGR.FLGPCOESP, CLIENTE.FLGCLIESP, CLIENTE.TIPCOBBCO, CLIENTE.TIPLIMCRD, ");
		query.append("CASE WHEN UPPER(MOTIVO.TIPMTVSIT) = 'C' THEN 'Cortado' WHEN UPPER(MOTIVO.TIPMTVSIT) = 'S' THEN 'Suspenso' ELSE 'Normal' END AS TIPMTVSIT ");
		query.append("FROM PCACLI CLIENTE ");
		query.append("LEFT JOIN PCABAI BAIRRO ON CLIENTE.CODBAI = BAIRRO.CODBAI ");
		query.append("LEFT JOIN PCACID CIDADE ON BAIRRO.CODCID = CIDADE.CODCID ");
		query.append("LEFT JOIN PCASCL SEGMENTO ON CLIENTE.CODSGMCLI = SEGMENTO.CODSGMCLI ");
		query.append("LEFT JOIN PCACLT TERRITORIO ON CLIENTE.CODCLI = TERRITORIO.CODCLI ");
		query.append("LEFT JOIN PCAMSC MOVIMENTACAO ON CLIENTE.CODCLI = MOVIMENTACAO.CODCLI ");
		query.append("LEFT JOIN PCAMTV MOTIVO ON MOVIMENTACAO.CODMTVSIT = MOTIVO.CODMTVSIT ");
		query.append("LEFT JOIN PCACGR CGR ON CLIENTE.CODGRPCLI = CGR.CODGRPCLI ");
		query.append("WHERE CLIENTE.CODCLI = %s ");

		if(codigoTerritorioVenda != null){
			query.append(" and TERRITORIO.CODTETVND = %s ");
		}
		
		Cliente cliente = null;
		try {
			String sql = DatabaseUtil.montaQuery(query, codigoCliente, codigoTerritorioVenda);
			List<Map<String, String>> result = db.executeQuery(sql);
			
			cliente = convertResultToCliente(result);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return cliente;
	}
	
	/**
	 * Atualiza o status de favorecido do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @param indicativoFavorito - indicativo de atualizacao: S - cliente favorecido ou N - remover o status de favorecido
	 * @return boolean - atualizacao efetuada com sucesso ou falha
	 */
	public static boolean atualizarFavorito(Integer codigoCliente, String indicativoFavorito) {
		db = DatabaseFactory.getInstance();
		
		String query = createUpdateQuery(codigoCliente, indicativoFavorito);
		
		boolean sucesso = true;
		try {
			db.executeSQL(query);
		} catch (SQLException e) {
			sucesso = false;
		}

		return sucesso;
	}
	
	/**
	 * Recupera lista de clientes com informacoes sobre a situacao
	 * 
	 * @param codigoTerritorio - código do território
	 * @return List<SituacaoCliente> - lista de clientes
	 */
	public static List<SituacaoCliente> listarSituacaoClientes(Integer codigoTerritorio) {
		db = DatabaseFactory.getInstance();

		StringBuilder query = new StringBuilder();
		query.append(" select distinct CLIENTE.CODCLI, CLIENTE.NOMCLI, MOVIMENTACAO.DATALTSIT,MOVIMENTACAO.DESJSTSIT, OPCIONAL.CODFILEPD, ");
		query.append(" case when (upper(MOTIVO.TIPMTVSIT) <> 'S' and upper(MOTIVO.TIPMTVSIT) <> 'C') then 'N' else upper(MOTIVO.TIPMTVSIT) end as TIPMTVSIT, ");
		query.append(" case when upper(MOTIVO.TIPANLCRD) = 'L' then 'Liberado' when upper(MOTIVO.TIPANLCRD) = 'B' then 'Bloqueado' ELSE 'An�lise' end as SITUACAO_PEDIDO, ");
		query.append(" case when upper(MOTIVO.TIPMTVSIT) = 'C' then 'Cortado' when upper(MOTIVO.TIPMTVSIT) = 'S' then 'Suspenso' ELSE 'Normal' end as SITUACAO_CLIENTE ");
		query.append(" from PCACLI CLIENTE ");
		query.append(" inner join PCACOP OPCIONAL on CLIENTE.CODCLI = OPCIONAL.CODCLI ");
		query.append(" left join PCAMSC MOVIMENTACAO on CLIENTE.CODCLI = MOVIMENTACAO.CODCLI ");
		query.append(" left join PCAMTV MOTIVO on MOVIMENTACAO.CODMTVSIT = MOTIVO.CODMTVSIT ");
		
		if(codigoTerritorio != null){
			query.append(" LEFT JOIN PCACLT CLT ON CLIENTE.CODCLI = CLT.CODCLI ");
			query.append(" where CLT.CODTETVND = %s AND ");
		}else{
			query.append(" where ");
		}
		
		query.append(" OPCIONAL.CODFILEPD = (select COP.CODFILEPD from PCACOP COP where COP.CODCLI = CLIENTE.CODCLI order by COP.CODPRRFIL LIMIT 1) ");
		query.append(" order by CLIENTE.NOMCLI ");

		
		SituacaoCliente situacaoCliente = null;
		List<SituacaoCliente> listaSituacaoClientes = new ArrayList<SituacaoCliente>();
		
		List<Map<String, String>> result;
		try {
			result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoTerritorio));
			
			if (!result.isEmpty()) {
				for (Map<String, String> map : result) {
					situacaoCliente = new SituacaoCliente();
					situacaoCliente.codigoCliente = Util.getInteger(map.get("CODCLI"));
					situacaoCliente.nomeCliente = map.get("NOMCLI");
					situacaoCliente.dataSituacao = DateUtil.formataData(map.get("DATALTSIT"), DateUtil.DEFAULT_DATE_DATABASE);
					situacaoCliente.codigoFilialExpedicao = Util.getInteger(map.get("CODFILEPD"));
					situacaoCliente.tipoSituacaoCliente = map.get("TIPMTVSIT");
					situacaoCliente.situacaoPedido = map.get("SITUACAO_PEDIDO");
					situacaoCliente.situacaoCliente = map.get("SITUACAO_CLIENTE");
					situacaoCliente.descricaoJustificativa=map.get("DESJSTSIT");
					listaSituacaoClientes.add(situacaoCliente);
				}
			}
			
		} catch (SQLException e) {
			Log.e(TAG, "ClienteDAO::listarSituacaoClientes - Erro na execução da query.");
		} catch (ParseException e) {
			Log.e(TAG, "ClienteDAO::listarSituacaoClientes - Erro na formatação da data.");
		}

		return listaSituacaoClientes;
	}
	
	/**
	 * Recupera informacoes do relacionamento do cliente
	 * 
	 * @param codigoCliente
	 * @return Cliente - informacoes do cliente
	 */
	public static Cliente obterRelacionamentoCliente(Integer codigoCliente) {
		db = DatabaseFactory.getInstance();

		StringBuilder query = new StringBuilder();
		query.append(" select CLIENTE.CODCLI, CLIENTE.VLRLIMCRD, ");
		query.append(" case when (upper(CLIENTE.TIPLIMCRD) = 'N' or upper(CLIENTE.TIPLIMCRD) = 'E') then 'Nomeado' ");
		query.append(" when upper(CLIENTE.TIPLIMCRD) = 'C' then 'Histórico' ");
		query.append(" when upper(CLIENTE.TIPLIMCRD) = 'R' then 'Máximo' ");
		query.append(" when upper(CLIENTE.TIPLIMCRD) = 'G' then 'MaxiCompras' ");
		query.append(" when upper(CLIENTE.TIPLIMCRD) = 'V' then 'VIP' ");
		query.append(" when upper(CLIENTE.TIPLIMCRD) = 'T' then 'VIP/MaxiCompras' else 'Antecipa' end as TIPLIMCRD, ");
		query.append(" TITULO.VLRSLDABT ");
		query.append(" from PCACLI CLIENTE ");
		query.append(" left join PCATIT TITULO on CLIENTE.CODCLI = TITULO.CODCLI and TITULO.IDTTIPREG = (select min(TIT.IDTTIPREG) from PCATIT TIT where TITULO.CODCLI = TIT.CODCLI) ");
		query.append(" where CLIENTE.CODCLI = %s ");
		query.append(" limit 1");

		Cliente cliente = null;
		try {
			List<Map<String, String>> result = db.executeQuery(DatabaseUtil.montaQuery(query.toString(), codigoCliente));
			cliente = convertResultToCliente(result);
		} catch (SQLException e) {
			Log.e(TAG, "ClienteDAO::listarSituacaoClientes - Erro na formatação da data.");
		}
			
		return cliente;
	}
	
	/**
	 * Recupera o ponto minimo do cliente relacionado ao estado do cliente, codigo estrategico do
	 * cliente e codigo do segmento do cliente. O codigo estrategico do cliente e obtido atraves do  canal do mesmo.
	 * 
	 * @param estadoUF - estado do cliente
	 * @param canal - canal do cliente
	 * @param codigoSegmento - codigo de segmento do cliente
	 * @return instancia de PontoMinimoPedidoSegmento totalmente preenchido
	 */
	public static PontoMinimoPedidoSegmento recuperaPontoMinimoPedidoSegmento(String estadoUF, Integer canal, Integer codigoSegmento) {
		db = DatabaseFactory.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select CODESTDSN, CODUNDESR, CODSGMCLI, VLRLMTPED, VLRPTOFLX ");
		sql.append(" from PCAFPE ");
		sql.append(" where CODESTDSN = '%s' ");
		sql.append(" and CODUNDESR = (select CODUNDESR from PCACNL where CODCNL = %s) ");
		sql.append(" and CODSGMCLI =  %s ");

		List<Map<String, String>> result;
		PontoMinimoPedidoSegmento pmps = new PontoMinimoPedidoSegmento();

		try {
			String query = DatabaseUtil.montaQuery(sql.toString(), estadoUF, canal, codigoSegmento);
			result = db.executeQuery(query);

			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				pmps.siglaEstadoDestino = map.get("CODESTDSN");
				pmps.codigoUnidadeEstrategia = Util.getInteger(map.get("CODUNDESR"));
				pmps.codigoSegmentoCliente = Util.getInteger(map.get("CODSGMCLI"));
				pmps.valorLancamentoPedido = Util.getInteger(map.get("VLRLMTPED"));
				pmps.valorPontoFlexivel = Util.getInteger(map.get("VLRPTOFLX"));
			}
			
		} catch (SQLException sqlException) {
			Log.e(TAG, "ClienteDAO::recuperaPontoMinimoPedidoSegmento - Erro na execução da query.");
		}

		return pmps;
	}
	
	/**
	 * Método responsável por converter o resultado proveniente da execução de
	 * comandos na base de dados, que se trata de um objeto do tipo
	 * <code>java.util.List<Map<String, String>></code>, para um objeto do tipo
	 * <code>br.com.martins.vendas.vo.Cliente</code>.
	 * 
	 * @param result
	 *            resultado proveniente da execução de comandos na base de
	 *            dados, objeto do tipo
	 *            <code>java.util.List<Map<String, String>></code>.
	 * @return objeto do tipo <code>br.com.martins.vendas.vo.Cliente</code>.
	 */
	private static Cliente convertResultToCliente(List<Map<String, String>> result) {
		Cliente cliente = null;
		
		if (!result.isEmpty()) {
			
			Map<String, String> map = result.get(0);
			
			cliente = new Cliente();
			cliente.codigoCliente=Util.getInteger(map.get("CODCLI"));
			cliente.codigoClassificacao = map.get("CODCLFCLI");
			cliente.codigoAtividade=Util.getInteger(map.get("CODATI"));
			cliente.codigoGrupoCliente=Util.getInteger(map.get("CODGRPCLI"));
			cliente.maximoDiasSemAtendimento = Util.getInteger(map.get("MAXDIASATD"));
			cliente.telefone = map.get("NUMTLFCLI");
			cliente.codigoTerritorio = Util.getInteger(map.get("CODTETVND"));
			cliente.email = map.get("EMLCLI");
			cliente.tipoLogradouro = map.get("DESTIPLGR");
			cliente.descricaoLogradouro = result.get(0).get("DESLGR");
			cliente.nomeBairro = map.get("NOMBAI");
			cliente.codigoCidade = Util.getInteger(map.get("CODCID"));
			cliente.nomeCidade = map.get("NOMCID");
			cliente.nomeCliente = map.get("NOMCLI");
			cliente.nomeFantasia = map.get("NOMFNTCLI");
			cliente.uf = map.get("CODESTUNI");
			cliente.descricaoSegmento = map.get("DESSGMCLI");
			cliente.situacao = map.get("TIPMTVSIT");
			cliente.indicativoFavorito = map.get("INDCLIFAV");
			cliente.valorLimiteCredito = Util.getBigDecimal(map.get("VLRLIMCRD"));
			cliente.tipoCobrancaCondicaoPagamento = Util.getInteger(map.get("TIPCOBCND"));
			cliente.canal = Util.getInteger(map.get("CODCNL"));
			cliente.tipoContratoVendor = map.get("TIPCTTVDR");
			cliente.codigoSegmento = map.get("CODSGMCLI");
			cliente.restricaoCondicaoPagamento = map.get("INDRTCCND");
			cliente.flagClienteEspecial = map.get("FLGCLIESP");
			cliente.tipoCobrancaBanco = Util.getInteger(map.get("TIPCOBBCO"));
			cliente.tipoLimiteCredito = map.get("TIPLIMCRD");
			cliente.flagPrecoEspecial = map.get("FLGPCOESP");
			cliente.flagAlvaraPsicotropico = map.get("FLGAVRPSC");
			cliente.codigoCidadePreco = Util.getInteger(map.get("CODCIDPCO"));
			cliente.flagClienteConsumidor = map.get("FLGCLICSM");
			cliente.percentualDescontoFlexivel = null == map.get("PERDSCFLX") ? BigDecimal.ZERO : Util.getBigDecimal(map.get("PERDSCFLX"));
			
		}
		
		return cliente;
	}

	/**
	 * Método responsável por converter o resultado proveniente da execução de
	 * comandos na base de dados, que se trata de um objeto do tipo
	 * <code>java.util.List<Map<String, String>></code>, para um objeto do tipo
	 * <code>java.util.List<Cliente></code>.
	 * 
	 * @param result
	 *            resultado proveniente da execução de comandos na base de
	 *            dados, objeto do tipo <code>java.util.List<Map<String, String>></code>.
	 * @return objeto do tipo <code>java.util.List<Cliente></code>.
	 * @throws Exception 
	 */
	private static List<Cliente> convertResultToListaClientes(List<Map<String, String>> result) throws Exception {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente cliente = null;

		if (!result.isEmpty()) {
			for (Map<String, String> map : result) {
				
				// TODO Verificar todos os outros dados relacionados a tabela cliente para ser inserido abaixo.
				
				cliente = new Cliente();
				cliente.codigoCliente = Util.getInteger(map.get("CODCLI"));
				cliente.nomeCliente = map.get("NOMCLI");
				cliente.mediaDiasSemAtendimento = Util.getInteger(map.get("MEDDIASATD"));
				cliente.maximoDiasSemAtendimento = Util.getInteger(map.get("MAXDIASATD"));
				cliente.indicativoFavorito = map.get("INDCLIFAV");
				cliente.indicativoTop = map.get("INDCLITOP");
				cliente.nomeFantasia = map.get("NOMFNTCLI");
				cliente.situacao = map.get("TIPMTVSIT");
				cliente.canal=Util.getInteger(map.get("CODCNL"));
				cliente.dataRevisaoCadastro=DateUtil.formataData(map.get("DATRVSCAD"), DateUtil.DEFAULT_DATE_DATABASE);
				cliente.qtdMesRevisao=Util.getInteger(map.get("QDEMESRVS"));
				cliente.descMotivoSituacao=map.get("DESMTVSIT");
				cliente.telefone = map.get("NUMTLFCLI");
				cliente.codigoTerritorio = Util.getInteger(map.get("CODTETVND"));
				cliente.dataUltimoPedido = DateUtil.formataData(map.get("DATPED"), DateUtil.DEFAULT_DATE_DATABASE);
				cliente.tipoContratoVendor = map.get("TIPCTTVDR");
				cliente.tipoCobrancaCondicaoPagamento = Util.getInteger(map.get("TIPCOBCND"));
				cliente.codigoSegmento = map.get("CODSGMCLI");
				cliente.statusAtendimento = verificaStatusAtendimento(cliente.dataUltimoPedido, cliente.maximoDiasSemAtendimento, cliente.mediaDiasSemAtendimento);
				cliente.tipoAnaliseCredito = map.get("TIPANLCRD");
				cliente.nomeCidade = map.get("NOMCID");
				cliente.uf = map.get("CODESTUNI");
				questionaRevisaoCadastral(cliente);
				listaClientes.add(cliente);
			}
		}

		return listaClientes;
	}
	
	/**
	 * Método que realiza o questionamento sobre revisão cadastral
	 * @param cliente - objeto do tipo cliente com suas informações
	 */
	private static void questionaRevisaoCadastral(Cliente cliente){
		Calendar hoje = Calendar.getInstance();
		Calendar dataRevisao = Calendar.getInstance();
		// Quantidade de milissegundos em um dia
		Integer tempoDia = 1000 * 60 * 60 * 24;
		dataRevisao.setTime(cliente.dataRevisaoCadastro);
	    Long dias = hoje.getTimeInMillis() - dataRevisao.getTimeInMillis();
	    cliente.diasCorridos = (int) (dias/tempoDia);
		gerarMensagens(cliente);
	}
	
	/**
	 * Método que cria as mensagens de revisão cadastral para serem exibidas em tela
	 * @param cliente - objeto do tipo cliente com suas informações
	 */
	private static void gerarMensagens(Cliente cliente){
		cliente.bloqueado=false;
		//StringBuilder mensagem = new StringBuilder();
		Integer diasRevisao= cliente.qtdMesRevisao*30;
		
		if((cliente.diasCorridos > diasRevisao) && (cliente.qtdMesRevisao!=0) && (cliente.dataRevisaoCadastro!=null)){
			cliente.mensagem="0";
			cliente.bloqueado=true;
	    	//mensagem.append("O prazo para a revisão cadastral deste cliente foi esgotado !!!")
	    	//.append("Para fazer o desbloqueio, basta revisar o cadastro do cliente !!!" + cliente.descMotivoSituacao);	    	
	    	
		}else{
	    	if((cliente.diasCorridos > (diasRevisao - 30)) && (cliente.qtdMesRevisao != 0 ) && (cliente.dataRevisaoCadastro!=null)){
	    		Integer diferenca = diasRevisao - cliente.diasCorridos ;
	    		if(diferenca.compareTo(0)==0){
	    			cliente.mensagem="1";
	    			//mensagem.append("Hoje é o último dia para a revisão cadastral deste cliente!!!")
			    	//.append("A partir de amanhã, os novos pedidos para ele serão bloqueados!!!" + cliente.descMotivoSituacao);
	    		}else{
	    			cliente.mensagem="2";
	    			cliente.diferencaDias=diferenca;
	    			//mensagem.append("O prazo para a revisão cadastral deste cliente é de " +  diferenca + " dia(s)!!! ")
			    	//.append("Após este prazo, os novos pedidos para ele serão bloqueados!!! " + cliente.descMotivoSituacao);
	    		}
	    	}
	    }
	    //cliente.mensagem=mensagem.toString();
	}
	
	/**
	 * Método que identifica qual o status do atendimento do cliente
	 * 
	 * @param dataPedido - data do último pedido efetuado
	 * @param maxDiasSemAtendimento - parâmetro do máximo de dias sem atendimento ao cliente
	 * @param medDiasSemAtendimento - parâmetro de média de dias sem atendimento ao cliente
	 * @return String - o status do atendimento (0 - atendimento fora do prazo, 1 - atendimento dentro do prazo e 2 - atendimento no limite do prazo
	 */
	public static String verificaStatusAtendimento(Date dataUltimoPedido, Integer maximoDiasSemAtendimento, Integer mediaDiasSemAtendimento) {
			
		//Verifica se o cliente tem data do último pedido
		if (dataUltimoPedido != null) {
			
			Calendar dataHoje = Calendar.getInstance();
			
			Calendar dataPedido = Calendar.getInstance();
			dataPedido.setTime(dataUltimoPedido);
			
			int medDiasSemAtendimento = (mediaDiasSemAtendimento != null) ? mediaDiasSemAtendimento : 0;
			int maxDiasSemAtendimento = (maximoDiasSemAtendimento != null) ? maximoDiasSemAtendimento : 0;
			
			long diferencaDias = DateUtil.diferencaEmDias(dataPedido.getTime(), dataHoje.getTime());
			
			if (0 < medDiasSemAtendimento && 0 < maxDiasSemAtendimento) {
				
				//if ((long) date4Hoje >= (long) date4Ped) {
				if (dataHoje.compareTo(dataPedido) != -1) {
					
					//lDifDatPed = (long) date4Hoje - (long) date4Ped;
					
					if (diferencaDias < medDiasSemAtendimento) {
						return ATENDIMENTO_DENTRO_PRAZO;
						//m_bCorVerde = TRUE;
				
					} else if (diferencaDias >= medDiasSemAtendimento && diferencaDias < maxDiasSemAtendimento) {
						return ATENDIMENTO_LIMITE_PRAZO;
							//m_bCorAmarelo = TRUE;
					}
				}
				//Date4 date4Posivacao;
				//date4Posivacao = date4Ped + (long) iMaxDiaSemAtd;
			} else {
				//Modo antigo de colorir a lista de clientes
				//if ((long) date4Hoje >= (long) date4Ped) {
				if (dataHoje.compareTo(dataPedido) != -1) {
					
					int mesHoje = dataHoje.get(Calendar.MONTH);
					int mesPedido = dataPedido.get(Calendar.MONTH);
					
					//if (((long) date4Hoje - (long) date4Ped) < 7) {
					// Quantidade de dias na semana = 7
					if (diferencaDias < 7) {
						
						if (dataHoje.get(Calendar.DAY_OF_WEEK) >= dataPedido.get(Calendar.DAY_OF_WEEK)) {
							return ATENDIMENTO_DENTRO_PRAZO;
							//	m_bCorAmarelo = FALSE;
						} else {
							
							if (mesHoje == mesPedido) {
								return ATENDIMENTO_LIMITE_PRAZO;
							//	m_bCorAmarelo = TRUE;
							}
						}
						
					} else {
						if (mesHoje == mesPedido) {
							return ATENDIMENTO_LIMITE_PRAZO;
							//m_bCorAmarelo = TRUE;
						}
					}
				}
			}
		}
		
		return ATENDIMENTO_FORA_PRAZO;
	}
	
	/**
	 * Método responsável por criar a query de update para Cliente.
	 * 
	 * @param codigoCliente
	 *            código do cliente.
	 * @param indicativoFavorito
	 *            indicativo do cliente para identificá-lo como favorito.
	 * @return query criada para a execução de um update na tabela de clientes.
	 */
	private static String createUpdateQuery(Integer codigoCliente, String indicativoFavorito) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE PCACLI SET ");
		if (indicativoFavorito.equalsIgnoreCase("S")) {
			query.append("INDCLIFAV = 1 ");
		} else {
			query.append("INDCLIFAV = '' ");
		}
		query.append("WHERE CODCLI = " + codigoCliente);
		return query.toString();
	}
	
	public static Cliente obtemDadosClienteAlteraPedido(Integer codigoCliente) {
		db = DatabaseFactory.getInstance();
		
		Cliente cliente = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" CLI.FLGCLICSM, CLI.CODATI, CLI.CODCLI, CLI.CODGRPCLI, CID.CODESTUNI, CGR.FLGPCOESP, ");
		sql.append(" CPC.NUMRLCCID, CLT.CODTETVND, AGE.CODESTDSN, CLI.CODCNL, CLI.TIPLIMCRD, CLI.PERDSCFLX ");
		sql.append("FROM PCACLI CLI ");
		sql.append("LEFT JOIN PCACID CID ");
		sql.append("ON CLI.CODCEP=CID.CODCEP ");
		sql.append("LEFT JOIN PCACGR CGR ");
		sql.append("ON CLI.CODGRPCLI=CGR.CODGRPCLI ");
		sql.append("LEFT JOIN PCACPC CPC ");
		sql.append("ON CPC.CODCIDPCO=CID.CODCID ");
		sql.append("LEFT JOIN PCACLT CLT ");
		sql.append("ON CLI.CODCLI=CLT.CODCLI ");
		sql.append("LEFT JOIN PCAAGE AGE ");
		sql.append("ON CPC.NUMRLCCID=AGE.NUMRLCCID ");
		sql.append("WHERE CLI.CODCLI=%s");

		try {
			String query = DatabaseUtil.montaQuery(sql.toString(), codigoCliente);
			List<Map<String, String>> result = db.executeQuery(query);

			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				cliente = new Cliente();
				cliente.flagClienteConsumidor = map.get("FLGCLICSM");
				cliente.codigoAtividade = Util.getInteger(map.get("CODATI"));
				cliente.codigoCliente = Util.getInteger(map.get("CODCLI"));
				cliente.uf = map.get("CODESTUNI");
				cliente.flagPrecoEspecial = map.get("FLGPCOESP");
				cliente.numeroRelacionamentoCliente = Util.getInteger(map.get("NUMRLCCID"));
				cliente.codigoTerritorio = Util.getInteger(map.get("CODTETVND"));
				cliente.codigoEstadoDestino = map.get("CODESTDSN");
				cliente.canal = Util.getInteger(map.get("CODCNL"));
				cliente.tipoLimiteCredito = map.get("TIPLIMCRD");
				cliente.codigoGrupoCliente = Util.getInteger(map.get("CODGRPCLI"));
				cliente.percentualDescontoFlexivel = Util.getBigDecimal(map.get("PERDSCFLX"));
			}
			
		} catch (SQLException sqlException) {
			Log.e(TAG, "ClienteDAO::obterPontoMinimoPedido - Erro na execução da query.");
		}

		return cliente;
	}
	
	public static boolean validaTipoContratoFilial(Integer codigoCliente, Integer codigoFilialExpedicao, Integer tipoContratoCondicao) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT 1 FROM PCACTT WHERE ");
		query.append(" CODCLI = %s ");
		query.append(" CODFILEPD = %s ");
		query.append(" TIPCTTCLI = %s ");

		db = DatabaseFactory.getInstance();
		String sql = DatabaseUtil.montaQuery(query.toString(), codigoCliente, codigoFilialExpedicao, tipoContratoCondicao);
		
		boolean resultado = false;
		try {
			List<Map<String, String>> result = db.executeQuery(sql);
			if (!result.isEmpty()) {
				resultado = true;
			}
		} catch (SQLException e) {
			Log.e("ClienteDAO - listarClientes ", e.getMessage());
		}
		
		return resultado;
	}

}