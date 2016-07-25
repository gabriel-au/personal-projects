package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class jurisprudenciaDetalhe_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!-- by Gustavo Marcelo -->\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=8859-1\" /> \n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\"/> \n");
      out.write("<!-- link rel=\"apple-touch-icon\" href=\"");
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
      out.write("/Pagina?p=Jurisprudencia&m=paginar&parametro=0\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/botao-inicio-azul.png\" /></a>\n");
      out.write("<div class=\"titulo_texto\">\n");
      out.write("Jurisprud&ecirc;ncia\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"arredondado_chamada_negrito_sem_borda\">\n");
      out.write("Processo\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("AIRR - 38640-55.2003.5.03.0003 \n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_negrito\">\n");
      out.write("Relator(a)\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("Ministro Horácio Raymundo de Senna Pires \n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_negrito\">\n");
      out.write("&Oacute;rg&atilde;o Judicante\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("6ª TURMA\n");
      out.write("</div>\n");
      out.write("<!-- div class=\"arredondado_chamada_negrito\">\n");
      out.write("Data do Julgamento\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("01/12/2009\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_negrito\">\n");
      out.write("Data da Publica&ccedil;&atilde;o/Fonte\n");
      out.write("</div-->\n");
      out.write("<div class=\"arredondado_chamada_negrito\">\n");
      out.write("Ementa\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("Trata-se de agravo de instrumento interposto pela reclamada, contra despacho às fls. 82-83, denegatório do recurso de revista, ante a incidência das Súmulas nº 23, 126, 221 e 296 do TST.\n");
      out.write("<br> \n");
      out.write("Alega, a Agravante, ter demonstrado a natureza indenizatória das diárias de viagem, bem como a limitação da integração das diárias ao salário do reclamante. Assevera, ainda, não ser devido o pagamento de horas extras\n");
      out.write("<br> \n");
      out.write("Regularmente notificado, o agravado contraminutou o agravo de instrumento às fls. 85-88 e ofereceu contra-razões ao apelo principal às fls. 89-92.\n");
      out.write("<br> \n");
      out.write("Sem remessa dos autos ao d. Ministério Público do Trabalho, nos termos regimentais.\n");
      out.write("<br> \n");
      out.write("É o relatório. \n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_negrito\">\n");
      out.write("Ac&oacute;rd&atilde;o\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("ACORDAM os Ministros da Sexta Turma do Tribunal Superior do Trabalho, por unanimidade, negar provimento ao agravo de instrumento.\n");
      out.write("</div>\n");
      out.write("<!-- div class=\"arredondado_chamada_negrito\">\n");
      out.write("Informa&ccedil;&otilde;es Complementares\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("Aguardando an&aacute;lise.\n");
      out.write("</div-->\n");
      out.write("<div class=\"arredondado_chamada_simples\">\n");
      out.write("Data de Publicação: DJ 13/06/2008\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/InteiroTeorJurisprudencia.rtf\" >\n");
      out.write("&Iacute;ntegra do Ac&oacute;rd&atilde;o</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/\" >\n");
      out.write("AcompanhamentoProcessual</a>\n");
      out.write("</div>\n");
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
