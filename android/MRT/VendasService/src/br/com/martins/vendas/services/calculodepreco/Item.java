package br.com.martins.vendas.services.calculodepreco;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.services.desconto.Desconto;
import br.com.martins.vendas.vo.Beneficio;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Comissao;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.ItemPedidoTemporario;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.Preco;
import br.com.martins.vendas.vo.Promocao;
import br.com.martins.vendas.vo.RegraDistribuicao;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

/**
 * Classe Item
 */
public class Item extends BaseVo {

	private static final long serialVersionUID = -7614627801343688888L;

	/**
	 * Construtor default - inicializa as propriedades do objeto Item
	 */
	public Item() {
			
		mercadoria = new Mercadoria();
		preco = new Preco();
		condicaoPagamento = new CondicaoPagamento();
		stb = new Imposto();
		desconto = new Desconto();
		promocao = new Promocao();

		codigoFilialExpedicao = BigDecimal.ZERO.intValue();
		regraDistribuicao = new RegraDistribuicao();

		percentualAcrescimoConcedido = BigDecimal.ZERO;
		percentualDescontoConcedido = BigDecimal.ZERO;
		percentualAcrescimoVirtual = BigDecimal.ZERO;
		percentualDescontoBeneficio = BigDecimal.ZERO;
		percentualICM = BigDecimal.ZERO;

		frete = BigDecimal.ZERO;
		freteCaixa = BigDecimal.ZERO;
		codigoCorMargem = BigDecimal.ZERO.intValue();
		codigoCorMargemB = BigDecimal.ZERO.intValue();

		tipoNegociacaoMercadoria = BigDecimal.ZERO.intValue();

		indicaRestricaoBonificacaoSemBrinde = BigDecimal.ZERO.intValue();
		indicaRestricaoBeneficioCustomizado = BigDecimal.ZERO.intValue();
		indicaRestricaoBeneficioCustomizadoBrinde = BigDecimal.ZERO.intValue();
		indicaRestricaoBeneficioCustomizadoMercadoria = BigDecimal.ZERO.intValue();
		indicaRestricaoItemBrinde = BigDecimal.ZERO.intValue();
		indicaItemImune = BigDecimal.ZERO.intValue();
		indicaItemVenda = BigDecimal.ZERO.intValue();
		indicaItemNovo = StringUtil.EMPTY;

		valorIPITotal = BigDecimal.ZERO;
		valorIPITotalBonificacao = BigDecimal.ZERO;

		valorPontuacaoMercadoria = 0;
		valorPontoBeneficioMercadoria = 0;

		valorLiquidoMercadoria = BigDecimal.ZERO;
		valorLiquidoTotal = BigDecimal.ZERO;
		valorLiquidoCaixa = BigDecimal.ZERO;
		valorBonificacao = BigDecimal.ZERO;
		valorReducao = BigDecimal.ZERO.intValue();

		percentualDescontoFlex = BigDecimal.ZERO;
		percentualDescontoAcaoTatica = BigDecimal.ZERO;
		percentualDescontoSimplificado = BigDecimal.ZERO;

		beneficio = new Beneficio();
		comissao = new Comissao();
		
		valorTotalMercadoriasComRestricao = BigDecimal.ZERO;
		quantidadeTotalMercadoriasComIsencao = 0;
		
		quantidadeMercadoria = BigDecimal.ZERO.intValue();
		notaFiscal = BigDecimal.ZERO.intValue();
		
		valorLiquidoComImposto       = BigDecimal.ZERO;
		valorUnitarioCaixaComImposto = BigDecimal.ZERO;
		valorCaixaComImposto         = BigDecimal.ZERO;
		valorUnitarioComImposto      = BigDecimal.ZERO;

		stb.valorSTBUnitario		 = BigDecimal.ZERO;
	}

	public Integer digitoMercadoria;
	public Integer tipoNegociacaoMercadoria;
	public Integer tipoNegociacaoMercadoriaAux;
	public Integer tipoDistribuicaoMercadoria;
	public Integer codigoRegraDistribuicao;
	
	public Integer tipoVendaPedido;
	
	public Cliente cliente;

	public Integer indicaRestricaoBonificacaoSemBrinde;
	
	// INDRTCBFV - Indica Restricao Beneficio Customizado
	public Integer indicaRestricaoBeneficioCustomizado;
	
	// INDRTCBFB - Indica Restricao Beneficio Customizado no Brinde
	public Integer indicaRestricaoBeneficioCustomizadoBrinde;
	public Integer indicaRestricaoBeneficioCustomizadoMercadoria;
	
	// INDRTCBNF - Indica Restricao de Item como Brinde
	public Integer indicaRestricaoItemBrinde;

	public Integer indicaItemImune;
	public Integer indicaItemVenda;
	public String indicaItemNovo;

	public Mercadoria mercadoria;
	public Preco preco;
	public CondicaoPagamento condicaoPagamento;
	public Imposto stb;
	public Desconto desconto;
	public Promocao promocao;

	public Integer codigoFilialExpedicao;
	public Integer codigoFilialFaturamento;
	public Integer codigoFilialExpedicaoAux;
	public Integer codigoFilialFaturamentoAux;
	public String siglaEstadoOrigem;
	public String siglaEstadoDestino;
	
	public RegraDistribuicao regraDistribuicao;

	public BigDecimal valorIPITotal;
	public BigDecimal valorIPITotalBonificacao;
	
	public Integer valorPontuacaoMercadoria;
	public Integer valorPontoBeneficioMercadoria;
	
	public BigDecimal valorSemEncargos;
	public BigDecimal valorDescontoBeneficio;
	public BigDecimal valorDescontoFlexivel;
	public BigDecimal valorLiquidoMercadoria; // VlrLiqMer
	public BigDecimal valorLiquidoTotal; //
	public BigDecimal valorLiquidoCaixa;
	public BigDecimal valorBonificacao;
	public Integer    valorReducao;
	public BigDecimal valorDescontoEspecial;//VLRDSCESP

	public BigDecimal percentualAcrescimoConcedido;
	public BigDecimal percentualDescontoConcedido;
	public BigDecimal percentualAcrescimoVirtual;
	public BigDecimal percentualDescontoBeneficio;
	public BigDecimal percentualDescontoAcaoTatica;
	public BigDecimal percentualDescontoSimplificado;
	public BigDecimal percentualDescontoFlex;	
	
	public BigDecimal frete;
	public BigDecimal freteCaixa;

	public Integer codigoCorMargem;
	public Integer codigoCorMargemB;
	
	public Margem margem;

	public Integer notaFiscal; // NUMNOTFSC
	public Integer notaFiscalAux;
	public Integer numeroSequenciaItem;

	// Valores calculados
	public BigDecimal valorLiquidoComImposto;
	public BigDecimal valorUnitarioCaixaComImposto; // UNTCXAIMP
	public BigDecimal valorCaixaComImposto;
	public BigDecimal valorUnitarioComImposto; // VLRUNTIMP
	
	public Integer quantidadeMercadoria;
	
	// Flag que indica se deve ser executado calculo do preco
	public boolean	flagPrecificavel = true;

	// Itens inseridos por necessitar para telas com relacionamento com Gondola
	public Integer codigoCliente; // CODCLI - Codigo Cliente
	public BigDecimal precoCliente; // PCOCLI - Preco Cliente 
	public BigDecimal migracaoLucroCliente; // MRGLCRCLI - Migracao Lucro Cliente
	public Date dataUltimaVenda; // DATULTVND - Data Ultima Venda
	public String codigoChaveRegistro; // CODCHVRGT - Codigo Chave Registro
	public String nomeFilialFat;
	
	public Integer quantidadePrazoBeneficiario; // QDEPRZBFC - Quantidade Prazo Beneficiario
	public BigDecimal percentualEconomicoBeneficio; //PERECOBFC - Percentual Economico Beneficio
	public Beneficio beneficio; // PERCMSADI - Percentual Comissao Adicional
	public Integer indicativoSemMargemLucro; // INDSEMMRGL - Indicativo Sem Margem de Lucro
	
	// TODO Avaliar que atributos devem fazer parte do objeto Comissao
	public Comissao comissao;
	public BigDecimal valorTotalMercadoriasComRestricao;
	public Integer quantidadeTotalMercadoriasComIsencao;
	public BigDecimal percentualICM;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mercadoria", mercadoria);
		map.put("preco", preco);
		map.put("condicaoPagamento", condicaoPagamento);
		map.put("percentualIPI", mercadoria.percentualIPI);
		map.put("stb", stb);
		map.put("desconto", desconto);
		map.put("promocao", promocao);
		map.put("regraDistribuicao", regraDistribuicao);
		map.put("digitoMercadoria", digitoMercadoria);
		map.put("nomeFilialFat", nomeFilialFat);
		map.put("tipoDistribuicaoMercadoria", tipoDistribuicaoMercadoria);
		map.put("codigoRegraDistribuicao", codigoRegraDistribuicao);
		map.put("tipoNegociacaoMercadoria", tipoNegociacaoMercadoria);
		map.put("frete", frete);
		map.put("freteCaixa", freteCaixa);
		map.put("codigoCorMargem", codigoCorMargem);
		map.put("codigoCorMargemB", codigoCorMargemB);
		map.put("notaFiscal", notaFiscal);
		map.put("quantidadeMercadoria", quantidadeMercadoria);

		map.put("codigoFilialExpedicao", codigoFilialExpedicao);
		map.put("codigoFilialFaturamento", codigoFilialFaturamento);
		map.put("siglaEstadoOrigem", siglaEstadoOrigem);
		map.put("siglaEstadoDestino", siglaEstadoDestino);

		map.put("numeroSequenciaItem", numeroSequenciaItem);
		
		map.put("indicaRestricaoBonificacaoSemBrinde", indicaRestricaoBonificacaoSemBrinde);
		map.put("indicaRestricaoBeneficioCustomizado", indicaRestricaoBeneficioCustomizado); // INDRTCBFV - Indica Restricao Beneficio Customizado
		map.put("indicaRestricaoBeneficioCustomizadoBrinde", indicaRestricaoBeneficioCustomizadoBrinde); // INDRTCBFB - Indica Restricao Beneficio Customizado no Brinde
		map.put("indicaRestricaoBeneficioCustomizadoMercadoria", indicaRestricaoBeneficioCustomizadoMercadoria);
		map.put("indicaRestricaoItemBrinde", indicaRestricaoItemBrinde); // INDRTCBNF - Indica Restricao de Item como Brinde
		map.put("indicaItemImune", indicaItemImune);
		map.put("indicaItemVenda", indicaItemVenda);
		map.put("indicaItemNovo", indicaItemNovo);
		
		map.put("percentualAcrescimoConcedido", percentualAcrescimoConcedido);
		map.put("percentualDescontoConcedido", percentualDescontoConcedido);
		map.put("percentualAcrescimoVirtual", percentualAcrescimoVirtual);
		map.put("percentualDescontoAcaoTatica", percentualDescontoAcaoTatica);
		map.put("percentualDescontoSimplificado", percentualDescontoSimplificado);
		map.put("percentualDescontoFlex", percentualDescontoFlex);
		map.put("percentualDescontoBeneficio", percentualDescontoBeneficio);
		
		map.put("valorLiquidoMercadoria", StringUtil.formataMonetario(valorLiquidoMercadoria, false));
		
		valorLiquidoTotal = calculaValorLiquidoTotal();
		map.put("valorLiquidoTotal", valorLiquidoTotal);
		
		map.put("valorSemEncargos", valorSemEncargos);
		map.put("valorLiquidoCaixa", valorLiquidoCaixa);
		map.put("valorBonificacao", valorBonificacao);
		map.put("valorReducao", valorReducao);
		map.put("valorLiquidoComImposto", StringUtil.formataMonetario(valorLiquidoComImposto, false));
		map.put("valorUnitarioCaixaComImposto", StringUtil.formataMonetario(valorUnitarioCaixaComImposto, false));
		map.put("valorCaixaComImposto", StringUtil.formataMonetario(valorCaixaComImposto,4, false));
		map.put("valorUnitarioComImposto", StringUtil.formataMonetario(valorUnitarioComImposto,4, false));
		map.put("valorIPITotal", valorIPITotal);
		map.put("valorIPITotalBonificacao", valorIPITotalBonificacao);

		map.put("valorPontoMercadoria", valorPontuacaoMercadoria);
		map.put("valorPontoBeneficioMercadoria", valorPontoBeneficioMercadoria);
		
		map.put("flagPrecificavel", flagPrecificavel);
		
		// Itens inseridos por necessitar para telas com relacionamento com Gondola
		map.put("codigoCliente", codigoCliente); // CODCLI - Codigo Cliente
		map.put("precoCliente", StringUtil.formataMonetario(precoCliente)); // PCOCLI - Preco Cliente 
		map.put("migracaoLucroCliente", StringUtil.formataMonetario(migracaoLucroCliente)); // MRGLCRCLI - Migracao Lucro Cliente
		map.put("dataUltimaVenda", StringUtil.formataData(dataUltimaVenda, DateUtil.DEFAULT_DATE_PATTERN)); // DATULTVND - Data Ultima Venda
		map.put("codigoChaveRegistro", codigoChaveRegistro); // CODCHVRGT - Codigo Chave Registro
		
		map.put("quantidadePrazoBeneficiario", quantidadePrazoBeneficiario); // QDEPRZBFC - Quantidade Prazo Beneficiario
		map.put("percentualEconomicoBeneficio", percentualEconomicoBeneficio); //PERECOBFC - Percentual Economico Beneficio
		map.put("indicativoSemMargemLucro", indicativoSemMargemLucro); // INDSEMMRGL - Indicativo Sem Margem de Lucro
		
		map.put("beneficio", beneficio); // PERCMSADI - Percentual Comissao Adicional
		
		map.put("comissao", comissao); // PERCMSADI - Percentual Comissao Adicional
		map.put("percentualICM", percentualICM);
		
		map.put("comissao", comissao); // PERCMSADI - Percentual Comissao Adicional		
		map.put("valorTotalMercadoriasComRestricao", valorTotalMercadoriasComRestricao);
		map.put("quantidadeTotalMercadoriasComIsencao", quantidadeTotalMercadoriasComIsencao);

		return map;
	}

	public BigDecimal calculaValorLiquidoTotal() {
		if (valorLiquidoMercadoria == null || quantidadeMercadoria == null) {
			return BigDecimal.ZERO;
		}
		return valorLiquidoMercadoria.multiply(BigDecimal.valueOf(quantidadeMercadoria));
	}

	public void populaObjeto(ItemPedidoTemporario itemPedidoTemporario) throws Exception {

		this.condicaoPagamento.codigoCondicaoPagamento = itemPedidoTemporario.codigoCondicaoPagamento;
		this.codigoFilialExpedicao = itemPedidoTemporario.codigoFilialExpedicao;
		this.codigoFilialFaturamento = itemPedidoTemporario.codigoFilialFaturamento;
		this.tipoNegociacaoMercadoria = itemPedidoTemporario.tipoNegocicaoMercadoria;
		this.quantidadeMercadoria = itemPedidoTemporario.quantidadeMercadoria;

		this.mercadoria =  MercadoriaService.buscaMercadoria(itemPedidoTemporario.codigoMercadoria);
		this.valorDescontoBeneficio = itemPedidoTemporario.percentualDescontoBeneficio;
		this.percentualAcrescimoConcedido = itemPedidoTemporario.percentualAcrescimoConcedido;
		this.percentualDescontoConcedido = itemPedidoTemporario.percentualDescontoFlexivel;
		this.tipoVendaPedido = itemPedidoTemporario.tipoVendaItem;
	}
	
	/**
	 * Metodo para verificar se todos os parametros
	 * foram passados corretamente para o calculo de preco
	 * @throws IntegrationException 
	 */
	public void validaItemPedidoCalculoPreco() throws IntegrationException{
		StringBuilder sb = new StringBuilder("");
		if(condicaoPagamento.codigoCondicaoPagamento == null){
			sb.append("codigoCondicaoPagamento ");
		}
		if(codigoFilialExpedicao == null){
			sb.append("codigoFilialExpedicao ");
		}
		if(codigoFilialFaturamento == null){
			sb.append("codigoFilialFaturamento ");
		}
		if(tipoNegociacaoMercadoria == null){
			sb.append("tipoNegociacaoMercadoria ");
		}
		if(quantidadeMercadoria == null){
			sb.append("quantidadeMercadoria ");
		}
		if(mercadoria == null){
			sb.append("mercadoria ");
		}
//		if(valorDescontoBeneficio == null){
//			sb.append("valorDescontoBeneficio ");
//		}
		if(percentualAcrescimoConcedido == null){
			sb.append("percentualAcrescimoConcedido ");
		}
		if(percentualDescontoConcedido == null){
			sb.append("percentualDescontoConcedido ");
		}
//		if(tipoVendaPedido == null){
//			sb.append("tipoVendaPedido ");
//		}
		if(!"".equals(sb.toString())){
			sb.insert(0, "Todos os parametros devem ser passados para calculo de preco: ");
			throw new IntegrationException(sb.toString());
		}
	}
	
}
