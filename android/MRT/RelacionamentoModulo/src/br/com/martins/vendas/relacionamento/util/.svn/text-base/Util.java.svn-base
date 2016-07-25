package br.com.martins.vendas.relacionamento.util;

import java.util.Date;

import com.brq.mobile.framework.util.DateUtil;

/**
 * Classe utilitária da aplicação
 */
public class Util {

	public static final String ATENDIMENTO_FORA_PRAZO   = "0"; // Vermelho
	public static final String ATENDIMENTO_DENTRO_PRAZO = "1"; // Verde
	public static final String ATENDIMENTO_LIMITE_PRAZO = "2"; // Amarelo

	/**
	 * Método que identifica qual o status do atendimento do cliente
	 * 
	 * @param dataPedido
	 *            data do último pedido efetuado
	 * @param maxDiasSemAtendimento
	 *            parâmetro do m�ximo de dias sem atendimento ao cliente
	 * @param medDiasSemAtendimento
	 *            parâmetro de m�dia de dias sem atendimento ao cliente
	 * @return String status do atendimento (0 - atendimento fora do prazo, 1 -
	 *         atendimento dentro do prazo e 2 - atendimento no limite do
	 *         prazo).
	 */
	public static String verificaStatusAtendimento(Date dataPedido, Integer maxDiasSemAtendimento, Integer medDiasSemAtendimento) {

		String statusAtendimento = ATENDIMENTO_FORA_PRAZO;
		Date dataHoje = DateUtil.obterDataAtual();
		if (dataPedido.compareTo(dataHoje) < 1) {
			long qntDiasUltimoAtendimento = DateUtil.diferencaEmDias(dataPedido, dataHoje);
			if (qntDiasUltimoAtendimento < medDiasSemAtendimento) {
				statusAtendimento = ATENDIMENTO_DENTRO_PRAZO;
			} else if (qntDiasUltimoAtendimento >= medDiasSemAtendimento && qntDiasUltimoAtendimento < maxDiasSemAtendimento) {
				statusAtendimento = ATENDIMENTO_LIMITE_PRAZO;
			}
		}
		return statusAtendimento;
	}

}