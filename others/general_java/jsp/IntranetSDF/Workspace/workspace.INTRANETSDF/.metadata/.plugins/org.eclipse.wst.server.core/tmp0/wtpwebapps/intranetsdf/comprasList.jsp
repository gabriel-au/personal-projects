<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterComprasControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Compras" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.ComprasArquivo" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.ComprasArquivoTipo" %>

<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="compras" class="br.com.consorciosdf.intranet.modelo.Compras" scope="request"/>

<!-- include de paginação de resultados - cabeçalho -->
<%@ include file="includes/pageNavigatorHeader.jsp"%>
<!-- fim do include de paginação de resultados - cabeçalho -->

<%
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    String messageSucess = "";
    String messageSucessText = "";
    String messageSucessClass = "";
    
    List list = null;
    ManterComprasControl manterComprasControl = new ManterComprasControl();
    list = manterComprasControl.recuperarComprasPag(paginaInicial, qtdRegistros, orderBy, order);
    
    if (list != null) {
        if (list.size() > 0) {
            compras = (Compras) list.get(0);
            paginacao = compras.getPaginacao();
        }
    }
    
    if ((request.getParameter("sucess")) != null) {
        if (request.getParameter("sucess").equals("1")) {
            messageSucessText = "Arquivo excluído com sucesso.";
            messageSucessClass = "Sucess";
        } else if (request.getParameter("sucess").equals("2")) {
            messageSucessText = "Houve um problema na transação.";
            messageSucessClass = "Error";
        }
        
        messageSucess = "<tr>" +
                        "<td class='" + messageSucessClass + "' align='center'>" + messageSucessText + "</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td height='10'>" +
                        "</td>" +
                        "</tr>";
    }
    
%>

<!-- include de paginação de resultados - rodapé -->
<%@ include file="includes/pageNavigatorFooter.jsp"%>
<!-- fim do include de paginação de resultados - rodapé -->

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
                            <td class="Title" width="420">Processo de Compra - Listagem (<%= paginacao.getResultSize() %> Registros - Página <%= pageNumber %> de <%= paginacao.getPageNavigator() %>)</td>
                            <td width="129" align="right">
                                <a href="javascript:history.back()"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
                                <a href="#"><img src="imagens/stock_search-16.gif" border="0" alt="Procurar"></a>
                                <a href="comprasAdd.jsp"><img src="imagens/stock_new-16.gif" border="0" alt="Novo"></a>
                                <a href="javascript:window.location.reload()"><img src="imagens/stock_refresh-16.gif" border="0" alt="Atualizar"></a>
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
            <%= messageSucess %>
            <tr>
                <td>
                    <table width='549' cellspacing='0' cellpadding='0' border='0' style='border: 1px solid #000000;'>
                        <tr>
                            <td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>
                            <td width='90' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=tipo" class="LinkTitle">Nº</a></td>
                            <td width='300' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=descricao" class="LinkTitle">Descrição</a></td>
                            <td width='9' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                            <td width='130' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Data de Inclusão</td>
                        </tr>
                        <%
                    
                            if (list != null) {
                                String tableStyle = "TableResultBody1";
                                int countResult = 0;

                                for (int i=0; i < list.size(); i++) {
                                    compras = (Compras) list.get(i);
                                    
                                    countResult++;

                                    if (countResult % 2 == 0) {
                                        tableStyle = "TableResultBody2";
                                    } else if (countResult % 2 == 1) {
                                        tableStyle = "TableResultBody1";
                                    }
                                    
                                    %>

                                    <tr>
                                        <td height='16' class='<%= tableStyle %>' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                                        <td class='<%= tableStyle %>'><a href="comprasView.jsp?id=<%= compras.getId() %>"><%= compras.getId() %></a></td>
                                        <td class='<%= tableStyle %>'><a href="comprasView.jsp?id=<%= compras.getId() %>"><%= compras.getDescricao() %></a></td>
                                        <td class='<%= tableStyle %>'>&nbsp;</td>
                                        <td class='<%= tableStyle %>'><a href="comprasView.jsp?id=<%= compras.getId() %>"><%= dateFormat.format(compras.getDataInclusao()) %></a></td>
                                    </tr>

                                    <%
                                }
                            }

                        %>
                        <tr>
                            <td colspan="5" height="5"></td>
                        </tr>
                        <tr>
                            <td colspan="5" align="center"><%= pageViewNavigator %></td>
                        </tr>
                        <tr>
                            <td colspan="5" height="5"></td>
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