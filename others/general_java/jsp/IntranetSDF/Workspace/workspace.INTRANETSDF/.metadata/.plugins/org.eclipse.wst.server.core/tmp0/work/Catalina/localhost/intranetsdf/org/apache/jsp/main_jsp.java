package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.controle.ManterEquipamentoControl;
import br.com.consorciosdf.intranet.controle.ManterReportControl;
import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/includes/sessionVerify.jsp");
    _jspx_dependants.add("/includes/pageNavigatorHeader.jsp");
    _jspx_dependants.add("/includes/pageNavigatorFooter.jsp");
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("                                                                                                                                       \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      br.com.consorciosdf.intranet.modelo.Usuario usuarioAtual = null;
      synchronized (request) {
        usuarioAtual = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuarioAtual", PageContext.REQUEST_SCOPE);
        if (usuarioAtual == null){
          usuarioAtual = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuarioAtual", usuarioAtual, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");
      br.com.consorciosdf.intranet.modelo.ContadorMain contadorMain = null;
      synchronized (request) {
        contadorMain = (br.com.consorciosdf.intranet.modelo.ContadorMain) _jspx_page_context.getAttribute("contadorMain", PageContext.REQUEST_SCOPE);
        if (contadorMain == null){
          contadorMain = new br.com.consorciosdf.intranet.modelo.ContadorMain();
          _jspx_page_context.setAttribute("contadorMain", contadorMain, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");


            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");

            ManterOSControl manterOSControl = new ManterOSControl();
            ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
            ManterReportControl manterReportControl = new ManterReportControl();

            Calendar dataNow = Calendar.getInstance();
            int horaNow = dataNow.get(Calendar.HOUR_OF_DAY);
            int month = dataNow.get(Calendar.MONTH) + 1;
            int year = dataNow.get(Calendar.YEAR);

            int qtdRisAtivos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(true, "RIS");
            int qtdRisInativos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(false, "RIS");
            int qtdRedAtivos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(true, "RED");
            int qtdRedInativos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(false, "RED");

            List qtdOSAfericaoAbertas = manterOSControl.recuperarQuantidadeOS(0, "a", month, year);
            List qtdOSCorretivaAbertas = manterOSControl.recuperarQuantidadeOS(0, "c", month, year);
            List qtdOSPreventivaAbertas = manterOSControl.recuperarQuantidadeOS(0, "p", month, year);

            List qtdOSAfericaoAbertasAtraso = manterOSControl.recuperarQuantidadeOS(1, "a", month, year);
            List qtdOSCorretivaAbertasAtraso = manterOSControl.recuperarQuantidadeOS(1, "c", month, year);
            List qtdOSPreventivaAbertasAtraso = manterOSControl.recuperarQuantidadeOS(1, "p", month, year);

            List qtdOSAfericaoFechadas = manterOSControl.recuperarQuantidadeOS(2, "a", month, year);
            List qtdOSCorretivaFechadas = manterOSControl.recuperarQuantidadeOS(2, "c", month, year);
            List qtdOSPreventivaFechadas = manterOSControl.recuperarQuantidadeOS(2, "p", month, year);

            List qtdReportColetaAberta = manterReportControl.recuperarQuantidadeReport(1, 0, month, year);
            List qtdReportColetaFechada = manterReportControl.recuperarQuantidadeReport(1, 1, month, year);
            List qtdReportCorretivaAberta = manterReportControl.recuperarQuantidadeReport(2, 0, month, year);
            List qtdReportCorretivaFechada = manterReportControl.recuperarQuantidadeReport(2, 1, month, year);
            List qtdReportDTSAberta = manterReportControl.recuperarQuantidadeReport(3, 0, month, year);
            List qtdReportDTSFechada = manterReportControl.recuperarQuantidadeReport(3, 1, month, year);

            String saudacao = "";

            if (horaNow >= 0 && horaNow <= 11) {
                saudacao = "Bom dia, ";
            } else if (horaNow >= 12 && horaNow <= 17) {
                saudacao = "Boa tarde, ";
            } else if (horaNow >= 18 && horaNow <= 23) {
                saudacao = "Boa noite, ";
            }

            String nomeUsuario = "";
            nomeUsuario = session.getAttribute("userName") + " " + session.getAttribute("userLastname");

            saudacao += nomeUsuario;
            ManterUserControl userControl = new ManterUserControl();

            usuarioAtual.setUser((String) session.getAttribute("user"));
            usuarioAtual.setMatriculaUsuario((String) session.getAttribute("userMatricula"));

            usuarioAtual = userControl.viewUser(usuarioAtual);
            ManterOSControl osControl = new ManterOSControl();


      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- include de verificação do usuário -->\r\n");
 
    
    if (session.getAttribute("user") == null || session.getAttribute("userCod") == null ||
        session.getAttribute("userName") == null || session.getAttribute("userLastname") == null ||
        session.getAttribute("userEmail") == null || session.getAttribute("userPerfil") == null ||
        session.getAttribute("userModify") == null || session.getAttribute("userMatricula") == null) {  
   		
        //session.invalidate();
        //session.setAttribute("url", request.getRequestURI());
        response.sendRedirect(request.getContextPath() + "/index.jsp?erro=3"); //&urlDest=" + request.getRequestURL());
    } else {
        session.setAttribute("url", request.getRequestURL());
    }


      out.write("\r\n");
      out.write("<!-- fim do include verificação do usuário -->\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>INTRANET CONSORCIO SDF</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/default.css\" />\r\n");
      out.write("\r\n");
      out.write("        <script language=\"javascript\" type=\"text/javascript\" src=\"scripts/default.js\">\r\n");
      out.write("        </script>\r\n");
      out.write("\r\n");
      out.write("        <!-- include do menu -->\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/menu.jsp", out, false);
      out.write("\r\n");
      out.write("        <!-- fim do include do menu -->\r\n");
      out.write("\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("        <!-- include do cabeçalho -->\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/header.jsp", out, false);
      out.write("\r\n");
      out.write("        <!-- fim do include do cabeçalho -->\r\n");
      out.write("        ");
if (usuarioAtual.getPerfilUsuario() < 3 || usuarioAtual.getPerfilUsuario() > 5) {
      out.write("\r\n");
      out.write("        <table width=\"779\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"220\">&nbsp;</td>\r\n");
      out.write("                <td width=\"549\" colspan=\"2\" valign=\"top\">\r\n");
      out.write("                    <table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>\r\n");
      out.write("                                <table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td class=\"Title\" width=\"300\">\r\n");
      out.write("                                            ");
      out.print( saudacao);
      out.write("\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td class=\"Title\" align=\"right\">\r\n");
      out.write("                                            ");
      out.print( dateFormat.format(new Date()));
      out.write("\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </table>\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td height=\"1\" bgcolor=\"#000000\"></td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td height=\"10\"></td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>\r\n");
      out.write("                                <table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td width=\"264\" valign=\"top\">\r\n");
      out.write("                                            <table width=\"261\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>OS - Corretiva</td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                    <td width=\"15\"></td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleGreenBold\">ABERTA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleRedBold\">ABERTA(S)*:</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaAbertasAtraso.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaAbertasAtraso.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleBlueBold\">FECHADA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaFechadas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaFechadas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td width=\"21\"></td>\r\n");
      out.write("                                        <td width=\"264\" valign=\"top\">\r\n");
      out.write("                                            <table width=\"261\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>Balanço de Equipamentos</td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                    <td width=\"15\"></td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td width=\"50\" align=\"center\">&nbsp;</td>\r\n");
      out.write("                                                    <td width=\"50\" align=\"center\"><b>RIS</b></td>\r\n");
      out.write("                                                    <td width=\"50\" align=\"center\"><b>RED</b></td>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleGreenBold\">ATIVO(S):</td>\r\n");
      out.write("                                                    <td align=\"center\">&nbsp;</td>\r\n");
      out.write("                                                    <td align=\"center\">");
      out.print( qtdRisAtivos);
      out.write("</td>\r\n");
      out.write("                                                    <td align=\"center\">");
      out.print( qtdRedAtivos);
      out.write("</td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleRedBold\">INATIVO(S):</td>\r\n");
      out.write("                                                    <td align=\"center\">&nbsp;</td>\r\n");
      out.write("                                                    <td align=\"center\">");
      out.print( qtdRisInativos);
      out.write("</td>\r\n");
      out.write("                                                    <td align=\"center\">");
      out.print( qtdRedInativos);
      out.write("</td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleBlueBold\">TOTAL:</td>\r\n");
      out.write("                                                    <td align=\"center\">&nbsp;</td>\r\n");
      out.write("                                                    <td align=\"center\">");
      out.print( qtdRisAtivos + qtdRisInativos);
      out.write("</td>\r\n");
      out.write("                                                    <td align=\"center\">");
      out.print( qtdRedAtivos + qtdRedInativos);
      out.write("</td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </table>\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td height=\"20\"></td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>\r\n");
      out.write("                                <table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td width=\"264\" valign=\"top\">\r\n");
      out.write("                                            <table width=\"261\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>OS - Preventiva</td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                    <td width=\"15\"></td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleGreenBold\">ABERTA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleRedBold\">ABERTA(S)*:</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaAbertasAtraso.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaAbertasAtraso.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleBlueBold\">FECHADA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaFechadas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaFechadas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td width=\"21\"></td>\r\n");
      out.write("                                        <td width=\"264\" valign=\"top\">\r\n");
      out.write("                                            <table width=\"261\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>Manutenção - Coleta</td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                    <td width=\"15\"></td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdReportColetaAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportColetaAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleGreenBold\">ABERTA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdReportColetaAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportColetaAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleBlueBold\">FECHADA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdReportColetaFechada.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportColetaFechada.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </table>\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td height=\"20\"></td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>\r\n");
      out.write("                                <table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td width=\"264\" valign=\"top\">\r\n");
      out.write("                                            <table width=\"261\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>OS - Aferição</td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                    <td width=\"15\"></td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleGreenBold\">ABERTA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleRedBold\">ABERTA(S)*:</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoAbertasAtraso.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoAbertasAtraso.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleBlueBold\">FECHADA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoFechadas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoFechadas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                        <td width=\"21\"></td>\r\n");
      out.write("                                        <td width=\"264\" valign=\"top\">\r\n");
      out.write("                                            <table width=\"261\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>Manutenção - DTS</td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                    <td width=\"15\"></td>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdReportDTSAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportDTSAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td width=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleGreenBold\">ABERTA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdReportDTSAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportDTSAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                    <td><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                                    <td class=\"StyleBlueBold\">FECHADA(S):</td>\r\n");
      out.write("                                                    ");

//exibição dos 3 ultimos meses
    for (int i = qtdReportDTSFechada.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportDTSFechada.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    
      out.write("\r\n");
      out.write("                                                    <td></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                                <tr>\r\n");
      out.write("                                                    <td colspan=\"7\" height=\"5\"></td>\r\n");
      out.write("                                                </tr>\r\n");
      out.write("                                            </table>\r\n");
      out.write("                                        </td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </table>\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td height=\"20\"></td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                        <tr>\r\n");
      out.write("                            <td>\r\n");
      out.write("                                <!--table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#FFFFFF\" style='border: 1px solid #000000;'>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td colspan=\"4\" height=\"18\" align=\"center\" class=\"TableResultTitle\" style='border-bottom: 1px solid #000000;'>Quadro de Avisos</td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td colspan=\"4\" height=\"5\"></td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td width=\"5\"></td>\r\n");
      out.write("                                        <td width=\"15\"><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                        <td width=\"524\">Protótipo de página inicial do sistema, com informações importantes para o gerenciamento.</td>\r\n");
      out.write("                                        <td width=\"5\"></td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td width=\"5\"></td>\r\n");
      out.write("                                        <td width=\"15\"><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                        <td width=\"524\">Teste...</td>\r\n");
      out.write("                                        <td width=\"5\"></td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td width=\"5\"></td>\r\n");
      out.write("                                        <td width=\"15\"><img src=\"imagens/topico.gif\" width=\"15\" height=\"15\"></td>\r\n");
      out.write("                                        <td width=\"524\">Teste...</td>\r\n");
      out.write("                                        <td width=\"5\"></td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                    <tr>\r\n");
      out.write("                                        <td colspan=\"4\" height=\"5\"></td>\r\n");
      out.write("                                    </tr>\r\n");
      out.write("                                </table-->\r\n");
      out.write("                            </td>\r\n");
      out.write("                        </tr>\r\n");
      out.write("                    </table>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td width=\"10\"></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td colspan=\"3\" height=\"20\">&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("        ");


            if (usuarioAtual.getPerfilUsuario() >= 3 && usuarioAtual.getPerfilUsuario() <= 5) {
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.OS os = null;
      synchronized (request) {
        os = (br.com.consorciosdf.intranet.modelo.OS) _jspx_page_context.getAttribute("os", PageContext.REQUEST_SCOPE);
        if (os == null){
          os = new br.com.consorciosdf.intranet.modelo.OS();
          _jspx_page_context.setAttribute("os", os, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.OS osVerify = null;
      synchronized (request) {
        osVerify = (br.com.consorciosdf.intranet.modelo.OS) _jspx_page_context.getAttribute("osVerify", PageContext.REQUEST_SCOPE);
        if (osVerify == null){
          osVerify = new br.com.consorciosdf.intranet.modelo.OS();
          _jspx_page_context.setAttribute("osVerify", osVerify, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.CheckEquipamento checkEquipamento = null;
      synchronized (request) {
        checkEquipamento = (br.com.consorciosdf.intranet.modelo.CheckEquipamento) _jspx_page_context.getAttribute("checkEquipamento", PageContext.REQUEST_SCOPE);
        if (checkEquipamento == null){
          checkEquipamento = new br.com.consorciosdf.intranet.modelo.CheckEquipamento();
          _jspx_page_context.setAttribute("checkEquipamento", checkEquipamento, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.Equipamento equipamento = null;
      synchronized (request) {
        equipamento = (br.com.consorciosdf.intranet.modelo.Equipamento) _jspx_page_context.getAttribute("equipamento", PageContext.REQUEST_SCOPE);
        if (equipamento == null){
          equipamento = new br.com.consorciosdf.intranet.modelo.Equipamento();
          _jspx_page_context.setAttribute("equipamento", equipamento, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.Usuario usuario = null;
      synchronized (request) {
        usuario = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuario", PageContext.REQUEST_SCOPE);
        if (usuario == null){
          usuario = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuario", usuario, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.Usuario usuarioAutor = null;
      synchronized (request) {
        usuarioAutor = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuarioAutor", PageContext.REQUEST_SCOPE);
        if (usuarioAutor == null){
          usuarioAutor = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuarioAutor", usuarioAutor, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.Usuario usuarioDest = null;
      synchronized (request) {
        usuarioDest = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuarioDest", PageContext.REQUEST_SCOPE);
        if (usuarioDest == null){
          usuarioDest = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuarioDest", usuarioDest, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("        <!-- include de paginação de resultados - cabeçalho -->\r\n");
      out.write("        ");
      br.com.consorciosdf.intranet.modelo.Paginacao paginacao = null;
      synchronized (request) {
        paginacao = (br.com.consorciosdf.intranet.modelo.Paginacao) _jspx_page_context.getAttribute("paginacao", PageContext.REQUEST_SCOPE);
        if (paginacao == null){
          paginacao = new br.com.consorciosdf.intranet.modelo.Paginacao();
          _jspx_page_context.setAttribute("paginacao", paginacao, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\n');
      out.write('\n');

    
    String orderBy = ""; //ordenar por (data, nº de os, equipamento)
    String order = "ASC";
    
    int paginaInicial = 0; //página atual da navegação
    int qtdRegistros = 30; //quantidade de registros por página;
    
    //paginacao por order by
    if ((request.getParameter("orderBy")) != null) {
        orderBy = request.getParameter("orderBy");
        
        if (session.getAttribute("orderBy") == null) {
            session.setAttribute("orderBy", orderBy);
            session.setAttribute("order", "ASC");
        } else {
            String orderBySession = (String) session.getAttribute("orderBy");
            String orderSession = (String) session.getAttribute("order");
            
            if (orderBySession.equals(orderBy)) {
                if (orderSession.equals("ASC")) {
                    session.setAttribute("order", "DESC");
                } else {
                    session.setAttribute("order", "ASC");
                }
            } else {
                session.setAttribute("orderBy", orderBy);
                session.setAttribute("order", "ASC");
            }
        }
    }
    
    //paginacao
    if ((request.getParameter("page")) != null) {
        paginaInicial = Integer.parseInt(request.getParameter("page"));
        paginaInicial--;
    } else {
        if ((request.getParameter("orderBy")) != null) {
            session.removeAttribute("orderBy");
            session.removeAttribute("order");
        }
    }

    if (session != null) {
        if (session.getAttribute("orderBy") != null) {
            orderBy = (String) session.getAttribute("orderBy");
            order = (String) session.getAttribute("order");
        }
    }
    

      out.write('\n');
      out.write("\r\n");
      out.write("        <!-- fim do include de paginação de resultados - cabeçalho -->\r\n");
      out.write("        ");

                Date dataNow1 = new Date();
                long dataTimeNow = dataNow1.getTime();
                long dataVerificador = 1 * 24 * 60 * 60 * 1000; //1 dias * 24 horas * 60 minutos * 60 segundos * 1000 milisegundos
                long dataVerifyStatus = dataTimeNow - dataVerificador;

                int zero = 0;


                String nula = null;

                String matriculaTecnico = usuarioAtual.getMatriculaUsuario();

                List<OS> list = manterOSControl.recuperarOSsPagFiltro(zero, nula, nula, nula, zero, matriculaTecnico, nula,nula,nula, nula, paginaInicial, qtdRegistros, orderBy, order);

                if (list != null) {
                    if (list.size() > 0) {
                        osVerify = (OS) list.get(0);
                        paginacao = osVerify.getPaginacao();
                    }
                }

        
      out.write("\r\n");
      out.write("        <!-- include de paginação de resultados - rodapé -->\r\n");
      out.write("        ");


            String pageViewNavigator = "";

            int pageFirst = 0;
            int pagePrevious = 0;
            int pageNext = 0;//
            int pageLast = 0;//total de paginas
            int pageNumber = 0;//pagina atual
            int pageShowAfter = 5;//Páginas a se mostrar após a atual
            int pageShowBefore = 5;//Páginas a se mostrar antes da atual
            int posAddition = 0;
            int preAddition = 0;
            int pagesBefore = 0;
            int pagesAfter = 0;
            



            if (paginacao != null) {
                
                String parametrosPesquisa = "";
                /*
                 * Carregando os parâmentros do filtro para preencher a paginação
                 *
                if (request.getParameter("numOS") != null) {

                    parametrosPesquisa += "&numOS=" + request.getParameter("numOS");
                }
                if (request.getParameter("statusOS") != null) {
                    parametrosPesquisa += "&statusOS=" + request.getParameter("statusOS");

                }
                if (request.getParameter("tipoOS") != null) {
                    parametrosPesquisa += "&tipoOS=" + request.getParameter("tipoOS");

                }
                if (request.getParameter("equipamento") != null) {
                    parametrosPesquisa += "&equipamento=" + request.getParameter("equipamento");

                }
                if (request.getParameter("tecnico") != null) {
                    parametrosPesquisa += "&tecnico=" + request.getParameter("tecnico");

                }
                if (request.getParameter("dt_ini_dia") != null &&
                        request.getParameter("dt_ini_mes") != null &&
                        request.getParameter("dt_ini_ano") != null) {



                    parametrosPesquisa += "&dt_ini_dia=" + request.getParameter("dt_ini_ano");
                    parametrosPesquisa += "&dt_ini_mes=" + request.getParameter("dt_ini_mes");
                    parametrosPesquisa += "&dt_ini_ano=" + request.getParameter("dt_ini_dia");


                }
                if (request.getParameter("dt_fim_dia") != null &&
                        request.getParameter("dt_fim_mes") != null &&
                        request.getParameter("dt_fim_ano") != null) {

                    parametrosPesquisa += "&dt_fim_ano=" + request.getParameter("dt_fim_ano");
                    parametrosPesquisa += "&dt_fim_mes=" + request.getParameter("dt_fim_mes");
                    parametrosPesquisa += "&dt_fim_dia=" + request.getParameter("dt_fim_dia");

                }*/
                /*
                 * Fim do carregamento de parâmentros.
                 **/
                pageNumber = paginacao.getPageNumber();
                pageNumber++;

                pageFirst = 1;
                pagePrevious = 1;
                pageNext = pageNumber + 1;
                pageLast = paginacao.getPageNavigator();
                pagesBefore = pageNumber - 1;
                pagesAfter = pageLast - pageNumber;
                /*
                 * Se o total de páginas anteriores for menor que 6
                 * o total de páginas a serem mostradas após a página atual
                 * vai ser = ao total de páginas restantes.
                 **/
                if (pageNumber < pageShowBefore + 1) {

                    pageShowBefore = pageNumber;
                    if (pageLast >= 10) {
                        posAddition = 4 - pageShowBefore;
                    }

                }
                /*
                 * Se o total de páginas posteriores for menor que 5
                 * o total de páginas a serem mostradas após a página atual 
                 * vai ser = ao total de páginas restantes.
                 **/
                if (pagesAfter < pageShowAfter) {
                    pageShowAfter = pagesAfter;
                    /*
                     * Se o total de páginas for maior que 10
                     * serão acrescentadas ao inicio da sequencia de páginas mostradas um total de (5 páginas - o total de páginas mostradas após a atual);
                     */
                    if (pageLast >= 10) {
                        preAddition = 5 - pageShowAfter;
                    }

                }
                if (pagesAfter < pageShowAfter && pageLast >= 10) {
                    preAddition = 5 - pageShowAfter;

                }
                if (pageNumber < (pageShowBefore + 1) && pageLast >= 10) {
                    posAddition = 4 - pageShowBefore;

                }
                if (pageNumber > 1) {
                    pagePrevious = pageNumber - 1;
                    pageViewNavigator += "<a href='?page=" + pageFirst + parametrosPesquisa + "'>&lt;&lt; Primeira</a> | ";
                }
                if (pageNumber > 10) {
                    int link = pageNumber - 10;
                    pageViewNavigator += "<a href='?page=" + link + parametrosPesquisa + "'>&lt; 10 Anteriores</a> |";
                }
                if (pageNumber > 1) {
                    pagePrevious = pageNumber - 1;
                    pageViewNavigator += " <a href='?page=" + pagePrevious + parametrosPesquisa + "'>&lt; Anterior</a> |";
                }
                if (pageLast >= 10) {

                    //Páginas a se mostrar anteriores a atual

                    for (int i = (pageNumber - pageShowBefore) - preAddition; i < pageNumber - 1; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }


                    //Páginas a se mostrar após a atual
                    for (int i = pageNumber - 1; i < pageNumber + pageShowAfter + posAddition; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }
                } else {
                    
                    for (int i = 0; i < pageLast; i++) {
                        int pagina = i + 1;
                        if (i == paginacao.getPageNumber()) {
                            pageViewNavigator += " <font color='#FF0000'><b>" + pagina + "</b></font>";
                        } else {
                            pageViewNavigator += " <a href='?page=" + pagina + parametrosPesquisa + "'>" + pagina + "</a>";
                        }
                    }

                }




                if (pageNumber < paginacao.getPageNavigator()) {
                    pageViewNavigator += " | <a href='?page=" + pageNext + parametrosPesquisa + "'>Próxima &gt;</a> | ";
                }
                if (pageNumber + 10 <= paginacao.getPageNavigator()) {
                    int link = pageNumber + 10;
                    pageViewNavigator += " <a href='?page=" + link + parametrosPesquisa + "'>Próximas 10 &gt;</a> | ";
                }
                if (pageNumber < paginacao.getPageNavigator()) {
                    pageViewNavigator += "<a href='?page=" + pageLast + parametrosPesquisa + "'>Última &gt;&gt;</a>";
                }
            }


      out.write("\r\n");
      out.write("        <!-- fim do include de paginação de resultados - rodapé -->\r\n");
      out.write("        <table width=\"\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"220\">&nbsp;</td>\r\n");
      out.write("                <td width=\"\" height=\"180\" colspan=\"2\" valign=\"top\">\r\n");
      out.write("                    <form method=\"get\" action=\"\">\r\n");
      out.write("                        <table width=\"\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td class=\"Title\">\r\n");
      out.write("                                    <table width='100%' cellspacing='0' cellpadding='0' border='0'>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\" width=\"\">");
      out.print( saudacao);
      out.write(' ');
      out.print( dateFormat.format(new Date()));
      out.write(" - Minhas Ordens de Serviço - Listagem (");
      out.print( paginacao.getResultSize());
      out.write(" Registros - Página ");
      out.print( pageNumber);
      out.write(" de ");
      out.print( paginacao.getPageNavigator());
      out.write(")</td>\r\n");
      out.write("                                            <td width=\"\" align=\"right\">\r\n");
      out.write("                                                <a href=\"javascript:history.back()\"><img src=\"imagens/stock_navigator-back-16.gif\" border=\"0\" alt=\"Voltar\"></a>\r\n");
      out.write("                                                <a href=\"osList.jsp\"><img src=\"imagens/stock_search-16.gif\" border=\"0\" alt=\"Procurar\"></a>\r\n");
      out.write("                                                    ");

                for (int j = 0; j < usuarioAtual.getUsuarioRules().size(); j++) {
                    if (usuarioAtual.getUsuarioRules().get(j).getNome().equals("os_incluir")) {
      out.write("\r\n");
      out.write("                                                <a href=\"osAdd.jsp\"><img src=\"imagens/stock_new-16.gif\" border=\"0\" alt=\"Novo\"></a>\r\n");
      out.write("                                                    ");
                    }
                }
      out.write("\r\n");
      out.write("                                                <a href=\"javascript:window.location.reload()\"><img src=\"imagens/stock_refresh-16.gif\" border=\"0\" alt=\"Atualizar\"></a>\r\n");
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                    </table>\r\n");
      out.write("                                </td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td height=\"1\" bgcolor=\"#000000\"></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td height=\"20\"></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td>\r\n");
      out.write("                                    <table width='' cellspacing='0' cellpadding='0' border='0' style='border: 1px solid #000000;'>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>\r\n");
      out.write("                                            <td width='80' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=id&page=1\" class=\"LinkTitle\">N&deg;.</a></td>\r\n");
      out.write("                                            <td width='80' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=equipamento&page=1\" class=\"LinkTitle\">Equip.</a></td>\r\n");
      out.write("                                            <td width='159' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=autor&page=1\" class=\"LinkTitle\">Solicitante:</a></td>\r\n");
      out.write("                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=tecnico&page=1\" class=\"LinkTitle\">Técnico:</a></td>\r\n");
      out.write("                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=data_abertura&page=1\" class=\"LinkTitle\">Data Abertura</a></td>\r\n");
      out.write("                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=data_servico&page=1\" class=\"LinkTitle\">Data Servico</a></td>\r\n");
      out.write("                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=data_fechamento&page=1\" class=\"LinkTitle\">Data Fechamento</a></td>\r\n");
      out.write("                                            <td width='90' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=status&page=1\" class=\"LinkTitle\">Status</a></td>\r\n");
      out.write("                                            <td width='90' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=prioridade&page=1\" class=\"LinkTitle\">Prioridade</a></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        ");


                if (list != null) {
                    String tableStyle = "TableResultBody1";
                    String status = "";
                    int countResult = 0;

                    for (int i = 0; i < list.size(); i++) {
                        os = (OS) list.get(i);
                        checkEquipamento = os.getCheckEquipamento();
                        equipamento = os.getEquipamento();
                        usuarioAutor = os.getUsuarioAutor();
                        usuarioDest = os.getUsuarioDest();
                        status = os.getStatus();

                        long dataVerifyAbertura = os.getDataAbertura().getTime();

                        String statusFechamento = "";
                        String statusStyle = "";
                        String prioridadeOSStyle = "";
                        String prioridadeOS = "";

                        countResult++;

                        if (countResult % 2 == 0) {
                            tableStyle = "TableResultBody2";
                        } else if (countResult % 2 == 1) {
                            tableStyle = "TableResultBody1";
                        }

                        if (status.equals("ab") && dataVerifyAbertura < dataVerifyStatus) {
                            statusFechamento = "ABERTO";
                            statusStyle = "OSWarning";
                        } else if (status.equals("ab")) {
                            statusFechamento = "ABERTO";
                            statusStyle = "OSOpen";
                        } else if (status.equals("an")) {
                            statusFechamento = "ANÁLISE";
                            statusStyle = "OSWorking";
                        } else if (status.equals("fc")) {
                            statusFechamento = "FECHADO";
                            statusStyle = "OSClose";
                        } else if (status.equals("ag")) {
                            statusFechamento = "AGUARDANDO";
                            statusStyle = "OSStandby";
                        }

                        /*if (os.getDataFechamento() == null && dataVerifyAbertura < dataVerifyStatus) {
                        statusFechamento = "ABERTO";
                        statusStyle = "OSWarning";
                        } else if (os.getDataFechamento() == null) {
                        statusFechamento = "ABERTO";
                        statusStyle = "OSOpen";
                        } else {
                        statusFechamento = "FECHADO";
                        statusStyle = "OSClose";
                        }*/

                                        
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td height='16' class='");
      out.print( tableStyle);
      out.write("' align=\"right\"><img src=\"imagens/topico.gif\" height=\"15\" width=\"15\" alt=\"\"></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write('"');
      out.write('>');
      out.print( os.getId() + os.getTipoOS().toUpperCase());
      out.write("</a></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write('"');
      out.write('>');
      out.print( equipamento.getNomeEquipamento());
      out.write("</a></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write('"');
      out.write('>');
      out.print( usuarioAutor.getNomeUsuario() + " " + usuarioAutor.getSobrenomeUsuario());
      out.write("</a></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write('"');
      out.write('>');
      out.print( usuarioDest.getNomeUsuario() + " " + usuarioDest.getSobrenomeUsuario());
      out.write("</a></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write('"');
      out.write('>');
      out.print( dateFormat.format(os.getDataAbertura()));
      out.write("</a></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write("\">\r\n");
      out.write("                                                    ");

                        if (os.getDataServico() == null) {
                            out.println("00:00:00 00/00/0000");
                        } else {
                            out.println(dateFormat.format(os.getDataServico()));
                        }
                                                    
      out.write("\r\n");
      out.write("                                                </a>\r\n");
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write("\">\r\n");
      out.write("                                                    ");

                        if (os.getDataFechamento() == null) {
                            out.println("00:00:00 00/00/0000");
                        } else {
                            out.println(dateFormat.format(os.getDataFechamento()));
                        }
                                                    
      out.write("\r\n");
      out.write("                                                </a></td>\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write("\"><span class=\"");
      out.print( statusStyle);
      out.write('"');
      out.write('>');
      out.print( statusFechamento);
      out.write("</span></a></td>\r\n");
      out.write("                                            ");


                        if (os.getPrioridade() == 0) {
                            prioridadeOS = "Alta";
                            prioridadeOSStyle = "OSPrioridadeAlta";
                        } else if (os.getPrioridade() == 1) {
                            prioridadeOS = "Média";
                            prioridadeOSStyle = "OSPrioridadeMedia";
                        } else if (os.getPrioridade() == 2) {
                            prioridadeOS = "Baixa";
                            prioridadeOSStyle = "OSPrioridadeBaixa";
                        }
                                            
      out.write("\r\n");
      out.write("                                            <td class='");
      out.print( tableStyle);
      out.write("'><a href=\"osView.jsp?id=");
      out.print( os.getId());
      out.write("\"><span class=\"");
      out.print( prioridadeOSStyle);
      out.write('"');
      out.write('>');
      out.print( prioridadeOS);
      out.write("</span></a></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("\r\n");
      out.write("                                        ");

                    }
                }

                                        
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td colspan=\"8\" height=\"5\"></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td colspan=\"8\" align=\"center\">");
      out.print( pageViewNavigator);
      out.write("</td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td colspan=\"8\" height=\"5\"></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                    </table>\r\n");
      out.write("                                </td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                        </table>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </td>\r\n");
      out.write("                <td width=\"10\"></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("        ");

            }
        
      out.write("\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr><td width=\"220\">&nbsp;</td>\r\n");
      out.write("                <td colspan=\"2\" align=\"center\" class=\"Footer\">\r\n");
      out.write("                    <!-- include do rodapé -->\r\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/footer.jsp", out, false);
      out.write("\r\n");
      out.write("                    <!-- fim do include do rodapé -->\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td colspan=\"2\">\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("        \tcontaMain();\r\n");
      out.write("        </script>\r\n");
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
