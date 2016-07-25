<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--@page contentType="text/html" pageEncoding="UTF-8"--%>

<%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.OS" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Usuario" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>


<%

            if ((request.getParameter("id")) != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                String matricula = (String) session.getAttribute("userMatricula");
                java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
                Date data = calendar.getTime();
                os.setDataServico(data);
                usuario.setMatriculaUsuario(matricula);
                os.setId(id);
                os.setUsuarioDest(usuario);
                os.setStatus("an");
 
                ManterOSControl manterOSControl = new ManterOSControl();
                if (manterOSControl.assumirOS(os)) {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + id + "&accept=2");
                } else {
                    response.sendRedirect(request.getContextPath() + "/osView.jsp?id=" + id + "&accept=1");
                }
            }

%>