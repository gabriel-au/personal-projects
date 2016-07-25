<%@ page import="br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.CheckEquipamento" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Equipamento" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Procedimento" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.StatusProcedimento" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="procedimento" class="br.com.consorciosdf.intranet.modelo.Procedimento" scope="request"/>
<jsp:useBean id="statusProcedimentoEnt" class="br.com.consorciosdf.intranet.modelo.StatusProcedimento" scope="request"/>
<jsp:useBean id="statusProcedimentoSai" class="br.com.consorciosdf.intranet.modelo.StatusProcedimento" scope="request"/>

<%
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    List list = null;
    
    if ((request.getParameter("idCheck")) != null) {
        int idCheck = Integer.parseInt(request.getParameter("idCheck"));
        
        ManterCheckEquipamentoControl manterCheckEquipamentoControl = new ManterCheckEquipamentoControl();
        list = manterCheckEquipamentoControl.recuperarChecagem(idCheck);
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
        
        <%
            int idChecagem = 0;
            int numSerie = 0;
            int numUPR = 0;
            int numCamera = 0;
            
            String tecnico = "";
            String nomeEquipamento = "";
            String endEquipamento = "";
            String dataChecagemInicial = "";
            String dataChecagemFinal = "";
            String numFlash = "";
            String execColeta = "";
            String observacao = "";
            
            if (list.size() > 0) {
                CheckEquipamento checkEquipamentoTitle = (CheckEquipamento) list.get(0);
                idChecagem = checkEquipamentoTitle.getId();
                tecnico = checkEquipamentoTitle.getUsuario().getNomeUsuario() + " " +
                        checkEquipamentoTitle.getUsuario().getSobrenomeUsuario();
                nomeEquipamento = checkEquipamentoTitle.getEquipamento().getNomeEquipamento();
                endEquipamento = checkEquipamentoTitle.getEquipamento().getEnderecoLogradouroEquipamento();
                numSerie = checkEquipamentoTitle.getNumSerie();
                numUPR = checkEquipamentoTitle.getNumUPR();
                numCamera = checkEquipamentoTitle.getNumCamera();
                numFlash = checkEquipamentoTitle.getNumFlash();
                execColeta = checkEquipamentoTitle.getExecColeta();
                observacao = checkEquipamentoTitle.getObservacao();
                dataChecagemInicial = dateFormat.format(checkEquipamentoTitle.getDataInicio());
                dataChecagemFinal = dateFormat.format(checkEquipamentoTitle.getDataFim());
                
                if (execColeta.trim().equals("s")) {
                    execColeta = "Sim";
                } else if (execColeta.trim().equals("n")) {
                    execColeta = "Não";
                }
            }
        %>
        
        <table width="549" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="Title">Checagem de Equipamento - Visualizar</td>
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
                            <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados de Checagem:</td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18" width="10">&nbsp;</td>
                            <td class="Title" width="120">N&deg; de Checagem:</td>
                            <td width="10">&nbsp;</td>
                            <td><%= idChecagem %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Técnico:</td>
                            <td>&nbsp;</td>
                            <td><%= tecnico %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">Equipamento:</td>
                            <td>&nbsp;</td>
                            <td><%= nomeEquipamento + " (" + endEquipamento + ")" %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">N&deg; de série:</td>
                            <td>&nbsp;</td>
                            <td><%= numSerie %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">N&deg; de UPR:</td>
                            <td>&nbsp;</td>
                            <td><%= numUPR %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18">&nbsp;</td>
                            <td class="Title">N&deg; da Câmera:</td>
                            <td>&nbsp;</td>
                            <td><%= numCamera %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18">&nbsp;</td>
                            <td class="Title">N&deg; do Flash:</td>
                            <td>&nbsp;</td>
                            <td><%= numFlash %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18"></td>
                            <td class="Title">Data Abertura:</td>
                            <td></td>
                            <td><%= dataChecagemInicial %></td>
                        </tr>
                        <tr class="TableResultBody1">
                            <td height="18"></td>
                            <td class="Title">Data Fechamento:</td>
                            <td></td>
                            <td><%= dataChecagemFinal %></td>
                        </tr>
                        <tr class="TableResultBody2">
                            <td height="18"></td>
                            <td class="Title">Executou Coleta:</td>
                            <td></td>
                            <td><%= execColeta %></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td height="10"></td>
            </tr>
            <tr>
                <td>
                    <table width="549" border="0" cellspacing="0" cellpadding="0" style='border: 1px solid #000000;'>
                        <tr>
                            <td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>
                            <td width="329" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Procedimento</td>
                            <td width="100" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Entrada</td>
                            <td width="100" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Saída</td>
                        </tr>
                        
                        <%
                    
                        if (list != null) {
                            String tableStyle = "TableResultBody1";
                            int countResult = 0;
                            
                            for (int i=0; i < list.size(); i++) {
                                checkEquipamento = (CheckEquipamento) list.get(i);
                                procedimento = checkEquipamento.getProcedimento();
                                statusProcedimentoEnt = checkEquipamento.getStatusProcedimentoEnt();
                                statusProcedimentoSai = checkEquipamento.getStatusProcedimentoSai();
                                
                                countResult++;
                                
                                if (countResult % 2 == 0) {
                                    tableStyle = "TableResultBody2";
                                } else if (countResult % 2 == 1) {
                                    tableStyle = "TableResultBody1";
                                }
                                
                                %>
                                
                                <tr>
                                    <td height='18' class='<%= tableStyle %>' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                                    <td class='<%= tableStyle %>'><%= procedimento.getNomeProcedimento() %></td>
                                    <td class='<%= tableStyle %>'><%= statusProcedimentoEnt.getDescricaoStatusProcedimento() %></td>
                                    <td class='<%= tableStyle %>'><%= statusProcedimentoSai.getDescricaoStatusProcedimento() %></td>
                                </tr>
                                
                                <%
                            }
                        }
                    
                    %>
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
                            <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Observações:</td>
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