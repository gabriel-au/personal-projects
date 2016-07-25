package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.dao.GrupoAfinidadePEEDAO;
import br.com.martins.vendas.dao.LivroPrecoDAO;
import br.com.martins.vendas.dao.MercadoriaDAO;
import br.com.martins.vendas.util.Constants;
import br.com.martins.vendas.vo.DetalheMercadoria;
import br.com.martins.vendas.vo.GrupoAfinidadePEE;
import br.com.martins.vendas.vo.LivroPreco;
import br.com.martins.vendas.vo.Produto;
import br.com.martins.vendas.vo.RelacaoGiro;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class ProdutoService {

	private static final String	TAG					= ProdutoService.class.getName();

	public static final int		VALOR_QUOTA_MAXIMA	= 99999;

	public static Produto carregaDadosProduto(Integer idProduto) throws Exception {

		Database db = DatabaseFactory.getInstance();
		StringBuilder query = new StringBuilder();
		query.append("select m.codmer as CodigoMercadoria,")
		.append("m.desmer as Mercadoria,m.ftrcnvunt as fatorConversao,")
		.append("m.qdecxafrn as Caixa, pc.vlrliqmer as PrecoLiq, pc.vlrfrtmer as ValorFrete,")
		.append("pc.codfilfat as Filial, pc.codfilepd as Cad, pc.pericmmer as ICM, pc.numnotfsc as NF,")
		.append("pc.codsmbsit as Simbolo, pc.qdeprzbfc as Prazo,pc.qdemerped as qtdMercadoria,")
		.append("pc.pervlrmnm as STB,pc.vlripimer as valorIpi,pc.vlrstbmer as valorStb,")
		.append("pc.codrgrdtb as ModeloDistribuicao,pc.codcndpgt as condPagamento,")
		.append("pc.codflxpco as codigoFlex,sdt.permrglcr as margemLucro,")
		.append("pc.cmscnsmer as comissao,pc.desngcmer as negociacao,pc.vlrptobfc as ponto,pc.perdscbfc as perDesconto,")
		.append("pc.vlrliqcxa as valorLiqCaixa,ipi.vlrpltipi as valorPltIpi,ipi.peripimer as PerIpiMercadoria,gde.datinivgr as dataInicialVigencia,")
		.append("sdt.percrdstb as perCreditoStb,sdt.perdbtstb as perDebitoStb,sdt.ftrrdcpco as fatorReducaoPreco, ")
		.append("sdt.tipdtzstb as tipoSTB, sdt.percrdstb as perCredito, sdt.flgvlrmnm as flagValorMnm")
		.append(" from cadmer as m ")
		.append("inner join TMPITEPE as pc ")
		.append("on m.codmer=pc.codmer ")
		.append("inner join pcaipi ipi ")
		.append("on ipi.codmer=m.codmer ")
		.append("inner join pcagde gde ")
		.append("on gde.codfilepd=pc.codfilepd ")
		.append("inner join pcasdt sdt ")
		.append("on sdt.datinivgr=gde.datinivgr ")
		.append("where m.codmer=" + idProduto);

		List<Map<String, String>> result = db.executeQuery(query.toString());
		return criaProduto(result);
	}

	private static Produto criaProduto(List<Map<String, String>> produto) throws Exception {
		Map<String, String> p = produto.get(0);

		Produto prod = new Produto();
		prod.codigo = Util.getInteger(p.get("CodigoMercadoria"));
		prod.nomeProduto = p.get("Mercadoria");
		prod.cad = Util.getInteger(p.get("Cad"));
		prod.caixa = Util.getInteger(p.get("Caixa"));
		prod.filial = Util.getInteger(p.get("Filial"));
		prod.icm = Util.getDouble(p.get("ICM"));
		prod.modeloDistribuicao = Util.getInteger(p.get("ModeloDistribuicao"));
		prod.notaFiscal = Util.getInteger(p.get("NF"));
		prod.prazo = Util.getInteger(p.get("Prazo"));
		prod.precoLiquido = Util.getDouble(p.get("PrecoLiq"));
		prod.stb = p.get("STB");
		prod.tipoEstoque = p.get("TipoEstoque");
		prod.simbolo = p.get("Simbolo");
		prod.incentivo = p.get("tipoIncentivo");
		prod.pontos = Util.getInteger(p.get("ponto"));
		prod.codigoFlex = Util.getInteger(p.get("codigoFlex"));
		prod.descNegociacao = p.get("negociacao");
		prod.valorComissao = Util.getDouble(p.get("comissao"));
		prod.condPagamento = Util.getInteger(p.get("condPagamento"));
		prod.valorIpiMercadoria = Util.getDouble(p.get("valorIpi"));
		prod.valorStbMercadoria = Util.getDouble(p.get("valorStb"));
		prod.fatorConversao = Util.getInteger(p.get("fatorConversao"));
		prod.valorLiqCaixa = Util.getDouble(p.get("valorLiqCaixa"));
		prod.tipoSTB = Util.getInteger(p.get("tipoSTB"));
		prod.dataInicialVigencia = DateUtil.formataData(p.get("dataInicialVigencia"), "yyyyMMdd");
		prod.perCredito = Util.getDouble(p.get("perCredito"));
		prod.valorPltIpi = Util.getDouble(p.get("valorPltIpi"));
		prod.qtdMercadoria = Util.getInteger(p.get("qtdMercadoria"));
		prod.perIpiMercadoria = Util.getDouble(p.get("PerIpiMercadoria"));
		prod.perDesconto = Util.getDouble(p.get("perDesconto"));
		prod.flagValorMnm = p.get("flagValorMnm");
		prod.perCreditoStb = Util.getDouble(p.get("perCreditoStb"));
		prod.perDebitoStb = Util.getDouble(p.get("perDebitoStb"));
		prod.fatorReducaoValor = Util.getDouble(p.get("fatorReducaoPreco"));
		prod.margemLucro = Util.getDouble(p.get("margemLucro"));

		return CalculaPrecoUnitarioCaixaImpFrete(prod);
	}
	
	public static Produto CalculaPrecoUnitarioCaixaImpFrete(Produto produto) {
		Double valorStbcaixa = CalcularStb(produto);
//		Double valorIpiCaixa = CalcularIpiCaixa(produto);
//		Double valorFrete = buscarValorFrete(produto.codigo);
		valorStbcaixa /= produto.qtdMercadoria;

//		Double valorCaixaImp = ((produto.valorLiqCaixa + valorStbcaixa + valorIpiCaixa + valorFrete) / produto.fatorConversao);
		return produto;
	}

	public static Double CalcularIpiCaixa(Produto produto) {
		Double valorIpiCaxTot = 0.;
		Double valorIpiCax = 0.;

		if (produto.valorPltIpi > 0) {
			valorIpiCaxTot = (produto.valorPltIpi * produto.qtdMercadoria) * produto.fatorConversao;

			if (valorIpiCaxTot < 0.00) {
				valorIpiCaxTot = 0.00;
			}
			valorIpiCax = valorIpiCaxTot / produto.qtdMercadoria;
		} else {

			Double valorEfe = produto.valorLiqCaixa / produto.fatorConversao;

			if (valorEfe < 0.00) {
				valorEfe = 0.01;
			}

			valorEfe *= produto.qtdMercadoria;
			valorIpiCaxTot = valorEfe * (produto.perIpiMercadoria / 100);
			valorIpiCaxTot *= produto.fatorConversao;

			if (valorIpiCaxTot < 0.00) {
				valorIpiCaxTot = 0.00;
			}
			valorIpiCax = valorIpiCaxTot / produto.qtdMercadoria;
		}
		return valorIpiCax;
	}

	public static Double CalcularStb(Produto produto) {
		Calendar hoje = Calendar.getInstance();
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(produto.dataInicialVigencia);

		Double valorStbTbtTot = 0.;
		Double valorEfeBnf = 0.;
		Double valorEfe = produto.precoLiquido * produto.fatorConversao;

		if (valorEfe <= 0.00) {
			valorEfe = 0.01;
		}

		valorEfe *= produto.qtdMercadoria;

		valorEfeBnf = valorEfe - ((produto.perDesconto / 100) * valorEfe);
		Double valorIpiEfe = produto.valorIpiMercadoria / produto.fatorConversao;

		valorEfe += valorIpiEfe;

		// Verifica se o STB nao está vencido e se ele não é
		// do tipo 1 (Isento)
		if (dataInicial.before(hoje) && produto.tipoSTB != 1) {
			if (produto.flagValorMnm.equals("*") || produto.flagValorMnm.equalsIgnoreCase("o") || produto.flagValorMnm.equalsIgnoreCase("v")) {
				// Obtem valor mínimo Estadual
				Double valorMnmEst = ObtemMinimoEstadual(produto.codigo);
				valorMnmEst *= produto.qtdMercadoria;

				if (valorMnmEst > 0.00) {
					if (produto.fatorReducaoValor <= 0.00) {
						produto.fatorReducaoValor = 1.00;
					}
					if ((((valorEfe * produto.fatorReducaoValor) * (1 + produto.margemLucro / 100)) <= valorMnmEst)
							|| (produto.flagValorMnm.equalsIgnoreCase("o") || (produto.flagValorMnm.equalsIgnoreCase("v")))) {
						if (produto.flagValorMnm.equalsIgnoreCase("o")) {
							valorStbTbtTot = ((valorMnmEst * produto.fatorReducaoValor) * (1 + produto.margemLucro / 100) * (produto.perDebitoStb / 100))
									- ((valorMnmEst * produto.fatorReducaoValor) * (produto.perCreditoStb / 100));

							if (valorStbTbtTot < 0.00) {
								valorStbTbtTot = 0.00;
							}
						} else {
							valorStbTbtTot = ((valorEfe * produto.fatorReducaoValor) * (1 + produto.margemLucro / 100) * (produto.perDebitoStb / 100))
									- ((valorEfeBnf * produto.fatorReducaoValor) * (produto.perCreditoStb / 100));
						}
					} else {
						// Obtém valor mínimo Nacional
						Double valorMnmNcn = ObtemMinimoNacional(produto.codigo);
						valorMnmNcn *= produto.qtdMercadoria;

						if (produto.fatorReducaoValor < 0.00) {
							produto.fatorReducaoValor = 1.00;
						}
						if ((valorMnmNcn > 0.00) && ((valorEfe * produto.fatorReducaoValor) * (1 + (produto.margemLucro / 100)) <= valorMnmNcn)) {
							valorStbTbtTot = (valorMnmNcn * (produto.perDebitoStb / 100)) - ((valorEfeBnf * produto.fatorReducaoValor) * (produto.perCreditoStb / 100));
						} else {
							valorStbTbtTot = (((valorEfe * produto.fatorReducaoValor) * (1 + produto.margemLucro / 100) * (produto.perDebitoStb / 100)) - (valorEfeBnf * produto.fatorReducaoValor)
									* (produto.perCreditoStb / 100));
						}
					}
				} else {
					if (produto.fatorReducaoValor <= 0.00) {
						produto.fatorReducaoValor = 1.00;
					}
					valorStbTbtTot = ((valorEfe * produto.fatorReducaoValor) * (1 + produto.margemLucro / 100) * (produto.perDebitoStb / 100)) - (valorEfeBnf * produto.fatorReducaoValor)
							* (produto.perCreditoStb / 100);
				}
				valorStbTbtTot *= produto.fatorConversao;
			}
		}
		if (valorStbTbtTot < 0.00) {
			valorStbTbtTot = 0.00;
		}

		return valorStbTbtTot;
	}

	public static Double ObtemMinimoEstadual(Integer codmer) {
		Double valorMnmEst = 0.;

		try {
			StringBuilder sb = new StringBuilder();
			sb.append(" select vlrmnmest from PCASGM where codmer=" + codmer);
			Database db = DatabaseFactory.getInstance();
			List<Map<String, String>> result = db.executeQuery(sb.toString());
			Map<String, String> p = result.get(0);
			valorMnmEst = Util.getDouble(p.get("vlrmnmest"));

			if (valorMnmEst == null) {
				valorMnmEst = 0.;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorMnmEst;
	}

	public static Double buscarValorFrete(Integer codmer) {
		Double valorFrete = 0.;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select vlruntfrt from pcafrt where codmer=" + codmer);
			Database db = DatabaseFactory.getInstance();
			List<Map<String, String>> result = db.executeQuery(sb.toString());
			Map<String, String> p = result.get(0);
			valorFrete = Util.getDouble(p.get("vlruntfrt"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		return valorFrete;
	}

	public static Double ObtemMinimoNacional(Integer codmer) {
		Double valorMnmNcn = 0.;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select vlrmnmnac from PCAsmn where codmer=" + codmer);
			Database db = DatabaseFactory.getInstance();
			List<Map<String, String>> result = db.executeQuery(sb.toString());
			Map<String, String> p = result.get(0);
			valorMnmNcn = Util.getDouble(p.get("vlrmnmnac"));

			if (valorMnmNcn == null) {
				valorMnmNcn = 0.;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return valorMnmNcn;
	}

	/**
	 * Carrega dos detalhes do Produto
	 * 
	 * @param codMercadoria
	 *            código do produto (merdadoria)
	 * 
	 * @param codFilialExpedicaoMercadoria
	 *            código filial expedição do item (mercadoria)
	 * 
	 * @param codFilialFaturamentoMercadoria
	 *            código filial faturamento do item (mercadoria)
	 * 
	 * @param codCliente
	 *            código do cliente
	 * 
	 * @param codGrupoCliente
	 *            código grupo do cliente
	 * 
	 * @param codTerCliente
	 *            código do território do cliente
	 * 
	 * @param codEstadoDestino
	 *            código do estado de destino da agenda mercadoria
	 * 
	 * @return instância da classe DetalheMercadoria com seus atributos populados
	 * @throws Exception 
	 */
	public static DetalheMercadoria carregaDetalheProduto(Integer codMercadoria, Integer codFilialExpedicaoMercadoria, Integer codFilialFaturamentoMercadoria, Integer codCliente, Integer codTerCliente, Integer codRegraDistribuicao, String codEstadoDestino) throws Exception {
	
		try {

			DetalheMercadoria detalheMercadoria = MercadoriaDAO.recupera(codMercadoria, codFilialExpedicaoMercadoria, codFilialFaturamentoMercadoria, codCliente, codTerCliente, codEstadoDestino);
			
			if (codRegraDistribuicao == 0) {
				detalheMercadoria.descModeloDistribuicao = ModeloDistribuicaoService.preencheTransporteProprio().razSocialTransportadora;
			} else {
				detalheMercadoria.descModeloDistribuicao = ModeloDistribuicaoService.recuperaModeloDistribuicao(codCliente, codRegraDistribuicao).razSocialTransportadora;
			}
			RelacaoGiro relacaoGiro = RelacaoGiroService.recuperarPorFilial(codTerCliente, codFilialExpedicaoMercadoria, codFilialFaturamentoMercadoria);
			if (relacaoGiro != null) {
				LivroPreco livroPreco = LivroPrecoDAO.recupera(relacaoGiro.codFilialExpedicao, relacaoGiro.codFilialFaturamento, relacaoGiro.numRelacaoCidade, codMercadoria);
				if (livroPreco != null) {
					detalheMercadoria.pee = Constants.FLG_ASTERISTICO.equals(livroPreco.flgPontoEncontroEletronico);
					detalheMercadoria.tipMarcacaoRepresentande = livroPreco.tipMarcacaoRepresentante;
				}
			}
			
			return detalheMercadoria;

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage(), e);
		}
		
		return null;
	}

	/**
	 * Verifica se o produto esta no mix
	 * 
	 * @param codigoProduto
	 *            código do produto
	 * 
	 * @return <code>true</code> caso possua e <code>false</code> caso contrário.
	 */
	public static boolean produtoEstaNoMix(Integer codigoGrupoCliente, Integer codigoProduto) {

		Database dataBase = DatabaseFactory.getInstance();
		try {

			List<Map<String, String>> result = dataBase.executeQuery(DatabaseUtil.montaQuery("SELECT COUNT(*) FROM PCAMIX WHERE CODGRPCLI = %s AND CODMER = %s", codigoGrupoCliente, codigoProduto));

			return result.isEmpty();

		} catch (SQLException e) {

			Log.e(TAG, e.getLocalizedMessage(), e);
		}
		return false;
	}


	/**
	 * 
	 * @param produtoId
	 * @param codTerCli
	 * @param itefilFat
	 * @param codGrpAfd
	 * @param pee
	 * @param peeInicial
	 * @return
	 * @throws Exception 
	 */
	public static boolean atualizarPontoEncontroEletronico(Integer produtoId, Integer codTerCli, Integer itefilFat, Integer codGrpAfd, boolean pee, boolean peeInicial, Integer codigoFilialExpedicao) throws Exception {
		
		TransactionManager transactionManager = null;
		try {			
			RelacaoGiro agendaLivroPreco = RelacaoGiroService.recuperarPorFilial(codTerCli, codigoFilialExpedicao, itefilFat);
			if (agendaLivroPreco != null) {

				GrupoAfinidadePEE grupoAfinidade = new GrupoAfinidadePEE();

				grupoAfinidade.codMercadoria		= produtoId;
				grupoAfinidade.codFilialExpedicao 	= agendaLivroPreco.codFilialExpedicao;
				grupoAfinidade.codFilialFaturamento = agendaLivroPreco.codFilialFaturamento;
				grupoAfinidade.numRelacaoCidade 	= agendaLivroPreco.numRelacaoCidade;

				grupoAfinidade.flgTransferencia = null;
				grupoAfinidade.flgAtualizacao 	= null;

				grupoAfinidade.codGrupoAfinidade = codGrpAfd;

				Calendar current = DateUtil.obterDataAtualTruncada();
				grupoAfinidade.datPontoEncontroEletronico = DateUtil.formataData(current.getTime(), DateUtil.DEFAULT_DATE_DATABASE);

				grupoAfinidade.idtInformcaoPEE = "R";

				current.add(Calendar.DAY_OF_YEAR, 7);
				grupoAfinidade.datValidadeAfinidade = DateUtil.formataData(current.getTime(), DateUtil.DEFAULT_DATE_DATABASE);

				transactionManager = DatabaseFactory.getInstance().getTransactionManager();
				transactionManager.beginTransaction();
				
				if (pee) {
					GrupoAfinidadePEEDAO.insereOuAtualiza(transactionManager, grupoAfinidade);
					
					LivroPrecoDAO.atualizaPEE(transactionManager, grupoAfinidade.codFilialExpedicao, grupoAfinidade.codFilialFaturamento, grupoAfinidade.numRelacaoCidade, grupoAfinidade.codMercadoria, Constants.FLG_ASTERISTICO);
					
					grupoAfinidade.flgAtualizacao = Constants.FLG_ASTERISTICO;
					if (peeInicial) {
						grupoAfinidade.flgTransferencia = Constants.FLG_ASTERISTICO;
					}

					GrupoAfinidadePEEDAO.insereOuAtualiza(transactionManager, grupoAfinidade);
					
				} else {

					GrupoAfinidadePEEDAO.deleta(transactionManager, grupoAfinidade);
					
					LivroPrecoDAO.atualizaPEE(transactionManager, grupoAfinidade.codFilialExpedicao, grupoAfinidade.codFilialFaturamento, grupoAfinidade.numRelacaoCidade, grupoAfinidade.codMercadoria, null);
				}

				transactionManager.commitTransaction();

				return true;
			}			
		
		} catch (SQLException e) {
			
			transactionManager.rollbackTransaction();
			
			Log.e(TAG, e.getLocalizedMessage(), e);
			throw e;
			
		} finally {
			
			transactionManager.endTransaction();
		}
		
		return false;
	}

	/**
	 * 
	 * @param produtoId
	 * @param codTerCli
	 * @param itefilFat
	 * @param codGrpAfd
	 * @param numlst
	 * @return
	 * @throws Exception 
	 */
	public static boolean atualizarMarcacaoRepresentante(Integer produtoId, Integer codTerCli, Integer itefilFat, Integer codGrpAfd, Integer numlst,Integer codigoFilialExpedicao) throws Exception {

		try {			
			RelacaoGiro relacaoGiro = RelacaoGiroService.recuperarPorFilial(codTerCli, codigoFilialExpedicao, itefilFat);
			if (relacaoGiro != null) {

				GrupoAfinidadePEE grupoAfinidade = new GrupoAfinidadePEE();

				grupoAfinidade.codMercadoria		= produtoId;
				grupoAfinidade.codFilialExpedicao 	= relacaoGiro.codFilialExpedicao;
				grupoAfinidade.codFilialFaturamento = relacaoGiro.codFilialFaturamento;
				grupoAfinidade.numRelacaoCidade 	= relacaoGiro.numRelacaoCidade;
					
				LivroPrecoDAO.atualizaMarcacaoRepresentante(grupoAfinidade.codFilialExpedicao, grupoAfinidade.codFilialFaturamento, grupoAfinidade.numRelacaoCidade, grupoAfinidade.codMercadoria, numlst);

				return true;
			}			
		
		} catch (SQLException e) {
			
			Log.e(TAG, e.getLocalizedMessage(), e);
			throw e;
			
		}
		
		return false;
		
	}

}
