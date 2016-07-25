package br.com.martins.vendas.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.martins.vendas.dao.CidadePrecoDAO;
import br.com.martins.vendas.dao.ClienteDAO;
import br.com.martins.vendas.enums.GrupamentoCliente;
import br.com.martins.vendas.exception.IntegrationException;
import br.com.martins.vendas.vo.CidadePreco;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.PontoMinimoPedidoSegmento;
import br.com.martins.vendas.vo.SituacaoCliente;

import com.brq.mobile.framework.util.DateUtil;

/**
 * Classe responsável por expor os serviços relacionados a Cliente.
 * 
 * @author BRQ Mobile Team
 * 
 */
public class ClienteService {

	public static final String	ATENDIMENTO_FORA_PRAZO = "0"; // Vermelho
	public static final String	ATENDIMENTO_DENTRO_PRAZO = "1"; // Verde
	public static final String	ATENDIMENTO_LIMITE_PRAZO = "2"; // Amarelo

	/**
	 * Metodo void clsTabLivPco::InformaDadosGrupoCliente(DdoGrpCli * pDdoGrpCli)
	 * da classe cslTabLivPco
	 * @param cliente 
	 * @param cliente 
	 * @return
	 */
	public static boolean isClienteGrupamentoPrecoEspecial(Cliente cliente) {
		// Se o cliente tem grupamento, e seu grupamento tem preço especial
		if (cliente.codigoGrupoCliente != Integer.valueOf(GrupamentoCliente.CLIENTESEMGRUPAMENTO.codigoGrupamentoCliente) && ("*".equals(cliente.flagClienteEspecial))){
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se cliente é do Tribanco.
	 * 
	 * @param cliente
	 * @return
	 */
	public static boolean isClienteTribanco(Cliente cliente) {
		if (cliente.tipoLimiteCredito.equals("G") || cliente.tipoLimiteCredito.equals("T") 
				|| cliente.tipoLimiteCredito.equals("V") || cliente.tipoLimiteCredito.equals("A")) {
			return true;
		}
		return false;
	}

	/**
	 * Método responsável por gerar a lista de clientes
	 * 
	 * @param codigoTerritorio - código do território selecionado
	 * @return objeto do tipo <code>java.util.List<Cliente></code>.
	 * @throws Exception
	 */
	public static List<Cliente> listarClientes(Integer codigoTerritorio) throws Exception {
		return ClienteDAO.listarClientes(codigoTerritorio);
	}

	/**
	 * Obtem detalhes do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @return Cliente - informacoes do cliente
	 */
	public static Cliente obterDetalheCliente(Integer codigoCliente, Integer codigoTerritorioVenda) {
		return ClienteDAO.obterDetalheCliente(codigoCliente, codigoTerritorioVenda);
	}
	
	/**
	 * Obtem detalhes do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @return Cliente - informacoes do cliente
	 */
	public static Cliente obterDetalheCliente(Integer codigoCliente) {
		return obterDetalheCliente(codigoCliente, null);
	}

	/**
	 * Atualiza o status de favorecido do cliente
	 * 
	 * @param codigoCliente - codigo do cliente
	 * @param indicativoFavorito - indicativo de atualizacao: S - cliente favorecido ou N - remover o status de favorecido
	 * @return boolean - atualizacao efetuada com sucesso ou falha
	 */
	public static boolean atualizarFavorito(Integer codigoCliente, String indicativoFavorito) {
		return ClienteDAO.atualizarFavorito(codigoCliente, indicativoFavorito);
	}

	/**
	 * Recupera lista de clientes com informacoes sobre a situacao
	 * 
	 * @param codigoTerritorio - código do território
	 * @return List<SituacaoCliente> - lista de clientes
	 */
	public static List<SituacaoCliente> listarSituacaoClientes(Integer codigoTerritorio) {
		return ClienteDAO.listarSituacaoClientes(codigoTerritorio);
	}

	/**
	 * Recupera informacoes do relacionamento do cliente
	 * 
	 * @param codigoCliente
	 * @return Cliente - informacoes do cliente
	 */
	public static Cliente obterRelacionamentoCliente(Integer codigoCliente) {
		return ClienteDAO.obterRelacionamentoCliente(codigoCliente);
	}

	/**
	 * Recupera o ponto minimo do cliente relacionado ao estado do cliente, codigo estrategico do
	 * cliente e codigo do segmento do cliente. O codigo estrategico do cliente e obtido atraves do  canal do mesmo.
	 * 
	 * @param estadoUF - estado do cliente
	 * @param canal - canal do cliente
	 * @param codigoSegmento - codigo de segmento do cliente
	 * @return instancia de PontoMinimoPedidoSegmento totalmente preenchido
	 */
	public static PontoMinimoPedidoSegmento recuperaPontoMinimoPedidoSegmento(String estadoUF, Integer canal, Integer codigoSegmento) {
		return ClienteDAO.recuperaPontoMinimoPedidoSegmento(estadoUF, canal, codigoSegmento);
	}

	/**
	 * Método que identifica qual o status do atendimento do cliente
	 * 
	 * @param dataPedido - data do último pedido efetuado
	 * @param maxDiasSemAtendimento - parâmetro do máximo de dias sem atendimento ao cliente
	 * @param medDiasSemAtendimento - parâmetro de média de dias sem atendimento ao cliente
	 * @return String - o status do atendimento (0 - atendimento fora do prazo, 1 - atendimento dentro do prazo e 2 - atendimento no limite do prazo
	 */
	public static String verificaStatusAtendimento(Date dataUltimoPedido, Integer maximoDiasSemAtendimento, Integer mediaDiasSemAtendimento) {

		// Verifica se o cliente tem data do último pedido
		if (dataUltimoPedido != null) {

			Calendar dataHoje = Calendar.getInstance();

			Calendar dataPedido = Calendar.getInstance();
			dataPedido.setTime(dataUltimoPedido);

			int medDiasSemAtendimento = (mediaDiasSemAtendimento != null) ? mediaDiasSemAtendimento : 0;
			int maxDiasSemAtendimento = (maximoDiasSemAtendimento != null) ? maximoDiasSemAtendimento : 0;

			long diferencaDias = DateUtil.diferencaEmDias(dataPedido.getTime(), dataHoje.getTime());

			if (0 < medDiasSemAtendimento && 0 < maxDiasSemAtendimento) {

				// if ((long) date4Hoje >= (long) date4Ped) {
				if (dataHoje.compareTo(dataPedido) != -1) {

					// lDifDatPed = (long) date4Hoje - (long) date4Ped;

					if (diferencaDias < medDiasSemAtendimento) {
						return ATENDIMENTO_DENTRO_PRAZO;
						// m_bCorVerde = TRUE;

					} else if (diferencaDias >= medDiasSemAtendimento && diferencaDias < maxDiasSemAtendimento) {
						return ATENDIMENTO_LIMITE_PRAZO;
						// m_bCorAmarelo = TRUE;
					}
				}
				// Date4 date4Posivacao;
				// date4Posivacao = date4Ped + (long) iMaxDiaSemAtd;
			} else {
				// Modo antigo de colorir a lista de clientes
				// if ((long) date4Hoje >= (long) date4Ped) {
				if (dataHoje.compareTo(dataPedido) != -1) {

					int mesHoje = dataHoje.get(Calendar.MONTH);
					int mesPedido = dataPedido.get(Calendar.MONTH);

					// if (((long) date4Hoje - (long) date4Ped) < 7) {
					// Quantidade de dias na semana = 7
					if (diferencaDias < 7) {

						if (dataHoje.get(Calendar.DAY_OF_WEEK) >= dataPedido.get(Calendar.DAY_OF_WEEK)) {
							return ATENDIMENTO_DENTRO_PRAZO;
							// m_bCorAmarelo = FALSE;
						} else {

							if (mesHoje == mesPedido) {
								return ATENDIMENTO_LIMITE_PRAZO;
								// m_bCorAmarelo = TRUE;
							}
						}

					} else {
						if (mesHoje == mesPedido) {
							return ATENDIMENTO_LIMITE_PRAZO;
							// m_bCorAmarelo = TRUE;
						}
					}
				}
			}
		}

		return ATENDIMENTO_FORA_PRAZO;
	}

	/**
	 * Método para obter o número do relacionamento do cliente na tabela
	 * 
	 * @param codigoFilialExpedicao
	 * @param codCidadePreco
	 * @return
	 * @throws Exception 
	 */
	public static Integer obtemNumeroRelacionamentoCliente(Integer codigoFilialExpedicao, Integer codFilialFaturamento, Integer codCidadePreco, Integer codTerritorio) throws IntegrationException {
		CidadePreco cidadePreco = CidadePrecoDAO.obtemCidadePreco(codigoFilialExpedicao, codCidadePreco);		

		if (cidadePreco != null) {
			return cidadePreco.numeroRelacionamentoCliente;			
		}
		return RelacaoGiroService.recuperarPorFilial(codTerritorio, codigoFilialExpedicao, codFilialFaturamento).numRelacaoCidade;
	}
	
	public static boolean validaTipoContratoFilial(Integer codigoCliente, Integer codigoFilialExpedicao, Integer tipoContratoCondicao) {
		return ClienteDAO.validaTipoContratoFilial(codigoCliente, codigoFilialExpedicao, tipoContratoCondicao);
	}

}