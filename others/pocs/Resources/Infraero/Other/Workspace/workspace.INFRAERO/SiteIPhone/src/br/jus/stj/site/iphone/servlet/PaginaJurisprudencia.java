package br.jus.stj.site.iphone.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import pix.brs.BRSException;
import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.ConsultaJurisprudencia;
import br.jus.stj.mobile.bd.pojo.JurisprudenciaPojo;
import br.jus.stj.site.iphone.PadraoPaginas;
import br.jus.stj.site.iphone.Padroes;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.HTML;
import br.jus.stj.site.iphone.ui.Img;
import br.jus.stj.site.iphone.ui.Texto;

public class PaginaJurisprudencia extends PadraoPaginas {

	private HttpSession session;

	@Override
	public String gerarPagina() throws SystemException {
		return null;
	}

	@Override
	public String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException {
		this.session = session;
		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		// topo das páginas
		Div div = new Div("titulo");
		A at = new A("", true);
		at.add(new Img("imagens/botao-inicio-azul.png"));
		div.add(at);
		div.add(new Div("titulo_texto", new Texto("Jurisprudência")));
		body.add(div);
		
		// montar a página inicial
		if (metodo == null || metodo.equals("gerarPagina")) {
			gerarPaginaConsulta(body, "Jurisprudencia");
		} else if (metodo.equals("consultar")) {
			String parametro = listaParametros.get("parametro");
			at.setHref("Pagina?p=Jurisprudencia");
			if (parametro == null || parametro.trim().equals("")) {
				pagina.clear();
				return gerarPaginaMensagem("Jurisprudência",
						"O critério de pesquisa não foi informado.",
						"Pagina?p=Jurisprudencia");
			}
			at.setHref("Pagina?p=Jurisprudencia");
			try {
			gerarPaginaConsultaResultado(body, parametro, listaParametros.get("pagina"));
			} catch (BRSException e) {
				pagina.clear();
				return gerarPaginaMensagem("Jurisprudência",
						"Nenhum registro encontrado.",
						"Pagina?p=Jurisprudencia");
			}
		}  else if (metodo.equals("detalhar")) {
			String parametro = listaParametros.get("parametro");
			String pag = listaParametros.get("pagina");
			if (pag==null)
				pag = "1";
			at.setHref("Pagina?p=Jurisprudencia&m=consultar&parametro="+parametro+"&pagina="+pag);
			gerarPaginaDetalhar(body, listaParametros.get("posicao"));
		} else {
			return gerarPaginaErro400();
		}

		return pagina.toString();
	}

	@SuppressWarnings("unchecked")
	private void gerarPaginaConsultaResultado(Body body,
			String parametroConsulta, String pagina) throws SystemException,BRSException {
		if (pagina == null)
			pagina = "1";
		
		Map<String,Object> map = new ConsultaJurisprudencia().consultaPaginaLista(parametroConsulta, pagina);
		
		List<JurisprudenciaPojo> lista = (List<JurisprudenciaPojo>) map.get("lista");
		session.setAttribute("listaJurisprudencia", lista);
	
		
		int pag = Integer.parseInt(pagina);
		
		int i = pag * ConsultaJurisprudencia.RESULTADOS_POR_PAGINA - ConsultaJurisprudencia.RESULTADOS_POR_PAGINA;
		int max = (Integer) map.get("resultados");
		int geral =0;
		for (JurisprudenciaPojo pj : lista) {
			i++;
			montarLista(body, parametroConsulta, i, geral, pagina ,pj);
			geral++;
			
		}
		
		Div div = new Div("arredondado");
		div.add(new Img("imagens/nvg_primeiro_inativo.png"));
		div.add(new Img("imagens/nvg_anterior_inativo.png"));

		if (pag != 1) {
			{
				A a = new A("Pagina?p=Jurisprudencia&m=consultar&parametro="+parametroConsulta+"pagina=1",
						true);
				a.add(new Img("imagens/nvg_primeiro.png"));
				div.add(a);
			}
			{
				A a = new A("Pagina?p=Jurisprudencia&m=consultar&parametro="+parametroConsulta+"&pagina="+(pag-1),
						true);
				a.add(new Img("imagens/nvg_anterior.png"));
				div.add(a);
			}
		}else{
			div.add(new Img("imagens/nvg_primeiro_inativo.png"));
			div.add(new Img("imagens/nvg_anterior_inativo.png"));
		}
		
		double totalPaginasD = (double) max / ConsultaJurisprudencia.RESULTADOS_POR_PAGINA;
		int totalPaginas = (int)totalPaginasD;
		totalPaginas = (totalPaginasD > totalPaginas ? totalPaginas+1: totalPaginas);
		if (pag < totalPaginas) {
			{
				A a = new A("Pagina?p=Jurisprudencia&m=consultar&parametro="
						+ parametroConsulta + "&pagina="+(pag+1), true);
				a.add(new Img("imagens/nvg_proximo.png"));
				div.add(a);
			}
			{
				A a = new A("Pagina?p=Jurisprudencia&m=consultar&parametro="
						+ parametroConsulta+"&pagina="+totalPaginas, true);
				a.add(new Img("imagens/nvg_ultimo.png"));
				div.add(a);
			}
		} else {
			div.add(new Img("imagens/nvg_proximo_inativo.png"));
			div.add(new Img("imagens/nvg_ultimo_inativo.png"));
		}
		body.add(div);
	}

	private void montarLista(Body body, String parametro, int posicaoGeral, int posicaoLista, String pagina , JurisprudenciaPojo pojo)
			throws SystemException {
		
		Div div = new Div("arredondado");

		A a = new A("Pagina?p=Jurisprudencia&m=detalhar&parametro="+parametro+"&posicao=" + posicaoLista + "&pagina="+pagina,
				true,"arredondado");
		
		// div esquerda
		Div dEsquerda = new Div("lista_esquerda");
		dEsquerda.add(new Texto(" " + posicaoGeral + " "));

		// div central
		Div dCentral = new Div();
		Div d1 = new Div("arredondado_link");

		d1.add(new Texto(pojo.getTopico()));
		d1.add(new Texto(" "));
		d1.add(new Texto(pojo.getCategoria()));
		//d1.add(a);

		Div d2 = new Div("lista_central", new Texto("Ministro "+pojo.getRelator()));

		Div d3 = new Div("lista_central");
		d3.add(new Texto(pojo.getDataPublicacaoFonte()));
		d3.add(new Texto(" "));
		d3.add(new Texto(pojo.getDataPublicacao()));

		Div d4 = new Div("lista_central");
		d4.add(new Texto("Decisão: "));
		d4.add(new Texto(pojo.getDataJulgamento()));

		dCentral.add(d1);
		dCentral.add(d2);
		dCentral.add(d3);
		dCentral.add(d4);

		// div direita
		Div dDireita = new Div("lista_base");
		dDireita.add(new Texto(pojo.getResumoEmenta(), true, true));

		div.add(dEsquerda);
		div.add(dCentral);
		div.add(dDireita);
		
		a.add(div);
		
		body.add(a);

	}

	/**
	 * Cria a página incial das noticias
	 * 
	 * @param body
	 * @throws SystemException
	 */
	@SuppressWarnings("unchecked")
	private void gerarPaginaDetalhar(Body body, String posicao)
			throws SystemException {

		List<JurisprudenciaPojo> lista;
		try {
			lista = (List<JurisprudenciaPojo>) session
					.getAttribute("listaJurisprudencia");
		} catch (Exception e) {
			// "Pagina?p=Jurisprudencia"
			return;
		}

		JurisprudenciaPojo pojo = lista.get(new Integer(posicao));

		Div div = new Div("arredondado");

		// dados da jurisprudência
		div.add(new Div("arredondado_chamada_negrito_sem_borda", new Texto("Processo")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getProcesso())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Relator(a)")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getRelator())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Órgão Julgador")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getOrgaoJulgador())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Data do Julgamento")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getDataJulgamento())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Data da Publicação/Fonte")));
		Div d = new Div("arredondado_chamada_simples");
		d.add(new Texto(pojo.getDataPublicacaoFonte()));
		d.add(new Texto(" "));
		d.add(new Texto(pojo.getDataPublicacao()));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Ementa")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getEmenta(), true, true)));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Acórdão")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getAcordao())));

		div.add(new Div("arredondado_chamada_negrito", new Texto("Informações Complementares")));
		div.add(new Div("arredondado_chamada_simples",new Texto(pojo.getInformacoesComplementares())));

		div.add(d);
		body.add(div);
		
		Div d1 = new Div("arredondado");
		// dados dos links
		if (pojo.getLinkIntegraAcordao() != null
				&& !pojo.getLinkIntegraAcordao().equals("")) {
			A a = new A(pojo.getLinkIntegraAcordao(), false);
			//https://ww2.stj.jus.br/revistaeletronica/Abre_Documento.asp?sSeq=944196&sReg=200901456959&sData=20100304&formato=PDF
			a.add(new Texto("Íntegra do Acórdão"));
			d1.add(new Div("arredondado_link",a));
		} else {
			d1.add(new Div(new Texto("Íntegra do Acórdão")));
		}

		Div d2 = new Div("arredondado");
		if (pojo.getLinkAcompanhamentoProcessual() != null
				&& !pojo.getLinkAcompanhamentoProcessual().equals("")) {
			A a = new A("Pagina?p=Processos&m=detalhar&parametro="
					+ pojo.getLinkAcompanhamentoProcessual(), true);
			a.add(new Texto("Acompanhamento Processual"));
			d2.add(new Div("arredondado_link",a));
		} else {
			d2.add(new Div(new Texto("AcompanhamentoProcessual")));
		}
		
		body.add(d1);
		body.add(d2);
	}
	private String formatarData(String data) {
		return data.substring(6) + "/" + data.substring(4, 6) + "/"
				+ data.substring(0, 4);
	}
}
