package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.consorciosdf.intranet.controle.ManterOSControl;
import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.awt.image.DataBuffer;
import br.com.consorciosdf.intranet.persistencia.ManterUserRuleDAO;
import br.com.consorciosdf.intranet.controle.ManterUserRulesControl;

public final class osList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=ISO-8859-1");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      br.com.consorciosdf.intranet.modelo.OS os = null;
      synchronized (request) {
        os = (br.com.consorciosdf.intranet.modelo.OS) _jspx_page_context.getAttribute("os", PageContext.REQUEST_SCOPE);
        if (os == null){
          os = new br.com.consorciosdf.intranet.modelo.OS();
          _jspx_page_context.setAttribute("os", os, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.OS osVerify = null;
      synchronized (request) {
        osVerify = (br.com.consorciosdf.intranet.modelo.OS) _jspx_page_context.getAttribute("osVerify", PageContext.REQUEST_SCOPE);
        if (osVerify == null){
          osVerify = new br.com.consorciosdf.intranet.modelo.OS();
          _jspx_page_context.setAttribute("osVerify", osVerify, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.CheckEquipamento checkEquipamento = null;
      synchronized (request) {
        checkEquipamento = (br.com.consorciosdf.intranet.modelo.CheckEquipamento) _jspx_page_context.getAttribute("checkEquipamento", PageContext.REQUEST_SCOPE);
        if (checkEquipamento == null){
          checkEquipamento = new br.com.consorciosdf.intranet.modelo.CheckEquipamento();
          _jspx_page_context.setAttribute("checkEquipamento", checkEquipamento, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Equipamento equipamento = null;
      synchronized (request) {
        equipamento = (br.com.consorciosdf.intranet.modelo.Equipamento) _jspx_page_context.getAttribute("equipamento", PageContext.REQUEST_SCOPE);
        if (equipamento == null){
          equipamento = new br.com.consorciosdf.intranet.modelo.Equipamento();
          _jspx_page_context.setAttribute("equipamento", equipamento, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Usuario usuario = null;
      synchronized (request) {
        usuario = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuario", PageContext.REQUEST_SCOPE);
        if (usuario == null){
          usuario = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuario", usuario, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Usuario usuarioAutor = null;
      synchronized (request) {
        usuarioAutor = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuarioAutor", PageContext.REQUEST_SCOPE);
        if (usuarioAutor == null){
          usuarioAutor = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuarioAutor", usuarioAutor, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      br.com.consorciosdf.intranet.modelo.Usuario usuarioDest = null;
      synchronized (request) {
        usuarioDest = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuarioDest", PageContext.REQUEST_SCOPE);
        if (usuarioDest == null){
          usuarioDest = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuarioDest", usuarioDest, PageContext.REQUEST_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
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
      out.write("<!-- include de paginação de resultados - cabeçalho -->\r\n");
      out.write(" ");
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
      out.write(" \r\n");
      out.write("<!-- fim do include de paginação de resultados - cabeçalho -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Aumento de propriedades para o filtro de busca\r\n");
      out.write("e retirada do include de \"MENU\"-->\r\n");


            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            Date dataNow = new Date();
            long dataTimeNow = dataNow.getTime();
            long dataVerificador = 1 * 24 * 60 * 60 * 1000; //1 dias * 24 horas * 60 minutos * 60 segundos * 1000 milisegundos
            long dataVerifyStatus = dataTimeNow - dataVerificador;

            //String que guarda o valor dos campos que especificam a pesquisa.


            List list = null;
            List listEquipamentos = null;
            List listUsuarios = null;


            ManterOSControl manterOSControl = new ManterOSControl();
            ManterUserControl manterUserControl = new ManterUserControl();

            usuarioAtual.setUser((String) session.getAttribute("user"));
            usuarioAtual.setMatriculaUsuario((String) session.getAttribute("userMatricula"));
            usuarioAtual = manterUserControl.viewUser(usuarioAtual);
            
            ManterUserRulesControl userRulesControl = new ManterUserRulesControl();
            usuarioAtual.setUsuarioRules(userRulesControl.listUserRules(usuarioAtual.getCodUsuario()));
            /*		
            if (request.getParameter("numOS") != null &&
                    request.getParameter("statusOS") != null &&
                    request.getParameter("prioridadeOS") != null &&
                    request.getParameter("tipoOS") != null &&
                    request.getParameter("equipamento") != null &&
                    request.getParameter("tecnico") != null &&
                    request.getParameter("dt_ano_dia") != null &&
                    request.getParameter("dt_ini_dia") != null &&
                    request.getParameter("dt_serv_dia") != null &&
                    request.getParameter("dt_fim_dia") != null) {
			*/
                int idOS = 0;
                int idEquipamento = 0;
				
                String statusF = null;
                String prioridade = null;
                String tipoOS = null;
                String matriculaTecnico = null;
                String dateAnomalia = null;
                String dataInicial = null;
                String dateServico = null;
                String dataFinal = null;
                
                if(request.getParameter("LFOS") == null){
        		
                	orderBy = request.getParameter("orderBy");
                	if(orderBy != null && !orderBy.equals("")){
                		request.getSession().setAttribute("orderByFiltroOS",orderBy);
                	} else{
                		orderBy = (String) request.getSession().getAttribute("orderByFiltroOS");
                	}
                	
                	if(request.getParameter("numOS") != null && !request.getParameter("numOS").trim().equals("")){
                		idOS = Integer.parseInt(request.getParameter("numOS"));
                		request.getSession(true).setAttribute("numFiltroOS",idOS);
                	}else{
                		if(request.getSession().getAttribute("numFiltroOS") != null){
                			Object ob = request.getSession().getAttribute("numFiltroOS");
                			if(ob instanceof Integer){
                				idOS = (Integer)request.getSession().getAttribute("numFiltroOS");
                			}
                			if(ob instanceof String){
                				idOS = Integer.parseInt((String)request.getSession().getAttribute("numFiltroOS"));	
                			}
                				
                		}
                		
                	}
                	
            		statusF = request.getParameter("statusOS");
            		if(statusF != null && !statusF.trim().equals("")){
            			request.getSession(true).setAttribute("statusFiltroOS",statusF);
            		}else{
            			statusF = (String) request.getSession().getAttribute("statusFiltroOS");
            		}
            		
            		prioridade = request.getParameter("prioridadeOS");
            		if(prioridade != null && !prioridade.trim().equals("")){
            			request.getSession(true).setAttribute("prioridadeFiltroOS",prioridade);
            		} else{
            			prioridade = (String) request.getSession().getAttribute("prioridadeFiltroOS");
            		}
            		
            		tipoOS = request.getParameter("tipoOS");
            		if(tipoOS != null && !tipoOS.trim().equals("")){
            			request.getSession(true).setAttribute("tipoFiltroOS",tipoOS);
            		}else{
            			tipoOS = (String) request.getSession().getAttribute("tipoFiltroOS");
            		}
            		
            		matriculaTecnico = request.getParameter("tecnico");
            		if(matriculaTecnico != null && !matriculaTecnico.trim().equals("")){
            			request.getSession(true).setAttribute("matriculaFiltroOS",matriculaTecnico);
            		} else{
            			matriculaTecnico = (String) request.getSession().getAttribute("matriculaFiltroOS");
            		}
            		
            		if(request.getParameter("equipamento") != null){
            			idEquipamento = Integer.parseInt(request.getParameter("equipamento"));	
            		}            		
            		if(idEquipamento != 0){
            			request.getSession(true).setAttribute("equipamentoFiltroOS",""+idEquipamento);
            		} else{
            			if(request.getSession().getAttribute("equipamentoFiltroOS") != null){
            				idEquipamento = Integer.parseInt((String) request.getSession().getAttribute("equipamentoFiltroOS"));	
            			}
            		}
               
            		if(request.getParameter("dt_ano_dia") != null
            				&& request.getParameter("dt_ano_mes") != null 
            				&& request.getParameter("dt_ano_ano") != null){
            			if (!request.getParameter("dt_ano_dia").trim().equals("00") &&
                                !request.getParameter("dt_ano_mes").trim().equals("00") &&
                                !request.getParameter("dt_ano_ano").trim().equals("0000")) {
                            dateAnomalia = request.getParameter("dt_ano_ano") + "-" +
                                    request.getParameter("dt_ano_mes") + "-" + request.getParameter("dt_ano_dia");
                            request.getSession(true).setAttribute("dataAnomaliaFiltroOS",dateAnomalia);

                        } else{
                        	dateAnomalia = (String)request.getSession().getAttribute("dataAnomaliaFiltroOS");
                        }
            		}else{
                    	dateAnomalia = (String)request.getSession().getAttribute("dataAnomaliaFiltroOS");
                    }
            		
            		if(request.getParameter("dt_ini_dia") != null
            				&& request.getParameter("dt_ini_mes") != null 
            				&& request.getParameter("dt_ini_ano") != null){
	                    if (!request.getParameter("dt_ini_dia").trim().equals("00") &&
	                            !request.getParameter("dt_ini_mes").trim().equals("00") &&
	                            !request.getParameter("dt_ini_ano").trim().equals("0000")) {
	                        
	                    	dataInicial = request.getParameter("dt_ini_ano") + "-" +
	                                request.getParameter("dt_ini_mes") + "-" + request.getParameter("dt_ini_dia");
	                        request.getSession(true).setAttribute("dataInicialFiltroOS",dataInicial);

	                        	
	                    } else {
	                    	dataInicial = (String)request.getSession().getAttribute("dataInicialFiltroOS");
	                    }
            		} else {
            			dataInicial = (String)request.getSession().getAttribute("dataInicialFiltroOS");
            		}
                    
                    if(request.getParameter("dt_serv_dia") != null
            				&& request.getParameter("dt_serv_mes") != null 
            				&& request.getParameter("dt_serv_ano") != null){
	                    if (!request.getParameter("dt_serv_dia").trim().equals("00") &&
	                            !request.getParameter("dt_serv_mes").trim().equals("00") &&
	                            !request.getParameter("dt_serv_ano").trim().equals("0000")) {
	                        dateServico = request.getParameter("dt_serv_ano") + "-" +
	                                request.getParameter("dt_serv_mes") + "-" + request.getParameter("dt_serv_dia");
	                        request.getSession(true).setAttribute("dataServicoFilroOS",dateServico);
;
	                        	
	                    } else {
	                    	dateServico = (String)request.getSession().getAttribute("dataServicoFiltroOS");
	                    }
                    } else{
                    	dateServico = (String)request.getSession().getAttribute("dataServicoFiltroOS");
                    }
                    
                    if(request.getParameter("dt_fim_dia") != null
            				&& request.getParameter("dt_fim_mes") != null 
            				&& request.getParameter("dt_fim_ano") != null){
	                    if (!request.getParameter("dt_fim_dia").trim().equals("00") &&
	                            !request.getParameter("dt_fim_mes").trim().equals("00") &&
	                            !request.getParameter("dt_fim_ano").trim().equals("0000")) {
	                        dataFinal = request.getParameter("dt_fim_ano") + "-" +
	                                request.getParameter("dt_fim_mes") + "-" + request.getParameter("dt_fim_dia");
	                        request.getSession(true).setAttribute("dataFinalFiltroOS",dataFinal);

	                    } else {
	                    	dataFinal = (String)request.getSession().getAttribute("dataFinalFiltroOS");
	                    }
                    } else {
                    	dataFinal = (String)request.getSession().getAttribute("dataFinalFiltroOS");
                    }
            		             	
                	
                } else{
                	request.getSession(true).setAttribute("numFiltroOS","0");
                	request.getSession(true).setAttribute("statusFiltroOS",null);
                	request.getSession(true).setAttribute("prioridadeFiltroOS",null);
                	request.getSession(true).setAttribute("tipoFiltroOS",null);
                	request.getSession(true).setAttribute("matriculaFiltroOS",null);
                	request.getSession(true).setAttribute("equipamentoFiltroOS","0");
                	request.getSession(true).setAttribute("dataAnomaliaFiltroOS",null);
                	request.getSession(true).setAttribute("dataInicialFiltroOS",null);
                	request.getSession(true).setAttribute("dataServicoFilroOS",null);
                	request.getSession(true).setAttribute("dataFinalFiltroOS",null);
            	}


                /*
                if (!request.getParameter("numOS").trim().equals("")) {
                    idOS = Integer.parseInt(request.getParameter("numOS"));

                }
                if (!request.getParameter("statusOS").equals("")) {
                    status = request.getParameter("statusOS");
                }

                if(!request.getParameter("prioridadeOS").equals("")){
                    prioridade = request.getParameter("prioridadeOS");
                }

                if (!request.getParameter("tipoOS").equals("")) {
                    tipoOS = request.getParameter("tipoOS");

                }
                if (!request.getParameter("equipamento").equals("0")) {
                    idEquipamento = Integer.parseInt(request.getParameter("equipamento"));


                }
                if (!request.getParameter("tecnico").equals("")) {
                    matriculaTecnico = request.getParameter("tecnico");


                }
                if (!request.getParameter("dt_ano_dia").trim().equals("0") &&
                        !request.getParameter("dt_ano_mes").trim().equals("0") &&
                        !request.getParameter("dt_ano_ano").trim().equals("0")) {

                    dateAnomalia = request.getParameter("dt_ano_ano") + "-" +
                            request.getParameter("dt_ano_mes") + "-" + request.getParameter("dt_ano_dia");
                }
                if (!request.getParameter("dt_ini_dia").trim().equals("0") &&
                        !request.getParameter("dt_ini_mes").trim().equals("0") &&
                        !request.getParameter("dt_ini_ano").trim().equals("0")) {

                    dataInicial = request.getParameter("dt_ini_ano") + "-" +
                            request.getParameter("dt_ini_mes") + "-" + request.getParameter("dt_ini_dia");
                }
                if (!request.getParameter("dt_serv_dia").trim().equals("0") &&
                        !request.getParameter("dt_serv_mes").trim().equals("0") &&
                        !request.getParameter("dt_serv_ano").trim().equals("0")) {

                    dateServico = request.getParameter("dt_serv_ano") + "-" +
                            request.getParameter("dt_serv_mes") + "-" + request.getParameter("dt_serv_dia");
                }
                if (!request.getParameter("dt_fim_dia").trim().equals("0") &&
                        !request.getParameter("dt_fim_mes").trim().equals("0") &&
                        !request.getParameter("dt_fim_ano").trim().equals("0")) {

                    dataFinal = request.getParameter("dt_fim_ano") + "-" +
                            request.getParameter("dt_fim_mes") + "-" + request.getParameter("dt_fim_dia");
                }
                */
                
                list = manterOSControl.recuperarOSsPagFiltro(idOS, statusF, prioridade,tipoOS, idEquipamento, matriculaTecnico, dateAnomalia,dataInicial,dateServico, dataFinal, paginaInicial, qtdRegistros, orderBy, order);

               /* 
            } else {
                
            }*/

            listEquipamentos = manterOSControl.recuperarEquipamentos();
            listUsuarios = manterUserControl.listUsers();

            if (list != null) {
                if (list.size() > 0) {
                    osVerify = (OS) list.get(0);
                    paginacao = osVerify.getPaginacao();
                }
            }


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- include de paginação de resultados - rodapé -->\r\n");


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
      out.write("<!-- fim do include de paginação de resultados - rodapé -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
      out.write("        <table width=\"\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td width=\"15\">&nbsp;</td>\r\n");
      out.write("                <td width=\"\" height=\"180\" colspan=\"2\" valign=\"top\">\r\n");
      out.write("                    <form method=\"get\" action=\"\">\r\n");
      out.write("                        <table width=\"\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td class=\"Title\">\r\n");
      out.write("                                    <table width='100%' cellspacing='0' cellpadding='0' border='0'>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\" width=\"\">Ordem de Serviço - Listagem (");
      out.print( paginacao.getResultSize());
      out.write(" Registros - Página ");
      out.print( pageNumber);
      out.write(" de ");
      out.print( paginacao.getPageNavigator());
      out.write(")</td>\r\n");
      out.write("                                            <td width=\"\" align=\"right\">\r\n");
      out.write("                                                ");
      out.write("\r\n");
      out.write("                                                    ");
      out.write("\r\n");
      out.write("                                                    ");

                                                    
                                                    if(usuarioAtual == null || usuarioAtual.getCodUsuario() == 0 ){
                                                    	usuarioAtual.setUser((String) session.getAttribute("user"));
                                                        usuarioAtual.setMatriculaUsuario((String) session.getAttribute("userMatricula"));
                                                        usuarioAtual = manterUserControl.viewUser(usuarioAtual);
                                                    }
                                                    
                                                    if(usuarioAtual.getUsuarioRules() == null || usuarioAtual.getUsuarioRules().size() == 0){
                                                    	usuarioAtual.setUsuarioRules(userRulesControl.listUserRules(usuarioAtual.getCodUsuario()));	
                                                    }											
                                                    
                                                    
										            for (int j = 0; j < usuarioAtual.getUsuarioRules().size(); j++) {
										
										                if (usuarioAtual.getUsuarioRules().get(j).getNome().equals("os_incluir")) {
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                                <a href=\"osAdd.jsp\"><img src=\"imagens/stock_new-16.gif\" border=\"0\" alt=\"Novo\"></a>\r\n");
      out.write("                                                    ");
}
										            }
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                                <a href=\"javascript:window.location.reload()\"><img src=\"imagens/stock_refresh-16.gif\" border=\"0\" alt=\"Atualizar\"></a>\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                    </table>\r\n");
      out.write("                                </td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td height=\"1\" bgcolor=\"#000000\"></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td height=\"10\"></td>\r\n");
      out.write("                            </tr>\r\n");
      out.write("                            <tr>\r\n");
      out.write("                                <td>\r\n");
      out.write("                                    <table width='' cellspacing='0' cellpadding='0' border='0'>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td width=\"165px\" class=\"Title\">Nº OS:</td>\r\n");
      out.write("                                            <td width=\"\"><input type=\"text\" name=\"numOS\" class=\"Input\" onkeypress='return Number(event)'\r\n");
      out.write("                                                                maxlength=\"20\" value=\"");
if(idOS != 0){ out.print(""+idOS);}
      out.write("\"\r\n");
      out.write("                                                                style=\"width:120px;\"></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td width=\"\" class=\"Title\">Status:</td>\r\n");
      out.write("                                            <td width=\"\">\r\n");
      out.write("                                                <select name='statusOS' id='statusOS' class='Select' style='width:250px;'>\r\n");
      out.write("                                                    <option value='' class='OptionBody1' >-- SELECIONE --</option>\r\n");
      out.write("                                                    <option value='ab' class='OptionBody2'");
if (statusF != null && statusF.equals("ab")) {
                out.print("selected");
            }
      out.write(">ABERTO</option>\r\n");
      out.write("                                                    <option value='an' class='OptionBody1'");
if (statusF != null && statusF.equals("an")) {
                out.print("selected");
            }
      out.write(">ANÁLISE</option>\r\n");
      out.write("                                                    <option value='ag' class='OptionBody2'");
if (statusF != null && statusF.equals("ag")) {
                out.print("selected");
            }
      out.write(">AGUARDANDO</option>\r\n");
      out.write("                                                    <option value='fc' class='OptionBody1'");
if (statusF != null && statusF.equals("fc")) {
                out.print("selected");
            }
      out.write(">FECHADO</option>\r\n");
      out.write("                                                </select>\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Prioridade:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                <select name='prioridadeOS' id='prioridadeOS' class='Select' style='width:250px;'>\r\n");
      out.write("                                                    <option value='' class='OptionBody1' selected>-- SELECIONE --</option>\r\n");
      out.write("                                                    <option value='0' class='OptionBody2' ");
if(prioridade != null && prioridade.equals("0")){ 
      out.write(" selected=\"selected\"");
} 
      out.write(">ALTA</option>\r\n");
      out.write("                                                    <option value='1' class='OptionBody1' ");
if(prioridade != null && prioridade.equals("1")){ 
      out.write(" selected=\"selected\"");
} 
      out.write(">MÉDIA</option>\r\n");
      out.write("                                                    <option value='2' class='OptionBody2' ");
if(prioridade != null && prioridade.equals("2")){ 
      out.write(" selected=\"selected\"");
} 
      out.write(">BAIXA</option>\r\n");
      out.write("                                                </select>\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td width=\"\" class=\"Title\">Tipo de OS:</td>\r\n");
      out.write("                                            <td width=\"\">\r\n");
      out.write("                                                <select name='tipoOS' id='tipoOS' class='Select' style='width:250px;'>\r\n");
      out.write("                                                    <option value='' class='OptionBody1'>-- SELECIONE --</option>\r\n");
      out.write("                                                    <option value='p' class='OptionBody2'");
if (tipoOS != null && tipoOS.equals("p")) {out.print("selected");}
      out.write(">Manutenção Preventiva</option>\r\n");
      out.write("                                                    <option value='c' class='OptionBody1'");
if (tipoOS != null && tipoOS.equals("c")) {out.print("selected");}
      out.write(">Manutenção Corretiva</option>\r\n");
      out.write("                                                    <option value='a' class='OptionBody2'");
if (tipoOS != null && tipoOS.equals("a")) {out.print("selected");}
      out.write(">Aferição</option>\r\n");
      out.write("                                                </select>\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Equipamento:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                ");


            if (listEquipamentos != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='equipamento' id='equipamento' class='Select' style='width:120px;'>");
                out.println("<option value='0' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listEquipamentos.size(); i++) {
                    equipamento = (Equipamento) listEquipamentos.get(i);
                    countResult++;
                    String select = "";
                    if (idEquipamento == equipamento.getIdEquipamento()) {
                        select = "selected";
                    }
                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + equipamento.getIdEquipamento() + "' class='" + optionStyle + "' " + select + ">");
                    out.println(equipamento.getNomeEquipamento());
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='equipamentoSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>Não há usuários para editar.</option>");
                out.println("</select>");
            }

                                                
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Técnico:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                ");


            if (listUsuarios != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='tecnico' id='tecnico' class='Select' style='width:250px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listUsuarios.size(); i++) {
                    usuario = (Usuario) listUsuarios.get(i);
                    countResult++;
                    String select = "";
                    if (matriculaTecnico != null && matriculaTecnico.equals(usuario.getMatriculaUsuario())) {
                        select = "selected";
                    }
                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + usuario.getMatriculaUsuario() + "' class='" + optionStyle + "'" + select + ">");
                    out.println(usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario() + " (" + usuario.getUser() + ")");
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='userSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>Não há usuários para editar.</option>");
                out.println("</select>");
            }

                                                
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Data de Anomalia:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                ");


            out.println("<select name='dt_ano_dia' id='dt_ano_dia' class='Select'>");
            if(dateAnomalia != null && !dateAnomalia.equals("00-00-0000") && !dateAnomalia.equals("")){
            	String diaTemp = null;
            	diaTemp = dateAnomalia.substring(8);
            	out.println("<option value='" + diaTemp + "' selected>" + diaTemp + "</option>");
            }
            for (int dia = 0; dia <= 31; dia++) {
                String selected = "";
				

				                
                if(dia < 10){
                	out.println("<option value='0" + dia + "'" + selected + ">0" + dia + "</option>");
                } else {
                	out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");	
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_ano_mes' id='dt_ano_mes' class='Select'>");

			if(dateAnomalia != null && !dateAnomalia.equals("00-00-0000") && !dateAnomalia.equals("")){
				String mesTemp = null;
            	mesTemp = dateAnomalia.substring(5,7);
            	out.println("<option value='" + mesTemp + "' selected>" + mesTemp + "</option>");
            }
            for (int mes = 0; mes <= 12; mes++) {
                String selected = "";
                if(mes < 10){
                	out.println("<option value='0" + mes + "'" + selected + ">0" + mes + "</option>");
                } else{
                	out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_ano_ano' id='dt_ano_ano' class='Select'>");            
            out.println("<option value='0000'>0000</option>");
            if(dateAnomalia != null && !dateAnomalia.equals("00-00-0000") && !dateAnomalia.equals("")){
                String anoTemp = null;
            	anoTemp = dateAnomalia.substring(0,4);
            	out.println("<option value='" + anoTemp + "' selected>" + anoTemp + "</option>");
            }
            for (int ano = 2007; ano <= 2012; ano++) {
                String selected = "";

                out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
            }

            out.println("</select>");

            out.println("&nbsp");

                                                
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Data de Abertura:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                ");


            out.println("<select name='dt_ini_dia' id='dt_ini_dia' class='Select'>");
            if(dataInicial != null && !dataInicial.equals("00-00-0000") && !dataInicial.equals("")){
                String diaTemp = null;
            	diaTemp = dataInicial.substring(8);
            	out.println("<option value='" + diaTemp + "' selected>" + diaTemp + "</option>");
            }
            for (int dia = 0; dia <= 31; dia++) {
                String selected = "";
                if(dia < 10){
                	out.println("<option value='0" + dia + "'" + selected + ">0" + dia + "</option>");
                } else {
                	out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");	
                }

            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_ini_mes' id='dt_ini_mes' class='Select'>");
			
			if(dataInicial != null && !dataInicial.equals("00-00-0000") && !dataInicial.equals("")){
				String mesTemp = null;
            	mesTemp = dataInicial.substring(5,7);
            	out.println("<option value='" + mesTemp + "' selected>" + mesTemp + "</option>");
            }
            for (int mes = 0; mes <= 12; mes++) {
                String selected = "";
                


                if(mes < 10){
                	out.println("<option value='0" + mes + "'" + selected + ">0" + mes + "</option>");
                } else{
                	out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_ini_ano' id='dt_ini_ano' class='Select'>");
            out.println("<option value='0000'>0000</option>");
            
            if(dataInicial != null && !dataInicial.equals("00-00-0000") && !dataInicial.equals("")){
            	String anoTemp = null;
            	anoTemp = dataInicial.substring(0,4);
            	out.println("<option value='" + anoTemp + "' selected>" + anoTemp + "</option>");
            }
            for (int ano = 2007; ano <= 2012; ano++) {
                String selected = "";
  

                out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
            }

            out.println("</select>");

            out.println("&nbsp");

                                                
      out.write("\r\n");
      out.write("                                                </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Data de Serviço:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                ");


            out.println("<select name='dt_serv_dia' id='dt_serv_dia' class='Select'>");
			
            if(dateServico != null && !dateServico.equals("00-00-0000") && !dateServico.equals("")){
            	String diaTemp = null;
            	diaTemp = dateServico.substring(8);
            	out.println("<option value='" + diaTemp + "' selected>" + diaTemp + "</option>");
            }			
            for (int dia = 0; dia <= 31; dia++) {
                String selected = "";

                if(dia < 10){
                	out.println("<option value='0" + dia + "'" + selected + ">0" + dia + "</option>");
                } else {
                	out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");	
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_serv_mes' id='dt_serv_mes' class='Select'>");
            if(dateServico != null && !dateServico.equals("00-00-0000") && !dateServico.equals("")){
            	String mesTemp = null;
            	mesTemp = dateServico.substring(5,7);
            	out.println("<option value='" + mesTemp + "' selected>" + mesTemp + "</option>");
            }
            for (int mes = 0; mes <= 12; mes++) {
                String selected = "";


                if(mes < 10){
                	out.println("<option value='0" + mes + "'" + selected + ">0" + mes + "</option>");
                } else{
                	out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_serv_ano' id='dt_serv_ano' class='Select'>");
            out.println("<option value='0000'>0000</option>");
            
            if(dateServico != null && !dateServico.equals("00-00-0000") && !dateServico.equals("")){
            	String anoTemp = null;
            	anoTemp = dateServico.substring(0,4);
            	out.println("<option value='" + anoTemp + "' selected>" + anoTemp + "</option>");
            }
            for (int ano = 2007; ano <= 2012; ano++) {
                String selected = "";


                out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
            }

            out.println("</select>");

            out.println("&nbsp");

                                                
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td class=\"Title\">Data de Fechamento:</td>\r\n");
      out.write("                                            <td>\r\n");
      out.write("                                                ");


            out.println("<select name='dt_fim_dia' id='dt_fim_dia' class='Select'>");
			
            if(dataFinal != null && !dataFinal.equals("00-00-0000") && !dataFinal.equals("")){
            	String diaTemp = null;
            	diaTemp = dataFinal.substring(8);
            	out.println("<option value='" + diaTemp + "' selected>" + diaTemp + "</option>");
            }
            for (int dia = 0; dia <= 31; dia++) {
                String selected = "";


                if(dia < 10){
                	out.println("<option value='0" + dia + "'" + selected + ">0" + dia + "</option>");
                } else {
                	out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");	
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_fim_mes' id='dt_fim_mes' class='Select'>");
			if(dataFinal != null && !dataFinal.equals("00-00-0000") && !dataFinal.equals("")){
				String mesTemp = null;
				mesTemp = dataFinal.substring(5,7);
            	out.println("<option value='" + mesTemp + "' selected>" + mesTemp + "</option>");
            }
            for (int mes = 0; mes <= 12; mes++) {
                String selected = "";
                if(mes < 10){
                	out.println("<option value=0'" + mes + "'" + selected + ">0" + mes + "</option>");
                } else{
                	out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
                }
            }

            out.println("</select>");

            out.println(" / ");

            out.println("<select name='dt_fim_ano' id='dt_fim_ano' class='Select'>");
            out.println("<option value='0000'>0000</option>");
            if(dataFinal != null && !dataFinal.equals("00-00-0000") && !dataFinal.equals("")){
            	String anoTemp = null;
            	anoTemp = dataFinal.substring(0,4);
            	out.println("<option value='" + anoTemp + "' selected>" + anoTemp + "</option>");
            }
            
            for (int ano = 2007; ano <= 2012; ano++) {
                String selected = "";
                
                out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
            }

            out.println("</select>");

            out.println("&nbsp");

                                                
      out.write("\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td colspan=\"2\" height=\"15\"></td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                        <tr>\r\n");
      out.write("                                            <td colspan=\"2\" align=\"center\">\r\n");
      out.write("                                                <input type=\"button\" value=\"Limpar Filtro\" id=\"Limpar Filtro\" class=\"Button\" onClick=\"location.href='osList.jsp?LFOS=a'\" onMouseOut=\"buttonOn(this)\" onMouseOver=\"buttonOver(this)\" style=\"width: 100px;\">&nbsp;&nbsp;&nbsp;\r\n");
      out.write("                                                <input type=\"submit\" value=\"Filtrar\" id=\"Filtrar\" class=\"Button\" onMouseOut=\"buttonOn(this)\" onMouseOver=\"buttonOver(this)\" style=\"width: 100px;\">\r\n");
      out.write("                                            </td>\r\n");
      out.write("                                        </tr>\r\n");
      out.write("                                    </table>\r\n");
      out.write("                                </td>\r\n");
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
      out.write("                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href=\"?orderBy=data_anomalia&page=1\" class=\"LinkTitle\">Data Anomalia</a></td>\r\n");
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
      out.write("\">\r\n");
      out.write("                                                    ");

                                                        if (os.getDataAnomalia() == null) {
                                                            out.println("00:00:00 00/00/0000");
                                                            } else {
                                                                out.println(dateFormat.format(os.getDataAnomalia()));
                                                            }
                                                    
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
      out.write("' ><a href=\"osView.jsp?id=");
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
      out.write("        <table align=\"center\">\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td colspan=\"3\" height=\"20\">&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td colspan=\"3\" align=\"center\" class=\"Footer\">\r\n");
      out.write("                    <!-- include do rodapé -->\r\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/footer.jsp", out, false);
      out.write("\r\n");
      out.write("                    <!-- fim do include do rodapé -->\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("                <td>&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("\r\n");
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
