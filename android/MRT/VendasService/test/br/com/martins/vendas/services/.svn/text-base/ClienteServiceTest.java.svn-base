package br.com.martins.vendas.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.SituacaoCliente;

import com.brq.mobile.framework.log.Log;

public class ClienteServiceTest {
	
	private static final String TAG = ClienteServiceTest.class.getName();
	
	int codigoTerritorio;
	int codigoCliente;

	@BeforeClass
	public static void setUpBeforeClass() {
//		ConfigUtils.config();
	}

	@Before
	public void setUp() {
		codigoTerritorio = 123;
		codigoCliente = 123;
	}
	
	public void listaClientes() {
		try {
			List<Cliente> lista = ClienteService.listarClientes(codigoTerritorio);
			//int sizeExpected = 10;
			//int sizeActual = lista.size();
			//Assert.assertEquals("Os tamanhos nao sao iguais.", sizeExpected, sizeActual);
			assertNotNull(lista);
			assertTrue(lista.size() > 0);
			
		}catch (Exception e) {
			Log.e(TAG, "ClienteServiceTest - Erro na execução de teste unitário do método listaClientes.");
		}
	}
	
	public void obterDetalheCliente() {
		try {
			Cliente cliente = ClienteService.obterDetalheCliente(codigoCliente);
			assertNotNull("Cliente e nulo.", cliente);
		}catch (Exception e) {
			Log.e(TAG, "ClienteServiceTest - Erro na execução de teste unitário do método obterDetalheCliente.");
		}
	}
	
	public void listarSituacaoClientes() {
		try {
			List<SituacaoCliente> lista = ClienteService.listarSituacaoClientes(codigoTerritorio);
			assertNotNull(lista);
			assertTrue(lista.size() > 0);
			
		}catch (Exception e) {
			Log.e(TAG, "ClienteServiceTest - Erro na execução de teste unitário do método listarSituacaoClientes.");
		}
	}
	
	public void atualizarFavorito() {
		try {
			boolean sucesso = ClienteService.atualizarFavorito(codigoCliente, "S");
			assertTrue("Nao foi possivel atualizar favorito.", sucesso);
		}catch (Exception e) {
			Log.e(TAG, "ClienteServiceTest - Erro na execução de teste unitário do método atualizarFavorito.");
		}
	}
	
	@Test
	public void obterRelacionamentoCliente(){
		try {
			Cliente cliente = ClienteService.obterRelacionamentoCliente(codigoCliente);
			assertNotNull("Cliente e nulo.", cliente);
		}catch (Exception e) {
			Log.e(TAG, "ClienteServiceTest - Erro na execução de teste unitário do método obterRelacionamentoCliente.");
		}
	}

}