<%@ page import="br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="checkEquipamentoVerify" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

<!-- include de paginação de resultados - cabeçalho -->
<%@ include file="includes/pageNavigatorHeader.jsp"%>
<!-- fim do include de paginação de resultados - cabeçalho -->

<%
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    List list = null;
    ManterCheckEquipamentoControl manterCheckEquipamentoControl = new ManterCheckEquipamentoControl();
    list = manterCheckEquipamentoControl.recuperarChecagensPag(paginaInicial, qtdRegistros, orderBy, order);
    
    if (list != null) {
        if (list.size() > 0) {
            checkEquipamentoVerify = (CheckEquipamento) list.get(0);
            paginacao = checkEquipamentoVerify.getPaginacao();
        }
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
                            <td class="Title" width="420">Manut. Periódica - Listagem (<%= paginacao.getResultSize() %> Registros - Página <%= pageNumber %> de <%= paginacao.getPageNavigator() %>)</td>
                            <td width="129" align="right">
                                <a href="javascript:history.back()"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
                                <a href="#"><img src="imagens/stock_search-16.gif" border="0" alt="Procurar"></a>
                                <a href="checkListEquipamentoSelect.jsp"><img src="imagens/stock_new-16.gif" border="0" alt="Novo"></a>
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
            <tr>
                <td>
                    <table width='549' cellspacing='0' cellpadding='0' border='0' style='border: 1px solid #000000;'>
						<tr>
							<td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>
                                                        <td width='50' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=id" class="LinkTitle">N&deg;.</a></td>
                                                        <td width='70' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=equipamento" class="LinkTitle">Equip.</a></td>
                                                        <td width='129' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=tecnico" class="LinkTitle">Técnico</a></td>
                                                        <td width='140' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=data_inicial" class="LinkTitle">Data/Hora Inicial</a></td>
                                                        <td width='140' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=data_final" class="LinkTitle">Data/Hora Final</a></td>
						</tr>
                    <%
                    
                        if (list != null) {
                            String tableStyle = "TableResultBody1";
                            int countResult = 0;
                            
                            for (int i=0; i < list.size(); i++) {
                                checkEquipamento = (CheckEquipamento) list.get(i);
                                equipamento = checkEquipamento.getEquipamento();
                                usuario = checkEquipamento.getUsuario();
                                
                                countResult++;
                                
                                if (countResult % 2 == 0) {
                                    tableStyle = "TableResultBody2";
                                } else if (countResult % 2 == 1) {
                                    tableStyle = "TableResultBody1";
                                }
                                
                                %>
                                
                                <tr>
                                    <td height='16' class='<%= tableStyle %>' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                                    <td class='<%= tableStyle %>'><a href="checkListView.jsp?idCheck=<%= checkEquipamento.getId() %>"><%= checkEquipamento.getId() %></a></td>
                                    <td class='<%= tableStyle %>'><a href="checkListView.jsp?idCheck=<%= checkEquipamento.getId() %>"><%= equipamento.getNomeEquipamento() %></a></td>
                                    <td class='<%= tableStyle %>'><a href="checkListView.jsp?idCheck=<%= checkEquipamento.getId() %>"><%= usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario() %></a></td>
                                    <td class='<%= tableStyle %>'><a href="checkListView.jsp?idCheck=<%= checkEquipamento.getId() %>"><%= dateFormat.format(checkEquipamento.getDataInicio()) %></a></td>
                                    <td class='<%= tableStyle %>'><a href="checkListView.jsp?idCheck=<%= checkEquipamento.getId() %>"><%= dateFormat.format(checkEquipamento.getDataFim()) %></a></td>
                                </tr>
                                
                                <%
                            }
                        }
                    
                    %>
                        <tr>
                            <td colspan="6" height="5"></td>
                        </tr>
                        <tr>
                            <td colspan="6" align="center"><%= pageViewNavigator %></td>
                        </tr>
                        <tr>
                            <td colspan="6" height="5"></td>
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