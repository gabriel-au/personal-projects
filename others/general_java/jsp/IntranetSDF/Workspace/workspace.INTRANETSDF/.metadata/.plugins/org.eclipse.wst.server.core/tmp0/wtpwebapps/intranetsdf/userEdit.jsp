<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.Usuario" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.UsuarioPerfil" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.UsuarioRule" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
<jsp:useBean id="usuarioSel" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
<jsp:useBean id="usuarioPerfil" class="br.com.consorciosdf.intranet.modelo.UsuarioPerfil" scope="request" />

<%

            String sucessAdd = "";
            String sucessAddClass = "Error";

            if ((request.getParameter("sucess")) != null) {
                if (request.getParameter("sucess").equals("1")) {
                    sucessAdd = "Favor digitar os campos corretamente.";
                } else if (request.getParameter("sucess").equals("2")) {
                    sucessAdd = "Não foi possível alterar o usuário.";
                } else if (request.getParameter("sucess").equals("3")) {
                    sucessAdd = "Senhas não conferem, por favor, digite-as novamente.";
                } else if (request.getParameter("sucess").equals("4")) {
                    sucessAdd = "Usuário alterado com sucesso.";
                    sucessAddClass = "Sucess";
                }
            }

            List listaUsuarios;
            List listaPerfis;
            ManterUserControl manterUserControl = new ManterUserControl();
            listaUsuarios = manterUserControl.listUsers();
            listaPerfis = manterUserControl.listPerfis();
            List<UsuarioRule> rules = manterUserControl.listRules();
            String user = "";
            String nome = "";
            String sobrenome = "";
            String email = "";
            String matricula = "";
            String skype = "";
            //String ativado = "";
            int perfil = 0;

            if ((request.getParameter("userSelected")) != null) {
                if (!request.getParameter("userSelected").equals("")) {
                    user = request.getParameter("userSelected");

                    Usuario userVerify = new Usuario();
                    userVerify.setUser(user);

                    usuarioSel = manterUserControl.viewUser(userVerify);

                    nome = usuarioSel.getNomeUsuario();
                    sobrenome = usuarioSel.getSobrenomeUsuario();
                    email = usuarioSel.getEmailUsuario();
                    perfil = usuarioSel.getPerfilUsuario();
                    matricula = usuarioSel.getMatriculaUsuario();
                    if(usuarioSel.getSkype()!= null){
                    	skype = usuarioSel.getSkype();
                    }
                    //ativado = usuarioSel.get
                }
            }

%>

<!-- include de verificação do usuário -->
<jsp:include page="includes/sessionVerify.jsp" />
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
                    <form name="editUser" method="post" action="editUser">
                        <table width="549" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">Usuário Web - Alterar</td>
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
                                            <td width="150" height="18" class="Title">Selecionar Usuário:</td>
                                            <td width="10"></td>
                                            <td width="389">
                                                <%

            if (listaUsuarios != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='userSelect' id='userSelect' class='Select' onChange='editUserSelected(this)' style='width:250px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listaUsuarios.size(); i++) {
                    usuario = (Usuario) listaUsuarios.get(i);
                    countResult++;

                    String userSelected = "";
                    if (!user.equals("") && user.equals(usuario.getUser())) {
                        userSelected = " selected='selected'";
                    }

                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + usuario.getUser() + "' class='" + optionStyle + "'" + userSelected + ">");
                    out.println(usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario() + " (" + usuario.getUser() + ")");
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='userSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>Não há usuários para editar.</option>");
                out.println("</select>");
            }

                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Usuário:</td>
                                            <td></td>
                                            <td><input type="text" name="userEdit" class="Input"
                                                       value="<%= user%>"
                                                       style="width:150px;" disabled="disabled">
                                                <input type="hidden" name="user" value="<%= user%>"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Matricula:</td>
                                            <td></td>
                                            <td><input type="text" name="matricula" value="<%= matricula%>"
                                                       class="Input"
                                                       style="width:150px;"> <!--disabled="disabled"--></td>
                                            <!--input type="text" name="matricula" class="Input"
                                                               value="0099<%--= matricula --%>"
                                                               style="width:150px;" disabled="disabled"-->
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

            if (listaPerfis != null) {
                String optionStyle = "OptionBody1";

                int countResult = 0;

                out.println("<select name='perfil' id='perfil' class='Select' style='width:150px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listaPerfis.size(); i++) {
                    usuarioPerfil = (UsuarioPerfil) listaPerfis.get(i);
                    countResult++;

                    //colore a combo
                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    String perfilSelected = "";
                    //verifica o perfil
                    if (usuarioPerfil.getCodPerfil() == perfil) {
                        perfilSelected = " selected='selected'";
                    }

                    out.println("<option value='" + usuarioPerfil.getCodPerfil() + "' class='" + optionStyle + "'" + perfilSelected + ">");
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
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Ativado:</td>
                                            <td></td>
                                            <td>
                                                <select name="ativado" class="Select"
                                                        style="width:150px;" disabled='disabled'>
                                                    <option value="S" selected="selected">
                                                        Sim
                                                    </option>
                                                    <option value="N">
                                                        Não
                                                    </option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title" valign="top">Permisões:</td>
                                            <td></td>
                                            <td >
                                                <table>
                                                    <%

        for (int i = 0; i < rules.size(); i++) {
            String optionStyle = "";
            String nomeRule = rules.get(i).getNome();
            String nomeExibicaoRule = rules.get(i).getNomeExibicao();
            String idRule = String.valueOf(rules.get(i).getId());
            String descRule = rules.get(i).getDescRule();
            String checked = "";
            if (i % 2 == 0) {
                optionStyle = "OptionBody1";
            } else if (i % 2 == 1) {
                optionStyle = "OptionBody2";
            }
            if (usuarioSel.getUsuarioRules() != null) {
                for (int j = 0; j < usuarioSel.getUsuarioRules().size(); j++) {
                    if (rules.get(i).getNome().equals(usuarioSel.getUsuarioRules().get(j).getNome())) {
                        checked = "checked";
                    }
                }
            }
                                                    %>
                                                    <tr>
                                                        <td class="<%=optionStyle%>">
                                                            <input type="checkbox" name="<%= nomeRule%>" value="<%=idRule%>" <%=checked%> /><span class="Title" onClick="document.editUser.<%= nomeRule%>.checked=(! document.editUser.<%= nomeRule%>.checked);"><%= nomeExibicaoRule%></span><br>
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
                                                       value="<%= nome%>" maxlength="20"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Sobrenome:</td>
                                            <td></td>
                                            <td><input type="text" name="lastName" class="Input"
                                                       value="<%= sobrenome%>" maxlength="20"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">E-mail:</td>
                                            <td></td>
                                            <td><input type="text" name="email" class="Input"
                                                       value="<%= email%>"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Skype:</td>
                                            <td></td>
                                            <td><input type="text" name="email" class="Input"
                                                       value="<%= skype%>"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3"><b>Obs.:</b> Todos os campos são de preenchimento obrigatório.<br>
                                                Caso não queira alterar a senha do usuário, deixe o campo em branco, caso contrario, a senha será alterada.</td>
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
                                                            <input type="submit" value="Alterar" id="Alterar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)">
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