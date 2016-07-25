package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class transactions_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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
      out.write("\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("<title>Fatura On-Line</title>\n");
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
      out.write("/imagens/credicard.png\" />\n");
      out.write("</div>");
      out.write("\n");
      out.write("\n");
      out.write("<!-- Início da Barra de Título -->\n");
      out.write("<!-- div class=\"titulo\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/\" >\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/botao-inicio-azul.png\" />\n");
      out.write("</a>\n");
      out.write("<div class=\"titulo_texto\">Noticias</div>\n");
      out.write("</div-->\n");
      out.write("<!-- Fim da Barra de Título -->\n");
      out.write("\n");
      out.write("<!-- Início do Conteúdo -->\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("\t<div class=\"arredondado_titulo_detalhe_espaco\">\n");
      out.write("\t\tDEMONSTRATIVO\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_simples_dupla_borda\">\n");
      out.write("\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td width=\"17%\">DATA</td>\n");
      out.write("\t\t\t\t<td width=\"50%\">DESCRIÇÃO</td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\">CRED/DEB R$</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t<tr style=\"font-size: 12px; font-weight: normal;\">\n");
      out.write("\t\t\t\t<td><strong>28/06</strong></td>\n");
      out.write("\t\t\t\t<td><strong>Saldo anterior</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>- R$ 647,34</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t<tr style=\"font-size: 12px; font-weight: normal;\">\n");
      out.write("\t\t\t\t<td><strong>28/06</strong></td>\n");
      out.write("\t\t\t\t<td><strong>Pagamento efetuado</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>+ R$ 647,34</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_nome\">\n");
      out.write("\t\t<strong>ADRIANA</strong>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_simples_dupla_borda\">\n");
      out.write("\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td width=\"40\">\n");
      out.write("\t\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo_cc_mastercard.jpg\" style=\"vertical-align: middle;\"/>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t\t<td>XXXX-XXXX-XXXX-3013</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_nome_right\">\n");
      out.write("\t\tMOVIMENTAÇÕES NACIONAIS\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_simples_dupla_borda\">\n");
      out.write("\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t\t\t<tr style=\"font-size: 12px; font-weight: normal; vertical-align: middle;\">\n");
      out.write("\t\t\t\t<td width=\"17%\"><strong>03/04</strong></td>\n");
      out.write("\t\t\t\t<td width=\"50%\"><strong>DELL COMPUTADORES</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>- R$ 2.542,34</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t<tr style=\"font-size: 12px; font-weight: normal;\">\n");
      out.write("\t\t\t\t<td><strong>10/04</strong></td>\n");
      out.write("\t\t\t\t<td><strong>CASAS BAHIA 304</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>- R$ 312,50</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_nome\">\n");
      out.write("\t\t<strong>ROBERTA</strong>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_simples_dupla_borda\">\n");
      out.write("\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td width=\"40\">\n");
      out.write("\t\t\t\t\t<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/logo_cc_mastercard.jpg\" style=\"vertical-align: middle;\"/>\n");
      out.write("\t\t\t\t</td>\n");
      out.write("\t\t\t\t<td>XXXX-XXXX-XXXX-1702</td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_nome_right\">\n");
      out.write("\t\tMOVIMENTAÇÕES NACIONAIS\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_simples_dupla_borda\">\n");
      out.write("\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t\t\t<tr style=\"font-size: 12px; font-weight: normal; vertical-align: middle;\">\n");
      out.write("\t\t\t\t<td width=\"18%\"><strong>03/04</strong></td>\n");
      out.write("\t\t\t\t<td width=\"50%\"><strong>TOP MALHAS 03/03</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>- R$ 29,83</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t\t<tr style=\"font-size: 12px; font-weight: normal;\">\n");
      out.write("\t\t\t\t<td><strong>10/04</strong></td>\n");
      out.write("\t\t\t\t<td><strong>BOM B B M INFANTIL</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>- R$ 49,90</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"arredondado_chamada_simples\">\n");
      out.write("\t\t<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<td width=\"20%\"><strong>TOTAL</strong></td>\n");
      out.write("\t\t\t\t<td style=\"text-align: right;\"><strong>R$ 2.934,57</strong></td>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</table>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"arredondado\">\n");
      out.write("<div class=\"arredondado_link\">\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/invoices.jsp\" >\n");
      out.write("FATURAS ANTERIORES</a>\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<!-- Fim do Conteúdo -->\n");
      out.write("\n");
      out.write("<!-- div style=\"margin-top:0px; \">\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/imagens/tabbar.png\" />\n");
      out.write("</div-->\n");
      out.write("\n");
      out.write("<!-- Início do Espaçamento -->\n");
      out.write("<p>&nbsp;</p>\n");
      out.write("<!-- Fim do Espaçamento -->\n");
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
