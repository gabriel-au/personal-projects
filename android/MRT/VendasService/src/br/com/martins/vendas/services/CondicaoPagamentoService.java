package br.com.martins.vendas.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.CalculoPrecoDAO;
import br.com.martins.vendas.dao.CondicaoPagamentoDAO;
import br.com.martins.vendas.enums.TipoCliente;
import br.com.martins.vendas.enums.TipoCobranca;
import br.com.martins.vendas.enums.TipoCondicaoPagamento;
import br.com.martins.vendas.enums.TipoFinanciamento;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class CondicaoPagamentoService {

	private static final String TAG = CondicaoPagamento.class.getName();
	
	private static Database db;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public static CondicaoPagamento buscaCondicaoPagamento(Mercadoria mercadoriaAux, Integer codigoCondicaoPagamento) throws Exception{
		Mercadoria mercadoria;
		if(mercadoriaAux.codigoMercadoriaPrincipal != null && mercadoriaAux.codigoMercadoriaPrincipal != 0){
			mercadoria = MercadoriaService.buscaMercadoria(mercadoriaAux.codigoMercadoriaPrincipal);
		}else{
			mercadoria = mercadoriaAux;
		}
		return CondicaoPagamentoService.buscaCondicaoPagamentoPorTipoMercadoria(codigoCondicaoPagamento, mercadoria.tipo);
	}
	
//	public List<CondicaoPagamento> listarCondicaoPagamento() {
//		StringBuilder query = new StringBuilder();
//		query.append("SELECT CODCNDPGT,NUMPCLCND, QDEDIAPRZ,NUMPODPCL,VLRLIMMNM,DESOBSCND FROM PCACND;");
//		return null;
//	}
	
	public static List<CondicaoPagamento> obterDadosCondicaoPagamento(
			final Integer codigoFilialExpedicao, 
			final String codigoEstadoUnidadeFederativa, 
			final Integer codigoCanal,
			final Integer codigoAtividade, 
			Integer codigoGrupoCliente, 
			final Integer numeroParcelas, 
			final Integer prazo,
			final Integer periodicidade, 
			final String restricaoCondicaoPagamento, 
			final String flagClienteEspecial,
			final String tipoContratoVendor, 
			final Integer tipoCobrancaBanco, 
			final String tipoLimiteCredito, 
			Boolean isFinanciamentoVendor, 
			Boolean isCobrancaBancaria, 
			Boolean isVendaCash) {
		return obterDadosCondicaoPagamento(codigoFilialExpedicao, codigoEstadoUnidadeFederativa, codigoCanal, codigoAtividade, codigoGrupoCliente, numeroParcelas, prazo, periodicidade, restricaoCondicaoPagamento, flagClienteEspecial, tipoContratoVendor, tipoCobrancaBanco, tipoLimiteCredito, isFinanciamentoVendor, isCobrancaBancaria, isVendaCash, null);
		
	}
	
	/**
	 * 
	 * Este m�todo verifica se a condicao de pagamento valida para o cliente.
	 * Para esta verificacao necessario que o registro esteja posicionado
	 * corretamente
	 * 
	 * @return TRUE -> A condicao de pagamento � valida FALSE -> A condicao de pagamento valida
	 */
	public static List<CondicaoPagamento> obterDadosCondicaoPagamento(
			final Integer codigoFilialExpedicao, 
			final String codigoEstadoUnidadeFederativa, final Integer codigoCanal,
			final Integer codigoAtividade, Integer codigoGrupoCliente, 
			final Integer numeroParcelas, 
			final Integer prazo,
			final Integer periodicidade, 
			final String restricaoCondicaoPagamento, 
			final String flagClienteEspecial,
			final String tipoContratoVendor, 
			final Integer tipoCobrancaBanco, 
			final String tipoLimiteCredito, 
			Boolean isFinanciamentoVendor, 
			Boolean isCobrancaBancaria, 
			Boolean isVendaCash,
			Integer codigoCondicaoPagamento) {
			
		StringBuffer query = new StringBuffer();
		db = DatabaseFactory.getInstance();
		List<CondicaoPagamento> listaCondicaoPagamento = new ArrayList<CondicaoPagamento>();
		
		query.append(" SELECT DISTINCT CODCNDPGT, CODATI, CODGRPCLI, FTRCNDPGT, QDEDIAPRZ, NUMPCLCND, NUMPODPCL, VLRLIMMNM, TIPFNMCND, TIPCOBCND, TIPCTTCND, FLGCNDESP, DESOBSCND, INDVDRPMC")
		.append(" FROM PCACND CND")
		.append(" WHERE")
		.append(" CND.CODFILEPD = " + codigoFilialExpedicao)
		.append(" AND CND.CODESTUNI IN ('99','" + codigoEstadoUnidadeFederativa + "')") 
		.append(" AND CND.CODCNL IN (9999, "+codigoCanal+")")
		.append(" AND CND.CODATI IN (9999, "+codigoAtividade+")")
		.append(" AND CND.CODGRPCLI IN (9999, "+codigoGrupoCliente+")")
		
		.append(" AND CND.CODCNDPGT NOT IN ("+TipoCondicaoPagamento.BRINDES_PROMOCOES.codigoCondicaoPagamento + "," +
				TipoCondicaoPagamento.BRINDES_DESCONTO_FLEXIVEL.codigoCondicaoPagamento + "," +
				TipoCondicaoPagamento.BRINDES_BENEFICIO_CUSTOMIZADO.codigoCondicaoPagamento +")") 
				
		.append(" AND (")	
		
		.append(" (CND.TIPCOBCND = " + TipoCobranca.ANTECIPADO.tipoCobranca+")")
		
		.append(" OR (")

		.append(" (strftime('%Y%m%d','now') BETWEEN DATINIVLD AND DATFIMVLD)")
		//Clientes cadastrados com TIPCTTVDR = ' ' (sem contrato vendor) não trabalham com financiamento 'Vendor' ou 'Tribanco'
		.append(" AND (")
		.append(" (CND.TIPFNMCND <> " + TipoFinanciamento.VENDOR.tipoFinanciamento + ")")
		.append(" OR ")
		.append(" (CND.TIPFNMCND = " + TipoFinanciamento.VENDOR.tipoFinanciamento + " and '"+tipoContratoVendor+"' <> '')")
		//e clientes cadastrados com TIPCTTVDR = '1' (carência do contrato vendor) não trabalham com financiamento 'Tribanco'
		.append(" OR ")
		.append(" (CND.TIPFNMCND = " + TipoFinanciamento.VENDOR.tipoFinanciamento + " and '"+tipoContratoVendor+"' not in ('',1))") 
		.append(" ) ")
		
		//Clientes cadastrados com TIPCOBBCO igual a '1' ou '3' trabalham somente com tipo de cobrança 'cobrança bancária' ou 'ordem de pagamento'
		.append("AND ")  
		.append("( ")
		.append(" 	("+tipoCobrancaBanco+" NOT IN ("+TipoCobranca.CHEQUE.tipoCobranca+","+ TipoCobranca.DUPLICATA.tipoCobranca + ")) ")  
		.append("	OR ")
		.append("	("+tipoCobrancaBanco+" IN ("+TipoCobranca.CHEQUE.tipoCobranca +","+ TipoCobranca.DUPLICATA.tipoCobranca + ") AND CND.TipCobCnd NOT IN ("+TipoCobranca.BANCARIA.tipoCobranca+","+ TipoCobranca.ORDEMPAGAMENTO.tipoCobranca + ")) ")
		.append(") ")
		
		//Clientes cadastrados com FLGCLIESP = '*' (Nota Cheia) possuem condições especiais e não
		//trabalham com financiamento 'Vendor'. Além disso, clientes cadastrados com TIPCOBBCO
		//igual a '2' não trabalham com tipo de cobrança 'cobrança bancária'
		.append(" AND (")
		.append(" ('"+flagClienteEspecial+"' = '*' AND CND.TipFnmCnd NOT IN ("+TipoFinanciamento.VENDOR.tipoFinanciamento+","+ TipoFinanciamento.TRIBANCO.tipoFinanciamento + ")) ")
		.append(" OR (CND.TipCobCnd != "+TipoCobranca.BANCARIA.tipoCobranca+" ) ")
		.append(" OR ('"+flagClienteEspecial+"' = CND.FlgCndEsp) ")
		.append(" ) ")
		
		.append(" AND( ")
		//Clientes cadastrados com determinados tipos de limite de crédito (Tribanco) não trabalham com financiamento próprio (Martins)
		.append(" 	(CND.TipFnmCnd <> " + TipoFinanciamento.PROPRIO.tipoFinanciamento +" ) ")
		.append(" OR ")
		.append(" 	(CND.TipFnmCnd = 1 AND '"+tipoLimiteCredito+"' NOT IN ('"+	TipoCliente.CONTAGARANTIDA.idTipoCliente
				 											+ "','" +	TipoCliente.TRIBANCO .idTipoCliente
				 											+ "','" +	TipoCliente.VIP.idTipoCliente
				 											+ "','" +	TipoCliente.ANALISE.idTipoCliente + "')")
		.append(" 	) ")
		.append(" ) ")
		
		//Determinados grupamentos de clientes exigem condições de pagamento exclusivas
		.append(" AND ( ")
		.append(" ("+restricaoCondicaoPagamento+" = 0) ")
		.append(" OR ")
		//9999 = TODOSGRUPAMENTOS
		.append(" ("+restricaoCondicaoPagamento+" = 1 AND "+codigoGrupoCliente+" <> '9999') ")
		.append(" ) ")
		
		.append(" AND ")
		.append(" ( ")
		.append(" (CND.TipFnmCnd <> " + TipoFinanciamento.VENDOR.tipoFinanciamento + ") ")
		.append(" or ")
		.append(" (CND.TipFnmCnd = " + TipoFinanciamento.VENDOR.tipoFinanciamento)
		//INDCNDVDRPMC 1 - Indica que a condição é vendor promocional
		.append(" AND (CND.Indvdrpmc == 1 OR '"+tipoLimiteCredito+"' IN ('"+	TipoCliente.CONTAGARANTIDA.idTipoCliente
				 											+ "','" +	TipoCliente.TRIBANCO.idTipoCliente 
				 											+ "','" +	TipoCliente.VIP.idTipoCliente
				 											+ "','" +	TipoCliente.ANALISE.idTipoCliente + "')) ")
		.append(" 	) ")
		.append(" ) ")
		.append(" ) ")
		.append(")");	
		
		//FILTROS
		if(codigoCondicaoPagamento != null){
			query.append(" AND CODCNDPGT = " + codigoCondicaoPagamento);
		}
		
		if(numeroParcelas != 0){
			query.append(" AND NUMPCLCND = " + numeroParcelas);
		}
		
		if(prazo != 0){
			query.append(" AND QDEDIAPRZ = " + prazo);
		}
		
		if(periodicidade != 0){
			query.append(" AND NUMPODPCL = " + periodicidade);
		}
		
		//QUANDO CASH = ANTECIPADO, IGNORA AS OUTRAS FORMAS DE FILTRO, ACORDO UGCctrlPsqCndPgt, metodo CUGCtrlPsqCndPgt::AdicionaDados
		if(isVendaCash != null && isVendaCash){
			query.append(" AND CND.TIPCOBCND = " + TipoCobranca.ANTECIPADO.tipoCobranca);
		}else{
			if(isCobrancaBancaria != null && isCobrancaBancaria){
				query.append(" AND CND.TIPCOBCND = " + TipoCobranca.BANCARIA.tipoCobranca);
			}
			
			if(isFinanciamentoVendor != null){
				if(isFinanciamentoVendor){
					query.append(" AND TIPFNMCND = " + TipoFinanciamento.VENDOR.tipoFinanciamento);
				}else{
					query.append(" AND TIPFNMCND = " + TipoFinanciamento.PROPRIO.tipoFinanciamento);
				}
			}
			
			if(isCobrancaBancaria != null || isFinanciamentoVendor != null){
				query.append(" AND CND.TIPCOBCND <> " + TipoCobranca.ANTECIPADO.tipoCobranca);
				query.append(" ORDER BY NUMPCLCND , QDEDIAPRZ");
			}

		}

		try {
			List<Map<String, String>> result = db.executeQuery(query.toString());
			listaCondicaoPagamento = carregarListaCondicaoPagamento(result);
		} catch (Exception e) {
			Log.e(TAG,e.getMessage());
		}
		
		return listaCondicaoPagamento;
	}
	
	private static List<CondicaoPagamento> carregarListaCondicaoPagamento(List<Map<String,String>> resultQuery){
				
		CondicaoPagamento condicaoPagamentoCliente;
		List<CondicaoPagamento> listaCondicaoPagamento = new ArrayList<CondicaoPagamento>();
		try {
			for (Map<String,String> condicaoPagamento : resultQuery) {
				condicaoPagamentoCliente = new CondicaoPagamento();
				condicaoPagamentoCliente.codigoAtividade = Util.getInteger(condicaoPagamento.get("CODATI"));
				condicaoPagamentoCliente.codigoCondicaoPagamento = Util.getInteger(condicaoPagamento.get("CODCNDPGT"));
				condicaoPagamentoCliente.codigoCanal = condicaoPagamento.get("CODCNL");
				condicaoPagamentoCliente.codigoEstadoUniao = condicaoPagamento.get("CODESTUNI");
				condicaoPagamentoCliente.codigoFilialExpedicao = Util.getInteger(condicaoPagamento.get("CODFILEPD"));
				condicaoPagamentoCliente.codigoGrupoCliente = Util.getInteger(condicaoPagamento.get("CODGRPCLI"));
				condicaoPagamentoCliente.dataFimValida = DateUtil.formataData(condicaoPagamento.get("DATFIMVLD"), DateUtil.DEFAULT_DATE_DATABASE);
				condicaoPagamentoCliente.dataInicioValida = DateUtil.formataData(condicaoPagamento.get("DATINIVLD"), DateUtil.DEFAULT_DATE_DATABASE);
				condicaoPagamentoCliente.descricaoObservacaoCondicao = condicaoPagamento.get("DESOBSCND");
				condicaoPagamentoCliente.flagCondicaoEspecial = Util.getInteger(condicaoPagamento.get("FLGCNDESP"));
				condicaoPagamentoCliente.fatorCondicaoPagamento = Util.getBigDecimal(condicaoPagamento.get("FTRCNDPGT"));
				condicaoPagamentoCliente.fatorEncargoVendor = Util.getBigDecimal(condicaoPagamento.get("FTRENCVDR"));
				condicaoPagamentoCliente.indicaCondicaoTribanco = Util.getInteger(condicaoPagamento.get("INDCNDTBO"));
				condicaoPagamentoCliente.indicaVendorPromocao = Util.getInteger(condicaoPagamento.get("INDVDRPMC"));
				condicaoPagamentoCliente.numeroParcelasCondicao = Util.getInteger(condicaoPagamento.get("NUMPCLCND"));
				condicaoPagamentoCliente.periodicidade = Util.getInteger(condicaoPagamento.get("NUMPODPCL"));
				condicaoPagamentoCliente.percentualPrimeiroParcial = Util.getBigDecimal(condicaoPagamento.get("PERPRIPCL"));
				condicaoPagamentoCliente.quantidadeDiaPrazo = Util.getInteger(condicaoPagamento.get("QDEDIAPRZ"));
				condicaoPagamentoCliente.tipoCobrancaCondicao = Util.getInteger(condicaoPagamento.get("TIPCOBCND"));
				condicaoPagamentoCliente.tipoContratoCondicao = Util.getInteger(condicaoPagamento.get("TIPCTTCND"));
				condicaoPagamentoCliente.tipoFinanciamento = Util.getInteger(condicaoPagamento.get("TIPFNMCND"));
				condicaoPagamentoCliente.tipoPrazoCondicao = Util.getInteger(condicaoPagamento.get("TIPPRZCND"));
				condicaoPagamentoCliente.valorLimiteMinimo = Util.getBigDecimal(condicaoPagamento.get("VLRLIMMNM"));
				condicaoPagamentoCliente.descricaoCondicao = montaDescricaoCondicao(condicaoPagamentoCliente);
				
				listaCondicaoPagamento.add(condicaoPagamentoCliente);
				/*if(validarCondicaoPagamento(cliente)){
					listaCondicaoPagamento.add(cliente);
				}*/
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCondicaoPagamento;
	}
	
	private static String montaDescricaoCondicao(
			CondicaoPagamento condicaoPagamentoCliente) {
		StringBuilder sb = new StringBuilder("");
		sb.append(montaParcelasCondicao(condicaoPagamentoCliente))
		.append(" dia(s) / ")
		.append(" Financ. ")
		.append(TipoFinanciamento.toString(condicaoPagamentoCliente.tipoFinanciamento) + " / ")
		.append(TipoCobranca.toString(condicaoPagamentoCliente.tipoCobrancaCondicao));
		return sb.toString();
	}

	public static CondicaoPagamento buscaCondicaoPagamento(Integer codigoCondicaoPagamento) {
		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT CODATI, CODCNDPGT, CODCNL, CODESTUNI, CODFILEPD, CODGRPCLI, ");
		sql.append(" DATFIMVLD, DATINIVLD, DESOBSCND, FLGCNDESP, FTRCNDPGT, FTRENCVDR, ");
		sql.append(" INDCNDTBO, INDVDRPMC, NUMPCLCND, NUMPODPCL, PERPRIPCL, QDEDIAPRZ, ");
		sql.append(" TIPCOBCND, TIPCTTCND, TIPFNMCND, TIPPRZCND, VLRLIMMNM ");
		sql.append(" FROM PCACND ");
		sql.append(" WHERE CODCNDPGT = ?");
		
		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();
		
		CondicaoPagamento condicaoPagamento = new CondicaoPagamento();
		try {
			List<Map<String,String>> resultQuery = db.executeQuery(query, Util.getStringValue(codigoCondicaoPagamento));
			if(!resultQuery.isEmpty()){
				condicaoPagamento = carregarListaCondicaoPagamento(resultQuery).get(0);
			}
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage());
		}
		return condicaoPagamento;
	}
	
	public static CondicaoPagamento buscaCondicaoPagamentoPorTipoMercadoria(Integer codigoCondicaoPagamento, String codigoTipoMercadoria) throws SQLException {
		StringBuilder query = new StringBuilder("");
		query.append(" select CND.* ")
		.append(" from pcacpt CPT")
		.append(" join pcacnd CND ON CPT.CODCNDPGT = CND.CODCNDPGT")
		.append(" where CPT.codcndpgt = " + codigoCondicaoPagamento)
		.append(" and CPT.tipmer = '" + codigoTipoMercadoria + "'");
		db = DatabaseFactory.getInstance();
		List<Map<String, String>> result = db.executeQuery(query.toString());
		return carregarListaCondicaoPagamento(result).get(0);
	}
	
	private static String montaParcelasCondicao(CondicaoPagamento condicaoPagamentoCliente) {
		StringBuilder sb = new StringBuilder("");
		int dias = 30;
		if(condicaoPagamentoCliente.numeroParcelasCondicao > 1){
			for(int i =1; i <= condicaoPagamentoCliente.numeroParcelasCondicao; i++){
				if(!"".equals(sb.toString())){
					sb.append("/");
				}
				sb.append(dias * i);
			}
		}else{
			sb.append(condicaoPagamentoCliente.quantidadeDiaPrazo);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param codigoCondicaoPagamento
	 * @param mercadoria
	 */
	public static boolean  verificaSeCondicaoDePagamentoValida(Integer codigoCondicaoPagamento, Mercadoria mercadoria){
		return CondicaoPagamentoDAO.verificaSeCondicaoDePagamentoValida(codigoCondicaoPagamento, mercadoria);
	}
	
	/**
	 * Verifica se o codigo da condicao de pagamento � para brindes integrais de promocao
	 * @param codigoCondicaoPagamento
	 * @return
	 */
	public static boolean isBrindeIntegralPromocao(Integer codigoCondicaoPagamento){
		return (TipoCondicaoPagamento.BRINDES_PROMOCOES.codigoCondicaoPagamento == codigoCondicaoPagamento);
	}
	
	
	/**
	 * Checks if is permitido somente tipo cobranca bancaria.
	 *
	 * @param tipoCobrancaCondicaoPagamento the tipo cobranca condicao pagamento
	 * @return true, if is permitido somente tipo cobranca bancaria
	 */
	public static boolean isPermitidoSomenteTipoCobrancaBancaria(
			Integer tipoCobrancaCondicaoPagamento) {
		if(tipoCobrancaCondicaoPagamento == 1 || tipoCobrancaCondicaoPagamento == 2){
			if (TipoCobranca.BANCARIA.equals(tipoCobrancaCondicaoPagamento)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Valida se o tipo de conbranca é tarifada (Bancária ou Cash)
	 * @param tipoCobrancaCondicaoPagamento
	 * @return boolean - true em caso afirmativou ou false 
	 */
	public static boolean isCondicaoTarifada(Integer tipoCobrancaCondicaoPagamento) {
		if(TipoCobranca.BANCARIA.tipoCobranca.equals(tipoCobrancaCondicaoPagamento) 
				|| TipoCobranca.CASH.tipoCobranca.equals(tipoCobrancaCondicaoPagamento)){
			return true;
		}
		return false;
	}

	/**
	 * Checks if is permitido somente financiamento proprio.
	 *
	 * @param tipoContratoVendor the tipo contrato vendor
	 * @return true, if is permitido somente financiamento proprio
	 */
	public static boolean isPermitidoSomenteFinanciamentoProprio(
			String tipoContratoVendor) {
		if(tipoContratoVendor == null || "".equals(tipoContratoVendor)){
			return true;
		}
		return false;
	}
	
	/**
	 * Obtem o fator de pagamento da condicao. Dependendo se houver Utilizacao de FatorEncargoVendor este podera ser utilizado,
	 * senao, ser� utilizado o fatorCondicaoPagamento da condicao.
	 * @param condicaoPagamento
	 * @param mercadoria
	 * @param cliente
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @return
	 */
	public static BigDecimal obtemFatorCondicaoPagamento(CondicaoPagamento condicaoPagamento,
			Mercadoria mercadoria, Cliente cliente, String siglaEstadoOrigem, String siglaEstadoDestino){
		
		BigDecimal fator = BigDecimal.ONE;
		
		if(condicaoPagamento.tipoFinanciamento.equals(TipoFinanciamento.VENDOR.getValue()) ||
				condicaoPagamento.tipoFinanciamento.equals(TipoFinanciamento.TRIBANCO.getValue())){
			boolean isFatorVendor = isUtilizadoFatorVendor(condicaoPagamento, mercadoria, cliente, siglaEstadoOrigem, siglaEstadoDestino);
			if(isFatorVendor){
				fator = condicaoPagamento.fatorEncargoVendor;
			}else {
				fator = condicaoPagamento.fatorCondicaoPagamento;
			}
		}
		
		return fator;
	}
	
	/**
	 * 
	 * @param condicaoPagamento
	 * @param mercadoria
	 * @param cliente
	 * @param siglaEstadoOrigem
	 * @param siglaEstadoDestino
	 * @return
	 */
	public static boolean isUtilizadoFatorVendor(CondicaoPagamento condicaoPagamento, 
			Mercadoria mercadoria, Cliente cliente, String siglaEstadoOrigem, String siglaEstadoDestino){
		boolean isValido = true;
		
		if (!(condicaoPagamento.indicaCondicaoTribanco == 1) && !(ClienteService.isClienteTribanco(cliente))){
			BigDecimal minimoEstadual = CalculoPrecoDAO.obtemValorMinimoEstadual(mercadoria, siglaEstadoOrigem, siglaEstadoDestino);
			if(minimoEstadual == null){
				if(CalculoPrecoDAO.obtemValorMinimoNacional(mercadoria) != null){
					isValido = false;
				}
			}
		}
		
		return isValido;
	}
	
	
	/**
	 * Verifica se � brindeIntegral
	 * @param tipoNegociacaoMercadoria
	 * @param codigoCondicaoPagamento
	 * @param percentualValorBonificacao
	 * @param valorBonificacaoMercadoria
	 * @return
	 */
	public static boolean isBrindeIntegral(Integer tipoNegociacaoMercadoria, 
			Integer codigoCondicaoPagamento, BigDecimal percentualValorBonificacao, 
			BigDecimal valorBonificacaoMercadoria){	
		return (isBrindeIntegralPromocao(codigoCondicaoPagamento) 
				|| ((tipoNegociacaoMercadoria == 2) || (tipoNegociacaoMercadoria == 3)) 
				&& percentualValorBonificacao.doubleValue() == 0 
				&& 0 != valorBonificacaoMercadoria.doubleValue());
	}
	
	/**
	 * Obter dados condicao pagamento.
	 *
	 * @param codigoCliente the codigo cliente
	 * @param codigoFilialExpedicao the codigo filial expedicao
	 * @param codigoEstadoUnidadeFederativa the codigo estado unidade federativa
	 * @param codigoCanal the codigo canal
	 * @param codigoAtividade the codigo atividade
	 * @param codigoGrupoCliente the codigo grupo cliente
	 * @param numeroParcelas the numero parcelas
	 * @param prazo the prazo
	 * @param periodicidade the periodicidade
	 * @param isFinanciamentoVendor the is financiamento vendor
	 * @param isCobrancaBancaria the is cobranca bancaria
	 * @param isVendaCash the is venda cash
	 * @return the list
	 */
	public static List<CondicaoPagamento> obterDadosCondicaoPagamento(
			final Integer codigoCliente, final Integer codigoFilialExpedicao, 
			final String codigoEstadoUnidadeFederativa, final Integer codigoCanal,
			final Integer codigoAtividade, Integer codigoGrupoCliente, 
			final Integer numeroParcelas, final Integer prazo,
			final Integer periodicidade, boolean isFinanciamentoVendor, 
			boolean isCobrancaBancaria, boolean isVendaCash) {
		// TODO Auto-generated method stub
		return CondicaoPagamentoDAO.obterDadosCondicaoPagamento(codigoCliente, codigoFilialExpedicao, codigoEstadoUnidadeFederativa, codigoCanal, 
				codigoAtividade, codigoGrupoCliente, numeroParcelas, prazo, periodicidade, isFinanciamentoVendor, isCobrancaBancaria, isVendaCash);
	}
}
