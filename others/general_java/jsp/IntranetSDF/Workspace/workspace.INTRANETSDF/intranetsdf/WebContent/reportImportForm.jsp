<%
    
    String fileName = "";
    String fileSize = "";
    String fileSizeResult = "";
    
    String relatorioTipoId = "";
    String relatorioTipoNome = "";
    
    String buttonSubmit = "<input type='submit' value='Importar' " +
                            "id='Importar' class='Button' " +
                            "onMouseOut='buttonOn(this)' " +
                            "onMouseOver='buttonOver(this)' " +
                            "style='width: 100px;'>";
    
    if ((request.getParameter("fileName")) != null && 
            (request.getParameter("fileSize")) != null) {
        
        fileName = request.getParameter("fileName");
        fileSize = request.getParameter("fileSize");

        if (!fileSize.trim().equals("")) {
            fileSizeResult = fileSize.substring(0,4) + " MB";
        }
    }
    
    if (fileSize != null) {
        
    }
    
    String sucessAdd = "";
    String sucessAddClass = "Error";

    if ((request.getParameter("sucess")) != null) {
        if (request.getParameter("sucess").equals("2")) {
            sucessAdd = "Houve um problema na transação.";
            
            session.removeAttribute("relatorioTipoId");
            session.removeAttribute("relatorioTipoNome");
            
            buttonSubmit = "";
        } else if (request.getParameter("sucess").equals("3")) {
            sucessAdd = "Planilha importada com sucesso.";
            sucessAddClass = "Sucess";
            
            relatorioTipoId = (String) session.getAttribute("relatorioTipoId");
            relatorioTipoNome = (String) session.getAttribute("relatorioTipoNome");
            
            session.removeAttribute("relatorioTipoId");
            session.removeAttribute("relatorioTipoNome");
            
            buttonSubmit = "";
        }
    }
    
    if ((session.getAttribute("relatorioTipoId")) != null &&
        (session.getAttribute("relatorioTipoNome")) != null) {
        
        relatorioTipoId = (String) session.getAttribute("relatorioTipoId");
        relatorioTipoNome = (String) session.getAttribute("relatorioTipoNome");
        
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <form method="POST" action="importReport">
                    <table width="549" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="Title"><%= relatorioTipoNome %> - Importar Planilha</td>
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
                                        <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados da Importação:</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="120">Nome do Arquivo:</td>
                                        <td width="10">&nbsp;</td>
                                        <td><%= fileName %></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Tamanho:</td>
                                        <td>&nbsp;</td>
                                        <td><%= fileSizeResult %></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Tipo de Relatório:</td>
                                        <td>&nbsp;</td>
                                        <td><%= relatorioTipoNome %></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td class="<%= sucessAddClass %>" align="center"><%= sucessAdd %></td>
                        </tr>
                        <tr>
                            <td align="right">
                                <input type="hidden" name="arquivo" value="<%= fileName %>">
                                <input type="hidden" name="idRelatorio" value="<%= relatorioTipoId %>">
                                <%= buttonSubmit %>
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