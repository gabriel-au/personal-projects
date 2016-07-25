package br.jus.stj.site.iphone.servlet;

import java.util.Map;

import javax.servlet.http.HttpSession;

import br.jus.stj.mobile.SystemException;
import br.jus.stj.site.iphone.PadraoPaginas;
import br.jus.stj.site.iphone.Padroes;
import br.jus.stj.site.iphone.ui.A;
import br.jus.stj.site.iphone.ui.Body;
import br.jus.stj.site.iphone.ui.Div;
import br.jus.stj.site.iphone.ui.HTML;
import br.jus.stj.site.iphone.ui.Img;
/**
 * Classe para montar a página inicial
 * @author Gustavo
 *
 */
public class PaginaIndex extends PadraoPaginas{

	@Override
	public String gerarPagina() throws SystemException {
		try {
			HTML pagina = new HTML();
			pagina.add(Padroes.getHeader());
			Body body = Padroes.getBody();
			pagina.add(body);

			Div divPrincipal = new Div("links");

			Div divNoticia = new Div("links_secundario");
			Div divJurisprudencia = new Div("links_secundario");
			Div divProcessos = new Div("links_secundario");
			Div divDJEletronico = new Div("links_secundario");

			A linkNoticia = new A("Pagina?p=Noticias",true);
			Img imgNoticia = new Img("imagens/btn_noticias.png");
			linkNoticia.add(imgNoticia);
			divNoticia.add(linkNoticia);

			A linkJurisprudencia = new A("Pagina?p=Jurisprudencia",true);
			Img imgJurisprudencia = new Img("imagens/btn_jurisprudencia.png");
			linkJurisprudencia.add(imgJurisprudencia);
			divJurisprudencia.add(linkJurisprudencia);

			A linkProcessos = new A("Pagina?p=Processos",true);
			Img imgProcessos = new Img("imagens/btn_processos.png");
			linkProcessos.add(imgProcessos);
			divProcessos.add(linkProcessos);

			A linkDJEletronico = new A("Pagina?p=DJEletronico",true);
			Img imgDJEletronico = new Img("imagens/btn_dj-eletronico.png");
			linkDJEletronico.add(imgDJEletronico);
			divDJEletronico.add(linkDJEletronico);
			
			divPrincipal.add(divNoticia);
			divPrincipal.add(divJurisprudencia);
			divPrincipal.add(divProcessos);
			divPrincipal.add(divDJEletronico);
			body.add(divPrincipal);
			
			return pagina.toString();
		} catch (SystemException e) {
			throw new SystemException(e.getMessage());			
		}
	}

	@Override
	public String gerarPagina(String metodo, Map<String, String> listaParametros, HttpSession session)
			throws SystemException {
		return gerarPagina();
	}
	
}
