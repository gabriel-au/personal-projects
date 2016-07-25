<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	
	if (user != null && pass != null) {
		if (user.equals("stj") && pass.equals("mobile")) {
			session.setAttribute("logado", true);
			response.sendRedirect("principal.jsp");
		} else {
			response.sendRedirect("index.jsp?erro=1");
		}
		
	}else {
		response.sendRedirect("index.jsp?erro=1");
	}
	
%>
</html>