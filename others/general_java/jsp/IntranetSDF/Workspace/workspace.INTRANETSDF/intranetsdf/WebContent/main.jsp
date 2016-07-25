<%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>                                                                                                                                       
<%@ page import="br.com.consorciosdf.intranet.controle.ManterEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterReportControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="usuarioAtual" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

<jsp:useBean id="contadorMain" class="br.com.consorciosdf.intranet.modelo.ContadorMain" scope="request"/>

<%

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");

            ManterOSControl manterOSControl = new ManterOSControl();
            ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
            ManterReportControl manterReportControl = new ManterReportControl();

            Calendar dataNow = Calendar.getInstance();
            int horaNow = dataNow.get(Calendar.HOUR_OF_DAY);
            int month = dataNow.get(Calendar.MONTH) + 1;
            int year = dataNow.get(Calendar.YEAR);

            int qtdRisAtivos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(true, "RIS");
            int qtdRisInativos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(false, "RIS");
            int qtdRedAtivos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(true, "RED");
            int qtdRedInativos = manterEquipamentoControl.recuperarQuantidadeEquipamentos(false, "RED");

            List qtdOSAfericaoAbertas = manterOSControl.recuperarQuantidadeOS(0, "a", month, year);
            List qtdOSCorretivaAbertas = manterOSControl.recuperarQuantidadeOS(0, "c", month, year);
            List qtdOSPreventivaAbertas = manterOSControl.recuperarQuantidadeOS(0, "p", month, year);

            List qtdOSAfericaoAbertasAtraso = manterOSControl.recuperarQuantidadeOS(1, "a", month, year);
            List qtdOSCorretivaAbertasAtraso = manterOSControl.recuperarQuantidadeOS(1, "c", month, year);
            List qtdOSPreventivaAbertasAtraso = manterOSControl.recuperarQuantidadeOS(1, "p", month, year);

            List qtdOSAfericaoFechadas = manterOSControl.recuperarQuantidadeOS(2, "a", month, year);
            List qtdOSCorretivaFechadas = manterOSControl.recuperarQuantidadeOS(2, "c", month, year);
            List qtdOSPreventivaFechadas = manterOSControl.recuperarQuantidadeOS(2, "p", month, year);

            List qtdReportColetaAberta = manterReportControl.recuperarQuantidadeReport(1, 0, month, year);
            List qtdReportColetaFechada = manterReportControl.recuperarQuantidadeReport(1, 1, month, year);
            List qtdReportCorretivaAberta = manterReportControl.recuperarQuantidadeReport(2, 0, month, year);
            List qtdReportCorretivaFechada = manterReportControl.recuperarQuantidadeReport(2, 1, month, year);
            List qtdReportDTSAberta = manterReportControl.recuperarQuantidadeReport(3, 0, month, year);
            List qtdReportDTSFechada = manterReportControl.recuperarQuantidadeReport(3, 1, month, year);

            String saudacao = "";

            if (horaNow >= 0 && horaNow <= 11) {
                saudacao = "Bom dia, ";
            } else if (horaNow >= 12 && horaNow <= 17) {
                saudacao = "Boa tarde, ";
            } else if (horaNow >= 18 && horaNow <= 23) {
                saudacao = "Boa noite, ";
            }

            String nomeUsuario = "";
            nomeUsuario = session.getAttribute("userName") + " " + session.getAttribute("userLastname");

            saudacao += nomeUsuario;
            ManterUserControl userControl = new ManterUserControl();

            usuarioAtual.setUser((String) session.getAttribute("user"));
            usuarioAtual.setMatriculaUsuario((String) session.getAttribute("userMatricula"));

            usuarioAtual = userControl.viewUser(usuarioAtual);
            ManterOSControl osControl = new ManterOSControl();

%>

<!-- include de verificação do usuário -->
<%@ include file="includes/sessionVerify.jsp" %>
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
        <%if (usuarioAtual.getPerfilUsuario() < 3 || usuarioAtual.getPerfilUsuario() > 5) {%>
        <table width="779" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="220">&nbsp;</td>
                <td width="549" colspan="2" valign="top">
                    <table width="549" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table width="549" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td class="Title" width="300">
                                            <%= saudacao%>
                                        </td>
                                        <td class="Title" align="right">
                                            <%= dateFormat.format(new Date())%>
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
                                <table width="549" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="264" valign="top">
                                            <table width="261" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                                <tr>
                                                    <td colspan="7" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>OS - Corretiva</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td width="5"></td>
                                                    <td width="15"></td>
                                                    <td></td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td width="5"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleGreenBold">ABERTA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleRedBold">ABERTA(S)*:</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaAbertasAtraso.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaAbertasAtraso.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleBlueBold">FECHADA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSCorretivaFechadas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSCorretivaFechadas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="21"></td>
                                        <td width="264" valign="top">
                                            <table width="261" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                                <tr>
                                                    <td colspan="7" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Balanço de Equipamentos</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td width="5"></td>
                                                    <td width="15"></td>
                                                    <td></td>
                                                    <td width="50" align="center">&nbsp;</td>
                                                    <td width="50" align="center"><b>RIS</b></td>
                                                    <td width="50" align="center"><b>RED</b></td>
                                                    <td width="5"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleGreenBold">ATIVO(S):</td>
                                                    <td align="center">&nbsp;</td>
                                                    <td align="center"><%= qtdRisAtivos%></td>
                                                    <td align="center"><%= qtdRedAtivos%></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleRedBold">INATIVO(S):</td>
                                                    <td align="center">&nbsp;</td>
                                                    <td align="center"><%= qtdRisInativos%></td>
                                                    <td align="center"><%= qtdRedInativos%></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleBlueBold">TOTAL:</td>
                                                    <td align="center">&nbsp;</td>
                                                    <td align="center"><%= qtdRisAtivos + qtdRisInativos%></td>
                                                    <td align="center"><%= qtdRedAtivos + qtdRedInativos%></td>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="20"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="549" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="264" valign="top">
                                            <table width="261" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                                <tr>
                                                    <td colspan="7" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>OS - Preventiva</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td width="5"></td>
                                                    <td width="15"></td>
                                                    <td></td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td width="5"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleGreenBold">ABERTA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleRedBold">ABERTA(S)*:</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaAbertasAtraso.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaAbertasAtraso.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleBlueBold">FECHADA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSPreventivaFechadas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSPreventivaFechadas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="21"></td>
                                        <td width="264" valign="top">
                                            <table width="261" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                                <tr>
                                                    <td colspan="7" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Manutenção - Coleta</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td width="5"></td>
                                                    <td width="15"></td>
                                                    <td></td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdReportColetaAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportColetaAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td width="5"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleGreenBold">ABERTA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdReportColetaAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportColetaAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleBlueBold">FECHADA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdReportColetaFechada.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportColetaFechada.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="20"></td>
                        </tr>
                        <tr>
                            <td>
                                <table width="549" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="264" valign="top">
                                            <table width="261" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                                <tr>
                                                    <td colspan="7" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>OS - Aferição</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td width="5"></td>
                                                    <td width="15"></td>
                                                    <td></td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td width="5"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleGreenBold">ABERTA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoAbertas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoAbertas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleRedBold">ABERTA(S)*:</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoAbertasAtraso.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoAbertasAtraso.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleBlueBold">FECHADA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdOSAfericaoFechadas.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdOSAfericaoFechadas.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="21"></td>
                                        <td width="264" valign="top">
                                            <table width="261" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                                <tr>
                                                    <td colspan="7" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Manutenção - DTS</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td width="5"></td>
                                                    <td width="15"></td>
                                                    <td></td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdReportDTSAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportDTSAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td width='50' align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getMes() + "/" + strYear.substring(2));
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td width="5"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleGreenBold">ABERTA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdReportDTSAberta.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportDTSAberta.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><img src="imagens/topico.gif" width="15" height="15"></td>
                                                    <td class="StyleBlueBold">FECHADA(S):</td>
                                                    <%
//exibição dos 3 ultimos meses
    for (int i = qtdReportDTSFechada.size() - 1; i >= 0; i--) {
        contadorMain = (ContadorMain) qtdReportDTSFechada.get(i);

        String strYear = String.valueOf(contadorMain.getIdAno());

        out.println("<td align='center'>");
        if (i == 0) {
            out.println("<b>");
        }
        out.println(contadorMain.getQuantidade());
        if (i == 0) {
            out.println("</b>");
        }
        out.println("</td>");
    }
                                                    %>
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="7" height="5"></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="20"></td>
                        </tr>
                        <tr>
                            <td>
                                <!--table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                    <tr>
                                        <td colspan="4" height="18" align="center" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Quadro de Avisos</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" height="5"></td>
                                    </tr>
                                    <tr>
                                        <td width="5"></td>
                                        <td width="15"><img src="imagens/topico.gif" width="15" height="15"></td>
                                        <td width="524">Protótipo de página inicial do sistema, com informações importantes para o gerenciamento.</td>
                                        <td width="5"></td>
                                    </tr>
                                    <tr>
                                        <td width="5"></td>
                                        <td width="15"><img src="imagens/topico.gif" width="15" height="15"></td>
                                        <td width="524">Teste...</td>
                                        <td width="5"></td>
                                    </tr>
                                    <tr>
                                        <td width="5"></td>
                                        <td width="15"><img src="imagens/topico.gif" width="15" height="15"></td>
                                        <td width="524">Teste...</td>
                                        <td width="5"></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" height="5"></td>
                                    </tr>
                                </table-->
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="10"></td>
            </tr>
            <tr>
                <td colspan="3" height="20">&nbsp;</td>
            </tr>
        </table>
        <%}%>
        <%

            if (usuarioAtual.getPerfilUsuario() >= 3 && usuarioAtual.getPerfilUsuario() <= 5) {%>
        <jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
        <jsp:useBean id="osVerify" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
        <jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
        <jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
        <jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
        <jsp:useBean id="usuarioAutor" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
        <jsp:useBean id="usuarioDest" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
        <!-- include de paginação de resultados - cabeçalho -->
        <%@ include file="includes/pageNavigatorHeader.jsp"%>
        <!-- fim do include de paginação de resultados - cabeçalho -->
        <%
                Date dataNow1 = new Date();
                long dataTimeNow = dataNow1.getTime();
                long dataVerificador = 1 * 24 * 60 * 60 * 1000; //1 dias * 24 horas * 60 minutos * 60 segundos * 1000 milisegundos
                long dataVerifyStatus = dataTimeNow - dataVerificador;

                int zero = 0;


                String nula = null;

                String matriculaTecnico = usuarioAtual.getMatriculaUsuario();

                List<OS> list = manterOSControl.recuperarOSsPagFiltro(zero, nula, nula, nula, zero, matriculaTecnico, nula,nula,nula, nula, paginaInicial, qtdRegistros, orderBy, order);

                if (list != null) {
                    if (list.size() > 0) {
                        osVerify = (OS) list.get(0);
                        paginacao = osVerify.getPaginacao();
                    }
                }

        %>
        <!-- include de paginação de resultados - rodapé -->
        <%@ include file="includes/pageNavigatorFooter.jsp"%>
        <!-- fim do include de paginação de resultados - rodapé -->
        <table width="" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="220">&nbsp;</td>
                <td width="" height="180" colspan="2" valign="top">
                    <form method="get" action="">
                        <table width="" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">
                                    <table width='100%' cellspacing='0' cellpadding='0' border='0'>
                                        <tr>
                                            <td class="Title" width=""><%= saudacao%> <%= dateFormat.format(new Date())%> - Minhas Ordens de Serviço - Listagem (<%= paginacao.getResultSize()%> Registros - Página <%= pageNumber%> de <%= paginacao.getPageNavigator()%>)</td>
                                            <td width="" align="right">
                                                <a href="javascript:history.back()"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
                                                <a href="osList.jsp"><img src="imagens/stock_search-16.gif" border="0" alt="Procurar"></a>
                                                    <%
                for (int j = 0; j < usuarioAtual.getUsuarioRules().size(); j++) {
                    if (usuarioAtual.getUsuarioRules().get(j).getNome().equals("os_incluir")) {%>
                                                <a href="osAdd.jsp"><img src="imagens/stock_new-16.gif" border="0" alt="Novo"></a>
                                                    <%                    }
                }%>
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
                                <td height="20"></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width='' cellspacing='0' cellpadding='0' border='0' style='border: 1px solid #000000;'>
                                        <tr>
                                            <td width='20' height='18' class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp</td>
                                            <td width='80' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=id&page=1" class="LinkTitle">N&deg;.</a></td>
                                            <td width='80' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=equipamento&page=1" class="LinkTitle">Equip.</a></td>
                                            <td width='159' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=autor&page=1" class="LinkTitle">Solicitante:</a></td>
                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=tecnico&page=1" class="LinkTitle">Técnico:</a></td>
                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=data_abertura&page=1" class="LinkTitle">Data Abertura</a></td>
                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=data_servico&page=1" class="LinkTitle">Data Servico</a></td>
                                            <td width='160' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=data_fechamento&page=1" class="LinkTitle">Data Fechamento</a></td>
                                            <td width='90' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=status&page=1" class="LinkTitle">Status</a></td>
                                            <td width='90' class='TableResultTitle' style='border-bottom: 1px solid #000000;'><a href="?orderBy=prioridade&page=1" class="LinkTitle">Prioridade</a></td>
                                        </tr>
                                        <%

                if (list != null) {
                    String tableStyle = "TableResultBody1";
                    String status = "";
                    int countResult = 0;

                    for (int i = 0; i < list.size(); i++) {
                        os = (OS) list.get(i);
                        checkEquipamento = os.getCheckEquipamento();
                        equipamento = os.getEquipamento();
                        usuarioAutor = os.getUsuarioAutor();
                        usuarioDest = os.getUsuarioDest();
                        status = os.getStatus();

                        long dataVerifyAbertura = os.getDataAbertura().getTime();

                        String statusFechamento = "";
                        String statusStyle = "";
                        String prioridadeOSStyle = "";
                        String prioridadeOS = "";

                        countResult++;

                        if (countResult % 2 == 0) {
                            tableStyle = "TableResultBody2";
                        } else if (countResult % 2 == 1) {
                            tableStyle = "TableResultBody1";
                        }

                        if (status.equals("ab") && dataVerifyAbertura < dataVerifyStatus) {
                            statusFechamento = "ABERTO";
                            statusStyle = "OSWarning";
                        } else if (status.equals("ab")) {
                            statusFechamento = "ABERTO";
                            statusStyle = "OSOpen";
                        } else if (status.equals("an")) {
                            statusFechamento = "ANÁLISE";
                            statusStyle = "OSWorking";
                        } else if (status.equals("fc")) {
                            statusFechamento = "FECHADO";
                            statusStyle = "OSClose";
                        } else if (status.equals("ag")) {
                            statusFechamento = "AGUARDANDO";
                            statusStyle = "OSStandby";
                        }

                        /*if (os.getDataFechamento() == null && dataVerifyAbertura < dataVerifyStatus) {
                        statusFechamento = "ABERTO";
                        statusStyle = "OSWarning";
                        } else if (os.getDataFechamento() == null) {
                        statusFechamento = "ABERTO";
                        statusStyle = "OSOpen";
                        } else {
                        statusFechamento = "FECHADO";
                        statusStyle = "OSClose";
                        }*/

                                        %>

                                        <tr>
                                            <td height='16' class='<%= tableStyle%>' align="right"><img src="imagens/topico.gif" height="15" width="15" alt=""></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><%= os.getId() + os.getTipoOS().toUpperCase()%></a></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><%= equipamento.getNomeEquipamento()%></a></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><%= usuarioAutor.getNomeUsuario() + " " + usuarioAutor.getSobrenomeUsuario()%></a></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><%= usuarioDest.getNomeUsuario() + " " + usuarioDest.getSobrenomeUsuario()%></a></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><%= dateFormat.format(os.getDataAbertura())%></a></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>">
                                                    <%
                        if (os.getDataServico() == null) {
                            out.println("00:00:00 00/00/0000");
                        } else {
                            out.println(dateFormat.format(os.getDataServico()));
                        }
                                                    %>
                                                </a>

                                            </td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>">
                                                    <%
                        if (os.getDataFechamento() == null) {
                            out.println("00:00:00 00/00/0000");
                        } else {
                            out.println(dateFormat.format(os.getDataFechamento()));
                        }
                                                    %>
                                                </a></td>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><span class="<%= statusStyle%>"><%= statusFechamento%></span></a></td>
                                            <%

                        if (os.getPrioridade() == 0) {
                            prioridadeOS = "Alta";
                            prioridadeOSStyle = "OSPrioridadeAlta";
                        } else if (os.getPrioridade() == 1) {
                            prioridadeOS = "Média";
                            prioridadeOSStyle = "OSPrioridadeMedia";
                        } else if (os.getPrioridade() == 2) {
                            prioridadeOS = "Baixa";
                            prioridadeOSStyle = "OSPrioridadeBaixa";
                        }
                                            %>
                                            <td class='<%= tableStyle%>'><a href="osView.jsp?id=<%= os.getId()%>"><span class="<%= prioridadeOSStyle%>"><%= prioridadeOS%></span></a></td>
                                        </tr>

                                        <%
                    }
                }

                                        %>

                                        <tr>
                                            <td colspan="8" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" align="center"><%= pageViewNavigator%></td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" height="5"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
                <td width="10"></td>
            </tr>
        </table>
        <%
            }
        %>
        <table>
            <tr><td width="220">&nbsp;</td>
                <td colspan="2" align="center" class="Footer">
                    <!-- include do rodapé -->
                    <jsp:include page="includes/footer.jsp" />
                    <!-- fim do include do rodapé -->
                </td>
            </tr>
            <tr>
                <td colspan="2">
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
        <script type="text/javascript">
        	contaMain();
        </script>
    </body>
</html>