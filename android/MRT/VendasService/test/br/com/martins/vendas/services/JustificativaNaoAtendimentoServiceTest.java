package br.com.martins.vendas.services;

import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.martins.vendas.util.ConfigUtils;
import br.com.martins.vendas.vo.JustificativaNaoAtendimento;

import com.brq.mobile.framework.util.DateUtil;

public class JustificativaNaoAtendimentoServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() {
		ConfigUtils.config();
	}

	@Test
	public void listarJustificativas() {
		try {
			List<JustificativaNaoAtendimento> listaJustificativas = JustificativaNaoAtendimentoService.listarJustificativas();

			int sizeExpected = 5;
			int sizeActual = listaJustificativas.size();
			Assert.assertEquals("Os tamanhos nao sao iguais.", sizeExpected, sizeActual);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void obterUltimaJustificativaCliente() {
		try {
			// Não existe
			Integer codigoCliente = 1877993;

			// Existente
			// Integer codigoCliente = 1455752;

			JustificativaNaoAtendimento justificativa = JustificativaNaoAtendimentoService.obterUltimaJustificativaCliente(codigoCliente);
			Assert.assertNotNull("Justificativa é nula.", justificativa);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void atualizarCadastrarJustificativaNaoAtendimento() {
		try {
			boolean sucesso = JustificativaNaoAtendimentoService.atualizarJustificativaCliente(preencherJustificativa("A"));
			// boolean sucesso =
			// JustificativaNaoAtendimentoService.cadastrarJustificativaCliente(preencherJustificativa("C"));

			Assert.assertTrue("Erro ao atualizar justificativa.", sucesso);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private JustificativaNaoAtendimento preencherJustificativa(String valor) throws JSONException {
		JustificativaNaoAtendimento justificativaNaoAtendimento = new JustificativaNaoAtendimento();

		if (valor == "C") {
			justificativaNaoAtendimento.codigoCliente = 1877993;
			justificativaNaoAtendimento.codigoJustificativa = 1; // Competitividade
			justificativaNaoAtendimento.descricaoJustificativa = "";
			justificativaNaoAtendimento.dataJustificativa = DateUtil.obterDataAtual();
		} else {
			justificativaNaoAtendimento.codigoCliente = 1;
			justificativaNaoAtendimento.codigoJustificativa = 9999;
			justificativaNaoAtendimento.descricaoJustificativa = "Outros - horário indisponível";
			justificativaNaoAtendimento.dataJustificativa = DateUtil.obterDataAtual();
		}

		return justificativaNaoAtendimento;
	}

}