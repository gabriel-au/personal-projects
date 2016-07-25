<%@ page import="br.com.consorciosdf.intranet.controle.ManterReportControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>

<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="relatorio" class="br.com.consorciosdf.intranet.modelo.Relatorio" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="tecnico" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

<%
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    int idReport = 0;
    
    String nomeEquipamento = "";
    String enderecoEquipamento = "";
    String observacao = "";
    String dtInicial = "";
    String dtFinal = "";
    String nomeTecnico = "";
    
    String relatorioTipoId = "";
    String relatorioTipoNome = "";
    
    if ((request.getParameter("relatorioTipoId")) != null &&
            (request.getParameter("relatorioTipoNome")) != null) {

        relatorioTipoId = request.getParameter("relatorioTipoId");
        relatorioTipoNome = request.getParameter("relatorioTipoNome");
        
    }
    
    if ((request.getParameter("id")) != null) {
        int id = Integer.parseInt(request.getParameter("id"));
        idReport = id;
        
        ManterReportControl manterReportControl = new ManterReportControl();
        relatorio = manterReportControl.recuperarReport(id);
        
        if (relatorio != null) {
            equipamento = relatorio.getEquipamento();
            tecnico = relatorio.getTecnico();
            
            if (equipamento != null) {
                nomeEquipamento = equipamento.getNomeEquipamento();
                enderecoEquipamento = equipamento.getEnderecoLogradouroEquipamento() + " - " +
                        equipamento.getEnderecoCidadeEquipamento() + "/" +
                        equipamento.getEnderecoEstadoEquipamento();
            }
            
            if (tecnico != null) {
                if (tecnico.getNomeUsuario() != null) {
                    nomeTecnico = tecnico.getNomeUsuario() + " " + tecnico.getSobrenomeUsuario();
                }
            }
            
            if (relatorio.getObservacao() != null) {
                observacao = relatorio.getObservacao();
            }
            
            if (relatorio.getDataInicial() != null) {
                dtInicial = dateFormat.format(relatorio.getDataInicial());
            }
            
            if (relatorio.getDataFinal() != null) {
                dtFinal = dateFormat.format(relatorio.getDataFinal());
            }
            
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
        <table width="549" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="Title"><%= relatorioTipoNome %> - Visualizar</td>
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
                            <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do Relatório:</td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18" width="10">&nbsp;</td>
                            <td class="Title" width="120">N&deg; de Controle:</td>
                            <td width="10">&nbsp;</td>
                            <td>
                                <table width="400" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td><%= idReport %></td>
                                        <td width="110"></td>
                                        <td class="Title" width="150:">Tipo de Relatório:</td>
                                        <td width="100"><%= relatorioTipoNome %></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Equipamento:</td>
                            <td>&nbsp;</td>
                            <td><%= nomeEquipamento + " (" + enderecoEquipamento + ")" %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Técnico:</td>
                            <td>&nbsp;</td>
                            <td><%= nomeTecnico %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Data Inicial:</td>
                            <td>&nbsp;</td>
                            <td><%= dtInicial %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Data Final:</td>
                            <td>&nbsp;</td>
                            <td><%= dtFinal %></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td>
                    <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                        <tr>
                            <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                            <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Observação:</td>
                        </tr>
                        <tr>
                            <td colspan="2" height="5"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <textarea name='observacao' disabled='disabled' class="TextAreaDisabled" style='border: 0px solid #000000; background-color:#FFFFFF; width:539px; height: 100px;'><%= observacao %></textarea>
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