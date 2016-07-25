<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--@page contentType="text/html" pageEncoding="UTF-8"--%>

<%@ page import="br.com.consorciosdf.intranet.controle.ManterEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>

<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>



<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="equipamentoPai" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<jsp:useBean id="equipamentoVerify" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request"/>
<%

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            String dataAlteracao = "";
            List listEquipamentos;
            List listUsuarios;

            ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
            listEquipamentos = manterEquipamentoControl.recuperarEquipamentos();


        if ((request.getParameter("id")) != null) {
            int id = Integer.parseInt(request.getParameter("id"));


            //ManterEquipamentoControl manterEquipamentoControl = new ManterEquipamentoControl();
            equipamento = manterEquipamentoControl.recuperarEquipamentos(id);

            if (equipamento.getIdPai() != 0) {

                int idPai = equipamento.getIdPai();
                equipamentoPai = manterEquipamentoControl.recuperarEquipamentos(idPai);

            }

        //dataInclusao = dateFormat.format(equipamento.getDataInclusao());
        }
        
        String sucessEdit = "";
        String sucessAddClass = "Error";
        
        if ((request.getParameter("sucess")) != null) {
            if (request.getParameter("sucess").equals("1")) {
                sucessEdit = "Equipamento alterado com sucesso.";
                sucessAddClass = "Sucess";
            } else if (request.getParameter("sucess").equals("2")) {
                sucessEdit = "Favor digitar os campos corretamente.";
            } else if (request.getParameter("sucess").equals("3")) {
                sucessEdit = "Houve um problema na transação.";
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            int idEquipamento = 0;

            int idPai = 0;
            String nomeEquipamento = "";
            String tipoEquipamento = "";
            String endEquipamento = "";
            String descEquipamento = "";
            String cidadeEquipamento = "";
            String estadoEquipamento = "";

            String styleButtonEnabled = "class='Button'";
            String styleButtonDisabled = "class='ButtonDisabled' disabled='disabled'";

            String buttonAccept = styleButtonEnabled;
            String buttonEdit = styleButtonEnabled;
            String buttonClose = styleButtonEnabled;

            if (equipamento != null) {
                if (equipamento.getNomeEquipamento() != null) {
                    nomeEquipamento = equipamento.getNomeEquipamento();
                }
                if(equipamento.getTipo() != null){
                	tipoEquipamento = equipamento.getTipo();
                }
                if (equipamento.getDescricaoEquipamento() != null) {
                    descEquipamento = equipamento.getDescricaoEquipamento();
                }
                if (equipamento.getEnderecoCidadeEquipamento() != null) {
                    cidadeEquipamento = equipamento.getEnderecoCidadeEquipamento();
                }
                if (equipamento.getEnderecoEstadoEquipamento() != null) {
                    estadoEquipamento = equipamento.getEnderecoEstadoEquipamento();
                }
                if (equipamento.getEnderecoLogradouroEquipamento() != null) {
                    endEquipamento = equipamento.getEnderecoLogradouroEquipamento();
                }
                if(equipamento.getIdEquipamento() != 0){
                    idPai = equipamento.getIdPai();
                }
                 idEquipamento= equipamento.getIdEquipamento();
            }
%>

                    <form method="post" action="equipamentoEdit">
                        <table width="549" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">
                                    <table width='549' cellspacing='0' cellpadding='0' border='0'>
                                        <tr>
                                            <td class="Title" width="400">Equipamento - Alterar</td>
                                            <td width="149" align="right">
                                                <a href="equipamentoView.jsp?id=<%=equipamento.getIdEquipamento()%>"><img src="imagens/stock_navigator-back-16.gif" border="0" alt="Voltar"></a>
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
                                    <table width="549" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style='border: 1px solid #000000;'>
                                        <tr>
                                            <td height="20" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>&nbsp;</td>
                                            <td colspan="3" class="TableResultTitle" style='border-bottom: 1px solid #000000;'>Dados do Equipamento:</td>
                                        </tr>                              
                                                                                                                 
                                        <tr class="TableResultBody2">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Nome:</td>
                                            <td>&nbsp;</td>
                                            <td><input type="text" name="nome" class="Input"
                                                       maxlength="20" value="<%= nomeEquipamento%>"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr class="TableResultBody1">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Tipo:</td>
                                            <td>&nbsp;</td>
                                            <td><input type="text" name="tipo" class="Input"
                                                       maxlength="20" value="<%= tipoEquipamento%>"
                                                       style="width:250px;"></td>
                                        </tr>
                                        <tr class="TableResultBody2">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Descrição:</td>
                                            <td>&nbsp;</td>
                                            <td><textarea   name="descricao" cols="29" rows="5"> <%= descEquipamento%></textarea>
                                        </tr>
                                        <tr class="TableResultBody">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Endereço:</td>
                                            <td>&nbsp;</td>
                                            <td>
                                            	<textarea name="endereco" cols="29" rows="5"><%= " "+endEquipamento%></textarea>
                                            </td>
                                        </tr>
                                        <tr class="TableResultBody2">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Cidade:</td>
                                            <td>&nbsp;</td>
                                            <td><input type="text" name="cidade" class="Input"
                                                       maxlength="20" value="<%= cidadeEquipamento%>"
                                                       style="width:250px;"></td> 
                                                       </tr>                                      
                                        <tr class="TableResultBody1">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Estado:</td>
                                            <td>&nbsp;</td>
                                            <td>
   <%

            out.println("<select name='estado' id='estado' class='Select' style='width:250px;'>");
            out.println("<option value='' class='OptionBody1'>-- SELECIONE --</option>");

            if (equipamento.getEnderecoEstadoEquipamento().equals("AC")) {
                out.println("<option value='AC' class='OptionBody2' selected='selected'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
            } else if (equipamento.getEnderecoEstadoEquipamento().equals("AL")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1' selected='selected'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
            } else if (equipamento.getEnderecoEstadoEquipamento().equals("AP")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2' selected='selected'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
            } else if (equipamento.getEnderecoEstadoEquipamento().equals("AM")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'selected='selected'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
            } else if (equipamento.getEnderecoEstadoEquipamento().equals("BA")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'selected='selected'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                } else if (equipamento.getEnderecoEstadoEquipamento().equals("CE")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'selected='selected'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                } else if (equipamento.getEnderecoEstadoEquipamento().equals("DF")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'selected='selected'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                } else if (equipamento.getEnderecoEstadoEquipamento().equals("GO")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'selected='selected'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("ES")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'selected='selected'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("MA")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'selected='selected'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("MT")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'selected='selected'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("MS")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'selected='selected'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("MG")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'selected='selected'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("PA")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'selected='selected'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("PB")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'selected='selected'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("PR")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'selected='selected'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("PE")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'selected='selected'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("PI")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'selected='selected'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("RJ")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'selected='selected'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("RN")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'selected='selected'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("RS")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'selected='selected'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("RO")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'selected='selected'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("RR")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'selected='selected'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("SP")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'selected='selected'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("SC")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'selected='selected'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("SE")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'selected='selected'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'>TO</option>");
                }else if (equipamento.getEnderecoEstadoEquipamento().equals("TO")) {
                out.println("<option value='AC' class='OptionBody2'>AC</option>");
                out.println("<option value='AL' class='OptionBody1'>AL</option>");
                out.println("<option value='AP' class='OptionBody2'>AP</option>");
                out.println("<option value='AM' class='OptionBody1'>AM</option>");
                out.println("<option value='BA' class='OptionBody2'>BA</option>");
                out.println("<option value='CE' class='OptionBody1'>CE</option>");
                out.println("<option value='DF' class='OptionBody2'>DF</option>");
                out.println("<option value='GO' class='OptionBody1'>GO</option>");
                out.println("<option value='ES' class='OptionBody2'>ES</option>");
                out.println("<option value='MA' class='OptionBody1'>MA</option>");
                out.println("<option value='MT' class='OptionBody2'>MT</option>");
                out.println("<option value='MS' class='OptionBody1'>MS</option>");
                out.println("<option value='MG' class='OptionBody2'>MG</option>");
                out.println("<option value='PA' class='OptionBody1'>PA</option>");
                out.println("<option value='PB' class='OptionBody2'>PB</option>");
                out.println("<option value='PR' class='OptionBody1'>PR</option>");
                out.println("<option value='PE' class='OptionBody2'>PE</option>");
                out.println("<option value='PI' class='OptionBody1'>PI</option>");
                out.println("<option value='RJ' class='OptionBody2'>RJ</option>");
                out.println("<option value='RN' class='OptionBody1'>RN</option>");
                out.println("<option value='RS' class='OptionBody2'>RS</option>");
                out.println("<option value='RO' class='OptionBody1'>RO</option>");
                out.println("<option value='RR' class='OptionBody2'>RR</option>");
                out.println("<option value='SP' class='OptionBody1'>SP</option>");
                out.println("<option value='SC' class='OptionBody2'>SC</option>");
                out.println("<option value='SE' class='OptionBody1'>SE</option>");
                out.println("<option value='TO' class='OptionBody2'selected='selected'>TO</option>");
                }
            out.println("</select>");

                                                %>
                                            </td>
                                        </tr>
                                        <tr class="TableResultBody2">
                                            <td height="18">&nbsp;</td>
                                            <td class="Title">Equipamento Origem:</td>
                                            <td>&nbsp;</td>
                                            <td>
                                            <%
            if (listEquipamentos != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;
                out.println("<select name='equipamento' id='equipamento' class='Select' style='width:250px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");
                countResult++;
                for (int i = 0; i < listEquipamentos.size(); i++) {
                    equipamentoPai = (Equipamento) listEquipamentos.get(i);
                    countResult++;
                    String equipamentoSelected = "";

                    if (equipamentoPai.getIdEquipamento() == equipamento.getIdPai()) {
                        equipamentoSelected = " selected='selected'";
                    }
                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }
                    out.println("<option value='" + equipamentoPai.getIdEquipamento() + "' class='" + optionStyle + "'" + equipamentoSelected + ">");
                    out.println(equipamentoPai.getNomeEquipamento());
                    out.println("</option>");
                }
                out.println("</select>");
            } else {
                out.println("<select name='equipamentoSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>Não há usuários para editar.</option>");
                out.println("</select>");
            }
                                                %>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                				<td colspan="3" height="20">&nbsp;</td>
            				</tr>
                             <tr>
                                <td>
                                    <table width="549" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td align="right">
                                                <input type="hidden" name="id" id="id" value="<%= idEquipamento%>">
                                                <input type="hidden" name="equipamento" id="equipamento" value="<%= equipamentoPai.getIdPai()  %>">
                                                <input type="submit" value="Salvar" id="Salvar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)" style="width: 100px;">
                                            </td>
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
                <td colspan="3" class="<%= sucessAddClass%>" align="center"><%= sucessEdit%></td>
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