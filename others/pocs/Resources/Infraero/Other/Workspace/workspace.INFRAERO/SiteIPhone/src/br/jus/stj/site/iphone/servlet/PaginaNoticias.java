package br.jus.stj.site.iphone.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.mobile.bd.consulta.ConsultaNoticia;
import br.jus.stj.mobile.bd.pojo.AuxiliarPojo;
import br.jus.stj.mobile.bd.pojo.NoticiaPojo;
import br.jus.stj.site.iphone.PadraoPaginas;
import br.jus.stj.site.iphone.Padroes;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.HTML;
import br.jus.stj.site.iphone.ui.Img;
import br.jus.stj.site.iphone.ui.Texto;

/**
 * Classe para implementar as noticias
 * 
 * @author gustavomarcelo
 * 
 */
public class PaginaNoticias extends PadraoPaginas {

	/**
	 * Método para gerar páginas inválidas
	 */
	@Override
	public String gerarPagina() throws SystemException {
		return gerarPaginaErro400().toString();
	}

	@Override
	public String gerarPagina(String metodo,
			Map<String, String> listaParametros, HttpSession session)
			throws SystemException {

		HTML pagina = new HTML();
		pagina.add(Padroes.getHeader());
		Body body = Padroes.getBody();
		pagina.add(body);

		// topo das páginas
		Div div = new Div("titulo");
		A at = new A("", true);
		at.add(new Img("imagens/botao-inicio-azul.png"));
		div.add(at);
		div.add(new Div("titulo_texto", new Texto("Sala de Notícias")));
		body.add(div);

		String paginaNoticia = listaParametros.get("paginaNoticia");
		if (paginaNoticia == null)
			paginaNoticia="1";
		// montar a página inicial
		if (metodo == null || metodo.equals("gerarPagina")) {
			gerarPaginaInicial(body);
			paginaNoticia = "1";
		} else if (metodo.equals("detalhar")) {
			at.setHref("Pagina?p=Noticias");
			String id = listaParametros.get("id");
			gerarPaginaDetalhe(body, id);
		} else if (metodo.equals("listar")) {
			at.setHref("Pagina?p=Noticias");
			gerarPaginaListar(body, paginaNoticia);
			paginaNoticia = String.valueOf(Integer.parseInt(paginaNoticia )+1);
		} else {
			return gerarPaginaErro400();
		}

		Div div2 = new Div("titulo_mais_noticias");
		A a = new A("Pagina?p=Noticias&m=listar&paginaNoticia=" + paginaNoticia, true);
		a.add(new Texto("Mais Notícias  "));
		div2.add(a);
		body.add(div2);

		return pagina.toString();
	}

	/**
	 * Cria a página incial das noticias
	 * 
	 * @param body
	 * @throws SystemException
	 */
	private void gerarPaginaInicial(Body body) throws SystemException {
		// neste ponto é realizada a consulta
		ConsultaNoticia c = new ConsultaNoticia();
		NoticiaPojo pojo = c.consultaPaginaInicial();

		Div divPrincipal = new Div("arredondado");

		// montar o título
		{
			Div div = new Div("arredondado_titulo");
			div.add(new Texto(pojo.getPaginaInicialTitulo()));
			divPrincipal.add(div);
		}
		{
			Div div = new Div("arredondado_chamada");
			div.add(new Texto(pojo.getPaginaInicialChamada()));

			A aChamada = new A("Pagina?p=Noticias&m=detalhar&id="
					+ pojo.getPaginaInicialLink(), true);
			aChamada.add(new Img("imagens/btn_leia_mais.png"));
			div.add(aChamada);

			divPrincipal.add(div);
		}
		body.add(divPrincipal);

		for (AuxiliarPojo link : pojo.getPaginaInicialLinkSecundarios()) {
			Div div = new Div("arredondado");
			
			A a = new A("Pagina?p=Noticias&m=detalhar&id="
					+ link.getParametro(), true);
			a.add(new Texto(link.getValor()));
			div.add(new Div("arredondado_link",a));

			body.add(div);
		}
	}

	/**
	 * Cria a página incial das noticias
	 * 
	 * @param body
	 * @throws SystemException
	 */
	private void gerarPaginaDetalhe(Body body, String id)
			throws SystemException {
		ConsultaNoticia c = new ConsultaNoticia();
		NoticiaPojo pojo = c.consultaPaginaDetalhe(id);

		Div divPricipal = new Div("arredondado");
		{
			Div div = new Div("arredondado_chamada_rigth");
			div.add(new Texto(pojo.getPaginaDetalheData()));
			div.add(new Texto(" - "));
			div.add(new Texto(pojo.getPaginaDetalheHora()));
			divPricipal.add(div);
		}
		{
			Div div = new Div("arredondado_chamada_dupla_borda");
			div.add(new Texto(pojo.getPaginaDetalheCategoria()));
			divPricipal.add(div);
		}
		{
			Div div = new Div("arredondado_titulo");
			div.add(new Texto(pojo.getPaginaDetalheTitulo()));
			divPricipal.add(div);
		}
		{
			Div div = new Div("arredondado_chamada");
			div.add(new Texto(pojo.getPaginaDetalheTexto(), true, true));
			divPricipal.add(div);
		}
		{
			Div div = new Div("arredondado_chamada_negrito");
			div.add(new Texto(pojo.getPaginaDetalheAutor()));
			divPricipal.add(div);
		}
		{
			Div div = new Div("arredondado_chamada_negrito");
			div.add(new Texto("Esta página foi acessada: "
					+ pojo.getPaginaDetalheAcesso() + " vezes"));
			divPricipal.add(div);
		}
		body.add(divPricipal);
	}

	/**
	 * Listar as noticias
	 * 
	 * @param body
	 * @throws SystemException
	 */
	private String gerarPaginaListar(Body body, String pagina)
	throws SystemException {
		ConsultaNoticia c = new ConsultaNoticia();
		List<NoticiaPojo> lista = c.consultaPaginaLista(pagina);

		String dataAtual = "";
		String dataInicio;
		if (lista != null && !lista.isEmpty()) {
			dataInicio = lista.get(0).getPaginaDetalheData();
		}
		Div divPricipal = new Div("arredondado");

		boolean primeiro = true;

		for (NoticiaPojo pojo : lista) {
			dataInicio = pojo.getPaginaDetalheData();
			if (!dataAtual.equals(dataInicio)) {
				Div div;
				if (primeiro)
					div = new Div("arredondado_titulo");
				else
					div = new Div("arredondado_titulo_espaco_superior");
				div.add(new Texto(pojo.getPaginaDetalheData()));
				divPricipal.add(div);
				dataAtual = dataInicio;
				primeiro = false;
			}

			{
				Div div = new Div("arredondado_titulo_hora");
				div.add(new Texto(pojo.getPaginaDetalheHora()));
				div.add(new Texto(" - "));
				div.add(new Texto(pojo.getPaginaDetalheCategoria()));
				divPricipal.add(div);
			}
			{
				Div div = new Div("arredondado_link_left");
				A a = new A("Pagina?p=Noticias&m=detalhar&id="
						+ pojo.getPaginaDetalheLink() + "&paginaNoticia="
						+ pagina, true);
				a.add(new Texto(pojo.getPaginaDetalheTitulo()));
				div.add(a);
				divPricipal.add(div);
			}
		}
		body.add(divPricipal);
		/*
		 * int day = new Integer(dataAtual.substring(0, 2)); int month = new
		 * Integer(dataAtual.substring(3, 5)) - 1; int year = new
		 * Integer(dataAtual.substring(6, 10));
		 * 
		 * GregorianCalendar gc = new GregorianCalendar(year, month, day);
		 * gc.add(Calendar.DATE, -1); SimpleDateFormat sdf = new
		 * SimpleDateFormat("dd/MM/yyyy"); return sdf.format(gc.getTime());
		 */
		return String.valueOf(Integer.parseInt(pagina) + 1);
	}

}
