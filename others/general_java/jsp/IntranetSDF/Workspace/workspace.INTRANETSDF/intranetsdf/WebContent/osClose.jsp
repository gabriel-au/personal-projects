<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--@page contentType="text/html" pageEncoding="UTF-8"--%>

<%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.OS" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.CheckEquipamento" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Equipamento" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Usuario" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request"/>
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="usuarioAutor" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>
<jsp:useBean id="usuarioDest" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request"/>

<%

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            int userPerfil = Integer.parseInt((String) session.getAttribute("userPerfil"));

            if ((request.getParameter("id")) != null) {
                int id = Integer.parseInt(request.getParameter("id"));

                ManterOSControl manterOSControl = new ManterOSControl();
                os = manterOSControl.recuperarOS(id);
            }

            String sucessAdd = "";
            String sucessAddClass = "Error";
            String msgSucess = "";

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
            if (os.getStatus().equals("fc")) {
                response.sendRedirect(request.getContextPath() + "/osView.jsp?id="
                        + request.getParameter("id") + "&sucess=8");
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
        <%--<jsp:include page="includes/menu.jsp" />--%>
        <!-- fim do include do menu -->

    </head>

    <body>
        <!-- include do cabeçalho -->
        <jsp:include page="includes/header.jsp" />
        <!-- fim do include do cabeçalho -->


        <table width="779" border="0" cellspacing="0" cellpadding="0">

            <tr>
                <td width="13">&nbsp;</td>
                <td width="549" height="180" colspan="2" valign="top">

                    <%
                                int idOs = 0;
                                int numSerie = 0;
                                int numUPR = 0;
                                int numCamera = 0;

                                Calendar dtFechamento = null;

                                String userAutor = "";
                                String userDest = "";
                                String userDestMatricula = "";
                                String nomeEquipamento = "";
                                String endEquipamento = "";
                                String numFlash = "";
                                String descDefeito = "";
                                String descReparo = "";
                                String descServExec = "";
                                String descFechamento = "";
                                String dataAnomalia = "";
                                String dataAbertura = "";
                                String dataServico = "";
                                String dataFechamento = "";
                                String statusFechamento = "";
                                String statusStyle = "";
                                String prioridadeOS = "Média";
                                String prioridadeOSStyle = "OsPrioridadeMedia";

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
                                    numSerie = equipamento.getNumSerie();
                                    numUPR = equipamento.getNumUPR();
                                    numCamera = equipamento.getNumCamera();
                                    numFlash = equipamento.getNumFlash();

                                    dataAbertura = dateFormat.format(os.getDataAbertura());

                                    if (os.getDataAnomalia() != null) {
                                        dataAnomalia = dateFormat.format(os.getDataAnomalia());
                                    }

                                    if (os.getDataServico() != null) {
                                        dataServico = dateFormat.format(os.getDataServico());
                                    }


                                    if (os.getDescFechamento() != null) {
                                        descFechamento = os.getDescFechamento();
                                    }
                                    if (os.getDescReparo() != null) {
                                        descReparo = os.getDescReparo();
                                    }
                                    if (os.getDescServicoExecutado() != null) {
                                        descServExec = os.getDescServicoExecutado();
                                    }
                                    if (os.getDataFechamento() != null) {
                                        dtFechamento = Calendar.getInstance();
                                        dtFechamento.setTime(os.getDataFechamento());
                                    } else {
                                        java.util.Calendar calendar = new java.util.GregorianCalendar();
                                        dtFechamento = calendar;
                                    }
                                    if (usuarioDest != null) {
                                        userDest = usuarioDest.getNomeUsuario() + " " + usuarioDest.getSobrenomeUsuario();
                                        userDestMatricula = usuarioDest.getMatriculaUsuario();
                                    }

                                    if (os.getDescDefeito() == null) {
                                        descDefeito = "";
                                    } else {
                                        descDefeito = os.getDescDefeito();
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
                                    if (os.getDataFechamento() == null) {
                                        statusFechamento = "ABERTO";
                                        statusStyle = "OSOpen";
                                    } else {
                                        dataFechamento = dateFormat.format(os.getDataFechamento());
                                        statusFechamento = "FECHADO";
                                        statusStyle = "OSClose";
                                    }
                                }
                    %>

                    <form method="post" action="closeOS">
                        <table width="549" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">
                                    <table width='549' cellspacing='0' cellpadding='0' border='0'>
                                        <tr>
                                            <td class="Title" width="400">Ordem de Serviço - Fechar</td>
                                            <td width="149" align="right">
                                                <a href='osView.jsp?id=<%= idOs%>'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar' /></a>
                                                 <a href='osList.jsp'> <img src='imagens/home16.gif' border='0' alt='Tela Inicial'> </a>
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
                            <%= msgSucess%>
                            <tr>
                                <td>
                                    <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                        <tr>
                                            <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                            <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados de Checagem:</td>
                                        </tr>
                                        <tr class="TableResultBody1">
                                            <td height="18" width="10">&nbsp;</td>
                                            <td class="Title" width="120">N&deg; de OS:</td>
                                            <td width="10">&nbsp;</td>
                                            <td>
                                                <table width="380" border="0" cellspacing="0" cellpadding="0">
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
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">N&deg; de série:</td>
                                            <td>&nbsp;</td>
                                            <td><%= numSerie%></td>
                                        </tr>
                                        <tr class="TableResultBody2">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">N&deg; de Embarcada:</td>
                                            <td>&nbsp;</td>
                                            <td><%= numUPR%></td>
                                        </tr>
                                        <tr class="TableResultBody1">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">N&deg; de Câmera:</td>
                                            <td>&nbsp;</td>
                                            <td><%= numCamera%></td>
                                        </tr>
                                        <tr class="TableResultBody2">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">N&deg; de Flash:</td>
                                            <td>&nbsp;</td>
                                            <td><%= numFlash%></td>
                                        </tr>
                                        <tr class="TableResultBody1">
                                            <td height="18"></td>
                                            <td class="Title">Data de Anomalia:</td>
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
                                            <td class="Title">Data de Serviço:</td>
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
                                    <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                        <tr>
                                            <td width="10" height="20" class='TableResultTitle' style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                            <td class='TableResultTitle' style='border-bottom: 1px solid #000000;'>Defeito:</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <textarea name='defeito' disabled='disabled' class="TextArea" style='border: 0px solid #000000; background-color:#FFFFFF; width:539px; height: 100px;'><%out.print(br.com.consorciosdf.intranet.modelo.EditorTextoWeb.br2nl(descDefeito));%></textarea>
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
                                    <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                                        <tr>
                                            <td width="250" height="18" class="Title">Data de Fechamento (hh:mm dd/mm/aaaa):</td>
                                            <td width="10"></td>
                                            <td width="289">
                                                <%

                                                            out.println("<select name='dt_fim_hr' id='dt_fim_hr' class='Select'>");

                                                            for (int hora = 0; hora <= 23; hora++) {
                                                                String selected = "";
                                                                if (dtFechamento != null) {
                                                                    if (hora == dtFechamento.get(Calendar.HOUR_OF_DAY)) {
                                                                        selected = " selected='selected'";
                                                                    }
                                                                }

                                                                out.println("<option value='" + hora + "'" + selected + ">" + hora + "</option>");
                                                            }

                                                            out.println("</select>");

                                                            out.println(" : ");

                                                            out.println("<select name='dt_fim_min' id='dt_fim_min' class='Select'>");

                                                            for (int min = 0; min <= 59; min++) {
                                                                String selected = "";
                                                                if (dtFechamento != null) {
                                                                    if (min == dtFechamento.get(Calendar.MINUTE)) {
                                                                        selected = " selected='selected'";
                                                                    }
                                                                }

                                                                out.println("<option value='" + min + "'" + selected + ">" + min + "</option>");
                                                            }

                                                            out.println("</select>");

                                                            out.println("&nbsp");

                                                            out.println("<select name='dt_fim_dia' id='dt_fim_dia' class='Select'>");

                                                            for (int dia = 0; dia <= 31; dia++) {
                                                                String selected = "";
                                                                if (dtFechamento != null) {
                                                                    if (dia == dtFechamento.get(Calendar.DAY_OF_MONTH)) {
                                                                        selected = " selected='selected'";
                                                                    }
                                                                }

                                                                out.println("<option value='" + dia + "'" + selected + ">" + dia + "</option>");
                                                            }

                                                            out.println("</select>");

                                                            out.println(" / ");

                                                            out.println("<select name='dt_fim_mes' id='dt_fim_mes' class='Select'>");

                                                            for (int mes = 0; mes <= 12; mes++) {
                                                                String selected = "";
                                                                if (dtFechamento != null) {
                                                                    if (mes == (dtFechamento.get(Calendar.MONTH)) + 1) {
                                                                        selected = " selected='selected'";
                                                                    }
                                                                }
                                                                out.println("<option value='" + mes + "'" + selected + ">" + mes + "</option>");
                                                            }

                                                            out.println("</select>");

                                                            out.println(" / ");

                                                            out.println("<select name='dt_fim_ano' id='dt_fim_ano' class='Select'>");
                                                            out.println("<option value='0'>0</option>");

                                                            for (int ano = 2007; ano <= 2012; ano++) {
                                                                String selected = "";
                                                                if (dtFechamento != null) {
                                                                    if (ano == dtFechamento.get(Calendar.YEAR)) {
                                                                        selected = " selected='selected'";
                                                                    }
                                                                }

                                                                out.println("<option value='" + ano + "'" + selected + ">" + ano + "</option>");
                                                            }

                                                            out.println("</select>");

                                                            out.println("&nbsp");

                                                %>
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
                                    <table width="549" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="10">&nbsp;</td>
                                            <td class="Error">Materiais Utilizados:</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <textarea name='reparo' class="TextArea" style='width:539px; height: 100px;'><%out.print(br.com.consorciosdf.intranet.modelo.EditorTextoWeb.br2nl(descReparo));%></textarea>
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
                                    <table width="549" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="10">&nbsp;</td>
                                            <td class="Error">Serviços Executados:</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <textarea name='serv_exec' class="TextArea" style='width:539px; height: 100px;'><% out.print(br.com.consorciosdf.intranet.modelo.EditorTextoWeb.br2nl(descServExec));%></textarea>
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
                                    <table width="549" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="10">&nbsp;</td>
                                            <td class="Error">Fechamento:</td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <textarea name='fechamento' class="TextArea" style='width:539px; height: 100px;'><%out.print(br.com.consorciosdf.intranet.modelo.EditorTextoWeb.br2nl(descFechamento));%></textarea>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="20"></td>
                            </tr>
                            <tr>
                                <td class="<%= sucessAddClass%>" align="center"><%= sucessAdd%></td>
                            </tr>
                            <tr>
                                <td height="20"></td>
                            </tr>
                            <tr>
                                <td>
                                    <table width="549" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td align="right">
                                                <input type="hidden" name="idOS" value="<%= idOs%>">
                                                <input type="hidden" name="userDest" value="<%= userDestMatricula%>">
                                                <input type="submit" value="Fechar OS" id="Fechar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)" style="width: 100px;">                                        </td>
                                            <td width="50"></td>
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
                <td colspan="3" class="Footer">
                    <table>
                        <tr>
                            <td align="center" width="535">
                                <!-- include do rodapé -->
                                <jsp:include page="includes/footer.jsp" />
                                <!-- fim do include do rodapé -->
                            </td>
                        </tr>
                    </table>
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