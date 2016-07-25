<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--@page contentType="text/html" pageEncoding="UTF-8"--%>

<%

    String idOs = "";

    if ((request.getParameter("id")) != null) {
        idOs = request.getParameter("id");
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
<%--<jsp:include page="includes/menu.jsp" />--%>
<!-- fim do include do menu -->

</head>

<body>
<!-- include do cabeçalho -->
<jsp:include page="includes/header.jsp" />
<!-- fim do include do cabeçalho -->

<table width="779" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td width="13">&nbsp;</td>
    <td width="549" height="180" colspan="2" valign="top">
        <form method="post" action="addMatUtilOS">
            <table width="549" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="Title">
                        <table width='549' cellspacing='0' cellpadding='0' border='0'>
                            <tr>
                                <td class="Title" width="400">Ordem de Serviço - Incluir Material Utilizado</td>
                                <td width="149" align="right">
                                    <a href="osView.jsp?id=<%= idOs%>"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
                                     <a href='osList.jsp'> <img src='imagens/home16.gif' border='0' alt='Tela Inicial'> </a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="1" bgcolor="#000000"></td>
                </tr>
                <tr>
                    <td height="10"></td>
                </tr>
                <tr>
                    <td>
                        <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                            <tr>
                                <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                <td colspan="2" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Material Utilizado</td>
                            </tr>
                            <tr>
                                <td height="18" width="10">&nbsp;</td>
                                <td class="Title" align="center">
                                    <textarea name='material' class="TextArea" style='border: 0px solid #000000; background-color:#FFFFFF; width:529px; height: 100px;'></textarea>
                                </td>
                                <td width="10">&nbsp</td>
                            </tr>
                            <tr>
                                <td colspan="3" height="10"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="20"></td>
                </tr>
                <tr>
                    <td>
                        <table width="549" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td align="right">
                                    <input type="hidden" name="id" id="id" value="<%= idOs %>">
                                    <input type="submit" value="Salvar" id="Salvar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)" style="width: 100px;">
                                </td>
                                <td width="50"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
    </td>
    <td width="10"></td>
  </tr>
  <tr>
    <td colspan="3" height="20">&nbsp;</td>
  </tr>
            <tr>
                <td colspan="3" class="Footer">
                    <table>
                        <tr>
                            <td align="center" width="535">
                                    <!-- include do rodapé -->
                                    <jsp:include page="includes/footer.jsp" />
                                    <!-- fim do include do rodapé -->
                            </td>
                        </tr>
                    </table>
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