package br.com.martins.vendas.services;

import java.util.Date;
import java.util.List;

import br.com.martins.vendas.dao.AlteraPedidoDAO;
import br.com.martins.vendas.dao.ClienteDAO;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Gondola;
import br.com.martins.vendas.vo.Pedido;

import com.brq.mobile.framework.mail.MailSender;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;
import com.brq.mobile.framework.util.Util;

public class MailService {

	/**
	 * 
	 * @param codigoCliente
	 * @param destinatarios
	 * @param showEstAnterior
	 * @param showEstAtual
	 * @param showValFreteEmbalagemMartins
	 * @param showPrecoLiqImpostoFrete
	 * @param showFilialExpedicao
	 * @param showFilialFaturamento
	 * @param showPercentualICMS
	 * @return
	 * @throws Exception
	 */
	public static boolean enviarEmailRelatorioGondola(Integer codigoCliente, String[] destinatarios, boolean showEstAnterior, boolean showEstAtual, boolean showValFreteEmbalagemMartins, boolean showPrecoLiqImpostoFrete, boolean showFilialExpedicao, boolean showFilialFaturamento,	boolean showPercentualICMS) throws Exception {

		Cliente cliente = ClienteService.obterDetalheCliente(codigoCliente);
		List<Gondola> itensGondola = GondolaService.listarItensTemporariosGondolaCliente(codigoCliente);

		final String br = "<br />";
		StringBuffer mail = new StringBuffer();

		mail.append("  Código do Cliente: ").append(cliente.codigoCliente).append(br);
		mail.append("    Nome do Cliente: ").append(cliente.nomeCliente).append(br);
		mail.append("         Data Atual: ").append(new Date()).append(br);
		mail.append("Quantidade de itens: ").append(itensGondola.size()).append(br);
		mail.append("====================================================").append(br);

		for (Gondola gondola : itensGondola) {
			mail.append(" Posição da Mercadoria    			: ").append(gondola.posicaoMercadoria).append(br);
			mail.append(" Código  da Mercadoria  			: ").append(gondola.codigoMercadoria).append(br);
			mail.append(" Descrição Mercadoria  			: ").append(gondola.descricaoMercadoria).append(br);
			mail.append(" Condição Pagamento    			: ").append(gondola.codigoCondicaoPagamento).append(br);
			mail.append(" Preço Unit Líq + Impost + Frete 	: ").append(gondola.valorLiquidoMercadoria.add(gondola.valorUnitarioImposto).add(gondola.valorFreteMercadoria)).append(br);

			mail.append(" Preço Cliente		    			: ").append(gondola.precoCliente).append(br);
			mail.append(" Margem    		    			: ").append(gondola.margemLucroCliente).append(br);

			if (showEstAnterior) {
				mail.append(" Estoque Anterior	    		: ").append(gondola.quantidadeMercadoriaAnterior).append(br);
			}

			if (showEstAtual) {
				mail.append(" Estoque Atual		    		: ").append(gondola.quantidadeMercadoriaAtual).append(br);
			}

			if (showValFreteEmbalagemMartins) {
				mail.append(" Valor Frete           		: ").append(gondola.valorFreteMercadoria).append(br);
			}

			if (showPrecoLiqImpostoFrete) {
				mail.append(" Preço Líquido + Impost + Frete: ").append(gondola.valorLiquidoMercadoria.add(gondola.valorUnitarioImposto).add(gondola.valorFreteMercadoria)).append(br);
			}

			if (showFilialExpedicao) {
				mail.append(" Código Filial Expedição   	: ").append(gondola.codigoFilialExpedicao).append(br);
			}

			if (showFilialFaturamento) {
				mail.append(" Código Filial Faturamento 	: ").append(gondola.codigoFilialFaturamento).append(br);
			}

			if (showPercentualICMS) {
				mail.append(" Percentual ICMS 				: ").append(gondola.percentualIcmsMercadoria).append(br);
			}

			mail.append("++++++++++++++++++++++++++++++++++++++++++++++++++++").append(br);
		}

		MailSender mailSender = new MailSender();
		return mailSender.sendMail(null, destinatarios, "Relatório de Gondola para o Cliente: ".concat(cliente.nomeCliente), mail.toString());
	}

	/**
	 * 
	 * @param codigoCliente
	 * @param destinatarios
	 * @param showValorFrete
	 * @param showTotalImpostos
	 * @param showFilialExp
	 * @param showFilialFaturamento
	 * @param showFilialExpedicao
	 * @param showFilialFaturamento
	 * @param showPercentualIcms
	 * @param pedido
	 * @return
	 * @throws Exception
	 */
	public static boolean enviarEmailRelatorioPedido(Integer codigoCliente, String nomeCliente, String emailRepresentante, boolean showValorFrete, boolean showTotalImpostos, boolean showFilialExp, boolean showFilialFaturamento, boolean showPercentualIcms, Pedido pedido) throws Exception {

		MailSender mailSender = new MailSender();
		
		List<Item> itensPedido = AlteraPedidoDAO.carregaDadosItens(Util.getInteger(pedido.numeroPedido), null);
		Cliente cliente = ClienteDAO.obtemDadosClienteAlteraPedido(codigoCliente);		
		
		final String br = "<br />";
		StringBuffer mail = new StringBuffer();
		
		mail.append(" Código do Cliente    : ").append(codigoCliente).append(br);
		mail.append(" Nome do Cliente      : ").append(nomeCliente).append(br);
		mail.append(" Número do Pedido     : ").append(pedido.numeroPedido).append(br);
		mail.append(" Data Atual           : ").append(StringUtil.formataData(new Date(), DateUtil.DEFAULT_DATE_PATTERN)).append(br);
		mail.append(" Preparado   		   : ").append(pedido.preparado).append(br);
		mail.append(" Data do Pedido	   : ").append(StringUtil.formataData(pedido.dataPedido, DateUtil.DEFAULT_DATE_PATTERN)).append(br);
		mail.append(" Hora do Pedido	   : ").append(pedido.horaPedido).append(br);
		mail.append(" Data do Resultado	   : ").append(StringUtil.formataData(pedido.dataResultado, DateUtil.DEFAULT_DATE_PATTERN)).append(br);
		mail.append(" Quantidade Itens	   : ").append(pedido.quantidadeTotalItens).append(br);
		mail.append(" Valor Total do Pedido: ").append(StringUtil.formataMonetario(pedido.valorTotalPedido)).append(br);

		for (Item item : itensPedido) {
			item = CalculoService.calculaPreco(item, cliente);
			
			mail.append("------------------------------------------------------------------").append(br);
			
			mail.append(" Mercadoria	   : ").append(item.mercadoria.codigo + " - " + item.mercadoria.descricao).append(br);
			
			mail.append(" Condição Pagamento	   : ").append(item.condicaoPagamento.codigoCondicaoPagamento).append(br);

			if (showValorFrete) {
				mail.append(" Valor Frete	    			: ").append(StringUtil.formataMonetario(item.frete)).append(br);
			}

			if (showTotalImpostos) {
				mail.append(" Total Líquido + Impostos + Frete  : ").append(StringUtil.formataMonetario(item.valorLiquidoComImposto)).append(br);
			}

			if (showFilialExp) {
				mail.append(" Filial de Expedição (CAD)         : ").append(item.codigoFilialExpedicao).append(br);
			}

			if (showFilialFaturamento) {
				mail.append(" Filial de Faturamento (FIL)		: ").append(item.codigoFilialExpedicao).append(br);
			}

			if (showPercentualIcms) {
				mail.append(" Percentual de ICMS   				: ").append(item.mercadoria.percentualICMS.percentualICMSMercadoria).append(br);
			}

			mail.append("------------------------------------------------------------------").append(br);
		}
			
		return mailSender.sendMail(emailRepresentante, new String[] { emailRepresentante }, "Relatório Pedido nº: ".concat(pedido.numeroPedido), mail.toString());
	}

}
