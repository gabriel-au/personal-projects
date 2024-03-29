package br.jus.tst.mobilesite.servlet;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.pojo.TipoPesquisaProcesso;
import br.jus.tst.mobilesite.PadraoPaginas;
import br.jus.tst.mobilesite.Padroes;
import br.jus.tst.mobilesite.ui.A;
import br.jus.tst.mobilesite.ui.Body;
import br.jus.tst.mobilesite.ui.Div;
import br.jus.tst.mobilesite.ui.Form;
import br.jus.tst.mobilesite.ui.HTML;
import br.jus.tst.mobilesite.ui.Head;
import br.jus.tst.mobilesite.ui.Img;
import br.jus.tst.mobilesite.ui.Input;
import br.jus.tst.mobilesite.ui.Option;
import br.jus.tst.mobilesite.ui.Script;
import br.jus.tst.mobilesite.ui.Select;
import br.jus.tst.mobilesite.ui.Style;
import br.jus.tst.mobilesite.ui.Texto;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class PaginaDJEletronico extends PadraoPaginas {
	public String gerarPagina() throws SystemException {
		return null;
	}

	public String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException {
		HTML pagina = new HTML();
		Head head = Padroes.getHeader();
		head.add(new Style("css/styleJurisprudencia.css"));
		head.add(new Script("js/script.js"));
		pagina.add(head);
		Body body = Padroes.getBody();
		pagina.add(body);

		Div div = new Div("titulo");
		A at = new A("", true);
		at.add(new Img("imagens/botao-inicio-azul.png"));
		div.add(at);
		div.add(new Div("titulo_texto", new Texto("DJE")));
		body.add(div);

		if ((metodo == null) || (metodo.equals("gerarPagina"))) {
			gerarPaginaInicial(body);
		} else if (metodo.equals("acesso")) {
			at.setHref("Pagina?p=DJEletronico");
			gerarPaginaConsultaProcesso(body, "DJEletronico",
					TipoPesquisaProcesso.NUMERO, "");
		} else if (metodo.equals("exibirOpcao")) {
			String parametro = (String) listaParametros.get("parametro");
			pagina.clear();
			body.clear();
			pagina.add(body);
			body.add(gerarPaginaOpcaoConsulta(parametro));
		} else if (metodo.equals("consultar")) {
			String parametro = (String) listaParametros.get("parametro");

			if ((parametro == null) || (parametro.trim().equals(""))) {
				pagina.clear();
				return gerarPaginaMensagem("DJE",
						"O crit�rio de pesquisa n�o foi informado.",
						"Pagina?p=Processos");
			}
			at.setHref("Pagina?p=DJEletronico");

			String opcaoConsulta = (String) listaParametros
					.get("opcaoConsulta");
			TipoPesquisaProcesso tipo;
			try {
				tipo = TipoPesquisaProcesso.valueOf(opcaoConsulta);
			} catch (Exception e) {
				tipo = TipoPesquisaProcesso.INTEGRAL;
			}

			gerarPaginaConsultaResultado(body, parametro, tipo);
		} else {
			return gerarPaginaErro400();
		}

		return pagina.toString();
	}

	private Div gerarPaginaOpcaoConsulta(String parametro)
			throws SystemException {
		TipoPesquisaProcesso tipo;
		try {
			tipo = TipoPesquisaProcesso.valueOf(parametro);
		} catch (Exception e) {
			tipo = TipoPesquisaProcesso.INTEGRAL;
		}
		PaginaDJEletronicoConsultas tela = new PaginaDJEletronicoConsultas();

		switch (tipo) {
		case INTEGRAL:
			return tela.getNumero();
		case NOME_ADVOGADO:
			return tela.getNumeroRegistro();
		case NOME_PARTE:
			return tela.getNumeroUnico();
		case NUMERO:
			return tela.getNumeroOrigem();
		case NUMERO_ORIGEM:
			return tela.getOAB();
		case NUMERO_REGISTRO:
			return tela.getNomeParte();
		case NUMERO_UNICO:
			return tela.getNomeAdvogado();
		case OAB:
			return tela.getIntegral();
		}
		return tela.getIntegral();
	}

	private void gerarPaginaConsultaResultado(Body body, String parametro,
			TipoPesquisaProcesso tipo) throws SystemException {
	}

	public void gerarPaginaInicial(Body body) throws SystemException {
		{
			Div div = new Div("arredondado");
			A a = new A("Pagina?p=DJEletronico&m=acesso", true);
			a.add(new Texto("Acesso Direto ao Sistema"));
			div.add(new Div("arredondado_link", a));
			body.add(div);
		}

		{
			Div div = new Div("arredondado");
			A a = new A("dje_guia.jsp", true);
			a.add(new Texto("Guia para Consulta � Publica��o"));
			div.add(new Div("arredondado_link", a));
			body.add(div);
		}
	}

	public void gerarPaginaConsultaProcesso(Body body, String metodoAction,
			TipoPesquisaProcesso selecionado, String texto)
			throws SystemException {
		Div divPrincipal = new Div("arredondado");

		divPrincipal.add(new Script("js/script_dje.js"));

		{
			Div div = new Div("arredondado_titulo");
			div.add(new Texto("Consulta"));
			divPrincipal.add(div);
		}

		Form form = new Form("Pagina");
		form.add(new Input("hidden", "p", metodoAction));
		form.add(new Input("hidden", "m", "consultar"));
		form.setMethod("GET");

		form.add(new Div("arredondado_chamada", new Texto(
				"Par�metro de pesquisa:")));

		Div div = new Div("arredondado_chamada_simples_center");
		Select lista = new Select("input_select", "opcaoConsulta");
		lista.setOnchange("exibirOpcao()");
		lista.add(new Option(TipoPesquisaProcesso.INTEGRAL + "",
				"�ntegra para Download"));
		lista.add(new Option(TipoPesquisaProcesso.NUMERO + "", "N�mero do Processo"));
		lista.add(new Option(TipoPesquisaProcesso.NUMERO_REGISTRO + "",
				"N�mero de REGISTRO"));
		lista.add(new Option(TipoPesquisaProcesso.NUMERO_UNICO + "",
				"N�mero �nico de Processo (NUP)"));
		lista.add(new Option(TipoPesquisaProcesso.NUMERO_ORIGEM + "",
				"N�mero do Processo na ORIGEM"));
		lista.add(new Option(TipoPesquisaProcesso.OAB + "", "OAB do Advogado"));
		lista.add(new Option(TipoPesquisaProcesso.NOME_PARTE + "", "Nome da PARTE"));
		lista.add(new Option(TipoPesquisaProcesso.NOME_ADVOGADO + "",
				"Nome do ADVOGADO"));

		div.add(lista);
		form.add(div);

		PaginaDJEletronicoConsultas consulta = new PaginaDJEletronicoConsultas();
		Div divConteudo = new Div();
		divConteudo.setId("conteudo");
		divConteudo.add(consulta.getIntegral());
		form.add(divConteudo);

		divPrincipal.add(form);
		body.add(divPrincipal);
	}
}