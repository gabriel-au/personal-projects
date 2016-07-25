<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	request.getSession(true).setAttribute("listaOSMobile",null);
	request.getSession(true).setAttribute("usuarioFilter",null);
    session.invalidate();
    response.sendRedirect(request.getContextPath() + "/index.jsp");
    
%>