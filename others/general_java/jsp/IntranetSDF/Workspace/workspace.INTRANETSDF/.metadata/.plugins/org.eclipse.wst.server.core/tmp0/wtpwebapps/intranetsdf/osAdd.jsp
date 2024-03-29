<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%--@page contentType="text/html" pageEncoding="UTF-8"--%>

<%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %><%@ page import="br.com.consorciosdf.intranet.controle.ManterOSControl" %>

<jsp:useBean id="os" class="br.com.consorciosdf.intranet.modelo.OS" scope="request" />
<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request" />
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request"/>

<%

            String sucessAdd = "";
            String sucessAddClass = "Error";

            if ((request.getParameter("sucess")) != null) {
                if (request.getParameter("sucess").equals("1")) {
                    sucessAdd = "Favor digitar os campos corretamente.";
                } else if (request.getParameter("sucess").equals("2")) {
                    sucessAdd = "Houve um problema na transa��o.";
                } else if (request.getParameter("sucess").equals("3")) {
                    sucessAdd = "Ordem de Servi�o inserida com sucesso.";
                    sucessAddClass = "Sucess";
                }
            }

            List listEquipamentos;
            List listUsuarios;
            List listChecks;

            ManterOSControl manterOSControl = new ManterOSControl();
            ManterUserControl manterUserControl = new ManterUserControl();
            ManterCheckEquipamentoControl manterCheckEquipamentoControl = new ManterCheckEquipamentoControl();
            listEquipamentos = manterOSControl.recuperarEquipamentos();
            listUsuarios = manterUserControl.listUsers();
            listChecks = manterCheckEquipamentoControl.recuperarChecagens();

%>

<!-- include de verifica��o do usu�rio -->
<%@ include file="includes/sessionVerify.jsp"%>
<!-- fim do include verifica��o do usu�rio -->

<html>
    <head>
        <title>INTRANET CONSORCIO SDF</title>
        <link rel="stylesheet" type="text/css" href="estilos/default.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script language="javascript" type="text/javascript" src="scripts/default.js">
        </script>

        <!-- include do menu -->
        <%--<jsp:include page="includes/menu.jsp" />--%>
        <!-- fim do include do menu -->

    </head>

    <body>
        <!-- include do cabe�alho -->
        <jsp:include page="includes/header.jsp" />
        <!-- fim do include do cabe�alho -->


        <table width="779" border="0" cellspacing="0" cellpadding="0">

            <tr>
                <td width="13">&nbsp;</td>
                <td width="549" height="180" colspan="2" valign="top">
                    <form method="post" action="saveOS">
                        <table width="549" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">
                                    <table width='549' cellspacing='0' cellpadding='0' border='0'>
                                        <tr>
                                            <td class="Title" width="400">Ordem de Servi�o - Cadastrar</td>
                                            <td width="149" align="right">
                                                <a href='osList.jsp'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'></a>
                                                 <a href='osList.jsp'> <img src='imagens/home16.gif' border='0' alt='Tela Inicial'> </a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <%--<td class="Title">Ordem de Servi�o - Cadastrar</td>--%>

                                <%--<td><a href='osList.jsp'><img src='imagens/stock_navigator-back-16.gif' border='0' alt='Voltar'></a></td>--%>
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
                                            <td width="250"></td>
                                            <td width="10"></td>
                                            <td width="289"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Prioridade da OS:</td>
                                            <td></td>
                                            <td>
                                                <select name='prioridade_os' id='prioridade_os' class='Select' style='width:250px;'>
                                                    <option value='0' class='OptionBody2'>ALTA</option>
                                                    <option value='1' class='OptionBody1' selected>M�DIA</option>
                                                    <option value='2' class='OptionBody2'>BAIXA</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Tipo da OS:</td>
                                            <td></td>
                                            <td>
                                                <select name='tipo_os' id='tipo_os' class='Select' style='width:250px;'>
                                                    <option value='' class='OptionBody1'>-- SELECIONE --</option>
                                                    <option value='p' class='OptionBody2'>Manuten��o Preventiva</option>
                                                    <option value='c' class='OptionBody1'>Manuten��o Corretiva</option>
                                                    <option value='a' class='OptionBody2'>Aferi��o</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">T�cnico:</td>
                                            <td></td>
                                            <td>
                                                <%

            if (listUsuarios != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='tecnico' id='tecnico' class='Select' style='width:250px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listUsuarios.size(); i++) {
                    usuario = (Usuario) listUsuarios.get(i);
                    countResult++;

                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + usuario.getMatriculaUsuario() + "' class='" + optionStyle + "'>");
                    out.println(usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario() + " (" + usuario.getUser() + ")");
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='userSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>N�o h� usu�rios para editar.</option>");
                out.println("</select>");
            }

                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Checagem No:</td>
                                            <td></td>
                                            <td>
                                                <%

            if (listChecks != null) {
                String optionStyle = "OptionBody1";
                int countResult = 0;

                out.println("<select name='checagem' id='checagem' class='Select' style='width:250px;'>");
                out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                countResult++;

                for (int i = 0; i < listChecks.size(); i++) {
                    checkEquipamento = (CheckEquipamento) listChecks.get(i);
                    countResult++;

                    if (countResult % 2 == 0) {
                        optionStyle = "OptionBody2";
                    } else if (countResult % 2 == 1) {
                        optionStyle = "OptionBody1";
                    }

                    out.println("<option value='" + checkEquipamento.getId() + "' class='" + optionStyle + "'>");
                    out.println(checkEquipamento.getId() + " (" + checkEquipamento.getEquipamento().getNomeEquipamento() + ")");
                    out.println("</option>");
                }

                out.println("</select>");
            } else {
                out.println("<select name='userSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>N�o h� usu�rios para editar.</option>");
                out.println("</select>");
            }

                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Equipamento:</td>
                                            <td></td>
                                            <td>
                                                <%

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
                out.println("<select name='equipamentoSelect' class='Select' style='width:150px;' disabled='disabled'>");
                out.println("<option>N�o h� usu�rios para editar.</option>");
                out.println("</select>");
            }

                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">N&deg; de S�rie:</td>
                                            <td></td>
                                            <td><input type="text" name="numSerie" class="Input" onkeypress='return Number(event)'
                                                       maxlength="20"
                                                       style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">N&deg; de Embarcada:</td>
                                            <td></td>
                                            <td><input type="text" name="numUPR" class="Input" onkeypress='return Number(event)'
                                                       maxlength="20"
                                                       style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">N&deg; de C�mera:</td>
                                            <td></td>
                                            <td><input type="text" name="numCamera" class="Input" onkeypress='return Number(event)'
                                                       maxlength="20"
                                                       style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">N&deg; de Flash:</td>
                                            <td></td>
                                            <td><input type="text" name="numFlash" class="Input" onkeypress='return Number(event)'
                                                       maxlength="20"
                                                       style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">
                                                Data da Anomalia (hh:mm dd/mm/aaaa):
                                            </td>
                                            <td></td>
                                            <td>
                                                <select name='dt_ano_hr' id='dt_ano_hr' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                    <option value='13'>13</option>
                                                    <option value='14'>14</option>
                                                    <option value='15'>15</option>
                                                    <option value='16'>16</option>
                                                    <option value='17'>17</option>
                                                    <option value='18'>18</option>
                                                    <option value='19'>19</option>
                                                    <option value='20'>20</option>
                                                    <option value='21'>21</option>
                                                    <option value='22'>22</option>
                                                    <option value='23'>23</option>
                                                </select> :
                                                <select name='dt_ano_min' id='dt_ano_min' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                    <option value='13'>13</option>
                                                    <option value='14'>14</option>
                                                    <option value='15'>15</option>
                                                    <option value='16'>16</option>
                                                    <option value='17'>17</option>
                                                    <option value='18'>18</option>
                                                    <option value='19'>19</option>
                                                    <option value='20'>20</option>
                                                    <option value='21'>21</option>
                                                    <option value='22'>22</option>
                                                    <option value='23'>23</option>
                                                    <option value='24'>24</option>
                                                    <option value='25'>25</option>
                                                    <option value='26'>26</option>
                                                    <option value='27'>27</option>
                                                    <option value='28'>28</option>
                                                    <option value='29'>29</option>
                                                    <option value='30'>30</option>
                                                    <option value='31'>31</option>
                                                    <option value='32'>32</option>
                                                    <option value='33'>33</option>
                                                    <option value='34'>34</option>
                                                    <option value='35'>35</option>
                                                    <option value='36'>36</option>
                                                    <option value='37'>37</option>
                                                    <option value='38'>38</option>
                                                    <option value='39'>39</option>
                                                    <option value='40'>40</option>
                                                    <option value='41'>41</option>
                                                    <option value='42'>42</option>
                                                    <option value='43'>43</option>
                                                    <option value='44'>44</option>
                                                    <option value='45'>45</option>
                                                    <option value='46'>46</option>
                                                    <option value='47'>47</option>
                                                    <option value='48'>48</option>
                                                    <option value='49'>49</option>
                                                    <option value='50'>50</option>
                                                    <option value='51'>51</option>
                                                    <option value='52'>52</option>
                                                    <option value='53'>53</option>
                                                    <option value='54'>54</option>
                                                    <option value='55'>55</option>
                                                    <option value='56'>56</option>
                                                    <option value='57'>57</option>
                                                    <option value='58'>58</option>
                                                    <option value='59'>59</option>
                                                </select>&nbsp;
                                                <select name='dt_ano_dia' id='dt_ano_dia' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                    <option value='13'>13</option>
                                                    <option value='14'>14</option>
                                                    <option value='15'>15</option>
                                                    <option value='16'>16</option>
                                                    <option value='17'>17</option>
                                                    <option value='18'>18</option>
                                                    <option value='19'>19</option>
                                                    <option value='20'>20</option>
                                                    <option value='21'>21</option>
                                                    <option value='22'>22</option>
                                                    <option value='23'>23</option>
                                                    <option value='24'>24</option>
                                                    <option value='25'>25</option>
                                                    <option value='26'>26</option>
                                                    <option value='27'>27</option>
                                                    <option value='28'>28</option>
                                                    <option value='29'>29</option>
                                                    <option value='30'>30</option>
                                                    <option value='31'>31</option>
                                                </select> /
                                                <select name='dt_ano_mes' id='dt_ano_mes' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                </select> /
                                                <select name='dt_ano_ano' id='dt_ano_ano' class='Select'>
                                                    <option value='0000'>0000</option>
                                                    <!--option value='2007'>2007</option-->
                                                    <option value='2008'>2008</option>
                                                    <option value='2009'>2009</option>
                                                    <option value='2010'>2010</option>
                                                    <option value='2011'>2011</option>
                                                    <option value='2012'>2012</option>
                                                </select>
                                            </td>
                                        </tr>

                                        <tr>
                                            <td height="18" class="Title">Data de Abertura (hh:mm dd/mm/aaaa):</td>
                                            <td></td>
                                            <td>
                                                <select name='dt_ini_hr' id='dt_ini_hr' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                    <option value='13'>13</option>
                                                    <option value='14'>14</option>
                                                    <option value='15'>15</option>
                                                    <option value='16'>16</option>
                                                    <option value='17'>17</option>
                                                    <option value='18'>18</option>
                                                    <option value='19'>19</option>
                                                    <option value='20'>20</option>
                                                    <option value='21'>21</option>
                                                    <option value='22'>22</option>
                                                    <option value='23'>23</option>
                                                </select> :
                                                <select name='dt_ini_min' id='dt_ini_min' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                    <option value='13'>13</option>
                                                    <option value='14'>14</option>
                                                    <option value='15'>15</option>
                                                    <option value='16'>16</option>
                                                    <option value='17'>17</option>
                                                    <option value='18'>18</option>
                                                    <option value='19'>19</option>
                                                    <option value='20'>20</option>
                                                    <option value='21'>21</option>
                                                    <option value='22'>22</option>
                                                    <option value='23'>23</option>
                                                    <option value='24'>24</option>
                                                    <option value='25'>25</option>
                                                    <option value='26'>26</option>
                                                    <option value='27'>27</option>
                                                    <option value='28'>28</option>
                                                    <option value='29'>29</option>
                                                    <option value='30'>30</option>
                                                    <option value='31'>31</option>
                                                    <option value='32'>32</option>
                                                    <option value='33'>33</option>
                                                    <option value='34'>34</option>
                                                    <option value='35'>35</option>
                                                    <option value='36'>36</option>
                                                    <option value='37'>37</option>
                                                    <option value='38'>38</option>
                                                    <option value='39'>39</option>
                                                    <option value='40'>40</option>
                                                    <option value='41'>41</option>
                                                    <option value='42'>42</option>
                                                    <option value='43'>43</option>
                                                    <option value='44'>44</option>
                                                    <option value='45'>45</option>
                                                    <option value='46'>46</option>
                                                    <option value='47'>47</option>
                                                    <option value='48'>48</option>
                                                    <option value='49'>49</option>
                                                    <option value='50'>50</option>
                                                    <option value='51'>51</option>
                                                    <option value='52'>52</option>
                                                    <option value='53'>53</option>
                                                    <option value='54'>54</option>
                                                    <option value='55'>55</option>
                                                    <option value='56'>56</option>
                                                    <option value='57'>57</option>
                                                    <option value='58'>58</option>
                                                    <option value='59'>59</option>
                                                </select>&nbsp;
                                                <select name='dt_ini_dia' id='dt_ini_dia' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                    <option value='13'>13</option>
                                                    <option value='14'>14</option>
                                                    <option value='15'>15</option>
                                                    <option value='16'>16</option>
                                                    <option value='17'>17</option>
                                                    <option value='18'>18</option>
                                                    <option value='19'>19</option>
                                                    <option value='20'>20</option>
                                                    <option value='21'>21</option>
                                                    <option value='22'>22</option>
                                                    <option value='23'>23</option>
                                                    <option value='24'>24</option>
                                                    <option value='25'>25</option>
                                                    <option value='26'>26</option>
                                                    <option value='27'>27</option>
                                                    <option value='28'>28</option>
                                                    <option value='29'>29</option>
                                                    <option value='30'>30</option>
                                                    <option value='31'>31</option>
                                                </select> /
                                                <select name='dt_ini_mes' id='dt_ini_mes' class='Select'>
                                                    <option value='00'>00</option>
                                                    <option value='01'>01</option>
                                                    <option value='02'>02</option>
                                                    <option value='03'>03</option>
                                                    <option value='04'>04</option>
                                                    <option value='05'>05</option>
                                                    <option value='06'>06</option>
                                                    <option value='07'>07</option>
                                                    <option value='08'>08</option>
                                                    <option value='09'>09</option>
                                                    <option value='10'>10</option>
                                                    <option value='11'>11</option>
                                                    <option value='12'>12</option>
                                                </select> /
                                                <select name='dt_ini_ano' id='dt_ini_ano' class='Select'>
                                                    <option value='0000'>0000</option>
                                                    <!--option value='2007'>2007</option-->
                                                    <option value='2008'>2008</option>
                                                    <option value='2009'>2009</option>
                                                    <option value='2010'>2010</option>
                                                    <option value='2011'>2011</option>
                                                    <option value='2012'>2012</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" class="Title">Defeito (opcional):</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <textarea name="defeito" class="TextArea" style="width:520px; height:100px;"></textarea>
                                            </td>
                                        </tr>
                                        <!--tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" class="Title">Reparo (opcional):</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <textarea name="reparo" class="TextArea" style="width:520px;height:100px;"></textarea>
                                            </td>
                                        </tr-->
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" class="<%= sucessAddClass%>" align="center"><%= sucessAdd%></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <table width="549" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td align="right">
                                                            <input type="submit" value="Salvar" id="Salvar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)" style="width: 100px;">
                                                        </td>
                                                        <td width="50"></td>
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
                <td colspan="3" class="Footer">
                    <table>
                        <tr>
                            <td align="center" width="525">
                                    <!-- include do rodap� -->
                                    <jsp:include page="includes/footer.jsp" />
                                    <!-- fim do include do rodap� -->
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