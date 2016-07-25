<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="br.com.consorciosdf.intranet.controle.ManterEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterEquipamentoControl" %>

<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request" />
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request" />
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>


<%

        String sucessAdd = "";
        String sucessAddClass = "Error";

        if ((request.getParameter("sucess")) != null) {
            if (request.getParameter("sucess").equals("1")) {
                sucessAdd = "Equipamento inserido com sucesso.";
                sucessAddClass = "Sucess";
            } else if (request.getParameter("sucess").equals("2")) {
                sucessAdd = "Favor digitar os campos corretamente.";
            } else if (request.getParameter("sucess").equals("3")) {
                sucessAdd = "Houve um problema na transação.";
            }
        }

        List listEquipamentos;


        ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
        listEquipamentos = manterEquipamentoControl.recuperarEquipamentos();



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
        <!--script language="JavaScript" type="text/javascript" >
    function CadastrarComponente(){
        document.salvarEquipamento.action="equipamentosSave?adicionarComponente=true";
        document.forms.salvarEquipamento.submit();
        <%//session.setAttribute("adicionarComponente", true); %>
    }
</script-->
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
                    <form name="salvarEquipamento" method="POST" action="equipamentosSave">
                        <table width="400" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">
                                		<table>
                                			<tr>
                                				<td class="title" width="400">Equipamento - Cadastrar</td>
                                				<td width="149" align="right"><a href="equipamentoList.jsp"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a></td>
                                			</tr>
                                		</table>
                            </tr>
                            <tr>
                                <td height="1" bgcolor="#000000"></td>
                            </tr>
                            <tr>
                                <td height="10"></td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                        <tr>
                                            <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                            <td colspan="4" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do Equipamento:</td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td width="10"></td>
                                            <td width="220" height="18" class="Title">Nome:</td>
                                            <td width="9"></td>
                                            <td width="300"><input type="text" name="nome" class="Input"
                                                                       maxlength="255"
                                                                   style="width:250px;"></td>
                                            <td width="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>

                                        <tr>
                                            <td width="10"></td>
                                            <td width="220" height="18" class="Title">Número do Equipamento:</td>
                                            <td width="9"></td>
                                            <td width="300"><input type="text" name="numEquip" class="Input" onkeypress='return Number(event)'
                                                                       maxlength="255"
                                                                   style="width:250px;"></td>
                                            <td width="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td width="10"></td>
                                            <td width="220" height="18" class="Title">Tipo:</td>
                                            <td width="9"></td>
                                            <td width="300"><input type="text" name="tipo" class="Input"
                                                                       maxlength="3"
                                                                   style="width:250px;"></td>
                                            <td width="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td width="10"></td>
                                            <td width="220" height="18" class="Title">Descrição:</td>
                                            <td width="9"></td>
                                            <td width="300"><textarea  name="descricao" cols="29" rows="5" style="border: 1px solid #000000"></textarea></td>
                                            <td width="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>

                                        <tr class="TableResultBody">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Endereço:</td>
                                            <td>&nbsp;</td>
                                            <td>
                                            	<textarea name="endereco" cols="29" rows="5" style="border: 1px solid #000000"></textarea>
                                            </td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td height="18" class="Title">Cidade:</td>
                                            <td></td>
                                            <td><input type="text" name="cidade" class="Input"
                                                           maxlength="255"
                                                       style="width:250px;"></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="5"></td>
                                        </tr>

                                        <tr>
                                            <td></td>
                                            <td height="18" class="Title">Estado:</td>
                                            <td></td>
                                            <td><select name='estado' id='estado' class='Select' style='width:120px;'>
                                                    <option value='' class='OptionBody1' >-- SELECIONE --</option>
                                                    <option value='AC' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("AC")) {
            out.print("selected");
        }%>>AC</option>
                                                    <option value='AL' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("AL")) {
            out.print("selected");
        }%>>AL</option>
                                                    <option value='AP' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("AP")) {
            out.print("selected");
        }%>>AP</option>
                                                    <option value='AM' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("AM")) {
            out.print("selected");
        }%>>AM</option>
                                                    <option value='BA' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("BA")) {
            out.print("selected");
        }%>>BA</option>
                                                    <option value='CE' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("CE")) {
            out.print("selected");
        }%>>CE</option>                             <option value='DF' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("DF")) {

            out.print("selected");
        }%>>DF</option>
                                                    <option value='GO' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("GO")) {
            out.print("selected");
        }%>>GO</option>                             <option value='ES' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("ES")) {

            out.print("selected");
        }%>>ES</option>
                                                    <option value='MA' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("MA")) {
            out.print("selected");
        }%>>MA</option>
                                                    <option value='MT' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("MT")) {
            out.print("selected");
        }%>>MT</option>
                                                    <option value='MS' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("MS")) {
            out.print("selected");
        }%>>MS</option>
                                                    <option value='MG' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("MG")) {
            out.print("selected");
        }%>>MG</option>
                                                    <option value='PA' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("PA")) {
            out.print("selected");
        }%>>PA</option>
                                                    <option value='PB' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("PB")) {
            out.print("selected");
        }%>>PB</option>
                                                    <option value='PR' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("PR")) {
            out.print("selected");
        }%>>PR</option>
                                                    <option value='PE' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("PE")) {
            out.print("selected");
        }%>>PE</option>
                                                    <option value='PI' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("PI")) {
            out.print("selected");
        }%>>PI</option>
                                                    <option value='RJ' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("RJ")) {
            out.print("selected");
        }%>>RJ</option>
                                                    <option value='RN' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("RN")) {
            out.print("selected");
        }%>>RN</option>
                                                    <option value='RS' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("RS")) {
            out.print("selected");
        }%>>RS</option>
                                                    <option value='RO' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("RO")) {
            out.print("selected");
        }%>>RO</option>
                                                    <option value='RR' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("RR")) {
            out.print("selected");
        }%>>RR</option>
                                                    <option value='SP' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("SP")) {
            out.print("selected");
        }%>>SP</option>
                                                    <option value='SC' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("SC")) {
            out.print("selected");
        }%>>SC</option>
                                                    <option value='SE' class='OptionBody1'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("SE")) {
            out.print("selected");
        }%>>SE</option>
                                                    <option value='TO' class='OptionBody2'<%if (request.getParameter("estado") != null && request.getParameter("estado").equals("TO")) {
            out.print("selected");
        }%>>TO</option>

                                            </select></td>
                                            <td></td>
                                            </tr>
                                            <td></td>
                                                                                  
                                        <tr>
                                            <td colspan="5" height="10"></td>
                                        </tr>
                                         <tr>
                                            <td></td>
                                            <td height="18" class="Title">Equipamento Origem:</td>
                                            <td></td>
                                            <td><%

            if (listEquipamentos != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='equipamento' id='equipamento' class='Select' style='width:250px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listEquipamentos.size(); i++) {
                    equipamento = (Equipamento) listEquipamentos.get(i);
                    countResult++;

                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + equipamento.getIdEquipamento() + "' class='" + optionStyle + "'>");
                    out.println(equipamento.getNomeEquipamento());
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='equipamento' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option value=''>Não há equipamentos.</option>");
                out.println("</select>");
            }

                                                %></td>
                                            <td></td>
                                        </tr>


                                        <tr>
                                            <td colspan="5" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td width="10">&nbsp;</td>
                                            <td colspan="3" align="right">
                                                <input type='submit' value='Salvar' id='Salvar' class='Button' onMouseOut='buttonOn(this)' onMouseOver='buttonOver(this)' style='width: 100px;'>
                                                <!--input type='submit' value='Cadastrar Componente' id='Salvar' class='Button' onMouseOut='buttonOn(this)' onMouseOver='buttonOver(this)' style='width: 100px;' onclick='CadastrarComponente()'-->
                                            </td>
                                            <td width="10">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="5" height="10"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="10"></td>
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
                <td colspan="3" class="<%= sucessAddClass%>" align="center"><%= sucessAdd%></td>
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
