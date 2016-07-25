package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class jurisprudenciaPesquisa_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/jurisprudencia.jsp\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/botao-inicio-azul.png\" /></a>\n");
      out.write("<div class=\"titulo_texto\">\n");
      out.write("Jurisprud&ecirc;ncia\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/jurisprudenciaDetalhe.jsp?parametro=0\" clazz=\"arredondado\" >\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"lista_esquerda\">\n");
      out.write(" 1 \n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("TST-AIRR-386/2003-003-03-40.8\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Relator Ministro: Horácio Raymundo de Senna Pires \n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Publicação: DJ 13/06/2008\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Julgamento: 11/06/2008\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_base\">\n");
      out.write("AGRAVO DE INSTRUMENTO. RECURSO DE REVISTA. DIÁRIAS. NATUREZA JURÍDICA. INTEGRAÇÃO.\n");
      out.write("<br>\n");
      out.write("... termos da Súmula nº 101 do TST, -integram o salário, pelo seu valor total e para efeitos indenizatórios...cento) do salário do empregado ... \n");
      out.write("</div>\n");
      out.write("</div></a>\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/jurisprudenciaDetalhe.jsp?parametro=1\" clazz=\"arredondado\" >\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"lista_esquerda\">\n");
      out.write(" 2 \n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("RR - 25900-34.2006.5.09.0669\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Relator Ministro: Horácio Raymundo de Senna Pires\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Publicação: DJ 13/06/2008\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Julgamento: 11/06/2008\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_base\">\n");
      out.write("RECURSO DE REVISTA. CONTRATAÇÃO SEM CONCURSO PÚBLICO. ADMINISTRAÇÃO PÚBLICA. NULIDADE. EFEITOS.\n");
      out.write("<br>\n");
      out.write("... trabalhadas, respeitado o valor da hora do salário mínimo, e dos valores referentes aos depósitos...1/3, 13º salário proporcional ...\n");
      out.write("</div>\n");
      out.write("</div></a>\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/jurisprudenciaDetalhe.jsp?parametro=2\" clazz=\"arredondado\" >\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"lista_esquerda\">\n");
      out.write(" 3 \n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("E-ED-RR - 28300-72.2006.5.15.0016\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Relator Ministro: Ives Gandra Martins Filho\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Publicação: DJ 13/06/2008\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Julgamento: 11/06/2008\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_base\">\n");
      out.write("RECURSO DE REVISTA DA RECLAMANTE - JORNADA EXTRAORDINÁRIA - INTERVALO INTRAJORNADA - CONCESSÃO ABAIXO DO MÍNIMO LEGAL - ORIENTAÇÃO JURISPRUDENCIAL 307 DA SBDI-1 DO TST.\n");
      out.write("<br>\n");
      out.write("... profissional e comprovar a percepção de salário inferior ao dobro do mínimo legal ou encontrar...máximo do salário de contribuição ...\n");
      out.write("</div>\n");
      out.write("</div></a>\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/jurisprudenciaDetalhe.jsp?parametro=3\" clazz=\"arredondado\" >\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"lista_esquerda\">\n");
      out.write(" 4 \n");
      out.write("</div>\n");
      out.write("<div>\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("RR - 679854-37.2000.5.02.5555\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Relator Ministro: Horácio Raymundo de Senna Pires \n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Publicação: DJ 13/06/2008\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_central\">\n");
      out.write("Data de Julgamento: 11/06/2008\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("<div class=\"lista_base\">\n");
      out.write("RECURSO DE REVISTA DOS RECLAMANTES. CONVERSÃO DOS SALÁRIOS EM URV. DIFERENÇAS. NECESSIDADE DE REVOLVIMENTO DE PROVAS. SÚMULA 126/TST.\n");
      out.write("<br>\n");
      out.write("... tabela 6, verifica-se que, efetivamente o salário pago para o mês de março/94 superou o de...Cruzeiros Reais, o salário de março/94 foi ...\n");
      out.write("</div>\n");
      out.write("</div></a>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/nvg_primeiro_inativo.png\" />\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/nvg_anterior_inativo.png\" />\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/jurisprudenciaPesquisa.jsp?m=paginar&parametro=10\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/nvg_proximo.png\" /></a>\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/jurisprudenciaPesquisa.jsp?m=paginar&parametro=20\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/nvg_ultimo.png\" /></a>\n");
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
