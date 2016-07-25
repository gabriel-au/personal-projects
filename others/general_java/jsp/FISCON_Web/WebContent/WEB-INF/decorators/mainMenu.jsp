<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:url id="sair" action="logout" namespace="/administracao" />
<%@page import="java.util.Calendar"%>
<html>
<head>
<title><decorator:title default="FISCON" /></title>
<!--
<link
	href="<s:url value='/styles/styles.css' encode='false' includeParams='none'/>"
	rel="stylesheet" type="text/css" media="all" />
-->
<link rel="stylesheet" type="text/css"
	href="<s:url value='/styles/default.css' encode='false' includeParams='none'/>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<decorator:head />
</head>

<SCRIPT type="text/javascript">
	var mensagem1 = '<s:property value="mensagem" escape="false"/>';
	if (mensagem1 != '') {
		alert(mensagem1);
	}
</SCRIPT>
<%
	Calendar dataNow = Calendar.getInstance();
	int horaNow = dataNow.get(Calendar.HOUR_OF_DAY);
	int month = dataNow.get(Calendar.MONTH) + 1;
	int year = dataNow.get(Calendar.YEAR);
	String saudacao = "";

	if (horaNow >= 0 && horaNow <= 11) {
		saudacao = "Bom dia, ";
	} else if (horaNow >= 12 && horaNow <= 17) {
		saudacao = "Boa tarde, ";
	} else if (horaNow >= 18 && horaNow <= 23) {
		saudacao = "Boa noite, ";
	}
%>
<body>
<div class="topo">
<div class="logoEasyForm"><img
	src="<s:url value='/img/logo.png' encode='false' includeParams='none'/>" /></div>
</div>
<table cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td rowspan="2" style="vertical-align: top;">
		<div class="left"><br />
		<ul>
			<s:iterator value="menus">
				<s:if test='#acao.equals(uri)'>
					<li><s:a href="%{index}">
						<a href="<%=request.getContextPath()%><s:property value="acao" />"><s:property
							value="nome" /></a>
					</s:a></li>
				</s:if>
				<s:else>
					<li><a
						href="<%=request.getContextPath()%><s:property value="acao" />"><s:property
						value="nome" /></a></li>
				</s:else>
			</s:iterator>
		</ul>
		<br />
		<ul>
			<li><s:a href="%{sair}">Sair</s:a></li>
		</ul>
		</div>
		</td>
		<td style="padding-bottom: 20px">
		<div class="content"></div>
		</td>
	</tr>

	<tr>
		<td class="conteudo">
		<div style=""><decorator:body /></div>
		</td>
	</tr>
</table>
<div class="footer"><!--<a href="http://www.primems.com.br">#{msg['prime']}</a>-->
</div>
</body>
</html>