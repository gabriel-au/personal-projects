package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class noticiaDetalhe_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \n");
      out.write("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
      out.write("\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<title>Tribunal Superior do Trabalho</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> \n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"/> \n");
      out.write("<!--  link rel=\"apple-touch-icon\" href=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo-touch-icon.png\"/--> \n");
      out.write("<!-- link  rel=\"shortcut icon\" href=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo-touch-icon.ico\" type=\"image/icon\" /--> \n");
      out.write("<style type=\"text/css\" media=\"screen\">@import \"");
      out.print(request.getContextPath());
      out.write("/css/style.css\";</style>\n");
      out.write("<style type=\"text/css\" media=\"screen\">@import \"");
      out.print(request.getContextPath());
      out.write("/css/styleComplemento.css\";</style>\n");
      out.write("<script type=\"application/x-javascript\">\n");
      out.write("if (navigator.userAgent.indexOf('iPhone') != -1) {\n");
      out.write("        addEventListener(\"load\", function() {\n");
      out.write("                setTimeout(hideURLbar, 0);\n");
      out.write("        }, false);\n");
      out.write("}\n");
      out.write("\n");
      out.write("function hideURLbar() {\n");
      out.write("        window.scrollTo(0, 1);\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"toolbar\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/topo.png\" /></a>\n");
      out.write("</div>\n");
      out.write("<div class=\"titulo\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias.jsp\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/botao-inicio-azul.png\" /></a>\n");
      out.write("<div class=\"titulo_texto\">\n");
      out.write("Not&iacute;cias\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"arredondado_chamada_rigth\">\n");
      out.write("04/10/2010\n");
      out.write("</div>\n");
      out.write("<!-- div class=\"arredondado_chamada_dupla_borda\">\n");
      out.write("DECIS&Atilde;O\n");
      out.write("</div-->\n");
      out.write("<div class=\"arredondado_titulo\">\n");
      out.write("Ao declarar que o espÃ³lio â representado por filhos e esposa do trabalhador - nÃ£o detÃ©m legitimidade para ajuizar aÃ§Ã£o de indenizaÃ§Ã£o por danos morais e materiais decorrentes da morte do empregado\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada\">\n");
      out.write("Ao declarar que o espÃ³lio â representado por filhos e esposa do trabalhador - nÃ£o detÃ©m legitimidade para ajuizar aÃ§Ã£o de indenizaÃ§Ã£o por danos morais e materiais decorrentes da morte do empregado, extinguindo o processo sem resoluÃ§Ã£o do mÃ©rito, o Tribunal Regional do Trabalho da 24Âª RegiÃ£o (MS) violou o artigo 943 do CÃ³digo Civil. Em razÃ£o desse entendimento, a Quarta Turma do Tribunal Superior do Trabalho determinou o retorno dos autos ao Regional para analisar o mÃ©rito do pedido. \n");
      out.write("<br>\n");
      out.write("Segundo a relatora do recurso de revista, ministra Maria de Assis Calsing, os sucessores tÃªm legitimidade para propor qualquer aÃ§Ã£o de indenizaÃ§Ã£o, por tratar-se de direito patrimonial, conforme o artigo 943 do CÃ³digo Civil. A relatora esclarece que isso ocorre âporque o que se transmite Ã© o direito de aÃ§Ã£o e nÃ£o o direito material em si, pelo fato de nÃ£o se tratar de direito personalÃ­ssimo, o que impediria sua transmissÃ£o a terceirosâ. \n");
      out.write("<br>\n");
      out.write("O Regional, ao dar provimento ao recurso ordinÃ¡rio do FrigorÃ­fico Sul Ltda. (Frigosul), considerou que o direito Ã  reparaÃ§Ã£o de dano moral Ã© personalÃ­ssimo, o que quer dizer que apenas o indivÃ­duo que Ã© vÃ­tima tem legitimidade para requerer a reparaÃ§Ã£o. Inconformados, os sucessores do trabalhador - sua esposa e filhos â recorreram ao TST. \n");
      out.write("<br>\n");
      out.write("Com posicionamento diverso do TRT/MS, a ministra Calsing, do TST, explica que, de acordo com o artigo 1.784 do CÃ³digo Civil, aberta a sucessÃ£o, a heranÃ§a Ã© transmitida aos herdeiros legÃ­timos e que, por sua vez, o artigo 943, tambÃ©m do atual CC, dispÃµe que âo direito de exigir reparaÃ§Ã£o e a obrigaÃ§Ã£o de prestÃ¡-la transmitem-se com a heranÃ§aâ. Nesse sentido, a relatora cita precedentes dos ministros Aloysio CorrÃªa da Veiga, Maria Cristina Irigoyen Peduzzi, Dora Maria da Costa e AntÃ´nio JosÃ© de Barros Levenhagen. \n");
      out.write("<br>\n");
      out.write("Em sua fundamentaÃ§Ã£o, a ministra Calsing conclui pela legitimidade dos sucessores para propor a aÃ§Ã£o de indenizaÃ§Ã£o, jÃ¡ que se refere a direito patrimonial. A relatora destaca que âos filhos e a esposa sÃ£o os legÃ­timos herdeiros do falecido e o pedido de indenizaÃ§Ã£o por danos morais e materiais decorre do contrato de trabalho havido entre a empresa e o trabalhador. O pleito nÃ£o deve, pois, ser considerado direito personalÃ­ssimo do empregado falecido, porquanto a natureza da aÃ§Ã£o Ã© patrimonialâ. \n");
      out.write("<br>\n");
      out.write("Seguindo o voto da relatora, a Quarta Turma, verificando ter o acÃ³rdÃ£o regional violado o artigo 943 do CC, deu provimento ao recurso de revista e, afastando a ilegitimidade ativa do espÃ³lio, determinou o retorno dos autos ao Regional para prosseguir na anÃ¡lise do mÃ©rito do pedido de indenizaÃ§Ã£o por danos morais e materiais, como entender de direito. (RR - 19400-08.2009.5.24.0061) \n");
      out.write("<br>\n");
      out.write("(Lourdes Tavares) \n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada\">\n");
      out.write("Esta matÃ©ria tem carÃ¡ter informativo, sem cunho oficial.<br> \n");
      out.write("Permitida a reproduÃ§Ã£o mediante citaÃ§Ã£o da fonte<br>\n");
      out.write("Assessoria de ComunicaÃ§Ã£o Social<br>\n");
      out.write("Tribunal Superior do Trabalho<br>\n");
      out.write("Tel. (61) 3043-4404<br>\n");
      out.write("<a href=\"mailto:imprensa@tst.gov.br\">imprensa@tst.gov.br</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_negrito\">\n");
      out.write("Esta p&aacute;gina foi acessada: 2991 vezes\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"titulo_mais_noticias\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/Pagina?p=Noticias&m=listar&dataInicio=null\" >\n");
      out.write("Mais Not&iacute;cias  </a>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
