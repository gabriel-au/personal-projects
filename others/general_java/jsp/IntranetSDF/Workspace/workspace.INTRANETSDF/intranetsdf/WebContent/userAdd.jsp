<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.UsuarioPerfil" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.UsuarioRule" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuarioPerfil" class="br.com.consorciosdf.intranet.modelo.UsuarioPerfil" scope="request" />

<%

            String sucessAdd = "";
            String sucessAddClass = "Error";

            if ((request.getParameter("sucess")) != null) {
                if (request.getParameter("sucess").equals("1")) {
                    sucessAdd = "Favor digitar os campos corretamente.";
                } else if (request.getParameter("sucess").equals("2")) {
                    sucessAdd = "Não foi possível inserir o usuário.";
                } else if (request.getParameter("sucess").equals("3")) {
                    sucessAdd = "Senhas não conferem, por favor, digite-as novamente.";
                } else if (request.getParameter("sucess").equals("4")) {
                    sucessAdd = "Usuário inserido com sucesso.";
                    sucessAddClass = "Sucess";
                }
            }

            List lista;
            ManterUserControl manterUserControl = new ManterUserControl();
            lista = manterUserControl.listPerfis();
            List<UsuarioRule> rules = manterUserControl.listRules();


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
                    <form method="post" name="addUser" action="addUser">
                        <table width="549" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">Usuário Web - Cadastrar</td>
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
                                            <td width="150" height="18" class="Title">Usuário:</td>
                                            <td width="10"></td>
                                            <td width="389"><input type="text" name="user" class="Input"
                                                                   maxlength="20"
                                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Matrícula:</td>
                                            <td></td>
                                            <td><input type="text" name="matricula" class="Input"
                                                       maxlength="4" style="width: 150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Senha:</td>
                                            <td></td>
                                            <td><input type="password" name="pass" class="Input"
                                                       maxlength="20" style="width: 150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Confirmar Senha:</td>
                                            <td></td>
                                            <td><input type="password" name="passConfirm" class="Input"
                                                       maxlength="20" style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Perfil:</td>
                                            <td></td>
                                            <td>
                                                <%

            if (lista != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='perfil' id='perfil' class='Select' style='width:150px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < lista.size(); i++) {
                    usuarioPerfil = (UsuarioPerfil) lista.get(i);
                    countResult++;

                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + usuarioPerfil.getCodPerfil() + "' class='" + optionStyle + "'>");
                    out.println(usuarioPerfil.getDescricaoPerfil());
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='perfil' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>Houve um problema ao carregar essa combo.</option>");
                out.println("</select>");
            }

                                                %>

                                                <!-- select name="perfil" class="Select"
                                                        style="width:150px;">
                                                    <option value="1" selected="selected">
                                                                    Usuário
                                                                </option>
                                                    <option value="2">
                                                                    Usuário Avançado
                                                                </option>
                                                    <option value="3">
                                                                    Administrador
                                                                </option>
                                                </select -->
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title" valign="top">Permisões:</td>
                                            <td></td>
                                            <td class="Title">
                                                <table>
                                                    <%

        for (int i = 0; i < rules.size(); i++) {
            String optionStyle = "";
            String nomeRule = rules.get(i).getNome();
            String nomeExibicaoRule = rules.get(i).getNomeExibicao();
            String idRule = String.valueOf(rules.get(i).getId());
            String descRule = rules.get(i).getDescRule();

            if (i % 2 == 0) {
                optionStyle = "OptionBody1";
            } else if (i % 2 == 1) {
                optionStyle = "OptionBody2";
            }

                                                    %>
                                                    <tr>
                                                        <td class="<%=optionStyle%>">
                                                            <input type="checkbox" name="<%= nomeRule%>" value="<%=idRule%>" /><span class="Title" onClick="document.addUser.<%= nomeRule%>.checked=(! document.addUser.<%= nomeRule%>.checked);"><%= nomeExibicaoRule%></span><br>
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Nome:</td>
                                            <td></td>
                                            <td><input type="text" name="name" class="Input"
                                                       maxlength="20"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Sobrenome:</td>
                                            <td></td>
                                            <td><input type="text" name="lastName" class="Input"
                                                       maxlength="20"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">E-mail:</td>
                                            <td></td>
                                            <td><input type="text" name="email" class="Input"
                                                       maxlength="100"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Skype:</td>
                                            <td></td>
                                            <td><input type="text" name="skype" class="Input"
                                                       maxlength="100"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3"><b>Obs.:</b> Todos os campos são de preenchimento obrigatório.</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" class="<%= sucessAddClass%>"><%= sucessAdd%></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <table width="549" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td align="right">
                                                            <input type="hidden" name="tipo" value="web">
                                                            <input type="submit" value="Cadastrar" id="Cadastrar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)">
                                                        </td>
                                                        <td width="150"></td>
                                                    </tr>
                                                </table>

                                            </td>
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