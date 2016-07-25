<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterArchiveControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Arquivo" %>

<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="arquivo" class="br.com.consorciosdf.intranet.modelo.Arquivo" scope="request"/>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    String dataInclusao = "";
    
    String menuHorizontal = "";
    
    if ((request.getParameter("id")) != null) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ManterArchiveControl manterArchiveControl = new ManterArchiveControl();
        arquivo = manterArchiveControl.recuperarArquivo(id);
        
        dataInclusao = dateFormat.format(arquivo.getDataInclusao());
    }
    
    int userPerfil = Integer.parseInt((String) session.getAttribute("userPerfil"));
    
    if (userPerfil <= 1) {
        menuHorizontal = "<a href='javascript:history.back()'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'></a> " +
                          "<a href='#'><img src='imagens/stock_edit-16.gif' border='0' alt='Editar'></a> " +
                          "<a href='archiveDelete.jsp?id=" + arquivo.getId() + "'><img src='imagens/stock_delete-16.gif' border='0' alt='Excluir'></a> " +
                          "<a href='#'><img src='imagens/stock_print-16.gif' border='0' alt='Imprimir'></a>";
    } else {
        menuHorizontal = "<a href='javascript:history.back()'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'></a> " +
                          "<a href='#'><img src='imagens/stock_print-16.gif' border='0' alt='Imprimir'></a>";
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
                <td class="Title">
                    <table width='549' cellspacing='0' cellpadding='0' border='0'>
                        <tr>
                            <td class="Title" width="420">Arquivos - Visualizar</td>
                            <td width="129" align="right">
                                <%= menuHorizontal %>
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
                            <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do Arquivo:</td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18" width="10">&nbsp;</td>
                            <td class="Title" width="170">N&deg; de Controle:</td>
                            <td width="10">&nbsp;</td>
                            <td width="359"><%= arquivo.getId() %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Tipo de Arquivo:</td>
                            <td>&nbsp;</td>
                            <td><%= arquivo.getArquivoTipo().getDescricao() %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Descrição:</td>
                            <td>&nbsp;</td>
                            <td><%= arquivo.getDescricao() %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Importado Por:</td>
                            <td>&nbsp;</td>
                            <td><%= arquivo.getUsuarioInclusao().getNomeUsuario() + " " + arquivo.getUsuarioInclusao().getSobrenomeUsuario() %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Data de Importação:</td>
                            <td>&nbsp;</td>
                            <td><%= dataInclusao %></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td align="right">
                    <a href="getArchive?tipo=1&file=<%= arquivo.getFileName() %>">Baixar Arquivo <img src="imagens/stock_down-16.gif" border="0" alt="Baixar Arquivo" align="top"></a>
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