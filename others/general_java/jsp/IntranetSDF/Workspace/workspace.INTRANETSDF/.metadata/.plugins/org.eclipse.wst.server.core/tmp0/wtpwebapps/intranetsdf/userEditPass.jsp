<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String sucessAdd = "";
    String sucessAddClass = "Error";
    
    if ((request.getParameter("sucess")) != null) {
        if (request.getParameter("sucess").equals("1")) {
            sucessAdd = "Favor digitar os campos corretamente.";
        } else if (request.getParameter("sucess").equals("2")) {
            sucessAdd = "Não foi possível alterar a senha.";
        } else if (request.getParameter("sucess").equals("3")) {
            sucessAdd = "Senha incorreta.";
        } else if (request.getParameter("sucess").equals("4")) {
            sucessAdd = "Senhas não conferem, por favor, digite-as novamente.";
        } else if (request.getParameter("sucess").equals("5")) {
            sucessAdd = "Senha não pode ser alterada.";
        } else if (request.getParameter("sucess").equals("6")) {
            sucessAdd = "Senha alterada com sucesso.";
            sucessAddClass = "Sucess";
        }
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
        <form method="post" action="editPassUser">
        <table width="549" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="Title">Usuário Web - Alterar Senha</td>
            </tr>
            <tr>
                <td height="1" bgcolor="#000000"></td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
              <td>
                <table width="549" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="150" height="18" class="Title">Senha Atual:</td>
                            <td width="10"></td>
                            <td width="389"><input type="password" name="passOld" class="Input"
                                       maxlength="20" style="width: 150px;"></td>
                        </tr>
                        <tr>
                            <td height="18" class="Title">Nova Senha:</td>
                            <td></td>
                            <td><input type="password" name="passNew" class="Input"
                                       maxlength="20" style="width: 150px;"></td>
                        </tr>
                        <tr>
                            <td height="18" class="Title">Confirmar Nova Senha:</td>
                            <td></td>
                            <td><input type="password" name="passNewConfirm" class="Input"
                                       maxlength="20" style="width: 150px;"></td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20"></td>
                        </tr>
                        <tr>
                            <td colspan="3"><b>Obs.:</b> Todos os campos são de preenchimento obrigatório.</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20"></td>
                        </tr>
                        <tr>
                            <td colspan="3" class="<%= sucessAddClass %>"><%= sucessAdd %></td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20"></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <table width="549" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td align="right">
                                            <input type="hidden" name="user" value="<%= session.getAttribute("user") %>">
                                            <input type="submit" value="Alterar" id="Alterar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)">
                                        </td>
                                        <td width="150"></td>
                                    </tr>
                                </table>
                            </td>
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