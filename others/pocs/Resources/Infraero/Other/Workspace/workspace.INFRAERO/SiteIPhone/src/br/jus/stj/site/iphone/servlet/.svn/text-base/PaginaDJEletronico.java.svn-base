package br.jus.stj.site.iphone.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.ConsultaDiarioJustica;
import br.jus.stj.mobile.bd.pojo.DiarioJusticaPojo;
import br.jus.stj.mobile.bd.pojo.PessoaPojo;
import br.jus.stj.mobile.bd.pojo.TipoPesquisaProcesso;
import br.jus.stj.site.iphone.PadraoPaginas;
import br.jus.stj.site.iphone.Padroes;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.Form;
import br.jus.stj.site.iphone.ui.HTML;
import br.jus.stj.site.iphone.ui.Head;
import br.jus.stj.site.iphone.ui.Img;
import br.jus.stj.site.iphone.ui.Input;
import br.jus.stj.site.iphone.ui.Option;
import br.jus.stj.site.iphone.ui.Script;
import br.jus.stj.site.iphone.ui.Select;
import br.jus.stj.site.iphone.ui.Texto;

public class PaginaDJEletronico extends PadraoPaginas {

	@Override
	public String gerarPagina() throws SystemException {
		return null;
	}

	@Override
	public String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException {
		// this.session = session;
		HTML pagina = new HTML();
		Head head = Padroes.getHeader();
		//head.add(new Style("css/styleJurisprudencia.css"));
		head.add(new Script("js/script.js"));
		pagina.add(head);
		Body body = Padroes.getBody();
		pagina.add(body);

		// topo das páginas
		Div div = new Div("titulo");
		A at = new A("", true);
		at.add(new Img("imagens/botao-inicio-azul.png"));
		div.add(at);
		div.add(new Div("titulo_texto", new Texto("DJe")));
		body.add(div);

		// montar a página inicial
		if (metodo == null || metodo.equals("gerarPagina")) {
			gerarPaginaInicial(body);
		} else if (metodo.equals("acesso")) {
			at.setHref("Pagina?p=DJEletronico");
			gerarPaginaConsultaProcesso(body, "DJEletronico",
					TipoPesquisaProcesso.NUMERO, "");
		} else if (metodo.equals("exibirOpcao")) {
			String parametro = listaParametros.get("parametro");
			pagina.clear();
			body.clear();
			pagina.add(body);
			body.add(gerarPaginaOpcaoConsulta(parametro));
		} else if (metodo.equals("consultar")) {
			at.setHref("Pagina?p=DJEletronico&m=acesso");
			String parametro = listaParametros.get("parametro");

			String opcaoConsulta = listaParametros.get("opcaoConsulta");

			TipoPesquisaProcesso tipo;
			try {
				tipo = TipoPesquisaProcesso.valueOf(opcaoConsulta);
			} catch (Exception e) {
				tipo = TipoPesquisaProcesso.INTEGRAL;
			}
			
			if (parametro == null || parametro.trim().equals("")) {
				pagina.clear();
				return gerarPaginaMensagem("DJE",
						"O critério de pesquisa não foi informado.",
						"Pagina?p=Processos");
			}

			String data = listaParametros.get("calendario");
			String dataInicial = listaParametros.get("calendarioInicio");
			String dataFinal = listaParametros.get("calendarioFinal");
			boolean dataPublicacao;
			try {
				dataPublicacao = listaParametros.get("dataPublicacao").equals(
						"publicado");
			} catch (Exception e) {
				dataPublicacao = true;
			}
			gerarPaginaConsultaResultado(body, tipo, parametro, data,
					dataInicial, dataFinal, dataPublicacao);
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
			tipo = TipoPesquisaProcesso.NUMERO;
		}
		PaginaDJEletronicoConsultas tela = new PaginaDJEletronicoConsultas();

		// div.add(new Texto(tipo.name()));
		switch (tipo) {
		case NUMERO:
			return tela.getNumero();
		case NUMERO_REGISTRO:
			return tela.getNumeroRegistro();
		case NUMERO_UNICO:
			return tela.getNumeroUnico();
		case NUMERO_ORIGEM:
			return tela.getNumeroOrigem();
		case OAB:
			return tela.getOAB();
		case NOME_PARTE:
			return tela.getNomeParte();
		case NOME_ADVOGADO:
			return tela.getNomeAdvogado();
		case INTEGRAL:
			return tela.getIntegral();
		default:
			return tela.getNumero();
		}

	}
	

	private void gerarPaginaConsultaResultado(Body body,
			TipoPesquisaProcesso tipo, String parametro, String data,
			String dataInicial, String dataFinal, boolean dataPublicacao)
			throws SystemException {

		ConsultaDiarioJustica consulta = new ConsultaDiarioJustica();

		switch (tipo) {
		case NUMERO: {
			List<DiarioJusticaPojo> lista = consulta
					.listarPorNumeroProcessoSTJ(parametro, data, dataPublicacao);

			montarExibicao(body, lista);
			return;
		}
		case NUMERO_REGISTRO: {
			List<DiarioJusticaPojo> lista = consulta
					.listarPorNumeroRegistroSTJ(parametro, data, dataPublicacao);

			montarExibicao(body, lista);
			return;
		}
		case NUMERO_UNICO: {
			List<DiarioJusticaPojo> lista = consulta
					.pesquisarPorNumeroUnico(parametro, data, dataPublicacao);

			montarExibicao(body, lista);
			return;
		}
		case NUMERO_ORIGEM: {
			List<DiarioJusticaPojo> lista = consulta
					.listarPorNumeroProcessoSTJ(parametro, data, dataPublicacao);

			montarExibicao(body, lista);
			return;
		}
		case OAB: {
			List<DiarioJusticaPojo> lista = consulta
					.listarPorOAB(parametro, data, dataInicial, dataFinal, dataPublicacao);

			montarExibicao(body, lista);
			return;
		}
		case NOME_PARTE:{
			List<PessoaPojo> lista = consulta.listarPorNomeParte(parametro,
					data, dataInicial, dataFinal, dataPublicacao);
			montarExibicaoPessoa(body, lista);
			return; 
		}
		case NOME_ADVOGADO: {
			List<PessoaPojo> lista = consulta.listarPorNomeAdvogado(parametro,
					data, dataInicial, dataFinal, dataPublicacao);
			montarExibicaoPessoa(body, lista);
			return; 
		}
		case SEQ_PARTE: {
			List<DiarioJusticaPojo> lista = consulta.listarPorSeqParte(parametro, data, dataPublicacao);
			montarExibicao(body, lista);
			return;
		}

		case INTEGRAL:
			return;
		default:
			return;
		}
	}
	
	
	private void montarExibicaoPessoa(Body body, List<PessoaPojo> lista)
			throws SystemException {
		Div divPrincipal = new Div("arredondado");

		if (lista == null || lista.size() == 0) {
			Div div = new Div("arredondado_titulo");
			div.add(new Texto("Não foi encontrado nenhum registro"));
			divPrincipal.add(div);
			body.add(divPrincipal);
			return;
		}

		int i = 1;
		boolean primeiro = true;
		for (PessoaPojo pojo : lista) {
			Div div;
			if (primeiro)
				div = new Div("arredondado_link_left");
			else
				div = new Div("arredondado_link_left_espaco_superior");
			primeiro = false;
			A a = new A(pojo.getParametro(), true);
			a.add((new Texto(i++ + " - ")));
			a.add((new Texto(pojo.getNome())));
			a.add((new Texto(" (")));
			a.add((new Texto(pojo.getOab())));
			a.add((new Texto(")")));
			div.add(a);
			divPrincipal.add(div);

		}

		body.add(divPrincipal);

	}

	private void montarExibicao(Body body, List<DiarioJusticaPojo> lista)
			throws SystemException {

		String capitulo = "";
		String subCapitulo = "";

		Div divPrincipal = new Div("arredondado");
		boolean primeiro = true;
		boolean primeiroItem = true;

		if (lista == null || lista.size() == 0) {
			Div div = new Div("arredondado_titulo");
			div.add(new Texto("Não foi encontrado nenhum registro"));
			divPrincipal.add(div);
			body.add(divPrincipal);
			return;
		}

		for (DiarioJusticaPojo pojo : lista) {

			if (!capitulo.equals(pojo.getNomeCapitulo())) {
				capitulo = pojo.getNomeCapitulo();
				Div div;
				if (primeiro)
					div = new Div("arredondado_titulo");
				else
					div = new Div("arredondado_titulo_espaco_superior");
				primeiro = false;

				div.add(new Texto(capitulo));

				divPrincipal.add(div);
				subCapitulo = "";
			}
			if (!subCapitulo.equals(pojo.getNomeSubCapitulo())) {
				subCapitulo = pojo.getNomeSubCapitulo();
				Div div = new Div("arredondado_titulo_hora");

				div.add(new Texto(subCapitulo));

				divPrincipal.add(div);
				primeiroItem = true;
			}
			Div div;
			if (primeiroItem)
				div = new Div("arredondado_link_left");
			else
				div = new Div("arredondado_link_left_espaco_superior");
			primeiroItem = false;

			A a = new A(pojo.getLink(), false);
			a.add((new Texto(pojo.getTitulo())));
			div.add(a);
			divPrincipal.add(div);

		}
		body.add(divPrincipal);

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
			a.add(new Texto("Guia para Consulta à Publicação"));
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
		// montar o formuário
		Form form = new Form("Pagina");
		form.add(new Input(Input.INPUT_HIDDEN, "p", metodoAction));
		form.add(new Input(Input.INPUT_HIDDEN, "m", "consultar"));
		form.setMethod("GET");

		form.add(new Div("arredondado_chamada", new Texto(
				"Parâmetro de pesquisa:")));

		{
			Div div = new Div("arredondado_chamada_simples_center");
			Select lista = new Select("input_select", "opcaoConsulta");
			lista.setOnchange("exibirOpcao()");

			lista.add(new Option(TipoPesquisaProcesso.NUMERO + "",
					"Número do Processo"));
			lista.add(new Option(TipoPesquisaProcesso.NUMERO_REGISTRO + "",
					"Número de REGISTRO"));
			lista.add(new Option(TipoPesquisaProcesso.NUMERO_UNICO + "",
					"Número Único de Processo (NUP)"));
			lista.add(new Option(TipoPesquisaProcesso.NUMERO_ORIGEM + "",
					"Número do Processo na ORIGEM"));
			lista.add(new Option(TipoPesquisaProcesso.OAB + "",
					"OAB do Advogado"));
			lista.add(new Option(TipoPesquisaProcesso.NOME_PARTE + "",
					"Nome da PARTE"));
			lista.add(new Option(TipoPesquisaProcesso.NOME_ADVOGADO + "",
					"Nome do ADVOGADO"));
			lista.add(new Option(TipoPesquisaProcesso.INTEGRAL + "",
			"Íntegra para Download"));
			

			div.add(lista);
			form.add(div);
		}

		PaginaDJEletronicoConsultas consulta = new PaginaDJEletronicoConsultas();
		Div divConteudo = new Div();
		divConteudo.setId("conteudo");
		divConteudo.add(consulta.getNumero());
		form.add(divConteudo);

		divPrincipal.add(form);
		body.add(divPrincipal);
	}

}
