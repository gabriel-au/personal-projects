package br.com.martins.vendas.dao.pedido;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.GrupoAfinidadePEE;
import br.com.martins.vendas.vo.Pedido;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

/**
 * Classe responsavel pelo acesso aos dados referentes a Pedido.
 * 
 * @author Time de Mobilidade BRQ
 * 
 */
public class PedidoDAO {

	private static final String TAG = PedidoDAO.class.getName();
	
	private static Database db;
	private static final Integer NUM_PED_MAX = 99999;

	/**
	 * Metodo responsavel por obter todos os pedidos de um determinado cliente.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            codigo do pedido.
	 * @return objeto do tipo <code>java.util.List<Map<String, String>></code>
	 *         que corresponde a lista gerada de um ou mais pedidos referentes a
	 *         um determinado cliente.
	 */
	public static List<Map<String, String>> obtemTodosPedidos(Integer codigoCliente) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct cli.codcli as codigoCliente, cli.nomcli as nomeCliente,cli.codsgmcli as codigoSegmento,clt.codtetvnd as codigoTerritorio,ped.numped as numPedido, ");
		sql.append(" ped.flgtrs as flagPreparado,ped.datped as dataPedido,ped.hraped as horaPedido, ");
		sql.append(" ped.qdeite as quantidadeItem,ped.vlrtotped as valorTotalPedido,crt.qdemerngc as qdtMercadoriaNegociado, ");
		//sql.append(" ite.qdemerped as qdtMercadoriaPedida,ite.vlrliqmer as valorLiqMercadoria, ");
		sql.append(" CASE WHEN (crt.[QDEMERCTD])!=0  THEN 'S' ELSE 'N' END AS cortes, ");
		sql.append(" (SELECT MAX(DATOCOANL) FROM PCASIT WHERE NUMPED = ped.numped) as dataResultado ");
		sql.append(" from pcacli as cli ");
		sql.append(" inner join pcaped as ped on ped.codcli = cli.codcli ");
		sql.append(" left join pcaclt clt on cli.codcli = clt.codcli  ");
		sql.append(" left outer join pcacrt as crt on crt.numped=ped.numped ");
		sql.append(" left outer join pcaite as ite on ite.numped=ped.numped ");

		if (codigoCliente != null) {
			sql.append(" where cli.codcli = %s ");
		}

		String query = DatabaseUtil.montaQuery(sql, codigoCliente);
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemTodosPedidos - ", e.getMessage());
		}

		return list;
	}

	/**
	 * Metodo responsavel por verificar se um pedido ja foi preparado.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            codigo do pedido.
	 * @return literal <code>boolean</code> que corresponde a verificacao do
	 *         pedido, retornara <code>true</code> caso o pedido ja esteja
	 *         preparado, e retornara <code>false</code> caso ainda nao tenha
	 *         sido preparado. <br>
	 * <br>
	 *         O pedido e seus itens sao poderao ser excluido caso o pedido nao
	 *         tenha sido preparado.
	 */
	public static boolean isPedidoPreparado(Integer numeroPedido) {
		boolean retorno = false;
		String sql = "select FLGTRS from PCAPED where NUMPED = %s";

		String query = DatabaseUtil.montaQuery(sql, numeroPedido);
		db = DatabaseFactory.getInstance();

		try {
			List<Map<String, String>> list = db.executeQuery(query);
			if (!list.isEmpty()) {
				Map<String, String> map = list.get(0);
				if (null != map.get("FLGTRS") && !"".equals(map.get("FLGTRS"))) { // !map.get("FLGTRS").isEmpty()){
					retorno = true;
				}
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - isPedidoPreparado - ", e.getMessage());
		}

		return retorno;
	}

	public static boolean marcaPedidoPreparado(Integer numeroPedido) {
		String sql = "UPDATE PCAPED SET FLGTRS = '*' WHERE NUMPED = %s";

		String query = DatabaseUtil.montaQuery(sql, numeroPedido);
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("PedidoDAO - setPedidoPreparado - ", e.getMessage());
		}

		return resultado;
	}

	public static String[] obtemDadosPcasos() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" CODCLI, CODESTPED, CODEXCSFW, CODFILEPD, CODFILFAT, ");
		sql.append(" CODTRRCLI, DATREFLIV, NUMPED, NUMPEDCLI, TIPPEDCOT, TIPVNDPED ");
		sql.append(" FROM PCASOS ");
		
		db = DatabaseFactory.getInstance();
		String[] dadosCliente = new String[11];
		
		try {
			List<Map<String, String>> result = db.executeQuery(sql.toString());
			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				dadosCliente[0] = map.get("CODCLI");
				dadosCliente[1] = map.get("CODESTPED");
				dadosCliente[2] = map.get("CODEXCSFW");
				dadosCliente[3] = map.get("CODFILEPD");
				dadosCliente[4] = map.get("CODFILFAT");
				dadosCliente[5] = map.get("CODTRRCLI");
				dadosCliente[6] = map.get("DATREFLIV");
				dadosCliente[7] = map.get("NUMPED");
				dadosCliente[8] = map.get("NUMPEDCLI");
				dadosCliente[9] = map.get("TIPPEDCOT");
				dadosCliente[10] = map.get("TIPVNDPED");
			}
			
		} catch (SQLException sqlException) {
			Log.e(TAG, "PedidoDAO::obtemDadosPcasos - Erro na execução da query.");
		}

		return dadosCliente;
	}

	public static PedidoStatus obtemStatusPcasos() {
		String sql = "SELECT CODEXCSFW FROM PCASOS";
		db = DatabaseFactory.getInstance();
		PedidoStatus statusPedido = null;

		try {
			List<Map<String, String>> list = db.executeQuery(sql);
			Map<String, String> map = list.get(0);

			if (null != map.get("CODEXCSFW")) {
				Integer codigo = Integer.valueOf(map.get("CODEXCSFW"));
	
				if (!map.get("CODEXCSFW").isEmpty()) {
					switch (codigo) {
					case 0:
						statusPedido = PedidoStatus.EM_CRIACAO;
						break;
					case 1:
						statusPedido = PedidoStatus.CRIADO;
						break;
					case 2:
						statusPedido = PedidoStatus.GRAVADO;
						break;
					case 3:
						statusPedido = PedidoStatus.PREPARADO;
						break;
					case 4:
						statusPedido = PedidoStatus.SOMENTE_LEITURA;
						break;
					default:
						statusPedido = PedidoStatus.EM_CRIACAO;
						break;
					}
				}
			}

		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemStatusPcasos - ", e.getMessage());
		}

		return statusPedido;
	}
	
	public static boolean criaLinhaTabelaPcaSos() {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PCASOS ( ");
		sql.append(" CODEXCSFW, NUMPED, CODCLI, ");
		sql.append(" CODTRRCLI, DATREFLIV, TIPPEDCOT, ");
		sql.append(" NUMPEDCLI, CODFILFAT, CODFILEPD, ");
		sql.append(" CODESTPED, TIPVNDPED ");
		sql.append(" ) VALUES ( ");
		sql.append(" null, 0, null, null, null, null, ");
		sql.append(" null, null, null, null, null ");
		sql.append(" ) ");
		
		db = DatabaseFactory.getInstance();
		
		boolean resultado = false;
		try {
			db.executeSQL(sql.toString());
			resultado = true;
		} catch (SQLException e) {
			Log.e("PedidoDAO - criaLinhaTabelaPcaSos - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean criaLinhaTabelaPcaPar() {
		String sql = "insert into PCAPAR (NROULTPED) values (0) ";
		db = DatabaseFactory.getInstance();
		
		boolean resultado = false;
		try {
			db.executeSQL(sql);
			resultado = true;
		} catch (SQLException e) {
			Log.e("PedidoDAO - criaLinhaTabelaPcaPar - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean verificaTabelaPcaSos() {
		String query = "select NUMPED from PCASOS";
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			List<Map<String, String>> list = db.executeQuery(query);
			if (!list.isEmpty()){
				resultado = true;
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - verificaTabelaPcaSos - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean verificaTabelaPcaPar() {
		String query = "select NROULTPED from PCAPAR";
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			List<Map<String, String>> list = db.executeQuery(query);
			if (!list.isEmpty()){
				resultado = true;
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - verificaTabelaStatusPedido - ", e.getMessage());
		}

		return resultado;
	}
	
	/**
	 * Metodo responsavel por alterar o status do pedido na tabela PCASOS.
	 * 
	 * @param numeroPedido
	 *            numero do pedido que tera o status alterado.
	 * @param statusPedido
	 *            novo status do pedido.
	 * @return <code></code> caso a alteracao do status obteve exito,
	 *         <code>false</code> caso a alteracao nao tenha exito.
	 */
	public static boolean atualizaStatusPcasos(Pedido pedido, PedidoStatus status) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PCASOS SET ");
		sql.append(" CODEXCSFW = %s, ");
		sql.append(" NUMPED = %s, ");
		sql.append(" CODCLI = %s, ");
		sql.append(" CODTRRCLI = %s, ");
		sql.append(" DATREFLIV = %s, ");
		sql.append(" TIPPEDCOT = '%s', ");
		sql.append(" NUMPEDCLI = %s, ");
		sql.append(" CODFILFAT = %s, ");
		sql.append(" CODFILEPD = %s, ");
		sql.append(" CODESTPED = '%s', ");
		sql.append(" TIPVNDPED = %s ");
		
		String query = DatabaseUtil.montaQuery(sql,
				status.getValue(),
				pedido.numeroPedido,
				null == pedido.cliente ? null : pedido.cliente.codigoCliente,
				null == pedido.cliente ? null : pedido.cliente.codigoTerritorio,
				null, // A DATA DO LIVRO NAO E MAIS UTILIZADA
				pedido.tipoPedido,
				null, //pedido.numeroPedidoCliente, // NAO EH MAIS PREENCHIDO
				pedido.filialFaturamento,
				pedido.filialExpedicao,
				null == pedido.cliente ? null : pedido.cliente.uf, // TODO VERIFICAR A INFORMACAO QUE DEVERA SER ARMAZENADA
				null == pedido.tipoVendaPedido ? null : pedido.tipoVendaPedido.getValue()
		);

		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("PedidoDAO - atualizaStatusPcasos - ", e.getMessage());
		}

		return resultado;
	}

	public static boolean atualizaStatusPcasos(PedidoStatus status) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PCASOS SET CODEXCSFW = %s ");
		
		String query = DatabaseUtil.montaQuery(sql, status.getValue());
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("PedidoDAO - atualizaStatusPcasos - ", e.getMessage());
		}

		return resultado;
	}
	
	/**
	 * Metodo responsavel por excluir um pedido e seus itens.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            codigo do pedido.
	 * @return literal <code>boolean</code> que corresponde ao sucesso ou falha
	 *         da exclusao de um pedido e de seus itens.
	 */
	public static boolean excluiPedido(Integer codigoPedido) {
		// Regra: Se o campo FLGTRS da tabela PCAPED estiver com o valor "*"
		// (segundo legado), ou preenchido ("S" ou "N") entao fica configurado
		// que tal pedido ja foi PREPARADO, o que impossibilita a exclusao do
		// mesmo.

		boolean retorno = false;

		// Regra: Se o pedido nao tiver sido preparado, entao ele podera ser
		// excluido.
		if (!isPedidoPreparado(codigoPedido)) {
			// Execucao da exclusao dos itens do Pedido.
			executaExclusaoItensPedido(codigoPedido);

			// Execucao da exclusao do Pedido.
			executaExclusaoPedido(codigoPedido);

			retorno = true;
		}

		return retorno;
	}

	/**
	 * Metodo responsavel pela execucao da exclusao de um pedido.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            codigo do pedido.
	 */
	private static void executaExclusaoPedido(Integer codigoPedido) {
		String sql = "delete from PCAPED where NUMPED = %s ";

		String query = DatabaseUtil.montaQuery(sql, codigoPedido);
		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - executaExclusaoPedido - ", e.getMessage());
		}
	}

	/**
	 * Metodo responsavel pela execucao da exclusao dos itens de um pedido.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            codigo do pedido.
	 */
	private static void executaExclusaoItensPedido(Integer codigoPedido) {
		String sql = "delete from PCAITE where NUMPED = %s ";

		String query = DatabaseUtil.montaQuery(sql, codigoPedido);
		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - executaExclusaoItensPedido - ", e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por obter o Ãºltimo pedido de um cliente.
	 * 
	 * @param codigoCliente
	 *            objeto do tipo <code>java.lang.Integer</code> que corresponde
	 *            ao codigo do cliente.
	 * @return objeto do tipo <code>br.com.martins.vendas.vo.Pedido</code> que
	 *         corresponde ao Ãºltimo pedido do cliente.
	 */
	public static Pedido obtemUltimoPedido(Integer codigoCliente) {
		Pedido pedido = new Pedido();

		StringBuilder sql = new StringBuilder();
		sql.append(" select PED.CODCLI, PED.DATPED, PED.NUMPED ");
		sql.append(" from PCAPED PED ");
		sql.append(" where PED.CODCLI = %s ");
		sql.append(" and PED.DATPED = (select max(IPED.DATPED) from PCAPED IPED where PED.CODCLI = IPED.CODCLI) ");

		String query = DatabaseUtil.montaQuery(sql, codigoCliente);
		db = DatabaseFactory.getInstance();

		try {
			List<Map<String, String>> list = db.executeQuery(query);
			if (!list.isEmpty()) {
				pedido.codigoCliente = list.get(0).get("CODCLI");
				pedido.dataPedido = DateUtil.formataData(list.get(0).get("DATPED"), DateUtil.DEFAULT_DATE_DATABASE);
				pedido.numeroPedido = list.get(0).get("NUMPED");
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemUltimoPedido - ", e.getMessage());
		} catch (ParseException e) {
			Log.e("PedidoDAO - obtemUltimoPedido - ", e.getMessage());
		}

		return pedido;
	}

	/**
	 * Metodo responsavel por obter os itens vendidos em uma determinada semana.
	 * 
	 * @param inicioSemana
	 *            objeto do tipo <code>java.util.Date</code> referente a data
	 *            inicial da pesquisa.
	 * @param fimSemana
	 *            objeto do tipo <code>java.util.Date</code> referente a data
	 *            final da pesquisa.
	 * @return objeto do tipo <code>java.util.List<Map<String, String>></code>
	 *         referente a listagem dos itens vendidos na semana pesquisa.
	 */
	public static List<Map<String, String>> obtemItensVendidosSemana(Date inicioSemana, Date fimSemana) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select PED.DATPED, PED.VLRTOTPED, PED.QDEITE, PED.NUMPED ");
		sql.append(" from PCAPED PED ");
		sql.append(" where PED.DATPED between %s and %s ");

		String query = DatabaseUtil.montaQuery(sql, DateUtil.formataData(inicioSemana, DateUtil.DEFAULT_DATE_DATABASE), DateUtil.formataData(fimSemana, DateUtil.DEFAULT_DATE_DATABASE));
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemItensVendidosSemana - ", e.getMessage());
		}

		return list;
	}

	/**
	 * Metodo responsavel por carregar os detalhes de um determinado pedido.
	 * 
	 * @param numeroPedido
	 *            objeto do tipo <code>java.lang.Integer</code> referente ao
	 *            nÃºmero do pedido.
	 * @param codigoCanal
	 *            objeto do tipo <code>java.lang.Integer</code> referente ao
	 *            codigo do canal.
	 * @return objeto do tipo <code>java.util.List<Map<String, String>></code>
	 *         referente aos detalhes do pedido.
	 */
	public static List<Map<String, String>> carregaDetalhesPedido() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct epe.codfilepd as Cad,epe.peracottc as percAcaoTatica,epe.codfilfat as Fat, ");
		sql.append("epe.qdemerped as QuantidadeMercadoria,epe.vlrliqmer as valorLiquido,epe.vlrstbtot as STBTotal,epe.vlripitot as IPITotal, ");
		sql.append("epe.vlrfrtmer as valorFrete,epe.perdscflx as porcDSC,epe.permaxflx as percMaximoFlex,epe.permaxsmp as porcMaximaSmp,epe.vlrbnfmer as valorBenMer, ");
		sql.append("(select distinct vlrmnmepd from pcapmn where codfilepd=epe.codfilepd)as valorMinimoEPD, ");
		sql.append("cnd.ftrcndpgt as FatorCndPagamento ");
		sql.append("from tmpitepe epe ");
		sql.append("inner join pcacnd cnd ");
		sql.append("on epe.codcndpgt=cnd.codcndpgt ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - carregaDetalhesPedido - ", e.getMessage());
		}

		return list;
	}

	public static List<Pedido> carregaDetalhesNotaFiscal(Integer codigoFilial) {
		List<Pedido> lista = new ArrayList<Pedido>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct epe.codfilepd as Cad,epe.codfilfat as Fat,epe.vlrliqmer as valorLiquido,epe.vlrstbtot as STBTotal,epe.vlripitot as IPITotal, ");
		sql.append("cnd.ftrcndpgt as FatorCndPagamento,cnd.numpclcnd as numeroParcelas,cnd.qdediaprz as qtdDiasPrazo,cnd.numpodpcl as numPeriodoParcelas, ");
		sql.append("epe.codcndpgt as CP,epe.numnotfsc as NF,epe.qdeprzbfc as PrazoBFC,epe.qdemerped as QuantidadeMercadoria ");
		sql.append("from tmpitepe epe ");
		sql.append("inner join pcacnd cnd ");
		sql.append("on epe.codcndpgt=cnd.codcndpgt ");
		sql.append("and epe.codfilfat = %s");

		String query = DatabaseUtil.montaQuery(sql, codigoFilial);
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);

			for (Map<String, String> p : list) {
				Pedido pedido = new Pedido();
				pedido.filialExpedicao = Util.getInteger(p.get("Cad"));
				pedido.filialFaturamento = Util.getInteger(p.get("Fat"));
				pedido.numNotaFiscal = Util.getInteger(p.get("NF"));
				pedido.condicaoPagamento = Util.getInteger(p.get("CP"));
				pedido.valorLiquidoMercadoria = Util.getBigDecimal(p.get("valorLiquido"));
				pedido.valorTotalStb = Util.getBigDecimal(p.get("STBTotal"));
				pedido.valorTotalIpi = Util.getBigDecimal(p.get("IPITotal"));
				pedido.numParcelas = Util.getInteger(p.get("numeroParcelas"));
				pedido.diasPrazoBeneficiario = p.get("PrazoBFC") != null ? Util.getInteger(p.get("PrazoBFC")) : 0;
				pedido.quantidadeMercadoria = Util.getInteger(p.get("QuantidadeMercadoria"));
				String diasPrazo = p.get("qtdDiasPrazo");
				pedido.qtdDiasPrazo = diasPrazo != null ? Util.getInteger(diasPrazo) : 0;
				String periodoParcelas = p.get("numPeriodoParcelas");
				pedido.numParcelas = periodoParcelas != null ? Util.getInteger(periodoParcelas) : 0;
				pedido.fatorCondicaoPagamento = Util.getBigDecimal(p.get("FatorCndPagamento"));

				lista.add(pedido);
			}

		} catch (SQLException e) {
			Log.e("PedidoDAO - carregaDetalhesPedidoByCad - ", e.getMessage());
		}

		return lista;
	}

	public static List<Pedido> carregaDetalhesFilialFat(Integer codigoFilial) {
		List<Pedido> lista = new ArrayList<Pedido>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct epe.codfilepd as Cad,epe.codfilfat as Fat,epe.vlrliqmer as valorLiquido,epe.vlrstbtot as STBTotal,epe.vlripitot as IPITotal, ");
		sql.append("cnd.ftrcndpgt as FatorCndPagamento, ");
		sql.append("epe.qdemerped as QuantidadeMercadoria ");
		sql.append("from tmpitepe epe ");
		sql.append("inner join pcacnd cnd ");
		sql.append("on epe.codcndpgt=cnd.codcndpgt ");
		sql.append("and epe.codfilepd = %s");

		String query = DatabaseUtil.montaQuery(sql, codigoFilial);
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);

			for (Map<String, String> p : list) {
				Pedido pedido = new Pedido();
				pedido.filialExpedicao = Integer.valueOf(p.get("Cad"));
				pedido.filialFaturamento = Integer.valueOf(p.get("Fat"));
				pedido.valorLiquidoMercadoria = Util.getBigDecimal(p.get("valorLiquido"));
				pedido.valorTotalStb = Util.getBigDecimal(p.get("STBTotal"));
				pedido.valorTotalIpi = Util.getBigDecimal(p.get("IPITotal"));
				pedido.quantidadeMercadoria = Integer.valueOf(p.get("QuantidadeMercadoria"));
				pedido.fatorCondicaoPagamento = Util.getBigDecimal(p.get("FatorCndPagamento"));

				lista.add(pedido);
			}

		} catch (SQLException e) {
			Log.e("PedidoDAO - carregaDetalhesPedidoByCad - ", e.getMessage());
		}

		return lista;
	}

	/**
	 * Metodo responsavel por listar todos os itens de um pedido que estao no
	 * caminhao.
	 * 
	 * @return
	 * 
	 * @return objeto do tipo <code>java.util.List<Map<String, String>></code>
	 *         com a listagem dos itens de um pedido que estao no caminhao
	 */
	public static List<Map<String, String>> carregaDetalhesPedidoCaminhao() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" TIPPMC, DESMER, QDEMERPED, CODFILFAT, TIPMERESG, ");
		sql.append(" CODCNDPGT, VLRLIQMER, QDECXAFRN, UNTCXAIMP, VLRUNTIMP, ");
		sql.append(" NUMNOTFSC, CODFLXPCO, CODSMBSIT, PERICMMER, CODFILEPD, ");
		sql.append(" CMSCNSMER, DESNGCMER, VLRPTOMER, TIPICT, VLRFRTMER, VLRLIQIMP, ");
		sql.append(" FLGPEE, VLRSTBMER, LIQCXAIMP,CODMER,VLRIPIMER,VLRBRTMER,VLRBNFMER,PERDSCBFC ");
		sql.append(" FROM TMPITEPE ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - carregaDetalhesPedidoCaminhao - ", e.getMessage());
		}

		return list;
	}

	/**
	 * Metodo responsavel por obter os dados de um pedido para gerar o relatorio
	 * de gondola.
	 * 
	 * @param numeroPedido
	 *            numero do pedido
	 */
	// TODO METODO INACABADO POIS FOI NECESSARIO SEGUIR PARA FUNCIONALIDADE
	// RELATORIO DA GONDOLA
	public static List<Map<String, String>> obtemDadosRelatorioPedido(Integer numeroPedido) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append("     SELECT PED.NUMPED, ");
		sql.append("            PED.CODCLI, ");
		sql.append("            PED.CODTRRCLI, ");
		sql.append("            CASE FLGTRS WHEN '*' THEN 'S' ELSE 'N' END, ");
		sql.append("            PED.DATPED, ");
		sql.append("            PED.HRAPED,  ");
		sql.append("            (SELECT COUNT(*) FROM PCAITE WHERE NUMPED = PED.NUMPED) QTD  ");
		sql.append("       FROM PCAPED PED ");
		sql.append(" INNER JOIN PCACLI CLI ");
		sql.append("         ON CLI.CODCLI = PED.CODCLI ");
		sql.append("      WHERE PED.NUMPED = %s ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		try {
			list = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemDadosRelatorioPedido - ", e.getMessage());
		}

		return list;
	}

	public static Pedido carregaDadosPreparaPedido(Integer numped) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CODCLI, QDEITE, NUMPED, NUMPEDCLI, CODTRRCLI, CODCOTNGC, TIPVNDPED, TIPPEDCOT ");
		sql.append(" FROM PCAPED ");
		sql.append(" WHERE NUMPED = %s ");

		String query = DatabaseUtil.montaQuery(sql, numped);
		db = DatabaseFactory.getInstance();

		Pedido pedido = null;
		try {
			list = db.executeQuery(query);
			Map<String, String> p = list.get(0);
			
			pedido = new Pedido();
			pedido.codigoCliente = p.get("CODCLI");
			pedido.quantidadeMercadoria = Util.getInteger(p.get("QDEITE"));
			pedido.numeroPedido = p.get("NUMPED");
			pedido.numeroPedidoCliente = p.get("NUMPEDCLI");
			pedido.codigoTerritorio = Util.getInteger(p.get("CODTRRCLI"));
			pedido.codigoNegociacao = p.get("CODCOTNGC");
			
			// TODO VERIFICAR SE ESSA EH A MELHOR ABORDAGEM
			int tipoVenda = Util.getInteger(p.get("TIPVNDPED"));
			switch (tipoVenda) {
			case 0:
				pedido.tipoVendaPedido = TipoVendaPedido.NORMAL;
				break;
			case 1:
				pedido.tipoVendaPedido = TipoVendaPedido.SIMPLIFICADA;
				break;
			case 2:
				pedido.tipoVendaPedido = TipoVendaPedido.MISTA;
				break;
			default:
				pedido.tipoVendaPedido = null;
				break;
			}
			
			pedido.tipoPedido = p.get("TIPPEDCOT");
		} catch (SQLException e) {
			Log.e("PedidoDAO - carregaDadosPreparaPedido - ", e.getMessage());
		}

		return pedido;
	}

	public static String obtemCodigoRepresentanteAgencia() {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct CODEDEVND from pcaage ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		try {
			Integer codigoRepresentanteAgencia = null;
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				Map<String, String> codigo = result.get(0);
				codigoRepresentanteAgencia = Util.getInteger(codigo.get("CODEDEVND"));
			}
			return codigoRepresentanteAgencia.toString();
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemCodigoRepresentanteAgencia - ", e.getMessage());
		}
		return null;
	}

	public static Integer obtemTotalPedidosPreparados() {
		Integer ultimoPedidoPreparado = 0;
		String query = "SELECT NROULTPRP FROM PCAPAR";
		db = DatabaseFactory.getInstance();
		
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			
			if (!result.isEmpty()) {
				Map<String, String> codigo = result.get(0);
				ultimoPedidoPreparado = null == codigo.get("NROULTPRP") ? 0 : Util.getInteger(codigo.get("NROULTPRP"));
			}
			
		} catch (SQLException e) {
			Log.e("PedidoDAO - ObtemUltimoPedidoPreparado - ", e.getMessage());
		}
		
		return ultimoPedidoPreparado;
	}

	public static Integer obtemRegiaoDistribuicao(String codigoCliente, Integer codigoFilialExp) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct CODREGDTB from PCACOP ");
		sql.append(" where CODCLI=%s ");
		sql.append(" and CODFILEPD=%s ");

		String query = DatabaseUtil.montaQuery(sql, codigoCliente, codigoFilialExp);
		db = DatabaseFactory.getInstance();

		try {
			Integer codigoRegionalDistribuicao = 0;
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				Map<String, String> codigo = result.get(0);
				codigoRegionalDistribuicao = Util.getInteger(codigo.get("CODREGDTB"));
			}

			return codigoRegionalDistribuicao;
		} catch (SQLException e) {
			Log.e("PedidoDAO - ObtemRegiaoDistribuicao - ", e.getMessage());
		}
		return null;
	}

	public static Integer validaVolumeQuantidade(Integer codigoAtividade, Integer codigoMercadoria, Integer codigoFilialExpedicao, String codigoEstado) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT RTF.PERRTC, RTM.QDERTCITE,  ");
		sql.append("	CASE WHEN RTA0.CODGRPRTC IS NOT NULL THEN  ");
		sql.append("		CASE WHEN RTA1.CODGRPRTC IS NOT NULL THEN 2 ELSE 0 END  ");
		sql.append("	ELSE  ");
		sql.append("		1  END QDE_METALGRAMPO ");
		sql.append(" FROM  ");
		sql.append("	PCARTM RTM INNER JOIN PCARTF RTF ON RTM.CODGRPRTC = RTF.CODGRPRTC ");
		sql.append("	LEFT JOIN PCARTA RTA0 ON RTM.CODGRPRTC = RTA0.CODGRPRTC ");
		sql.append("	LEFT JOIN PCARTA RTA1 ON RTM.CODGRPRTC = RTA1.CODGRPRTC AND RTA1.CODATI = %s ");
		sql.append(" WHERE ");
		sql.append("	RTM.CODMER = %s ");
		sql.append("	AND RTF.CODFILEPD = %s ");
		sql.append("	AND RTF.CODESTUNI = %s ");

		String query = DatabaseUtil.montaQueryGenerica(sql, codigoAtividade, codigoMercadoria, codigoFilialExpedicao, codigoEstado);
		db = DatabaseFactory.getInstance();

		Integer resultado = 0;
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				Map<String,String> map = result.get(0);
				resultado = Util.getInteger(map.get("QDE_METALGRAMPO"));
			}			
		} catch (SQLException e) {
			Log.e("PedidoFechamentoDAO - validaVolumeQuantidade - ", e.getMessage());
		}

		return resultado;
	}
	
	public static Integer geraNumeroPedido() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NROULTPED + 1 AS NUMERO FROM PCAPAR");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();

		Integer numeroPedido = 1;
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				numeroPedido = Util.getInteger(result.get(0).get("NUMERO"));
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - geraNumeroPedido - ", e.getMessage());
		}

		// Regra: Caso o numero do pedido alcance o valor 99999, o próximo
		// número de pedido será reiniciado com o valor "1".
		if (numeroPedido > NUM_PED_MAX) {
			numeroPedido = 1;
		}

		return numeroPedido;
	}

	public static void atualizaUltimoPedidoPreparado(Integer numero, String numeroPedido) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PCAPAR SET NROULTPRP = %s, NROULTPED = '%s' ");

		String query = DatabaseUtil.montaQuery(sql, numero, numeroPedido);
		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - atualizaUltimoPedidoPreparado - ", e.getMessage());
		}

	}

	public static void atualizaUltimoPedidoFechado(Integer numeroPedido) {
		String query = DatabaseUtil.montaQuery(" UPDATE PCAPAR SET NROULTPED = '%s' ", numeroPedido);
		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(query);
		} catch (SQLException e) {
			Log.e("PedidoDAO - atualizaUltimoPedidoFechado - ", e.getMessage());
		}
	}

	public static Integer obtemNumeroUltimoPedidoPcapar() {
		String query = DatabaseUtil.montaQuery(" SELECT NROULTPED FROM PCAPAR ");
		db = DatabaseFactory.getInstance();

		Integer numeroPedido = 1;
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				numeroPedido = Util.getInteger(result.get(0).get("NROULTPED"));
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - geraNumeroPedido - ", e.getMessage());
		}

		// Regra: Caso o numero do pedido alcance o valor 99999, o próximo
		// número de pedido será reiniciado com o valor "1".
		if (numeroPedido > NUM_PED_MAX) {
			numeroPedido = 1;
		}

		return numeroPedido;
	}
	
	// obtem dados para gerar o cabeçalho do arquivo PEE
	public static GrupoAfinidadePEE obtemDadosCabecalhoArquivoPEE() {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct CODFILEPD,CODGRPAFD,CODFILFAT,NUMRLCCID from PCARAF  where IDTINFPEE = 'R' and FLGTRN <> '*' ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		GrupoAfinidadePEE grupo = null;

		try {
			list = db.executeQuery(query);

			if (!list.isEmpty()) {
				Map<String, String> p = list.get(0);
				grupo = new GrupoAfinidadePEE();
				grupo.codFilialExpedicao = Util.getInteger(p.get("CODFILEPD"));
				grupo.codFilialFaturamento = Util.getInteger(p.get("CODFILFAT"));
				grupo.codGrupoAfinidade = Util.getInteger(p.get("CODGRPAFD"));
				grupo.numRelacaoCidade = Util.getInteger(p.get("NUMRLCCID"));
			}

			return grupo;
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemDadosCabecalhoArquivoPEE - ", e.getMessage());
		}
		return null;
	}

	// obtem itens para gerar o corpo do arquivo PEE
	public static List<GrupoAfinidadePEE> obtemItensArquivoPEE() {
		StringBuilder sql = new StringBuilder();
		sql.append("select CODMER from PCARAF  where idtinfpee = 'R' and flgtrn <> '*' ");

		String query = DatabaseUtil.montaQuery(sql);
		db = DatabaseFactory.getInstance();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<GrupoAfinidadePEE> lista = new ArrayList<GrupoAfinidadePEE>();
		GrupoAfinidadePEE grupo;

		try {
			list = db.executeQuery(query);

			for (Map<String, String> p : list) {
				grupo = new GrupoAfinidadePEE();
				grupo.codMercadoria = Util.getInteger(p.get("CODMER"));
				lista.add(grupo);
			}

			return lista;
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemItensArquivoPEE - ", e.getMessage());
		}
		return null;
	}

	public static void atualizaTabelaArquivoPEE(Integer codigoMercadoria) {
		String flagTransf = "*";
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE PCARAF SET FLGTRN='%s' WHERE CODMER = %s");

		String query = DatabaseUtil.montaQuery(sql, flagTransf, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(query);

		} catch (SQLException e) {
			Log.e("PedidoDAO - atualizaTabelaArquivoPEE - ", e.getMessage());
		}

	}

	public static boolean inserePedido(Pedido pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PCAPED ( ");
		sql.append(" CODCLI, "); // CODCLI - Codigo Cliente
		sql.append(" CODCOTNGC, "); // CODCOTNGC - Codigo Negociacao
		sql.append(" CODFILEPD, "); // CODFILEPD - Codigo Filial Expedicao
		sql.append(" CODFILFAT, "); // CODFILFAT - Codigo Filial Faturamento
		sql.append(" CODTRRCLI, "); // CODTRRCLI - Codigo Territorio Cliente
		sql.append(" DATPED, "); // DATPED - Data Pedido
		sql.append(" DATREFLIV, "); // DATREFLIV - Data Referencia Livro
		sql.append(" FLGTRS, "); // FLGTRS - Flag Transmissao
		sql.append(" FLGULTPRP, "); // FLGULTPRP - Flag Ultimo Preposto
		sql.append(" HRAPED, "); // HRAPED - Hora Pedido
		sql.append(" NUMPED, "); // NUMPED - Numero do Pedido
		sql.append(" NUMPEDCLI, "); // NUMPEDCLI - Numero Pedido Cliente
		sql.append(" QDEITE, "); // QDEITE - Quantidade Itens
		sql.append(" TIPPEDCOT, "); // TIPPEDCOT - Tipo Pedido
		sql.append(" TIPVNDPED, "); // TIPVNDPED - Tipo Venda Pedido
		sql.append(" TXAENTPED, "); // TXAENTPED - Taxa Entrega Pedido
		sql.append(" VLRTOTPED "); // VLRTOTPED - Valor Total Pedido
		sql.append(" ) VALUES ( ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, '%s', %s, %s, %s ");
		sql.append(" ) ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		String query = montaQueryInserePedido(pedido, sql);

		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("PedidoDAO - inserePedido - ", e.getMessage());
		}

		return resultado;
	}

	public static boolean alteraPedido(Pedido pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PCAPED SET ");
		sql.append(" CODCLI		= %s, "); // CODCLI - Codigo Cliente
		sql.append(" CODCOTNGC	= %s, "); // CODCOTNGC - Codigo Negociacao
		sql.append(" CODFILEPD	= %s, "); // CODFILEPD - Codigo Filial Expedicao
		sql.append(" CODFILFAT	= %s, "); // CODFILFAT - Codigo Filial Faturamento
		sql.append(" CODTRRCLI	= %s, "); // CODTRRCLI - Codigo Territorio Cliente
		sql.append(" DATPED		= %s, "); // DATPED - Data Pedido
		sql.append(" DATREFLIV	= %s, "); // DATREFLIV - Data Referencia Livro
		sql.append(" FLGTRS		= %s, "); // FLGTRS - Flag Transmissao
		sql.append(" FLGULTPRP	= %s, "); // FLGULTPRP - Flag Ultimo Preposto
		sql.append(" HRAPED		= %s, "); // HRAPED - Hora Pedido
		sql.append(" NUMPEDCLI	= %s, "); // NUMPEDCLI - Numero Pedido Cliente
		sql.append(" QDEITE		= %s, "); // QDEITE - Quantidade Itens
		sql.append(" TIPPEDCOT	= '%s', "); // TIPPEDCOT - Tipo Pedido
		sql.append(" TIPVNDPED	= %s, "); // TIPVNDPED - Tipo Venda Pedido
		sql.append(" TXAENTPED	= %s, "); // TXAENTPED - Taxa Entrega Pedido
		sql.append(" VLRTOTPED	= %s  "); // VLRTOTPED - Valor Total Pedido
		sql.append(" WHERE ");
		sql.append(" NUMPED		= %s "); // Integer PRIMARY KEY,

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		String query = montaQueryAlteraPedido(pedido, sql);

		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.d("PedidoDAO - alteraPedido - ", e.getMessage());
		}

		return resultado;
	}
	
	public static boolean insereItensPedido(Pedido pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO PCAITE ( ");
		sql.append("CMSCNSMER, ");
		sql.append("CMSMAXMER, ");
		sql.append("CODCNDPGT, ");
		sql.append("CODCORMRG, ");
		sql.append("CODCORMRGB, ");
		sql.append("CODFILEPD, ");
		sql.append("CODFILFAT, ");
		sql.append("CODFLXPCO, ");
		sql.append("CODMER, ");
		sql.append("CODMERPCP, ");
		sql.append("CODPMC, ");
		sql.append("CODRGRDTB, ");
		sql.append("CODSMBSIT, ");
		sql.append("DESNGCMER, ");
		sql.append("INDITEIMU, ");
		sql.append("INDMERKIT, ");
		sql.append("INDRTCBFB, ");
		sql.append("INDRTCBFV, ");
		sql.append("INDSEMMRGL, ");
		sql.append("IPITOTBNF, ");
		sql.append("ITEPEDDIN, ");
		sql.append("NUMNOTFSC, ");
		sql.append("NUMPCLCND, ");
		sql.append("NUMPED, ");
		sql.append("NUMSEQITE, ");
		sql.append("PERACOCLI, ");
		sql.append("PERACOTTC, ");
		sql.append("PERACRCNS, ");
		sql.append("PERACRVTL, ");
		sql.append("PERCMSADI, ");
		sql.append("PERDSCBFC, ");
		sql.append("PERDSCFLX, ");
		sql.append("PERDSCMNM, ");
		sql.append("PERECOBFC, ");
		sql.append("PERICMMER, ");
		sql.append("PERMAXFLX, ");
		sql.append("PERMAXSMP, ");
		sql.append("PERVLRMNM, ");
		sql.append("QDEMERPED, ");
		sql.append("QDEPRZBFC, ");
		sql.append("RTCBFVMER, ");
		sql.append("STBCPLTOT, ");
		sql.append("STBTOTBNF, ");
		sql.append("TIPDTBMER, ");
		sql.append("TIPESGMER, ");
		sql.append("TIPICTMER, ");
		sql.append("TIPNGCMER, ");
		sql.append("VLRBNFMER, ");
		sql.append("VLRBRTCXA, ");
		sql.append("VLRBRTFRC, ");
		sql.append("VLRBRTMER, ");
		sql.append("VLRBRTTMP, ");
		sql.append("VLRDSCESP, ");
		sql.append("VLRFRTMER, ");
		sql.append("VLRIPIMER, ");
		sql.append("VLRIPITOT, ");
		sql.append("VLRLIQCXA, ");
		sql.append("VLRLIQMER, ");
		sql.append("VLRMAXISN, ");
		sql.append("VLRMNMMER, ");
		sql.append("VLRPTOBFC, ");
		sql.append("VLRPTOMER, ");
		sql.append("VLRRDCITE, ");
		sql.append("VLRSEMENC, ");
		sql.append("VLRSTBCPL, ");
		sql.append("VLRSTBMER, ");
		sql.append("VLRSTBTOT ");
		sql.append(" ) VALUES ( ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s ");
		sql.append(" ) ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		
		Integer numeroSequencia = 1;
		for (Item item : pedido.listaItens){
			
			// Insere o numero de sequencia do item
			item.numeroSequenciaItem = numeroSequencia;
			numeroSequencia++;
			
			String query = montaQueryInsereItemPedido(pedido, item, sql);
			
			try {
				db.executeSQL(query);
				resultado = true;
			} catch (SQLException e) {
				Log.e("PedidoDAO - insereItensPedido - ", e.getMessage());
				resultado = false;
			}
		}

		return resultado;
	}

	public static Integer obtemPontuacaoMinimaPedido(Integer codigoFilialExpedicao, String estadoUF, Integer canal) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT VLRMNMEPD "); // VLRMNMBLT
		sql.append("FROM PCAPMN ");
		sql.append("WHERE CODFILEPD = %s ");
		sql.append("AND CODESTUNI = '%s' ");
		sql.append("AND CODCNL = %s ");

		String query = DatabaseUtil.montaQuery(sql.toString(), codigoFilialExpedicao, estadoUF, canal);
		db = DatabaseFactory.getInstance();
		
		Integer valorPontuacaoMinima = 0;
		try {
			List<Map<String, String>> result = db.executeQuery(query);
			if (!result.isEmpty()) {
				Map<String, String> map = result.get(0);
				valorPontuacaoMinima = Util.getInteger(map.get("VLRMNMEPD"));
			}
		} catch (SQLException e) {
			Log.e("PedidoDAO - obtemPontuacaoMinimaPedido - ", e.getMessage());
		}

		return valorPontuacaoMinima;
	}
	
	private static String montaQueryInserePedido(Pedido pedido, StringBuilder sql) {
		Date date = new Date();
		
		String query = DatabaseUtil.montaQueryGenerica(sql, //

				// CODCLI - Codigo Cliente
				pedido.cliente.codigoCliente,

				// CODCOTNGC - Codigo Negociacao
				pedido.codigoNegociacao,

				// CODFILEPD - Codigo Filial Expedicao
				pedido.filialExpedicao,

				// CODFILFAT - Codigo Filial Faturamento
				pedido.filialFaturamento,

				// CODTRRCLI - Codigo Territorio Cliente
				pedido.cliente.codigoTerritorio,

				// DATPED - Data Pedido
				DateUtil.formataData(date, DateUtil.DEFAULT_DATE_DATABASE),

				// DATREFLIV - Data Referencia Livro
				null, // ESSE VALOR NAO EH MAIS PREENCHIDO

				// FLGTRS - Flag Transmissao
				null, // OCORRERA UPDATE QUANDO O PEDIDO FOR PREPARADO

				// FLGULTPRP - Flag Ultimo Preposto
				null, // OCORRERA UPDATE QUANDO O PEDIDO FOR PREPARADO

				// HRAPED - Hora Pedido
				DateUtil.formataHora(date),

				// NUMPED - Numero do Pedido
				pedido.numeroPedido,

				// NUMPEDCLI - Numero Pedido Cliente
				null, // pedido.numeroPedidoCliente, // ESSE VALOR NAO EH MAIS PREENCHIDO

				// QDEITE - Quantidade Itens
				pedido.quantidadeTotalItens,

				// TIPPEDCOT - Tipo Pedido
				pedido.tipoPedido,

				// TIPVNDPED - Tipo Venda Pedido
				pedido.tipoVendaPedido.getValue(),

				// TXAENTPED - Taxa Entrega Pedido
				null, // TODO VERIFICAR DE ONDE VEM ESSE DADO

				// VLRTOTPED - Valor Total Pedido
				pedido.valorTotalPedido

		);
		return query;
	}

	private static String montaQueryAlteraPedido(Pedido pedido, StringBuilder sql) {
		Date date = new Date();
		
		String query = DatabaseUtil.montaQueryGenerica(sql, //

				// CODCLI - Codigo Cliente
				pedido.cliente.codigoCliente,

				// CODCOTNGC - Codigo Negociacao
				pedido.codigoNegociacao,

				// CODFILEPD - Codigo Filial Expedicao
				pedido.filialExpedicao,

				// CODFILFAT - Codigo Filial Faturamento
				pedido.filialFaturamento,

				// CODTRRCLI - Codigo Territorio Cliente
				pedido.cliente.codigoTerritorio,

				// DATPED - Data Pedido
				DateUtil.formataData(date, DateUtil.DEFAULT_DATE_DATABASE),

				// DATREFLIV - Data Referencia Livro
				null, // ESSE VALOR NAO EH MAIS PREENCHIDO

				// FLGTRS - Flag Transmissao
				null, //pedido.statusPedido.status, // OCORRERA UPDATE QUANDO O PEDIDO FOR PREPARADO

				// FLGULTPRP - Flag Ultimo Preposto
				null, // OCORRERA UPDATE QUANDO O PEDIDO FOR PREPARADO

				// HRAPED - Hora Pedido
				DateUtil.formataHora(date),

				// NUMPEDCLI - Numero Pedido Cliente
				null, // pedido.numeroPedidoCliente, // ESSE VALOR NAO EH MAIS PREENCHIDO

				// QDEITE - Quantidade Itens
				pedido.quantidadeTotalItens,

				// TIPPEDCOT - Tipo Pedido
				pedido.tipoPedido,

				// TIPVNDPED - Tipo Venda Pedido
				pedido.tipoVendaPedido.getValue(),

				// TXAENTPED - Taxa Entrega Pedido
				null, // TODO VERIFICAR DE ONDE VEM ESSE DADO

				// VLRTOTPED - Valor Total Pedido
				pedido.valorTotalPedido,
				
				// NUMPED - Numero do Pedido
				pedido.numeroPedido

		);
		return query;
	}
	
	private static String montaQueryInsereItemPedido(Pedido pedido, Item item, StringBuilder sql) {
		String query = DatabaseUtil.montaQueryGenerica(sql, //

				// CMSCNSMER - Comissao Concessao Mercadoria
				item.comissao.comissaoMercadoria,

				// CMSMAXMER - Maximo Comissao Mercadoria
				item.comissao.comissaoMaximaMercadoria,

				// CODCNDPGT - Codigo Condicao de Pagamento
				item.condicaoPagamento.codigoCondicaoPagamento,

				// CODCORMRG - Codigo Cor de Margem
				item.codigoCorMargem,

				// CODCORMRGB - Codigo Cor de Margem B
				item.codigoCorMargemB,

				// CODFILEPD - Codigo Filial Expedicao
				item.codigoFilialExpedicao,

				// CODFILFAT - Codigo de Filial de Faturamento
				item.codigoFilialFaturamento,

				// CODFLXPCO - Codigo Preco Flexivel
				item.preco.codigoFlexivelPreco,

				// CODMER - Codigo da Mercadoria
				item.mercadoria.codigo,

				// CODMERPCP - Codigo Mercadoria Principal
				item.mercadoria.codigoMercadoriaPrincipal,

				// CODPMC - Codigo Promocao
				item.promocao.codigo,

				// CODRGRDTB - Codigo Regra Distribuicao
				item.regraDistribuicao.codigoRegra,

				// CODSMBSIT - Codigo Simbolo Situacao
				item.preco.codigoSimboloSituacao,

				// DESNGCMER - Descricao Negociacao Mercadoria
				item.mercadoria.descricaoNegocioMercadoria,

				// INDITEIMU - Indica Item Imune
				item.indicaItemImune,

				// INDMERKIT - Indica Mercadoria Kit
				item.mercadoria.indicaMercadoriaKit,

				// INDRTCBFB - Indica Restricao Beneficio Customizado no Brinde
				item.indicaRestricaoBeneficioCustomizadoBrinde,

				// INDRTCBFV - Indica Restricao Beneficio Customizado
				item.indicaRestricaoBeneficioCustomizado,

				// INDSEMMRGL - Indica Sem Margem de Lucro
				(item.stb.temMargemDeLucro ? '1' : '0'),

				// IPITOTBNF - Valor IPI Total Bonificacao
				item.valorIPITotalBonificacao,

				// ITEPEDDIN - Item Pedido Dinamico
				null, // TODO VERIFICAR DE ONDE VEM ESSE VALOR

				// NUMNOTFSC - Numero da Nota Fiscal
				item.notaFiscal,

				// NUMPCLCND - Numero Parcela
				pedido.numParcelas,

				// NUMPED - Numero Pedido
				pedido.numeroPedido,

				// NUMSEQITE - Numero Sequencial Item
				item.numeroSequenciaItem,

				// PERACOCLI - Percentual de Acao do Cliente
				item.desconto.percentualDescontoAcao,

				// PERACOTTC - Percentual de Acao Tatica
				item.percentualDescontoAcaoTatica,

				// PERACRCNS - Percentual Acrescimo Concedido
				item.percentualAcrescimoConcedido,

				// PERACRVTL - Percentual de Acrescimo Virtual
				item.percentualAcrescimoVirtual,

				// PERCMSADI - Percentual Comissao Adicional
				item.preco.percentualComissaoAdicional,

				// PERDSCBFC - Percentual Desconto Beneficio
				item.percentualDescontoBeneficio,

				// PERDSCFLX - Percentual Desconto Flexivel
				item.percentualDescontoFlex,

				// PERDSCMNM - Percentual Desconto Minimo
				item.desconto.percentualMinimoDesconto,

				// PERECOBFC - Percentual Economico Beneficio
				item.percentualEconomicoBeneficio,

				// PERICMMER - Percentual de ICM da Mercadoria
				item.preco.percentualICMS,

				// PERMAXFLX - Percentual Maximo Flexivel
				item.desconto.percentualDescontoFlexivel,

				// PERMAXSMP - Percentual Maximo Simplificado
				item.percentualDescontoSimplificado,

				// PERVLRMNM - Percentual Valor Minimo
				item.stb.percentualValorMinimo,

				// QDEMERPED - Quantidade da Mercadoria no Pedido
				item.quantidadeMercadoria,

				// QDEPRZBFC - Quantidade Prazo Beneficio
				item.quantidadePrazoBeneficiario,

				// RTCBFVMER - Restricao de Beneficio de Mercadoria
				item.indicaRestricaoBeneficioCustomizadoMercadoria,

				// STBCPLTOT - STB Complementar Total
				item.stb.valorSTBComplementarTotal,

				// STBTOTBNF - STB Total Bonificacao
				item.stb.valorSTBTotalBonificacao,

				// TIPDTBMER - Tipo Distribuicao Mercadoria
				item.codigoRegraDistribuicao, // TODO VERIFICAR SE item.tipoDistribuicaoMercadoria EH A MESMA COISA QUE item.codigoRegraDistribuicao,

				// TIPESGMER - Tipo Esgotamento Mercadoria
				item.mercadoria.tipoEsgotamento,

				// TIPICTMER - Tipo Incentivo Mercadoria
				item.preco.tipoIncentivoMercadoria,

				// TIPNGCMER - Tipo Negociacao Mercadoria
				item.tipoNegociacaoMercadoria,

				// VLRBNFMER - Valor Bonificacao Mercadoria
				item.valorBonificacao,

				// VLRBRTCXA - Valor Bruto Caixa
				item.preco.valorBrutoCaixa,

				// VLRBRTFRC - Valor Bruto Fracionado
				item.preco.valorBrutoFracionado,

				// VLRBRTMER - Valor Bruto Mercadoria
				item.preco.valorBrutoMercadoria,

				// VLRBRTTMP - Valor Bruto Temporario
				item.preco.valorBrutoTMP,

				// VLRDSCESP - Valor Desconto Especial
				item.desconto.valorDescontoEspecial,

				// VLRFRTMER - Valor do Frete da Mercadoria
				item.frete,

				// VLRIPIMER - Valor IPI Mercadoria
				item.mercadoria.percentualIPI.percentualIPI,

				// VLRIPITOT - Valor IPI Total Mercadoria
				item.valorIPITotal,

				// VLRLIQCXA - Valor Liquido Caixa
				item.valorLiquidoCaixa,

				// VLRLIQMER - Valor Liquido Mercadoria
				item.valorLiquidoMercadoria,

				// VLRMAXISN - Valor Maximo Mercadoria
				item.desconto.valorDescontoIsencao,

				// VLRMNMMER - Valor Minimo Mercadoria
				item.mercadoria.valorMinimoMercadoria,

				// VLRPTOBFC - Valor Pontos de Beneficio
				item.valorPontoBeneficioMercadoria,

				// VLRPTOMER - Valor Pontos
				item.valorPontuacaoMercadoria, // valorPontoMercadoria

				// VLRRDCITE - Valor Reducao Item
				item.valorReducao,

				// VLRSEMENC - Valor Sem Encargos
				item.valorSemEncargos,

				// VLRSTBCPL - Valor STB Complementar
				item.stb.valorSTBComplementar,

				// VLRSTBMER - Valor STB (Substituicao Tributaria) Mercadoria
				item.stb.valorSTBUnitario,

				// VLRSTBTOT - Valor STB Total
				item.stb.valorSTBTotal

		);
		return query;
	}
	
	public static boolean isPedidoExiste(Integer numeroPedido) {
		StringBuilder sql = new StringBuilder(60);
		sql.append("SELECT * FROM PCAPED WHERE NUMPED = %s");

		boolean isExiste = false;
		try {
			String query = DatabaseUtil.montaQuery(sql, numeroPedido);
			db = DatabaseFactory.getInstance();
			
			List<Map<String, String>> result = db.executeQuery(query);

			if (!result.isEmpty()) {
				isExiste = true;
			}

		} catch (Exception e) {
			Log.e("PedidoDAO - isPedidoExiste - ", e.getMessage());
		}	
		
		return isExiste;
	}
	
	public static boolean isPedidoPreparadoReprovado(Integer numeroPedido){
		boolean isExiste=true;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		StringBuilder sql = new StringBuilder();
				
		try {
			sql.append("SELECT SIT.CODSITPED,ITE.NUMPED,PED.FLGTRS ");
			sql.append("FROM PCAITE ITE ");
			sql.append("LEFT JOIN PCASIT SIT ");
			sql.append("ON SIT.NUMPED=ITE.NUMPED ");
			sql.append("LEFT JOIN PCAPED PED ");
			sql.append("ON ITE.NUMPED=PED.NUMPED ");
			sql.append("WHERE ITE.NUMPED=%s ");
			sql.append("AND SIT.CODSITPED=3 ");
			sql.append("OR SIT.CODSITPED=4 ");
			sql.append("AND PED.FLGTRS<>'' ");			
			
			String query = DatabaseUtil.montaQuery(sql, numeroPedido);
			db = DatabaseFactory.getInstance();
			result = db.executeQuery(query);
			
			if(result.isEmpty()){
				isExiste = false;
			}			
		} catch (Exception e) {
			Log.e("PedidoDAO - isPedidoPreparadoReprovado - ", e.getMessage());
		}		
		return isExiste;
	}
	public static boolean verificaMotivoCortes(Integer numeroPedido){
		boolean isExiste=true;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		StringBuilder sql = new StringBuilder();
				
		try {
			sql.append("SELECT distinct NUMPED from pcacrt WHERE NUMPED=%s ");
			sql.append("AND (CODMTVCTS=3 OR CODMTVCTS=8 OR CODMTVCTS=9 OR CODMTVCTS=16 OR CODMTVCTS=20) ");
						
			String query = DatabaseUtil.montaQuery(sql, numeroPedido);
			db = DatabaseFactory.getInstance();
			result = db.executeQuery(query);
			
			if(result.isEmpty()){
				isExiste = false;
			}			
		} catch (Exception e) {
			Log.e("PedidoDAO - verificaMotivoCortes - ", e.getMessage());
		}		
		return isExiste;
	}

	public static boolean excluiItensPedido(Pedido pedido) {
		boolean resultado = false;
		try {
			executaExclusaoItensPedido(Integer.valueOf(pedido.numeroPedido));
			resultado = true;
		} catch (Exception e) {
			Log.e("PedidoDAO - isPedidoPreparadoReprovado - ", e.getMessage());
		}
		return resultado;
	}
	
}
