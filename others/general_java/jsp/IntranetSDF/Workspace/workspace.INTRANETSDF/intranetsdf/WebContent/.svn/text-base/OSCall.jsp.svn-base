<%-- 
    Document   : OSCall
    Created on : 05/03/2010, 14:06:03
    Author     : André

    Pagina feita para abrir o resultado de OS (osList.jsp)
    em um pop-up.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="includes/sessionVerify.jsp" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INTRANET CONSORCIO SDF</title>

        <script language="javascript" type="text/javascript" src="scripts/default.js">
        </script>
       
        <link rel="stylesheet" type="text/css" href="estilos/default.css" />
    </head>
    <body>
        <!-- include do menu -->
        <jsp:include page="includes/menu.jsp" />
        <!-- fim do include do menu -->
        <jsp:include page="includes/header.jsp" />

        <center><b>O resultado irá abrir em outra janela(Pop-up).<br>Se o bloqueador de Pop-up estiver ativado desative-o.</b>
        <br><br>Redirecionando, aguarde <span id="tempo">0</span> segundos...</center>
        
        <script type="text/javascript">
        openWindowScreen('osList.jsp?LFOS=a','osList');
        conta();
        </script>
    </body>
</html>
