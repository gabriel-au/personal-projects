<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page import="java.text.SimpleDateFormat"%><html>
    <head>
        <title><decorator:title default="FISCON - Impressão" /></title>
        <decorator:head />
    </head>

    <body onload="window.print();">
    <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); %>
        Impresso em <%= sdf.format(new java.util.Date())%>.<br/>
        <hr noshade="noshade" size="1"/>
        <br/>
        <decorator:body />
    </body>
</html>