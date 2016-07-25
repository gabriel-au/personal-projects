package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import br.com.consorciosdf.intranet.controle.ManterUserControl;
import br.com.consorciosdf.intranet.modelo.Usuario;
import br.com.consorciosdf.intranet.seguranca.Criptography;;

public final class userVerify_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      br.com.consorciosdf.intranet.modelo.Usuario usuario = null;
      synchronized (request) {
        usuario = (br.com.consorciosdf.intranet.modelo.Usuario) _jspx_page_context.getAttribute("usuario", PageContext.REQUEST_SCOPE);
        if (usuario == null){
          usuario = new br.com.consorciosdf.intranet.modelo.Usuario();
          _jspx_page_context.setAttribute("usuario", usuario, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\r\n");


            if ((request.getParameter("user")) != null && (request.getParameter("pass")) != null) {
                if (!request.getParameter("user").equals("") || !request.getParameter("pass").equals("")) {
                    ManterUserControl manterUserControl = new ManterUserControl();

                    Usuario userLogin = new Usuario();
                    userLogin.setUser(request.getParameter("user"));
                    userLogin.setPassword(Criptography.criptografar(request.getParameter("pass")));

                    usuario = manterUserControl.loginWeb(userLogin);
                    request.getSession(true).setAttribute("usuarioFilter",usuario);
                    if (usuario != null) {
                        session.setAttribute("user", request.getParameter("user"));
                        session.setAttribute("userCod", String.valueOf(usuario.getCodUsuario()));
                        session.setAttribute("userMatricula", String.valueOf(usuario.getMatriculaUsuario()));
                        session.setAttribute("userName", usuario.getNomeUsuario());
                        session.setAttribute("userLastname", usuario.getSobrenomeUsuario());
                        session.setAttribute("userEmail", usuario.getEmailUsuario());
                        session.setAttribute("userPerfil", String.valueOf(usuario.getPerfilUsuario()));
                        session.setAttribute("userModify", usuario.getModificarPrefUsuario());

                        if (!request.getParameter("urlDest").equals("")) {
                            response.sendRedirect(request.getParameter("urlDest"));
                        } else {
                            response.sendRedirect(request.getContextPath() + "/main.jsp");
                        }
                    } else {
                        if (!request.getParameter("urlDest").equals("")) {
                            response.sendRedirect(request.getParameter("urlDest"));
                        } else {
                            response.sendRedirect(request.getContextPath() + "/index.jsp?erro=2");
                        }
                    }
                } else {
                    if (!request.getParameter("urlDest").equals("")) {
                        response.sendRedirect(request.getParameter("urlDest"));
                    } else {
                        response.sendRedirect(request.getContextPath() + "/index.jsp?erro=1");
                    }
                }
            } else {
                if (!request.getParameter("urlDest").equals("")) {
                    response.sendRedirect(request.getParameter("urlDest"));
                } else {
                    response.sendRedirect(request.getContextPath() + "/index.jsp?erro=5");
                }
            }


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
