package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.martins.vendas.dao.AlteraPedidoDAO;
import br.com.martins.vendas.dao.ClienteDAO;
import br.com.martins.vendas.dao.CondicaoPagamentoDAO;
import br.com.martins.vendas.dao.temp.TabelaTmpItePeDAO;
import br.com.martins.vendas.services.calculodepreco.CalculoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.Pedido;

public class AlteraPedidoService {

	
	public static Pedido carregaDadosInfoGerais(Integer numeroPedido) throws SQLException {
	 	return AlteraPedidoDAO.carregaDadosInfoGerais(numeroPedido);
	}
	
	public static String montaDescricaoCondicaoPagamento(Pedido pedido) {
		return CondicaoPagamentoDAO.montaDescricaoCondicao(pedido.cliente.condicaoPagamento); 
	}
	
	public static void carregaDadosItensAlteraPedido(Integer numeroPedido, Integer codigoCliente) throws Exception {
		List<Item> lista = AlteraPedidoDAO.carregaDadosItens(numeroPedido, null);
		Cliente cliente = ClienteDAO.obtemDadosClienteAlteraPedido(codigoCliente);
		TabelaTmpItePeDAO.limpaTabela();
		for (Item item : lista) {
			item = CalculoService.calculaPreco(item, cliente);
			TabelaTmpItePeDAO.insereItem(item);
		}
	}
	
	public static void carregaDadosItensRegeraByFilial(Integer numeroPedido, Integer codigoCliente) throws Exception {
		List<Item> lista = new ArrayList<Item>();
		List<Item> listaFiliais = AlteraPedidoDAO.carregaFiliaisPedidoReprovado(numeroPedido);

		for (Item item : listaFiliais) {
			lista = AlteraPedidoDAO.carregaDadosItens(numeroPedido, item.codigoFilialExpedicao);
		}

		Cliente cliente = ClienteDAO.obtemDadosClienteAlteraPedido(codigoCliente);
		for (Item item : lista) {
			item = CalculoService.calculaPreco(item, cliente);
			TabelaTmpItePeDAO.insereItem(item);
		}

	}
	public static void carregaDadosItensRegeraByCortes(Integer numeroPedido, Integer codigoCliente) throws Exception {
		List<Item> lista = new ArrayList<Item>();
		lista = AlteraPedidoDAO.carregaDadosItensRegeraByCortes(numeroPedido);		
				
		Cliente cliente = ClienteDAO.obtemDadosClienteAlteraPedido(codigoCliente);
		for (Item item : lista) {
			item = CalculoService.calculaPreco(item, cliente);
			TabelaTmpItePeDAO.insereItem(item);
		}

	}
}
