<% 
    
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

%>