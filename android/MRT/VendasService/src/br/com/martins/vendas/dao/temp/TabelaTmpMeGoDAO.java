package br.com.martins.vendas.dao.temp;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.martins.vendas.services.calculodepreco.Imposto;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.services.desconto.Desconto;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.PercentualIPI;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.dao.Database;
import com.brq.mobile.framework.dao.DatabaseFactory;
import com.brq.mobile.framework.dao.TransactionManager;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DatabaseUtil;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.Util;

public class TabelaTmpMeGoDAO {

	private static final String TAG = TabelaTmpMeGoDAO.class.getName();

	private static Database db;

	/**
	 * Método responsável por remover todos os dados da tabela TMPMEGO.
	 * 
	 * @return <code>true</code> se os dados da tabela foram removidos com
	 *         sucesso, e <code>false</code> caso tenha ocorrido algum problema
	 *         que tenha impedido a remoção dos dados da tabela.
	 */
	public static boolean limpaTabela() {
		String sql = " delete from TMPMEGO ";
		boolean resultado = false;

		db = DatabaseFactory.getInstance();

		try {
			db.executeSQL(sql);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpMeGoDAO - limpaTabela - ", e.getMessage());
		}

		return resultado;
	}

	public static boolean insereItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into TMPMEGO ( ");
		sql.append(" CMSCNSMER, "); // Comissao Concessao Mercadoria  
		sql.append(" CMSMAXMER, "); // Maximo Comissao Mercadoria  
		sql.append(" CODCHVRGT, ");
		sql.append(" CODCLI, ");     
		sql.append(" CODCNDPGT, "); // Codigo Condicao de Pagamento  
		sql.append(" CODCORMRG, "); // Codigo Cor de Margem  
		sql.append(" CODCORMRGB, "); // Codigo Cor de Margem B 
		sql.append(" CODFILEPD, "); // Codigo Filial Expedicao  
		sql.append(" CODFILFAT, "); // Codigo de Filial de Faturamento  
		sql.append(" CODFLXPCO, "); // Codigo Preco Flexivel  
		sql.append(" CODMER, "); // Codigo da Mercadoria     
		sql.append(" CODMERPCP, "); // Codigo Mercadoria Principal  
		sql.append(" CODPMC, "); // Codigo Promocao     
		sql.append(" CODRGRDTB, "); // Codigo Regra Distribuicao  
		sql.append(" CODSMBSIT, "); // Codigo Simbolo Situacao  
		sql.append(" DATULTVND, ");
		sql.append(" DESMER, "); // Descricao da Mercadoria     
		sql.append(" DESNGCMER, "); // Descricao Negociacao Mercadoria  
		sql.append(" FLGPEE, "); // Flag PEE     
		sql.append(" FLGSTSITE, "); //  
		sql.append(" INDITEIMU, "); // Indica Item Imune  
		sql.append(" INDITEVND, "); // Indica Item de Venda  
		sql.append(" INDMERKIT, "); // Indica Mercadoria Kit  
		sql.append(" INDNVO, "); // Indica Novo     
		sql.append(" INDRTCBFB, "); // Indica Restricao Beneficio Customizado no Brinde  
		sql.append(" INDRTCBFV, "); // Indica Restricao Beneficio Customizado  
		sql.append(" INDRTCBNF, "); // Indica Restricao de Item como Brinde  
		sql.append(" INDSEMMRGL, "); // Indica Sem Margem de Lucro 
		sql.append(" IPITOTBNF, "); // Valor IPI Total Bonificacao  
		sql.append(" LIQCXAIMP, "); // Valor Liquido Caixa com Imposto  
		sql.append(" MRGLCRCLI, ");  
		sql.append(" NUMNOTFSC, "); // Numero da Nota Fiscal  
		sql.append(" PCOCLI, ");     
		sql.append(" PERACOCLI, "); // Percentual de Acao do Cliente  
		sql.append(" PERACOTTC, "); // Percentual de Acao Tatica  
		sql.append(" PERACRCNS, "); // Percentual Acrescimo Concedido  
		sql.append(" PERACRVTL, "); // Percentual de Acrescimo Virtual  
		sql.append(" PERDSCFLX, "); // Percentual Desconto Flexivel  
		sql.append(" PERDSCMNM, "); // Percentual Desconto Minimo  
		sql.append(" PERICMMER, "); // Percentual de ICM da Mercadoria  
		sql.append(" PERMAXFLX, "); // Percentual Maximo Flexivel  
		sql.append(" PERMAXSMP, "); // Percentual Maximo Simplificado  
		sql.append(" PERVLRMNM, "); // Percentual Valor Minimo  
		sql.append(" POSMER, ");     
		sql.append(" QDECXAFRN, "); // Quantidade Caixa Fornecedor  
		sql.append(" QDEMERANT, ");  
		sql.append(" QDEMERATU, ");  
		sql.append(" QDEMERPED, "); // Quantidade da Mercadoria no Pedido  
		sql.append(" RTCBFVMER, "); // Restricao de Beneficio de Mercadoria  
		sql.append(" STBTOTBNF, "); // STB Total Bonificacao  
		sql.append(" TIPICT, "); // Tipo de Incentivo     
		sql.append(" TIPMCOREP, "); // Tipo Marcacao Representante (Listas Customizadas)  
		sql.append(" TIPMERDSC, "); // Tipo Desconto Mercadoria  
		sql.append(" TIPMERESG, "); // Tipo de Esgotamento da Mercadoria  
		sql.append(" TIPNGCMER, "); // Tipo Negociacao Mercadoria  
		sql.append(" TIPPMC, "); // Tipo Promocao     
		sql.append(" UNTCXAIMP, "); // Valor Unitario da Caixa com Imposto  
		sql.append(" VLRBNFMER, "); // Valor Bonificacao Mercadoria  
		sql.append(" VLRBRTCXA, "); // Valor Bruto Caixa  
		sql.append(" VLRBRTFRC, "); // Valor Bruto Fracionado  
		sql.append(" VLRBRTMER, "); // Valor Bruto Mercadoria  
		sql.append(" VLRBRTTMP, "); // Valor Bruto Temporario  
		sql.append(" VLRDSCESP, "); // Valor Desconto Especial  
		sql.append(" VLRFRTMER, "); // Valor do Frete da Mercadoria  
		sql.append(" VLRIPIMER, "); // Valor IPI Mercadoria  
		sql.append(" VLRIPITOT, "); // Valor IPI Total Mercadoria  
		sql.append(" VLRLIQCXA, "); // Valor Liquido Caixa  
		sql.append(" VLRLIQIMP, "); // Valor Liquido da Mercadoria com Imposto  
		sql.append(" VLRLIQMER, "); // Valor Liquido Mercadoria  
		sql.append(" VLRMAXISN, "); // Valor Maximo Isencao  
		sql.append(" VLRMNMMER, "); // Valor Minimo Mercadoria  
		sql.append(" VLRPTOBFC, "); // Valor Pontos de Beneficio  
		sql.append(" VLRPTOMER, "); // Valor Pontos  
		sql.append(" VLRRDCITE, "); // Valor Reducao Item  
		sql.append(" VLRSTBMER, "); // Valor STB (Substituicao Tributaria) Mercadoria  
		sql.append(" VLRSTBTOT, "); // Valor STB Total  
		sql.append(" VLRUNTIMP "); // Valor Unitario com Imposto  
		sql.append(" ) values ( ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, ");
		sql.append(" %s, %s, %s, %s, %s, %s, %s ");
		sql.append(" ); ");

		boolean resultado = false;
		db = DatabaseFactory.getInstance();
		String query = montaQueryItem(item, sql);

		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpMeGoDAO - insereItem - ", e.getMessage());
		}

		return resultado;
	}

	public static boolean insereItem(Item item, TransactionManager transactionManager) {
		Map<String, String> values = new HashMap<String, String>();

		// Comissao Concessao Mercadoria
		values.put("CMSCNSMER", Util.getStringValue(item.comissao.comissaoMercadoria));
				
		// Maximo Comissao Mercadoria
		values.put("CMSMAXMER", Util.getStringValue(item.comissao.comissaoMaximaMercadoria));
				
		// CODCHVRGT - Codigo Chave Registro
		values.put("CODCHVRGT", Util.getStringValue(item.codigoChaveRegistro));
		
		// CODCLI - Codigo Cliente
		values.put("CODCLI", Util.getStringValue(item.codigoCliente));
		
		// Codigo Condição de Pagamento
		values.put("CODCNDPGT", Util.getStringValue(item.condicaoPagamento.codigoCondicaoPagamento));
		
		// Codigo Cor de Margem
		values.put("CODCORMRG", Util.getStringValue(item.codigoCorMargem));
				
		// Codigo Cor de Margem B
		values.put("CODCORMRGB", Util.getStringValue(item.codigoCorMargemB));
				
		// Codigo Filial
		values.put("CODFILEPD", Util.getStringValue(item.codigoFilialExpedicao));
				
		// Codigo de Filial de Faturamento
		values.put("CODFILFAT", Util.getStringValue(item.codigoFilialFaturamento));
				
		// Codigo Preco Flexivel
		values.put("CODFLXPCO", item.preco.codigoFlexivelPreco);
				
		// Codigo da Mercadoria
		values.put("CODMER", Util.getStringValue(item.mercadoria.codigo));
				
		// Codigo Mercadoria Principal
		values.put("CODMERPCP", Util.getStringValue(item.mercadoria.codigoMercadoriaPrincipal));
				
		// Codigo Promocao
		values.put("CODPMC", Util.getStringValue(item.promocao.codigo));
				
		// Codigo Regra Distribuicao
		values.put("CODRGRDTB", Util.getStringValue(item.regraDistribuicao.codigoRegra));
				
		// Codigo Simbolo Situacao
		values.put("CODSMBSIT", item.preco.codigoSimboloSituacao);
				
		// DATULTVND - Data Ultima Venda
		values.put("DATULTVND", Util.getStringValue(item.dataUltimaVenda));

		// Descricao da Mercadoria
		values.put("DESMER", item.mercadoria.descricao);
		
		// Descricao Negociacao Mercadoria
		values.put("DESNGCMER", item.mercadoria.descricaoNegocioMercadoria);
				
		// Flag PEE
		values.put("FLGPEE", Util.getStringValue(item.mercadoria.flagPee));
				
		// Flag Simbolo Situacao
		values.put("FLGSTSITE", Util.getStringValue(item.preco.flagSimboloSituacao));
				
		// Indica Item Imune
		values.put("INDITEIMU", Util.getStringValue(item.indicaItemImune));
				
		// Indica Item de Venda
		values.put("INDITEVND", Util.getStringValue(item.indicaItemVenda));
				
		// Indica Mercadoria Kit
		values.put("INDMERKIT", Util.getStringValue(item.mercadoria.indicaMercadoriaKit));
				
		// Idicador de item novo
		values.put("INDNVO", item.indicaItemNovo);
				
		// Indica Restricao Beneficio Customizado no Brinde
		values.put("INDRTCBFB", Util.getStringValue(item.indicaRestricaoBeneficioCustomizadoBrinde));
				
		// Indica Restricao Beneficio Customizado
		values.put("INDRTCBFV", Util.getStringValue(item.indicaRestricaoBeneficioCustomizado));
				
		// Indica Restricao de Item como Brinde
		values.put("INDRTCBNF", Util.getStringValue(item.indicaRestricaoItemBrinde));
				
		// Indica Sem Margem de Lucro
		values.put("INDSEMMRGL", Util.getStringValue((item.stb.temMargemDeLucro ? '1' : '0')));
				
		// Valor IPI Total Bonificacao
		values.put("IPITOTBNF", Util.getStringValue(item.valorIPITotalBonificacao));
				
		// Valor Liquido Caixa com Imposto
		values.put("LIQCXAIMP", Util.getStringValue(item.valorCaixaComImposto));
				
		// Migracao Lucro Cliente
		values.put("MRGLCRCLI", Util.getStringValue(item.migracaoLucroCliente));

		// Numero da Nota Fiscal
		values.put("NUMNOTFSC", Util.getStringValue(item.notaFiscal));
		
		// Preco Cliente
		values.put("PCOCLI", Util.getStringValue(item.precoCliente));

		// Percentual de Acao do Cliente
		values.put("PERACOCLI", Util.getStringValue(item.desconto.percentualDescontoAcao));
		
		// Percentual de Acao Tatica
		values.put("PERACOTTC", Util.getStringValue(item.percentualDescontoAcaoTatica));
				
		// Percentual Acrescimo Concedido
		values.put("PERACRCNS", Util.getStringValue(item.percentualAcrescimoConcedido));
				
		// Percentual de Acrescimo Virtual
		values.put("PERACRVTL", Util.getStringValue(item.percentualAcrescimoVirtual));
				
		// Percentual Desconto Flexivel
		values.put("PERDSCFLX", Util.getStringValue(item.percentualDescontoFlex));
				
		// Percentual Desconto Minimo
		values.put("PERDSCMNM", Util.getStringValue(item.desconto.percentualMinimoDesconto));
				
		// Percentual de ICM da Mercadoria
		values.put("PERICMMER", Util.getStringValue(item.preco.percentualICMS));

		// Percentual Maximo Flexivel
		values.put("PERMAXFLX", Util.getStringValue(item.desconto.percentualDescontoFlexivel));
				
		// Percentual Maximo Simplificado
		values.put("PERMAXSMP", Util.getStringValue(item.percentualDescontoSimplificado));
				
		//Expedicao Percentual Valor Minimo
		values.put("PERVLRMNM", Util.getStringValue(item.stb.percentualValorMinimo));
				
		// Posicao Mercadoria
		values.put("POSMER", Util.getStringValue(item.mercadoria.posicaoMercadoria));

		// Quantidade Caixa Fornecedor
		values.put("QDECXAFRN", Util.getStringValue(item.mercadoria.quantidadeCaixaFornecedor));
		
		// QDEMERANT - Quantidade Mercadoria Anterior
		values.put("QDEMERANT", Util.getStringValue(item.mercadoria.quantidadeMercadoriaAnterior));
		
		// QDEMERATU - Quantidade Mercadoria Atual
		values.put("QDEMERATU", Util.getStringValue(item.mercadoria.quantidadeMercadoriaAtual));

		// Quantidade da Mercadoria no Pedido
		values.put("QDEMERPED", Util.getStringValue(item.quantidadeMercadoria));
		
		// Restricao de Beneficio de Mercadoria
		values.put("RTCBFVMER", Util.getStringValue(item.indicaRestricaoBeneficioCustomizadoMercadoria));
				
		// STB Total Bonificacao
		values.put("STBTOTBNF", Util.getStringValue(item.stb.valorSTBTotalBonificacao));
				
		// Tipo de Incentivo
		values.put("TIPICT", item.preco.tipoIncentivoMercadoria);
				
		// Tipo Marcação Representante (Listas Customizadas)
		values.put("TIPMCOREP", item.preco.tipoMarcacaoRepresentante);
				
		// Tipo Desconto Mercadoria
		values.put("TIPMERDSC", item.desconto.tipo);
				
		// Tipo de Esgotamento da Mercadoria
		values.put("TIPMERESG", item.mercadoria.tipoEsgotamento);
				
		// Mercadoria Tipo Negociacao Mercadoria
		values.put("TIPNGCMER", Util.getStringValue(item.tipoNegociacaoMercadoria));
				
		// Tipo Promocao
		values.put("TIPPMC", Util.getStringValue(item.promocao.tipo));
				
		// Valor Unitario da Caixa com Imposto
		values.put("UNTCXAIMP", Util.getStringValue(item.valorUnitarioCaixaComImposto));
				
		// Valor Bonificacao
		values.put("VLRBNFMER", Util.getStringValue(item.valorBonificacao));
				
		// Valor Bruto Caixa
		values.put("VLRBRTCXA", Util.getStringValue(item.preco.valorBrutoCaixa));
				
		// Caixa Valor Bruto Fracionado
		values.put("VLRBRTFRC", Util.getStringValue(item.preco.valorBrutoFracionado));
				
		// Valor Bruto Mercadoria
		values.put("VLRBRTMER", Util.getStringValue(item.preco.valorBrutoMercadoria));
				
		// Valor Bruto Temporario
		values.put("VLRBRTTMP", Util.getStringValue(item.preco.valorBrutoTMP));
				
		// Valor Desconto Especial
		values.put("VLRDSCESP", Util.getStringValue(item.desconto.valorDescontoEspecial));
				
		// Valor do Frete da Mercadoria
		values.put("VLRFRTMER", Util.getStringValue(item.frete));
				
		// Valor IPI Mercadoria
		values.put("VLRIPIMER", Util.getStringValue(item.mercadoria.percentualIPI.percentualIPI));
				
		// Valor IPI Total Mercadoria
		values.put("VLRIPITOT", Util.getStringValue(item.valorIPITotal));
				
		// Valor Liquido 
		values.put("VLRLIQCXA", Util.getStringValue(item.valorLiquidoCaixa));
				
		// Valor Liquido da Mercadoria com Imposto
		values.put("VLRLIQIMP", Util.getStringValue(item.valorLiquidoComImposto));
				
		// Valor Liquido Mercadoria
		values.put("VLRLIQMER", Util.getStringValue(item.valorLiquidoMercadoria));
				
		// Valor Maximo Isencao
		values.put("VLRMAXISN", Util.getStringValue(item.mercadoria.valorMaximoMercadoria));
				
		// Valor Minimo Mercadoria
		values.put("VLRMNMMER", Util.getStringValue(item.mercadoria.valorMinimoMercadoria));
				
		// Valor Pontos de Beneficio
		values.put("VLRPTOBFC", Util.getStringValue(item.valorPontoBeneficioMercadoria));
				
		// Valor Pontos
		values.put("VLRPTOMER", Util.getStringValue(item.mercadoria.valorPontos));
				
		// Valor Reducao Item
		values.put("VLRRDCITE", Util.getStringValue(item.valorReducao));
				
		// Valor STB (Substituicao Tributaria) Mercadoria
		values.put("VLRSTBMER", Util.getStringValue(item.stb.valorSTBUnitario));
				
		// Valor STB Total
		values.put("VLRSTBTOT", Util.getStringValue(item.stb.valorSTBTotal));
				
		// Valor Unitario com Imposto
		values.put("VLRUNTIMP", Util.getStringValue(item.valorUnitarioComImposto));

		try {
			transactionManager.insert("TMPMEGO", values);
		} catch (SQLException e) {
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return true;
	}
	
	public static boolean alteraItem(Item item) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE TMPMEGO SET ");
		sql.append(" CMSCNSMER	= %s, "); // Comissao Concessao Mercadoria  
		sql.append(" CMSMAXMER	= %s, "); // Maximo Comissao Mercadoria  
		sql.append(" CODCHVRGT	= %s, ");
		sql.append(" CODCLI		= %s, ");     
		sql.append(" CODCNDPGT	= %s, "); // Codigo Condicao de Pagamento  
		sql.append(" CODCORMRG	= %s, "); // Codigo Cor de Margem  
		sql.append(" CODCORMRGB	= %s, "); // Codigo Cor de Margem B 
		sql.append(" CODFILEPD	= %s, "); // Codigo Filial Expedicao  
		sql.append(" CODFILFAT	= %s, "); // Codigo de Filial de Faturamento  
		sql.append(" CODFLXPCO	= %s, "); // Codigo Preco Flexivel  
		sql.append(" CODMER		= %s, "); // Codigo da Mercadoria     
		sql.append(" CODMERPCP	= %s, "); // Codigo Mercadoria Principal  
		sql.append(" CODPMC		= %s, "); // Codigo Promocao     
		sql.append(" CODRGRDTB	= %s, "); // Codigo Regra Distribuicao  
		sql.append(" CODSMBSIT	= %s, "); // Codigo Simbolo Situacao  
		sql.append(" DATULTVND	= %s, ");
		sql.append(" DESMER		= %s, "); // Descricao da Mercadoria     
		sql.append(" DESNGCMER	= %s, "); // Descricao Negociacao Mercadoria  
		sql.append(" FLGPEE		= %s, "); // Flag PEE     
		sql.append(" FLGSTSITE	= %s, "); //  
		sql.append(" INDITEIMU	= %s, "); // Indica Item Imune  
		sql.append(" INDITEVND	= %s, "); // Indica Item de Venda  
		sql.append(" INDMERKIT	= %s, "); // Indica Mercadoria Kit  
		sql.append(" INDNVO		= %s, "); // Indica Novo     
		sql.append(" INDRTCBFB	= %s, "); // Indica Restricao Beneficio Customizado no Brinde  
		sql.append(" INDRTCBFV	= %s, "); // Indica Restricao Beneficio Customizado  
		sql.append(" INDRTCBNF	= %s, "); // Indica Restricao de Item como Brinde  
		sql.append(" INDSEMMRGL	= %s, "); // Indica Sem Margem de Lucro 
		sql.append(" IPITOTBNF	= %s, "); // Valor IPI Total Bonificacao  
		sql.append(" LIQCXAIMP	= %s, "); // Valor Liquido Caixa com Imposto  
		sql.append(" MRGLCRCLI	= %s, ");  
		sql.append(" NUMNOTFSC	= %s, "); // Numero da Nota Fiscal  
		sql.append(" PCOCLI		= %s, ");     
		sql.append(" PERACOCLI	= %s, "); // Percentual de Acao do Cliente  
		sql.append(" PERACOTTC	= %s, "); // Percentual de Acao Tatica  
		sql.append(" PERACRCNS	= %s, "); // Percentual Acrescimo Concedido  
		sql.append(" PERACRVTL	= %s, "); // Percentual de Acrescimo Virtual  
		sql.append(" PERDSCFLX	= %s, "); // Percentual Desconto Flexivel  
		sql.append(" PERDSCMNM	= %s, "); // Percentual Desconto Minimo  
		sql.append(" PERICMMER	= %s, "); // Percentual de ICM da Mercadoria  
		sql.append(" PERMAXFLX	= %s, "); // Percentual Maximo Flexivel  
		sql.append(" PERMAXSMP	= %s, "); // Percentual Maximo Simplificado  
		sql.append(" PERVLRMNM	= %s, "); // Percentual Valor Minimo  
		sql.append(" POSMER     = %s, ");     
		sql.append(" QDECXAFRN	= %s, "); // Quantidade Caixa Fornecedor  
		sql.append(" QDEMERANT	= %s, ");  
		sql.append(" QDEMERATU	= %s, ");  
		sql.append(" QDEMERPED	= %s, "); // Quantidade da Mercadoria no Pedido  
		sql.append(" RTCBFVMER	= %s, "); // Restricao de Beneficio de Mercadoria  
		sql.append(" STBTOTBNF	= %s, "); // STB Total Bonificacao  
		sql.append(" TIPICT		= %s, "); // Tipo de Incentivo     
		sql.append(" TIPMCOREP	= %s, "); // Tipo Marcacao Representante (Listas Customizadas)  
		sql.append(" TIPMERDSC	= %s, "); // Tipo Desconto Mercadoria  
		sql.append(" TIPMERESG	= %s, "); // Tipo de Esgotamento da Mercadoria  
		sql.append(" TIPNGCMER	= %s, "); // Tipo Negociacao Mercadoria  
		sql.append(" TIPPMC		= %s, "); // Tipo Promocao     
		sql.append(" UNTCXAIMP	= %s, "); // Valor Unitario da Caixa com Imposto  
		sql.append(" VLRBNFMER	= %s, "); // Valor Bonificacao Mercadoria  
		sql.append(" VLRBRTCXA	= %s, "); // Valor Bruto Caixa  
		sql.append(" VLRBRTFRC	= %s, "); // Valor Bruto Fracionado  
		sql.append(" VLRBRTMER	= %s, "); // Valor Bruto Mercadoria  
		sql.append(" VLRBRTTMP	= %s, "); // Valor Bruto Temporario  
		sql.append(" VLRDSCESP	= %s, "); // Valor Desconto Especial  
		sql.append(" VLRFRTMER	= %s, "); // Valor do Frete da Mercadoria  
		sql.append(" VLRIPIMER	= %s, "); // Valor IPI Mercadoria  
		sql.append(" VLRIPITOT	= %s, "); // Valor IPI Total Mercadoria  
		sql.append(" VLRLIQCXA	= %s, "); // Valor Liquido Caixa  
		sql.append(" VLRLIQIMP	= %s, "); // Valor Liquido da Mercadoria com Imposto  
		sql.append(" VLRLIQMER	= %s, "); // Valor Liquido Mercadoria  
		sql.append(" VLRMAXISN	= %s, "); // Valor Maximo Isencao  
		sql.append(" VLRMNMMER	= %s, "); // Valor Minimo Mercadoria  
		sql.append(" VLRPTOBFC	= %s, "); // Valor Pontos de Beneficio  
		sql.append(" VLRPTOMER	= %s, "); // Valor Pontos  
		sql.append(" VLRRDCITE	= %s, "); // Valor Reducao Item  
		sql.append(" VLRSTBMER	= %s, "); // Valor STB (Substituicao Tributaria) Mercadoria  
		sql.append(" VLRSTBTOT	= %s, "); // Valor STB Total  
		sql.append(" VLRUNTIMP	= %s "); // Valor Unitario com Imposto  
		
		// Codigo da Mercadoria
		sql.append(" WHERE CODMER = ").append(item.mercadoria.codigo);

		String query = montaQueryItem(item, sql);

		db = DatabaseFactory.getInstance();
		try {
			db.executeSQL(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpMeGoDAO - alteraItem - ", e.getMessage());
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return true;
	}

	public static Item obtemItem(Integer codigoMercadoria) {
		StringBuilder sql = obtemQuerySelect();
		sql.append(" where CODMER = %s ");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(query);
		} catch (SQLException e) {
			Log.e("TabelaTmpMeGoDAO - obtemItem - ", e.getMessage());
		}

		List<Item> listaItens = obtemListaItens(maps);
		if (listaItens.isEmpty()) {
			return null;
		} else {
			return listaItens.get(0);
		}
	}

	public static boolean excluiItem(Integer codigoMercadoria) {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from TMPMEGO where CODMER = %s ");

		String query = DatabaseUtil.montaQuery(sql, codigoMercadoria);
		db = DatabaseFactory.getInstance();

		boolean resultado = false;
		try {
			db.executeSQL(query);
			resultado = true;
		} catch (SQLException e) {
			Log.e("TabelaTmpMeGoDAO - excluiItem - ", e.getMessage());
		}

		return resultado;
	}
	
	public static List<Item> obtemTodosItens() {
		StringBuilder sql = obtemQuerySelect();
		db = DatabaseFactory.getInstance();

		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		try {
			maps = db.executeQuery(sql.toString());
		} catch (SQLException e) {
			Log.e("TabelaTmpMeGoDAO - obtemTodosItens - ", e.getMessage());
		}

		return obtemListaItens(maps);
	}

	private static String montaQueryItem(Item item, StringBuilder sql) {
		String query = DatabaseUtil.montaQueryGenerica(sql,
				
				// CMSCNSMER - Comissao Concessao Mercadoria
				item.comissao.comissaoMercadoria,
						
				// CMSMAXMER - Maximo Comissao Mercadoria
				item.comissao.comissaoMaximaMercadoria,
						
				// CODCHVRGT - CODCHVRGT - Codigo Chave Registro
				item.codigoChaveRegistro,
				
				// CODCLI - Codigo Cliente
				item.codigoCliente, // CODCLI - Codigo Cliente
				
				// CODCNDPGT - Codigo Condição de Pagamento
				item.condicaoPagamento.codigoCondicaoPagamento,
				
				// CODCORMRG - Codigo Cor de Margem
				item.codigoCorMargem,
						
				// CODCORMRGB - Codigo Cor de Margem B
				item.codigoCorMargemB,
						
				// CODFILEPD - Codigo Filial
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
						
				// DATULTVND - Data Ultima Venda
				item.dataUltimaVenda,

				// DESMER - Descricao da Mercadoria
				item.mercadoria.descricao,
				
				// DESNGCMER - Descricao Negociacao Mercadoria
				item.mercadoria.descricaoNegocioMercadoria,
						
				// FLGPEE - Flag PEE
				item.mercadoria.flagPee,
						
				// FLGSTSITE - Flag Simbolo Situacao
				item.preco.flagSimboloSituacao,
						
				// INDITEIMU - Indica Item Imune
				item.indicaItemImune,
						
				// INDITEVND - Indica Item de Venda
				item.indicaItemVenda,
						
				// INDMERKIT - Indica Mercadoria Kit
				item.mercadoria.indicaMercadoriaKit,
						
				// INDNVO - Idicador de item novo
				item.indicaItemNovo,
						
				// INDRTCBFB - Indica Restricao Beneficio Customizado no Brinde
				item.indicaRestricaoBeneficioCustomizadoBrinde,
						
				// INDRTCBFV - Indica Restricao Beneficio Customizado
				item.indicaRestricaoBeneficioCustomizado,
						
				// INDRTCBNF - Indica Restricao de Item como Brinde
				item.indicaRestricaoItemBrinde,
						
				// INDSEMMRGL - Indica Sem Margem de Lucro
				item.stb.temMargemDeLucro ? '1' : '0',
						
				// IPITOTBNF - Valor IPI Total Bonificacao
				item.valorIPITotalBonificacao,
						
				// LIQCXAIMP - Valor Liquido Caixa com Imposto
				item.valorCaixaComImposto,
						
				// MRGLCRCLI - Migracao Lucro Cliente
				item.migracaoLucroCliente,

				// NUMNOTFSC - Numero da Nota Fiscal
				item.notaFiscal,
				
				// PCOCLI - Preco Cliente
				item.precoCliente,

				// PERACOCLI - Percentual de Acao do Cliente
				item.desconto.percentualDescontoAcao,
				
				// PERACOTTC - Percentual de Acao Tatica
				item.percentualDescontoAcaoTatica,
						
				// PERACRCNS - Percentual Acrescimo Concedido
				item.percentualAcrescimoConcedido,
						
				// PERACRVTL - Percentual de Acrescimo Virtual
				item.percentualAcrescimoVirtual,
						
				// PERDSCFLX - Percentual Desconto Flexivel
				item.percentualDescontoFlex,
						
				// PERDSCMNM - Percentual Desconto Minimo
				item.desconto.percentualMinimoDesconto,
						
				// PERICMMER - Percentual de ICM da Mercadoria
				item.preco.percentualICMS,

				// PERMAXFLX - Percentual Maximo Flexivel
				item.desconto.percentualDescontoFlexivel,
						
				// PERMAXSMP - Percentual Maximo Simplificado
				item.percentualDescontoSimplificado,
						
				// PERVLRMNM - Expedicao Percentual Valor Minimo
				item.stb.percentualValorMinimo,
						
				// POSMER - Posicao Mercadoria
				item.mercadoria.posicaoMercadoria,

				// QDECXAFRN - Quantidade Caixa Fornecedor
				item.mercadoria.quantidadeCaixaFornecedor,
				
				// QDEMERANT - Quantidade Mercadoria Anterior
				item.mercadoria.quantidadeMercadoriaAnterior,
				
				// QDEMERATU - Quantidade Mercadoria Atual
				item.mercadoria.quantidadeMercadoriaAtual,

				// QDEMERPED - Quantidade da Mercadoria no Pedido
				item.quantidadeMercadoria,
				
				// RTCBFVMER - Restricao de Beneficio de Mercadoria
				item.indicaRestricaoBeneficioCustomizadoMercadoria,
						
				// STBTOTBNF - STB Total Bonificacao
				item.stb.valorSTBTotalBonificacao,
						
				// TIPICT - Tipo de Incentivo
				item.preco.tipoIncentivoMercadoria,
						
				// TIPMCOREP - Tipo Marcação Representante (Listas Customizadas)
				item.preco.tipoMarcacaoRepresentante,
						
				// TIPMERDSC - Tipo Desconto Mercadoria
				item.desconto.tipo,
						
				// TIPMERESG - Tipo de Esgotamento da Mercadoria
				item.mercadoria.tipoEsgotamento,
						
				// TIPNGCMER - Mercadoria Tipo Negociacao Mercadoria
				item.tipoNegociacaoMercadoria,
						
				// TIPPMC - Tipo Promocao
				item.promocao.tipo,
						
				// UNTCXAIMP - Valor Unitario da Caixa com Imposto
				item.valorUnitarioCaixaComImposto,
						
				// VLRBNFMER - Valor Bonificacao
				item.valorBonificacao,
						
				// VLRBRTCXA - Valor Bruto Caixa
				item.preco.valorBrutoCaixa,
						
				// VLRBRTFRC - Caixa Valor Bruto Fracionado
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
						
				// VLRLIQCXA - Liquido 
				item.valorLiquidoCaixa,
						
				// VLRLIQIMP - Valor Liquido da Mercadoria com Imposto
				item.valorLiquidoComImposto,
						
				// VLRLIQMER - Valor Liquido Mercadoria
				item.valorLiquidoMercadoria,
						
				// VLRMAXISN - Valor Maximo Isencao
				item.mercadoria.valorMaximoMercadoria,
						
				// VLRMNMMER - Valor Minimo Mercadoria
				item.mercadoria.valorMinimoMercadoria,
						
				// VLRPTOBFC - Valor Pontos de Beneficio
				item.valorPontoBeneficioMercadoria,
						
				// VLRPTOMER - Valor Pontos
				item.mercadoria.valorPontos,
						
				// VLRRDCITE - Valor Reducao Item
				item.valorReducao,
						
				// VLRSTBMER - Valor STB (Substituicao Tributaria) Mercadoria
				item.stb.valorSTBUnitario,
						
				// VLRSTBTOT - Valor STB Total
				item.stb.valorSTBTotal,
						
				// VLRUNTIMP - Valor Unitario com Imposto
				item.valorUnitarioComImposto
				);
		
		return query;
	}
	
	private static List<Item> obtemListaItens(List<Map<String, String>> itens) {
		List<Item> listaItens = new ArrayList<Item>();

		try {
			for (Map<String, String> itemAux : itens) {
				Item item = new Item();
				item.promocao = new Promocao();
				item.mercadoria = new Mercadoria();
				item.mercadoria.percentualIPI = new PercentualIPI();
				item.preco = new Preco();
				item.condicaoPagamento = new CondicaoPagamento();
				item.stb = new Imposto();
				item.desconto = new Desconto();
				item.regraDistribuicao = new RegraDistribuicao();
				
				
				// CMSCNSMER - Comissao Concessao Mercadoria
				item.comissao.comissaoMercadoria = Util.getBigDecimal(itemAux.get("comissaoConcessaoMercadoria"));
						
				// CMSMAXMER - Maximo Comissao Mercadoria
				item.comissao.comissaoMaximaMercadoria = Util.getBigDecimal(itemAux.get("comissaoMaximaMercadoria"));
						
				// CODCHVRGT - CODCHVRGT - Codigo Chave Registro
				item.codigoChaveRegistro = Util.getStringValue(itemAux.get("codigoChaveRegistro"));
				
				// CODCLI - Codigo Cliente
				item.codigoCliente = Util.getInteger(itemAux.get("codigoCliente"));
				
				// CODCNDPGT - Codigo Condição de Pagamento
				item.condicaoPagamento.codigoCondicaoPagamento = Util.getInteger(itemAux.get("codigoCondicaoPagamento"));
				
				// CODCORMRG - Codigo Cor de Margem
				item.codigoCorMargem = Util.getInteger(itemAux.get("codigoCorMargem"));
						
				// CODCORMRGB - Codigo Cor de Margem B
				item.codigoCorMargemB = Util.getInteger(itemAux.get("codigoCorMargemB"));
						
				// CODFILEPD - Codigo Filial
				item.codigoFilialExpedicao = Util.getInteger(itemAux.get("codigoFilialExpedicao"));
						
				// CODFILFAT - Codigo de Filial de Faturamento
				item.codigoFilialFaturamento = Util.getInteger(itemAux.get("codigoFilialFaturamento"));
						
				// CODFLXPCO - Codigo Preco Flexivel
				item.preco.codigoFlexivelPreco = itemAux.get("codigoFlexivelPreco");
						
				// CODMER - Codigo da Mercadoria
				item.mercadoria.codigo = Util.getInteger(itemAux.get("codigoMercadoria"));
						
				// CODMERPCP - Codigo Mercadoria Principal
				item.mercadoria.codigoMercadoriaPrincipal = Util.getInteger(itemAux.get("codigoMercadoriaPrincipal"));
				if (item.mercadoria.codigoMercadoriaPrincipal != null) {
					item.mercadoria.temMercadoriaSimilar = true;
				}
						
				// CODPMC - Codigo Promocao
				item.promocao.codigo = Util.getInteger(itemAux.get("codigoPromocao"));
						
				// CODRGRDTB - Codigo Regra Distribuicao
				item.regraDistribuicao.codigoRegra = Util.getInteger(itemAux.get("codigoRegra"));
						
				// CODSMBSIT - Codigo Simbolo Situacao
				item.preco.codigoSimboloSituacao = itemAux.get("codigoSimboloSituacao");
						
				// DATULTVND - Data Ultima Venda
				item.dataUltimaVenda = DateUtil.formataData(itemAux.get("dataUltimaVenda"), DateUtil.DEFAULT_DATE_PATTERN);

				// DESMER - Descricao da Mercadoria
				item.mercadoria.descricao = itemAux.get("descricaoMercadoria");
				
				// DESNGCMER - Descricao Negociacao Mercadoria
				item.mercadoria.descricaoNegocioMercadoria = itemAux.get("descricaoNegocioMercadoria");
						
				// FLGPEE - Flag PEE
				item.mercadoria.flagPee = itemAux.get("flagPee");
						
				// FLGSTSITE - Flag Simbolo Situacao
				item.preco.flagSimboloSituacao = Util.getInteger(itemAux.get("flagSimboloSituacao"));
						
				// INDITEIMU - Indica Item Imune
				item.indicaItemImune = Util.getInteger(itemAux.get("indicaItemImune"));
						
				// INDITEVND - Indica Item de Venda
				item.indicaItemVenda = Util.getInteger(itemAux.get("indicaItemVenda"));
						
				// INDMERKIT - Indica Mercadoria Kit
				item.mercadoria.indicaMercadoriaKit = Util.getInteger(itemAux.get("indicaMercadoriaKit"));
						
				// INDNVO - Idicador de item novo
				item.indicaItemNovo = itemAux.get("indicaItemNovo");
						
				// INDRTCBFB - Indica Restricao Beneficio Customizado no Brinde
				item.indicaRestricaoBeneficioCustomizadoBrinde = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizadoBrinde"));
						
				// INDRTCBFV - Indica Restricao Beneficio Customizado
				item.indicaRestricaoBeneficioCustomizado = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizado"));
						
				// INDRTCBNF - Indica Restricao de Item como Brinde
				item.indicaRestricaoItemBrinde = Util.getInteger(itemAux.get("indicaRestricaoItemBrinde"));
						
				// INDSEMMRGL - Indica Sem Margem de Lucro
				item.stb.temMargemDeLucro = Util.getInteger(itemAux.get("temMargemDeLucro")) == 0 ? false : true;
						
				// IPITOTBNF - Valor IPI Total Bonificacao
				item.valorIPITotalBonificacao = Util.getBigDecimal(itemAux.get("valorIPITotalBonificacao"));
						
				// LIQCXAIMP - Valor Liquido Caixa com Imposto
				item.valorCaixaComImposto = Util.getBigDecimal(itemAux.get("valorCaixaComImposto"));
						
				// MRGLCRCLI - Migracao Lucro Cliente
				item.migracaoLucroCliente = Util.getBigDecimal(itemAux.get("migracaoLucroCliente"));

				// NUMNOTFSC - Numero da Nota Fiscal
				item.notaFiscal = Util.getInteger(itemAux.get("notaFiscal"));
				
				// PCOCLI - Preco Cliente
				item.precoCliente = Util.getBigDecimal(itemAux.get("precoCliente"));

				// PERACOCLI - Percentual de Acao do Cliente
				item.desconto.percentualDescontoAcao = Util.getBigDecimal(itemAux.get("percentualDescontoAcao"));
				
				// PERACOTTC - Percentual de Acao Tatica
				item.percentualDescontoAcaoTatica = Util.getBigDecimal(itemAux.get("percentualDescontoAcaoTatica"));
						
				// PERACRCNS - Percentual Acrescimo Concedido
				item.percentualAcrescimoConcedido = Util.getBigDecimal(itemAux.get("percentualAcrescimoConcedido"));
						
				// PERACRVTL - Percentual de Acrescimo Virtual
				item.percentualAcrescimoVirtual = Util.getBigDecimal(itemAux.get("percentualAcrescimoVirtual"));
						
				// PERDSCFLX - Percentual Desconto Flexivel
				item.percentualDescontoFlex = Util.getBigDecimal(itemAux.get("percentualDescontoFlex"));
						
				// PERDSCMNM - Percentual Desconto Minimo
				item.desconto.percentualMinimoDesconto = Util.getBigDecimal(itemAux.get("percentualMinimoDesconto"));
						
				// PERICMMER - Percentual de ICM da Mercadoria
				item.preco.percentualICMS = Util.getBigDecimal(itemAux.get("percentualICMS"));

				// PERMAXFLX - Percentual Maximo Flexivel
				item.desconto.percentualDescontoFlexivel = Util.getBigDecimal(itemAux.get("percentualDescontoFlexivel"));
						
				// PERMAXSMP - Percentual Maximo Simplificado
				item.percentualDescontoSimplificado = Util.getBigDecimal(itemAux.get("percentualDescontoSimplificado"));
						
				// PERVLRMNM - Expedicao Percentual Valor Minimo
				item.stb.percentualValorMinimo = Util.getBigDecimal(itemAux.get("percentualValorMinimo"));
						
				// POSMER - Posicao Mercadoria
				item.mercadoria.posicaoMercadoria = Util.getInteger(itemAux.get("posicaoMercadoria"));

				// QDECXAFRN - Quantidade Caixa Fornecedor
				item.mercadoria.quantidadeCaixaFornecedor = Util.getInteger(itemAux.get("quantidadeCaixaFornecedor"));
				
				// QDEMERANT - Quantidade Mercadoria Anterior
				item.mercadoria.quantidadeMercadoriaAnterior = Util.getInteger(itemAux.get("quantidadeMercadoriaAnterior"));
				
				// QDEMERATU - Quantidade Mercadoria Atual
				item.mercadoria.quantidadeMercadoriaAtual = Util.getInteger(itemAux.get("quantidadeMercadoriaAtual"));

				// QDEMERPED - Quantidade da Mercadoria no Pedido
				item.quantidadeMercadoria = Util.getInteger(itemAux.get("quantidadeMercadoria"));
				
				// RTCBFVMER - Restricao de Beneficio de Mercadoria
				item.indicaRestricaoBeneficioCustomizadoMercadoria = Util.getInteger(itemAux.get("indicaRestricaoBeneficioCustomizadoMercadoria"));
						
				// STBTOTBNF - STB Total Bonificacao
				item.stb.valorSTBTotalBonificacao = Util.getBigDecimal(itemAux.get("valorSTBTotalBonificacao"));
						
				// TIPICT - Tipo de Incentivo
				item.preco.tipoIncentivoMercadoria = itemAux.get("tipoIncentivoMercadoria");
						
				// TIPMCOREP - Tipo Marcação Representante (Listas Customizadas)
				item.preco.tipoMarcacaoRepresentante = itemAux.get("tipoMarcacaoRepresentante");
						
				// TIPMERDSC - Tipo Desconto Mercadoria
				item.desconto.tipo = itemAux.get("tipoDesconto");
						
				// TIPMERESG - Tipo de Esgotamento da Mercadoria
				item.mercadoria.tipoEsgotamento = itemAux.get("tipoEsgotamento");
						
				// TIPNGCMER - Mercadoria Tipo Negociacao Mercadoria
				item.tipoNegociacaoMercadoria = Util.getInteger(itemAux.get("tipoNegociacaoMercadoria"));
						
				// TIPPMC - Tipo Promocao
				item.promocao.tipo = itemAux.get("tipoPromocao");
						
				// UNTCXAIMP - Valor Unitario da Caixa com Imposto
				item.valorUnitarioCaixaComImposto = Util.getBigDecimal(itemAux.get("valorUnitarioCaixaComImposto"));
						
				// VLRBNFMER - Valor Bonificacao
				item.valorBonificacao = Util.getBigDecimal(itemAux.get("valorBonificacao"));
						
				// VLRBRTCXA - Valor Bruto Caixa
				item.preco.valorBrutoCaixa = Util.getBigDecimal(itemAux.get("valorBrutoCaixa"));
						
				// VLRBRTFRC - Caixa Valor Bruto Fracionado
				item.preco.valorBrutoFracionado = Util.getBigDecimal(itemAux.get("valorBrutoFracionado"));
						
				// VLRBRTMER - Valor Bruto Mercadoria
				item.preco.valorBrutoMercadoria = Util.getBigDecimal(itemAux.get("valorBrutoMercadoria"));
						
				// VLRBRTTMP - Valor Bruto Temporario
				item.preco.valorBrutoTMP = Util.getBigDecimal(itemAux.get("valorBrutoTMP"));
						
				// VLRDSCESP - Valor Desconto Especial
				item.desconto.valorDescontoEspecial = Util.getBigDecimal(itemAux.get("valorDescontoEspecial"));
						
				// VLRFRTMER - Valor do Frete da Mercadoria
				item.frete = Util.getBigDecimal(itemAux.get("valorFrete"));
				item.mercadoria.valorFrete = Util.getBigDecimal(itemAux.get("valorFrete"));
				
				// VLRIPIMER - Valor IPI Mercadoria
				item.mercadoria.percentualIPI.percentualIPI = Util.getBigDecimal(itemAux.get("percentualIPI"));
						
				// VLRIPITOT - Valor IPI Total Mercadoria
				item.valorIPITotal = Util.getBigDecimal(itemAux.get("valorIPITotal"));
						
				// VLRLIQCXA - Liquido 
				item.valorLiquidoCaixa = Util.getBigDecimal(itemAux.get("valorLiquidoCaixa"));
						
				// VLRLIQIMP - Valor Liquido da Mercadoria com Imposto
				item.valorLiquidoComImposto = Util.getBigDecimal(itemAux.get("valorLiquidoComImposto"));
						
				// VLRLIQMER - Valor Liquido Mercadoria
				item.valorLiquidoMercadoria = Util.getBigDecimal(itemAux.get("valorLiquidoMercadoria"));
						
				// VLRMAXISN - Valor Maximo Isencao
				item.mercadoria.valorMaximoMercadoria = Util.getBigDecimal(itemAux.get("valorMaximoMercadoria"));
						
				// VLRMNMMER - Valor Minimo Mercadoria
				item.mercadoria.valorMinimoMercadoria = Util.getBigDecimal(itemAux.get("valorMinimoMercadoria"));
						
				// VLRPTOBFC - Valor Pontos de Beneficio
				item.valorPontoBeneficioMercadoria = Util.getInteger(itemAux.get("valorPontosBeneficio"));
						
				// VLRPTOMER - Valor Pontos
				item.mercadoria.valorPontos = Util.getBigDecimal(itemAux.get("valorPontos"));
						
				// VLRRDCITE - Valor Reducao Item
				item.valorReducao = Util.getInteger(itemAux.get("valorReducao"));
						
				// VLRSTBMER - Valor STB (Substituicao Tributaria) Mercadoria
				item.stb.valorSTBUnitario = Util.getBigDecimal(itemAux.get("valorSTBUnitario"));
						
				// VLRSTBTOT - Valor STB Total
				item.stb.valorSTBTotal = Util.getBigDecimal(itemAux.get("valorSTBTotal"));
						
				// VLRUNTIMP - Valor Unitario com Imposto
				item.valorUnitarioComImposto = Util.getBigDecimal(itemAux.get("valorUnitarioComImposto"));
				
				listaItens.add(item);
			}
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage());
		} finally {
			Log.e(TAG, "TabelaTmpMeGoDAO - obtemListaItens");
		}

		return listaItens;
	}
	
	private static StringBuilder obtemQuerySelect() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		
		// CMSCNSMER - Comissao Concessao Mercadoria
		sql.append(" CMSCNSMER	as comissaoConcessaoMercadoria, ");
				
		// CMSMAXMER - Maximo Comissao Mercadoria
		sql.append(" CMSMAXMER	as comissaoMaximaMercadoria, ");
				
		// CODCHVRGT - CODCHVRGT - Codigo Chave Registro
		sql.append(" CODCHVRGT	as codigoChaveRegistro, ");
		
		// CODCLI - Codigo Cliente
		sql.append(" CODCLI		as codigoCliente, ");
		
		// CODCNDPGT - Codigo Condição de Pagamento
		sql.append(" CODCNDPGT	as codigoCondicaoPagamento, ");
		
		// CODCORMRG - Codigo Cor de Margem
		sql.append(" CODCORMRG	as codigoCorMargem, ");
				
		// CODCORMRGB - Codigo Cor de Margem B
		sql.append(" CODCORMRGB	as codigoCorMargemB, ");
				
		// CODFILEPD - Codigo Filial Expedicao
		sql.append(" CODFILEPD	as codigoFilialExpedicao, ");
				
		// CODFILFAT - Codigo Filial Faturamento
		sql.append(" CODFILFAT	as codigoFilialFaturamento, ");
				
		// CODFLXPCO - Codigo Flexivel Preco
		sql.append(" CODFLXPCO	as codigoFlexivelPreco, ");
				
		// CODMER - Codigo da Mercadoria
		sql.append(" CODMER		as codigoMercadoria, ");
				
		// CODMERPCP - Codigo Mercadoria Principal
		sql.append(" CODMERPCP	as codigoMercadoriaPrincipal, ");
				
		// CODPMC - Codigo Promocao
		sql.append(" CODPMC		as codigoPromocao, ");
				
		// CODRGRDTB - Codigo Regra Distribuicao
		sql.append(" CODRGRDTB	as codigoRegra, ");
				
		// CODSMBSIT - Codigo Simbolo Situacao
		sql.append(" CODSMBSIT	as codigoSimboloSituacao, ");
				
		// DATULTVND - Data Ultima Venda
		sql.append(" DATULTVND	as dataUltimaVenda, ");

		// DESMER - Descricao Mercadoria
		sql.append(" DESMER		as descricaoMercadoria, ");
		
		// DESNGCMER - Descricao Negociacao Mercadoria
		sql.append(" DESNGCMER	as descricaoNegocioMercadoria, ");
				
		// FLGPEE - Flag PEE
		sql.append(" FLGPEE		as flagPee, ");
				
		// FLGSTSITE - Flag Simbolo Situacao
		sql.append(" FLGSTSITE	as flagSimboloSituacao, ");
				
		// INDITEIMU - Indica Item Imune
		sql.append(" INDITEIMU	as indicaItemImune, ");
				
		// INDITEVND - Indica Item de Venda
		sql.append(" INDITEVND	as indicaItemVenda, ");
				
		// INDMERKIT - Indica Mercadoria Kit
		sql.append(" INDMERKIT	as indicaMercadoriaKit, ");
				
		// INDNVO - Indica Item Novo
		sql.append(" INDNVO		as indicaItemNovo, ");
				
		// INDRTCBFB - Indica Restricao Beneficio Customizado no Brinde
		sql.append(" INDRTCBFB	as indicaRestricaoBeneficioCustomizadoBrinde, ");
				
		// INDRTCBFV - Indica Restricao Beneficio Customizado
		sql.append(" INDRTCBFV	as indicaRestricaoBeneficioCustomizado, ");
				
		// INDRTCBNF - Indica Restricao de Item como Brinde
		sql.append(" INDRTCBNF	as indicaRestricaoItemBrinde, ");
				
		// INDSEMMRGL - Indica Sem Margem de Lucro
		sql.append(" INDSEMMRGL	as temMargemDeLucro, ");
				
		// IPITOTBNF - Valor IPI Total Bonificacao
		sql.append(" IPITOTBNF	as valorIPITotalBonificacao, ");
				
		// LIQCXAIMP - Valor Liquido Caixa com Imposto
		sql.append(" LIQCXAIMP	as valorCaixaComImposto, ");
				
		// MRGLCRCLI - Migracao Lucro Cliente
		sql.append(" MRGLCRCLI	as migracaoLucroCliente, ");

		// NUMNOTFSC - Numero da Nota Fiscal
		sql.append(" NUMNOTFSC	as notaFiscal, ");
		
		// PCOCLI - Preco Cliente
		sql.append(" PCOCLI		as precoCliente, ");

		// PERACOCLI - Percentual de Acao do Cliente
		sql.append(" PERACOCLI	as percentualDescontoAcao, ");
		
		// PERACOTTC - Percentual de Acao Tatica
		sql.append(" PERACOTTC	as percentualDescontoAcaoTatica, ");
				
		// PERACRCNS - Percentual Acrescimo Concedido
		sql.append(" PERACRCNS	as percentualAcrescimoConcedido, ");
				
		// PERACRVTL - Percentual de Acrescimo Virtual
		sql.append(" PERACRVTL	as percentualAcrescimoVirtual, ");
				
		// PERDSCFLX - Percentual Desconto Flexivel
		sql.append(" PERDSCFLX	as percentualDescontoFlex, ");
				
		// PERDSCMNM - Percentual Desconto Minimo
		sql.append(" PERDSCMNM	as percentualMinimoDesconto, ");
				
		// PERICMMER - Percentual de ICM da Mercadoria
		sql.append(" PERICMMER	as percentualICMS, ");

		// PERMAXFLX - Percentual Maximo Flexivel
		sql.append(" PERMAXFLX	as percentualDescontoFlexivel, ");
				
		// PERMAXSMP - Percentual Maximo Simplificado
		sql.append(" PERMAXSMP	as percentualDescontoSimplificado, ");
				
		// PERVLRMNM - Expedicao Percentual Valor Minimo
		sql.append(" PERVLRMNM	as percentualValorMinimo, ");
				
		// POSMER - Posicao Mercadoria
		sql.append(" POSMER		as posicaoMercadoria, ");

		// QDECXAFRN - Quantidade Caixa Fornecedor
		sql.append(" QDECXAFRN	as quantidadeCaixaFornecedor, ");
		
		// QDEMERANT - Quantidade Mercadoria Anterior
		sql.append(" QDEMERANT	as quantidadeMercadoriaAnterior, ");
		
		// QDEMERATU - Quantidade Mercadoria Atual
		sql.append(" QDEMERATU	as quantidadeMercadoriaAtual, ");

		// QDEMERPED - Quantidade da Mercadoria no Pedido
		sql.append(" QDEMERPED	as quantidadeMercadoria, ");
		
		// RTCBFVMER - Restricao de Beneficio de Mercadoria
		sql.append(" RTCBFVMER	as indicaRestricaoBeneficioCustomizadoMercadoria, ");
				
		// STBTOTBNF - STB Total Bonificacao
		sql.append(" STBTOTBNF	as valorSTBTotalBonificacao, ");
				
		// TIPICT - Tipo de Incentivo
		sql.append(" TIPICT		as tipoIncentivoMercadoria, ");
				
		// TIPMCOREP - Tipo Marcação Representante (Listas Customizadas)
		sql.append(" TIPMCOREP	as tipoMarcacaoRepresentante, ");
				
		// TIPMERDSC - Tipo Desconto Mercadoria
		sql.append(" TIPMERDSC	as tipoDesconto, ");
				
		// TIPMERESG - Tipo de Esgotamento da Mercadoria
		sql.append(" TIPMERESG	as tipoEsgotamento, ");
				
		// TIPNGCMER - Tipo Negociacao Mercadoria
		sql.append(" TIPNGCMER	as tipoNegociacaoMercadoria, ");
				
		// TIPPMC - Tipo Promocao
		sql.append(" TIPPMC		as tipoPromocao, ");
				
		// UNTCXAIMP - Valor Unitario da Caixa com Imposto
		sql.append(" UNTCXAIMP	as valorUnitarioCaixaComImposto, ");
				
		// VLRBNFMER - Valor Bonificacao Mercadoria
		sql.append(" VLRBNFMER	as valorBonificacao, ");
				
		// VLRBRTCXA - Valor Bruto Caixa
		sql.append(" VLRBRTCXA	as valorBrutoCaixa, ");
				
		// VLRBRTFRC - Caixa Valor Bruto Fracionado
		sql.append(" VLRBRTFRC	as valorBrutoFracionado, ");
				
		// VLRBRTMER - Valor Bruto Mercadoria
		sql.append(" VLRBRTMER	as valorBrutoMercadoria, ");
				
		// VLRBRTTMP - Valor Bruto Temporario
		sql.append(" VLRBRTTMP	as valorBrutoTMP, ");
				
		// VLRDSCESP - Valor Desconto Especial
		sql.append(" VLRDSCESP	as valorDescontoEspecial, ");
				
		// VLRFRTMER - Valor do Frete da Mercadoria
		sql.append(" VLRFRTMER	as valorFrete, ");
		
		// VLRIPIMER - Valor IPI Mercadoria
		sql.append(" VLRIPIMER	as percentualIPI, ");
				
		// VLRIPITOT - Valor IPI Total Mercadoria
		sql.append(" VLRIPITOT	as valorIPITotal, ");
				
		// VLRLIQCXA - Valor Liquido Caixa
		sql.append(" VLRLIQCXA	as valorLiquidoCaixa, ");
				
		// VLRLIQIMP - Valor Liquido com Imposto
		sql.append(" VLRLIQIMP	as valorLiquidoComImposto, ");
				
		// VLRLIQMER - Valor Liquido Mercadoria
		sql.append(" VLRLIQMER	as valorLiquidoMercadoria, ");
				
		// VLRMAXISN - Valor Maximo Isencao
		sql.append(" VLRMAXISN	as valorMaximoMercadoria, ");
				
		// VLRMNMMER - Valor Minimo Mercadoria
		sql.append(" VLRMNMMER	as valorMinimoMercadoria, ");
				
		// VLRPTOBFC - Valor Pontos de Beneficio
		sql.append(" VLRPTOBFC	as valorPontosBeneficio, ");
				
		// VLRPTOMER - Valor Pontos
		sql.append(" VLRPTOMER	as valorPontos, ");
				
		// VLRRDCITE - Valor Reducao Item
		sql.append(" VLRRDCITE	as valorReducao, ");
				
		// VLRSTBMER - Valor STB (Substituicao Tributaria) Mercadoria
		sql.append(" VLRSTBMER	as valorSTBUnitario, ");
				
		// VLRSTBTOT - Valor STB (Substituicao Tributaria) Total
		sql.append(" VLRSTBTOT	as valorSTBTotal, ");
				
		// VLRUNTIMP - Valor Unitario com Imposto
		sql.append(" VLRUNTIMP	as valorUnitarioComImposto ");
		
		sql.append(" from TMPMEGO ");
		
		return sql;
	}

}
