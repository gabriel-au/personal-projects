
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<sx:head />

<s:url id="sair" action="logout" />
<s:url id="index" action="index" />
<s:url id="sobre" action="sobre" />
<s:url id="contato" action="contato" />
<%
	//identificar o user-agent
	String agent = request.getHeader("user-agent");

	//passar para minusculo
	agent = agent.toLowerCase();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><decorator:title default="FISCON" /></title>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/default.css' encode='false' includeParams='none'/>" />
<style type="text/css" media="screen">
<!--
body {
	height: 100%;
	padding: 0;
	margin: 0;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

.fieldset {
	margin-left: auto;
	margin-right: auto;
	width: 255px;
}

.msg {
	margin-left: auto;
	margin-right: auto;
	width: 250px;
	border: none;
}
-->
</style>
</head>

<SCRIPT type="text/javascript">
	var mensagem1 = '<s:property value="mensagem" escape="false"/>';
	if (mensagem1 != '') {
		alert(mensagem1);
	}
</SCRIPT>

<body>

<div class="topo">
<div class="logoEasyForm"><img
	src="<s:url value='/img/logo.png' encode='false' includeParams='none'/>" /></div>

<div class="logoPrime"><img src="img/prime.png" /></div>
</div>

<div style="margin-top: 180px; text-align: center;" class="fieldset"><s:form
	focusElement="user" theme="simple" action="login" namespace="/">
	<table style="background: #f5f5f5;">
		<tr>
			<th
				style="background: #4a75b5; color: #FFFFFF; padding-bottom: 2px; padding-top: 2px; padding-right: 5px; padding-left: 5px; font-size: 9pt;">Login</th>
		<tr>
		<tr>
		<td><table style="padding: 15px; padding-left:20px;">
				<tr>
			<td class="colunaSenha"><label>Usuário: </label></td>
			<td class="colunaSenha2"><label> <s:textfield
				label="Usuário" id="user" key="pojo.username" /> </label></td>
		</tr>
		<tr>
			<td class="colunaSenha"><label>Senha:</label></td>
			<td class="colunaSenha2"><label> <s:password
				label="Senha" key="pojo.passwordWeb" /> </label></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><label> &nbsp;&nbsp;&nbsp;<s:submit
				cssClass="button" value="Entrar" /> </label></td>
		</tr>
		</table></td>
		</tr>
	</table>
</s:form></div>

<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />

<div class="footer"><!--<a href="http://www.primems.com.br">#{msg['prime']}</a>-->
</div>
</body>
</html>



<%--
	
<center>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td><img alt="Logotipo"
			src="<s:url value='/imagens/logo_fiscon.gif' encode='false' includeParams='none'/>">
		</td>
		<td width="100px"><s:form action="login" namespace="/">
			
			
			
		</s:form></td>
	</tr>
</table>
<table width="100%" cellpadding="0" cellspacing="0">
	<tr background="<s:url value='/imagens/fiscon_cabecalho.gif' encode='false' includeParams='none'/>">
		<td>&nbsp;</td>
	</tr>
</table>
<div id="header">
<ul>

	<s:if test='#index.equals(uri)'>
		<li id="current"><s:a href="%{index}">
			<span>Home</span>
		</s:a></li>
	</s:if>
	<s:else>
		<li><s:a href="%{index}"><span>Home</span></s:a></li>
	</s:else>

	<s:if test='#contato.equals(uri)'>
		<li id="current"><s:a href="%{contato}"><span>Contato</span></s:a></li>
	</s:if>
	<s:else>
		<li><s:a href="%{contato}"><span>Contato</span></s:a></li>
	</s:else>

	<s:if test='#sobre.equals(uri)'>
		<li id="current"><s:a href="%{sobre}"><span>Sobre</span></s:a></li>
	</s:if>
	<s:else>
		<li><s:a href="%{sobre}">
			<span>Sobre</span>
		</s:a></li>
	</s:else>
</ul>
</div>


<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top"><decorator:body /></td>
	</tr>
	<tr>
		<td align="center"><small>Copyright &copy; 2010 - PRIME
		LTDA.<br>
		SITRAN ELETRÔNICA LTDA.</small></td>
	</tr>
</table>

</center>
</body>
</html> --%>