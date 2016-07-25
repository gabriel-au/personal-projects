package org.apache.jsp.noticias;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/includes/head.jsp");
    _jspx_dependants.add("/includes/toolbar.jsp");
  }

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
      out.write("<title>INFRAERO - Aeroportos Brasileiros</title>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> \n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"/>\n");
      out.write(" \n");
      out.write("<link rel=\"apple-touch-icon\" href=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo-touch-icon.png\"/> \n");
      out.write("<link rel=\"shortcut icon\" href=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo-touch-icon.ico\" type=\"image/icon\" />\n");
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
      out.write("</head>");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"toolbar\">\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/topo.png\" />\n");
      out.write("</div>");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"titulo\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/botao-inicio-azul.png\" /></a>\n");
      out.write("<div class=\"titulo_texto\">Noticias</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("22/01/2010\n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Acordo entre seguradora e terceiro n&atilde;o isenta segurado de ressarcir</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("16/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Infraero ministra treinamento para funcionÃ¡rios do Aeroporto de Fernando de Noronha</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("16/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Hangar do Aprendiz forma novos profissionais em Congonhas</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("16/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Aeroporto de Curitiba entra no ritmo da Copa do Mundo</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("14/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Infraero e ExÃ©rcito iniciam obras no sistema de pistas do Aeroporto de Guarulhos</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("14/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Infraero marca presenÃ§a na feira ABF Franchising</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("14/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Brasil conquista quatro medalhas na etapa de Lisboa da Copa do Mundo de JudÃ´</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("11/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Aeroporto de Rio Branco jÃ¡ pode voltar a operar voos internacionais</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("11/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Aeroporto de Manaus mostra resultados das aÃ§Ãµes adotadas no Terminal de Cargas</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("11/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Presidente da Infraero participa de comemoraÃ§Ã£o dos 11 anos do MinistÃ©rio da Defesa</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("11/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Tem inÃ­cio readequaÃ§Ã£o do canteiro de obras do Aeroporto de Guarulhos</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("10/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Terminal de Carga de Navegantes bate recorde pelo quinto mÃªs consecutivo</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("09/06/10 \n");
      out.write("<div class=\"arredondado_link_left\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/noticias.jsp\" >\n");
      out.write("Presidente Lula assina decreto de concessÃ£o do Aeroporto de SÃ£o GonÃ§alo do Amarante (RN)</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"titulo_mais_noticias\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/noticias/\" >\n");
      out.write("Mais Not&iacute;cias  </a>\n");
      out.write("</div>\n");
      out.write("\n");
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
