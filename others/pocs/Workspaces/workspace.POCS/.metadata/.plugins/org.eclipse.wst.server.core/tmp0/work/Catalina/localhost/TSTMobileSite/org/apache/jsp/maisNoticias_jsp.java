package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class maisNoticias_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Superior Tribunal de Justi&ccedil;a - O Tribunal da Cidadania</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=8859-1\" /> \n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"/> \n");
      out.write("<!-- link rel=\"apple-touch-icon\" href=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo-touch-icon.png\"/-->\n");
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
      out.write("<div class=\"arredondado_titulo\">\n");
      out.write("04/10/2010\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("14h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("Corregedor-geral da Justiça do Trabalho inicia correição no Pará e Amapá</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("13h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("TST decide sobre cota para portadores de deficiência</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("12h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("Filhos e esposa são legítimos para pedir indenização por morte de trabalhador</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("11h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write(" Com amparo legal, hospital dispensa imotivadamente empregado concursado</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_espaco_superior\">\n");
      out.write("01/10/2010\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("18h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("Sexta Turma: concessionária de telefonia não pode terceirizar serviços de call center</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("16h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("Universidade pagará em dobro férias remuneradas fora do prazo legal</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("14h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("Greve não é motivo para demissão por justa causa</a>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_titulo_hora\">\n");
      out.write("12h07\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticiaDetalhe.jsp\" >\n");
      out.write("Contrato nulo não impede indenização por doença ocupacional</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"titulo_mais_noticias\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/maisNoticias.jsp\" >\n");
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
