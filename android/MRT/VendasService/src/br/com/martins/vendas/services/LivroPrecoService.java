package br.com.martins.vendas.services;

import br.com.martins.vendas.dao.LivroPrecoDAO;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.LivroPreco;
import br.com.martins.vendas.vo.LivroPrecoGrupamentoCliente;

/**
 * @author BRQ Mobile Team
 * 
 */
public class LivroPrecoService {

	//private final static String	TAG	= LivroPrecoService.class.getName();

	/**
	 * Método void clsLivPco::ObtemIndPcoEsp(DdoLivPco * pDdoLivPco) da classe clsLivPco
	 * @param cliente 
	 * @param livroPreco 
	 * @param cliente 
	 * @param isEstadoPrice 
	 * @return
	 */
	public static LivroPreco obtemFlagsPrecoEspecial(Cliente cliente, LivroPreco livroPreco, LivroPrecoGrupamentoCliente livroPrecoGrupamentoCliente, boolean isEstadoPrice) {
		// Flags para o controle de Margem por Canal
		boolean m_bIndMerPcoEsp = false;	

		livroPreco.flagUtilizaMinimoPrecoEspecial = true;
		livroPreco.flagUtilizaFlexivelPrecoEspecial = true;
		livroPreco.flagUtilizaFracaoPrecoEspecial = true;
		livroPreco.flagUtilizaBeneficioPrecoEspecial = true;
		
		// Se a mercadoria possui preço especial, pesquisa
		// no livro por grupamento de clientes
		if (ClienteService.isClienteGrupamentoPrecoEspecial(cliente)){
			if (livroPrecoGrupamentoCliente != null){
				//double dblVlrUntBrt;
				//int iIndUtzFlx, iIndUtzMnm, iIndUtzBfc, iIndUtzFrc;
				if (livroPreco.valorUnitarioBruto.doubleValue() > 0.00){											
					m_bIndMerPcoEsp = true;;					
				}				
				/*pDdoLivPco->iIndUtzFlxPcoEsp = iIndUtzFlx;
				pDdoLivPco->iIndUtzMnmPcoEsp = iIndUtzMnm;
				pDdoLivPco->iIndUtzFrcPcoEsp = iIndUtzFrc;
				pDdoLivPco->iIndUtzBfvPcoEsp = iIndUtzBfc;*/
				
				livroPreco.flagUtilizaMinimoPrecoEspecial = livroPrecoGrupamentoCliente.flagUtilizaMinimo;
				livroPreco.flagUtilizaFlexivelPrecoEspecial = livroPrecoGrupamentoCliente.flagUtilizaFlexivel;
				livroPreco.flagUtilizaFracaoPrecoEspecial = livroPrecoGrupamentoCliente.flagUtilizaFracao;
				livroPreco.flagUtilizaBeneficioPrecoEspecial = livroPrecoGrupamentoCliente.flagUtilizaBeneficio;
			}
		}
		
		//Se estado for price não permitir dar flexivel em preco monitorado (Martins/Smart)
		//Regra solicitada por Divino
		if(isEstadoPrice &&  (LivroPrecoService.isPrecoMonitorado(livroPreco) || m_bIndMerPcoEsp)){
			livroPreco.flagUtilizaFlexivelPrecoEspecial = false;
		}
		return livroPreco;
	}
	
	public static LivroPreco recupera(Integer codigoFilialExpedicao, Integer codFilialFaturamento, Integer numeroRelacionamentoCliente, Integer codigoMercadoria) throws Exception {
		return LivroPrecoDAO.recupera(codigoFilialExpedicao, codFilialFaturamento, numeroRelacionamentoCliente, codigoMercadoria);		
	}
	
	public static boolean isPrecoMonitorado(LivroPreco livroPreco){
		if ("1".equals(livroPreco.indMercadoriaMonitorada)){
			return true;
		}
		return false;
	}
}