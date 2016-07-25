package br.com.martins.vendas.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.martins.vendas.services.pedido.PedidoService;
import br.com.martins.vendas.vo.Pedido;

public class PedidoServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() {
//		ConfigUtils.config();
	}
	
	@Test
	public void obterCliente() {
		try {
			//Pedido existente
			Integer codigoCliente = 351184;
			
			//Pedido inesistente
//			Integer codigoCliente = 0;
			
			Pedido pedido = PedidoService.obtemUltimoPedido(codigoCliente);
			assertNotNull("Pedido é nulo.", pedido);
		
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Test
	public void limpaTabelasTemporarias() {
		boolean resultado = PedidoService.limpaTabelasTemporarias();
		assertTrue(resultado);
	}
	
}
