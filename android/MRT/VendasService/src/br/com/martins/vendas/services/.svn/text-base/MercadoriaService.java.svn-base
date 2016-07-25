package br.com.martins.vendas.services;

import java.sql.SQLException;
import java.util.List;

import br.com.martins.vendas.dao.CategoriaMercadoriaDAO;
import br.com.martins.vendas.dao.GrupoMercadoriaDAO;
import br.com.martins.vendas.dao.MercadoriaDAO;
import br.com.martins.vendas.dao.SubCategoriaMercadoriaDAO;
import br.com.martins.vendas.vo.CategoriaMercadoria;
import br.com.martins.vendas.vo.GrupoMercadoria;
import br.com.martins.vendas.vo.Mercadoria;
import br.com.martins.vendas.vo.RestricaoMercadoria;
import br.com.martins.vendas.vo.SubCategoriaMercadoria;

public class MercadoriaService {

	public static Mercadoria buscaMercadoria(Integer codigoMercadoria) throws SQLException {
		return MercadoriaDAO.buscaMercadoria(codigoMercadoria);
	}

	/**
	 * Retorna o codigo da mercadoria, de acordo com o codigo da mercadoria principals
	 * 
	 * @param mercadoria
	 * @return
	 * @throws Exception 
	 */
	public static Integer obtemCodigoMercadoria(Integer codigoMercadoria) throws Exception {
		Mercadoria mercadoria = buscaMercadoria(codigoMercadoria);
		return mercadoria.codigoMercadoriaPrincipal != null && mercadoria.codigoMercadoriaPrincipal != 0 ? mercadoria.codigoMercadoriaPrincipal : mercadoria.codigo;
	}

	
	/**
	 * 
	 * @param codCliente
	 * @return
	 */
	public static List<Integer> recuperaComportamentoDeCompras(Integer codCliente) {
		return MercadoriaDAO.recuperaComportamentoDeCompras(codCliente);
	}
	
	/**
	 * Método que recupera a quota da mercadoria
	 * @param codigoFilialExpedicao - código da filial de expedicao
	 * @param siglaEstadoDestino - sigla estado destino
	 * @param codigoMercadoria - código da mercadoria
	 * @return Integer - valor da quantidade de quota
	 */
	public static Integer recuperaQuota(Integer codigoFilialExpedicao, String siglaEstadoDestino, Integer codigoMercadoria){
		return MercadoriaDAO.recuperaQuota(codigoFilialExpedicao, siglaEstadoDestino, codigoMercadoria);
	}
	
	public static RestricaoMercadoria obtemRestricaoMercadoria(Integer codigoMercadoria, Integer codigoFilialExpedicao, Integer codigoAtividade, String siglaEstadoDestino){
		return MercadoriaDAO.obtemRestricaoMercadoria(codigoMercadoria, codigoFilialExpedicao, codigoAtividade, siglaEstadoDestino);
	}
	
	
	
	public static GrupoMercadoria obtemGrupoMercadoria(Integer codigoGrupo) {
		return GrupoMercadoriaDAO.buscarGrupoMercadoria(codigoGrupo);
	}
	
	public static List<GrupoMercadoria> obtemGrupoMercadoria() {
		return GrupoMercadoriaDAO.listarGrupoMercadoria();
	}
	
	public static List<GrupoMercadoria> obtemGruposMercadoria(Integer codigoCategoria) {
		return GrupoMercadoriaDAO.listarGrupoMercadoria(codigoCategoria);
	}

	
	public static List<CategoriaMercadoria> obtemCategoriaMercadoria() {
		return CategoriaMercadoriaDAO.listarCategoriaMercadoria();
	}
	
	public static List<CategoriaMercadoria> obtemCategoriaMercadoria(Integer codigoGrupo) {
		return CategoriaMercadoriaDAO.listarCategoriaMercadoria(codigoGrupo);
	}
	
	public static CategoriaMercadoria obtemCategoriaMercadoria(Integer codigoGrupo, Integer codigoCategoria) {
		return CategoriaMercadoriaDAO.recuparCategoriaMercadoria(codigoGrupo, codigoCategoria);
	}

	public static CategoriaMercadoria obtemCategoriaMercadoria(Integer codigoCategoria, String descricaoCategoria) {
		return CategoriaMercadoriaDAO.recuparCategoriaMercadoria(codigoCategoria, descricaoCategoria);
	}
	
	public static SubCategoriaMercadoria obtemSubCategoriaMercadoria(Integer codigoSubCategoria, String descricaoSubCategoria) {
		return SubCategoriaMercadoriaDAO.recuperarSubCategoriaMercadoria(codigoSubCategoria, descricaoSubCategoria);
	}
	
	public static List<SubCategoriaMercadoria> obtemSubCategoriaMercadoria() {
		return SubCategoriaMercadoriaDAO.listarSubCategoriaMercadoria();
	}
	
	public static List<SubCategoriaMercadoria> obtemSubCategoriaMercadoria(Integer codigoGrupo, Integer codigoCategoria) {
		return SubCategoriaMercadoriaDAO.listarSubCategoriaMercadoria(codigoGrupo, codigoCategoria);
	}
}