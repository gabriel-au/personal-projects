package br.jus.stj.site.iphone.servlet;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.PesquisaProcessual;
import br.jus.stj.mobile.bd.pojo.ProcessualDecisoesPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualFasesPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualNumerosOrigemPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualPartesAdvogadosPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualPeticoesPojo;
import br.jus.stj.mobile.bd.pojo.ProcessualPojo;
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
import br.jus.stj.site.iphone.ui.P;
import br.jus.stj.site.iphone.ui.Script;
import br.jus.stj.site.iphone.ui.Select;
import br.jus.stj.site.iphone.ui.Texto;

public class PaginaProcessos extends PadraoPaginas {

	private String parametro;
	@Override
	public String gerarPagina() throws SystemException {
		return null;
	}

	@Override
	public String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException {
		HTML pagina = new HTML();
		Head head = Padroes.getHeader();
		//head.add(new Style("css/styleJurisprudencia.css"));
		head.add(new Script("js/script.js"));
		pagina.add(head);
		Body body = Padroes.getBody();
		pagina.add(body);

		// topo das páginas
		Div div = new Div("titulo");
		A at = new A("/", true);
		at.add(new Img("imagens/botao-inicio-azul.png"));
		div.add(at);
		div.add(new Div("titulo_texto", new Texto("Processos")));
		body.add(div);

		// montar a página inicial
		if (metodo == null || metodo.equals("gerarPagina")) {
			gerarPaginaConsultaProcesso(body, "Processos",TipoPesquisaProcesso.NUMERO,"");
		} else if (metodo.equals("consultar")) {
			String parametro = listaParametros.get("parametro");

			if (parametro == null || parametro.trim().equals("")) {
				pagina.clear();
				return gerarPaginaMensagem("Processos",
						"O critério de pesquisa não foi informado.",
						"Pagina?p=Processos");
			}
			at.setHref("Pagina?p=Processos");
			
			String opcaoConsulta = listaParametros.get("opcaoConsulta");
			
			TipoPesquisaProcesso tipo;
			try {
				tipo = TipoPesquisaProcesso.valueOf(opcaoConsulta);
			} catch (Exception e) {
				tipo = TipoPesquisaProcesso.NUMERO;
			}
			

			gerarPaginaConsultaResultado(body, parametro, tipo);
		} else if (metodo.equals("detalhar")) {
			parametro = listaParametros.get("parametro");
			at.setHref("Pagina?p=Processos");
			gerarPaginaDetalhar(body, parametro);
		} else if (metodo.equals("listar")) {
			String lista = listaParametros.get("lista");
			parametro = listaParametros.get("parametro");
			if (lista != null) {
				pagina.clear();
				pagina.add(body);
				if (lista.equals("numeroOrigem")) {
					getListaNumeroOrigens(body, parametro);

				} else if (lista.equals("partesAdvogados")) {
					getListaPartesAdvogados(body, parametro);

				} else if (lista.equals("peticoes")) {
					getListaPeticoes(body, parametro);

				} else if (lista.equals("fases")) {
					getListaFases(body, parametro);

				} else if (lista.equals("decisoes")) {
					getListaDecisoes(body, parametro);

				} else {
					return gerarPaginaErro400();
				}
			}
		} else {
			return gerarPaginaErro400();
		}

		return pagina.toString();
	}

	public void gerarPaginaConsultaProcesso(Body body, String metodoAction,
			TipoPesquisaProcesso selecionado, String texto)
			throws SystemException {
		Div divPrincipal = new Div("arredondado");

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

			div.add(lista);
			form.add(div);
		}
		{
			Div div = new Div("arredondado_chamada_simples_center");
			div.add(new Input(Input.INPUT_TEXT, "parametro", "input_text",
					texto, "50"));
			div.add(new Input(Input.INPUT_SUBMIT, "valor", "input_submit", "",
					"50"));
			form.add(div);
		}
		form.add(new P(""));
		divPrincipal.add(form);

		body.add(divPrincipal);
	}

	private void gerarPaginaConsultaResultado(Body body, String parametro,
			TipoPesquisaProcesso tipo) throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();

		for (ProcessualPojo pojo : cp.consultaPaginaLista(parametro, tipo)) {
			Div div = new Div("arredondado");

			Div d1 = new Div("arredondado_link");
			A a1 = new A("Pagina?p=Processos&m=detalhar&parametro="
					+ pojo.getNumeroRegistro(), true);
			a1.add(new Div("arredondado_chamada_negrito_sem_borda", new Texto(
			"Processo / UF")));
			a1.add(new Texto(pojo.getProcesso()));
			a1.add(new Texto(" - "));
			a1.add(new Texto(pojo.getUf()));
			d1.add(a1);
			div.add(d1);

			
			Div d2 = new Div("arredondado_link");
			A a2 = new A("Pagina?p=Processos&m=detalhar&parametro="
					+ pojo.getNumeroRegistro(), true);
			a2.add(new Div("arredondado_chamada_negrito", new Texto(
			"Número de Registro")));
			a2.add(new Texto(pojo.getNumeroRegistro()));
			d2.add(a2);
			div.add(d2);

			div.add(new Div("arredondado_chamada_negrito", new Texto("NUP")));
			if (pojo.getNup() != null && !pojo.getNup().equals("")) {
				div.add(new Div("arredondado_chamada_simples", new Texto(pojo.getNup())));
			} else {
				div.add(new Div("arredondado_chamada_simples", new Texto("---")));
			}
			
			

			div.add(new Div("arredondado_chamada_negrito", new Texto(
					"Data de Autuação")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getDataAutuacao())));

			div
					.add(new Div("arredondado_chamada_negrito", new Texto(
							"Partes")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getParteAutor())));
			div.add(new Div("arredondado_chamada_simples_espaco", new Texto(pojo
					.getParteReu())));

			body.add(div);
		}

	}

	private void gerarPaginaDetalhar(Body body, String parametro)
			throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();
		ProcessualPojo pojo = cp.consultaPaginaDetalhe(parametro);
		Div div = new Div("arredondado");

		div.add(new Div("arredondado_chamada_negrito_sem_borda", new Texto(
				"Processo / UF")));
		Div d1 = new Div("arredondado_chamada_simples");
		d1.add(new Texto(pojo.getProcesso()));
		d1.add(new Texto(" - "));
		d1.add(new Texto(pojo.getUf()));
		div.add(d1);

		div.add(new Div("arredondado_chamada_negrito", new Texto(
				"Número de Registro")));
		div.add(new Div("arredondado_chamada_simples", new Texto(pojo
				.getNumeroRegistro())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("NUP")));
		if (pojo.getNup() != null && !pojo.getNup().equals("")) {
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getNup())));
		} else {
			div.add(new Div("arredondado_chamada_simples", new Texto("---")));
		}

		div.add(new Div("arredondado_chamada_negrito", new Texto(
				"Data de Autuação")));
		div.add(new Div("arredondado_chamada_simples", new Texto(pojo
				.getDataAutuacao())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Autor")));
		div.add(new Div("arredondado_chamada_simples", new Texto(pojo
				.getParteAutor())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Reu")));
		div.add(new Div("arredondado_chamada_simples_final", new Texto(pojo
				.getParteReu())));
		body.add(div);

		// motar os 5 botões
		Div dBotoes = new Div("arredondado_center");

		A a1 = new A(
				"javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=numeroOrigem&parametro="
						+ pojo.getNumeroRegistro() + "');", false);
		A a2 = new A(
				"javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=partesAdvogados&parametro="
						+ pojo.getNumeroRegistro() + "');", false);
		A a3 = new A(
				"javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=peticoes&parametro="
						+ pojo.getNumeroRegistro() + "');", false);
		A a4 = new A(
				"javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=fases&parametro="
						+ pojo.getNumeroRegistro() + "');", false);
		A a5 = new A(
				"javascript:wmCarrega('Pagina?p=Processos&m=listar&lista=decisoes&parametro="
						+ pojo.getNumeroRegistro() + "');", false);

		a1.add(new Img("imagens/btn_proc_num_origem.png"));
		a2.add(new Img("imagens/btn_proc_partes_advogados.png"));
		a3.add(new Img("imagens/btn_proc_peticoes.png"));
		a4.add(new Img("imagens/btn_proc_fases.png"));
		a5.add(new Img("imagens/btn_proc_decisoes.png"));
		{
			Div d = new Div("arredondado_chamada_simples_center");
			d.add(a1);
			d.add(a2);
			dBotoes.add(d);
		}
		{
			Div d = new Div("arredondado_chamada_simples_center");
			d.add(a3);
			d.add(a4);
			dBotoes.add(d);
		}
		{
			Div d = new Div("arredondado_chamada_simples_center");
			d.add(a5);
			dBotoes.add(d);
		}

		body.add(dBotoes);

		Div divConteudo = new Div("");
		divConteudo.setId("conteudo");

		body.add(divConteudo);

	}

	public void getListaNumeroOrigens(Body body, String parametro)
			throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();
		body.clear();

		Div div = new Div("arredondado");

		for (ProcessualNumerosOrigemPojo pojo : cp
				.getListaNumeroOrigens(parametro)) {

			A a = new A("Pagina?p=Processos&m=consultar&opcaoConsulta=NUMERO_ORIGEM&parametro="+pojo.getNumero()+"&valor=", true);
			a.add(new Texto(pojo.getNumero()));

			div.add(new Div("arredondado_link", a));
		}
		body.add(div);
	}

	public void getListaPartesAdvogados(Body body, String parametro)
			throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();
		body.clear();
		Div div = new Div("arredondado");

		boolean primeiro = true;
		String style = "arredondado_chamada_negrito_sem_borda";

		for (ProcessualPartesAdvogadosPojo pojo : cp
				.getListaPartesAdvogados(parametro)) {
			
			//Tem que separar os nomes e depois juntar com o caractere + (soma)
			String tripaNome = "";
			
			StringTokenizer tok = new StringTokenizer(pojo.getNome());
			
			while(tok.hasMoreElements())
				tripaNome += tok.nextToken(" ")+"+";
			
			//remove o último caractere +
			tripaNome = tripaNome.substring(0, tripaNome.length()-1);
			
			

			A a = null;
			//se for advogado o link tem que ser diferente.
			if( pojo.getCategoria().toUpperCase().equals("ADVOGADO") )
				a = new A("Pagina?p=Processos&m=consultar&opcaoConsulta="+TipoPesquisaProcesso.NOME_ADVOGADO+"&parametro="+tripaNome+"&valor=", true);
			else//ser for nome de parte
				a = new A("Pagina?p=Processos&m=consultar&opcaoConsulta="+TipoPesquisaProcesso.NOME_PARTE+"&parametro="+tripaNome+"&valor=", true);
			a.add(new Texto(pojo.getNome()));

			div.add(new Div(style, new Texto(pojo.getCategoria())));
			div.add(new Div("arredondado_link", a));
			if (primeiro) {
				primeiro = false;
				style = "arredondado_chamada_negrito";

			}
		}
		body.add(div);
	}

	public void getListaPeticoes(Body body, String parametro)
			throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();
		body.clear();
		
		List<ProcessualPeticoesPojo> lista = cp.getListaPeticoes(parametro);
		
		if( lista == null || lista.size() == 0 )
		{
			Div div = new Div("arredondado");
			
			div.add(new Texto("Não existem petições") );

			body.add(div);
		}
		
		
		for (ProcessualPeticoesPojo pojo : lista) {
			Div div = new Div("arredondado");

			div.add(new Div("arredondado_chamada_negrito_sem_borda", new Texto(
					"Número")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getNumero())));
			div.add(new Div("arredondado_chamada_negrito", new Texto("Tipo")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getTipo())));
			div.add(new Div("arredondado_chamada_negrito", new Texto(
					"Peticionário")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getPeticionario())));
			div.add(new Div("arredondado_chamada_negrito", new Texto(
					"Protocolo")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getProtocolo())));
			div.add(new Div("arredondado_chamada_negrito", new Texto(
					"Processamento")));
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getProcessamento())));

			body.add(div);
		}
	}

	public void getListaFases(Body body, String parametro)
			throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();
		body.clear();
		List<ProcessualFasesPojo> lista = cp.getListaFases(parametro);
		
		if( lista == null || lista.size() == 0 )
		{
			Div div = new Div("arredondado");
			
			div.add(new Texto("Não existem fases") );

			body.add(div);
		}
		
		for (ProcessualFasesPojo pojo : lista) {
			Div div = new Div("arredondado");

			Div d1 = new Div("arredondado_chamada_negrito_sem_borda");
			d1.add(new Texto(pojo.getData()));
			d1.add(new Texto(" - "));
			d1.add(new Texto(pojo.getHora()));
			div.add(d1);
			div.add(new Div("arredondado_chamada_simples", new Texto(pojo
					.getTexto())));

			body.add(div);
		}
	}

	public void getListaDecisoes(Body body, String parametro)
			throws SystemException {
		PesquisaProcessual cp = new PesquisaProcessual();
		body.clear();
		body.clear();
		List<ProcessualDecisoesPojo> lista =  cp.getListaDecisoes(parametro);
		if( lista == null || lista.size() == 0 )
		{
			Div div = new Div("arredondado");
			
			div.add(new Texto("Não existem decisões") );

			body.add(div);
		}
		for (ProcessualDecisoesPojo pojo : lista) {
			Div div = new Div("arredondado");

			A a = new A("Pagina?p=DJEletronico&opcaoConsulta="+TipoPesquisaProcesso.NUMERO_REGISTRO+"&calendario="+pojo.getData()+"&tipoData=publicado&m=detalhar&parametro="
					+ pojo.getRegistro(), true);
			a.add(new Texto(pojo.getTexto()));

			div.add(new Div("arredondado_link", a));

			body.add(div);
		}
	}
}
