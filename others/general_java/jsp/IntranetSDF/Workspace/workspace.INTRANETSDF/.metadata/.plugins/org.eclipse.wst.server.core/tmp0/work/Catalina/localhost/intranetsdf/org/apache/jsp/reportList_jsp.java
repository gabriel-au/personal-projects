package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.consorciosdf.intranet.controle.ManterReportControl;
import br.com.consorciosdf.intranet.modelo.*;
import java.util.List;
import java.text.SimpleDateFormat;

public final class reportList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(3);
    _jspx_dependants.add("/includes/pageNavigatorHeader.jsp");
    _jspx_dependants.add("/includes/pageNavigatorFooter.jsp");
    _jspx_dependants.add("/includes/sessionVerify.jsp");
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      br.com.consorciosdf.intranet.modelo.Relatorio relatorio = null;
      synchronized (request) {
        relatorio = (br.com.consorciosdf.intranet.modelo.Relatorio) _jspx_page_context.getAttribute("relatorio", PageContext.REQUEST_SCOPE);
        if (relatorio == null){
          relatorio = new br.com.consorciosdf.intranet.modelo.Relatorio();
          _jspx_page_context.setAttribute("relatorio", relatorio, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Relatorio relatorioVerify = null;
      synchronized (request) {
        relatorioVerify = (br.com.consorciosdf.intranet.modelo.Relatorio) _jspx_page_context.getAttribute("relatorioVerify", PageContext.REQUEST_SCOPE);
        if (relatorioVerify == null){
          relatorioVerify = new br.com.consorciosdf.intranet.modelo.Relatorio();
          _jspx_page_context.setAttribute("relatorioVerify", relatorioVerify, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Equipamento equipamento = null;
      synchronized (request) {
        equipamento = (br.com.consorciosdf.intranet.modelo.Equipamento) _jspx_page_context.getAttribute("equipamento", PageContext.REQUEST_SCOPE);
        if (equipamento == null){
          equipamento = new br.com.consorciosdf.intranet.modelo.Equipamento();
          _jspx_page_context.setAttribute("equipamento", equipamento, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Usuario tecnico = null;
      synchronized (request) {
        tecnico = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("tecnico", PageContext.REQUEST_SCOPE);
        if (tecnico == null){
          tecnico = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("tecnico", tecnico, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\n");
      out.write("\n");
      out.write("<!-- include de paginação de resultados - cabeçalho -->\n");
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
      out.write("\n");
      out.write("<!-- fim do include de paginação de resultados - cabeçalho -->\n");
      out.write("\n");

    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    String relatorioTipoId = "";
    String relatorioTipoNome = "";
    
    List listEquipamentos = null;
    
    if ((session.getAttribute("relatorioTipoId")) != null &&
        (session.getAttribute("relatorioTipoNome")) != null) {
        
        relatorioTipoId = (String) session.getAttribute("relatorioTipoId");
        relatorioTipoNome = (String) session.getAttribute("relatorioTipoNome");
        
        if ((request.getParameter("relatorioTipoId")) != null &&
                (request.getParameter("relatorioTipoNome")) != null) {
            
            String relatorioTipoIdReq = request.getParameter("relatorioTipoId");
            String relatorioTipoNomeReq = request.getParameter("relatorioTipoNome");
                
            if (!relatorioTipoId.equals(relatorioTipoIdReq) &&
                    !relatorioTipoNome.equals(relatorioTipoNomeReq)) {
                
                relatorioTipoId = request.getParameter("relatorioTipoId");
                relatorioTipoNome = request.getParameter("relatorioTipoNome");
                
                session.setAttribute("relatorioTipoId", request.getParameter("relatorioTipoId"));
                session.setAttribute("relatorioTipoNome", request.getParameter("relatorioTipoNome"));
            }
        }
        
    } else {
        if ((request.getParameter("relatorioTipoId")) != null &&
                (request.getParameter("relatorioTipoNome")) != null) {
                
            relatorioTipoId = request.getParameter("relatorioTipoId");
            relatorioTipoNome = request.getParameter("relatorioTipoNome");
            
            session.setAttribute("relatorioTipoId", request.getParameter("relatorioTipoId"));
            session.setAttribute("relatorioTipoNome", request.getParameter("relatorioTipoNome"));
        }
    }
    
    List list = null;
    ManterReportControl manterReportControl = new ManterReportControl();
    list = manterReportControl.recuperarReportsPag(Integer.parseInt(relatorioTipoId), paginaInicial, qtdRegistros, orderBy, order);
    
    listEquipamentos = manterReportControl.recuperarEquipamentos();
    
    if (list != null) {
        if (list.size() > 0) {
            relatorioVerify = (Relatorio) list.get(0);
            paginacao = relatorioVerify.getPaginacao();
        }
    }
    

      out.write("\n");
      out.write("\n");
      out.write("<!-- include de paginação de resultados - rodapé -->\n");


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


      out.write("\n");
      out.write("<!-- fim do include de paginação de resultados - rodapé -->\n");
      out.write("\n");
      out.write("<!-- include de verificação do usuário -->\n");
 
    
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


      out.write("\n");
      out.write("<!-- fim do include verificação do usuário -->\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>INTRANET CONSORCIO SDF</title>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos/default.css\" />\n");
      out.write("\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"scripts/default.js\">\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<!-- include do menu -->\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/menu.jsp", out, false);
      out.write("\n");
      out.write("<!-- fim do include do menu -->\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<!-- include do cabeçalho -->\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/header.jsp", out, false);
      out.write("\n");
      out.write("<!-- fim do include do cabeçalho -->\n");
      out.write("\n");
      out.write("<table width=\"779\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("  \n");
      out.write("  <tr>\n");
      out.write("    <td width=\"220\">&nbsp;</td>\n");
      out.write("    <td width=\"549\" height=\"180\" colspan=\"2\" valign=\"top\">\n");
      out.write("        <form method=\"get\" action=\"\">\n");
      out.write("\t        <table width=\"549\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
      out.write("\t            <tr>\n");
      out.write("\t                <td class=\"Title\">\n");
      out.write("\t                    <table width='549' cellspacing='0' cellpadding='0' border='0'>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td class=\"Title\" width=\"420\">");
      out.print( relatorioTipoNome );
      out.write(" - Listagem (");
      out.print( paginacao.getResultSize() );
      out.write(" Registros - Página ");
      out.print( pageNumber );
      out.write(" de ");
      out.print( paginacao.getPageNavigator() );
      out.write(")</td>\n");
      out.write("\t                            <td width=\"129\" align=\"right\">\n");
      out.write("\t                                <a href=\"javascript:history.back()\"><img src=\"imagens/stock_navigator-back-16.gif\" border=\"0\" alt=\"Voltar\"></a>\n");
      out.write("\t                                <a href=\"#\"><img src=\"imagens/stock_search-16.gif\" border=\"0\" alt=\"Procurar\"></a>\n");
      out.write("\t                                <a href=\"reportUpload.jsp?relatorioTipoId=");
      out.print( relatorioTipoId );
      out.write("&relatorioTipoNome=");
      out.print( relatorioTipoNome );
      out.write("\"><img src=\"imagens/stock_new-16.gif\" border=\"0\" alt=\"Novo\"></a>\n");
      out.write("\t                                <a href=\"javascript:window.location.reload()\"><img src=\"imagens/stock_refresh-16.gif\" border=\"0\" alt=\"Atualizar\"></a>\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                    </table>\n");
      out.write("\t                </td>\n");
      out.write("\t            </tr>\n");
      out.write("\t            <tr>\n");
      out.write("\t                <td height=\"1\" bgcolor=\"#000000\"></td>\n");
      out.write("\t            </tr>\n");
      out.write("\t            <tr>\n");
      out.write("\t                <td height=\"10\"></td>\n");
      out.write("\t            </tr>\n");
      out.write("\t            <tr>\n");
      out.write("\t                <td>\n");
      out.write("\t                    <table width='549' cellspacing='0' cellpadding='0' border='0'>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td width=\"150\" class=\"Title\">Nº:</td>\n");
      out.write("\t                            <td width=\"399\"><input type=\"text\" name=\"num\" class=\"Input\" onkeypress='return Number(event)'\n");
      out.write("\t                                                       maxlength=\"20\"\n");
      out.write("\t                                                   style=\"width:120px;\"></td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td width=\"150\" class=\"Title\">Tipo:</td>\n");
      out.write("\t                            <td width=\"399\">\n");
      out.write("\t                                <select name='tipo_os' id='tipo_os' class='Select' style='width:250px;'>\n");
      out.write("\t                                    <option value='' class='OptionBody1'>-- SELECIONE --</option>\n");
      out.write("\t                                    <option value='1' class='OptionBody2'>Coleta</option>\n");
      out.write("\t                                    <option value='2' class='OptionBody1'>Corretiva</option>\n");
      out.write("\t                                    <option value='3' class='OptionBody2'>DTS</option>\n");
      out.write("\t                                </select>\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td class=\"Title\">Equipamento:</td>\n");
      out.write("\t                            <td>\n");
      out.write("\t                                ");

	
	                                if (listEquipamentos != null) {
	                                    String optionStyle = "OptionBody1";
	                                    int countResult = 0;
	
	                                    out.println("<select name='equipamento' id='equipamento' class='Select' style='width:120px;'>");
	                                    out.println("<option value='0' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");
	
	                                    countResult++;
	
	                                    for (int i=0; i < listEquipamentos.size(); i++) {
	                                        equipamento = (Equipamento) listEquipamentos.get(i);
	                                        countResult++;
	
	                                        if (countResult % 2 == 0) {
	                                            optionStyle = "OptionBody2";
	                                        } else if (countResult % 2 == 1) {
	                                            optionStyle = "OptionBody1";
	                                        }
	
	                                        out.println("<option value='" + equipamento.getIdEquipamento() + "' class='" + optionStyle + "'>");
	                                        out.println(equipamento.getNomeEquipamento());
	                                        out.println("</option>");
	                                    }
	
	                                    out.println("</select>");
	                                    } else {
	                                        out.println("<select name='equipamentoSelect' class='Select' style='width:150px;' disabled='disabled'>");
	                                        out.println("<option>Não há equipamentos para selecionar.</option>");
	                                        out.println("</select>");
	                                }
	
	                                
      out.write("\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <!--tr>\n");
      out.write("\t                            <td class=\"Title\">Técnico:</td>\n");
      out.write("\t                            <td>\n");
      out.write("\t                                ");
      out.write("\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr-->\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td class=\"Title\">Data de Abertura:</td>\n");
      out.write("\t                            <td>\n");
      out.write("\t                                ");

	
	                                out.println("<select name='dt_ini_dia' id='dt_ini_dia' class='Select'>");
	
	                                for (int dia=0; dia <= 31; dia++) {
	                                    String selected = "";
	                                    /*if (dtAbertura != null) {
	                                    if (dia == dtAbertura.get(Calendar.DAY_OF_MONTH)) {
	                                    selected = " selected='selected'";
	                                    }
	                                    }*/
	
	                                    out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");
	                                }
	
	                                out.println("</select>");
	
	                                out.println(" / ");
	
	                                out.println("<select name='dt_ini_mes' id='dt_ini_mes' class='Select'>");
	
	                                for (int mes=0; mes <= 12; mes++) {
	                                    String selected = "";
	                                    /*if (dtAbertura != null) {
	                                    if (mes == (dtAbertura.get(Calendar.MONTH)) + 1) {
	                                    selected = " selected='selected'";
	                                    }
	                                    }*/
	                                    out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
	                                }
	
	                                out.println("</select>");
	
	                                out.println(" / ");
	
	                                out.println("<select name='dt_ini_ano' id='dt_ini_ano' class='Select'>");
	                                out.println("<option value='0'>0</option>");
	
	                                for (int ano=2007; ano <= 2011; ano++) {
	                                    String selected = "";
	                                    /*if (dtAbertura != null) {
	                                    if (ano == dtAbertura.get(Calendar.YEAR)) {
	                                    selected = " selected='selected'";
	                                    }
	                                    }*/
	
	                                    out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
	                                }
	
	                                out.println("</select>");
	
	                                out.println("&nbsp");
	
	                                
      out.write("\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td class=\"Title\">Data de Fechamento:</td>\n");
      out.write("\t                            <td>\n");
      out.write("\t                                ");

	
	                                out.println("<select name='dt_fim_dia' id='dt_fim_dia' class='Select'>");
	
	                                for (int dia=0; dia <= 31; dia++) {
	                                    String selected = "";
	                                    /*if (dtFechamento != null) {
	                                    if (dia == dtFechamento.get(Calendar.DAY_OF_MONTH)) {
	                                    selected = " selected='selected'";
	                                    }
	                                    }*/
	
	                                    out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");
	                                }
	
	                                out.println("</select>");
	
	                                out.println(" / ");
	
	                                out.println("<select name='dt_fim_mes' id='dt_fim_mes' class='Select'>");
	
	                                for (int mes=0; mes <= 12; mes++) {
	                                    String selected = "";
	                                    /*if (dtFechamento != null) {
	                                    if (mes == (dtFechamento.get(Calendar.MONTH)) + 1) {
	                                    selected = " selected='selected'";
	                                    }
	                                    }*/
	                                    out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
	                                }
	
	                                out.println("</select>");
	
	                                out.println(" / ");
	
	                                out.println("<select name='dt_fim_ano' id='dt_fim_ano' class='Select'>");
	                                out.println("<option value='0'>0</option>");
	
	                                for (int ano=2007; ano <= 2009; ano++) {
	                                    String selected = "";
	                                    /*if (dtFechamento != null) {
	                                    if (ano == dtFechamento.get(Calendar.YEAR)) {
	                                    selected = " selected='selected'";
	                                    }
	                                    }*/
	
	                                    out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
	                                }
	
	                                out.println("</select>");
	
	                                out.println("&nbsp");
	
	                                
      out.write("\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td colspan=\"2\" height=\"15\"></td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td colspan=\"2\" align=\"right\">\n");
      out.write("\t                                <input type=\"button\" value=\"Limpar Filtro\" id=\"Limpar Filtro\" class=\"Button\" onClick=\"location.href='osList.jsp'\" onMouseOut=\"buttonOn(this)\" onMouseOver=\"buttonOver(this)\" style=\"width: 100px;\">&nbsp;&nbsp;&nbsp;\n");
      out.write("\t                                <input type=\"submit\" value=\"Filtrar\" id=\"Filtrar\" class=\"Button\" onMouseOut=\"buttonOn(this)\" onMouseOver=\"buttonOver(this)\" style=\"width: 100px;\">\n");
      out.write("\t                            </td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                    </table>\n");
      out.write("\t                </td>\n");
      out.write("\t            </tr>\n");
      out.write("\t            <tr>\n");
      out.write("\t                <td height=\"20\"></td>\n");
      out.write("\t            </tr>\n");
      out.write("\t            <tr>\n");
      out.write("\t                <td>\n");
      out.write("\t                    <table width='549' cellspacing='0' cellpadding='0' border='0' style='border: 1px solid #000000;'>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>\n");
      out.write("\t                            <td width='50' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=id\" class=\"LinkTitle\">N&deg;.</a></td>\n");
      out.write("\t                            <td width='60' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=equipamento\" class=\"LinkTitle\">Equip.</a></td>\n");
      out.write("\t                            <td width='230' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=observacao\" class=\"LinkTitle\">Observação</a></td>\n");
      out.write("\t                            <td width='39' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=tecnico\" class=\"LinkTitle\">Tec.</a></td>\n");
      out.write("\t                            <td width='150' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=data\" class=\"LinkTitle\">Data Inicial</a></td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        ");

	                    
	                            if (list != null) {
	                                String tableStyle = "TableResultBody1";
	                                String linkReport = "";
	                                
	                                int countResult = 0;
	
	                                for (int i=0; i < list.size(); i++) {
	                                    relatorio = (Relatorio) list.get(i);
	                                    equipamento = relatorio.getEquipamento();
	                                    tecnico = relatorio.getTecnico();
	                                    
	                                    linkReport = "reportView.jsp?id=" + relatorio.getId() +
	                                            "&relatorioTipoId=" + relatorioTipoId +
	                                            "&relatorioTipoNome=" + relatorioTipoNome;
	
	                                    countResult++;
	
	                                    if (countResult % 2 == 0) {
	                                        tableStyle = "TableResultBody2";
	                                    } else if (countResult % 2 == 1) {
	                                        tableStyle = "TableResultBody1";
	                                    }
	                                    
	                                    String dtInicial = "";
	                                    
	                                    if (relatorio.getDataInicial() != null) {
	                                        dtInicial = dateFormat.format(relatorio.getDataInicial());
	                                    }
	
	                                    
      out.write("\n");
      out.write("\t\n");
      out.write("\t                                    <tr>\n");
      out.write("\t                                        <td height='16' class='");
      out.print( tableStyle );
      out.write("' align=\"right\"><img src=\"imagens/topico.gif\" height=\"15\" width=\"15\" alt=\"\"></td>\n");
      out.write("\t                                        <td class='");
      out.print( tableStyle );
      out.write("'><a href=\"");
      out.print( linkReport );
      out.write('"');
      out.write('>');
      out.print( relatorio.getId() );
      out.write("</a></td>\n");
      out.write("\t                                        <td class='");
      out.print( tableStyle );
      out.write("'><a href=\"");
      out.print( linkReport );
      out.write('"');
      out.write('>');
      out.print( equipamento.getNomeEquipamento() );
      out.write("</a></td>\n");
      out.write("\t                                        <td class='");
      out.print( tableStyle );
      out.write("'><a href=\"");
      out.print( linkReport );
      out.write('"');
      out.write('>');
      out.print( relatorio.getObservacao() );
      out.write("</a></td>\n");
      out.write("\t                                        <td class='");
      out.print( tableStyle );
      out.write("'><a href=\"");
      out.print( linkReport );
      out.write('"');
      out.write('>');
      out.print( tecnico.getMatriculaUsuario() );
      out.write("</a></td>\n");
      out.write("\t                                        <td class='");
      out.print( tableStyle );
      out.write("'><a href=\"");
      out.print( linkReport );
      out.write('"');
      out.write('>');
      out.print( dtInicial );
      out.write("</a></td>\n");
      out.write("\t                                    </tr>\n");
      out.write("\t\n");
      out.write("\t                                    ");

	                                }
	                            }
	
	                        
      out.write("\n");
      out.write("\t                        \n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td colspan=\"6\" height=\"5\"></td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td colspan=\"6\" align=\"center\">");
      out.print( pageViewNavigator );
      out.write("</td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                        <tr>\n");
      out.write("\t                            <td colspan=\"6\" height=\"5\"></td>\n");
      out.write("\t                        </tr>\n");
      out.write("\t                    </table>\n");
      out.write("\t                </td>\n");
      out.write("\t            </tr>\n");
      out.write("\t        </table>\n");
      out.write("    \t</form>\n");
      out.write("    </td>\n");
      out.write("    <td width=\"10\"></td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td colspan=\"3\" height=\"20\">&nbsp;</td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td colspan=\"3\" align=\"center\" class=\"Footer\">\n");
      out.write("        <!-- include do rodapé -->\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/footer.jsp", out, false);
      out.write("\n");
      out.write("        <!-- fim do include do rodapé -->\n");
      out.write("    </td>\n");
      out.write("  </tr>\n");
      out.write("  <tr>\n");
      out.write("    <td>&nbsp;</td>\n");
      out.write("    <td>&nbsp;</td>\n");
      out.write("    <td>&nbsp;</td>\n");
      out.write("  </tr>\n");
      out.write("</table>\n");
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
