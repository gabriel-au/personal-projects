<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    String sucessAdd = "";
    String sucessAddClass = "Error";

    if ((request.getParameter("sucess")) != null) {
        if (request.getParameter("sucess").equals("1")) {
            sucessAdd = "Ordem de Serviço inserida com sucesso.";
            sucessAddClass = "Sucess";
        } else if (request.getParameter("sucess").equals("2")) {
            sucessAdd = "Favor digitar os campos corretamente.";
        } else if (request.getParameter("sucess").equals("3")) {
            sucessAdd = "Houve um problema na transação.";
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
            <form method="POST" action="saveCompras">
            <table width="549" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="Title">Compras - Cadastrar</td>
                </tr>
                <tr>
                    <td height="1" bgcolor="#000000"></td>
                </tr>
                <tr>
                    <td height="10"></td>
                </tr>
                <tr>
                    <td valign="top">
                        <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                            <tr>
                                <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                <td colspan="4" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do arquivo:</td>
                            </tr>
                            <tr>
                                <td colspan="5" height="5"></td>
                            </tr>
                            <tr>
                                <td width="10"></td>
                                <td width="220" height="18" class="Title">Descrição:</td>
                                <td width="9"></td>
                                <td width="300"><input type="text" name="descricao" class="Input"
                                   maxlength="255"
                                   style="width:250px;"></td>
                                <td width="10"></td>
                            </tr>
                            <tr>
                                <td colspan="5" height="5"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td height="18" class="Title">Empresas Participantes:</td>
                                <td></td>
                                <td><input type="text" name="empresas" class="Input"
                                   maxlength="255"
                                   style="width:250px;"></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="5" height="10"></td>
                            </tr>
                            <tr>
                                <td width="10">&nbsp;</td>
                                <td colspan="3" align="right">
                                    <input type='submit' value='Salvar' id='Salvar' class='Button' onMouseOut='buttonOn(this)' onMouseOver='buttonOver(this)' style='width: 100px;'>
                                </td>
                                <td width="10">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="5" height="10"></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="10"></td>
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
        <td colspan="3" class="<%= sucessAddClass %>" align="center"><%= sucessAdd %></td>
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