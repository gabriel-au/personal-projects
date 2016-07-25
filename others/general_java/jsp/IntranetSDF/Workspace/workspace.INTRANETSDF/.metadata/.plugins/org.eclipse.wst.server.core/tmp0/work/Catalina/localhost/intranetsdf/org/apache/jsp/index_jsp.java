package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');


String erroLogin = "";
String erro = "";
erro = request.getParameter("erro");
if (erro != null) {
    if (erro.equals("1")) {
        erroLogin = "Favor digitar os campos corretamente.";
    } else if (erro.equals("2")) {
        erroLogin = "Usuário e/ou senha incorretos.";
    } else if (erro.equals("3")) {
        erroLogin = "Área restrita, favor efetuar o Login.";
    } else if (erro.equals("4")) {
        erroLogin = "Usuário bloqueado, favor entrar em contato com o administrador do sistema.";
    } else if (erro.equals("5")) {
        erroLogin = "Página não autorizada.";
    }else if (erro.equals("6")){
    	erroLogin = "Feche a janela e faça o login novamente.";
    } 
}else{
	erro = "";
}

String urlDest = "";
if ((request.getParameter("urlDest")) != null) {
    if (!request.getParameter("urlDest").equals("")) {
        urlDest = request.getParameter("urlDest");
    }
}

//identificar o user-agent
	String agent = request.getHeader("user-agent");

//passar para minusculo
	agent = agent.toLowerCase();
	
	if(agent.indexOf("iphone")>=0||
			agent.indexOf("jme")>=0 || 
			agent.indexOf("jme")>=0 || 
			agent.indexOf("j2me")>=0 || 
			agent.indexOf("symbian")>=0 ||
			agent.indexOf("android")>=0 ||
			agent.indexOf("blackberry")>=0 ){
		response.sendRedirect(request.getContextPath()+"/mobile/index.jsp");
		}
	
	
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>INTRANET CONSORCIO SDF</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/default.css\">\r\n");
      out.write("        \r\n");
      out.write("        <script language=\"javascript\" type=\"text/javascript\" src=\"scripts/default.js\">\r\n");
      out.write("        </script>\r\n");
      out.write("        \r\n");
      out.write("    </head>\r\n");
      out.write("    \r\n");
      out.write("    <body onLoad=\"document.login.user.focus()\">\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td align=\"center\" valign=\"middle\"><table width=\"500\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td width=\"10\">&nbsp;</td>\r\n");
      out.write("                            <td align=\"center\"><img src=\"imagens/logo_intranetsdf.gif\" width=\"361\" height=\"120\" alt=\"\"></td>\r\n");
      out.write("                            <td width=\"10\">&nbsp;</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                            <td align=\"center\">\r\n");
      out.write("                                <form name=\"login\" method=\"post\" action=\"userVerify.jsp\">\r\n");
      out.write("                                    <table width=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                                    ");
if(!erro.equals("6")){ 
      out.write("\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td width=\"10\">&nbsp;</td>\r\n");
      out.write("                                            <td width=\"100\" align=\"right\" height=\"18\" class=\"Title\">Usu&aacute;rio:</td>\r\n");
      out.write("                                            <td width=\"30\">&nbsp;</td>\r\n");
      out.write("                                            <td width=\"155\"><input name=\"user\" type=\"text\" size=\"25\" class=\"Input\"></td>\r\n");
      out.write("                                            <td width=\"10\">&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td colspan=\"5\" height=\"2\"></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td align=\"right\" height=\"18\" class=\"Title\">Senha:</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td><input name=\"pass\" type=\"password\" size=\"25\" class=\"Input\"></td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td colspan=\"3\" align=\"center\" class=\"Error\">");
      out.print( erroLogin );
      out.write("</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td><input type=\"hidden\" name=\"urlDest\" value=\"");
      out.print( urlDest );
      out.write("\"></td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td colspan=\"3\" align=\"right\"><input type=\"submit\" value=\"Entrar\" id=\"Entrar\" class=\"Button\" onMouseOut=\"buttonOn(this)\" onMouseOver=\"buttonOver(this)\"></td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td colspan=\"3\">&nbsp;</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        ");
} else{
      out.write("\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td colspan=\"3\" align=\"center\" class=\"Error\">");
      out.print( erroLogin );
      out.write("</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        ");
} 
      out.write("\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td colspan=\"3\" height=\"40\"></td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                            <td colspan=\"3\" align=\"right\">INTRANET CONSÓRCIO SDF&reg; 1.0.0<br>\r\n");
      out.write("                                            &copy;2007/2012 - CONSÓRCIO SDF</td>\r\n");
      out.write("                                            <td>&nbsp;</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                    </table>\r\n");
      out.write("                            </form></td>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                            <td>&nbsp;</td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                </table></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </body>\r\n");
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
