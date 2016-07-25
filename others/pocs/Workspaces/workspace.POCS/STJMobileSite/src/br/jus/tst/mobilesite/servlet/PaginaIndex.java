package br.jus.tst.mobilesite.servlet;

import br.jus.stj.mobile.SystemException;
import br.jus.tst.mobilesite.PadraoPaginas;
import br.jus.tst.mobilesite.Padroes;
import br.jus.tst.mobilesite.ui.A;
import br.jus.tst.mobilesite.ui.Body;
import br.jus.tst.mobilesite.ui.Div;
import br.jus.tst.mobilesite.ui.HTML;
import br.jus.tst.mobilesite.ui.Img;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class PaginaIndex extends PadraoPaginas
{
  public String gerarPagina()
    throws SystemException
  {
    try
    {
/* 25 */       HTML pagina = new HTML();
/* 26 */       pagina.add(Padroes.getHeader());
/* 27 */       Body body = Padroes.getBody();
/* 28 */       pagina.add(body);

/* 30 */       Div divPrincipal = new Div("links");

/* 32 */       Div divNoticia = new Div("links_secundario");
/* 33 */       Div divJurisprudencia = new Div("links_secundario");
/* 34 */       Div divProcessos = new Div("links_secundario");
/* 35 */       Div divDJEletronico = new Div("links_secundario");

/* 37 */       A linkNoticia = new A("Pagina?p=Noticias", true);
/* 38 */       Img imgNoticia = new Img("imagens/btn_noticias.png");
/* 39 */       linkNoticia.add(imgNoticia);
/* 40 */       divNoticia.add(linkNoticia);

/* 42 */       A linkJurisprudencia = new A("Pagina?p=Jurisprudencia", true);
/* 43 */       Img imgJurisprudencia = new Img("imagens/btn_jurisprudencia.png");
/* 44 */       linkJurisprudencia.add(imgJurisprudencia);
/* 45 */       divJurisprudencia.add(linkJurisprudencia);

/* 47 */       A linkProcessos = new A("Pagina?p=Processos", true);
/* 48 */       Img imgProcessos = new Img("imagens/btn_processos.png");
/* 49 */       linkProcessos.add(imgProcessos);
/* 50 */       divProcessos.add(linkProcessos);

/* 52 */       A linkDJEletronico = new A("Pagina?p=DJEletronico", true);
/* 53 */       Img imgDJEletronico = new Img("imagens/btn_dj-eletronico.png");
/* 54 */       linkDJEletronico.add(imgDJEletronico);
/* 55 */       divDJEletronico.add(linkDJEletronico);

/* 57 */       divPrincipal.add(divNoticia);
/* 58 */       divPrincipal.add(divJurisprudencia);
/* 59 */       divPrincipal.add(divProcessos);
/* 60 */       divPrincipal.add(divDJEletronico);
/* 61 */       body.add(divPrincipal);

/* 63 */       return pagina.toString(); }
    
    catch (SystemException e) {
    	throw new SystemException(e.getMessage());
    }
/* 65 */     
  }

  public String gerarPagina(String metodo, Map<String, String> listaParametros, HttpSession session)
    throws SystemException
  {
/* 72 */     return gerarPagina();
  }
}

/* Location:           /Users/Gabriel/Workspace/POCs/Workspaces/workspace.POCS/STJMobileSite/WebContent/WEB-INF/classes/
 * Qualified Name:     br.jus.tst.mobilesite.servlet.PaginaIndex
 * JD-Core Version:    0.6.0
 */