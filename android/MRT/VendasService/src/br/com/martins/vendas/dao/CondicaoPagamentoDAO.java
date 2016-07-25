package br.com.martins.vendas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.TipoCobranca;
import br.com.martins.vendas.enums.TipoFinanciamento;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.Util;

public class CondicaoPagamentoDAO {
	private static final String TAG = CondicaoPagamentoDAO.class.getName();
	private static Database db;
	
	
	/*
	 * /** Informa se a condi��o � vendor promocional
	 * 
	 * @param cp
	 * 
	 * @return
	 */
	/*
	 * private Boolean isVendorPromocional(CondicaoPagamento cp){
	 * if(cp.tipoContratoCondicao >= 1) return true; else return false; }
	 */

	/**
	 * 
	 * Este m�todo verifica se a condi��o de pagamento � v�lida para o cliente.
	 * Para esta verifica��o � necess�rio que o registro esteja posicionado
	 * corretamente
	 * 
	 * @return TRUE -> A condi��o de pagamento � valida FALSE -> A condi��o de
	 *         pagamento n�o � v�lida
	 */
	public static List<CondicaoPagamento> obterDadosCondicaoPagamento(
			final Integer codigoCliente, final Integer codigoFilialExpedicao, 
			final String codigoEstadoUnidadeFederativa, final Integer codigoCanal,
			final Integer codigoAtividade, Integer codigoGrupoCliente, 
			final Integer numeroParcelas, final Integer prazo,
			final Integer periodicidade, boolean isFinanciamentoVendor, 
			boolean isCobrancaBancaria, boolean isVendaCash) {
			
		StringBuffer query = new StringBuffer();
		Database db = DatabaseFactory.getInstance();
		List<CondicaoPagamento> listaCondicaoPagamento = new ArrayList<CondicaoPagamento>();
		
		query.append(" SELECT DISTINCT CLI.CODCLI, CODCNDPGT, CND.CODATI, CLI.TIPCOBBCO, CND.CODGRPCLI, FTRCNDPGT, QDEDIAPRZ, NUMPCLCND, NUMPODPCL, VLRLIMMNM, TIPFNMCND, TIPCOBCND, TIPCTTCND, FLGCNDESP, DESOBSCND, INDVDRPMC")
		.append(" FROM PCACND CND")
		.append(" JOIN PCACGR CGR ON CND.CODGRPCLI = CGR.CODGRPCLI ")
		.append(" JOIN PCACLI CLI ON CND.CODGRPCLI = CLI.CODGRPCLI")
		.append(" WHERE")
		.append(" CLI.CODCLI = " + codigoCliente)
		.append(" and CND.CODFILEPD = " + codigoFilialExpedicao)
		.append(" AND CND.CODESTUNI IN ('99','" + codigoEstadoUnidadeFederativa + "')") 
		.append(" AND CND.CODCNL IN (9999, "+codigoCanal+")")
		.append(" AND CND.CODATI IN (9999, "+codigoAtividade+")")
		.append(" AND CND.CODGRPCLI IN (9999, "+codigoGrupoCliente+")")
		.append(" AND strftime('%Y%m%d','now') BETWEEN CND.DATINIVLD AND CND.DATFIMVLD");
		
		/*// Clientes cadastrados com TIPCTTVDR = ' ' (sem contrato vendor) não trabalham com
		// financiamento 'Vendor' ou 'Tribanco', e clientes cadastrados com TIPCTTVDR = '1'
		// (carência do contrato vendor) não trabalham com financiamento 'Tribanco'
		.append(" AND ")
		.append(" ((CLI.TIPCTTVDR is null or CLI.TIPCTTVDR = '')")
		.append(" AND CND.TIPFNMCND <> " + TipoFinanciamentoEnum.VENDOR.idTipoFinanciamento + " OR CND.TIPFNMCND <> " + TipoFinanciamentoEnum.TRIBANCO.idTipoFinanciamento + ")")
		.append(" AND ")
		.append(" (TIPCTTVDR = 1")
		.append(" AND CND.TIPFNMCND <> " + TipoFinanciamentoEnum.TRIBANCO.idTipoFinanciamento + ")")
		
		
		// Clientes de cidades cadastradas com TIPCOBCND igual a '2' (100% cobrança bancária) trabalham
		// somente com tipo de cobrança 'cobrança bancária', e clientes de cidades cadastradas com
		// TIPCOBCND igual a '1' (100% cheque) trabalham somente com tipo de cobrança 'cheque'
		.append(" AND CND.TIPCOBCND = 2 ");*/
		
		if(numeroParcelas != 0){
			query.append(" AND NUMPCLCND = " + numeroParcelas);
		}
		
		if(prazo != 0){
			query.append(" AND QDEDIAPRZ = " + prazo);
		}
		
		if(periodicidade != 0){
			query.append(" AND NUMPODPCL = " + periodicidade);
		}
		
		if(isFinanciamentoVendor){
			query.append(" AND TIPFNMCND = " + TipoFinanciamento.VENDOR.tipoFinanciamento);
		}else{
			query.append(" AND TIPFNMCND <> " + TipoFinanciamento.VENDOR.tipoFinanciamento);
		}
		
		if(isCobrancaBancaria){
			query.append(" AND TIPCOBCND = " + TipoCobranca.BANCARIA.tipoCobranca);
		}else{
			query.append(" AND TIPCOBCND <> " + TipoCobranca.BANCARIA.tipoCobranca);
		}
		
		//CASH = ANTECIPADO
		if(isVendaCash){
			query.append(" AND TIPCOBCND = " + TipoCobranca.ANTECIPADO.getValue());
		}else{
			query.append(" AND TIPCOBCND <> " + TipoCobranca.ANTECIPADO.getValue());
		}

		try {
			List<Map<String, String>> result = db.executeQuery(query.toString());
			listaCondicaoPagamento = carregarListaCondicaoPagamento(result);
		} catch (Exception e) {
			Log.e(TAG,e.getMessage());
		}
		
		return listaCondicaoPagamento;
	}
	
	/**
	 * Verifica se condicao de pagamento valida.
	 *
	 * @param codigoCondicaoPagamento the codigo condicao pagamento
	 * @param mercadoria the mercadoria
	 * @return true, if successful
	 */
	public static boolean verificaSeCondicaoDePagamentoValida(Integer codigoCondicaoPagamento, Mercadoria mercadoria){
		String query = DatabaseUtil.montaQuery(" SELECT COUNT(*) FROM PCACPT WHERE CODCNDPGT = ? AND TIPMER = ? ");		
		db = DatabaseFactory.getInstance();
		try {
			int count = db.executeCount(query, Util.getStringValue(codigoCondicaoPagamento), mercadoria.tipo);
			return count > 0;
		} catch (SQLException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param condicaoPagamentoCliente
	 * @return
	 */
	public static String montaDescricaoCondicao(
			CondicaoPagamento condicaoPagamentoCliente) {
		StringBuilder sb = new StringBuilder("");
		sb.append(montaParcelasCondicao(condicaoPagamentoCliente))
		.append(" dia(s) / ")
		.append(" Financ. ")
		.append(TipoFinanciamento.toString(condicaoPagamentoCliente.tipoFinanciamento) + " / ")
		.append(TipoCobranca.toString(condicaoPagamentoCliente.tipoCobrancaCondicao));
		return sb.toString();
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
	
	private static List<CondicaoPagamento> carregarListaCondicaoPagamento(List<Map<String,String>> resultQuery){
		CondicaoPagamento condicaoPagamentoCliente;
		List<CondicaoPagamento> listaCondicaoPagamento = new ArrayList<CondicaoPagamento>();
		for (Map<String,String> condicaoPagamento : resultQuery) {
			condicaoPagamentoCliente = new CondicaoPagamento();
			condicaoPagamentoCliente.codigoCondicaoPagamento   = Util.getInteger(condicaoPagamento.get("CODCNDPGT"));
			condicaoPagamentoCliente.codigoCanal               = condicaoPagamento.get("CODCNL");
			condicaoPagamentoCliente.tipoCobrancaCondicao      = Util.getInteger(condicaoPagamento.get("TIPCOBCND"));
			condicaoPagamentoCliente.tipoFinanciamento = Util.getInteger(condicaoPagamento.get("TIPFNMCND"));
			condicaoPagamentoCliente.indicaVendorPromocao      = Util.getInteger(condicaoPagamento.get("INDVDRPMC"));
			condicaoPagamentoCliente.codigoEstadoUniao         = condicaoPagamento.get("CODESTUNI");
			condicaoPagamentoCliente.descricaoObservacaoCondicao = condicaoPagamento.get("DESOBSCND");
			condicaoPagamentoCliente.valorLimiteMinimo = Util.getBigDecimal(condicaoPagamento.get("VLRLIMMNM"));
			condicaoPagamentoCliente.quantidadeDiaPrazo = Util.getInteger(condicaoPagamento.get("QDEDIAPRZ"));
			condicaoPagamentoCliente.numeroParcelasCondicao = Util.getInteger(condicaoPagamento.get("NUMPCLCND"));
			condicaoPagamentoCliente.periodicidade = Util.getInteger(condicaoPagamento.get("NUMPODPCL"));
			condicaoPagamentoCliente.descricaoCondicao = montaDescricaoCondicao(condicaoPagamentoCliente);
			listaCondicaoPagamento.add(condicaoPagamentoCliente);
		}
		return listaCondicaoPagamento;
	}
}
