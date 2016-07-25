<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--@page contentType="text/html" pageEncoding="UTF-8"--%>

<%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="br.com.consorciosdf.intranet.util.DataUtil" %>

<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="usuarioAutor" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioDest" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

<%

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            DataUtil dataUtil = new DataUtil();
            Date dataNow = new Date();
            int userPerfil = 0;

            if (session.getAttribute("userPerfil") != null) {
                userPerfil = Integer.parseInt((String) session.getAttribute("userPerfil"));
            }

            String userMatricula = "";

            if (session.getAttribute("userMatricula") != null) {
                userMatricula = (String) session.getAttribute("userMatricula");
            }

            String userFullName = "";

            if (session.getAttribute("userName") != null && session.getAttribute("userLastname") != null) {
                userFullName = (String) session.getAttribute("userName");
                userFullName += " ";
                userFullName += (String) session.getAttribute("userLastname");
            }



            if ((request.getParameter("id")) != null) {
                int id = Integer.parseInt(request.getParameter("id"));

                ManterOSControl manterOSControl = new ManterOSControl();
                os = manterOSControl.recuperarOS(id);
            }

            String sucessAdd = "";
            String sucessAddClass = "Error";
            String msgSucess = "";

            if ((request.getParameter("accept")) != null) {
                if (request.getParameter("accept").equals("1")) {
                    sucessAdd = "Houve um problema na transação. Não foi possível assumir a OS.";
                } else if (request.getParameter("accept").equals("2")) {
                    sucessAdd = "Ordem de Serviço assumida com sucesso.";
                    sucessAddClass = "Sucess";
                }

                msgSucess = ""
                        + "<tr>"
                        + "<td height='20' align='center' class='" + sucessAddClass + "'>" + sucessAdd + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td height='10'></td>"
                        + "</tr>";
            }

            if ((request.getParameter("sucess")) != null) {
                if (request.getParameter("sucess").equals("1")) {
                    sucessAdd = "Favor digitar os campos corretamente.";
                } else if (request.getParameter("sucess").equals("2")) {
                    sucessAdd = "Houve um problema na transação.";
                } else if (request.getParameter("sucess").equals("3")) {
                    sucessAdd = "Ordem de Serviço fechada com sucesso.";
                    sucessAddClass = "Sucess";
                }

                msgSucess = ""
                        + "<tr>"
                        + "<td height='20' align='center' class='" + sucessAddClass + "'>" + sucessAdd + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td height='10'></td>"
                        + "</tr>";
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

    </head>

    <body>
        <table width="690" border="0" cellspacing="0" cellpadding="0">

            <tr>
                <td width="10">&nbsp;</td>
                <td width="670" height="180" colspan="2" valign="top">

                    <%
                                int idOs = 0;

                                String userAutor = "";
                                String userDest = "";
                                String nomeEquipamento = "";
                                String endEquipamento = "";
                                String numSerie = "";
                                String numUPR = "";
                                String numCamera = "";
                                String numFlash = "";
                                String descDefeito = "";
                                String descObservacao = "";
                                String descReparo = "";
                                String descFechamento = "";
                                String descServExec = "";
                                String dataAnomalia = "";
                                String dataAbertura = "";
                                String dataServico = "";
                                String dataFechamento = "";
                                String difAnomaliaAbertura = "";
                                String difAberturaServico = "";
                                String difServicoFechamento = "";
                                String difAnomaliaFechamento = "";
                                String status = "";
                                String statusFechamento = "";
                                String statusStyle = "";
                                String prioridadeOS = "";
                                String prioridadeOSStyle = "";

                                String styleButtonEnabled = "class='Button'";
                                String styleButtonDisabled = "class='ButtonDisabled' disabled='disabled'";

                                String buttonAccept = styleButtonEnabled;
                                String buttonEdit = styleButtonEnabled;
                                String buttonClose = styleButtonEnabled;

                                if (os != null) {
                                    checkEquipamento = os.getCheckEquipamento();
                                    equipamento = os.getEquipamento();
                                    usuarioAutor = os.getUsuarioAutor();
                                    usuarioDest = os.getUsuarioDest();

                                    idOs = os.getId();
                                    userAutor = usuarioAutor.getNomeUsuario() + " "
                                            + usuarioAutor.getSobrenomeUsuario();
                                    nomeEquipamento = equipamento.getNomeEquipamento();
                                    endEquipamento = equipamento.getEnderecoLogradouroEquipamento();

                                    if (os.getDataAnomalia() != null) {
                                        dataAnomalia = dateFormat.format(os.getDataAnomalia());
                                    }

                                    if (os.getDataAbertura() != null) {
                                        dataAbertura = dateFormat.format(os.getDataAbertura());
                                        difAnomaliaAbertura = dataUtil.diferecaData(os.getDataAnomalia(), os.getDataAbertura());
                                    }



                                    if (os.getDataServico() != null) {
                                        dataServico = dateFormat.format(os.getDataServico());
                                        difAberturaServico = dataUtil.diferecaData(os.getDataAbertura(), os.getDataServico());
                                    }

                                    if (os.getDataFechamento() != null) {
                                        dataFechamento = dateFormat.format(os.getDataFechamento());
                                        difServicoFechamento = dataUtil.diferecaData(os.getDataServico(), os.getDataFechamento());
                                        difAnomaliaFechamento = dataUtil.diferecaData(os.getDataAnomalia(), os.getDataFechamento());
                                    }

                                    if (usuarioDest != null) {
                                        userDest = usuarioDest.getNomeUsuario() + " " + usuarioDest.getSobrenomeUsuario();
                                        buttonAccept = styleButtonDisabled;

                                        if (!usuarioDest.getMatriculaUsuario().equals(userMatricula)) {
                                            buttonClose = styleButtonDisabled;
                                        }
                                    } else {
                                        buttonClose = styleButtonDisabled;
                                    }

                                    if (userPerfil > 2) {
                                        buttonEdit = styleButtonDisabled;
                                    }

                                    if (equipamento.getNumSerie() != 0) {
                                        numSerie = String.valueOf(equipamento.getNumSerie());
                                    }

                                    if (equipamento.getNumUPR() != 0) {
                                        numUPR = String.valueOf(equipamento.getNumUPR());
                                    }

                                    if (equipamento.getNumCamera() != 0) {
                                        numCamera = String.valueOf(equipamento.getNumCamera());
                                    }

                                    if (equipamento.getNumFlash() != null) {
                                        numFlash = equipamento.getNumFlash();
                                    }

                                    if (os.getDescDefeito() != null) {
                                        descDefeito = os.getDescDefeito();
                                    }

                                    if (os.getDescObservacao() != null) {
                                        descObservacao = os.getDescObservacao();
                                    }

                                    if (os.getDescReparo() != null) {
                                        descReparo = os.getDescReparo();
                                    }

                                    if (os.getDescServicoExecutado() != null) {
                                        descServExec = os.getDescServicoExecutado();
                                    }
                                    if (os.getDescFechamento() != null) {
                                        descFechamento = os.getDescFechamento();
                                    }
                                    if (os.getStatus() != null) {
                                        status = os.getStatus();

                                        if (status.equals("ab")) {
                                            statusFechamento = "ABERTO";
                                            statusStyle = "OSOpen";
                                        } else if (status.equals("an")) {
                                            statusFechamento = "ANÁLISE";
                                            statusStyle = "OSWorking";
                                        } else if (status.equals("ag")) {
                                            statusFechamento = "AGUARDANDO";
                                            statusStyle = "OSStandby";
                                        } else if (status.equals("fc")) {
                                            statusFechamento = "FECHADO";
                                            statusStyle = "OSClose";

                                            buttonEdit = styleButtonDisabled;
                                            buttonClose = styleButtonDisabled;
                                        }
                                    }
                                    if (os.getPrioridade() >= 0) {
                                        prioridadeOS = "";
                                        if (os.getPrioridade() == 0) {
                                            prioridadeOS = "Alta";
                                            prioridadeOSStyle = "OsPrioridadeAlta";
                                        } else if (os.getPrioridade() == 1) {
                                            prioridadeOS = "Média";
                                            prioridadeOSStyle = "OsPrioridadeMedia";
                                        } else if (os.getPrioridade() == 2) {
                                            prioridadeOS = "Baixa";
                                            prioridadeOSStyle = "OsPrioridadeBaixa";
                                        }

                                    }

                                    /*if (os.getDataFechamento() == null) {
                                    statusFechamento = "ABERTO";
                                    statusStyle = "OSOpen";
                                    } else {
                                    dataFechamento = dateFormat.format(os.getDataFechamento());
                                    statusFechamento = "FECHADO";
                                    statusStyle = "OSClose";

                                    buttonEdit = styleButtonDisabled;
                                    buttonClose = styleButtonDisabled;
                                    }*/
                                }
                    %>

                    <table width="670" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td align="center">
                                <img src="imagens/logo_sdf_empresas.jpg">
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td align="right">
                                <a href="#" onclick="window.print()">
                                    <img src="imagens/stock_print-16.gif" border="0">
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados da OS - Detalhes:</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de OS:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width=""><%= idOs + os.getTipoOS().toUpperCase()%></td>
                                                    <td class="Title" width="" align="right">Status:</td>
                                                    <td class="<%= statusStyle%>" align="center"> <%= statusFechamento%></td>
                                                    <td class="Title" width="" align="right">Prioridade:</td>
                                                    <td class="<%=prioridadeOSStyle%>" align="center"> <%=prioridadeOS%></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Solicitante:</td>
                                        <td>&nbsp;</td>
                                        <td><%= userAutor%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Técnico:</td>
                                        <td>&nbsp;</td>
                                        <td><%= userDest%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Equipamento:</td>
                                        <td>&nbsp;</td>
                                        <td><%= nomeEquipamento + " (" + endEquipamento + ")"%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Série:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numSerie%></td>
                                                    <td width="100"></td>
                                                    <td class="Title" width="110">N&deg; de HD:</td>
                                                    <td>00000000</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Embarcada:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numUPR%></td>
                                                    <td width="100"></td>
                                                    <td class="Title" width="110">N&deg; de Fonte:</td>
                                                    <td>00000000</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Câmera:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numCamera%></td>
                                                    <td width="100"></td>
                                                    <td class="Title" width="110">Outros N&deg;s (1):</td>
                                                    <td>00000000</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Flash:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numFlash%></td>
                                                    <td width="100"></td>
                                                    <td class="Title" width="110">Outros N&deg;s (2):</td>
                                                    <td>00000000</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18"></td>
                                        <td class="Title">Data da Anomalia:</td>
                                        <td></td>
                                        <td><%= dataAnomalia%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18"></td>
                                        <td class="Title">Data de Abertura:</td>
                                        <td></td>
                                        <td><%= dataAbertura%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18"></td>
                                        <td class="Title">Data do Serviço:</td>
                                        <td></td>
                                        <td><%= dataServico%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18"></td>
                                        <td class="Title">Data de Fechamento:</td>
                                        <td></td>
                                        <td><%= dataFechamento%></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td colspan="3">
                                            <table border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                                                <tr>
                                                    <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                                    <td colspan="2" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Tempo da OS:</td>
                                                </tr>
                                                <tr class="TableResultBody1">
                                                    <td height="18" width="11"></td>
                                                    <td class="Title" width="175">
                                                        Anomalia x Abertura:
                                                    </td>
                                                    <td width="484px">
                                                        <%=difAnomaliaAbertura%>
                                                    </td>
                                                </tr>
                                                <tr class="TableResultBody2">
                                                    <td height="18"></td>
                                                    <td class="Title">
                                                        Abertura x Serviço:
                                                    </td>

                                                    <td>
                                                        <%=difAberturaServico%>

                                                    </td>
                                                </tr>
                                                <tr class="TableResultBody1">
                                                    <td height="18"></td>
                                                    <td class="Title">Serviço x Fechamento:</td>

                                                    <td><%= difServicoFechamento%></td>
                                                </tr>
                                                <tr class="TableResultBody2">
                                                    <td height="18"></td>
                                                    <td class="Title" width="175">Anomalia x Fechamento:</td>

                                                    <td><%= difAnomaliaFechamento%></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Defeito:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descDefeito%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="150"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Observações:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descObservacao%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="200"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Materiais Utilizados:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descReparo%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="100"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Serviços Executados:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descServExec%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="100"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Fechamento:</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <%= descFechamento%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" height="100"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td colspan="3" height="10"></td>
                                    </tr>
                                    <tr>
                                        <td width="10"></td>
                                        <td align="right">
                                            <b>Impresso às <%= dateFormat.format(dataNow)%> por <%= userFullName%>.</b>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" height="10"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="50"></td>
                        </tr>
                       <%-- <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados da OS - Verificação Obrigatória:</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de OS:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= idOs + os.getTipoOS().toUpperCase()%></td>
                                                    <td width="100"></td>
                                                    <td class="Title" width="110">Status da OS:</td>
                                                    <td class="<%= statusStyle%>"><%= statusFechamento%></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Solicitante:</td>
                                        <td>&nbsp;</td>
                                        <td><%= userAutor%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Técnico:</td>
                                        <td>&nbsp;</td>
                                        <td><%= userDest%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Equipamento:</td>
                                        <td>&nbsp;</td>
                                        <td><%= nomeEquipamento + " (" + endEquipamento + ")"%></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="4" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="4" align="center" class="Title" style='border-bottom: 1px solid #000000;'>DADOS ATUAIS</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Série:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numSerie%></td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">N&deg; de HD:</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Embarcada:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numUPR%></td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">N&deg; de Fonte:</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Câmera:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numCamera%></td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">Outros N&deg;s (1):</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="19" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Flash:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100"><%= numFlash%></td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">Outros N&deg;s (2):</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="4" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="4" align="center" class="Title" style='border-bottom: 1px solid #000000;'>ALTERAR PARA:</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Série:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100">&nbsp;</td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">N&deg; de HD:</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Embarcada:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100">&nbsp;</td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">N&deg; de Fonte:</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Câmera:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100">&nbsp;</td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">Outros N&deg;s (1):</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td class="Title" width="140">N&deg; de Flash:</td>
                                        <td width="10">&nbsp;</td>
                                        <td>
                                            <table width="480" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="100">&nbsp;</td>
                                                    <td width="100">&nbsp;</td>
                                                    <td class="Title" width="110">Outros N&deg;s (2):</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="4" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Data de Abertura:</td>
                                        <td>&nbsp;</td>
                                        <td><%= dataAbertura%></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Data do Serviço:</td>
                                        <td>&nbsp;</td>
                                        <td><%= dataAbertura%></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18">&nbsp;</td>
                                        <td class="Title">Data de Fechamento:</td>
                                        <td>&nbsp;</td>
                                        <td><%= dataFechamento%></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>--%>
                        <tr>
                            <td height="10"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="670" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                        <td class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Verificação Obrigatória:</td>
                                        <td class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>REDE</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Situação:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Reiniciado
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">VNC:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Reconfigurado
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Lotes:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Enviando
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Coletado
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Embarcada:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Reiniciada
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="3" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>CÂMERA</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Enquadramento:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Refeito
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Foco:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Refeito
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Configuração:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Refeito
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Acrilico:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Limpo
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="3" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>FLASH</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Módulo:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Trocado
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">&nbsp;</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="3" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>LÂMPADAS</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Faixa 1:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Trocada
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Faixa 2:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Trocada
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Faixa 3:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Trocada
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">&nbsp;</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="3" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>TENSÃO</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Rede:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="180">___________________________</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Fonte:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="180">___________________________</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Nobreak:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="180">___________________________</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Nobreak carga:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="180">___________________________</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="3" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>GABINETE</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Limpeza:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Limpo
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Fechadura:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Defeito
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Poste</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="120">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Fazer Manutenção
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">&nbsp;</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="18" colspan="3" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;'>SOFTWARE</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Captura:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Reiniciado
                                                    </td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Parâmetros:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;Alterado
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Lt Estatística:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td width="90">____________</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">Lt Infrações:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="90">
                                                        <img src="imagens/checkbox.gif" style="vertical-align:middle">&nbsp;OK
                                                    </td>
                                                    <td>____________</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td>
                                            <table width="650" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="Title" width="120">Banco de Dados:</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td width="180">________________________ GB</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td class="Title" width="100">&nbsp;</td>
                                                    <td width="10">&nbsp;</td>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr>
                                        <td height="20" colspan="3" style='border-top: 1px solid #000000;'>&nbsp;</td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>SERVIÇOS DO WINDOWS</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td height="100">&nbsp;</td>
                                        <td width="10"></td>
                                    </tr>
                                    <tr class="TableResultBody2">
                                        <td height="30" colspan="3" align="center" class="Title" style='border-bottom: 1px solid #000000;border-top: 1px solid #000000;'>VISUALIZADOR DE EVENTOS</td>
                                    </tr>
                                    <tr class="TableResultBody1">
                                        <td height="18" width="10">&nbsp;</td>
                                        <td height="100">&nbsp;</td>
                                        <td width="10"></td>
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