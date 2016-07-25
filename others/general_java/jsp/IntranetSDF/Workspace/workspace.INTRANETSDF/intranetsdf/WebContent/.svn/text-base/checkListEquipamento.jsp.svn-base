<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterCheckEquipamentoControl" %>
<%@ page import="br.com.consorciosdf.intranet.controle.ManterUserControl" %>
<%@ page import="br.com.consorciosdf.intranet.modelo.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>

<jsp:useBean id="usuario" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
<jsp:useBean id="tecnico" class="br.com.consorciosdf.intranet.modelo.Usuario" scope="request" />
<jsp:useBean id="checkEquipamento" class="br.com.consorciosdf.intranet.modelo.CheckEquipamento" scope="request" />
<jsp:useBean id="equipamento" class="br.com.consorciosdf.intranet.modelo.Equipamento" scope="request" />
<jsp:useBean id="procedimento" class="br.com.consorciosdf.intranet.modelo.Procedimento" scope="request" />
<jsp:useBean id="statusProcedimento" class="br.com.consorciosdf.intranet.modelo.StatusProcedimento" scope="request" />

<%

String sucessAdd = "";
String sucessAddClass = "Error";

Date dataNow = new Date();
session.setAttribute("dataInicioCheckList", dataNow);

if ((request.getParameter("sucess")) != null) {
    if (request.getParameter("sucess").equals("1")) {
        sucessAdd = "Favor digitar os campos corretamente.";
    } else if (request.getParameter("sucess").equals("2")) {
        sucessAdd = "Houve um problema na transação.";
    } else if (request.getParameter("sucess").equals("3")) {
        sucessAdd = "Checagem inserida com sucesso.";
        sucessAddClass = "Sucess";
    }
}

//verificando o perfil do técnico
int perfilChecagem = -1;
if ((request.getParameter("perfilChecagem")) != null) {
    String strPerfil = request.getParameter("perfilChecagem");
    perfilChecagem = Integer.parseInt(strPerfil);
}

List listEquipamentos;
List listProcedimentos;
List listStatusProcedimentosEnt;
List listStatusProcedimentosSai;
List listTecnicos;

ManterUserControl manterUserControl = new ManterUserControl();
ManterCheckEquipamentoControl manterCheckEquipamentoControl = new ManterCheckEquipamentoControl();
listEquipamentos = manterCheckEquipamentoControl.recuperarEquipamentos();
listProcedimentos = manterCheckEquipamentoControl.recuperarProcedimentos(perfilChecagem);
listStatusProcedimentosEnt = manterCheckEquipamentoControl.recuperarStatusProcedimentos("ent");
listStatusProcedimentosSai = manterCheckEquipamentoControl.recuperarStatusProcedimentos("sai");
listTecnicos = manterUserControl.listUsers();

//verificando o perfil do usuário para gerar formulário
String formAvancado = "";

if ((session.getAttribute("userPerfil")) != null) {
    int perfil = Integer.parseInt((String)session.getAttribute("userPerfil"));
    
    if (perfil <= 2) {
        formAvancado = "<tr>" +
                        "<td height='18' class='Title'>Técnico:</td>" +
                        "<td></td>" +
                        "<td>";
                                
        if (listTecnicos != null) {
            String optionStyle = "OptionBody1";
            int countResult = 0;

            formAvancado += "<select name='tecnico' id='tecnico' class='Select' style='width:250px;'>";
            formAvancado += "<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>";

            countResult++;

            for (int i=0; i < listTecnicos.size(); i++) {
                tecnico = (Usuario) listTecnicos.get(i);
                countResult++;

                if (countResult % 2 == 0) {
                    optionStyle = "OptionBody2";
                } else if (countResult % 2 == 1) {
                    optionStyle = "OptionBody1";
                }

                formAvancado += "<option value='" + tecnico.getMatriculaUsuario() + "' class='" + optionStyle + "'>";
                formAvancado += tecnico.getNomeUsuario() + " " + tecnico.getSobrenomeUsuario() + " (" + tecnico.getUser() + ")";
                formAvancado += "</option>";
            }

            formAvancado += "</select>";
        } else {
            formAvancado += "<select name='tecnico' id='tecnico' class='Select' style='width:150px;' disabled='disabled'>";
            formAvancado += "<option>Não há usuários para selecionar.</option>";
            formAvancado += "</select>";
        }
        
        formAvancado += "</td>" +
                        "</tr>";
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
                        <form method="post" action="saveCheckList">
                        <table width="549" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="Title">Check List - Equipamento</td>
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
                                        <%= formAvancado %>
                                        <tr>
                                            <td height="18" class="Title">Equipamento:</td>
                                            <td></td>
                                            <td>
                                                <%
                                                
                                                if (listEquipamentos != null) {
                                                    String optionStyle = "OptionBody1";
                                                    int countResult = 0;

                                                    out.println("<select name='equipamentoSelect' id='equipamentoSelect' class='Select' style='width:250px;'>");
                                                    out.println("<option value='' " + " class='" + optionStyle + "'>-- SELECIONE --</option>");

                                                    countResult++;

                                                    for (int i=0; i < listEquipamentos.size(); i++) {
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
                                                    out.println("<option>Não há usuários para editar.</option>");
                                                    out.println("</select>");
                                                }
                                                
                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Número do Lacre do INMETRO:</td>
                                            <td></td>
                                            <td><input type="text" name="numInmetro" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Número da CPU:</td>
                                            <td></td>
                                            <td><input type="text" name="numSerie" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Número do Gabinete:</td>
                                            <td></td>
                                            <td><input type="text" name="numGabinete" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">UPR:</td>
                                            <td></td>
                                            <td><input type="text" name="numUPR" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Câmera:</td>
                                            <td></td>
                                            <td><input type="text" name="numCamera" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Flash:</td>
                                            <td></td>
                                            <td><input type="text" name="numFlash" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Temperatura de Trabalho - ± 30ºC:</td>
                                            <td></td>
                                            <td><input type="text" name="tempTrabalho" class="Input" onkeypress='return Number(event)' maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Valor de Induntância do Laço em Henry (mH) - 12.75mH - Tolerância de ±5%:</td>
                                            <td></td>
                                            <td><input type="text" name="induntanciaLaco" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Intensidade da Corrente Elétrica (Amperes) - 3A:</td>
                                            <td></td>
                                            <td><input type="text" name="intensidadeCorrente" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Tensão (Volts) Entrada do Anti-Raio - 200V ±10%:</td>
                                            <td></td>
                                            <td><input type="text" name="tensaoEntradaAntiRaio" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Valor da Resistividade da Malha de Aterramento - 10Ohms - Tolerância de + 5%:</td>
                                            <td></td>
                                            <td><input type="text" name="resistividadeMalhaAterramento" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Tensão (Volts) Saída do Anti-Raio - 200V ±10%:</td>
                                            <td></td>
                                            <td><input type="text" name="tensaoSaidaAntiRaio" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Leitura de tensão de saída do Nobreak, com multímetro - 200V ±10%:</td>
                                            <td></td>
                                            <td><input type="text" name="tensaoSaidaNobreak" class="Input" onkeypress='return Number(event)'
                                                   maxlength="20"
                                                   style="width:150px;"></td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Data Inicial (hh:mm dd/mm/aaaa):</td>
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
                                                    <option value='2007'>2007</option>
                                                    <option value='2008'>2008</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Data Final (hh:mm dd/mm/aaaa):</td>
                                            <td></td>
                                            <td>
                                                <select name='dt_fim_hr' id='dt_fim_hr' class='Select'>
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
                                                <select name='dt_fim_min' id='dt_fim_min' class='Select'>
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
                                                <select name='dt_fim_dia' id='dt_fim_dia' class='Select'>
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
                                                <select name='dt_fim_mes' id='dt_fim_mes' class='Select'>
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
                                                <select name='dt_fim_ano' id='dt_fim_ano' class='Select'>
                                                    <option value='0000'>0000</option>
                                                    <option value='2007'>2007</option>
                                                    <option value='2008'>2008</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="18" class="Title">Executou Coleta:</td>
                                            <td></td>
                                            <td><input type="radio" name="execColeta" value="s">Sim&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="radio" name="execColeta" value="n">Não
                                            </td>
                                        </tr>
                                        <tr>
                                            <td height="20" colspan="3"></td>
                                        </tr>
                                        <tr>
                                            <td class="Title">CHECAGEM</td>
                                            <td></td>
                                            <td>
                                                <table width='289' border='0' cellpadding='0' cellspacing='0'>
                                                    <tr>
                                                        <td width="140" class="Title">ENTRADA</td>
                                                        <td width="9"></td>
                                                        <td width="140" class="Title">SAÍDA</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <%
                                        
                                            if (listProcedimentos != null && listStatusProcedimentosEnt != null) {

                                            for (int i=0; i < listProcedimentos.size(); i++) {
                                                procedimento = (Procedimento) listProcedimentos.get(i);

                                                out.println("<tr>");
                                                out.println("<td valign='top'>");
                                                out.println("<table width='250' border='0' cellpadding='0' cellspacing='0'>");
                                                out.println("<tr>");
                                                out.println("<td width='15'>");
                                                out.println("<img src='imagens/topico.gif' height='15' width='15' alt=''>");
                                                out.println("</td>");
                                                out.println("<td height='18' class='Title'>" + procedimento.getNomeProcedimento() + "</td>");
                                                out.println("</tr>");
                                                out.println("</table>");
                                                out.println("</td>");
                                                out.println("<td></td>");
                                                out.println("<td>");
                                                
                                                out.println("<table width='289' border='0' cellpadding='0' cellspacing='0'>");
                                                out.println("<tr>");
                                                out.println("<td width='140'>");
                                                
                                                //entrada
                                                for (int x=0; x < listStatusProcedimentosEnt.size(); x++) {
                                                    statusProcedimento = (StatusProcedimento) listStatusProcedimentosEnt.get(x);
                                                    out.println("<input type='radio' name='" + procedimento.getIdProcedimento() +
                                                    "_ent' value='" + statusProcedimento.getIdStatusProcedimento() +"'>" +
                                                    statusProcedimento.getDescricaoStatusProcedimento() + "<br>");
                                                }

                                                out.println("</td>");
                                                
                                                out.println("<td width='9'>");
                                                out.println("</td>");
                                                
                                                //saida
                                                out.println("<td width='140'>");

                                                for (int x=0; x < listStatusProcedimentosSai.size(); x++) {
                                                    statusProcedimento = (StatusProcedimento) listStatusProcedimentosSai.get(x);
                                                    out.println("<input type='radio' name='" + procedimento.getIdProcedimento() +
                                                    "_sai' value='" + statusProcedimento.getIdStatusProcedimento() +"'>" +
                                                    statusProcedimento.getDescricaoStatusProcedimento() + "<br>");
                                                }
                                                
                                                out.println("</td>");
                                                out.println("</tr>");
                                                out.println("</table>");
                                                
                                                out.println("</td>");
                                                
                                                out.println("</tr>");
                                                out.println("<tr>");
                                                out.println("<td colspan='3' height='10'></td>");
                                                out.println("</tr>");
                                                }

                                            }
                                        
                                        %>
                                        
                                        <tr>
                                            <td colspan="3" height="10"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" class="Title">Observações (opcional):</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <textarea name="obs" class="TextArea" style="width:520px;height:100px;"></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3"><b>Obs.:</b> Todos os campos são de preenchimento obrigatório, exceto o campo de observações.</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" class="<%= sucessAddClass %>" align="center"><%= sucessAdd %></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="20"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <table width="549" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>
                                                        <td align="right">
                                                            <input type="hidden" name="perfilChecagem" value="<%= perfilChecagem %>">
                                                        <input type="submit" value="Salvar" id="Salvar" class="Button" onMouseOut="buttonOn(this)" onMouseOver="buttonOver(this)" style="width: 100px;">                                        </td>
                                                        <td width="50"></td>
                                                    </tr>
                                            </table>
                                        </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
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