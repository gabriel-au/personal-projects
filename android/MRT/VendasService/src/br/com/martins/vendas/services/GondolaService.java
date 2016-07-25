package br.com.martins.vendas.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.martins.vendas.dao.GondolaDAO;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Gondola;

/**
 * Classe responsável por expor os serviços relacionados a Cliente.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class GondolaService {

	/**
	 * Método que altera um determinado item na gôndola do cliente
	 * @param gondola - informações a serem alteradas
	 * @return boolean - true para sucesso ou false para erro
	 * @throws Exception
	 */
	public static boolean alterarItemGondola(Gondola gondola) throws Exception {
		return GondolaDAO.alterarItemGondola(gondola);
	}
	
	/**
	 * Método que obtem um determinado item na gôndola do cliente
	 * @param codigoCliente - código do cliente
	 * @param posicaoMercadoria - posição da mercadoria
	 * @return Gondola - informações da gôndola
	 * @throws Exception
	 */
	public static Gondola obterItemGondolaCliente(Integer codigoCliente, Integer posicaoMercadoria) throws Exception {
		return GondolaDAO.obterItemGondolaCliente(codigoCliente, posicaoMercadoria);
	}
	
	/**
	 * Obtem a lista de itens da gondola temporária do cliente
	 * @return List<Gondola> - lista com os itens da gondola do cliente
	 * @throws Exception
	 */
	public static List<Gondola> listarItensTemporariosGondolaCliente(Integer codigoCliente) throws Exception {
		return GondolaDAO.listarItensTemporariosGondolaCliente(codigoCliente);
	}
	
	/**
	 * Obtem a lista de itens da gondola do cliente
	 * @return List<Gondola> - lista com os itens da gondola do cliente
	 * @throws Exception
	 */
	public static List<Gondola> listarItensGondolaCliente(Integer codigoCliente) throws Exception {
		return GondolaDAO.listarItensGondolaCliente(codigoCliente);
	}

	/**
	 * Deletar item da gondola do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @param posicaoMercadoria - posicao da mercadoria na gondola
	 * @return boolean - delecao efetuada com sucesso ou falha
	 */
	public static boolean deletarItemGondola(Integer codigoCliente, Integer posicaoMercadoria) throws Exception {
		return GondolaDAO.deletarItemGondola(codigoCliente, posicaoMercadoria);
	}
	
	/**
	 * Método para obter a última posição da mercadoria na gondola do cliente
	 * @param codigoCliente - codigo do cliente
	 * @return int - última posição da mercadoria
	 * @throws SQLException
	 */
	public static int obterUltimaPosicaoMercadoriaGondola(Integer codigoCliente) throws SQLException {
		return GondolaDAO.obterUltimaPosicaoMercadoriaGondola(codigoCliente);
	}

	/**
	 * Método para cadastrar item na gondola do cliente
	 * @param gondola - informações do item
	 * @return boolean - true para sucesso ou false para erro
	 * @throws Exception
	 */
	public static boolean cadastrarItemGondola(Gondola gondola) throws Exception {
		return GondolaDAO.cadastrarItemGondola(gondola);
	}
	
	/**
	 * Método para cadastrar item na gondola do cliente
	 * @param gondola - informações do item
	 * @return boolean - true para sucesso ou false para erro
	 * @throws Exception
	 */
	public static Gondola adicionarItemGondola(Integer codigoCliente, Item item) throws Exception {
		
		Gondola gondola = new Gondola();
		
		gondola.codigoCliente 			 = codigoCliente;
		gondola.codigoMercadoria 		 = item.mercadoria.codigo;
		gondola.codigoFilialExpedicao 	 = item.codigoFilialExpedicao;
		gondola.codigoFilialFaturamento  = item.codigoFilialFaturamento;
		gondola.descricaoMercadoria 	 = item.mercadoria.descricao;			
		gondola.valorUnitarioImposto 	 = item.valorUnitarioComImposto;
		
		/*
		 * valores fixos no cadastro
		 */
		gondola.precoCliente 				 = BigDecimal.ZERO;
		gondola.quantidadeMercadoriaAnterior = 0;
		gondola.quantidadeMercadoriaAtual    = 0;
		gondola.dataUltimaVenda 			 = "";
		
		Integer ultimaPosicao = GondolaDAO.obterUltimaPosicaoMercadoriaGondola(codigoCliente);
		gondola.posicaoMercadoria = ++ultimaPosicao;
		
		return gondola;
	}	
}