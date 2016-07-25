<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  
    
    String idDelete = "";
    
    if ((request.getParameter("id")) != null) {
        idDelete = request.getParameter("id");
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
                <td class="Title">Arquivos - Excluir</td>
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
                            <td colspan="2" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Confirmar Exclusão:</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="10"></td>
                        </tr>
                        <tr>
                            <td height="18" width="10">&nbsp;</td>
                            <td class="Title" align="center">Deseja excluir o arquivo selecionado?</td>
                            <td width="10">&nbsp</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20"></td>
                        </tr>
                        <tr>
                            <td height="18" width="10">&nbsp;</td>
                            <td class="Title" align="center">
                                <input type="button" value="Sim" id="Sim" class="Button" onclick="javascript:location.href='deleteArchive?id=<%= idDelete %>'" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)">&nbsp;&nbsp;&nbsp;
                                <input type="button" value="Não" id="Nao" class="Button" onclick="javascript:history.back()" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)">
                            </td>
                            <td width="10">&nbsp</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="10"></td>
                        </tr>
                    </table>
                </td>
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