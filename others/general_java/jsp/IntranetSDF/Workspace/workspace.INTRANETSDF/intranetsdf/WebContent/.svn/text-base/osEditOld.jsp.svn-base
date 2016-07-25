<%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="usuarioAutor" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioDest" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

<%
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    if ((request.getParameter("id")) != null) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ManterOSControl manterOSControl = new ManterOSControl();
        os = manterOSControl.recuperarOS(id);
    }
    
    if ((request.getParameter("id")) != null) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ManterOSControl manterOSControl = new ManterOSControl();
        os = manterOSControl.recuperarOS(id);
    }
    
%>

<!-- include de verificação do usuário -->
<%@ include file="includes/sessionVerify.jsp"%>
<!-- fim do include verificação do usuário -->

<html>
<head>
<title>INTRANET CONSORCIO SDF</title>
<link rel="stylesheet" type="text/css" href="estilos/default.css" />

<script language="javascript" type="text/javascript" src="scripts/default.js">
</script>

<!-- include do menu -->
<jsp:include page="includes/menu.jsp" />
<!-- fim do include do menu -->

</head>

<body>
<!-- include do cabeçalho -->
<jsp:include page="includes/header.jsp" />
<!-- fim do include do cabeçalho -->

<table width="779" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="220">&nbsp;</td>
    <td width="549" height="180" colspan="2" valign="top">
        <table width="549" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="Title">Ordem de Serviço - Alterar</td>
            </tr>
            <tr>
                <td height="1" bgcolor="#000000"></td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
        </table>
    </td>
    <td width="10"></td>
  </tr>
  <tr>
    <td colspan="3" height="20">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="Footer">
    <!-- include do rodapé -->
    <jsp:include page="includes/footer.jsp" />
    <!-- fim do include do rodapé -->
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>