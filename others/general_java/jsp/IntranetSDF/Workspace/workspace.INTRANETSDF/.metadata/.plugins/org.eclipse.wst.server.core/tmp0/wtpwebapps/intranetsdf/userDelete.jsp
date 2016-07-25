<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Usuario" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />

<%

    String sucessAdd = "";
    String sucessAddClass = "Error";
    
    if ((request.getParameter("sucess")) != null) {
        if (request.getParameter("sucess").equals("1")) {
            sucessAdd = "Não foi possível deletar o usuário.";
        } else if (request.getParameter("sucess").equals("2")) {
            sucessAdd = "Usuário não selecionado.";
        } else if (request.getParameter("sucess").equals("3")) {
            sucessAdd = "Usuário deletado com sucesso.";
            sucessAddClass = "Sucess";
        }
    }
    
    List list;
    ManterUserControl manterUserControl = new ManterUserControl();
    list = manterUserControl.listUsers();

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
        <form method="post" action="deleteUser">
        <table width="549" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="Title">Usuário Web - Excluir</td>
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
                            <td width="150" height="18" class="Title">Selecionar Usuário:</td>
                            <td width="10"></td>
                            <td width="389">
                                <%
                                
                                    if (list != null) {
                                        String optionStyle = "OptionBody1";
                                        int countResult = 0;
                                        
                                        out.println("<select name='user' id='user' class='Select' style='width:250px;'>");
                                        out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");
                                        
                                        countResult++;
                                        
                                        for (int i=0; i < list.size(); i++) {
                                            usuario = (Usuario) list.get(i);
                                            countResult++;
                                            
                                            if (countResult % 2 == 0) {
                                                optionStyle = "OptionBody2";
                                            } else if (countResult % 2 == 1) {
                                                optionStyle = "OptionBody1";
                                            }
                                            
                                            out.println("<option value='" + usuario.getUser() + "' class='" + optionStyle + "'>");
                                            out.println(usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario() + " (" + usuario.getUser() + ")");
                                            out.println("</option>");
                                        }
                                        
                                        out.println("</select>");
                                    } else {
                                        out.println("<select name='userSelect' class='Select' style='width:150px;' disabled='disabled'>");
                                        out.println("<option>Não há usuários para deletar.</option>");
                                        out.println("</select>");
                                    }
                                
                                %>
                            </td>
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
                                            <input type="hidden" name="tipo" value="web">
                                            <input type="submit" value="Deletar" id="Deletar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)">
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