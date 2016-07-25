<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.CheckEquipamentoPerfil" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="checkEquipamentoPerfil" class="br.com.consorciosdf.intranet.modelo.CheckEquipamentoPerfil" scope="request" />

<%
    
    List lista;
    ManterCheckEquipamentoControl manterCheckEquipamentoControl = new ManterCheckEquipamentoControl();
    lista = manterCheckEquipamentoControl.recuperarPerfis();
    
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
                <td class="Title">Check List - Equipamento</td>
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
                            <td width="250" height="18" class="Title">Perfil de Checagem:</td>
                            <td width="10"></td>
                            <td width="289">
                                <%
                                
                                    if (lista != null) {
                                        String optionStyle = "OptionBody1";
                                        int countResult = 0;
                                        
                                        out.println("<select name='perfilChecagem' id='perfilChecagem' class='Select' onChange='perfilChecagem(this)' style='width:250px;'>");
                                        out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");
                                        
                                        countResult++;
                                        
                                        for (int i=0; i < lista.size(); i++) {
                                            checkEquipamentoPerfil = (CheckEquipamentoPerfil) lista.get(i);
                                            countResult++;
                                            
                                            if (countResult % 2 == 0) {
                                                optionStyle = "OptionBody2";
                                            } else if (countResult % 2 == 1) {
                                                optionStyle = "OptionBody1";
                                            }
                                            
                                            out.println("<option value='" + checkEquipamentoPerfil.getId() + "' class='" + optionStyle + "'>");
                                            out.println(checkEquipamentoPerfil.getNome());
                                            out.println("</option>");
                                        }
                                        
                                        out.println("</select>");
                                    } else {
                                        out.println("<select name='perfilChecagem' class='Select' style='width:250px;' disabled='disabled'>");
                                        out.println("<option>Houve um problema ao carregar essa combo.</option>");
                                        out.println("</select>");
                                    }
                                
                                %>
                            </td>
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